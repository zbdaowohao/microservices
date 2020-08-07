package com.ads.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ads.api.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author project
 * @since 2020-01-08
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
	
	@Select("SELECT user_id, user_name, email FROM u_user")
	List<User> getUserList();
	
}
