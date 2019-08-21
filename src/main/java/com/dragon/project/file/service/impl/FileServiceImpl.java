package com.dragon.project.file.service.impl;

import com.dragon.framework.web.domain.Ztree;
import com.dragon.project.file.domain.FileList;
import com.dragon.project.file.domain.FilePath;
import com.dragon.project.file.mapper.FileListMapper;
import com.dragon.project.file.mapper.FilePathMapper;
import com.dragon.project.file.service.FileService;
import com.dragon.project.system.user.domain.User;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/10 14:30
 * @description： 文件管理服务接口实现
 * @modified By：
 * @version: 1.0.0
 */
@Configurable
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileListMapper fileListMapper;

    @Autowired
    private FilePathMapper filePathMapper;

    @Value("${file.root.path}")
    private String rootPath;

    @Override
    public FilePath selectByPathName(String pathName) {
        return filePathMapper.selectFilePathByPathName(pathName);
    }

    @Override
    public int save(FilePath filePathNew) {
        return filePathMapper.insertFilePath(filePathNew);
    }

    @Override
    public List<FilePath> selectPathByParent(Long parentId) {
        return filePathMapper.selectByParentIdAndPathIstrash(parentId, 0L);
    }

    @Override
    public List<FileList> selectFileByPath(FilePath filePath) {
        return fileListMapper.selectByFilePathAndFileIstrash(filePath.getPathId(), 0L);
    }

    @Override
    public String onlyName(String pathName, FilePath filePath, String shuffix, int num, boolean isFile) {
        Object file = null;
        if(isFile){
            file = fileListMapper.selectByFileNameAndFilePath(pathName, filePath.getPathId());
        } else {
            file = filePathMapper.selectByPathNameAndParentId(pathName, filePath.getPathId());
        }
        if(file != null){
            int numNew = num -1;
            if(shuffix == null){
                pathName = pathName.replace("("+numNew+")", "")+"("+num+")";
            }else{
                pathName = pathName.replace("."+shuffix,"").replace("("+numNew+")", "")+"("+num+")"+"."+shuffix;
            }
            num += 1;
            return onlyName(pathName,filePath,shuffix,num,isFile);
        }
        return pathName;
    }

    @Override
    public FilePath selectFilePathById(Long pathId) {
        return filePathMapper.selectFilePathById(pathId);
    }

    @Override
    public FileList saveFile(MultipartFile file, User user, FilePath nowPath, boolean isFile) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        File root = new File(this.rootPath,simpleDateFormat.format(new Date()));

        File savePath = new File(root,user.getUserName());

        if (!savePath.exists()) {
            savePath.mkdirs();
        }

        String shuffix = FilenameUtils.getExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString().toLowerCase()+"."+shuffix;
        File targetFile = new File(savePath,newFileName);
        file.transferTo(targetFile);


        if(isFile){
            FileList filelist = new FileList();
            String filename = file.getOriginalFilename();
            filename = onlyName(filename,nowPath,shuffix,1,true);
            filelist.setFileName(filename);
            filelist.setFilePath(targetFile.getAbsolutePath().replace("\\", "/").replace(this.rootPath, ""));
            filelist.setFileShuffix(shuffix);
            filelist.setFileSize(file.getSize());
            filelist.setUploadTime(new Date());
            filelist.setPathId(nowPath.getPathId());
            filelist.setContentType(file.getContentType());
            filelist.setFileUserId(user.getUserId());
            fileListMapper.insertFileList(filelist);
            return filelist;
        }else{
            return null;
        }
    }

    @Override
    public Long saveFileReturnSrc(MultipartFile file, User user, FilePath nowPath, boolean isFile) throws IOException {
        FileList fl = saveFile(file,user,nowPath,isFile);
        FileList fileList = fileListMapper.selectFileList(fl);
        return fileList.getFileId();
    }

    @Override
    public void SelectAllParent(FilePath filePath, List<FilePath> allParentPaths) {
        if (filePath.getParentId() != 1L) {
            FilePath filePathItem = filePathMapper.selectFilePathById(filePath.getParentId());
            allParentPaths.add(filePathItem);
            SelectAllParent(filePathItem,allParentPaths);
        }
    }

    @Override
    public FilePath selectFilePathByPathName(String pathName) {
        return filePathMapper.selectFilePathByPathName(pathName);
    }

    @Override
    public FileList selectFileListById(Long fileId) {
        return fileListMapper.selectFileListById(fileId);
    }

    @Override
    public File getFile(String filePath) {
        return new File(this.rootPath,filePath);
    }

    @Override
    public List<FileList> selectDocumentByUserId(Long userId, Long isTrash) {
        return fileListMapper.selectDocumentByUserIdAndIsTrash(userId, isTrash);
    }

    @Override
    public List<FileList> selectByUserIdAndContentTypeLikeAndFileIsTrash(Long userId, String contentType, Long isTrash) {
        return fileListMapper.selectByUserIdAndContentTypeLikeAndFileIsTrash(userId, contentType, isTrash);
    }

    @Override
    public List<FileList> selectFileListByUserIdAndIsTrash(Long userId,Long isTrash) {
        return fileListMapper.selectFileListByUserIdAndIsTrash(userId,isTrash);
    }

    @Override
    public List<FileList> selectFileListByIsShareAndIsTrash(Long isShare,Long isTrash) {
        return fileListMapper.selectFileListByIsShareAndIsTrash(isShare, isTrash);
    }

    @Override
    public List<FilePath> selectPathListByUserIdAndIsTrash(Long userId, Long isTrash) {
        return filePathMapper.selectPathListByUserIdAndIsTrash(userId, isTrash);
    }

    @Override
    public void trashFile(List<Long> checkFileIds, Long setIsTrashHowMany, Long userId) {
        for (Long fileId : checkFileIds) {
            FileList fileList = fileListMapper.selectFileListById(fileId);
            fileList.setFileIstrash(setIsTrashHowMany);
            if(userId != null){
                fileList.setPathId(null);
            }

            fileListMapper.updateFileList(fileList);
        }
    }

    @Override
    public void trashPath(List<Long> checkPathIds, Long setIsTrashHowMany, boolean isFirst) {
        for (Long pathId : checkPathIds) {
            FilePath filePath = filePathMapper.selectFilePathById(pathId);

            //首先将此文件夹下的文件放入回收战
            List<FileList> files = fileListMapper.selectFileListByPathId(pathId);
            if(!files.isEmpty()){
                List<Long> fileIds= new ArrayList<>();
                for (FileList filelist : files) {
                    fileIds.add(filelist.getFileId());
                }
                trashFile(fileIds,1L,null);
            }
            //然后将此文件夹下的文件夹放入回收战
            List<FilePath> filePaths = filePathMapper.selectFilePathByParentId(pathId);
            if(!filePaths.isEmpty()){
                List<Long> pathIdsNext = new ArrayList<>();
                for (FilePath filePathTemp : filePaths) {
                    pathIdsNext.add(filePathTemp.getPathId());
                }
                trashPath(pathIdsNext,1L,false);
            }
            filePath.setPathIstrash(setIsTrashHowMany);
            filePathMapper.updateFilePath(filePath);
        }
    }

    @Override
    public void rename(String name, Long renamefp, Long nowPathId, boolean isFile) {
        if(isFile){
            //文件名修改
            FileList fileList = fileListMapper.selectFileListById(renamefp);
            FilePath filePath = filePathMapper.selectFilePathById(fileList.getPathId());
            String newName = onlyName(name, filePath, fileList.getFileShuffix(), 1, isFile);
            fileList.setFileName(newName);
            fileListMapper.updateFileList(fileList);
        }else{
            //文件夹名修改
            FilePath fp = filePathMapper.selectFilePathById(renamefp);
            FilePath filePath = filePathMapper.selectFilePathById(nowPathId);
            String newName = onlyName(name, filePath, null, 1, false);
            fp.setPathName(newName);
            filePathMapper.updateFilePath(fp);
        }
    }

    @Override
    public List<Ztree> pathTreeData() {
        List<FilePath> filePathList = filePathMapper.selectByPathIsTrash(0L);
        List<Ztree> ztrees = initZtree(filePathList);
        return ztrees;
    }

    public List<Ztree> initZtree(List<FilePath> filePathList){
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (FilePath filePath : filePathList){
            Ztree ztree = new Ztree();
            ztree.setId(filePath.getPathId());
            ztree.setpId(filePath.getParentId());
            ztree.setName(filePath.getPathName());
            ztree.setTitle(filePath.getPathName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public void deleteFileByFileId(List<Long> fileIds) {
        for (Long fileId : fileIds) {
            FileList filelist = fileListMapper.selectFileListById(fileId);

            File file = new File(this.rootPath,filelist.getFilePath());
            if(file.exists() && file.isFile()){
                fileListMapper.deleteFileListById(fileId);
                file.delete();
            }
        }
    }

    @Override
    public void deletePathByPathId(List<Long> pathIds) {
        for (Long pathId : pathIds) {
            //首先删除此文件夹下的文件
            List<FileList> files = fileListMapper.selectFileListByPathId(pathId);
            if(!files.isEmpty()){
                List<Long> fileIdList= new ArrayList<>();
                for (FileList fileList : files) {
                    fileIdList.add(fileList.getFileId());
                }
                deleteFileByFileId(fileIdList);
            }

            //然后删除此文件夹下的文件夹
            List<FilePath> filePathList = filePathMapper.selectFilePathByParentId(pathId);
            if(!filePathList.isEmpty()){
                List<Long> pathIdList = new ArrayList<>();
                for (FilePath filePath : filePathList) {
                    pathIdList.add(filePath.getPathId());
                }
                deletePathByPathId(pathIdList);
            }

            filePathMapper.deleteFilePathByPathId(pathId);
        }
    }

    @Override
    public boolean deleteFileByFileId(Long fileId) {
        FileList fileList = fileListMapper.selectFileListById(fileId);
        File file = new File(this.rootPath,fileList.getFilePath());
        int isDelete = fileListMapper.deleteFileListById(fileId);
        boolean b = file.delete();
        if(isDelete == 1 && b){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void moveAndCopy(List<Long> mcfileids, List<Long> mcpathids, Long toPathId, boolean fromWhere, User user) {
        FilePath toPath = filePathMapper.selectFilePathById(toPathId);
        if(fromWhere){
            System.out.println("这里是移动！！~~");
            if(!mcfileids.isEmpty()){
                for (Long mcfileid : mcfileids) {
                    FileList filelist = fileListMapper.selectFileListById(mcfileid);
                    String filename = onlyName(filelist.getFileName(),toPath,filelist.getFileShuffix(),1,true);
                    filelist.setPathId(toPath.getPathId());
                    filelist.setFileName(filename);
                    fileListMapper.updateFileList(filelist);
                }
            }
            if(!mcpathids.isEmpty()){
                System.out.println("pathid is not null");
                for (Long mcpathid : mcpathids) {
                    FilePath filepath = filePathMapper.selectFilePathById(mcpathid);
                    String name = onlyName(filepath.getPathName(), toPath, null, 1, false);
                    filepath.setParentId(toPathId);
                    filepath.setPathName(name);
                    filePathMapper.updateFilePath(filepath);
                }
            }
        }else{
            System.out.println("这里是复制！！~~");
            if(!mcfileids.isEmpty()){
                for (Long mcfileid : mcfileids) {
                    FileList filelist = fileListMapper.selectFileListById(mcfileid);
                    copyFile(filelist,toPath,true, user);
                }
            }
            if(!mcpathids.isEmpty()){
                for (Long mcpathid : mcpathids) {
                    copyPath(mcpathid, toPathId, true, user);
                }
            }
        }
    }

    public void copyPath(Long copyPathId,Long toPathId,boolean isFirst,User user){
        FilePath filepath = filePathMapper.selectFilePathById(copyPathId);

        //第一个文件夹的复制
        FilePath copyPath = new FilePath();
        copyPath.setParentId(toPathId);
        String copypathname = filepath.getPathName();
        if(isFirst){
            copypathname = "拷贝 "+filepath.getPathName().replace("拷贝 ", "");
        }
        copyPath.setPathName(copypathname);
        copyPath.setPathUserId(user.getUserId());
        filePathMapper.insertFilePath(copyPath);
        //这一个文件夹下的文件的复制
        List<FileList> fileLists = fileListMapper.selectByFilePathAndFileIstrash(filepath.getPathId(), 0L);
        for (FileList fileList : fileLists) {
            copyFile(fileList,copyPath,false, user);
        }

        List<FilePath> filepathsons = filePathMapper.selectByParentIdAndPathIstrash(filepath.getPathId(), 0L);

        if(!filepathsons.isEmpty()){
            for (FilePath filepathson : filepathsons) {
                copyPath(filepathson.getPathId(),copyPath.getPathId(),false,user);
            }
        }

    }

    /**
     * 文件复制
     * @param fileList
     */
    public void copyFile(FileList fileList,FilePath toPath,boolean isFileIn, User user){
        File s = getFile(fileList.getFilePath());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
        File root = new File(this.rootPath,simpleDateFormat.format(new Date()));
        File savePath = new File(root,user.getUserName());

        if (!savePath.exists()) {
            savePath.mkdirs();
        }

        String suffix = fileList.getFileShuffix();
        String newFileName = UUID.randomUUID().toString().toLowerCase()+"."+suffix;
        File t = new File(savePath,newFileName);

        copyFileIo(s,t);

        FileList fileListNew = new FileList();
        String fileName = "";
        if(isFileIn){
            fileName = "拷贝 "+fileList.getFileName().replace("拷贝 ", "");
        }else{
            fileName = fileList.getFileName();
        }
        fileName = onlyName(fileName,toPath,suffix,1,true);
        fileListNew.setFileName(fileName);
        fileListNew.setFilePath(t.getAbsolutePath().replace("\\", "/").replace(this.rootPath, ""));
        fileListNew.setFileShuffix(suffix);
        fileListNew.setFileSize(fileList.getFileSize());
        fileListNew.setUploadTime(new Date());
        fileListNew.setPathId(toPath.getPathId());
        fileListNew.setContentType(fileList.getContentType());
        fileListNew.setFileUserId(user.getUserId());
        fileListMapper.insertFileList(fileListNew);

    }
    /**
     * 本地文件复制
     * @param s
     * @param t
     */
    public void copyFileIo(File s,File t){
        InputStream fis = null;
        OutputStream fos = null;

        try {
            fis = new BufferedInputStream(new FileInputStream(s));
            fos = new BufferedOutputStream(new FileOutputStream(t));
            byte[] buf = new byte[2048];
            int i ;
            while((i = fis.read(buf)) != -1){
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
