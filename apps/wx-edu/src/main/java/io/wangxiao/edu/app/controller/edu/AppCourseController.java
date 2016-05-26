package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.constants.enums.CourseProfileType;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.enums.SellType;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.service.course.*;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/app")
public class AppCourseController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppCourseController.class);
    private String toAppCourse = getViewPath("/course/course-app-info");

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    GuidGeneratorService guidGeneratorService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private CourseProfileService courseProfileService;

    /**
     * app查询课程详情
     *
     * @param userId
     * @param courseId
     * @param currentCourseId
     * @return
     */
    @RequestMapping("/course/info")
    @ResponseBody
    public Map<String, Object> queryCourseInfo(@RequestParam("userId") Long userId, @RequestParam("courseId") Long courseId, @RequestParam(required = false) Long currentCourseId) {
        Map<String, Object> json = null;
        try {
            Map<String, Object> dataMap = new HashMap<>();
            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(courseId);
            if (course == null) {
                json = this.getJsonMap(false, "课程已下架或不存在", null);
                return json;
            }
            //判断该课程是否可以观看
            boolean isok = false;
            //判断是否购买课程且未到期
            boolean isok1 = false;
            boolean isok2 = false;

            isok1 = trxorderService.checkCourseLook(courseId, userId);
            if (isok1 == false) {// 判断该课程不可以观看
                if (userId != null && userId > 0) {//登陆才判断会员
                    isok2 = memberRecordService.checkUserMember(userId, courseId);
                }
            }
            if (isok1 == true || isok2 == true) {//判断会员不可以观看
                isok = true;
            }
            //显示目录树list,课程时也放到此list.size为1
            List<CourseDto> courseDtos = new ArrayList<CourseDto>();
            //获取套餐的课程列表
            if (course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())) {
                List<Long> alist = new ArrayList<Long>();
                alist.add(course.getId());
                courseDtos = courseService.getCourseListPackage(alist);
                if (ObjectUtils.isNotNull(courseDtos)) {
                    if (currentCourseId == null || currentCourseId == 0) {
                        currentCourseId = courseDtos.get(0).getId();
                    }
                }
            } else {
                courseDtos.add(course);
                currentCourseId = course.getId();
            }
            //重新封装course数据
            List<Course> courses = new ArrayList<>();
            for (CourseDto cd : courseDtos) {
                Course c = new Course();
                c.setId(cd.getId());
                c.setName(cd.getName());
                courses.add(c);
            }
            Course cou = new Course();
            cou.setId(course.getId());
            cou.setCurrentprice(course.getCurrentprice());
            cou.setSourceprice(course.getSourceprice());
            cou.setName(course.getName());
            cou.setTeacherList(course.getTeacherList());
            cou.setContext(course.getContext());

            //课程目录节点
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(currentCourseId);
            List<CourseKpoint> courseKpoints = courseKpointService.getCourseKpointList(courseKpoint);
            Map<String, Object> map = kpointResult(courseKpoints, course.getIsPay(), isok);
            courseKpoints = (List<CourseKpoint>) map.get("courseKpoints");
            //课程的会员信息
            List<MemberType> memberTypes = memberTypeService.getMemberTypesBycourse(course.getId());
            if (ObjectUtils.isNotNull(memberTypes) && memberTypes.size() > 0) {
                dataMap.put("memberType", memberTypes.get(0).getTitle());
            }
            //更新课程的浏览数量
            courseProfileService.updateCourseProfile(CourseProfileType.viewcount.toString(), course.getId(), 1L, CourseProfile.ADD);

            dataMap.put("currentCourseId", currentCourseId);
            dataMap.put("coursePackageList", courses);
            dataMap.put("course", cou);
            dataMap.put("courseKpoints", courseKpoints);
            dataMap.put("isok", isok);
            Long defaultKpointId = (Long) map.get("defaultKpointId");
            dataMap.put("defaultKpointId", defaultKpointId);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryCourseInfo()--error", e);
        }
        return json;
    }

    //封装子节点到父节点中
    public Map<String, Object> kpointResult(List<CourseKpoint> kpoints, int isPay, boolean isok) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<CourseKpoint> kpointList = new ArrayList<CourseKpoint>();
        LinkedHashMap<Long, CourseKpoint> courseKpointMap = new LinkedHashMap<Long, CourseKpoint>();
        for (CourseKpoint kpoint : kpoints) {
            courseKpointMap.put(kpoint.getId(), kpoint);
        }

        for (Map.Entry<Long, CourseKpoint> entry : courseKpointMap.entrySet()) {
            Long parentId = entry.getValue().getParentId();
            if (parentId > 0) {
                if (courseKpointMap.get(parentId) != null) {//子节点加入到父节点中
                    courseKpointMap.get(parentId).getChildKpoints().add(entry.getValue());
                }
            }
        }
        //只支持1 2级
        for (Map.Entry<Long, CourseKpoint> entry : courseKpointMap.entrySet()) {
            if (entry.getValue().getParentId() == 0) {
                kpointList.add(entry.getValue());
            }
        }
        map.put("courseKpoints", kpointList);
        //默认播放节点
        Long defaultKpointId = 0L;
        lableA:
        for (CourseKpoint parKpoint : kpointList) {
            if (parKpoint.getType() == 0) {//课程节点
                if (isPay == 0 || isok) {
                    defaultKpointId = parKpoint.getId();
                    break lableA;
                } else {
                    if (parKpoint.getIsfree() == 1) {
                        defaultKpointId = parKpoint.getId();
                        break lableA;
                    }
                }
            } else {//章节
                if (isPay == 0 || isok) {
                    if (parKpoint.getChildKpoints().size() > 0) {
                        defaultKpointId = parKpoint.getChildKpoints().get(0).getId();
                        break lableA;
                    }
                } else {
                    for (CourseKpoint subKpoint : parKpoint.getChildKpoints()) {
                        if (subKpoint.getIsfree() == 1) {
                            defaultKpointId = subKpoint.getId();
                            break lableA;
                        }
                    }
                }
            }
        }
        map.put("defaultKpointId", defaultKpointId);
        return map;
    }

    /**
     * 查询专业列表
     *
     * @param parenId
     * @return
     */
    @RequestMapping("/subject/list")
    @ResponseBody
    public Map<String, Object> querySubjectList(@RequestParam("parentId") Long parenId) {
        Map<String, Object> json = null;
        try {
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(parenId);
            List<Subject> subjects = subjectService.getSubjectList(querySubject);
            json = this.getJsonMap(true, "查询成功", subjects);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryCourseList()--error", e);
        }
        return json;
    }

    /**
     * 查询教师列表
     *
     * @return
     */
    @RequestMapping("/teacher/query")
    @ResponseBody
    public Map<String, Object> queryTeacherList() {
        Map<String, Object> json = null;
        try {
            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
            for (Teacher teacher : teacherList) {
                teacher.setEducation("");
                teacher.setCareer("");
            }
            json = this.getJsonMap(true, "查询成功", teacherList);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryCourseList()--error", e);
        }
        return json;
    }


    /**
     * App（首页/课程分类列表）查询所有的课程列表接口 currentPage当前页面数
     * pageSize页面显示记录数，如果为null则用户默认设置10条
     */
    @RequestMapping("/course/list")
    @ResponseBody
    public Map<String, Object> queryCourseList(@ModelAttribute("page") PageEntity page, QueryCourse queryCourse) {
        Map<String, Object> json = null;
        try {
            // 页面传来的数据放到page中

            page.setPageSize(10);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType("NOLIVE");//非直播的课程
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            List<Course> courses = new ArrayList<>();
            if (courseList != null) {
                for (CourseDto cd : courseList) {
                    Course c = new Course();
                    c.setId(cd.getId());
                    c.setName(cd.getName());
                    c.setTeacherList(cd.getTeacherList());
                    c.setLessionnum(cd.getLessionnum());
                    c.setPageViewcount(cd.getPageViewcount());
                    c.setCurrentprice(cd.getCurrentprice());
                    c.setSourceprice(cd.getSourceprice());
                    courses.add(c);
                }
            }
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("courseList", courses);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryCourseList()--error", e);
        }
        return json;
    }


    /**
     * 判断节点是否可以播放
     *
     * @return
     */
    @RequestMapping("/check/kpoint")
    @ResponseBody
    public Map<String, Object> checkPlayKopint(@RequestParam("kpointId") Long kpointId, @RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            // 查询节点
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);
            json = this.getJsonMap(false, "fail", null);
            // 当传入数据不正确时直接返回
            if (ObjectUtils.isNull(courseKpoint)) {
                return json;
            }
            boolean isok = true;
            boolean isok1 = false;
            boolean isok2 = false;
            if (courseKpoint.getIsfree() == 2) {//判断节点不可以试听
                isok1 = trxorderService.checkCourseLook(courseKpoint.getCourseId(), userId);
                if (isok1 == false) {// 判断该课程不可以观看
                    if (userId != 0) {
                        isok2 = memberRecordService.checkUserMember(userId, courseKpoint.getCourseId());
                        if (isok2 == false) {//判断会员不可以观看
                            isok = false;
                        }
                    } else {
                        isok = false;
                    }
                }
            }
            if (isok) {//添加播放记录，返回可播放验证true
                CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
                courseStudyhistory.setCourseId(courseKpoint.getCourseId());
                courseStudyhistory.setKpointId(kpointId);
                courseStudyhistory.setUserId(userId);
                courseStudyhistoryService.playertimes(courseStudyhistory);
                //添加播放次数同时添加积分
                userIntegralService.addUserIntegral(IntegralKeyword.watch_video.toString(), userId, kpointId, 0L, "");
                json = this.getJsonMap(true, "success", null);
            }
        } catch (Exception e) {
            logger.error("AppController.checkPlayKopint", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * app查询教师详情
     *
     * @param page
     * @param teacherId
     * @return
     */
    @RequestMapping("/course/teacher/list")
    @ResponseBody
    public Map<String, Object> getTeacherCourseList(@ModelAttribute("page") PageEntity page, @RequestParam("teacherId") Long teacherId) {
        Map<String, Object> json = null;
        try {
            // 讲师所江讲的课程
            page.setPageSize(10);
            QueryCourse queryCourse = new QueryCourse();
            queryCourse.setTeacherId(teacherId);
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            List<Course> courses = new ArrayList<>();
            if (courseList != null) {
                for (CourseDto cd : courseList) {
                    Course c = new Course();
                    c.setId(cd.getId());
                    c.setName(cd.getName());
                    c.setPageViewcount(cd.getPageViewcount());
                    courses.add(c);
                }
            }
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("courseList", courses);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);

        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("getTeacherCourseList()----error", e);
        }
        return json;
    }

    /**
     * app课程介绍
     *
     * @return
     */
    @RequestMapping("/course/content/{id}")
    public String toAppArticle(HttpServletRequest request, @PathVariable Long id) {
        try {
            Course course = courseService.getCourseById(id);
            request.setAttribute("course", course);
        } catch (Exception e) {
            logger.error("ArticleController.toAppArticle", e);
            return setExceptionRequest(request, e);
        }
        return toAppCourse;
    }

}
