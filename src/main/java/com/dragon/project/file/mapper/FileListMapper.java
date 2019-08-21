package com.dragon.project.file.mapper;

import com.dragon.project.file.domain.FileList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 14:54
 * @description： 文件列表dao层
 * @modified By：
 * @version: 1.0.0
 */
public interface FileListMapper {

    /**
     * 根据路径查询 文件集合
     * @param filePathId
     * @param fileIstrash
     * @return
     */
    List<FileList> selectByFilePathAndFileIstrash(@Param("filePathId") Long filePathId, @Param("fileIstrash") Long fileIstrash);

    /**
     *  根据文件名和文件路径查找文件
     * @param pathName
     * @param filePathId
     * @return
     */
    FileList selectByFileNameAndFilePath(@Param("pathName") String pathName, @Param("filePathId") Long filePathId);

    /**
     * 插入文件
     * @param filelist
     */
    void insertFileList(FileList filelist);

    /**
     * 根据文件id查找文件
     * @param fileId
     * @return
     */
    FileList selectFileListById(@Param("fileId") Long fileId);

    /**
     * 根据用户名查询文件
     * @param userId
     * @return
     */
    List<FileList> selectDocumentByUserIdAndIsTrash(@Param("userId") Long userId, @Param("isTrash") Long isTrash);

    /**
     * 根据文件类型查找文件
     * @param userId
     * @param contentType
     * @param isTrash
     * @return
     */
    List<FileList> selectByUserIdAndContentTypeLikeAndFileIsTrash(@Param("userId") Long userId, @Param("contentType") String contentType, @Param("isTrash") Long isTrash);

    /**
     * 根据文件是否删除状态查询文件
     * @param userId
     * @param isTrash
     * @return
     */
    List<FileList> selectFileListByUserIdAndIsTrash(@Param("userId") Long userId ,@Param("isTrash") Long isTrash);

    /**
     * 根据文件共享状态查询文件
     * @param isShare
     * @param isTrash
     * @return
     */
    List<FileList> selectFileListByIsShareAndIsTrash(@Param("isShare") Long isShare, @Param("isTrash") Long isTrash);

    /**
     * 更新文件
     * @param fileList
     */
    void updateFileList(FileList fileList);

    /**
     * 根据文件夹id查找文件
     * @param pathId
     * @return
     */
    List<FileList> selectFileListByPathId(@Param("pathId") Long pathId);

    /**
     * 查找FileList
     * @param fileList
     * @return
     */
    FileList selectFileList(FileList fileList);

    /**
     * 根据文件id 批量 删除文件  同时删除 数据库以及本地文件
     * @param fileId
     */
    int deleteFileListById(@Param("fileId") Long fileId);
}
