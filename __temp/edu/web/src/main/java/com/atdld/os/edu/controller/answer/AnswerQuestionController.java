package com.atdld.os.edu.controller.answer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.common.util.DateEditor;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.answer.AnswerQuestion;
import com.atdld.os.edu.entity.answer.AnswerReply;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.service.answer.AnswerQuestionService;
import com.atdld.os.edu.service.answer.AnswerReplyService;
import com.atdld.os.edu.service.user.UserService;

@Controller
public class AnswerQuestionController extends EduBaseController{
	  private static final Logger logger = LoggerFactory.getLogger(AnswerQuestionController.class);
	  @Autowired
	  private AnswerQuestionService answerQuestionService;
	  @Autowired
	  private AnswerReplyService answerReplyService;
	  @Autowired
	  private UserService userService;
	  
	  // 课程详情课程评价
	  private static final String answerList = getViewPath("/course/to_course_sug_ajax");
	  private static final String toanswerList = getViewPath("/course/to_course_anssug_ajax");
	  
	  
	  @InitBinder("answerQuestion")
	  public void initBindAnswerQuestion(HttpServletRequest request, ServletRequestDataBinder binder){
		  binder.setFieldDefaultPrefix("answerQuestion.");
		  binder.registerCustomEditor(Date.class, new DateEditor());
	  }
	  @InitBinder("answerReply")
	  public void initBindAnswerReply(HttpServletRequest request, ServletRequestDataBinder binder){
		  binder.setFieldDefaultPrefix("answerReply.");
		  binder.registerCustomEditor(Date.class, new DateEditor());
	  }
	  
	  @RequestMapping("/front/addAnswerQuestion")
	  @ResponseBody
	  public Map<String,Object> annAnswerQuestion(HttpServletRequest request,@ModelAttribute("answerQuestion") AnswerQuestion answerQuestion){
		  try{
			  Long userId=getLoginUserId(request);
			  if(ObjectUtils.isNotNull(userId)){
				  answerQuestion.setAddTime(new Date());
				  answerQuestion.setStatus(0);
				  answerQuestion.setUserId(userId);
				  answerQuestion.setReplyCount(0);
				  answerQuestion.setIsReply(0);
				  answerQuestionService.addAnswerQuestion(answerQuestion);
				  this.setJson(true, "success", null);
			  }else{
				  this.setJson(false, "未登录", null);
			  }
		  }catch(Exception e){
			  logger.error("AnswerQuestionController.annAnswerQuestion", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
	  
	  	/**
	     * 问答列表
	     */
	    @RequestMapping("/front/ajax/answerList")
	    public String sugList(HttpServletRequest request,@RequestParam("kpointId") Long kpointId
	    		, @ModelAttribute("page") PageEntity page) {
	        try {
	        	this.setPage(page);
				this.getPage().setPageSize(10);
				//设置参数查询
	        	 AnswerQuestion answer=new AnswerQuestion();
	        	 answer.setSonId(kpointId);
	        	 //answer.setParentId(kpointId);
	        	 answer.setType("course");
	        	 List<AnswerQuestion> answerQuestionList = answerQuestionService.getAnswerQuestionList(answer, page);
	        	 //返回数据
	        	 request.setAttribute("sugSuggestList", answerQuestionList);
	        	 request.setAttribute("page", this.getPage());
	             return answerList;
	        } catch (Exception e) {
	            logger.error("CourseSuggestController.sugList", e);
				return setExceptionRequest(request, e);
	        }
	    }
	    
	    /**
	     * 问答列表
	     */
	    @RequestMapping("/front/ajax/answer_list")
	    public String sugCourseList(HttpServletRequest request,@RequestParam("kpointId") Long kpointId
	    		, @ModelAttribute("page") PageEntity page) {
	        try {
	        	this.setPage(page);
				this.getPage().setPageSize(10);
				//设置参数查询
	        	 AnswerQuestion answer=new AnswerQuestion();
	        	 //answer.setSonId(kpointId);
	        	 answer.setParentId(kpointId);
	        	 answer.setType("course");
	        	 List<AnswerQuestion> answerQuestionList = answerQuestionService.getAnswerQuestionList(answer, page);
	        	 //返回数据
	        	 request.setAttribute("sugSuggestList", answerQuestionList);
	        	 request.setAttribute("page", this.getPage());
	             return toanswerList;
	        } catch (Exception e) {
	            logger.error("CourseSuggestController.sugList", e);
				return setExceptionRequest(request, e);
	        }
	    }
	    
	    /**
	     * 前台获取回复信息
	     * */
	    @RequestMapping("/front/ajax/answerReplyList")
	    @ResponseBody
	    public Map<String, Object> answerReplyList(HttpServletRequest request,@RequestParam("answerId") Long answerId){
	    	try{
	    		List<AnswerReply> replyList= answerReplyService.getAnswerReplyList(answerId);
	        	 this.setJson(true, "", replyList);
	             return json;
	    	}catch(Exception e){
	    		logger.error("CourseSuggestController.replyList", e);
				this.setJson(false, "系统异常", null);
	    	}
	    	return json;
	    }
	    
	    /**
	     * 回复添加
	     */
	    @RequestMapping("/front/ajax/addAnswerReply")
	    @ResponseBody
	    public Map<String, Object> addReply(HttpServletRequest request,@ModelAttribute("answerReply") AnswerReply answerReply) {
	        try {
	        	Long userId= getLoginUserId(request);
	        	UserExpandDto user=userService.queryUserExpand(userId);
				if(ObjectUtils.isNull(user)){
					this.setJson(false, "未登录", null);
		            return json;
				}
	        	answerReply.setUserId(getLoginUserId(request));
	        	answerReply.setShowName(user.getShowname());
	        	answerReply.setAddTime(new Date());
	        	answerReply.setStatus(0);
	        	answerReplyService.addAnswerReply(answerReply);
	        	this.setJson(true, "", "");
	            return json;
	        } catch (Exception e) {
	            logger.error("CourseSuggestController.sugList", e);
	            this.setJson(false, "", null);
	            return json;
	        }
	    }
}
