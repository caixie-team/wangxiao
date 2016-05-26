package com.atdld.os.exam.controller.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.common.exception.BaseException;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.RequestUtil;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.exampaper.PaperMiddle;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamProfessionalDTO;
import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.QuestErrorCheck;
import com.atdld.os.exam.entity.question.Question;
import com.atdld.os.exam.entity.question.QuestionOption;
import com.atdld.os.exam.service.exampaper.PaperMiddleService;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.question.OptionService;
import com.atdld.os.exam.service.question.QuestErrorCheckService;
import com.atdld.os.exam.service.question.QuestionService;
import com.atdld.os.exam.service.subject.SubjectService;



/**
 * @author
 * @ClassName AdminQuestionAction
 * @package com.atdld.os.exam.controller.question.admin
 * @description
 * @Create Date: 2013-9-7 下午3:44:13
 */
@Controller
@RequestMapping("/admin")
public class AdminQuestionController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(AdminQuestionController.class);
    @Autowired
    private OptionService optionService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PaperMiddleService paperMiddleService;
    private String examPaperAssembleAjax = getViewPath("/admin/paper/examPaper_assemble_ajax");
    @Getter
    @Setter
    private QstMiddle qstMiddle;
    @Getter
    @Setter
    private Question question;
    @Getter
    @Setter
    private QueryQuestion queryQuestion;
    @Autowired
    private QuestErrorCheckService questErrorCheckService;
    @Autowired
    private ProfessionalService professionalService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("question")
    public void initBinderQuestion(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("question.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("qstMiddle")
    public void initBinderQstMiddle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("qstMiddle.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryQuestion")
    public void initBinderQueryQuestion(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryQuestion.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("subject")
    public void initBinderSubject(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("subject.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("questErrorCheck")
    public void initBinderquestErrorCheck(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("questErrorCheck.");
    }

    // 返回路径
    private String toAddquestion = getViewPath("/admin/question/add_question");
    private String addQuestion = "redirect:/admin/quest/toQuestionList";
    private String toQuestionList = getViewPath("/admin/question/question_list");
    private String lookQuestion = getViewPath("/admin/question/look_question");
    private String toUpdateQuestion = getViewPath("/admin/question/update_question");
    private String updateQuestion = "redirect:/admin/quest/toQuestionList";
    private String toQuestionListByType = getViewPath("/admin/question/question_select_list");
    private String toAddCaiLiaoQuestion = getViewPath("/admin/question/add_cailiao_question");
    private String toRandomQuestion = getViewPath("/admin/question/random_question");
    private String batch = getViewPath("/admin/question/batch");
    private String toSubjectiveQstRecordList = getViewPath("/admin/question/subjective_question_list");
    private String queryQuestECList = getViewPath("/admin/question/questerror_check_list");
    private String batch_word = getViewPath("/admin/question/batch_word");


    // 到添加试题的页面
    @RequestMapping("/quest/toAddQuestion")
    public String toAddQuestion(HttpServletRequest request, HttpServletResponse response) {
        try {
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            request.setAttribute("professionalList", professionalList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.toAddquestion", e);
            return setExceptionRequest(request, e);
        }
        return toAddquestion;
    }

    // 添加材料题
    @RequestMapping("/quest/toAddCaiLiaoQuestion")
    public String toAddCaiLiaoQuestion(HttpServletRequest request,
                                       HttpServletResponse response, @ModelAttribute("qstMiddle") QstMiddle qstMiddle) {
        try {
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            request.setAttribute("professionalList", professionalList);
            request.setAttribute("qstMiddle", qstMiddle);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.toAddCaiLiaoQuestion", e);
            return setExceptionRequest(request, e);
        }
        return toAddCaiLiaoQuestion;
    }

    // 批量删除试题
    @RequestMapping("/quest/addQuestionAjax")
    @ResponseBody
    public Map<String, Object> addQuestionAjax(@ModelAttribute("question") Question question,
                                               @RequestParam("asr") List<String> asr, HttpServletRequest request,
                                               HttpServletResponse response) {
        try {
            /*question.setAuthor(getSysLoginedUser(request).getLoginName());*/
        	question.setAuthor(SingletonLoginUtils.getSysLoginLoginName(request));
            questionService.addOneQuestion(question, asr);
            this.setJson(true, "", question);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.delQuestionListBatch", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 添加试题
    @RequestMapping("/quest/addQuestion")
    public String addQuestion(@ModelAttribute("question") Question question,
                              @RequestParam("asr") List<String> asr, HttpServletRequest request,
                              HttpServletResponse response) {
        try {

            //question.setAuthor(getSysLoginedUser(request).getLoginName());
        	question.setAuthor(SingletonLoginUtils.getSysLoginLoginName(request));
            questionService.addOneQuestion(question, asr);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.addQuestion", e);
            return setExceptionRequest(request, e);
        }
        return addQuestion;
    }

    // 查看试题
    @RequestMapping("/quest/lookQuestion")
    public String lookQuestion(
            @ModelAttribute("queryQuestion") QueryQuestion queryQuestion,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            Question question = questionService.getOneQuestion(queryQuestion);
            List<QuestionOption> options = optionService.queryOptionListByQstId(question
                    .getId());
            ExamProfessionalDTO examProfessionalDto = professionalService.getProfessionalBySubjectId(question.getSubjectId());
            request.setAttribute("question", question);
            request.setAttribute("options", options);
            request.setAttribute("examProfessionalDto", examProfessionalDto);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.lookQuestion", e);
            return setExceptionRequest(request, e);
        }
        return lookQuestion;
    }

    // 到更新试题的页面
    @RequestMapping("/quest/toUpdateQuestion")
    public String toUpdateQuestion(
            @ModelAttribute("queryQuestion") QueryQuestion queryQuestion,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            Question question = questionService.getOneQuestion(queryQuestion);
            ExamProfessionalDTO examProfessionalDto = professionalService.getProfessionalBySubjectId(question.getSubjectId());
            request.setAttribute("examProfessionalDto", examProfessionalDto);
            List<QuestionOption> options = optionService.queryOptionListByQstId(question
                    .getId());
            request.setAttribute("question", question);
            request.setAttribute("options", options);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.toUpdateQuestion", e);
            return setExceptionRequest(request, e);
        }
        return toUpdateQuestion;
    }

    @RequestMapping("/quest/toQuestionList")
    public String toQuestionList(
            @ModelAttribute("queryQuestion") QueryQuestion queryQuestion, @ModelAttribute("page") PageEntity page,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            this.setPage(page);
          //获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            if (queryQuestion == null) {
                queryQuestion = new QueryQuestion();
            }
            this.getPage().setPageSize(10);// 每页分页的数量10页
            List<QueryQuestion> questionList = questionService.getQuestionAllList(
                    queryQuestion, this.getPage());
            request.setAttribute("questionList", questionList);
            request.setAttribute("professionalList",professionalList);
            request.setAttribute("page", this.getPage());
            request.setAttribute("queryQuestion", queryQuestion);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.toQuestionList", e);
            return setExceptionRequest(request, e);
        }
        return toQuestionList;
    }

    @RequestMapping("/quest/toQuestionListByType")
    public String toQuestionListByType(
            @ModelAttribute("queryQuestion") QueryQuestion queryQuestion,
            @ModelAttribute("qstMiddle") QstMiddle qstMiddle,
            @RequestParam("complexId") Integer complexId,
            @ModelAttribute("page") PageEntity page,
            HttpServletRequest request, HttpServletResponse response
    ) {
        try {

        	  //获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            if (queryQuestion == null) {
                queryQuestion = new QueryQuestion();
            }
            //用于判断是否是材料题
            queryQuestion.setComplexFalg(Long.valueOf(complexId));
            List<QueryQuestion> questionList = questionService.getQuestionAllList(
                    queryQuestion, page);
            request.setAttribute("questionList", questionList);
            request.setAttribute("professionalList", professionalList);
            request.setAttribute("page", page);
            request.setAttribute("complexId", complexId);
            request.setAttribute("qstMiddle", qstMiddle);
            request.setAttribute("qstChecked", request.getParameter("qstChecked"));
            request.setAttribute("qstNum", request.getParameter("qstNum"));
            request.setAttribute("queryQuestion", queryQuestion);

        } catch (Exception e) {
            logger.error("AdminQuestionAction.toQuestionListByType", e);
            return setExceptionRequest(request, e);
        }
        return toQuestionListByType;
    }

    // 批量删除试题
    @RequestMapping("/quest/delQuestionListBatch")
    @ResponseBody
    public Map<String, Object> delQuestionListBatch(HttpServletRequest request,
                                                    HttpServletResponse response) {
        try {
            String questionIds = request.getParameter("questionIds");
            questionService.delQuestionListBatch(questionIds);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.delQuestionListBatch", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 更新试题
    @RequestMapping("/quest/updateQuestion")
    public String updateQuestion(@ModelAttribute("question") Question question,
                                 @RequestParam("asr") List<String> asr, HttpServletRequest request,
                                 HttpServletResponse response) {
        try {
            questionService.updateQuestion(question, asr);
            return updateQuestion;
        } catch (Exception e) {
            logger.error("AdminQuestionAction.updateQuestion", e);
            return setExceptionRequest(request, e);
        }
    }

    @RequestMapping("/ajax/quest/delQstMiddleById")
    public String delQstMiddleById(
            @ModelAttribute("qstMiddle") QstMiddle qstMiddle, HttpServletRequest request,
            HttpServletResponse response) {
        try {
        	String paperId=request.getParameter("paperId");
            questionService.delQstMiddleById(qstMiddle);
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByPaperId(Long.parseLong(paperId),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.delQstMiddleById", e);
            return this.setExceptionRequest(request,e);
	    }
        return examPaperAssembleAjax;
    }

    // 试题上移
    @RequestMapping("/ajax/quest/moveUp")
    public String moveUp(@ModelAttribute("qstMiddle") QstMiddle qstMiddle,HttpServletRequest request, HttpServletResponse response) {
        try {
            int oneSort = RequestUtil.getInt(request, "oneSort");
            Long oneId = RequestUtil.getLong(request, "oneId");
            int twotSort = RequestUtil.getInt(request, "twotSort");
            Long twoId = RequestUtil.getLong(request, "twoId");
            questionService.updateMoveUp(oneSort, oneId, twotSort, twoId, qstMiddle);
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByPaperId(qstMiddle.getPaperId(),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.moveUp", e);
            return this.setExceptionRequest(request,e);
	    }
        return examPaperAssembleAjax;
    }

    // 到试题随机页面
    @RequestMapping("/quest/toRandomQuestion")
    public String toRandomQuestion(HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            request.setAttribute("professionalList", professionalList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.toRandomQuestion", e);
            return setExceptionRequest(request, e);
        }
        return toRandomQuestion;
    }

    // 生成随机试题
    @RequestMapping("/quest/randomQuestion")
    @ResponseBody
    public Map<String, Object> randomQuestion(
            @ModelAttribute("qstMiddle") QstMiddle qstMiddle, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            String pointIds = request.getParameter("pointIds");
            int num = RequestUtil.getInt(request, "num");
            List<QueryQuestion> questionList = questionService
                    .getRandomQuestionByPointIds(pointIds, qstMiddle.getQstType(), num);
            this.setJson(true, "", questionList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.randomQuestion", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    @RequestMapping("/quest/batch")
    public String batch() {
        return batch;
    }
    
    @RequestMapping("/quest/batchword")
    public String batchWord() {
        return batch_word;
    }

    // 批量添加 excel 试题
    @RequestMapping("/quest/importExcel")
    public String importExcel(HttpServletRequest request, @RequestParam("myFile") MultipartFile myFile) {
        try {
            logger.info("myFile:" + myFile.getName());
            questionService.updateImportExcel(myFile);

            request.setAttribute("msg", "操作成功");
        } catch (BaseException e) {
            logger.error("QuestionAction.importExcel", e);
            request.setAttribute("msg", e.getMessage());
            return msgError;
        } catch (Exception e) {
            logger.error("AdminQuestionAction.randomQuestion", e);
            request.setAttribute("msg", e.getMessage());
            return msgError;
        }
        //return ADMIN_SUCCESS;
        return "/common/success";
    }
    
    
    // 批量添加 Word 试题
    @RequestMapping("/quest/importWord")
    public String importWord(HttpServletRequest request, @RequestParam("myFile") MultipartFile myFile) {
        try {
            logger.info("myFile:" + myFile.getName());
            //File _1 = (File)myFile;
            questionService.updateImportWord(myFile);

            request.setAttribute("msg", "操作成功");
        } catch (BaseException e) {
            logger.error("QuestionAction.importExcel", e);
            request.setAttribute("msg", e.getMessage());
            return msgError;
        } catch (Exception e) {
            logger.error("AdminQuestionAction.randomQuestion", e);
            request.setAttribute("msg", e.getMessage());
            return msgError;
        }
        //return ADMIN_SUCCESS;
        return "/common/success";
    }

    /**
     * 学生回答主观题试题列表
     *
     * @return
     */
    @RequestMapping("/quest/toSubjectiveQstRecordList")
    public ModelAndView toSubjectiveQstRecordList(
            HttpServletRequest request, HttpServletResponse responses, @ModelAttribute("page") PageEntity page,
            @ModelAttribute("queryQuestion") QueryQuestion queryQuestion) {
        ModelAndView modelAndView = new ModelAndView(toSubjectiveQstRecordList);
        try {
            List<QueryQuestion> queryQuestionList = questionService.getQuestionSubjectiveList(queryQuestion, page);
            //查询出的数据放入modelView中
            modelAndView.addObject("queryQuestion", queryQuestion);
            modelAndView.addObject("queryQuestionList", queryQuestionList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("toPaperRecord", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 查询纠错列表
     *
     * @param request
     * @param page
     * @param questErrorCheck
     * @return
     */
    @RequestMapping("/quest/queryQuestErrorCheckList")
    public ModelAndView queryQuestErrorCheckList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("questErrorCheck") QuestErrorCheck questErrorCheck) {

        ModelAndView modelAndView = new ModelAndView(queryQuestECList);
        try {
            this.setPage(page);
            List<QuestErrorCheck> questErrorCheckList = questErrorCheckService.queryQuestErrorCheckList(questErrorCheck, this.getPage());
            modelAndView.addObject("questErrorCheckList", questErrorCheckList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("AdminQuestionAction.queryQuestErrorCheckList", e);
        }
        return modelAndView;
    }

    /**
     * 删除纠错问题
     *
     * @param request
     * @param questErrorCheck
     * @return
     */
    @RequestMapping("/quest/delQuestErrorCheckById")
    @ResponseBody
    public Map<String, Object> delQuestErrorCheckById(HttpServletRequest request, @ModelAttribute("questErrorCheck") QuestErrorCheck questErrorCheck) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (ObjectUtils.isNotNull(questErrorCheck)) {
                String flag = questErrorCheckService.delQuestErrorCheckById(questErrorCheck);
                map.put("message", flag);
            }
        } catch (Exception e) {
            logger.error("AdminQuestionAction.delQuestErrorCheckById", e);
        }
        return map;
    }

}
