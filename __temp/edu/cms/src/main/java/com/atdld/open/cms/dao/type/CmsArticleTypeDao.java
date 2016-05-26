package com.atdld.open.cms.dao.type;

import com.atdld.open.cms.entity.type.CmsArticleType;

import java.util.List;


/**
 * 线下资讯分类Dao
 *
 */
public interface CmsArticleTypeDao {
	
	/**创建线下资讯分类*/
	public long createArticleType(CmsArticleType articleType);
	
	/**查询所有的线下资讯分类列表*/
	public List<CmsArticleType> queryAllArticleTypeList();
	
	/**修改线下资讯分类名*/
	public void updateArticleTypeName(CmsArticleType articleType);
	
	/**修改线下资讯分类父ID*/
	public void updateTypeParentId(CmsArticleType articleType);

	/**批量删除分类*/
	public void deleteArticleType(String ids);
	
	/**根据类型ID，查询类型下的子级类型List*/
	public List<CmsArticleType> queryChildTypeListByParentId(long typeId);
	
	/**前台查询资讯类型List*/
	public List<CmsArticleType> queryWebLineArticle();
	
	/**将分类显示到导航栏**/
	public void showNav(String ids);
	/**取消导航栏显示**/
	public void hideNav();
}
