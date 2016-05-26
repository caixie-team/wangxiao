package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.WebsiteUseDao;
import io.wangxiao.edu.app.entity.website.WebsiteUse;
import io.wangxiao.edu.app.service.website.WebsiteUseService;
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
        if (websiteUse.getBeginFalg() == 1) {
            websiteUse.setCreateTime(new Date());
            websiteUse.setBeginTime(new Date());
            websiteUseDao.insertWebsiteUse(websiteUse);
        } else if (websiteUse.getBeginFalg() == 2) {
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
