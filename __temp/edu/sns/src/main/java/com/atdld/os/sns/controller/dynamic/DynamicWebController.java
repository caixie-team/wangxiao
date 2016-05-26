package com.atdld.os.sns.controller.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.web.VelocityHtmlUtil;
import com.atdld.os.sns.constants.DynamicConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.discuss.DisMember;
import com.atdld.os.sns.entity.dynamic.DynamicWeb;
import com.atdld.os.sns.entity.friend.Friend;
import com.atdld.os.sns.service.discuss.DisMemberService;
import com.atdld.os.sns.service.dynamic.DynamicWebService;
import com.atdld.os.sns.service.friend.FriendService;

/**
 * @author :
 * @ClassName com.atdld.os.sns.controller.dynamic.DynamicWebAction
 * @description 动态的action类
 * @Create Date : 2014-1-11 下午2:32:29
 */
@Controller
public class DynamicWebController extends SnsBaseController {
    /**
     * log对象
     */
    private Logger logger = Logger.getLogger(DynamicWebController.class);

    @Autowired
    private DynamicWebService dynamicWebService;
    @Autowired
    private DisMemberService disMemberService;
    @Autowired
    private FriendService friendService;

    /**
     * 首页的动态
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/dw/queryDynamicWebList")
    @ResponseBody
    public Map<String, Object> queryDynamicWebList(HttpServletRequest request,
                                                   @ModelAttribute("page") PageEntity page, @RequestParam("type") int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long cusId = getLoginUserId(request);
            VelocityHtmlUtil velocityHtmlUtil = new VelocityHtmlUtil(request.getSession().getServletContext().getRealPath(""), "WEB-INF/view/" + SnsBaseController.SNS_VIEW_PATH + "/dynamic/dynamic.vm");
            this.setPage(page);
            if (type == DynamicConstans.DYNAMICWEB_PAGE_TYPE_ALL) {// 全部
                //查询当前用户加入过的小组
                List<DisMember> disMemberList = disMemberService.queryGroupIdByCusId(cusId);
                // 获得全站动态
                List<DynamicWeb> dynamicWebList = dynamicWebService.queryDynamicWebList(null, this.getPage());
                if (ObjectUtils.isNotNull(dynamicWebList) &&  ObjectUtils.isNotNull(dynamicWebList)) {
                    for (DynamicWeb dynamicWeb : dynamicWebList) {
                        //小组类型动态，判断是用户否加入小组，加标识
                        if (dynamicWeb.getType() == DynamicConstans.DYNAMICWEB_TYPE_DISARTICLE || dynamicWeb.getType() == DynamicConstans.DYNAMICWEB_TYPE_REDISARTICLE) {
                            boolean flag = true;
                            for (DisMember disMember : disMemberList) {
                                if (dynamicWeb.getAssistId().longValue() == disMember
                                        .getGroupId().longValue()) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                dynamicWeb.setContent("");
                            }
                        }
                    }
                }
                //添加是否关注过的标识dynamicWebList
                dynamicWebList=dynamicWebService.setFriendFlag(dynamicWebList, cusId);
                velocityHtmlUtil.put("dynamicWebList", dynamicWebList);
            }
            if (type == DynamicConstans.DYNAMICWEB_PAGE_TYPE_DIS) {// 小组动态
                // 获得用户id
                List<DisMember> disMemberList = disMemberService.queryGroupIdByCusId(cusId);
                // 页面传来的数据放到page中
                List<Long> ids = new ArrayList<Long>();
                if (ObjectUtils.isNotNull(disMemberList)) {
                    for (int i = 0; i < disMemberList.size(); i++) {
                        Long groupId = disMemberList.get(i).getGroupId();
                        ids.add(groupId);
                    }
                    List<DynamicWeb> dynamicWebList = dynamicWebService.queryDynamicWebDisGroup(ids, this.getPage());
                    //添加是否关注过的标识dynamicWebList
                    dynamicWebList=dynamicWebService.setFriendFlag(dynamicWebList, cusId);
                    velocityHtmlUtil.put("dynamicWebList", dynamicWebList);
                }
            }
            if (type == DynamicConstans.DYNAMICWEB_PAGE_TYPE_FRIEND) {// 好友动态
                // 页面传来的数据放到page中
                List<DynamicWeb> dynamicWebList = dynamicWebService.queryDynamicWebJunZiHui(cusId, this.getPage());
                if (ObjectUtils.isNotNull(dynamicWebList)) {
                  //添加是否关注过的标识dynamicWebList,好友肯定是关注过的。
                    for(DynamicWeb dynamicWeb:dynamicWebList){
                        dynamicWeb.setCusAttentionId(dynamicWeb.getCusId());
                    }
                    velocityHtmlUtil.put("dynamicWebList", dynamicWebList);
                }
            }
            // 课程动态
            if (type == DynamicConstans.DYNAMICWEB_PAGE_TYPE_COURSE) {
                // 页面传来的数据放到page中
                List<DynamicWeb> dynamicWebList = dynamicWebService.queryDynamicWebByTP("11,12", this.getPage());
                //添加是否关注过的标识dynamicWebList
                dynamicWebList=dynamicWebService.setFriendFlag(dynamicWebList, cusId);
                velocityHtmlUtil.put("dynamicWebList", dynamicWebList);
            }
            // 考试动态
            if (type == DynamicConstans.DYNAMICWEB_PAGE_TYPE_EXAM) {
                // 页面传来的数据放到page中
                List<DynamicWeb> dynamicWebList = dynamicWebService
                        .queryDynamicWebByTP("13", this.getPage());
                //添加是否关注过的标识dynamicWebList
                dynamicWebList=dynamicWebService.setFriendFlag(dynamicWebList, cusId);
                velocityHtmlUtil.put("dynamicWebList", dynamicWebList);
            }
            velocityHtmlUtil.put("loginCusId", cusId);
            velocityHtmlUtil.put("dateformat", new DateTool());
            velocityHtmlUtil.put("page", this.getPage());
            velocityHtmlUtil.put("imagesPath", CommonConstants.staticServer);
            velocityHtmlUtil.put("contextPath", request.getContextPath());
            velocityHtmlUtil.put("uploadStaticUrl", CommonConstants.staticImageServer);
            map.put("html", velocityHtmlUtil.getText());
            map.put("page", this.getPage());
            return map;
        } catch (Exception e) {
            logger.error("DynamicWebAction.queryDynamicWebList", e);
            setExceptionRequest(request, e);
            return map;
        }


    }

    /**
     * 他人主页全站动态
     *
     * @param request
     * @param page    分页参数
     * @return
     */
    @RequestMapping("/dw/queryPersonDynamicWebList")
    @ResponseBody
    public Map<String, Object> queryPersonDynamicWebList(HttpServletRequest request,
                                                         @ModelAttribute("page") PageEntity page, @RequestParam("userid") Long userid) {
        Map<String, Object> map = new HashMap<String, Object>();
        VelocityHtmlUtil velocityHtmlUtil = new VelocityHtmlUtil(request.getSession().getServletContext().getRealPath(""), "WEB-INF/view/" + SnsBaseController.SNS_VIEW_PATH + "/dynamic/dynamic.vm");
        try {
            List<DisMember> disMemberList = new ArrayList<DisMember>();
            if (userid.longValue() != getLoginUserId(request).longValue()) {
                disMemberList = disMemberService.queryGroupIdByCusId(getLoginUserId(request));
            } else {
                disMemberList = disMemberService.queryGroupIdByCusId(userid);
            }
            // 页面传来的数据放到page中
            this.setPage(page);
            this.getPage().setPageSize(SnsConstants.PERSON_DYNAMIC_NUM);// 默认页10
            // 获得全站动态
            List<DynamicWeb> dynamicWebList = dynamicWebService.queryDynamicWebList(
                    userid, this.getPage());
            if (dynamicWebList != null && dynamicWebList.size() > 0) {
                for (DynamicWeb dynamicWeb : dynamicWebList) {
                    //遐之苑动态头像下方不显示关注按钮（当该值不为null，则不显示，所以输入无效数字0，则页面不在显示关注按钮）
                    dynamicWeb.setCusAttentionId(0L);
                    //判断是否加入小组
                    if (dynamicWeb.getType() == DynamicConstans.DYNAMICWEB_TYPE_DISARTICLE
                            || dynamicWeb.getType() == DynamicConstans.DYNAMICWEB_TYPE_REDISARTICLE) {
                        if (disMemberList != null && disMemberList.size() > 0) {
                            boolean flag = true;
                            for (DisMember disMember : disMemberList) {
                                if (dynamicWeb.getAssistId().longValue() == disMember
                                        .getGroupId().longValue()) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                dynamicWeb.setContent("");
                            }
                        }
                    }
                }
            }
            velocityHtmlUtil.put("dynamicWebList", dynamicWebList);
            velocityHtmlUtil.put("dateformat", new DateTool());
            velocityHtmlUtil.put("page", this.getPage());
            velocityHtmlUtil.put("imagesPath", CommonConstants.staticServer);
            velocityHtmlUtil.put("contextPath", request.getContextPath());
            velocityHtmlUtil.put("uploadStaticUrl", CommonConstants.staticImageServer);
            velocityHtmlUtil.getText();
            map.put("html", velocityHtmlUtil.getText());

            //	map.put("dynamicWebList", dynamicWebList);
            map.put("page", this.getPage());
        } catch (Exception e) {
            logger.error("DynamicWebAction.queryDynamicWebList", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 查看好友动态
     *
     * @param request
     * @param page    分页查询
     * @return
     */
    @RequestMapping("/dw/queryDynamicWebJunZiHui")
    @ResponseBody
    public Map<String, Object> queryDynamicWebJunZiHui(HttpServletRequest request,
                                                       @ModelAttribute("page") PageEntity page) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long cusId = getLoginUserId(request);

            // 页面传来的数据放到page中
            this.setPage(page);
            // 获得加好友动态
            List<DynamicWeb> dynamicWebList = dynamicWebService.queryDynamicWebJunZiHui(
                    cusId, this.getPage());
            // 加好友动态u
            List<DynamicWeb> dynamicWebFriend = dynamicWebService.queryDynamicWebFriend(
                    cusId, this.getPage());
            map.put("entity", dynamicWebList);
            map.put("dynamicWebFriend", dynamicWebFriend);
            map.put("page", this.getPage());
        } catch (Exception e) {
            logger.error("DynamicWebAction.queryDynamicWebFriend", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 查询小组组动态
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/dynamic/disgroup")
    public ModelAndView queryDynamicWebDisGroup(HttpServletRequest request,
                                                @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获得用户id
            Long cusId = getLoginUserId(request);
            List<DisMember> disMemberList = disMemberService.queryGroupIdByCusId(cusId);
            // 页面传来的数据放到page中
            this.setPage(page);
            List<Long> ids = new ArrayList<Long>();
            if (disMemberList != null && disMemberList.size() > 0) {
                for (int i = 0; i < disMemberList.size(); i++) {
                    Long groupId = disMemberList.get(i).getGroupId();
                    ids.add(groupId);
                }
                List<DynamicWeb> dynamicWebList = dynamicWebService
                        .queryDynamicWebDisGroup(ids, this.getPage());
                modelAndView.addObject("dynamicWebList", dynamicWebList);
            } else {
                modelAndView.addObject("dynamicWebList", null);
            }

        } catch (Exception e) {
            logger.error("DynamicWebAction.queryDynamicWebDisGroup", e);// 记录log
            return new ModelAndView(setExceptionRequest(request, e));// 报错返回404界面
        }
        return modelAndView;
    }
}
