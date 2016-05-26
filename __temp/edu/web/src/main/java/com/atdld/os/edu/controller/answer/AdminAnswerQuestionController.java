package com.atdld.os.edu.controller.answer;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.common.util.DateEditor;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.answer.AnswerQuestion;
import com.atdld.os.edu.entity.answer.AnswerReply;
import com.atdld.os.edu.service.answer.AnswerQuestionService;
import com.atdld.os.edu.service.answer.AnswerReplyService;
import com.atdld.os.sysuser.entity.QuerySubject;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.entity.SysUser;
import com.atdld.os.sysuser.service.SubjectService;

@Controller
@RequestMapping("/admin")
public class AdminAnswerQuestionController extends EduBaseController{
	  private static final Logger logger = LoggerFactory.getLogger(AdminAnswerQuestionController.class);
	  @Autowired
	  private AnswerQuestionService answerQuestionService;
	  @Autowired
	  private AnswerReplyService answerReplyService;
	  @Autowired
	  private SubjectService subjectService;
	  
	  private String toExamPaperAnswerList=getViewPath("/admin/answer/examPaper_answer_list");
	  private String toCourseAnswerList=getViewPath("/admin/answer/course_answer_list");
	  private String toAnswerInfo=getViewPath("/admin/answer/answerInfo");
	  private String toReplyList=getViewPath("/admin/answer/reply_list");
	  
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
	  
	  /**
	   * 提问信息
	   * */
	  @RequestMapping("/answer/answerQuestionList/{courseType}")
	  public ModelAndView getAnswerList(HttpServletRequest request,@ModelAttribute("answerQuestion") AnswerQuestion answerQuestion,@PathVariable("courseType")int courseType,@ModelAttribute("page") PageEntity page){
		  ModelAndView model=new ModelAndView();
		  try{
			  this.setPage(page);
			  this.getPage().setPageSize(10);
			  answerQuestion.setType("course"); // 设置提问类型 默认为course
			  answerQuestion.setCourseType(courseType);
			  List<AnswerQuestion> answerList=answerQuestionService.queryCourseAnswerQuestionList(answerQuestion, page);
			  if(ObjectUtils.isNotNull(answerList)){
				  for(AnswerQuestion answer :answerList){
					  if (courseType == 1) {
						  answer.setSonName(answer.getSonName().replaceAll("</?[^>]+>", ""));
					  }
					  if (courseType == 2) {
						  answer.setSonName(answer.getParentName().replaceAll("</?[^>]+>", ""));
					  }
				  }
			  }
			  // 获得一级项目
              QuerySubject querySubject = new QuerySubject();
              List<Subject> subjectList = subjectService.getSubjectList(querySubject);
              model.addObject("subjectList", gson.toJson(subjectList));
			  model.addObject("answerQuestionList", answerList);
			  model.addObject("page", this.getPage());
			  model.addObject("answerQuestion", answerQuestion);
			  if(answerQuestion.getType().equals("course")){
				  model.setViewName(toCourseAnswerList);
			  }else{
				  model.setViewName(toExamPaperAnswerList);
			  }
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.getAnswerList", e);
		  }
		  return model;
	  }
	  
