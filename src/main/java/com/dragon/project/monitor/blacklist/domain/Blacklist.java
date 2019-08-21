package com.dragon.project.monitor.blacklist.domain;

import com.dragon.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/4 09:37
 * @description： 黑名单实体类
 * @modified By：
 * @version: 1.0.0
 */
public class Blacklist extends BaseEntity {
    private Integer blacklistId;
    private String ipAddr;
    private String description;
    private Integer count;
    private Date lastAccessTime;
    private String lastAccessUrl;

    public Integer getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(Integer blacklistId) {
        this.blacklistId = blacklistId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getLastAccessUrl() {
        return lastAccessUrl;
    }

    public void setLastAccessUrl(String lastAccessUrl) {
        this.lastAccessUrl = lastAccessUrl;
    }
}
