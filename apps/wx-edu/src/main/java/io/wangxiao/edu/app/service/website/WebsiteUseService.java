package io.wangxiao.edu.app.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.WebsiteUse;

import java.util.List;

/**
 * 使用统计
 */
public interface WebsiteUseService {
    /**
     * 添加使用记录
     */
    void insertWebsiteUse(WebsiteUse websiteUse);

    /**
     * 使用统计分页列表
     */
    List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse, PageEntity page);

}
