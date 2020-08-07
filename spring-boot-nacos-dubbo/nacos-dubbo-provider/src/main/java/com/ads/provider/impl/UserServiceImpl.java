package com.ads.provider.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.ads.api.entity.User;
import com.ads.api.service.UserProviderService;
import com.ads.provider.dao.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(version = "1.0.0", interfaceClass = UserProviderService.class, group = "PROVIDER_SERVICE_GROUP")
//@RefreshScope
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserProviderService {

//	@Value("${localConfig}")
//	private String localConfig;
//
//	@Value("${server.port}")
//	private String port;
//
//	@Value("${dubbo.protocol.port}")
//	private String dubboPort;

	@Autowired
	UserMapper userMapper;

	@Override
	public String getConfig() {
		return "端口号：" /* + port + "，dubbo.port：" + dubboPort + "，localConfig：" + localConfig */;
	}

	@Override
	public String getInfo() {
		return "return info";
	}

	@Override
	public List<User> getUserList() {
		return userMapper.getUserList();
	}
}
