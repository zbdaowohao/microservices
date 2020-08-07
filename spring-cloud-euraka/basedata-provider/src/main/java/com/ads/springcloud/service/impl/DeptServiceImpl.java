package com.ads.springcloud.service.impl;

import org.springframework.stereotype.Service;

import com.ads.springcloud.bean.Dept;
import com.ads.springcloud.mapper.DeptMapper;
import com.ads.springcloud.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper,Dept> implements DeptService {

}
