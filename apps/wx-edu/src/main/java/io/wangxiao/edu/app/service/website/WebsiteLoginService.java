package io.wangxiao.edu.app.service.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.WebsiteLogin;

import java.util.List;

/**
 * 登陆统计
 */
public interface WebsiteLoginService {
    /**
     * 添加登陆记录
     */
    void insertWebsiteLogin(WebsiteLogin websiteLogin);

    /**
     * 登陆统计分页列表
     */
    List<WebsiteLogin> getWebsiteLoginPageList(WebsiteLogin websiteLogin, PageEntity page);

}
