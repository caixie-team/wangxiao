package io.wangxiao.edu.home.controller.card;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.controller.order.TrxorderController;
import io.wangxiao.edu.home.entity.card.Card;
import io.wangxiao.edu.home.entity.card.CardCode;
import io.wangxiao.edu.home.entity.card.CourseCardDTO;
import io.wangxiao.edu.home.entity.card.QueryCardCode;
import io.wangxiao.edu.home.service.card.CardCodeService;
import io.wangxiao.edu.home.service.card.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
     * @param card 要修改的Card
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
     *
     * @param request
     * @param cardCode
     * @return
     */
    @RequestMapping("/course/activationcard/{type}")
    @ResponseBody
    public Map<String, Object> activationCard(HttpServletRequest request, @ModelAttribute("cardCode") CardCode cardCode, @PathVariable int type) {
        String msg = "";
        Map<String, Object> json = null;
        try {
            msg = cardCodeSerivce.activationCard(cardCode, getLoginUserId(request), type);
            json = this.getJsonMap(true, "", msg);
        } catch (Exception e) {
            json = this.getJsonMap(false, "参数错误", null);
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