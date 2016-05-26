package com.atdld.os.exam.controller.question;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.exampaper.PaperType;
import com.atdld.os.exam.entity.favorite.Favorite;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.QuestErrorCheck;
import com.atdld.os.exam.entity.subject.QuerySubject;
import com.atdld.os.exam.service.exampaper.PaperTypeService;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.question.QuestErrorCheckService;
import com.atdld.os.exam.service.question.QuestionService;
import com.atdld.os.exam.service.subject.SubjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @ClassName QuestionAction
 * @package com.atdld.os.exam.controller.question
 * @description 试题管理
 * @Create Date: 2013-9-7 下午3:43:44
 */
@Controller
@RequestMapping("/quest")
public class QuestionController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;
    @Autowired
    private PaperTypeService paperTypeService;
    @Autowired
    private QuestErrorCheckService questErrorCheckService;
    @Autowired
    private ProfessionalService professionalService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryQuestion")
    public void initBinderqueryQuestion(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryQuestion.");
    }

    @InitBinder("favorite")
    public void initBinderquerfavorite(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("favorite.");
    }

    @InitBinder("queryErrorQuestion")
    public void initBinderquerqueryErrorQuestion(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryErrorQuestion.");
    }

    @InitBinder("queryNoteQuestion")
    public void initBinderquerqueryNoteQuestion(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryNoteQuestion.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("questErrorCheck")
    public void initBinderquestErrorCheck(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("questErrorCheck.");
    }

    // 获得错误记录前两条
    public MemCache memCache = MemCache.getInstance();

    // 返回路径
    private String parse = getViewPath("/question/test-myExe");
    private String toErrorQuestionList = getViewPath("/question/test-myerror");
    private String favoriteQuestion = getViewPath("/question/test-favorite");
    private String toNoteQuestionList = getViewPath("/question/test-mynote");
    private String toQuestionitemList = getViewPath("/paper/item-list");
    private static final String examIndex=getViewPath("/question/exam_index");//考试专题页面
    private static final String examItem=getViewPath("/question/exam_item");//考试专题页面选择项目页面

    // 查看解析queryQuestion.id
    @RequestMapping("/parse/{id}")
    public String parse(@PathVariable Long id,RedirectAttributes redirectAttributes,
                        HttpServletRequest request, ModelMap modelMap) {
        try {
            QueryQuestion queryQuestion = new QueryQuestion();
            queryQuestion.setId(id);
            queryQuestion.setCusId(getLoginUserId(request));
            queryQuestion = questionService.getParse(queryQuestion);
            if(ObjectUtils.isNull(queryQuestion)){
            	 redirectAttributes.addAttribute("msg", "对不起,该试题解析不存在！");
                 return "redirect:/front/success";

            }
            modelMap.addAttribute("queryQuestion", queryQuestion);
        } catch (Exception e) {
            logger.error("ExamPaperAction.parse", e);
            return setExceptionRequest(request, e);
        }
        return parse;
    }

    // 收藏试题
    @RequestMapping("/toFavorite/{qstId}")
    @ResponseBody
    public Map<String, Object> toFavorite(@PathVariable Long qstId,
                                          HttpServletRequest request) {
        try {
            Long cusId = getLoginUserId(request);
            Favorite favorite = new Favorite();
            favorite.setCusId(cusId);
            favorite.setAddDate(new Date());
            favorite.setSubjectId(this.getLoginSubjectId(request));
            favorite.setQstId(qstId);
            questionService.addFavorite(favorite);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("QuestionAction.toFavorite", e);
            this.setJson(false, "", null);
        }
        return json;
    }

    // 取消收藏
    @RequestMapping("/notFavorite/{qstId}")
    @ResponseBody
    public Map<String, Object> notFavorite(@PathVariable Long qstId, HttpServletRequest request) {
        try {
            Long cusId = getLoginUserId(request);
            Favorite favorite = new Favorite();
            favorite.setCusId(cusId);
            favorite.setQstId(qstId);
            questionService.delFavorite(favorite);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("QuestionAction.notFavorite", e);
            this.setJson(false, "error", null);
        }
        return json;
    }


    // 查询用户收藏的
    @RequestMapping("/favoriteQuestion")
    public ModelAndView favoriteQuestion(@ModelAttribute("page") PageEntity page,
                                         HttpServletRequest request,
                                         @ModelAttribute("queryQuestion") QueryQuestion queryQuestion) {
        ModelAndView modelAndView = new ModelAndView(favoriteQuestion);
        try {
            queryQuestion.setCusId(getLoginUserId(request));
            queryQuestion.setSubjectId(this.getLoginSubjectId(request));
            List<QueryQuestion> queryQuestionList = questionService.getFavoriteQuestion(
                    queryQuestion, page);
            modelAndView.addObject("queryQuestionList", queryQuestionList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("QuestionAction.favoriteQuestion", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    // 错题列表
    @RequestMapping("/toErrorQuestionList")
    public String toErrorQuestionList(@ModelAttribute("page") PageEntity page, HttpServletRequest request,
                                      ModelMap modelMap) {
        try {
            QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
            queryErrorQuestion.setSubjectId(this.getLoginSubjectId(request));
            queryErrorQuestion.setCusId(getLoginUserId(request));
            List<QueryQuestion> queryQuestionList = questionService.getErrorQuestionList(
                    queryErrorQuestion, page);
            modelMap.addAttribute("page", page);
            modelMap.addAttribute("queryQuestionList", queryQuestionList);
        } catch (Exception e) {
            logger.error("QuestionAction.toErrorQuestionList", e);
            return setExceptionRequest(request, e);
        }
        return toErrorQuestionList;
    }

    // 到写过笔记的题的分页
    @RequestMapping("/toNoteQuestionList")
    public ModelAndView toNoteQuestionList(@ModelAttribute("page") PageEntity page,
                                           HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toNoteQuestionList);
        try {
            QueryNoteQuestion queryNoteQuestion = new QueryNoteQuestion();
            queryNoteQuestion.setSubjectId(this.getLoginSubjectId(request));
            queryNoteQuestion.setCusId(getLoginUserId(request));
            List<QueryQuestion> queryQuestionList = questionService.getNoteQuestionList(
                    queryNoteQuestion, page);
            modelAndView.addObject("page", page);
            modelAndView.addObject("queryQuestionList", queryQuestionList);
        } catch (Exception e) {
            logger.error("QuestionAction.toNoteQuestionList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    // 移出错题
    @RequestMapping("/delerrorQuestion/{id}")
    @ResponseBody
    public Map<String, Object> delerrorQuestion(@PathVariable Long id) {
        try {
            QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
            queryErrorQuestion.setId(id);
            questionService.delQueryErrorQuestion(queryErrorQuestion);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("QuestionAction.delerrorQuestion", e);
            this.setJson(false, "", null);
        }
        return json;
    }

    /**
     * 项目的详细页面 查询错题记录和我的笔记即可
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/toQuestionitemList")
    public String toQuestionitemList(HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("queryErrorQuestion") QueryErrorQuestion queryErrorQuestion) {
        try {
            // response.getWriter().write("111111");
            if (queryErrorQuestion == null) {
                queryErrorQuestion = new QueryErrorQuestion();
            }
            List<PaperType> paperTypeList = paperTypeService.queryPaperTypeListByState(0);
            request.setAttribute("paperTypeList", paperTypeList);
            Long cus_Id = getLoginUserId(request);
            Long subjectId = this.getLoginSubjectId(request);
            if (subjectId.longValue() == 0) {
                return toQuestionitemList;
            }
            
            
            // 添加缓存
            String ERRORQUESTIONKEY = MemConstans.MEM_CUS_ERRORQUESTION
                    + getLoginUserId(request);
            List<QueryQuestion> queryQuestionitemList = (List<QueryQuestion>) memCache
                    .get(ERRORQUESTIONKEY);
            if (queryQuestionitemList == null || queryQuestionitemList.size() == 0) {
                // 错误记录
                queryErrorQuestion.setCusId(cus_Id);
                queryErrorQuestion.setSubjectId(subjectId);
                queryQuestionitemList = questionService
                        .getErrorQuestionitemList(queryErrorQuestion);
                if (queryQuestionitemList != null && queryQuestionitemList.size() > 0) {
                    memCache.set(ERRORQUESTIONKEY, queryQuestionitemList,  MemConstans.MEM_COMMON_TIME);
                }
            }
            // 添加缓存
            String NOTEKEY = MemConstans.MEM_CUS_NOTE + getLoginUserId(request);
            List<QueryQuestion> queryNoteQuestionitemList = (List<QueryQuestion>) memCache
                    .get(NOTEKEY);
            if (queryNoteQuestionitemList == null
                    || queryNoteQuestionitemList.size() == 0) {
                // 我的笔记
                QueryNoteQuestion queryNoteQuestion = new QueryNoteQuestion();
                queryNoteQuestion.setCusId(cus_Id);
                queryNoteQuestion.setSubjectId(subjectId);
                queryNoteQuestionitemList = questionService
                        .getNoteQuestionitemList(queryNoteQuestion);
                if (queryNoteQuestionitemList != null
                        && queryNoteQuestionitemList.size() > 0) {
                    memCache.set(NOTEKEY, queryNoteQuestionitemList, MemConstans.MEM_CUS_NOTE_TIME);
                }
            }


            request.setAttribute("queryQuestionitemList", queryQuestionitemList);
            request.setAttribute("queryNoteQuestionitemList", queryNoteQuestionitemList);
        } catch (Exception e) {
            logger.error("QuestionAction.toQuestionitemList", e);
            return setExceptionRequest(request, e);
        }
        return toQuestionitemList;
    }

    /**
     * 网校进入考试时专题页面
     */
    @RequestMapping("/exam")
    public ModelAndView examIndex(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(examIndex);
        return modelAndView;
    }
    /**
     * 网校进入考试专题页面选择考试项目页面
     */
    @RequestMapping("/examitem")
    public ModelAndView examItem(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(examItem);
        try {

        String s="#DE5656,#A17ED0,#67C174,#4BB3E9,#D4C060";
        String[] str = s.split(",");
        //查询专业
        List<ExamProfessional> professionalList = professionalService.queryExamProfessionalList();
        //放颜色
        if(ObjectUtils.isNotNull(professionalList)){
            for(int i=0;i<professionalList.size();i++ ){
                int index = i%5;
                professionalList.get(i).setColour(str[index]);
            }
        }
        modelAndView.addObject("professionalList", professionalList);
        } catch (Exception e) {
            logger.error("QuestionAction.examItem", e);
        }
        return modelAndView;
    }
    @Autowired
    private SubjectService subjectService;
    /**
     * 网校进入考试专题页面选择考试项目页面
     */
    @RequestMapping("/querySubject")
    @ResponseBody
    public Map<String, Object> querySubject(HttpServletRequest request,@RequestParam Long id) {
        try {
            QuerySubject querySubject = new QuerySubject();
            querySubject.setProfessionalId(id);
            List<ExamSubject> examSubjectList = subjectService.getSubjectListByProfessionalId(querySubject);
            //查询该专业下的试题数
            if(ObjectUtils.isNotNull(examSubjectList)){
                for(ExamSubject e:examSubjectList){
                    QueryQuestion queryQuestion = new QueryQuestion();
                    queryQuestion.setSubjectId(e.getSubjectId());
                    e.setQstNum(questionService.getAllQuestionListCount(queryQuestion));
                }
            }
            this.setJson(true,"success",examSubjectList);
        } catch (Exception e) {
            logger.error("QuestionAction.querySubject", e);
        }
        return json;
    }

    // 添加笔记
    @RequestMapping("/insertNote")
    @ResponseBody
    public Map<String, Object> insertNote(HttpServletRequest request,
                                          @ModelAttribute("queryNoteQuestion") QueryNoteQuestion queryNoteQuestion) {
        try {
            Long cusId = getLoginUserId(request);
            queryNoteQuestion.setCusId(cusId);
            queryNoteQuestion.setSubjectId(this.getLoginSubjectId(request));
            queryNoteQuestion.setAddTime(new Date());
            questionService.insertNote(queryNoteQuestion);
            // 新建笔记清除该用户笔记缓存
            String NOTEKEY = MemConstans.MEM_CUS_NOTE + getLoginUserId(request);
            memCache.remove(NOTEKEY);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("QuestionAction.insertNote", e);
            return json;
        }
        return json;
    }

    // 更新筆記
    @RequestMapping("/updateNote")
    @ResponseBody
    public Map<String, Object> updateNote(
            @ModelAttribute("queryNoteQuestion") QueryNoteQuestion queryNoteQuestion,
            HttpServletRequest request) {
        try {
            Long cusId = getLoginUserId(request);
            queryNoteQuestion.setCusId(cusId);
            queryNoteQuestion.setAddTime(new Date());
            queryNoteQuestion.setSubjectId(this.getLoginSubjectId(request));
            questionService.updatetNote(queryNoteQuestion);
            // 更新笔记清除该用户笔记缓存
            String NOTEKEY = MemConstans.MEM_CUS_NOTE + getLoginUserId(request);
            memCache.remove(NOTEKEY);
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("QuestionAction.updateNote", e);
        }
        return json;
    }

    /**
     * 添加纠错内容
     *
     * @param request
     * @param questErrorCheck
     * @return
     */
    @RequestMapping("/addQuestErrorCheck")
    @ResponseBody
    public Map<String, Object> addQuestErrorCheck(HttpServletRequest request, @ModelAttribute("questErrorCheck") QuestErrorCheck questErrorCheck) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (questErrorCheck != null) {
                String flag = questErrorCheckService.addQuestErrorCheck(questErrorCheck);
                map.put("message", flag);
            }
        } catch (Exception e) {
            logger.error("ExamPaperAction.addQuestErrorCheck", e);
            map.put("message", "false");
        }
        return map;
    }
}
