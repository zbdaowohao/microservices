package com.ads.comsumer.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.ads.api.entity.User;
import com.ads.api.service.UserProviderService;
import com.ads.comsumer.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Reference(version = "1.0.0", check = false, group = "PROVIDER_SERVICE_GROUP")
	private UserProviderService userProviderService;

	public String getInfo() {
		return userProviderService.getInfo();
	}

	public String getConfig() {
		return userProviderService.getConfig();
	}

	public List<User> getUserList() {
		return userProviderService.getUserList();
	}

}
