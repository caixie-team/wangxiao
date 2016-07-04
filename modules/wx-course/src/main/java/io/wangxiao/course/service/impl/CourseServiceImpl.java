package io.wangxiao.course.service.impl;

import io.wangxiao.course.dao.CourseDao;
import io.wangxiao.course.model.Course;
import io.wangxiao.course.model.CourseProfile;
import io.wangxiao.course.model.CourseSubject;
import io.wangxiao.course.model.EduCourseTeacher;
import io.wangxiao.course.service.CourseService;
import io.wangxiao.course.vo.CourseVo;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by bison on 5/25/16.
 * <p>
 * 课程管理服务实现类
 */
@Service
//@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    SQLManager sqlManager;

    @Autowired
    CourseDao courseDao;

//    private CacheKit cacheKit = CacheKit.getInstance();


    ///~ 查询

    /**
     * 根据id获取单个Course对象
     *
     * @param id 要查询的id
     * @return Course
     */
    public Course getCourseById(Long id) {
        return sqlManager.unique(Course.class, id);
    }

    /**
     * 添加Course
     *
     * @return id
     */
    public Long addCourse(CourseVo vo) {

        // 设置课程添加与更新时间
        vo.setAddtime(new Date());
        vo.setUpdateTime(new Date());

        KeyHolder keyHolder = new KeyHolder();
        sqlManager.insert(Course.class, vo, keyHolder);

        Long _key = (Long) keyHolder.getKey();

        // 添加课程关联专业
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourseId(_key);
        courseSubject.setSubjectId(vo.getSubjectId());
        sqlManager.insert(CourseSubject.class, courseSubject);

        // 删除该课程下全部的教师
//        courseTeacherDao.deleteCourseTeacherByCourseId(course.getId());//这句有修改

        // 批量添加课程和教师的关联
        if (StringUtils.isNotEmpty(vo.getTeacherIds())) {
            List<EduCourseTeacher> courseTeacherList = new ArrayList<>();
            String[] teaherIds = vo.getTeacherIds().split(",");
            for (String id : teaherIds) {
                EduCourseTeacher courseTeacher = new EduCourseTeacher();
                courseTeacher.setCourseId(_key);
                courseTeacher.setTeacherId(Long.valueOf(id));
                courseTeacherList.add(courseTeacher);
            }

            sqlManager.insertBatch(EduCourseTeacher.class, courseTeacherList);
        }

        //添加课程配置数据
        CourseProfile courseProfile = new CourseProfile();
        courseProfile.setCourseId(_key);
        sqlManager.insert(courseProfile.getClass(), courseProfile);

        return _key;
    }

    @Override
    public List<CourseVo> findRecommendCourses(Long recommendId) {
        return courseDao.findRecommendCoursesById(recommendId);
    }

    /**
     * 首页获取推荐的课程 封装为map key:index_course_+recommendId ,List<Course>
     *
     * @param recommendId
     * @throws Exception
     * @returnmap key:index_course_1 ,List<Course>
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<CourseVo>> getCourseListByHomePage(Long recommendId) throws Exception {// 首页课程存缓存
        Map<String, List<CourseVo>> map = new HashMap<String, List<CourseVo>>();
        // TODO: 待加入缓存开关
//        courseList = courseDao.getCourseListByHomePage(recommendId);// 查询db

//        List<CourseVo> courseList = (List<CourseVo>) cacheKit.get(MemConstans.RECOMMEND_COURSE);
//        if (ObjectUtils.isNull(courseList)) {
//            courseList = courseDao.getCourseListByHomePage(recommendId);// 查询db
//            if (ObjectUtils.isNotNull(courseList)) {
//                cacheKit.set(MemConstans.RECOMMEND_COURSE, courseList, MemConstans.RECOMMEND_COURSE_TIME);// 存缓存一小时
//            }
//        }
//        if (ObjectUtils.isNotNull(courseList)) {
//            List<CourseVo> courseHotList = new ArrayList<CourseDto>();
//            Long recommend = courseList.get(0).getRecommendId();
//            for (CourseVo course : courseList) {
//                if (recommend.longValue() != course.getRecommendId().longValue()) {
//                    map.put("index_course_" + recommend, courseHotList);
//                    courseHotList = new ArrayList<CourseDto>();
//                    courseHotList.add(course);
//                    recommend = course.getRecommendId();
//                } else {
//                    courseHotList.add(course);
//                }
//            }
//            if (ObjectUtils.isNotNull(courseHotList)) {
//                map.put("index_course_" + recommend, courseHotList);
//            }
//        }
        return map;
    }

    @Override
    public void deleteCourseById(String ids) {

    }


    @Override
    public void updateCourse(Course course) {

    }


    @Override
    public CourseVo getCourseInfoById(Long id) {
        return null;
    }

    @Override
    public List<Course> getCourseList(Course course) {
        return null;
    }

    @Override
    public boolean checkCourseIsInner(Long userId, Long courseId) {
        return false;
    }

    @Override
    public List<Course> getCourseListByBro() {
        return null;
    }


    @Override
    public List<CourseVo> getSubjectCourseList(Long subjectId, Long courseId, int num) throws Exception {
        return null;
    }

    @Override
    public List<CourseVo> getIndexCourseList(Long subjectId) {
        return null;
    }

    @Override
    public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
        return null;
    }

    @Override
    public List<CourseVo> getCourseListPackage(List<Long> ids) {
        return null;
    }

    @Override
    public List<CourseVo> getUserBuyCourseList(Long userId, String sellWay) {
        return null;
    }

    @Override
    public List<CourseVo> getFreeCourseList(Long userId, Long num) {
        return null;
    }

    @Override
    public void delFavouriteCourseByIds(String courseIds) {

    }

    @Override
    public List<Course> queryCourseListByIds(String courseIds) {
        return null;
    }

    @Override
    public List<Course> getCourseListPackageByCondition(Course course) {
        return null;
    }

    @Override
    public void updateCoursePlayTime(Course course) {

    }

    @Override
    public void updateCourseIsavaliableById(Course course) {

    }

    @Override
    public void updateCourseIsavaliableBatch(Map map) {

    }

    @Override
    public void updateCourselessionnum(Course course) {

    }

    @Override
    public Long getCourselessionnum(Long id) {
        return null;
    }

    @Override
    public CourseVo getCourseInfoByCourseId(Long id) {
        return null;
    }

    @Override
    public boolean isPlay(Long userId, CourseVo courseDto) {
        return false;
    }
}
