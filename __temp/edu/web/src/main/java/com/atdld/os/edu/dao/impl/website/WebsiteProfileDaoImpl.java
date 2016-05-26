package com.atdld.os.edu.dao.impl.website;

import java.util.List;

import com.atdld.os.edu.entity.website.WebsiteProfile;
import com.atdld.os.edu.dao.website.WebsiteProfileDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
/**
 * 
 * @ClassName  com.atdld.os.edu.dao.impl.website.WebsiteProfileDaoImpl
 * @description
 * @author :
 * @Create Date : 2014年6月12日 上午9:46:05
 */
 @Repository("websiteProfileDao")
public class WebsiteProfileDaoImpl extends GenericDaoImpl implements WebsiteProfileDao{

	/**
	 * 根据type查询网站配置
	 * 
	 * @param type
	 * @return
	 */
	public WebsiteProfile getWebsiteProfileByType(String type) {
		return this.selectOne("WebsiteProfileMapper.getWebsiteProfileByType", type);
	}

	/**
	 * 更新网站配置管理
	 * 
	 * @param websiteProfile
	 */
	public void updateWebsiteProfile(WebsiteProfile websiteProfile) {
		this.update("WebsiteProfileMapper.updateWebsiteProfile", websiteProfile);
	}

	/**
	 * 查询网站配置
	 * 
	 * @return List<WebsiteProfile>
	 */
	public List<WebsiteProfile> getWebsiteProfileList() {
		return this.selectList("WebsiteProfileMapper.getWebsiteProfileList", null);
	}

}
