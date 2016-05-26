package com.atdld.open.cms.dao.impl.article;

import org.springframework.stereotype.Repository;

import com.atdld.open.cms.dao.article.CmsArticleContentDao;
import com.atdld.open.cms.entity.article.CmsArticleContent;
import com.atdld.open.core.dao.impl.common.GenericDaoImpl;

@Repository("cmsArticleContentDao")
public class CmsArticleContentDaoImpl extends GenericDaoImpl implements CmsArticleContentDao{

	@Override
	public void delArticleContent(String ids) {
		// TODO Auto-generated method stub
		delete("CmsArticleContentMapper.delArticleContent", ids);
	}

	@Override
	public long createArticleContent(CmsArticleContent lineArticleContent) {
		// TODO Auto-generated method stub
		return insert("CmsArticleContentMapper.createArticleContent", lineArticleContent);
	}

	@Override
	public CmsArticleContent getArticleContent(long id) {
		// TODO Auto-generated method stub
		return selectOne("CmsArticleContentMapper.getArticleContentById", id);
	}

	@Override
	public long updateArticleContent(CmsArticleContent article) {
		// TODO Auto-generated method stub
		return update("CmsArticleContentMapper.updateArticleContent", article);
	}

}
