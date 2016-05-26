package com.atdld.os.mobile.front;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.MobileBaseController;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.user.UserFeedback;
import com.atdld.os.edu.service.article.ArticleService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.TeacherService;
import com.atdld.os.edu.service.user.UserFeedbackService;
import com.atdld.os.edu.service.website.WebsiteImagesService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.sysuser.service.SubjectService;

/**
 * 
 * @ClassName com.atdld.os.edu.controller.web.WebFrontController
 * @description
 * @author :
 * @Create Date : 2014年6月7日 下午2:16:56
 */
@Controller
public class MobileFrontController extends MobileBaseController {

	private static final Logger logger = LoggerFactory.getLogger(MobileFrontController.class);

	private static String getIndexpage = getViewPath("/front/index");
	private static String getStartpage = getViewPath("/front/start_page");
	private static String msg_success = getViewPath("/common/msg_success");
	private static String feedback = getViewPath("/front/help-fb");
	
	
	private static String getDialogHtml="/common/dialog";

	@Autowired
	private CourseService courseService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private WebsiteImagesService websiteImagesService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private UserFeedbackService userFeedbackService;
	@Autowired
	private WebHessianService webHessianService;
	
	 // 绑定变量名字和属性，参数封装进类
    @InitBinder("userFeedBack")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userFeedBack.");
    }
	
	/**
	 * 开始页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/start")
	public String getStartpage(HttpServletRequest request) {
		return getStartpage;
	}
	
	/**
	 * 消息页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/success")
	public String getMobileSuccess(HttpServletRequest request) {
		request.setAttribute(OrderConstans.RESMSG, request.getParameter(OrderConstans.RESMSG));
		return msg_success;
	}
	
	
	/**
	 * 首页获取网站首页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String getIndexpage(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			
		    //获得banner图
		    Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
		    model.addAttribute("websiteImages", websiteImages);
		    
//		    // 查询公告
//            List<Article> noticeList = articleService.queryArticleIndex();
//            model.addAttribute("noticeList", noticeList);
//            
//			// 查询所有专业
//			QuerySubject querySubject = new QuerySubject();
//			querySubject.setParentId(0L);
//			List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
//			model.addAttribute("subjectList", subjectList);
//			// 查询文章排行
//            List<Article> articleList = articleService.queryArticleListOrderclickTimes(10);
//			model.addAttribute("articleList", articleList);
//			// 查询老师  讲师团队
//			PageEntity page = new PageEntity();
//			page.setPageSize(12);
//            List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(new Teacher(), page);
//			model.addAttribute("teacherList", teacherList);
//			//获取自定义课程
//			List<QueryCustomerCourse> QueryCustomerCourseList=customerCourseService.getPageQueryCusCourseList(4);
//			model.addAttribute("QueryCustomerCourseList", QueryCustomerCourseList);
//			//获取自定义课程排行榜
//			List<CustomerCourse> cusCourseRankList=customerCourseService.getCustomCourseRankings(10);
//			model.addAttribute("cusCourseRankList", cusCourseRankList);
//			//查询加入自定义课程的人数
//			int cusCourseJoinNum=customerCourseService.queryJoinNum();
//			model.addAttribute("cusCourseJoinNum", cusCourseJoinNum);
			
			// 获得所有推荐课程
			Map<String,List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
			model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
			
		} catch (Exception e) {
			logger.error("MobileFrontController.getIndexpage", e);
			return setExceptionRequest(request, e);
		}
		return getIndexpage;
	}
	
	/**
	 * 意见反馈页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/feedback")
	public String getMobileFeedback(HttpServletRequest request) {
		return feedback;
	}
	
	/**
	 * 添加意见反馈
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/mobile/feedback/add")
	@ResponseBody
	public Map<String,Object> addMobileFeedback(HttpServletRequest request,UserFeedback userFeedback) {
		try {
			Map<String,String> map=new HashMap<String, String>();
			//联系方式  手机/电话/邮箱
			String contact=request.getParameter("contact");
			if(contact!=null&&!contact.equals("")){
				// 如果是手机格式
				String regEx = "^1{1}[0-9]{10}$"; // 表示a或F
				Pattern pat = Pattern.compile(regEx);
				Matcher mat = pat.matcher(contact);
				boolean rs = mat.find();
				if (rs) {
					map.put("mobile",contact);
					map.put("email","");
					map.put("qq","");
				}else{
					// 如果是邮箱格式
					Pattern emailRes = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
					mat = emailRes.matcher(contact);
					boolean emailF = mat.matches();
					if (emailF) {
						map.put("email",contact);
						map.put("mobile","");
						map.put("qq","");
					}else{
						map.put("qq",contact);
						map.put("email","");
						map.put("mobile","");
					}
				}
			}
			map.put("userId",getLoginUserId(request)+"");
			map.put("content",userFeedback.getContent());
			map.put("name","");
			webHessianService.mobileAddUserFeedback(map);
			this.setJson(true, "true", null);
		} catch (Exception e) {
			logger.error("MobileFrontController.addMobileFeedback(", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
}
