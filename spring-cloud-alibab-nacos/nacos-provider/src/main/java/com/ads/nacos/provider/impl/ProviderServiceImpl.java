package com.ads.nacos.provider.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.ads.nacos.api.model.User;
import com.ads.nacos.api.service.ProviderService;
import com.ads.nacos.provider.dao.ProviderMapper;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service(version = "1.0.0", interfaceClass = ProviderService.class, group = "PROVIDER_SERVICE_GROUP")
@RefreshScope
@Slf4j
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, User> implements ProviderService {

	@Value("${localConfig}")
	private String localConfig;

	@Value("${server.port}")
	private String port;

	@Value("${dubbo.protocol.port}")
	private String dubboPort;

	@Autowired
	ProviderMapper providerMapper;

	@Override
	public String getConfig() {
		return "端口号：" + port + "，dubbo.port：" + dubboPort + "，localConfig：" + localConfig;
	}

	@Override
	// @SentinelResource(value = "getInfo")
	public String getInfo() {
		Entry entry = null;
		try {
			// sentinel获取许可的工具类
			entry = SphU.entry("QPS1");
		} catch (BlockException e) {
			log.warn("当前访问人数过多，请稍后再试");
			return "当前访问人数过多，请稍后再试";
		} finally {
			if (entry != null) {
				entry.exit();
			}
		}
		return "return info success";
	}
	
	/**
	 * 模拟超过响应时间进行降级处理
	 */
	@Override
	@SentinelResource(value = "sleep")
	public String sleep() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "sleep 1s";
	}

	@Override
	public List<User> getUserList() {
		return providerMapper.getUserList();
	}

}
