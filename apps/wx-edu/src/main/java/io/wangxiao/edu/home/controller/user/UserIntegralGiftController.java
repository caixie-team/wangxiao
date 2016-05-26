package io.wangxiao.edu.home.controller.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserIntegralGift;
import io.wangxiao.edu.home.service.user.UserIntegralGiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/uc")
public class UserIntegralGiftController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserIntegralGiftController.class);

    private static final String toGiftList = getViewPath("/ucenter/u-integ-exchange");// 礼品页面列表
    private static final String toMyGiftList = getViewPath("/ucenter/myGift");// 我的礼品页面列表
    @Autowired
    private UserIntegralGiftService userIntegralGiftService;

    /**
     * 查询礼品列表
     *
     * @param request
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/integift")
    public String getGiftList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页参数
            page.setPageSize(12);
            // 查询礼品列表
            UserIntegralGift userIntegralGift = new UserIntegralGift();
            List<UserIntegralGift> userIntegralGiftList = userIntegralGiftService.getUserIntegralGiftListPage(userIntegralGift, page);
            model.addAttribute("userIntegralGiftList", userIntegralGiftList);// 礼品列表数据
            model.addAttribute("page", page);// 分页参数
        } catch (Exception e) {
            logger.error("UserIntegralGiftController.getGiftList", e);
            return setExceptionRequest(request, e);
        }
        return toGiftList;
    }

    /**
     * 获得我的礼品列表
     *
     * @param request
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/mygift")
    public String getMyGiftList(HttpServletRequest request, Model model, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页参数

            page.setPageSize(9);
            List<UserIntegralGift> userIntegralGiftList = userIntegralGiftService.getIntegralGiftListByUserId(getLoginUserId(request), page);
            model.addAttribute("userIntegralGiftList", userIntegralGiftList);// 我的礼品列表数据
            model.addAttribute("page", page);// 分页参数
        } catch (Exception e) {
            logger.error("UserIntegralGiftController.getMyGiftList", e);
            return setExceptionRequest(request, e);
        }
        return toMyGiftList;
    }
}