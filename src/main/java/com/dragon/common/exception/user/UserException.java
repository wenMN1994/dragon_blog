package com.dragon.common.exception.user;

import com.dragon.common.exception.base.BaseException;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 用户信息异常类
 * @modified By：
 * @version: 1.0.0
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }

}
