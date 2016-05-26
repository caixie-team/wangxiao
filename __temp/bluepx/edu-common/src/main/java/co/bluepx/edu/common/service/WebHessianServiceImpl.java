package co.bluepx.edu.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.constants.enums.AccountBizType;
import com.atdld.os.edu.constants.enums.AccountHistoryType;
import com.atdld.os.edu.constants.enums.AccountType;
import com.atdld.os.edu.constants.enums.ReqChanle;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.course.CourseAssess;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.CourseStudyhistory;
import com.atdld.os.edu.entity.order.Trxorder;
import com.atdld.os.edu.entity.order.TrxorderDetail;
import com.atdld.os.edu.entity.user.LoginOnline;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.entity.user.UserExpandDto;
import com.atdld.os.edu.entity.user.UserFeedback;
import com.atdld.os.edu.entity.user.UserLoginLog;
import com.atdld.os.edu.service.card.CardCodeService;
import com.atdld.os.edu.service.course.CourseAssessService;
import com.atdld.os.edu.service.course.CourseProfileService;
import com.atdld.os.edu.service.course.CourseService;
import com.atdld.os.edu.service.course.CourseStudyhistoryService;
import com.atdld.os.edu.service.letter.MsgReceiveService;
import com.atdld.os.edu.service.order.TrxorderDetailService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.LoginOnlineService;
import com.atdld.os.edu.service.user.UserAccountService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.edu.service.user.UserFeedbackService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserLoginLogService;
import com.atdld.os.edu.service.user.UserService;
import com.atdld.os.sysuser.service.KeywordService;
/**
 * Created by Administrator on 2014/12/25.
 */
@Service("webHessianService")
public class WebHessianServiceImpl implements WebHessianService {
	private static final Logger logger = LoggerFactory.getLogger(WebHessianServiceImpl.class);
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private UserService userService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseAssessService courseAssessService;
	@Autowired
	private KeywordService keywordService;
	@Autowired
	private CourseProfileService courseProfileService;
	@Autowired
	private UserFeedbackService userFeedbackService;
	@Autowired
	private LoginOnlineService loginOnlineService;
	@Autowired
	private CourseStudyhistoryService courseStudyhistoryService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@Autowired
	private UserLoginLogService userLoginLogService;
	@Autowired
	private CardCodeService cardCodeService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private UserAccountService userAccountService;
	
	private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	/**
     * 修改用户最后考试的专业
     */
	public void updateUserExpandForSubject(Map<String,String> map){
		Long userId=Long.parseLong(map.get("userId"));// 用户id
		Long subjectId=Long.parseLong(map.get("subjectId"));//辅助id
		UserExpand userExpand = new UserExpand();
        userExpand.setCusId(userId);
        userExpand.setStudysubject(subjectId);
        userExpandService.updateUserExpandForSubject(userExpand);
	}
	
	
	/**
     * 查询用户信息
     * @param map
     * @return
     */
	public Map<String,String> getUserInfo(Long cusId){
		Map<String,String> map=new HashMap<String, String>();
		try {
			//用户信息
	        UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(cusId);
	        //转Json
	        String userJson=gson.toJson(userExpandDto).toString();
	        map.put("userJson", userJson);
		} catch (Exception e) {
			logger.error("WebHessianServiceImpl.getUserInfo-----error",e);
		}
		return map;
	}
	
