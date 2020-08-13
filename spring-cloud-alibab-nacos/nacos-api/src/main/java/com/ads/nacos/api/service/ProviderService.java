package com.ads.nacos.api.service;

import java.util.List;

import com.ads.nacos.api.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ProviderService extends IService<User> {

	String getConfig();

	String getInfo();
	
	String sleep();

	List<User> getUserList();
}
