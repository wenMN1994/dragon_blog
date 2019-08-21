package com.dragon.project.blog.comments.service;

import com.dragon.project.blog.comments.domain.CommentsReply;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/17 11:26
 * @description：回复评论服务层接口
 * @modified By：
 * @version: 1.0.0
 */
public interface CommentsReplyService {
    /**
     * 插入评论信息数据
     * @param commentsReply
     * @return
     */
    int insertCommentReply(CommentsReply commentsReply);
}
