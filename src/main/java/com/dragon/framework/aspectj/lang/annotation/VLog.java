package com.dragon.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/21 14:13
 * @description： 自定义访问日志注解
 * @modified By：
 * @version: 1.0.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VLog {
    /**
     * 请求的模块
     */
    String title() default "";

    int blogId() default 0;
}
