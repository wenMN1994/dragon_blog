package com.dragon.admin.modules.oss.service;

import com.dragon.admin.modules.oss.entity.SysOssEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.common.utils.PageUtils;

import java.util.Map;

/**
 * 文件上传
 *
 * @author Dragon Wen
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 批量保存文件
	 * @param fileMap
	 * @return
	 */
	boolean saveBatchFile(Map<String, String> fileMap);

	/**
	 * 从数据库中删除文件并删除阿里云oss文件
	 * @param data
	 */
	void removeFileByIds(Map<String, Object> data);
}
