package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.course.entity.Course;
import co.bluepx.edu.course.entity.CourseDto;
import co.bluepx.edu.course.entity.QueryCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface CourseDao extends BaseDao<Course> {

    /**
     * 查询推荐课程
     *
     * @param recommendId
     * @return
     */
    List<CourseDto> findRecommendCourses(@Param(value = "recommendId") Long recommendId);

    /**
     * 根据 条件(ids,userId,sellType) 查询课程详情
     *
     * @return
     */
    List<CourseDto> findCourseListByCourseIds(Map<String, Object> map);

    /**
     * 根据教师 id 查询关联课程
     *
     * @param teacherId
     * @return
     */
    List<Course> findCourseByTeacherId(@Param(value = "teacherId") long teacherId);
    /**
     * 分页查询课程 DTO 信息
     * @param queryCourse
     * @return List<CourseDto>
     */
    List<CourseDto> findCourseListByPage(@Param(value="e") QueryCourse queryCourse);

    /**
     * 根据课程ID 与课程类型查询课程的目录与列表信息
     * @param list
     * @return
     */
    List<CourseDto> findCourseListPackage(List list);

    /**
     * 查询专业下的课程
     *
     * @return
     */
    List<CourseDto> findSubjectCourseList(Map map);


}
