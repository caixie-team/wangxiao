package com.atdld.os.edu.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.order.TrxorderDetail;
import com.atdld.os.edu.service.order.TrxorderDetailService;

/**
 * @ClassName com.atdld.os.edu.controller.user.UserCourseController
 * @description
 * @author :
 * @Create Date : 2014-8-4 下午5:01:24
 */
@Controller
public class UserCourseController extends EduBaseController {

    private static Logger logger = LoggerFactory.getLogger(UserCourseController.class);
    @Autowired
    TrxorderDetailService trxorderDetailService;

    /**
     * 我的课程
     * 
     * @return
     */
    @RequestMapping("/course")
    public String course(Model model, HttpServletRequest request) {
        try {
            List<TrxorderDetail> list = trxorderDetailService.getTrxorderDetailListBuy(getLoginUserId(request));
            model.addAttribute("courselist", list);
        } catch (Exception e) {
            logger.error("course", e);
        }

        return getViewPath("/ucenter/course");
    }

}
