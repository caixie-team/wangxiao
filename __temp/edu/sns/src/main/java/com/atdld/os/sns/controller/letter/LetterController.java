package com.atdld.os.sns.controller.letter;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.sns.constants.LetterConstans;
import com.atdld.os.sns.constants.PageConstans;
import com.atdld.os.sns.constants.SnsConstants;
import com.atdld.os.sns.controller.common.LogController;
import com.atdld.os.sns.controller.common.SnsBaseController;
import com.atdld.os.sns.entity.customer.SnsUserExpand;
import com.atdld.os.sns.entity.customer.SnsUserExpandDto;
import com.atdld.os.sns.entity.letter.MsgReceive;
import com.atdld.os.sns.entity.letter.MsgSender;
import com.atdld.os.sns.entity.letter.MsgSystem;
import com.atdld.os.sns.entity.letter.QueryMsgReceive;
import com.atdld.os.sns.service.letter.MsgReceiveService;
import com.atdld.os.sns.service.letter.MsgSenderService;
import com.atdld.os.sns.service.letter.MsgSystemService;
import com.atdld.os.sns.service.user.SnsUserService;

/**
 * @author :
 * @ClassName com.atdld.os.core.action.msgReceive.LetterAction
 * @description 站内信的action
 * @Create Date : 2013-12-13 下午8:04:11
 */
@Controller
public class LetterController extends SnsBaseController {

    private static final Logger logger = Logger.getLogger(LetterController.class);

    // 返回路径
    private static final String queryLetterByOutbox = getViewPath("/letter/to_letter_outbox");// 站内信收件箱
    private static final String queryLetterByInbox = getViewPath("/letter/to_letter_inbox");// 站内信收件箱
    private static final String systemInform = getViewPath("/letter/system_inform");// 系统消息
    private static final String toLetterHistory = getViewPath("/letter/to_letter_history");// 站内信历史记录

