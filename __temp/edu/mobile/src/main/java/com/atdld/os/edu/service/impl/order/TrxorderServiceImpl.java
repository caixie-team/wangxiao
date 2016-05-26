package com.atdld.os.edu.service.impl.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.atdld.os.common.constants.CommonConstants;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.common.service.SnsHessianService;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.DateUtils;
import com.atdld.os.core.util.EnumUtil;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.core.util.web.WebUtils;
import com.atdld.os.edu.constants.enums.AccountStatus;
import com.atdld.os.edu.constants.enums.AuthStatus;
import com.atdld.os.edu.constants.enums.PayType;
import com.atdld.os.edu.constants.enums.TrxOrderStatus;
import com.atdld.os.edu.constants.enums.WebSiteProfileType;
import com.atdld.os.edu.constants.web.OrderConstans;
import com.atdld.os.edu.dao.order.ShopcartDao;
import com.atdld.os.edu.dao.order.TrxorderDao;
import com.atdld.os.edu.dao.order.TrxorderDetailDao;
import com.atdld.os.edu.entity.coupon.CouponCode;
import com.atdld.os.edu.entity.coupon.CouponCodeDTO;
import com.atdld.os.edu.entity.course.Course;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.order.QueryTrxorder;
import com.atdld.os.edu.entity.order.TrxOrderDTO;
import com.atdld.os.edu.entity.order.TrxReqData;
import com.atdld.os.edu.entity.order.Trxorder;
import com.atdld.os.edu.entity.order.TrxorderDetail;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.service.coupon.CouponCodeService;
import com.atdld.os.edu.service.coupon.CouponService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.order.ShopcartService;
import com.atdld.os.edu.service.order.TrxorderDetailService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.website.WebsiteProfileService;

/**
 * @ClassName com.atdld.os.edu.service.impl.order.TrxorderServiceImpl
 * @description
 * @author :
 * @Create Date : 2014-6-23 上午10:40:33
 */
@Service("trxorderService")
public class TrxorderServiceImpl implements TrxorderService {

	private static Logger logger = LoggerFactory.getLogger(TrxorderServiceImpl.class);
	@Autowired
	private TrxorderDao trxorderDao;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private ShopcartDao shopcartDao;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private SnsHessianService snsHessianService;
	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private TrxorderDetailDao trxorderDetailDao;
	@Autowired
	private CouponCodeService couponCodeService;
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private WebHessianService webHessianService;

	MemCache memCache = MemCache.getInstance();
	
	@Getter@Setter
	private Map<String,Object> userMesg= new HashMap<String,Object>();
	
	
	/**
	 * 添加微站Trxorder
	 * 
	 * @param trxorder
	 *            要添加的Trxorder
	 * @return id
	 */
	public Map<String, Object> addMobileTrxorder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		TrxReqData trxReqData = checkorderparam(sourceMap);
		if (trxReqData == null||trxReqData.getCourseId()==null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		//购买课程
		Course course=courseService.getCourseById(Long.parseLong(sourceMap.get("courseId")));
		if(trxReqData.getType()==2){//购买直播课程 
			if(course.getLiveEndTime().getTime()<new Date().getTime()){//判断结束时间
				result.put("msg", "ending");
				return result;
			}
			int num=trxorderDetailService.queryOrderByLive(trxReqData.getUserId(), course.getId());
			if(num>0){//判断已购买
				result.put("msg", "havebuy");
				return result;
			}
		}
		// 拼装订单数据
		Trxorder trxorder = new Trxorder();
		Map<String,String> trxordrMap=new HashMap<String, String>();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(trxReqData.getUserId());
		trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
		trxorder.setPayType(trxReqData.getPayType().toString());// 支付类型
		trxorder.setReqChannel(trxReqData.getReqChannel());// 请求渠道
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setDescription("无");
		
		trxordrMap.put("userId", trxReqData.getUserId()+"");
		trxordrMap.put("requestId", this.getOrderNum(trxReqData.getUserId()));
		trxordrMap.put("trxStatus", TrxOrderStatus.INIT.toString());
		trxordrMap.put("payType", trxReqData.getPayType().toString());
		trxordrMap.put("reqChannel", trxReqData.getReqChannel());
		trxordrMap.put("reqIp", trxReqData.getReqIp());
		trxordrMap.put("description", "无");
		
		
		if(course.getCurrentprice().doubleValue()==0){
			result.put("msg", "amount");
			return result;
		}
		Date authDate = null;
		// 到期时间
		if (course.getLosetype() == 0) {
			authDate = course.getLoseAbsTime();
		}
		// 按天数计算
		if (course.getLosetype() == 1) {// 
			// 按所写时间推移过期时间
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(course.getLoseTime()));
			authDate = now.getTime();
		}
		// 如果课程已经过期，无法购买
		if (date.getTime() > authDate.getTime()) {
			result.put("msg", "courselosedate");
			return result;
		}
		// 原始金额
		BigDecimal orderAmount = course.getCurrentprice() ;
		
