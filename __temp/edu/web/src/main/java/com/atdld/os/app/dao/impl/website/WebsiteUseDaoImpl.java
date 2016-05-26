package com.atdld.os.app.dao.impl.website;

import com.atdld.os.app.dao.website.WebsiteUseDao;
import com.atdld.os.app.entity.website.WebsiteUse;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用统计
 *
 */
@Repository("websiteUseDao")
public class WebsiteUseDaoImpl extends GenericDaoImpl implements WebsiteUseDao {

	/**
	 * 添加使用统计
	 * @param
	 */
	public void insertWebsiteUse(WebsiteUse websiteUse){
		this.insert("WebsiteUseMapper.insertWebsiteUse", websiteUse);
	}
    /**
     * 更新使用统计的使用结束时间
     */
    public void updateWebsiteUseForEndtime(WebsiteUse websiteUse){
        this.update("WebsiteUseMapper.updateWebsiteUseForEndtime",websiteUse);
    }
	/**
	 * 使用统计分页列表
	 */
	public List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse,PageEntity page){
        return this.queryForListPage("WebsiteUseMapper.getWebsiteUsePage",websiteUse, page);
	}


}
