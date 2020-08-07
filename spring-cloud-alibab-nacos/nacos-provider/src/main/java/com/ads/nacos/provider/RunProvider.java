package com.ads.nacos.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ads.nacos.provider.dao")
public class RunProvider {

	public static void main(String[] args) {
		SpringApplication.run(RunProvider.class, args);
	}

}
