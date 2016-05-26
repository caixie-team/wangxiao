package io.wangxiao.edu.app.service.website;


import io.wangxiao.edu.app.entity.website.AppWebsiteCourse;

import java.util.List;

/**
 * WebsiteCourse管理接口
 */
public interface AppWebsiteCourseService {

    /**
     * 推荐课程分类列表
     *
     * @return
     */
    List<AppWebsiteCourse> queryWebsiteCourseList();

    /**
     * 查询推荐课程分类
     *
     * @param id
     * @return
     */
    AppWebsiteCourse queryWebsiteCourseById(Long id);

    /**
     * 修改推荐课程分类
     */
    void updateWebsiteCourseById(AppWebsiteCourse websiteCourse);

    /**
     * 添加推荐课程分类
     */
    void addWebsiteCourse(AppWebsiteCourse websiteCourse);

    /**
     * 删除推荐课程分类及分类下推荐课程
     */
    void deleteWebsiteCourseDetailById(Long id);
}