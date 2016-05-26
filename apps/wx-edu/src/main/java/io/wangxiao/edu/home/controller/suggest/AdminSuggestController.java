package io.wangxiao.edu.home.controller.suggest;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.suggest.QuerySugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggest;
import io.wangxiao.edu.home.entity.suggest.SugSuggestReply;
import io.wangxiao.edu.home.service.suggest.SugSuggestReplyService;
import io.wangxiao.edu.home.service.suggest.SugSuggestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminSuggestController extends EduBaseController {

    private static final Logger logger = Logger.getLogger(AdminSuggestController.class);

    // 路径
    private static final String toSugSuggestList = getViewPath("/admin/suggest/to_suggest_list");// 到建议列表页面
    private static final String toSugSuggestReplyList = getViewPath("/admin/suggest/to_sugsuggest_reply_list");// 到回复列表页面
    private static final String toRecommendSugSuggestList = getViewPath("/admin/suggest/to_recommend_suggest_list");// 到回复列表页面
    private static final String toUpdateSugSuggest = getViewPath("/admin/suggest/to_update_sugsuggest");// 到建议更新页面

    @Autowired
    private SugSuggestService sugSuggestService;
    @Autowired
    private SugSuggestReplyService sugSuggestReplyService;

    /**
     * 建议
     *
     * @param binder
     */
    @InitBinder("querySugSuggest")
    public void initBinderQuerySugSuggest(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("querySugSuggest.");
    }


    /**
     * 后台建议列表
     *
     * @return
     */
    @RequestMapping(value = "/sug/toSugSuggestList")
    public ModelAndView toSugSuggestList(HttpServletRequest request, @ModelAttribute QuerySugSuggest querySugSuggest, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toSugSuggestList);
        try {
            // set 前台传来的page的值
            List<SugSuggest> sugSuggestList = sugSuggestService.getSugSuggestList(querySugSuggest, page);// 后台建议列表
            modelAndView.addObject("sugSuggestList", sugSuggestList);
            modelAndView.addObject("page", page);
            return modelAndView;// 转到添加建议页面
        } catch (Exception e) {
            logger.error("AdminSuggestAction.toSugSuggestList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }

    /**
     * 回复管理
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/sug/toSugSuggestReplyList")
    public ModelAndView toSugSuggestReplyList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toSugSuggestReplyList);
        try {
            Long sugSugestId = Long.parseLong(request.getParameter("sugSugestId"));// 建议id
            // set 前台传来的page的值
            SugSuggest sugSuggest = sugSuggestService.getSugSuggestById(sugSugestId);
            List<SugSuggestReply> sugSuggestReplyList = sugSuggestReplyService.querySugSuggestReplyListBySuggestId(sugSugestId, -1, page);// 传入建议id
            // 是否为最佳答案的状态
            // 和分页参数
            modelAndView.addObject("sugSuggestReplyList", sugSuggestReplyList);// 回复list
            modelAndView.addObject("sugSuggest", sugSuggest);// 建议
            modelAndView.addObject("page", page);// 分页参数

            return modelAndView;// 转到添加建议页面
        } catch (Exception e) {
            logger.error("AdminSuggestAction.toSugSuggestReplyList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }

    /*
     * 标记
     */
    @RequestMapping(value = "/sug/delCommons")
    @ResponseBody
    public Map<String, Object> updateCommon(HttpServletRequest request, @RequestParam("common") String common,
                                            @RequestParam("sugSugestId") Long sugSugestId) {
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            Long id = Long.valueOf(request.getParameter("sugSugestId"));

            SugSuggest s = new SugSuggest();
            s.setId(new Long(id));
            s.setCommon(new Long(common));
            sugSuggestService.getSugSuggesByCommon(s);
        } catch (Exception e) {
            logger.error("AdminSuggestAction.updateCommon", e);
        }


        return json;
    }

    /**
     * 批量标记
     *
     * @param request
     * @param common
     * @param sugSugestIds
     * @return
     */
    @RequestMapping("/sug/delCommonss")
    @ResponseBody
    public Map<String, Object> delSugSuggestByIds(HttpServletRequest request, @RequestParam("common") String common,
                                                  @RequestParam("ids") String sugSugestIds) {

        Map<String, Object> json = null;
        String[] ids = sugSugestIds.replaceAll(" ", "").split(",");

        SugSuggest s = new SugSuggest();
        s.setCommon(new Long(common));
        for (int i = 0; i < ids.length; i++) {
            s.setId(new Long(ids[i]));

            sugSuggestService.getSugSuggesByCommon(s);
        }

        json = this.getJsonMap(true, "success", null);
        return json;
    }


    /**
     * 删除建议
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/sug/delSugSuggest")
    @ResponseBody
    public Map<String, Object> delSugSuggestById(HttpServletRequest request) {
        Map<String, Object> json;
        try {
            String sugSugestId = request.getParameter("sugSugestId");// 获得前台传来
            // 的建议id
            String falg = sugSuggestService.deleteSugSuggestById(new Long(sugSugestId));// 删除建议
            String falgs = sugSuggestReplyService.deleteSugSuggestReplyById(new Long(sugSugestId));// 删除回复
            if (falg.equals(falgs) && falg.equals("success")) {//判断删除建议和删除回复是否成功
                json = getJsonMap(true, "success", null);
            } else {
                json = getJsonMap(false, "false", null);
            }
        } catch (Exception e) {
            logger.error("SuggestAction.delSugSuggestById", e);
            json = getJsonMap(false, "error", null);
        }
        return json;
    }


    /**
     * 批量删除建议
     *
     * @param request
     * @param sugSugestIds
     * @return
     */
    @RequestMapping(value = "/sug/delSugSuggests")
    @ResponseBody
    public Map<String, Object> delSugSuggestByIds(HttpServletRequest request, @RequestParam("ids") String sugSugestIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            String[] ids = sugSugestIds.replaceAll(" ", "").split(",");
            String falg = "success";
            String falgs = "success";
            for (int i = 0; i < ids.length; i++) {

                falg = sugSuggestService.deleteSugSuggestById(new Long(ids[i]));// 删除建议
                falgs = sugSuggestReplyService.deleteSugSuggestReplyById(new Long(ids[i]));// 删除回复


            }

            map.put("message", falg);

        } catch (Exception e) {
            logger.error("SuggestAction.delSugSuggestById", e);
            map.put("message", "false");
        }
        return map;
    }


    /**
     * 置顶建议 或取消建议
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/sug/upateSugSuggestTop")
    @ResponseBody
    public Map<String, Object> upateSugSuggestTop(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long sugSugestId = Long.parseLong(request.getParameter("sugSugestId"));// 获得前台传来
            // 的建议id
            int top = Integer.parseInt(request.getParameter("top"));// 前台传入1为置顶0为取消
            String falg = sugSuggestService.updateSugSuggestBySuggestIdForTop(sugSugestId, top);// 删除建议
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("SuggestAction.upateSugSuggestTop", e);
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 推荐回复 或取消推荐回复
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/sug/updateSugSuggestReplyForIsBest")
    @ResponseBody
    public Map<String, Object> updateSugSuggestReplyForIsBest(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long sugSuggestReplyId = Long.valueOf(request.getParameter("sugSuggestReplyId"));// 建议id
            Long sugSuggestId = Long.valueOf(request.getParameter("sugSuggestId"));// 获得前台传来
            int isBest = Integer.parseInt(request.getParameter("isBest"));// 获得前台传来
            Long falgNum = sugSuggestReplyService.updateSugSuggestReplyBySugSuggestReplyIdForIsBest(sugSuggestReplyId, sugSuggestId, isBest);// 推荐回复
            // 或取消推荐回复
            if (falgNum > 0) {
                map.put("message", "success");// 成功
            } else {
                map.put("message", "false");// 失败
            }

        } catch (Exception e) {
            logger.error("SuggestAction.upateSugSuggestTop", e);
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 删除回复
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/sug/delSugSuggestReplyBySugSuggestReplyId")
    @ResponseBody
    public Map<String, Object> delSugSuggestReplyBySugSuggestReplyId(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long suggestReplyId = Long.parseLong(request.getParameter("suggestReplyId"));// 获得前台传来
            // 的建议id
            String falg = sugSuggestReplyService.deleteSugSuggestReplyById(suggestReplyId);// 删除回复
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("SuggestAction.upateSugSuggestTop", e);
            map.put("message", "false");
        }
        return map;
    }

    /**
     * 推荐的建议列表
     *
     * @return
     */
    @RequestMapping(value = "/sug/toRecommendSugSuggestList")
    public ModelAndView toRecommendSugSuggestList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute QuerySugSuggest querySugSuggest) {
        ModelAndView modelAndView = new ModelAndView(toRecommendSugSuggestList);
        try {
            // set 前台传来的page的值
            List<SugSuggest> sugSuggestList = sugSuggestService.querySuggestRecommend(querySugSuggest, page);// 推荐的建议列表
            modelAndView.addObject("sugSuggestList", sugSuggestList);// 把值得出的分页的值
            modelAndView.addObject("page", page);// 分页参数
            return modelAndView;
        } catch (Exception e) {
            logger.error("SuggestAction.toRecommendSugSuggestList", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }

    /**
     * 到建议更新页面
     *
     * @return
     */
    @RequestMapping(value = "/sug/toUpdateSugSuggest")
    public ModelAndView toUpdateSugSuggest(HttpServletRequest request, @RequestParam("sugSuggestId") Long sugSuggestId) {
        ModelAndView modelAndView = new ModelAndView(toUpdateSugSuggest);
        try {
            SugSuggest sugSuggest = sugSuggestService.getSugSuggestById(sugSuggestId);
            modelAndView.addObject("sugSuggest", sugSuggest);// 把建议放入modelAndView
            return modelAndView;
        } catch (Exception e) {
            logger.error("SuggestAction.toUpdateSugSuggest", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
    }

    /**
     * 到建议更新页面
     *
     * @return
     */
    @RequestMapping(value = "/sug/updateSugSuggest")
    @ResponseBody
    public Map<String, Object> updateSugSuggest(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            SugSuggest sugSuggest = new SugSuggest();
            sugSuggest.setContent(content);// set 建议内容
            sugSuggest.setTitle(title);// set 建议标题
            sugSuggest.setId(id);// set 建议id
            sugSuggestService.updateSugSuggestBySugSuggestIdForContentAndTitle(sugSuggest);// 更新建议内容和标题
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("SuggestAction.updateSugSuggest", e);
            map.put("message", "false");// 报错返回404页面
        }
        return map;
    }

}
