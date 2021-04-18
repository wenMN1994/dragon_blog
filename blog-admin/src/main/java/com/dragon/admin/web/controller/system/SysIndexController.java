package com.dragon.admin.web.controller.system;

import com.dragon.common.utils.DateUtils;
import com.dragon.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2021/4/10 16:50
 * @description：后台首页Controller
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/system/index")
public class SysIndexController {

    /**
     * 获取一年内博客提交的数量
     * @param params
     * @return
     */
    @GetMapping("/getBlogContributeCount")
    public Result getBlogContributeCount(@RequestParam Map<String, Object> params){
        // 获取今天结束时间
        String endTime = DateUtils.getNowTime();
        // 获取365天前的日期
        Date temp = DateUtils.getDate(endTime, -365);
        String startTime = DateUtils.dateTimeToStr(temp);
        List<String> dateList = DateUtils.getDayBetweenDates(startTime, endTime);
        Map<String, Object> dateMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            dateMap.put(DateUtils.format(DateUtils.addDateDays(new Date(), -i)), i);
        }
        List<List<Object>> resultList = new ArrayList<>();
        for (String item : dateList) {
            Integer count = 0;
            if (dateMap.get(item) != null) {
                count = Integer.valueOf(dateMap.get(item).toString());
            }
            List<Object> objectList = new ArrayList<>();
            objectList.add(item);
            objectList.add(count);
            resultList.add(objectList);
        }
        Map<String, Object> resultMap = new HashMap<>();
        List<String> contributeDateList = new ArrayList<>();
        contributeDateList.add(startTime);
        contributeDateList.add(endTime);
        resultMap.put("contributeDate", contributeDateList);
        resultMap.put("blogContributeCount", resultList);
        return Result.ok().put("data", resultMap);
    }
}
