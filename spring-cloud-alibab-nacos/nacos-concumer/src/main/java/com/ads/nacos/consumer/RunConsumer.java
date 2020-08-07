package com.ads.nacos.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class RunConsumer {

	public static void main(String[] args) {
		SpringApplication.run(RunConsumer.class, args);
	}

}
