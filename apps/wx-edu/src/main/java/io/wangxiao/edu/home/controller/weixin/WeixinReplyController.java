package io.wangxiao.edu.home.controller.weixin;

import io.wangxiao.edu.common.util.DESCoder;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.WeixinType;
import io.wangxiao.edu.home.constants.web.WebContants;
import io.wangxiao.edu.home.controller.weixin.util.MessageUtil;
import io.wangxiao.edu.home.controller.weixin.util.XMLParse;
import io.wangxiao.edu.home.entity.weixin.WeixinReply;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.home.service.weixin.WeixinReplyService;
import io.wangxiao.edu.mobile.order.util.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * 微信线上回复
 *
 */
@Controller
@RequestMapping("/web")
public class WeixinReplyController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(WeixinReplyController.class);


    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private WeixinReplyService weixinReplyService;

    private static final String detailModel = getViewPath("/admin/weixin/reply/weixin_detail_model");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("weixinReply")
    public void initBinderWeixinReply(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("weixinReply.");
    }

    @RequestMapping("/onweixin/manage")
    public void WeixinMsgManage(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        if (method.equals("GET")) {//如果微信发过来的是get请求进行消息正确性验证
            checkToken(request, response);
        } else if (method.equals("POST")) {//如果微信发过来的是post请求进行消息处理并相应
            processRequest(request, response);
        }
    }


    /**
     * signature 	微信加密签名
     * timestamp 	时间戳
     * nonce 	随机数
     * echostr 	随机字符串
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，否则接入失败。
     * signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * 加密/校验流程：
     * 1. 将token、timestamp、nonce三个参数进行字典序排序
     * 2. 将三个参数字符串拼接成一个字符串进行sha1加密
     * 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     *
     * @param request
     * @param response
     */
    public void checkToken(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        //微信用户自定义token，验证微信消息的真实性
        String Token = "";
        try {
            Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");
            Map<String, Object> weixinMapVal = (Map<String, Object>) weixinMap.get("weixin");
            Token = (String) weixinMapVal.get("wxToken");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] ArrTmp = {Token, timestamp, nonce};
        Arrays.sort(ArrTmp);//字典序排序
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ArrTmp.length; i++) {
            sb.append(ArrTmp[i]);
        }
        String pwd = DESCoder.sha1(sb.toString());//SHA-1加密后的字符串
        if (pwd.equals(signature)) {//如果加密后的字符串和微信传过来的参数相同，返回echostr
            if (!"".equals(echostr) && echostr != null) {
                try {
                    response.getWriter().print(echostr);
                } catch (IOException e) {
                    logger.error("WeixinActioncheckToken", e);
                }
            }
        }
    }

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        WeixinReply weixinReply = null;
        try {
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String msgSignature = request.getParameter("msg_signature");
            /**
             * 发送get请求，获取access_token
             * encodingAESKey 	 	加密明文
             * token 	 微信上填写的token
             * */
            Map<String, Object> weixinMap = websiteProfileService.getWebsiteProfileByType("weixin");
            @SuppressWarnings("unchecked")
            Map<String, Object> weixinMapVal = (Map<String, Object>) weixinMap.get("weixin");
            String encodingAESKey = (String) weixinMapVal.get("encodingAESKey");
            String token = (String) weixinMapVal.get("wxToken");
            String appId = (String) weixinMapVal.get("wxAppID");
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request, encodingAESKey, appId, token, timestamp, nonce, msgSignature);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 文本消息
            if (msgType.equals(WeixinType.text.toString())) {
                String keyWords = requestMap.get("Content");
                if (keyWords.equals("客服")) {//文本跳转客服接口
                    weixinReply = new WeixinReply();
                    weixinReply.setMsgType(4);
                } else {
                    weixinReply = weixinReplyService.WeixinExit(keyWords, fromUserName, toUserName);//判断关键字回复是否存在并拼接XML
                }

            }
            // 图片消息
            else if (msgType.equals(WeixinType.image.toString())) {

            }
            // 事件推送
            else if (msgType.equals(WeixinType.event.toString())) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(WeixinType.subscribe.toString())) {
                    //查询关注时回复
                    weixinReply = weixinReplyService.getDefaultWeixin(WeixinType.add.toString());
                }
                // 取消订阅
                else if (eventType.equals(WeixinType.unsubscribe.toString())) {
                    // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(WeixinType.CLICK.toString())) {
                    // 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    String eventKey = requestMap.get("EventKey");
                    if (eventKey.equals("客服")) {//菜单跳转客服接口
                        weixinReply = new WeixinReply();
                        weixinReply.setMsgType(4);
                    } else {
                        weixinReply = weixinReplyService.WeixinExit(eventKey, fromUserName, toUserName);//判断Key值回复是否存在并拼接XML
                    }

                }
            }
            String respMessage = "";
            OutputStream os = response.getOutputStream();
            BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
            if (weixinReply == null)//最终处理查询默认回复
            {
                weixinReply = weixinReplyService.getDefaultWeixin(WeixinType.tolerate.toString());//查询默认回复
            }
            if (weixinReply != null) {//默认回复也为空，返回空串让微信不做回应
                respMessage = getXmlWeixin(weixinReply, fromUserName, toUserName);//拼接默认回复xml
            }

            String encryptType = request.getParameter("encrypt_type");
            //System.out.println(encryptType);

            if (encryptType != null && !encryptType.equals("raw")) {//无encrypt_type或者其值为raw，则回复明文，否则加密回复密文
                //加密发送给微信的消息
                WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAESKey, appId);
                respMessage = pc.encryptMsg(respMessage, timestamp, nonce);

            }
            //System.out.println(respMessage);
            resBr.write(respMessage);
            resBr.flush();
            resBr.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 组装不同类型的微信xml
     *
     * @param weixinReply
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public String getXmlWeixin(WeixinReply weixinReply, String fromUserName, String toUserName) {
        String respMessage = "";
        Date date = new Date();
        //回复消息，发送方和接收方反转
        if (weixinReply.getMsgType() == 1)//文本
        {
            respMessage = XMLParse.generateText(fromUserName, toUserName, date.getTime(), weixinReply.getContent());
        } else if (weixinReply.getMsgType() == 2)//图文
        {
            List<WeixinReply> weixinReplys = new ArrayList<WeixinReply>();
            weixinReplys.add(weixinReply);
            respMessage = XMLParse.generateImageText(fromUserName, toUserName, date.getTime(), weixinReplys);
        } else if (weixinReply.getMsgType() == 3)//多图文
        {
            List<WeixinReply> weixinReplys = new ArrayList<WeixinReply>();
            weixinReplys.add(weixinReply);
            //查询子图文集合
            List<WeixinReply> sonWeixinReplys = weixinReplyService.getWeixinReplyByManyId(weixinReply.getId());
            for (WeixinReply sonWeixinReply : sonWeixinReplys) {
                weixinReplys.add(sonWeixinReply);
            }
            respMessage = XMLParse.generateImageText(fromUserName, toUserName, date.getTime(), weixinReplys);
        } else if (weixinReply.getMsgType() == 4)//进入客服
        {
            respMessage = XMLParse.generateService(fromUserName, toUserName, date.getTime());
        }
        return respMessage;
    }

    /**
     * 微信图文详情模版页面
     *
     * @return
     */
    @RequestMapping("/weixin/content/{id}")
    public ModelAndView getWeixinContentById(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(detailModel);
        try {
            WeixinReply weixinReply = weixinReplyService.getWeixinById(id);
            weixinReply.setNickName(WebContants.company_zh);
            modelAndView.addObject("weixinReply", weixinReply);
        } catch (Exception e) {
            logger.error("AdminWeixinReplyController.getWeixinContentById", e);
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    @RequestMapping("/weixin/a")
    public ModelAndView getWeixinAA() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/edu/admin/weixin/a");
        return modelAndView;
    }
}
