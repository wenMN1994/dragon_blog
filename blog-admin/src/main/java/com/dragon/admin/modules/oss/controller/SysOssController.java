/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dragon.admin.modules.oss.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.admin.modules.oss.service.SysOssService;
import com.dragon.common.utils.Result;
import com.dragon.admin.common.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
	
	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public Result list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);

		return Result.ok().put("page", page);
	}

	/**
	 * 保存文件
	 */
	@PostMapping("/saveFile")
	@RequiresPermissions("sys:oss:all")
	public Result saveFile(@RequestParam("files") String files) {
		//保存文件信息
		Map<String, String> fileMap = (Map<String, String>) JSON.parse(files);
		try {
			boolean isSuccess = sysOssService.saveBatchFile(fileMap);
			if(isSuccess){
				return Result.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("保存数据失败");
		}
		return Result.error("保存数据失败");
	}


	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public Result delete(@RequestBody Long[] ids){
		sysOssService.removeByIds(Arrays.asList(ids));

		return Result.ok();
	}

}
