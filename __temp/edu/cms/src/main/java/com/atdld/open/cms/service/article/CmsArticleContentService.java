package com.atdld.open.cms.service.article;

import com.atdld.open.cms.entity.article.CmsArticleContent;


/**
 * 线下资讯内容Service
 */
public interface CmsArticleContentService {
	/**创建线下资讯分类内容*/
	public long createArticleContent(CmsArticleContent lineArticleContent);
	
	/**批量删除分类*/
	public void delArticleContent(String ids);
	
	/******根据资讯id查询资讯内容********/
	public CmsArticleContent getArticleContent(long id);
	
	/******修改资讯内容********/
	public long updateArticleContent(CmsArticleContent article);
}
