package com.atdld.os.app.service.impl.website;

import com.atdld.os.app.common.AppMemConstans;
import com.atdld.os.app.dao.website.AppWebsiteCourseDetailDao;
import com.atdld.os.app.entity.website.AppWebsiteCourseDetail;
import com.atdld.os.app.entity.website.AppWebsiteCourseDetailDTO;
import com.atdld.os.app.service.website.AppWebsiteCourseDetailService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * WebsiteCourseDetail管理接口
 * User:
 * Date: 2014-05-27
 */
@Service("appWebsiteCourseDetailService")
public class AppWebsiteCourseDetailServiceImpl implements AppWebsiteCourseDetailService {
	MemCache memCache = MemCache.getInstance();
 	@Autowired
    private AppWebsiteCourseDetailDao appWebsiteCourseDetailDao;
    
 	/**
	 * 添加推荐课程
	 */
    public void addWebsiteCourseDetail(List<AppWebsiteCourseDetail> AppwebsiteCourseDetails){
    	appWebsiteCourseDetailDao.addWebsiteCourseDetail(AppwebsiteCourseDetails);
        
    	memCache.remove(AppMemConstans.APP_RECOMMEND_COURSE);
    }

    /**
     * 查询推荐课程列表
     */
    public List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetailDTO websiteCourseDetailDTO,PageEntity page){
    	return appWebsiteCourseDetailDao.queryWebsiteCourseDetailList(websiteCourseDetailDTO, page);
    }
    /**
     * 查询推荐课程列表
     * @return
     */
    public List<AppWebsiteCourseDetailDTO> queryWebsiteCourseDetailList(AppWebsiteCourseDetail appWebsiteCourseDetail){
        return appWebsiteCourseDetailDao.queryWebsiteCourseDetailList(appWebsiteCourseDetail);
    }
    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id){
    	appWebsiteCourseDetailDao.deleteWebsiteCourseDetail(id);
    	memCache.remove(AppMemConstans.APP_RECOMMEND_COURSE);
    }

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public AppWebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id){
    	return appWebsiteCourseDetailDao.queryWebsiteCourseDetailById(id);
    }

    /**
     * 更新推荐课程
     * @return Long
     */
    public void updateWebsiteCourseDetail(AppWebsiteCourseDetail appWebsiteCourseDetail){
    	appWebsiteCourseDetailDao.updateWebsiteCourseDetail(appWebsiteCourseDetail);
    	memCache.remove(AppMemConstans.APP_RECOMMEND_COURSE);
    }
    
    /**
     * 分类查找推荐课程集合
     * @param id
     */
    public List<AppWebsiteCourseDetail> getWebsiteCourseDetails(Long id){
    	return appWebsiteCourseDetailDao.getWebsiteCourseDetails(id);
    }
    
    /**
     * web推荐课程集合
     */
    public List<AppWebsiteCourseDetailDTO> showWebsiteCourseDetails(int recommendId,int count){
    	Map<String,Object> map =new HashMap<String,Object>();
    	map.put("recommendId", recommendId);
    	map.put("count", count);
        List<AppWebsiteCourseDetailDTO> AppwebsiteCourseDetails = appWebsiteCourseDetailDao.getWebWebsiteCourseDetails(map);
        return AppwebsiteCourseDetails;
    }

    /**
     *  app图片管理排序
     * */
    public Map<Long, List<AppWebsiteCourseDetailDTO>> appWebsiteCourseDetailDTOSort(Map<Long, List<AppWebsiteCourseDetailDTO>> map) {
        for (Long key : map.keySet()) {
            List<AppWebsiteCourseDetailDTO> list = map.get(key);
            Collections.sort(list, new Comparator<AppWebsiteCourseDetailDTO>() {
                public int compare(AppWebsiteCourseDetailDTO arg0, AppWebsiteCourseDetailDTO arg1) {
                    if (arg0.getOrderNum() > arg1.getOrderNum()) {
                        return -1;
                    } else if (arg0.getOrderNum() < arg1.getOrderNum()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            map.put(key,list);
        }
        return map;
    }
}