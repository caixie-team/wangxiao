package com.atdld.open.cms.service.impl.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.open.cms.dao.article.CmsArticleDao;
import com.atdld.open.cms.entity.article.CmsArticle;
import com.atdld.open.cms.entity.article.QueryArticle;
import com.atdld.open.cms.service.article.CmsArticleService;
import com.atdld.open.core.entity.PageEntity;

@Service("cmsArticleService")
public class CmsArticleServiceImpl implements CmsArticleService{

	@Autowired
	private CmsArticleDao cmsArticleDao;
	
	@Override
	public long createArticle(CmsArticle article) {
		// TODO Auto-generated method stub
		return cmsArticleDao.createArticle(article);
	}


	@Override
	public CmsArticle getArticleById(long id) {
		// TODO Auto-generated method stub
		return cmsArticleDao.getArticleById(id);
	}

	@Override
	public void updateArticle(CmsArticle article) {
		// TODO Auto-generated method stub
		cmsArticleDao.updateArticle(article);
	}

	@Override
	public void delArticleByIds(String ids) {
		// TODO Auto-generated method stub
		cmsArticleDao.delArticleByIds(ids);
	}

	@Override
	public List<CmsArticle> getArticleList(QueryArticle queryArticle, PageEntity page) {
		// TODO Auto-generated method stub
		return cmsArticleDao.getArticleList(queryArticle,page);
	}


	@Override
	public CmsArticle getArticleId() {
		return cmsArticleDao.getArticleId();
	}


	@Override
	public List<CmsArticle> getArticleByType(long typeId,int showNum,String property,int isHot) {
 		return cmsArticleDao.getArticleByType(typeId,showNum,property,isHot);
	}

	public List<CmsArticle> getHotArticle(int num){
		return cmsArticleDao.getHotArticle(num);
	}
	
	/**获取上一篇或者下一篇资讯**/
	public CmsArticle getUpOrDownArticle(Long articleId,int type){
		return cmsArticleDao.getUpOrDownArticle(articleId, type);
	}
	
	/**更新浏览次数**/
	public void updateLookNum(Long articleId){
		cmsArticleDao.updateLookNum(articleId);
	}


	@Override
	public List<CmsArticle> getArticleByCondition(long typeId, String property,
			int showNum) {
		// TODO Auto-generated method stub
		QueryArticle queryArticle=new QueryArticle();
		queryArticle.setTypeId(typeId);
		queryArticle.setShowNum(showNum);
		queryArticle.setProperty(property);
		return cmsArticleDao.getArticleByCondition(queryArticle);
	}

}
