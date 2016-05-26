package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.WebsiteUseDao;
import io.wangxiao.edu.app.entity.website.WebsiteUse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 使用统计
 */
@Repository("websiteUseDao")
public class WebsiteUseDaoImpl extends GenericDaoImpl implements WebsiteUseDao {

    /**
     * 添加使用统计
     *
     * @param
     */
    public void insertWebsiteUse(WebsiteUse websiteUse) {
        this.insert("WebsiteUseMapper.insertWebsiteUse", websiteUse);
    }

    /**
     * 更新使用统计的使用结束时间
     */
    public void updateWebsiteUseForEndtime(WebsiteUse websiteUse) {
        this.update("WebsiteUseMapper.updateWebsiteUseForEndtime", websiteUse);
    }

    /**
     * 使用统计分页列表
     */
    public List<WebsiteUse> getWebsiteUsePage(WebsiteUse websiteUse, PageEntity page) {
        return this.queryForListPage("WebsiteUseMapper.getWebsiteUsePage", websiteUse, page);
    }


}
