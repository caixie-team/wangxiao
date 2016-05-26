package com.atdld.open.cms.entity.article;

import lombok.Data;

import java.io.Serializable;

/**
 * 线下资讯内容对象
 */
@Data
public class CmsArticleContent implements Serializable{
	private static final long serialVersionUID = 8170201634496662L;
	private long contentId;//资讯内容ID
	private String content;//资讯内容
	private long articleId;//资讯标题 
}
