package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.app.dao.website.AppWebsiteCourseDetailDao;
import io.wangxiao.edu.app.entity.website.AppWebsiteCourseDetailDTO;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.constants.enums.SellType;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.dao.coupon.CouponLimitDao;
import io.wangxiao.edu.home.dao.course.*;
import io.wangxiao.edu.home.dao.suggest.SugSuggestDao;
import io.wangxiao.edu.home.dao.user.UserGroupDao;
import io.wangxiao.edu.home.dao.user.UserGroupMiddleDao;
import io.wangxiao.edu.home.dao.user.UserIntegralGiftDao;
import io.wangxiao.edu.home.dao.website.WebsiteCourseDetailDao;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.entity.order.TrxorderDetail;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.entity.website.WebsiteCourseDetailDTO;
import io.wangxiao.edu.home.service.course.CourseReserveRecordService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Course管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseTeacherDao courseTeacherDao;
    private CacheKit cacheKit = CacheKit.getInstance();
    @Autowired
    private CourseSubjectDao courseSubjectDao;
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
    @Autowired
    private UserGroupDao userGroupDao; // 学员组dao层
    @Autowired
    private UserGroupMiddleDao userGroupMiddleDao;
    @Autowired
    private CouponLimitDao couponLimitDao;
    @Autowired
    private CourseMemberDao courseMemberDao;
    @Autowired
    private UserIntegralGiftDao userIntegralGiftDao;
    @Autowired
    private SugSuggestDao sugSuggestDao;
    @Autowired
    private AppWebsiteCourseDetailDao appWebsiteCourseDetailDao;
    @Autowired
    private CourseReserveRecordDao courseReserveRecordDao;
    @Autowired
    private CourseNoteDao courseNoteDao;
    @Autowired
    private CourseAssessDao courseAssessDao;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private CourseReserveRecordService courseReserveRecordService;

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
        courseSubjectDao.addCourseSubject(courseSubject);
        // 删除该课程下全部的教师
        courseTeacherDao.deleteCourseTeacherByCourseId(course.getId());//这句有修改
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
     * @param ids 要删除的id
     */
    public void deleteCourseById(String ids) {

        // 删除该课程关联的专业
        CourseSubject courseSubject = new CourseSubject();
        // 删除包相关的课程
        CoursePackage coursePackage = new CoursePackage();
        //删除课程推荐模块信息
        WebsiteCourseDetailDTO websiteCourseDetailDTO = new WebsiteCourseDetailDTO();
        // 删除app课程推荐表
        AppWebsiteCourseDetailDTO appWebsiteCourseDetailDTO = new AppWebsiteCourseDetailDTO();
        String[] split = ids.replaceAll(" ", "").split(",");//吧获取的ids截出来
        for (String string : split) {
            System.out.println(string + "id号码为");
            Long id = new Long(string);
            courseDao.deleteCourseById(id);
            // 删除该课程下全部的教师
            courseTeacherDao.deleteCourseTeacherByCourseId(id);
            courseSubject.setCourseId(id);
            courseSubjectDao.deleteCourseSubject(courseSubject);
            coursePackage.setMainCourseId(id);
            coursePackageDao.delCoursePackage(coursePackage);
            websiteCourseDetailDTO.setCourseId(id);
            websiteCourseDetailDao.deleteWebsiteCourseDetail(websiteCourseDetailDTO);
            appWebsiteCourseDetailDTO.setCourseId(id);
            appWebsiteCourseDetailDao.deleteWebsiteCourseDetail(appWebsiteCourseDetailDTO);
            // 删除小组课程中间表
            websiteCourseDetailDao.deleteWebsiteCourseDetail(id);
            // 删除课程收藏表
            courseFavoritesDao.deleteCourseFavoritesByCourseId(id);
            // 删除优惠券课程中间表
            couponLimitDao.deleteCouponLimitByCourseId(id);
            // 删除会员课程中间表
            courseMemberDao.delCourseMember(id);
            // 删除礼品表
            userIntegralGiftDao.deleteUserIntegralGiftByCourseId(id);
            // 删除课程节点表
            courseKpointDao.deleteCourseKpointByCourseId(id);
            // 删除建议表
            sugSuggestDao.deleteSugSuggestByCourseId(id);
            // 删除课程记录表
            courseProfileDao.deleteCourseProfileByCourseId(id);
            // 删除直播审核表
            courseReserveRecordDao.delteCourseReservedByCourseId(id);
            // 删除课程笔记表
            courseNoteDao.deleteCourseNoteByCourseId(id);
            // 删除课程评论表
            courseAssessDao.deleteCourseAssessByCourseId(id);
        }
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
        if (ObjectUtils.isNotNull(course.getSubjectId())) {
            courseSubject.setSubjectId(course.getSubjectId());
            courseSubjectDao.deleteCourseSubject(courseSubject);
            courseSubjectDao.addCourseSubject(courseSubject);
        }
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

        // 删除课程岗位
        userGroupMiddleDao.deleteCourseGroupMiddle(course.getId());

        if (StringUtils.isNotEmpty(course.getGroupIds())) {
            // 添加岗位课程
            userGroupMiddleDao.batchGroupMiddleCourse(course.getId(), course.getGroupIds());
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
        List<CourseDto> courseDtos = courseDao.getCourseListByCourseIds(ids, null, 0);
        if (ObjectUtils.isNull(courseDtos)) {
            return null;
        }
        CourseDto courseDto = courseDtos.get(0);

        // 获取课程专业id
        CourseSubject courseSubject = new CourseSubject();
        courseSubject.setCourseId(courseDto.getId());
        List<CourseSubject> courseSubjectList = courseSubjectDao.getCourseSubjectList(courseSubject);
        if (ObjectUtils.isNotNull(courseSubjectList)) {
            courseDto.setSubjectId(courseSubjectList.get(0).getSubjectId());
        }

        // 获取讲师的list
        List<Long> list = new ArrayList<Long>();
        list.add(id);
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            courseDto.setTeacherList(map.get(courseDto.getId()));
        }

        // 会员功能是否开启
        Map<String, Object> saleMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
        Map<String, Object> sale = (Map<String, Object>) saleMap.get(WebSiteProfileType.sale.toString());
        if (sale.get("verifyMember").equals("ON")) {//单课购买功能关闭
            // 课程的会员信息
            List<MemberType> memberTypes = memberTypeService.getMemberTypesBycourse(id);
            courseDto.setMemberTypes(memberTypes);

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
        // 判断课程列表不为空
        if (ObjectUtils.isNull(courseDtos)) {
            return null;
        }
        // 课程Id集合
        List<Long> list = new ArrayList<Long>();
        for (CourseDto courseDto : courseDtos) {
            list.add(courseDto.getId());
            // 如果是查询套餐
            if (SellType.PACKAGE.toString().equalsIgnoreCase(course.getSellType())) {
                int courseCount = coursePackageDao.getCoursePackageCount(courseDto.getId());
                courseDto.setLessionnum(new Long(courseCount));
            }
        }
        //查询每个课程包含的老师
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            for (CourseDto courseDto : courseDtos) {
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
    public List<CourseDto> getPublicCourseListPage(QueryCourse course, PageEntity page) {

        List<CourseDto> courseDtos = courseDao.getPublicCourseListPage(course, page);
        //获得每个课程的老师
        if (ObjectUtils.isNull(courseDtos)) {
            return null;
        }

        // 获取讲师的list
        List<Long> list = new ArrayList<Long>();
        for (CourseDto courseDto : courseDtos) {
            list.add(courseDto.getId());
        }
        //查询每个课程包含的老师
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            for (CourseDto courseDto : courseDtos) {
                courseDto.setTeacherList(map.get(courseDto.getId()));
            }

        }
        return courseDtos;
    }


    /**
     * 获取内部课程
     *
     * @param userId
     * @return
     */
    public List<CourseDto> getUserInnerCourseList(Long userId, String sellType) {
        List<Long> alist = new ArrayList<Long>();

        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(userId);
        // 学员组
        List<UserGroup> userGroupList = userGroupDao.getUserGroupList(userGroup);
        if (ObjectUtils.isNotNull(userGroupList)) {
            List<Long> ids = new ArrayList<Long>();
            List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
            for (UserGroup _userGroup : userGroupList) {
                alist.add(_userGroup.getId());
            }
            //查询多个组下包含的课程
            List<CourseDto> courseDtos = courseDao.getInnerCourseList(alist);
            for (CourseDto courseDto : courseDtos) {
                if (sellType.equals("ALL")) {
                    courseDtoList.add(courseDto);
                    ids.add(courseDto.getId());
                } else if (courseDto.getSellType().equals(sellType)) {
                    courseDtoList.add(courseDto);
                    ids.add(courseDto.getId());
                }
            }
            Map<Long, List<Teacher>> teachermaps = courseTeacherDao.getCourseTeacherListByCourse(ids);
            for (CourseDto dto : courseDtoList)
                if (ObjectUtils.isNotNull(teachermaps)) {
                    dto.setTeacherList(teachermaps.get(dto.getId()));
                }
            return courseDtoList;
        }
        return null;
    }


    @Override
    public boolean checkCourseIsInner(Long userId, Long courseId) {
        List<Long> ids = new ArrayList<Long>();
        if (userId == 0) {
            return false;
        }
        //查询该用户所属的小组下的包含的课程
        List<CourseDto> courseList = this.getUserInnerCourseList(userId, SellType.ALL.toString());
        if (ObjectUtils.isNotNull(courseList)) {
            for (CourseDto course : courseList) {
                if (course.getId().longValue() == courseId.longValue()) {
                    return true;
                }
                if (course.getSellType().equals(SellType.PACKAGE.toString())) {
                    ids.add(course.getId());
                }
            }
        }
        if (ObjectUtils.isNotNull(ids)) {
            // 再查询小组中套餐下包含的课程
            List<CourseDto> courseDtos = getCourseListPackageForIsPayOK(ids);
            if (ObjectUtils.isNotNull(courseDtos)) {
                for (CourseDto courseDto2 : courseDtos) {
                    if (courseDto2.getId().longValue() == courseId.longValue()) {
                        return true;
                    }
                }
            }
        }
        return false;
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
        List<CourseDto> courseList = (List<CourseDto>) cacheKit.get(MemConstans.RECOMMEND_COURSE);
        if (ObjectUtils.isNull(courseList)) {
            courseList = courseDao.getCourseListByHomePage(recommendId);// 查询db
            if (ObjectUtils.isNotNull(courseList)) {
                cacheKit.set(MemConstans.RECOMMEND_COURSE, courseList, MemConstans.RECOMMEND_COURSE_TIME);// 存缓存一小时
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
        List<CourseDto> courseDtos = (List<CourseDto>) cacheKit.get(MemConstans.COURSE_SAME_SUBJECT + courseId);
        if (ObjectUtils.isNull(courseDtos)) {
            courseDtos = courseDao.getSubjectCourseList(subjectId, courseId, num, null);
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
            cacheKit.set(MemConstans.COURSE_SAME_SUBJECT + courseId, courseDtos, MemConstans.COURSE_SAME_SUBJECT_TIME);
        }
        return courseDtos;
    }

    public List<CourseDto> getIndexCourseList(Long subjectId) {
        List<CourseDto> courseDtos = courseDao.getSubjectCourseList(subjectId, null, 4, "NOTINNER");
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
        List<CourseDto> courseListPackage = courseDao.getCourseListPackage(ids);

        if (ObjectUtils.isNull(courseListPackage)) {
            return null;
        }
        // 获取讲师的list
        List<Long> list = new ArrayList<Long>();
        for (CourseDto courseDto : courseListPackage) {
            list.add(courseDto.getId());
        }
        //查询每个课程包含的老师
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(list);
        if (ObjectUtils.isNotNull(map)) {
            for (CourseDto courseDto : courseListPackage) {
                courseDto.setTeacherList(map.get(courseDto.getId()));
            }
        }
        return courseListPackage;
    }

    /**
     * 查询套餐的课程,判断权限用
     *
     * @param ids 查询条件
     * @return List<Course>
     */
    public List<CourseDto> getCourseListPackageForIsPayOK(List<Long> ids) {
        List<CourseDto> courseListPackage = courseDao.getCourseListPackage(ids);
        if (ObjectUtils.isNull(courseListPackage)) {
            return null;
        }
        return courseListPackage;
    }

    /**
     * 获取用户购买过的课程，未过期的
     *
     * @param userId
     * @return
     */
    public List<CourseDto> getUserBuyCourseList(Long userId, String sellWay) {
        List<TrxorderDetail> details = trxorderDetailService.getTrxorderDetailListBuy(userId);
        if (ObjectUtils.isNotNull(details)) {
            // 查询课程信息
            List<Long> ids = new ArrayList<Long>();
            for (TrxorderDetail trxorderDetail : details) {
                ids.add(trxorderDetail.getCourseId());
            }

            //查询课程信息
            List<CourseDto> courseDtos = courseDao.getCourseListByCourseIdsAndUser(ids, userId, "NOLIVE");

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
                                cacheKit.remove(MemConstans.USER_CANLOOK + "_" + trxorderDetail.getCourseId() + "_" + userId);
                            }
                        }
                    }
                    if (dto.getSellType().equals("PACKAGE")) {
                        List<Long> ids1 = new ArrayList<>();
                        ids1.add(dto.getId());
                        if (ids1.size() > 0) {
                            List<CourseDto> list = courseDao.getCourseListPackage(ids1);
                            //查出课程包所有的视频节点
                            if (list != null && list.size() > 0) {
                                Long num = courseKpointDao.getCourseKpointNumByCourseList(list);
                                dto.setKpointNum(num);
                            }
                            Long historyNum = 0L;
                            if (list != null && list.size() > 0) {
                                List<CourseDto> _list = new ArrayList<>();
                                _list.add(dto);
                                historyNum = courseStudyhistoryDao.getCourseStudyhistoryCount(_list, userId);
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
    public List<CourseDto> getUserBuyLiveCourseList(Long userId, PageEntity page) {
        List<TrxorderDetail> details = trxorderDetailService.getTrxorderDetailListBuy(userId);
        if (ObjectUtils.isNotNull(details)) {
            // 查询课程信息
            List<Long> ids = new ArrayList<Long>();
            for (TrxorderDetail trxorderDetail : details) {
                ids.add(trxorderDetail.getCourseId());
            }

            //查询课程信息
            List<CourseDto> courseDtos = courseDao.getCourseListByCourseIds(ids, SellType.LIVE.toString(), 0);
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
                                cacheKit.remove(MemConstans.USER_CANLOOK + "_" + trxorderDetail.getCourseId() + "_" + userId);
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
    public List<CourseDto> getFreeCourseList(Long userId, Long num) {
        cacheKit.remove(MemConstans.COURE_FREE);
        @SuppressWarnings("unchecked")
        List<CourseDto> list = (List<CourseDto>) cacheKit.get(MemConstans.COURE_FREE);
        if (ObjectUtils.isNotNull(list)) {
            return list;
        }
        list = courseDao.getFreeCourseList(userId, num);
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
            cacheKit.set(MemConstans.COURE_FREE, list, MemConstans.COURE_FREE_TIME);
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
     *
     * @param courseId
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

    /**
     * 获取内部课程
     *
     * @param userId
     * @return
     */
    public List<UserGroup> getUserInnerCourseListByGroup(Long userId) {
        List<UserGroup> userGroupList = userGroupDao.getCourseDtoByGroup(userId);
        return userGroupList;
    }


    @Override
    public List<CourseDto> getUserCourseList(QueryCourse queryCourse, PageEntity page) {
        // 数据库获取list
        List<CourseDto> courseListPage = this.courseDao.getUserCourseList(queryCourse, page);
        // 要返回的list
        List<CourseDto> courseList = new ArrayList<>();
        if (ObjectUtils.isNotNull(courseListPage)) {
            for (CourseDto course : courseListPage) {
                if (SellType.COURSE.toString().equals(course.getSellType())) {
                    if (ObjectUtils.isNotNull(course.getId())) {
                        List<CourseDto> list = new ArrayList<>();
                        list.add(course);
                        Long courseStudyhistoryCount = courseStudyhistoryDao.getCourseStudyhistoryCount(list, queryCourse.getUserId());
                        course.setStudyhistoryNum(courseStudyhistoryCount);
                        Long courseLessionNum = courseKpointDao.getCourseKpointCount(course.getId());
                        course.setLessionnum(courseLessionNum);
                    }
                } else if (SellType.PACKAGE.toString().equals(course.getSellType())) {
                    CoursePackage coursePackage = new CoursePackage();
                    coursePackage.setMainCourseId(course.getId());
                    List<CoursePackage> coursePackageList = coursePackageDao.getCoursePackageList(coursePackage);
                    if (ObjectUtils.isNotNull(coursePackageList)) {
                        Long courseStudyhistoryCount = 0L;
                        Long courseLessionNum = 0l;
                        for (CoursePackage _coursePackage : coursePackageList) {
                            List<CourseDto> list = new ArrayList<>();
                            CourseDto _course = getCourseInfoById(_coursePackage.getCourseId());
                            if (ObjectUtils.isNotNull(_course)) {
                                list.add(_course);
                                Long historyCount = courseStudyhistoryDao.getCourseStudyhistoryCount(list, queryCourse.getUserId());
                                courseStudyhistoryCount += ObjectUtils.isNotNull(historyCount) ? historyCount : 0L;
                                Long KpointCount = courseKpointDao.getCourseKpointCount(_course.getId());
                                courseLessionNum += ObjectUtils.isNotNull(KpointCount) ? historyCount : 0L;
                            }
                        }
                        course.setStudyhistoryNum(courseStudyhistoryCount);
                        course.setLessionnum(courseLessionNum);
                    }
                }
                courseList.add(course);
            }
        }
        return courseList;
    }

    @Override
    public void updateCoursePlayTime(Course course) {
        this.courseDao.updateCoursePlayTime(course);
    }

    @Override
    public void updateCourseIsavaliableById(Course course) {
        courseDao.updateCourseIsavaliableById(course);
    }

    @Override
    public void updateCourseIsavaliableBatch(Map map) {
        courseDao.updateCourseIsavaliableBatch(map);
    }

    @Override
    public void updateCourselessionnum(Course course) {
        courseDao.updateCourselessionnum(course);

    }

    @Override
    public Long getCourselessionnum(Long id) {
        return courseDao.getCourselessionnum(id);

    }

    @Override
    public CourseDto getCourseInfoByCourseId(Long id) {
        //查询课程信息
        CourseDto courseDto = courseDao.getCourseInfoByCourseId(id);
        if (ObjectUtils.isNull(courseDto)) {
            return null;
        }
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
     * 判断课程是否有观看权限
     *
     * @param userId    用户id
     * @param courseDto 课程
     * @return
     */
    @Override
    public boolean isPlay(Long userId, CourseDto courseDto) {
        if (ObjectUtils.isNull(courseDto)) {
            return false;
        } else {

            // 判断课程是否免费
            if (!"INNER".equalsIgnoreCase(courseDto.getCompanySellType()) && (courseDto.getIsPay() == 0 || courseDto.getCurrentprice().compareTo(new BigDecimal(0)) <= 0)) {
                if (SellType.LIVE.toString().equals(courseDto.getSellType())) {
                    if (ObjectUtils.isNull(userId)) {//未登陆
                        return false;
                    }
                    // 是否预约直播
                    CourseReserveRecord reserveRecord = new CourseReserveRecord();
                    reserveRecord.setUserId(userId);
                    reserveRecord.setCourseId(courseDto.getId());
                    CourseReserveRecord _reserveRecord = courseReserveRecordService.queryCourseReserveRecord(reserveRecord);
                    if (ObjectUtils.isNull(_reserveRecord) || _reserveRecord.getCheck() == 0) {
                        return false;
                    }
                }
                return true;
            } else {
                if (ObjectUtils.isNull(userId)) {//未登陆
                    return false;
                }
                return isCoursePlayOK(userId, courseDto);

//                String key=MemConstans.USER_CANLOOK + "_" + userId + "_" + courseDto.getId();
//                Object o = cacheKit.get(key);
//                if(ObjectUtils.isNotNull(o)){
//                    return (boolean)o;
//                }
//                boolean isPlayOk = isCoursePlayOK(userId,courseDto);
//                cacheKit.set(key, isPlayOk, MemConstans.USER_CANLOOK_TIME);
//                return isPlayOk;
            }
        }
    }

    /**
     * 判断课程是否有观看权限
     *
     * @param userId    用户id
     * @param courseDto 课程
     * @return
     */
    boolean isCoursePlayOK(Long userId, CourseDto courseDto) {
        if (ObjectUtils.isNotNull(userId) && ObjectUtils.isNotNull(courseDto)) {
            Long courseId = courseDto.getId();
            // 查询购买过的课程
            List<TrxorderDetail> trxorderDetailList = trxorderDetailService.getTrxorderDetailListBuy(userId);
            if (ObjectUtils.isNotNull(trxorderDetailList)) {
                List<Long> ids = new ArrayList<Long>();
                for (TrxorderDetail detail : trxorderDetailList) {
                    ids.add(detail.getCourseId());
                    if (detail.getCourseId().longValue() == courseId.longValue()) {
                        return true;
                    }
                }
                // 再查询购买的课程中是否是套餐。查询套餐下是否包含该课程
                List<CourseDto> courseDtos = getCourseListPackageForIsPayOK(ids);
                if (ObjectUtils.isNotNull(courseDtos)) {
                    for (CourseDto courseDto2 : courseDtos) {
                        if (courseDto2.getId().longValue() == courseId.longValue()) {
                            return true;
                        }
                    }
                }
            }
            // 小组权限
            boolean isPlay = checkCourseIsInner(userId, courseId);
            return isPlay || memberRecordService.checkUserMember(userId, courseId);
// 会员观看权限
        }
        return false;
    }

}