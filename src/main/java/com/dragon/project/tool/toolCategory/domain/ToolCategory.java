package com.dragon.project.tool.toolCategory.domain;

import com.dragon.framework.web.domain.BaseEntity;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 工具的分类(ToolCategory)实体类
 * @modified By：
 * @version: 1.0.0
 */
public class ToolCategory extends BaseEntity {
    private static final long serialVersionUID = -63065711007716650L;

    private Integer toolCategoryId;
    //分类的标题
    private String toolCategoryTitle;
    //权重
    private Integer weight;
    //是否显示，1表示显示，0表示不显示
    private String display;

    public Integer getToolCategoryId() {
        return toolCategoryId;
    }

    public void setToolCategoryId(Integer toolCategoryId) {
        this.toolCategoryId = toolCategoryId;
    }

    public String getToolCategoryTitle() {
        return toolCategoryTitle;
    }

    public void setToolCategoryTitle(String toolCategoryTitle) {
        this.toolCategoryTitle = toolCategoryTitle;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}