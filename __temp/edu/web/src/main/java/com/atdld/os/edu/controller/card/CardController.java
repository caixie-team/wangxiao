package com.atdld.os.edu.controller.card;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.controller.order.TrxorderController;
import com.atdld.os.edu.entity.card.Card;
import com.atdld.os.edu.entity.card.CardCode;
import com.atdld.os.edu.entity.card.CourseCardDTO;
import com.atdld.os.edu.entity.card.QueryCardCode;
import com.atdld.os.edu.service.card.CardCodeService;
import com.atdld.os.edu.service.card.CardService;

/**
 * Card管理接口 User:  Date: 2014-09-25
 */
@Controller
public class CardController extends EduBaseController {
	private static final Logger logger = LoggerFactory.getLogger(TrxorderController.class);
	@Autowired
	private CardService cardService;
	@Autowired
	private CardCodeService cardCodeSerivce;
	
	private static final String cardList = getViewPath("/ucenter/card_list"); // 课程卡信息
	private static final String toCardList = getViewPath("/edu/card/couse_card_list");// 创建课程卡页面

	/**
	 * 修改Card
	 * 
	 * @param card
	 *            要修改的Card
	 */
	public void updateCard(Card card) {
		cardService.updateCard(card);
	}

	// 绑定属性 封装参数
	@InitBinder("queryCardCode")
	public void initQueryCardCode(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("queryCardCode.");
	}

	// 绑定属性 封装参数
	@InitBinder("cardCode")
	public void initCardCode(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("cardCode.");
	}

	
	@RequestMapping("/course/course_card_list")
	public ModelAndView toUserCardList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryCardCode queryCardCode = new QueryCardCode();
		try {
			modelAndView.setViewName(toCardList);
			queryCardCode.setUserId(getLoginUserId(request));
			List<CourseCardDTO> cartList = cardService.getCardListByCondtion(queryCardCode, page);
			modelAndView.addObject("cartList", cartList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("toUserCardList" + e);
		}
		return modelAndView;
	}

	/**
	 * 虚拟卡信息激活
	 * @param request
	 * @param cardCode
	 * @return
	 */
	@RequestMapping("/course/activationcard/{type}")
	@ResponseBody
	public Map<String, Object> activationCard(HttpServletRequest request, @ModelAttribute("cardCode") CardCode cardCode,@PathVariable int type) {
		String msg = "";
		try {
			msg = cardCodeSerivce.activationCard(cardCode, getLoginUserId(request),type);
			this.setJson(true, "", msg);
		} catch (Exception e) {
			this.setJson(false, "参数错误", null);
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 课程卡信息
	 * 
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/uc/card")
	public ModelAndView cardList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		QueryCardCode queryCardCode = new QueryCardCode();
		modelAndView.setViewName(cardList);
		try {
			this.setPage(page);
			queryCardCode.setUserId(getLoginUserId(request));
			queryCardCode.setType(1);
			List<CourseCardDTO> userCardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
			modelAndView.addObject("userCardCodeList", userCardCodeList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	
	
}