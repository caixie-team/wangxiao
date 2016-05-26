package com.atdld.open.cms.entity.article;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryArticle implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 4550896941810655734L;
    private long articleId;//资讯ID
	private String title;//资讯标题 
	private String source;//资讯标题 
	private String summary;//资讯摘要
	private String typeName;
	private String author;//作者
	private Date addTime;//添加时间 
	private Date updateTime;//修改时间
	private Date pushTime;//发布时间
	private long typeId;//资讯类型
	private String typeLink;//资讯类型链
	private String pushStates;//文章发布状态
	private String imageUrl;//文章图片路径
	private int lookNum;//文章浏览量
	private String property;//资讯推荐状态
	private String content;//资讯内容
	private String linkUrl;//保存路径
	private int showNum;//显示资讯条数
}
