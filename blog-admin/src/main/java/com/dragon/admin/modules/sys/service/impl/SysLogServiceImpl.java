/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dragon.admin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.admin.modules.sys.dao.SysLogDao;
import com.dragon.admin.modules.sys.entity.SysLogEntity;
import com.dragon.admin.modules.sys.service.SysLogService;
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
@Service("sysLogService")
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String)params.get("key");

        IPage<SysLogEntity> page = this.page(
            new Query<SysLogEntity>().getPage(params),
            new QueryWrapper<SysLogEntity>().like(StringUtils.isNotBlank(key),"username", key)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils getLog(Map<String, Object> params) {
        String type = (String)params.get("logType");
        String orderByColumn = (String)params.get("orderByColumn");
        String isAsc = (String)params.get("isAsc");
        Page<SysLogEntity> page = new Page<SysLogEntity>();
        Long pageSize = Long.parseLong((String) params.get("pageSize"));
        page.setSize(pageSize);
        IPage<SysLogEntity> result = sysLogDao.getLog(page, type, orderByColumn, isAsc);
        return new PageUtils(result);
    }
}
