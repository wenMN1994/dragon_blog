package com.dragon.admin.common.aspect;

import com.dragon.admin.common.annotation.SysLog;
import com.dragon.admin.modules.sys.form.SysLoginForm;
import com.dragon.common.utils.Constant;
import com.dragon.common.utils.HttpContextUtils;
import com.dragon.common.utils.IPUtils;
import com.dragon.admin.modules.sys.entity.SysLogEntity;
import com.dragon.admin.modules.sys.entity.SysUserEntity;
import com.dragon.admin.modules.sys.service.SysLogService;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 *
 * @author
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.dragon.admin.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.operation());
			sysLog.setLogType(syslog.logType());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args);
			sysLog.setParams(params);
		}catch (Exception e){

		}

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		//用户名
		if(!Constant.SYS_LOG_TYPE_LOGIN.equals(syslog.logType())){
			String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
			sysLog.setUsername(username);
		}else {
			if(args != null &&  args.length > 0){
				SysLoginForm sysLoginForm = (SysLoginForm) args[0];
				if(sysLoginForm != null){
					sysLog.setUsername(sysLoginForm.getUsername());
				}else {
					sysLog.setUsername("未知");
				}
			}else {
				sysLog.setUsername("未知");
			}

		}

		sysLog.setTime(time);
		sysLog.setCreateTime(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}
}
