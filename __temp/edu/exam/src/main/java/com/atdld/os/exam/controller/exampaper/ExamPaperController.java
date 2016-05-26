package com.atdld.os.exam.controller.exampaper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.customer.CusDateRecord;
import com.atdld.os.exam.entity.exampaper.ExamPaper;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.exampaper.PaperMiddle;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaper;
import com.atdld.os.exam.entity.exampaper.QueryPaperRecord;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.QuestionRecordBean;
import com.atdld.os.exam.entity.question.QuestionRecordForm;
import com.atdld.os.exam.service.customer.CusDateRecordService;
import com.atdld.os.exam.service.exampaper.ExamPaperRecordService;
import com.atdld.os.exam.service.exampaper.ExamPaperService;
import com.atdld.os.exam.service.exampaper.ExamRecordService;
import com.atdld.os.exam.service.exampaper.PaperMiddleService;
import com.atdld.os.exam.service.question.QuestionService;

/**
 * @author
 * @ClassName ExamPaperAction
 * @package com.atdld.os.exam.controller.examPaper
 * @description 试卷管理
 * @Create Date: 2013-9-7 下午3:42:33
 */
@Controller
@RequestMapping("/paper")
public class ExamPaperController extends ExamBaseController {
    private static final Logger logger = Logger.getLogger(ExamPaperController.class);

    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private PaperMiddleService paperMiddleService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CusDateRecordService cusDateRecordService;
    @Autowired
    private ExamPaperRecordService examPaperRecordService;
    @Autowired
    private ExamRecordService examRecordService;

