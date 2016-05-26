package io.wangxiao.edu.home.service.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseAssess;
import io.wangxiao.edu.home.entity.course.QueryCourseAssess;

import java.util.List;

public interface CourseAssessService {

    /**
     * 添加CourseAssess
     *
     * @param courseAssess 要添加的CourseAssess
     * @return id
     */
    java.lang.Long addCourseAssess(CourseAssess courseAssess);

    /**
     * 根据id删除一个CourseAssess
     *
     * @param id 要删除的id
     */
    void deleteCourseAssessById(Long id);

    /**
     * 修改CourseAssess
     *
     * @param courseAssess 要修改的CourseAssess
     */
    void updateCourseAssess(CourseAssess courseAssess);

    /**
     * 根据id获取单个QueryCourseAssess对象
     *
     * @param id 要查询的id
     * @return CourseAssess
     */
    QueryCourseAssess getCourseAssessById(Long id);

    /**
     * 根据条件获取CourseAssess列表
     *
     * @param courseAssess 查询条件
     * @return List<CourseAssess>
     */
    List<CourseAssess> getCourseAssessList(CourseAssess courseAssess);

    /**
     * 根据条件获取CourseAssess列表
     *
     * @param courseAssess 查询条件
     * @return List<CourseAssess>
     */
    List<QueryCourseAssess> getCourseAssessListPage(CourseAssess courseAssess, PageEntity page);

    /**
     * 如果课程为套餐课程，查询该套餐课程下所有课程的评论
     *
     * @param courseAssess
     * @param page
     * @return
     */
    List<QueryCourseAssess> getAllCourseAssessListPage(CourseAssess courseAssess, PageEntity page);

    /**
     * 后台查询课程评论列表
     *
     * @param queryCourseAssess
     * @param page
     * @return
     */
    List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page) throws Exception;

    /**
     * 删除课程评论
     *
     * @param courseAssessIds
     */
    void delCourseAssessBatch(String courseAssessIds);

    /**
     * 根据课程id删除课程评论
     *
     * @param courseId 课程id
     */
    void deleteCourseAssessByCourseId(Long courseId);
}