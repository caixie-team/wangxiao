package com.atdld.os.app.service.website;

import java.util.List;

import com.atdld.os.app.entity.website.WebsiteInstall;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * 登陆统计
 */
public interface WebsiteInstallService {
	/**
	 * 添加安装记录
	 */
	public void insertWebsiteInstall(WebsiteInstall websiteInstall);

	/**
	 * 安装统计分页列表
	 */
	public List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page);

}
