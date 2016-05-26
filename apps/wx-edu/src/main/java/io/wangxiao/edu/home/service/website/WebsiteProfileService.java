package io.wangxiao.edu.home.service.website;

import io.wangxiao.edu.home.entity.website.WebsiteProfile;

import java.util.Map;

public interface WebsiteProfileService {
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
    Map<String, Object> getWebsiteProfileByType(String type);


}