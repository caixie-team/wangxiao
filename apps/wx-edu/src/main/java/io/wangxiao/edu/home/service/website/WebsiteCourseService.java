package io.wangxiao.edu.home.service.website;

import java.util.List;

import io.wangxiao.edu.home.entity.website.WebsiteCourse;

public interface WebsiteCourseService {

	 /**
     * 推荐课程分类列表
     * @param websiteCourse
     * @param page
     * @return
     */
     List<WebsiteCourse> queryWebsiteCourseList();

    /**
     * 查询推荐课程分类
     * @param id
     * @return
     */
    WebsiteCourse queryWebsiteCourseById(Long id);
    /**
     * 修改推荐课程分类
     * @param id
     * @return
     */
    void updateWebsiteCourseById(WebsiteCourse websiteCourse);
    /**
     * 添加推荐课程分类
     * @param id
     * @return
     */
    void addWebsiteCourse(WebsiteCourse websiteCourse);

    /**
     * 删除推荐课程分类及分类下推荐课程
     * 
     * @param id
     */
    void deleteWebsiteCourseDetailById(Long id);
}