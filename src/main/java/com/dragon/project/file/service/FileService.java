package com.dragon.project.file.service;

import com.dragon.framework.web.domain.Ztree;
import com.dragon.project.file.domain.FileList;
import com.dragon.project.file.domain.FilePath;
import com.dragon.project.system.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 14:29
 * @description： 文件管理服务接口
 * @modified By：
 * @version: 1.0.0
 */
public interface FileService {
    /**
     * 根据登录用户名查询文件路径
     * @param loginName
     * @return
     */
    FilePath selectByPathName(String loginName);

    /**
     * 保存文件路径
     * @param filePathNew
     * @return
     */
    int save(FilePath filePathNew);

    /**
     * 根据父 ID 查询 显示的 路径
     * @param parentId
     * @return
     */
    List<FilePath> selectPathByParent(Long parentId);

    /**
     * 根据路径查找文件
     * @param filePath 文件路径
     * @return
     */
    List<FileList> selectFileByPath(FilePath filePath);


    /**
     * 文件以及路径得同名处理
     * @param pathName 路径名称
     * @param filePath 文件路径
     * @param shuffix 文件后缀
     * @param num 编号
     * @param isFile 是否文件
     * @return
     */
    String onlyName(String pathName, FilePath filePath, String shuffix,int num,boolean isFile);

    /**
     * 根据目录id查询目录
     * @param pathId
     * @return
     */
    FilePath selectFilePathById(Long pathId);

    /**
     * 根据文件路径 递归查询父级目录
     * @param filePath
     * @param allParentPaths
     */
    void SelectAllParent(FilePath filePath, List<FilePath> allParentPaths);

    /**
     * 保存文件以及附件
     * @param file
     * @param user
     * @param nowPath
     * @param isFile
     * @return
     */
    FileList saveFile(MultipartFile file, User user, FilePath nowPath, boolean isFile) throws IOException;

    /**
     * 根据用户名查找文件路径
     * @param loginName
     * @return
     */
    FilePath selectFilePathByPathName(String loginName);

    /**
     * 根据文件id查找文件
     * @param fileId
     * @return
     */
    FileList selectFileListById(Long fileId);

    /**
     * 从硬盘中获取文件
     * @param filePath
     * @return
     */
    File getFile(String filePath);

    /**
     * 根据用户名查询文件
     * @param userId
     * @return
     */
    List<FileList> selectDocumentByUserId(Long userId, Long isTrash);

    /**
     * 根据文件类型查找文件
     * @param userId
     * @param contentType
     * @param isTrash
     * @return
     */
    List<FileList> selectByUserIdAndContentTypeLikeAndFileIsTrash(Long userId, String contentType, Long isTrash);

    /**
     * 根据文件是否删除状态查询文件
     * @param userId
     * @param isTrash
     * @return
     */
    List<FileList> selectFileListByUserIdAndIsTrash(Long userId, Long isTrash);

    /**
     * 根据文件共享状态查询文件
     * @param isShare
     * @param isTrash
     * @return
     */
    List<FileList> selectFileListByIsShareAndIsTrash(Long isShare, Long isTrash);

    /**
     * 根据路径用户ID和是否删除查找文件夹
     * @param userId
     * @param isTrash
     * @return
     */
    List<FilePath> selectPathListByUserIdAndIsTrash(Long userId, Long isTrash);

    /**
     *  根据文件id 将文件放入回收站
     * @param checkFileIds
     * @param setIsTrashHowMany
     * @param userId
     */
    void trashFile(List<Long> checkFileIds, Long setIsTrashHowMany, Long userId);

    /**
     * 根据文件夹id 批量放入回收战
     * @param checkPathIds
     * @param setIsTrashHowMany
     * @param isFirst
     */
    void trashPath(List<Long> checkPathIds, Long setIsTrashHowMany, boolean isFirst);

    /**
     * 重命名
     * @param name
     * @param renamefp
     * @param pathId
     * @param isFile
     */
    void rename(String name, Long renamefp, Long pathId, boolean isFile);

    /**
     * 移动复制文件树 点击加载
     */
    List<Ztree> pathTreeData();

    /**
     * 保存博客封面图片
     * @param file
     * @param user
     * @param nowPath
     * @param isFile
     * @return
     */
    Long saveFileReturnSrc(MultipartFile file, User user, FilePath nowPath, boolean isFile) throws IOException;

    /**
     * 根据文件id 批量 删除文件  同时删除 数据库以及本地文件
     * @param checkFileIds
     */
    void deleteFileByFileId(List<Long> checkFileIds);

    /**
     * 根据文件夹id 批量删除 文件夹  并删除此路径下的所有文件以及文件夹
     * @param checkPathIds
     */
    void deletePathByPathId(List<Long> checkPathIds);

    /**
     * 根据文件id 删除文件
     * @param fileId
     * @return
     */
    boolean deleteFileByFileId(Long fileId);

    /**
     * 复制和移动
     * @param moveOrCopyFileIds
     * @param moveOrCopyPathIds
     * @param moveOrCopyToId
     * @param fromWhere true为复制， false为移动
     * @param user
     */
    void moveAndCopy(List<Long> moveOrCopyFileIds, List<Long> moveOrCopyPathIds, Long moveOrCopyToId, boolean fromWhere, User user);
}
