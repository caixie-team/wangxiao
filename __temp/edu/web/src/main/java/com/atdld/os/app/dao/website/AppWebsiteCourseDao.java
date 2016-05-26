package com.atdld.os.app.dao.website;


import java.util.List;

import com.atdld.os.app.entity.website.AppWebsiteCourse;

/**
 * WebsiteCourse管理接口 User:  Date: 2014-05-27
 */
public interface AppWebsiteCourseDao {

	
    /**
     * 推荐课程分类列表
     */
    public List<AppWebsiteCourse> queryWebsiteCourseList();

    /**
     * 查询推荐课程分类
     */
    public AppWebsiteCourse queryWebsiteCourseById(Long id);
    /**
     * 修改推荐课程分类
     */
    public void updateWebsiteCourseById(AppWebsiteCourse websiteCourse);
    /**
     * 添加推荐课程分类
     * @return
     */
    public void addWebsiteCourse(AppWebsiteCourse websiteCourse);

    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    public void deleteWebsiteCourseDetailById(Long id);
}