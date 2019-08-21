package com.dragon.project.log.visitorLog.service.impl;

import com.dragon.project.dashboard.domain.BusinessCommonData;
import com.dragon.project.log.visitorLog.domain.VisitLog;
import com.dragon.project.log.visitorLog.mapper.VisitLogMapper;
import com.dragon.project.log.visitorLog.service.VisitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/21 15:40
 * @description： 前端访问记录服务接口实现前端访问记录服务接口实现
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {

    @Autowired
    VisitLogMapper visitLogMapper;

    @Override
    public List<VisitLog> selectVisitLogList(VisitLog visitLog) {
        return visitLogMapper.selectVisitLogList(visitLog);
    }

    @Override
    public int deleteVisitLogByIds(Integer[] ids) {
        return visitLogMapper.deleteVisitLogByIds(ids);
    }

    @Override
    public VisitLog selectVisitLogById(Integer visitId) {
        return visitLogMapper.selectVisitLogById(visitId);
    }

    @Override
    public void cleanVisitLog() {
        visitLogMapper.cleanVisitLog();
    }

    @Override
    public void insertVisitLog(VisitLog visitLog) {
        visitLogMapper.insertVisitLog(visitLog);
    }

    @Override
    public Integer selectVisitLogTotalCount() {
        return visitLogMapper.selectVisitLogTotalCount();
    }

    @Override
    public Integer selectVisitLogTodayCount() {
        return visitLogMapper.selectVisitLogTodayCount();
    }

    @Override
    public List<BusinessCommonData> selectSpiderData() {
        return visitLogMapper.selectSpiderData();
    }

    @Override
    public List<BusinessCommonData> selectVisitLogData(String startTime, String endTime) {
        if (startTime != null && "undefined".equals(startTime)) {
            startTime = null;
        }
        if (endTime != null && "undefined".equals(endTime)) {
            endTime = null;
        }
        return visitLogMapper.selectVisitLogData(startTime, endTime);
    }
}
