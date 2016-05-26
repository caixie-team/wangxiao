package com.atdld.os.mobile.order;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.gain.GuidGeneratorService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.common.MobileBaseController;
import com.atdld.os.edu.constants.enums.PayType;
import com.atdld.os.edu.constants.enums.ReqChanle;
import com.atdld.os.edu.constants.enums.TrxOrderStatus;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.order.QueryTrxorder;
import com.atdld.os.edu.entity.order.TrxOrderDTO;
import com.atdld.os.edu.entity.order.Trxorder;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.service.coupon.CouponCodeService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.order.TrxHessianService;
import com.atdld.os.edu.service.order.TrxorderDetailService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.website.WebsiteProfileService;
import com.atdld.os.mobile.order.util.AlipayNotify;
import com.atdld.os.mobile.order.util.AlipaySubmit;
import com.atdld.os.mobile.order.util.HttpUtil;
import com.atdld.os.mobile.order.util.MessageUtil;
import com.atdld.os.mobile.order.util.PackageUtil;
import com.atdld.os.mobile.order.util.XMLParse;

/**
 * Trxorder管理接口 User:  Date: 2014-05-27
 */
@Controller
public class MobileTrxorderController extends MobileBaseController {
    private static final Logger logger = LoggerFactory.getLogger(MobileTrxorderController.class);

    @Autowired
    private TrxorderService trxorderService;

    @Autowired
    private TrxHessianService trxHessianService;
    @Autowired
	private CouponCodeService couponCodeService;
    @Autowired
   	private CourseService courseService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private TrxorderDetailService trxorderDetailService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    GuidGeneratorService guidGeneratorService;
    private String appid;
    private String payKey;
    private String mchid;
    
    //课程详情
	private String course_order_list = getViewPath("/ucenter/course_order_list");// 课程订单列表
	private String live_order_list = getViewPath("/ucenter/live_order_list");// 直播订单列表
	private String course_order_list_ajax = getViewPath("/ucenter/course_order_list_ajax");// 课程订单列表ajax
	private String live_order_list_ajax = getViewPath("/ucenter/live_order_list_ajax");// 直播订单列表ajax
	private String order_submit= getViewPath("/order/order-submit");// 添加课程订单
	private String live_order_submit= getViewPath("/order/live-order-submit");// 添加课程订单
	private String pay_success= getViewPath("/order/pay-success");// 添加课程订单

	@Getter@Setter
	private static String encodingCharset = "UTF-8";
    
