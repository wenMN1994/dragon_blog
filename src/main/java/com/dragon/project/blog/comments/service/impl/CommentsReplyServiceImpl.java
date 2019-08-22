package com.dragon.project.blog.comments.service.impl;

import com.dragon.project.blog.comments.domain.CommentsReply;
import com.dragon.project.blog.comments.mapper.CommentsReplyMapper;
import com.dragon.project.blog.comments.service.CommentsReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/17 11:27
 * @description：回复评论服务层接口实现
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class CommentsReplyServiceImpl implements CommentsReplyService {

    @Autowired
    CommentsReplyMapper commentsReplyMapper;

    @Override
    public int insertCommentReply(CommentsReply commentsReply) {
        return commentsReplyMapper.insertCommentReply(commentsReply);
    }

    @Override
    public List<CommentsReply> selectCommentsReplyList(CommentsReply commentsReply) {
        return commentsReplyMapper.selectCommentsReplyList(commentsReply);
    }

    @Override
    public List<CommentsReply> selectCommentsReplyListById(Integer id) {
        return commentsReplyMapper.selectCommentsReplyListById(id);
    }
}
