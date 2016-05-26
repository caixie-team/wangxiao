package com.atdld.os.edu.service.impl.card;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atdld.os.edu.constants.enums.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.exception.AccountException;
import com.atdld.os.common.exception.StaleObjectStateException;
import com.atdld.os.edu.dao.card.CardCodeDao;
import com.atdld.os.edu.dao.user.UserDao;
import com.atdld.os.edu.entity.card.Card;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.card.CardCourse;
import com.atdld.os.edu.entity.card.CourseCardDTO;
import com.atdld.os.edu.entity.card.QueryCardCode;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.user.UserAccount;
import com.atdld.os.edu.service.card.CardCodeService;
import com.atdld.os.edu.service.card.CardCourseService;
import com.atdld.os.edu.service.card.CardService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserAccountService;

/**
 * CardCode管理接口 User:  Date: 2014-09-25
 */
@Service("cardCodeService")
public class CardCodeServiceImpl implements CardCodeService {

	@Autowired
	private CardCodeDao cardCodeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CardCourseService cardCourseService;
	@Autowired
	private TrxorderService trxorderService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private CardService cardService;

	/**
	 * 添加CardCode
	 * 
	 * @param cardCode
	 *            要添加的CardCode
	 * @return id
	 */
	public java.lang.Long addCardCode(CardCode cardCode) {
		return cardCodeDao.addCardCode(cardCode);
	}

	/**
	 * 根据id删除一个CardCode
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCardCodeById(Long id) {
		cardCodeDao.deleteCardCodeById(id);
	}

	/**
	 * 修改CardCode
	 * 
	 * @param cardCode
	 *            要修改的CardCode
	 */
	public void updateCardCode(CardCode cardCode) {
		cardCodeDao.updateCardCode(cardCode);
	}

	/**
	 * 根据id获取单个CardCode对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CardCode
	 */
	public CardCode getCardCodeById(Long id) {
		return cardCodeDao.getCardCodeById(id);
	}

	/**
	 * 根据条件获取CardCode列表
	 * 
	 * @param cardCode
	 *            查询条件
	 * @return List<CardCode>
	 */
	public List<CardCode> getCardCodeList(CardCode cardCode) {
		return cardCodeDao.getCardCodeList(cardCode);
	}

	@Override
	public List<CardCode> getHistoryByQueryCardCode(QueryCardCode queryCardCode) {
		// TODO Auto-generated method stub
		return cardCodeDao.getHistoryByQueryCardCode(queryCardCode);
	}

