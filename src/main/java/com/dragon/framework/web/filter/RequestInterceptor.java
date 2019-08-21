package com.dragon.framework.web.filter;

import com.dragon.common.utils.IpUtils;
import com.dragon.project.monitor.blacklist.domain.Blacklist;
import com.dragon.project.monitor.blacklist.service.BlacklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 用户请求的拦截器
 * @modified By：
 * @version: 1.0.0
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    BlacklistService blacklistService;

    /**
     * 请求处理之前调用（Controller方法之前）
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    /**
     * 请求处理之后调用（controller调用方法之后）
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("当前访问的IP {}", IpUtils.getIpAddr(request));


        Blacklist blacklist = blacklistService.selectBlacklistByIp(IpUtils.getIpAddr(request));

        if (blacklist != null) {
            log.warn("拦截到黑名单IP [{}]请求！", IpUtils.getIpAddr(request));
            blacklistService.updateNewestBlacklist(blacklist.getBlacklistId(), request.getRequestURI());
            if (modelAndView == null) {
                ModelAndView modelAndView1 = new ModelAndView();
                modelAndView1.setViewName("/error/blacklist");
            } else {
                modelAndView.setViewName("/error/blacklist");
            }
        }
    }

    /**
     * 整个请求完成之后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
