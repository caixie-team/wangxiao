package io.wangxiao.edu.home.service.website;

import java.util.List;
import java.util.Map;

import io.wangxiao.edu.home.entity.website.WebsiteNavigate;

public interface WebsiteNavigateService {

    /**
     * 首页导航列表
     *
     * @return
     */
    List<WebsiteNavigate> getWebsiteNavigate(WebsiteNavigate websiteNavigate);

    /**
     * 添加导航
     *
     * @param WebsiteNavigate
     */
    void addWebsiteNavigate(WebsiteNavigate websiteNavigate);

    /**
     * 冻结、解冻导航
     *
     * @param WebsiteNavigate
     */
    void freezeWebsiteNavigate(WebsiteNavigate websiteNavigate);

    /**
     * 删除导航
     *
     * @param id
     */
    void delWebsiteNavigate(Long id);

    /**
     * 更新导航
     *
     * @param WebsiteNavigate
     */
    void updateWebsiteNavigate(WebsiteNavigate websiteNavigate);

    /**
     * id查询导航
     *
     * @param id
     * @return
     */
    WebsiteNavigate getWebsiteNavigateById(Long id);

    /**
     * 导航列表
     *
     * @return
     */
    Map<String, Object> getWebNavigate();

    /**
     * 批量删除导航
     *
     * @param ids
     */
    void delNavigatebatch(String ids);

}