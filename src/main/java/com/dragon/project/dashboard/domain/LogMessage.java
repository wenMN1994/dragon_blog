package com.dragon.project.dashboard.domain;

import java.util.Date;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/7 22:14
 * @description： dashboard展示的最新消息
 * @modified By：
 * @version: 1.0.0
 */
public class LogMessage {
    /**
     * Date String ,如“刚刚”等
     */
    String dateStr;
    /**
     * 具体时间
     */
    Date date;
    /**
     * 消息
     */
    String message;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
