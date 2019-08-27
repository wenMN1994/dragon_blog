package com.dragon.project.blog.comments.controller;

import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.blog.comments.domain.CommentsInfo;
import com.dragon.project.blog.comments.service.CommentsInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/15 15:09
 * @description：评论管理控制器
 * @modified By：
 * @version: 1.0.0
 */
@Api("评论管理")
@Controller
@RequestMapping("/blog/comments")
public class CommentsController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CommentsController.class);

    private String prefix = "blog/comments";

    @Autowired
    private CommentsInfoService commentsInfoService;

    @GetMapping()
    @RequiresPermissions("blog:comment:view")
    @ApiOperation("访问评论管理页面")
    public String comments() {
        return prefix + "/comments";
    }

    @GetMapping("/list")
    @RequiresPermissions("blog:comment:list")
    @ApiOperation("查询评论信息")
    @ResponseBody
    public TableDataInfo list(CommentsInfo commentsInfo) {
        startPage();
        List<CommentsInfo> commentsInfoList = commentsInfoService.selectCommentsInfoList(commentsInfo);
        return getDataTable(commentsInfoList);
    }

    /**
     * 查询回复详细
     */
    @GetMapping("/reply/{id}")
    @RequiresPermissions("blog:comment:list")
    @Log(title = "回复详细列表", businessType = BusinessType.DELETE)
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        modelMap.put("commentId", id);
        return "blog/comments/reply";
    }

    @DeleteMapping("/remove")
    @RequiresPermissions("blog:comment:remove")
    @Log(title = "评论管理", businessType = BusinessType.DELETE)
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax(commentsInfoService.deleteCommentsInfoByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

}
