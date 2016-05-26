package io.wangxiao.edu.app.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.WebsiteUse;

import java.util.List;


/**
 * 使用统计
 */
public interface WebsiteUseDao {

    /**
     * 添加使用统计
     */
    void insertWebsiteUse(WebsiteUse websiteUse);

    /**
     * 更新使用统计的使用结束时间
     */
    void updateWebsiteUseForEndtime(WebsiteUse websiteUse);

    /**
     * 使用统计分页列表
     */
    List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse, PageEntity page);


}
