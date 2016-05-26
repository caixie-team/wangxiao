package com.atdld.os.app.service.impl.website;

import com.atdld.os.app.dao.website.WebsiteUseDao;
import com.atdld.os.app.entity.website.WebsiteUse;
import com.atdld.os.app.service.website.WebsiteUseService;
import com.atdld.os.core.entity.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 使用统计实现
 */
@Service("websiteUseService")
public class WebsiteUseServiceImpl implements WebsiteUseService {
	@Autowired
	private WebsiteUseDao websiteUseDao;
	
    /**
	 * 添加使用统计
	 */
	public void insertWebsiteUse(WebsiteUse websiteUse) {
        if(websiteUse.getBeginFalg()==1){
            websiteUse.setCreateTime(new Date());
            websiteUse.setBeginTime(new Date());
            websiteUseDao.insertWebsiteUse(websiteUse);
        }else if(websiteUse.getBeginFalg()==2){
            websiteUse.setEndTime(new Date());
            websiteUseDao.updateWebsiteUseForEndtime(websiteUse);
        }

	}

    /**
	 * 使用统计分页列表
	 */
	public List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse, PageEntity page) {
		return websiteUseDao.getWebsiteUsePage(websiteUse, page);
	}

}
