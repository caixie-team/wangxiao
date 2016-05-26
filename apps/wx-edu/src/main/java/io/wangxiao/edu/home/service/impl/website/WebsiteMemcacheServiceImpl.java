package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.edu.home.dao.website.WebsiteMemcacheDao;
import io.wangxiao.edu.home.entity.website.WebsiteMemcache;
import io.wangxiao.edu.home.service.website.WebsiteMemcacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("websiteMemcacheService")
public class WebsiteMemcacheServiceImpl implements WebsiteMemcacheService {
    @Autowired
    private WebsiteMemcacheDao websiteMemcacheDao;
    private CacheKit cacheKit = CacheKit.getInstance();

    /**
     * 添加memcache key
     *
     * @param websiteMemcache
     */
    public void addWebsiteMemcache(WebsiteMemcache websiteMemcache) {
        websiteMemcacheDao.addWebsiteMemcache(websiteMemcache);
    }

    /**
     * 查询memcache管理list
     *
     * @param websiteMemcache
     * @param page
     * @return
     */
    public List<WebsiteMemcache> queryWebsiteMemcacheList(WebsiteMemcache websiteMemcache, PageEntity page) {
        return websiteMemcacheDao.queryWebsiteMemcacheList(websiteMemcache, page);
    }

    /**
     * 查詢Memcachekey是否存在
     *
     * @param memKey
     * @return
     */
    public boolean queryWebsiteMemcacheIsExsit(String memKey) {
        int flag = websiteMemcacheDao.queryWebsiteMemcacheIsExsit(memKey);
        if (flag == 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除Memcache缓存
     *
     * @param memKey
     * @return
     */
    public void removeMemcacheKey(String memKey) {
        cacheKit.remove(memKey);
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    public WebsiteMemcache queryWebsiteMemcacheById(Long id) {
        return websiteMemcacheDao.queryWebsiteMemcacheById(id);
    }

    /**
     * 删除memcache
     *
     * @param id
     */
    public void deleteWebsiteMemcacheById(Long id) {
        websiteMemcacheDao.deleteWebsiteMemcacheById(id);
    }
}
