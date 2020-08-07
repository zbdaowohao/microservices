package com.ztc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerMain {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMain.class, args);
	}

}
