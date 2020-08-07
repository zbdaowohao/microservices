package com.ads.comsumer.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.api.entity.User;
import com.ads.api.service.UserProviderService;
import com.ads.comsumer.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Reference(version = "1.0.0", check = false, group = "PROVIDER_SERVICE_GROUP")
	private UserProviderService userProviderService;

	@GetMapping("/info")
	public String getInfo() {
		return userProviderService.getInfo();
	}

	@GetMapping("/config")
	public String getConfig() {
		return userService.getConfig();
	}

	@GetMapping("/users")
	public List<User> getUserList() {
		return userService.getUserList();
	}

}
