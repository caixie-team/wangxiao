package com.atdld.os.sns.controller.weibo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.sns.constants.PageConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.weibo.Comment;
import com.atdld.os.sns.entity.weibo.QueryComment;
import com.atdld.os.sns.entity.weibo.QueryWeiBo;
import com.atdld.os.sns.entity.weibo.WeiBo;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoCommentService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.core.action.weibo.WeiBoAction
 * @description 微博前台action
 * @Create Date : 2013-12-14 下午1:21:35
 */
@Controller
public class WeiBoController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(WeiBoController.class);

    // 路径
    private static final String toAllWeiBo = getViewPath("/weibo/to_All_weibo");// 全部微博
    private static final String queryAllWeiBoPage = getViewPath("/weibo/to_All_weibo_ajax");// ajax全部微博
    private static final String toOtherWeiBo = getViewPath("/phome/p_weibo");// 他人首页微博
    @Autowired
    private WeiBoService weiBoService;// 微博service
    @Autowired
    private WeiBoCommentService weiBoCommentService;// 微博评论service
    @Autowired
    private SnsUserService snsUserService;// 微博评论service
    @Autowired
    private FriendService friendService;

    /**
     * 微博
     *
     * @param binder
     */
    @InitBinder("weiBo")
    public void initBinderWeiBo(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("weiBo.");
    }

    /**
     * 用户评论
     *
     * @param binder
     */
    @InitBinder("comment")
    public void initBinderComment(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("comment.");
    }

    /**
     * 用户关注实体
     *
     * @param binder
     */
    @InitBinder("cusAttention")
    public void initBindercusAttention(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("cusAttention.");
    }

    /**
     * 添加微博
     *
     * @param weiBo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/weibo/addWeiBo")
    @ResponseBody
    public Map<String, Object> addWeiBo(@ModelAttribute WeiBo weiBo, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (weiBo != null) {// 如果传来的微博不等于null执行添加操作
                weiBo.setAddTime(new Date());
                weiBo.setCusId(getLoginUserId(request));// set用户id
                weiBo.setUpdateTime(new Date());
                if (StringUtils.isEmpty(weiBo.getContent())) {
                    map.put("message", SnsConstants.FALSE);
                    return map;
                }
                String falg = weiBoService.addWeiBo(weiBo);// 添加微博
                // 日志打印
                Map<String, Object> logMap = LogController.getlogMap(request);
                logMap.put(LogController.ACTION, "addWeiBo");
                logMap.put("weiboId", "" + weiBo.getId());
                LogController.printLog(logMap);
                // 日志打印结束
                map.put("message", falg);
            }
        } catch (Exception e) {
            logger.error("WeiBoAction.addWeiBo", e);
        }
        return map;
    }

    /**
     * 删除微博
     *
     * @param weiBo   传入微博Id
     * @param request
     * @return
     */
    @RequestMapping(value = "/weibo/delWeiBo")
    @ResponseBody
    public Map<String, Object> delWeiBo(@ModelAttribute WeiBo weiBo, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            if (weiBo != null) {// 如果传入的微博不为null则执行
                weiBo.setCusId(getLoginUserId(request));// 传入用户id
                if (weiBoService.queryWeiBoById(weiBo).getCusId().intValue() == getLoginUserId(request).intValue()) {// 验证该微博是否是我发表的
                    String falg = weiBoService.delWeiBo(weiBo);// 删除微博
                    map.put("message", falg);// 是否删除成功标记
                    // 如果等于success则成功等于false则失败
                    // 日志打印
                    Map<String, Object> logMap = LogController.getlogMap(request);
                    logMap.put(LogController.ACTION, "delWeiBo");
                    logMap.put("weiboId", "" + weiBo.getId());
                    logMap.put("cusId", "" + weiBo.getCusId());
                    LogController.printLog(logMap);
                    // 日志打印结束
                } else {
                    map.put("message", "false");//
                }
            }
        } catch (Exception e) {
            logger.error("WeiBoAction.delWeiBo", e);
        }
        return map;
    }


    /**
     * 他的微博
     *
     * @param pageEntity 分页参数
     * @param request
     * @return
     */
    @RequestMapping("/p/{userid}/weibo")
    public ModelAndView toOtherWeiBo(@ModelAttribute("page") PageEntity page, HttpServletRequest request, @PathVariable Long userid) {
        ModelAndView modelAndView = new ModelAndView(toOtherWeiBo);
        try {
            this.setPage(page);// set分页参数
            int status = 0;
            if (userid.longValue() != getLoginUserId(request).longValue()) {// 如果观看别人微博时
                // 只能看公开的微博
                status = 1;
            }
            List<QueryWeiBo> queryWeiBoList = weiBoService.queryMyWeiBo(userid, status, this.getPage());// 通过cusId查询我发布的微博

            List<QueryWeiBo> queryWeiBoListForWeiBoNumByWeek = weiBoService.queryCustomerForWeiBoNumByWeek();// 查询一个星期发表微博最多的用户数
            // 查询是否是好友
            Friend friend = new Friend();
            friend.setCusId(getLoginUserId(request));
            friend.setCusFriendId(userid);
            friend = friendService.queryFriendByCusIdAndCusFriendId(friend);
            // 获得用户的信息
            SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(userid);
            modelAndView.addObject("queryWeiBoListForWeiBoNumByWeek", queryWeiBoListForWeiBoNumByWeek);// 查询出一周内发表微博最多的用户的list放入modelAndView中
            modelAndView.addObject("queryWeiBoList", queryWeiBoList);// 放入查询出的微博list
            modelAndView.addObject("page", this.getPage());// 放入page数据
            modelAndView.addObject("userid", userid);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("showname", userExpand.getShowname());
            modelAndView.addObject("userExpandDto", userExpand);
            modelAndView.addObject("friend", friend);
        } catch (Exception e) {
            logger.error("WeiBoAction.toMyWeiBo", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }


    /**
     * 通过微博id查询微博的评论
     *
     * @param comment 传入微博id
     * @return
     */
    @RequestMapping(value = "/weibo/queryWeiBoComment")
    @ResponseBody
    public Map<String, Object> queryWeiBoComment(@ModelAttribute Comment comment, HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            this.setPage(page);
            this.getPage().setPageSize(PageConstans.WeiboCom_Page);
            List<QueryComment> queryCommentList = weiBoCommentService.queryCommentByWbId(comment, this.getPage());// 查询微博评论
            map.put("queryCommentList", queryCommentList);// 查询出的微博评论list放入map中
            map.put("cusId", getLoginUserId(request));// 查询出的微博评论list放入map中
            map.put("page", this.getPage());
        } catch (Exception e) {
            logger.error("WeiBoAction.queryWeiBoComment", e);
        }
        return map;
    }

    /**
     * 添加微博评论
     *
     * @param comment 传入微博id 和评论内容
     * @param request
     * @return
     */
    @RequestMapping(value = "/weibo/addWeiBoComment")
    @ResponseBody
    public Map<String, Object> addWeiBoComment(@ModelAttribute Comment comment, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            //判断微博评论内容不为空
            if (StringUtils.isEmpty(comment.getContent())) {
                map.put("message", SnsConstants.FALSE);
            }
            comment.setCusId(getLoginUserId(request));
            String falg = weiBoCommentService.addComment(comment);// 添加微博评论
            SnsUserExpandDto userExpand = snsUserService.getUserExpandByCusId(getLoginUserId(request));// 调用接口通过cusId
            // 获得用户
            map.put("userExpandDto", userExpand);// 用户信息
            map.put("message", falg);
            map.put("comment", comment);// 评论信息放入map中
            map.put("customer", userExpand);// 用户信息放入map中
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "addWeiBoComment");
            logMap.put("cusId", "" + comment.getCusId());
            logMap.put("weiboId", "" + comment.getWeiboId());
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("WeiBoAction.addWeiBoComment", e);
        }
        return map;
    }

    /**
     * 根据评论id删除评论
     *
     * @param comment 传入评论id
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/weibo/delCommentById")
    @ResponseBody
    public Map<String, Object> delCommentById(@ModelAttribute Comment comment, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            String falg = weiBoCommentService.delCommentById(comment);// 根据评论id删除评论
            map.put("message", falg);// 把信息放入map中返回到页面用json接收
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "delCommentById");
            logMap.put("cusId", "" + comment.getCusId());
            logMap.put("commentid", "" + comment.getId());
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("WeiBoAction.delCommentById", e);
        }
        return map;
    }


    /**
     * 全站微博
     *
     * @param request
     * @param pageEntity 分页参数
     * @return
     */
    @RequestMapping(value = "/weibo")
    public ModelAndView toAllWeiBo(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toAllWeiBo);
        try {
            this.setPage(page);// set传来的分页参数
            WeiBo weiBo = new WeiBo();
            weiBo.setCusId(getLoginUserId(request));// set用户id
            // 通过用户id查询是否点赞过该微博收藏过该微博
            List<QueryWeiBo> queryWeiBoListForWeiBoNumByWeek = weiBoService.queryCustomerForWeiBoNumByWeek();// 查询一个星期发表微博最多的用户数
            // 评论最多
            List<QueryWeiBo> queryWeiBoList = weiBoService.queryCommentMostWeiBo(weiBo, this.getPage());
            modelAndView.addObject("queryWeiBoListForWeiBoNumByWeek", queryWeiBoListForWeiBoNumByWeek);// 查询出一周内发表微博最多的用户的list放入modelAndView中
            modelAndView.addObject("queryWeiBoList", queryWeiBoList);
            modelAndView.addObject("cusId", getLoginUserId(request));// 把当前登陆的用户id放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 把分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("WeiBoAction.toAllWeiBo", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }


    /**
     * 微博ajax
     *
     * @param request
     * @param pageEntity 分页参数
     * @return
     */
    @RequestMapping(value = "/weibo/ajax/queryWeiBoPageajax")
    public ModelAndView queryAllWeiBo(HttpServletRequest request, @ModelAttribute("page") PageEntity page
            , @ModelAttribute("type") int type) {
        ModelAndView modelAndView = new ModelAndView(queryAllWeiBoPage);
        try {
            this.setPage(page);// set传来的分页参数
            WeiBo weiBo = new WeiBo();
            weiBo.setCusId(getLoginUserId(request));// set用户id
            // 通过用户id查询是否点赞过该微博收藏过该微博
            List<QueryWeiBo> queryWeiBoList = new ArrayList<QueryWeiBo>();
            //全站微博
            if (type == 1) {
                queryWeiBoList = weiBoService.queryAllWeiBo(weiBo, this.getPage());// 查询全站微博
                // 根据置顶字段和添加时间排序
            }
            //热门微博
            if (type == 2) {
                queryWeiBoList = weiBoService.queryHotWeiBo(weiBo, this.getPage());// 热门微博查询
            }
            //我的微博
            if (type == 3) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                queryWeiBoList = weiBoService.queryMyWeiBo(getLoginUserId(request), 0, this.getPage());// 通过cusId查询我发布的微博
            }
            //好友微博
            if (type == 4) {
                //未登录不可操作
                if (getLoginUserId(request).longValue() == 0) {
                    return modelAndView;
                }
                queryWeiBoList = weiBoService.queryAttentionWeiBo(getLoginUserId(request), this.getPage());// 查询我关注的人的微博
            }
            //评论最多
            if (type == 5) {
                queryWeiBoList = weiBoService.queryCommentMostWeiBo(weiBo, this.getPage());// 热评论最多
            }
            modelAndView.addObject("queryWeiBoList", queryWeiBoList);// 查询出的微博list放入modelAndView中
            modelAndView.addObject("cusId", getLoginUserId(request));// 把当前登陆的用户id放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 把分页参数放入modelAndView中
            modelAndView.addObject("type", type);
        } catch (Exception e) {
            logger.error("WeiBoAction.queryAllWeiBo", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }


    /**
     * 取消微博关注
     *
     * @param cusAttention
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/weibo/quxiaoAttentionCustomer")
    @ResponseBody
    public Map<String, Object> quxiaoAttentionCustomer(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long friId = Long.valueOf(request.getParameter("friId"));
            Friend fri = new Friend();
            fri.setCusId(getLoginUserId(request));
            fri.setCusFriendId(friId);

            String falg = friendService.delFriend(fri);// 取消微博关注
            map.put("message", falg);
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "quxiaoAttentionCustomer");
            logMap.put("cusId", "" + getLoginUserId(request));
            logMap.put("CusAttentionId", "" + friId);
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("WeiBoAction.quxiaoAttentionCustomer", e);
        }
        return map;
    }

    /**
     * 转发微博
     */
    @RequestMapping(value = "/weibo/forward")
    @ResponseBody
    public Map<String, Object> forward(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("content") String content) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            String falg = weiBoService.addForward(id, getLoginUserId(request), content);
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("WeiBoAction.forward", e);
        }
        return map;
    }
}