	/**
     * 批量查询用户信息
     * @param map
     * @return
     */
	public Map<String,String> getUserInfoByUids(String cusIds){
		Map<String,String> userMap=new HashMap<String, String>();
		try {
			//批量用户信息
			Map<String, UserExpandDto> map=userExpandService.getUserExpandByUids(cusIds);
			//转为String用户集合
			List<String> userExpandDtos=new ArrayList<String>();
			for(Entry<String, UserExpandDto> entry: map.entrySet()) { 
				userExpandDtos.add(gson.toJson(entry.getValue())); 
			} 
			//转Json
			String usersJson=gson.toJson(userExpandDtos).toString();
			userMap.put("usersJson", usersJson);
		} catch (Exception e) {
			logger.error("WebHessianServiceImpl.getUserInfoByUids-----error",e);
		}
		return userMap;
	}
	
	
	/**
	 * 添加积分
	 * @param map
	 */
	public void addUserIntegral(Map<String,String> map){
		try {
	        String keyWord=map.get("keyWord");//关键字
	        Long userId=Long.parseLong(map.get("userId"));// 用户id
	        Long other=Long.parseLong(map.get("other")); //辅助id
	        Long fromUserId=Long.parseLong(map.get("fromUserId"));// 来源id
	        String otherScore=map.get("otherScorel"); // otherScore
	        userIntegralService.addUserIntegral(keyWord, userId, other, fromUserId, otherScore);
		} catch (Exception e) {
			logger.error("WebHessianServiceImpl.addUserIntegral-----error",e);
		}
	}
   
	/**
     * 修改CourseProfile浏览次数
     * @param map
     */
    public void updateCourseProfile(Map<String,String> map){
    	String type=map.get("type");
    	Long courseId=Long.parseLong(map.get("courseId"));
    	Long count=Long.parseLong(map.get("count"));
    	String operation=map.get("operation");
    	courseProfileService.updateCourseProfile(type, courseId, count, operation);
    }


    /**
	 * 通过标识更新未读数加一或清零
	 */
	public void readMsgNumAddOrReset(String falg, Long cusId,String iType){
		if(iType.equals("add")){//添加
			userExpandService.updateUnReadMsgNumAddOne(falg, cusId);
		}else if(iType.equals("reset")){//清零
			userExpandService.updateUnReadMsgNumReset(falg, cusId);
		}
	}
	
	/**
	 * 更新用户的上传统计系统消息时间
	 */
	public void updateCusForLST(Long cusId,Long date){
		userExpandService.updateCusForLST(cusId, new Date(date));
	}
	
	/**
     * 修改UserExpand浏览次数
     * @param map
     */
    public void updateUserExpandCount(Map<String,String> map){
    	String type=map.get("type");
    	Long userId=Long.parseLong(map.get("cusId"));
    	Long count=Long.parseLong(map.get("count"));
    	String operation=map.get("operation");
    	UserExpand userExpand=userExpandService.getUserExpandByUserId(userId);
    	if(operation.equals("-")){
    		if(type.equals("weiboNum")&&userExpand.getWeiBoNum()>0){
    			userExpandService.updateUserExpandCount(type, userId, count, operation);
    		}
    		if(type.equals("fansNum")&&userExpand.getFansNum()>0){
    			userExpandService.updateUserExpandCount(type, userId, count, operation);
    		}
    		if(type.equals("msgNum")&&userExpand.getMsgNum()>0){
    			userExpandService.updateUserExpandCount(type, userId, count, operation);
    		}
    		if(type.equals("attentionNum")&&userExpand.getAttentionNum()>0){
    			userExpandService.updateUserExpandCount(type, userId, count, operation);
    		}
    		if(type.equals("sysMsgNum")&&userExpand.getSysMsgNum()>0){
    			userExpandService.updateUserExpandCount(type, userId, count, operation);
    		}
    		if(type.equals("unreadFansNum")&&userExpand.getUnreadFansNum()>0){
    			userExpandService.updateUserExpandCount(type, userId, count, operation);
    		} 
    	}else{
    		userExpandService.updateUserExpandCount(type, userId, count, operation);
    	}
    }


    
    /**
     * 查询全部好友
     * 
     * @param customer
     *            好友实体
     * @param page
     *            分页参数
     * @return List<QueryCustomer>
     * @throws Exception
     */
    public Map<String,String> queryAllCustomer(Map<String,String> map){
    	String email=map.get("email");//邮箱
    	Long cusId=Long.parseLong(map.get("cusId"));//用户id
    	int pageSize=Integer.parseInt(map.get("pageSize"));//分页size
    	int currentPage=Integer.parseInt(map.get("currentPage"));//当前页数
    	UserExpandDto customer=new UserExpandDto();
    	customer.setCusId(cusId);
    	customer.setEmail(email);
    	PageEntity page=new PageEntity();
    	page.setCurrentPage(currentPage);
    	page.setPageSize(pageSize);
    	//用户集合
    	List<UserExpandDto> users=userExpandService.queryAllCustomer(customer, page);
    	List<String> usersString=new ArrayList<String>();
    	for(UserExpandDto u:users){
    		usersString.add(gson.toJson(u));
    	}
    	//转成json
    	String usersJson=gson.toJson(usersString);
    	String pageJson=gson.toJson(page);
    	Map<String,String> resultMap=new HashMap<String, String>();
    	resultMap.put("usersJson", usersJson);
    	resultMap.put("pageJson", pageJson);
    	return resultMap;
    }

  
    
