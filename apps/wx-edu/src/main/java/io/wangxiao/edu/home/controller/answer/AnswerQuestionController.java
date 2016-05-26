package io.wangxiao.edu.home.controller.answer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.answer.AnswerQuestion;
import io.wangxiao.edu.home.entity.answer.AnswerReply;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.answer.AnswerQuestionService;
import io.wangxiao.edu.home.service.answer.AnswerReplyService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AnswerQuestionController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AnswerQuestionController.class);
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private AnswerReplyService answerReplyService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserExpandService userExpandService;

    // 课程详情课程评价
    private static final String answerList = getViewPath("/course/to_course_sug_ajax");
    private static final String toanswerList = getViewPath("/course/to_course_anssug_ajax");


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

    @RequestMapping("/front/addAnswerQuestion")
    @ResponseBody
    public Map<String, Object> annAnswerQuestion(HttpServletRequest request, @ModelAttribute("answerQuestion") AnswerQuestion answerQuestion) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNotNull(userId)) {
                answerQuestion.setAddTime(new Date());
                answerQuestion.setStatus(0);
                answerQuestion.setUserId(userId);
                answerQuestion.setReplyCount(0);
                answerQuestion.setIsReply(0);
                answerQuestionService.addAnswerQuestion(answerQuestion);
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "未登录", null);
            }
        } catch (Exception e) {
            logger.error("AnswerQuestionController.annAnswerQuestion", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 问答列表
     */
    @RequestMapping("/front/ajax/answerList")
    public String sugList(HttpServletRequest request, @RequestParam("kpointId") Long kpointId
            , @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(10);
            //设置参数查询
            AnswerQuestion answer = new AnswerQuestion();
            answer.setSonId(kpointId);
            //answer.setParentId(kpointId);
            answer.setType("course");
            List<AnswerQuestion> answerQuestionList = answerQuestionService.getAnswerQuestionList(answer, page);
            //返回数据
            request.setAttribute("sugSuggestList", answerQuestionList);
            request.setAttribute("page", page);
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
    public String sugCourseList(HttpServletRequest request, @RequestParam("kpointId") Long kpointId, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(6);
            //设置参数查询
            AnswerQuestion answer = new AnswerQuestion();
            //answer.setSonId(kpointId);
            answer.setParentId(kpointId);
            answer.setType("COURSE");
            List<AnswerQuestion> answerQuestionList = answerQuestionService.getAnswerQuestionList(answer, page);

            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(getLoginUserId(request));
            if (ObjectUtils.isNotNull(userExpandDto)) {
                request.setAttribute("userImg", userExpandDto.getAvatar());
            }
            request.setAttribute("sugSuggestList", answerQuestionList);
            request.setAttribute("page", page);
            return toanswerList;
        } catch (Exception e) {
            logger.error("CourseSuggestController.sugList", e);
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 前台获取回复信息
     */
    @RequestMapping("/front/ajax/answerReplyList")
    @ResponseBody
    public Map<String, Object> answerReplyList(HttpServletRequest request, @RequestParam("answerId") Long answerId) {
        Map<String, Object> json = null;
        try {
            List<AnswerReply> replyList = answerReplyService.getAnswerReplyList(answerId);
            json = this.getJsonMap(true, "", replyList);
            return json;
        } catch (Exception e) {
            logger.error("CourseSuggestController.replyList", e);
            json = this.getJsonMap(false, "系统异常", null);
        }
        return json;
    }

    /**
     * 回复添加
     */
    @RequestMapping("/front/ajax/addAnswerReply")
    @ResponseBody
    public Map<String, Object> addReply(HttpServletRequest request, @ModelAttribute("answerReply") AnswerReply answerReply) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            UserExpandDto user = userService.queryUserExpand(userId);
            if (ObjectUtils.isNull(user)) {
                json = this.getJsonMap(false, "未登录", null);
                return json;
            }
            answerReply.setUserId(getLoginUserId(request));
            answerReply.setShowName(user.getShowname());
            answerReply.setAddTime(new Date());
            answerReply.setStatus(0);
            answerReplyService.addAnswerReply(answerReply);
            json = this.getJsonMap(true, "", "");
            return json;
        } catch (Exception e) {
            logger.error("CourseSuggestController.sugList", e);
            json = this.getJsonMap(false, "", null);
            return json;
        }
    }
}
