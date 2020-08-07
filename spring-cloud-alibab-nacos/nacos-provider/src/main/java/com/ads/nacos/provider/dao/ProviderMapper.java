package com.ads.nacos.provider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ads.nacos.api.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ProviderMapper extends BaseMapper<User> {

	@Select("SELECT user_id, user_name, email FROM u_user")
	List<User> getUserList();

}