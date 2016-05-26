package io.wangxiao.edu.home.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.website.WebsiteImagesDao;
import io.wangxiao.edu.home.entity.website.WebsiteImages;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("websiteImagesDao")
public class WebsiteImagesDaoImpl extends GenericDaoImpl implements WebsiteImagesDao {

    /**
     * 查询首页banner图
     *
     * @param websiteImages
     * @return List<WebsiteImages>
     */
    public List<WebsiteImages> getIndexPageBanner(WebsiteImages websiteImages) {
        return this.selectList("WebsiteImagesMapper.getIndexPageBanner", websiteImages);
    }

    /**
     * 添加广告图
     *
     * @param WebsiteImages
     */
    public void insertWebsiteImages(WebsiteImages websiteImages) {
        this.insert("WebsiteImagesMapper.createImages", websiteImages);
    }

    /**
     * 广告图分页列表
     *
     * @param websiteImages
     * @param page
     * @return
     */
    public List<WebsiteImages> getImgPageList(WebsiteImages websiteImages, PageEntity page) {
        return this.queryForListPage("WebsiteImagesMapper.getImgPageList", websiteImages, page);
    }

    /**
     * 删除广告图
     *
     * @param ids
     */
    public void deleteImgByIds(String ids) {
        this.delete("WebsiteImagesMapper.deleteImg", ids);
    }

    /**
     * 查询广告图
     *
     * @param id
     * @return
     */
    public WebsiteImages getWebsiteImagesById(Long id) {
        return this.selectOne("WebsiteImagesMapper.getImageseById", id);
    }

    /**
     * 更新广告图
     *
     * @param WebsiteImages
     */
    public void updateWebsiteImages(WebsiteImages websiteImages) {
        this.selectOne("WebsiteImagesMapper.updateImages", websiteImages);
    }
}
