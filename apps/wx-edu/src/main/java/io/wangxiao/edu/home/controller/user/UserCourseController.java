package io.wangxiao.edu.home.controller.user;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.order.TrxorderDetail;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
