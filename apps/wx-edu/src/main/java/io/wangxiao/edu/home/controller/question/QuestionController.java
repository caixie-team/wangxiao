package io.wangxiao.edu.home.controller.question;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.web.SuggestConstans;
import io.wangxiao.edu.home.controller.suggest.ProblemController;
import io.wangxiao.edu.home.entity.suggest.*;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.suggest.SugSuggestLikeService;
import io.wangxiao.edu.home.service.suggest.SugSuggestReplyService;
import io.wangxiao.edu.home.service.suggest.SugSuggestService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class QuestionController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);
    private static final String toQuestionList = getViewPath("/question/question-list");// 问答列表
    private static final String toAddSugSuggest = getViewPath("/question/question-creat");// 到问答提问页面
    private static final String ajaxSuglist = getViewPath("/question/ajaxQuestion_list");// 建议ajax分页
    private static final String toSugSuggestInfo = getViewPath("/question/question-info");// 建议详情
    private static final String secondReplyList = getViewPath("/question/ajax_second_reply_list");// 二级回复
    private static final String myQues = getViewPath("/ucenter/u-my-question");//我的问答
    @Autowired
    private SugSuggestService sugSuggestService;// 问答服务
    @Autowired
    private SugSuggestReplyService sugSuggestReplyService;
    @Autowired
    private SugSuggestLikeService sugSuggestLikeService;
    @Autowired
    private UserExpandService userExpandService;

    /**
     * 建议
     *
     * @param binder
     */
    @InitBinder("sugSuggest")
    public void initBinderSugSuggest(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("sugSuggest.");
    }

    /**
     * 问答列表
     *
     * @return
     */
    @RequestMapping("/front/question")
    public String toWuShiSugSuggestList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            Long loginUserId = getLoginUserId(request);// 当前登陆用户id
            List<SugSuggest> querySuggestHtoComments = sugSuggestService.querySuggestHtoComments(null);//热门评论排行
            Long publishedNum = sugSuggestService.querySuggestQuestionNum(loginUserId);//提问量
            Long replycountNum = sugSuggestService.querySuggestAnswerNum(loginUserId);//用户回答总量
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(loginUserId);// 查询用户资料
            request.setAttribute("userExpandDto", userExpandDto);
            request.setAttribute("querySuggestHtoComments", querySuggestHtoComments);//热门评论
            request.setAttribute("publishedNum", publishedNum);//提问量
            request.setAttribute("replycountNum", replycountNum);

            String flag = request.getParameter("flag");
            request.setAttribute("flag", flag);
            page.setPageSize(6);
            if ("unsolved".equalsIgnoreCase(flag)) {// 查询未解决问题
                List<SugSuggest> SugSuggestList = sugSuggestService.querySugSuggestListByStatus(0, page);
                request.setAttribute("SugSuggestList", SugSuggestList);
            } else if ("solved".equalsIgnoreCase(flag)) {// 查询已解决问题
                List<SugSuggest> SugSuggestList = sugSuggestService.querySugSuggestListByStatus(1, page);
                request.setAttribute("SugSuggestList", SugSuggestList);
            } else {// 查询全部问题
                List<SugSuggest> SugSuggestList = sugSuggestService.querySuggestAll(new QuerySugSuggest(), page);
                request.setAttribute("SugSuggestList", SugSuggestList);
            }
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("QuestionController.toWuShiSugSuggestList", e);
        }
        return toQuestionList;
    }

    /**
     * 到添加问答页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/questionAdd")
    public ModelAndView toAddSugSuggest(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toAddSugSuggest);
        try {
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNull(userId)) {
                return new ModelAndView("redirect:/login");
            }
            // 最新问题排行
            List<SugSuggest> sugSuggestWisdomList = sugSuggestService.querySuggestOrderById(6);
            modelAndView.addObject("sugSuggestWisdomList", sugSuggestWisdomList);// 把智慧排行值放入modelAndView中
            return modelAndView;// 转到添加问答页面
        } catch (Exception e) {
            logger.error("QuestionController.toAddSugSuggest", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }

    /**
     * 添加问题
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping("/front/sug/addSugSuggest")
    @ResponseBody
    public Map<String, Object> AddSugSuggest(HttpServletRequest request, @ModelAttribute SugSuggest sugSuggest) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 未登录不可操作
            if (getLoginUserId(request) == 0) {
                return map;
            }
            if (sugSuggest != null) {
                if (StringUtils.isEmpty(sugSuggest.getContent())) {
                    map.put("message", "false");
                    return map;
                }
                Date date = new Date();
                sugSuggest.setAddtime(date);// 添加时间
                sugSuggest.setReplyTime(date);
                sugSuggest.setCusId(getLoginUserId(request));// 发表建议的用户id
                String falg = sugSuggestService.addSugSuggest(sugSuggest);// 添加建议
                map.put("message", falg);//
                // 日志打印
                // Map<String, Object> logMap =
                // LogController.getlogMap(request);
                // logMap.put(LogController.ACTION, "AddSugSuggest");
                // logMap.put("sugSuggestId", "" + sugSuggest.getId());
                // logMap.put("cusId", "" + getLoginUserId(request));
                // LogController.printLog(logMap);
                // 日志打印结束
            } else {
                map.put("message", "contentTooLong");// 输入文字太长
            }
        } catch (Exception e) {
            logger.error("QuestionController.AddSugSuggest", e);
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 通过条件查询建议list
     *
     * @param request
     * @return
     */
    @RequestMapping("/front/ajax/questlist")
    public ModelAndView suglist(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(ajaxSuglist);
        try {
            // set 前台传来的page的值
            String falg = request.getParameter("falg");
            if ("all".equals(falg)) {// 查询全部
                QuerySugSuggest querySugSuggest = new QuerySugSuggest();
                page.setPageSize(6);
                List<SugSuggest> SugSuggestList = sugSuggestService.querySuggestAll(querySugSuggest, page);
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            if ("unsolved".equals(falg)) {// 查询未解决问题
                page.setPageSize(6);
                List<SugSuggest> SugSuggestList = sugSuggestService.querySugSuggestListByStatus(0, page);
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            if ("solved".equals(falg)) {// 已解决
                page.setPageSize(6);
                List<SugSuggest> SugSuggestList = sugSuggestService.querySugSuggestListByStatus(1, page);
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            if ("mysug".equals(falg)) {// 我的问题
                // 未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                List<SugSuggest> SugSuggestList = sugSuggestService.querySuggestByCusIdAndStatus(
                        getLoginUserId(request), SuggestConstans.SUGSUGGEST_STATUS_CANREPLY, page);// 通过cusId查询我寻求的建议
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            // 我回答的问题
            if ("mysughd".equals(falg)) {
                // 未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                List<SugSuggest> sugSuggestList = sugSuggestService.querySuggestByReplyCusId(getLoginUserId(request),
                        page);// 查询我回答的建议
                modelAndView.addObject("SugSuggestList", sugSuggestList);// 把值得出的分页的值
            }
            // 我推荐的问题
            if ("myrecommend".equals(falg)) {
                // 未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                List<SugSuggest> sugSuggestList = sugSuggestService.querySuggestByReplyCusIdAndIsBest(getLoginUserId(request), page);// 我被推荐的建议
                modelAndView.addObject("SugSuggestList", sugSuggestList);// 把值得出的分页的值
            }
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("WeiBoAction.suglist", e);
        }
        return modelAndView;
    }

    /**
     * 建议详细页面
     *
     * @return
     */
    @RequestMapping("/front/question/info/{sugSuggestId}")
    public String toSugSuggestInfo(@PathVariable Long sugSuggestId, HttpServletRequest request,
                                   @ModelAttribute("page") PageEntity page, RedirectAttributes redirectAttributes, Model model) {
        try {
            // 用户id
            Long userId = getLoginUserId(request);
            model.addAttribute("cusId", userId);

            // 问答信息
            SugSuggest sugSuggest = sugSuggestService.getSugSuggestById(sugSuggestId);
            model.addAttribute("sugSuggest", sugSuggest);
            if (ObjectUtils.isNull(sugSuggest)) {
                redirectAttributes.addAttribute("msg", "对不起该问题不存在或者已删除");
                return "redirect:/front/success";
            }
            // 是否可以显示采纳按钮
            if (userId == sugSuggest.getCusId()) {
                model.addAttribute("adopt", true);
            } else {
                model.addAttribute("adopt", false);
            }
            // 热门问答
            List<SugSuggest> hotCommentList = sugSuggestService.querySuggestHtoComments(null);
            model.addAttribute("hotCommentList", hotCommentList);

            // 问答回复(未采纳回复)
            page.setPageSize(5);
            List<SugSuggestReply> replyList = sugSuggestReplyService.querySugSuggestReplyListBySuggestId(
                    sugSuggestId, SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_NO, page);
            model.addAttribute("replyList", replyList);

            // 问答回复(采纳回复)
            SugSuggestReply bestReply = new SugSuggestReply();
            bestReply.setSuggestId(sugSuggest.getId());
            bestReply.setIsbest(SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES);
            List<SugSuggestReply> bestReplyList = sugSuggestReplyService.getSugSuggestReplyList(bestReply);
            if (ObjectUtils.isNotNull(bestReplyList)) {
                SugSuggestReply sugSuggestReply = bestReplyList.get(0);
                UserExpandDto user = userExpandService.getUserExpandByUid(sugSuggestReply.getCusId());
                sugSuggestReply.setUserExpandDto(user);
                model.addAttribute("bestReply", sugSuggestReply);
            }

            // 用户资料
            UserExpandDto user = userExpandService.getUserExpandByUids(userId);
            model.addAttribute("user", user);

            // 提问数
            Long questionNum = sugSuggestService.querySuggestQuestionNum(userId);
            model.addAttribute("questionNum", questionNum);

            // 回答数
            Long answerNum = sugSuggestService.querySuggestAnswerNum(userId);
            model.addAttribute("answerNum", answerNum);

            // 获取点赞数量
            SugSuggestLike sugSuggestLike = new SugSuggestLike();
            sugSuggestLike.setSuggestId(sugSuggestId);
            int likeCount = sugSuggestLikeService.getSugSuggestLikeCount(sugSuggestLike);
            model.addAttribute("likeCount", likeCount);

            // 当前用户是否点赞
            sugSuggestLike.setUserId(userId);
            int isLike = sugSuggestLikeService.getSugSuggestLikeCount(sugSuggestLike);
            model.addAttribute("isLike", isLike);

            // 分页
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("QuestionController.toSugSuggestInfo", e);
            return setExceptionRequest(request, e);// 报错返回404页面
        }

        return toSugSuggestInfo;
    }

    /**
     * 添加建议回复
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping("/front/sug/addSugSuggestReply")
    @ResponseBody
    public Map<String, Object> addSugSuggestReply(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Long sugSugestId = Long.parseLong(request.getParameter("sugSugestId"));// 获得建议id
            String content = request.getParameter("content");// 获得回复的内容
            // 如果建议内容为空则添加失败
            if (StringUtils.isEmpty(content)) {
                map.put("message", "false");
            }
            if (content != null && content.length() < 5000) {// 当回复数不能大于5000
                int type = Integer.parseInt(request.getParameter("type"));// 获得建议类型
                SugSuggestReply sugSuggestReply = new SugSuggestReply();
                sugSuggestReply.setAddtime(new Date());// set 添加时间
                sugSuggestReply.setContent(content);// set 回复内容
                sugSuggestReply.setCusId(getLoginUserId(request));// 回复的id
                sugSuggestReply.setSuggestId(sugSugestId);// 建议id
                sugSuggestReply.setType(type);// 建议的类型
                String falg = sugSuggestReplyService.addSugSuggestReply(sugSuggestReply);// 添加回复
                int count = 0;
                count++;
                sugSuggestService.updateSugSuggestBySuggestIdCount(sugSugestId, count);
                map.put("message", falg);// 返回成功
                // 日志打印
                // Map<String, Object> logMap =
                // LogController.getlogMap(request);
                // logMap.put(LogController.ACTION, "addSugSuggestReply");
                // logMap.put("sugSuggestReplyId", "" +
                // sugSuggestReply.getId());
                // logMap.put("cusId", "" + getLoginUserId(request));
                // LogController.printLog(logMap);
                // 日志打印结束
            } else {
                map.put("message", "contentTooLong");// 返回添加内容过长
            }

        } catch (Exception e) {
            logger.error("QuestionController.addSugSuggestReply", e);
            map.put("message", "false");// 返回失败
        }
        return map;
    }

    /**
     * 我的问题
     *
     * @return
     */
    @RequestMapping("/uc/ques/my")
    public ModelAndView toMySeekSugSuggestList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(myQues);
        try {
            modelAndView.addObject("flag", request.getParameter("flag"));
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return modelAndView;
            }
            return modelAndView;
        } catch (Exception e) {
            logger.error("QuestionController.toMySeekSugSuggestList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }

    /**
     * 赞一下(取消赞)
     *
     * @return
     */
    @RequestMapping("/front/question/ajax/PraiseNum")
    @ResponseBody
    public Map<String, Object> updateSugSuggestLike(HttpServletRequest request, @RequestParam("flag") boolean flag, @ModelAttribute("suggestId") Long suggestId) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            SugSuggestLike sugSuggestLike = new SugSuggestLike();
            sugSuggestLike.setUserId(userId);
            sugSuggestLike.setSuggestId(suggestId);
            if (flag) {
                int sugSuggestLikeCount = sugSuggestLikeService.getSugSuggestLikeCount(sugSuggestLike);
                if (sugSuggestLikeCount <= 0) {
                    sugSuggestLikeService.createSugSuggestLike(sugSuggestLike);
                }
            } else {
                sugSuggestLikeService.deleteSugSuggestLike(sugSuggestLike);
            }
        } catch (Exception e) {
            logger.error("QuestionController.updateSugSuggestLike", e);
        }
        return json;
    }

    /**
     * 获取二级回复
     *
     * @param model
     * @param secondReply
     * @param page
     * @return
     */
    @RequestMapping("/ajax/secondReply")
    public String secondReply(Model model, @ModelAttribute("secondReply") SecondReply secondReply, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(5);
            List<SecondReply> replyList = sugSuggestReplyService.getSecondReplyList(secondReply, page);
            model.addAttribute("replyList", replyList);
        } catch (Exception e) {
            logger.error("QuestionController.secondReply", e);
        }
        return secondReplyList;
    }

    /**
     * 添加二级回复
     *
     * @param request
     * @param secondReply
     * @return
     */
    @RequestMapping("/ajax/addSecondReply")
    @ResponseBody
    public Map<String, Object> addSecondReply(HttpServletRequest request, @ModelAttribute("secondReply") SecondReply secondReply) {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNotNull(userId)) {
                secondReply.setUserId(userId);
                sugSuggestReplyService.createSecondReply(secondReply);
                json = this.getJsonMap(true, "success", null);
            } else {
                json = this.getJsonMap(false, "noLogin", null);
            }
        } catch (Exception e) {
            logger.error("QuestionController.secondReply", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }
}
