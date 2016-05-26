package com.atdld.open.cms.controller;

import com.atdld.open.cms.common.controller.CmsBaseController;
import com.atdld.open.cms.common.enums.CmsArticlePushStates;
import com.atdld.open.cms.entity.article.CmsArticle;
import com.atdld.open.cms.entity.article.CmsArticleContent;
import com.atdld.open.cms.entity.article.QueryArticle;
import com.atdld.open.cms.entity.type.CmsArticleType;
import com.atdld.open.cms.service.article.CmsArticleContentService;
import com.atdld.open.cms.service.article.CmsArticleService;
import com.atdld.open.cms.service.type.CmsArticleTypeService;
import com.atdld.open.common.constants.CommonConstants;
import com.atdld.open.common.util.DateEditor;
import com.atdld.open.core.entity.PageEntity;
import com.atdld.open.core.util.ObjectUtils;
import com.atdld.open.core.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 线下资讯Controller
 *
 */
@Controller
@RequestMapping("/admin/line")
public class AdminCmsArticleController extends CmsBaseController{
	
	private static Logger logger = Logger.getLogger(AdminCmsArticleController.class);
	@Autowired
	private CmsArticleTypeService cmsArticleTypeService;
	@Autowired
	private CmsArticleService cmsArticleService;
	@Autowired
	private CmsArticleContentService cmsArticleContentService;
	@Autowired
	private CmsArticleTypeService lineArticleTypeService;
	private ModelAndView model=null;
	private String toAddArticle = getViewPath("/admin/article/article-add");
	private String toArticleList = getViewPath("/admin/article/article_list");
	private String toUpdateArticle = getViewPath("/admin/article/article_update");
	@InitBinder("article")
	public void initBinderLineArticle(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("article.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	@InitBinder("queryArticle")
    public void initBinderQueryArticle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryArticle.");
    }
	
	/**
	 * 发布资讯首页
	 * @return
	 */
	@RequestMapping("/article/pushIndex")
	@ResponseBody
	public Map<String,Object> pushIndex(){
		try{
			FileOutputStream fos = null;  
			BufferedWriter writer = null;
				//模板路径
				String tempFile="/cms_index.vm";
				String file=CommonConstants.cmsIndexPath+"/index.html";
				File filePath=new File(file);
				if(!filePath.getParentFile().exists()){
					filePath.getParentFile().mkdirs();
				}
				//模板文件上下文
				VelocityContext context=new VelocityContext();
				context.put("lineArticleService", cmsArticleService);
				context.put("staticServer",CommonConstants.staticServer);
				context.put("staticImageServer",CommonConstants.staticImageServer);
				context.put("staticArticle", CommonConstants.staticArticle);
				context.put("articleUrl", CommonConstants.articleUrl);
				context.put("lineArticleTypeService", lineArticleTypeService);
				context.put("cmsArticleService", cmsArticleService);
				context.put("dateTool",new DateTool());
				
				List<Map<String,Object>> typeList = queryHeaderType();
				context.put("typeList",typeList);
				VelocityEngine engine=new VelocityEngine();//初始化模板引擎
				Properties properties = new Properties();  
		        properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,CommonConstants.lineTemplate);  
	  	        properties.setProperty(VelocityEngine.INPUT_ENCODING,"UTF-8"); 
		        properties.setProperty(VelocityEngine.OUTPUT_ENCODING,"UTF-8"); 
		        engine.init(properties);   
				Template temp=engine.getTemplate(tempFile,"UTF-8");//得到模板文件
				//开始写出HTML文件
				fos = new FileOutputStream(file); 
		        writer = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
		        temp.merge(context, writer);
		        //关闭流
		        writer.close();
		        fos.close();
			this.setJson(true, "发布成功", null);
		}catch (Exception e) {
			this.setJson(false, "系统繁忙，请稍后再操作", null);
			logger.error("pushIndex()---error",e);
		}
		return json;
	}
	
