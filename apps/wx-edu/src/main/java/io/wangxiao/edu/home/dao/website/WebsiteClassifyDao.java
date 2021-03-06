package io.wangxiao.edu.home.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.website.WebsiteClassify;

import java.util.List;

/**
 * @Description: 分类表
 */
public interface WebsiteClassifyDao {
    /**
     * 添加分类
     *
     * @param websiteClassify
     * @return
     */
    Long addWebsiteClassify(WebsiteClassify websiteClassify);

    /**
     * 获取分类列表
     *
     * @param websiteClassify
     * @param page
     * @return
     */
    List<WebsiteClassify> getWebsiteClassifyList(WebsiteClassify websiteClassify, PageEntity page);

    /**
     * 查询WebsiteClassify
     *
     * @param List<WebsiteClassify>
     * @return
     */
    List<WebsiteClassify> getWebsiteClassifyByCondition(WebsiteClassify websiteClassify);

    /**
     * 删除
     *
     * @param id
     */
    void delWebsiteClassifyById(Long id);

    /**
     * 根据id获取WebsiteClassify
     *
     * @param id
     * @return
     */
    WebsiteClassify getWebsiteClassifyById(Long id);

    /**
     * 更新WebsiteClassify
     *
     * @param websiteClassify
     */
    void updateWebsiteClassify(WebsiteClassify websiteClassify);
}
