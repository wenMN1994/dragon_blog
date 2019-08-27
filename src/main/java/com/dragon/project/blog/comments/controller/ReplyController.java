package com.dragon.project.blog.comments.controller;

import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.blog.comments.domain.CommentsReply;
import com.dragon.project.blog.comments.service.CommentsReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/22 15:58
 * @description：回复管理
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@Api("回复管理")
@RequestMapping("/blog/reply")
public class ReplyController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(ReplyController.class);

    private String prefix = "blog/comments";

    @Autowired
    private CommentsReplyService commentsReplyService;

    @GetMapping()
    @RequiresPermissions("blog:comment:view")
    @ApiOperation("访问评论管理页面")
    public String comments() {
        return prefix + "/reply";
    }

    @GetMapping("/list")
    @RequiresPermissions("blog:comment:list")
    @ApiOperation("查询评论信息")
    @ResponseBody
    public TableDataInfo list(CommentsReply commentsReply) {
        startPage();
        List<CommentsReply> commentsReplyList = commentsReplyService.selectCommentsReplyList(commentsReply);
        return getDataTable(commentsReplyList);
    }

    @Log(title = "回复管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("blog:comment:remove")
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(commentsReplyService.deleteCommentsReplyByIds(ids));
    }
}
