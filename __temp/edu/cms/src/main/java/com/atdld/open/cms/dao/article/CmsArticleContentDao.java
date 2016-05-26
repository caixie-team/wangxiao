package com.atdld.open.cms.dao.article;

import com.atdld.open.cms.entity.article.CmsArticleContent;


/**
 * 线下资讯内容Dao
 *
 */
public interface CmsArticleContentDao {
	
	/**创建线下资讯分类*/
	public long createArticleContent(CmsArticleContent lineArticleContent);
	
	/******根据资讯id查询资讯内容********/
	public CmsArticleContent getArticleContent(long id);
	
	/******修改资讯内容********/
	public long updateArticleContent(CmsArticleContent article);
	
	/**批量删除分类*/
	public void delArticleContent(String ids);
}
