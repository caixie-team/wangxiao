package io.wangxiao.website.dao;

import io.wangxiao.core.BaseDao;
import io.wangxiao.website.entity.WebsiteNavigate;

import java.util.List;

/**
 *
 */
public interface WebsiteNavigateDao extends BaseDao<WebsiteNavigate> {
	
	/**
	 * 导航列表
	 * @return
	 */
	public List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 添加导航
	 * @param WebsiteNavigate
	 */
	public void addWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 冻结、解冻导航
	 * @param WebsiteNavigate
	 */
	public void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * 删除导航
	 * @param id
	 */
	public void delWebsiteNavigate(Long id);
	/**
	 * 更新导航
	 * @param WebsiteNavigate
	 */
	public void updateWebsiteNavigate(WebsiteNavigate websiteNavigate);
	/**
	 * id查询导航
	 * @param id
	 * @return
	 */
	public WebsiteNavigate getWebsiteNavigateById(Long id);
	/**
	 * 查询未冻结的导航列表
	 * @return
	 */
	public List<WebsiteNavigate> getWebNavigate();
	
}