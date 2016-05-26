package com.atdld.os.app.service.website;

import java.util.List;

import com.atdld.os.app.entity.website.WebsiteUse;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * 使用统计
 */
public interface WebsiteUseService {
	/**
	 * 添加使用记录
	 */
	public void insertWebsiteUse(WebsiteUse websiteUse);

	/**
	 * 使用统计分页列表
	 */
	public List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse, PageEntity page);

}
