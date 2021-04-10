/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dragon.admin.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.common.utils.PageUtils;
import com.dragon.admin.modules.sys.entity.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取登录日志
     * @param params
     * @return
     */
    PageUtils getLog(Map<String, Object> params);
}
