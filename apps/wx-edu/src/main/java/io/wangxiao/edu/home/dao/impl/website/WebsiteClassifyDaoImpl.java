package io.wangxiao.edu.home.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.website.WebsiteClassifyDao;
import io.wangxiao.edu.home.entity.website.WebsiteClassify;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("websiteClassifyDao")
public class WebsiteClassifyDaoImpl extends GenericDaoImpl implements WebsiteClassifyDao {

    /**
     * 添加分类
     *
     * @param websiteClassify
     * @return
     */
    public Long addWebsiteClassify(WebsiteClassify websiteClassify) {
        return this.insert("WebsiteClassifyMapper.addWebsiteClassify", websiteClassify);
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
        return this.queryForListPage("WebsiteClassifyMapper.getWebsiteClassifyList", websiteClassify, page);
    }

    /**
     * 查询WebsiteClassify
     *
     * @param List<WebsiteClassify>
     * @return
     */
    public List<WebsiteClassify> getWebsiteClassifyByCondition(
            WebsiteClassify websiteClassify) {
        return this.selectList("WebsiteClassifyMapper.getWebsiteClassifyByCondition", websiteClassify);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delWebsiteClassifyById(Long id) {
        this.delete("WebsiteClassifyMapper.delWebsiteClassifyById", id);
    }

    /**
     * 根据id获取WebsiteClassify
     *
     * @param id
     * @return
     */
    public WebsiteClassify getWebsiteClassifyById(Long id) {
        return this.selectOne("WebsiteClassifyMapper.getWebsiteClassifyById", id);
    }

    /**
     * 更新WebsiteClassify
     *
     * @param websiteClassify
     */
    public void updateWebsiteClassify(WebsiteClassify websiteClassify) {
        this.update("WebsiteClassifyMapper.updateWebsiteClassify", websiteClassify);
    }
}
