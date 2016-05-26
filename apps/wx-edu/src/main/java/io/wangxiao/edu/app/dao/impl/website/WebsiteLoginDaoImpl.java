package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.WebsiteLoginDao;
import io.wangxiao.edu.app.entity.website.WebsiteLogin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登陆统计
 */
@Repository("websiteLoginDao")
public class WebsiteLoginDaoImpl extends GenericDaoImpl implements WebsiteLoginDao {

    /**
     * 添加登陆统计
     *
     * @param
     */
    public void insertWebsiteLogin(WebsiteLogin websiteLogin) {
        this.insert("WebsiteLoginMapper.insertWebsiteLogin", websiteLogin);
    }

    /**
     * 登陆统计分页列表
     */
    public List<WebsiteLogin> getWebsiteLoginPageList(WebsiteLogin websiteLogin, PageEntity page) {
        return this.queryForListPage("WebsiteLoginMapper.getWebsiteLoginPageList", websiteLogin, page);
    }


}
