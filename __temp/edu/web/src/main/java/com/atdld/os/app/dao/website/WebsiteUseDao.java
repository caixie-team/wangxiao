package com.atdld.os.app.dao.website;

import java.util.List;

import com.atdld.os.app.entity.website.WebsiteUse;
import com.atdld.os.core.entity.PageEntity;


/**
 * 
 * 使用统计
 *
 */
public interface WebsiteUseDao {
	
	/**
	 * 添加使用统计
	 */
	public void insertWebsiteUse(WebsiteUse websiteUse);

    /**
     * 更新使用统计的使用结束时间
     */
    public void updateWebsiteUseForEndtime(WebsiteUse websiteUse);
	/**
	 * 使用统计分页列表
	 */
	public List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse, PageEntity page);


}