		trxorder.setOrderAmount(orderAmount); // 原始金额
		trxorder.setCouponAmount(new BigDecimal(0));//youhuijine
		trxorder.setAmount(orderAmount);// 实际支付金额
		
		trxordrMap.put("orderAmount",orderAmount.toString());
		trxordrMap.put("couponAmount","0");
		trxordrMap.put("amount",orderAmount.toString());
		// 添加订单
		Long trxorderId=webHessianService.mobileAddTrxorder(trxordrMap);
		trxorder.setId(trxorderId);
		// 添加流水
		Map<String,String> trxorderDetailMap=new HashMap<String, String>();
		
		trxorderDetailMap.put("userId", trxReqData.getUserId()+"");// 用户id
		trxorderDetailMap.put("courseId", course.getId()+"");// 课程id
		trxorderDetailMap.put("trxorderId", trxorder.getId()+"");// 订单id
		
		trxorderDetailMap.put("trxStatus", TrxOrderStatus.INIT.toString());// 流水支付状态
		trxorderDetailMap.put("losetype", course.getLosetype()+"");// 有效期类型
		if(course.getLoseAbsTime()!=null){
			trxorderDetailMap.put("loseAbsTime", course.getLoseAbsTime().getTime()+"");// 订单过期时间段
		}
		
		trxorderDetailMap.put("loseTime", course.getLoseTime()+"");// 订单过期时间点
		
