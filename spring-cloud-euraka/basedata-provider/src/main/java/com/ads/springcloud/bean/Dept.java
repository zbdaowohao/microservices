package com.ads.springcloud.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
//@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Dept implements Serializable// entity --orm--- db_table
{
	private static final long serialVersionUID = 5037014213352996316L;
	private Long id; // 主键
	private String dname; // 部门名称
	private String dbSource;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库
}
