package com.atdld.os.app.dao.impl.website;

import com.atdld.os.app.dao.website.WebsiteInstallDao;
import com.atdld.os.app.entity.website.WebsiteInstall;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 安装统计
 */
@Repository("websiteInstallDao")
public class WebsiteInstallDaoImpl extends GenericDaoImpl implements WebsiteInstallDao {
	/**
	 * 添加安装记录
	 */
	public void insertWebsiteInstall(WebsiteInstall websiteInstall){
        this.insert("WebsiteInstallMapper.insertWebsiteInstall", websiteInstall);
    }

	/**
	 * 安装统计分页列表
	 */
	public List<WebsiteInstall> getWebsiteInstallPage(WebsiteInstall websiteInstall, PageEntity page){
        return this.queryForListPage("WebsiteInstallMapper.getWebsiteInstallPage",websiteInstall, page);
    }

}
