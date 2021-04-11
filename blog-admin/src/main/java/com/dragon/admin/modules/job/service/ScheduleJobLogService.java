package com.dragon.admin.modules.job.service;

import com.dragon.admin.modules.job.entity.ScheduleJobLogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.common.utils.PageUtils;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils getJobLogList(Map<String, Object> params);
	
}