    @Autowired
    private MsgReceiveService msgReceiveService;// 站内信service
    @Autowired
    private MsgSenderService msgSenderService;// 站内信发件箱
    @Autowired
    private SnsUserService snsUserService;// 站内信发件箱
    @Autowired
    private WebHessianService webHessianService;

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
     * 用户发送站内信
     *
     * @param msgReceive
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/addLetter")
    @ResponseBody
    public Map<String, Object> addLetter(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgReceive.setAddTime(new Date());// 添加时间
            msgReceive.setUpdateTime(new Date());// 更新时间
            msgReceive.setStatus(LetterConstans.LETTER_STATUS_UNREAD);
            msgReceive.setCusId(getLoginUserId(request));
            msgReceive.setType(LetterConstans.LETTER_TYPE_MESSAGE);
            String falg = msgReceiveService.addMsgReceive(msgReceive);// 添加站内信
            map.put("message", falg);
            // 日志打印
            Map<String, Object> logMap = LogController.getlogMap(request);
            logMap.put(LogController.ACTION, "addLetter");
            logMap.put("ReceivingCusId", "" + msgReceive.getReceivingCusId());
            logMap.put("msgReceiveid", "" + msgReceive.getId());
            logMap.put("cusId", "" + getLoginUserId(request));
            LogController.printLog(logMap);
            // 日志打印结束
        } catch (Exception e) {
            logger.error("LetterAction.addLetter", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 站内信发件箱
     *
     * @param request
     * @param page 分页参数
     * @return
     */
    @RequestMapping(value = "/letter/outbox")
    public ModelAndView queryLetterByOutbox(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(queryLetterByOutbox);
        try {
            this.setPage(page);
            page.setPageSize(PageConstans.Letter_Page);// 分页页数为6
            MsgSender msgSender = new MsgSender();
            msgSender.setCusId(getLoginUserId(request));
            List<MsgSender> msgSenderList = msgSenderService.queryMsgSenderByOutbox(msgSender, page);// 查询发件箱
            modelAndView.addObject("msgSenderList", msgSenderList);// 站内信发件箱list放入
            modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("LetterAction.queryLetterByOutbox", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;// 返回ModelAndView
    }


    /**
     * 删除站内信发件箱
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/letter/delLetterOutbox")
    @ResponseBody
    public Map<String, Object> delLetterOutbox(@RequestParam("id") Long id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            MsgSender msgSender = new MsgSender();
            msgSender.setCusId(getLoginUserId(request));
            msgSender.setId(id);// 发件箱id
            Long num = msgSenderService.delLetterOutbox(msgSender);
            if (num.intValue() == 1) {
                map.put("message", SnsConstants.SUCCESS);// 成功
            } else {
                map.put("message", SnsConstants.FALSE);// 失败
            }
        } catch (Exception e) {
            logger.error("LetterAction.delLetterOutbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 删除站内信发件箱
     *
     * @param ids
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/letter/delselectbyids")
    @ResponseBody
    public Map<String, Object> delSelectByids(@RequestParam("ids") String ids, @RequestParam("type") String type, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if ("".equals(ids.trim())) {
                map.put("message", "strempty");
                return map;
            }
            Long num = 0L;
            if ("msgSender".equals(type)) {
                num = msgSenderService.delMsgSenderByids(ids);
            } else {
                num = msgReceiveService.delMsgReceiveByids(ids);
            }

            if (num.intValue() == 0) {
                map.put("message", SnsConstants.EMPTY);
            } else {
                map.put("message", SnsConstants.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("LetterAction.delLetterOutbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 清空站内信发件箱
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/delAllOutbox")
    @ResponseBody
    public Map<String, Object> delAllOutbox(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long num = msgSenderService.delAllOutbox(getLoginUserId(request));
            if (num.intValue() == 0) {
                map.put("message", SnsConstants.EMPTY);
            } else {
                map.put("message", SnsConstants.SUCCESS);
            }

        } catch (Exception e) {
            logger.error("LetterAction.delAllOutbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 清空站内信收件箱
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/delAllInbox")
    @ResponseBody
    public Map<String, Object> delAllInbox(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long num = msgReceiveService.delAllOutbox(getLoginUserId(request));
            if (num.intValue() == 0) {
                map.put("message", SnsConstants.EMPTY);
            } else {
                map.put("message", SnsConstants.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("LetterAction.delAllOutbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 删除站内信收件箱
     *
     * @param msgReceive
     * @return
     */
    @RequestMapping(value = "/letter/delLetterInbox")
    @ResponseBody
    public Map<String, Object> delLetterInbox(@ModelAttribute MsgReceive msgReceive, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgReceive.setReceivingCusId(getLoginUserId(request));// set 用户id
            Long num = msgReceiveService.delMsgReceiveInbox(msgReceive);// 删除收件箱
            if (num.intValue() == 1) {
                map.put("message", SnsConstants.SUCCESS);// 成功
            } else {
                map.put("message", SnsConstants.FALSE);// 失败
            }
        } catch (Exception e) {
            logger.error("LetterAction.delLetterInbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 删除站内信系统消息
     *
     * @param id
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/letter/delLetter")
    @ResponseBody
    public Map<String, Object> delLetter(@RequestParam("id") Long id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setId(id);
            Long num = msgReceiveService.delMsgReceive(msgReceive);
            if (num.intValue() == 1) {
                map.put("message", SnsConstants.SUCCESS);// 成功
            } else {
                map.put("message", SnsConstants.FALSE);// 失败
            }
        } catch (Exception e) {
            logger.error("LetterAction.delLetterInbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 清空系统消息
     *
     * @param request
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/letter/delallmsgsys")
    @ResponseBody
    public Map<String, Object> delAllMsgSys(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Long num = msgReceiveService.delAllMsgSys(getLoginUserId(request));
            if (num.intValue() == 0) {
                map.put("message", SnsConstants.EMPTY);
            } else {
                map.put("message", SnsConstants.SUCCESS);
            }
        } catch (Exception e) {
            logger.error("LetterAction.delAllOutbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 查询站内信收件箱
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/letter")
    public ModelAndView queryLetterByInbox(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(queryLetterByInbox);
        try {
            this.setPage(page);
            page.setPageSize(PageConstans.Letter_Page);// 分页页数为6
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setReceivingCusId(getLoginUserId(request));// set用户id
            //msgReceiveService.updateAllReadMsgReceiveInbox(msgReceive);// 更新所有收件箱为已读
            List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveByInbox(msgReceive, this.getPage());// 查询站内信收件箱
            modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("LetterAction.queryLetterByInbox", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }
    /**
     * 站内信历史记录
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/letter/history/{userid}")
    public ModelAndView toLetterHistory(HttpServletRequest request, @PathVariable Long userid, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(toLetterHistory);
        try {
            this.setPage(page);
            page.setPageSize(PageConstans.Letter_Page);// 分页页数为6
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setCusId(userid);//
            msgReceive.setReceivingCusId(getLoginUserId(request));// set用户id
            List<QueryMsgReceive> queryLetterList = msgReceiveService.queryMsgReceiveHistory(msgReceive, this.getPage());// 查询站内信收件箱
            modelAndView.addObject("queryLetterList", queryLetterList);// 查询出的站内信放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中
            modelAndView.addObject("cusId", getLoginUserId(request));// 登陆的id
            modelAndView.addObject("userid", userid);// 登陆的id
        } catch (Exception e) {
            logger.error("LetterAction.toLetterHistory", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }

    /**
     * 更新所有收件箱为已读
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/weibo/updateAllReadLetterInbox")
    @ResponseBody
    public Map<String, Object> updateAllReadLetterInbox(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setReceivingCusId(getLoginUserId(request));// set用户id
            msgReceiveService.updateAllReadMsgReceiveInbox(msgReceive);// 更新所有收件箱为已读
            map.put("message", SnsConstants.SUCCESS);

        } catch (Exception e) {
            logger.error("LetterAction.updateAllReadLetterInbox", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    @Autowired
    private MsgSystemService msgSystemService;
    /**
     * 系统通知
     *
     * @param request
     * @param page
     * @return ModelAndView
     */
    @RequestMapping(value = "/letter/sys")
    public ModelAndView systemInform(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView(systemInform);
        try {
            SnsUserExpand customer = new SnsUserExpand();
            customer.setCusId(getLoginUserId(request));
            //查询该用户的未读消息数
            SnsUserExpandDto userExpandDto  = snsUserService.getUserExpandByCusId(getLoginUserId(request));
            Date lastTime = userExpandDto.getLastSystemTime();
            List<MsgSystem> MSlist = msgSystemService.queryMSListByLT(lastTime);
            if (ObjectUtils.isNotNull(MSlist)) {
                List<MsgReceive> msgrcList = new ArrayList<MsgReceive>();
                //查出未读的系统消息插入到系统中 更新
                for (MsgSystem mgstm : MSlist) {
                    MsgReceive msgReceive = new MsgReceive();
                    msgReceive.setContent(mgstm.getContent());
                    msgReceive.setAddTime(new Date());
                    msgReceive.setReceivingCusId(getLoginUserId(request));
                    msgReceive.setStatus(LetterConstans.LETTER_STATUS_READ);
                    msgReceive.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
                    msgReceive.setUpdateTime(new Date());
                    msgReceive.setShowname(customer.getShowname());
                    msgrcList.add(msgReceive);
                }
                //批量添加站内信
                msgReceiveService.addMsgReceiveBatch(msgrcList);
            }

            this.setPage(page);
            page.setPageSize(PageConstans.Letter_Page);// 分页页数为6
            MsgReceive msgReceive = new MsgReceive();
            msgReceive.setReceivingCusId(getLoginUserId(request));// set用户id
            List<QueryMsgReceive> queryLetterList = msgReceiveService.querysystemInform(msgReceive, this.getPage());
            //清除粉丝未读消息的缓存
            webHessianService.readMsgNumAddOrReset("sysMsgNum", getLoginUserId(request),"reset");
            //上传统计系统消息时间更新最新时间
            webHessianService.updateCusForLST(getLoginUserId(request), new Date().getTime());
            modelAndView.addObject("queryLetterList", queryLetterList);// 系统通知放入modelAndView中
            modelAndView.addObject("page", this.getPage());// 分页参数放入modelAndView中
        } catch (Exception e) {
            logger.error("LetterAction.systemInform", e);
            setExceptionRequest(request, e);
            ModelAndView modelAndViewError = new ModelAndView(ERROR);
            return modelAndViewError;// 返回404页面
        }
        return modelAndView;
    }


    /**
     * 查询该用户有多少未读消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/queryUnReadLetter")
    @ResponseBody
    public Map<String, Object> queryUnReadLetter(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            Map<String, String> queryletter = msgReceiveService.queryUnReadMsgReceiveNumByCusId(getLoginUserId(request));// 查询该用户有多少未读消息
            map.put("entity", queryletter);// 把值放入map中返回json
        } catch (Exception e) {
            logger.error("LetterAction.queryUnReadLetter", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 通过用户名查询该用户是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/queryUserByName")
    @ResponseBody
    public Map<String, Object> queryUserByName(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //未登录不可操作
            if (getLoginUserId(request).longValue() == 0) {
                return map;
            }
            String username = request.getParameter("username");
            //不能为空
            if (StringUtils.isEmpty(username)) {
                map.put("message", "empty");// 把值放入map中返回json
                return map;
            }

            List<SnsUserExpandDto> cusList = snsUserService.queryCustomerByShowNameEquals(username.trim());
            if (ObjectUtils.isNull(cusList)) {
                map.put("message", "null");// 把值放入map中返回json
                return map;
            } else {
                map.put("message", "success");// 把值放入map中返回json
                map.put("entity", cusList.get(0).getCusId());// 把值放入map中返回json
                return map;
            }
        } catch (Exception e) {
            logger.error("LetterAction.queryUnReadLetter", e);
            setExceptionRequest(request, e);
        }
        return map;
    }
    
    public void getSystemMsg(){
    	
    }
}
