package com.dragon.project.file.mapper;

import com.dragon.project.file.domain.FilePath;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 14:55
 * @description：文件路径dao层
 * @modified By：
 * @version: 1.0.0
 */
public interface FilePathMapper {

    /**
     * 保存文件路径
     * @param filePathNew
     * @return
     */
    int insertFilePath(FilePath filePathNew);

    /**
     * 根据文件夹ID查找文件夹
     * @param isTrash
     * @return
     */
    List<FilePath> selectByPathIsTrash(@Param("isTrash") Long isTrash);

    /**
     * 根据登录的用户名查找文件路径
     * @param pathName
     * @return
     */
    FilePath selectFilePathByPathName(String pathName);

    /**
     * 根据父 ID 查询 显示的 路径
     * @param parentId
     * @param pathIstrash
     * @return
     */
    List<FilePath> selectByParentIdAndPathIstrash(@Param("parentId") Long parentId, @Param("pathIstrash") Long pathIstrash);

    /**
     * 根据文件夹名称和文件夹ID查找文件夹
     * @param pathName
     * @param parentId
     * @return
     */
    FilePath selectByPathNameAndParentId(@Param("pathName") String pathName, @Param("parentId") Long parentId);

    /**
     * 根据目录id查询目录
     * @param pathId
     * @return
     */
    FilePath selectFilePathById(@Param("pathId") Long pathId);

    /**
     * 根据路径用户ID和是否删除查找文件夹
     * @param userId
     * @param isTrash
     * @return
     */
    List<FilePath> selectPathListByUserIdAndIsTrash(@Param("userId") Long userId, @Param("isTrash") Long isTrash);

    /**
     * 根据父文件夹id查找子文件夹
     * @param parentId
     * @return
     */
    List<FilePath> selectFilePathByParentId(@Param("parentId") Long parentId);

    /**
     * 根据文件夹对象更新文件夹信息
     * @param filePath
     * @return
     */
    void updateFilePath(FilePath filePath);

    /**
     * 根据文件夹id 批量删除 文件夹  并删除此路径下的所有文件以及文件夹
     * @param pathId
     */
    void deleteFilePathByPathId(@Param("pathId") Long pathId);
}
