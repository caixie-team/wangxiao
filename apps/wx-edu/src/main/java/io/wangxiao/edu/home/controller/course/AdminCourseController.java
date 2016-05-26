package io.wangxiao.edu.home.controller.course;

import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.FileExportImportUtil;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.SellType;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.dao.course.CourseMemberDao;
import io.wangxiao.edu.home.entity.course.*;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.entity.user.GroupMiddleCourse;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.entity.website.WebsiteCourse;
import io.wangxiao.edu.home.service.course.*;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import io.wangxiao.edu.home.service.user.UserGroupMiddleService;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.home.service.website.WebsiteCourseDetailService;
import io.wangxiao.edu.home.service.website.WebsiteCourseService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.entity.SysUserGroup;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SubjectService;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import io.wangxiao.edu.sysuser.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminCourseController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);

    // 课程列表
    private static final String showCourseList = getViewPath("/admin/course/course_list");// 课程列表
    private static final String showRecommendCourseList = getViewPath("/admin/course/course_recommend_list");// 课程列表(推荐课程)
    private static final String showCouponCourseList = getViewPath("/admin/course/course_coupon_list");// 课程列表(优惠券限制课程)
    private static final String toAddCourse = getViewPath("/admin/course/add_course");// 添加课程
    private static final String toUpdateCourse = getViewPath("/admin/course/update_course");// 更新课程
    private static final String packCourseList = getViewPath("/admin/course/course_package_list");// 该课程的课程包列表
    private static final String selectCourseList = getViewPath("/admin/course/select_course_list");// 课程选择
    private static final String openerCourseList = getViewPath("/admin/course/course_opener_list");// 小窗口课程列表页面（礼品课程使用）
    private static final String showUserGroupList = getViewPath("/admin/course/course_group_user");// 课程列表(学员组课程)
    private static final String sysShowUserGroupList = getViewPath("/admin/course/sys_course_group_user");// 课程列表(学员组课程)
    private static final String toReserveCourseRecordList = getViewPath("/admin/course/reserve_course_list");//预约课程列表
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private WebsiteCourseService websiteCourseService;
    @Autowired
    private CourseMemberDao courseMemberDao;
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private CourseMemberService courseMemberService;
    @Autowired
    private CoursePackageService coursePackageService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private WebsiteCourseDetailService websiteCourseDetailService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CourseReserveRecordService courseReserveRecordService;
    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;
    @Autowired
    private UserGroupMiddleService userGroupMiddleService;
    @Autowired
    private CourseTeacherService courseTeacherService;
    @Autowired
    private CourseSubjectService courseSubjectService;


    @Autowired
    private WebsiteProfileService websiteProfileService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    @InitBinder("course")
    public void initBinderCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("course.");
    }

    @InitBinder("courseReserveRecord")
    public void initBinderCourseReserveRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseReserveRecord.");
    }

    /**
     * 课程列表展示
     *
     * @return
     */
    @RequestMapping("/cou/list")
    public String showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                 @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            String reqType = request.getParameter("reqType");
            // 页面传来的数据放到page中
            page.setPageSize(12);
            // 搜索课程列表
            queryCourse.setOrder(2);
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            List<Long> ids = new ArrayList<Long>();
            if (courseList != null && courseList.size() > 0) {
                for (CourseDto courseDto : courseList) {
                    ids.add(courseDto.getId());
                }
            }

            // 获取会员的list
            Map<Long, List<MemberType>> map = courseMemberDao.getCourseMemberTypeListByCourse(ids);
            // 将会员的list放到旧的list中
            if (courseList != null && courseList.size() > 0) {
                for (CourseDto courseDto : courseList) {
                    if (map.get(courseDto.getId()) == null) {
                        courseDto.setMemberTypes(new ArrayList<MemberType>());
                    } else {
                        courseDto.setMemberTypes(map.get(courseDto.getId()));
                    }

                }
            }
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
            // 会员类型
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            request.setAttribute("memberTypes", memberTypes);
            request.setAttribute("reqType", reqType);
            if (queryCourse.getGroupId() != null && queryCourse.getGroupId().longValue() > 0) {
                UserGroup userGroup = userGroupService.queryUserGroupById(queryCourse.getGroupId());
                request.setAttribute("userGroup", userGroup);
            }
        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return setExceptionRequest(request, e);
        }
        return showCourseList;
    }

    /**
     * 课程列表(推荐课程用)
     *
     * @return
     */
    @RequestMapping("/cou/recommendCourseList")
    public String showCourseListByRecommend(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                            @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            List<WebsiteCourse> websiteCourses = websiteCourseService.queryWebsiteCourseList();
            request.setAttribute("websiteCourses", websiteCourses);
        } catch (Exception e) {
            logger.error("CourseController.showCourseListByRecommend", e);
            return setExceptionRequest(request, e);
        }
        return showRecommendCourseList;
    }

    /**
     * 课程列表(优惠券限制课程用)
     *
     * @return
     */
    @RequestMapping("/cou/couponCourseList")
    public String showCourseListByCoupon(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                         @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            // 只查询上架的
            queryCourse.setIsavaliable(0L);
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            List<WebsiteCourse> websiteCourses = websiteCourseService.queryWebsiteCourseList();
            request.setAttribute("websiteCourses", websiteCourses);
        } catch (Exception e) {
            logger.error("CourseController.showCourseListByRecommend", e);
            return setExceptionRequest(request, e);
        }
        return showCouponCourseList;
    }

    /**
     * 到添加课程页面
     */
    @RequestMapping("/cou/toAddCourse")
    public String toAddCourse(HttpServletRequest request) {
        try {
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            // 获取专业类型
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
            // 会员类型
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            request.setAttribute("memberTypes", memberTypes);

            PageEntity page = new PageEntity();
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
            request.setAttribute("type", request.getParameter("type"));
        } catch (Exception e) {
            logger.error("CourseController.toAddCourse", e);
            return setExceptionRequest(request, e);
        }
        return toAddCourse;
    }

    /**
     * 到更新课程页面
     */
    @RequestMapping("/cou/toUpdate/{id}")
    public String toUpdateCourse(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            Course course = courseService.getCourseById(id);
            // 查询课程关联的教师
            CourseTeacher courseTeacher = new CourseTeacher();
            courseTeacher.setCourseId(id);
            List<CourseTeacher> courseTeacherList = courseTeacherService.getCourseTeacherList(courseTeacher);
            // 整理关联的教师id，放入课程里
            if (ObjectUtils.isNotNull(courseTeacherList)) {
                String teacherIds = "";
                for (CourseTeacher ct : courseTeacherList) {
                    teacherIds += ct.getTeacherId() + ",";
                }
                teacherIds = teacherIds.substring(0, teacherIds.length() - 1);
                course.setTeacherIds(teacherIds);
            }
            // 查询课程专业
            CourseSubject courseSubject = new CourseSubject();
            courseSubject.setCourseId(id);
            List<CourseSubject> courseSubjectList = courseSubjectService.getCourseSubjectList(courseSubject);
            if (ObjectUtils.isNotNull(courseSubjectList)) {
                course.setSubjectId(courseSubjectList.get(0).getSubjectId());
            }
            // 查询课程会员
            List<MemberType> couMemberTypes = memberTypeService.getMemberTypesBycourse(id);
            if (ObjectUtils.isNotNull(couMemberTypes)) {
                course.setMemberTypes(couMemberTypes);
            }
            request.setAttribute("course", course);
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));

            // 会员类型
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            request.setAttribute("memberTypes", memberTypes);

            //课程下的小组
            GroupMiddleCourse groupMiddleCourse = new GroupMiddleCourse();
            groupMiddleCourse.setCourseId(course.getId());
            List<GroupMiddleCourse> groupMiddleCourseList = websiteCourseDetailService.getGroupMiddleCourse(groupMiddleCourse);

            PageEntity page = new PageEntity();
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
            for (UserGroup _userGroup : userGroupList) {
                for (GroupMiddleCourse _groupMiddleCourse : groupMiddleCourseList) {
                    if (_userGroup.getId() == _groupMiddleCourse.getGroupId()) {
                        _userGroup.setCheck(1);
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("CourseController.toAddCourse", e);
            return setExceptionRequest(request, e);
        }
        return toUpdateCourse;
    }

    /**
     * 更新课程
     */
    @RequestMapping("/cou/update")
    public String updateCourse(HttpServletRequest request, @ModelAttribute("course") Course course,
                               @RequestParam(value = "courseMember") String courseMember) {
        try {
            course.setUpdateuser(getSysLoginLoginName(request));
            course.setCoursetag("");
            Course beforeRecord = this.courseService.getCourseById(course.getId());
            courseService.updateCourse(course);
            Course afterRecord = course;//this.courseService.getCourseById(course.getId());
            SysUserOptRecord record = new SysUserOptRecord(request, "更新课程", "课程记录表-" + course.getId(), beforeRecord, afterRecord);
            if (record != null) {
                sysUserOptRecordService.addRecord(record);
            }


            // 获取课程销售开关配置
            Map<String, Object> saleMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
            if (ObjectUtils.isNotNull(saleMap) && saleMap.containsKey("sale") && ObjectUtils.isNotNull(saleMap.get("sale"))) {
                Map<String, Object> verifyMemberMap = (Map<String, Object>) saleMap.get("sale");
                //会员制度是否开启
                if (verifyMemberMap.containsKey("verifyMember") && ObjectUtils.isNotNull(verifyMemberMap.get("verifyMember"))) {
                    String verifyMember = verifyMemberMap.get("verifyMember").toString().toUpperCase();
                    if ("ON".equals(verifyMember)) {
                        // 先删除课程所属会员
                        courseMemberService.delCourseMember(course.getId());
                        // 添加课程所属会员
                        if (courseMember != "") {// 课程所属会员
                            List<CourseMember> courseMembers = new ArrayList<CourseMember>();
                            String[] arry = courseMember.replaceAll(",", " ").trim().split(" ");// 课程所属会员数组
                            for (String s : arry) {
                                CourseMember cm = new CourseMember();
                                cm.setCourseId(course.getId());// 课程id
                                cm.setMemberTypeId(Long.parseLong(s));// 会员类型id
                                courseMembers.add(cm);
                            }
                            courseMemberService.addCourseMember(courseMembers);// 添加课程所属会员
                        }
                    }
                }
            }

            //根据添加课程的类型判断跳转页面
            String courseType = course.getSellType();
            return "redirect:/admin/cou/list?queryCourse.sellType=" + courseType;
        } catch (Exception e) {
            logger.error("CourseController.toAddCourse", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 添加课程
     */
    @RequestMapping("/cou/addCourse")
    public String addCourse(HttpServletRequest request, @ModelAttribute("course") Course course,
                            @RequestParam(value = "courseMember") String courseMember) {
        try {
            course.setUpdateuser(getSysLoginLoginName(request));
            course.setCoursetag("");
            course.setOrderNum(0L); // 设置排序默认为0
            courseService.addCourse(course);
            // 添加课程所属会员
            if (courseMember != "") {// 课程所属会员
                List<CourseMember> courseMembers = new ArrayList<CourseMember>();
                String[] arry = courseMember.replaceAll(",", " ").trim().split(" ");// 课程所属会员数组
                for (String s : arry) {
                    CourseMember cm = new CourseMember();
                    cm.setCourseId(course.getId());// 课程id
                    cm.setMemberTypeId(Long.parseLong(s));// 会员类型id
                    courseMembers.add(cm);
                }
                courseMemberService.addCourseMember(courseMembers);// 添加课程所属会员
            }
            if (course.getGroupIds() != null && !course.getGroupIds().equals("")) {
                websiteCourseDetailService.deleteGroupCourseList(course.getId());
                String[] idsArry = course.getGroupIds().split(",");
                List<GroupMiddleCourse> groupMiddleCourseList = new ArrayList<GroupMiddleCourse>();
                for (int i = 0; i < idsArry.length; i++) {
                    GroupMiddleCourse groupMiddleCourse = new GroupMiddleCourse();
                    groupMiddleCourse.setCourseId(course.getId());// 课程id
                    groupMiddleCourse.setGroupId(Long.parseLong(idsArry[i]));
                    groupMiddleCourseList.add(groupMiddleCourse);
                }
                websiteCourseDetailService.addGroupCourseList(groupMiddleCourseList);
            }
            String type = request.getParameter("type");
            if ("app".equals(type)) {
                return "redirect:/admin/appCourse/getAppCourseList";
            }
            //根据添加课程的类型判断跳转页面
            String courseType = course.getSellType();
            return "redirect:/admin/cou/list?queryCourse.sellType=" + courseType;
        } catch (Exception e) {
            logger.error("CourseController.toAddCourse", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 复制课程
     *
     * @param request
     * @param courseId
     * @return
     */
    @RequestMapping("/cou/copyCourse/{courseId}")
    public String copyCourse(HttpServletRequest request, @PathVariable("courseId") Long courseId) {
        Course course = null;
        try {
            course = courseService.getCourseById(courseId);
            Long oldCourseId = course.getId();
            //复制课程的subjectId
            CourseSubject queryCourseSubject = new CourseSubject();
            queryCourseSubject.setCourseId(oldCourseId);
            List<CourseSubject> courseSubjectList = courseSubjectService.getCourseSubjectList(queryCourseSubject);
            CourseSubject courseSubject = courseSubjectList.get(0);
            course.setSubjectId(courseSubject.getSubjectId());
            //复制课程教师
            CourseTeacher queryCourseTeacher = new CourseTeacher();
            queryCourseTeacher.setCourseId(oldCourseId);
            List<CourseTeacher> courseTeacherList = courseTeacherService.getCourseTeacherList(queryCourseTeacher);
            String teacherIds = "";
            for (CourseTeacher courseTeacher : courseTeacherList) {
                teacherIds += courseTeacher.getTeacherId() + ",";
            }
            course.setTeacherIds(teacherIds);
            course.setId(null);
            String newCourseName = request.getParameter("newCourseName");
            course.setName(newCourseName);
            Date date = new Date();
            course.setAddtime(date);
            course.setUpdateTime(date);
            courseService.addCourse(course);
            //复制会员类型
            List<CourseMember> courseMemberList = courseMemberService.getCourseMemberListByCourseId(oldCourseId);
            List<CourseMember> _courseMemberList = new ArrayList<CourseMember>();
            for (CourseMember courseMember : courseMemberList) {
                CourseMember _courseMember = new CourseMember();
                _courseMember.setCourseId(course.getId());
                _courseMember.setMemberTypeId(courseMember.getMemberTypeId());
                _courseMemberList.add(_courseMember);
            }
            if (_courseMemberList != null && _courseMemberList.size() > 0) {
                courseMemberService.addCourseMember(_courseMemberList);
            }
            //复制小组
            GroupMiddleCourse queryGroupMiddleCourse = new GroupMiddleCourse();
            queryGroupMiddleCourse.setCourseId(oldCourseId);
            List<GroupMiddleCourse> groupMiddleCourseList = websiteCourseDetailService.getGroupMiddleCourse(queryGroupMiddleCourse);
            List<GroupMiddleCourse> _groupMiddleCourseList = new ArrayList<GroupMiddleCourse>();
            for (GroupMiddleCourse groupMiddleCourse : groupMiddleCourseList) {
                GroupMiddleCourse _groupMiddleCourse = new GroupMiddleCourse();
                _groupMiddleCourse.setCourseId(course.getId());
                _groupMiddleCourse.setGroupId(groupMiddleCourse.getGroupId());
                _groupMiddleCourseList.add(_groupMiddleCourse);
            }
            if (_groupMiddleCourseList != null && _groupMiddleCourseList.size() > 0) {
                websiteCourseDetailService.addGroupCourseList(_groupMiddleCourseList);
            }
            //课程类型为 课程/直播时  复制视频节点
            if ("COURSE".equals(course.getSellType()) || "LIVE".equals(course.getSellType())) {
                CourseKpoint queryCourseKpoint = new CourseKpoint();
                queryCourseKpoint.setCourseId(oldCourseId);
                List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(queryCourseKpoint);
                for (CourseKpoint courseKpoint : courseKpointList) {
                    courseKpoint.setId(null);
                    courseKpoint.setCourseId(course.getId());
                }
                if (courseKpointList != null && courseKpointList.size() > 0) {
                    courseKpointService.addCourseKpointBatch(courseKpointList);
                }
            }
            //课程类型为套餐 复制套餐包含的课程
            if ("PACKAGE".equals(course.getSellType())) {
                CoursePackage queryCoursePackage = new CoursePackage();
                queryCoursePackage.setMainCourseId(oldCourseId);
                List<CoursePackage> coursePackageList = coursePackageService.getCoursePackageList(queryCoursePackage);
                List<CoursePackage> _coursePackageList = new ArrayList<CoursePackage>();
                for (CoursePackage coursePackage : coursePackageList) {
                    CoursePackage _coursePackage = new CoursePackage();
                    _coursePackage.setMainCourseId(course.getId());
                    _coursePackage.setCourseId(coursePackage.getCourseId());
                    _coursePackage.setOrderNum(coursePackage.getOrderNum());
                    _coursePackageList.add(_coursePackage);
                }
                if (_coursePackageList != null && _coursePackageList.size() > 0) {
                    coursePackageService.createCoursePackageBatch(_coursePackageList);
                }
            }
        } catch (Exception e) {
            logger.error("CourseController.copyCourse", e);
        }
        return "redirect:/admin/cou/list?queryCourse.sellType=" + course.getSellType();
    }

    /**
     * 多单删除课程
     */
    @RequestMapping("/cou/del")
    @ResponseBody
    public Map<String, Object> delCourse(HttpServletRequest request, @ModelAttribute("ids") String ids) {
        Map<String, Object> json = null;

        courseService.deleteCourseById(ids);
        json = this.getJsonMap(true, "success", null);
        return json;
    }


    /**
     * 课程包列表
     */
    @RequestMapping("/cou/pack/{courseId}")
    public String packCourseList(HttpServletRequest request, @PathVariable("courseId") Long courseId, Course course) {
        try {
            course.setId(courseId);
            List<Course> courseList = courseService.getCourseListPackageByCondition(course);
            request.setAttribute("courseList", courseList);
            request.setAttribute("course", course);
        } catch (Exception e) {
            logger.error("CourseController.packCourseList", e);
            return setExceptionRequest(request, e);
        }
        return packCourseList;
    }

    /**
     * 流水导出
     *
     * @param request
     * @param response
     * @param queryCourse
     */
    @RequestMapping("/course/export")
    public void exportOrderResult(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/order");
            // 文件名
            String expName = "课程信息_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "课程名称", "售卖形式", "上课方式", "上下架", "价格", "购买数", "浏览数", "添加时间", "课程时间"};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            courseService.getCourseListPage(queryCourse, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<CourseDto> courseDtoList = courseService.getCourseListPage(queryCourse, page);
                List<List<String>> list = courseJoint(courseDtoList);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单excel格式拼接
     *
     * @param courseList
     * @return
     */
    public List<List<String>> courseJoint(List<CourseDto> courseList) {
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < courseList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(courseList.get(i).getId() + "");
            small.add(courseList.get(i).getName());
            if (courseList.get(i).getSellType().equals(SellType.COURSE.toString())) {
                small.add("课程");
            } else if (courseList.get(i).getSellType().equals(SellType.LIVE.toString())) {
                small.add("直播");
            } else if (courseList.get(i).getSellType().equals(SellType.PACKAGE.toString())) {
                small.add("套餐");
            }
            if (courseList.get(i).getIsavaliable() == 0) {
                small.add("上架");
            }
            if (courseList.get(i).getIsavaliable() == 1) {
                small.add("下架");
            }
            small.add(courseList.get(i).getCurrentprice().toString());
            small.add(courseList.get(i).getBuycount() + "");
            small.add(courseList.get(i).getViewcount() + "");
            if (courseList.get(i).getAddtime() != null) {
                small.add(format.format(courseList.get(i).getAddtime()));
            }
            if (courseList.get(i).getLectureTime() != null) {
                small.add(format.format(courseList.get(i).getLectureTime()));
            }
            list.add(small);
        }
        return list;
    }

    /**
     * 课程包选择课程
     *
     * @return
     */
    @RequestMapping("/cou/select")
    public String selectCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                   @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            queryCourse.setSellType(SellType.COURSE.toString());
            // 只查询上架的
            queryCourse.setIsavaliable(0L);

            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
        } catch (Exception e) {
            logger.error("CourseController.selectCourseList", e);
            return setExceptionRequest(request, e);
        }
        return selectCourseList;
    }

    /**
     * 课程包添加课程
     */
    @RequestMapping("/cou/addPack")
    @ResponseBody
    public Map<String, Object> addtCourseList(HttpServletRequest request, @ModelAttribute("ids") String ids,
                                              @ModelAttribute("courseId") Long courseId) {
        Map<String, Object> json = null;
        try {
            coursePackageService.addCoursePackageBatch(ids, courseId);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("CourseController.addtCourseList", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 删除课程包添加的课程
     */
    @RequestMapping("/cou/delPack/{courseId}/{mainCourseId}")
    @ResponseBody
    public Map<String, Object> delCourseList(HttpServletRequest request, @PathVariable("courseId") Long courseId,
                                             @PathVariable("mainCourseId") Long mainCourseId) {
        Map<String, Object> json = null;
        try {
            CoursePackage coursePackage = new CoursePackage();
            coursePackage.setCourseId(courseId);
            coursePackage.setMainCourseId(mainCourseId);
            coursePackageService.delCoursePackage(coursePackage);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("CourseController.delCourseList", e);
            json = this.getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 课程列表展示
     *
     * @return
     */
    @RequestMapping("/cou/openerlist")
    public String openerCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,
                                   @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 页面传来的数据放到page中

            page.setPageSize(12);
            // 添加时间倒叙
            queryCourse.setOrder(2);
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", gson.toJson(subjectList));
        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return setExceptionRequest(request, e);
        }
        return openerCourseList;
    }

    /**
     * 判断选中课程是否已过期
     */
    @RequestMapping("/cou/courseOverdue")
    @ResponseBody
    public Map<String, Object> courseOverdue(HttpServletRequest request, @RequestParam("courseId") Long courseId) {
        Map<String, Object> json = null;
        try {
            Course course = courseService.getCourseById(courseId);
            if (ObjectUtils.isNotNull(course)) {
                if (course.getLosetype() == 0) {
                    Date date = new Date();// 今天
                    String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
                    String authStr = DateUtils.formatDate(course.getLoseAbsTime(), "yyyy-MM-dd");
                    if (Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1 > 0) {
                        json = this.getJsonMap(true, "success", null);
                    } else {
                        json = this.getJsonMap(true, "overdue", null);
                    }
                } else {
                    json = this.getJsonMap(true, "success", null);
                }
            } else {
                json = this.getJsonMap(true, "noCourse", null);
            }

        } catch (Exception e) {
            logger.error("CourseController.courseOverdue", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 修改课程排序值
     *
     * @param course
     * @return
     */
    @RequestMapping("/cou/updateOrderNum")
    @ResponseBody
    public Map<String, Object> updateCourseOrderNum(Course course) {
        Map<String, Object> json = null;
        try {
            CoursePackage coursep = new CoursePackage();
            coursep.setCourseId(course.getId());
            coursep.setOrderNum(course.getOrderNum());
            coursePackageService.updateCoursePackageOrderNum(coursep);
            json = this.getJsonMap(true, "修改成功", null);
        } catch (Exception e) {
            logger.error("CourseController.updateCourseOrderNum", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }


    /**
     * 课程列表(小组课程用)
     *
     * @return
     */
    @RequestMapping("/cou/groupCourseList")
    public String showCourseListByGroup(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {

            JsonObject sysUser = SingletonLoginUtils.getLoginUser(request);
            // 页面传来的数据放到page中
            page.setPageSize(12);
            QueryCourse _queryCourse = new QueryCourse();
            // 添加时间倒叙
            _queryCourse.setOrder(2);
            // 只查询上架的
            _queryCourse.setIsavaliable(0L);
            _queryCourse.setId(queryCourse.getId());
            _queryCourse.setSellType(queryCourse.getSellType());
            _queryCourse.setSysUserId(sysUser.get("id").getAsLong());
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(_queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", _queryCourse);

            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(sysUser.get("id").getAsLong());
            List<SysUserGroup> sysUserGroupList = sysUserService.getSysUserGroupBySysUserId(sysUserGroup);
            if (sysUserGroupList != null && sysUserGroupList.size() > 0) {
                List<Long> ids = new ArrayList<Long>();
                for (SysUserGroup _sysUserGroup : sysUserGroupList) {
                    ids.add(_sysUserGroup.getGroupId());
                }
                List<UserGroup> userGroupList = userGroupService.getUserGroupListByIds(ids);
                request.setAttribute("userGroupList", userGroupList);
            }
            request.setAttribute("groupId", queryCourse.getGroupId());

        } catch (Exception e) {
            logger.error("CourseController.showCourseListByRecommend", e);
            return setExceptionRequest(request, e);
        }
        return showUserGroupList;
    }


    /**
     * 课程列表(小组课程用)
     *
     * @return
     */
    @RequestMapping("/sys/cou/groupCourseList")
    public String showSysCourseListByGroup(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            //Long groupId=Long.parseLong(request.getParameter("groupId"));
            //JsonObject sysUser=SingletonLoginUtils.getSysUser(request);
            // 页面传来的数据放到page中
            page.setPageSize(12);
            QueryCourse _queryCourse = new QueryCourse();
            // 添加时间倒叙
            _queryCourse.setOrder(2);
            // 只查询上架的
            _queryCourse.setIsavaliable(0L);
            _queryCourse.setId(queryCourse.getId());
            _queryCourse.setSellType(queryCourse.getSellType());
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(_queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", _queryCourse);


            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
            request.setAttribute("groupId", queryCourse.getGroupId());

        } catch (Exception e) {
            logger.error("CourseController.showCourseListByRecommend", e);
            return setExceptionRequest(request, e);
        }
        return sysShowUserGroupList;
    }

    /**
     * 到预约审核分页页面
     *
     * @param courseReserveRecord
     * @param page
     * @return
     */
    @RequestMapping("/cou/checkReserveCourse")
    public String checkReserveCourse(Model model, @ModelAttribute("courseReserveRecord") CourseReserveRecord courseReserveRecord, @ModelAttribute("page") PageEntity page) {
        try {
            List<CourseReserveRecord> recordList = courseReserveRecordService.getCourseReserveRecordPageList(courseReserveRecord, page);
            model.addAttribute("recordList", recordList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("CourseController.checkReserveCourse", e);
        }
        return toReserveCourseRecordList;
    }

    /**
     * 审核通过直播
     *
     * @param request
     * @return
     */
    @RequestMapping("/cou/passReserveCourse")
    @ResponseBody
    public Map<String, Object> passReserveCourse(HttpServletRequest request, @RequestParam Long id) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(id)) {
                CourseReserveRecord beforeRecord = courseReserveRecordService.getCourseReserveRecord(id);
                courseReserveRecordService.passCheck(id);
                CourseReserveRecord afterRecord = courseReserveRecordService.getCourseReserveRecord(id);
                SysUserOptRecord record = new SysUserOptRecord(request, "直播通过审核", "直播审核表-" + id, beforeRecord, afterRecord);
                if (ObjectUtils.isNotNull(record)) {
                    sysUserOptRecordService.addRecord(record);
                }
                json = getJsonMap(true, "success", null);
            } else {
                json = getJsonMap(false, "false", null);
            }
        } catch (NumberFormatException e) {
            logger.error("CourseController.passReserveCourse", e);
            json = getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 课程列表 上下架课程
     *
     * @param request
     * @param course
     * @return
     */
    @RequestMapping("/cou/updateIsavaliable")
    @ResponseBody
    public Map<String, Object> updateIsavaliable(HttpServletRequest request, @ModelAttribute Course course) {
        Map<String, Object> json = null;
        try {
            Course beforeRecord = courseService.getCourseById(course.getId());
            courseService.updateCourseIsavaliableById(course);
            Course afterRecord = courseService.getCourseById(course.getId());
            SysUserOptRecord record = new SysUserOptRecord(request, "课程" + (course.getIsavaliable() == 0 ? "上架" : "下架"), "课程记录表", beforeRecord, afterRecord);
            if (record != null) sysUserOptRecordService.addRecord(record);
            json = getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("CourseController.updateIsavaliable", e);
            json = getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 课程列表 批量上下架课程
     *
     * @param request
     * @return
     */
    @RequestMapping("/cou/updateIsavaliableBatch")
    @ResponseBody
    public Map<String, Object> updateIsavaliableBatch(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            List<Course> list = new ArrayList<>();
            String couIdsStr = request.getParameter("couIds");
            String isavaliable = request.getParameter("isavaliable");
            String[] couIds = couIdsStr.replaceAll(" ", "").split(",");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("couIds", couIds);
            map.put("isavaliable", isavaliable);
            for (String id : couIds) {
                Course course = courseService.getCourseById(Long.valueOf(id));
                list.add(course);
            }
            courseService.updateCourseIsavaliableBatch(map);
            for (int i = 0; i < couIds.length; i++) {
                Course afterRecord = courseService.getCourseById(Long.valueOf(couIds[i]));
                SysUserOptRecord record = new SysUserOptRecord(request, "课程" + ("0".equals(isavaliable) ? "上架" : "下架"), "课程记录表", list.get(i), afterRecord);
                if (record != null) sysUserOptRecordService.addRecord(record);
            }
            json = getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("CourseController.updateIsavaliableBatch", e);
            json = getJsonMap(false, "false", null);
        }
        return json;
    }

    @RequestMapping("/cou/deleteCourseGroup")
    @ResponseBody
    public Map<String, Object> deleteCourseGroup(@RequestParam("groupId") Long groupId, @RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(groupId) && StringUtils.isNotEmpty(ids)) {
                userGroupMiddleService.deleteGroupCourseByCourseId(groupId, ids);
                json = getJsonMap(true, "success", null);
            } else {
                json = getJsonMap(false, "", null);
            }
        } catch (Exception e) {
            logger.error("AdminCourseController.");
            json = getJsonMap(false, "error", null);
        }
        return json;
    }
}