package com.dragon.project.log.logininfor.mapper;

import com.dragon.project.dashboard.domain.LogMessage;
import com.dragon.project.log.logininfor.domain.Logininfor;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/8 07:13
 * @description： 系统访问日志情况信息 数据层
 * @modified By：
 * @version: 1.0.0
 */
public interface LogininforMapper {
    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    public void insertLogininfor(Logininfor logininfor);

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    public List<Logininfor> selectLogininforList(Logininfor logininfor);

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteLogininforByIds(String[] ids);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    public int cleanLogininfor();

    /**
     * 获取最近3条登录信息
     * @return
     */
    List<LogMessage> selectLoginInforData();
}
