package com.dragon.admin.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.admin.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务日志
 *
 * @author
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {

    /**
     * 获取定时任务日志
     *
     * @param page
     * @param jobId
     * @param orderByColumn
     * @param isAsc
     * @return
     */
    IPage<ScheduleJobLogEntity> getJobLogList(@Param("page") Page<ScheduleJobLogEntity> page, @Param("jobId") String jobId, @Param("orderByColumn") String orderByColumn, @Param("isAsc") String isAsc);
}
