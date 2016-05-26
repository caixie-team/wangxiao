package com.atdld.os.app.service.impl.website;

import com.atdld.os.app.dao.website.WebsiteLoginDao;
import com.atdld.os.app.entity.website.WebsiteLogin;
import com.atdld.os.app.service.website.WebsiteLoginService;
import com.atdld.os.core.entity.PageEntity;
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
