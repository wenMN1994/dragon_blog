package com.dragon.framework.aspectj.lang.annotation;

import com.dragon.framework.aspectj.lang.enums.BusinessType;
import com.dragon.framework.aspectj.lang.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/6/10 06:27
 * @description： 自定义操作日志记录注解
 * @modified By：
 * @version: 1.0.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
