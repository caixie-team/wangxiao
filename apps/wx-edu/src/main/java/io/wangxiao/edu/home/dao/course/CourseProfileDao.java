package io.wangxiao.edu.home.dao.course;

import io.wangxiao.edu.home.entity.course.CourseProfile;

import java.util.List;

/**
 * CourseProfile管理接口
 */
public interface CourseProfileDao {

    /**
     * 添加CourseProfile
     *
     * @param courseProfile 要添加的CourseProfile
     * @return id
     */
    java.lang.Long addCourseProfile(CourseProfile courseProfile);

    /**
     * 根据id删除一个CourseProfile
     *
     * @param id 要删除的id
     */
    void deleteCourseProfileById(Long id);

    /**
     * 根据courseId删除一个CourseProfile
     *
     * @param courseId 要删除的courseId
     */
    void deleteCourseProfileByCourseId(Long courseId);

    /**
     * 修改CourseProfile
     *
     * @param courseProfile 要修改的CourseProfile
     */
    void updateCourseProfile(CourseProfile courseProfile);


    /**
     * 根据条件获取CourseProfile列表
     *
     * @param courseProfile 查询条件
     * @return List<CourseProfile>
     */
    List<CourseProfile> getCourseProfileList(CourseProfile courseProfile);


    /**
     * 要更新的数量类型
     * 修改CourseProfile浏览次数
     *
     * @param courseProfile
     */
    void updateCourseProfile(String type, Long courseId, Long count, String operation);

    /**
     * 根据课程编号更新购买该课程的数量
     *
     * @param ids
     */
    void updateCourseBuyCount(String ids);
}