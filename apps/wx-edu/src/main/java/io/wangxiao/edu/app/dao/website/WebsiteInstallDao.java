package io.wangxiao.edu.app.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.WebsiteInstall;

import java.util.List;

/**
 *
 * 安装统计
 */
public interface WebsiteInstallDao {
	/**
	 * 添加安装记录
	 */
	void insertWebsiteInstall(WebsiteInstall websiteInstall);

	/**
	 * 安装统计分页列表
	 */
	List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page);

}
