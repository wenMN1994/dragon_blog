package com.dragon.project.blog.comments.domain;

import com.dragon.framework.web.domain.BaseEntity;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/15 21:25
 * @description： 评论回复实体
 * @modified By：
 * @version: 1.0.0
 */
public class CommentsReply extends BaseEntity {
    /**
     * 回复主键id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * 评论主表id
     *
     * @mbg.generated
     */
    private Integer commentId;

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
     * 被评论者id
     *
     * @mbg.generated
     */
    private String toId;

    /**
     * 被评论者名字
     *
     * @mbg.generated
     */
    private String toName;

    /**
     * 被评论者头像
     *
     * @mbg.generated
     */
    private String toAvatar;

    /**
     * 评论内容
     *
     * @mbg.generated
     */
    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentId=").append(commentId);
        sb.append(", fromId=").append(fromId);
        sb.append(", fromQq=").append(fromQq);
        sb.append(", fromName=").append(fromName);
        sb.append(", fromAvatar=").append(fromAvatar);
        sb.append(", toId=").append(toId);
        sb.append(", toName=").append(toName);
        sb.append(", toAvatar=").append(toAvatar);
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
        CommentsReply other = (CommentsReply) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
                && (this.getFromId() == null ? other.getFromId() == null : this.getFromId().equals(other.getFromId()))
                && (this.getFromQq() == null ? other.getFromQq() == null : this.getFromQq().equals(other.getFromQq()))
                && (this.getFromName() == null ? other.getFromName() == null : this.getFromName().equals(other.getFromName()))
                && (this.getFromAvatar() == null ? other.getFromAvatar() == null : this.getFromAvatar().equals(other.getFromAvatar()))
                && (this.getToId() == null ? other.getToId() == null : this.getToId().equals(other.getToId()))
                && (this.getToName() == null ? other.getToName() == null : this.getToName().equals(other.getToName()))
                && (this.getToAvatar() == null ? other.getToAvatar() == null : this.getToAvatar().equals(other.getToAvatar()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getFromId() == null) ? 0 : getFromId().hashCode());
        result = prime * result + ((getFromQq() == null) ? 0 : getFromQq().hashCode());
        result = prime * result + ((getFromName() == null) ? 0 : getFromName().hashCode());
        result = prime * result + ((getFromAvatar() == null) ? 0 : getFromAvatar().hashCode());
        result = prime * result + ((getToId() == null) ? 0 : getToId().hashCode());
        result = prime * result + ((getToName() == null) ? 0 : getToName().hashCode());
        result = prime * result + ((getToAvatar() == null) ? 0 : getToAvatar().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}