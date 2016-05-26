package com.atdld.os.edu.controller.website;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.app.controller.website.AdminAppWebsiteImagesController;
import com.atdld.os.common.util.DateEditor;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.article.Article;
import com.atdld.os.edu.entity.website.WebsiteImageMange;
import com.atdld.os.edu.service.website.WebsiteImageMangeService;

@Controller
@RequestMapping("/admin")
public class AdminWebsiteImageMangeController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteImageMangeController.class);
	
	@Autowired
	private WebsiteImageMangeService websiteImageMangeService;
	
	private static final String imagesMangeList = getViewPath("/admin/website/images/imagesMange_list");//图片类型列表
	private static final String updateImagesMange = getViewPath("/admin/website/images/updateImagesMange");//图片类型列表
	
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("websiteImageMange")
	public void initBinderWebsiteImageMange(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteImageMange.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 展现全部的图片管理列表
	 * @return
	 */
	@RequestMapping("/website/imagesMange")
	  public String showWebsiteImageList(Model model){
		try{
		List<WebsiteImageMange>   websiteImageMangeList=websiteImageMangeService.queryWebsiteImageMangeList();
		 model.addAttribute("websiteImageMangeList", websiteImageMangeList);
		System.out.println(websiteImageMangeList);
		}catch(Exception e){
			logger.error("AdminWebsiteImageMangeController.showWebsiteImageList", e);
		}
	    	return imagesMangeList;
	    }
	/**
	 * 检查图片key 是否重复
	 * @return
	 */
	@RequestMapping("/website/checkImagesMange")
	@ResponseBody
	  public Map<String,Object> checkImagesMange(HttpServletRequest request){
	     String key =request.getParameter("imagekey");
	     try{                           
	    	 int count=websiteImageMangeService.checkImagesMange(key);
	    	 if(count==0){
	    		 this.setJson(true, "success", null);
		}else {
			 this.setJson(false, "fail", null);
		}
	     }catch(Exception e){
	    		logger.error("AdminWebsiteImageMangeController.addImagesMange", e);
	    		 this.setJson(false, "error", null);
	     }
	    return json;
		    }
	/**
	 * 执行图片的添加
	 * @return
	 */
	@RequestMapping("/website/addImagesMange")
	@ResponseBody
	  public Map<String,Object> addImagesMange(HttpServletRequest request,@ModelAttribute("websiteImageMange") WebsiteImageMange websiteImageMange){
		websiteImageMange.setCreateTime(new Date());
	     try{
			websiteImageMangeService.addimagesMange( websiteImageMange);
			this.setJson(true, "success", null);
	     }catch(Exception e){
	    		logger.error("AdminWebsiteImageMangeController.addImagesMange", e);
				this.setJson(false, "fail", null);
	     }
	    return json;
		    }
	/**
	 *删除图片管理
	 * @param id
	 * @return
	 */
   @RequestMapping("/website/deleteImagesMange/{id}")
	public String  deleteImagesMange(@PathVariable("id")long id){
		try{
			websiteImageMangeService.deleteImagesMangeById(id);
		}catch(Exception e){
			logger.error("AdminWebsiteImageMangeController.deleteImagesMange", e);
		}
		return "redirect:/admin/website/imagesMange";
	}
   /**
    * 得到单个管理图片的信息并跳转更新页面
    * @param id
    * @return
    */
   @RequestMapping("/website/toUpdateImagesMange/{key}")
	public String toUpdateImagesMange(@PathVariable("key")String key,Model model){
	   try{
		   WebsiteImageMange websiteImageMange= websiteImageMangeService.getImagesMangeByKey(key);
		   model.addAttribute("websiteImageMange", websiteImageMange);
	   }catch(Exception e){
			logger.error("AdminWebsiteImageMangeController.toUpdateImagesMange", e);
	   }
		return updateImagesMange;
	}
   /**
    * 更新单个管理图片信息
    * @param id
    * @return
    */
   @RequestMapping("/website/updateImagesMange")
	public String updateImagesMange( @ModelAttribute("websiteImageMange") WebsiteImageMange websiteImageMange){
	   try{
			websiteImageMangeService.updateImagesMange(websiteImageMange);
	   }catch(Exception e){
			logger.error("AdminWebsiteImageMangeController.toUpdateImagesMange", e);
	   }
		return "redirect:/admin/website/imagesMange";
	}
	
}
