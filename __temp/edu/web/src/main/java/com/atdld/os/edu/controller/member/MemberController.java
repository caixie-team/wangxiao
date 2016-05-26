package com.atdld.os.edu.controller.member;



import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.service.gain.GuidGeneratorService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.alipay.CheckURL;
import com.atdld.os.core.util.alipay.Payment;
import com.atdld.os.core.util.alipay.SignatureHelper;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.service.member.MemberHessianService;
import com.atdld.os.edu.service.member.MemberOrderService;
import com.atdld.os.edu.service.member.MemberSaleService;
import com.atdld.os.edu.service.member.MemberTypeService;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.edu.service.weixin.WeixinPayService;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.constants.enums.PayType;
import com.atdld.os.edu.constants.enums.ReqChanle;
import com.atdld.os.edu.constants.enums.TrxOrderStatus;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.entity.member.MemberOrder;
import com.atdld.os.edu.entity.member.MemberSale;
import com.atdld.os.edu.entity.member.MemberType;
import com.atdld.os.edu.entity.user.UserAccount;
/**
 * MemberOrder管理接口
 * User:
 * Date: 2014-09-26
 */
@Controller
public class MemberController extends EduBaseController{
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
 	@Autowired
    private MemberOrderService memberOrderService;
 	@Autowired
    private MemberSaleService memberSaleService;
 	@Autowired
    private MemberHessianService memberHessianService;
 	@Autowired
    private UserAccountService userAccountService;
 	@Autowired
    private WebsiteProfileService websiteProfileService;
 	@Autowired
    GuidGeneratorService guidGeneratorService;
 	@Autowired
    private MemberTypeService memberTypeService;
 	@Autowired
    private WeixinPayService weixinPayService;
 	@InitBinder("memberOrder")
	public void initBinderMemberOrder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("memberOrder.");
	}
 	private static final String getAllMemberType = getViewPath("/member/memberCart");
 	//微信支付二维码
 	private String qcCode = getViewPath("/shopcart/QRCode");
 	/**
     * 跳转所有会员类型页
     *
     * @throws Exception
     */
    @RequestMapping("/member/alltype/{id}")
    public String showAllType(Model model,HttpServletRequest request,@PathVariable Long id){
  		try{
  			//网站会员配置
  	      	Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
  	  		@SuppressWarnings("unchecked")
			Map<String,Object> map=(Map<String, Object>) saleMap.get(WebSiteProfileType.sale.toString());
  	  		if(!map.get("verifyMember").equals("ON")){
  	  			return "redirect:/";
  	  		}
  	  		List<MemberType> memberTypes=memberTypeService.getWebMemberTypes();
  	  		model.addAttribute("memberTypes",memberTypes);
  	  		MemberType defaultMemberType=memberTypeService.getMemberTypeById(id);
  	  		model.addAttribute("defaultMemberType",defaultMemberType);
    	}catch(Exception e){
    		logger.error("MemberController.showAllType",e);
    		return this.setExceptionRequest(request, e);
    	}
        return getAllMemberType;
    }
    /**
     * 会员类型下的全部会员商品
     *
     * @throws Exception
     */
    @RequestMapping("/member/membertype/{type}")
    @ResponseBody
    public Map<String,Object> getMemberType(@PathVariable Long type, HttpServletRequest request){
    	try{
    		List<MemberSale> memberSale= memberSaleService.getMemberSaleListByType(type);
    		this.setJson(true, "true", memberSale);
    	}catch(Exception e){
    		logger.error("MemberController.getMemberType",e);
    		this.setJson(false, "error", null);
    	}
        return json;
    }
 	/**
     * 创建会员订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/memberorder", params = "command=buy")
    @ResponseBody
    public Map<String, Object> createTrxorder(Model model, HttpServletRequest request, HttpServletResponse response, MemberOrder memberOrder) throws Exception {
        try {
            // 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("memberId", request.getParameter("memberId"));// 购买会员
            sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
            sourceMap.put("userid", getLoginUserId(request).toString());
            sourceMap.put("reqchanle", ReqChanle.WEB.toString());
            sourceMap.put("payType", request.getParameter("payType"));
            sourceMap.put("reqIp",WebUtils.getIpAddr(request));//用户ip
            Map<String, Object> res = memberOrderService.addMemberOrder(sourceMap);
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            res.put("balance", userAccount.getBalance().toString());// 账户余额
            MemberOrder memberOrder2 = (MemberOrder) res.get("memOrder");
            if (userAccount.getBalance().compareTo(memberOrder2.getAmount()) < 0) {
                // 还需支付的金额
                res.put("bankAmount", memberOrder2.getAmount().subtract(userAccount.getBalance()).toString());
            }
            setJson(true, "success", res);
        } catch (Exception e) {
            logger.error("MemberController.createTrxorder error", e);
            setJson(false, "", null);
        }
        return json;
    }

    /**
     * 跳转到支付宝银行，企业支付宝，个人未开发
     *
     * @param request
     * @param response
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping("/memberorder/bank")
    public String gotobank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId,
                           @RequestParam(value = "payType", required = true) String payType, RedirectAttributes redirectAttributes) {
        try {

            // 查询订单
            MemberOrder memberOrder = memberOrderService.getMemberOrderById(orderId);
            if (ObjectUtils.isNotNull(memberOrder)) {
                // 先查询账户的金额是否足够支付本次订单的，如果够，直接走扣账流程
                Map<String, String> sourceMap = new HashMap<String, String>();
                sourceMap.put("total_fee", "0.00");// 充值金额，先设置为0.尝试账户余额直接支付
                sourceMap.put("requestId", memberOrder.getRequestId());
                sourceMap.put("userId", getLoginUserId(request).toString());
                sourceMap.put("payType", PayType.ACCOUNT.toString());

                Map<String, String> res = memberHessianService.noMemberOrderComplete(sourceMap);
                // 余额支付成功,直接返回支付成功页面
                if (res.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)) {
                    redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    return "redirect:/front/success?type=member";
                } else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
                    // 不够时，走银行流程，支付的金额为差的金额
                    if (payType.equals(PayType.ALIPAY.toString())) {
                        return gotoalipay(request, response, res);
                    } else if (payType.equals(PayType.WEIXIN.toString())) {
						// 尝试支付余额不足,进行微信扫码支付
					    String wxPayUrl=weixinPayService.getWxpayUrl(memberOrder.getRequestId(),"member");
					    request.setAttribute("wxPayUrl",wxPayUrl );
					    request.setAttribute("requestId",memberOrder.getRequestId());
					    request.setAttribute("orderId",memberOrder.getId());
					    request.setAttribute("type","member");
						return qcCode;
					}
                } else  {// 优惠券错误信息
                	redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    return "redirect:/front/success?type=member";
                }

            }
            return ERROR;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return ERROR;
        }

    }

    /**
     * 跳转到支付宝支付页面
     *
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    public String gotoalipay(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {

        try {
            logger.info("+++gotoalipay sourceMap:" + sourceMap);
            Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String requestId = sourceMap.get("requestId");// 对外支付订单号
            String paygateway = "https://mapi.alipay.com/gateway.do?"; // 支付接口（不可以修改）
            String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
            String sign_type = "MD5";// 文件加密机制（不可以修改）
            String out_trade_no = guidGeneratorService.gainCode("PAY",true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
            String input_charset = OrderConstans.alipay_input_charset; // （不可以修改）
            // partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            // (账户内提取)
            String key = websitemap.get("alipaykey"); // 支付宝安全校验码(账户内提取)
            String body = getLoginUserId(request).toString() + "-" +requestId+"-" +out_trade_no;// 商品描述，推荐格式：商品名称（订单编号：订单编号）
            String total_fee = sourceMap.get("bankAmount");// 订单总价,差价尚须银行支付的金额
            // total_fee = String.valueOf(total_fee); // 订单总价
            String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
            String seller_email = websitemap.get("sellerEmail"); // 卖家支付宝帐户
            // subject 商品名称
            String subject = OrderConstans.companyName +requestId;
            // 扩展信息,存用户id和requestId.重要，必须存
            String extra_common_param = getLoginUserId(request).toString()+","+requestId;

            String show_url = "http://" + request.getContextPath() + "/"; // 商品地址，
            // 根据集成的网站而定
            // 回调的地址
            String path = CommonConstants.contextPath;
            String notify_url = path + "/memberorder/alipaynotify/1"; // 通知接收URL(本地测试时，服务器返回无法测试)
            String return_url = path + "/memberorder/alipaynotify/2"; // 支付完成后跳转返回的网址URL
            // 注意以上两个地址 要用 http://格式的完整路径
            /* 以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销 */

            String defaultbank = request.getParameter("defaultbank");
            String paymethod = "directPay";
            if (StringUtils.isNotEmpty(defaultbank)) {
                paymethod = "bankPay";
            } else {
                defaultbank = null;
                paymethod = "directPay";
            }

            String submiturl = Payment.CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body, total_fee, payment_type, seller_email, subject, notify_url,
                    return_url, paymethod, defaultbank, extra_common_param);
            logger.info("+++ submiturl:" + submiturl);
            return "redirect:" + submiturl;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return ERROR;
        }

    }

    
    /**
     * 支付宝回调
     *
     * @param rtype 异步，2同步
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/memberorder/alipaynotify/{rtype}")
    public String alipayrtn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long rtype, RedirectAttributes redirectAttributes) {
        logger.info("++++ alipaynotify rtype:" + rtype);
        try {
        	Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            String privateKey = websitemap.get("alipaykey"); // 支付宝安全校key
            String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?" + "partner=" + partner + "&notify_id=" + request.getParameter("notify_id");
            String responseTxt = CheckURL.check(alipayNotifyURL);
            Map<String, Object> params = new HashMap<String, Object>();
            // 获得POST 过来参数设置到新的params中
            Map<String, Object> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
                }
                /*
                 * 乱码解决，这段代码在出现乱码时使用,但是不一定能解决所有的问题，所以建议写过滤器实现编码控制。
                 * 如果mysign和sign不相等也可以使用这段代码转化
                 */
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8"); // 乱码解决
                params.put(name, valueStr);
            }
            // 验证加密签名
            String mysign = SignatureHelper.sign(params, privateKey);
            // 最好是在异步做日志动作，可以记录mysign、sign、resposenTXT和其他值，方便以后查询错误。
            if (mysign.equals(request.getParameter("sign")) && responseTxt.equals("true")) {
                /* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
                // 订单编号,系统内的requestId
                String out_trade_no = request.getParameter("out_trade_no");

                String trade_no = request.getParameter("trade_no"); // 支付宝交易号
                logger.info("++++ trade_no:" + trade_no);
                // 总价
                String total_fee = request.getParameter("total_fee");
                // 订单名称
                // String get_subject = new
                // String(request.getParameter("subject").getBytes("ISO-8859-1"),
                // "UTF-8");
                // 订单说明
                String get_body = new String(request.getParameter("body"));
                logger.info("+++ get_body:" + get_body);
                get_body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
                String extra_common_param = request.getParameter("extra_common_param");// 扩展信息存用户的id
                // 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                if (request.getParameter("trade_status").equals("WAIT_BUYER_PAY")) {
                    // 等待买家付款
                    this.sendMessage(request, response, "fail");
                    logger.info("alipaynotify ,WAIT_BUYER_PAY");
                } else if (request.getParameter("trade_status").equals("TRADE_FINISHED") || request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
                    // 校验好状态,在这里可以写入订单的数据处理,

                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", total_fee);
                    sourceMap.put("out_trade_no", out_trade_no);
                    sourceMap.put("requestId", extra_common_param.split(",")[1]);
                    sourceMap.put("userId", extra_common_param.split(",")[0]);
                    sourceMap.put("payType", PayType.ALIPAY.toString());
                    MemberOrder memberOrder= memberOrderService.getMemberOrderByRequestId(extra_common_param.split(",")[1]);
                    if(memberOrder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
                    	if(rtype==1){
                    		sendMessage(request, response, "success");
                    		return null;
                    	}else{
                    		sendMessage(request, response, "success");
                    		redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
                        	return "redirect:/front/success?type=member";
                    	}
                    }
                    // 必须校验支付的金额 TODO
                    Map<String, String> res = memberHessianService.noMemberOrderComplete(sourceMap);
                    logger.info("/memberorder/alipaynotify/" + rtype + " res:" + res);
                    if (ObjectUtils.isNotNull(res)) {

                        redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));

                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                        if (res.get(OrderConstans.BANKCODE).equalsIgnoreCase("success")) {
                            sendMessage(request, response, "success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                        } else {
                            logger.info("alipaynotify fail chongzhi error");
                            sendMessage(request, response, "fail");// 失败
                        }
                    } else {
                        logger.info("alipaynotify fail noTrxTrxOrderComplete null");
                        sendMessage(request, response, "fail");// 失败
                    }

                }
            } else {
                logger.info("alipaynotify fail mysign error");
                redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
                sendMessage(request, response, "fail");
            }
        } catch (Exception e) {
            logger.error("order_alipaynotify_error", e);
            try {
                this.sendMessage(request, response, "fail");
                return ERROR;
            } catch (IOException e1) {

            }
        }
        // 同步时跳转到成功页面
        if (rtype.longValue() == 2) {

            return "redirect:/front/success?type=member";
        }
        return null;
    }


    /**
     * 获取支付宝 密钥
     * @return
     */
    public Map<String,String> getAlipayInfo(){
    	//获得支付配置
    	Map<String,Object> map=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.alipay.toString());
    	JsonParser jsonParser= new JsonParser();
    	//获得详细info
    	JsonObject jsonObject=jsonParser.parse(gson.toJson(map.get(WebSiteProfileType.alipay.toString()))).getAsJsonObject();
    	if(!jsonObject.isJsonNull()){
    		Map<String,String> websitemap = new HashMap<String,String>();
    		websitemap.put("alipaykey", jsonObject.get("alipaykey").getAsString());//支付宝key
    		websitemap.put("alipaypartnerID", jsonObject.get("alipaypartnerID").getAsString());//支付宝合作伙伴id
    		websitemap.put("sellerEmail",jsonObject.get("sellerEmail").getAsString() );//商家
    		return websitemap;
    	}
    	return null;
    }
    
    
}