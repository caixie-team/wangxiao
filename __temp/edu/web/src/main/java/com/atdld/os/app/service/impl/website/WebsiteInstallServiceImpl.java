package com.atdld.os.app.service.impl.website;

import com.atdld.os.app.dao.website.WebsiteInstallDao;
import com.atdld.os.app.entity.website.WebsiteInstall;
import com.atdld.os.app.service.website.WebsiteInstallService;
import com.atdld.os.core.entity.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * 登陆统计
 */
@Service("websiteInstallService")
public class WebsiteInstallServiceImpl implements WebsiteInstallService {
    @Autowired
    private WebsiteInstallDao websiteInstallDao;
	/**
	 * 添加安装记录
	 */
	public void insertWebsiteInstall(WebsiteInstall websiteInstall){
        websiteInstall.setCreateTime(new Date());
        websiteInstallDao.insertWebsiteInstall(websiteInstall);
    }

	/**
	 * 安装统计分页列表
	 */
	public List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page){
        return websiteInstallDao.getWebsiteInstallPage(websiteInstall, page);
    }

}
