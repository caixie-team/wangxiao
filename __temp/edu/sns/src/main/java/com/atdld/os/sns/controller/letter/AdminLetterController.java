package com.atdld.os.sns.controller.letter;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.letter.MsgSystem;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.letter.MsgSystemService;

/**
 * @author :
 * @ClassName com.atdld.os.core.action.letter.AdminLetterAction
 * @description 后台系统消息
 * @Create Date : 2013-12-18 下午4:19:37
 */
@Controller
@RequestMapping("/admin")
public class AdminLetterController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(AdminLetterController.class);

    // 返回路径
    private static final String senSystemMessages = getViewPath("/admin/letter/to_send_systemMessage");// 发送系统消息页面
    private static final String toSendSystemMessagesByCusId = getViewPath("/admin/letter/to_send_systemMessageByCusId");// 站内信收件箱
    private static final String toSystemList = getViewPath("/admin/letter/to_system_list");// 系统消息列表

    @Autowired
    private MsgSystemService msgSystemService;// 站内信service
    @Autowired
    private MsgReceiveService msgReceiveService;

    @InitBinder("msgSystem")
    public void initBinderMsgSystem(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("msgSystem.");
    }

    /**
     * 跳转到小组发消息页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/toSendSystemMessages")
    public ModelAndView senSystemMessages(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(senSystemMessages);
        return modelAndView;
    }

    /**
     * 跳转到单个用户发送消息
     *
     * @param request
     * @param cusId
     * @return
     */
    @RequestMapping(value = "/letter/toSendSystemMessagesByCusId")
    public ModelAndView senSystemMessagesByCusId(HttpServletRequest request, @RequestParam("cusId") Long cusId) {
        ModelAndView modelAndView = new ModelAndView(toSendSystemMessagesByCusId);
        try {
            modelAndView.addObject("cusId", cusId);//
        } catch (Exception e) {
            logger.error("AdminLetterAction.senSystemMessagesByCusId", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }

    /**
     * 发送系统消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/sendJoinGroup")
    @ResponseBody
    public Map<String, Object> sendSystemInform(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String content = request.getParameter("content");// 发送系统消息的内容
            MsgSystem msgReceive = new MsgSystem();
            msgReceive.setContent(content);// 添加站内信的内容
            msgReceive.setUpdateTime(new Date());// 更新时间s
            msgReceive.setAddTime(new Date());// 添加时间
            msgSystemService.addMsgSystem(msgReceive);
			//msgReceiveService.addSystemMessage(content);
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemInform", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 发送个人消息
     *
     * @param request
     * @param content
     * @param cusId
     * @return
     */
    @RequestMapping(value = "/letter/sendSystemInformByCusId")
    @ResponseBody
    public Map<String, Object> sendSystemInformByCusId(HttpServletRequest request, @RequestParam("content") String content, @RequestParam("cusId") Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            msgReceiveService.addSystemMessageByCusId(content, cusId);
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemInformByCusId", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 系统消息列表
     *
     * @param request
     * @param cusId
     * @return
     */
    @RequestMapping(value = "/letter/toSystemList")
    public ModelAndView toSystemList(HttpServletRequest request, @ModelAttribute MsgSystem msgSystem, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toSystemList);
        try {
            //系统消息列表
            List<MsgSystem> msgSystemList = msgSystemService.queryMsgSystemList(msgSystem, page);
            modelAndView.addObject("msgSystemList", msgSystemList);
            modelAndView.addObject("msgSystem", msgSystem);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("AdminLetterAction.toSystemList", e);
            setExceptionRequest(request, e);
        }
        return modelAndView;
    }

    /**
     * 删除系统消息
     *
     * @param request
     * @param id      系统消息id
     * @return
     */
    @RequestMapping(value = "/letter/delSystemById")
    @ResponseBody
    public Map<String, Object> delSystemById(HttpServletRequest request, @RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgSystemService.delMsgSystemById(id);
            map.put("message", SnsConstants.SUCCESS);
        } catch (Exception e) {
            logger.error("AdminLetterAction.delSystemById", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

}
