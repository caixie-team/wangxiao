package com.atdld.os.edu.dao.website;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.website.WebsiteMemcache;

/**
 * 
 * @ClassName com.atdld.os.edu.dao.website.WebsiteMemcacheDao
 * @description
 * @author :
 * @Create Date : 2014年9月23日 下午6:09:08
 */
public interface WebsiteMemcacheDao {
	/**
	 * 添加memcache key
	 * 
	 * @param websiteMemcache
	 */
	public void addWebsiteMemcache(WebsiteMemcache websiteMemcache);

	/**
	 * 查询memcache管理list
	 * 
	 * @param websiteMemcache
	 * @param page
	 * @return
	 */
	public List<WebsiteMemcache> queryWebsiteMemcacheList(WebsiteMemcache websiteMemcache, PageEntity page);

	/**
	 * 查詢Memcachekey是否存在
	 * 
	 * @param memKey
	 * @return
	 */
	public Integer queryWebsiteMemcacheIsExsit(String memKey);

	/**
	 * 查询详情
	 * 
	 * @param id
	 * @return
	 */
	public WebsiteMemcache queryWebsiteMemcacheById(Long id);

	/**
	 * 删除memcache
	 * 
	 * @param id
	 */
	public void deleteWebsiteMemcacheById(Long id);
}
