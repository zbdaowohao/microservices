package com.ads.api.service;

import java.util.List;

import com.ads.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author project
 * @since 2020-01-08
 */
public interface UserProviderService extends IService<User> {

	String getConfig();

	String getInfo();

	List<User> getUserList();
}
