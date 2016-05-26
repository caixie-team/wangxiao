package com.atdld.os.mobile.course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.constants.enums.SellType;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.CourseKpoint;
import com.atdld.os.edu.entity.course.CourseStudyhistory;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.course.CourseKpointService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.CourseStudyhistoryService;
import com.atdld.os.edu.service.member.MemberRecordService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.edu.service.website.WebsiteProfileService;

/**
 * CourseKpoint管理接口 User:  Date: 2014-05-27
 */
@Controller
public class MobileKpointController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(MobileKpointController.class);

	// 课程详情
	private static final String getKopintHtml = getViewPath("/course/videocode");
	// 课程播放
	private static final String playkpoint = getViewPath("/course/videoplayer");
    //直播课程播放
    private static final String livePlaykpoint = getViewPath("/course/live_play_kpoint");

	private static final String callBack56Uploading = getViewPath("/course/callBack56_uploading");//56视频上传回调

	@Autowired
	private TrxorderService trxorderService;

	@Autowired
	private CourseKpointService courseKpointService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private MemberRecordService memberRecordService;
	@Autowired
	private WebHessianService webHessianService;
	@Autowired
	private UserService userService;
	private MemCache memcache = MemCache.getInstance();
	/**
	 * 获得视频播放的html
	 * 
	 * @return
	 */
	@RequestMapping("/front/ajax/getKopintHtml")
	public String getKopintHtml(Model model, HttpServletRequest request) {
		try {
			Long kpointId = Long.valueOf(request.getParameter("kpointId"));
			// 查询节点
			CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);
			
			// 当传入数据不正确时直接返回
			if (ObjectUtils.isNull(courseKpoint)) {
				return getKopintHtml;
			}
			if(courseKpoint.getIsfree()!=1){//该节点不能试听
				//判断该课程是否可以观看
	            boolean isok=false;
	            //判断是否购买课程且未到期
	            boolean isok1 = trxorderService.checkCourseLook(courseKpoint.getCourseId(), getLoginUserId(request));
	            boolean isok2=false;
	            if(isok1!=true){
	            	isok2 = memberRecordService.checkUserMember(getLoginUserId(request),courseKpoint.getCourseId());
	            }
	            if(isok1||isok2){//已购买或已开通会员
	            	isok=true;
	            }
	            if(isok==false){
	            	model.addAttribute("isok", isok);
	            	return getKopintHtml;
	            }
			}
			
			// 视频url
			String videourl = courseKpoint.getVideourl();
			// 播放类型
			String videotype = courseKpoint.getVideotype();
			//查询cc的appId key
			Map<String,Object> map=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.cc.toString());
			if(courseKpoint.getVideotype().equalsIgnoreCase(WebSiteProfileType.letv.toString())){//如果乐视
    			Map<String,Object>	lmap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.letv.toString());
    			model.addAttribute("websiteLetvmap", lmap);
			}
			// 放入数据
			model.addAttribute("videourl", videourl);
			model.addAttribute("videotype", videotype);
			model.addAttribute("ccwebsitemap", map);
			
		} catch (Exception e) {
			logger.error("CourseKpointController.getKopintHtml", e);
			return setExceptionRequest(request, e);
		}
		return getKopintHtml;
	}

	/**
	 * 播放视频页面
	 * 
	 * 不管是课程套餐还是课程目录时都放到list(coursePackageList)中
	 * 
	 * @param model
	 * @param request
	 *            课程id
	 * @param kpointId
	 *            节点id
	 * @return
	 */
	@RequestMapping("/front/playkpoint/{id}")
	public String playkpoint(Model model, HttpServletRequest request, @PathVariable("id") Long id,
			@RequestParam(required=false) Long kpointId,RedirectAttributes redirectAttributes) {
		try {
			Course course = courseService.getCourseById(id);
			if(ObjectUtils.isNull(course)){
				redirectAttributes.addAttribute("msg","对不起该课程已经下架或者删除");
		        return "redirect:/front/success";
			}
			//判断该课程是否可以观看
            boolean isok=false;
            //判断是否购买课程且未到期
            boolean isok1 = trxorderService.checkCourseLook(id, getLoginUserId(request));
            boolean isok2=false;
            if(isok1!=true){
            	isok2 = memberRecordService.checkUserMember(getLoginUserId(request),course.getId());
            }
            if(isok1||isok2){//已购买或已开通会员
            	isok=true;
            }
			logger.info("++isok:" + isok);
			if(isok!=true){
				return "redirect:/front/couinfo/"+id;//不符合观看资格返回课程详情
			}
			
			// 显示目录树list,课程时也放到此list.size为1而已
			List<CourseDto> courseDtos = new ArrayList<CourseDto>();
			// 获取套餐的课程列表
			if (course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())) {
				List<Long> alist = new ArrayList<Long>();
				alist.add(course.getId());
				courseDtos = courseService.getCourseListPackage(alist);
				if (ObjectUtils.isNotNull(courseDtos)) {
					model.addAttribute("currentCourseId", courseDtos.get(0).getId());
				}
			} else {
				CourseDto courseDto = new CourseDto();
				courseDto.setId(course.getId());
				courseDto.setName(course.getName());
				courseDto.setCurrentprice(course.getCurrentprice());
				courseDtos.add(courseDto);
				model.addAttribute("currentCourseId", course.getId());
			}

			model.addAttribute("coursePackageList", courseDtos);// 不是套餐课程时就只放的当前课程
			model.addAttribute("course", courseService.getCourseById(id));
			model.addAttribute("kpointId", kpointId);
            //通过标识判断跳转是直播页面还是 播放页面
            if("live".equals(request.getParameter("falg"))){
                //直播
                return livePlaykpoint;
            }else{
                return playkpoint;
            }

		} catch (Exception e) {
			return setExceptionRequest(request, e);
		}
	}
  /*  @RequestMapping("/front/playLive/{id}")
    public String playLive(Model model, HttpServletRequest request, @PathVariable("id") Long id,
                             @RequestParam(required=false) Long kpointId,RedirectAttributes redirectAttributes) {
        try {
            Course course = courseService.getCourseById(id);
            model.addAttribute("course", course);
            return livePlaykpoint;

        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
    }*/
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
	/**
	 * 添加播放次数
	 * 
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/course/playertimes")
	@ResponseBody
	public Object addCoursePlayerTime(HttpServletRequest request, @ModelAttribute("courseId") Long courseId,@ModelAttribute("kpointId") Long kpointId) {
		// 要更新3个表 edu_course_profile,edu_course_studyhistory,edu_course_kpoint
		try {
			Map<String,String> map=new HashMap<String, String>();
			map.put("courseId", courseId+"");
			map.put("kpointId", kpointId+"");
			map.put("userId", getLoginUserId(request)+"");
			webHessianService.mobilePlayertimes(map);
			//添加播放次数同时添加积分
			Map<String,String> integralMap=new HashMap<String, String>();
            integralMap.put("keyWord",IntegralKeyword.watch_video.toString() );
            integralMap.put("userId",getLoginUserId(request)+"" );
            integralMap.put("other", kpointId+"" );
            integralMap.put("fromUserId",0L+"" );
            integralMap.put("otherScorel","" );
            webHessianService.addUserIntegral(integralMap);
			this.setJson(true, "", null);
		} catch (Exception e) {
			return setExceptionRequest(request, e);
		}
		return json;
	}
	 /**
     * 获得56上传成功之后的回调
     */
    @RequestMapping("/api/56/callBack")
    public String callBack56Uploading(){
        return callBack56Uploading;
    }
}