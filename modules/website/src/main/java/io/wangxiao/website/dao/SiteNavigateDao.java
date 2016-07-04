package io.wangxiao.website.dao;

import io.wangxiao.website.entity.WebsiteNavigate;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * 网站导航的数据操作接口
 */
public interface SiteNavigateDao extends BaseMapper<WebsiteNavigate> {

	/**
	 * 按条件查询
	 *
	 * @param websiteNavigate
	 * @return
     */
//	List<WebsiteNavigate> findAllByCondition(WebsiteNavigate websiteNavigate);

}