		// 时间段
		if (course.getLosetype() == 0) {
			trxorderDetailMap.put("authTime", course.getLoseAbsTime().getTime()+"");// 订单过期时间点
		}
		// 时间点
		if (course.getLosetype() == 1) {
			// 按所写时间推移过期时间
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(course.getLoseTime()));
			trxorderDetailMap.put("authTime", now.getTime().getTime()+"");// 订单过期时间点
		}
		
		trxorderDetailMap.put("sourcePrice",course.getSourceprice().toString());// 课程原始价格
		trxorderDetailMap.put("currentPrice",course.getCurrentprice().toString());// 课程实际支付价格
		trxorderDetailMap.put("courseName",course.getName());//课程名称
		trxorderDetailMap.put("requestId",trxorder.getRequestId());// 订单请求号
		trxorderDetailMap.put("authStatus",AuthStatus.INIT.toString());
		trxorderDetailMap.put("description","");
		
		// 批量添加流水
		webHessianService.mobileAddBatchTrxorderDetail(trxorderDetailMap);
		result.put("order", trxorder);
		return result;
	}

	/**
	 * 生成订单号 当前用户id+毫秒数
	 * 
	 * @return
	 */
	public String getOrderNum(Long userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * 根据id删除一个Trxorder
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteTrxorderById(Long id) {
		trxorderDao.deleteTrxorderById(id);
	}

	/**
	 * 修改Trxorder
	 * 
	 * @param trxorder
	 *            要修改的Trxorder
	 */
	public void updateTrxorder(Trxorder trxorder) {
		trxorderDao.updateTrxorder(trxorder);
	}

	/**
	 * 根据id获取单个Trxorder对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Trxorder
	 */
	public Trxorder getTrxorderById(Long id) {
		return trxorderDao.getTrxorderById(id);
	}

	/**
	 * 根据条件获取Trxorder列表
	 * 
	 * @param trxorder
	 *            查询条件
	 * @return List<Trxorder>
	 */
	public List<Trxorder> getTrxorderList(Trxorder trxorder) {
		return trxorderDao.getTrxorderList(trxorder);
	}

	/**
	 * 检查该用户是否可以观看该课程
	 * 
	 * @param courseId
	 * @param userId
	 * @return
	 */
	public boolean checkCourseLook(Long courseId, Long userId) {
		//查找售卖方式开关配置
		Map<String,Object> saleMap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sale.toString());
	  	@SuppressWarnings("unchecked")
		Map<String,Object> map=(Map<String, Object>) saleMap.get(WebSiteProfileType.sale.toString());
	  	if(map.get("verifyCourse").equals("OFF")){//单课购买功能关闭
			return false;
		}
	  	Course course = courseService.getCourseById(courseId);
		if (course != null) {
			// 如果课程价格为0则可以观看
			if (course.getIsPay()<= 0) {// 免费的课程可以直接试听
				return true;
			}
		}
		if(userId==null||userId==0){//不再往下判断
			return false;
		}
		// 如果传入的参数为空则不可观看该课程
		if (ObjectUtils.isNull(courseId) || ObjectUtils.isNull(userId)) {
			return false;
		}
		Boolean isOk = (Boolean) memCache.get(MemConstans.USER_CANLOOK + "_" + courseId + "_" + userId);
		if (ObjectUtils.isNotNull(isOk)) {
			return true;
		}
		
		// 查询购买过的课程
		List<TrxorderDetail> trxorderDetailList = trxorderDetailService.getTrxorderDetailListBuy(userId);

		if (ObjectUtils.isNotNull(trxorderDetailList)) {
			List<Long> ids = new ArrayList<Long>();
			for (TrxorderDetail detail : trxorderDetailList) {
				ids.add(detail.getCourseId());
				if (detail.getCourseId().longValue() == courseId.longValue()) {
					memCache.set(MemConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, MemConstans.USER_CANLOOK_TIME);
					return true;
				}
			}
			// 再查询购买的课程中是否是套餐。查询套餐下是否包含该课程
			List<CourseDto> courseDtos = courseService.getCourseListPackage(ids);
			if (ObjectUtils.isNotNull(courseDtos)) {
				for (CourseDto courseDto : courseDtos) {
					if (courseDto.getId().longValue() == courseId.longValue()) {
						memCache.set(MemConstans.USER_CANLOOK + "_" + courseId + "_" + userId, true, MemConstans.USER_CANLOOK_TIME);
						return true;
					}
				}
			}

		}
		return false;
	}

	/**
	 * 订单检查请求参数
	 * 
	 * @param sourceMap
	 * @return
	 */
	public TrxReqData checkorderparam(Map<String, String> sourceMap) {
		TrxReqData reqData = new TrxReqData();
		String typestr = sourceMap.get("type").toString();// 购物类型 1 是课程
		// 下单类型
		if (StringUtils.isNotEmpty(typestr) && Long.valueOf(typestr).longValue() > 0) {
			reqData.setType(Long.valueOf(typestr));
		} else {
			return null;
		}
		
		String courseId = sourceMap.get("courseId"); //微站课程订单用
		if (StringUtils.isNotEmpty(courseId) && Long.valueOf(courseId).longValue() > 0) {
			reqData.setCourseId(Long.parseLong(courseId));
		}
		String couponcode = sourceMap.get("couponcode"); // 优惠卷编码
		if (StringUtils.isNotEmpty(couponcode)) {
			reqData.setCouponCode(couponcode);
		}

		String userid = sourceMap.get("userid");// 用户id
		if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
			reqData.setUserId(Long.valueOf(userid));
		} else {
			return null;
		}

		String reqchanle = sourceMap.get("reqchanle");// 用户id
		if (StringUtils.isNotEmpty(reqchanle)) {
			reqData.setReqChannel(reqchanle);
		} else {
			return null;
		}

		String payType = sourceMap.get("payType");// 支付类型
		if (StringUtils.isNotEmpty(payType)) {
			reqData.setPayType(EnumUtil.transStringToEnum(PayType.class, payType));
		} else {
			return null;
		}

		String reqIp = sourceMap.get("reqIp");// 用户reqIp
		if (StringUtils.isNotEmpty(reqIp)) {
			reqData.setReqIp(reqIp);
		} else {
			reqData.setReqIp("");
		}
		return reqData;
	}

	
	/**
	 * 根据requestId获取Trxorder
	 * 
	 * @param 列表
	 * @return Trxorder
	 */
	public Trxorder getTrxorderByRequestId(String requestId) {
		return trxorderDao.getTrxorderByRequestId(requestId);
	}

	/**
	 * 订单回调,余额支付订单操作 事物开启
	 * 
	 * @param
	 * @return
	 * @throws StaleObjectStateException
	 */
	public Map<String, String> updateCompleteOrder(TrxReqData trxReqData) throws AccountException, StaleObjectStateException {
		logger.info("updateCompleteOrder trxReqData:" + trxReqData);
		Map<String, String> res = new HashMap<String, String>();
		// userAccount要重新查询一次，否则乐观锁版本号异常
		UserAccount userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
		if (!AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
			res.put(OrderConstans.RESCODE, "您的账户已冻结");
			res.put("requestId", trxReqData.getRequestId());
			logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
			return res; // 账户被冻结
		}
		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = trxReqData.getAmount();// 订单所需支付的金额
		BigDecimal balance = userAccount.getBalance();// 账户余额
		BigDecimal useCashAmoun = new BigDecimal(0);
		BigDecimal useVmAmount = new BigDecimal(0);
		if (balance.compareTo(amount) < 0) {// 账户余额不足
			res.put(OrderConstans.RESCODE, "balance");
			res.put("amount", amount.toString());
			res.put("balance", balance.toString());
			res.put("bankAmount", amount.subtract(balance).toString());
			res.put("requestId", trxReqData.getRequestId());
			logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
			return res;
		}
		if (userAccount.getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额,vm足够支付
			useVmAmount = amount;
		} else {// vm不够的时候 再扣除cash的余额
			useVmAmount = userAccount.getVmAmount();//
			useCashAmoun = amount.subtract(useVmAmount);// 需要扣除的cash的金额
		}
		Trxorder trxorder = getTrxorderByRequestId(trxReqData.getRequestId());
		trxorder.setCashAmount(useCashAmoun);
		trxorder.setVmAmount(useVmAmount);
		trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
		trxorder.setPayTime(trxReqData.getCreateTime());
		trxorder.setPayType(trxReqData.getPayType().toString());
		//验证优惠券信息
		if(trxorder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
			List<Course> courses=getTrxCourseByRequestId(trxorder.getRequestId());//订单课程集合
			//验证优惠券编码
	        Map<String,Object> map = couponCodeService.checkCode(couponCodeDTO,"",courses);
	        if(map.get("msg")!="true"){//验证不通过，返回优惠券编码错误信息
	        	res.put(OrderConstans.RESCODE, map.get("msg").toString());
				res.put("amount", amount.toString());
				res.put("balance", balance.toString());
				res.put("bankAmount", amount.subtract(balance).toString());
				res.put("requestId", trxReqData.getRequestId());
				logger.info("updateCompleteOrder no balance,RequestId:" + trxReqData.getRequestId() + ",BankAmount:" + trxReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
				return res;
	        }
		}
		// 扣款
		
		Map<String,String> accountMap=new HashMap<String, String>();
        accountMap.put("id", userAccount.getId()+"");
        accountMap.put("userId", userAccount.getUserId()+"");
        accountMap.put("balance", userAccount.getBalance().toString());// 账户余额
        accountMap.put("forzenAmount", userAccount.getForzenAmount().toString());// 冻结金额
        accountMap.put("cashAmount", userAccount.getCashAmount().toString());// 银行入账
        accountMap.put("vmAmount", userAccount.getVmAmount().toString());// 课程卡入账
        accountMap.put("accountStatus", userAccount.getAccountStatus());// 账户状态
        accountMap.put("version", userAccount.getVersion()+"");// 课程卡入账
        accountMap.put("trxAmount", trxReqData.getAmount().toString());
        accountMap.put("userId", trxReqData.getUserId()+"");
        accountMap.put("trxOrderId", trxReqData.getTrxorderId()+"");
        accountMap.put("requestId", trxReqData.getRequestId());
        accountMap.put("description",  trxReqData.getDescription());
        webHessianService.mobiledebit(accountMap);
        
		// 更新订单的状态
		Map<String,String> trxorderMap =new HashMap<String, String>();
		trxorderMap.put("payTime", trxorder.getPayTime().getTime()+"");
		trxorderMap.put("cashAmount", trxorder.getCashAmount().toString());
		trxorderMap.put("vmAmount", trxorder.getVmAmount().toString());
		trxorderMap.put("trxStatus", TrxOrderStatus.SUCCESS.toString());
		trxorderMap.put("payType",trxorder.getPayType());
		trxorderMap.put("requestId",trxorder.getRequestId());
		Gson gson=new Gson(); 
		System.out.println(gson.toJson(trxorderMap).toString()+"11111111111111111111111111111111111");
		webHessianService.mobileUpdateTrxorderStatusSuccess(trxorderMap);
		//更改优惠券信息
		if(trxorder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(trxorder.getCouponCodeId());
			couponService.updateCouponUserNum(couponCodeDTO.getCouponId());//更新优惠券使用数
			if(couponCodeDTO.getUseType()==2){//非无限次使用权的优惠券编码才更新状态
				CouponCode couponCode=new CouponCode();
				couponCode.setPayTime(new Date());
				couponCode.setStatus(2);//已使用
				couponCode.setTrxorderId(trxorder.getId());//订单id
				couponCode.setRequestId(trxorder.getRequestId());//订单请求号
			    couponCode.setUserId(trxorder.getUserId());//用户id
			    couponCode.setUseTime(new Date());//使用时间
				couponCodeService.updateCouponCode(couponCode);//更新优惠券编码使用后的信息
			}
		}
		try{
			//购买成功添加动态
			addDynamicForBuyCourse(trxReqData.getUserId(),trxorder.getRequestId());
		}catch(Exception e){
			e.printStackTrace();
		}
		res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
		logger.info("updateCompleteOrder trxReqData success");
		return res;
	}

	/**
	 * 订单回调支付成功,预处理查询
	 * 
	 * @param
	 * @return
	 */
	public TrxReqData preQueryCompleteOrder(TrxReqData trxReqData) throws AccountException {
		// 查询用户账户
		UserAccount userAccount = userAccountService.getUserAccountByUserId(trxReqData.getUserId());
		// 账户状态为正常状态
		if (AccountStatus.ACTIVE.toString().equals(userAccount.getAccountStatus().toString())) {
			BigDecimal orderAmount = trxReqData.getAmount();// 订单的金额
			BigDecimal bankAmount = trxReqData.getBankAmount();// 本次支付回调的金额
			BigDecimal balance = userAccount.getBalance().subtract(userAccount.getForzenAmount());// 账户的可用余额
			// 充值的加余额不足支付本次订单的，改为充值操作
			if (bankAmount.add(balance).compareTo(orderAmount) < 0) {
				trxReqData.setRecharge(true);
			} else {
				trxReqData.setRecharge(false);
			}
		} else {
			// 此处冻结状态不管。可以充值进去，但是不允许消费
			// 只充值操作,不够支付改订单的。
			trxReqData.setRecharge(true);
		}
		trxReqData.setUserAccount(userAccount);
		return trxReqData;
	}

	/**
	 * 更新订单状态为成功,网银的回调
	 * 
	 * @param trxorder
	 */
	/*public void updateTrxorderStatusSuccess(Trxorder trxorder) throws StaleObjectStateException {
		// 更新订单表状态
		Long cnt = trxorderDao.updateTrxorderStatusSuccess(trxorder);
		// 更新流水的状态
		TrxorderDetail trxorderDetail = new TrxorderDetail();
		trxorderDetail.setPayTime(trxorder.getPayTime());
		trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
		trxorderDetail.setTrxStatus(trxorder.getTrxStatus());
		trxorderDetail.setRequestId(trxorder.getRequestId());
		Long cnt2 = trxorderDetailDao.updateTrxorderDetailStatusSuccess(trxorderDetail);
		if (cnt.longValue() == 0 || cnt2.longValue() == 0) {
			throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_UPDATE_NONE);
		}
	}
*/
	/**
	 * 添加 课程卡订单信息 流水信息
	 * 
	 * */
	@Override
	public String addCourseCardOrder(Map<String, String> sourceMap) throws Exception {
		String returnMsg;
		
		TrxReqData trxReqData = checkorderparam(sourceMap);
		// 拼装订单数据
		Trxorder trxorder = new Trxorder();
		// 下单时间
		Date date = new Date();
		trxorder.setCreateTime(date);// 下单时间
		trxorder.setUserId(trxReqData.getUserId());
		trxorder.setRequestId(this.getOrderNum(trxReqData.getUserId()));// 交易请求号
		trxorder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 交易装态,成功状态的
		trxorder.setPayType(PayType.CARD.toString());// 支付类型 免费
		trxorder.setReqChannel(trxReqData.getReqChannel());
		trxorder.setReqIp(trxReqData.getReqIp());
		trxorder.setOrderAmount(new BigDecimal(0)); // 原始金额
		trxorder.setCouponAmount(new BigDecimal(0));// 优惠金额
		trxorder.setAmount(new BigDecimal(0));// 实际支付金额
		trxorder.setDescription(new Gson().toJson(sourceMap));
		trxorder.setPayTime(date);// 支付时间
		
		
		Map<String,String> trxordrMap=new HashMap<String, String>();
		trxordrMap.put("userId", trxReqData.getUserId()+"");
		trxordrMap.put("requestId", this.getOrderNum(trxReqData.getUserId()));
		trxordrMap.put("trxStatus", TrxOrderStatus.INIT.toString());
		trxordrMap.put("payType", trxReqData.getPayType().toString());
		trxordrMap.put("reqChannel", trxReqData.getReqChannel());
		trxordrMap.put("reqIp", trxReqData.getReqIp());
		trxordrMap.put("description", "无");
		trxordrMap.put("orderAmount","0");
		trxordrMap.put("couponAmount","0");
		trxordrMap.put("amount","0");
		Long trxorderId=webHessianService.mobileAddTrxorder(trxordrMap);
		trxorder.setId(trxorderId);
		// 添加订单
		returnMsg =trxorder.getId()+","+trxorder.getRequestId();
		String[] courseArray = sourceMap.get("courseIds").split(",");
		// 添加流水表
		List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
		for (int i = 0; i < courseArray.length; i++) {
			Course cou = courseService.getCourseById(new Long(courseArray[i]));
			if (ObjectUtils.isNull(cou)) {
				continue;
			}
			// 创建流水
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			// 用户id
			trxorderDetail.setUserId(trxReqData.getUserId());
			// 课程id
			trxorderDetail.setCourseId(new Long(courseArray[i]));
			// 订单id
			trxorderDetail.setTrxorderId(trxorder.getId());
			// 订单类型
			trxorderDetail.setTrxStatus(AuthStatus.SUCCESS.toString());// 流水支付状态
			// 有效期类型
			trxorderDetail.setLosetype(cou.getLosetype());
			// 订单过期时间段
			trxorderDetail.setLoseAbsTime(cou.getLoseAbsTime());
			// 订单过期时间点
			trxorderDetail.setLoseTime(cou.getLoseTime());
			// 到期时间
			if (cou.getLosetype() == 0) {
				trxorderDetail.setAuthTime(cou.getLoseAbsTime());
			}
			// 按天数计算
			if (cou.getLosetype() == 1) {
				// 按所写时间推移过期时间
				Calendar now = Calendar.getInstance();
				now.setTime(date);
				now.set(Calendar.DATE, now.get(Calendar.DATE) + Integer.valueOf(cou.getLoseTime()));
				trxorderDetail.setAuthTime(now.getTime());
			}

			// 下单时间
			trxorderDetail.setCreateTime(new Date());
			// 课程原始价格
			trxorderDetail.setSourcePrice(cou.getSourceprice());
			// 课程实际支付价格
			trxorderDetail.setCurrentPrice(cou.getCurrentprice());
			// 课程名
			trxorderDetail.setCourseName(cou.getName());
			// 课程状态
			trxorderDetail.setAuthStatus(AuthStatus.SUCCESS.toString());
			trxorderDetail.setPayTime(new Date());
			// 订单请求号
			trxorderDetail.setRequestId(trxorder.getRequestId());
			trxorderDetail.setLastUpdateTime(date);
			trxorderDetail.setDescription("");
			trxorderDetailList.add(trxorderDetail);
		}
		// 批量添加流水
		//trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
		
		
		
		
		return returnMsg;
	}

	/**
	 * 订单分页查询 ,根据条件
	 * 
	 * @param QueryTrxorder
	 * @return List
	 */
	public List<QueryTrxorder> queryOrderPageResult(QueryTrxorder queryTrxorder, PageEntity page) {

		return trxorderDao.queryOrderPageResult(queryTrxorder, page);
	}

	/**
	 * 订单id查询流水的课程集合
	 * @return
	 */
	public List<Course> getTrxCourseByRequestId(String requestId){
		return trxorderDao.getTrxCourseByRequestId(requestId);
	}
	/**
	 * 个人中心订单查询
	 * */
	public List<TrxOrderDTO> queryOrderForWebUc(QueryTrxorder queryTrxorder, PageEntity page) {
		return trxorderDao.queryOrderForUc(queryTrxorder, page);
	}
	public void addDynamicForBuyCourse(Long userId,String requestId) throws Exception{
		//订单课程集合
		List<Course> courses=getTrxCourseByRequestId(requestId);
		if(ObjectUtils.isNotNull(courses)&&courses.size()>0){
			Map<String, String> map = new HashMap<String, String>();
	        map.put("userId",userId + "");//用户id
	        map.put("bizId", courses.get(0).getId() + "");// 学员活动（事件）id 商品id 试卷id
	        map.put("type", 12 + "");//11观看课程 12购买了商品 13 考试
	        map.put("desc", "购买课程");// 动态描述
	        map.put("title", courses.get(0).getName());// 辅助title
	        map.put("assistId",0L+"");// 辅助id
			String content=WebUtils.replaceTagHTML(courses.get(0).getTitle());
			 if (StringUtils.isNotEmpty(content)) {// 回复的内容
				 content=StringUtils.getLength(content, 300);
				 map.put("content", content);
			 }else{
				 map.put("content", "");
			 }
			 map.put("url", CommonConstants.contextPath+"/front/couinfo/"+courses.get(0).getId());//操作url
		    // snsHessianService.addDynamic(map);
		}
	}
	/**
	 * 订单详情
	 * @param id
	 * @return
	 */
	public QueryTrxorder getOrderInfoById(Long id) {
		return trxorderDao.getOrderInfoById(id);
	}
	/**
	 * 网站支付成功的订单数量和销售金额
	 * 
	 * @return orderNum(key) 订单数
	 *         salesNum(key) 销售金额
	 */
	public Map<String, Object> getOrderTotalAndSales() {
		return trxorderDao.getOrderTotalAndSales();
	}

}