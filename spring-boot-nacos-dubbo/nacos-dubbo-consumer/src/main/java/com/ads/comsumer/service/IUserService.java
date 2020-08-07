package com.ads.comsumer.service;

import java.util.List;

import com.ads.api.entity.User;

public interface IUserService {

	String getConfig();

	String getInfo();

	List<User> getUserList();
}
