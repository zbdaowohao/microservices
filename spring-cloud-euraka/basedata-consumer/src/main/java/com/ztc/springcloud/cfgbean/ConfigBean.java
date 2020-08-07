package com.ztc.springcloud.cfgbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
	
	@Bean
	@LoadBalanced  //开启Riboon客户端负载均衡
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
