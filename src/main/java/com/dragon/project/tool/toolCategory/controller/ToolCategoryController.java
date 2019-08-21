package com.dragon.project.tool.toolCategory.controller;

import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
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
 * @description： 工具的分类(ToolCategory)$desc Controller层
 * @modified By：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/tool/toolCategory")
public class ToolCategoryController extends BaseController {
    @Autowired
    ToolCategoryService toolCategoryService;

    @RequiresPermissions("tool:toolCategory:view")
    @GetMapping()
    public String toolCategory() {
        return "tool/toolCategory/tool";
    }


    @GetMapping("/list")
    @RequiresPermissions("blog:toolCategory:list")
    @ResponseBody
    public TableDataInfo list(ToolCategory toolCategory) {
        startPage();
        List<ToolCategory> list = toolCategoryService.selectToolCategoryList(toolCategory);
        return getDataTable(list);
    }

    @GetMapping("/add")
    public String add() {
        return "tool/toolCategory/add";
    }


    @Log(title = "工具分类管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("tool:toolCategory:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ToolCategory toolCategory) {
        return toAjax(toolCategoryService.insertToolCategory(toolCategory));
    }

    @GetMapping("/edit/{toolCategoryId}")
    public String edit(@PathVariable Integer toolCategoryId, Model model) {
        model.addAttribute("toolCategory", toolCategoryService.selectToolCategoryById(toolCategoryId));
        return "tool/toolCategory/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("tool:toolCategory:edit")
    @Log(title = "工具分类管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(ToolCategory toolCategory) {
        return toAjax(toolCategoryService.updateToolCategory(toolCategory));
    }


    @DeleteMapping("/remove")
    @Log(title = "工具分类管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("blog:toolCategory:remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(toolCategoryService.deleteToolCategoryByIds(ids));
    }


}