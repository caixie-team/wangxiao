package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.entity.PageEntity;
import co.bluepx.edu.course.entity.CourseAssess;
import co.bluepx.edu.course.entity.QueryCourseAssess;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseAssessDao extends BaseDao<CourseAssess> {

    /**
     * 根据条件获取CourseAssess列表
     *
     * @param map 查询条件 courseId、kpointId
     * @return List<CourseAssess>
     */
    List<QueryCourseAssess> findCourseAssessList(@Param("e") Map<String,Object> map);

    /**
     * 如果课程为套餐课程，查询该套餐课程下所有课程的评论
     *
     * @param courseAssess
     * @param page
     * @return
     */
    List<QueryCourseAssess> findPackageCourseAssessList(@Param("courseIds") String courseIds);
    /**
     * 添加CourseAssess
     *
     * @param courseAssess 要添加的CourseAssess
     * @return id
     */
    Long addCourseAssess(CourseAssess courseAssess);

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
     * @return QueryCourseAssess
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
     * 后台查询课程评论列表
     *
     * @param queryCourseAssess
     * @param page
     * @return
     */
    List<QueryCourseAssess> getAdminCourseAssessList(QueryCourseAssess queryCourseAssess, PageEntity page);

    /**
     * 删除课程评论
     *
     * @param courseAssessIds
     */
    void delCourseAssessBatch(String courseAssessIds);
}