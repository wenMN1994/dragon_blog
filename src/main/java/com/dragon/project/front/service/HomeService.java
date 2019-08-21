package com.dragon.project.front.service;

import com.dragon.project.blog.blog.domain.Blog;
import com.dragon.project.front.domain.Archives;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/26 14:54
 * @description：前端首页服务层接口
 * @modified By：
 * @version: 1.0.0
 */
public interface HomeService {
    /**
     * 获取最新的blog
     * @param blog
     * @return 获取最新的blog集合
     */
    List<Blog> selectFrontBlogList(Blog blog);

    /**
     * 获取归档信息
     *
     * @return 归档集合
     */
    List<Archives> selectArchives();
}