    /**
     * 通过showname 查询customer(精确搜索)
     */
    public Map<String,String> queryCustomerByShowNameEquals(String showName){
    	//用户集合
    	List<UserExpandDto> users=userExpandService.queryCustomerByShowNameEquals(showName);
    	List<String> usersString=new ArrayList<String>();
    	for(UserExpandDto u:users){
    		usersString.add(gson.toJson(u));
    	}
    	//转成json
    	String usersJson=gson.toJson(usersString).toString();
    	Map<String,String> resultMap=new HashMap<String, String>();
    	resultMap.put("usersJson", usersJson);
    	return resultMap;
    }
    
	
	/**
	 * 通过showname 查询customer
	 * 
	 * @param showName
	 */
	public Map<String,String> queryCustomerByShowName(String showName, int size){
		//用户集合
    	List<UserExpandDto> users=userExpandService.queryCustomerByShowName(showName, size);
    	//转成json
    	String usersJson=gson.toJson(users).toString();
    	Map<String,String> resultMap=new HashMap<String, String>();
    	resultMap.put("usersJson", usersJson);
    	return resultMap;
	}
	/**
	 * 首页获取推荐的课程 封装为map
	 * @param recommendId
	 * @return
	 * @throws Exception
	 */
    public Map<String,String> getCourseListByHomePage(Long recommendId){
    	Map<String,String> resultMap=new HashMap<String, String>();
		try {
			//批量课程信息
			Map<String, List<CourseDto>> map=courseService.getCourseListByHomePage(recommendId);
			Map<String,String> courseMap=new HashMap<String, String>();
			for(Entry<String, List<CourseDto>> entry: map.entrySet()) { 
				//course对象转为json再存入集合
				List<String> coursesString=new ArrayList<String>();
				for(CourseDto course:entry.getValue()){
					Map<String,String> mapTemp=new HashMap<String, String>();
					mapTemp.put("id", course.getId()+"");//课程id
					mapTemp.put("logo",course.getLogo());//课程封面
					mapTemp.put("name",course.getName());//课程名称
					mapTemp.put("lessionnum",course.getLessionnum()+"");//课时
					coursesString.add(gson.toJson(mapTemp));
				}
				courseMap.put(entry.getKey(), gson.toJson(coursesString));
			} 
			//转Json
			String coursesMapJson=gson.toJson(courseMap).toString();
			resultMap.put("coursesMapJson", coursesMapJson);
		} catch (Exception e) {
			logger.error("WebHessianServiceImpl.getCourseListByHomePage-----error",e);
		}
		return resultMap;
    }
    /**
     * 关键字过滤
     * @param str
     * @return
     */
    public String doFilter(String content){
    	return keywordService.doFilter(content);
    }
    
    /**
	 * 添加课程评论
	 */
	public void mobileAddCourseAssess(Map<String,String> map){
		CourseAssess courseAssess=new CourseAssess();
		courseAssess.setCourseId(Long.parseLong(map.get("courseId")));
		courseAssess.setKpointId(Long.parseLong(map.get("kpointId")));
		courseAssess.setCreateTime(new Date());
		courseAssess.setUserId(Long.parseLong(map.get("userId")));
		courseAssess.setStatus(0);
		courseAssess.setContent(map.get("content"));
		courseAssessService.addCourseAssess(courseAssess);
	}
	
