package com.dragon.project.blog.category.domain;

import com.dragon.framework.web.domain.BaseEntity;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/12 21:24
 * @description： 分类实体类
 * @modified By：
 * @version: 1.0.0
 */
public class Category extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer categoryId;
    /**
     * 分类的名称
     */
    private String categoryTitle;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 描述
     */
    private String description;
    /**
     * 是否推荐，0表示不推荐，1表示推荐
     */
    private String support;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
}