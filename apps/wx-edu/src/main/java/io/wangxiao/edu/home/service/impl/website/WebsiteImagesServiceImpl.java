package io.wangxiao.edu.home.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.website.WebsiteImagesDao;
import io.wangxiao.edu.home.entity.website.WebsiteImages;
import io.wangxiao.edu.home.service.website.WebsiteImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("websiteImagesService")
public class WebsiteImagesServiceImpl implements WebsiteImagesService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private WebsiteImagesDao websiteImagesDao;

    /**
     * 查询首页banner图 ,结果封装为Map<keyword,list<WebsiteImages>>
     *
     * @param websiteImages
     * @return Map<String,Object>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getIndexPageBanner(WebsiteImages websiteImages) throws Exception {
        Map<String, Object> map = (Map<String, Object>) cacheKit.get(MemConstans.BANNER_IMAGES);
        if (ObjectUtils.isNotNull(map)) {
            return map;
        }
        map = new HashMap<String, Object>();
        // 查询的结果必须以keyword排序
        List<WebsiteImages> websiteImagesList = websiteImagesDao.getIndexPageBanner(websiteImages);

        if (ObjectUtils.isNotNull(websiteImagesList)) {
            String keyword = websiteImagesList.get(0).getKeyWord();
            List<WebsiteImages> list = new ArrayList<WebsiteImages>();
            for (WebsiteImages images : websiteImagesList) {
                if (!keyword.equalsIgnoreCase(images.getKeyWord())) {
                    map.put(keyword, list);
                    keyword = images.getKeyWord();
                    list = new ArrayList<WebsiteImages>();
                    list.add(images);
                } else {
                    list.add(images);
                }
            }
            if (ObjectUtils.isNotNull(list)) {
                map.put(keyword, list);
            }
        }
        if (ObjectUtils.isNotNull(map)) {
            cacheKit.set(MemConstans.BANNER_IMAGES, map, MemConstans.BANNER_IMAGES_TIME);
        }
        return map;
    }

    /**
     * 添加广告图
     *
     * @param WebsiteImages
     */
    public void insertWebsiteImages(WebsiteImages websiteImages) {
        websiteImagesDao.insertWebsiteImages(websiteImages);
        cacheKit.remove(MemConstans.BANNER_IMAGES);
    }

    /**
     * 广告图分页列表
     *
     * @param websiteImages
     * @param page
     * @return
     */
    public List<WebsiteImages> getImgPageList(WebsiteImages websiteImages, PageEntity page) {
        return websiteImagesDao.getImgPageList(websiteImages, page);
    }

    /**
     * 删除广告图
     *
     * @param ids
     */
    public void deleteImgByIds(String ids) {
        websiteImagesDao.deleteImgByIds(ids);
        cacheKit.remove(MemConstans.BANNER_IMAGES);
    }

    /**
     * 查询广告图
     *
     * @param id
     * @return
     */
    public WebsiteImages getWebsiteImagesById(Long id) {
        return websiteImagesDao.getWebsiteImagesById(id);
    }

    /**
     * 更新广告图
     *
     * @param WebsiteImages
     */
    public void updateWebsiteImages(WebsiteImages websiteImages) {
        websiteImagesDao.updateWebsiteImages(websiteImages);
        cacheKit.remove(MemConstans.BANNER_IMAGES);
    }
}