	/**
	 * 添加反馈意见
	 * @param userFeedback
	 * @return
	 */
	public void mobileAddUserFeedback(Map<String,String> map){
		UserFeedback userFeedback=new UserFeedback();
		userFeedback.setCreateTime(new Date());
		userFeedback.setContent(map.get("content"));
		userFeedback.setEmail(map.get("email"));
		userFeedback.setMobile(map.get("mobile"));
		userFeedback.setQq(map.get("qq"));
		userFeedback.setUserId(Long.parseLong(map.get("userId")));
		userFeedback.setName(map.get("name"));
		userFeedbackService.addUserFeedback(userFeedback);
	}
	/**
	 * 修改用户信息
	 * @param queryUser
	 * @return
	 * @throws Exception
	 */
	public String mobileUpdateQueryUser(Map<String,String> map) throws Exception{
		UserExpandDto userExpandDto=new UserExpandDto();
		userExpandDto.setId(Long.parseLong(map.get("id")));
		userExpandDto.setNickname(map.get("nickName"));
		userExpandDto.setMobile(map.get("mobile"));
		userExpandDto.setGender(Integer.parseInt(map.get("gender")));
		userExpandDto.setUserInfo(map.get("userInfo"));
		userExpandDto.setRealname(map.get("realName"));
		return userService.updateQueryUser(userExpandDto);
	}
	
	/**
	 * 修改用户密码
	 * @param map
	 */
	public void mobileUpdatePwdById(Map<String,String> map){
		User user=new User();
		user.setId(Long.parseLong(map.get("userId")));
		user.setPassword(map.get("password"));
		userService.updatePwdById(user, null);
	}
	
	/**
	 * 删除播放历史
	 * @param ids
	 */
	public void mobileDelCourseStudyhistory(String ids){
		courseStudyhistoryService.delCourseStudyhistory(ids);
	}
	
	/**
	 * 添加播放记录
	 * @param courseStudyhistory
	 */
	public void mobilePlayertimes(Map<String,String> map){
		CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
		courseStudyhistory.setCourseId(Long.parseLong(map.get("courseId")));
		courseStudyhistory.setKpointId(Long.parseLong(map.get("kpointId")));
		courseStudyhistory.setUserId(Long.parseLong(map.get("userId")));
		courseStudyhistoryService.playertimes(courseStudyhistory);
	}
	
	/**
     * 发送系统消息
     *
     * @param content 要发送的内容
     * @param cusId   用户id
     * @throws Exception
     */
    public String mobileAddSystemMessageByCusId(String content, Long cusId) throws Exception{
    	return msgReceiveService.addSystemMessageByCusId(content, cusId);
    	
    }
    
    /**
	 * 添加User
	 * 
	 * @param user
	 *            要添加的User
	 * @return id
	 */
	public Long mobileAddUser(Map<String,String> map){
		User user=new User();
		user.setEmail(map.get("email"));
		user.setMobile(map.get("mobile"));
		user.setPassword(map.get("password"));
		user.setUserip(map.get("userIp"));
		user.setRegisterFrom(map.get("registerFrom"));
		if(map.get("cusName")!=null){
			user.setNickname(map.get("cusName"));
		}
		return userService.addUser(user);
	}
	
