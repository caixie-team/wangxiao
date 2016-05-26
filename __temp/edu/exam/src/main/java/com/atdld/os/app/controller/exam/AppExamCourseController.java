package com.atdld.os.app.controller.exam;


import com.atdld.os.app.controller.exam.common.AppBaseController;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.exam.entity.customer.CusDateRecord;
import com.atdld.os.exam.entity.exampaper.*;
import com.atdld.os.exam.entity.favorite.Favorite;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.QuestionRecordBean;
import com.atdld.os.exam.entity.question.QuestionRecordForm;
import com.atdld.os.exam.service.customer.CusDateRecordService;
import com.atdld.os.exam.service.exampaper.ExamPaperRecordService;
import com.atdld.os.exam.service.exampaper.ExamPaperService;
import com.atdld.os.exam.service.exampaper.PaperMiddleService;
import com.atdld.os.exam.service.point.PointService;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.question.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * app考试接口
 */
@Controller
@RequestMapping("/webapp/exam")
public class AppExamCourseController extends AppBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AppExamCourseController.class);
    @Autowired
    private PaperMiddleService paperMiddleService;
    @Autowired
    private CusDateRecordService cusDateRecordService;
    @Autowired
    private PointService pointService;
    @Autowired
    private ExamPaperRecordService examPaperRecordService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private ProfessionalService professionalService;

    public MemCache memCache = MemCache.getInstance();

	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("appWebsiteCourse")
	public void initBinderWebsiteCourse(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("appWebsiteCourse.");
	}

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("paperRecord")
    public void initBinderpaperRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("paperRecord.");
    }


    /**
     * 专业接口
     * */
    @RequestMapping("/sub")
    @ResponseBody
    public Map<String,Object> getSubjectList(HttpServletRequest request){
        try{
            List<ExamProfessional> subjectList = professionalService.queryProfessionalList();
            this.setJson(true, "操作成功", subjectList);
        }catch (Exception e) {
            this.setJson(false, "系统繁忙", e);
            logger.error("countUserApply()---error",e);
        }
        return json;
    }

    /**
     * 考试中心
     * */
    @RequestMapping("/examuc")
    @ResponseBody
    public Map<String,Object> examuc(HttpServletRequest request,@RequestParam Long cusId,@RequestParam Long subId){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            // 查询用户全站的考试记录
            CusDateRecord cusDateRecord = new CusDateRecord();
            cusDateRecord.setCusId(cusId);
            cusDateRecord.setSubjectId(subId);
            cusDateRecord = cusDateRecordService.queryCusDateRecordByCusIdAndSubjectId(cusDateRecord);
            map.put("cusDateRecord",cusDateRecord);
            List<ExamPoint> pointList = new ArrayList<ExamPoint>();
            //该专业下全部的考点
            List<ExamPoint> pointListAll = pointService.getPointListBySubject(subId);
            //获得一级考点
            for(ExamPoint e:pointListAll){
                if(e.getParentId()==0){
                    pointList.add(e);
                }
            }

            //添加二级考点
            for(ExamPoint e1:pointList){
                for(ExamPoint e2:pointListAll){
                    if(e1.getId().longValue()==e2.getParentId().longValue()){
                        e1.getExamPointList().add(e2);
                    }
                }
            }
            map.put("pointList",pointList);
            this.setJson(true, "成功", map);
        }catch (Exception e) {
            this.setJson(false, "系统繁忙", null);
            logger.error("examuc",e);
        }
        return json;
    }

    /**
     * 错题记录
     * */
    @RequestMapping("/errqst")
    @ResponseBody
    public Map<String,Object> errqst(HttpServletRequest request,@RequestParam Long cusId,@RequestParam Long subId, @ModelAttribute("page") PageEntity page){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
            queryErrorQuestion.setSubjectId(subId);
            queryErrorQuestion.setCusId(cusId);
            List<QueryQuestion> queryQuestionList = questionService.getErrorQuestionList(queryErrorQuestion, page);
            map.put("page", page);
            map.put("queryQuestionList", queryQuestionList);
            this.setJson(true, "success", map);
        }catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("errqst",e);
        }
        return json;
    }
    /**
     * 练习历史
     * */
    @RequestMapping("/practicehis")
    @ResponseBody
    public Map<String,Object> practicehis(HttpServletRequest request,@RequestParam Long cusId,@RequestParam Long subId, @ModelAttribute("page") PageEntity page){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            PaperRecord paperRecord = new PaperRecord();
            paperRecord.setCusId(cusId);
            paperRecord.setSubjectId(subId);
            paperRecord.setPaperName("专项智能练习");
            List<QueryPaperRecord> queryPaperRecordList = examPaperRecordService.queryExamPaperRecordList(paperRecord, page);

            map.put("queryPaperRecordList",queryPaperRecordList);
            map.put("page",page);
            this.setJson(true, "success", map);
        }catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("errqst",e);
        }
        return json;
    }

    // 到写过笔记的题的分页
    @RequestMapping("/notesql")
    @ResponseBody
    public Map<String,Object> notesql(@RequestParam Long cusId,@RequestParam Long subId, @ModelAttribute("page") PageEntity page) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            QueryNoteQuestion queryNoteQuestion = new QueryNoteQuestion();
            queryNoteQuestion.setSubjectId(subId);
            queryNoteQuestion.setCusId(cusId);
            List<QueryQuestion> queryQuestionList = questionService.getNoteQuestionList(queryNoteQuestion, page);
            map.put("page", page);
            map.put("queryQuestionList",queryQuestionList);
            this.setJson(true, "success", map);
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("notesql",e);
        }
        return json;
    }

    // 收藏的试题
    @RequestMapping("/collectqst")
    @ResponseBody
    public Map<String,Object> collectqst(@RequestParam Long cusId,@RequestParam Long subId, @ModelAttribute("page") PageEntity page) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            QueryQuestion queryQuestion = new QueryQuestion();
            queryQuestion.setCusId(cusId);
            queryQuestion.setSubjectId(subId);
            List<QueryQuestion> queryQuestionList = questionService.getFavoriteQuestion(queryQuestion, page);
            map.put("page",page);
            map.put("queryQuestionList",queryQuestionList);
            this.setJson(true, "success", map);
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("collectqst",e);
        }
        return json;
    }
    // 查看单个试题解析
    @RequestMapping("/qstanalysis")
    @ResponseBody
    public Map<String,Object> qstanalysis(@RequestParam Long qstId,@RequestParam Long cusId) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            QueryQuestion queryQuestion = new QueryQuestion();
            queryQuestion.setId(qstId);
            queryQuestion.setCusId(cusId);
            queryQuestion = questionService.getParse(queryQuestion);
            //试题不存在
            if(ObjectUtils.isNull(queryQuestion)){
                this.setJson(true, "noqst", "试题不存在");
            }
            map.put("queryQuestion",queryQuestion);
            this.setJson(true, "success", map);
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("qstanalysis",e);
        }
        return json;
    }

    // 移除错题
    @RequestMapping("/delqst")
    @ResponseBody
    public Map<String,Object> delqst(@RequestParam String ids) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            if(StringUtils.isNotEmpty(ids)){
                String[] str = ids.split(",");
                for(String id :str){
                    if(StringUtils.isNotEmpty(id)){
                        QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
                        queryErrorQuestion.setId(Long.valueOf(id));
                        questionService.delQueryErrorQuestion(queryErrorQuestion);
                    }
                }

            }else{
                this.setJson(true, "idsnull", "ids参数为null");
            }
            this.setJson(true, "success", "");
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("qstanalysis",e);
        }
        return json;
    }
    // 清空错题
    @RequestMapping("/clearQst")
    @ResponseBody
    public Map<String,Object> clearQst(@RequestParam Long subId,@RequestParam Long cusId) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
            queryErrorQuestion.setCusId(cusId);
            queryErrorQuestion.setSubjectId(subId);
            questionService.clearQueryErrorQuestion(queryErrorQuestion);
            this.setJson(true, "success", "");
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("qstanalysis",e);
        }
        return json;
    }
    // 取消收藏
    @RequestMapping("/notfavorite")
    @ResponseBody
    public Map<String,Object> notfavorite(@RequestParam Long qstId,@RequestParam Long cusId) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Favorite favorite = new Favorite();
            favorite.setCusId(cusId);
            favorite.setQstId(qstId);
            questionService.delFavorite(favorite);
            this.setJson(true, "success", "");
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("notfavorite",e);
        }
        return json;
    }

    // 收藏试题
    @RequestMapping("/favoriteqst")
    @ResponseBody
    public Map<String,Object> favoriteQst(@RequestParam Long qstId,@RequestParam Long cusId,@RequestParam Long subjectId) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            Favorite favorite = new Favorite();
            favorite.setCusId(cusId);
            favorite.setAddDate(new Date());
            favorite.setSubjectId(subjectId);
            favorite.setQstId(qstId);
            questionService.addFavorite(favorite);
            this.setJson(true, "success", "");
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("favoriteQst",e);
        }
        return json;
    }


    // 试卷列表
    @RequestMapping("/papers")
    @ResponseBody
    public Map<String,Object> papers(@RequestParam int type,@RequestParam Long cusId,@RequestParam Long subId, @ModelAttribute("page") PageEntity page) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            QueryPaper queryPaper = new QueryPaper();
            queryPaper.setType(type);
            queryPaper.setSubjectId(subId);
            queryPaper.setCusId(cusId);
            this.setPage(page);
            this.getPage().setPageSize(6);
            List<QueryPaper> paperList = examPaperService.getPaperAllList(queryPaper, this.getPage());
            map.put("page",this.getPage());
            map.put("paperList",paperList);
            this.setJson(true, "success",map);
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("papers",e);
        }
        return json;
    }

    // 考试页面
    @RequestMapping("/exampaper")
    @ResponseBody
    public Map<String,Object> exampaper(@RequestParam Long paperId,@RequestParam Long cusId) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            ExamPaper paper = new ExamPaper();
            paper.setId(paperId);
            paper = examPaperService.queryExamPaperById(paper);
            if(ObjectUtils.isNull(paper)){
                this.setJson(true, "nopaper", "试卷不存在");
                return json;
            }
            //查询试卷
            List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByPaperId(paper.getId(), cusId);
            map.put("paperMiddleList",paperMiddleList);
            map.put("paper",paper);
            this.setJson(true, "success", map);
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("exampaper",e);
        }
        return json;
    }


    //生成该用户考试记录
    @RequestMapping("/addpaper")
    @ResponseBody
    public Map<String,Object> addpaper(@RequestParam Long cusId,@RequestParam Long subId,@ModelAttribute("paperRecord") PaperRecord paperRecord, @RequestParam(required = true) String optype, QuestionRecordForm questionRecordForm,
                                       HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {

            if (ObjectUtils.isNull(questionRecordForm) || ObjectUtils.isNull(questionRecordForm.getRecord())) {
                this.setJson(false, "", null);
                return json;
            }
            List<QuestionRecordBean> recordBeans = questionRecordForm.getRecord();
            String paperTitle = request.getParameter("paperTitle");
            paperRecord.setCusId(cusId);
            paperRecord.setSubjectId(subId);
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
            this.setJson(false, "error", null);
            logger.error("addpaper",e);
        }
        return json;
    }

    // 查看报告
    @RequestMapping("/viewreport")
    @ResponseBody
    public Map<String,Object> viewreport(@RequestParam Long id,@RequestParam Long cusId,@RequestParam Long subId) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            PaperRecord paperRecord = new PaperRecord();
            paperRecord.setId(id);
            paperRecord = examPaperRecordService.queryPaperRecordById(paperRecord);
            if(ObjectUtils.isNull(paperRecord)){
                this.setJson(true, "nopaper", "没有该记录");
                return json;
            }
            paperRecord.setCusId(cusId);
            paperRecord.setSubjectId(subId);
            List<QueryPaperRecord> queryPaperReport = examPaperRecordService.getExamPaperReport(paperRecord);
            // 查询该试卷的分数排名
            // 该试卷的分数排名
            int ScoreRanking = examPaperRecordService.queryExamPaperRecordScoreRanking(paperRecord);
            // 查询该试卷的正确题数排名
            int CorrectNumRanking = examPaperRecordService.queryExamPaperRecordCorrectNumRanking(paperRecord);
            ExamPaper paper = new ExamPaper();
            paper.setId(paperRecord.getEpId());
            paper = examPaperService.queryExamPaperById(paper);
            // 该试卷的分数排名
            map.put("ScoreRanking",ScoreRanking);
            // 查询该试卷的正确题数排名
            map.put("CorrectNumRanking",CorrectNumRanking);
            map.put("queryPaperReport",queryPaperReport);
            map.put("paperRecord",paperRecord);
            this.setJson(true, "success", map);
        } catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("viewreport",e);
        }
        return json;
    }
    // 查看解析
    @RequestMapping("/parsing")
    @ResponseBody
    public Map<String,Object> parsing(@RequestParam Long id,@RequestParam Long cusId,@RequestParam Long subId) {
        Map<String,Object> map = new HashMap<String,Object>();
        List<PaperMiddle> paperMiddleList = null;
        try {

            PaperRecord paperRecord = new PaperRecord();
            paperRecord.setId(id);
            paperRecord.setCusId(cusId);
            // 获得考试记录
            paperRecord = paperMiddleService.queryPaperRecordByIdAndCusId(paperRecord);
            if (ObjectUtils.isNull(paperRecord)) {
                this.setJson(true, "null", "考试记录并不存在");
                return json;
            }

            ExamPaper paper = new ExamPaper();
            paper.setId(paperRecord.getEpId());
            paper = examPaperService.queryExamPaperById(paper);
            paperRecord.setCusId(cusId);

            map.put("paper", paper);
            map.put("paperRecord", paperRecord);

            // 当类型等于2时他是试题组卷
            if (paperRecord.getType() == 2) {
                paperMiddleList = paperMiddleService.getPaperMiddleListByExamPaperRecord(paperRecord);
                map.put("paperMiddleList", paperMiddleList);
                if (paperRecord.getStatus() == 1) {
                    // 试卷继续考试
                    this.setJson(true, "继续考试", map);
                    return json;
                }

                // 试卷查看结果
                this.setJson(true, "试卷查看结果", map);
                return json;
            }
            // 当类型等于1时他是随机试题组卷
            if (paperRecord.getType() == 1) {
                List<QueryQuestion> queryQuestionList = paperMiddleService.getRandomQuestionByExamPaperRecord(paperRecord);
                map.put("queryQuestionList", queryQuestionList);
                if (paperRecord.getStatus() == 1) {
                    // 随机测试继续考试
                    this.setJson(true, "随机测试继续考试", map);
                    return json;
                } else {
                    // 随机测试查看结果
                    this.setJson(true, "随机测试查看结果", map);
                    return json;
                }
            }

        }catch (Exception e) {
            this.setJson(false, "error", null);
            logger.error("parsing",e);
        }
        return json;
    }

    // 添加笔记
    @RequestMapping("/addnote")
    @ResponseBody
    public Map<String, Object> addnote(@RequestParam Long cusId,@RequestParam Long subId,
                                          @ModelAttribute("queryNoteQuestion") QueryNoteQuestion queryNoteQuestion) {
        try {
            queryNoteQuestion.setCusId(cusId);
            queryNoteQuestion.setSubjectId(subId);
            questionService.insertNote(queryNoteQuestion);
            // 新建笔记清除该用户笔记缓存
            String NOTEKEY = MemConstans.MEM_CUS_NOTE + cusId;
            memCache.remove(NOTEKEY);
            this.setJson(true, "success", null);
        } catch (Exception e) {
            logger.error("addnote", e);
            this.setJson(false, "false", e);
            return json;
        }
        return json;
    }

    // 专项智能练习  从所选考点中随机抽题练习
    @RequestMapping("/getRandomQuestionByPointIds")
    @ResponseBody
    public Map<String, Object> getRandomQuestionByPointIds(@RequestParam Long cusId,@RequestParam Long subId,@RequestParam String pointIds,@RequestParam int num,
                                       @ModelAttribute("queryNoteQuestion") QueryNoteQuestion queryNoteQuestion) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String paperTitle = "专项智能练习";
            map.put("paperTitle", paperTitle);
            // 抽题数量
            String qstIds = questionService.getRandomQuestionByPointIds(cusId, pointIds, num);

            map.put("testTime", 30);// 随机的考试时间
            if (qstIds != null && qstIds.length() != 0) {
                List<QueryQuestion> queryQuestionList = questionService.getQuestionByQuestionIds(cusId, qstIds);
                map.put("queryQuestionList", queryQuestionList);
            }

            map.put("t_type", 2);// 随机测试类型1 错题 2考点测试

            this.setJson(true, "success", map);
        } catch (Exception e) {
            logger.error("getRandomQuestionByPointIds", e);
            this.setJson(false, "false", e);
            return json;
        }
        return json;
    }


    // 错题智能练习
    @RequestMapping("/getRandomQuestion")
    @ResponseBody
    public Map<String, Object> getRandomQuestion(@RequestParam Long cusId,@RequestParam Long subId,@RequestParam("falg") Integer falg) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String paperTitle = "错题智能练习";
            map.put("paperTitle", paperTitle);
            // 抽题数量
            Integer num = 30;
            String qstIds = "";
            if (falg == 2) {// 随机抽试题
                qstIds = questionService.getRandomQuestionByErrorQst(num, subId, cusId);
            } else {// 顺序抽题
                qstIds = questionService.getQuestionByErrorQst(num, subId, cusId);
            }

            map.put("testTime", 30);// 随机的考试时间
            if (qstIds != null && qstIds.length() != 0) {
                List<QueryQuestion> queryQuestionList = questionService.getQuestionByQuestionIds(cusId, qstIds);
                map.put("queryQuestionList", queryQuestionList);
            }
            //map.put("t_type", 1);// 随机测试类型1 错题 2考点测试
            //map.put("prac", prac);
            this.setJson(true, "success", map);
        } catch (Exception e) {
            logger.error("ExamPaperAction.getRandomQuestion", e);
            this.setJson(false, "false", e);
            return json;
        }
        return json;
    }
}
