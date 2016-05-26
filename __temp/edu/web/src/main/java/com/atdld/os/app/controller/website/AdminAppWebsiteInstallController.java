package com.atdld.os.app.controller.website;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.app.common.AppBaseController;
import com.atdld.os.app.entity.website.WebsiteInstall;
import com.atdld.os.app.service.website.WebsiteInstallService;
import com.atdld.os.core.entity.PageEntity;

/**
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminAppWebsiteInstallController extends AppBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminAppWebsiteInstallController.class);

	@Autowired
	private WebsiteInstallService websiteInstallService;

	private static final String installPage = getViewPath("/admin/website/statistics/install_list");

	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("websiteInstall")
	public void initBinderwebsiteInstall(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteInstall.");
	}

	/**
	 * 安装统计分页
	 *
	 */
	@RequestMapping("/install/list")
	public String installPage(WebsiteInstall websiteInstall,HttpServletRequest request,@ModelAttribute("page") PageEntity page) {
		try {
            this.setPage(page);
            List<WebsiteInstall> websiteInstallList = websiteInstallService.getWebsiteInstallPage(websiteInstall, page);
            request.setAttribute("websiteInstallList",websiteInstallList);
            request.setAttribute("page",getPage());
		} catch (Exception e) {
			logger.error("installPage", e);
			return setExceptionRequest(request, e);
		}
		return installPage;
	}

    /***
     *添加安装记录(app调用还需修改)
     */
    @RequestMapping("/install/addinstall")
    @ResponseBody
    public Map<String,Object> queryInstall(HttpServletRequest request,WebsiteInstall websiteInstall){
        try{
            websiteInstallService.insertWebsiteInstall(websiteInstall);
            this.setJson(true,"success",websiteInstall);
        }catch (Exception e) {
            logger.error("queryInstall",e);
            this.setJson(false,"false",null);
        }
        return json;
    }

}
