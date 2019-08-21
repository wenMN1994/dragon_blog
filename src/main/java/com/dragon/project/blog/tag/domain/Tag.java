package com.dragon.project.blog.tag.domain;

import com.dragon.framework.web.domain.BaseEntity;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 06:21
 * @description： 标签实体类
 * @modified By：
 * @version: 1.0.0
 */
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;


    private Integer tagId;

    /**
     * 标签内容
     */
    private String tagTitle;
    /**
     * 关联的博客个数
     */
    private int count;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}