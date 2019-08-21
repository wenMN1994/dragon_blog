package com.dragon.project.chart.business.domain;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/01 16:11
 * @description： 业务图标实体类
 * @modified By：
 * @version: 1.0.0
 */
public class Business {
    String name;
    Object value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
