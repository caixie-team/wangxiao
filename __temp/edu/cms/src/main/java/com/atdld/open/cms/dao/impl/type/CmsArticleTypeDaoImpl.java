package com.atdld.open.cms.dao.impl.type;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.open.cms.dao.type.CmsArticleTypeDao;
import com.atdld.open.cms.entity.type.CmsArticleType;
import com.atdld.open.core.dao.impl.common.GenericDaoImpl;

@Repository("CmsArticleTypeDao")
public class CmsArticleTypeDaoImpl extends GenericDaoImpl implements CmsArticleTypeDao{

	@Override
	public long createArticleType(CmsArticleType articleType) {
		return insert("CmsArticleTypeMapper.createArticleType", articleType);
	}

	@Override
	public List<CmsArticleType> queryAllArticleTypeList() {
		return selectList("CmsArticleTypeMapper.queryAllArticleTypeList", null);
	}

	@Override
	public void updateArticleTypeName(CmsArticleType articleType) {
		update("CmsArticleTypeMapper.updateArticleTypeName", articleType);
	}

	@Override
	public void updateTypeParentId(CmsArticleType articleType) {
		update("CmsArticleTypeMapper.updateTypeParentId", articleType);
	}

	@Override
	public void deleteArticleType(String ids) {
		delete("CmsArticleTypeMapper.deleteArticleType", ids);
	}

	@Override
	public List<CmsArticleType> queryChildTypeListByParentId(long typeId) {
		return selectList("CmsArticleTypeMapper.queryChildTypeListByParentId", typeId);
	}

	@Override
	public List<CmsArticleType> queryWebLineArticle() {
		return selectList("CmsArticleTypeMapper.queryWebLineArticle", null);
	}

	@Override
	public void showNav(String ids) {
		// TODO Auto-generated method stub
		update("CmsArticleTypeMapper.showNav", ids);
	}

	@Override
	public void hideNav() {
		// TODO Auto-generated method stub
		update("CmsArticleTypeMapper.hideNav", null);
	}

}
