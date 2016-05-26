package com.atdld.os.exam.controller.point;

import java.util.Date;
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
import com.atdld.os.core.util.RequestUtil;
import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.service.point.PointService;
import com.atdld.os.exam.service.professional.ProfessionalService;
import com.atdld.os.exam.service.subject.SubjectService;


/**
 * @author
 * @ClassName AdminSubjectAction
 * @package com.atdld.os.exam.controller.subject
 * @description
 * @Create Date: 2013-9-7 下午3:46:17
 */
@Controller
@RequestMapping("/admin")
public class AdminPointController extends ExamBaseController {

    private static final Logger logger = Logger.getLogger(AdminPointController.class);
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private PointService pointService;
    @Autowired
    private ProfessionalService professionalService;

    private String toSavePoint = getViewPath("/admin/point/add_point");

    @InitBinder("point")
    public void initBinderPage(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("point.");
    }

    // 到考点页面
    @RequestMapping("/point/toSavePoint")
    public String toSavePoint(HttpServletRequest request, HttpServletResponse response) {
        Long subjectId = RequestUtil.getLong(request, "subjectId");
        List<ExamPoint> pointList = null;
        try {
        	//获取所有专业
        	List<ExamProfessional> professionalList = professionalService.queryProfessionalList();
            pointList = pointService.getPointListBySubject(subjectId);
            request.setAttribute("professionalList", professionalList);
            request.setAttribute("pointList", gson.toJson(pointList));
        } catch (Exception e) {
            logger.error("AdminPointAction.toSavePoint", e);
            return setExceptionRequest(request, e);
        }
        return toSavePoint;
    }

    @RequestMapping("/point/queryPointList")
    @ResponseBody
    public Map<String, Object> queryPointList(@ModelAttribute("point") ExamPoint point,
                                              HttpServletRequest request, HttpServletResponse response) {
        List<ExamPoint> pointList = null;
        try {
            pointList = pointService.getPointListBySubject(point.getSubjectId());
            if (pointList != null && pointList.size() > 0) {
                this.setJson(true, "", pointList);
            } else {
                this.setJson(false, "", pointList);
            }
        } catch (Exception e) {
            logger.error("AdminPointAction.queryPointList", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    @RequestMapping("/point/queryPointListByPIdAndSubjectId")
    @ResponseBody
    public Map<String, Object> queryPointListByPIdAndSubjectId(@ModelAttribute("point") ExamPoint point,
                                                               HttpServletRequest request, HttpServletResponse response) {
        List<ExamPoint> pointList = null;
        try {
            pointList = pointService.getPointListBySubject(point.getSubjectId());
            this.setJson(true, "", pointList);
        } catch (Exception e) {
            logger.error("AdminPointAction.queryPointListByPIdAndSubjectId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 保存考点
    @RequestMapping("/point/savePoint")
    @ResponseBody
    public Map<String, Object> savePoint(@ModelAttribute("point") ExamPoint point,
                                         HttpServletRequest request, HttpServletResponse response) {
        try {
            // 传来的pointid为0说明是添加不为0为更新
            if (point != null && point.getId() == 0) {
                point.setAddTime(new Date());
                pointService.savePoint(point);
            } else {
                pointService.updatePointByPointId(point);
            }
            this.setJson(true, "", null);
        } catch (Exception e) {
            logger.error("AdminPointAction.savePoint", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 获得考点
    @RequestMapping("/point/queryPointByPointId")
    @ResponseBody
    public Map<String, Object> queryPointByPointId(
            @ModelAttribute("point") ExamPoint point, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            point = pointService.getPointByPointId(point);
            this.setJson(true, "success", point);
        } catch (Exception e) {
            this.setJson(false, "failure", point);
            logger.error("AdminPointAction.queryPointByPointId", e);
            this.setJson(false, "error", null);
        }
        return json;
    }

    // 获得考点
    @RequestMapping("/point/delPointByPointId")
    @ResponseBody
    public Map<String, Object> delPointByPointId(HttpServletRequest request,
                                                 HttpServletResponse response) {
        String pointIds = request.getParameter("pointIds");
        try {
            String[] parray = pointIds.replaceAll(" ", "").split(",");
            if (!ObjectUtils.isNull(parray)) {
                pointService.delPointByPointId(parray);
            }
            this.setJson(true, "", null);
            return json;
        } catch (Exception e) {
            logger.error("AdminPointAction.delPointByPointId", e);
            this.setJson(false, "error", null);
            return json;
        }
    }

    @RequestMapping("/point/queryPointsNameByPId")
    @ResponseBody
    public Map<String, Object> queryPointsNameByPId(@ModelAttribute("point") ExamPoint point, HttpServletRequest request,
                                                    HttpServletResponse response) {
        try {
            ExamPoint point1 = pointService.getPointByPointId(point);
            this.setJson(true, "", point1);
            return json;
        } catch (Exception e) {
            logger.error("AdminPointAction.queryPointsNameByPId", e);
            this.setJson(false, "error", null);
            return json;
        }
    }


}
