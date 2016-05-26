package io.wangxiao.edu.mobile.front;


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

import io.wangxiao.edu.home.common.MobileBaseController;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.user.UserFeedback;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.user.UserFeedbackService;
import io.wangxiao.edu.home.service.website.WebsiteImagesService;

@Controller
public class MobileFrontController extends MobileBaseController {

	private static final Logger logger = LoggerFactory.getLogger(MobileFrontController.class);

	private static String getIndexpage = getViewPath("/front/index");
	private static String getStartpage = getViewPath("/front/start_page");
	private static String msg_success = getViewPath("/common/msg_success");
	private static String feedback = getViewPath("/front/help-fb");
	


	@Autowired
	private CourseService courseService;
	@Autowired
	private WebsiteImagesService websiteImagesService;
	@Autowired
	private UserFeedbackService userFeedbackService;
	 // 绑定变量名字和属性，参数封装进类
    @InitBinder("userFeedBack")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userFeedBack.");
    }

	/**
	 * 开始页
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobile/start")
	public String getStartpage(HttpServletRequest request) {
		return getStartpage;
	}

	/**
	 * 消息页
	 * @param request
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
	@RequestMapping("/mobile/index")
	public String getIndexpage(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			
		    //获得banner图
		    Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
		    model.addAttribute("websiteImages", websiteImages);
			
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
	 * @param request
	 * @return
	 */
	@RequestMapping("/mobile/feedback")
	public String getMobileFeedback(HttpServletRequest request) {
		return feedback;
	}

	/**
	 * 添加意见反馈
	 * @param request
	 * @param userFeedback
	 * @return
	 */
	@RequestMapping("/mobile/feedback/add")
	@ResponseBody
	public Map<String,Object> addMobileFeedback(HttpServletRequest request,UserFeedback userFeedback) {
		Map<String, Object> json=null;
		try {
			//联系方式  手机/电话/邮箱
			String contact=request.getParameter("contact");
			if(contact!=null&&!contact.equals("")){
				// 如果是手机格式
				String regEx = "^1{1}[0-9]{10}$"; // 表示a或F
				Pattern pat = Pattern.compile(regEx);
				Matcher mat = pat.matcher(contact);
				boolean rs = mat.find();
				if (rs) {
					userFeedback.setMobile(contact);
					userFeedback.setQq("");
					userFeedback.setEmail("");
				}else{
					// 如果是邮箱格式
					Pattern emailRes = Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
					mat = emailRes.matcher(contact);
					boolean emailF = mat.matches();
					if (emailF) {
						userFeedback.setEmail(contact);
						userFeedback.setMobile("");
						userFeedback.setQq("");
					}else{
						userFeedback.setQq(contact);
						userFeedback.setMobile("");
						userFeedback.setEmail("");
					}
				}
			}
			userFeedback.setName("");
			userFeedbackService.addUserFeedback(userFeedback);
			json=this.getJsonMap(true, "true", null);
		} catch (Exception e) {
			logger.error("MobileFrontController.addMobileFeedback(", e);
			json=this.getJsonMap(false, "error", null);
		}
		return json;
	}
	
}
