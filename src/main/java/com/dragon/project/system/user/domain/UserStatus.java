package com.dragon.project.system.user.domain;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 用户状态
 * @modified By：
 * @version: 1.0.0
 */
public enum UserStatus {
    /**
     * 正常状态
     */
    OK("0", "正常"),
    /**
     * 停用状态
     */
    DISABLE("1", "停用"),
    /**
     * 已删除状态
     */
    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
