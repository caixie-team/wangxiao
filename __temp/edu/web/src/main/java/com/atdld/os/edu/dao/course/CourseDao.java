package com.atdld.os.edu.dao.course;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourse;
import com.atdld.os.edu.entity.user.UserExpand;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
public interface CourseDao {

    /**
     * 添加Course
     *
     * @param course 要添加的Course
     * @return id
     */
    public java.lang.Long addCourse(Course course);

    /**
     * 根据id删除一个Course
     *
     * @param id 要删除的id
     */
    public void deleteCourseById(Long id);

    /**
     * 修改Course
     *
     * @param course 要修改的Course
     */
    public void updateCourse(Course course);

    /**
     * 根据id获取单个Course对象
     *
     * @param id 要查询的id
     * @return Course
     */
    public Course getCourseById(Long id);

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<Course> getCourseList(Course course);
    
    
    
    /**
     * 获取受欢迎浏览多的课程
     * @param course
     * @return
     */
    public List<Course> getCourseListByBro();
    

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page);
    
    /**
     * 根据条件获取公开课列表
     * 
     * @param course
     *            查询条件
     * @return List<Course>
     */
    public List<CourseDto> getPublicCourseListPage(QueryCourse course, PageEntity page);

    /**
     * 首页获取课程
     *
     * @param recommendId
     * @return
     */
    public List<CourseDto> getCourseListByHomePage(Long recommendId);

    /**
     * 获得专业下的课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    public List<CourseDto> getSubjectCourseList(Long subjectId, Long courseId, int num);

    /**
     * 查询最新课程
     *
     * @param num
     * @return
     */
    public List<Course> getNowCourseList(int num);

    /**
     * 获取课程套餐的详细课程列表
     *
     * @param id
     * @return
     */
    public List<CourseDto> getCourseListPackage(List<Long> ids);

    /**
     * 获取多个课程的信息
     *
     * @param List <Long> ids
     * @return
     */
    public List<CourseDto> getCourseListByCourseIds(List<Long> ids,String sellType);
    /**
     * 获取多个课程的信息
     *
     * @param List <Long> ids
     * @return
     */
    public List<CourseDto> getCourseListByCourseIdsAndUser(List<Long> ids,Long userId,String sellType);

    /**
     * 获取免费课程
     *
     * @param num 条数
     * @return
     */
    public List<CourseDto> getFreeCourseList(Long userId,Long num);
    
    /**
     * app查询课程列表专用 
     * @param map 查询条件
     * @param page 分页条件
     * @return
     */
    public List<Map<String,Object>> queryAppAllCourse(Map<String,Object> map,PageEntity page);
    /**
     * 获得专业下的所有课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    public List<Course> getCouponSubjectCourse(Long subjectId,String courseIds);
    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getAppCourseListPage(QueryCourse course, PageEntity page);
    
    /**
     * 根据课程ID串，查询课程列表
     * @param courseIds
     * @return
     */
    public List<Course> queryCourseListByIds(String courseIds);
    /**
     * 根据课程ID，查询用户列表信息
     * @param courseIds
     * @return
     */
    public List<UserExpand> queryUserExpandListByCourseId (Long courseId);
    
    /**
     * 后台获取课程套餐下的详细课程列表
     *
     * @param course
     * @return
     */
    public List<Course> getCourseListPackageByCondition(Course course);
    
    
}