package com.dragon.project.blog.comments.service;

import com.dragon.project.blog.comments.domain.CommentsInfo;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/17 11:22
 * @description：新建评论服务层接口
 * @modified By：
 * @version: 1.0.0
 */
public interface CommentsInfoService {
    /**
     * 插入评论信息
     * @param commentsInfo
     * @return
     */
    int insertCommentInfo(CommentsInfo commentsInfo);

    /**
     * 根据被评论对象ID查询评论信息
     * @param ownerId
     * @return
     */
    List<CommentsInfo> selectCommentsInfoByOwnerId(Integer ownerId);

    /**
     * 根据id更新评论信息
     * @param commentsInfo
     * @return
     */
    int updateCommentsInfo(CommentsInfo commentsInfo);

    /**
     * 根据评论ID查询评论信息
     * @param id
     * @return
     */
    CommentsInfo selectCommentsInfoById(Integer id);

    /**
     * 根据文章id统计评论个数
     * @param ownerId
     * @return
     */
    int commentsInfoCountByOwnerId(Integer ownerId);

    /**
     * 查询总评论数
     * @return
     */
    int commentsInfoCount();

    /**
     * 查询所有评论
     * @param commentsInfo
     * @return 评论信息列表
     */
    List<CommentsInfo> selectCommentsInfoList(CommentsInfo commentsInfo);

    /**
     * 批量删除评论信息
     * @param ids
     * @return
     */
    int deleteCommentsInfoByIds(String ids) throws Exception;
}
