package com.atdld.os.api.course.controller;


import com.atdld.os.api.course.entity.CourseDto;
import com.atdld.os.api.course.entity.QueryCourse;
import com.atdld.os.api.course.service.CourseService;
import com.atdld.os.entity.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
@Controller
@RequestMapping("/course")
public class CourseController {


    @Autowired
    private CourseService courseService;

//    @Autowired
//    private TeacherService teacherService;
//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private CourseKpointService courseKpointService;
//    @Autowired
//    private SubjectService subjectService;
//    @Autowired
//    private TrxorderService trxorderService;
//    @Autowired
//    private CourseProfileService courseProfileService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    private MemberTypeService memberTypeService;
//    @Autowired
//    private CourseFavoritesService courseFavoritesService;
//    @Autowired
//    private MemberRecordService memberRecordService;
//    @Autowired
//    private WebsiteProfileService websiteProfileService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

/*    private void _querySubjectList(QueryCourse queryCourse, ModelAndView modelAndView) {
        //查询条件专业,子专业
        if (ObjectUtils.isNotNull(queryCourse.getSubjectId())) {
            Subject subject = new Subject();
            subject.setSubjectId(queryCourse.getSubjectId());
            subject = subjectService.getSubjectBySubjectId(subject);
            modelAndView.addObject("subject", subject);
            //查询子专业
            List<Subject> sonSubjectList = null;
            if (subject.getParentId() != 0) {//条件为二级专业
                sonSubjectList = subjectService.getSubjectListByOne(subject.getParentId());
            } else {//条件为一级专业
                sonSubjectList = subjectService.getSubjectListByOne(subject.getSubjectId());
            }
            modelAndView.addObject("sonSubjectList", sonSubjectList);
        }
    }*/

    // 课程列表展示
    // list
    @ResponseBody
    @RequestMapping("/list")
    public Object list(@ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        //只查询上架的
        queryCourse.setIsavaliable(0L);
        queryCourse.setSellType("NOLIVE");//非直播的课程
        // 搜索课程列表
        List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);

        return courseList;
        // 查询所有1级专业
//        QuerySubject querySubject = new QuerySubject();
//        querySubject.setParentId(0L);
//        List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
//
//        _querySubjectList(queryCourse, modelAndView);

//        查询教师
//        Teacher teacher = teacherService.getTeacherById(queryCourse.getTeacherId());
//        modelAndView.addObject("teacher", teacher);
//         全部教师
//        List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
//        modelAndView.addObject("courseList", courseList);
//        modelAndView.addObject("page", this.getPage());
//        modelAndView.addObject("course", queryCourse);
//        modelAndView.addObject("teacherList", teacherList);
//        modelAndView.addObject("subjectList", subjectList);
//        查找售卖方式开关配置
//        Map<String, Object> saleMap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
//        modelAndView.addObject("saleMap", saleMap);
//        查询会员类型
//        MemberType memberType = memberTypeService.getMemberTypeById(queryCourse.getMembertype());
//        modelAndView.addObject("memberType", memberType);
//        会员类型
//        List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
//        modelAndView.addObject("memberTypes", memberTypes);
//        if (ObjectUtils.isNotNull(queryCourse.getTeacherId())) {
//            modelAndView.addObject("teacher", teacherService.getTeacherById(queryCourse.getTeacherId()));
//        }
    }

    // 公开课列表展示
    // publiclist{page,querycourse}
    // 课程详情
    // course{id}
    // 课程树
    // coursetree
    // 我的课程
    // /user/course/list
    // 购买、免费、推荐
    // 我的直播
    // /user/course/live
    // 课程收藏
    // /favourite
    // /favourite/del|add|list
}
