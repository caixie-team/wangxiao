package io.wangxiao.edu.home.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.website.WebsiteImages;

import java.util.List;


public interface WebsiteImagesDao {
    /**
     * 查询首页banner图
     *
     * @param websiteWebsiteImages
     * @return List<WebsiteWebsiteImages>
     */
    List<WebsiteImages> getIndexPageBanner(WebsiteImages websiteWebsiteImages);

    /**
     * 添加广告图
     *
     * @param WebsiteImages
     */
    void insertWebsiteImages(WebsiteImages websiteImages);

    /**
     * 广告图分页列表
     *
     * @param websiteImages
     * @param page
     * @return
     */
    List<WebsiteImages> getImgPageList(WebsiteImages websiteImages, PageEntity page);

    /**
     * 删除广告图
     *
     * @param ids
     */
    void deleteImgByIds(String ids);

    /**
     * 查询广告图
     *
     * @param id
     * @return
     */
    WebsiteImages getWebsiteImagesById(Long id);

    /**
     * 更新广告图
     *
     * @param WebsiteImages
     */
    void updateWebsiteImages(WebsiteImages websiteImages);
}
