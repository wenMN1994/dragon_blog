package com.dragon.admin.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.common.utils.Result;
import com.dragon.admin.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author Dragon Wen
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
