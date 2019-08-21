package com.dragon.project.blog.blog.domain;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/16 21:51
 * @description： 博客和标签
 * @modified By：
 * @version: 1.0.0
 */
public class BlogTag {
    private Integer blogId;
    private Integer tagId;

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}