package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.CourseProfileType;
import io.wangxiao.edu.home.constants.enums.SellType;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.service.article.ArticleService;
import io.wangxiao.edu.home.service.course.*;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    // 课程列表
    private static final String showCourseList = getViewPath("/course/courses-list");
    // 套餐列表
    private static final String showPackageList = getViewPath("/course/courses-package-list");
    // 直播列表
    private static final String showLiveList = getViewPath("/course/courses-live-list");
    // 课程详情
    private static final String couInfo = getViewPath("/course/course_info");
    // 直播课程详情
    private static final String couLiveInfo = getViewPath("/course/course_live_info");
    // 直播课程详情
    private static final String liveplay = getViewPath("/live/live_play");

    // 公开课详情
    private static final String showPublicCourseList = getViewPath("/course/publicCourses-list");
    // 岗位课程列表
    private static final String groupCourseList = getViewPath("/course/group_course_list");
    // 首页专业切换时ajax获取
    private static final String courses_ajax = getViewPath("/course/courses_ajax");
    private static final String uc_course = getViewPath("/ucenter/uc_course");
    private static final String uc_live = getViewPath("/ucenter/uc_live");
    private CacheKit cacheKit = CacheKit.getInstance();
    private String favouriteList = getViewPath("/ucenter/favourite_course_list");// 收藏列表
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private CourseProfileService courseProfileService;
    @Autowired
    UserService userService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private CourseFavoritesService courseFavoritesService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private CourseReserveRecordService courseReserveRecordService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    /**
     * 课程列表展示,搜索课程
     * /front/showCourselist 课程列表
     * /front/showPackagelist 套餐列表
     * /front/showLivelist 直播列表
     *
     * @return
     */
    @RequestMapping("/front/show{sellType}List")
    public ModelAndView showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                       @ModelAttribute("queryCourse") QueryCourse queryCourse, @PathVariable("sellType") String sellType) {
        ModelAndView modelAndView = new ModelAndView(showCourseList);
        try {
            if (StringUtils.isNotEmpty(queryCourse.getName())) {
                queryCourse.setName(queryCourse.getName().trim());
            }
            // 课程类型
            sellType = sellType.toUpperCase();
            queryCourse.setSellType(sellType);
            // 套餐
            if (SellType.PACKAGE.toString().equals(sellType)) {
                page.setPageSize(4);
                modelAndView.setViewName(showPackageList);
            }
            // 直播
            else if (SellType.LIVE.toString().equals(sellType)) {
                page.setPageSize(10);
                modelAndView.setViewName(showLiveList);
            }
            // 课程
            else {
                page.setPageSize(12);
            }
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
            // 只查询非内部课程
            queryCourse.setCompanySellType("NOINNER");
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            modelAndView.addObject("courseList", courseList);
            // 查询所有1级专业
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(0L);
            List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
            modelAndView.addObject("subjectList", subjectList);

            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
            modelAndView.addObject("teacherList", teacherList);

            // 如果选择了专业，查询条件专业,子专业
            if (ObjectUtils.isNotNull(queryCourse.getSubjectId())) {
                Subject subject = new Subject();
                subject.setSubjectId(queryCourse.getSubjectId());
                subject = subjectService.getSubjectBySubjectId(subject);
                if (ObjectUtils.isNotNull(subject.getParentId())) {
                    Subject _subject = new Subject();
                    _subject.setSubjectId(subject.getParentId());
                    _subject = subjectService.getSubjectBySubjectId(_subject);
                    subject.setSubjectName(_subject.getSubjectName() + ">" + subject.getSubjectName());
                }
                modelAndView.addObject("subject", subject);
                // 查询子专业
                List<Subject> sonSubjectList = null;
                if (subject.getParentId() != 0) {// 条件为二级专业
                    sonSubjectList = subjectService.getSubjectListByOne(subject.getParentId());
                } else {// 条件为一级专业
                    sonSubjectList = subjectService.getSubjectListByOne(subject.getSubjectId());
                }
                modelAndView.addObject("sonSubjectList", sonSubjectList);
            }
            // 如果选择了讲师，查询教师的信息
            if (ObjectUtils.isNotNull(queryCourse.getTeacherId())) {
                Teacher teacher = teacherService.getTeacherById(queryCourse.getTeacherId());
                modelAndView.addObject("teacher", teacher);
            }

            // 获取课程销售开关配置
            Map<String, Object> saleMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
            if (ObjectUtils.isNotNull(saleMap) && saleMap.containsKey("sale") && ObjectUtils.isNotNull(saleMap.get("sale"))) {
                Map<String, Object> verifyMemberMap = (Map<String, Object>) saleMap.get("sale");
                //会员制度是否开启
                if (verifyMemberMap.containsKey("verifyMember") && ObjectUtils.isNotNull(verifyMemberMap.get("verifyMember"))) {
                    String verifyMember = verifyMemberMap.get("verifyMember").toString().toUpperCase();
                    if ("ON".equals(verifyMember)) {
                        modelAndView.addObject("saleMap", saleMap);
                        // 查询会员类型
                        MemberType memberType = memberTypeService.getMemberTypeById(queryCourse.getMembertype());
                        modelAndView.addObject("memberType", memberType);
                        // 会员类型
                        List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
                        modelAndView.addObject("memberTypes", memberTypes);
                    }
                }
            }

            // 分页
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 课程公开课列表展示,搜索课程
     *
     * @return
     */
    @RequestMapping("/front/showPublicCourselist")
    public ModelAndView showPublicCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                             @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        ModelAndView modelAndView = new ModelAndView(showPublicCourseList);
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType("NOLIVE");// 非直播的课程
            // 搜索课程列表
            List<CourseDto> publicCourseList = courseService.getPublicCourseListPage(queryCourse, page);

            // 查询所有1级专业
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(0L);
            List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
            // 查询条件专业,子专业
            if (ObjectUtils.isNotNull(queryCourse.getSubjectId())) {
                Subject subject = new Subject();
                subject.setSubjectId(queryCourse.getSubjectId());
                subject = subjectService.getSubjectBySubjectId(subject);
                modelAndView.addObject("subject", subject);
                // 查询子专业
                List<Subject> sonSubjectList = null;
                if (subject.getParentId() != 0) {// 条件为二级专业
                    sonSubjectList = subjectService.getSubjectListByOne(subject.getParentId());
                } else {// 条件为一级专业
                    sonSubjectList = subjectService.getSubjectListByOne(subject.getSubjectId());
                }
                modelAndView.addObject("sonSubjectList", sonSubjectList);
            }
            // 查询教师
            Teacher teacher = teacherService.getTeacherById(queryCourse.getTeacherId());
            modelAndView.addObject("teacher", teacher);
            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
            modelAndView.addObject("publicCourseList", publicCourseList);
            modelAndView.addObject("page", page);
            modelAndView.addObject("course", queryCourse);
            modelAndView.addObject("teacherList", teacherList);
            modelAndView.addObject("subjectList", subjectList);
            // 查找售卖方式开关配置
            Map<String, Object> saleMap = websiteProfileService
                    .getWebsiteProfileByType(WebSiteProfileType.sale.toString());
            modelAndView.addObject("saleMap", saleMap);
            // 查询会员类型
            MemberType memberType = memberTypeService.getMemberTypeById(queryCourse.getMembertype());
            modelAndView.addObject("memberType", memberType);
            // 会员类型
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes", memberTypes);
            if (ObjectUtils.isNotNull(queryCourse.getTeacherId())) {
                modelAndView.addObject("teacher", teacherService.getTeacherById(queryCourse.getTeacherId()));
            }

        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 课程详情
     *
     * @return
     */
    @RequestMapping("/front/couinfo/{id}")
    public String couinfo(Model model, HttpServletRequest request, @PathVariable("id") Long id,
                          RedirectAttributes redirectAttributes) {
        try {
            // 获取登录用户ID
            Long userId = getLoginUserId(request);
            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(id);
            // 判断课程是否不存在
            if (ObjectUtils.isNull(course)) {
                redirectAttributes.addAttribute("msg", "对不起该课程已经下架或者删除");
                return "redirect:/front/success";
            }
            model.addAttribute("course", course);
            // 查询课程是否可以观看
            boolean isPlay = courseService.isPlay(userId, course);
            if (!isPlay && "INNER".equalsIgnoreCase(course.getCompanySellType())) {
                redirectAttributes.addAttribute("msg", "对不起,该课程为内部课程,您无权查看");
                return "redirect:/front/success";
            }
            model.addAttribute("isPlay", isPlay);
            // 查询学过此课程的用户
            List<UserExpand> userExpandList = courseService.queryUserExpandListByCourseId(id);
            model.addAttribute("userExpandList", userExpandList);
            // 套餐时，查询该套餐下包含的课程
            if (course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())) {
                List<Long> courseIds = new ArrayList<Long>();
                courseIds.add(course.getId());
                List<CourseDto> courseList = courseService.getCourseListPackage(courseIds);
                if (ObjectUtils.isNotNull(courseList)) {
                    model.addAttribute("coursePackageList", courseList);
                    model.addAttribute("coursePackage", courseList.get(0));
                }
            }
            // 是否已收藏
            if (userId != null && userId > 0) {
                boolean alreadyFavorite = courseFavoritesService.getCourseIfFavorite(id, userId);
                model.addAttribute("alreadyFavorite", alreadyFavorite);
            }
            // 同类课程推荐,加缓存
            List<CourseDto> courseList = courseService.getSubjectCourseList(course.getSubjectId(), course.getId(), 3);
            model.addAttribute("courseList", courseList);
            // 更新课程的浏览数量
            courseProfileService.updateCourseProfile(CourseProfileType.viewcount.toString(), course.getId(), 1L,
                    CourseProfile.ADD);
            model.addAttribute("msg", request.getParameter("msg"));
            // 是否是直播
            if (SellType.LIVE.toString().equals(course.getSellType())) {
                return couLiveInfo;
            }
        } catch (Exception e) {
            logger.error("CourseController.couinfo", e);
            return setExceptionRequest(request, e);
        }
        return couInfo;
    }

    /**
     * 获取课程的树JSON
     *
     * @return
     */
    @RequestMapping("/front/coursetree/{id}")
    @ResponseBody
    public Object getCourseTree(@PathVariable Long id) {
        Map<String, Object> json = null;
        CourseKpoint courseKpoint = new CourseKpoint();
        courseKpoint.setCourseId(id);
        List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
        json = this.getJsonMap(true, "", courseKpointList);
        return json;
    }

    /**
     * 首页根据专业查询课程 ajax请求
     *
     * @return
     */
    @RequestMapping("/front/ajax/course")
    public ModelAndView ajaxcourse(HttpServletRequest request, Model model, Long subjectId) {
        ModelAndView modelAndView = new ModelAndView(courses_ajax);
        try {
            // 课程列表
            List<CourseDto> courseList = courseService.getIndexCourseList(subjectId);
            model.addAttribute("courseList", courseList);
        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 我的课程
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/uc/course")
    public String mycourse(Model model, HttpServletRequest request) {
        try {
            // 查询免费课程
            //List<CourseDto> freecourses = courseService.getFreeCourseList(getLoginUserId(request), 6L);
            // 查询购买过的课程
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(getLoginUserId(request),
                    SellType.COURSE.toString());
            // 获得所有推荐课程
            //Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            model.addAttribute("buycourses", courseDtos);// 购买的课程
            //model.addAttribute("freecourses", freecourses);// 免费的课程 推荐6条
            //model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return uc_course;
    }

    /**
     * 我的直播
     */
    @RequestMapping("/uc/mylive")
    public String myLive(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            // 查询购买过的课程和直播
            List<CourseDto> courseDtos = courseService.getUserBuyLiveCourseList(getLoginUserId(request), page);
//	    // 获得所有推荐课程
//	    Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            model.addAttribute("buycourses", courseDtos);// 购买的课程
//	    model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
            model.addAttribute("page", page);// 分页
            // 讲师推荐未查询 js 获取 
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return uc_live;
    }

    /**
     * 课程详情 不管是课程套餐还是课程目录时都放到list(coursePackageList)中
     *
     * @return
     */
    @RequestMapping("/front/liveplay/{id}")
    public String liveplay(Model model, HttpServletRequest request, @PathVariable("id") Long id,
                           RedirectAttributes redirectAttributes) {
        try {

            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(id);
            // 查询用户信息
            List<UserExpand> userExpandList = courseService.queryUserExpandListByCourseId(id);
            if (ObjectUtils.isNull(course)) {
                redirectAttributes.addAttribute("msg", "对不起该课程已经下架或者删除");
                return "redirect:/front/success";
            }
            // 判断该课程是否可以观看
            boolean isok = false;
            // 判断是否购买课程且未到期
            boolean isok1 = trxorderService.checkCourseLook(id, getLoginUserId(request));
            boolean isok2 = false;
            if (isok1 != true) {
                isok2 = memberRecordService.checkUserMember(getLoginUserId(request), course.getId());
            }
            boolean isok3 = false;
            if (isok1 != true && isok2 != true) {
                isok3 = courseService.checkCourseIsInner(getLoginUserId(request), course.getId());
            }
            if (isok1 || isok2 || isok3) {// 已购买或已开通会员
                isok = true;
            }

            User user = userService.getUserById(getLoginUserId(request));
            model.addAttribute("course", course);
            model.addAttribute("isok", isok);
            model.addAttribute("userExpandList", userExpandList);
            model.addAttribute("_user", user);
            if (isok) {
                return liveplay;
            } else {
                return "redirect:/front/couinfo/" + course.getId();
            }
        } catch (Exception e) {
            logger.error("CourseController.couinfo", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 开通课程验证
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/course/check/{id}")
    @ResponseBody
    public Map<String, Object> checkCourse(@PathVariable Long id, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Course course = courseService.getCourseById(id);
            if (course.getIsavaliable() != 0) {
                json = this.getJsonMap(false, "课程已下架", null);
            } else if (course.getCurrentprice().doubleValue() <= 0) {
                json = this.getJsonMap(false, "开通课程金额不能为0", null);
            } else {
                json = this.getJsonMap(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("CourseController.checkCourse", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 获取收藏列表
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/uc/fav")
    public ModelAndView favouriteCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(favouriteList);
        try {
            // 用户收藏课程
            List<FavouriteCourseDTO> courseList = courseFavoritesService.getFavouriteCourseDTO(getLoginUserId(request),
                    page);
//	    // 收藏最多
//	    List<FavouriteCourseDTO> courseMoreList = courseFavoritesService.getMoreFavouriteCourse();
//	    // 获得所有推荐课程
//	    Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            modelAndView.addObject("courseList", courseList);
//	    modelAndView.addObject("courseMoreList", courseMoreList);
//	    modelAndView.addObject("mapCourseList", mapCourseList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("favouriteCourseList" + e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 删除收藏课程
     *
     * @param courseIds
     * @return
     */
    @RequestMapping("/uc/del")
    public ModelAndView delCourseFav(HttpServletRequest request, @RequestParam("sellIdArr") String courseIds) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/uc/fav");
        try {
            // 课程方法删除
            courseFavoritesService.deleteCourseFavoritesById(courseIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    @RequestMapping("/course/reserve")
    @ResponseBody
    public Map<String, Object> reserve(HttpServletRequest request, HttpServletResponse response, @RequestParam Long courseId) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNull(userId)) {
                json = this.getJsonMap(false, "notLogin", null);
                return json;
            }
            if (ObjectUtils.isNotNull(courseId)) {
                // 是否已预约
                CourseReserveRecord courseReserveRecord = new CourseReserveRecord();
                courseReserveRecord.setUserId(userId);
                courseReserveRecord.setCourseId(courseId);
                courseReserveRecord = courseReserveRecordService.queryCourseReserveRecord(courseReserveRecord);
                if (ObjectUtils.isNull(courseReserveRecord)) {//没有预约
                    // 直播信息
                    Course course = courseService.getCourseById(courseId);
                    // 限定人数
                    if (course.getIsRestrict() == 1) {
                        // 预约人数
                        Integer count = courseReserveRecordService.getCourseReserveRecordCount(courseId);
                        if (course.getRestrictNum().intValue() <= count) {// 预约人数不小于限定人数
                            json = this.getJsonMap(false, "false", null);
                            return json;
                        }
                    }
                    // 初始化
                    courseReserveRecord = new CourseReserveRecord();
                    // 用户ID
                    courseReserveRecord.setUserId(userId);
                    // 课程ID
                    courseReserveRecord.setCourseId(courseId);
                    // 课程名称
                    courseReserveRecord.setCourseName(course.getName());
                    // 课程价格
                    courseReserveRecord.setCoursePrice(new BigDecimal(0));
                    // 预约时间
                    courseReserveRecord.setCreateTime(new Date());
                    // 未审核
                    courseReserveRecord.setCheck(0);
                    // 添加预约
                    courseReserveRecordService.addCourseReserveRecord(courseReserveRecord);
                    json = this.getJsonMap(true, "success", null);
                } else if (courseReserveRecord.getCheck() == 0) {//预约未通过审核
                    json = this.getJsonMap(false, "notCheck", null);
                } else {// 通过审核
                    json = this.getJsonMap(false, "reserve", null);
                }
            } else {
                json = this.getJsonMap(false, "courseIsNot", null);
            }
        } catch (Exception e) {
            logger.error("CourseController.checkRestrictNum" + e);
            json = getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 岗位课程列表
     *
     * @param model
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/uc/groupCourseList")
    public String groupCourseList(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            Long userId = getLoginUserId(request);
            page.setPageSize(6);
            QueryCourse queryCourse = new QueryCourse();
            queryCourse.setUserId(userId);
            queryCourse.setIsGroup(1l);
            List<CourseDto> courseList = courseService.getUserCourseList(queryCourse, page);
            model.addAttribute("courseList", courseList);
        } catch (Exception e) {
            logger.error("CourseController.groupCourseList", e);
        }
        return groupCourseList;
    }

}