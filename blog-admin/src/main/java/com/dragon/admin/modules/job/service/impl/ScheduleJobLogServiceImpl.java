package com.dragon.admin.modules.job.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.admin.modules.job.dao.ScheduleJobLogDao;
import com.dragon.admin.modules.job.entity.ScheduleJobLogEntity;
import com.dragon.admin.modules.job.service.ScheduleJobLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.common.utils.PageUtils;
import com.dragon.admin.common.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author DragonWen
 */
@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity> implements ScheduleJobLogService {

	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;

	@Override
	public PageUtils getJobLogList(Map<String, Object> params) {
		String jobId = (String)params.get("jobId");
		Long pageNum = Long.parseLong((String) params.get("pageNum"));
		Long pageSize = Long.parseLong((String) params.get("pageSize"));
		String orderByColumn = (String)params.get("orderByColumn");
		String isAsc = (String)params.get("isAsc");
		Page<ScheduleJobLogEntity> page = new Page<>();
		page.setCurrent(pageNum);
		page.setSize(pageSize);
		IPage<ScheduleJobLogEntity> result = scheduleJobLogDao.getJobLogList(page, jobId, orderByColumn, isAsc);

		return new PageUtils(result);
	}

}
