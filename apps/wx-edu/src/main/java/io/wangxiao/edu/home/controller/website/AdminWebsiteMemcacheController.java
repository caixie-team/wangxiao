package io.wangxiao.edu.home.controller.website;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.website.WebsiteMemcache;
import io.wangxiao.edu.home.service.website.WebsiteMemcacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminWebsiteMemcacheController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteMemcacheController.class);

	private static final String toWebsiteMemcache = getViewPath("/admin/website/memcache/website_memcache");// Memcache列表页
	private static final String toAddwebsiteMemcache = getViewPath("/admin/website/memcache/website_add");// 添加页面
	@Autowired
	private WebsiteMemcacheService websiteMemcacheService;

	// 绑定变量名参数
	@InitBinder("websiteMemcache")
	public void initBinderWebsiteMemcache(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteMemcache.");
	}

	/**
	 * 跳转到添加页
	 * 
	 * @return
	 */
	@RequestMapping("/websitemem/toAdd")
	public String toAddwebsiteMemcache() {
		return toAddwebsiteMemcache;
	}

	/**
	 * 添加Memcachekey
	 * 
	 * @param request
	 * @param websiteMemcache
	 * @return
	 */
	@RequestMapping("/websitemem/add")
	@ResponseBody
	public Map<String, Object> addWebsiteMemcache(HttpServletRequest request,
			@ModelAttribute WebsiteMemcache websiteMemcache) {
		Map<String, Object> json = null;
		try {
			if (ObjectUtils.isNotNull(websiteMemcache) && StringUtils.isNotEmpty(websiteMemcache.getMemKey())) {
				// 判断key是否存在
				boolean flag = websiteMemcacheService
						.queryWebsiteMemcacheIsExsit(websiteMemcache.getMemKey().replaceAll(" ", ""));
				if (flag) {
					websiteMemcache.setMemKey(websiteMemcache.getMemKey().replaceAll(" ", ""));
					websiteMemcacheService.addWebsiteMemcache(websiteMemcache);
					json = this.getJsonMap(true, "", null);
				} else {
					json = this.getJsonMap(false, "该key已存在", null);
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.addWebsiteMemcache", e);
		}
		return json;
	}

	/**
	 * 查询memcache管理
	 * 
	 * @param request
	 * @param model
	 * @param websiteMemcache
	 * @param page
	 * @return
	 */
	@RequestMapping("/websitemem/list")
	public String getWebsiteMemcacheList(HttpServletRequest request, Model model,
			@ModelAttribute WebsiteMemcache websiteMemcache, @ModelAttribute("page") PageEntity page) {
		try {

			page.setPageSize(10);
			// 查詢WebsiteMemcache
			List<WebsiteMemcache> websiteMemcacheList = websiteMemcacheService.queryWebsiteMemcacheList(websiteMemcache,
					page);
			model.addAttribute("websiteMemcacheList", websiteMemcacheList);
			model.addAttribute("page", page);
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.getWebsiteMemcacheList", e);
			return setExceptionRequest(request, e);
		}
		return toWebsiteMemcache;
	}

	/**
	 * 删除memcache缓存
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/websitemem/removeMemKey")
	@ResponseBody
	public Map<String, Object> removeMemKey(HttpServletRequest request, @RequestParam("id") Long id) {
		Map<String, Object> json = null;
		try {
			// 获得WebsiteMemcache的result
			WebsiteMemcache websiteMemcache = websiteMemcacheService.queryWebsiteMemcacheById(id);
			if (ObjectUtils.isNotNull(websiteMemcache)) {
				// 移除memcache
				websiteMemcacheService.removeMemcacheKey(MemConstans.MEMFIX + websiteMemcache.getMemKey());
				json = this.getJsonMap(true, "", null);
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.removeMemKey", e);
			json = this.getJsonMap(false, "", null);
		}
		return json;
	}

	/**
	 * 删除此条信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/websitemem/del")
	@ResponseBody
	public Map<String, Object> deleteWebsiteMemcache(@RequestParam("id") Long id) {
		Map<String, Object> json = null;
		try {
			// 删除此条信息
			websiteMemcacheService.deleteWebsiteMemcacheById(id);
			json = this.getJsonMap(true, "", null);
		} catch (Exception e) {
			logger.error("AdminWebsiteMemcacheController.deleteWebsiteMemcache", e);
			json = this.getJsonMap(false, "", null);
		}
		return json;
	}
}
