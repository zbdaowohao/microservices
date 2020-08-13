package com.ads.nacos.provider.sentinel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@Configuration
public class FlowConfig {
	
	private String LIMIT_KEY = "QPS1";
	
	// 初始限流流规则
	@PostConstruct
	public void inintFlowQpsRule() {
		List<FlowRule> rules = new ArrayList<FlowRule>();
		FlowRule flowRule = new FlowRule();
		// 限流的资源
		flowRule.setResource(LIMIT_KEY);
		// 限流规则
		flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// 设置并发数2(1s只能有2个请求进来)
		flowRule.setCount(2);
		// 应用来源(隔离用)
		flowRule.setLimitApp("default");
		rules.add(flowRule);
		
		FlowRuleManager.loadRules(rules);
	}

}
