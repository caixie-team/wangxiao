package io.wangxiao.edu.home.controller.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.suggest.QuerySugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggestReply;
import io.wangxiao.edu.home.service.suggest.SugSuggestReplyService;
import io.wangxiao.edu.home.service.suggest.SugSuggestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 课程播放问答
 */
@Controller
public class CourseSuggestController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(CourseSuggestController.class);
	// 课程详情课程评价
	private static final String sugList = getViewPath("/course/to_course_sug_ajax");

	@Autowired
	private SugSuggestService sugSuggestService;

	// 绑定变量名参数
	@InitBinder("sugSuggest")
	public void initBinderSugSuggest(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sugSuggest.");
	}

	// 绑定变量名参数
	@InitBinder("sugSuggestReply")
	public void initBinderSugSuggestReply(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sugSuggestReply.");
	}

	/**
	 * 问答添加
	 */
	@RequestMapping("/front/ajax/addsug")
	@ResponseBody
	public Map<String, Object> addSug(Model model, HttpServletRequest request, @ModelAttribute("sugSuggest") SugSuggest sugSuggest) {
		Map<String, Object> json = null;
		try {
			// 添加问答
			sugSuggest.setCusId(getLoginUserId(request));
			sugSuggestService.addSugSuggest(sugSuggest);
			json = this.getJsonMap(true, "", null);
			return json;
		} catch (Exception e) {
			logger.error("CourseSuggestController.addSug", e);
			json = this.getJsonMap(false, "", null);
			return json;
		}
	}

	/**
	 * 问答列表
	 */
	@RequestMapping("/sug/ajax/list")
	public String sugList(HttpServletRequest request, @ModelAttribute("kpointId") Long kpointId, @ModelAttribute("order") String order, @ModelAttribute("page") PageEntity page) {
		try {

			page.setPageSize(10);
			// 设置参数查询
			QuerySugSuggest querySugSuggest = new QuerySugSuggest();
			querySugSuggest.setKpointId(kpointId);
			querySugSuggest.setOrderFalg(order);
			List<SugSuggest> sugSuggestList = sugSuggestService.getSugSuggestList(querySugSuggest, page);
			// 返回数据
			request.setAttribute("sugSuggestList", sugSuggestList);
			request.setAttribute("page", page);
			return sugList;
		} catch (Exception e) {
			logger.error("CourseSuggestController.sugList", e);
			return setExceptionRequest(request, e);
		}
	}

	@Autowired
	private SugSuggestReplyService sugSuggestReplyService;

	/**
	 * 问答回复列表
	 */
	@RequestMapping("/reply/ajax/list")
	@ResponseBody
	public Map<String, Object> replyList(HttpServletRequest request, @ModelAttribute("sugId") Long sugId) {
		Map<String, Object> json = null;
		try {
			List<SugSuggestReply> replyList = sugSuggestReplyService.querySugSuggestReplyAllListBySuggestId(sugId);
			json = this.getJsonMap(true, "", replyList);
			return json;
		} catch (Exception e) {
			logger.error("CourseSuggestController.sugList", e);
			json = this.getJsonMap(false, "", null);
			return json;
		}
	}

	/**
	 * 回复添加
	 */
	@RequestMapping("/reply/ajax/add")
	@ResponseBody
	public Map<String, Object> replyList(HttpServletRequest request, @ModelAttribute("sugSuggestReply") SugSuggestReply sugSuggestReply) {
		Map<String, Object> json = null;
		try {
			sugSuggestReply.setCusId(getLoginUserId(request));
			sugSuggestReplyService.addSugSuggestReply(sugSuggestReply);
			json = this.getJsonMap(true, "", "");
			return json;
		} catch (Exception e) {
			logger.error("CourseSuggestController.sugList", e);
			json = this.getJsonMap(false, "", null);
			return json;
		}
	}
}