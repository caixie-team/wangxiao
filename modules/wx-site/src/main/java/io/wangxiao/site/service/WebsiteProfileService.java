package io.wangxiao.site.service;


import io.wangxiao.site.model.WebsiteProfile;

import java.util.List;
import java.util.Map;

public interface WebsiteProfileService {

    /**
     * 根据网站配置类型查找配置信息
     *
     * @param type
     * @return
     */
    WebsiteProfile findByType(String type);

    /**
     * 查找所有配置文件
     *
     * @return
     */
    List<WebsiteProfile> getAllWebsiteProfile();
    /**
     * 修改WebsiteProfile
     *
     * @param websiteProfile
     * @throws Exception
     */
    void updateWebsiteProfile(WebsiteProfile websiteProfile) throws Exception;

    /**
     * 查询所有网站配置
     *
     * @return 无参
     * @throws Exception
     */
    Map<String, Object> getWebsiteProfileList() throws Exception;

    /**
     * 根据type查询网站配置
     *
     * @param type
     * @return
     */
    Map<String, Object> findWebsiteProfileByType(String type);


}