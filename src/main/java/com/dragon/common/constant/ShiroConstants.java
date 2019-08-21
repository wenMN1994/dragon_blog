package com.dragon.common.constant;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/5/27 22:06
 * @description： Shiro通用常量
 * @modified By：
 * @version: 1.0.0
 */
public interface ShiroConstants {
    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "currentUser";

    /**
     * 用户名
     */
    String CURRENT_USERNAME = "username";

    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "errorMsg";

    /**
     * 编码格式
     */
    String ENCODING = "UTF-8";

    /**
     * 当前在线会话
     */
    String ONLINE_SESSION = "online_session";

    /**
     * 验证码key
     */
    String CURRENT_CAPTCHA = "captcha";

    /**
     * 验证码开关
     */
    String CURRENT_ENABLED = "captchaEnabled";

    /**
     * 验证码开关
     */
    String CURRENT_TYPE = "captchaType";

    /**
     * 验证码
     */
    String CURRENT_VALIDATECODE = "validateCode";

    /**
     * 验证码错误
     */
    String CAPTCHA_ERROR = "captchaError";
}
