package io.wangxiao.edu.home.dao.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.user.UserExpand;

import java.util.List;
import java.util.Map;

public interface CourseDao {


    /**
     * 添加Course
     *
     * @param course 要添加的Course
     * @return id
     */
    java.lang.Long addCourse(Course course);

    /**
     * 根据id删除一个Course
     *
     * @param id 要删除的id
     */
    void deleteCourseById(Long ids);

    /**
     * 修改Course
     *
     * @param course 要修改的Course
     */
    void updateCourse(Course course);

    /**
     * 根据id获取单个Course对象
     *
     * @param id 要查询的id
     * @return Course
     */
    Course getCourseById(Long id);

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    List<Course> getCourseList(Course course);


    /**
     * 获取受欢迎浏览多的课程
     *
     * @param course
     * @return
     */
    List<Course> getCourseListByBro();


    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page);

    /**
     * 根据条件获取公开课列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    List<CourseDto> getPublicCourseListPage(QueryCourse course, PageEntity page);

    /**
     * 首页获取课程
     *
     * @param recommendId
     * @return
     */
    List<CourseDto> getCourseListByHomePage(Long recommendId);

    /**
     * 获得专业下的课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    List<CourseDto> getSubjectCourseList(Long subjectId, Long courseId, int num, String companySellType);

    /**
     * 查询最新课程
     *
     * @param num
     * @return
     */
    List<Course> getNowCourseList(int num);

    /**
     * 获取课程套餐的详细课程列表
     *
     * @param id
     * @return
     */
    List<CourseDto> getCourseListPackage(List<Long> ids);

    /**
     * 获取多个课程的信息
     *
     * @param ids <Long> ids
     * @param num 查询条数
     * @return
     */
    List<CourseDto> getCourseListByCourseIds(List<Long> ids, String sellType, int num);

    /**
     * 获取多个课程的信息
     *
     * @param List <Long> ids
     * @return
     */
    List<CourseDto> getCourseListByCourseIdsAndUser(List<Long> ids, Long userId, String sellType);

    /**
     * 获取免费课程
     *
     * @param num 条数
     * @return
     */
    List<CourseDto> getFreeCourseList(Long userId, Long num);

    /**
     * app查询课程列表专用
     *
     * @param map  查询条件
     * @param page 分页条件
     * @return
     */
    List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page);

    /**
     * 获得专业下的所有课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    List<Course> getCouponSubjectCourse(Long subjectId, String courseIds);

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    List<CourseDto> getAppCourseListPage(QueryCourse course, PageEntity page);

    /**
     * 根据课程ID串，查询课程列表
     *
     * @param courseIds
     * @return
     */
    List<Course> queryCourseListByIds(String courseIds);

    /**
     * 根据课程ID，查询用户列表信息
     *
     * @param courseIds
     * @return
     */
    List<UserExpand> queryUserExpandListByCourseId(Long courseId);

    /**
     * 后台获取课程套餐下的详细课程列表
     *
     * @param course
     * @return
     */
    List<Course> getCourseListPackageByCondition(Course course);

    List<CourseDto> getInnerCourseList(List alist);

    /**
     * 根据用户id获取课程列表
     *
     * @param queryCourse
     * @param page
     * @return
     */
    List<CourseDto> getUserCourseList(QueryCourse queryCourse, PageEntity page);

    /**
     * 更新课程播放时长
     *
     * @param course 课程
     */
    void updateCoursePlayTime(Course course);

    /**
     * 更新课程上下架状态
     *
     * @param course
     */
    void updateCourseIsavaliableById(Course course);

    /**
     * 批量上下架课程
     *
     * @param map
     */
    void updateCourseIsavaliableBatch(Map map);

    /**
     * 更新课时数
     *
     * @param course
     */
    void updateCourselessionnum(Course course);

    /**
     * 更新课时数
     *
     * @param course
     */
    Long getCourselessionnum(Long id);

    /**
     * 查询前台观看课程（可查出下架课程）
     *
     * @param id
     * @return
     */
    CourseDto getCourseInfoByCourseId(Long id);
}