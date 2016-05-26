package io.wangxiao.edu.mobile.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.MobileBaseController;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MobileCoursehistoryController extends MobileBaseController {
    private String play_record = getViewPath("/playRecord/play-record");// 播放记录
    private String play_record_ajax = getViewPath("/playRecord/play-record-ajax");// 播放记录
    private Logger logger = LoggerFactory.getLogger(MobileCoursehistoryController.class);

    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;

    /**
     * 用户播放记录列表
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/mobile/uc/course/study")
    public String userStudyHistory(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            page.setPageSize(6);
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService
                    .getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            request.setAttribute("studylist", studylist);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("MobileCoursehistoryController.userStudyHistory", e);
            return this.setExceptionRequest(request, e);
        }
        return play_record;
    }

    /**
     * 播放记录分页
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/mobile/uc/course/ajax/study")
    public String userStudyHistoryAjax(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            page.setPageSize(6);
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(getLoginUserId(request));
            List<CourseStudyhistory> studylist = courseStudyhistoryService
                    .getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            request.setAttribute("studylist", studylist);
            request.setAttribute("page", page);
        } catch (Exception e) {
            logger.error("MobileCoursehistoryController.userStudyHistory", e);
            return this.setExceptionRequest(request, e);
        }
        return play_record_ajax;
    }

    /**
     * 删除用户播放记录
     *
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping("/mobile/uc/study/del")
    @ResponseBody
    public Map<String, Object> delStudyHistory(HttpServletRequest request, @RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (ids != null && !ids.equals("")) {
                ids = ids.replace(",", " ").trim().replace(" ", ",");
                courseStudyhistoryService.delCourseStudyhistory(ids);
                json = this.getJsonMap(true, "success", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "error", null);
            logger.error("MobileCoursehistoryController.delStudyHistory", e);
        }
        return json;
    }
}