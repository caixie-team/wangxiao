package com.atdld.os.edu.service.impl.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.entity.website.WebsiteCourseDetail;
import com.atdld.os.edu.entity.website.WebsiteCourseDetailDTO;
import com.atdld.os.edu.dao.website.WebsiteCourseDetailDao;
import com.atdld.os.edu.service.website.WebsiteCourseDetailService;
/**
 * WebsiteCourseDetail管理接口
 * User:
 * Date: 2014-05-27
 */
@Service("websiteCourseDetailService")
public class WebsiteCourseDetailServiceImpl implements WebsiteCourseDetailService{
	MemCache memCache = MemCache.getInstance();
 	@Autowired
    private WebsiteCourseDetailDao websiteCourseDetailDao;
    
 	/**
	 * 添加推荐课程
	 * @param websiteCourseDetail
	 */
    public void addWebsiteCourseDetail(List<WebsiteCourseDetail> websiteCourseDetails){
    	websiteCourseDetailDao.addWebsiteCourseDetail(websiteCourseDetails);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 查询推荐课程列表
     * @param websiteCourseDetail
     * @param page
     * @return
     */
    public List<WebsiteCourseDetailDTO> queryWebsiteCourseDetailList(WebsiteCourseDetailDTO websiteCourseDetailDTO,PageEntity page){
    	return websiteCourseDetailDao.queryWebsiteCourseDetailList(websiteCourseDetailDTO, page);
    }

    /**
     * 根据id删除推荐课程
     * 
     * @param id
     * @return Long
     * @throws Exception
     */
    public void deleteWebsiteCourseDetail(Long id){
    	websiteCourseDetailDao.deleteWebsiteCourseDetail(id);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }

    /**
     * 查询单个推荐课程分类
     * @param id
     * @return
     */
    public WebsiteCourseDetailDTO queryWebsiteCourseDetailById(Long id){
    	return websiteCourseDetailDao.queryWebsiteCourseDetailById(id);
    }

    /**
     * 更新推荐课程
     * 
     * @param reSortId
     * @return Long
     */
    public void updateWebsiteCourseDetail(WebsiteCourseDetail websiteCourseDetail){
    	websiteCourseDetailDao.updateWebsiteCourseDetail(websiteCourseDetail);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }
    
    /**
     * 分类查找推荐课程集合
     * @param id
     */
    public List<WebsiteCourseDetail> getWebsiteCourseDetails(Long id){
    	return websiteCourseDetailDao.getWebsiteCourseDetails(id);
    }
    
    /**
     * web推荐课程集合
     * @param id
     */
    public Map<String,Object> showWebsiteCourseDetails(){
    	
		@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>)memCache.get(MemConstans.RECOMMEND_COURSE);
    	if (ObjectUtils.isNotNull(map)) {
            return map;
        } 
        map=new HashMap<String,Object>();
        //查询的结果必须以recommendId排序
        List<WebsiteCourseDetailDTO> websiteCourseDetails = websiteCourseDetailDao.getWebWebsiteCourseDetails();

        if (ObjectUtils.isNotNull(websiteCourseDetails)) {
            Long recommendId = websiteCourseDetails.get(0).getRecommendId();
            List<WebsiteCourseDetailDTO> list = new ArrayList<WebsiteCourseDetailDTO>();
            for (WebsiteCourseDetailDTO websiteCourseDetailDTO : websiteCourseDetails) {
                if(recommendId!=websiteCourseDetailDTO.getRecommendId()){
                    map.put(recommendId.toString(), list) ;
                    recommendId=websiteCourseDetailDTO.getRecommendId();
                    list=new ArrayList<WebsiteCourseDetailDTO>();
                    list.add(websiteCourseDetailDTO);
                }else{
                    list.add(websiteCourseDetailDTO);
                }
            }
            if(ObjectUtils.isNotNull(list)){
                map.put(recommendId.toString(), list) ;
            }
        }
        if (ObjectUtils.isNotNull(map)) {
            memCache.set(MemConstans.RECOMMEND_COURSE, map,MemConstans.RECOMMEND_COURSE_TIME);
        } 
        return map;
    }
}