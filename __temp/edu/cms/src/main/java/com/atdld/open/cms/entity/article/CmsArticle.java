package com.atdld.open.cms.entity.article;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 线下资讯对象
 *
 */
@Data
public class CmsArticle implements Serializable{
	private static final long serialVersionUID = 8170201634496662L;
	private long articleId;//资讯ID
	private String title;//资讯标题 
	private String source;//资讯来源
	private String summary;//资讯摘要
	private String linkUrl;//保存路径
	private String typeName;
	private String author;//作者
	private Date addTime;//添加时间 
	private Date updateTime;//修改时间
	private Date pushTime;//发布时间
	private int typeId;//资讯类型
	private String typeLink;//资讯类型链
	private String pushStates;//文章发布状态
	private String imageUrl;//文章图片路径
	private int lookNum;//文章浏览量
	private String property;//资讯推荐状态
	private String content;//资讯内容
}