	@InitBinder("queryTrxorder")
	public void initBinderQueryTrxorder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTrxorder.");
	}
	
	/**
	 * 课程订单列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/uc/order/course")
	public String courseOrderList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,QueryTrxorder queryTrxorder) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(6);
			this.getPage().setCurrentPage(1);//非ajax从第一页开始
			queryTrxorder.setUserId(getLoginUserId(request));
			queryTrxorder.setSellType("NOLIVE");
			List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, page);
			//订单信息
			request.setAttribute("trxorderList", trxorderList);
			request.setAttribute("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return course_order_list;
	}
	/**
	 * 课程订单列表ajax
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/uc/order/ajax/course")
	public String courseOrderListAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page,QueryTrxorder queryTrxorder) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(6);
			queryTrxorder.setUserId(getLoginUserId(request));
			queryTrxorder.setSellType("NOLIVE");
			List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, page);
			//订单信息
			request.setAttribute("trxorderList", trxorderList);
			request.setAttribute("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return course_order_list_ajax;
	}
	
	/**
	 * 直播订单列表
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/uc/order/live")
	public String liveOrderList(HttpServletRequest request, @ModelAttribute("page") PageEntity page,QueryTrxorder queryTrxorder) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(6);
			queryTrxorder.setUserId(getLoginUserId(request));
			queryTrxorder.setSellType("LIVE");
			List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, page);
			//订单信息
			request.setAttribute("trxorderList", trxorderList);
			request.setAttribute("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return live_order_list;
	}
	/**
	 * 直播订单列表ajax
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/uc/order/ajax/live")
	public String liveOrderListAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page,QueryTrxorder queryTrxorder) {
		try {
			this.setPage(page);
			this.getPage().setPageSize(6);
			queryTrxorder.setUserId(getLoginUserId(request));
			queryTrxorder.setSellType("LIVE");
			List<TrxOrderDTO> trxorderList = trxorderService.queryOrderForWebUc(queryTrxorder, page);
			//订单信息
			request.setAttribute("trxorderList", trxorderList);
			request.setAttribute("page", this.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return live_order_list_ajax;
	}
	
    /**
     * 创建课程订单
     *
     * @throws Exception
     */
    @RequestMapping(value = "/mobile/order", params = "command=buy")
    @ResponseBody
    public Map<String, Object> createMobileTrxorder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 拼装参数
            Map<String, String> sourceMap = new HashMap<String, String>();
            sourceMap.put("type", request.getParameter("type"));// 下单类型
            sourceMap.put("courseId", request.getParameter("courseId"));//课程id
            sourceMap.put("userid", getLoginUserId(request).toString());
            sourceMap.put("reqchanle", ReqChanle.MOBILE.toString());
            sourceMap.put("payType", request.getParameter("payType"));//支付类型
            sourceMap.put("reqIp",WebUtils.getIpAddr(request));//用户ip
            Map<String, Object> res = trxorderService.addMobileTrxorder(sourceMap);
            if(res.get("msg")!=null){
            	setJson(true, "success", res);
            	return json;
            }
            UserAccount userAccount = userAccountService.getUserAccountByUserId(getLoginUserId(request));
            res.put("balance", userAccount.getBalance().toString());// 账户余额
            Trxorder trxorder2 = (Trxorder) res.get("order");
            if (userAccount.getBalance().compareTo(trxorder2.getAmount()) < 0) {
                // 还需支付的金额
                res.put("bankAmount", trxorder2.getAmount().subtract(userAccount.getBalance()).toString());
            }
            
            setJson(true, "success", res);
        } catch (Exception e) {
            logger.error("deleteShopItem error", e);
            setJson(false, "", null);
        }
        return json;
    }

    

    /**
     * 跳转到支付宝银行，企业支付宝
     *
     * @param request
     * @param response
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping("/mobile/order/bank")
    public String gotobank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId,
                           @RequestParam(value = "payType", required = true) String payType, RedirectAttributes redirectAttributes) {
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
                    return pay_success;
                } else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
                    // 不够时，走银行流程，支付的金额为差的金额
                	return "redirect:/to/alipay?requestId="+res.get("requestId")+"&bankAmount="+res.get("bankAmount");
                } else {// 优惠券错误信息
                	redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                    return "redirect:/mobile/success";
                }
            }
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            return ERROR;
        }
        return ERROR;
    }

    /**
     * 支付宝发起支付请求
     *
     * @param request
     * @param response
     * @param sourceMap
     * @return
     */
    @RequestMapping("/to/alipay")
    public void gotoalipay(HttpServletRequest request, HttpServletResponse response) {

        try {
        	//支付类型
    		String payment_type = "1";
            Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
            String input_charset = OrderConstans.alipay_input_charset; // （不可以修改）
           
    		String notify_url = CommonConstants.contextPath+"/mobile/order/alipay/back/"+getLoginUserId(request)+"/"+1;//服务器异步通知页面路径
    		String return_url = CommonConstants.contextPath+"/mobile/order/alipay/back/"+getLoginUserId(request)+"/"+2;//页面跳转同步通知页面路径
    		String merchant_url = CommonConstants.contextPath;//操作中断返回地址,用户付款中途退出返回商户的地址,需http://格式的完整路径，不允许加?id=123这类自定义参数
    		String out_trade_no = request.getParameter("requestId");// 商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
    		logger.info("+++gotoalipay requestId:" + out_trade_no);
    		String subject = "orderno:" + out_trade_no;// subject 商品名称,必填
    		String total_fee = request.getParameter("bankAmount");// 订单总价,差价尚须银行支付的金额
    		
    		
    		//把请求参数打包成数组
    		Map<String, String> sParaTemp = new HashMap<String, String>();
    		sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
            sParaTemp.put("partner",partner);
            sParaTemp.put("seller_id", partner);
            sParaTemp.put("_input_charset", input_charset);
    		sParaTemp.put("payment_type", payment_type);
    		sParaTemp.put("notify_url", notify_url);
    		sParaTemp.put("return_url", return_url);
    		sParaTemp.put("out_trade_no", out_trade_no);
    		sParaTemp.put("subject", subject);
    		sParaTemp.put("total_fee", total_fee);
    		sParaTemp.put("show_url", merchant_url);
    		
    		//建立请求
    		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
    		logger.info("+++ sHtmlText:" + sHtmlText);
    		response.getWriter().println(sHtmlText);

        } catch (Exception e) {
            this.setExceptionRequest(request, e);
        }

    }



   
   /**
    * 支付宝回调
    * @param rtype 1异步，2同步
    * @return
    */
    @SuppressWarnings("unchecked")
    @RequestMapping("/mobile/order/alipay/back/{userId}/{rtype}")
    public String alipayBack(HttpServletRequest request,@PathVariable String userId ,@PathVariable("rtype") Long rtype,HttpServletResponse response, RedirectAttributes redirectAttributes) {
    	if(rtype.longValue()==2){
        	logger.info("++++ alipaynotify :同步");
        }else{
        	logger.info("++++ alipaynotify :异步");
        }
        try {
        	Map<String,String> websitemap=getAlipayInfo();//获得支付宝配置
            String partner = websitemap.get("alipaypartnerID"); // 支付宝合作伙伴id
        	//获取支付宝GET过来反馈信息
            Map<String,String> params = new HashMap<String,String>();
        	Map<String,Object> requestParams = request.getParameterMap();
        	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
        		String name = (String) iter.next();
        		String[] values = (String[]) requestParams.get(name);
        		String valueStr = "";
        		for (int i = 0; i < values.length; i++) {
        			valueStr = (i == values.length - 1) ? valueStr + values[i]
        					: valueStr + values[i] + ",";
        		}
        		//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
        		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
        		params.put(name, valueStr);
        	}
        	//计算得出通知验证结果
        	boolean verify_result = AlipayNotify.verify(params,partner);
        	logger.info("++++ verify_result:" + verify_result);
        	if(verify_result){//验证成功
        		//商户订单号
            	String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            	//支付宝交易号
            	String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            	Trxorder trxorder=trxorderService.getTrxorderByRequestId(out_trade_no);
        		if(trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
        			sendMessage(request, response, "success");
        			if(rtype.longValue()==2){
        				return "redirect:/mobile/order/pay/success";
        			}else{
        				return null;
        			}
    			}
                logger.info("++++ trade_no:" + trade_no);
                // 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                // 校验好状态,在这里可以写入订单的数据处理
                Map<String, String> sourceMap = new HashMap<String, String>();
                sourceMap.put("total_fee", trxorder.getAmount().toString());
                sourceMap.put("out_trade_no", out_trade_no);
                sourceMap.put("requestId", out_trade_no);
                sourceMap.put("payType", PayType.ALIPAY.toString());
                logger.info("++++ ,getLoginUserId(request) :"+getLoginUserId(request));
                // 必须校验支付的金额
                Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                
                
                if (ObjectUtils.isNotNull(res)) {
                    // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                    if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) &&  res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
                        sendMessage(request, response, "success");// 注意一定要返回给支付宝一个成功的信息(不包含HTML脚本语言)
                        // 同步时跳转课程购买成功
                        return "redirect:/mobile/order/pay/success";
                    } else {
                        logger.info("alipaynotify fail chongzhi error");
                        sendMessage(request, response, "fail");// 失败
                    }
                } else {
                    logger.info("alipaynotify fail noTrxTrxOrderComplete null");
                    sendMessage(request, response, "fail");// 失败
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
                return "redirect:/mobile/success";
            } catch (IOException e1) {

            }
        }
        if(rtype.longValue()==2){
        	return "redirect:/mobile/success";
		}else{
			return null;
		}
    }
    @RequestMapping("/mobile/order/pay/success")
    public String alipaySuccess(HttpServletRequest request){
    	return pay_success;
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
    
    
    /**
     * 跳转到微信支付
     *
     * @param request
     * @param response
     * @param orderId
     * @param payType
     * @return
     */
    @RequestMapping("/mobile/weixin/order/bank")
    @ResponseBody
    public Map<String,Object> gotoWeixinbank(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "orderId", required = true) Long orderId) {
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
                	this.setJson(true, "success", null);
                    return json;
                } else if ("balance".equals(res.get(OrderConstans.RESCODE))) {// 余额不足，跳转到银行页面
                	String openId=WebUtils.getCookie(request, CommonConstants.USER_SINGEL_OPENID);
                	if(openId==null){
     	            	this.setJson(false, "订单异常，请稍后再试", null);
     	            	return json;
     	            }
     			    //微信支付生成支付url
     	            String prepay_id=bizpaygetpackage(request,response,trxorder.getId(),openId);
     	            if(prepay_id.equals("")){
     	            	this.setJson(false, "订单异常，请稍后再试", null);
     	            	return json;
     	            }
     	            Map<String,String> map=getWxpayUrl(request,prepay_id);
     	            this.setJson(true, trxorder.getUserId().toString(), map);
                	
                } else {// 优惠券错误信息
                	this.setJson(false, res.get(OrderConstans.RESMSG), null);
                    return json;
            
                }
            }
        } catch (Exception e) {
            this.setExceptionRequest(request, e);
            this.setJson(false,"error", null);
        }
        return json;
    }
    
    /**
     * 微信支付请求
     *
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    public Map<String,String> getWxpayUrl(HttpServletRequest request,String prepay_id) {
	       
	    	String timeStanmp=PackageUtil.getTimeStamp();//timestamp 时间点
	    	String nonceStr=PackageUtil.getNonceStr();//noncestr 随机字符串
	    	SortedMap<String, String> signParams = new TreeMap<String, String>();
	    	signParams.put("appId",appid);//qppid
	    	signParams.put("timeStamp",timeStanmp);//时间戳
	    	signParams.put("nonceStr",nonceStr);//随机字符串
	    	signParams.put("package","prepay_id="+prepay_id);//预支付订单id
	    	signParams.put("signType","MD5");//微信签名方式
	    	//生成微信签名
			String sign=PackageUtil.createSign(signParams,payKey);
			System.out.println(sign);
			signParams.put("paySign",sign);//paySign
			return signParams;
    }
    
    /**
     * 获取微信 密钥
     * @return
     */
    public Map<String,String> getWxpayInfo(){
    	//获得微信支付配置
    	Map<String,Object> weixinMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.weixin.toString());
    	//获得详细info
    	@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) weixinMap.get(WebSiteProfileType.weixin.toString());
    	if(map!=null){
    		Map<String,String> websitemap = new HashMap<String,String>();
    		this.appid=map.get("wxAppID").toString();//appId
    		this.payKey=map.get("wxPayKey").toString();//支付密钥
    		this.mchid=map.get("wxMchId").toString();//商户id
    		return websitemap;
    	}
    	return null;
    }
    
    /**
     * 微信统一支付获取商品package
     *
     * @param request
     * @param response
     * @param orderId
     * @return
     */
    public String bizpaygetpackage(HttpServletRequest request, HttpServletResponse response,Long id,String openId) {
    	String prepay_id="";
        try {
        	getWxpayInfo();
    		//请求参数包
    		SortedMap<String, String> packageParams=createPackage(id,openId,request);
    		//sign签名	
    		String sign = PackageUtil.createSign(packageParams,payKey);
    		//统一支付请求参数xml
    		String xmlPackage=XMLParse.generateXmlPackage(packageParams,sign);
    		System.out.println(xmlPackage);
    		//预支付订单创建结果
    		String resultXmlStr=HttpUtil.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlPackage);
    		Map<String, String> resultMap=MessageUtil.parseStringXml(resultXmlStr);
    		String returnCode=resultMap.get("return_code");//通信标识返回状态码
    		String returnMsg=resultMap.get("return_msg");//返回状态消息
			if(returnCode.equalsIgnoreCase("FAIL")){
				logger.info("return_code-----fail:"+returnMsg);
			}else{
				String result_code=resultMap.get("result_code");//业务结果
				if(result_code.equalsIgnoreCase("FAIL")){
					logger.info("result_code-----fail:err_code"+resultMap.get("err_code")+"/n"+"err_code_des"+resultMap.get("result_code"));
					logger.info("err_code:"+resultMap.get("err_code"));
					logger.info("err_code_des:"+resultMap.get("err_code_des"));
				}else{
					prepay_id=resultMap.get("prepay_id");
				}
			}
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prepay_id;
    }
    
    /**
     * 微信预支付订单请求参数
     * @param requestMap
     * @param request
     * @return
     */
    public SortedMap<String, String> createPackage(Long id,String openId,HttpServletRequest request){
    	//订单信息
	    Trxorder trxOrder= trxorderService.getTrxorderById(id);
    	String notify_url = CommonConstants.contextPath + "/mobile/order/wxpaynotify";//微信支付通知url
    	String noncestr = PackageUtil.getNonceStr();//随机字符串
    	//设置package订单参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		
		//必须字段
		packageParams.put("appid", appid);  //appid
		packageParams.put("mch_id", mchid);  //商户号id
		packageParams.put("openid", openId);  //用户微信id
		packageParams.put("body", "orderno:" + trxOrder.getRequestId()); //商品描述 
		packageParams.put("notify_url", notify_url); //通知地址  
		packageParams.put("trade_type", "JSAPI"); //交易类型 JSAPI、NATIVE、APP
		packageParams.put("out_trade_no", trxOrder.getRequestId()); //商户订单号  
		double money=trxOrder.getAmount().doubleValue()*100;
		packageParams.put("total_fee", Integer.toString(new BigDecimal(money).intValue())); //订单总金额，单位为分，不能带小数点
		packageParams.put("spbill_create_ip", trxOrder.getReqIp()); //订单生成的机器IP，指用户浏览器端IP
		packageParams.put("nonce_str",noncestr);
		//非必需字段
		//附加数据   订单编号+userId
		packageParams.put("attach",trxOrder.getRequestId()+","+trxOrder.getUserId()); 
		Date date=new Date();
		SimpleDateFormat s=new SimpleDateFormat("yyyyMMddHHmmss");
		packageParams.put("time_start",s.format(date)); //订单生成时间，格式为yyyyMMddHHmmss
				
    	//订单3天后失效
		Calendar c=Calendar.getInstance();
		c.setTime(date);
    	c.set(Calendar.DATE, c.get(Calendar.DATE) +3);	
		packageParams.put("time_expire",s.format(c.getTime())); //订单失效时间，格式为yyyyMMddHHmmss
		
		return packageParams;
    }
    /**
     * 验证回调签名
     * @return
     */
    public boolean checkSignature(Map<String, String> requestMap){
    	SortedMap<String, String> signParams = new TreeMap<String, String>();
    	String signature=requestMap.get("sign");
    	if(signature==null||signature.equals("")){
    		return false;
    	}
    	requestMap.remove("sign");
    	for(Entry<String, String> entry: requestMap.entrySet()){//循环放入树Map
    		signParams.put(entry.getKey(),entry.getValue() );
    	}
    	try {
			String sign=PackageUtil.createSign(signParams,payKey);
			if(signature.equalsIgnoreCase(sign)){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	return false;
    }
    /**
     * 微信支付通知
     *
     * @param rtype 异步，2同步
     * @return
     */
    @RequestMapping("/mobile/order/wxpaynotify")
    public void wxpayrtn(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
        	// xml请求解析
        	Map<String, String> requestMap = MessageUtil.parseXml(request);
        	//验证签名，参数签名及xml签名、通信成功
        	if(checkSignature(requestMap)&&requestMap.get("return_code").equals("SUCCESS")){
        		if(requestMap.get("result_code").equals("SUCCESS")){
	                /* 可以在不同状态下获取订单信息，操作商户数据库使数据同步 */
	                // 订单编号,非requestId，给微信的payId
	                String out_trade_no = requestMap.get("out_trade_no");
	                logger.info("++++ out_trade_no:" + out_trade_no);
	                // 微信交易号
	                String transaction_id = requestMap.get("transaction_id");
	                logger.info("++++ transaction_id:" + transaction_id);
	                // 总价 微信是分为单位，需要转化为元
	                String total_fee = requestMap.get("total_fee");
	                total_fee=Double.toString((new BigDecimal(total_fee).doubleValue()/100));
	                //附加数据  用户id，订单requestId,订单类型
	                String attach = requestMap.get("attach");
                    // 校验好状态,在这里可以写入订单的数据处理,
                    Map<String, String> sourceMap = new HashMap<String, String>();
                    sourceMap.put("total_fee", total_fee);
                    sourceMap.put("out_trade_no", out_trade_no);
                    sourceMap.put("requestId", attach.split(",")[0]);
                    sourceMap.put("payType", PayType.WEIXIN.toString());
                    //判断该笔订单是否在商户网站中已经做过处理
        			Trxorder trxorder=trxorderService.getTrxorderByRequestId(attach.split(",")[0]);
        			if(trxorder.getTrxStatus().equals(TrxOrderStatus.SUCCESS.toString())){
        				sendXmlMessage(request, response, "SUCCESS");
        			}else{
        				 // 必须校验支付的金额
                        Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);
                        if (ObjectUtils.isNotNull(res)) {
                            redirectAttributes.addAttribute(OrderConstans.RESMSG, res.get(OrderConstans.RESMSG));
                            // 根据返回的结果，订单支付成功或者充值成功时，代表成功
                            if (StringUtils.isNotEmpty(res.get(OrderConstans.BANKCODE)) &&  res.get(OrderConstans.BANKCODE).equalsIgnoreCase(OrderConstans.SUCCESS)) {
                            	sendXmlMessage(request, response, "SUCCESS");// 注意一定要返回给微信一个成功的信息(不包含HTML脚本语言)
                            } else {
                                logger.info("weixin fail chongzhi error");
                                sendXmlMessage(request, response, "FAIL");
                            }
                        } else {
                            logger.info("alipaynotify fail noTrxTrxOrderComplete null");
                            sendXmlMessage(request, response, "FAIL");
                        }
        			}
	        	}else{
	    			logger.info("wxpaynotify fail result_code error");
	    			logger.info("err_code:"+requestMap.get("return_code"));
	    			logger.info("err_code_des:"+requestMap.get("err_code_des"));
	    			sendXmlMessage(request, response, "FAIL");
	    		}
        	} else {
                logger.info("wxpaynotify fail AppSignature or return_code error");
                redirectAttributes.addAttribute(OrderConstans.RESMSG, "请求异常");
                sendXmlMessage(request, response, "FAIL");
            }
        } catch (Exception e) {
            logger.error("order_wxpaynotify_error", e);
            try {
                this.sendXmlMessage(request, response, "FAIL");
            } catch (IOException e1) {

            }
        }
    }
    public void sendXmlMessage(HttpServletRequest request, HttpServletResponse response, String content)throws IOException
    {
	    try
	    {
	    	System.out.println("--------------------------------------------"+content);
	    	String contentXml="<xml><return_code><![CDATA["+content+"]]></return_code></xml>";
	    	OutputStream os = response.getOutputStream();
	        BufferedWriter resBr = new BufferedWriter(new OutputStreamWriter(os));
	        resBr.write(contentXml);
	        resBr.flush();
	        resBr.close();
	    } catch (Exception e) {
	      logger.error("sendMessage", e);
	    }
    }

    
    
    /**
	 * 确认订单页面
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/order/add")
	public String addCourseOrder(HttpServletRequest request) {
		try {
			String openId=WebUtils.getCookie(request, CommonConstants.USER_SINGEL_OPENID);
			logger.info("+++openId:" + openId);
		    if(openId!=null&&!openId.equals("")){//判断是否是微信
		    	request.setAttribute("isWeixin", "isWeixin");
            }
			Long courseId=Long.parseLong(request.getParameter("courseId"));
			CourseDto course=courseService.getCourseInfoById(courseId);
			//订单信息
			request.setAttribute("course", course);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return order_submit;
	}
	
	/**
	 * 直播确认订单页面
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/mobile/live/order/add")
	public String addLiveOrder(HttpServletRequest request) {
		try {
			String openId=WebUtils.getCookie(request, CommonConstants.USER_SINGEL_OPENID);
			logger.info("+++openId:" + openId);
		    if(openId!=null&&!openId.equals("")){//判断是否是微信
		    	request.setAttribute("isWeixin", "isWeixin");
            }
			Long courseId=Long.parseLong(request.getParameter("courseId"));
			CourseDto course=courseService.getCourseInfoById(courseId);
			Date date=new Date();
         	//直播中设置距离结束分钟
         	//未开始的收费直播设置开始天数时分
 			Map<String,String> begin=daysBetween(date,course.getLiveBeginTime());
 			course.setBegin(begin);
			//订单信息
			request.setAttribute("live", course);
		} catch (Exception e) {
			e.printStackTrace();
			return this.setExceptionRequest(request, e);
		}
		return live_order_submit;
	}
	
	/**   
     * 计算两个日期之间相差的天时分
     * @param bdate  较大的时间  
     * @return 相差天数  时分
     * @throws ParseException   
     */     
    public static Map<String,String> daysBetween(Date date1,Date date2) 
    {  
    	Map<String,String> map=new HashMap<String, String>();
    	Calendar cal = Calendar.getInstance(); 
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();  
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();  
        long between_days=(time2-time1)/(1000*3600*24);
        //相差时
        long hourMins=(time2-time1)%(1000*3600*24);
        long between_hours=hourMins/(1000*3600);
        //相差分
        long between_mins=0L;
        if(between_hours>0){
        	long mins=hourMins%(1000*3600);
        	between_mins=mins/(1000*60);
        }else{
        	between_mins=hourMins/(1000*60);
        }
        map.put("day", between_days+"");
        map.put("hour", between_hours+"");
        map.put("min", between_mins+"");
		return map;
    }  
}