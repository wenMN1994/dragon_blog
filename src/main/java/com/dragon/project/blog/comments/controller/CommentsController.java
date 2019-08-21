package com.dragon.project.blog.comments.controller;

import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/8/15 15:09
 * @description：评论管理控制器
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/blog/comments")
@Api("评论管理")
public class CommentsController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CommentsController.class);

    private String prefix = "blog/comments";

    @GetMapping("/")
    @ApiOperation("访问评论管理页面")
    public String comments() {
        return prefix + "/comments";
    }

    @GetMapping("/list")
    @ApiOperation("查询评论信息")
    public TableDataInfo list() {
        return null;
    }

}
