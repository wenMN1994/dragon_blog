package com.dragon.project.blog.comments.domain;

import com.dragon.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/15 21:25
 * @description： 新建评论实体
 * @modified By：
 * @version: 1.0.0
 */
public class CommentsInfo extends BaseEntity {
    /**
     * 评论主键id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 评论类型：1、对人评论，2、对资源评论
     *
     * @mbg.generated
     */
    private Integer type;

    /**
     * 被评论对象id
     *
     * @mbg.generated
     */
    private Integer ownerId;

    /**
     * 评论者id
     *
     * @mbg.generated
     */
    private String fromId;

    /**
     * 评论者QQ
     *
     * @mbg.generated
     */
    private String fromQq;

    /**
     * 评论者名字
     *
     * @mbg.generated
     */
    private String fromName;

    /**
     * 评论者头像
     *
     * @mbg.generated
     */
    private String fromAvatar;

    /**
     * 点赞的数量
     *
     * @mbg.generated
     */
    private Integer likeNum;

    /**
     * 评论内容
     *
     * @mbg.generated
     */
    private String content;

    private List<CommentsReply> commentsReplyList;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromQq() {
        return fromQq;
    }

    public void setFromQq(String fromQq) {
        this.fromQq = fromQq;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<CommentsReply> getCommentsReplyList() {
        return commentsReplyList;
    }

    public void setCommentsReplyList(List<CommentsReply> commentsReplyList) {
        this.commentsReplyList = commentsReplyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", fromId=").append(fromId);
        sb.append(", fromQq=").append(fromQq);
        sb.append(", fromName=").append(fromName);
        sb.append(", fromAvatar=").append(fromAvatar);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", content=").append(content);
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", updateTime=").append(getUpdateTime());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CommentsInfo other = (CommentsInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
                && (this.getFromId() == null ? other.getFromId() == null : this.getFromId().equals(other.getFromId()))
                && (this.getFromQq() == null ? other.getFromQq() == null : this.getFromQq().equals(other.getFromQq()))
                && (this.getFromName() == null ? other.getFromName() == null : this.getFromName().equals(other.getFromName()))
                && (this.getFromAvatar() == null ? other.getFromAvatar() == null : this.getFromAvatar().equals(other.getFromAvatar()))
                && (this.getLikeNum() == null ? other.getLikeNum() == null : this.getLikeNum().equals(other.getLikeNum()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getFromId() == null) ? 0 : getFromId().hashCode());
        result = prime * result + ((getFromQq() == null) ? 0 : getFromQq().hashCode());
        result = prime * result + ((getFromName() == null) ? 0 : getFromName().hashCode());
        result = prime * result + ((getFromAvatar() == null) ? 0 : getFromAvatar().hashCode());
        result = prime * result + ((getLikeNum() == null) ? 0 : getLikeNum().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}