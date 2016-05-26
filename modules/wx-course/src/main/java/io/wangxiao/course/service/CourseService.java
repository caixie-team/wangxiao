package io.wangxiao.course.service;


import io.wangxiao.course.model.Course;
import io.wangxiao.course.vo.CourseVo;

import java.util.List;
import java.util.Map;

public interface CourseService {

    /**
     * 添加Course
     *
     * @param courseVo 要添加的Course
     * @return id
     */
    Long addCourse(CourseVo courseVo);

    /**
     * 根据推荐别ID获取推荐课程
     *
     * @param recommendId
     * @return
     */
    List<CourseVo> findRecommendCourses(Long recommendId);

    /**
     * 根据id删除一个Course
     *
     * @param ids 要删除的多条ID
     */
    void deleteCourseById(String ids);

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
     * 根据id获取单个Course对象里面包含该课程的教师
     *
     * @param id 要查询的id
     * @return Course
     */
    CourseVo getCourseInfoById(Long id);

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    List<Course> getCourseList(Course course);

    /**
     * 获取用户购买过的课程，未过期的
     *
     * @param userId
     * @return
     */
    boolean checkCourseIsInner(Long userId, Long courseId);

    /**
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
//    List<CourseVo> getCourseListPage(QueryCourse course, PageEntity page);
//
    /**
     * 根据条件获取公开课列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
//    List<CourseVo> getPublicCourseListPage(QueryCourse course, PageEntity page);

    /**
     * 首页获取课程
     *
     * @param recommendId
     * @return List<Course>
     * @throws Exception
     */
    Map<String, List<CourseVo>> getCourseListByHomePage(Long recommendId) throws Exception;

    /**
     * 获得专业下的课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    List<CourseVo> getSubjectCourseList(Long subjectId, Long courseId, int num) throws Exception;

    /**
     * 首页根据专业id获取课程信息
     *
     * @param subjectId
     * @return
     */
    List<CourseVo> getIndexCourseList(Long subjectId);

    /**
     * 获得项目专业限制的所有课程
     *
     * @param subjectId
     * @param courseIds
     * @return
     */
    List<Course> getCouponSubjectCourse(Long subjectId, String courseIds);

    /**
     * 查询某套餐的具体课程
     *
     * @param ids 查询条件
     * @return List<Course>
     */
    List<CourseVo> getCourseListPackage(List<Long> ids);

    /**
     * 获取用户购买过的课程，未过期的
     *
     * @param userId
     * @return
     */
    List<CourseVo> getUserBuyCourseList(Long userId, String sellWay);

    /**
     * 获取用户购买过的直播课程，未过期的
     */
//    List<CourseVo> getUserBuyLiveCourseList(Long userId, PageEntity page);

    /**
     * 过滤直播的课程
     */
//    List<CourseVo> filtrationLive(List<CourseDto> courseDtoList);

    /**
     * 获取免费课程
     *
     * @param num 条数
     * @return
     */
    List<CourseVo> getFreeCourseList(Long userId, Long num);

    /**
     * 删除收藏课程
     *
     * @param courseIds
     */
    void delFavouriteCourseByIds(String courseIds);

    /**
     * app查询课程列表专用
     *
     * @param map  查询条件
     * @param page 分页条件
     * @return
     */
//    List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page);


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
//    List<UserExpand> queryUserExpandListByCourseId(Long courseId);

    /**
     * 后台获取课程套餐下的详细课程列表
     *
     * @param course
     * @return
     */
    List<Course> getCourseListPackageByCondition(Course course);

    /**
     * 获取用户购买过的课程，未过期的
     *
     * @param userId
     * @return
     */
//    List<UserGroup> getUserInnerCourseListByGroup(Long userId);

    /**
     * 获取用户购买过的课程，未过期的
     *
     * @param userId
     * @return
     */
//    List<CourseDto> getUserInnerCourseList(Long userId, String showType);
//
    /**
     * 根据用户id获取课程列表
     *
     * @param queryCourse
     * @return
     */
//    List<CourseDto> getUserCourseList(QueryCourse queryCourse, PageEntity page);

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
     * 前台观看课程（可查出下架课程）
     *
     * @param id
     * @return
     */
    CourseVo getCourseInfoByCourseId(Long id);


    /**
     * 是否可以观看课程
     *
     * @param userId    用户id
     * @param courseDto 课程
     * @return
     */
    boolean isPlay(Long userId, CourseVo courseDto);
}