package io.wangxiao.edu.home.service.course;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseHandout;

import java.util.List;

public interface CourseHandoutService {

    /**
     * 添加讲义
     *
     * @param courseHandout 讲义
     * @return 影响行数
     */
    Long addCourseHandout(CourseHandout courseHandout);

    /**
     * 删除讲义
     *
     * @param ids id集合
     * @return 删除行数
     */
    Long deleteCourseHandout(String ids);

    /**
     * 修改讲义
     *
     * @param courseHandout 讲义
     * @return 影响行数
     */
    Long updateCourseHandout(CourseHandout courseHandout);

    /**
     * 获取讲义
     *
     * @param id 讲义id
     * @return 讲义
     */
    CourseHandout getCourseHandoutById(Long id);

    /**
     * 获取讲义列表
     *
     * @param courseHandout 参数
     * @return 讲义列表
     */
    List<CourseHandout> getCourseHandoutList(CourseHandout courseHandout);

    /**
     * 获取讲义列表
     *
     * @param courseHandout 参数
     * @param page          分页
     * @return 讲义列表
     */
    List<CourseHandout> getCourseHandoutListPage(CourseHandout courseHandout, PageEntity page);
}