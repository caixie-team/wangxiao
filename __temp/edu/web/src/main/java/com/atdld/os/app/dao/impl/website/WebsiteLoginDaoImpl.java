package com.atdld.os.app.dao.impl.website;

import com.atdld.os.app.dao.website.WebsiteLoginDao;
import com.atdld.os.app.entity.website.WebsiteLogin;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 登陆统计
 *
 */
@Repository("websiteLoginDao")
public class WebsiteLoginDaoImpl extends GenericDaoImpl implements WebsiteLoginDao {

	/**
	 * 添加登陆统计
	 * @param
	 */
	public void insertWebsiteLogin(WebsiteLogin websiteLogin){
		this.insert("WebsiteLoginMapper.insertWebsiteLogin", websiteLogin);
	}

	/**
	 * 登陆统计分页列表
	 */
	public List<WebsiteLogin> getWebsiteLoginPageList(WebsiteLogin websiteLogin,PageEntity page){
        return this.queryForListPage("WebsiteLoginMapper.getWebsiteLoginPageList",websiteLogin, page);
	}


}
