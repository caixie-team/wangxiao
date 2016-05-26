package com.atdld.os.edu.controller.suggest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.web.SnsConstants;
import com.atdld.os.edu.constants.web.SuggestConstans;
import com.atdld.os.edu.entity.suggest.QuerySugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggest;
import com.atdld.os.edu.entity.suggest.SugSuggestReply;
import com.atdld.os.edu.service.suggest.SugSuggestReplyService;
import com.atdld.os.edu.service.suggest.SugSuggestService;
/**
 * 
 * @ClassName  com.atdld.os.edu.controller.suggest.QuestionController
 * @description
 * @author :
 * @Create Date : 2014年10月21日 上午9:12:17
 */
@RequestMapping("/uc")
@Controller
public class ProblemController extends EduBaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);
	
	private static final String toQuestionList = getViewPath("/ucenter/question-list");// 问题列表
	private static final String ajaxSuglist = getViewPath("/ucenter/ajaxQuestion_list");// 建议ajax分页
	private static final String toSugSuggestInfo = getViewPath("/ucenter/u-question-infor");// 建议详细
	private static final String querySugSuggestReplyList = getViewPath("/ucenter/ajax_question_replylist");// 问题回复ajax
	private static final String toAddSugSuggest = getViewPath("/ucenter/u-toQuestion");// 到添加问答页面
    private static final String myQues = getViewPath("/ucenter/myQues");//我的问答
	@Autowired
    private SugSuggestService sugSuggestService;
	@Autowired
    private SugSuggestReplyService sugSuggestReplyService;
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
    @RequestMapping(value = "/question")
    public String toWuShiSugSuggestList(HttpServletRequest request) {
    	return toQuestionList;// 转到务实建议列表页面
    }
    /**
     * 我的问题
     *
     * @return
     */
    @RequestMapping(value = "/ques/my")
    public ModelAndView toMySeekSugSuggestList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(myQues);
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return modelAndView;
            }
            return modelAndView;
        } catch (Exception e) {
            logger.error("SuggestAction.toMySeekSugSuggestList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }
    /**
     * 通过条件查询建议list
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ajax/questlist")
    public ModelAndView suglist(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(ajaxSuglist);
        try {
            this.setPage(page);// set 前台传来的page的值
            String falg = request.getParameter("falg");
            if ("unsolved".equals(falg)) {
                //查询全部的问题
                List<SugSuggest> SugSuggestList = sugSuggestService.querySugSuggestListByStatus(0, this.getPage());
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            if ("solved".equals(falg)) {
                QuerySugSuggest querySugSuggest = new QuerySugSuggest();
                List<SugSuggest> SugSuggestList = sugSuggestService.querySuggestRecommend(querySugSuggest, this.getPage());
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            //我的问题
            if ("mysug".equals(falg)) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                List<SugSuggest> SugSuggestList = sugSuggestService.querySuggestByCusIdAndStatus(getLoginUserId(request), SuggestConstans.SUGSUGGEST_STATUS_CANREPLY, this.getPage());// 通过cusId查询我寻求的建议
                modelAndView.addObject("SugSuggestList", SugSuggestList);// 把值得出的分页的值
            }
            //我回答的问题
            if ("mysughd".equals(falg)) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                List<SugSuggest> sugSuggestList = sugSuggestService.querySuggestByReplyCusId(getLoginUserId(request), this.getPage());// 查询我回答的建议
                modelAndView.addObject("SugSuggestList", sugSuggestList);// 把值得出的分页的值
            }
            //我推荐的问题
            if ("myrecommend".equals(falg)) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                List<SugSuggest> sugSuggestList = sugSuggestService.querySuggestByReplyCusIdAndIsBest(getLoginUserId(request), this.getPage());// 我被推荐的建议
                modelAndView.addObject("SugSuggestList", sugSuggestList);// 把值得出的分页的值
            }
            modelAndView.addObject("page", this.getPage());
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
    @RequestMapping(value = "/question/info/{sugSuggestId}")
    public String toSugSuggestInfo(@PathVariable Long sugSuggestId, HttpServletRequest request, 
    		@ModelAttribute("page") PageEntity page,RedirectAttributes redirectAttributes,Model model) {
        try {
            this.setPage(page);// set 前台传来的page的值
            // 建议查看
            SugSuggest sugSuggest = sugSuggestService.getSugSuggestById(sugSuggestId);// 通过建议id
            if(ObjectUtils.isNull(sugSuggest)){
				redirectAttributes.addAttribute("msg","对不起该问题不存在或者已删除");
		        return "redirect:/front/success";
			}
            List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyService.querySugSuggestReplyListBySuggestId(sugSuggestId, SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES, this.getPage());// 该建议下的是最佳答案的回复
            List<SugSuggest> sugSuggestWisdomList = sugSuggestService.querySuggestOrderById(6);// 最新问题排行6条
            model.addAttribute("sugSuggestWisdomList", sugSuggestWisdomList);// 把智慧排行值放入modelAndView中
            model.addAttribute("sugSuggestReplyList", sugSuggestReplyList);
            model.addAttribute("sugSuggest", sugSuggest);// 把值得出的分页的值
            model.addAttribute("page", this.getPage());// 分页参数
            model.addAttribute("cusId", getLoginUserId(request));// 当前登陆用户id
            return toSugSuggestInfo;
        } catch (Exception e) {
            logger.error("SuggestAction.toSugSuggestInfo", e);
            return setExceptionRequest(request, e);// 报错返回404页面
        }
    }
    /**
     * 建议详细页面 的回复分页
     *
     * @return
     */
    @RequestMapping(value = "/question/ajax/replyList")
    public ModelAndView querySugSuggestReplyList(HttpServletRequest request, @ModelAttribute("page") PageEntity page
            , @ModelAttribute SugSuggest sugSuggest) {
        ModelAndView modelAndView = new ModelAndView(querySugSuggestReplyList);
        try {
            this.setPage(page);// set 前台传来的page的值
            this.getPage().setPageSize(6);
            Long sugSugestId = Long.parseLong(request.getParameter("sugSugestId"));
            List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyService.querySugSuggestReplyListBySuggestId(sugSugestId, SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_NO, this.getPage());// 该建议下的不是最佳答案的回复
            modelAndView.addObject("message", SnsConstants.SUCCESS);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("cusId", getLoginUserId(request));
            modelAndView.addObject("sugSuggestReplyList", sugSuggestReplyList);
            modelAndView.addObject("sugSuggest", sugSuggest);
        } catch (Exception e) {
            logger.error("SuggestAction.querySugSuggestReplyList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }
    /**
     * 到添加问答
     *
     * @return
     */
    @RequestMapping(value = "/ques/add")
    public ModelAndView toAddSugSuggest(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toAddSugSuggest);
        try {
            //最新问题排行
            List<SugSuggest> sugSuggestWisdomList = sugSuggestService.querySuggestOrderById(6);
            modelAndView.addObject("sugSuggestWisdomList", sugSuggestWisdomList);// 把智慧排行值放入modelAndView中
            return modelAndView;// 转到添加问答页面
        } catch (Exception e) {
            logger.error("SuggestAction.toAddSugSuggest", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }
    /**
     * 添加建议
     *
     * @param request
     * @param sugSuggest
     * @return
     */
    @RequestMapping(value = "/sug/addSugSuggest")
    @ResponseBody
    public Map<String, Object> AddSugSuggest(HttpServletRequest request, @ModelAttribute SugSuggest sugSuggest) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (sugSuggest != null) {
                if (StringUtils.isEmpty(sugSuggest.getContent())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                sugSuggest.setAddtime(new Date());// 添加时间
                sugSuggest.setCusId(getLoginUserId(request));// 发表建议的用户id
                String falg = sugSuggestService.addSugSuggest(sugSuggest);// 添加建议
                map.put("message", falg);//
                // 日志打印
//                Map<String, Object> logMap = LogController.getlogMap(request);
//                logMap.put(LogController.ACTION, "AddSugSuggest");
//                logMap.put("sugSuggestId", "" + sugSuggest.getId());
//                logMap.put("cusId", "" + getLoginUserId(request));
//                LogController.printLog(logMap);
                // 日志打印结束
            } else {
                map.put("message", "contentTooLong");// 输入文字太长
            }

        } catch (Exception e) {
            logger.error("SuggestAction.AddSugSuggest", e);
            map.put("message", "false");
        }
        return map;
    }
    /**
     * 添加建议回复
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/sug/addSugSuggestReply")
    @ResponseBody
    public Map<String, Object> addSugSuggestReply(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Long sugSugestId = Long.parseLong(request.getParameter("sugSugestId"));// 获得建议id
            String content = request.getParameter("content");// 获得回复的内容
            //如果建议内容为空则添加失败
            if (StringUtils.isEmpty(content)) {
                map.put("message", SnsConstants.FALSE);
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
                map.put("message", falg);// 返回成功
                // 日志打印
//                Map<String, Object> logMap = LogController.getlogMap(request);
//                logMap.put(LogController.ACTION, "addSugSuggestReply");
//                logMap.put("sugSuggestReplyId", "" + sugSuggestReply.getId());
//                logMap.put("cusId", "" + getLoginUserId(request));
//                LogController.printLog(logMap);
                // 日志打印结束
            } else {
                map.put("message", "contentTooLong");// 返回添加内容过长
            }

        } catch (Exception e) {
            logger.error("SuggestAction.addSugSuggestReply", e);
            map.put("message", "false");// 返回失败
        }
        return map;
    }

    /**
     * 推荐回复为最佳答案
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sug/recommendSugSuggestReply")
    @ResponseBody
    public Map<String, Object> recommendSugSuggestReply(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Long sugSuggestReplyId = Long.parseLong(request.getParameter("sugSuggestReplyId"));// 获得回答的id
            Long sugSuggestId = Long.parseLong(request.getParameter("sugSuggestId"));// 建议的id
            Long num = sugSuggestReplyService.updateSugSuggestReplyBySugSuggestReplyIdForIsBest(sugSuggestReplyId, sugSuggestId, SuggestConstans.SUGSUGGEST_RECOMMEND_ISBEST_YES, getLoginUserId(request));// 推荐回复为最佳答案
            if (num != 0) {// 不等于0 添加成功
                map.put("message", SnsConstants.SUCCESS);// 返回成功

            } else {// 等于0 添加失败
                map.put("message", "false");// 返回失败
            }
            // 日志打印
//            Map<String, Object> logMap = LogController.getlogMap(request);
//            logMap.put(LogController.ACTION, "recommendSugSuggestReply");
//            logMap.put("sugSuggestId", "" + sugSuggestId);
//            logMap.put("cusId", "" + getLoginUserId(request));
//            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("SuggestAction.recommendSugSuggestReply", e);
            map.put("message", "false");// 返回失败
        }
        return map;
    }


    /**
     * 根据回复id 删除回复
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sug/delSugSuggestReplyById")
    @ResponseBody
    public Map<String, Object> delSugSuggestReplyById(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Long sugSuggestReplyId = Long.parseLong(request.getParameter("sugSuggestReplyId"));// 建议的id
            String falg = sugSuggestReplyService.deleteSugSuggestReplyById(sugSuggestReplyId, getLoginUserId(request));// 根据回复id
            // 删除回复
            map.put("message", falg);// 返回标识字符
            // 日志打印
//            Map<String, Object> logMap = LogController.getlogMap(request);
//            logMap.put(LogController.ACTION, "delSugSuggestReplyById");
//            logMap.put("sugSuggestReplyId", "" + sugSuggestReplyId);
//            logMap.put("cusId", "" + getLoginUserId(request));
//            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("SuggestAction.delSugSuggestReplyById", e);
            map.put("message", "false");// 返回失败
        }
        return map;
    }
}
