package com.dragon.project.ad.controller;

import com.dragon.common.utils.poi.ExcelUtil;
import com.dragon.framework.aspectj.lang.annotation.Log;
import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.framework.web.page.TableDataInfo;
import com.dragon.project.ad.domain.BgAd;
import com.dragon.project.ad.service.IBgAdService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告Controller
 * 
 * @author Dragon Wen
 * @date 2019-08-28
 */
@Controller
@RequestMapping("/ad/ad")
public class BgAdController extends BaseController
{
    private String prefix = "ad";

    @Autowired
    private IBgAdService bgAdService;

    @RequiresPermissions("ad:ad:view")
    @GetMapping()
    public String ad()
    {
        return prefix + "/ad";
    }

    /**
     * 查询广告列表
     */
    @RequiresPermissions("ad:ad:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(BgAd bgAd)
    {
        startPage();
        List<BgAd> list = bgAdService.selectBgAdList(bgAd);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @RequiresPermissions("ad:ad:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BgAd bgAd)
    {
        List<BgAd> list = bgAdService.selectBgAdList(bgAd);
        ExcelUtil<BgAd> util = new ExcelUtil<BgAd>(BgAd.class);
        return util.exportExcel(list, "ad");
    }

    /**
     * 新增广告
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存广告
     */
    @RequiresPermissions("ad:ad:add")
    @Log(title = "新增广告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BgAd bgAd)
    {
        return toAjax(bgAdService.insertBgAd(bgAd));
    }

    /**
     * 修改广告
     */
    @GetMapping("/edit/{adId}")
    public String edit(@PathVariable("adId") Long adId, ModelMap mmap)
    {
        BgAd bgAd = bgAdService.selectBgAdById(adId);
        mmap.put("bgAd", bgAd);
        return prefix + "/edit";
    }

    /**
     * 修改保存广告
     */
    @RequiresPermissions("ad:ad:edit")
    @Log(title = "修改广告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BgAd bgAd)
    {
        return toAjax(bgAdService.updateBgAd(bgAd));
    }

    /**
     * 删除广告
     */
    @RequiresPermissions("ad:ad:remove")
    @Log(title = "删除广告", businessType = BusinessType.DELETE)
    @DeleteMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bgAdService.deleteBgAdByIds(ids));
    }

    /**
     * 校验广告位置是否已有广告
     */
    @PostMapping("/checkDataFlagUnique")
    @ResponseBody
    public String checkMenuNameUnique(BgAd bgAd)
    {
        return bgAdService.checkDataFlagUnique(bgAd);
    }
}
