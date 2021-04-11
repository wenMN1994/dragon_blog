package com.dragon.admin.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.admin.modules.sys.dao.SysLogDao;
import com.dragon.admin.modules.sys.entity.SysLogEntity;
import com.dragon.admin.modules.sys.service.SysLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.common.utils.PageUtils;
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
    public PageUtils getLog(Map<String, Object> params) {
        String userName = (String)params.get("userName");
        String operation = (String)params.get("operation");
        Long pageNum = Long.parseLong((String) params.get("pageNum"));
        Long pageSize = Long.parseLong((String) params.get("pageSize"));
        String type = (String)params.get("logType");
        String orderByColumn = (String)params.get("orderByColumn");
        String isAsc = (String)params.get("isAsc");
        Page<SysLogEntity> page = new Page<SysLogEntity>();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        IPage<SysLogEntity> result = sysLogDao.getLog(page, userName, operation, type, orderByColumn, isAsc);
        return new PageUtils(result);
    }
}
