package com.dragon.admin.quartz.task;

import org.springframework.stereotype.Component;
import com.dragon.common.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author DragonWen
 */
@Component("dragonTask")
public class DragonTask
{
    public void dragonMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void dragonParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void dragonNoParams()
    {
        System.out.println("执行无参方法");
    }
}
