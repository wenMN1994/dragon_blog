package com.dragon.common.exception.user;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/1 1:12
 * @description： 验证码错误异常类
 * @modified By：
 * @version: 1.0.0
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
