package com.ads.springcloud.controller.businessController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ads.springcloud.bean.Dept;
import com.ads.springcloud.controller.BaseController;
import com.ads.springcloud.service.DeptService;
import com.baomidou.mybatisplus.extension.service.IService;

import io.swagger.annotations.Api;

@Controller
@Api(tags = { "部门管理" })
@RequestMapping("/dept")
public class DeptController extends BaseController<Dept, String> {

	@Autowired
	private DeptService deptService;

	@Override
	public IService<Dept> getService() {
		return deptService;
	}

}
