package com.dragon.project.dashboard.domain;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/7 22:10
 * @description： 访问总量实体类
 * @modified By：
 * @version: 1.0.0
 */
public class VisitCount {
    /**
     * 访问日期
     */
    private String date;
    /**
     * 访问量
     */
    private Integer value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