	/**
     * 添加登陆记录
     * @param userLoginLog 要添加的UserLoginLog
     * @return id
     */
    public void mobileAddUserLoginLog(Map<String,String> map){
    	// 添加登录记录
		UserLoginLog userLoginLog = new UserLoginLog();
		userLoginLog.setLoginIp(map.get("loginIp"));
		userLoginLog.setUserId(Long.parseLong(map.get("userId")));
		userLoginLog.setLoginTime(new Date());
		if(map.get("userAgent")!=null){
			userLoginLog.setUserAgent(map.get("userAgent").split(";")[0]);//浏览器
			userLoginLog.setOsname(map.get("userAgent").split(";")[1]);//操作系统
		}
		userLoginLogService.addUserLoginLog(userLoginLog);
    }
    /**
     * 添加LoginOnline
     * @param loginOnline 要添加的LoginOnline
     * @return id
     */
    public void mobileAddLoginOnline(Map<String,String> map){
    	//添加在线记录状态
        LoginOnline loginOnline=new LoginOnline();
        loginOnline.setUserId(Long.parseLong(map.get("userId")));
        loginOnline.setLoginsid(map.get("loginsid"));
        loginOnline.setCreateTime(new Date());
        loginOnline.setType(ReqChanle.MOBILE.toString());
        loginOnlineService.addLoginOnline(loginOnline);
    }
    /**
     * 根据卡编码  修改   未使用  为  已使用  状态
     * @param card_code 
     */
    public void mobileUpdateCardStatusByCode(String cardCode){
    	cardCodeService.updateCardStatusByCode(cardCode);
    }
    /**
     * 微站添加订单
     * @param map
     */
    public Long mobileAddTrxorder(Map<String,String> map){
    	Trxorder trxorder=new Trxorder();
    	trxorder.setUserId(Long.parseLong(map.get("userId")));
    	trxorder.setRequestId(map.get("requestId"));
    	trxorder.setReqChannel(map.get("reqChannel"));
    	trxorder.setPayType(map.get("payType"));
    	trxorder.setReqIp(map.get("reqIp"));
    	trxorder.setDescription(map.get("description"));
    	trxorder.setTrxStatus(map.get("trxStatus"));
    	trxorder.setAmount(new BigDecimal(map.get("amount")));
    	trxorder.setOrderAmount(new BigDecimal(map.get("orderAmount")));
    	trxorder.setCouponAmount(new BigDecimal(map.get("couponAmount")));
    	trxorder.setCreateTime(new Date());
    	return trxorderService.mobileAddTrxorder(trxorder);
    }
    
    /**
     * 批量添加TrxorderDetail
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void mobileAddBatchTrxorderDetail(Map<String,String> map){
    	// 添加流水
    	List<TrxorderDetail> trxorderDetailList = new ArrayList<TrxorderDetail>();
    	TrxorderDetail trxorderDetail = new TrxorderDetail();
    	Date date=new Date();
    	trxorderDetail.setCreateTime(date);
    	trxorderDetail.setLastUpdateTime(date);
		trxorderDetail.setUserId(Long.parseLong(map.get("userId")));// 用户id
		trxorderDetail.setCourseId(Long.parseLong(map.get("courseId")));// 课程id
		trxorderDetail.setTrxorderId(Long.parseLong(map.get("trxorderId")));// 订单id
		trxorderDetail.setTrxStatus(map.get("trxStatus"));// 流水支付状态
		trxorderDetail.setLosetype(Integer.parseInt(map.get("losetype")));// 有效期类型
		if(map.get("loseAbsTime")!=null){
			trxorderDetail.setLoseAbsTime(new Date(Long.parseLong(map.get("loseAbsTime"))));// 订单过期时间段
		}
		trxorderDetail.setLoseTime(map.get("loseTime"));
		trxorderDetail.setAuthTime(new Date(Long.parseLong(map.get("authTime"))));// 订单过期时间点
		trxorderDetail.setSourcePrice(new BigDecimal(map.get("sourcePrice")));// 课程原始价格
		trxorderDetail.setCurrentPrice(new BigDecimal(map.get("currentPrice")));// 课程实际支付价格
		trxorderDetail.setCourseName(map.get("courseName"));//课程名称
		trxorderDetail.setRequestId(map.get("requestId"));// 订单请求号
		trxorderDetail.setAuthStatus(map.get("authStatus"));
		trxorderDetail.setDescription(map.get("description"));
    	trxorderDetailList.add(trxorderDetail);
    	trxorderDetailService.addBatchTrxorderDetail(trxorderDetailList);
    }
    
    /**
     * 更新订单状态为成功,网银的回调
     * @param trxorder
     */
    public void mobileUpdateTrxorderStatusSuccess(Map<String,String> map) throws StaleObjectStateException{
    	Trxorder trxorder=new Trxorder();
    	trxorder.setPayTime(new Date(Long.parseLong(map.get("payTime"))));
    	trxorder.setRequestId(map.get("requestId"));
    	trxorder.setPayType(map.get("payType"));
    	trxorder.setCashAmount(new BigDecimal(map.get("cashAmount")));
    	trxorder.setVmAmount(new BigDecimal(map.get("vmAmount")));
    	trxorder.setTrxStatus(map.get("trxStatus"));
    	Gson gson=new Gson(); 
		System.out.println(gson.toJson(trxorder).toString()+"11111111111111111111111111111111111");
    	trxorderService.updateTrxorderStatusSuccess(trxorder);
    }
    
