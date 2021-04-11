package com.dragon.admin.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.admin.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author Dragon Wen
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
