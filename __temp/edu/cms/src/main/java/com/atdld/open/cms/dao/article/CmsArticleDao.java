package com.atdld.open.cms.dao.article;

import com.atdld.open.cms.entity.article.CmsArticle;
import com.atdld.open.cms.entity.article.QueryArticle;
import com.atdld.open.core.entity.PageEntity;

import java.util.List;


/**
 * 线下资讯Dao
 *
 */
public interface CmsArticleDao {
	
	/**创建线下资讯分类*/
	public long createArticle(CmsArticle article);
	
	/**查询所有的线下资讯列表*/
	public List<CmsArticle> getArticleList(QueryArticle queryArticle, PageEntity page);
	
	/**根据id查询资讯**/
	public CmsArticle getArticleById(long id);
	
	/**修改线下资讯分类名*/
	public void updateArticle(CmsArticle article);
	
	/**获取最新添加资讯的id*/
	public CmsArticle getArticleId();

	/**批量删除分类*/
	public void delArticleByIds(String ids);
	
	/**根据分类显示资讯**/
	public List<CmsArticle> getArticleByType(long typeId,int showNum,String property,int isHot);
	
	/***获取最热资讯*/
	public List<CmsArticle> getHotArticle(int num);
	
	/**获取上一篇或者下一篇资讯**/
	public CmsArticle getUpOrDownArticle(Long articleId,int type);
	
	/**更新浏览次数**/
	public void updateLookNum(Long articleId);
	
	/**根据条件查询资讯**/
	public List<CmsArticle> getArticleByCondition(QueryArticle queryArticle);
}
