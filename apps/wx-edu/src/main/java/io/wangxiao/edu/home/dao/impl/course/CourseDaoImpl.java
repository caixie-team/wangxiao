package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.course.CourseDao;
import io.wangxiao.edu.home.dao.course.CourseTeacherDao;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.user.UserExpand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl implements CourseDao {

    @Autowired
    CourseTeacherDao courseTeacherDao;

    public java.lang.Long addCourse(Course course) {
        this.insert("CourseMapper.createCourse", course);
        CourseProfile courseProfile = new CourseProfile();
        courseProfile.setCourseId(course.getId());
        return course.getId();
    }

    public void deleteCourseById(Long ids) {
        this.delete("CourseMapper.deleteCourseById", ids);
    }

    public void updateCourse(Course course) {
        this.update("CourseMapper.updateCourse", course);
    }

    public Course getCourseById(Long id) {
        return this.selectOne("CourseMapper.getCourseById", id);
    }


    public List<Course> getCourseList(Course course) {
        return this.selectList("CourseMapper.getCourseList", course);
    }


    public List<Course> getCourseListByBro() {
        return this.selectList("CourseMapper.getCourseListByBro", null);
    }


    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page) {
        return this.queryForListPage("CourseMapper.getCourseListPage", course, page);
    }

    /**
     * 根据条件获取公开课列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getPublicCourseListPage(QueryCourse course, PageEntity page) {
        return this.queryForListPage("CourseMapper.getPublicCourseListPage", course, page);
    }

    /**
     * 首页获取课程 查询了2次SQL。尽量少用循环的查询
     *
     * @param recommendId
     * @return
     */
    public List<CourseDto> getCourseListByHomePage(Long recommendId) {

        List<CourseDto> courseDtos = this.selectList("CourseMapper.getCourseListByHomePage", recommendId);
        if (ObjectUtils.isNotNull(courseDtos)) {
            List<Long> list = new ArrayList<Long>();
            for (CourseDto courseDto : courseDtos) {
                list.add(courseDto.getId());
            }
            // 获取讲师的list
            Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
            // 将讲师的list放到旧的list中
            if (ObjectUtils.isNotNull(map)) {
                for (CourseDto courseDto : courseDtos) {
                    courseDto.setTeacherList(map.get(courseDto.getId()));
                }
            }
        }
        return courseDtos;
    }

    /**
     * 获得专业下的课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    public List<CourseDto> getSubjectCourseList(Long subjectId, Long courseId, int num, String companySellType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subjectId", subjectId);
        map.put("courseId", courseId);
        map.put("num", num);
        map.put("companySellType", companySellType);
        return this.selectList("CourseMapper.getSubjectCourseList", map);
    }

    /**
     * 查询最新课程
     *
     * @param num
     * @return List<Course>
     */
    public List<Course> getNowCourseList(int num) {
        return this.selectList("CourseMapper.getNowCourseList", num);
    }

    /**
     * 获取课程套餐的详细课程列表
     *
     * @param List<Long> ids
     * @return
     */
    public List<CourseDto> getCourseListPackage(List<Long> ids) {
        return this.selectList("CourseMapper.getCourseListPackage", ids);
    }

    /**
     * 获取多个课程的信息
     *
     * @param List<Long> ids
     * @return
     */
    public List<CourseDto> getCourseListByCourseIds(List<Long> ids, String sellType, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ids", ids);
        map.put("sellType", sellType);
        map.put("num", num);
        return this.selectList("CourseMapper.getCourseListByCourseIds", map);
    }

    /**
     * 查询前台观看课程（可查出下架课程）
     *
     * @param id
     * @return
     */
    public CourseDto getCourseInfoByCourseId(Long id) {
        return this.selectOne("CourseMapper.getCourseInfoByCourseId", id);
    }

    /**
     * 获取多个课程的信息
     *
     * @param List<Long> ids
     * @return
     */
    public List<CourseDto> getCourseListByCourseIdsAndUser(List<Long> ids, Long userId, String sellType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ids", ids);
        map.put("sellType", sellType);
        map.put("userId", userId);
        return this.selectList("CourseMapper.getCourseListByCourseIds", map);
    }

    /**
     * 获取免费课程
     *
     * @param num 条数
     * @return
     */
    public List<CourseDto> getFreeCourseList(Long userId, Long num) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("num", num);
        return this.selectList("CourseMapper.getFreeCourseList", map);
    }


    public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map, PageEntity page) {
        return queryForListPage("CourseMapper.queryAppAllCourse", map, page);
    }

    public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subjectId", subjectId);
        map.put("courseIds", courseIds);
        return this.selectList("CourseMapper.getCouponSubjectCourse", map);
    }

    @Override
    public List<CourseDto> getAppCourseListPage(QueryCourse course,
                                                PageEntity page) {
        // TODO Auto-generated method stub
        return this.queryForListPage("CourseMapper.getAppCourseListPage", course, page);
    }

    @Override
    public List<Course> queryCourseListByIds(String courseIds) {
        return selectList("CourseMapper.queryCourseListByIds", courseIds);
    }

    /**
     * 根据课程ID，查询用户列表信息
     *
     * @param courseIds
     * @return
     */
    public List<UserExpand> queryUserExpandListByCourseId(Long courseId) {
        return selectList("UserExpandMapper.queryUserExpandListByCourseId", courseId);
    }

    /**
     * 后台获取课程套餐下的详细课程列表
     *
     * @param course
     * @return
     */
    public List<Course> getCourseListPackageByCondition(Course course) {
        return this.selectList("CourseMapper.getCourseListPackageByCondition", course);
    }

    @Override
    public List<CourseDto> getInnerCourseList(List alist) {
        // TODO Auto-generated method stub
        return this.selectList("CourseMapper.getInnerCourseList", alist);
    }

    @Override
    public List<CourseDto> getUserCourseList(QueryCourse queryCourse, PageEntity page) {
        return this.queryForListPage("CourseMapper.getUserCourseList", queryCourse, page);
    }

    @Override
    public void updateCoursePlayTime(Course course) {
        this.update("CourseMapper.updateCoursePlayTime", course);
    }

    @Override
    public void updateCourseIsavaliableById(Course course) {
        this.update("CourseMapper.updateCourseIsavaliableById", course);
    }

    @Override
    public void updateCourseIsavaliableBatch(Map map) {
        this.update("CourseMapper.updateCourseIsavaliableBatch", map);
    }

    @Override
    public void updateCourselessionnum(Course course) {
        this.update("CourseMapper.updateCourselessionnum", course);

    }

    @Override
    public Long getCourselessionnum(Long id) {

        return this.selectOne("CourseMapper.getCourselessionnum", id);
    }
}
