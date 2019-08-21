package com.dragon.project.chart.business.controller;

import com.dragon.framework.web.controller.BaseController;
import com.dragon.framework.web.domain.AjaxResult;
import com.dragon.project.blog.blog.service.BlogService;
import com.dragon.project.chart.business.domain.Business;
import com.dragon.project.dashboard.domain.BusinessCommonData;
import com.dragon.project.log.visitorLog.service.VisitLogService;
import com.dragon.project.system.carouselMap.service.CarouselMapService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/01 16:25
 * @description： 业务图表Controller
 * @modified By：
 * @version: 1.0.0
 */
@Controller
@RequestMapping("/chart/business")
public class BusinessController extends BaseController {

    @Autowired
    CarouselMapService carouselMapService;

    @Autowired
    VisitLogService visitLogService;

    @Autowired
    BlogService blogService;


    @RequiresPermissions("chart:business:view")
    @GetMapping
    public String business() {
        return "chart/business/business";
    }


    @GetMapping("/carouselMapData")
    @ResponseBody
    public AjaxResult carouselMapData() {
        List<Business> businesses = carouselMapService.selectCarouselMapData();
        return AjaxResult.success().put("data", businesses);
    }

    @GetMapping("/spiderData")
    @ResponseBody
    public AjaxResult spiderData() {
        List<BusinessCommonData> businessCommonData = visitLogService.selectSpiderData();
        return AjaxResult.success().put("data", businessCommonData);
    }

    @GetMapping("/blogClickData")
    @ResponseBody
    public AjaxResult blogClickData(String startTime, String endTime) {
        List<BusinessCommonData> businessCommonData = blogService.selectBlogClickData(startTime, endTime);
        return AjaxResult.success().put("data", businessCommonData);
    }

    @GetMapping("/visitData")
    @ResponseBody
    public AjaxResult visitData(String startTime, String endTime) {
        List<BusinessCommonData> businessCommonData = visitLogService.selectVisitLogData(startTime, endTime);
        return AjaxResult.success().put("data", businessCommonData);
    }

}
