package com.ads.nacos.consumer.controller.sentinel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@Configuration
public class FlowConfig {

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
		flowRule2.setCount(2); // QPS值
		flowRule2.setLimitApp("default");

		FlowRule flowRule3 = new FlowRule();
		flowRule3.setResource(LIMIT_KEY_3);
		flowRule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
		flowRule3.setCount(3); // QPS值
		flowRule3.setLimitApp("default");

		rules.add(flowRule);
		rules.add(flowRule2);
		rules.add(flowRule3);
		FlowRuleManager.loadRules(rules);
		System.out.println("配置限流规则成功");
	}

}
