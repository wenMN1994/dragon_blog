package com.dragon.project.dashboard.service.impl;

import com.dragon.common.utils.DateUtils;
import com.dragon.project.dashboard.domain.BusinessCommonData;
import com.dragon.project.dashboard.domain.LogMessage;
import com.dragon.project.dashboard.domain.VisitCount;
import com.dragon.project.dashboard.service.DashboardService;
import com.dragon.project.log.jobLog.domain.JobLog;
import com.dragon.project.log.jobLog.mapper.JobLogMapper;
import com.dragon.project.log.logininfor.mapper.LogininforMapper;
import com.dragon.project.log.operlog.domain.OperLog;
import com.dragon.project.log.operlog.mapper.OperLogMapper;
import com.dragon.project.log.visitorLog.domain.VisitLog;
import com.dragon.project.log.visitorLog.mapper.VisitLogMapper;
import com.dragon.project.system.dict.mapper.DictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/7 22:20
 * @description： dashboard接口实现类
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    VisitLogMapper visitLogMapper;
    @Autowired
    OperLogMapper operLogMapper;
    @Autowired
    LogininforMapper logininforMapper;
    @Autowired
    JobLogMapper jobLogMapper;
    @Autowired
    DictDataMapper dictDataMapper;


    @Override
    public List<BusinessCommonData> selectSpiderData() {
        return visitLogMapper.selectSpiderData();
    }

    @Override
    public List<VisitCount> getVisitData() {
        List<String> pastDaysList = DateUtils.getPastDaysList(7);
        List<VisitCount> visitCounts = new ArrayList<>();
        for (String date : pastDaysList) {
            Integer visitorCountByVisitTime = visitLogMapper.selectVisitCountByCreateTime(date);
            VisitCount visitCount = new VisitCount();
            visitCount.setDate(date);
            visitCount.setValue(visitorCountByVisitTime);
            visitCounts.add(visitCount);
        }
        return visitCounts;
    }

    @Override
    public List<LogMessage> selectLogMessage() {
        List<LogMessage> messages = new ArrayList<>();
        //登录日志
        List<LogMessage> logMessages = logininforMapper.selectLoginInforData();
        if (logMessages != null && logMessages.size() != 0) {
            for (LogMessage logMessage : logMessages) {
                LogMessage temp = new LogMessage();
                temp.setDateStr(DateUtils.showTime(logMessage.getDate()));
                temp.setDate(logMessage.getDate());
                temp.setMessage(logMessage.getMessage() + " 登录了系统");
                messages.add(temp);
            }
        }
        //操作日志
        List<OperLog> operLogs = operLogMapper.selectOperLogData();
        if (operLogs != null && operLogs.size() != 0) {
            for (OperLog operLog : operLogs) {
                LogMessage temp = new LogMessage();
                temp.setDateStr(DateUtils.showTime(operLog.getOperTime()));
                temp.setDate(operLog.getOperTime());
                String sys_oper_type = dictDataMapper.selectDictLabel("sys_oper_type", operLog.getBusinessType().toString());
                temp.setMessage(operLog.getOperName() + " 对 " + operLog.getTitle() + " 进行了 " + sys_oper_type + " 操作");
                messages.add(temp);
            }
        }
        //访问日志
        List<VisitLog> visitLogs = visitLogMapper.selectVisitData();
        if (visitLogs != null && visitLogs.size() != 0) {
            for (VisitLog visitLog : visitLogs) {
                LogMessage temp = new LogMessage();
                temp.setDate(visitLog.getCreateTime());
                temp.setDateStr(DateUtils.showTime(visitLog.getCreateTime()));
                temp.setMessage(visitLog.getIpAddr() + " 访问了 " + visitLog.getTitle() + " 模块");
                messages.add(temp);
            }
        }
        //调度日志
        List<JobLog> jobLogs = jobLogMapper.selectJobData();
        for (JobLog jobLog : jobLogs) {
            LogMessage temp = new LogMessage();
            temp.setDateStr(DateUtils.showTime(jobLog.getCreateTime()));
            temp.setDate(jobLog.getCreateTime());
            temp.setMessage(jobLog.getJobName() + " 启动运行");
            messages.add(temp);
        }
        Collections.sort(messages, (logMessage1, logMessage2) -> {
            long time = logMessage1.getDate().getTime() - logMessage2.getDate().getTime();
            if (time < 0) {
                return 1;
            } else if (time > 0) {
                return -1;
            } else {
                return 0;
            }
        });
        return messages;
    }

}
