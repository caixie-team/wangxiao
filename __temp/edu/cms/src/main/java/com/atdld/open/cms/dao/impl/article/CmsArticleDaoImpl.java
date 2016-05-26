package com.atdld.open.cms.dao.impl.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.open.cms.dao.article.CmsArticleDao;
import com.atdld.open.cms.entity.article.CmsArticle;
import com.atdld.open.cms.entity.article.QueryArticle;
import com.atdld.open.core.dao.impl.common.GenericDaoImpl;
import com.atdld.open.core.entity.PageEntity;

@Repository("cmsArticleDao")
public class CmsArticleDaoImpl extends GenericDaoImpl implements CmsArticleDao{

	@Override
	public long createArticle(CmsArticle article) {
		// TODO Auto-generated method stub
		return insert("CmsArticleMapper.createArticle", article);
	}

	@Override
	public void updateArticle(CmsArticle article) {
		// TODO Auto-generated method stub
		update("CmsArticleMapper.updateArticle", article);
	}

	@Override
	public void delArticleByIds(String ids) {
		delete("CmsArticleMapper.delArticleByIds", ids);
	}

	@Override
	public List<CmsArticle> getArticleList(QueryArticle queryArticle, PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("CmsArticleMapper.getArticleListPage", queryArticle, page);
	}

	@Override
	public CmsArticle getArticleById(long id) {
		// TODO Auto-generated method stub
		return  selectOne("CmsArticleMapper.getArticleById", id);
	}

	@Override
	public CmsArticle getArticleId() {
		// TODO Auto-generated method stub
		return  this.selectOne("CmsArticleMapper.getArticleId", null);
	}

	@Override
	public List<CmsArticle> getArticleByType(long typeId,int showNum,String property,int isHot) {
		// TODO Auto-generated method stub
		QueryArticle queryArticle=new QueryArticle();
		queryArticle.setTypeId(typeId);
		queryArticle.setShowNum(showNum);
		queryArticle.setProperty(property);
		queryArticle.setLookNum(isHot);
		return selectList("CmsArticleMapper.getArticleByType", queryArticle);
	}
	//获取最热资讯
	public List<CmsArticle> getHotArticle(int num){
		return this.selectList("CmsArticleMapper.getHotArticle", num);
	}
	//获取上一篇或者下一篇资讯
	public CmsArticle getUpOrDownArticle(Long articleId,int type){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("articleId", articleId);
		map.put("type", type);
		return this.selectOne("CmsArticleMapper.getUpOrDownArticle", map);
	}

	/**更新浏览次数**/
	public void updateLookNum(Long articleId){
		this.update("CmsArticleMapper.updateLookNum", articleId);
	}

	@Override
	public List<CmsArticle> getArticleByCondition(QueryArticle queryArticle) {
		// TODO Auto-generated method stub
		return selectList("CmsArticleMapper.getArticleByCondition", queryArticle);
	}

}
