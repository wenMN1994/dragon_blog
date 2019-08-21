package com.dragon.project.tool.tool.domain;

import com.dragon.framework.web.domain.BaseEntity;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 常用工具(Tool)实体类
 * @modified By：
 * @version: 1.0.0
 */
public class Tool extends BaseEntity {
    private static final long serialVersionUID = -29134451060966794L;
    
    private Integer toolId;
    //工具名称
    private String toolName;
    //工具描述	
    private String description;
    //显示状态，1表示显示，0表示不显示
    private String display;
    //链接地址
    private String url;
    
    private String type;
    //头像地址
    private String headImg;
    //权重
    private Integer weight;
    
    private Integer toolCategoryId;

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getToolCategoryId() {
        return toolCategoryId;
    }

    public void setToolCategoryId(Integer toolCategoryId) {
        this.toolCategoryId = toolCategoryId;
    }
}