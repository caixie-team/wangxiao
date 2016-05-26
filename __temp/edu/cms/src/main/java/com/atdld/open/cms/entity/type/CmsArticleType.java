package com.atdld.open.cms.entity.type;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 线下资讯类型对象
 *
 */
@Data
public class CmsArticleType implements Serializable{
	private static final long serialVersionUID = 8170201634496662L;
	private long typeId;//专业ID
	private String typeName;//专业名 
	private Date updateTime;//更新时间 
	private long parentId;//专业父ID 默认是-1，-1为根专业 
	private int isnav;//是否在导航栏显示
}
