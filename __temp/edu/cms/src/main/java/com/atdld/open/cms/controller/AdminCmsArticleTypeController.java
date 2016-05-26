package com.atdld.open.cms.controller;

import com.atdld.open.cms.common.controller.CmsBaseController;
import com.atdld.open.cms.entity.type.CmsArticleType;
import com.atdld.open.cms.service.type.CmsArticleTypeService;
import com.atdld.open.common.util.DateEditor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 线下课程专业Controller
 *
 */
@Controller
@RequestMapping("/admin/line")
public class AdminCmsArticleTypeController extends CmsBaseController{
	
	private static Logger logger = Logger.getLogger(AdminCmsArticleTypeController.class);
	@Autowired
	private CmsArticleTypeService cmsArticleTypeService;
	private ModelAndView model=null;
	private String typeListPage = getViewPath("/admin/cmsType/line-article-type-list");
	
	@InitBinder("articleType")
	public void initBinderArticleType(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("articleType.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/**
	 * 修改线下分类节点的父ID
	 * @param articleType
	 * @return
	 */
	@RequestMapping("/updateTypeParentId")
	@ResponseBody
	public Map<String,Object> updateTypeParentId(@ModelAttribute("articleType") CmsArticleType articleType){
		try{
			articleType.setUpdateTime(new Date());
			cmsArticleTypeService.updateTypeParentId(articleType);
			this.setJson(true, "操作成功", null);
		}catch (Exception e) {
			this.setJson(false, "系统繁忙，请稍后再操作", null);
			logger.error("updateSubjectParentId()--error",e);
		}
		return json;
	}
	
	/**
	 * 删除分类名
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteType")
	@ResponseBody
	public Map<String,Object> deleteType(HttpServletRequest request){
		try{
			String ids = request.getParameter("ids");
			if(ids.endsWith(",")){
				ids = ids.substring(0,ids.length()-1);
			}
			cmsArticleTypeService.deleteArticleType(ids);
			this.setJson(true, "删除成功", null);
		}catch (Exception e) {
			this.setJson(false, "系统繁忙，请稍后再操作", null);
			logger.error("deleteSubject()---error",e);
		}
		return json;
	}
	
	/**
	 * 将分类显示到导航栏
	 * @param request
	 * @return
	 */
	@RequestMapping("/showNav")
	@ResponseBody
	public Map<String,Object> showNav(HttpServletRequest request){
		try{
			String ids = request.getParameter("ids");
			if(ids.endsWith(",")){
				ids = ids.substring(0,ids.length()-1);
			}
			//取消导航栏分类所有的显示
			cmsArticleTypeService.hideNav();
			//将选中的分类显示到导航栏上
			cmsArticleTypeService.showNav(ids);
			this.setJson(true, "保存成功", null);
		}catch (Exception e) {
			this.setJson(false, "系统繁忙，请稍后再操作", null);
			logger.error("showNav()---error",e);
		}
		return json;
	}
	/**
	 * 修改分类名
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateTypeName")
	@ResponseBody
	public Map<String,Object> updateTypeName(HttpServletRequest request,@ModelAttribute("articleType") CmsArticleType articleType){
		try{
			articleType.setUpdateTime(new Date());
			cmsArticleTypeService.updateArticleTypeName(articleType);
			this.setJson(true, "修改成功", null);
		}catch (Exception e) {
			this.setJson(false, "系统繁忙，请稍后再操作", null);
			logger.error("updateSubjectName()---error",e);
		}
		return json;
	}
	
	/**
	 * 创建新线下课程专业
	 * @param request
	 * @return
	 */
	@RequestMapping("/createType")
	@ResponseBody
	public Map<String,Object> createType(HttpServletRequest request,@ModelAttribute("articleType") CmsArticleType articleType){
		try{
			articleType.setUpdateTime(new Date());
			articleType.setIsnav(0);
			cmsArticleTypeService.createArticleType(articleType);
			this.setJson(true, "资讯分类创建成功", articleType);
		}catch (Exception e) {
			this.setJson(false, "资讯分类创建失败", null);
			logger.error("createType()---error",e);
		}
		return json;
	}
	
	/**
	 * 查询所有的线下资讯分类List
	 * @return
	 */
	@RequestMapping("/queryTypeList")
	public ModelAndView queryAllArticleTypeList(){
		model = new ModelAndView();
		try{
			model.setViewName(typeListPage);
			List<CmsArticleType> typeList = cmsArticleTypeService.queryAllArticleTypeList();
			String stringTypeList = gson.toJson(typeList);
			model.addObject("typeList", stringTypeList);
		}catch (Exception e) {
			logger.error("queryAllCourseSubjectList()--error",e);
		}
		return model;
	}
}
