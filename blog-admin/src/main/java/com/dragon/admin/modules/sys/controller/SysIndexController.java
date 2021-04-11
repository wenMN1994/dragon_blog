package com.dragon.admin.modules.sys.controller;

import com.dragon.admin.modules.job.service.ScheduleJobLogService;
import com.dragon.admin.modules.sys.service.SysLogService;
import com.dragon.common.utils.PageUtils;
import com.dragon.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2021/4/10 16:50
 * @description：后台首页Controller
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/sys/index")
public class SysIndexController {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    /**
     * 获取访问日志
     * @param params
     * @return
     */
    @GetMapping("/visitLog")
    public Result getVisitLog(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.getLog(params);
        return Result.ok().put("page", page);
    }

    /**
     * 获取登录日志
     * @param params
     * @return
     */
    @GetMapping("/loginLog")
    public Result getLoginLog(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.getLog(params);
        return Result.ok().put("page", page);
    }

    /**
     * 获取操作日志
     * @param params
     * @return
     */
    @GetMapping("/operateLog")
    public Result getOperateLog(@RequestParam Map<String, Object> params){
        PageUtils page = sysLogService.getLog(params);
        return Result.ok().put("page", page);
    }

    /**
     * 获取任务日志
     * @param params
     * @return
     */
    @GetMapping("/jobLog")
    public Result getJobLog(@RequestParam Map<String, Object> params){
        PageUtils page = scheduleJobLogService.getJobLogList(params);
        return Result.ok().put("page", page);
    }
}
