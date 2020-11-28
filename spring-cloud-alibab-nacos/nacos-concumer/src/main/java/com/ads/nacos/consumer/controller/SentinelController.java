package com.ads.nacos.consumer.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.nacos.api.service.ProviderService;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@RestController
@RequestMapping
public class SentinelController {
	
	@Reference(version = "1.0.0", check = false, group = "PROVIDER_SERVICE_GROUP")
	private ProviderService providerService;

		private static final String LIMIT_KEY_1 = "testQps1";

		private static final String LIMIT_KEY_2 = "testQps2";
		
		private static final String LIMIT_KEY_3 = "testQps3";
		

	    @GetMapping("/testQPS1")
	    public String testQPS1() {
	        //Entry获取许可
	        long threadId = Thread.currentThread().getId();
	        Entry entry = null;
	        try {
	            entry = SphU.entry(LIMIT_KEY_1);
	            System.out.println("testQPS1 success---" + threadId);
	            return "testQPS1 success---" + threadId;
	        } catch (BlockException e) {
	            //如果抛异常了，则表示被限流了
	            //e.printStackTrace();
	            System.out.println("当前访问量过大，请稍后重试..." + threadId);
	            return "当前访问量过大，请稍后重试..." + threadId;
	        } finally {
	            if (entry != null) {
	                entry.exit();
	            }
	        }
	    }

	    @GetMapping("/testQPS2")
	    @SentinelResource(value = LIMIT_KEY_2, blockHandler = "testBlockHandler")
	    public String testQPS2() {
	        long threadId = Thread.currentThread().getId();
	        System.out.println("testQPS1 success---" + threadId);
	        return "testQPS1 success---" + threadId;
	    }

	    public String testBlockHandler(BlockException e) {
	        long threadId = Thread.currentThread().getId();
	        System.out.println("当前访问量过大，请稍后重试..." + threadId);
	        return "当前访问量过大，请稍后重试..." + threadId;
	    }
	    
		@GetMapping("/info1")
		@SentinelResource(value = LIMIT_KEY_3, blockHandler = "testBlockHandler")
		public String getInfo() {
			long threadId = Thread.currentThread().getId();
		    System.out.println("testQPS1 success---" + threadId);
			return providerService.getInfo();
		}

}
