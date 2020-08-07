package com.ads.nacos.provider.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.ads.nacos.api.model.User;
import com.ads.nacos.api.service.ProviderService;
import com.ads.nacos.provider.dao.ProviderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service(version = "1.0.0", interfaceClass = ProviderService.class, group = "PROVIDER_SERVICE_GROUP")
@RefreshScope
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, User> implements ProviderService {

	@Value("${localConfig}")
	private String localConfig;

	@Value("${server.port}")
	private String port;

	@Value("${dubbo.protocol.port}")
	private String dubboPort;

	@Autowired
	ProviderMapper providerMapper;

	public String getConfig() {
		return "端口号：" + port + "，dubbo.port：" + dubboPort + "，localConfig：" + localConfig;
	}

	@Override
	public String getInfo() {
		return "return info";
	}

	@Override
	public List<User> getUserList() {
		return providerMapper.getUserList();
	}

}
