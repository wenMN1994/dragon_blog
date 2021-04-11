package com.dragon.admin.modules.sys.controller;

import com.dragon.admin.modules.sys.service.SysLogService;
import com.dragon.common.utils.PageUtils;
import com.dragon.common.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Dragon Wen
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:log:list")
	public Result list(@RequestParam Map<String, Object> params){
		PageUtils page = sysLogService.getLog(params);

		return Result.ok().put("page", page);
	}
	
}
