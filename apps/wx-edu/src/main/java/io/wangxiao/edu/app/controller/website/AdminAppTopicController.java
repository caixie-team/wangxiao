package io.wangxiao.edu.app.controller.website;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.common.IMUtil;
import io.wangxiao.edu.app.constants.enums.TopicStatu;
import io.wangxiao.edu.app.entity.website.AppGroup;
import io.wangxiao.edu.app.entity.website.AppTopic;
import io.wangxiao.edu.app.entity.website.AppTopicDto;
import io.wangxiao.edu.app.entity.website.QueryAppTopicCondition;
import io.wangxiao.edu.app.service.website.AppTopicService;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * app话题管理
 */
@Controller
@RequestMapping("/admin")
public class AdminAppTopicController extends AppBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminAppTopicController.class);
	private static final String toAddAppTopic = getViewPath("/admin/website/topic/add_topic");
	private static final String toUpdateAppTopic = getViewPath("/admin/website/topic/update_topic");
	private static final String toAppTopicList = getViewPath("/admin/website/topic/app_topic_list");
	@Autowired
	private AppTopicService appTopicService;

	// app课程查询条件
	@InitBinder("queryAppTopicCondition")
	public void initBinderQueryAppTopicCondition(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryAppTopicCondition.");
	}

	@InitBinder("appTopic")
	public void initBinderAppTopic(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("appTopic.");
	}

	/**
	 * 跳转创建话题页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/appTopic/toAdd")
	public ModelAndView toAdd() {
		ModelAndView model = new ModelAndView(toAddAppTopic);
		return model;
	}

	/**
	 * 创建话题
	 * 
	 * @param request
	 * @param appTopic
	 * @return
	 */
	@RequestMapping("/appTopic/add")
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("appTopic") AppTopic appTopic) {
		ModelAndView model = new ModelAndView("redirect:/admin/appTopic/queryAppTopic");
		try {
			JsonObject sysUser = SingletonLoginUtils.getLoginUser(request);
			JsonElement je = sysUser.get("id");
			long userId = je.getAsLong();
			appTopic.setUserId(userId);
			appTopic.setCreateTime(new Date());
			appTopic.setStates(TopicStatu.DEFAULT + "");
			AppGroup appGroup = new AppGroup(appTopic.getTopicTitle(), "", "0", "demo@test.com", "0", appTopic.getTopicContent());
			Map<String, String> group = IMUtil.createGroup(appGroup);
			if (group.get("group_id") != null) {
				appTopic.setGroupId(group.get("group_id"));
				appTopicService.createAppTopic(appTopic);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("add--error", e);
		}
		return model;
	}

	/**
	 * 跳转修改话题页面
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping("/appTopic/toUpdate/{id}")
	public ModelAndView toUpdate(@PathVariable long id) {
		ModelAndView model = new ModelAndView(toUpdateAppTopic);
		try {
			AppTopicDto appTopic = appTopicService.getAppTopicById(id);
			model.addObject("appTopic", appTopic);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("toUpdate--error", e);
		}
		return model;
	}

	/**
	 * 修改话题
	 * 
	 * @param request
	 * @param appTopic
	 * @return
	 */
	@RequestMapping("/appTopic/update")
	public ModelAndView update(HttpServletRequest request, @ModelAttribute("appTopic") AppTopic appTopic) {
		ModelAndView model = new ModelAndView("redirect:/admin/appTopic/queryAppTopic");
		try {
			AppTopic appTopicNew = appTopicService.getAppTopic(appTopic.getTopicId());
			appTopic.setCreateTime(appTopicNew.getCreateTime());
			appTopic.setUserId(appTopicNew.getUserId());
			appTopicService.updateAppTopic(appTopic);
			AppGroup appGroup = new AppGroup();
			appGroup.setGroupId(appTopicNew.getGroupId());
			appGroup.setGroupName(appTopic.getTopicTitle());
			appGroup.setGroupInfo(appTopic.getTopicContent());
			IMUtil.modifyGroup(appGroup);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("update--error", e);
		}
		return model;
	}

	/**
	 * 查询话题列表
	 * 
	 * @return
	 */
	@RequestMapping("/appTopic/queryAppTopic")
	public ModelAndView queryAppTopic(@ModelAttribute("queryAppTopicCondition") QueryAppTopicCondition queryAppTopicCondition, @ModelAttribute("page") PageEntity page) {
		ModelAndView model = new ModelAndView(toAppTopicList);
		try {
			page.setPageSize(12);
			List<AppTopicDto> appTopicList = appTopicService.queryAppTopic(queryAppTopicCondition, page);
			model.addObject("appTopicList", appTopicList);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("queryAppTopic--error", e);
		}
		return model;
	}

	/**
	 * 删除话题
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/appTopic/delAppTopic/{ids}")
	@ResponseBody
	public Map<String, Object> deleteAppCourseById(HttpServletRequest request, @PathVariable String ids) {
		Map<String, Object> json = null;
		try {
			if (ids != null && !ids.equals("")) {
				String[] idNum = ids.split(",");
				for (int i = 0; i < idNum.length; i++) {
					AppTopic appTopicNew = appTopicService.getAppTopic(Long.parseLong(idNum[i]));
					IMUtil.dismissGroup(appTopicNew.getGroupId());
				}
				// 删除话题
				appTopicService.delAppTopicById(ids);
				json = this.getJsonMap(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("AdminAppCourseController.deleteAppCourseById--error", e);
			json = this.getJsonMap(false, "false", null);
		}
		return json;
	}

	/**
	 * 修改话题显示状态
	 * 
	 * @param request
	 * @param id
	 * @param is
	 * @return
	 */
	@RequestMapping("/appTopic/updateAppTopicState")
	@ResponseBody
	public Map<String, Object> updateAppTopicState(@RequestParam("id") long id, @RequestParam("is") String is) {
		Map<String, Object> json = null;
		try {
			if (id != 0 && is != null && !is.equals("")) {
				AppTopic app = appTopicService.getAppTopic(id);
				app.setStates(is);
				appTopicService.updateAppTopic(app);
				json = this.getJsonMap(true, "true", null);
			}
		} catch (Exception e) {
			logger.error("updateAppTopicState--error", e);
			json = this.getJsonMap(false, "false", null);
		}
		return json;
	}
}
