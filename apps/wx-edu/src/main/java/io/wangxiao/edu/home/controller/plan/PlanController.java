package io.wangxiao.edu.home.controller.plan;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.plan.PhaseDTO;
import io.wangxiao.edu.home.entity.plan.PlanDTO;
import io.wangxiao.edu.home.entity.plan.PlanRecord;
import io.wangxiao.edu.home.entity.plan.QueryPlan;
import io.wangxiao.edu.home.service.plan.PhaseService;
import io.wangxiao.edu.home.service.plan.PlanRecordService;
import io.wangxiao.edu.home.service.plan.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PlanController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

    private static final String planRecordList = getViewPath("/plan/plan_list");
    private static final String planRecord = getViewPath("/plan/plan_record");

    @Autowired
    private PlanService planService;// 计划服务
    @Autowired
    private PlanRecordService planRecordService;// 计划记录
    @Autowired
    private PhaseService phaseService;// 阶段服务

    @InitBinder
    public void initBinderQueryPlan(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryPlan.");
    }


    /**
     * 获取计划列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/plan/planList")
    public String planList(Model model, @ModelAttribute("queryPlan") QueryPlan queryPlan, @ModelAttribute("page") PageEntity page) {
        try {
            List<PlanDTO> planList = planService.queryPlanList(queryPlan, page);
            model.addAttribute("planList", planList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("PlanController.planInfo", e);
        }
        return null;
    }


    /**
     * 获取计划记录列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/uc/planRecordList")
    public String planRecordList(HttpServletRequest request, Model model, @ModelAttribute("planRecord") PlanRecord planRecord, @ModelAttribute("page") PageEntity page) {
        try {
            Long userId = getLoginUserId(request);
            page.setPageSize(6);
            planRecord.setUserId(userId);
            List<PlanRecord> recordList = planRecordService.getPlanRecordListPage(planRecord, page);
            model.addAttribute("recordList", recordList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("PlanController.planRecordList", e);
        }
        return planRecordList;
    }


    /**
     * 获取计划记录详细
     *
     * @return
     */
    @RequestMapping("/uc/planRecord/{id}")
    public String planRecord(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        Long userId = getLoginUserId(request);
        try {
            if (ObjectUtils.isNotNull(id)) {
                // 获取计划基本信息
                PlanDTO plan = planService.queryPlanById(id);
                request.setAttribute("plan", plan);
                PlanRecord planRecord = new PlanRecord();
                planRecord.setPlanId(id);
                planRecord.setUserId(getLoginUserId(request));
                planRecord = planRecordService.getPlanRecord(planRecord);
                request.setAttribute("planRecord", planRecord);
                if (ObjectUtils.isNotNull(plan)) {
                    // 获取阶段信息
                    List<PhaseDTO> phaseList = phaseService.getPhaseListByUserId(id, userId);
                    request.setAttribute("phaseList", phaseList);
                }
                PlanRecord _planRecord = new PlanRecord();
                _planRecord.setPlanId(plan.getId());
                List<PlanRecord> recordList = planRecordService.getPlanRecordList(_planRecord);
                request.setAttribute("recordList", recordList);
            }
        } catch (Exception e) {
            logger.error("PlanController.planInfo", e);
        }
        return planRecord;
    }
}
