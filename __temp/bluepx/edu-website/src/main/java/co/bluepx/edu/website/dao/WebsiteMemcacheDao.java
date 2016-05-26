package co.bluepx.edu.website.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.entity.PageEntity;
import co.bluepx.edu.website.entity.WebsiteMemcache;

import java.util.List;

/**
 * @author :
 * @description
 */
public interface WebsiteMemcacheDao extends BaseDao<WebsiteMemcache> {
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
