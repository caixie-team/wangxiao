package io.wangxiao.edu.home.service.impl.member;

import com.google.gson.Gson;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.EnumUtil;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.exception.AccountException;
import io.wangxiao.edu.common.exception.StaleObjectStateException;
import io.wangxiao.edu.home.constants.enums.AccountBizType;
import io.wangxiao.edu.home.constants.enums.AccountHistoryType;
import io.wangxiao.edu.home.constants.enums.PayType;
import io.wangxiao.edu.home.constants.enums.TrxOrderStatus;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.dao.member.MemberOrderDao;
import io.wangxiao.edu.home.entity.coupon.CouponCode;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.member.*;
import io.wangxiao.edu.home.entity.user.UserAccount;
import io.wangxiao.edu.home.service.coupon.CouponCodeService;
import io.wangxiao.edu.home.service.coupon.CouponService;
import io.wangxiao.edu.home.service.impl.order.TrxorderServiceImpl;
import io.wangxiao.edu.home.service.member.MemberOrderService;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.member.MemberSaleService;
import io.wangxiao.edu.home.service.user.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * MemberOrder管理接口
 */
@Service("memberOrderService")
public class MemberOrderServiceImpl implements MemberOrderService{
	private static Logger logger = LoggerFactory.getLogger(TrxorderServiceImpl.class);
 	@Autowired
    private MemberOrderDao memberOrderDao;
 	@Autowired
    private MemberSaleService memberSaleService;
 	@Autowired
    private MemberRecordService memberRecordService;
 	@Autowired
    private CouponCodeService couponCodeService;
 	@Autowired
    private CouponService couponService;
 	@Autowired
	private UserAccountService userAccountService;
 	
