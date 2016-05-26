package com.atdld.open.cms.service.impl.article;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.open.cms.dao.article.CmsArticleContentDao;
import com.atdld.open.cms.entity.article.CmsArticleContent;
import com.atdld.open.cms.service.article.CmsArticleContentService;

@Service("cmsArticleContentService")
public class CmsArticleContentServiceImpl implements CmsArticleContentService{

	@Autowired
	private CmsArticleContentDao cmsArticleContentDao;


	@Override
	public void delArticleContent(String ids) {
		// TODO Auto-generated method stub
		cmsArticleContentDao.delArticleContent(ids);
		
	}

	@Override
	public long createArticleContent(CmsArticleContent lineArticleContent) {
		// TODO Auto-generated method stub
		return cmsArticleContentDao.createArticleContent(lineArticleContent);
	}

	@Override
	public CmsArticleContent getArticleContent(long id) {
		// TODO Auto-generated method stub
		return cmsArticleContentDao.getArticleContent(id);
	}

	@Override
	public long updateArticleContent(CmsArticleContent article) {
		// TODO Auto-generated method stub
		return cmsArticleContentDao.updateArticleContent(article);
	}
	

}
