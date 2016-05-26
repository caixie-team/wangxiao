package io.wangxiao.edu.home.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.website.WebsiteMemcache;

import java.util.List;

public interface WebsiteMemcacheService {
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
    boolean queryWebsiteMemcacheIsExsit(String memKey);

    /**
     * 删除Memcache缓存
     *
     * @param memKey
     * @return
     */
    void removeMemcacheKey(String memKey);

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
