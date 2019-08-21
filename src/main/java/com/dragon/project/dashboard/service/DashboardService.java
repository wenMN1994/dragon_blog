package com.dragon.project.dashboard.service;

import com.dragon.project.dashboard.domain.BusinessCommonData;
import com.dragon.project.dashboard.domain.LogMessage;
import com.dragon.project.dashboard.domain.VisitCount;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/7 22:19
 * @description： dashboard接口
 * @modified By：
 * @version: 1.0.0
 */
public interface DashboardService {
    /**
     * 获取爬虫访问记录
     *
     * @return 爬虫数据实体类
     */
    List<BusinessCommonData> selectSpiderData();

    /**
     * 获取访问量的数据
     *
     * @return VisitCount实体类
     */
    List<VisitCount> getVisitData();

    /**
     * 获取最近3条登录信息
     * @return
     */
    List<LogMessage> selectLogMessage();
}