	/**获取要显示在导航栏上的分类*/
	private List<Map<String,Object>>  queryHeaderType(){
		List<CmsArticleType> typeList = lineArticleTypeService.queryAllArticleTypeList();
		List<Map<String,Object>> parnetList = new ArrayList<Map<String,Object>>();
		if(typeList!=null&& typeList.size()>0){
			for(CmsArticleType cat : typeList){
				if(cat.getIsnav()==1&&cat.getParentId()==-1){
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("parent", cat);
					parnetList.add(map);
				}
			}
			if(parnetList.size()>0){
				for(Map<String,Object> map : parnetList){
					List<CmsArticleType> childList = new ArrayList<CmsArticleType>();
					for(CmsArticleType cat : typeList){
						CmsArticleType ca = (CmsArticleType)map.get("parent");
						if(cat.getIsnav()==1&&cat.getParentId()== ca.getTypeId()){
							childList.add(cat);
						}
					}
					map.put("childList", childList);
				}
			}
			
		}
		return parnetList;
	}
	/**
	 * 跳转资讯页面
	 * @return
	 */
	@RequestMapping("/article/toAdd")
	public ModelAndView toAddArticle() {
		model = new ModelAndView();
		try{
			model.setViewName(toAddArticle);
			List<CmsArticleType> typeList = cmsArticleTypeService.queryAllArticleTypeList();
			String stringTypeList = gson.toJson(typeList);
			model.addObject("stringTypeList", stringTypeList);
		}catch (Exception e) {
			logger.error("toAddArticle()--error",e);
		}
		return model;
	}
	/**
	 * 添加资讯
	 * @param article
	 * @return
	 */
	@RequestMapping("/article/add")
	public ModelAndView addArticle(@ModelAttribute("article") CmsArticle article) {
		model = new ModelAndView();
		try{
			model.setViewName("redirect:/admin/line/article/toAdd");
			article.setAddTime(new Date());
			article.setUpdateTime(new Date());
			article.setLinkUrl("");
			article.setPushStates(CmsArticlePushStates.UNPUBLISHED.toString());
			cmsArticleService.createArticle(article);
			List<CmsArticleType> typeList = cmsArticleTypeService.queryAllArticleTypeList();
			CmsArticle articleNew=cmsArticleService.getArticleId();
			//将资讯内容添加到内容表中
			CmsArticleContent lineArticleContent=new CmsArticleContent();
			lineArticleContent.setContent(article.getContent());
			lineArticleContent.setArticleId(articleNew.getArticleId());
			cmsArticleContentService.createArticleContent(lineArticleContent);
			String stringTypeList = gson.toJson(typeList);
			model.addObject("stringTypeList", stringTypeList);
		}catch (Exception e) {
			logger.error("addArticle()--error",e);
		}
		return model;
	}
	/**
	 * 添加资讯并生成html
	 * 
	 * @param request
	 * @param article
	 * @return
	 */
	@RequestMapping("/article/addAndHtml")
	public ModelAndView addArticleAndHtml(HttpServletRequest request, @ModelAttribute("article") CmsArticle article) {
		model=new ModelAndView();
		try {
			model.setViewName(toAddArticle);
			if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
				article.setAddTime(new Date());
				article.setUpdateTime(new Date());
				article.setPushTime(new Date());
				article.setLinkUrl("");
				cmsArticleService.createArticle(article);
				List<CmsArticleType> typeList = cmsArticleTypeService.queryAllArticleTypeList();
				CmsArticle articleNew=cmsArticleService.getArticleId();
				//将资讯内容添加到内容表中
				CmsArticleContent lineArticleContent=new CmsArticleContent();
				lineArticleContent.setContent(article.getContent());
				lineArticleContent.setArticleId(articleNew.getArticleId());
				cmsArticleContentService.createArticleContent(lineArticleContent);
				//文章生成路径
				String linkUrl=articleNew.getArticleId()+".html";
				articleNew.setLinkUrl(linkUrl);
				cmsArticleService.updateArticle(articleNew);
				String stringTypeList = gson.toJson(typeList);
				model.addObject("stringTypeList", stringTypeList);
				publishLineArticle(articleNew,linkUrl);
			}
		} catch (Exception e) {
			logger.error("addArticleAndHtml----error", e);
		}
		return model;
	}
	
	/**
	 * 生成资讯html
	 * @param request
	 * @return
	 */
	public void publishLineArticle(CmsArticle lineArticleOld,String linkUrl){
		FileOutputStream fos = null;  
		BufferedWriter writer = null;
		try{
			//修改文章的发布状态
			lineArticleOld.setPushStates(CmsArticlePushStates.PUBLISH.toString());
			lineArticleOld.setPushTime(new Date());
	        cmsArticleService.updateArticle(lineArticleOld);
	        CmsArticle lineArticle=cmsArticleService.getArticleById(lineArticleOld.getArticleId());
			//模板路径
			String tempFile="/lineArticle_temp.vm";
			String file=CommonConstants.staticArticle+"/info/"+linkUrl;
			System.out.println("+++++++++++++++++++file:"+file);
			File filePath=new File(file);
			if(!filePath.getParentFile().exists()){
				filePath.getParentFile().mkdirs();
			}
			//=============================查询所需数据=================//
			
		    //获取最新开班
//		    List<LineCourse> newCourse=lineCourseService.getNewCourse(6);
		    //获取热门资讯
		    List<CmsArticle> hotArticle=cmsArticleService.getHotArticle(12);
			//线下资讯详情
			CmsArticle article =cmsArticleService.getArticleById(lineArticle.getArticleId());
			//更新资讯浏览量 
			cmsArticleService.updateLookNum(lineArticle.getArticleId());
			CmsArticle upArticle=cmsArticleService.getUpOrDownArticle(article.getArticleId(), 1);//上一篇资讯
			CmsArticle downArticle=cmsArticleService.getUpOrDownArticle(article.getArticleId(), 2);//下一篇资讯
			
			//=============================添加数据=================//
			//模板文件上下文
			VelocityContext context=new VelocityContext();
			context.put("staticServer",CommonConstants.staticServer);//链接地址
			context.put("staticImageServer",CommonConstants.staticImageServer);//图片路径
			context.put("hotArticle", hotArticle);
			context.put("article", article);
			context.put("upArticle",upArticle );
			context.put("downArticle",downArticle);
			DateTool dateTool = new DateTool(); 
	        context.put("dateTool",dateTool);//时间格式化
	        context.put("articleUrl", CommonConstants.articleUrl);
			
			VelocityEngine engine=new VelocityEngine();//初始化模板引擎
			Properties properties = new Properties();  
	        properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,CommonConstants.lineTemplate);  
	        engine.init(properties);   
			Template temp=engine.getTemplate(tempFile,"UTF-8");//得到模板文件
			//开始写出HTML文件
			fos = new FileOutputStream(file); 
	        writer = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
	        temp.setEncoding("utf-8");
	        temp.merge(context, writer);
	        //关闭流
	        writer.close();
	        fos.close();
	       
		}catch(Exception e){
			try{
				if(fos!=null){
					fos.close();
				}
				if(writer!=null){
					writer.close();
				}
			}catch (Exception ex) {
			}
			logger.error("publishLineArticle()--error",e);
		}
	}
	
	/**
	 * 跳转资讯列表页面
	 * @return
	 */
	@RequestMapping("/article/toArticleList") 
	public ModelAndView toArticleList(@ModelAttribute QueryArticle queryArticle, @ModelAttribute("page") PageEntity page) {
		// 设置分页
		this.setPage(page);
		this.getPage().setPageSize(15);
		model = new ModelAndView();
		try{
			model.setViewName(toArticleList);
			List<CmsArticle> articleList=cmsArticleService.getArticleList(queryArticle, page) ;
			List<CmsArticleType> typeList = cmsArticleTypeService.queryAllArticleTypeList();
			
			String stringTypeList = gson.toJson(typeList);
			model.addObject("stringTypeList", stringTypeList);
			model.addObject("articleList",articleList);
			model.addObject("page", this.getPage());
			model.addObject("articleUrl", CommonConstants.articleUrl);
		}catch (Exception e) {
			logger.error("toAddArticle()--error",e);
		}
		return model;
	}
	/**
	 * 跳转到更新页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/article/toUpdate/{id}")
	public String toUpdateArticle(Model model, @PathVariable("id") long id) {
		try {
			// 查询资讯详情
			CmsArticle article = cmsArticleService.getArticleById(id);
			List<CmsArticleType> typeList = cmsArticleTypeService.queryAllArticleTypeList();
			String stringTypeList = gson.toJson(typeList);
			model.addAttribute("stringTypeList", stringTypeList);
			model.addAttribute("article", article);
		} catch (Exception e) {
			logger.error("toUpdateArticle----error", e);
		}
		return toUpdateArticle;
	}
	/**
	 * 更新资讯
	 * @param article
	 * @return
	 */
	@RequestMapping("/article/updateArticle")
	public ModelAndView updateArticle(@ModelAttribute QueryArticle queryArticle,HttpServletRequest request, @ModelAttribute("article") CmsArticle article, @ModelAttribute("page") PageEntity page) {
		model = new ModelAndView();
		try {
			this.setPage(page);
			this.getPage().setPageSize(15);
			model.setViewName("redirect:/admin/line/article/toArticleList");
			if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
				article.setUpdateTime(new Date());
				cmsArticleService.updateArticle(article);
				CmsArticleContent lineArticleContent= cmsArticleContentService.getArticleContent(article.getArticleId());
				lineArticleContent.setContent(article.getContent());
				cmsArticleContentService.updateArticleContent(lineArticleContent);
			}
		} catch (Exception e) {
			logger.error("updateArticle--error", e);
		}
		return model;
	}
	/**
	 * 更新资讯并生成html
	 * @param article
	 * @return
	 */
	@RequestMapping("/article/updateArticleAndHtml")
	public ModelAndView updateArticleAndHtml(@ModelAttribute QueryArticle queryArticle,HttpServletRequest request, @ModelAttribute("article") CmsArticle article, @ModelAttribute("page") PageEntity page) {
		model = new ModelAndView();
		try {
			this.setPage(page);
			this.getPage().setPageSize(15);
			model.setViewName("redirect:/admin/line/article/toArticleList");
			if (ObjectUtils.isNotNull(article) && StringUtils.isNotEmpty(article.getTitle()) && StringUtils.isNotEmpty(article.getContent())) {
				article.setUpdateTime(new Date());
				//文章生成路径
				String linkUrl=article.getArticleId()+".html";
				article.setLinkUrl(linkUrl);
				cmsArticleService.updateArticle(article);
				CmsArticleContent lineArticleContent= cmsArticleContentService.getArticleContent(article.getArticleId());
				lineArticleContent.setContent(article.getContent());
				cmsArticleContentService.updateArticleContent(lineArticleContent);
				//生成html
				publishLineArticle(article,linkUrl);
			}
		} catch (Exception e) {
			logger.error("updateArticleAndHtml--error", e);
		}
		return model;
	}
	/**
	 * 批量删除资讯
	 * @param request
	 * @param artIds
	 * @return
	 */
	@RequestMapping("/article/delArticleBatch")
	@ResponseBody
	public Map<String, Object> delArticleBatch(HttpServletRequest request, @RequestParam("artIds") String artIds) {
		try {
			if(StringUtils.isNotEmpty(artIds)){
//				将资讯内容删除
				cmsArticleContentService.delArticleContent(artIds);
//				批量刪除
				cmsArticleService.delArticleByIds(artIds);
				//删除html
				String [] arry=artIds.split(",");
				//生成文件路径
		        String dir = CommonConstants.staticArticle+"/info/";
                for (String anArry : arry) {
                    File file = new File(dir + "/" + anArry + ".html");
                    // 路径为文件且不为空则进行删除
                    if (file.isFile() && file.exists()) {
                        file.delete();
                    }
                }
				this.setJson(true, "success", null);
			}else{
				this.setJson(false, "false", null);
			}
		} catch (Exception e) {
			logger.error("delArticleBatch----error", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 列表生成html
	 * @param request
	 * @param artIds
	 * @return
	 */
	@RequestMapping("/article/createArticleHtml")
	@ResponseBody
	public Map<String, Object> createArticleHtml(HttpServletRequest request, @RequestParam("artId") String artId) {
		try {
			if(StringUtils.isNotEmpty(artId)){
				
					CmsArticle lineArticle=cmsArticleService.getArticleById(Integer.parseInt(artId));
					//文章生成路径
					String linkUrl=lineArticle.getArticleId()+".html";
					lineArticle.setLinkUrl(linkUrl);
					cmsArticleService.updateArticle(lineArticle);
					publishLineArticle(lineArticle,linkUrl);
				this.setJson(true, "success", null);
			}else{
				this.setJson(false, "false", null);
			}
		} catch (Exception e) {
			logger.error("createArticleHtmlBatch----error", e);
			this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 批量生成html
	 * @param request
	 * @param artIds
	 * @return
	 */
	@RequestMapping("/article/createArticleHtmlBatch")
	@ResponseBody
	public Map<String, Object> createArticleHtmlBatch(HttpServletRequest request, @RequestParam("artIds") String artIds) {
		try {
			if(StringUtils.isNotEmpty(artIds)){
				String[] ids=artIds.split(",");
				for(String id :ids){
					CmsArticle lineArticle=cmsArticleService.getArticleById(Long.parseLong(id));
					//文章生成路径
					String linkUrl=lineArticle.getArticleId()+".html";
					lineArticle.setLinkUrl(linkUrl);
					cmsArticleService.updateArticle(lineArticle);
					publishLineArticle(lineArticle,linkUrl);
				}
				this.setJson(true, "success", null);
			}else{
				this.setJson(false, "false", null);
			}
		} catch (Exception e) {
			logger.error("createArticleHtmlBatch----error", e);
			this.setJson(false, "false", null);
		}
		return json;
	}

	/**
	 * 根据类型查询资讯
	 * @param request
	 * @return
	 */
	@RequestMapping("/article/queryArticleByType")
	@ResponseBody
	public Map<String,Object> queryArticleByType(HttpServletRequest request){
		try{
			int typeId =Integer.parseInt(request.getParameter("typeId"));
			int showNum=Integer.parseInt(request.getParameter("showNum"));
			if(typeId==0){
				this.setJson(false, "查询失败","请选择分类");
			}
			if(showNum==0){
				this.setJson(false, "查询失败","请选择显示条数");
			}
		}catch (Exception e) {
			this.setJson(false, "系统繁忙", null);
			logger.error("queryArticleByType()---error",e);
		}
		return json;
	}
	
}
