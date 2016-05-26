package io.wangxiao.edu.app.controller.edu;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.gain.GuidGeneratorService;
import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppCourseRecordController extends AppBaseController {
    private static Logger logger = Logger.getLogger(AppCourseRecordController.class);

    @Autowired
    GuidGeneratorService guidGeneratorService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;

    /**
     * 清空播放记录
     *
     * @param userId
     * @return
     */
    @RequestMapping("/study/clean")
    @ResponseBody
    public Map<String, Object> cleanPlayRecord(@RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            courseStudyhistoryService.cleanCourseStudyhistory(userId);
            json = this.getJsonMap(true, "清空成功", null);

        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙！", null);
            logger.error("cleanPlayRecord()--error", e);
        }
        return json;
    }

    /**
     * 删除播放记录 传入播放记录ID（ids） ,多个可用“,”隔开
     *
     * @param ids
     * @return
     */
    @RequestMapping("/study/del")
    @ResponseBody
    public Map<String, Object> deletePlayRecord(@RequestParam("ids") String ids) {
        Map<String, Object> json = null;
        try {
            if (ids != null && ids.trim().length() > 0) {
                if (ids.trim().endsWith(",")) {
                    ids = ids.trim().substring(0, ids.trim().length() - 1);
                }
                courseStudyhistoryService.delCourseStudyhistory(ids);
            } else {
                json = this.getJsonMap(false, "请选择要删除的数据!", null);
            }
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙，请稍后再操作!", null);
            logger.error("deletePlayRecord()--error", e);
        }
        return json;
    }

    /**
     * app查询用户学习记录列表
     *
     * @param page
     * @param userId
     * @return
     */
    @RequestMapping("/study/records")
    @ResponseBody
    public Map<String, Object> queryUserStudyRecords(@ModelAttribute("page") PageEntity page,
                                                     @RequestParam("userId") Long userId) {
        Map<String, Object> json = null;
        try {
            page.setPageSize(10);
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(userId);
            // 查询用户学习记录
            List<CourseStudyhistory> studyList = courseStudyhistoryService
                    .getCourseStudyhistoryListByCondition(courseStudyhistory, page);
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("studyList", studyList);
            dataMap.put("page", page);
            json = this.getJsonMap(true, "查询成功", dataMap);
        } catch (Exception e) {
            json = this.getJsonMap(false, "系统繁忙", null);
            logger.error("queryUserLearningRecords()----error", e);
        }
        return json;
    }

}
