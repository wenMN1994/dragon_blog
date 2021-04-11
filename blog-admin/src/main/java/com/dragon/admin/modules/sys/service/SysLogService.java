package com.dragon.admin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.common.utils.PageUtils;
import com.dragon.admin.modules.sys.entity.SysLogEntity;

import java.util.Map;

/**
 * 系统日志
 *
 * @author Dragon Wen
 */
public interface SysLogService extends IService<SysLogEntity> {

    /**
     * 获取日志
     * @param params
     * @return
     */
    PageUtils getLog(Map<String, Object> params);
}
