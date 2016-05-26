package io.wangxiao.edu.home.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.website.WebsiteMemcache;

import java.util.List;

public interface WebsiteMemcacheDao {
    /**
     * 添加memcache key
     *
     * @param websiteMemcache
     */
    void addWebsiteMemcache(WebsiteMemcache websiteMemcache);

    /**
     * 查询memcache管理list
     *
     * @param websiteMemcache
     * @param page
     * @return
     */
    List<WebsiteMemcache> queryWebsiteMemcacheList(WebsiteMemcache websiteMemcache, PageEntity page);

    /**
     * 查詢Memcachekey是否存在
     *
     * @param memKey
     * @return
     */
    Integer queryWebsiteMemcacheIsExsit(String memKey);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    WebsiteMemcache queryWebsiteMemcacheById(Long id);

    /**
     * 删除memcache
     *
     * @param id
     */
    void deleteWebsiteMemcacheById(Long id);
}
