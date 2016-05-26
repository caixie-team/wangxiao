package io.wangxiao.website.dao;

import io.wangxiao.core.BaseDao;
import io.wangxiao.website.entity.WebsiteProfile;

import java.util.List;

/**
 * 网站配置
 */
public interface WebsiteProfileDao extends BaseDao<WebsiteProfile> {
    /**
     * 根据type查询网站配置
     *
     * @param type
     * @return
     */
    WebsiteProfile getWebsiteProfileByType(String type);

    /**
     * 更新网站配置管理
     *
     * @param websiteProfile
     */
    void updateWebsiteProfile(WebsiteProfile websiteProfile);

    /**
     * 查询网站配置
     *
     * @return List<WebsiteProfile>
     */
    List<WebsiteProfile> getWebsiteProfileList();
}