package io.wangxiao.edu.home.service.course;

import java.util.List;

import io.wangxiao.edu.home.entity.course.CourseProfile;

public interface CourseProfileService {

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

}