package com.dragon.project.front.service.impl;

import com.dragon.project.blog.blog.domain.Blog;
import com.dragon.project.blog.blog.mapper.BlogMapper;
import com.dragon.project.blog.comments.mapper.CommentsInfoMapper;
import com.dragon.project.front.domain.Archives;
import com.dragon.project.front.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/26 14:55
 * @description：前端首页服务层接口实现类
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentsInfoMapper commentsInfoMapper;

    @Override
    public List<Blog> selectFrontBlogList(Blog blog) {
        List<Blog> blogList = blogMapper.selectFrontBlogList(blog);
        for (Blog blogTemp : blogList) {
            blogTemp.setCommentCount(commentsInfoMapper.commentsInfoCountByOwnerId(blogTemp.getBlogId()));
        }
        return blogList;
    }

    @Override
    public List<Archives> selectArchives() {
        List<Archives> archives = blogMapper.selectArchivesDateAndCount();
        for (Archives archive : archives) {
            archive.setBlogs(blogMapper.selectBlogByCreateTime(archive.getDate()));
        }
        return archives;
    }
}
