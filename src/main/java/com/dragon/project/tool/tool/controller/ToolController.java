package com.dragon.project.tool.tool.controller;

import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.tool.tool.domain.Tool;
import com.dragon.project.tool.tool.service.ToolService;
import com.dragon.project.tool.toolCategory.domain.ToolCategory;
import com.dragon.project.tool.toolCategory.service.ToolCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/17 16:27
 * @description： 常用工具(Tool)$desc Controller层
 * @modified By：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/tool/tool/")
public class ToolController extends BaseController {
    @Autowired
    ToolService toolService;

    @Autowired
    ToolCategoryService toolCategoryService;

    @RequiresPermissions("tool:tool:view")
    @GetMapping()
    public String tool() {
        return "tool/tool/tool";
    }

    @GetMapping("/list")
    @RequiresPermissions("blog:blog:list")
    @ResponseBody
    public TableDataInfo list(Tool tool) {
        startPage();
        List<Tool> list = toolService.selectToolList(tool);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("toolCategories", toolCategoryService.selectToolCategoryList(new ToolCategory()));
        return "tool/tool/add";
    }

    @Log(title = "工具管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("tool:tool:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Tool tool) {
        return toAjax(toolService.insertTool(tool));
    }

    @GetMapping("/edit/{toolId}")
    public String edit(@PathVariable Integer toolId, Model model) {
        model.addAttribute("tool", toolService.selectToolById(toolId));
        model.addAttribute("toolCategories", toolCategoryService.selectToolCategoryList(new ToolCategory()));
        return "tool/tool/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("tool:tool:edit")
    @Log(title = "工具管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(Tool tool) {
        return toAjax(toolService.updateTool(tool));
    }

    @DeleteMapping("/remove")
    @Log(title = "工具管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("blog:blog:remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(toolService.deleteToolByIds(ids));
    }


}