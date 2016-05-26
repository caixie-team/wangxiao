package com.atdld.os.edu.controller.course;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.suggest.QuerySugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggestReply;
import com.atdld.os.edu.service.suggest.SugSuggestReplyService;
import com.atdld.os.edu.service.suggest.SugSuggestService;

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
    public Map<String, Object> addSug(Model model,HttpServletRequest request, @ModelAttribute("sugSuggest") SugSuggest sugSuggest) {
        try {
        	//添加问答
        	 sugSuggest.setCusId(getLoginUserId(request));
        	 sugSuggestService.addSugSuggest(sugSuggest);
        	 this.setJson(true, "", null);
             return json;
        } catch (Exception e) {
            logger.error("CourseSuggestController.addSug", e);
            this.setJson(false, "", null);
            return json;
        }
    }
    /**
     * 问答列表
     */
    @RequestMapping("/sug/ajax/list")
    public String sugList(HttpServletRequest request,@ModelAttribute("kpointId") Long kpointId
    		,@ModelAttribute("order") String order, @ModelAttribute("page") PageEntity page) {
        try {
        	this.setPage(page);
			this.getPage().setPageSize(10);
			//设置参数查询
        	 QuerySugSuggest querySugSuggest = new QuerySugSuggest();
        	 querySugSuggest.setKpointId(kpointId);
        	 querySugSuggest.setOrderFalg(order);
        	 List<SugSuggest> sugSuggestList = sugSuggestService.getSugSuggestList(querySugSuggest, page);
        	 //返回数据
        	 request.setAttribute("sugSuggestList", sugSuggestList);
        	 request.setAttribute("page", this.getPage());
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
    public Map<String, Object> replyList(HttpServletRequest request,@ModelAttribute("sugId") Long sugId) {
        try {
        	 List<SugSuggestReply> replyList= sugSuggestReplyService.querySugSuggestReplyAllListBySuggestId(sugId);
        	 this.setJson(true, "", replyList);
             return json;
        } catch (Exception e) {
            logger.error("CourseSuggestController.sugList", e);
            this.setJson(false, "", null);
            return json;
        }
    }
    /**
     * 回复添加
     */
    @RequestMapping("/reply/ajax/add")
    @ResponseBody
    public Map<String, Object> replyList(HttpServletRequest request,@ModelAttribute("sugSuggestReply") SugSuggestReply sugSuggestReply) {
        try {
        	sugSuggestReply.setCusId(getLoginUserId(request));
        	 sugSuggestReplyService.addSugSuggestReply(sugSuggestReply);
        	 this.setJson(true, "", "");
             return json;
        } catch (Exception e) {
            logger.error("CourseSuggestController.sugList", e);
            this.setJson(false, "", null);
            return json;
        }
    }
}