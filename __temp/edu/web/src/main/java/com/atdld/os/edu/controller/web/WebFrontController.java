package com.atdld.os.edu.controller.web;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.PropertiesReader;
import com.atdld.os.core.util.PropertyUtil;
import com.atdld.os.core.util.security.PurseSecurityUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.article.Article;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryTeacher;
import com.atdld.os.edu.entity.course.Teacher;
import com.atdld.os.edu.entity.customer.CustomerCourse;
import com.atdld.os.edu.entity.customer.QueryCustomerCourse;
import com.atdld.os.edu.entity.help.HelpMenu;
import com.atdld.os.edu.service.article.ArticleService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.TeacherService;
import com.atdld.os.edu.service.customer.CustomerCourseService;
import com.atdld.os.edu.service.help.HelpMenuService;
import com.atdld.os.edu.service.website.WebsiteImagesService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.sysuser.entity.QuerySubject;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.service.SubjectService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName com.atdld.os.edu.controller.web.WebFrontController
 * @description
 * @author :
 * @Create Date : 2014年6月7日 下午2:16:56
 */
@Controller
public class WebFrontController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(WebFrontController.class);

	private static String getIndexpage = getViewPath("/front/index");
	
	private static String getHelpCenter = getViewPath("/front/helpCenter");
	
	private static String getDialogHtml="/common/dialog";
	private static String guide_one=getViewPath("/guideOne/guide_one");
	private static String guide_two=getViewPath("/guideTwo/guide_two");

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
	private CustomerCourseService customerCourseService;
	@Autowired
    private HelpMenuService helpMenuService;
	/**
	 * 首页获取网站首页数据
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String getIndexpage(HttpServletRequest request, Model model) {
		try {
		    //获得banner图
		    Map<String, Object> websiteImages = websiteImagesService.getIndexPageBanner(null);
		    model.addAttribute("websiteImages", websiteImages);
		    
		    // 查询公告
            List<Article> noticeList = articleService.queryArticleIndex();
            model.addAttribute("noticeList", noticeList);
            
			// 查询所有专业
			QuerySubject querySubject = new QuerySubject();
			querySubject.setParentId(0L);
			List<Subject> subjectList = subjectService.getSubjectListByLevel(querySubject);
			model.addAttribute("subjectList", subjectList);
			// 查询文章排行
            List<Article> articleList = articleService.queryArticleListOrderclickTimes(10);
			model.addAttribute("articleList", articleList);
			// 查询老师  讲师团队
			PageEntity page = new PageEntity();
			page.setPageSize(12);
            List<QueryTeacher> teacherList = teacherService.queryTeacherListPage(new Teacher(), page);
			model.addAttribute("teacherList", teacherList);
			//获取自定义课程
			List<QueryCustomerCourse> QueryCustomerCourseList=customerCourseService.getPageQueryCusCourseList(4);
			model.addAttribute("QueryCustomerCourseList", QueryCustomerCourseList);
			//获取自定义课程排行榜
			List<CustomerCourse> cusCourseRankList=customerCourseService.getCustomCourseRankings(10);
			model.addAttribute("cusCourseRankList", cusCourseRankList);
			//查询加入自定义课程的人数
			int cusCourseJoinNum=customerCourseService.queryJoinNum();
			model.addAttribute("cusCourseJoinNum", cusCourseJoinNum);
			
			// 获得所有推荐课程
			Map<String,List<CourseDto>> mapCourseList = courseService.getCourseListByHomePage(0L);
			model.addAttribute("mapCourseList", mapCourseList);// 推荐课程
			
		} catch (Exception e) {
			logger.error("WebFrontController.getIndexpage", e);
			return setExceptionRequest(request, e);
		}
		return getIndexpage;
	}
	
	/**
	 * 获取热门的课程
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/getHotCourse")
	@ResponseBody
	public Object getHotCourse(HttpServletRequest request){
		try {
			//热门课程搜索
			List<Course> courseList=  courseService.getCourseListByBro();
			this.setJson(true, "", courseList);
		} catch (Exception e) {
			logger.error(".getHotCourse",e);
			return setExceptionRequest(request, e);
		}
		return json;
	}
	
	
	@RequestMapping("/front/mdfiy")
	@ResponseBody
	 public Object mdfiy(HttpServletRequest request,HttpServletResponse response){
	        try {
	            String k=request.getParameter("k");
	            String v=request.getParameter("v");
	            PropertyUtil prosecurity = PropertyUtil.getInstance("prosecurity");
	            String domiankey=PurseSecurityUtils.decryption(prosecurity.getProperty("domiankey"), CommonConstants.SecurityKey);
	            JsonParser jsonParser = new JsonParser();
	            JsonObject jsonObject  = jsonParser.parse(domiankey).getAsJsonObject();
	            if(k.equals("l")){
	                CommonConstants.l=v;
	                jsonObject.addProperty("l", v);
	            }else if(k.equals("w")){
	                CommonConstants.w=v;
	                jsonObject.addProperty("w", v);
	            }
	            domiankey=PurseSecurityUtils.secrect(jsonObject.toString(), CommonConstants.SecurityKey);
	            PropertiesReader.setValue("prosecurity", "domiankey", domiankey);
	            this.setJson(true,  "success", null);
	        } catch (Exception e) {
	            this.setJson(true,  "failure", null);
	        }
	        return json;
	}
	/**
	 * 帮助中心
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/help")
	public String getHelpCenter(HttpServletRequest request,Model model) {
		try {
			//右侧显示内容的二级菜单id
			String id=request.getParameter("id");
			//帮助中心菜单集合，不含内容
			List<List<HelpMenu>> helpMenus=helpMenuService.getHelpMenuAll();
		    model.addAttribute("helpMenus", helpMenus);
		    
			//右侧显示内容
		    HelpMenu helpMenuContent=null;
		    if(id!=null&&!id.equals("")){
		    	helpMenuContent = helpMenuService.getHelpMenuById(Long.parseLong(id));
		    }else if(helpMenus.size()>0&&helpMenus.get(0).get(1)!=null){
		    	helpMenuContent = helpMenuService.getHelpMenuById(helpMenus.get(0).get(1).getId());
		    }
			model.addAttribute("helpMenuContent", helpMenuContent);// 推荐课程
			
		} catch (Exception e) {
			logger.error("WebFrontController.getHelpCenter", e);
			return setExceptionRequest(request, e);
		}
		return getHelpCenter;
	}
	
	/**
	 * 导航one
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/guideOne")
	public String guideOne(HttpServletRequest request){
		return guide_one;
	}
	/**
	 * 导航two
	 * @param request
	 * @return
	 */
	@RequestMapping("/front/guideTwo")
	public String guideTwo(HttpServletRequest request){
		return guide_two;
	}
	
	@RequestMapping("/front/paomadeng")
	@ResponseBody
	public Map<String,Object> paomadeng(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			JsonObject jsonObject=SingletonLoginUtils.getLoginUser(request);
			if(ObjectUtils.isNotNull(jsonObject)){
				map.put("username", jsonObject.get("email"));
			}else{
				map.put("username", "");
			}
			map.put("status", 1);
			map.put("msg", "");
			map.put("fontSize", 18);
			map.put("fontColor", "0xffffff");
			map.put("speed", 200);
			map.put("filter", "on");
			map.put("setting", 3);
			map.put("alpha", 0.5);
			map.put("filterAlpha", 1);
			map.put("filterColor", "0x000000");
			map.put("blurX", 2);
			map.put("blurY", 2);
			map.put("interval", 5);
			map.put("lifeTime", 3);
			map.put("tweenTime", 1);
			map.put("strength", 4);
		} catch (Exception e) {
			logger.error("paomadeng---error",e);
		}
		return map;
	}
}
