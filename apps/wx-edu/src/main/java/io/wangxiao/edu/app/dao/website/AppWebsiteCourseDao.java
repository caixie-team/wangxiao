package io.wangxiao.edu.app.dao.website;


import io.wangxiao.edu.app.entity.website.AppWebsiteCourse;

import java.util.List;

/**
 * WebsiteCourse管理接口
 */
public interface AppWebsiteCourseDao {


    /**
     * 推荐课程分类列表
     */
    List<AppWebsiteCourse> queryWebsiteCourseList();

    /**
     * 查询推荐课程分类
     */
    AppWebsiteCourse queryWebsiteCourseById(Long id);

    /**
     * 修改推荐课程分类
     */
    void updateWebsiteCourseById(AppWebsiteCourse websiteCourse);

    /**
     * 添加推荐课程分类
     *
     * @return
     */
    void addWebsiteCourse(AppWebsiteCourse websiteCourse);

    /**
     * 删除推荐课程分类及分类下推荐课程
     *
     * @param id
     */
    void deleteWebsiteCourseDetailById(Long id);
}