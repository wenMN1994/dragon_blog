/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dragon.admin.modules.app.controller;


import com.dragon.admin.modules.app.annotation.Login;
import com.dragon.admin.modules.app.annotation.LoginUser;
import com.dragon.admin.modules.app.entity.UserEntity;
import com.dragon.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
@Api("APP测试接口")
public class AppTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public Result userInfo(@LoginUser UserEntity user){
        return Result.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public Result userInfo(@RequestAttribute("userId") Integer userId){
        return Result.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public Result notToken(){
        return Result.ok().put("msg", "无需token也能访问。。。");
    }

}
