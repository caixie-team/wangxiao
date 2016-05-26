package io.wangxiao.edu.home.controller.answer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.answer.AnswerQuestion;
import io.wangxiao.edu.home.entity.answer.AnswerReply;
import io.wangxiao.edu.home.service.answer.AnswerQuestionService;
import io.wangxiao.edu.home.service.answer.AnswerReplyService;
import io.wangxiao.edu.sysuser.entity.QuerySubject;
import io.wangxiao.edu.sysuser.entity.Subject;
import io.wangxiao.edu.sysuser.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminAnswerQuestionController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminAnswerQuestionController.class);
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private AnswerReplyService answerReplyService;
    @Autowired
    private SubjectService subjectService;

    private String toExamPaperAnswerList = getViewPath("/admin/answer/examPaper_answer_list");
    private String toCourseAnswerList = getViewPath("/admin/answer/course_answer_list");
    private String toAnswerInfo = getViewPath("/admin/answer/answerInfo");
    //private String toReplyList = getViewPath("/admin/answer/reply_list");

    @InitBinder("answerQuestion")
    public void initBindAnswerQuestion(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("answerQuestion.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @InitBinder("answerReply")
    public void initBindAnswerReply(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("answerReply.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    /**
     * 提问信息
     */
    @RequestMapping("/answer/answerQuestionList/{courseType}")
    public ModelAndView getAnswerList(HttpServletRequest request, @ModelAttribute("answerQuestion") AnswerQuestion answerQuestion, @PathVariable("courseType") int courseType, @ModelAttribute("page") PageEntity page) {
        ModelAndView model = new ModelAndView();
        try {

            page.setPageSize(10);
            answerQuestion.setType("course"); // 设置提问类型 默认为course
            answerQuestion.setCourseType(courseType);
            List<AnswerQuestion> answerList = answerQuestionService.queryCourseAnswerQuestionList(answerQuestion, page);
            if (ObjectUtils.isNotNull(answerList)) {
                for (AnswerQuestion answer : answerList) {
                    if (courseType == 1) {
                        if (answer.getSonName() != null) {
                            answer.setSonName(answer.getSonName().replaceAll("</?[^>]+>", ""));
                        }
                    }
                    if (courseType == 2) {
                        if (answer.getParentName() != null) {
                            answer.setSonName(answer.getParentName().replaceAll("</?[^>]+>", ""));
                        }
                    }
                }
            }
            // 获得一级项目
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            model.addObject("subjectList", gson.toJson(subjectList));
            model.addObject("answerQuestionList", answerList);
            model.addObject("page", page);
            model.addObject("answerQuestion", answerQuestion);
            if (answerQuestion.getType().toUpperCase().equals("COURSE")) {
                model.setViewName(toCourseAnswerList);
            } else {
                model.setViewName(toExamPaperAnswerList);
            }
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.getAnswerList", e);
        }
        return model;
    }

    /**
     * 更新提问状态
     */
    @RequestMapping("/answer/updateAnswerStatus")
    @ResponseBody
    public Map<String, Object> updateAnswerStatus(HttpServletRequest request, @RequestParam("answerId") Long answerId, @RequestParam("status") int status) {
        Map<String, Object> json = null;
        try {
            AnswerQuestion updateAnswer = new AnswerQuestion();
            updateAnswer.setId(answerId);
            updateAnswer.setStatus(status);
            answerQuestionService.updateAnswerQuestionStatus(updateAnswer);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.updateAnswerStatus", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 详情并回复
     */
    @RequestMapping("/answer/answerInfo")
    public ModelAndView answerInfo(HttpServletRequest request, @RequestParam("answerId") Long answerId, @RequestParam("type") String type) {
        ModelAndView model = new ModelAndView(toAnswerInfo);
        try {
            AnswerQuestion answer = new AnswerQuestion();
            answer.setId(answerId);

            answer.setType(type);
            answer = answerQuestionService.queryAnswerQuestionInfo(answer);

            List<AnswerReply> replyList = answerReplyService.getAdminReplyList(answerId);
            model.addObject("replyList", replyList);
            if (ObjectUtils.isNotNull(answer)) {
                model.addObject("answer", answer);
            }
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.answerInfo", e);
        }
        return model;
    }

    /**
     * 后台老师回复
     */
    @RequestMapping("/answer/addAnswerReply")
    @ResponseBody
    public Map<String, Object> addAnswerReply(HttpServletRequest request, @ModelAttribute("answerReply") AnswerReply answerReply) {
        Map<String, Object> json = null;
        try {
            JsonObject user = SingletonLoginUtils.getLoginUser(request);
            JsonElement je = user.get("nickname");
            String userName = je.getAsString();
            answerReply.setUserId(0L);
            answerReply.setShowName(userName);
            answerReply.setAddTime(new Date());
            answerReply.setStatus(0);
            answerReplyService.addAnswerReply(answerReply);
            // 更新状态为已回复
            answerQuestionService.updateIsReply(answerReply.getAnswerId());
            json = this.getJsonMap(true, "success", "");
        } catch (Exception e) {
            logger.error("CourseSuggestController.sugList", e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }

    /**
     * 回复列表
     * */
    /*
     * @RequestMapping("/answer/replyList") public ModelAndView
	 * replyList(HttpServletRequest request,@RequestParam("answerId") Long
	 * answerId,@ModelAttribute("page") PageEntity page){ ModelAndView model=new
	 * ModelAndView(toReplyList); try{
	 * 
	 * List<AnswerReply>
	 * replyList=answerReplyService.getAdminReplyList(answerId, page);
	 * model.addObject("answerId", answerId); model.addObject("replyList",
	 * replyList); model.addObject("page", page); }catch(Exception e){
	 * logger.error("CourseSuggestController.sugList", e); } return model; }
	 */

    /**
     * 更新回复状态
     */
    @RequestMapping("/answer/updateReplyStatus")
    @ResponseBody
    public Map<String, Object> updateReplyStatus(HttpServletRequest request, @ModelAttribute("answerReply") AnswerReply answerReply) {
        Map<String, Object> json = null;
        try {
            answerReplyService.updateAnswerReplyStatus(answerReply);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.updateAnswerStatus", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 删除答疑
     */
    @RequestMapping("/answer/deleteAnswer")
    @ResponseBody
    public Map<String, Object> deleteAnswerQuetion(HttpServletRequest request, @RequestParam("answerId") Long answerId) {
        Map<String, Object> json = null;
        try {
            answerQuestionService.delAnswerQuestion(answerId);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.deleteAnswerQuetion", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }


    /**
     * 批量删除答疑
     */
    @RequestMapping("/answer/deleteAnswers")
    @ResponseBody
    public Map<String, Object> deleteAnswerQuetions(HttpServletRequest request, @RequestParam("answerId") String answerIds) {
        Map<String, Object> json = null;
        try {

            String[] split = answerIds.replaceAll(" ", "").split(",");

            for (int i = 0; i < split.length; i++) {
                System.out.println(split[i] + "id号吗为");
                answerQuestionService.delAnswerQuestion(new Long(split[i]));
            }
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.deleteAnswerQuetion", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }


    /**
     * 修改答疑
     */
    @RequestMapping("/answer/updateAnswer")
    @ResponseBody
    public Map<String, Object> upadteAnswerQuetion(HttpServletRequest request, @ModelAttribute("answerQuestion") AnswerQuestion answerQuestion) {
        Map<String, Object> json = null;
        try {
            answerQuestionService.updateAnswerQuestion(answerQuestion);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.upadteAnswerQuetion", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 修改回复
     */
    @RequestMapping("/answer/updateReply")
    @ResponseBody
    public Map<String, Object> upadteAnswerReply(HttpServletRequest request, @ModelAttribute("answerReply") AnswerReply reply) {
        Map<String, Object> json = null;
        try {
            answerReplyService.updateAnswerReply(reply);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.upadteAnswerReply", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 删除回复
     */
    @RequestMapping("/answer/deleteReply")
    @ResponseBody
    public Map<String, Object> deleteAnswerReply(HttpServletRequest request, @ModelAttribute("answerReply") AnswerReply reply) {
        Map<String, Object> json = null;
        try {
            answerReplyService.delAnswerReply(reply);
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminAnswerQuestionController.deleteAnswerReply", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }
}