    /**
     * 账户扣款，消费，出账
     * @param map
     * @return
     * @throws AccountException
     * @throws StaleObjectStateException
     */
	public void mobiledebit(Map<String,String> map) throws AccountException, StaleObjectStateException{
		UserAccount account=new UserAccount();
        account.setId(Long.parseLong(map.get("id")));
        account.setUserId(Long.parseLong(map.get("userId")));
        account.setBalance(new BigDecimal(map.get("balance")));
        account.setForzenAmount(new BigDecimal(map.get("forzenAmount")));
        account.setCashAmount(new BigDecimal(map.get("cashAmount")));
        account.setVmAmount(new BigDecimal(map.get("vmAmount")));
        account.setAccountStatus(map.get("accountStatus"));
        account.setVersion(Long.parseLong(map.get("version")));
		userAccountService.debit(account,new BigDecimal(map.get("trxAmount")), AccountHistoryType.SALES,Long.parseLong(map.get("userId")), Long.parseLong(map.get("trxOrderId")), map.get("requestId"), new Date(), map.get("description"), true, AccountBizType.COURSE);
	}

	/**
	 * 账户入账,充值
	 * @param map
	 * @throws AccountException
	 * @throws StaleObjectStateException
	 */
	public void mobilecredit(Map<String,String> map) throws AccountException, StaleObjectStateException{
        UserAccount account=new UserAccount();
        account.setId(Long.parseLong(map.get("id")));
        account.setUserId(Long.parseLong(map.get("userId")));
        account.setBalance(new BigDecimal(map.get("balance")));
        account.setForzenAmount(new BigDecimal(map.get("forzenAmount")));
        account.setCashAmount(new BigDecimal(map.get("cashAmount")));
        account.setVmAmount(new BigDecimal(map.get("vmAmount")));
        account.setAccountStatus(map.get("accountStatus"));
        account.setVersion(Long.parseLong(map.get("version")));
		userAccountService.credit(account, new BigDecimal(map.get("trxAmount")), AccountType.CASH, AccountHistoryType.CASHLOAD, Long.parseLong(map.get("userId")), Long.parseLong(map.get("trxOrderId")), map.get("requestId"), map.get("out_trade_no"), new Date(), map.get("description"),  true, AccountBizType.COURSE);
		
	}
    
	/**
	 * 课程卡激活
	 * @param cardCode
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String activationCard(Map<String, Object> map){
		String msg = "";
		try {
			String cardCodeJson = (String) map.get("cardCode");
			Long userId = (Long)map.get("userId");
			int type = Integer.parseInt(map.get("type").toString());
			@SuppressWarnings("serial")
			CardCode cardCode = gson.fromJson(cardCodeJson, new TypeToken<CardCode>(){}.getType());
			msg = cardCodeService.activationCard(cardCode, userId, type);
		} catch (Exception e) {
			logger.error("MobileHessianServiceImpl.activationCard", e);
			msg = "error"; // 系统错误
		}
		return msg;
	}
    
}
