package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.AppWebsiteImagesDao;
import io.wangxiao.edu.app.entity.website.AppWebsiteImages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("appWebsiteImagesDao")
public class AppWebsiteImagesDaoImpl extends GenericDaoImpl implements AppWebsiteImagesDao {

    /**
     * 查询首页banner图
     *
     * @param appWebsiteImages
     * @return List<WebsiteImages>
     */
    public List<AppWebsiteImages> getIndexPageBanner(Map<String, Object> map) {
        return this.selectList("AppWebsiteImagesMapper.getIndexPageBanner", map);
    }

    /**
     * 添加广告图
     *
     * @param
     */
    public void insertWebsiteImages(AppWebsiteImages appWebsiteImages) {
        this.insert("AppWebsiteImagesMapper.createImages", appWebsiteImages);
    }

    /**
     * 广告图分页列表
     *
     * @param appWebsiteImages
     * @param page
     * @return
     */
    public List<AppWebsiteImages> getImgPageList(AppWebsiteImages appWebsiteImages, PageEntity page) {
        return this.queryForListPage("AppWebsiteImagesMapper.getImgPageList", appWebsiteImages, page);
    }

    /**
     * 删除广告图
     *
     * @param ids
     */
    public void deleteImgByIds(String ids) {
        this.delete("AppWebsiteImagesMapper.deleteImg", ids);
    }

    /**
     * 查询广告图
     *
     * @param id
     * @return
     */
    public AppWebsiteImages getWebsiteImagesById(Long id) {
        return this.selectOne("AppWebsiteImagesMapper.getImageseById", id);
    }

    /**
     * 更新广告图
     *
     * @param
     */
    public void updateWebsiteImages(AppWebsiteImages appWebsiteImages) {
        this.selectOne("AppWebsiteImagesMapper.updateImages", appWebsiteImages);
    }
}
