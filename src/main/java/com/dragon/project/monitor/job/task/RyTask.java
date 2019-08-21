package com.dragon.project.monitor.job.task;

import org.springframework.stereotype.Component;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/21 10:31
 * @description： 定时任务调度测试
 * @modified By：
 * @version: 1.0.0
 */
@Component("ryTask")
public class RyTask {
    public void ryParams(String params) {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams() {
        System.out.println("执行无参方法");
    }
}
