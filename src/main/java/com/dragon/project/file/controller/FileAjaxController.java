package com.dragon.project.file.controller;

import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.Ztree;
import com.dragon.project.file.domain.FileList;
import com.dragon.project.file.domain.FilePath;
import com.dragon.project.file.service.FileService;
import com.dragon.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/8 22:57
 * @description： 文件管理Ajax请求
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/")
public class FileAjaxController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping("filetypeload")
    public String fileTypeLoad(@RequestParam("type") String type, Model model){
        typeLoad(model,type);
        return "file/filetypeload";
    }

    private void typeLoad(Model model, String type){
        User user = getSysUser();

        String contentType;

        List<FileList> fileLists = null;
        List<FilePath> filePaths = null;

        switch (type) {
            case "document":
                fileLists = fileService.selectDocumentByUserId(user.getUserId(), 0L);
                model.addAttribute("files", fileLists);
                model.addAttribute("isload",1);
                break;
            case "picture":
                contentType = "image";
                fileLists = fileService.selectByUserIdAndContentTypeLikeAndFileIsTrash(user.getUserId(), contentType, 0L);
                model.addAttribute("files", fileLists);
                model.addAttribute("isload",1);
                break;
            case "music":
                contentType = "audio";
                fileLists = fileService.selectByUserIdAndContentTypeLikeAndFileIsTrash(user.getUserId(), contentType, 0L);
                model.addAttribute("files", fileLists);
                model.addAttribute("isload",1);
                break;
            case "video":
                contentType = "video";
                fileLists = fileService.selectByUserIdAndContentTypeLikeAndFileIsTrash(user.getUserId(), contentType, 0L);
                model.addAttribute("files", fileLists);
                model.addAttribute("isload",1);
                break;
            case "yasuo":
                contentType = "application";
                fileLists = fileService.selectByUserIdAndContentTypeLikeAndFileIsTrash(user.getUserId(), contentType, 0L);
                model.addAttribute("files", fileLists);
                model.addAttribute("isload",1);
                break;
            case "trash":
                fileLists = fileService.selectFileListByUserIdAndIsTrash(user.getUserId(),1L);
                filePaths = fileService.selectPathListByUserIdAndIsTrash(user.getUserId(), 1L);
                model.addAttribute("nowpath", fileService.selectByPathName(user.getLoginName()));
                model.addAttribute("paths", filePaths);
                model.addAttribute("files", fileLists);
                model.addAttribute("istrash", 1);
                model.addAttribute("isload",1);
                break;
            case "share":
                fileLists = fileService.selectFileListByIsShareAndIsTrash(1L,1L);
                model.addAttribute("files", fileLists);
                model.addAttribute("isshare", 1);
                model.addAttribute("isload",1);
                model.addAttribute("userid",user.getUserId());
                break;

            default:
                break;
        }

        model.addAttribute("type", type);
    }

    /**
     * 移动复制文件树
     * @return
     */
    @RequestMapping("pathTreeData")
    @ResponseBody
    public List<Ztree> pathTreeData(){
        List<Ztree> ztrees = fileService.pathTreeData();
        return ztrees;
    }

    /**
     * 回收站文件删除
     * @param type
     * @param checkPathIds
     * @param checkFileIds
     * @param model
     * @return
     */
    @RequestMapping("fileloaddeletefile")
    public String fileLoadDeleteFile(@RequestParam("type") String type,
                                     @RequestParam(value="checkpathids[]",required=false) List<Long> checkPathIds,
                                     @RequestParam(value="checkfileids[]",required=false) List<Long> checkFileIds,
                                     Model model){
        if (checkFileIds!=null) {
            // 删除文件
            fileService.deleteFileByFileId(checkFileIds);
        }
        if (checkPathIds!=null) {
            // 删除文件夹
            fileService.deletePathByPathId(checkPathIds);
        }

        typeLoad(model,type);
        return "file/filetypeload";
    }

    /**
     * 删除富文本编辑器中添加的图片
     * @param fileUrl
     * @return
     */
    @DeleteMapping("/remove")
    @Log(title = "图片管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public boolean remove(@RequestParam("fileUrl")String fileUrl) {
        boolean isSuccess = false;
        if(fileUrl.contains("imgshow?fileId")){
            Long fileId = Long.valueOf(fileUrl.substring(fileUrl.indexOf("=")+1));
            isSuccess = fileService.deleteFileByFileId(fileId);
        }
        return isSuccess;
    }

}
