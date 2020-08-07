package com.ads.api.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "user_id")
	private Integer userId;

	// @NotEmpty(message = "用户名不能为空！")
	private String userName;

	private String email;

	@TableField(exist = false)
	private String group = "group";

}
