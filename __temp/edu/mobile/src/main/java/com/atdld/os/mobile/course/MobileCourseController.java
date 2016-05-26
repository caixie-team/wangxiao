package com.atdld.os.mobile.course;


import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.MobileBaseController;
import com.atdld.os.edu.constants.enums.CourseProfileType;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.constants.enums.SellType;
import com.atdld.os.edu.entity.course.*;
import com.atdld.os.edu.entity.member.MemberType;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.article.ArticleService;
import com.atdld.os.edu.service.course.*;
import com.atdld.os.edu.service.member.MemberRecordService;
import com.atdld.os.edu.service.member.MemberTypeService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserIntegralService;
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

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.SortedMap;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
@Controller
public class MobileCourseController extends MobileBaseController {
    private static final Logger logger = LoggerFactory.getLogger(MobileCourseController.class);
    // 课程列表
    private static final String courseList = getViewPath("/course/course-list");
    // 课程列表ajax
    private static final String courseListAjax = getViewPath("/course/course-list-ajax");
    //课程详细检索条件
    private static final String courseConditionAjax = getViewPath("/course/course-condition-ajax");
    
    // 直播列表
    private static final String liveList = getViewPath("/course/live-list");
    // 直播列表ajax
    private static final String liveListAjax = getViewPath("/course/live-list-ajax");
    // 课程详情
    private static final String couinfo = getViewPath("/course/course-infor");
    // 课程详情 目录
    private static final String couinfoCatalog = getViewPath("/course/course-infor-catalog");
    // 课程详情 评论
    private static final String couinfoAssess = getViewPath("/course/course-infor-discuss");
 // 课程详情 评论 ajax
    private static final String couinfoAssessAjax = getViewPath("/course/course-infor-discuss-ajax");
    // 直播课程详情
    private static final String couLiveinfo = getViewPath("/course/course-live-infor");

    //首页专业切换时ajax获取
    private static final String courses_ajax = getViewPath("/course/courses_ajax");
    private static final String uc_mycourse=getViewPath("/ucenter/my-course");
    private static final String uc_live=getViewPath("/ucenter/my-live");
    private static final String exchange_course=getViewPath("/ucenter/exchange-course");
    
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
	private CourseAssessService courseAssessService;
	
	@Autowired
	private UserIntegralService userIntegralService;
	
