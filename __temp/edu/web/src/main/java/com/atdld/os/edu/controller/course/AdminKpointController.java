package com.atdld.os.edu.controller.course;

import java.lang.reflect.Type;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.util.UploadLetvCloud;
import com.atdld.os.common.util.Vedio56Util;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.course.CourseKpoint;
import com.atdld.os.edu.service.course.CourseKpointService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.website.WebsiteProfileService;

/**
 * Course管理接口 User:  Date: 2014-05-27
 */
@Controller
@RequestMapping("/admin")
public class AdminKpointController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminKpointController.class);

	// 课程列表
	private static final String kpointList = getViewPath("/admin/kpoint/kpoint_list");// 课程列表
	private static final String toUploadLetv=getViewPath("/admin/course/upload_letv");
	private static final String newKpointList = getViewPath("/admin/kpoint/kpoint_lists");// 视频节点列表
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private WebsiteProfileService websiteProfileService;

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("courseKpoint")
	public void initBinderCourseKpoint(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("courseKpoint.");
	}

	@Autowired
	private CourseKpointService courseKpointService;

	/**
	 * 课程的视频列表
	 */
	@RequestMapping("/kpoint/list/{id}")
	public String showKpointList(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			CourseKpoint courseKpoint = new CourseKpoint();
			courseKpoint.setCourseId(id);
			List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
			request.setAttribute("courseId", id);
		} catch (Exception e) {
			logger.error("AdminKpointController.showKpointList", e);
			return setExceptionRequest(request, e);
		}
		return kpointList;
	}

	/** 查询课程下的视频节点
	 * @return
	 */
	@RequestMapping("/kpoint/query/{id}")
	@ResponseBody
	public Map<String, Object> queryKpoint(@PathVariable("id") Long id, HttpServletRequest request,HttpServletResponse response) {
		try {
			CourseKpoint courseKpoint = new CourseKpoint();
			courseKpoint.setCourseId(id);
			List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);

			if (ObjectUtils.isNotNull(courseKpointList)) {
				this.setJson(true, "", courseKpointList);
			} else {
				this.setJson(false, "", null);
			}
		} catch (Exception e) {
			logger.error("AdminPointAction.queryKpoint", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	/** 查询课程下的视频节点
	 * @return
	 */
	@RequestMapping("/kpoint/querytype/{id}")
	@ResponseBody
	public Map<String, Object> querytype(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CourseKpoint courseKpoint = new CourseKpoint();
			courseKpoint.setCourseId(id);
			//文件夹节点
			courseKpoint.setType(1L);
			List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);

			if (ObjectUtils.isNotNull(courseKpointList)) {
				this.setJson(true, "", courseKpointList);
			} else {
				this.setJson(false, "", null);
			}
		} catch (Exception e) {
			logger.error("AdminPointAction.queryKpoint", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	/** 查询课程下的视频节点
	 * @return
	 */
	@RequestMapping("/kpoint/add")
	@ResponseBody
	public Map<String, Object> add( @ModelAttribute("courseKpoint") CourseKpoint courseKpoint, HttpServletRequest request) {
		try {
			if(ObjectUtils.isNull(courseKpoint.getId())){
				courseKpoint.setStatus(0L);
				courseKpointService.addCourseKpoint(courseKpoint);
			}else{
				courseKpointService.updateCourseKpoint(courseKpoint);
			}
			 this.setJson(true, "", null);
		} catch (Exception e) {
			logger.error("AdminPointAction.queryKpoint", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
	
	// 批量删除
    @RequestMapping("/kpoint/delBatch")
    @ResponseBody
    public Map<String, Object> delPointByPointId(HttpServletRequest request,
            HttpServletResponse response) {
        String pointIds = request.getParameter("pointIds");
        try {
        	String [] parray=pointIds.replaceAll(" ", "").split(",");
        	if(!ObjectUtils.isNull(parray)){
        		courseKpointService.deleteCourseKpointByIdBatch(parray);
        	}
            this.setJson(true, "", null);
            return json;
        } catch (Exception e) {
            logger.error("AdminPointAction.delPointByPointId", e);
            this.setJson(false, "error", null);
            return json;
        }
    }
    /**
     * 获得56上传的url
     */
    @RequestMapping("/56/uploadurl")
    @ResponseBody
    public Map<String, Object> query56UploadingUrl() {
        try {
        	Map<String,String> map=getVideoTypeInfo(WebSiteProfileType.p56.toString());//获取56配置
            Vedio56Util vedio56Util = new Vedio56Util("http://oapi.56.com", "/video/diyupload.plugin",map.get("p56appID"),map.get("p56appKEY"));
            String url = vedio56Util.getVideoComponenUrl("268xue", "cDElM0RwMSUyNnAyJTNEcDIlMjZvbiUzRG9uJTI2b24lM0RvbiUyNm9uJTNEb24lMjZsJTNEY24lMjZjJTNEcDEwJTI2aSUzRDE",
            		CommonConstants.contextPath + "/api/56/callBack", CommonConstants.contextPath + "/api/56/callBack", "Y", "268xue");
            this.setJson(true, "", url);
        } catch (Exception ex) {
            logger.error("query56UploadingUrl", ex);
            this.setJson(false, "error", null);
            return json;
        }
        return json;
    }
    /**
     * 获取56配置
     * @return
     */
    public Map<String,String> getVideoTypeInfo(String profileType){
    	//获得56配置
    	Map<String,Object> map=websiteProfileService.getWebsiteProfileByType(profileType);
    	JsonParser jsonParser= new JsonParser();
    	//获得详细info
    	JsonObject jsonObject=jsonParser.parse(gson.toJson(map.get(profileType))).getAsJsonObject();
    	if(!jsonObject.isJsonNull()){
    		Map<String,String> websitemap = new HashMap<String,String>();
    		if(profileType.equals(WebSiteProfileType.p56.toString())){
    			websitemap.put("p56appID", jsonObject.get("p56appID").getAsString());//56key
        		websitemap.put("p56appKEY", jsonObject.get("p56appKEY").getAsString());//
    		}else if(profileType.equals(WebSiteProfileType.letv.toString())){
    			websitemap.put("user_unique", jsonObject.get("user_unique").getAsString());//56key
        		websitemap.put("secret_key", jsonObject.get("secret_key").getAsString());//
    		}
    		return websitemap;
    	}
    	return null;
    }
    /**
     * 跳转乐视上传
     * @return
     */
    @RequestMapping("/letv/toletv")
    public String toUploadLetv(){
    	return toUploadLetv;
    }
    /**
     * 获取letv上传url
     * @param request
     * @return
     */
    @RequestMapping("/letv/uploadurl")
    @ResponseBody
    public Map<String,Object> initLetvUrl(HttpServletRequest request){
    	try{
    		String videoName=request.getParameter("videoName");
    		String courseId=request.getParameter("courseId");
    		Course course=courseService.getCourseById(Long.parseLong(courseId));
    		Map<String,String> map=getVideoTypeInfo(WebSiteProfileType.letv.toString());
            //初始化乐视云
            UploadLetvCloud uploadLetvCloud = new UploadLetvCloud(map.get("user_unique"),map.get("secret_key"));
            //定义输出格式 (json|xml)
            uploadLetvCloud.format = "json";
            //视频上传初始化（Web方式）
            String response = uploadLetvCloud.videoUploadFlash(videoName!=""?course.getName()+"-"+videoName:"乐视云视频","letv_callback",450,260);
            if(StringUtils.isNotEmpty(response)){
            	Map<String, Object> letvmap= new HashMap<String,Object>();
            	JsonParser jsonParser = new JsonParser();
            	//获得json对象
            	JsonObject jsonObject=jsonParser.parse(response).getAsJsonObject();
            	Type typeToken = new TypeToken<Map<String, Object>>() {}.getType();
            	letvmap=gson.fromJson(jsonObject.get("data"),typeToken);
            	Map<String, Object> lmap= new HashMap<String,Object>();
            	lmap.put("letvmap", letvmap);
            	lmap.put("letvjson", gson.toJson(jsonObject.get("data")));
            	this.setJson(true, "init is success", lmap);
            	return json;
            }
            this.setJson(false, "init upload error", null);
    	}catch(Exception e){
    		logger.error("initLetvUrl", e);
    		this.setJson(false, "init upload error", null);
    	}
    	return json;
    }
    /**
	 * 课程的视频列表
	 */
	@RequestMapping("/newkpoint/list")
	public String showNewKpointList(HttpServletRequest request,@ModelAttribute("courseKpoint")CourseKpoint courseKpoint,@ModelAttribute("page") PageEntity page) {
		try {
			List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointNewList(courseKpoint,page);
			request.setAttribute("list", courseKpointList);
		} catch (Exception e) {
			logger.error("AdminKpointController.showKpointList", e);
			return setExceptionRequest(request, e);
		}
		return newKpointList;
	}
	
	/**
	 * 复制节点
	 * @param request
	 * @return
	 */
	@RequestMapping("/copy/kpoints")
	@ResponseBody
	public Map<String,Object> copyKpointList(HttpServletRequest request) {
		try {
			
			String kpoints=request.getParameter("copyKpoints");//复制的节点ids
			Long copyCourseId=Long.parseLong(request.getParameter("copyCourseId"));
			Course course=courseService.getCourseById(copyCourseId);
			if(course==null){
				this.setJson(false, "课程不存在", null);
				return json;
			}
			//ids查询节点集合
			List<CourseKpoint> courseKpoints=courseKpointService.getCourseKpointListByIds(kpoints);
			List<CourseKpoint> childKpointList=new ArrayList<CourseKpoint>();//所有子节点集合
			Map<String,CourseKpoint> map=new HashMap<String, CourseKpoint>();//id键map集合
			for(CourseKpoint courseKpoint:courseKpoints){
				map.put(courseKpoint.getId()+"", courseKpoint);
			}
			//子节点添加到父节点中
			for(Map.Entry<String,CourseKpoint> entry: map.entrySet()) { 
				CourseKpoint courseKpoint=entry.getValue();
				if(courseKpoint.getParentId().longValue()>0){//子节点
					CourseKpoint parentKpoint = map.get(courseKpoint.getParentId()+"");//父节点
					parentKpoint.getChildKpoints().add(courseKpoint);//添加子节点到父节点中
				}
			} 
			//保存父节点
			for(Map.Entry<String,CourseKpoint> entry: map.entrySet()) { 
				CourseKpoint courseKpoint=entry.getValue();
				if(courseKpoint.getParentId().longValue()==0){//父节点
					List<CourseKpoint> childKpoints=courseKpoint.getChildKpoints();//子节点集合
					courseKpoint.setId(null);//id
					courseKpoint.setCourseId(copyCourseId);//课程id
					courseKpointService.addCourseKpoint(courseKpoint);//添加父节点
					if(childKpoints.size()>0){
						for(CourseKpoint childKpoint: childKpoints){//子节点设置父id课程id并添加到大集合中
							childKpoint.setId(null);//id
							childKpoint.setCourseId(copyCourseId);//课程id
							childKpoint.setParentId(courseKpoint.getId());//父Id
							childKpointList.add(childKpoint);
						}
					}
				}
			} 
			//批量保存子节点
			if(childKpointList.size()>0){
				courseKpointService.createChildCourseKpointList(childKpointList);
			}
			this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("AdminKpointController.copyKpointList", e);
			this.setJson(false, "系统错误", null);
		}
		return json;
	}
}