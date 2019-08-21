package com.dragon.common.exception.user;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/24 09:30
 * @description： 角色锁定异常类
 * @modified By：
 * @version: 1.0.0
 */
public class RoleBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super("role.blocked", null);
    }

}