	@Autowired
	private WebHessianService webHessianService;
	
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }
    
    @InitBinder("courseAssess")
    public void initBinderCourseAssess(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseAssess.");
    }

    /**
     * 课程列表展示,搜索课程
     * 
     * @return
     */
    @RequestMapping("/mobile/course/list")
    public String showCourseList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,QueryCourse queryCourse) {
    	try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(6);
            queryCourse.setOrder(2);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType("NOLIVE");//出去直播的课程
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            
            // 查询所有专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
           
            
            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", this.getPage());
            request.setAttribute("teacherList", teacherList);
            request.setAttribute("subjectList", subjectList);
//            //查找售卖方式开关配置
//    		Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
//    		request.setAttribute("saleMap", saleMap);
//            //查询会员类型
//            MemberType memberType=memberTypeService.getMemberTypeById(queryCourse.getMembertype());
//            request.setAttribute("memberType", memberType);
//            //会员类型
//            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
//            request.setAttribute("memberTypes",memberTypes);
//            if (ObjectUtils.isNotNull(queryCourse.getTeacherId())) {
//                request.setAttribute("teacher", teacherService.getTeacherById(queryCourse.getTeacherId()));
//            }
            //拼接查询条件
            String condition="";
            //查询教师
            if(queryCourse.getTeacherId()!=null&&queryCourse.getTeacherId()>0){
            	Teacher teacher=teacherService.getTeacherById(queryCourse.getTeacherId());
            	condition+=teacher.getName()+",";
            }
            if(queryCourse.getSubjectId()!=null&&queryCourse.getSubjectId()>0){
	            //查询专业
	            Subject subject=new Subject();
	            subject.setSubjectId(queryCourse.getSubjectId());
	            subject=subjectService.getSubjectBySubjectId(subject);
	            condition+=subject.getSubjectName()+",";
            }
            if(queryCourse.getIsPay()>-1){
            	if(queryCourse.getIsPay()>0){
            		condition+="收费课程";
            	}else{
            		condition+="免费课程";
            	}
            }
            request.setAttribute("condition", condition.replace(","," ").trim().replace(" ", ","));
            
        } catch (Exception e) {
            logger.error("MobileCourseController.showCourseList", e);
            return setExceptionRequest(request, e);
        }
        return courseList;
    }
    
    /**
     * 课程列表展示,搜索课程详细条件
     * 
     * @return
     */
    @RequestMapping("/mobile/course/ajax/condition")
    public String showCourseCondition(HttpServletRequest request,QueryCourse queryCourse) {
    	try {
            // 查询所有专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            // 全部教师
            List<Teacher> teacherList = teacherService.getTeacherList(new Teacher());
    
            request.setAttribute("page", this.getPage());
            request.setAttribute("teacherList", teacherList);
            request.setAttribute("subjectList", subjectList);
            request.setAttribute("queryCourse",queryCourse);
        } catch (Exception e) {
            logger.error("MobileCourseController.showCourseCondition", e);
            return setExceptionRequest(request, e);
        }
        return courseConditionAjax;
    }
    
    /**
     * 课程列表展示,搜索课程
     * 
     * @return
     */
    @RequestMapping("/mobile/course/ajax/list")
    public String showCourseListAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page,QueryCourse queryCourse) {
    	try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(6);
            queryCourse.setOrder(2);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType("NOLIVE");//出去直播的课程
            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
//            //查找售卖方式开关配置
//    		Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
//    		request.setAttribute("saleMap", saleMap);
//            //查询会员类型
//            MemberType memberType=memberTypeService.getMemberTypeById(queryCourse.getMembertype());
//            request.setAttribute("memberType", memberType);
//            //会员类型
//            List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
//            request.setAttribute("memberTypes",memberTypes);
//            if (ObjectUtils.isNotNull(queryCourse.getTeacherId())) {
//                request.setAttribute("teacher", teacherService.getTeacherById(queryCourse.getTeacherId()));
//            }
            
        } catch (Exception e) {
            logger.error("MobileCourseController.showCourseListAjax", e);
            return setExceptionRequest(request, e);
        }
        return courseListAjax;
    }
    
    /**
     * 课程详情
     * 不管是课程套餐还是课程目录时都放到list(coursePackageList)中
     * @return
     */
    @RequestMapping("/mobile/course/info/{id}")
    public String couinfo(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(id);
            if(ObjectUtils.isNull(course)){
                request.setAttribute("msg","对不起该课程已经下架或者删除");
                return "redirect:/mobile/success";
            }
            List<MemberType> memberTypes=memberTypeService.getMemberTypesBycourse(course.getId());//课程的会员信息
            course.setMemberTypes(memberTypes);
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
            model.addAttribute("course", course);
            model.addAttribute("isok", isok);
            //更新课程的浏览数量
            Map<String,String> map=new HashMap<String, String>();
            map.put("type", CourseProfileType.viewcount.toString());//类型
            map.put("courseId", course.getId()+"");//课程id
            map.put("count", 1L+"");//1
            map.put("operation",CourseProfile.ADD);//操作
            webHessianService.updateCourseProfile(map);
            if(SellType.LIVE.toString().equals(course.getSellType())){
                return couLiveinfo;
            }
        } catch (Exception e) {
            logger.error("MobileCourseController.couinfo", e);
            return setExceptionRequest(request, e);
        }

        return couinfo;
    }
    
    /**
     * 课程目录
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/mobile/course/ajax/info/{id}/{currentCourseId}")
    public String couinfoCatalog(Model model, HttpServletRequest request, @PathVariable("id") Long id, @PathVariable("currentCourseId") Long currentCourseId) {
        try {
            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(id);
            
            //显示目录树list,课程时也放到此list.size为1
            List<CourseDto> courseDtos =new ArrayList<CourseDto>();
            //获取套餐的课程列表
            if(course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())){
                List<Long> alist= new ArrayList<Long>();
                alist.add(course.getId());
                courseDtos = courseService.getCourseListPackage(alist);
                if(ObjectUtils.isNotNull(courseDtos)){
                	if(currentCourseId==0){
                		currentCourseId=courseDtos.get(0).getId();
                	}
                }
            }else{
                courseDtos.add(course);
                currentCourseId=course.getId();
            }
            model.addAttribute("currentCourseId", currentCourseId);
            model.addAttribute("coursePackageList", courseDtos);
            model.addAttribute("course", course);
            //课程目录节点
            CourseKpoint courseKpoint=new CourseKpoint();
            courseKpoint.setCourseId(currentCourseId);
            List<CourseKpoint> courseKpoints=courseKpointService.getCourseKpointList(courseKpoint);
            Map<String,Object> map=kpointResult(courseKpoints,course.getIsPay());
            courseKpoints=(List<CourseKpoint>) map.get("courseKpoints");
            model.addAttribute("courseKpoints", courseKpoints);
            Long defaultKpointId=(Long) map.get("defaultKpointId");
            model.addAttribute("defaultKpointId", defaultKpointId);
        } catch (Exception e) {
            logger.error("MobileCourseController.couinfo", e);
            return setExceptionRequest(request, e);
        }

        return couinfoCatalog;
    }
    
    /**
     * 课程详情咨询列表
     * 
     * @return
     */
    @RequestMapping("/mobile/course/ajax/assess")
    public String consAssess(Model model,HttpServletRequest request, @RequestParam("courseId") Long courseId,@ModelAttribute("page") PageEntity page) {
        try {
            //分页页数
            page.setPageSize(6);
            //查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            courseAssess.setKpointId(0L);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            model.addAttribute("userId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("MobileCourseController.assess", e);
            return setExceptionRequest(request, e);
        }
        return couinfoAssess;
    }
    
    /**
     * 课程详情评价列表
     * 
     * @return
     */
    @RequestMapping("/mobile/assess/add")
    @ResponseBody
    public Map<String, Object> addassess(HttpServletRequest request, CourseAssess courseAssess) {
        try {
        	//添加评论
        	Map<String,String> map=new HashMap<String, String>();
        	map.put("userId",getLoginUserId(request)+"" );
        	map.put("courseId",courseAssess.getCourseId()+"" );
        	map.put("kpointId",courseAssess.getKpointId()+"" );
        	map.put("content",WebUtils.replaceTagHTML(courseAssess.getContent()));
            webHessianService.mobileAddCourseAssess(map);
            //添加课程评论添加积分
            Map<String,String> integralMap=new HashMap<String, String>();
            integralMap.put("keyWord",IntegralKeyword.assess.toString() );
            integralMap.put("userId",getLoginUserId(request)+"" );
            integralMap.put("other",courseAssess.getCourseId()+"" );
            integralMap.put("fromUserId",0L+"" );
            integralMap.put("otherScorel","" );
            webHessianService.addUserIntegral(integralMap);
            this.setJson(true, "true", null);
        		
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            this.setJson(false, "error", null);
            
        }
        return json;
    }
    
    /**
     * 课程详情咨询列表Ajax
     * 
     * @return
     */
    @RequestMapping("/mobile/course/ajax/more/assess")
    public String consAssessAjax(Model model,HttpServletRequest request, @RequestParam("courseId") Long courseId,@ModelAttribute("page") PageEntity page) {
        try {
            //分页页数
            page.setPageSize(6);
            //查询该课程下的评论
            CourseAssess courseAssess = new CourseAssess();
            courseAssess.setCourseId(courseId);
            courseAssess.setKpointId(0L);
            List<QueryCourseAssess> courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
            model.addAttribute("courseAssessList", courseAssessList);
            model.addAttribute("page", page);
            model.addAttribute("userId", getLoginUserId(request));
        } catch (Exception e) {
            logger.error("CourseAssessController.assess", e);
            return setExceptionRequest(request, e);
        }
        return couinfoAssessAjax;
    }
    
    /**
     * 直播列表展示,搜索直播
     *
     * @return
     */
    @RequestMapping("/mobile/live/list")
    public String showlivelist(HttpServletRequest request, @ModelAttribute("page") PageEntity page, QueryCourse queryCourse) {
      
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(4);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType(SellType.LIVE.toString());//直播
            // 搜索课程列表
            queryCourse.setOrder(4);//根据直播开始时间排序
            
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            if(courseList!=null&&courseList.size()>0){
            	 Date date=new Date();
                 for(CourseDto course: courseList){
                 	//直播中设置距离结束分钟
                 	if(course.getLiveBeginTime().getTime()<date.getTime()&&course.getLiveEndTime().getTime()>date.getTime()){
                 		int min=daysBetweenMin(date,course.getLiveEndTime());
             			course.setEndMin(min);
             		}else if(course.getLiveBeginTime().getTime()>date.getTime()){//未开始的收费直播设置开始天数时分
             			if(course.getIsPay()>0){//收费课程
                 			Map<String,String> begin=daysBetween(date,course.getLiveBeginTime());
                 			course.setBegin(begin);
             			}
             		}
             		
                 }
            }
           
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", this.getPage());
        } catch (Exception e) {
            logger.error("CourseController.showLiveList", e);
            return setExceptionRequest(request, e);
        }
        return liveList;
    }
    
    /**
     * 直播列表展示,搜索直播
     *
     * @return
     */
    @RequestMapping("/mobile/live/ajax/list")
    public String showlivelistAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page, QueryCourse queryCourse) {
      
        try {
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(1);
            //只查询上架的
            queryCourse.setIsavaliable(0L);
            queryCourse.setSellType(SellType.LIVE.toString());//直播
            // 搜索课程列表
            queryCourse.setOrder(4);//根据直播开始时间排序
            
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            if(courseList!=null&&courseList.size()>0){
            	 Date date=new Date();
                 for(CourseDto course: courseList){
                 	//直播中设置距离结束分钟
                 	if(course.getLiveBeginTime().getTime()<date.getTime()&&course.getLiveEndTime().getTime()>date.getTime()){
                 		int min=daysBetweenMin(date,course.getLiveEndTime());
             			course.setEndMin(min);
             		}else if(course.getLiveBeginTime().getTime()>date.getTime()){//未开始的收费直播设置开始天数时分
             			if(queryCourse.getIsPay()>0){//收费课程
                 			Map<String,String> begin=daysBetween(date,course.getLiveBeginTime());
                 			course.setBegin(begin);
             			}
             		}
             		
                 }
            }
           
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", this.getPage());
        } catch (Exception e) {
            logger.error("CourseController.showLiveListAjax", e);
            return setExceptionRequest(request, e);
        }
        return liveListAjax;
    }
  
    /**   
     * 计算两个日期之间相差的天时分
     * @param bdate  较大的时间  
     * @return 相差天数  时分
     * @throws ParseException   
     */     
    public static Map<String,String> daysBetween(Date date1,Date date2) 
    {  
    	Map<String,String> map=new HashMap<String, String>();
    	Calendar cal = Calendar.getInstance(); 
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();  
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();  
        long between_days=(time2-time1)/(1000*3600*24);
        //相差时
        long hourMins=(time2-time1)%(1000*3600*24);
        long between_hours=hourMins/(1000*3600);
        //相差分
        long between_mins=0L;
        if(between_hours>0){
        	long mins=hourMins%(1000*3600);
        	between_mins=mins/(1000*60);
        }else{
        	between_mins=hourMins/(1000*60);
        }
        map.put("day", between_days+"");
        map.put("hour", between_hours+"");
        map.put("min", between_mins+"");
		return map;
    }  

    /**   
     * 计算两个日期之间相差的分钟  
     * @param bdate  较大的时间  
     * @return 相差天数  
     * @throws ParseException   
     */     
    public static int daysBetweenMin(Date date1,Date date2) 
    {  

        Calendar cal = Calendar.getInstance(); 
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();  
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();  
        long between_days=(time2-time1)/(1000*60);  
       return Integer.parseInt(String.valueOf(between_days)); 
    }  
    
    
    //封装子节点到父节点中
    public Map<String,Object> kpointResult(List<CourseKpoint> kpoints,int isPay){
    	Map<String,Object> map=new HashMap<String, Object>();
    	List<CourseKpoint> kpointList=new ArrayList<CourseKpoint>();
    	LinkedHashMap<Long,CourseKpoint> courseKpointMap=new LinkedHashMap<Long, CourseKpoint>();
    	for(CourseKpoint kpoint: kpoints){
    		courseKpointMap.put(kpoint.getId(), kpoint);
    	}
    	
    	for(Entry<Long,CourseKpoint> entry: courseKpointMap.entrySet()) { 
    		Long parentId=entry.getValue().getParentId();
    		if(parentId>0){
    			if(courseKpointMap.get(parentId)!=null){//子节点加入到父节点中
    				courseKpointMap.get(parentId).getSubKpoints().add(entry.getValue());
    			}
    		}
    	}
    	//只支持1 2级
    	for(Entry<Long,CourseKpoint> entry: courseKpointMap.entrySet()) { 
    		if(entry.getValue().getParentId()==0){
    			kpointList.add(entry.getValue());
    		}
    	}
    	map.put("courseKpoints", kpointList);
    	//默认播放节点
    	Long defaultKpointId=0L;
    	lableA: 
    	for(CourseKpoint parKpoint: kpointList) { 
			if(parKpoint.getType()==0){//课程节点
				if(isPay==0){
					defaultKpointId=parKpoint.getId();
    			}else{
    				if(parKpoint.getIsfree()==1){
    					defaultKpointId=parKpoint.getId();
    					break lableA;
    				}
    			}
			}else{//章节
				if(isPay==0){
					if(parKpoint.getSubKpoints().size()>0){
						defaultKpointId=parKpoint.getSubKpoints().get(0).getId();
					}
    			}else{
    				for(CourseKpoint subKpoint:parKpoint.getSubKpoints()){
    					if(subKpoint.getIsfree()==1){
    						defaultKpointId=subKpoint.getId();
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
     * 我的课程
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/mobile/uc/course")
    public String mycourse(Model model, HttpServletRequest request) {
        try {
            // 查询购买过的课程
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(getLoginUserId(request));
            model.addAttribute("buycourses", courseDtos);// 购买的课程
            //讲师推荐未查询 js 获取 
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return uc_mycourse;
    }
    /**
     * 我的直播
     */
    @RequestMapping("/mobile/uc/mylive")
    public String myLive(Model model, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            // 查询购买过的课程和直播
            List<CourseDto> courseDtos = courseService.getUserBuyLiveCourseList(getLoginUserId(request), page);
            model.addAttribute("buycourses", courseDtos);// 购买的课程
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return uc_live;
    }
    
    /**
     * 课程卡兑换
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/mobile/uc/course/exchange")
    public String exchangeCourse() {       
        return exchange_course;
    }
    
}