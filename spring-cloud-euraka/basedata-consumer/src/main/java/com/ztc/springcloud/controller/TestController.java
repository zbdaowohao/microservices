package com.ztc.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.jackson.builder.StringInterningAmazonInfoBuilder;

@RestController
public class TestController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/consumer/test")
	public String test(){
		String url = "http://BASEDATA-PROVIDER";
		return this.restTemplate.getForObject(url+"/get", String.class);
		//return this.restTemplate.getForObject("http://localhost:8001/get", String.class);
	}
}
