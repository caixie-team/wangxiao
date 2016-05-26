package io.wangxiao.edu.home.service.course;

import io.wangxiao.edu.home.entity.course.CourseSubject;

import java.util.List;

/**
 * CourseSubject管理接口
 */
public interface CourseSubjectService {

    /**
     * 添加CourseSubject
     * @param CourseSubject 要添加的CourseSubject
     * @return id
     */
    java.lang.Long addCourseSubject(CourseSubject CourseSubject);

    /**
     * 根据id删除一个CourseSubject
     * @param id 要删除的id
     */
    void deleteCourseSubjectById(Long id);
    /**
     * 根据条件删除一个CourseSubject
     */
    void deleteCourseSubject(CourseSubject CourseSubject);
    /**
     * 修改CourseSubject
     * @param CourseSubject 要修改的CourseSubject
     */
    void updateCourseSubject(CourseSubject CourseSubject);

    /**
     * 根据id获取单个CourseSubject对象
     * @param id 要查询的id
     * @return CourseSubject
     */
    CourseSubject getCourseSubjectById(Long id);

    /**
     * 根据条件获取CourseSubject列表
     * @param CourseSubject 查询条件
     * @return List<CourseSubject>
     */
    List<CourseSubject> getCourseSubjectList(CourseSubject CourseSubject);
}