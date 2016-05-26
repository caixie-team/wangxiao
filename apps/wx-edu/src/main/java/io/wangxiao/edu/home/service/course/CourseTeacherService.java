package io.wangxiao.edu.home.service.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseProfile;
import io.wangxiao.edu.home.entity.course.CourseTeacher;
import io.wangxiao.edu.home.entity.course.QueryCourseProfile;

import java.util.List;

/**
 * CourseTeacher管理接口
 */
public interface CourseTeacherService {

    /**
     * 添加CourseTeacher
     *
     * @param courseTeacher 要添加的CourseTeacher
     * @return id
     */
    java.lang.Long addCourseTeacher(CourseTeacher courseTeacher);

    /**
     * 根据id删除一个CourseTeacher
     *
     * @param id 要删除的id
     */
    void deleteCourseTeacherById(Long id);

    /**
     * 修改CourseTeacher
     *
     * @param courseTeacher 要修改的CourseTeacher
     */
    void updateCourseTeacher(CourseTeacher courseTeacher);

    /**
     * 根据id获取单个CourseTeacher对象
     *
     * @param id 要查询的id
     * @return CourseTeacher
     */
    CourseTeacher getCourseTeacherById(Long id);

    /**
     * 根据条件获取CourseTeacher列表
     *
     * @param courseTeacher 查询条件
     * @return List<CourseTeacher>
     */
    List<CourseTeacher> getCourseTeacherList(CourseTeacher courseTeacher);

    /**
     * 根据条件查询课程统计
     *
     * @param map
     * @return
     */
    List<CourseProfile> getCourseByteacher(QueryCourseProfile queryCourseProfile, PageEntity entity);
}