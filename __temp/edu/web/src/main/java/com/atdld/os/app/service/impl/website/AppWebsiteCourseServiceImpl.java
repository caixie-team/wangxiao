package com.atdld.os.app.service.impl.website;

import com.atdld.os.app.dao.website.AppWebsiteCourseDao;
import com.atdld.os.app.entity.website.AppWebsiteCourse;
import com.atdld.os.app.service.website.AppWebsiteCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * WebsiteCourse管理接口 User:  Date: 2014-05-27
 */
@Service("appWebsiteCourseService")
public class AppWebsiteCourseServiceImpl implements AppWebsiteCourseService {
	@Autowired
	private AppWebsiteCourseDao appWebsiteCourseDao;

	 /**
     * 推荐课程分类列表
     * @return
     */
    public List<AppWebsiteCourse> queryWebsiteCourseList(){
    	return appWebsiteCourseDao.queryWebsiteCourseList();
    }

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public AppWebsiteCourse queryWebsiteCourseById(Long id){
    	return appWebsiteCourseDao.queryWebsiteCourseById(id);
    }
    /**
     * 修改推荐课程分类
     * @return
     */
    public void updateWebsiteCourseById(AppWebsiteCourse websiteCourse){
    	appWebsiteCourseDao.updateWebsiteCourseById(websiteCourse);
    }
    /**
     * 添加推荐课程分类
     * @return
     */
    public void addWebsiteCourse(AppWebsiteCourse websiteCourse){
    	appWebsiteCourseDao.addWebsiteCourse(websiteCourse);
    }
    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id){
    	appWebsiteCourseDao.deleteWebsiteCourseDetailById(id);
    }
}