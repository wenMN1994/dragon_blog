/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dragon.admin.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.admin.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {

    /**
     * 获取日志
     *
     * @param userName 用户名
     * @param operation 用户操作
     * @param type 日志类型
     * @param orderByColumn 排序字段
     * @param isAsc 排序方式
     * @return
     */
    IPage<SysLogEntity> getLog(@Param("page") Page<?> page, @Param("userName") String userName, @Param("operation") String operation, @Param("type") String type, @Param("orderByColumn") String orderByColumn, @Param("isAsc") String isAsc);
}
