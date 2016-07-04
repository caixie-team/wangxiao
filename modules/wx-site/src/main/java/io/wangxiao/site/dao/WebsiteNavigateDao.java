package io.wangxiao.site.dao;

import io.wangxiao.site.model.WebsiteNavigate;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * 网站导航的数据操作接口
 */
public interface WebsiteNavigateDao extends BaseMapper<WebsiteNavigate> {

	/**
	 * 按条件查询
	 *
	 * @param websiteNavigate
	 * @return
     */
	List<WebsiteNavigate> findByCondition(WebsiteNavigate websiteNavigate);

}