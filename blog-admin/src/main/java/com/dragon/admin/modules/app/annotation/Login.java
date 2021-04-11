package com.dragon.admin.modules.app.annotation;

import java.lang.annotation.*;

/**
 * app登录效验
 *
 * @author Dragon Wen
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
