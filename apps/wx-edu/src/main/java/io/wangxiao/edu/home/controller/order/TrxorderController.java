package io.wangxiao.edu.home.controller.order;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.util.alipay.CheckURL;
import io.wangxiao.edu.common.util.alipay.Payment;
import io.wangxiao.edu.common.util.alipay.SignatureHelper;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.PayType;
import io.wangxiao.edu.home.constants.enums.ReqChanle;
import io.wangxiao.edu.home.constants.enums.TrxOrderStatus;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.controller.user.UserController;
import io.wangxiao.edu.home.entity.coupon.CouponCode;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.order.*;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.service.coupon.CouponCodeService;
import io.wangxiao.edu.home.service.order.TrxHessianService;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserAccountService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.home.service.weixin.WeixinPayService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

@Controller
public class TrxorderController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(TrxorderController.class);

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryTrxorder")
    public void initBinderQueryTrxorder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryTrxorder.");
    }

    @Autowired
    private TrxorderService trxorderService;

    @Autowired
    private TrxHessianService trxHessianService;
    @Autowired
    private CouponCodeService couponCodeService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TrxorderDetailService trxorderDetailService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private WeixinPayService weixinPayService;
    @Autowired
    GuidGeneratorService guidGeneratorService;
    // 快钱提交页面
    private String kq_jsp = getViewPath("/order/kqbank");
    // 重新支付显示
    private String repay = getViewPath("/order/repay");

    // 课程详情
    private String oDetails = getViewPath("/order/u_order_detail");
    private String orderList = getViewPath("/order/u_order_manage");// 订单列表

    // 易宝支付信息
    private String yeepay = getViewPath("/order/reqpay");
    // 微信支付二维码
    private String qcCode = getViewPath("/shopcart/QRCode");

    @Getter
    @Setter
    private static String encodingCharset = "UTF-8";

    /**
     * 创建购物车订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/order", params = "command=buy")
    @ResponseBody
    public Map<String, Object> createTrxorder(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("trxorder") Trxorder trxorder) throws Exception {
        Map<String, Object> json = null;
        try {
            // 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("type", request.getParameter("type"));// 下单类型
            sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
            sourceMap.put("userid", getLoginUserId(request).toString());
            sourceMap.put("reqchanle", ReqChanle.WEB.toString());
            sourceMap.put("payType", request.getParameter("payType"));// 支付类型
            sourceMap.put("reqIp", WebUtils.getIpAddr(request));// 用户ip
            Map<String, Object> res = trxorderService.addTrxorder(sourceMap);
            if (res.get("msg") != null) {
                json = this.getJsonMap(true, "success", res);
                return json;
            }
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            res.put("balance", userAccount.getBalance().toString());// 账户余额
            Trxorder trxorder2 = (Trxorder) res.get("order");
            if (userAccount.getBalance().compareTo(trxorder2.getAmount()) < 0) {
                // 还需支付的金额
                res.put("bankAmount", trxorder2.getAmount().subtract(userAccount.getBalance()).toString());
            }

            json = this.getJsonMap(true, "success", res);
        } catch (Exception e) {
            logger.error("deleteShopItem error", e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }

    /**
     * 添加免费课程
     *
     * @throws Exception
     */
    @RequestMapping(value = "/order", params = "command=free")
    @ResponseBody
    public Map<String, Object> addFreeTrxorder(Model model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("trxorder") Trxorder trxorder) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> json = null;
        try {
            // 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();

            sourceMap.put("type", request.getParameter("type"));// 下单类型
            sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
            sourceMap.put("userid", getLoginUserId(request).toString());
            sourceMap.put("reqchanle", ReqChanle.WEB.toString());
            sourceMap.put("reqIp", WebUtils.getIpAddr(request));

            Map<String, Object> res = trxorderService.addFreeTrxorder(sourceMap);
            if (ObjectUtils.isNotNull(res.get("order"))) {
                json = this.getJsonMap(true, "success", res);
            } else {
                json = this.getJsonMap(false, "success", res);
            }
        } catch (Exception e) {
            logger.error("/order?command=free", e);
            json = this.getJsonMap(false, "error", e);
            return map;
        }
        return json;
    }

    /**
     * 跳转到支付宝银行，企业支付宝，个人未开发 , 易宝支付
     *
     * @param request
     * @param response
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping("/order/bank")
    public String gotobank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId, @RequestParam(value = "payType", required = true) String payType, RedirectAttributes redirectAttributes) {
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (ObjectUtils.isNotNull(trxorder)) {
                // 先查询账户的金额是否足够支付本次订单的，如果购，直接走扣账流程
                Map<String, String> sourceMap = new HashMap<String, String>();
                sourceMap.put("total_fee", "0.00");// 充值金额，先设置为0.尝试账户余额直接支付
                sourceMap.put("requestId", trxorder.getRequestId());
                sourceMap.put("userId", getLoginUserId(request).toString());
                sourceMap.put("payType", PayType.ACCOUNT.toString());
                Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                // 余额支付成功,直接返回支付成功页面
                if (res.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)) {
                    redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    return "redirect:/front/success";
                } else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
                    // 不够时，走银行流程，支付的金额为差的金额
                    if (payType.equals(PayType.ALIPAY.toString())) {
                        return gotoalipay(request, response, res);
                    } else if (payType.equals(PayType.KUAIQIAN.toString())) {
                        return gotokq(request, response, res);
                    } else if (payType.equals(PayType.YEEPAY.toString())) {
                        return gotoyp(request, response, res);
                    } else if (payType.equals(PayType.WEIXIN.toString())) {
                        // 尝试支付余额不足,进行微信扫码支付
                        String wxPayUrl = weixinPayService.getWxpayUrl(trxorder.getRequestId(), "course");
                        request.setAttribute("wxPayUrl", wxPayUrl);
                        request.setAttribute("requestId", trxorder.getRequestId());
                        request.setAttribute("orderId", trxorder.getId());
                        request.setAttribute("type", "course");
                        return qcCode;
                    }
                } else {// 优惠券错误信息
                    redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    return "redirect:/front/success";
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
     * @param sourceMap
     * @return
     */
    public String gotoalipay(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {

        try {
            logger.info("+++gotoalipay sourceMap:" + sourceMap);
            Map<String, String> websitemap = getAlipayInfo();// 获得支付宝配置
            String requestId = sourceMap.get("requestId");// 订单支付订单号
            String paygateway = "https://mapi.alipay.com/gateway.do?"; // 支付接口（不可以修改）
            String service = "create_direct_pay_by_user";// 快速付款交易服务（不可以修改）
            String sign_type = "MD5";// 文件加密机制（不可以修改）
            String out_trade_no = guidGeneratorService.gainCode("PAY", true);// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
            String input_charset = OrderConstans.alipay_input_charset; // （不可以修改）
            // partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            // (账户内提取)
            String key = websitemap.get("alipaykey"); // 支付宝安全校验码(账户内提取)
            String body = getLoginUserId(request).toString() + "-" + requestId + "-" + out_trade_no;
            // 商品描述，推荐格式：商品名称（订单编号：订单编号）
            String total_fee = sourceMap.get("bankAmount");// 订单总价,差价尚须银行支付的金额
            // total_fee = String.valueOf(total_fee); // 订单总价
            String payment_type = "1";// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
            String seller_email = websitemap.get("sellerEmail"); // 卖家支付宝帐户
            // subject 商品名称
            String subject = OrderConstans.companyName + requestId;
            // 扩展信息,存用户id和requestId.重要，必须存
            String extra_common_param = getLoginUserId(request).toString() + "," + requestId;

            String show_url = "http://" + request.getContextPath() + "/"; // 商品地址，
            // 根据集成的网站而定
            // 回调的地址
            String path = CommonConstants.contextPath;
            String notify_url = path + "/order/alipaynotify/1"; // 通知接收URL(本地测试时，服务器返回无法测试)
            String return_url = path + "/order/alipaynotify/2"; // 支付完成后跳转返回的网址URL
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

            String submiturl = Payment.CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, key, show_url, body, total_fee, payment_type, seller_email, subject, notify_url, return_url, paymethod, defaultbank, extra_common_param);
            logger.info("+++ submiturl:" + submiturl);
            return "redirect:" + submiturl;
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return ERROR;
        }

    }

    /**
     * 跳转到快钱
     *
     * @param request
     * @param response
     * @param sourceMap
     * @return
     */
    public String gotokq(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {
        try {
            logger.info("+++gotokq sourceMap:" + sourceMap);
            KqParamInfo kqParamInfo = new KqParamInfo();
            String requestId = sourceMap.get("requestId");// 对外支付订单号
            kqParamInfo.setOrderId(requestId);
            String money = sourceMap.get("bankAmount");// 订单总价,差价尚须银行支付的金额
            kqParamInfo.setOrderAmount(new BigDecimal(money).multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).toString());

            kqParamInfo.setExt1(getLoginUserId(request).toString());
            String bankId = sourceMap.get("bankId");
            if (StringUtils.isNotEmpty(bankId)) {
                kqParamInfo.setPayType("10");
            }
            kqParamInfo.setRtnUrl(CommonConstants.contextPath + "/kqreturn/1");
            kqParamInfo.setBgUrl(CommonConstants.contextPath + "/kqreturn/2");// bgUrl优先
            String pkipath = getRealPath(request, "/static/kqpfx/");
            kqParamInfo.setSignMsg(createSignMsg(kqParamInfo, pkipath));
            request.setAttribute("kqParamInfo", kqParamInfo);
        } catch (Exception e) {
            logger.error("KQAction.goToKQ", e);
            return ERROR;
        }
        return kq_jsp;
    }

    /**
     * 生成访问快钱加密串，顺序不可变 快钱用
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    private String createSignMsg(KqParamInfo kqParamInfo, String pkipath) throws UnsupportedEncodingException {
        String signMsgVal = "";
        signMsgVal = appendParam(signMsgVal, "inputCharset", kqParamInfo.getInputCharset());
        signMsgVal = appendParam(signMsgVal, "pageUrl", kqParamInfo.getPageUrl());
        signMsgVal = appendParam(signMsgVal, "bgUrl", kqParamInfo.getBgUrl());
        signMsgVal = appendParam(signMsgVal, "version", kqParamInfo.getVersion());
        signMsgVal = appendParam(signMsgVal, "language", kqParamInfo.getLanguage());
        signMsgVal = appendParam(signMsgVal, "signType", kqParamInfo.getSignType());
        signMsgVal = appendParam(signMsgVal, "merchantAcctId", kqParamInfo.getMerchantAcctId());
        signMsgVal = appendParam(signMsgVal, "payerName", kqParamInfo.getPayerName());
        signMsgVal = appendParam(signMsgVal, "payerContactType", kqParamInfo.getPayerContactType());
        signMsgVal = appendParam(signMsgVal, "payerContact", kqParamInfo.getPayerContact());
        signMsgVal = appendParam(signMsgVal, "orderId", kqParamInfo.getOrderId());
        signMsgVal = appendParam(signMsgVal, "orderAmount", kqParamInfo.getOrderAmount());
        signMsgVal = appendParam(signMsgVal, "orderTime", kqParamInfo.getOrderTime());
        signMsgVal = appendParam(signMsgVal, "productName", kqParamInfo.getProductName());
        signMsgVal = appendParam(signMsgVal, "productNum", kqParamInfo.getProductNum());
        signMsgVal = appendParam(signMsgVal, "productId", kqParamInfo.getProductId());
        signMsgVal = appendParam(signMsgVal, "productDesc", kqParamInfo.getProductDesc());
        signMsgVal = appendParam(signMsgVal, "ext1", kqParamInfo.getExt1());
        signMsgVal = appendParam(signMsgVal, "ext2", kqParamInfo.getExt2());
        signMsgVal = appendParam(signMsgVal, "payType", kqParamInfo.getPayType());
        signMsgVal = appendParam(signMsgVal, "bankId", kqParamInfo.getBankId());
        signMsgVal = appendParam(signMsgVal, "redoFlag", kqParamInfo.getRedoFlag());
        signMsgVal = appendParam(signMsgVal, "pid", kqParamInfo.getPid());
		/*
		 * （signType=1 为MD5）PKI加密（signType=4）
		 * KQMD5Util.md5Hex(signMsgVal.getBytes("UTF-8")).toUpperCase();
		 */
        signMsgVal = signMsg(signMsgVal, pkipath);
        logger.info("++signMsgVal:" + signMsgVal);
        return signMsgVal;
    }

    /**
     * 生成返回加密串，顺序不可变
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    public String createMerchantSignMsg(KqParamInfo kqParamInfo) throws UnsupportedEncodingException {
        String merchantSignMsgVal = "";

        merchantSignMsgVal = appendParam(merchantSignMsgVal, "merchantAcctId", kqParamInfo.getMerchantAcctId());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "version", kqParamInfo.getVersion());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "language", kqParamInfo.getLanguage());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "signType", kqParamInfo.getSignType());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "payType", kqParamInfo.getPayType());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankId", kqParamInfo.getBankId());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderId", kqParamInfo.getOrderId());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderTime", kqParamInfo.getOrderTime());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "orderAmount", kqParamInfo.getOrderAmount());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealId", kqParamInfo.getDealId());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "bankDealId", kqParamInfo.getBankDealId());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "dealTime", kqParamInfo.getDealTime());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "payAmount", kqParamInfo.getPayAmount());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "fee", kqParamInfo.getFee());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext1", kqParamInfo.getExt1());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "ext2", kqParamInfo.getExt2());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "payResult", kqParamInfo.getPayResult());
        merchantSignMsgVal = appendParam(merchantSignMsgVal, "errCode", kqParamInfo.getErrCode());

        return merchantSignMsgVal;
    }

    /**
     * 快钱用 将变量值不为空的参数组成字符串
     */
    private String appendParam(String returnStr, String paramId, String paramValue) {
        if (returnStr != null && !returnStr.equals("")) {
            if (paramValue != null && !paramValue.equals("")) {
                returnStr = returnStr + "&" + paramId + "=" + paramValue;
            }
        } else {
            if (paramValue != null && !paramValue.equals("")) {
                returnStr = paramId + "=" + paramValue;
            }
        }
        return returnStr;
    }

    /**
     * 快钱用 请求加密方法
     *
     * @param signMsg
     * @param pkipath
     * @return
     */
    public static String signMsg(String signMsg, String pkipath) {
        String base64 = "";
        try {
            // 密钥仓库
            KeyStore ks = KeyStore.getInstance("PKCS12");
            // 读取密钥仓库
            FileInputStream ksfis = new FileInputStream(pkipath + "/99bill-rsa.pfx");
            BufferedInputStream ksbufin = new BufferedInputStream(ksfis);
            // e2d5guy 是生成pfx证书时的密码
            char[] keyPwd = "1q2w3e4r5t6y".toCharArray();
            ks.load(ksbufin, keyPwd);
            // 从密钥仓库得到私钥
            PrivateKey priK = (PrivateKey) ks.getKey("test-alias", keyPwd);
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(priK);
            signature.update(signMsg.getBytes());
            @Deprecated
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            base64 = encoder.encode(signature.sign());

        } catch (FileNotFoundException e) {
            logger.error("KQAction.signMsg", e);
        } catch (Exception ex) {
            logger.error("KQAction.signMsg", ex);
        }
        return base64;
    }

    // 接受响应验签方法
    public static boolean enCodeByCer(String val, String msg, String pkipath) {
        // 响应验签处理结果
        boolean flag = false;
        try {
            // 获得文件
            InputStream inStream = new FileInputStream(pkipath + "/99bill.cert.rsa.20140728.cer");
            // InputStream inStream =
            // this.getClass().getClassLoader().getResourceAsStream("\\demo\\99bill[1].cert.rsa.20140728.cer");
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
            // 获得公钥
            PublicKey pk = cert.getPublicKey();
            // 签名
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(pk);
            signature.update(val.getBytes());
            // 解码
            @Deprecated
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            flag = signature.verify(decoder.decodeBuffer(msg));
        } catch (Exception e) {
            logger.error("KQAction.enCodeByCer", e);
        }
        return flag;
    }

    /**
     * 支付宝回调
     *
     * @param rtype 异步，2同步
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/order/alipaynotify/{rtype}")
    public String alipayrtn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long rtype, RedirectAttributes redirectAttributes) {
        logger.info("++++ alipaynotify rtype:" + rtype);
        try {
            Map<String, String> websitemap = getAlipayInfo();// 获得支付宝配置
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            String privateKey = websitemap.get("alipaykey"); // 支付宝安全校key
            String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?" + "partner=" + partner + "&notify_id=" + request.getParameter("notify_id");
            String responseTxt = CheckURL.check(alipayNotifyURL);
            Map<String, Object> params = new HashMap<String, Object>();
            // 获得POST 过来参数设置到新的params中
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                String[] values = requestParams.get(name);
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
                    sourceMap.put("userId", extra_common_param.split(",")[0]);
                    sourceMap.put("requestId", extra_common_param.split(",")[1]);
                    sourceMap.put("payType", PayType.ALIPAY.toString());
                    Trxorder trxorder = trxorderService.getTrxorderByRequestId(extra_common_param.split(",")[1]);
                    if (trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {
                        if (rtype == 1) {
                            return null;
                        } else {
                            redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
                            return "redirect:/front/success";
                        }
                    }
                    // 必须校验支付的金额
                    Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                    logger.info("/order/alipaynotify/" + rtype + " res:" + res);
                    if (ObjectUtils.isNotNull(res)) {

                        redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));

                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                        if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
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

            return "redirect:/front/success";
        }
        return null;
    }

    /**
     * 快钱回调函数
     *
     * @param request
     * @param response
     * @param rtype
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/kqtrn/{type}")
    public String kqrtn(HttpServletRequest request, HttpServletResponse response, @PathVariable Long rtype, RedirectAttributes redirectAttributes) {
        logger.info("++kqrtn:" + rtype);
        try {
            // 初始化块钱回调的数据
            KqParamInfo kqParamInfo = initKqReturnParms(request);
            kqParamInfo.setMerchantSignMsgVal(createMerchantSignMsg(kqParamInfo));
            // 初始化结果及地址
            kqParamInfo.setRtnOk(0);// 1代表返回给快钱成功，不会再继续回调
            kqParamInfo.setRtnUrl(CommonConstants.contextPath + "/front/success?msg=订单支付异常");
            // 商家进行数据处理，并跳转会商家显示支付结果的页面
            // /首先进行签名字符串验证
            String pkipath = getRealPath(request, "/static/kqpfx/");
            boolean checkMsg = enCodeByCer(kqParamInfo.getMerchantSignMsgVal(), kqParamInfo.getSignMsg(), pkipath);// PKI加密时验证方式
            if (checkMsg) {// /接着进行支付结果判断
                if (Integer.parseInt(kqParamInfo.getPayResult()) == 10) {
                    // 商户网站逻辑处理，比方更新订单支付状态为成功
                    // 特别注意：只有signMsg.toUpperCase().equals(merchantSignMsg.toUpperCase())，且payResult=10，才表示支付成功！同时将订单金额与提交订单前的订单金额进行对比校验。
                    // 报告给快钱处理结果，并提供将要重定向的地址。
                    // 修改订单支付状态（用于支付的回调）

                    // 校验好状态,在这里可以写入订单的数据处理************

                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", kqParamInfo.getOrderAmount());
                    sourceMap.put("requestId", kqParamInfo.getOrderId());
                    sourceMap.put("userId", kqParamInfo.getExt1());
                    sourceMap.put("payType", PayType.KUAIQIAN.toString());

                    // 必须校验支付的金额 TODO
                    Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                    logger.info("/kqtrn/" + rtype + " res:" + res);
                    if (ObjectUtils.isNotNull(res)) {
                        kqParamInfo.setRtnUrl(CommonConstants.contextPath + "/front/success?msg=" + res.get(OrderConstans.RESMSG));
                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                        if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
                            kqParamInfo.setRtnOk(1);// 1代表返回给快钱成功，不会再继续回调
                            kqParamInfo.setRtnUrl(CommonConstants.contextPath + "/front/success?msg=订单支付成功");
                        } else {
                            logger.info("kqtrn fail chongzhi error");
                        }
                    } else {
                        logger.info("kqtrn fail noTrxTrxOrderComplete null");
                    }

                } else {
                    kqParamInfo.setRtnUrl(CommonConstants.contextPath + "/front/success?msg=订单异常");
                }
            } else {
                kqParamInfo.setRtnUrl(CommonConstants.contextPath + "/front/success?msg=error");
            }
            // 以下报告给快钱处理结果，并提供将要重定向的地址
            response.getOutputStream().print("<result>" + kqParamInfo.getRtnOk() + "</result><redirecturl>" + kqParamInfo.getRtnUrl() + "</redirecturl>");
        } catch (Exception e) {
            logger.info("快钱回调error", e);
        }
        return null;
    }

    /**
     * 取消订单
     *
     * @param request
     * @return
     */
    @RequestMapping("/cancleoder/{orderId}")
    public String cancleOrder(HttpServletRequest request, @PathVariable("orderId") Long orderId) {
        try {
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            trxorder.setTrxStatus("CANCEL");
            trxorderService.updateTrxorder(trxorder);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("取消订单", e);
        }
        return "redirect:/uc/order";
    }

    /**
     * 订单详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/uc/odetail/{orderId}")
    public ModelAndView toOrderDetailse(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @PathVariable("orderId") Long orderId) {
        ModelAndView modelAndView = new ModelAndView();
        QueryTrxorder queryTrxorder = new QueryTrxorder();
        modelAndView.setViewName(oDetails);
        try {
            queryTrxorder.setId(orderId);
            TrxOrderDTO trxOrderDTO = trxorderService.queryOrderForWebUc(queryTrxorder, page).get(0);
            modelAndView.addObject("trxorder", trxOrderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("取消订单", e);
        }
        return modelAndView;
    }

    /**
     * 订单列表
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/uc/order")
    public String orderList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            String trxStatus = (request.getParameter("trxStatus"));
            QueryTrxorder queryTrxorder = new QueryTrxorder();

            queryTrxorder.setUserId(UserController.getLoginUserId(request));
            queryTrxorder.setTrxStatus(trxStatus);
            List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, page);
            // 订单信息
            request.setAttribute("trxorderList", trxorderList);
            request.setAttribute("page", page);
            request.setAttribute("payStatusRe", trxStatus);
        } catch (Exception e) {
            logger.info("orderList---e");
            return this.setExceptionRequest(request, e);
        }
        return orderList;
    }

    /**
     * 获取快钱返回参数
     */
    private KqParamInfo initKqReturnParms(HttpServletRequest request) {
        KqParamInfo kqParamInfo = new KqParamInfo();
        kqParamInfo.setMerchantAcctId(request.getParameter("merchantAcctId").trim());
        kqParamInfo.setVersion(request.getParameter("version").trim());
        kqParamInfo.setLanguage(request.getParameter("language").trim());
        kqParamInfo.setSignType(request.getParameter("signType").trim());
        kqParamInfo.setPayType(request.getParameter("payType").trim());
        kqParamInfo.setBankId(request.getParameter("bankId").trim());
        kqParamInfo.setOrderId(request.getParameter("orderId").trim());
        kqParamInfo.setOrderTime(request.getParameter("orderTime").trim());
        kqParamInfo.setOrderAmount(request.getParameter("orderAmount").trim());
        kqParamInfo.setDealId(request.getParameter("dealId").trim());
        kqParamInfo.setBankDealId(request.getParameter("bankDealId").trim());
        kqParamInfo.setDealTime(request.getParameter("dealTime").trim());
        kqParamInfo.setPayAmount(request.getParameter("payAmount").trim());
        kqParamInfo.setFee(request.getParameter("fee").trim());
        kqParamInfo.setExt1(request.getParameter("ext1").trim());
        kqParamInfo.setExt2(request.getParameter("ext2").trim());
        kqParamInfo.setPayResult(request.getParameter("payResult").trim());
        kqParamInfo.setErrCode(request.getParameter("errCode").trim());
        kqParamInfo.setSignMsg(request.getParameter("signMsg").trim());
        return kqParamInfo;
    }

    /**
     * 重新支付验证
     *
     * @param request
     * @return String
     */
    @RequestMapping("/front/repaycheck/{orderId}")
    @ResponseBody
    public Map<String, Object> repayCheck(HttpServletRequest request, @PathVariable Long orderId) {
        Map<String, Object> json = null;
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (trxorder == null) {// 为空
                json = this.getJsonMap(false, "订单不存在", null);
                return json;
            } else if (!trxorder.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {// 订单状态不为INIT
                json = this.getJsonMap(false, "订单状态异常，请稍后再试", null);
                return json;
            }
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("TrxorderController.repayCheck", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 重新支付显示页面
     *
     * @param request
     * @param orderId
     * @return String
     */
    @RequestMapping("/front/repay/{orderId}")
    public String repay(HttpServletRequest request, @PathVariable Long orderId) {
        try {
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderById(orderId);
            if (ObjectUtils.isNotNull(trxorder) && trxorder.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {// 不为空且订单状态为INIT
                UserAccount userAccount = userAccountService.getUserAccountByUserId(trxorder.getUserId());
                request.setAttribute("trxorder", trxorder);
                request.setAttribute("userAccount", userAccount);
                TrxorderDetail trxorderDetail = new TrxorderDetail();
                trxorderDetail.setRequestId(trxorder.getRequestId());
                // 订单流水 购物车显示
                List<TrxorderDetail> orderList = trxorderDetailService.getTrxorderDetailList(trxorderDetail);
                request.setAttribute("orderList", orderList);
                if (trxorder.getCouponCodeId() > 0) {// 查询优惠券编码信息
                    // 查询优惠券编码
                    CouponCode couponCode = couponCodeService.getCouponCodeById(trxorder.getCouponCodeId());
                    request.setAttribute("couponCode", couponCode.getCouponCode());
                }
                // 需要支付的金额
                request.setAttribute("bankAmount", trxorder.getOrderAmount());

            } else {
                return ERROR;
            }

        } catch (Exception e) {
            return setExceptionRequest(request, e);

        }
        return repay;
    }

    /**
     * 易宝支付信息
     *
     * @param request
     * @param response
     * @param sourceMap
     * @return
     */
    public String gotoyp(HttpServletRequest request, HttpServletResponse response, Map<String, String> sourceMap) {
        try {
            logger.info("+++ gotoyp sourceMap" + sourceMap);
            request.setCharacterEncoding("GBK");
            Map<String, String> websitemap = getYeePayInfo();// 获得支付宝配置
            String keyValue = websitemap.get("keyValue"); // 易宝valueKey
            String p1_MerId = websitemap.get("p1_MerId"); // 易宝P1MerId
            String requestId = sourceMap.get("requestId");// 订单支付订单号
            String bankId = request.getParameter("bankId");

            // 易宝参数定义
            YbParamInfo ybParamInfo = new YbParamInfo();

            ybParamInfo.setP0_Cmd("Buy");// 固定类型 切勿改动
            ybParamInfo.setP1_MerId(p1_MerId);
            ybParamInfo.setPayUrl("https://www.yeepay.com/app-merchant-proxy/node");// 支付地址了
            ybParamInfo.setP2_Order(requestId);
            ybParamInfo.setP3_Amt(sourceMap.get("bankAmount"));
			/* ybParamInfo.setP3_Amt("0.01"); */
            ybParamInfo.setP4_Cur("CNY");// 固定类型 切勿改动
            ybParamInfo.setP8_Url(CommonConstants.contextPath + "/order/ybReturn"); // 回调地址
            ybParamInfo.setPa_MP(getLoginUserId(request).toString() + "," + requestId);
            ybParamInfo.setPr_NeedResponse("1");
            ybParamInfo.setP9_SAF("0");
            if (bankId != null) {
                ybParamInfo.setPd_FrpId(bankId);
            }

			/*
			 * ybParamInfo.setP0_Cmd("Buy"); ybParamInfo.setP1_MerId(p1_MerId);
			 * ybParamInfo
			 * .setPayUrl("https://www.yeepay.com/app-merchant-proxy/node");//
			 * 支付地址了 ybParamInfo.setP4_Cur("CNY");
			 * ybParamInfo.setP2_Order("403504587qwe");
			 * ybParamInfo.setP3_Amt("0.01");
			 * ybParamInfo.setP8_Url("www.baidu.com");
			 * ybParamInfo.setP9_SAF("0"); ybParamInfo.setPr_NeedResponse("1");
			 * ybParamInfo.setPt_Email("邮件地址");
			 */
            // 密钥生成
            String hmac = getReqMd5HmacForOnlinePayment(ybParamInfo.getP0_Cmd(), ybParamInfo.getP1_MerId(), ybParamInfo.getP2_Order(), ybParamInfo.getP3_Amt(), ybParamInfo.getP4_Cur(), ybParamInfo.getP5_Pid(), ybParamInfo.getP6_Pcat(), ybParamInfo.getP7_Pdesc(), ybParamInfo.getP8_Url(), ybParamInfo.getP9_SAF(), ybParamInfo.getPa_MP(), ybParamInfo.getPd_FrpId(), ybParamInfo.getPm_Period(), ybParamInfo.getPn_Unit(), ybParamInfo.getPr_NeedResponse(), ybParamInfo.getPt_UserName(), ybParamInfo.getPt_PostalCode(), ybParamInfo.getPt_Address(), ybParamInfo.getPt_TeleNo(), ybParamInfo.getPt_Mobile(), ybParamInfo.getPt_Email(), keyValue);
            ybParamInfo.setHmac(hmac);
            System.out.println(hmac);
            request.setAttribute("ybParamInfo", ybParamInfo);
        } catch (Exception e) {
            logger.error("gotoyp" + e);
            return ERROR;
        }
        return yeepay;
    }

    /**
     * 易宝回调函数
     *
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("/order/ybReturn")
    public String ybReturn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        logger.info("++ybrtn: 易宝回调");
        try {
            // 易宝返回参数解析
            Map<String, String> yeePayInfo = getYeePayInfo();
            String keyValue = formatString(yeePayInfo.get("keyValue")); // 商家密钥
            String r0_Cmd = formatString(request.getParameter("r0_Cmd")); // 业务类型
            String p1_MerId = formatString(yeePayInfo.get("p1_MerId")); // 商户编号
            String r1_Code = formatString(request.getParameter("r1_Code"));// 支付结果
            String r2_TrxId = formatString(request.getParameter("r2_TrxId"));// 易宝支付交易流水号
            String r3_Amt = formatString(request.getParameter("r3_Amt"));// 支付金额
            String r4_Cur = formatString(request.getParameter("r4_Cur"));// 交易币种
            String r5_Pid = new String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");// 商品名称
            String r6_Order = formatString(request.getParameter("r6_Order"));// 商户订单号
            String r7_Uid = formatString(request.getParameter("r7_Uid"));// 易宝支付会员ID
            String r8_MP = new String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "gbk");// 商户扩展信息
            String r9_BType = formatString(request.getParameter("r9_BType"));// 交易结果返回类型
            String hmac = formatString(request.getParameter("hmac"));// 签名数据
            boolean isOK = false;
            // 校验返回数据包
            isOK = verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
            if (isOK) {
                // 在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
                if (r1_Code.equals("1")) {
                    // 产品通用接口支付成功
                    // 校验好状态,在这里可以写入订单的数据处理,

                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", r3_Amt);
                    sourceMap.put("out_trade_no", r6_Order);
                    sourceMap.put("userId", r8_MP.split(",")[0]);
                    sourceMap.put("requestId", r8_MP.split(",")[1]);
                    sourceMap.put("payType", PayType.YEEPAY.toString());
                    Trxorder trxorder = trxorderService.getTrxorderByRequestId(r8_MP.split(",")[1]);
                    if (trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())) {
                        redirectAttributes.addAttribute(OrderConstans.RESMSG, "支付成功");
                        return "redirect:/front/success";
                    }
                    // 必须校验支付的金额
                    Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                    logger.info("/order/ybReturn res:" + res);
                    if (ObjectUtils.isNotNull(res)) {

                        redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));

                        // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                        if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) && res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
                            sendMessage(request, response, "success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                            return "redirect:/front/success";
                        } else {
                            logger.info("ybrtn fail chongzhi error");
                            sendMessage(request, response, "fail");// 失败
                        }
                    } else {
                        logger.info("ybrtn fail noTrxTrxOrderComplete null");
                        sendMessage(request, response, "fail");// 失败
                    }

                }
            } else {//
                logger.info("ybrtn sign 交易签名被篡改!");
                sendMessage(request, response, "fail");// 失败
            }

        } catch (Exception e) {
            logger.info("易宝回调error", e);
        }
        return null;
    }

    /**
     * 返回校验hmac方法
     *
     * @param hmac     商户编号
     * @param p1_MerId 业务类型
     * @param r0_Cmd   支付结果
     * @param r1_Code  易宝支付交易流水号
     * @param r2_TrxId 支付金额
     * @param r3_Amt   交易币种
     * @param r4_Cur   商品名称
     * @param r5_Pid   商户订单号
     * @param r6_Order 易宝支付会员ID
     * @param r7_Uid   商户扩展信息
     * @param r8_MP    交易结果返回类型
     * @param r9_BType 交易结果返回类型
     * @param keyValue
     * @return
     */
    public static boolean verifyCallback(String hmac, String p1_MerId, String r0_Cmd, String r1_Code, String r2_TrxId, String r3_Amt, String r4_Cur, String r5_Pid, String r6_Order, String r7_Uid, String r8_MP, String r9_BType, String keyValue) {
        StringBuffer sValue = new StringBuffer();
        // 商户编号
        sValue.append(p1_MerId);
        // 业务类型
        sValue.append(r0_Cmd);
        // 支付结果
        sValue.append(r1_Code);
        // 易宝支付交易流水号
        sValue.append(r2_TrxId);
        // 支付金额
        sValue.append(r3_Amt);
        // 交易币种
        sValue.append(r4_Cur);
        // 商品名称
        sValue.append(r5_Pid);
        // 商户订单号
        sValue.append(r6_Order);
        // 易宝支付会员ID
        sValue.append(r7_Uid);
        // 商户扩展信息
        sValue.append(r8_MP);
        // 交易结果返回类型
        sValue.append(r9_BType);
        String sNewString = null;
        sNewString = hmacSign(sValue.toString(), keyValue);

        return hmac.equals(sNewString);
    }

    /**
     * 字符串格式化
     *
     * @param text
     * @return
     */
    String formatString(String text) {
        if (text == null) {
            return "";
        }
        return text;
    }

    /**
     * 生成hmac方法 业务类型
     *
     * @param p0_Cmd          商户编号
     * @param p1_MerId        商户订单号
     * @param p2_Order        支付金额
     * @param p3_Amt          交易币种
     * @param p4_Cur          商品名称
     * @param p5_Pid          商品种类
     * @param p6_Pcat         商品描述
     * @param p7_Pdesc        商户接收支付成功数据的地址
     * @param p8_Url          送货地址
     * @param p9_SAF          商户扩展信息
     * @param pa_MP           银行编码
     * @param pd_FrpId        订单有效期
     * @param pm_Period       订单有效期单位
     * @param pn_Unit         应答机制
     * @param pr_NeedResponse 考生/学员姓名
     * @param pt_UserName     身份证号
     * @param pt_PostalCode   地区
     * @param pt_Address      报考序号
     * @param pt_TeleNo       手机号
     * @param pt_Mobile       邮件地址
     * @param pt_Email        商户密钥
     * @param keyValue
     * @return
     */
    public static String getReqMd5HmacForOnlinePayment(String p0_Cmd, String p1_MerId, String p2_Order, String p3_Amt, String p4_Cur, String p5_Pid, String p6_Pcat, String p7_Pdesc, String p8_Url, String p9_SAF, String pa_MP, String pd_FrpId, String pm_Period, String pn_Unit, String pr_NeedResponse, String pt_UserName, String pt_PostalCode, String pt_Address, String pt_TeleNo, String pt_Mobile, String pt_Email, String keyValue) {
        StringBuffer sValue = new StringBuffer();
        // 业务类型
        sValue.append(p0_Cmd);
        // 商户编号
        if (p1_MerId == null) {
            p1_MerId = "";
        }
        sValue.append(p1_MerId);
        // 商户订单号
        if (p2_Order == null) {
            p2_Order = "";
        }
        sValue.append(p2_Order);
        // 支付金额
        sValue.append(p3_Amt);
        // 交易币种
        sValue.append(p4_Cur);
        if (p5_Pid == null) {
            p5_Pid = "";
        }
        // 商品名称
        sValue.append(p5_Pid);
        // 商品种类
        if (p6_Pcat == null) {
            p6_Pcat = "";
        }
        sValue.append(p6_Pcat);
        // 商品描述
        if (p7_Pdesc == null) {
            p7_Pdesc = "";
        }
        sValue.append(p7_Pdesc);
        // 商户接收支付成功数据的地址
        if (p8_Url == null) {
            p8_Url = "";
        }
        sValue.append(p8_Url);
        // 送货地址
        if (p9_SAF == null) {
            p9_SAF = "";
        }
        sValue.append(p9_SAF);
        // 商户扩展信息
        if (pa_MP == null) {
            pa_MP = "";
        }
        sValue.append(pa_MP);
        // 银行编码
        if (pd_FrpId == null) {
            pd_FrpId = "";
        }
        sValue.append(pd_FrpId);
        // 有效期
        if (pm_Period == null) {
            pm_Period = "";
        }
        sValue.append(pm_Period);
        // 有效期单位
        if (pn_Unit == null) {
            pn_Unit = "";
        }
        sValue.append(pn_Unit);
        // 应答机制
        sValue.append(pr_NeedResponse);
        // 考生/学员姓名
        if (pt_UserName == null) {
            pt_UserName = "";
        }
        sValue.append(pt_UserName);
        // 身份证号
        if (pt_PostalCode == null) {
            pt_PostalCode = "";
        }
        sValue.append(pt_PostalCode);
        // 地区
        if (pt_Address == null) {
            pt_Address = "";
        }
        sValue.append(pt_Address);
        // 报考序号
        if (pt_TeleNo == null) {
            pt_TeleNo = "";
        }
        sValue.append(pt_TeleNo);
        // 手机号
        if (pt_Mobile == null) {
            pt_Mobile = "";
        }
        sValue.append(pt_Mobile);
        // 邮件地址
        if (pt_Email == null) {
            pt_Email = "";
        }
        sValue.append(pt_Email);

        String sNewString = null;

		/* System.out.println(sValue.toString()); */
        sNewString = hmacSign(sValue.toString(), keyValue);
        return (sNewString);
    }

    public static String hmacSign(String aValue, String aKey) {
        byte k_ipad[] = new byte[64];
        byte k_opad[] = new byte[64];
        byte keyb[];
        byte value[];
        try {
            keyb = aKey.getBytes(encodingCharset);
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            keyb = aKey.getBytes();
            value = aValue.getBytes();
        }

        Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
        Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
        for (int i = 0; i < keyb.length; i++) {
            k_ipad[i] = (byte) (keyb[i] ^ 0x36);
            k_opad[i] = (byte) (keyb[i] ^ 0x5c);
        }

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            return null;
        }
        md.update(k_ipad);
        md.update(value);
        byte dg[] = md.digest();
        md.reset();
        md.update(k_opad);
        md.update(dg, 0, 16);
        dg = md.digest();
        return toHex(dg);
    }

    public static String toHex(byte input[]) {
        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

    /**
     * @param args
     * @param key
     * @return
     */
    public static String getHmac(String[] args, String key) {
        if (args == null || args.length == 0) {
            return (null);
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            str.append(args[i]);
        }
        return (hmacSign(str.toString(), key));
    }

    /**
     * @param aValue
     * @return
     */
    public static String digest(String aValue) {
        aValue = aValue.trim();
        byte value[];
        try {
            value = aValue.getBytes(encodingCharset);
        } catch (UnsupportedEncodingException e) {
            value = aValue.getBytes();
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return toHex(md.digest(value));

    }

    /**
     * 重新支付修改订单信息
     *
     * @param request
     * @return Map<String,Object>
     */
    @RequestMapping("/front/repayupdate")
    @ResponseBody
    public Map<String, Object> repay(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String couponCode = request.getParameter("couponcode");// 优惠编码
            String requestId = request.getParameter("requestId");// 订单请求号
            // 查询订单
            Trxorder trxorder = trxorderService.getTrxorderByRequestId(requestId);
            CouponCodeDTO couponCodeDTO = null;
            Map<String, Object> mapCode = null;
            if (couponCode != null && couponCode != "") {// 重新支付使用了优惠券
                // 查询优惠券编码
                couponCodeDTO = couponCodeService.getCouponCodeByCode(couponCode);
                List<Course> courses = trxorderService.getTrxCourseByRequestId(trxorder.getRequestId());// 订单课程集合
                // 验证优惠券编码
                mapCode = couponCodeService.checkCode(couponCodeDTO, "", courses);
                if (mapCode.get("msg") != "true") {// 优惠券编码验证不通过
                    json = this.getJsonMap(false, mapCode.get("msg").toString(), null);
                    return json;
                }
            }
            UserAccount userAccount = userAccountService.getUserAccountByUserId(trxorder.getUserId());
            // 优惠金额
            BigDecimal couponAmount = new BigDecimal(0);
            // 还需要支付的金额
            BigDecimal amount = new BigDecimal(0);
            // 计算金额，更新订单
            if (ObjectUtils.isNotNull(trxorder) && trxorder.getTrxStatus().equals(TrxOrderStatus.INIT.toString())) {// 不为空且订单状态为INIT
                if (trxorder.getCouponCodeId() <= 0 && couponCode.equals("")) {// 订单未使用优惠券,重新支付也未使用优惠券
                    // 不更新
                    json = this.getJsonMap(true, "true", null);
                }
                // 订单使用了优惠券并且未更改优惠券 不更新
                else if (trxorder.getCouponCodeId() > 0 && couponCodeDTO != null && couponCodeDTO.getId().equals(trxorder.getCouponCodeId())) {
                    json = this.getJsonMap(true, "true", null);
                } else {// 更新订单的优惠券列并重新计算价格
                    if (couponCode.equals("")) {// 重新支付无优惠券
                        trxorder.setCouponCodeId(0L);// 优惠券编码id
                        trxorder.setCouponAmount(new BigDecimal(0));// 优惠金额
                        trxorder.setDescription("无");
                        trxorder.setAmount(trxorder.getOrderAmount());// 实际支付金额等于订单金额
                    } else {// 重新支付有优惠券
                        trxorder.setCouponCodeId(couponCodeDTO.getId());// 优惠券编码id

                        if (couponCodeDTO.getType() == 1) {// 折扣券
                            BigDecimal tempPrice = new BigDecimal(Double.parseDouble(mapCode.get("tempPrice").toString()) * 0.1);
                            couponAmount = new BigDecimal(Double.parseDouble(mapCode.get("tempPrice").toString())).subtract(tempPrice.multiply(couponCodeDTO.getAmount()));// 折扣优惠金额
                        } else {// 定额券
                            couponAmount = couponCodeDTO.getAmount();// 定额优惠金额
                        }
                        trxorder.setCouponAmount(couponAmount);// 优惠金额
                        // 实际需要支付的金额,四舍五去取2位
                        amount = trxorder.getOrderAmount().subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
                        trxorder.setAmount(amount);// 实际支付金额
                        if (mapCode.get("limitCourseIds") != null) {// 订单描述，限制的课程id字符串
                            trxorder.setDescription("课程限制：" + mapCode.get("limitCourseIds").toString());
                        } else {
                            trxorder.setDescription("课程限制：所有课程");
                        }
                    }
                    trxorderService.updateTrxorder(trxorder);
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("balance", userAccount.getBalance());
                map.put("amount", trxorder.getAmount());
                if (userAccount.getBalance().compareTo(trxorder.getAmount()) < 0) {
                    // 还需支付的金额
                    map.put("bankAmount", trxorder.getAmount().subtract(userAccount.getBalance()).toString());
                }
                json = this.getJsonMap(true, "true", map);
            } else {
                json = this.getJsonMap(false, "false", null);
            }

        } catch (Exception e) {
            logger.error("TrxorderController.repayupdate", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 获取支付宝 密钥
     *
     * @return
     */
    public Map<String, String> getAlipayInfo() {
        // 获得支付配置
        Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.alipay.toString());
        JsonParser jsonParser = new JsonParser();
        // 获得详细info
        JsonObject jsonObject = jsonParser.parse(gson.toJson(map.get(WebSiteProfileType.alipay.toString()))).getAsJsonObject();
        if (!jsonObject.isJsonNull()) {
            Map<String, String> websitemap = new HashMap<String, String>();
            websitemap.put("alipaykey", jsonObject.get("alipaykey").getAsString());// 支付宝key
            websitemap.put("alipaypartnerID", jsonObject.get("alipaypartnerID").getAsString());// 支付宝合作伙伴id
            websitemap.put("sellerEmail", jsonObject.get("sellerEmail").getAsString());// 商家
            return websitemap;
        }
        return null;
    }

    /**
     * 获取易宝密钥
     *
     * @return
     */
    public Map<String, String> getYeePayInfo() {
        Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.yee.toString());
        JsonParser jsonParser = new JsonParser();
        // 获得详细info
        JsonObject jsonObject = jsonParser.parse(gson.toJson(map.get(WebSiteProfileType.yee.toString()))).getAsJsonObject();
        if (!jsonObject.isJsonNull()) {
            Map<String, String> websitemap = new HashMap<String, String>();
            websitemap.put("p1_MerId", jsonObject.get("p1_MerId").getAsString());// 支付宝key
            websitemap.put("keyValue", jsonObject.get("keyValue").getAsString());// 支付宝合作伙伴id
            return websitemap;
        }
        return null;
    }

}