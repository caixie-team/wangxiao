package com.atdld.os.app.service.impl.website;

import com.atdld.os.app.common.AppMemConstans;
import com.atdld.os.app.dao.website.AppWebsiteImagesDao;
import com.atdld.os.app.entity.website.AppWebsiteImages;
import com.atdld.os.app.service.website.AppWebsiteImagesService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 
 * @author jjl
 * 
 */
@Service("appWebsiteImagesService")
public class AppWebsiteImagesServiceImpl implements AppWebsiteImagesService {
	MemCache memCache = MemCache.getInstance();
	@Autowired
	private AppWebsiteImagesDao appWebsiteImagesDao;

	/**
	 * 查询首页banner图 ,结果封装为Map<keyword,list<AppWebsiteImages>>
	 * 
	 * @param appWebsiteImages
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String, List<AppWebsiteImages>> getIndexPageBanner(Map<String,Object> paramMap) throws Exception {
		Map<String, List<AppWebsiteImages>>  map = new HashMap<String, List<AppWebsiteImages>>();
		// 查询的结果必须以keyword排序
		List<AppWebsiteImages> websiteImagesList = appWebsiteImagesDao.getIndexPageBanner(paramMap);

		if (ObjectUtils.isNotNull(websiteImagesList)) {
			for (AppWebsiteImages images : websiteImagesList) {

				if(ObjectUtils.isNotNull(map.get(images.getKeyWord()))){
                    map.get(images.getKeyWord()).add(images);
                }else{
                    List<AppWebsiteImages> appWebsiteImagesList = new ArrayList<AppWebsiteImages>();
                    appWebsiteImagesList.add(images);
                    map.put(images.getKeyWord(),appWebsiteImagesList);
                }
			}
		}
        // app图片管理排序
        map = appWebsiteImagesSort(map);
		if (ObjectUtils.isNotNull(map)) {
			memCache.set(AppMemConstans.APP_BANNER_IMAGES, map,AppMemConstans.APP_BANNER_IMAGES_TIME);
		}
		return map;
	}
    /**
     *  app图片管理排序
     * */
    public Map<String, List<AppWebsiteImages>> appWebsiteImagesSort(Map<String, List<AppWebsiteImages>> map) throws Exception {
        for (String key : map.keySet()) {
            List<AppWebsiteImages> list = map.get(key);
            Collections.sort(list, new Comparator<AppWebsiteImages>() {
                public int compare(AppWebsiteImages arg0, AppWebsiteImages arg1) {
                    if(arg0.getSeriesNumber()>arg1.getSeriesNumber()){
                        return -1;
                    }else if(arg0.getSeriesNumber()<arg1.getSeriesNumber()){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            });
            map.put(key,list);
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
		memCache.remove(AppMemConstans.APP_BANNER_IMAGES);
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
		memCache.remove(AppMemConstans.APP_BANNER_IMAGES);
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
		memCache.remove(AppMemConstans.APP_BANNER_IMAGES);
	}
}