	/**
	 * 激活课程卡 充值卡实现层
	 * 
	 * @param cardCode
	 */
	public String activationCard(CardCode cardCode, Long userId,int type) throws Exception {
		String returnMsg = "";
		if (cardCodeDao.getCardCodeList(cardCode) == null || cardCodeDao.getCardCodeList(cardCode).size() == 0) {
			returnMsg = "passwordError";
		} else {
			CardCode _cardCode = cardCodeDao.getCardCodeList(cardCode).get(0);
			if (_cardCode.getType()!=type) {
				returnMsg = "typeError";//类型不匹配
			} else if (!_cardCode.getCardCodePassword().equals(cardCode.getCardCodePassword())) {
				returnMsg = "passwordError";//卡号或密码错误
			} else if (_cardCode.getStatus().equals("USED")) {
				returnMsg = "alreadyUse";//已经使用
			} else if(_cardCode.getStatus().equals(CardStatus.CLOSE.toString())){
				returnMsg = "close";//已关闭
			} else if(_cardCode.getStatus().equals(CardStatus.OVERDUE.toString())){
				returnMsg ="overDue";//已过期
			} else{//不在有效期内
				Card card= cardService.getCardById(_cardCode.getCardId());
				Date startDate = card.getBeginTime();
		        Date endDate = card.getEndTime();
		        Date date=new Date();
		    	if (startDate.getTime() > date.getTime() || endDate.getTime() < date.getTime()) {
		    		returnMsg="dateError";
		    	}
			}
			if(returnMsg.equals("")){
				User user = userDao.getUserById(userId);
				if(_cardCode.getType()==2){
					addRechargeCard(_cardCode, user);
				}else{
					addCourseCard(_cardCode, user);
				}
			}
		}
		return returnMsg;
	}
	/**
	 * 定时器操作
	 * 课程卡过期状态操作
	 */
	public void updateCardStatus() {
		try {
			String cardIds = "";
			List<CourseCardDTO> cardCodeList = cardCodeDao.getCardCodeStatus();
			for (CourseCardDTO courseCardDTO : cardCodeList) {
				if (courseCardDTO.getEndTime().before(new Date())) {
					cardIds += courseCardDTO.getId() + ",";
				}
			}
			//传递数据修改
			if(cardIds.length()>1){
				cardIds = cardIds.substring(0, cardIds.length()-1);
				cardCodeDao.updateCardCodeStatus(cardIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 操作充值卡
	 * 
	 * @throws StaleObjectStateException
	 * @throws AccountException
	 * @throws NumberFormatException
	 */
	public void addRechargeCard(CardCode cardCode, User user) throws NumberFormatException, AccountException, StaleObjectStateException {
		cardCode.setUseTime(new Date());
		cardCode.setUserEmail(user.getEmail());
		cardCode.setStatus(CardStatus.USED.toString());
		cardCode.setUserId(user.getId());
		cardCodeDao.updateCardCode(cardCode);
		Card card = cardService.getCardById(cardCode.getCardId());
		UserAccount userAccount = userAccountService.getUserAccountByUserId(user.getId());
		userAccountService.credit(userAccount, card.getMoney(), AccountType.VM, AccountHistoryType.VMLOAD, user.getId(), 0L, cardCode.getCardCode(), cardCode.getCardCode(), new Date(), "充值卡充值", true, AccountBizType.CARDLOAD);
	}
	
	/**
	 * 通过主卡作废详卡表信息
	 * 
	 * */
	@Override
	public void closeMainCard(long cardId) {
		CardCode  cardCode = new CardCode();
		cardCode.setCardId(cardId);
		cardCode.setStatus(CardStatus.INIT.toString());
		List<CardCode> cardList = cardCodeDao.getCardCodeList(cardCode);
		cardCodeDao.closeCardCodeStatus(cardList);
	}
	/**
	 * 执行课程卡操作
	 * 
	 * @param cardCode
	 * @throws Exception
	 */
	public void addCourseCard(CardCode cardCode, User user) throws Exception {
	
		CardCourse cardCourse = new CardCourse();
		cardCourse.setCardId(cardCode.getCardId());
		String courseIds = "";
		List<CardCourse> cardCourseList = cardCourseService.getCardCourseList(cardCourse);
		for (CardCourse _cardCourse : cardCourseList) {
			courseIds += _cardCourse.getCourseId() + ",";
		}

		// 参数拼装
		Map<String, String> sourceMap = new HashMap<String, String>();
		sourceMap.put("courseIds", courseIds);
		sourceMap.put("type", "1");
		sourceMap.put("couponcode", cardCode.getCardCode());
		sourceMap.put("userid", user.getId() + "");
		sourceMap.put("payType", PayType.CARD.toString());
		sourceMap.put("reqchanle", ReqChanle.WEB.toString());
		sourceMap.put("reqIp", "");// 用户访问id
		// 订单
		
		String [] array =trxorderService.addCourseCardOrder(sourceMap).split(",");
		Long orderId =new Long (array[0]);
		// 课程卡信息改变
		cardCode.setUseTime(new Date());
		cardCode.setUserEmail(user.getEmail());
		cardCode.setStatus(CardStatus.USED.toString());
		cardCode.setUserId(user.getId());
		cardCode.setTrxorderId(orderId);
		cardCode.setRequestId(array[1]);
		cardCodeDao.updateCardCode(cardCode);
	}
	
	/**
     * 根据卡编码  修改   未使用  为  已使用  状态
     * @param card_code 
     */
    public void updateCardStatusByCode(String cardCode){
    	cardCodeDao.updateCardStatusByCode(cardCode);
    }
}