package com.atdld.os.exam.controller.point;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.point.PointExtend;
import com.atdld.os.exam.service.point.PointService;
import com.atdld.os.exam.service.subject.SubjectService;

/**
 * @author
 * @ClassName PointAction
 * @package com.atdld.os.exam.controller.point
 * @description
 * @Create Date: 2013-9-7 下午3:46:17
 */
@Controller
@RequestMapping("/point")
public class PointController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(PointController.class);
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PointService pointService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("point")
    public void initBinderpoint(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("point.");
    }

    @RequestMapping("/queryPointListByPIdAndSubjectId")
    @ResponseBody
    public Map<String, Object> queryPointListByPIdAndSubjectId(
            @ModelAttribute("point") ExamPoint point, HttpServletRequest request) {
        try {
            List<ExamPoint> pointList = null;
            pointList = pointService.getPointList(point);
            this.setJson(true, "", pointList);
        } catch (Exception e) {
            logger.error("PointAction.queryPointListByPIdAndSubjectId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 点击专项测试
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/queryPointList")
    @ResponseBody
    public Map<String, Object> queryPointList(HttpServletRequest request,
                                              HttpServletResponse response) {
        try {
            List<PointExtend> pointList = pointService.queryAllPointQstCount(this
                    .getLoginSubjectId(request));
            if (pointList != null && pointList.size() > 0) {
                this.setJson(true, "", pointList);
            } else {
                this.setJson(false, "", pointList);
            }
        } catch (Exception e) {
            logger.error("PointAction.queryPointList", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 获得考点
    @RequestMapping("/queryPointByPointId")
    @ResponseBody
    public Map<String, Object> queryPointByPointId(@ModelAttribute ExamPoint point) {
        try {
            point = pointService.getPointByPointId(point);
            this.setJson(true, "", point);
        } catch (Exception e) {
            logger.error("PointAction.queryPointByPointId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    @RequestMapping("/queryPointsNameByParentId")
    @ResponseBody
    public Map<String, Object> queryPointsNameByParentId(
            @ModelAttribute("point") ExamPoint point, HttpServletRequest request) {
        try {
            if (point.getId() == null || point.getId() == 0) {
                this.setJson(true, "", "");
                return json;
            }
            point = pointService.getPointByPointId(point);
            if (point == null) {
                this.setJson(true, "", "");
                return json;
            } else {
                if (point.getParentId() == null || point.getParentId() == 0) {
                    this.setJson(true, "", point.getName());
                    return json;
                } else {
                    ExamPoint point2 = new ExamPoint();
                    point2.setId(point.getParentId());
                    point2 = pointService.getPointByPointId(point);
                    if (point2 == null) {
                        this.setJson(true, "", point.getName());
                        return json;
                    } else {
                        this.setJson(true, "", point.getName() + ">" + point2.getName());
                        return json;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("PointAction.queryPointsNameByPId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 每个考点做过的题数和正确题数
    @RequestMapping("/queryPointAndQuestionRecordListByCusId")
    @ResponseBody
    public Map<String, Object> queryPointAndQuestionRecordListByCusId(
            HttpServletRequest request) {
        try {
            String paperRecordIdStr = request.getParameter("paperRecordId");
            Long paperRecordId = 0L;
            if (!ObjectUtils.isNull(paperRecordIdStr)) {
                paperRecordId = Long.valueOf(paperRecordIdStr);
            }
            List<PointExtend> list = pointService.queryPointAndQuestionRecordListByCusId(
                    this.getLoginSubjectId(request), getLoginUserId(request),
                    paperRecordId);
            if (list != null && list.size() > 0) {
                this.setJson(true, "", list);
            } else {
                this.setJson(false, "", list);
            }
        } catch (Exception e) {
            logger.error("PointAction.queryPointAndQuestionRecordListByCusId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

}
