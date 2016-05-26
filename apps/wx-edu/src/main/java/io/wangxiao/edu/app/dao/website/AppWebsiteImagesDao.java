package io.wangxiao.edu.app.dao.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.entity.website.AppWebsiteImages;

import java.util.List;
import java.util.Map;


public interface AppWebsiteImagesDao {
    /**
     * 查询首页banner图
     *
     * @param appWebsiteImages
     * @return List<WebsiteWebsiteImages>
     */
    List<AppWebsiteImages> getIndexPageBanner(Map<String, Object> map);

    /**
     * 添加广告图
     */
    void insertWebsiteImages(AppWebsiteImages appWebsiteImages);

    /**
     * 广告图分页列表
     *
     * @param appWebsiteImages
     * @param page
     * @return
     */
    List<AppWebsiteImages> getImgPageList(AppWebsiteImages appWebsiteImages, PageEntity page);

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
    AppWebsiteImages getWebsiteImagesById(Long id);

    /**
     * 更新广告图
     */
    void updateWebsiteImages(AppWebsiteImages appWebsiteImages);
}
