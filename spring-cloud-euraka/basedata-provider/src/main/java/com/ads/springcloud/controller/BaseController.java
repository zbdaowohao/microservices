package com.ads.springcloud.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ads.springcloud.common.Result;
import com.ads.springcloud.common.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import io.swagger.annotations.ApiOperation;

public abstract class BaseController<E, ID extends Serializable> {

	@Autowired
	public abstract IService<E> getService();

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "通过id获取")
	public Result<E> get(@PathVariable ID id) {
		E entity = getService().getById(id);
		return new ResultUtil<E>().setData(entity);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取全部数据")
	public Result<List<E>> getAll() {
		QueryWrapper<E> queryWrapper = new QueryWrapper<>();
		List<E> list = getService().list(queryWrapper);
		return new ResultUtil<List<E>>().setData(list,"成功");
	}

	@RequestMapping(value = "/getByPage", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "分页获取")
	public Result<IPage<E>> getByPage(@ModelAttribute Page<E> page, E e) {
		QueryWrapper<E> queryWrapper = new QueryWrapper<E>(e);
		IPage<E> pageResult = getService().page(page, queryWrapper);
		return new ResultUtil<IPage<E>>().setData(pageResult);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "保存数据")
	public Result<E> save(@RequestBody E entity) {
		Boolean e = getService().save(entity);
		return e == true ? new ResultUtil<E>().setSuccessMsg("保存成功") : new ResultUtil<E>().setErrorMsg("保存失败");
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "更新数据")
	public Result<E> update(@RequestBody E entity) {
		Boolean e = getService().updateById(entity);
		return e == true ? new ResultUtil<E>().setSuccessMsg("保存成功") : new ResultUtil<E>().setErrorMsg("保存失败");
	}

	@RequestMapping(value = "/delByIds/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(value = "批量通过id删除")
	public Result<Object> delAllByIds(@PathVariable List<ID> ids) {
		getService().removeByIds(ids);
		return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
	}

}
