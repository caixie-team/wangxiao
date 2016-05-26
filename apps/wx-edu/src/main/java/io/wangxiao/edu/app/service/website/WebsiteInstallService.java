package io.wangxiao.edu.app.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.WebsiteInstall;

import java.util.List;

/**
 * 登陆统计
 */
public interface WebsiteInstallService {
    /**
     * 添加安装记录
     */
    void insertWebsiteInstall(WebsiteInstall websiteInstall);

    /**
     * 安装统计分页列表
     */
    List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page);

}
