package io.wangxiao.edu.home.controller.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.plan.Phase;
import io.wangxiao.edu.home.entity.plan.PhaseDTO;
import io.wangxiao.edu.home.entity.plan.PhaseDetail;
import io.wangxiao.edu.home.entity.plan.Plan;
import io.wangxiao.edu.home.service.plan.PhaseDetailService;
import io.wangxiao.edu.home.service.plan.PhaseService;
import io.wangxiao.edu.home.service.plan.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminPhaseController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminPhaseController.class);

    private static final String getPhaseListAjax = getViewPath("/admin/plan/getPhaseListAjax");//阶段信息
    private static final String getPhaseById = getViewPath("/admin/plan/getPhaseById");// 去创建任务页面

    private static final String getPhaseListForInfo = getViewPath("/admin/plan/getPhaseListForInfo");
    private static final String getPhaseForInfo = getViewPath("/admin/plan/getPhaseForInfo");

    private static final String ajaxPlanStudyInfo = getViewPath("/admin/plan/ajax_plan_studyInfo");

    @Autowired
    private PlanService planService;
    @Autowired
    private PhaseService phaseService;// 学习阶段
    @Autowired
    private PhaseDetailService phaseDetailService;// 学习阶段详情

    @InitBinder("phase")
    public void initBinderPhase(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("phase.");
    }

    @InitBinder("phaseDetail")
    public void initBinderPhaseDetail(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("phaseDetail.");
    }

    @RequestMapping("/phase/addPhase/{planId}")
    @ResponseBody
    public Map<String, Object> addPhase(HttpServletRequest request,
                                        @ModelAttribute("phase") Phase phase,
                                        @PathVariable("planId") Long planId) {
        Map<String, Object> json = null;
        try {
            phase.setPlanId(planId);
            List<Phase> phaseList = phaseService.getPhaseList(phase);
            int size = 0;
            long phaseRank = 0;
            if (phaseList.size() > 0) {
                size = phaseList.size();
                Phase _phase = phaseList.get(phaseList.size() - 1);
                phaseRank = _phase.getPhaseRank();
            }
            //学习阶段创建
            phase.setPlanId(planId);
            int nextName = size + 1;
            phase.setPhaseName("学习阶段" + nextName);
            phase.setPhaseRank(phaseRank + 1);
            phase.setStatus(0L);
            phaseService.createPhase(phase);
            json = this.getJsonMap(true, "添加成功", phase);
        } catch (Exception e) {
            e.printStackTrace();
            setExceptionRequest(request, e);
            logger.error("AdminPhaseController.addPhase", e);
        }
        return json;
    }

    /**
     * ajax查询阶段
     *
     * @param request
     * @param phase
     * @param planId
     * @return
     */
    @RequestMapping("/phase/getPhaseListAjax/{planId}")
    public ModelAndView getPhaseListAjax(HttpServletRequest request,
                                         @ModelAttribute("phase") Phase phase,
                                         @PathVariable("planId") Long planId) {
        ModelAndView modelAndView = new ModelAndView(getPhaseListAjax);
        try {
            phase.setPlanId(planId);
            List<Phase> phaseList = phaseService.getPhaseList(phase);
            if (phaseList.size() > 0) {
                modelAndView.addObject("phase", phaseList.get(0));
            }
            modelAndView.addObject("phaseList", phaseList);
            modelAndView.addObject("phaseListSize", phaseList.size());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.getPhaseListAjax", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 通过Id查询阶段
     *
     * @param request
     * @param phaseDetail
     * @param phaseId
     * @return
     */
    @RequestMapping("/phase/getPhaseById/{phaseId}")
    public ModelAndView getPhaseById(HttpServletRequest request,
                                     @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                     @PathVariable("phaseId") Long phaseId) {
        ModelAndView modelAndView = new ModelAndView(getPhaseById);
        try {
            //阶段信息
            Phase phaseById = phaseService.getPhaseById(phaseId);
            if (ObjectUtils.isNotNull(phaseById)) {
                //阶段详情
                Long id = phaseById.getId();
                phaseDetail.setPhaseId(id);
                //批量添加阶段详情
                List<PhaseDetail> phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
                modelAndView.addObject("phaseDetailList", phaseDetailList);
                modelAndView.addObject("phase", phaseById);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.getPhaseById", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 添加阶段详情
     *
     * @param request
     * @param phaseId
     * @return
     */
    @RequestMapping("/phase/addPhaseDetail/{phaseId}")
    @ResponseBody
    public Map<String, Object> addPhaseDetail(HttpServletRequest request,
                                              @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                              @PathVariable("phaseId") Long phaseId) {
        Map<String, Object> json = null;
        try {
            //是否存在阶段详情
            phaseDetail.setPhaseId(phaseId);
            List<PhaseDetail> _phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
            if (ObjectUtils.isNotNull(_phaseDetailList)) {
                phaseDetailService.deletePhaseDetailByPhaseId(phaseId);
            }
            //获取行数
            int num = Integer.parseInt(request.getParameter("len"));
            List<PhaseDetail> phaseDetailList = new ArrayList<PhaseDetail>();
            for (int i = 1; i <= num; i++) {
                //获取参数
                String otherId = request.getParameter("otherId" + i);
                String type = request.getParameter("type" + i);
                String detailName = request.getParameter("detailName" + i);
                String hours = request.getParameter("hours" + i);
                String detailRank = request.getParameter("detailRank" + i);
                PhaseDetail _phaseDetail = new PhaseDetail();
                //执行添加
                _phaseDetail.setPhaseId(phaseId);
                _phaseDetail.setOtherId(Long.parseLong(otherId));
                _phaseDetail.setType(Long.parseLong(type));
                _phaseDetail.setDetailName(detailName);
                _phaseDetail.setHours(Long.parseLong(hours));
                _phaseDetail.setDetailRank(Long.parseLong(detailRank));
                phaseDetailList.add(_phaseDetail);
            }
            phaseDetailService.addPhaseDetailList(phaseDetailList);
            json = this.getJsonMap(true, "添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.addPhaseDetail", e);
        }
        return json;
    }

    /**
     * 学习计划阶段字段发生改变（在编辑状态下）
     *
     * @param request
     * @param phase
     * @param phaseId
     * @return
     */
    @RequestMapping("/phase/updatePhase/{phaseId}")
    @ResponseBody
    public Map<String, Object> updatePhase(HttpServletRequest request,
                                           @ModelAttribute("phase") Phase phase,
                                           @PathVariable("phaseId") Long phaseId) {
        Map<String, Object> json = null;
        try {
            //获取阶段
            Phase phaseById = phaseService.getPhaseById(phaseId);
            //新的内容
            String newValue = request.getParameter("newValue");
            // 修改 阶段名称/是描述
            String fieldName = request.getParameter("fieldName");
            if (fieldName.equals("phaseName")) {
                phaseById.setPhaseName(newValue);
            } else if (fieldName.equals("phaseDescribe")) {
                phaseById.setPhaseDescribe(newValue);
            }
            phaseService.updatePhase(phaseById);
            json = this.getJsonMap(true, "修改成功", phaseById);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.updatePhase", e);
        }
        return json;
    }

    /**
     * 阶段修改排序
     *
     * @param request
     * @param phase
     * @return
     */
    @RequestMapping("/phase/updatePhaseRank")
    @ResponseBody
    public Map<String, Object> updatePhaseRank(HttpServletRequest request,
                                               @ModelAttribute("phase") Phase phase) {
        Map<String, Object> json = null;
        try {
            //获取参数
            String theId = request.getParameter("theId");
            String newId = request.getParameter("newId");
            //获取阶段
            Phase thePhase = phaseService.getPhaseById(Long.parseLong(theId));
            Phase newPhase = phaseService.getPhaseById(Long.parseLong(newId));
            //交换排序
            long theRank = thePhase.getPhaseRank();
            long newRank = newPhase.getPhaseRank();
            thePhase.setPhaseRank(newRank);
            phaseService.updatePhase(thePhase);
            newPhase.setPhaseRank(theRank);
            phaseService.updatePhase(newPhase);
            json = this.getJsonMap(true, "修改成功", thePhase);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.updatePhaseRank", e);
        }
        return json;
    }

    /**
     * 阶段移除
     *
     * @param request
     * @param phase
     * @param taskId
     * @return
     */
    @RequestMapping("/phase/delPhaseRank/{taskId}")
    @ResponseBody
    public Map<String, Object> delPhaseRank(HttpServletRequest request,
                                            @ModelAttribute("phase") Phase phase,
                                            @PathVariable("taskId") Long taskId) {
        Map<String, Object> json = null;
        try {
            //获取参数
            String theId = request.getParameter("theId");
            //删除阶段
            phaseService.deletePhaseById(Long.parseLong(theId));
            phase.setPlanId(taskId);
            List<Phase> phaseList = phaseService.getPhaseList(phase);
            //删除后，将排序重新赋值
            for (int i = 0; i < phaseList.size(); i++) {
                long j = i + 1;
                Phase _phase = phaseList.get(i);
                _phase.setPhaseRank(j);
                phaseService.updatePhase(_phase);
            }
            json = this.getJsonMap(true, "删除成功", taskId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.delPhaseRank", e);
        }
        return json;
    }

    /**
     * 阶段详情修改排序
     *
     * @param request
     * @param phaseDetail
     * @return
     */
    @RequestMapping("/phase/updatePhaseDetailRank")
    @ResponseBody
    public Map<String, Object> updatePhaseDetailRank(HttpServletRequest request,
                                                     @ModelAttribute("phaseDetail") PhaseDetail phaseDetail) {
        Map<String, Object> json = null;
        try {
            //获取参数
            String theId = request.getParameter("theId");
            String newId = request.getParameter("newId");
            //获取阶段详情
            PhaseDetail thePhaseDetail = phaseDetailService.getPhaseDetailById(Long.parseLong(theId));
            PhaseDetail newPhaseDetail = phaseDetailService.getPhaseDetailById(Long.parseLong(newId));
            //交换排序
            long theDetailRank = thePhaseDetail.getDetailRank();
            long newDetailRank = newPhaseDetail.getDetailRank();
            thePhaseDetail.setDetailRank(newDetailRank);
            phaseDetailService.updatePhaseDetail(thePhaseDetail);
            newPhaseDetail.setDetailRank(theDetailRank);
            phaseDetailService.updatePhaseDetail(newPhaseDetail);
            json = this.getJsonMap(true, "修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.updatePhaseDetailRank", e);
        }
        return json;
    }

    /**
     * 阶段详情移除
     *
     * @param request
     * @param phaseDetail
     * @param phaseId
     * @return
     */
    @RequestMapping("/phase/delPhaseDetailRank/{phaseId}")
    @ResponseBody
    public Map<String, Object> delPhaseDetailRank(HttpServletRequest request,
                                                  @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                                  @PathVariable("phaseId") Long phaseId) {
        Map<String, Object> json = null;
        try {
            //获取参数
            String theId = request.getParameter("theId");
            //根据id删除
            phaseDetailService.deletePhaseDetailById(Long.parseLong(theId));
            //重新查询列表
            phaseDetail.setPhaseId(phaseId);
            List<PhaseDetail> phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
            //删除后，将排序重新赋值
            for (int i = 0; i < phaseDetailList.size(); i++) {
                long j = i + 1;
                PhaseDetail _phaseDetail = phaseDetailList.get(i);
                _phaseDetail.setDetailRank(j);
                phaseDetailService.updatePhaseDetail(_phaseDetail);
            }

            json = this.getJsonMap(true, "删除成功", phaseId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.delPhaseDetailRank", e);
        }
        return json;
    }

    /**
     * 计划概要
     *
     * @param request
     * @param phase
     * @param phaseDetail
     * @param planId
     * @return
     */
    @RequestMapping("/phase/summarySchedule/{planId}")
    @ResponseBody
    public Map<String, Object> summarySchedule(HttpServletRequest request,
                                               @ModelAttribute("phase") Phase phase,
                                               @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                               @PathVariable("planId") Long planId) {
        Map<String, Object> json = null;
        try {
            long knowledgeNo = 0;//知识数
            long studyTimeNo = 0;//学时数
            long examNo = 0;//试卷数
            phase.setPlanId(planId);
            List<Phase> phaseList = phaseService.getPhaseList(phase);
            for (Phase _phase : phaseList) {
                long _phaseId = _phase.getId();
                phaseDetail.setPhaseId(_phaseId);
                List<PhaseDetail> phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
                knowledgeNo += phaseDetailList.size();
                for (PhaseDetail _phaseDetail : phaseDetailList) {
                    studyTimeNo += _phaseDetail.getHours();
                    long type = _phaseDetail.getType();
                    if (type == 1) {
                        examNo += 1;
                    }
                }
            }
            phase.setKnowledgeNo(knowledgeNo);
            phase.setStudyTimeNo(studyTimeNo);
            phase.setExamNo(examNo);
            json = this.getJsonMap(true, "统计成功", phase);
        } catch (Exception e) {
            e.printStackTrace();
            setExceptionRequest(request, e);
            logger.error("AdminPhaseController.summarySchedule", e);
        }
        return json;
    }

    /**
     * ajax查询阶段
     *
     * @param request
     * @param phase
     * @param planId
     * @return
     */
    @RequestMapping("/phase/getPhaseListForInfo/{planId}")
    public ModelAndView getPhaseListForInfo(HttpServletRequest request,
                                            @ModelAttribute("phase") Phase phase,
                                            @PathVariable("planId") Long planId) {
        ModelAndView modelAndView = new ModelAndView(getPhaseListForInfo);
        try {
            phase.setPlanId(planId);
            List<Phase> phaseList = phaseService.getPhaseList(phase);
            if (phaseList.size() > 0) {
                modelAndView.addObject("phase", phaseList.get(0));
            }
            modelAndView.addObject("phaseList", phaseList);
            modelAndView.addObject("phaseListSize", phaseList.size());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.getPhaseListForInfo", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 通过Id查询阶段
     *
     * @param request
     * @param phaseDetail
     * @param phaseId
     * @return
     */
    @RequestMapping("/phase/getPhaseForInfo/{phaseId}")
    public ModelAndView getPhaseForInfo(HttpServletRequest request,
                                        @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                        @PathVariable("phaseId") Long phaseId) {
        ModelAndView modelAndView = new ModelAndView(getPhaseForInfo);
        try {
            //阶段信息
            Phase phaseById = phaseService.getPhaseById(phaseId);
            if (ObjectUtils.isNotNull(phaseById)) {
                //阶段详情
                long id = phaseById.getId();
                phaseDetail.setPhaseId(id);
                //批量添加阶段详情
                List<PhaseDetail> phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
                modelAndView.addObject("phaseDetailList", phaseDetailList);
                modelAndView.addObject("phase", phaseById);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPhaseController.getPhaseForInfo", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    // ajax分页查询
    @RequestMapping("/ajax/planStudyInfo/{id}")
    public String planStudyInfo(Model model, @PathVariable("id") Long id, @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(5);
            if (ObjectUtils.isNotNull(id)) {
                Plan plan = planService.getPlanById(id);
                if (ObjectUtils.isNotNull(plan)) {
                    Phase phase = new Phase();
                    phase.setPlanId(id);
                    List<PhaseDTO> phaseList = phaseService.getPhaseDTOList(phase, page);
                    model.addAttribute("phaseList", phaseList);
                    model.addAttribute("page", page);
                }
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.planStudyInfo", e);
        }
        return ajaxPlanStudyInfo;
    }

}
