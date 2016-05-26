package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.WebsiteInstallDao;
import io.wangxiao.edu.app.entity.website.WebsiteInstall;
import io.wangxiao.edu.app.service.website.WebsiteInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 登陆统计
 */
@Service("websiteInstallService")
public class WebsiteInstallServiceImpl implements WebsiteInstallService {
    @Autowired
    private WebsiteInstallDao websiteInstallDao;

    /**
     * 添加安装记录
     */
    public void insertWebsiteInstall(WebsiteInstall websiteInstall) {
        websiteInstall.setCreateTime(new Date());
        websiteInstallDao.insertWebsiteInstall(websiteInstall);
    }

    /**
     * 安装统计分页列表
     */
    public List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page) {
        return websiteInstallDao.getWebsiteInstallPage(websiteInstall, page);
    }

}