	/** 
	 * 添加MemberOrder
	 * @param MemberOrder
	 *            要添加的MemberOrder
	 * @return id
	 */
	public Map<String, Object> addMemberOrder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		MemberOrderReqData memberOrderReqData = checkMemberOrderParam(sourceMap);
		if (memberOrderReqData == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		if (ObjectUtils.isNull(memberOrderReqData.getMemberId())) {
			result.put("msg", "param");
			return result;
		}
		//开通的会员类型
		MemberSale memberType= memberSaleService.getMemberSaleById(memberOrderReqData.getMemberId());
		// 拼装订单数据
		MemberOrder memberOrder = new MemberOrder();
		// 下单时间
		Date date = new Date();
		memberOrder.setCreateTime(date);// 下单时间
		memberOrder.setUserId(memberOrderReqData.getUserId());
		memberOrder.setRequestId(this.getMemberOrderNum(memberOrderReqData.getUserId()));// 交易请求号
		memberOrder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
		memberOrder.setPayType(memberOrderReqData.getPayType().toString());// 支付类型
		memberOrder.setReqChannel(memberOrderReqData.getReqChannel());// 请求渠道
		memberOrder.setReqIp(memberOrderReqData.getReqIp());
		memberOrder.setDescription(new Gson().toJson(sourceMap));
		memberOrder.setMemberId(memberType.getId());//会员商品id
		memberOrder.setMemberDays(memberType.getDays());//开通天数
		memberOrder.setMemberType(memberType.getType());//会员类型
		// 原始金额
		BigDecimal orderAmount = memberType.getPrice();
		memberOrder.setOrderAmount(orderAmount); // 原始金额
		memberOrder.setCouponCodeId(0L);
		// 是否使用优惠券，判断优惠券是否在规则内
		// 优惠金额
		BigDecimal couponAmount = new BigDecimal(0);
		if (sourceMap.get("couponcode") != null && sourceMap.get("couponcode") != "") {// 使用优惠券
			// 查询优惠券编码
			CouponCodeDTO couponCodeDTO= couponCodeService.getCouponCodeByCode(sourceMap.get("couponcode"));
	        //验证优惠券编码
	        Map<String,Object> map = couponCodeService.checkCode(couponCodeDTO,"memberCode",null);
			if(map.get("msg")=="true"){//验证通过
				memberOrder.setCouponCodeId(couponCodeDTO.getId());//优惠券编码id
				//会员定额券
				couponAmount=couponCodeDTO.getAmount();//定额优惠金额
			}
		}
		
		memberOrder.setCouponAmount(couponAmount);//优惠金额
		// 实际需要支付的金额,四舍五去取2位
		BigDecimal amount = orderAmount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
		if (amount.doubleValue() <= 0) {
			result.put("msg", "amount");
			return result;
		}
		memberOrder.setAmount(amount);// 实际支付金额
		// 添加订单
		memberOrderDao.addMemberOrder(memberOrder);
		result.put("memOrder", memberOrder);
		return result;
	}
 	/**
	 * 订单检查请求参数
	 * 
	 * @param sourceMap
	 * @return
	 */
	public MemberOrderReqData checkMemberOrderParam(Map<String, String> sourceMap) {
		MemberOrderReqData reqData = new MemberOrderReqData();
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
		String memberid = sourceMap.get("memberId");// 用户id
		if (StringUtils.isNotEmpty(memberid)) {
			reqData.setMemberId(Long.valueOf(memberid));
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
	 * 生成订单号 当前用户id+毫秒数
	 * 
	 * @return
	 */
	public String getMemberOrderNum(Long userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}
    /**
     * 添加MemberOrder
     * @param memberOrder 要添加的MemberOrder
     * @return id
     */
    public java.lang.Long addMemberOrder(MemberOrder memberOrder){
    	return memberOrderDao.addMemberOrder(memberOrder);
    }

    /**
     * 根据id删除一个MemberOrder
     * @param id 要删除的id
     */
    public void deleteMemberOrderById(Long id){
    	 memberOrderDao.deleteMemberOrderById(id);
    }

    /**
     * 修改MemberOrder
     * @param memberOrder 要修改的MemberOrder
     */
    public void updateMemberOrder(MemberOrder memberOrder){
     	memberOrderDao.updateMemberOrder(memberOrder);
    }

    /**
     * 根据id获取单个MemberOrder对象
     * @param id 要查询的id
     * @return MemberOrder
     */
    public MemberOrder getMemberOrderById(Long id){
    	return memberOrderDao.getMemberOrderById( id);
    }
    /**
     * 根据id获取单个MemberOrderDTO对象
     * @param id 要查询的id
     * @return MemberOrderDTO
     */
    public MemberOrderDTO getMemberOrderDTOById(Long id){
    	return memberOrderDao.getMemberOrderDTOById( id);
    }
    /**
     * 根据requestId获取单个MemberOrder对象
     * @param id
     * @return
     */
    public MemberOrder getMemberOrderByRequestId(String requestId){
    	return memberOrderDao.getMemberOrderByRequestId(requestId);
    }
    
    /**
     * 根据条件获取MemberOrder列表
     * @param memberOrder 查询条件
     * @return List<MemberOrder>
     */
    public List<MemberOrder> getMemberOrderList(MemberOrder memberOrder){
    	return memberOrderDao.getMemberOrderList(memberOrder);
    }

    /**
     * 会员订单列表
     * @param queryMemberOrder
     * @param page
     * @return
     */
	public List<MemberOrderDTO> getMemberOrderPage(QueryMemberOrder queryMemberOrder, PageEntity page){
		return memberOrderDao.getMemberOrderPage(queryMemberOrder,page);
	}
	/**
	 * 订单回调,余额支付订单操作 事物开启
	 * 
	 * @param
	 * @return
	 * @throws StaleObjectStateException
	 */
	public Map<String, String> updateCompleteMemberOrder(MemberOrderReqData memberReqData) throws AccountException, StaleObjectStateException {
		logger.info("updateCompleteOrder memberReqData:" + memberReqData);
		Map<String, String> res = new HashMap<String, String>();
		// userAccount要重新查询一次，否则乐观锁版本号异常
		UserAccount userAccount = userAccountService.getUserAccountByUserId(memberReqData.getUserId());
		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = memberReqData.getAmount();// 订单所需支付的金额
		BigDecimal balance = userAccount.getBalance();// 账户余额
		BigDecimal useCashAmoun = new BigDecimal(0);
		BigDecimal useVmAmount = new BigDecimal(0);
		if (balance.compareTo(amount) < 0) {// 账户余额不足
			res.put(OrderConstans.RESCODE, "balance");
			res.put("amount", amount.toString());
			res.put("balance", balance.toString());
			res.put("bankAmount", amount.subtract(balance).toString());
			res.put("requestId", memberReqData.getRequestId());
			logger.info("updateCompleteOrder no balance,RequestId:" + memberReqData.getRequestId() + ",BankAmount:" + memberReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
			return res;
		}
		if (userAccount.getVmAmount().compareTo(amount) >= 0) {// vm余额大于等于扣款的金额,vm足够支付
			useVmAmount = amount;
		} else {// vm不够的时候 再扣除cash的余额
			useVmAmount = userAccount.getVmAmount();//
			useCashAmoun = amount.subtract(useVmAmount);// 需要扣除的cash的金额
		}
		MemberOrder memberOrder = getMemberOrderByRequestId(memberReqData.getRequestId());
		memberOrder.setCashAmount(useCashAmoun);
		memberOrder.setVmAmount(useVmAmount);
		memberOrder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
		memberOrder.setPayTime(memberReqData.getCreateTime());
		memberOrder.setPayType(memberReqData.getPayType().toString());
		//验证优惠券
		if(memberOrder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(memberOrder.getCouponCodeId());
			//验证优惠券编码
	        Map<String,Object> map = couponCodeService.checkCode(couponCodeDTO,"memberCode",null);
	        if(map.get("msg")!="true"){//验证不通过，返回优惠券编码异常信息
	        	res.put(OrderConstans.RESCODE, map.get("msg").toString());
				res.put("amount", amount.toString());
				res.put("balance", balance.toString());
				res.put("bankAmount", amount.subtract(balance).toString());
				res.put("requestId", memberReqData.getRequestId());
				logger.info("updateCompleteOrder no balance,RequestId:" + memberReqData.getRequestId() + ",BankAmount:" + memberReqData.getBankAmount() + ",Balance:" + userAccount.getBalance());
				return res;
	        	//throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_COUPON_USE);
	        }
		}
		// 扣款
		userAccountService.debit(userAccount, memberReqData.getAmount(), AccountHistoryType.SALES, memberReqData.getUserId(), memberReqData.getMemberOrderId(), memberReqData.getRequestId(), memberReqData.getCreateTime(), memberReqData.getDescription(), true, AccountBizType.MEMBER);
		// 更新订单的状态
		updateMemberOrderStatusSuccess(memberOrder);
		//更改优惠券信息
		if(memberOrder.getCouponCodeId()>0){//订单使用了优惠券
			CouponCodeDTO couponCodeDTO=couponCodeService.getCouponCodeDTO(memberOrder.getCouponCodeId());
	        couponService.updateCouponUserNum(couponCodeDTO.getCouponId());//更新优惠券使用数
			if(couponCodeDTO.getUseType()==2){//次数限制正常的优惠券改变优惠券编码状态
				//改变优惠券编码信息
				CouponCode couponCode=new CouponCode();
				couponCode.setId(couponCodeDTO.getId());
				couponCode.setStatus(2);//已使用
				couponCode.setTrxorderId(memberOrder.getId());//订单id
				couponCode.setRequestId(memberOrder.getRequestId());//订单请求号
			    couponCode.setUserId(memberOrder.getUserId());//用户id
			    couponCode.setUseTime(new Date());//使用时间
			    couponCode.setPayTime(new Date());//支付时间
			    couponCode.setId(memberOrder.getCouponCodeId());
				couponCodeService.updateCouponCode(couponCode);//更新优惠券编码使用后的信息
			}
		}
		//开通会员
		memberRecordService.addMemberRecord(memberOrder);
		
		res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
		logger.info("updateCompleteOrder memberReqData success");
		return res;
	}
	/**
	 * 更新订单状态为成功,网银的回调
	 * 
	 * @param trxorder
	 */
	public void updateMemberOrderStatusSuccess(MemberOrder memberOrder) throws StaleObjectStateException {
		// 更新订单表状态
		Long cnt = memberOrderDao.updateMemberOrderStatusSuccess(memberOrder);
		if (cnt.longValue() == 0 ) {
			throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_UPDATE_NONE);
		}
	}
	
}