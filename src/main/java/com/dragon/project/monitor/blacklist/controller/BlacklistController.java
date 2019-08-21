package com.dragon.project.monitor.blacklist.controller;

import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.monitor.blacklist.domain.Blacklist;
import com.dragon.project.monitor.blacklist.service.BlacklistService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/4/10:35
 * @description： 黑名单业务处理的Controller
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/monitor/blacklist")
public class BlacklistController extends BaseController {
    @Autowired
    BlacklistService blacklistService;

    @GetMapping
    @RequiresPermissions("monitor:blacklist:view")
    public String blacklist() {
        return "monitor/blacklist/blacklist";
    }

    @RequiresPermissions("monitor:blacklist:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Blacklist blacklist) {
        startPage();
        List<Blacklist> list = blacklistService.selectBlacklistList(blacklist);
        return getDataTable(list);
    }

    @RequiresPermissions("monitor:blacklist:remove")
    @Log(title = "黑名单", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids) {
        return toAjax(blacklistService.deleteBlacklistByIds(ids));
    }

    @GetMapping("/edit/{blacklistId}")
    public String edit(@PathVariable Integer blacklistId, Model model) {
        model.addAttribute("blacklist", blacklistService.selectBlacklistById(blacklistId));
        return "monitor/blacklist/edit";
    }

    @PutMapping("/edit")
    @RequiresPermissions("monitor:blacklist:edit")
    @Log(title = "黑名单", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult editSave(Blacklist blacklist) {
        return toAjax(blacklistService.updateBlacklist(blacklist));
    }

    @Log(title = "黑名单", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:blacklist:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        blacklistService.cleanBlacklist();
        return success();
    }

    @GetMapping("/add")
    public String add() {
        return "monitor/blacklist/add";
    }

    @PostMapping("/add")
    @RequiresPermissions("monitor:blacklist:add")
    @ResponseBody
    public AjaxResult addSave(Blacklist blacklist) {
        return toAjax(blacklistService.insertBlacklist(blacklist));
    }


    @PostMapping("/addBlacklist")
    @ResponseBody
    public AjaxResult addBlacklistSave(String ipAddr) {
        return toAjax(blacklistService.insertBlacklist(ipAddr));
    }
}
