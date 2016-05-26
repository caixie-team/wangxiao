package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.website.WebsiteClassifyDao;
import io.wangxiao.edu.home.entity.website.WebsiteClassify;
import io.wangxiao.edu.home.service.website.WebsiteClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("websiteClassifyService")
public class WebsiteClassifyServiceImpl implements WebsiteClassifyService {

    @Autowired
    private WebsiteClassifyDao websiteClassifyDao;

    /**
     * 添加分类
     *
     * @param websiteClassify
     * @return
     */
    public Long addWebsiteClassify(WebsiteClassify websiteClassify) {
        return websiteClassifyDao.addWebsiteClassify(websiteClassify);
    }

    /**
     * 获取分类列表
     *
     * @param websiteClassify
     * @param page
     * @return
     */
    public List<WebsiteClassify> getWebsiteClassifyList(
            WebsiteClassify websiteClassify, PageEntity page) {
        return websiteClassifyDao.getWebsiteClassifyList(websiteClassify, page);
    }

    /**
     * 查询WebsiteClassify
     *
     * @param List<WebsiteClassify>
     * @return
     */
    public List<WebsiteClassify> getWebsiteClassifyByCondition(
            WebsiteClassify websiteClassify) {
        return websiteClassifyDao.getWebsiteClassifyByCondition(websiteClassify);
    }

    /**
     * 删除memcache
     *
     * @param id
     */
    public void deleteWebsiteMemcacheById(Long id) {
        websiteClassifyDao.delWebsiteClassifyById(id);
    }

    /**
     * 根据id获取WebsiteClassify
     *
     * @param id
     * @return
     */
    public WebsiteClassify getWebsiteClassifyById(Long id) {
        return websiteClassifyDao.getWebsiteClassifyById(id);
    }

    /**
     * 更新WebsiteClassify
     *
     * @param websiteClassify
     */
    public void updateWebsiteClassify(WebsiteClassify websiteClassify) {
        websiteClassifyDao.updateWebsiteClassify(websiteClassify);
    }
}
