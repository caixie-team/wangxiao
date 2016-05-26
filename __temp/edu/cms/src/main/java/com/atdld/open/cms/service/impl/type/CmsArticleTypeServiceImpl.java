package com.atdld.open.cms.service.impl.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.open.cms.dao.type.CmsArticleTypeDao;
import com.atdld.open.cms.entity.type.CmsArticleType;
import com.atdld.open.cms.service.type.CmsArticleTypeService;

@Service("cmsArticleTypeService")
public class CmsArticleTypeServiceImpl implements CmsArticleTypeService{
	
	@Autowired
	private CmsArticleTypeDao cmsArticleTypeDao;

	@Override
	public long createArticleType(CmsArticleType articleType) {
		return cmsArticleTypeDao.createArticleType(articleType);
	}

	@Override
	public List<CmsArticleType> queryAllArticleTypeList() {
		return cmsArticleTypeDao.queryAllArticleTypeList();
	}

	@Override
	public void updateArticleTypeName(CmsArticleType articleType) {
		cmsArticleTypeDao.updateArticleTypeName(articleType);
	}

	@Override
	public void updateTypeParentId(CmsArticleType articleType) {
		cmsArticleTypeDao.updateTypeParentId(articleType);
	}

	@Override
	public void deleteArticleType(String ids) {
		cmsArticleTypeDao.deleteArticleType(ids);
	}

	@Override
	public List<CmsArticleType> queryChildTypeListByParentId(long typeId) {
		return cmsArticleTypeDao.queryChildTypeListByParentId(typeId);
	}

	@Override
	public List<CmsArticleType> queryWebLineArticle() {
		return cmsArticleTypeDao.queryWebLineArticle();
	}

	@Override
	public void showNav(String ids) {
		// TODO Auto-generated method stub
		cmsArticleTypeDao.showNav(ids);
	}

	@Override
	public void hideNav() {
		// TODO Auto-generated method stub
		cmsArticleTypeDao.hideNav();
	}
	

}
