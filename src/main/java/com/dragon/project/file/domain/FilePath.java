package com.dragon.project.file.domain;

import java.io.Serializable;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 14:36
 * @description： 文件路径实体类
 * @modified By：
 * @version: 1.0.0
 */
public class FilePath implements Serializable {
    /**
     * 路径ID
     *
     * @mbg.generated
     */
    private Long pathId;

    /**
     * 父节点ID
     *
     * @mbg.generated
     */
    private Long parentId;

    /**
     * 路径名称
     *
     * @mbg.generated
     */
    private String pathName;

    /**
     * 路径关联用户ID
     *
     * @mbg.generated
     */
    private Long pathUserId;

    /**
     * 路径是否删除
     *
     * @mbg.generated
     */
    private Long pathIstrash;

    private static final long serialVersionUID = 1L;

    public FilePath() {

    }

    public FilePath(Long pathId, String newName) {
        super();
        this.parentId = pathId;
        this.pathName = newName;
    }

    public Long getPathId() {
        return pathId;
    }

    public void setPathId(Long pathId) {
        this.pathId = pathId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Long getPathUserId() {
        return pathUserId;
    }

    public void setPathUserId(Long pathUserId) {
        this.pathUserId = pathUserId;
    }

    public Long getPathIstrash() {
        return pathIstrash;
    }

    public void setPathIstrash(Long pathIstrash) {
        this.pathIstrash = pathIstrash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pathId=").append(pathId);
        sb.append(", parentId=").append(parentId);
        sb.append(", pathName=").append(pathName);
        sb.append(", pathUserId=").append(pathUserId);
        sb.append(", pathIstrash=").append(pathIstrash);
        sb.append("]");
        return sb.toString();
    }
}
