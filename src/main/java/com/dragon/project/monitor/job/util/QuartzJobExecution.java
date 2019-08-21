package com.dragon.project.monitor.job.util;

import org.quartz.JobExecutionContext;
import com.dragon.project.monitor.job.domain.Job;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/21 10:59
 * @description： 定时任务处理（允许并发执行）
 * @modified By：
 * @version: 1.0.0
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception {
        JobInvokeUtil.invokeMethod(job);
    }
}
