package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.WebsiteLoginDao;
import io.wangxiao.edu.app.entity.website.WebsiteLogin;
import io.wangxiao.edu.app.service.website.WebsiteLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 登陆统计实现
 */
@Service("websiteLoginService")
public class WebsiteLoginServiceImpl implements WebsiteLoginService {
    @Autowired
    private WebsiteLoginDao websiteLoginDao;

    /**
     * 添加登陆统计
     */
    public void insertWebsiteLogin(WebsiteLogin websiteLogin) {
        websiteLogin.setCreateTime(new Date());
        websiteLoginDao.insertWebsiteLogin(websiteLogin);
    }

    /**
     * 登陆统计分页列表
     */
    public List<WebsiteLogin> getWebsiteLoginPageList(WebsiteLogin websiteLogin, PageEntity page) {
        return websiteLoginDao.getWebsiteLoginPageList(websiteLogin, page);
    }

}
