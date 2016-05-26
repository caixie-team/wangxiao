package com.atdld.os.sns.controller.discuss;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.sns.constants.ArticleConstants;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.sns.entity.discuss.DisArticle;
import com.atdld.os.sns.entity.discuss.DisArticleReply;
import com.atdld.os.sns.entity.discuss.DisGroupClassify;
import com.atdld.os.sns.service.discuss.DisArticleService;
import com.atdld.os.sns.service.discuss.DisGroupService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.discuss.AdminDisGroupAction
 * @description 小组管理后台
 * @Create Date : 2013-12-20 上午9:03:48
 */
@Controller
@RequestMapping("/admin")
public class AdminDisArticleController extends SnsBaseController {
    /**
     * log对象
     */
    private Logger logger = Logger.getLogger(AdminDisArticleController.class);
    private static final String disArticleListPage=getViewPath("/admin/discuss/disArticle_list"); 
    private static final String toUpdatePage=getViewPath("/admin/discuss/update_DisArticle"); 
    private static final String replyList=getViewPath("/admin/discuss/reply_disArticle"); 
    
    
    @Autowired
    private DisGroupService disGroupService;
    @Autowired
    private DisArticleService disArticleService;
    
    @InitBinder("disArticle")
    public void initBinderDisArticle(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disArticle.");
    }
    // 发表文章回复 绑定变量名字和属性，把参数封装到类
    @InitBinder("disArticleReply")
    public void InitBinderDisArticleReply(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("disArticleReply.");
    }
    
    /**
     * 按条件查询小组话题列表
     * 
     * */
    @RequestMapping("/disArticle/getDisArticleList")
    public ModelAndView queryDisArticleList(@ModelAttribute("disArticle") DisArticle disArticle,
    		@ModelAttribute("page") PageEntity page,
    		HttpServletRequest request){
    	ModelAndView model=new ModelAndView(disArticleListPage);
    	try{
    		 // 页面传来的数据放到page中
            this.setPage(page);
            // 获得分类的list
            List<DisGroupClassify> disGroupClassify = disGroupService.queryAdminDisGroupClassify();
            
            List<DisArticle> disArticleList=disArticleService.queryDisArticleByCondition(disArticle, this.getPage());
            // 把返回的数据放到mdoelAndView中
            model.addObject("disGroupClassify", disGroupClassify);
            model.addObject("disArticleList", disArticleList);
            model.addObject("page", this.getPage());
            model.addObject("disArticle", disArticle);
    	}catch(Exception e){
    		 logger.error("AdminDisArticleController.queryDisArticleList", e);// 记录log
    		 return new ModelAndView(setExceptionRequest(request, e));// 返回到404界面
    	}
    	return model;
    }
    
