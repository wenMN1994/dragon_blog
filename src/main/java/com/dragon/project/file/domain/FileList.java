package com.dragon.project.file.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 14:35
 * @description：文件列表实体类
 * @modified By：
 * @version: 1.0.0
 */
public class FileList implements Serializable {
    /**
     * 文件ID
     *
     * @mbg.generated
     */
    private Long fileId;

    /**
     * 文件名
     *
     * @mbg.generated
     */
    private String fileName;

    /**
     * 文件路径
     *
     * @mbg.generated
     */
    private String filePath;

    /**
     * 文件后缀
     *
     * @mbg.generated
     */
    private String fileShuffix;

    /**
     * 文件类型
     *
     * @mbg.generated
     */
    private String contentType;

    private String model;

    /**
     * 路径ID
     *
     * @mbg.generated
     */
    private Long pathId;

    /**
     * 文件大小
     *
     * @mbg.generated
     */
    private Long fileSize;

    /**
     * 上传时间
     *
     * @mbg.generated
     */
    private Date uploadTime;

    /**
     * 文件上传用户
     *
     * @mbg.generated
     */
    private Long fileUserId;

    /**
     * 文件是否在垃圾箱
     *
     * @mbg.generated
     */
    private Long fileIstrash;

    /**
     * 文件是否共享
     *
     * @mbg.generated
     */
    private Long fileIsshare;

    private static final long serialVersionUID = 1L;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileShuffix() {
        return fileShuffix;
    }

    public void setFileShuffix(String fileShuffix) {
        this.fileShuffix = fileShuffix;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPathId() {
        return pathId;
    }

    public void setPathId(Long pathId) {
        this.pathId = pathId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getFileUserId() {
        return fileUserId;
    }

    public void setFileUserId(Long fileUserId) {
        this.fileUserId = fileUserId;
    }

    public Long getFileIstrash() {
        return fileIstrash;
    }

    public void setFileIstrash(Long fileIstrash) {
        this.fileIstrash = fileIstrash;
    }

    public Long getFileIsshare() {
        return fileIsshare;
    }

    public void setFileIsshare(Long fileIsshare) {
        this.fileIsshare = fileIsshare;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileId=").append(fileId);
        sb.append(", fileName=").append(fileName);
        sb.append(", filePath=").append(filePath);
        sb.append(", fileShuffix=").append(fileShuffix);
        sb.append(", contentType=").append(contentType);
        sb.append(", model=").append(model);
        sb.append(", pathId=").append(pathId);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", fileUserId=").append(fileUserId);
        sb.append(", fileIstrash=").append(fileIstrash);
        sb.append(", fileIsshare=").append(fileIsshare);
        sb.append("]");
        return sb.toString();
    }
}
