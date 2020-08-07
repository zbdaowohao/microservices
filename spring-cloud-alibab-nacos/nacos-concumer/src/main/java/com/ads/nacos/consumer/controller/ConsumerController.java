package com.ads.nacos.consumer.controller;

import java.util.List;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.nacos.api.model.User;
import com.ads.nacos.api.service.ProviderService;

@RestController
public class ConsumerController {

	@Reference(version = "1.0.0", check = false, group = "PROVIDER_SERVICE_GROUP")
	private ProviderService providerService;

	@GetMapping("/info")
	public String getInfo() {
		return providerService.getInfo();
	}

	@GetMapping("/config")
	public String getConfig() {
		return providerService.getConfig();
	}

	@GetMapping("/users")
	public List<User> getUserList() {
		return providerService.getUserList();
	}

}
