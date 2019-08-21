package com.dragon.project.file.controller;

import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.project.file.domain.FileList;
import com.dragon.project.file.service.FileService;
import com.dragon.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/17 23:04
 * @description： 本地图片控制器
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/local/image/")
public class localImageController extends BaseController{

    String contentType;

    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        User user = getSysUser();
        contentType = "image";
        List<FileList> fileLists = fileService.selectByUserIdAndContentTypeLikeAndFileIsTrash(user.getUserId(), contentType, 0L);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("files", fileLists);
        return ajaxResult;
    }
}
