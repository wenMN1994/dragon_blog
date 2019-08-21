package com.dragon.project.log.visitorLog.domain;

import com.dragon.framework.web.domain.BaseEntity;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/21 15:19
 * @description： 前端访问日志实体类
 * @modified By：
 * @version: 1.0.0
 */
public class VisitLog extends BaseEntity {
    private Integer visitId;
    /**
     * IP地址
     */
    private String ipAddr;
    /**
     * ip地理位置
     */
    private String location;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统类型
     */
    private String os;
    /**
     * 爬虫
     */
    private String spider;
    /**
     * 请求的地址
     */
    private String requestUrl;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 访问的模块
     */
    private String title;
    /**
     * 访问状态
     */
    private Integer status;

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getSpider() {
        return spider;
    }

    public void setSpider(String spider) {
        this.spider = spider;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
