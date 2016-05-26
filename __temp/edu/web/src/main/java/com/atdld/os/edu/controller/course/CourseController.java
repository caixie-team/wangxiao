package com.atdld.os.edu.controller.course;


import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.CourseProfileType;
import com.atdld.os.edu.constants.enums.SellType;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.entity.article.Article;
import com.atdld.os.edu.entity.course.*;
import com.atdld.os.edu.entity.member.MemberType;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.service.article.ArticleService;
import com.atdld.os.edu.service.course.*;
import com.atdld.os.edu.service.member.MemberRecordService;
import com.atdld.os.edu.service.member.MemberTypeService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.sysuser.entity.QuerySubject;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.service.SubjectService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
@Controller
public class CourseController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    // 课程列表
    private static final String showCourseList = getViewPath("/course/courses-list");
    //直播课程列表
    private static final String showlivelist = getViewPath("/course/courses-live-list");
    // 课程详情
    private static final String couinfo = getViewPath("/course/course-infor");
    // 直播课程详情
    private static final String couLiveinfo = getViewPath("/course/course-live-infor");
    //公开课详情
    private static final String showPublicCourseList=getViewPath("/course/publicCourses-list");
    

    //首页专业切换时ajax获取
    private static final String courses_ajax = getViewPath("/course/courses_ajax");
    private static final String uc_mycourse=getViewPath("/ucenter/mycourse");
    private static final String uc_live=getViewPath("/ucenter/uc_live");
    
    
    
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
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    private void _querySubjectList(QueryCourse queryCourse, ModelAndView modelAndView){
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
    }

    /**
     * 课程列表展示,搜索课程
     * 
     * @return
     */
    @RequestMapping("/front/showcoulist")
    public ModelAndView showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        ModelAndView modelAndView = new ModelAndView(showCourseList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(12);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType("NOLIVE");//非直播的课程
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            
            // 查询所有1级专业
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(0L);
            List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);

            _querySubjectList(queryCourse, modelAndView);

            //查询教师
            Teacher teacher=teacherService.getTeacherById(queryCourse.getTeacherId());
            modelAndView.addObject("teacher", teacher);
            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
            modelAndView.addObject("courseList", courseList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("course", queryCourse);
            modelAndView.addObject("teacherList", teacherList);
            modelAndView.addObject("subjectList", subjectList);
            //查找售卖方式开关配置
    		Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
    		modelAndView.addObject("saleMap", saleMap);
            //查询会员类型
            MemberType memberType=memberTypeService.getMemberTypeById(queryCourse.getMembertype());
            modelAndView.addObject("memberType", memberType);
            //会员类型
            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes",memberTypes);
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
     * 课程公开课列表展示,搜索课程
     * 
     * @return
     */
    @RequestMapping("/front/showPublicCourselist")
    public ModelAndView showPublicCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        ModelAndView modelAndView = new ModelAndView(showPublicCourseList);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(12);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType("NOLIVE");//非直播的课程
            // 搜索课程列表
            List<CourseDto> publicCourseList = courseService.getPublicCourseListPage(queryCourse, page);
            
            // 查询所有1级专业
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(0L);
            List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
            //查询条件专业,子专业
            _querySubjectList(queryCourse, modelAndView);


            //查询教师
            Teacher teacher=teacherService.getTeacherById(queryCourse.getTeacherId());
            modelAndView.addObject("teacher", teacher);
            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
            modelAndView.addObject("publicCourseList", publicCourseList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("course", queryCourse);
            modelAndView.addObject("teacherList", teacherList);
            modelAndView.addObject("subjectList", subjectList);
            //查找售卖方式开关配置
    		Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
    		modelAndView.addObject("saleMap", saleMap);
            //查询会员类型
            MemberType memberType=memberTypeService.getMemberTypeById(queryCourse.getMembertype());
            modelAndView.addObject("memberType", memberType);
            //会员类型
            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes",memberTypes);
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
     * 课程列表展示,搜索课程
     *
     * @return
     */
    @RequestMapping("/front/showlivelist")
    public ModelAndView showlivelist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        ModelAndView modelAndView = new ModelAndView(showlivelist);
        try {
            // 页面传来的数据放到page中
            this.setPage(page);

            this.getPage().setPageSize(6);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType(SellType.LIVE.toString());//出去直播的课程
            // 搜索课程列表
            queryCourse.setOrder(4);//根据直播开始时间排序
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);

            // 查询所有1级专业
            QuerySubject querySubject = new QuerySubject();
            querySubject.setParentId(0L);
            List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
            //查询条件专业,子专业
            _querySubjectList(queryCourse, modelAndView);


            modelAndView.addObject("courseList", courseList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("course", queryCourse);
            modelAndView.addObject("subjectList", subjectList);
            //查找售卖方式开关配置
            Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
            modelAndView.addObject("saleMap", saleMap);
        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }
    /**
     * 课程详情
     * 不管是课程套餐还是课程目录时都放到list(coursePackageList)中
     * @return
     */
    @RequestMapping("/front/couinfo/{id}")
    public String couinfo(Model model, HttpServletRequest request, @PathVariable("id") Long id,RedirectAttributes redirectAttributes) {
        try {
            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(id);
            //查询用户信息
            List<UserExpand> userExpandList=courseService.queryUserExpandListByCourseId(id);          
            if(ObjectUtils.isNull(course)){
                redirectAttributes.addAttribute("msg","对不起该课程已经下架或者删除");
                return "redirect:/front/success";
            }
            List<MemberType> memberTypes=memberTypeService.getMemberTypesBycourse(course.getId());//课程的会员信息
            course.setMemberTypes(memberTypes);
            // 点击量资讯排行
            List<Article> articleListOrderclickTimes = articleService.queryArticleListOrderclickTimes(10);
            //同类课程推荐
            List<CourseDto> courseList = courseService.getSubjectCourseList(null,course.getId(), 3);
            //判断该课程是否可以观看
            boolean isok=false;
            //判断是否购买课程且未到期
            boolean isok1 = trxorderService.checkCourseLook(id,getLoginUserId(request));
            boolean isok2=false;
            if(isok1!=true){
            	isok2 = memberRecordService.checkUserMember(getLoginUserId(request),course.getId());
            }
            if(isok1||isok2){//已购买或已开通会员
            	isok=true;
            }
            //显示目录树list,课程时也放到此list.size为1而已
            List<CourseDto> courseDtos =new ArrayList<CourseDto>();
            //获取套餐的课程列表
            if(course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())){
                List<Long> alist= new ArrayList<Long>();
                alist.add(course.getId());
                 courseDtos = courseService.getCourseListPackage(alist);
                if(ObjectUtils.isNotNull(courseDtos)){
                    model.addAttribute("currentCourseId", courseDtos.get(0).getId());
                }
            }else{
                courseDtos.add(course);
                model.addAttribute("currentCourseId", course.getId());
            }
            model.addAttribute("coursePackageList", courseDtos);
            model.addAttribute("courseList", courseList);
            model.addAttribute("course", course);
            model.addAttribute("articleListOrderclickTimes", articleListOrderclickTimes);
            model.addAttribute("isok", isok);
            model.addAttribute("userExpandList", userExpandList);
            //更新课程的浏览数量
            courseProfileService.updateCourseProfile(CourseProfileType.viewcount.toString(), course.getId(), 1L,CourseProfile.ADD);
            if(SellType.LIVE.toString().equals(course.getSellType())){
                return couLiveinfo;
            }
        } catch (Exception e) {
            logger.error("CourseController.couinfo", e);
            return setExceptionRequest(request, e);
        }

        return couinfo;
    }

    
    /**
     * 获取课程的树JSON
     * @return
     */
    @RequestMapping("/front/coursetree/{id}")
    @ResponseBody
    public Object getCourseTree(@PathVariable Long id){
        CourseKpoint courseKpoint = new CourseKpoint();
        courseKpoint.setCourseId(id);
        List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
        this.setJson(true,"", courseKpointList);
        return json;
    }
    
    /**
     * 首页根据专业查询课程
     * ajax请求
     * @return
     */
    @RequestMapping("/front/ajax/course")
    public ModelAndView ajaxcourse(HttpServletRequest request,Model model, Long subjectId) {
        ModelAndView modelAndView = new ModelAndView(courses_ajax);
        try {
            // 课程列表
            List<CourseDto> courseList = courseService.getSubjectCourseList(subjectId,null, 8);
             model.addAttribute("courseList", courseList);
        } catch (Exception e) {
            logger.error("CourseController.showCourseList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }
    /**
     * 我的课程
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/uc/course")
    public String mycourse(Model model, HttpServletRequest request) {
        try {
            // 查询免费课程
            List<CourseDto> freecourses = courseService.getFreeCourseList(getLoginUserId(request),6L);
            // 查询购买过的课程
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(getLoginUserId(request));
            // 获得所有推荐课程
            Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            model.addAttribute("buycourses", courseDtos);// 购买的课程
            model.addAttribute("freecourses", freecourses);// 免费的课程 推荐6条
            model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
            //讲师推荐未查询 js 获取 
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return uc_mycourse;
    }
    /**
     * 我的直播
     */
    @RequestMapping("/uc/mylive")
    public String myLive(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            // 查询购买过的课程和直播
            List<CourseDto> courseDtos = courseService.getUserBuyLiveCourseList(getLoginUserId(request), page);
            // 获得所有推荐课程
            Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
            model.addAttribute("buycourses", courseDtos);// 购买的课程
            model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
            model.addAttribute("page", page);// 推荐课程
            //讲师推荐未查询 js 获取 
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return uc_live;
    }

    /**
     * 开通课程验证
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/course/check/{id}")
    @ResponseBody
    public Map<String,Object> checkCourse(@PathVariable Long id, HttpServletRequest request) {
        try {
            Course course=courseService.getCourseById(id);
            if(course.getIsavaliable()!=0){
            	this.setJson(false, "课程已下架", null);
            }else if(course.getCurrentprice().doubleValue()<=0){
            	this.setJson(false, "开通课程金额不能为0", null);
            }else{
            	this.setJson(true, "true", null);
            }
        } catch (Exception e) {
            logger.error("CourseController.checkCourse",e);
            this.setJson(false, "error", null);
        }
        return json;
    }
    
    

	/**
	 * 获取收藏列表
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/fav")
	public ModelAndView favouriteCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(favouriteList);
		try {
			this.setPage(page);
			//用户收藏课程
			List<FavouriteCourseDTO> courseList = courseFavoritesService.getFavouriteCourseDTO(getLoginUserId(request), this.getPage());
			//收藏最多
			List<FavouriteCourseDTO> courseMoreList = courseFavoritesService.getMoreFavouriteCourse();
			// 获得所有推荐课程
			Map<String, List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
			modelAndView.addObject("courseList", courseList);
			modelAndView.addObject("courseMoreList", courseMoreList);
			modelAndView.addObject("mapCourseList", mapCourseList);
			modelAndView.addObject("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("favouriteCourseList" + e);
            return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
    /**
     * 删除收藏课程
     * @param courseIds
     * @return
     */
    @RequestMapping("/uc/del")
    public ModelAndView delCourseFav(HttpServletRequest request,@RequestParam("sellIdArr") String courseIds){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/uc/fav");
    	try {
    		//课程方法删除
    		courseFavoritesService.deleteCourseFavoritesById(courseIds);
		} catch (Exception e) {
			e.printStackTrace();
            return new ModelAndView(setExceptionRequest(request, e));
		}
    	return modelAndView;
    }
	
}