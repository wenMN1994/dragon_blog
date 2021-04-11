package com.dragon.admin.modules.oss.controller;

import com.alibaba.fastjson.JSON;
import com.dragon.admin.common.annotation.SysLog;
import com.dragon.admin.modules.oss.service.SysOssService;
import com.dragon.common.utils.Result;
import com.dragon.common.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 文件上传
 *
 * @author Dragon Wen
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
	@SysLog(operation = "保存文件")
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
	@SysLog(operation = "删除文件")
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public Result delete(@RequestBody Map<String, Object> data){
		sysOssService.removeFileByIds(data);

		return Result.ok();
	}

}