    // 返回路径
    private String toExamPaper = getViewPath("/paper/to_ExamPaper");
    private String toExamPaperRecord = getViewPath("/paper/to_ExamPaper_record");
    private String getRandomQuestionByPointIds = getViewPath("/paper/random_question_test");
    private String toRandomQuestionExamPaperRecord = getViewPath("/paper/random_question_record");
    private String toExamPaperRecordList = getViewPath("/question/test-myHistory");
    private String toRandomQuestionExamPaperRecordContinue = getViewPath("/paper/random_question_continue");
    private String toQuestionExamPaperRecordContinue = getViewPath("/paper/paper_record_continue");
    private String getExamPaperReport = getViewPath("/question/test-report");
    private String competentAssessment = getViewPath("/paper/test-nl-report");
    private String queryPaperListByType = getViewPath("/ajax/ajaxitemlist");


    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryPaper")
    public void initBinderqueryPaper(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryPaper.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("paper")
    public void initBinderpaper(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("paper.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("paperRecord")
    public void initBinderpaperRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("paperRecord.");
    }

    public MemCache memCache = MemCache.getInstance();

    // 去考试页面 paper.id
    @RequestMapping("/toExamPaper/{paperId}")
    public ModelAndView toExamPaper(@PathVariable Long paperId,RedirectAttributes redirectAttributes, HttpServletRequest request) {
        ModelAndView andView = new ModelAndView(toExamPaper);
        try {
            ExamPaper paper = new ExamPaper();
            paper.setId(paperId);
            paper = examPaperService.queryExamPaperById(paper);
            if(ObjectUtils.isNull(paper)){
            	 redirectAttributes.addAttribute("msg", "对不起,该试卷不存在！");
                 return new ModelAndView("redirect:/front/success");
            }
            List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByPaperId(paper.getId(), getLoginUserId(request));
            andView.addObject("paperMiddleList", paperMiddleList);
            andView.addObject("paper", paper);

        } catch (Exception e) {
            logger.error("ExamPaperAction.toExamPaper", e);
            return new ModelAndView(setExceptionRequest(request, e));

        }
        return andView;
    }

    // 去试题记录页面
    @RequestMapping("/toExamPaperRecord/{id}")
    public String toExamPaperRecord(@PathVariable Long id, HttpServletRequest request) {

        List<PaperMiddle> paperMiddleList = null;
        try {

            PaperRecord paperRecord = new PaperRecord();
            paperRecord.setId(id);
            paperRecord.setCusId(getLoginUserId(request));
            // 获得考试记录
            paperRecord = paperMiddleService.queryPaperRecordByIdAndCusId(paperRecord);
            if (ObjectUtils.isNull(paperRecord)) {
                return toExamPaperRecord;
            }
            // paperRecord =
            // paperMiddleService.queryPaperRecordById(paperRecord);
            ExamPaper paper = new ExamPaper();
            paper.setId(paperRecord.getEpId());
            paper = examPaperService.queryExamPaperById(paper);
            paperRecord.setCusId(getLoginUserId(request));
            request.setAttribute("paper", paper);
            request.setAttribute("paperRecord", paperRecord);
            // 当类型等于2时他是试题组卷
            if (paperRecord.getType() == 2) {
                paperMiddleList = paperMiddleService.getPaperMiddleListByExamPaperRecord(paperRecord);
                request.setAttribute("paperMiddleList", paperMiddleList);
                if (paperRecord.getStatus() == 1) {
                    // 试卷继续考试
                    return toQuestionExamPaperRecordContinue;
                }
                // 试卷查看结果
                return toExamPaperRecord;
            }
            // 当类型等于1时他是随机试题组卷
            if (paperRecord.getType() == 1) {
                List<QueryQuestion> queryQuestionList = paperMiddleService.getRandomQuestionByExamPaperRecord(paperRecord);
                request.setAttribute("queryQuestionList", queryQuestionList);
                if (paperRecord.getStatus() == 1) {
                    // 随机测试继续考试
                    return toRandomQuestionExamPaperRecordContinue;
                } else {
                    // 随机测试查看结果
                    return toRandomQuestionExamPaperRecord;
                }
            }

        } catch (Exception e) {
            logger.error("ExamPaperAction.toExamPaperRecord", e);
            return setExceptionRequest(request, e);
        }
        return toExamPaperRecord;
    }

    // 通过考点随机生成试题
    @RequestMapping("/getRandomQuestionByPointIds")
    public String getRandomQuestionByPointIds(@RequestParam("randomnum") Integer num, HttpServletRequest request) {
        try {
            String pointIds = request.getParameter("pointIds");
            String paperTitle = "专项智能练习";
            request.setAttribute("paperTitle", paperTitle);
            // 抽题数量
            Long cusId = getLoginUserId(request);
            String qstIds = questionService.getRandomQuestionByPointIds(cusId, pointIds, num);
            request.setAttribute("testTime", 30);// 随机的考试时间
            if (qstIds != null && qstIds.length() != 0) {
                List<QueryQuestion> queryQuestionList = questionService.getQuestionByQuestionIds(cusId, qstIds);
                request.setAttribute("queryQuestionList", queryQuestionList);
            }
            request.setAttribute("t_type", 2);// 随机测试类型1 错题 2考点测试
        } catch (Exception e) {
            logger.error("ExamPaperAction.getRandomQuestionByPointIds", e);
            return setExceptionRequest(request, e);
        }
        return getRandomQuestionByPointIds;
    }

    // 错题智能练习
    @RequestMapping("/getRandomQuestion")
    public String getRandomQuestion(HttpServletRequest request, @RequestParam("falg") Integer falg, @RequestParam("prac") Integer prac) {
        try {
            String paperTitle = "错题智能练习";
            request.setAttribute("paperTitle", paperTitle);
            Long cusId = getLoginUserId(request);
            // 抽题数量
            Integer num = 30;
            String qstIds = "";
            if (falg == 2) {// 随机抽试题
                qstIds = questionService.getRandomQuestionByErrorQst(num, this.getLoginSubjectId(request), cusId);
            } else {// 顺序抽题
                qstIds = questionService.getQuestionByErrorQst(num, this.getLoginSubjectId(request), cusId);
            }

            request.setAttribute("testTime", 30);// 随机的考试时间
            if (qstIds != null && qstIds.length() != 0) {
                List<QueryQuestion> queryQuestionList = questionService.getQuestionByQuestionIds(cusId, qstIds);
                request.setAttribute("queryQuestionList", queryQuestionList);
            }
            request.setAttribute("t_type", 1);// 随机测试类型1 错题 2考点测试
            request.setAttribute("prac", prac);
        } catch (Exception e) {
            logger.error("ExamPaperAction.getRandomQuestion", e);
            return setExceptionRequest(request, e);
        }
        return getRandomQuestionByPointIds;
    }

    // 通过考点随机生成试题
    @RequestMapping("/getQuestionByQuestionIds")
    public String getQuestionByQuestionIds(HttpServletRequest request) {
        try {
            Long cusId = getLoginUserId(request);
            request.setAttribute("testTime", 30);// 随机的考试时间
            String qstIds = request.getParameter("qstIds");
            if (qstIds != null && qstIds.length() != 0) {
                List<QueryQuestion> queryQuestionList = questionService.getQuestionByQuestionIds(cusId, qstIds);
                request.setAttribute("queryQuestionList", queryQuestionList);
            }
        } catch (Exception e) {
            logger.error("ExamPaperAction.getQuestionByQuestionIds", e);
            return setExceptionRequest(request, e);
        }
        return getRandomQuestionByPointIds;
    }

    // 考试记录列表
    @RequestMapping("/toExamPaperRecordList")
    public String toExamPaperRecordList(@ModelAttribute("paperRecord") PaperRecord paperRecord, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        try {

            paperRecord.setCusId(getLoginUserId(request));
            paperRecord.setSubjectId(this.getLoginSubjectId(request));
            List<QueryPaperRecord> queryPaperRecordList = examPaperRecordService.queryExamPaperRecordList(paperRecord, page);
            request.setAttribute("queryPaperRecordList", queryPaperRecordList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("ExamPaperAction.toExamPaperRecordList", e);
            return setExceptionRequest(request, e);
        }
        return toExamPaperRecordList;
    }

    // 生成该用户考试记录
    @RequestMapping(value = "/addPaperRecord", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addPaperRecord(@ModelAttribute("paperRecord") PaperRecord paperRecord, @RequestParam(required = true) String optype, QuestionRecordForm questionRecordForm,
                                              HttpServletRequest request) {
        try {
            if (ObjectUtils.isNull(questionRecordForm) || ObjectUtils.isNull(questionRecordForm.getRecord())) {
                this.setJson(false, "", null);
                return json;
            }
            List<QuestionRecordBean> recordBeans = questionRecordForm.getRecord();
            String paperTitle = request.getParameter("paperTitle");
            Long cusId = getLoginUserId(request);
            paperRecord.setCusId(cusId);
            paperRecord.setSubjectId(this.getLoginSubjectId(request));
            if ("1".equals(optype)) {// 下次再做操作
                paperRecord.setStatus(1);
            } else if ("0".equals(optype)) {// 提交试卷
                paperRecord.setStatus(0);
            }
            // 专项智能练习添加记录
            if (paperRecord.getType() == 1) {
                if (paperRecord.getId() == null || paperRecord.getId() == 0) {
                    // 我要交卷或者第一次点下次再做
                    examPaperRecordService.addRandomPaperRecord(paperTitle, recordBeans, paperRecord);
                } else {
                    // （继续做题时再次提交）
                    examPaperRecordService.updateRandomPaperRecord(recordBeans, paperRecord);
                }
            }
            // 试题组卷的卷子添加记录
            if (paperRecord.getType() == 2) {
                if (paperRecord.getId() == null || paperRecord.getId().longValue() == 0) {
                    // 我要交卷或者第一次点下次再做
                    examPaperRecordService.addPaperRecord(recordBeans, paperRecord);
                } else {
                    // （继续做题时再次提交）
                    examPaperRecordService.updatePaperRecord(recordBeans, paperRecord);
                }
            }
            this.setJson(true, optype, paperRecord.getId());
        } catch (Exception e) {
            this.setJson(false, "", null);
            logger.error("ExamPaperAction.addPaperRecord", e);
        }
        return json;
    }

    // 继续上次练习
    @RequestMapping("/continuePractise")
    @ResponseBody
    public Map<String, Object> continuePractise(@ModelAttribute("paperRecord") PaperRecord paperRecord, HttpServletRequest request) {
        try {
            paperRecord = new PaperRecord();
            Long cusId = 1L;
            Long subjectId = 1L;
            paperRecord.setCusId(cusId);
            paperRecord.setSubjectId(subjectId);
            QueryPaperRecord queryPaperRecord = examPaperRecordService.continuePractise(paperRecord);
            this.setJson(true, "", queryPaperRecord);
        } catch (Exception e) {
            logger.error("ExamPaperAction.continuePractise", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 查看报告
    @RequestMapping("/getExamPaperReport/{id}")
    public String getExamPaperReport(@PathVariable Long id,RedirectAttributes redirectAttributes, HttpServletRequest request) {
        try {
            PaperRecord paperRecord = new PaperRecord();
            paperRecord.setId(id);
            paperRecord = examPaperRecordService.queryPaperRecordById(paperRecord);
            if(ObjectUtils.isNull(paperRecord)){
            	redirectAttributes.addAttribute("msg", "对不起,试卷报告不存在！");
            	return "redirect:/front/success";
            }
            paperRecord.setCusId(getLoginUserId(request));
            paperRecord.setSubjectId(this.getLoginSubjectId(request));
            List<QueryPaperRecord> queryPaperReport = examPaperRecordService.getExamPaperReport(paperRecord);
            // 查询该试卷的分数排名
            // 该试卷的分数排名
            int ScoreRanking = examPaperRecordService.queryExamPaperRecordScoreRanking(paperRecord);
            // 查询该试卷的正确题数排名
            int CorrectNumRanking = examPaperRecordService.queryExamPaperRecordCorrectNumRanking(paperRecord);
            ExamPaper paper = new ExamPaper();
            paper.setId(paperRecord.getEpId());
            paper = examPaperService.queryExamPaperById(paper);
            request.setAttribute("ScoreRanking", ScoreRanking);
            request.setAttribute("CorrectNumRanking", CorrectNumRanking);
            request.setAttribute("queryPaperReport", queryPaperReport);
            request.setAttribute("paperRecord", paperRecord);
        } catch (Exception e) {
            logger.error("ExamPaperAction.getExamPaperReport", e);
            return setExceptionRequest(request, e);
        }
        return getExamPaperReport;
    }

    /**
     * 查询我考过的记录，只显示最近的4条
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/querypaperRecordByCusIdAndSubjectId")
    @ResponseBody
    public Map<String, Object> querypaperRecordByCusIdAndSubjectId(@ModelAttribute("paperRecord") PaperRecord paperRecord, HttpServletRequest request) {
        try {
            List<QueryPaperRecord> queryPaperRecordList = (List<QueryPaperRecord>) memCache.get(MemConstans.MEM_CUS_LASTPAPER + getLoginUserId(request));
            if (queryPaperRecordList == null || queryPaperRecordList.size() == 0) {
                paperRecord = new PaperRecord();
                paperRecord.setCusId(getLoginUserId(request));
                paperRecord.setSubjectId(this.getLoginSubjectId(request));
                if (this.getLoginSubjectId(request) != 0) {
                    this.getPage().setPageSize(4);
                    queryPaperRecordList = examPaperRecordService.queryExamPaperRecordList(paperRecord, this.getPage());
                    if (queryPaperRecordList != null && queryPaperRecordList.size() > 0) {
                        memCache.set(MemConstans.MEM_CUS_LASTPAPER + getLoginUserId(request), queryPaperRecordList, MemConstans.MEM_COMMON_TIME);
                    }
                }
            }
            this.setJson(true, "", queryPaperRecordList);
        } catch (Exception e) {
            logger.error("ExamPaperAction.querypaperRecordByCusIdAndSubjectId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * ajax 获得试卷
     *
     * @param queryPaper
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/ajax/queryPaperListByType")
    public ModelAndView queryPaperListByType(@ModelAttribute("queryPaper") QueryPaper queryPaper, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            queryPaper.setSubjectId(this.getLoginSubjectId(request));
            queryPaper.setCusId(getLoginUserId(request));
            this.setPage(page);
            this.getPage().setPageSize(6);
            List<QueryPaper> paperList = examPaperService.getPaperAllList(queryPaper, this.getPage());
            modelAndView.setViewName(queryPaperListByType);
            modelAndView.addObject("paperList", paperList);
            modelAndView.addObject("page", this.getPage());
        } catch (Exception e) {
            logger.error("ExamPaperAction.queryPaperListByType", e);
        }
        return modelAndView;
    }

    /**
     * 能力评估页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/competentAssessment")
    public String competentAssessment(HttpServletRequest request) {
        try {
            CusDateRecord cusDateRecord = new CusDateRecord();
            cusDateRecord.setCusId(getLoginUserId(request));
            cusDateRecord.setSubjectId(this.getLoginSubjectId(request));
            // 查询用户考试记录
            cusDateRecord = cusDateRecordService.queryCusDateRecordByCusIdAndSubjectId(cusDateRecord);
            // 平均分排名
            int AverageScoreRanking = cusDateRecordService.queryCusDateRecordAverageScoreRanking(cusDateRecord);
            request.setAttribute("cusDateRecord", cusDateRecord);
            request.setAttribute("AverageScoreRanking", AverageScoreRanking);
        } catch (Exception e) {
            logger.error("ExamPaperAction.competentAssessment", e);
            return setExceptionRequest(request, e);
        }
        return competentAssessment;
    }

    @RequestMapping("/examPaperForTimeAndAverageScore")
    public String examPaperForTimeAndAverageScore(HttpServletRequest request) {// 更新每个试卷的参考人数和每个试卷的平均分
        // 和每个试题最对过的次数正确率等
        try {
            ExamRecord examRecord = new ExamRecord();
            examRecord.setType("1");
            // 查找examRecord获得上次的记录
            examRecord = examRecordService.queryExamRecordByType(examRecord);
            // 更新每个试卷的参考人数和每个试卷的平均分
            examPaperService.updateAllExamPaperByPaperRecord(examRecord);
            // 每个试题最对过的次数做错过的次数正确率等
            questionService.updateQuestionByPaperRecord(examRecord);
            // 获得最大的paperRecordId更新到ExamRecord中
            Date updateTime = examPaperRecordService.queryExamPaperRecordMaxUpdateTime();
            examRecord.setLastUpdateRecord(updateTime);
            examRecordService.updateExamRecordById(examRecord);
        } catch (Exception e) {
            logger.error("ExamPaperAction.examPaperForTimeAndAverageScore", e);
            return setExceptionRequest(request, e);
        }
        return null;
    }

}
