package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.WebsiteInstallDao;
import io.wangxiao.edu.app.entity.website.WebsiteInstall;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 安装统计
 */
@Repository("websiteInstallDao")
public class WebsiteInstallDaoImpl extends GenericDaoImpl implements WebsiteInstallDao {
    /**
     * 添加安装记录
     */
    public void insertWebsiteInstall(WebsiteInstall websiteInstall) {
        this.insert("WebsiteInstallMapper.insertWebsiteInstall", websiteInstall);
    }

    /**
     * 安装统计分页列表
     */
    public List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page) {
        return this.queryForListPage("WebsiteInstallMapper.getWebsiteInstallPage", websiteInstall, page);
    }

}
