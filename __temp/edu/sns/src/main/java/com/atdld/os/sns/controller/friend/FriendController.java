package com.atdld.os.sns.controller.friend;

import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.constants.PageConstans;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.customer.Visitor;
import com.atdld.os.sns.entity.friend.BlackList;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.entity.letter.MsgReceive;
import com.atdld.os.sns.service.customer.VisitorService;
import com.atdld.os.sns.service.friend.BlackListService;
import com.atdld.os.sns.service.friend.FriendService;
import com.atdld.os.sns.service.user.SnsUserService;
import com.atdld.os.sns.service.weibo.WeiBoService;

/**
 * @author :
 * @ClassName com.atdld.os.core.action.letter.LetterAction
 * @description 站内信的action
 * @Create Date : 2013-12-13 下午8:04:11
 */
@Controller
public class FriendController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(FriendController.class);

    // 返回路径
    private static final String queryBlackList = getViewPath("/friend/to_blackList");// 黑名单列表
    private static final String toMyattentionCustomer = getViewPath("/friend/to_my_attention_customer");// 我关注的微博
    private static final String toMyVisitorCustomer = getViewPath("/friend/to_my_visitor_customer");// 我关注的微博
    private static final String toMyFans = getViewPath("/friend/to_my_fans_customer");// 我的粉丝列表
    private static final String toOtherFriend = getViewPath("/phome/p_friend_customer");// 返回他人好友的页面
    private static final String ajaxfri = getViewPath("/friend/ajax_fri");// ajax分页
    private static final String toPersonVisitorCustomer = getViewPath("/friend/to_p_attention_customer");// ajax分页

    @Autowired
    private BlackListService blackListService;// 黑名单service
    @Autowired
    private FriendService friendService;// 好友service
    @Autowired
    private SnsUserService snsUserService;
    @Autowired
    private WeiBoService weiBoService;// 微博 service
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private WebHessianService webHessianService;
    /**
     * 好友实体
     *
     * @param binder
     */
    @InitBinder("friend")
    public void initBinderFriend(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("friend.");
    }

    /**
     * 黑名单实体
     *
     * @param binder
     */
    @InitBinder("blackList")
    public void initBinderDelblackList(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("blackList.");
    }

    /**
     * 站内信实体
     *
     * @param binder
     */
    @InitBinder("msgReceive")
    public void initBinderLetter(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("msgReceive.");
    }

    /**
     * 添加黑名单
     *
     * @param request
     * @param blackList 传入cusBlackListId
     * @return
     */
    @RequestMapping(value = "/black/addBlack")
    @ResponseBody
    public Map<String, Object> addBlack(HttpServletRequest request, @ModelAttribute BlackList blackList, @ModelAttribute MsgReceive msgReceive) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            blackList.setCusId(getLoginUserId(request));// set用户id
            blackList.setAddTime(new Date());
            String falg = blackListService.addBlackList(blackList);// 添加黑名单
            map.put("message", falg);
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "addBlack");
            logMap.put("blackListid", "" + blackList.getId());
            logMap.put("CusBlackListId", "" + blackList.getCusBlackListId());
            logMap.put("cusId", "" + SnsBaseController.getLoginUserId(request));
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("FriendAction.addBlack", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 移除黑名单
     *
     * @param request
     * @param blackList 传入cusBlackListId
     */
    @RequestMapping(value = "/black/delBlackList")
    @ResponseBody
    public Map<String, Object> delBlackList(HttpServletRequest request, @ModelAttribute BlackList blackList) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            blackList.setCusId(getLoginUserId(request));// set用户id
            String falg = blackListService.delBlackList(blackList);// 移除黑名单
            map.put("message", falg);
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "delBlackList");
            logMap.put("CusBlackListId", "" + blackList.getCusBlackListId());
            logMap.put("cusId", "" + SnsBaseController.getLoginUserId(request));
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("FriendAction.delBlackList", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 接受添加好友的请求 添加好友
     *
     * @param friend     传入cusFriendId
     * @param msgReceive 传入msgReceive的id
     * @param request
     * @return
     */
    @RequestMapping(value = "/friend/addFriend")
    @ResponseBody
    public Map<String, Object> addFriend(@ModelAttribute Friend friend, @ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            friend.setAddTime(new Date());
            friend.setCusId(getLoginUserId(request));// set用户id
            logger.info(String.format("addFriend uid:%s,fuid:%s", getLoginUserId(request), friend.getCusFriendId()));
            String falg = friendService.addFriend(friend);// 添加好友

            map.put("message", falg);
        } catch (Exception e) {
            logger.error("FriendAction.addFriend", e);
            setExceptionRequest(request, e);
        }
        return map;
    }


    /**
     * 删除好友
     *
     * @param friend  传入cusFriendId
     * @param request
     * @return
     */
    @RequestMapping(value = "/friend/delFriend")
    @ResponseBody
    public Map<String, Object> delFriend(@ModelAttribute Friend friend, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            friend.setCusId(getLoginUserId(request));// set用户id
            logger.info(String.format("delFriend uid:%s,fuid:%s", getLoginUserId(request), friend.getCusFriendId()));
            String falg = friendService.delFriend(friend);// 删除好友
            map.put("message", falg);
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "delFriend");
            logMap.put("CusFriendId", "" + friend.getCusFriendId());
            logMap.put("cusId", "" + SnsBaseController.getLoginUserId(request));
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("FriendAction.delFriend", e);
            setExceptionRequest(request, e);
        }
        return map;
    }


    /**
     * 我的好友列表, 我关注的
     *
     * @param request
     * @param pageEntity
     * @return
     */
    @RequestMapping("/p/{userid}/fri")
    public ModelAndView toOtherFriend(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable Long userid) {
        ModelAndView modelAndView = new ModelAndView(toOtherFriend);
        try {
            this.setPage(page);// set传来的分页参数
            this.getPage().setPageSize(PageConstans.MyFriend_Page);// set每页显示条数
            
            Friend fri = new Friend();
            fri.setCusId(getLoginUserId(request));
            List<SnsUserExpandDto> queryCustomerList = weiBoService.queryMyAttentionCustomer(fri, this.getPage());// 我关注的用户的列表
            
            // 获得用户的信息
            SnsUserExpandDto  userExpandDto = snsUserService.getUserExpandByCusId(userid);
            
            modelAndView.addObject("queryCustomerList", queryCustomerList);// 查询出的用户list放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 传入分页参数放入modelAndView中
            modelAndView.addObject("userid", userid);
            modelAndView.addObject("loginId", getLoginUserId(request));
            modelAndView.addObject("showname", userExpandDto.getShowname());
            modelAndView.addObject("userExpandDto", userExpandDto);
        } catch (Exception e) {
            logger.error("FriendAction.toOtherFriend", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 报错返回404页面
        }
        return modelAndView;
    }

    /**
     * 更新备注
     *
     * @param request
     * @param friend  friend 传入Remarks（备注） CusId（自己的id） CusFriendId（好友的id）
     * @return
     */
    @RequestMapping(value = "/friend/addRemarks")
    @ResponseBody
    public Map<String, Object> addRemarks(HttpServletRequest request, @ModelAttribute Friend friend) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            friend.setCusId(getLoginUserId(request));// set用户id
            String falg = friendService.updateFriendForRemarksByCusIdAndCusFriendId(friend);
            map.put("message", falg);
        } catch (Exception e) {
            logger.error("FriendAction.addRemarks", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 我关注 的用户的列表
     *
     * @param request
     * @param pageEntity 分页参数
     * @return
     */
    @RequestMapping(value = "/friend")
    public ModelAndView toMyattentionCustomer(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(toMyattentionCustomer);
        try {
        } catch (Exception e) {
            logger.error("FriendAction.toMyattentionCustomer", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }

    /**
     * 我关注 的用户的列表
     *
     * @param request
     * @param pageEntity 分页参数
     * @return
     */
    @RequestMapping(value = "/fri/ajax/myattention")
    public ModelAndView ajaxfri(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(ajaxfri);
        try {
            String falg = request.getParameter("falg");
            this.setPage(page);
            this.getPage().setPageSize(12);//每页显示条数
            if ("myattention".equals(falg)) {
                this.setPage(page);// set传来的分页参数
                this.getPage().setPageSize(PageConstans.MyFriend_Page);// 每页显示条数
                Friend fri = new Friend();
                fri.setCusId(getLoginUserId(request));
                List<SnsUserExpandDto> customerList = weiBoService.queryMyAttentionCustomer(fri, this.getPage());// 我关注的用户的列表
                modelAndView.addObject("customerList", customerList);// 查询出的用户list
            }
            if ("myfans".equals(falg)) {
                modelAndView = new ModelAndView(toMyFans);
                Friend friend = new Friend();
                friend.setCusId(getLoginUserId(request));// set用户id
                List<SnsUserExpandDto> customerList = weiBoService.queryMyFans(friend, page);// 查询我的粉丝用户列表
                //清除粉丝未读消息的缓存
                webHessianService.readMsgNumAddOrReset("unreadFansNum", getLoginUserId(request),"reset");
                modelAndView.addObject("customerList", customerList);// 查询出的用户list放入modelAndView中
            }
            if ("myblack".equals(falg)) {
                modelAndView = new ModelAndView(queryBlackList);
                BlackList blackList = new BlackList();
                blackList.setCusId(getLoginUserId(request));// set用户id
                List<SnsUserExpandDto> queryCustomerList = blackListService.queryBlackListByCusId(blackList, this.getPage());// 黑名单列表
                modelAndView.addObject("queryCustomerList", queryCustomerList);// 查询出
                // 的黑名单list放入modelAndView中
            }
            if ("visitor".equals(falg)) {
                modelAndView = new ModelAndView(toMyVisitorCustomer);
                this.getPage().setPageSize(PageConstans.Myvisitor_Page);// 每页显示条数
                List<Visitor> visitorList = visitorService.queryVisitorByCusId(getLoginUserId(request), this.getPage());// 最近访客
                modelAndView.addObject("visitorList", visitorList);// 查询出的用户list
                // 放入modelAndView中
            }

            modelAndView.addObject("page", this.getPage());// 分页的参数放入modelAndView中
        } catch (Exception e) {
            logger.error("FriendAction.ajaxfri", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }


    /**
     * 我关注 的用户的列表
     *
     * @param request
     * @param pageEntity 分页参数
     * @return
     */
    @RequestMapping(value = "/friend/{cusId}/visitor")
    public ModelAndView toVisitor(HttpServletRequest request, @ModelAttribute("page") PageEntity page,@PathVariable("cusId") Long cusId) {
        ModelAndView modelAndView = new ModelAndView(toPersonVisitorCustomer);
        try {
            this.setPage(page);// set传来的分页参数
            this.getPage().setPageSize(PageConstans.Myvisitor_Page);// 每页显示条数
            List<Visitor> visitorList = visitorService.queryVisitorByCusId(cusId, this.getPage());// 最近访客
            modelAndView.addObject("visitorList", visitorList);// 查询出的用户list
            // 放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 分页的参数放入modelAndView中
        } catch (Exception e) {
            logger.error("FriendAction.toMyattentionCustomer", e);
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404页面
        }
        return modelAndView;
    }

}
