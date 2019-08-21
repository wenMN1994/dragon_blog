package com.dragon.project.file.controller;

import com.dragon.common.utils.file.FileUtils;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.project.file.domain.FileList;
import com.dragon.project.file.domain.FilePath;
import com.dragon.project.file.service.FileService;
import com.dragon.project.system.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/8 16:58
 * @description： 文件控制器
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/")
public class FileController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileService fileService;

    @RequestMapping("filemanage")
    public String fileManager(Model model){
        User user = getSysUser();
        FilePath filePath = fileService.selectByPathName(user.getLoginName());

        if(filePath == null){
            FilePath filePathNew = new FilePath();
            filePathNew.setParentId(1L);
            filePathNew.setPathName(user.getUserName());
            filePathNew.setPathUserId(user.getUserId());
            filePathNew.setPathIstrash(0L);
            int temp = fileService.save(filePathNew);
            if(temp == 1){
                filePath = fileService.selectFilePathByPathName(user.getLoginName());
            }
        }
        model.addAttribute("nowpath", filePath);
        model.addAttribute("paths",fileService.selectPathByParent(filePath.getPathId()));
        model.addAttribute("files", fileService.selectFileByPath(filePath));
        model.addAttribute("userrootpath",filePath);
        model.addAttribute("mcpaths",fileService.selectPathByParent(filePath.getPathId()));
        return "file/filemanage";
    }

    /**
     * 进入指定文件夹
     * @param pathId
     * @param model
     * @return
     */
    @RequestMapping("intofolder")
    public String intoFolder(@RequestParam("pathId") Long pathId, Model model) {
        folder(model,pathId);
        return "file/filemanage";
    }

    /**
     * 文件上传
     * @param file
     * @param pathId
     * @param model
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("fileupload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("pathId") Long pathId,
                             Model model) throws IllegalStateException, IOException {
        User user = getSysUser();
        FilePath nowPath = fileService.selectFilePathById(pathId);
        // true 表示从文件使用上传
        fileService.saveFile(file, user, nowPath, true);
        folder(model,pathId);
        return "file/filemanage";
    }

    /**
     * 上传博客封面图片
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping("blogfileupload")
    @ResponseBody
    public AjaxResult uploadBlogFile(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        User user = getSysUser();
        FilePath nowPath = fileService.selectByPathName(user.getUserName());
        Long fileId = fileService.saveFileReturnSrc(file, user, nowPath, true);
        String src = "imgshow?fileId=" + fileId;
        return AjaxResult.success().put("data", src);

    }

    /**
     *  新建文件夹
     * @param pathId
     * @param pathName
     * @param model
     * @return
     */
    @RequestMapping("createfolder")
    public String createFolder(@RequestParam("pathId") Long pathId, @RequestParam("pathName") String pathName, Model model) {
        User user = getSysUser();
        FilePath filePath = fileService.selectFilePathById(pathId);
        String newName = fileService.onlyName(pathName, filePath, null, 1, false);
        FilePath newFilePath = new FilePath(pathId, newName);

        newFilePath.setPathUserId(user.getUserId());
        fileService.save(newFilePath);
        folder(model,pathId);
        return "file/filemanage";
    }

    /**
     * 重命名方法
     * @param name
     * @param renamefp
     * @param pathId
     * @param isFile
     * @param model
     * @return
     */
    @RequestMapping("rename")
    public String rename(@RequestParam("name") String name,
                         @RequestParam("renamefp") Long renamefp,
                         @RequestParam("pathid") Long pathId,
                         @RequestParam("isfile") boolean isFile,
                         Model model){

        //这里调用重命名方法
        fileService.rename(name, renamefp, pathId, isFile);

        folder(model,pathId);
        return "file/filemanage";

    }

    /**
     * 移动和复制
     * @param moveOrCopyToId
     * @param model
     * @return
     */
    @RequestMapping("moveOrCopyTo")
    public String moveOrCopy(@RequestParam("moveOrCopy") boolean moveOrCopy,
                       @RequestParam("moveOrCopyToId") Long moveOrCopyToId,
                       @RequestParam("pathId") Long pathId,
                       @RequestParam("moveOrCopyFileIds")List<Long> moveOrCopyFileIds,
                       @RequestParam("moveOrCopyPathIds")List<Long> moveOrCopyPathIds,
                       Model model){
        User user = getSysUser();
        if(moveOrCopy){
            // 复制
            fileService.moveAndCopy(moveOrCopyFileIds, moveOrCopyPathIds, moveOrCopyToId,true, user);
        }else{
            // 移动
            fileService.moveAndCopy(moveOrCopyFileIds, moveOrCopyPathIds, moveOrCopyToId,false, user);
        }

        folder(model,pathId);
        return "file/filemanage";
    }

    /**
     * 删除前台选择的文件以及文件夹
     * @param pathId
     * @param checkPathIds
     * @param checkFileIds
     * @param model
     * @return
     */
    @RequestMapping("deletefile")
    public String deleteFile(@RequestParam("pathid") Long pathId,
                             @RequestParam("checkpathids") List<Long> checkPathIds,
                             @RequestParam("checkfileids") List<Long> checkFileIds, Model model) {
        User user = getSysUser();
        if (!checkFileIds.isEmpty()) {
            //文件放入回收战
            fileService.trashFile(checkFileIds, 1L,user.getUserId());
        }
        if (!checkPathIds.isEmpty()) {
            //文件夹放入回收战
            fileService.trashPath(checkPathIds, 1L, true);
        }

        folder(model,pathId);
        return "file/filemanage";
    }

    private void folder(Model model, Long pathId) {
        User user = getSysUser();
        FilePath userRootPath = fileService.selectByPathName(user.getLoginName());
        // 查询当前目录
        FilePath filePath = fileService.selectFilePathById(pathId);
        // 查询当前目录的所有父级目录
        List<FilePath> allParentPaths = new ArrayList<>();
        fileService.SelectAllParent(filePath, allParentPaths);
        Collections.reverse(allParentPaths);

        model.addAttribute("allparentpaths", allParentPaths);
        model.addAttribute("nowpath", filePath);
        model.addAttribute("paths",fileService.selectPathByParent(filePath.getPathId()));
        model.addAttribute("files", fileService.selectFileByPath(filePath));

        //复制移动显示 目录
        model.addAttribute("userrootpath",userRootPath);
        model.addAttribute("mcpaths",fileService.selectPathByParent(userRootPath.getPathId()));
    }

    /**
     * 图片预览
     * @param response
     * @param fileId
     */
    @RequestMapping("imgshow")
    public void imgShow(HttpServletResponse response, @RequestParam("fileId") Long fileId) {
        FileList filelist = fileService.selectFileListById(fileId);
        File file = fileService.getFile(filelist.getFilePath());
        FileUtils.writeFile(response, file);
    }
}
