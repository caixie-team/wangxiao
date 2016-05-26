package com.atdld.os.app.dao.impl.website;

import com.atdld.os.app.dao.website.AppWebsiteCourseDao;
import com.atdld.os.app.entity.website.AppWebsiteCourse;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * WebsiteCourse
 * User:
 * Date: 2014-05-27
 */
 @Repository("appWebsiteCourseDao")
public class AppWebsiteCourseDaoImpl extends GenericDaoImpl implements AppWebsiteCourseDao {
    /**
     * 推荐课程分类列表
     */
    public List<AppWebsiteCourse> queryWebsiteCourseList(){
    	return this.selectList("AppWebsiteCourseMapper.queryWebsiteCourseList",0);
    }

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    public AppWebsiteCourse queryWebsiteCourseById(Long id){
    	return this.selectOne("AppWebsiteCourseMapper.getWebsiteCourseById", id);
    }
    /**
     * 修改推荐课程分类
     * @return
     */
    public void updateWebsiteCourseById(AppWebsiteCourse appWebsiteCourse){
    	this.update("AppWebsiteCourseMapper.updateWebsiteCourse", appWebsiteCourse);
    }
    /**
     * 添加推荐课程分类
     * @param appWebsiteCourse
     * @return
     */
    public void addWebsiteCourse(AppWebsiteCourse appWebsiteCourse){
    	this.insert("AppWebsiteCourseMapper.createWebsiteCourse", appWebsiteCourse);
    	
    }
    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id){
    	this.delete("AppWebsiteCourseMapper.deleteWebsiteCourseById", id);
    	this.delete("AppWebsiteCourseDetailMapper.delWebsiteCourseDetails", id);
    }
}
