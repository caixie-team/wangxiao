package com.atdld.open.cms.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
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
import org.springframework.web.servlet.ModelAndView;

import com.atdld.open.cms.common.controller.CmsBaseController;
import com.atdld.open.cms.common.enums.CmsArticlePushStates;
import com.atdld.open.cms.entity.article.CmsArticle;
import com.atdld.open.cms.entity.article.QueryArticle;
import com.atdld.open.cms.entity.type.CmsArticleType;
import com.atdld.open.cms.service.article.CmsArticleService;
import com.atdld.open.cms.service.type.CmsArticleTypeService;
import com.atdld.open.common.constants.CommonConstants;
import com.atdld.open.core.entity.PageEntity;

/**
 * 
 */
@Controller
public class CmsArticleController extends CmsBaseController {

	private static final Logger logger = LoggerFactory.getLogger(CmsArticleController.class);
	@Autowired
	private CmsArticleTypeService cmsArticleTypeService;
	@Autowired
	private CmsArticleService cmsArticleService;
	@InitBinder("queryArticle")
    public void initBinderQueryArticle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryArticle.");
    }
	@InitBinder("queryBranch")
    public void initBinderQueryLineArticle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryBranch.");
    }
	
	
	/**
	 * 资讯中心资讯列表
	 * @return
	 */
	@RequestMapping("/cms/news/list")
	public ModelAndView queryArticleList(@ModelAttribute("queryArticle") QueryArticle queryArticle, @ModelAttribute("page") PageEntity page){
		ModelAndView model=new ModelAndView("/cms/article/cms-news-list");
		try{
		   //获取所有的资讯类别
		   List<CmsArticleType> typeList = cmsArticleTypeService.queryWebLineArticle();
		   String stringTypeList = gson.toJson(typeList);
		   //获取所有的资讯 
		   this.setPage(page);
		   this.getPage().setPageSize(5);
		   queryArticle.setPushStates(CmsArticlePushStates.PUBLISH.toString());
		   List<CmsArticle> articleList=cmsArticleService.getArticleList(queryArticle, page);
		   //获取热门资讯
		   List<CmsArticle> hotArticle=cmsArticleService.getHotArticle(12);
		   model.addObject("articleList",articleList);
		   model.addObject("articleUrl",CommonConstants.articleUrl);
		   model.addObject("hotArticle",hotArticle);
		   model.addObject("page", this.getPage());
		   model.addObject("stringTypeList", stringTypeList);
		   model.addObject("typeList", typeList);
		}catch (Exception e) {
			logger.error("queryArticleList()--error",e);
		}
		return model;
	}
	/***
	 * 查询类型的子级类型
	 * @param typeId 资讯类型ID
	 * @return Map<String,Object>
	 */
	@RequestMapping("/line/type/querytypechild/{typeId}")
	@ResponseBody
	public Map<String,Object> queryTypeChildList(@PathVariable("typeId") int typeId){
		try{
			List<CmsArticleType> typeList = cmsArticleTypeService.queryChildTypeListByParentId(typeId);
			this.setJson(true, null, typeList);
		}catch (Exception e) {
			this.setJson(false, null, null);
			logger.error("queryTypeChildList()---error",e);
		}
		return json;
	}
	
	
	/**
	 * 更新点击次数
	 * */
	@RequestMapping("line/article/updateLookNum/{articleId}")
	@ResponseBody
	public Map<String,Object> updateLookNum(@PathVariable Long articleId,HttpServletRequest requet){
		try{
			cmsArticleService.updateLookNum(articleId);
			CmsArticle article=cmsArticleService.getArticleById(articleId);
			String linkUrl=article.getAddTime().getTime()+"_"+article.getArticleId()+".html";
			publishLineArticle(article,linkUrl);
			this.setJson(true, "操作成功", null);
		}catch(Exception e){
			this.setJson(false, "操作异常", null);
			logger.error("updateLookNum()---error",e);
		}
		return json;
	}
	
	/**
	 * 生成资讯html
	 * @param request
	 * @return
	 */
	public void publishLineArticle(CmsArticle lineArticle,String linkUrl){
		FileOutputStream fos = null;  
		BufferedWriter writer = null;
		try{
			//模板路径
			String tempFile="/lineArticle_temp.vm";
			String file=CommonConstants.staticArticle+"\\info\\"+linkUrl;
			File filePath=new File(file);
			if(!filePath.getParentFile().exists()){
				filePath.getParentFile().mkdirs();
			}
			//=============================查询所需数据=================//	
		    //获取热门资讯
		    List<CmsArticle> hotArticle=cmsArticleService.getHotArticle(10);
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
	        
	        //修改文章的发布状态
	        lineArticle.setPushStates(CmsArticlePushStates.PUBLISH.toString());
	        lineArticle.setPushTime(new Date());
	        cmsArticleService.updateArticle(lineArticle);
		}catch(Exception e){
			try{
				if(fos!=null){
					fos.close();
				}
				if(writer!=null){
					writer.close();
				}
			}catch (Exception ignored) {
			}
			logger.error("publishLineArticle()--error",e);
		}
	}
	
}
