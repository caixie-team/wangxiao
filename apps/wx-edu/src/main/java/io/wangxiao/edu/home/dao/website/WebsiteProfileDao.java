package io.wangxiao.edu.home.dao.website;

import java.util.List;

import io.wangxiao.edu.home.entity.website.WebsiteProfile;

/**
 * 网站配置
 * 
 * @ClassName com.business.platform.edu.dao.website.WebsiteProfileDao
 * @description
 * @author :xull
 * @Create Date : 2014年6月12日 上午9:42:53
 */
public interface WebsiteProfileDao {
	/**
	 * 根据type查询网站配置
	 * 
	 * @param type
	 * @return
	 */
	public WebsiteProfile getWebsiteProfileByType(String type);

	/**
	 * 更新网站配置管理
	 * 
	 * @param websiteProfile
	 */
	public void updateWebsiteProfile(WebsiteProfile websiteProfile);

	/**
	 * 查询网站配置
	 * 
	 * @return List<WebsiteProfile>
	 */
	public List<WebsiteProfile> getWebsiteProfileList();
}