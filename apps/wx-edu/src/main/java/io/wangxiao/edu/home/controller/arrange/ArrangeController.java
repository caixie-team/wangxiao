package io.wangxiao.edu.home.controller.arrange;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.arrange.ArrangeRecord;
import io.wangxiao.edu.home.service.arrange.ArrangeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ArrangeController管理接口
 */
@Controller
public class ArrangeController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(ArrangeController.class);

    private String arrangeExam = getViewPath("/ucenter/arrange/arrangeExam");// 考试安排页面
    @Autowired
    private ArrangeRecordService arrangeRecordService;// 安排考试记录服务

    /**
     * 我的考试安排
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/uc/myArrangeExam")
    public String myArrangeExam(HttpServletRequest request, @ModelAttribute("arrangeRecord") ArrangeRecord arrangeRecord, @ModelAttribute("page") PageEntity page) {
        try {
            arrangeRecord.setUserId(getLoginUserId(request));
            List<ArrangeRecord> recordList = arrangeRecordService.getArrangeRecordList(arrangeRecord, page);
            request.setAttribute("recordList", recordList);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("ArrangeController.myArrangeExam");
        }
        return arrangeExam;
    }


}