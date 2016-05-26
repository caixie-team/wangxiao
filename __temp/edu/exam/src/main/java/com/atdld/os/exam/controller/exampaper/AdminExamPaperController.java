package com.atdld.os.exam.controller.exampaper;

import com.atdld.os.common.contoller.SingletonLoginUtils;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.RequestUtil;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.exampaper.*;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamProfessionalDTO;
import com.atdld.os.exam.entity.question.QstComplex;
import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.service.exampaper.ExamPaperRecordService;
import com.atdld.os.exam.service.exampaper.ExamPaperService;
import com.atdld.os.exam.service.exampaper.PaperMiddleService;
import com.atdld.os.exam.service.exampaper.PaperTypeService;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.question.QuestionService;
import com.atdld.os.exam.service.subject.SubjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @ClassName AdminExamPaperAction
 * @package com.atdld.os.exam.controller.examPaper.admin
 * @description 试卷管理
 * @Create Date: 2013-9-7 下午3:43:11
 */
@Controller
@RequestMapping("/admin")
public class AdminExamPaperController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(AdminExamPaperController.class);
    @Autowired
    private ExamPaperService examPaperService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PaperMiddleService paperMiddleService;
    @Autowired
    private PaperTypeService paperTypeService;
    @Autowired
    private ExamPaperRecordService examPaperRecordService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ProfessionalService professionalService;

    // 返回路径

    private String toAddPaper = getViewPath("/admin/paper/addExamPaper");
    private String listAllPaper = getViewPath("/admin/paper/paper_list");
    private String addExamPaper = "redirect:/admin/paper/listAllPaper";
    private String toUpdateExamPaper = getViewPath("/admin/paper/updateExamPaper");
    private String updateExamPaper = "redirect:/admin/paper/listAllPaper";
    private String showExamPaperById = getViewPath("/admin/paper/look_examPaper");
    private String queryPaperType = getViewPath("/admin/paper/paper_type");
    private String toExamPaperAssemble = getViewPath("/admin/paper/to_ExamPaper_assemble");
    private String toUpdatePaperType = getViewPath("/admin/paper/updatePaperType");
    private String toPaperRecord = getViewPath("/admin/paper/to_paperrecord_list");//考试记录查询
    private String toPaperRecordInfo = getViewPath("/admin/paper/to_exampaper_recordinfo");//考试记录详情
    private String toPaperRecordSubjective = getViewPath("/admin/paper/to_paperrecord_subjective");//包含主观题的试卷列表
    private String examPaperAssembleAjax = getViewPath("/admin/paper/examPaper_assemble_ajax");
    private String paperMiddleAjax=getViewPath("/admin/paper/paperMiddle_update_ajax");
    // 绑定变量名字和属性，参数封装进类
    @InitBinder("paper")
    public void initBinderPaper(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("paper.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("qstMiddle")
    public void initBinderQstMiddle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("qstMiddle.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("complex")
    public void initBinderComplex(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("complex.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryPaper")
    public void initBinderQueryPaper(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryPaper.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("paperMiddle")
    public void initBinderPaperMiddle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("paperMiddle.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("paperType")
    public void initBinderPaperType(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("paperType.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("page")
    public void initBinderPaperPage(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("page.");
    }

    //试卷记录
    @InitBinder("queryPaperRecord")
    public void initBinderQueryPaperRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryPaperRecord.");
    }

    /**
     * 添加试卷
     *
     * @return
     */
    @RequestMapping("/paper/addExamPaper")
    public String addExamPaper(@ModelAttribute("paper") ExamPaper paper,
                               HttpServletRequest request) {
        try {
            paper.setAuthor(SingletonLoginUtils.getSysLoginLoginName(request));
            examPaperService.addExamPaper(paper);
        } catch (Exception e) {
            logger.error("addExamPaper", e);
            return setExceptionRequest(request, e);
        }
        return addExamPaper;
    }

    /**
     * 跳转到添加试卷页面
     *
     * @return
     */
    @RequestMapping("/paper/toAddExamPaper")
    public String toAddExamPaper(HttpServletRequest request, HttpServletResponse response) {
        try {
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            List<PaperType> paperTypeList = paperTypeService.queryPaperTypeListByState(0);//0为显示状态的试卷分类
            request.setAttribute("professionalList", professionalList);
            request.setAttribute("paperTypeList", paperTypeList);
        } catch (Exception e) {
            logger.error("toAddExamPaper", e);
            return setExceptionRequest(request, e);
        }
        return toAddPaper;
    }

    /**
     * 组卷试题
     *
     * @return
     */
    @RequestMapping("/paper/toExamPaperAssemble")
    public String toExamPaperAssemble(@ModelAttribute("paper") ExamPaper paper,
                                      HttpServletRequest request, HttpServletResponse response) {
        try {
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(paper.getId(), null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("toExamPaperAssemble", e);
            return setExceptionRequest(request, e);
        }
        return toExamPaperAssemble;
    }

 // paperMiddle更新
    @RequestMapping("/ajax/paper/updatePaperMiddle")
    public String updatePaperMiddle(
            @ModelAttribute("paperMiddle") PaperMiddle paperMiddle,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            paperMiddleService.updatePaperMiddle(paperMiddle);
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(paperMiddle.getPaperId(),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("updatePaperMiddle", e);
            return this.setExceptionRequest(request, e);
        }
        return examPaperAssembleAjax;
    }

    // 添加paperMiddle
    @RequestMapping("/ajax/paper/addPaperMiddle")
    public String addPaperMiddle(
            @ModelAttribute("paperMiddle") PaperMiddle paperMiddle,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 保存试题组卷
            paperMiddleService.addPaperMiddle(paperMiddle);
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(paperMiddle.getPaperId(),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
        	logger.error("updatePaperMiddle", e);
            return this.setExceptionRequest(request, e);
        }
        return examPaperAssembleAjax;
    }

    @RequestMapping("/ajax/paper/addExamQstMiddleBatch")
    public String addExamQstMiddleBatch(
            @ModelAttribute("qstMiddle") QstMiddle qstMiddle, HttpServletRequest request,
            HttpServletResponse response) {
        String qstIds = request.getParameter("qstIds");
        try {

            // 保存试题组卷中的试题
            List<QstMiddle> qstMiddleList = examPaperService.addExamQstMiddleBatch(
                    qstIds, qstMiddle);
            if (ObjectUtils.isNotNull(qstMiddleList)) {
                qstIds = "";
                for (QstMiddle qstMiddl : qstMiddleList) {
                    qstIds += "" + qstMiddl.getQstId() + ",";
                }
            }

            Map<Long, QueryQuestion> map = questionService.getMapQuestionByQuestionIds(qstIds);
            if (ObjectUtils.isNotNull(qstMiddleList)) {
                qstIds = "";
                for (QstMiddle qstMiddl : qstMiddleList) {
                    QueryQuestion queryQuestion = map.get(qstMiddl.getQstId());
                    if (ObjectUtils.isNotNull(queryQuestion)) {
                        qstMiddl.setQstContent(queryQuestion.getQstContent());
                    }
                }
            }
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(qstMiddle.getPaperId(),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("addExamPaperAssemble", e);
            return this.setExceptionRequest(request, e);
	    }
	    return examPaperAssembleAjax;
    }
 // 添加材料题的材料
    @RequestMapping("/ajax/paper/addCaiLiaoContent")
    public String addCaiLiaoContent(
            @ModelAttribute("complex") QstComplex complex, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 添加材料内容
            // 页面要传试卷的paper_id和中间表的papermiddle_id
            complex = examPaperService.addCaiLiaoContent(complex);
         // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(complex.getPaperId(),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("addExamPaperAssemble", e);
            return this.setExceptionRequest(request, e);
	    }
	    return examPaperAssembleAjax;
    }

    // 通过complexId删除qstMiddle
    @RequestMapping("/ajax/paper/delCaiLiaoQuestion")
    public String delCaiLiaoQuestion(
            @RequestParam("paperId") Long paperId, HttpServletRequest request,
            @RequestParam("complexId") Long complexId, HttpServletResponse response) {
        try {
            // 更新材料内容
            paperMiddleService.delQstMiddleBycomplexId(paperId, complexId);
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(paperId,null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
	    	logger.error("delCaiLiaoQuestion", e);
	        return this.setExceptionRequest(request, e);
	    }
	    return examPaperAssembleAjax;
	 }

    @RequestMapping("/ajax/paper/updateCaiLiaoContent")
    public String updateCaiLiaoContent(
            @ModelAttribute("complex") QstComplex complex, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 更新材料内容
            examPaperService.updateCaiLiaoContent(complex);
            // 查询当前试卷的题
            String paperId=request.getParameter("paperId");
            List<PaperMiddle> paperMiddleList = paperMiddleService
                    .getPaperMiddleListByPaperId(Long.parseLong(paperId),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("updateCaiLiaoContent", e);
            return this.setExceptionRequest(request, e);
	    }
	    return examPaperAssembleAjax;
    }

    // 查询所有试卷
    @RequestMapping("/paper/listAllPaper")
    public ModelAndView listAllPaper(@ModelAttribute("queryPaper") QueryPaper queryPaper,
                                     @ModelAttribute("page") PageEntity page,
                                     HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(listAllPaper);
        try {
            this.setPage(page);
            if (queryPaper == null) {
                queryPaper = new QueryPaper();
            }
            List<QueryPaper> paperList = examPaperService.getPaperAllList(queryPaper,
                    this.getPage());
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
        	modelAndView.addObject("professionalList",professionalList);
        	modelAndView.addObject("paperList", paperList);
            modelAndView.addObject("page", this.getPage());
            List<PaperType> paperTypeList = paperTypeService.queryPaperTypeListByState(0);//0为显示状态的试卷分类
            modelAndView.addObject("paperTypeList",paperTypeList);
            Map<String,String> paperTypeName=new HashMap<String, String>();
            for(PaperType paperType:paperTypeList){
                paperTypeName.put("title"+paperType.getId().toString(),paperType.getTitle());
            }
            modelAndView.addObject("paperTypeName",paperTypeName);
        } catch (Exception e) {
            logger.error("listAllPaper", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 跳转到修改试卷页面
     *
     * @return
     */
    @RequestMapping("/paper/toUpdateExamPaper")
    public ModelAndView toUpdateExamPaper(@ModelAttribute("paper") ExamPaper paper,
                                          HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView(toUpdateExamPaper);
        try {
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
        	view.addObject("professionalList", professionalList);
        	paper = examPaperService.queryExamPaperById(paper);
        	view.addObject("paper", paper);
            ExamProfessionalDTO examProfessionalDto = professionalService.getProfessionalBySubjectId(paper.getSubjectId());
            view.addObject("examProfessionalDto", examProfessionalDto);
            List<PaperType> paperTypeList = paperTypeService.queryPaperTypeListByState(0);
            view.addObject("paperTypeList", paperTypeList);
        } catch (Exception e) {
            logger.error("toUpdateExamPaper", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return view;
    }

    /**
     * 修改试卷
     *
     * @return
     */
    @RequestMapping("/paper/updateExamPaper")
    public String updateExamPaper(@ModelAttribute("paper") ExamPaper paper,
                                  HttpServletRequest request) {
        try {
            examPaperService.updateExamPaperById(paper);
        } catch (Exception e) {
            logger.error(updateExamPaper, e);
            return setExceptionRequest(request, e);
        }
        return updateExamPaper;
    }

    // 删除试卷
    @RequestMapping("/paper/delPaperListBatch")
    @ResponseBody
    public Map<String, Object> delPaperListBatch(HttpServletRequest request,
                                                 HttpServletResponse response) {
        String paperIds = request.getParameter("paperIds");
        try {
            examPaperService.delPaperListBatch(paperIds);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.delQuestionListBatch", e);
        }
        return json;
    }

    /**
     * 查看试卷
     *
     * @return
     */
    @RequestMapping("/paper/showExamPaperById")
    public ModelAndView showExamPaperById(@ModelAttribute("paper") ExamPaper paper,
                                          HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(showExamPaperById);
        try {
           
            paper = examPaperService.queryExamPaperById(paper);
            ExamProfessionalDTO examProfessionalDto = professionalService.getProfessionalBySubjectId(paper.getSubjectId());
            modelAndView.addObject("paper", paper);
            modelAndView.addObject("examProfessionalDto", examProfessionalDto);

            List<PaperType> paperTypeList = paperTypeService.queryPaperTypeListByState(0);//0为显示状态的试卷分类
            modelAndView.addObject("paperTypeList",paperTypeList);


        } catch (Exception e) {
            logger.error("showExamPaperById", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    // 试卷标题上移
    @RequestMapping("/ajax/paper/paperMiddleMoveUp")
    public String paperMiddleMoveUp(HttpServletRequest request,HttpServletResponse response) {
        int oneSort = RequestUtil.getInt(request, "oneSort");
        Long oneId = RequestUtil.getLong(request, "oneId");
        int twotSort = RequestUtil.getInt(request, "twotSort");
        Long twoId = RequestUtil.getLong(request, "twoId");
        String paperId=request.getParameter("paperId");
        try {
            paperMiddleService.updatePaperMiddleMoveUp(oneSort, oneId, twotSort, twoId);
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByPaperId(Long.parseLong(paperId),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.paperMiddleMoveUp", e);
            return this.setExceptionRequest(request,e);
	    }
        return examPaperAssembleAjax;
    }

    // 删除paperMiddle
    @RequestMapping("/ajax/paper/delPaperMiddle")
    public String delPaperMiddle(@ModelAttribute("paperMiddle") PaperMiddle paperMiddle,HttpServletRequest request, HttpServletResponse response) {
        try {
            paperMiddleService.delQstMiddleById(paperMiddle);
            
            // 查询当前试卷的题
            List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByPaperId(paperMiddle.getPaperId(),null);
            request.setAttribute("paperMiddleList", paperMiddleList);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.delPaperMiddle", e);
            return this.setExceptionRequest(request,e);
	    }
        return examPaperAssembleAjax;
    }
    
    // 修改paperMiddle
    @RequestMapping("/ajax/paper/doUpdatePaperMiddle/{id}")
    public String doUpdatePaperMiddle(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response) {
        try {
            // 查询大题目
            PaperMiddle paperMiddle = paperMiddleService.getPaperMiddleById(id);
            request.setAttribute("paperMiddle", paperMiddle);
        } catch (Exception e) {
            logger.error("AdminQuestionAction.doUpdatePaperMiddle", e);
            return this.setExceptionRequest(request,e);
	    }
        return paperMiddleAjax;
    }
    /**
     * 试卷分类
     *
     * @return
     */
    @RequestMapping("/paper/queryPaperType")
    public ModelAndView queryPaperType(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView(queryPaperType);
        try {
            List<PaperType> paperTypeList = paperTypeService.queryPaperTypeList();
            modelAndView.addObject("paperTypeList", paperTypeList);
        } catch (Exception e) {
            logger.error("queryPaperType", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 到试卷分类更新页面
     *
     * @return
     */
    @RequestMapping("/paper/toUpdatePaperType")
    public ModelAndView toUpdatePaperType(
            HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView(toUpdatePaperType);
        try {
            PaperType paperType = paperTypeService.queryPaperTypeById(id);
            modelAndView.addObject("paperType", paperType);
        } catch (Exception e) {
            logger.error("toUpdatePaperType", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 到试卷分类更新页面
     *
     * @return
     */
    @RequestMapping("/paper/updatePaperType")
    public ModelAndView updatePaperType(
            HttpServletRequest request, HttpServletResponse responses, @ModelAttribute("paperType") PaperType paperType) {
        try {

            paperTypeService.updatePaperTypeById(paperType);
        } catch (Exception e) {
            logger.error("toUpdatePaperType", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return new ModelAndView("redirect:/admin/paper/queryPaperType");
    }

    @InitBinder("queryPaperRecord")
    public void initBinderPage(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryPaperRecord.");
    }

    /**
     * 考试记录查询
     *
     * @return
     */
    @RequestMapping("/paper/toPaperRecord")
    public ModelAndView toPaperRecord(
            HttpServletRequest request, HttpServletResponse responses, @ModelAttribute("page") PageEntity page
            , @ModelAttribute("queryPaperRecord") QueryPaperRecord queryPaperRecord) {
        ModelAndView modelAndView = new ModelAndView(toPaperRecord);
        try {
            //查询试卷记录
            List<QueryPaperRecord> queryPaperRecordList = examPaperRecordService.queryExamPaperRecordAllList(queryPaperRecord, page);
            //查询出的数据放入modelView中
            modelAndView.addObject("queryPaperRecord", queryPaperRecord);
            modelAndView.addObject("queryPaperRecordList", queryPaperRecordList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("toPaperRecord", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 考试记录详情
     *
     * @return
     */
    @RequestMapping("/paper/toPaperRecordInfo")
    public ModelAndView toPaperRecordInfo(
            HttpServletRequest request, HttpServletResponse responses, @RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView(toPaperRecordInfo);
        try {
            String type = request.getParameter("type");
            PaperRecord paperRecord = new PaperRecord();
            paperRecord.setId(id);
            // 获得考试记录
            paperRecord = paperMiddleService.queryPaperRecordById(paperRecord);


            ExamPaper paper = new ExamPaper();
            paper.setId(paperRecord.getEpId());
            paper = examPaperService.queryExamPaperById(paper);
            paperRecord.setCusId(paperRecord.getCusId());
            request.setAttribute("type", type);
            request.setAttribute("paper", paper);
            request.setAttribute("paperRecord", paperRecord);

            if (paperRecord.getType() == 2) {
                List<PaperMiddle> paperMiddleList = paperMiddleService.getPaperMiddleListByExamPaperRecord(paperRecord);
                request.setAttribute("paperMiddleList", paperMiddleList);
                modelAndView.setViewName(toPaperRecordInfo);
            }else{
                List<QueryQuestion> queryQuestionList = paperMiddleService.getRandomQuestionByExamPaperRecord(paperRecord);
                request.setAttribute("paperMiddleList", queryQuestionList);
                modelAndView.setViewName(getViewPath("/admin/paper/to_exampaper_random_recordinfo"));
            }

        } catch (Exception e) {
            logger.error("toPaperRecord", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }


    /**
     * 主观题试卷列表
     *
     * @return
     */
    @RequestMapping("/paper/toSubjectivePaperRecord")
    public ModelAndView toSubjectivePaperRecord(
            HttpServletRequest request, HttpServletResponse responses, @ModelAttribute("page") PageEntity page,
            @ModelAttribute("queryPaperRecord") QueryPaperRecord queryPaperRecord) {
        ModelAndView modelAndView = new ModelAndView(toPaperRecordSubjective);
        try {

        	 //查询试卷记录
            // List<QueryPaperRecord> queryPaperRecordList = examPaperRecordService.queryExamPaperRecordAllList(queryPaperRecord, page);

            List<QueryPaperRecord> queryPaperRecordList = examPaperRecordService
                    .queryExamPaperRecordAllListBySubjective(queryPaperRecord, page);
            //查询出的数据放入modelView中
            modelAndView.addObject("queryPaperRecord", queryPaperRecord);
            modelAndView.addObject("queryPaperRecordList", queryPaperRecordList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("toSubjectivePaperRecord", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 主观题添加分数
     *
     * @return
     */
    @RequestMapping("/paper/updateExampaperRecordScore")
    @ResponseBody
    public Map<String, Object> updateExampaperRecordScore(
            HttpServletRequest request, HttpServletResponse responses) {
        try {
            Integer score = Integer.valueOf(request.getParameter("score"));
            Integer qstScore = Integer.valueOf(request.getParameter("qstScore"));
            if (qstScore < score) {
                this.setJson(false, "", null);
                return json;
            }
            Long qstrcdId = Long.valueOf(request.getParameter("qstrcdId"));
            if (ObjectUtils.isNull(score) || ObjectUtils.isNull(qstrcdId)) {
                this.setJson(false, "false", null);
                return json;
            }
            examPaperRecordService.updateExampaperRecordAddScore(qstrcdId, score);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("updateExampaperRecordScore", e);
            this.setJson(false, "false", null);
        }
        return json;
    }

}