	  /**
	   * 更新提问状态
	   * */
	  @RequestMapping("/answer/updateAnswerStatus")
	  @ResponseBody
	  public Map<String,Object> updateAnswerStatus(HttpServletRequest request,
			  @RequestParam("answerId") Long answerId, @RequestParam("status") int status){
		  try{
			  AnswerQuestion updateAnswer=new AnswerQuestion();
			  updateAnswer.setId(answerId);
			  updateAnswer.setStatus(status);
			  answerQuestionService.updateAnswerQuestionStatus(updateAnswer);
			  this.setJson(true, "success", null);
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.updateAnswerStatus", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
	  
	  /**
	   * 详情并回复
	   * */
	  @RequestMapping("/answer/answerInfo")
	  public ModelAndView answerInfo(HttpServletRequest request,@RequestParam("answerId") Long answerId,@RequestParam("type") String type){
		  ModelAndView model=new ModelAndView(toAnswerInfo);
		  try{
			  AnswerQuestion answer=new AnswerQuestion();
			  answer.setId(answerId);
			 
			  answer.setType(type);
			  answer=answerQuestionService.queryAnswerQuestionInfo(answer);
			  
			  List<AnswerReply> replyList=answerReplyService.getAdminReplyList(answerId);
			  model.addObject("replyList", replyList);
			  if(ObjectUtils.isNotNull(answer)){
				  model.addObject("answer", answer);
			  }
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.answerInfo", e);
		  }
		  return model;
	  }
	  
	  /**
	   * 后台老师回复
	   * */
	  @RequestMapping("/answer/addAnswerReply")
	  @ResponseBody
	  public Map<String, Object> addAnswerReply(HttpServletRequest request,@ModelAttribute("answerReply") AnswerReply answerReply){
		  try{
			  JsonObject user = SingletonLoginUtils.getSysUser(request);
			  JsonElement je = user.get("userName");
			  String userName = je.getAsString();
			  answerReply.setUserId(0L);
			  answerReply.setShowName(userName);
			  answerReply.setAddTime(new Date());
	          answerReply.setStatus(0);
	          answerReplyService.addAnswerReply(answerReply);
	          //更新状态为已回复
	          answerQuestionService.updateIsReply(answerReply.getAnswerId());
	          this.setJson(true, "success", "");
		  }catch(Exception e){
			  logger.error("CourseSuggestController.sugList", e);
	          this.setJson(false, "", null);
		  }
		  return json;
	  }
	  
	  /**
	   * 回复列表
	   * */
	 /* @RequestMapping("/answer/replyList")
	  public ModelAndView replyList(HttpServletRequest request,@RequestParam("answerId") Long answerId,@ModelAttribute("page") PageEntity page){
		  ModelAndView model=new ModelAndView(toReplyList);
		  try{
			  this.setPage(page);
			  List<AnswerReply> replyList=answerReplyService.getAdminReplyList(answerId, this.getPage());
			  model.addObject("answerId", answerId);
			  model.addObject("replyList", replyList);
			  model.addObject("page", this.getPage());
		  }catch(Exception e){
			  logger.error("CourseSuggestController.sugList", e);
		  }
		  return model;
	  }
	  */
	  /**
	   * 更新回复状态
	   * */
	  @RequestMapping("/answer/updateReplyStatus")
	  @ResponseBody
	  public Map<String,Object> updateReplyStatus(HttpServletRequest request,
			  @ModelAttribute("answerReply") AnswerReply answerReply){
		  try{
			  answerReplyService.updateAnswerReplyStatus(answerReply);
			  this.setJson(true, "success", null);
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.updateAnswerStatus", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
	  
	  /**
	   * 删除答疑
	   * 
	   * */
	  @RequestMapping("/answer/deleteAnswer")
	  @ResponseBody
	  public Map<String,Object> deleteAnswerQuetion(HttpServletRequest request,@RequestParam("answerId") Long answerId){
		  try{
			  answerQuestionService.delAnswerQuestion(answerId);
			  this.setJson(true, "success", null);
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.deleteAnswerQuetion", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
	  
	  /**
	   * 修改答疑
	   * 
	   * */
	  @RequestMapping("/answer/updateAnswer")
	  @ResponseBody
	  public Map<String,Object> upadteAnswerQuetion(HttpServletRequest request,@ModelAttribute("answerQuestion") AnswerQuestion answerQuestion){
		  try{
			  answerQuestionService.updateAnswerQuestion(answerQuestion);
			  this.setJson(true, "success", null);
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.upadteAnswerQuetion", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
	  
	  /**
	   * 修改回复
	   * 
	   * */
	  @RequestMapping("/answer/updateReply")
	  @ResponseBody
	  public Map<String,Object> upadteAnswerReply(HttpServletRequest request,@ModelAttribute("answerReply") AnswerReply reply){
		  try{
			  answerReplyService.updateAnswerReply(reply);
			  this.setJson(true, "success", null);
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.upadteAnswerReply", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
	  
	  /**
	   * 删除回复
	   * 
	   * */
	  @RequestMapping("/answer/deleteReply")
	  @ResponseBody
	  public Map<String,Object> deleteAnswerReply(HttpServletRequest request,@ModelAttribute("answerReply") AnswerReply reply){
		  try{
			  answerReplyService.delAnswerReply(reply);
			  this.setJson(true, "success", null);
		  }catch(Exception e){
			  logger.error("AdminAnswerQuestionController.deleteAnswerReply", e);
			  this.setJson(false, "系统异常", null);
		  }
		  return json;
	  }
}
