package com.ads.nacos.consumer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ads.nacos.api.service.ProviderService;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@RestController
@RequestMapping
public class SentinelController {
	
	@Reference(version = "1.0.0", check = false, group = "PROVIDER_SERVICE_GROUP")
	private ProviderService providerService;

		private static final String LIMIT_KEY_1 = "testQps1";

		private static final String LIMIT_KEY_2 = "testQps2";
		
		private static final String LIMIT_KEY_3 = "testQps3";
		
		// 初始限流流规则
		@PostConstruct
		public void inintFlowQpsRule() {
			List<FlowRule> rules = new ArrayList<FlowRule>();
			FlowRule flowRule = new FlowRule();
			// 流控规则资源名称
			flowRule.setResource(LIMIT_KEY_1);
			// 限流规则
			flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
			// 设置并发数2(1s只能有2个请求进来)
			flowRule.setCount(1);
			// 应用来源(隔离用)
			flowRule.setLimitApp("default");
			
			FlowRule flowRule2 = new FlowRule();
	        flowRule2.setResource(LIMIT_KEY_2);
	        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
	        flowRule2.setCount(2); //QPS值
	        flowRule2.setLimitApp("default");
	        
	        FlowRule flowRule3 = new FlowRule();
	        flowRule3.setResource(LIMIT_KEY_3);
	        flowRule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
	        flowRule3.setCount(3); //QPS值
	        flowRule3.setLimitApp("default");
			
	        rules.add(flowRule);
	        rules.add(flowRule2);
	        rules.add(flowRule3);
			FlowRuleManager.loadRules(rules);
			System.out.println("配置限流规则成功");
		}
		

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
