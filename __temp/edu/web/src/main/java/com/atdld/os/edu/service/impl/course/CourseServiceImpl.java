package com.atdld.os.edu.service.impl.course;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.constants.enums.SellType;
import com.atdld.os.edu.dao.course.*;
import com.atdld.os.edu.dao.website.WebsiteCourseDetailDao;
import com.atdld.os.edu.entity.course.*;
import com.atdld.os.edu.entity.order.TrxorderDetail;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.entity.website.WebsiteCourseDetailDTO;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.CourseSubjectService;
import com.atdld.os.edu.service.order.TrxorderDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseTeacherDao courseTeacherDao;
    private MemCache memcache = MemCache.getInstance();
    @Autowired
    private CourseSubjectService courseSubjectService;
    @Autowired
    private CoursePackageDao coursePackageDao;
    @Autowired
    private TrxorderDetailService trxorderDetailService;
    @Autowired
    private CourseFavoritesDao courseFavoritesDao;
    @Autowired
    private WebsiteCourseDetailDao websiteCourseDetailDao;
    @Autowired
    private CourseProfileDao courseProfileDao;
    @Autowired
    private CourseKpointDao courseKpointDao;
    @Autowired
    private CourseStudyhistoryDao courseStudyhistoryDao;


    /**
     * 添加Course
     *
     * @param course 要添加的Course
     * @return id
     */
    public java.lang.Long addCourse(Course course) {
        course.setAddtime(new Date());
        course.setUpdateTime(new Date());
        Long num = courseDao.addCourse(course);
        // 添加课程关联专业
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourseId(course.getId());
        courseSubject.setSubjectId(course.getSubjectId());
        courseSubjectService.addCourseSubject(courseSubject);
        // 删除该课程下全部的教师
        courseTeacherDao.deleteCourseTeacherByCourseId(course.getId());
        // 批量添加课程和教师的关联
        if (StringUtils.isNotEmpty(course.getTeacherIds())) {
            List<CourseTeacher> courseTeacherList = new ArrayList<CourseTeacher>();
            String[] teaherIds = course.getTeacherIds().split(",");
            for (String id : teaherIds) {
                CourseTeacher courseTeacher = new CourseTeacher();
                courseTeacher.setCourseId(course.getId());
                courseTeacher.setTeacherId(Long.valueOf(id));
                courseTeacherList.add(courseTeacher);
            }
            courseTeacherDao.addCourseTeacherBatch(courseTeacherList);
        }
        //添加课程配置数据
        CourseProfile courseProfile = new CourseProfile();
        courseProfile.setCourseId(course.getId());
        courseProfileDao.addCourseProfile(courseProfile);
        return num;
    }

    /**
     * 根据id删除一个Course
     *
     * @param id 要删除的id
     */
    public void deleteCourseById(Long id) {
        courseDao.deleteCourseById(id);
        // 删除该课程下全部的教师
        courseTeacherDao.deleteCourseTeacherByCourseId(id);
        // 删除该课程关联的专业
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourseId(id);
        courseSubjectService.deleteCourseSubject(courseSubject);
        // 删除包相关的课程
        CoursePackage coursePackage = new CoursePackage();
        coursePackage.setMainCourseId(id);
        coursePackageDao.delCoursePackage(coursePackage);
        //删除课程推荐模块信息
        WebsiteCourseDetailDTO websiteCourseDetailDTO = new WebsiteCourseDetailDTO();
        websiteCourseDetailDTO.setCourseId(id);
        websiteCourseDetailDao.deleteWebsiteCourseDetail(websiteCourseDetailDTO);
    }

    /**
     * 修改Course
     *
     * @param course 要修改的Course
     */
    public void updateCourse(Course course) {
        course.setUpdateTime(new Date());
        courseDao.updateCourse(course);

        // 添加课程关联专业
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourseId(course.getId());
        courseSubject.setSubjectId(course.getSubjectId());
        courseSubjectService.deleteCourseSubject(courseSubject);
        courseSubjectService.addCourseSubject(courseSubject);

        // 删除该课程下全部的教师
        courseTeacherDao.deleteCourseTeacherByCourseId(course.getId());
        // 批量添加课程和教师的关联
        if (StringUtils.isNotEmpty(course.getTeacherIds())) {
            List<CourseTeacher> courseTeacherList = new ArrayList<CourseTeacher>();
            String[] teaherIds = course.getTeacherIds().split(",");
            for (String id : teaherIds) {
                CourseTeacher courseTeacher = new CourseTeacher();
                courseTeacher.setCourseId(course.getId());
                courseTeacher.setTeacherId(Long.valueOf(id));
                courseTeacherList.add(courseTeacher);
            }
            courseTeacherDao.addCourseTeacherBatch(courseTeacherList);
        }
    }

    /**
     * 根据id获取单个Course对象
     *
     * @param id 要查询的id
     * @return Course
     */
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    /**
     * 根据id获取单个Course对象里面包含该课程的教师
     *
     * @param id 要查询的id
     * @return Course
     */
    public CourseDto getCourseInfoById(Long id) {
        List<Long> ids = new ArrayList<Long>();
        ids.add(id);
        //查询课程信息
        List<CourseDto> courseDtos = courseDao.getCourseListByCourseIds(ids,null);
        if (ObjectUtils.isNull(courseDtos)) {
            return null;
        }
        CourseDto courseDto = courseDtos.get(0);
        // 获取讲师的list
        List<Long> list = new ArrayList<Long>();
        list.add(id);
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            courseDto.setTeacherList(map.get(courseDto.getId()));
        }
        return courseDto;
    }

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<Course> getCourseList(Course course) {
        return courseDao.getCourseList(course);
    }
    
    @Override
    public List<Course> getCourseListByBro() {
    	// TODO Auto-generated method stub
    	return courseDao.getCourseListByBro();
    }
    
    

    /**
     * 根据条件获取Course列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPage(QueryCourse course, PageEntity page) {

        List<CourseDto> courseDtos = courseDao.getCourseListPage(course, page);
        //获得每个课程的老师
        if (ObjectUtils.isNull(courseDtos)) {
            return null;
        }

        // 获取讲师的list
        List<Long> list = new ArrayList<Long>();
        for(CourseDto courseDto:courseDtos){
            list.add(courseDto.getId());
        }
        //查询每个课程包含的老师
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            for(CourseDto courseDto:courseDtos){
                courseDto.setTeacherList(map.get(courseDto.getId()));
            }

        }
        return courseDtos;
    }
    
    /**
     * 根据条件获取公开课列表
     *
     * @param course 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getPublicCourseListPage(QueryCourse course, PageEntity page){

        List<CourseDto> courseDtos = courseDao.getPublicCourseListPage(course, page);
        //获得每个课程的老师
        if (ObjectUtils.isNull(courseDtos)) {
            return null;
        }

        // 获取讲师的list
        List<Long> list = new ArrayList<Long>();
        for(CourseDto courseDto:courseDtos){
            list.add(courseDto.getId());
        }
        //查询每个课程包含的老师
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            for(CourseDto courseDto:courseDtos){
                courseDto.setTeacherList(map.get(courseDto.getId()));
            }

        }
        return courseDtos;
    }

    /**
     * 首页获取推荐的课程 封装为map key:index_course_+recommendId ,List<Course>
     *
     * @param recommendId
     * @throws Exception
     * @returnmap key:index_course_1 ,List<Course>
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<CourseDto>> getCourseListByHomePage(Long recommendId) throws Exception {// 首页课程存缓存
        Map<String, List<CourseDto>> map = new HashMap<String, List<CourseDto>>();


        List<CourseDto> courseList = (List<CourseDto>) memcache.get(MemConstans.RECOMMEND_COURSE);
        if (ObjectUtils.isNull(courseList)) {
            courseList = courseDao.getCourseListByHomePage(recommendId);// 查询db
            if (ObjectUtils.isNotNull(courseList)) {
                memcache.set(MemConstans.RECOMMEND_COURSE, courseList, MemConstans.RECOMMEND_COURSE_TIME);// 存缓存一小时
            }
        }
        if (ObjectUtils.isNotNull(courseList)) {
            List<CourseDto> courseHotList = new ArrayList<CourseDto>();
            Long recommend = courseList.get(0).getRecommendId();
            for (CourseDto course : courseList) {
                if (recommend.longValue() != course.getRecommendId().longValue()) {
                    map.put("index_course_" + recommend, courseHotList);
                    courseHotList = new ArrayList<CourseDto>();
                    courseHotList.add(course);
                    recommend = course.getRecommendId();
                } else {
                    courseHotList.add(course);
                }
            }
            if (ObjectUtils.isNotNull(courseHotList)) {
                map.put("index_course_" + recommend, courseHotList);
            }
        }
        return map;
    }

    /**
     * 获得专业下的课程
     *
     * @param subjectId 专业id
     * @param num       　返回条数
     * @return List<Course>
     * @param　courseId　传此参数时，查询改课程相同专业下的课程
     */
    public List<CourseDto> getSubjectCourseList(Long subjectId, Long courseId, int num) throws Exception {
        List<CourseDto> courseDtos = courseDao.getSubjectCourseList(subjectId, courseId, num);
        if (ObjectUtils.isNotNull(courseDtos)) {
            List<Long> list = new ArrayList<Long>();
            for (CourseDto courseDto : courseDtos) {
                list.add(courseDto.getId());
            }
            // 获取讲师的list
            Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
            // 将讲师的list放到旧的list中
            for (CourseDto courseDto : courseDtos) {
                courseDto.setTeacherList(map.get(courseDto.getId()));
            }
        }
        return courseDtos;
    }

    /**
     * 获得项目专业限制的所有课程
     *
     * @param subjectId
     * @param courseIds
     * @return
     */
    public List<Course> getCouponSubjectCourse(Long subjectId, String courseIds) {
        return courseDao.getCouponSubjectCourse(subjectId, courseIds);
    }

    /**
     * 查询套餐的具体课程
     *
     * @param ids 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPackage(List<Long> ids) {
        return courseDao.getCourseListPackage(ids);
    }
    /**
     * 获取用户购买过的课程，未过期的
     *
     * @param userId
     * @return
     */
    public List<CourseDto> getUserBuyCourseList(Long userId) {
        List<TrxorderDetail> details = trxorderDetailService.getTrxorderDetailListBuy(userId);
        if (ObjectUtils.isNotNull(details)) {
            // 查询课程信息
            List<Long> ids = new ArrayList<Long>();
            for (TrxorderDetail trxorderDetail : details) {
                ids.add(trxorderDetail.getCourseId());
            }

            //查询课程信息
            List<CourseDto> courseDtos = courseDao.getCourseListByCourseIdsAndUser(ids,userId,"NOLIVE");

            // 获取讲师的list
            Map<Long, List<Teacher>> teachermaps = courseTeacherDao.getCourseTeacherListByCourse(ids);
            // 计算课程的剩余天数
            if (ObjectUtils.isNotNull(courseDtos)) {
                Date date = new Date();// 今天
                String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
                for (CourseDto dto : courseDtos) {
                    if (ObjectUtils.isNotNull(teachermaps)) {//设置讲师list
                        dto.setTeacherList(teachermaps.get(dto.getId()));
                    }
                    //设置有效期
                    for (TrxorderDetail trxorderDetail : details) {
                        if (dto.getId().longValue() == trxorderDetail.getCourseId().longValue()) {
                            String authStr = DateUtils.formatDate(trxorderDetail.getAuthTime(), "yyyy-MM-dd");
                            dto.setAuthTime(trxorderDetail.getAuthTime());
                            // 计算差多少天
                            dto.setRemainDays(Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1);
                            if ((Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1) <= 0) {
                                memcache.remove(MemConstans.USER_CANLOOK + "_" + trxorderDetail.getCourseId() + "_" + userId);
                            }
                        }
                    }
                    if(dto.getSellType().equals("PACKAGE")){
                    	List<Long> ids1 = new ArrayList<>();
                    	ids1.add(dto.getId());
                    	if(ids1!=null&&ids1.size()>0){
                    		List<CourseDto> list = courseDao.getCourseListPackage(ids1);
                    		//查出课程包所有的视频节点
                    		if(list!=null && list.size()>0){
                    		Long num = courseKpointDao.getCourseKpointNumByCourseList(list);
                     		dto.setKpointNum(num);
                    		}
                    		Long historyNum =0L;
                    		if(list!=null && list.size()>0){
                    			historyNum = courseStudyhistoryDao.getCourseStudyhistoryCount(list,userId);
                    		}
                    		dto.setStudyhistoryNum(historyNum);
                    	}
                    }
                }
            }
            return courseDtos;
        }
        return null;
    }
    /**
     * 获取用户购买过的直播课程，未过期的
     */
    public List<CourseDto> getUserBuyLiveCourseList(Long userId,PageEntity page){
        List<TrxorderDetail> details = trxorderDetailService.getTrxorderDetailListBuy(userId);
        if (ObjectUtils.isNotNull(details)) {
            // 查询课程信息
            List<Long> ids = new ArrayList<Long>();
            for (TrxorderDetail trxorderDetail : details) {
                ids.add(trxorderDetail.getCourseId());
            }

            //查询课程信息
            List<CourseDto> courseDtos = courseDao.getCourseListByCourseIds(ids,SellType.LIVE.toString());
            // 获取讲师的list
            Map<Long, List<Teacher>> teachermaps = courseTeacherDao.getCourseTeacherListByCourse(ids);
            // 计算课程的剩余天数
            if (ObjectUtils.isNotNull(courseDtos)) {
                Date date = new Date();// 今天
                String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
                for (CourseDto dto : courseDtos) {
                    if (ObjectUtils.isNotNull(teachermaps)) {//设置讲师list
                        dto.setTeacherList(teachermaps.get(dto.getId()));
                    }
                    //设置有效期
                    for (TrxorderDetail trxorderDetail : details) {
                        if (dto.getId().longValue() == trxorderDetail.getCourseId().longValue()) {
                            String authStr = DateUtils.formatDate(trxorderDetail.getAuthTime(), "yyyy-MM-dd");
                            dto.setAuthTime(trxorderDetail.getAuthTime());
                            // 计算差多少天
                            dto.setRemainDays(Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1);
                            if ((Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1) <= 0) {
                                memcache.remove(MemConstans.USER_CANLOOK + "_" + trxorderDetail.getCourseId() + "_" + userId);
                            }
                        }
                    }
                }
            }
            return courseDtos;
        }
        return null;
    }

    //过滤直播的课程
    public List<CourseDto> filtrationLive(List<CourseDto> courseDtoList) {
        List<CourseDto> newCourseDtoList = new ArrayList<CourseDto>();
        if (ObjectUtils.isNotNull(courseDtoList)) {
            for (CourseDto courseDto : courseDtoList) {
                if (!SellType.LIVE.toString().equals(courseDto.getSellType())) {
                    newCourseDtoList.add(courseDto);
                }
            }
        }
        return newCourseDtoList;
    }

    /**
     * 获取免费课程
     *
     * @param num 条数
     * @return
     */
    public List<CourseDto> getFreeCourseList(Long userId,Long num) {
    	memcache.remove(MemConstans.COURE_FREE);
        @SuppressWarnings("unchecked")
        List<CourseDto> list = (List<CourseDto>) memcache.get(MemConstans.COURE_FREE);
        if (ObjectUtils.isNotNull(list)) {
            return list;
        }
        list = courseDao.getFreeCourseList(userId,num);
        if (ObjectUtils.isNotNull(list)) {
            List<Long> ids = new ArrayList<Long>();
            for (CourseDto courseDto : list) {
                ids.add(courseDto.getId());
            }
            // 获取讲师的list
            Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(ids);
            // 将讲师的list放到旧的list中
            for (CourseDto courseDto : list) {
                courseDto.setTeacherList(map.get(courseDto.getId()));
            }
            memcache.set(MemConstans.COURE_FREE, list, MemConstans.COURE_FREE_TIME);
        }

        return list;
    }

    /**
     * 删除收藏课程
     *
     * @return
     */
    @Override
    public void delFavouriteCourseByIds(String courseIds) {
        courseFavoritesDao.deleteCourseFavoritesById(courseIds);
    }

    @Override
    public List<Map<String, Object>> queryAppAllCourse(Map<String, Object> map,
                                                       PageEntity page) {
        return courseDao.queryAppAllCourse(map, page);
    }

	@Override
	public List<Course> queryCourseListByIds(String courseIds) {
		return courseDao.queryCourseListByIds(courseIds);
	}
	/**
    * 根据课程ID，查询用户列表信息
    * @return
    */
	public List<UserExpand> queryUserExpandListByCourseId(Long courseId) {
		return courseDao.queryUserExpandListByCourseId(courseId);
	}

	/**
     * 后台获取课程套餐下的详细课程列表
     *
     * @param course
     * @return
     */
	public List<Course> getCourseListPackageByCondition(Course course) {
		return courseDao.getCourseListPackageByCondition(course);
	}

	
}