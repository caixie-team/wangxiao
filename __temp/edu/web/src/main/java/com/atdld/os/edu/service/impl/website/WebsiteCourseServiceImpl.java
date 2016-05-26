package com.atdld.os.edu.service.impl.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.edu.entity.website.WebsiteCourse;
import com.atdld.os.edu.dao.website.WebsiteCourseDao;
import com.atdld.os.edu.service.website.WebsiteCourseService;

/**
 * WebsiteCourse管理接口 User:  Date: 2014-05-27
 */
@Service("websiteCourseService")
public class WebsiteCourseServiceImpl implements WebsiteCourseService {
	MemCache memCache = MemCache.getInstance();
	@Autowired
	private WebsiteCourseDao websiteCourseDao;

	 /**
     * 推荐课程分类列表
     * @param websiteCourse
     * @param page
     * @return
     */
    public List<WebsiteCourse> queryWebsiteCourseList(){
    	return websiteCourseDao.queryWebsiteCourseList();
    }

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public WebsiteCourse queryWebsiteCourseById(Long id){
    	return websiteCourseDao.queryWebsiteCourseById(id);
    }
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
    public void updateWebsiteCourseById(WebsiteCourse websiteCourse){
    	websiteCourseDao.updateWebsiteCourseById(websiteCourse);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
    public void addWebsiteCourse(WebsiteCourse websiteCourse){
    	websiteCourseDao.addWebsiteCourse(websiteCourse);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }
    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id){
    	websiteCourseDao.deleteWebsiteCourseDetailById(id);
    	memCache.remove(MemConstans.RECOMMEND_COURSE);
    }
    
}