    /**
     * 置顶小组文章
     *
     * @param request
     * @param articleId 小组文章articleId
     * @return
     */
    @RequestMapping("/disArticle/top")
    @ResponseBody
    public Map<String, Object> updateDisArticleByTop(HttpServletRequest request, @RequestParam("articleId") Long articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得返回更新结果
            String flag = disArticleService.updateDisArticleByTop(articleId);
            // 把获得结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("DisArticleAction.updateDisArticleByTop", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }

    /**
     * 取消置顶小组文章
     *
     * @param request
     * @param articleId 小组文章articleId
     * @return
     */
    @RequestMapping("/disArticle/cancel")
    @ResponseBody
    public Map<String, Object> updateCancelDisArticleByTop(HttpServletRequest request, @RequestParam("articleId") Long articleId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获得返回更新结果
            String flag = disArticleService.updateCancelDisArticleByTop(articleId);
            // 把获得结果放到map中
            map.put("message", flag);
        } catch (Exception e) {
            logger.error("DisArticleAction.updateCancelDisArticleByTop", e);// 记录log
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }
    
    /**
     * 更新文章的状态
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/disArticle/updatestatus")
    @ResponseBody
    public Map<String, Object> updateDisArticleStatus(HttpServletRequest request, @ModelAttribute("disArticle") DisArticle disArticle, @RequestParam("type") Integer type) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (ObjectUtils.isNotNull(disArticle)) {
                if (type == 1) {// 类型为1 禁言
                    disArticle.setStatus(ArticleConstants.ACTIVITY_STATUS_NO);// 禁止发言
                } else {
                    disArticle.setStatus(ArticleConstants.ACTIVITY_STATUS_YES);// 允许发言
                }
                String flag = disArticleService.updateDisArticleStatus(disArticle);
                map.put("message", flag);
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.updateDisArticleStatus", e);
            map.put("message", SnsConstants.FALSE);
        }
        return map;
    }
    /**
     * 查询单个文章详情，修改时调用
     *
     * @param request
     * @param articleId 文章id
     * @param groupId   小组id
     * @return
     */
    @RequestMapping("/disArticle/artail/{articleId}/{groupId}")
    public ModelAndView toUpdateDisArticle(HttpServletRequest request, @PathVariable Long articleId,@PathVariable Long groupId) {
        ModelAndView modelAndView = new ModelAndView(toUpdatePage);
        try {
            // 实例文章
            DisArticle disArticle = new DisArticle();
            disArticle.setId(articleId);// 文章id
            disArticle.setGroupId(groupId);
            // 获得文章详情
            DisArticle disArticleDetail = disArticleService.queryMyArticleDetail(disArticle);
            // 返回的结果放到modelAndView中
            modelAndView.addObject("disArticleDetail", disArticleDetail);
        } catch (Exception e) {
            logger.error("DisArticleAction.queryMyArticleDetail", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }

    /**
     * 修改我的小组文章
     *
     * @param request
     * @param disArticle
     * @return
     */
    @RequestMapping("/disArticle/updateMyArticle")
    public String updateMyArticle(HttpServletRequest request, @ModelAttribute("disArticle") DisArticle disArticle) {
        try {
            if (ObjectUtils.isNotNull(disArticle)) {
                // 更新我的小组文章，返回结果
                 disArticleService.updateMyArticle(disArticle);
            }
        } catch (Exception e) {
        	logger.error("DisArticleAction.queryMyArticleDetail", e);// 记录log
        }
        return "redirect:/admin/disArticle/getDisArticleList";
    }

    /**
     * 获取回复列表
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/disArticle/replyList/{disArticleId}/{groupId}")
    public ModelAndView queryblogReplyList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long disArticleId,@PathVariable Long groupId) {
        ModelAndView modelAndView = new ModelAndView(replyList);
        try {
            // 获得回复列表
            this.setPage(page);
            this.getPage().setPageSize(1);
            DisArticle disArticle = new DisArticle();
            disArticle.setId(disArticleId);
            disArticle.setGroupId(groupId);
            // 查看回复列表
            List<DisArticleReply> disArticleReplyList = disArticleService.queryDisArticleReplyList(disArticle, this.getPage());
            modelAndView.addObject("disArticleReplyList", disArticleReplyList);
            modelAndView.addObject("page", this.getPage());
            modelAndView.addObject("disArticleId", disArticleId);
            modelAndView.addObject("groupId", groupId);
        } catch (Exception e) {
            logger.error("DisGroupAction.queryblogReplyList", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }
    
    /**
     * 删除回复
     *
     * @param request
     * @param disArticleReply 文章id 回复id
     * @return
     */
    @RequestMapping("/disArticle/delartrep")
    @ResponseBody
    public Map<String, Object> deleteDisArticleReply(HttpServletRequest request, @ModelAttribute("disArticleReply") DisArticleReply disArticleReply) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (disArticleReply != null) {
                String flag = disArticleService.deleteDisArticleReply(disArticleReply);
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "deleteDisArticleReply");
                logMap.put("replyId", "" + disArticleReply.getId());
                LogController.printLog(logMap);
                // 日志打印结束
                map.put("message", flag);
            } else {
                map.put("message", "isNull");
            }
        } catch (Exception e) {
            logger.error("DisArticleAction.deleteDisArticleReply", e);// 记录log
            map.put("message", SnsConstants.FALSE);// 报错返回错误信息
        }
        return map;
    }
}
