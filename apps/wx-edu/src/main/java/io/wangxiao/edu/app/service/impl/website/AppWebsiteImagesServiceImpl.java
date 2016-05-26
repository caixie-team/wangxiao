package io.wangxiao.edu.app.service.impl.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.app.common.AppMemConstans;
import io.wangxiao.edu.app.dao.website.AppWebsiteImagesDao;
import io.wangxiao.edu.app.entity.website.AppWebsiteImages;
import io.wangxiao.edu.app.service.website.AppWebsiteImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("appWebsiteImagesService")
public class AppWebsiteImagesServiceImpl implements AppWebsiteImagesService {
    CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private AppWebsiteImagesDao appWebsiteImagesDao;

    /**
     * 查询首页banner图 ,结果封装为Map<keyword,list<AppWebsiteImages>>
     *
     * @param paramMap
     * @return Map<String,Object>
     * @throws Exception
     */
    public Map<String, List<AppWebsiteImages>> getIndexPageBanner(Map<String, Object> paramMap) throws Exception {
        Map<String, List<AppWebsiteImages>> map = new HashMap<String, List<AppWebsiteImages>>();
        // 查询的结果必须以keyword排序
        List<AppWebsiteImages> websiteImagesList = appWebsiteImagesDao.getIndexPageBanner(paramMap);

        if (ObjectUtils.isNotNull(websiteImagesList)) {
            for (AppWebsiteImages images : websiteImagesList) {

                if (ObjectUtils.isNotNull(map.get(images.getKeyWord()))) {
                    map.get(images.getKeyWord()).add(images);
                } else {
                    List<AppWebsiteImages> appWebsiteImagesList = new ArrayList<AppWebsiteImages>();
                    appWebsiteImagesList.add(images);
                    map.put(images.getKeyWord(), appWebsiteImagesList);
                }
            }
        }
        // app图片管理排序
        map = appWebsiteImagesSort(map);
        if (ObjectUtils.isNotNull(map)) {
            cacheKit.set(AppMemConstans.APP_BANNER_IMAGES, map, AppMemConstans.APP_BANNER_IMAGES_TIME);
        }
        return map;
    }

    /**
     * app图片管理排序
     */
    public Map<String, List<AppWebsiteImages>> appWebsiteImagesSort(Map<String, List<AppWebsiteImages>> map) throws Exception {
        for (String key : map.keySet()) {
            List<AppWebsiteImages> list = map.get(key);
            Collections.sort(list, (arg0, arg1) -> {
                if (arg0.getSeriesNumber() > arg1.getSeriesNumber()) {
                    return -1;
                } else if (arg0.getSeriesNumber() < arg1.getSeriesNumber()) {
                    return 1;
                } else {
                    return 0;
                }
            });
            map.put(key, list);
        }
        return map;
    }


    /**
     * 添加广告图
     *
     * @param
     */
    public void insertWebsiteImages(AppWebsiteImages appWebsiteImages) {
        appWebsiteImagesDao.insertWebsiteImages(appWebsiteImages);
        cacheKit.remove(AppMemConstans.APP_BANNER_IMAGES);
    }

    /**
     * 广告图分页列表
     *
     * @param appWebsiteImages
     * @param page
     * @return
     */
    public List<AppWebsiteImages> getImgPageList(AppWebsiteImages appWebsiteImages, PageEntity page) {
        return appWebsiteImagesDao.getImgPageList(appWebsiteImages, page);
    }

    /**
     * 删除广告图
     *
     * @param ids
     */
    public void deleteImgByIds(String ids) {
        appWebsiteImagesDao.deleteImgByIds(ids);
        cacheKit.remove(AppMemConstans.APP_BANNER_IMAGES);
    }

    /**
     * 查询广告图
     *
     * @param id
     * @return
     */
    public AppWebsiteImages getWebsiteImagesById(Long id) {
        return appWebsiteImagesDao.getWebsiteImagesById(id);
    }

    /**
     * 更新广告图
     *
     * @param
     */
    public void updateWebsiteImages(AppWebsiteImages appWebsiteImages) {
        appWebsiteImagesDao.updateWebsiteImages(appWebsiteImages);
        cacheKit.remove(AppMemConstans.APP_BANNER_IMAGES);
    }
}
