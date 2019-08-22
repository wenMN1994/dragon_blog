package com.dragon.project.blog.comments.mapper;

import com.dragon.project.blog.comments.domain.CommentsReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/17 11:25
 * @description：回复评论处理数据层
 * @modified By：
 * @version: 1.0.0
 */
public interface CommentsReplyMapper {
    /**
     * 插入回复信息数据
     * @param commentsReply
     * @return
     */
    int insertCommentReply(CommentsReply commentsReply);

    /**
     * 根据评论主表ID查询回复内容
     * @param commentId
     * @return
     */
    List<CommentsReply> selectCommentsReplyByCommentId(@Param("commentId") Integer commentId);

    /**
     * 查询回复列表
     * @param commentsReply
     * @return
     */
    List<CommentsReply> selectCommentsReplyList(CommentsReply commentsReply);

    /**
     *
     * @param id
     * @return
     */
    List<CommentsReply> selectCommentsReplyListById(Integer id);
}
