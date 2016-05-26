package com.atdld.os.edu.service.impl.card;

import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.gain.GuidGeneratorService;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.core.util.StringUtils;
import com.atdld.os.edu.constants.enums.CardStatus;
import com.atdld.os.edu.constants.enums.IntegralKeyword;
import com.atdld.os.edu.constants.enums.ReqChanle;
import com.atdld.os.edu.constants.enums.UserExpandFrom;
import com.atdld.os.edu.dao.card.CardCodeDao;
import com.atdld.os.edu.dao.card.CardCourseDao;
import com.atdld.os.edu.dao.card.CardDao;
import com.atdld.os.edu.dao.user.UserDao;
import com.atdld.os.edu.entity.card.*;
import com.atdld.os.edu.entity.user.User;
import com.atdld.os.edu.entity.user.UserExpand;
import com.atdld.os.edu.service.card.CardService;
import com.atdld.os.edu.service.order.TrxorderService;
import com.atdld.os.edu.service.user.UserExpandService;
import com.atdld.os.edu.service.user.UserIntegralService;
import com.atdld.os.edu.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Card管理接口 User:  Date: 2014-09-25
 */
@Service("cardService")
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;
	@Autowired
	private CardCourseDao cardCourseDao;
	@Autowired
	private CardCodeDao cardCodeDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private GuidGeneratorService guidGeneratorService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserIntegralService userIntegralService;
	@Autowired
	private TrxorderService trxorderService;// 订单服务层
	@Autowired
	private UserExpandService userExpandService;
	@Autowired
	private WebHessianService webHessianService;
	

	/**
	 * 添加Card
	 * 
	 * @param card
	 *            要添加的Card
	 * @return id
	 */
	public java.lang.Long addCard(Card card) {
		return cardDao.addCard(card);
	}

	/**
	 * 根据id删除一个Card
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCardById(Long id) {
		cardDao.deleteCardById(id);
	}

	/**
	 * 修改Card
	 * 
	 * @param card
	 *            要修改的Card
	 */
	public void updateCard(Card card) {
		cardDao.updateCard(card);
	}

	/**
	 * 根据id获取单个Card对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return Card
	 */
	public Card getCardById(Long id) {
		return cardDao.getCardById(id);
	}

	/**
	 * 根据条件获取Card列表
	 * 
	 * @param card
	 *            查询条件
	 * @return List<Card>
	 */
	public List<Card> getCardList(Card card) {
		return cardDao.getCardList(card);
	}

	@Override
	public void saveCourseCardInfo(Card card, String courseIds) {
		// 主卡信息
		cardDao.addCard(card);
		// 副卡信息
		addcardCode(card.getId(), card.getNum());
		// 课程卡处理
		if (card.getType() == Card.COURSE_CARD) {
			// 中间表信息处理
			cardCourseDao.addCardCourse(getCardCourseList(card.getId(), courseIds));
		}
	}

	@Override
	public List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, PageEntity page) {
		return cardCodeDao.getCardListByCondtion(queryCardCode, page);
	}
	
	@Override
	public List<MainCardDTO> getMianListByCondition(QueryMainCard queryMainCard, PageEntity page) {
		return cardCodeDao.getMainCardListCondition(queryMainCard, page);
	}

	/**
	 * 课程卡集合拼凑
	 * 
	 * @param cardId
	 * @param courseIds
	 * @return
	 */
	public List<CardCourse> getCardCourseList(long cardId, String courseIds) {
		List<CardCourse> CardCourseList = new ArrayList<CardCourse>();
		String[] courseId = courseIds.split(",");
		for (int i = 0; i < courseId.length; i++) {
			CardCourse cardCourse = new CardCourse();
			cardCourse.setCardId(cardId);
			cardCourse.setCourseId(Long.parseLong(courseId[i]));
			CardCourseList.add(cardCourse);
		}
		return CardCourseList;
	}

	/**
	 * 批量添加课程卡副卡信息
	 * */
	public void addcardCode(long cardId, long num) {
		int i;
		List<CardCode> cardList = new ArrayList<CardCode>();
		for (i = 1; i <= num; i++) {
			CardCode cardCode = new CardCode();
			cardCode.setCreateTime(new Date());
			cardCode.setCardId(cardId);
			cardCode.setStatus(CardStatus.INIT.toString());
            String ccode="1"+getFixString(Integer.valueOf(cardId+"").intValue(),3)+getFixString(i,3)+ StringUtils.getRandStr(4);
			cardCode.setCardCode(ccode);
			cardCode.setCardCodePassword(StringUtils.getRandStr(10));// 生成10位密码
			cardList.add(cardCode);
		}
		cardCodeDao.addCardCodeList(cardList);
	}

    /**
     * 长度补冲，前面加0
     *
     * @param num
     * @param len
     * @return String
     */
    static String getFixString(int num, int len) {
        String tp = "" + num;
        if (len == 0) {
            return tp;
        }
        if (tp.length() == len)
            return tp;
        if (tp.length() > len)
            return tp.substring(0, len);
        for (int i = 0; i <= len / 4 + 1; i++) {
            tp = "00000" + tp;
        }
        return tp.substring(tp.length() - len);
    }

   
    
    // 订单流水信息
 	public void addOrderMsg(Long userId, String courseId) throws Exception {
 		// 拼装参数
 		Map<String, String> sourceMap = new HashMap<String, String>();
 		sourceMap.put("type", "1");// 下单类型  1课程
 		sourceMap.put("couponcode", "");// 优惠卷编码
 		sourceMap.put("userid", userId + "");
 		sourceMap.put("reqchanle", ReqChanle.WEB.toString());
 		sourceMap.put("reqIp", "");
 		sourceMap.put("courseId", courseId);
 		sourceMap.put("payType", "FREE");
 		//trxorderService.addFreeTrxorder(sourceMap);
 	}
}