package io.wangxiao.edu.home.controller.plan;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.plan.*;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.service.plan.*;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.home.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminPlanController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminPlanController.class);

    private static final String toAddUserPlan = getViewPath("/admin/plan/add_user_plan");// 创建个人任务页面
    private static final String toAddGroupPlan = getViewPath("/admin/plan/add_group_plan");// 创建部门任务页面

    private static final String planList = getViewPath("/admin/plan/plan_list");// 任务列表页面
    private static final String updateGroupPlan = getViewPath("/admin/plan/update_group_plan");//去修改组计划页面
    private static final String updateUserPlan = getViewPath("/admin/plan/update_user_plan");//去修改个人计划页面

    private static final String getPlanExam = getViewPath("/admin/plan/exam_list");// 试卷列表页面

    private static final String toPlanProgress = getViewPath("/admin/plan/plan_progress");// 任务完成进度
    private static final String toPhaseDetailProgress = getViewPath("/admin/plan/phase_detail_list"); //阶段任务详情完成进度

    private static final String toPlanInfoProgress = getViewPath("/admin/plan/plan_info_progress");//计划详情进度
    private static final String toPlanGroupUserProgress = getViewPath("/admin/plan/plan_group_user_progress");//小组计划用户进度
    private static final String toPhaseProgressList = getViewPath("/admin/plan/phase_progress_list");// 阶段详细信息

    @Autowired
    private PlanService planService;// 计划服务
    @Autowired
    private UserGroupService userGroupService;//个人部门服务
    @Autowired
    private PlanMiddleUserService planMiddleUserService;//计划员工服务
    @Autowired
    private PlanMiddleGroupService planMiddleGroupService;//计划部门服务
    @Autowired
    private PhaseService phaseService;// 阶段服务
    @Autowired
    private PhaseDetailService phaseDetailService;// 阶段详情
    @Autowired
    private UserService userService;
    @Autowired
    private PlanRecordService planRecordService;

    @InitBinder("plan")
    public void initBinderPlan(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("plan.");
    }

    @InitBinder("phase")
    public void initBinderPhase(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("phase.");
    }

    @InitBinder("queryPlan")
    public void initBinderQueryPlan(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryPlan.");
    }

    /**
     * 去创建个人任务页面
     *
     * @return
     */
    @RequestMapping("/plan/toAddUserPlan")
    public String toAddUserPlan(HttpServletRequest request,
                                @ModelAttribute("plan") Plan plan,
                                @ModelAttribute("queryPlan") QueryPlan queryPlan,
                                @ModelAttribute("phase") Phase phase,
                                @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                @ModelAttribute("page") PageEntity page) {

        try {
            plan.setSubmit(1L);
            plan.setType(0L);
            Plan _plan = planService.getPlan(plan);
            if (ObjectUtils.isNotNull(_plan)) {
                phase.setPlanId(_plan.getId());
                List<Phase> phaseList = phaseService.getPhaseList(phase);
                //初始任务员工实体
                PlanMiddleUser planMiddleUser = new PlanMiddleUser();
                planMiddleUser.setPlanId(_plan.getId());//设置任务编号
                //查询员工
                List<PlanMiddleUser> planMiddleUsers = planMiddleUserService.getPlanUserById(planMiddleUser);
                if (ObjectUtils.isNotNull(planMiddleUsers)) {
                    String userIds = "";
                    for (PlanMiddleUser user : planMiddleUsers) {
                        userIds += user.getUserId() + ",";
                    }
                    userIds = userIds.substring(0, userIds.length() - 1);
                    //把关联的员工放进任务中
                    queryPlan.setUserIds(userIds);
                }
                request.setAttribute("phaseOld", phaseList.get(0));
                request.setAttribute("queryPlan", queryPlan);
                request.setAttribute("planOld", _plan);
            } else {
                // 创建时间
                plan.setReleaseTime(new Date());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(new Date());
                plan.setName("学习计划" + format);
                plan.setType(0L);
                plan.setStatus(0L);
                // 执行创建任务
                planService.addPlan(plan);
                //学习阶段创建
                Long planId = plan.getId();
                phase.setPlanId(planId);
                phase.setPhaseName("学习阶段1");
                phase.setPhaseRank(1L);
                phase.setStatus(0L);
                phaseService.createPhase(phase);
                request.setAttribute("planOld", plan);
                request.setAttribute("phaseOld", phase);
            }
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPlanController.toAddUserPlan", e);
            return setExceptionRequest(request, e);
        }
        return toAddUserPlan;
    }

    /**
     * 去创建部门人任务页面
     *
     * @return
     */
    @RequestMapping("/plan/toAddGroupPlan")
    public String toAddGroupPlan(HttpServletRequest request,
                                 @ModelAttribute("plan") Plan plan,
                                 @ModelAttribute("queryPlan") QueryPlan queryPlan,
                                 @ModelAttribute("phase") Phase phase,
                                 @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                 @ModelAttribute("page") PageEntity page) {
        try {
            String examIds = "";
            plan.setSubmit(1L);
            plan.setType(1L);
            Plan _plan = planService.getPlan(plan);
            if (ObjectUtils.isNotNull(_plan)) {
                phase.setPlanId(_plan.getId());
                List<Phase> phaseList = phaseService.getPhaseList(phase);
                if (phaseList.size() > 0) {
                    for (Phase _phase : phaseList) {
                        long _phaseId = _phase.getId();
                        phaseDetail.setPhaseId(_phaseId);
                        List<PhaseDetail> phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
                        if (phaseDetailList.size() > 0) {
                            for (PhaseDetail _PhaseDetail : phaseDetailList) {
                                long type1 = _PhaseDetail.getType();
                                if (type1 == 1) {
                                    examIds += _PhaseDetail.getOtherId() + ",";
                                }
                            }
                        }
                    }
                }
                queryPlan.setExamIds(examIds);
                request.setAttribute("phaseOld", phaseList.get(0));
                request.setAttribute("queryPlan", queryPlan);
                request.setAttribute("planOld", _plan);
            } else {
                // 创建时间
                plan.setReleaseTime(new Date());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(new Date());
                plan.setName("学习计划" + format);
                plan.setType(1L);
                plan.setStatus(0L);
                // 执行创建任务
                planService.addPlan(plan);
                //学习阶段创建
                Long planId = plan.getId();
                phase.setPlanId(planId);
                phase.setPhaseName("学习阶段1");
                phase.setPhaseRank(1L);
                phase.setStatus(0L);
                phaseService.createPhase(phase);
                request.setAttribute("queryPlan", plan);
                request.setAttribute("phaseOld", phase);
            }
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPlanController.toAddGroupPlan", e);
            return setExceptionRequest(request, e);
        }
        return toAddGroupPlan;
    }


    /**
     * 任务列表
     */
    @RequestMapping("/plan/planList")
    public String planList(HttpServletRequest request,
                           @ModelAttribute("queryPlan") QueryPlan queryPlan,
                           @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(10);
            queryPlan.setSubmit(2L);
            List<QueryPlan> planList = planService.getPlanList(queryPlan, page);
            request.setAttribute("planList", planList);
            request.setAttribute("page", page);
            request.setAttribute("plan", queryPlan);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPlanController.planList", e);
        }
        return planList;
    }

    //去任务修改页面
    @RequestMapping("/plan/toUpdatePlan/{planId}")
    public ModelAndView toUpdatePlan(HttpServletRequest request,
                                     @ModelAttribute("queryPlan") QueryPlan queryPlan,
                                     @ModelAttribute("phase") Phase phase,
                                     @ModelAttribute("phaseDetail") PhaseDetail phaseDetail,
                                     @PathVariable("planId") Long planId) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String examIds = "";
            Plan plan = planService.getPlanById(planId);
            Long type = plan.getType();
            if (type == 0) {
                //初始任务员工实体
                PlanMiddleUser planMiddleUser = new PlanMiddleUser();
                planMiddleUser.setPlanId(planId);//设置任务编号
                //查询员工
                List<PlanMiddleUser> planMiddleUsers = planMiddleUserService.getPlanUserById(planMiddleUser);
                if (ObjectUtils.isNotNull(planMiddleUsers)) {
                    String userIds = "";
                    for (PlanMiddleUser user : planMiddleUsers) {
                        userIds += user.getUserId() + ",";
                    }
                    userIds = userIds.substring(0, userIds.length() - 1);
                    //把关联的员工放进任务中
                    queryPlan.setUserIds(userIds);
                }
                modelAndView.setViewName(updateUserPlan);
            } else {
                //任务下的部门
                PlanMiddleGroup planMiddleGroup = new PlanMiddleGroup();
                planMiddleGroup.setPlanId(plan.getId());
                List<PlanMiddleGroup> planMiddleGroups = planMiddleGroupService.getPlanGroupList(planMiddleGroup);
                if (ObjectUtils.isNotNull(planMiddleGroups)) {
                    String groupIds = "";
                    for (PlanMiddleGroup _planMiddleGroup : planMiddleGroups) {
                        groupIds += _planMiddleGroup.getUserGroupId() + ",";

                    }
                    groupIds = groupIds.substring(0, groupIds.length() - 1);
                    //把关联的员工放进任务中
                    queryPlan.setGroupIds(groupIds);
                }
                //是否选中
                PageEntity page = new PageEntity();
                UserGroup userGroup = new UserGroup();
                page.setPageSize(9999);
                List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
                for (UserGroup _userGroup : userGroupList) {
                    for (PlanMiddleGroup _planMiddleGroup : planMiddleGroups) {
                        if (_userGroup.getId() == _planMiddleGroup.getUserGroupId()) {
                            _userGroup.setCheck(1);
                            continue;
                        }
                    }
                }
                modelAndView.addObject("userGroupList", userGroupList);
                modelAndView.setViewName(updateGroupPlan);
            }
            if (ObjectUtils.isNotNull(plan)) {
                phase.setPlanId(plan.getId());
                List<Phase> phaseList = phaseService.getPhaseList(phase);
                if (phaseList.size() > 0) {
                    for (Phase _phase : phaseList) {
                        long _phaseId = _phase.getId();
                        phaseDetail.setPhaseId(_phaseId);
                        List<PhaseDetail> phaseDetailList = phaseDetailService.getPhaseDetailList(phaseDetail);
                        if (phaseDetailList.size() > 0) {
                            for (PhaseDetail _PhaseDetail : phaseDetailList) {
                                long _type = _PhaseDetail.getType();
                                if (_type == 1) {
                                    examIds += _PhaseDetail.getOtherId() + ",";
                                }
                            }
                        }
                    }
                }
                queryPlan.setExamIds(examIds);
                modelAndView.addObject("phaseList", phaseList);
                modelAndView.addObject("phaseOld", phaseList.get(0));
            }
            modelAndView.addObject("queryPlan", queryPlan);
            modelAndView.addObject("planOld", plan);
        } catch (Exception e) {
            logger.error("AdminPlanController.updatePlan", e);
        }
        return modelAndView;
    }

    /**
     * 执行修改任务
     *
     * @param request
     * @param queryPlan
     * @return
     */
    @RequestMapping("/plan/updatePlan")
    public String updatePlan(HttpServletRequest request,
                             @ModelAttribute("queryPlan") QueryPlan queryPlan,
                             @ModelAttribute("plan") Plan plan) {
        try {
            plan.setReleaseTime(new Date());
            plan.setSubmit(2L);
            planService.updatePlan(plan);
            String userIds = queryPlan.getUserIds();
            String groupIds = queryPlan.getGroupIds();
            if (StringUtils.isNotEmpty(userIds)) {
                // 删除任务下的员工
                planMiddleUserService.deletePlanMiddleUserById(plan.getId());
                // 如果员工不是空的执行添加
                List<PlanMiddleUser> planMiddleUsers = new ArrayList<PlanMiddleUser>();
                String[] _userIds = userIds.split(",");
                for (String userId : _userIds) {// 循环员工
                    PlanMiddleUser planMiddleUser = new PlanMiddleUser();
                    planMiddleUser.setUserId(Long.valueOf(userId));// 设置员工编号
                    planMiddleUser.setPlanId(plan.getId());// 设置任务编号
                    planMiddleUsers.add(planMiddleUser);

                } // getGroupPlan
                // 执行添加计划与员工中间表(批量添加)
                planMiddleUserService.batchAddPlanMiddleUser(planMiddleUsers);
            }

            // 可以添加多个组
            if (StringUtils.isNotEmpty(groupIds)) {
                // 删除任务下的部门
                planMiddleGroupService.deletePlanMiddleGroupById(plan.getId());
                List<PlanMiddleGroup> planMiddleGroups = new ArrayList<PlanMiddleGroup>();
                // 学员信息分组
                String[] groupIdArray = groupIds.split(",");
                for (String groupId : groupIdArray) {
                    PlanMiddleGroup planMiddleGroup = new PlanMiddleGroup();
                    planMiddleGroup.setUserGroupId(Long.valueOf(groupId));
                    planMiddleGroup.setPlanId(plan.getId());
                    planMiddleGroups.add(planMiddleGroup);
                }
                // 执行添加部门
                planMiddleGroupService.batchAddPlanMiddleGroup(planMiddleGroups);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminPlanController.updatePlan", e);
        }
        return "redirect:/admin/plan/planList";
    }

    /**
     * 启动计划
     */
    @RequestMapping("/plan/updatePlanStatus/{id}")
    public String updateTaskStatus(HttpServletRequest request,
                                   @ModelAttribute("plan") Plan plan,
                                   @PathVariable("id") Long id) {
        try {
            Plan _plan = planService.getPlanById(id);
            if (ObjectUtils.isNotNull(_plan)) {
                _plan.setStatus(1L);
                planService.updatePlan(_plan);
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.updatePlanStatus", e);
        }
        return "redirect:/admin/plan/planList";
    }

    /**
     * 作废计划
     */
    @RequestMapping("/plan/invalidPlan/{id}")
    public String invalidPlan(HttpServletRequest request,
                              @ModelAttribute("plan") Plan plan,
                              @PathVariable("id") Long id) {
        try {
            Plan planById = planService.queryPlanById(id);
            planById.setStatus(2L);
            planService.updatePlan(planById);
        } catch (Exception e) {
            logger.error("AdminPlanController.invalidPlan", e);
        }
        return "redirect:/admin/plan/planList";
    }

    /**
     * 查询所有试卷
     *
     * @param request
     * @return
     */
    @RequestMapping("/plan/getPlanExamList")
    public String getPlanExamList(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            String id = request.getParameter("examId");
            String examName = request.getParameter("examName");
            map.put("id", id);
            map.put("examName", examName);
            String examJson = HttpUtil.doPost(CommonConstants.examPath + "/webapp/exam/papers", map);
            Map<String, Object> result = new Gson().fromJson(examJson, new TypeToken<Map<String, Object>>() {
            }.getType());
            request.setAttribute("result", result);
            if (!(boolean) result.get("success")) {
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.getPlanExamList", e);
        }
        return getPlanExam;
    }

    /**
     * 根据id获取试卷
     *
     * @param request
     * @return
     */
    @RequestMapping("/plan/queryByExamIds")
    @ResponseBody
    public Map<String, Object> queryGroupUserIds(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String id = request.getParameter("id");
        map.put("id", id);
        Map<String, Object> json = null;
        try {
            String examJson = HttpUtil.doPost(CommonConstants.examPath + "/webapp/exam/getExamByids", map);
            Map<String, Object> examResult = new Gson().fromJson(examJson, new TypeToken<Map<String, Object>>() {
            }.getType());
            if (ObjectUtils.isNotNull(examResult)) {
                json = this.getJsonMap(true, "success", examResult);
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.queryGroupUserIds");
            json = this.getJsonMap(false, "not found", null);
        }
        return json;
    }

    /**
     * 复制计划
     */
    @RequestMapping("/plan/copyPlan/{newName}/{id}")
    public String copyPlan(@PathVariable("newName") String newName, @PathVariable("id") Long id) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                Plan plan = planService.getPlanById(id);
                plan.setName(newName);
                if (ObjectUtils.isNotNull(plan)) {
                    planService.updateCopyPlan(plan);
                }
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.copyPlan", e);
        }
        return "redirect:/admin/plan/planList";
    }

    /**
     * 计划完成详情
     */
    @RequestMapping("/plan/planProgress/{id}")
    public String planProgress(Model model, @PathVariable("id") Long id) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                Plan plan = planService.getPlanById(id);
                model.addAttribute("plan", plan);
                Long type = plan.getType();
                if (type == 0) {
                    //初始任务员工实体
                    PlanMiddleUser planMiddleUser = new PlanMiddleUser();
                    planMiddleUser.setPlanId(id);//设置任务编号
                    //查询员工
                    List<PlanMiddleUser> planMiddleUsers = planMiddleUserService.getPlanUserById(planMiddleUser);
                    if (ObjectUtils.isNotNull(planMiddleUsers)) {
                        String userIds = "";
                        for (PlanMiddleUser _planMiddleUser : planMiddleUsers) {
                            userIds += _planMiddleUser.getUserId() + ",";
                        }
                        if (StringUtils.isNotEmpty(userIds)) {
                            List<User> userList = userService.queryUserListByIds(userIds);
                            model.addAttribute("userList", userList);
                        }
                    }

                } else {
                    //任务下的部门
                    PlanMiddleGroup planMiddleGroup = new PlanMiddleGroup();
                    planMiddleGroup.setPlanId(plan.getId());
                    List<PlanMiddleGroup> planMiddleGroups = planMiddleGroupService.getPlanGroupList(planMiddleGroup);
                    //是否选中
                    PageEntity page = new PageEntity();
                    UserGroup userGroup = new UserGroup();
                    page.setPageSize(9999);
                    List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
                    List<UserGroup> _userGroupList = new ArrayList<>();
                    for (UserGroup _userGroup : userGroupList) {
                        for (PlanMiddleGroup _planMiddleGroup : planMiddleGroups) {
                            if (_userGroup.getId() == _planMiddleGroup.getUserGroupId()) {
                                _userGroup.setCheck(1);
                                _userGroupList.add(_userGroup);
                                continue;
                            }
                        }
                    }
                    model.addAttribute("userGroupList", _userGroupList);
                }
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.planProgress", e);
        }
        return toPlanProgress;
    }

    /**
     * 阶段详情完成进度
     */
    @RequestMapping("/plan/phaseDetailProgress/{id}")
    public String phaseDetailProgress(Model model, @PathVariable("id") Long id, @ModelAttribute("page") PageEntity page) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                Phase phase = phaseService.getPhaseById(id);
                if (ObjectUtils.isNotNull(phase)) {
                    PhaseDetail phaseDetail = new PhaseDetail();
                    phaseDetail.setPhaseId(id);
                    List<PhaseDetailDTO> phaseDetailList = phaseDetailService.getPhaseDetailDTOList(phaseDetail, page);
                    model.addAttribute("phaseDetailList", phaseDetailList);
                }
                model.addAttribute("phase", phase);
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.phaseDetailProgress", e);
        }
        return toPhaseDetailProgress;
    }

    /**
     * 计划详细
     *
     * @param model
     * @param id
     * @param page
     * @return
     */
    @RequestMapping("/plan/planInfo/{id}")
    public String planInfo(Model model, @PathVariable("id") Long id, @ModelAttribute("page") PageEntity page) {
        try {
            if (ObjectUtils.isNotNull(id) && id > 0) {
                PlanDTO plan = planService.queryPlanById(id);
                model.addAttribute("plan", plan);
                if (ObjectUtils.isNull(plan)) {// 计划不存在

                } else if (plan.getType() == 0) {//个人计划
                    PlanRecord planRecord = new PlanRecord();
                    planRecord.setPlanId(plan.getId());
                    List<PlanRecord> recordList = planRecordService.getPlanRecordListPage(planRecord, page);
                    model.addAttribute("recordList", recordList);
                } else if (plan.getType() == 1) {//部门计划
                    PlanMiddleGroup planMiddleGroup = new PlanMiddleGroup();
                    planMiddleGroup.setPlanId(id);
                    //查询部门
                    List<PlanMiddleGroupDTO> planGroupList = planMiddleGroupService.getPlanGroupDTOList(planMiddleGroup, page);
                    if (ObjectUtils.isNotNull(planGroupList)) {
                        for (PlanMiddleGroupDTO planGroup : planGroupList) {
                            planGroup.setTotalTime(plan.getTotalTime());
                        }
                    }
                    model.addAttribute("planGroupList", planGroupList);
                }
            }
        } catch (Exception e) {
            logger.error("AdminPlanController.planInfo", e);
        }
        return toPlanInfoProgress;
    }

    /**
     * 根据用户id查询完成进度
     *
     * @param model
     * @param userId
     * @param planId
     * @return
     */
    @RequestMapping("/plan/userPlanInfo/{planId}/{userId}")
    public String userPlanInfo(Model model, @PathVariable("userId") Long userId, @PathVariable("planId") Long planId) {
        try {
            Plan plan = planService.getPlanById(planId);
            model.addAttribute("plan", plan);
            User user = userService.getUserById(userId);
            model.addAttribute("nickname", ObjectUtils.isNotNull(user.getNickname()) ? user.getNickname() : user.getEmail());
            List<PhaseDTO> phaseList = phaseService.getPhaseListByUserId(planId, userId);
            model.addAttribute("phaseList", phaseList);
        } catch (Exception e) {
            logger.error("AdminPlanController.userPlanInfo", e);
        }
        return toPhaseProgressList;
    }

    /**
     * 根据部门id查询完成进度
     *
     * @param model
     * @param planId
     * @param groupId
     * @return
     */
    @RequestMapping("/plan/groupPlanInfo/{planId}/{groupId}")
    public String groupPlanInfo(Model model, @PathVariable("planId") Long planId, @PathVariable("groupId") Long groupId, @ModelAttribute("page") PageEntity page) {
        try {
            PlanDTO plan = planService.queryPlanById(planId);
            model.addAttribute("plan", plan);
            //查询员工
            List<PlanMiddleUserDTO> planUserList = planMiddleUserService.getPlanUserDTOListByGroupIdAndPlanId(groupId, planId, page);
            if (ObjectUtils.isNotNull(planUserList)) {
                for (PlanMiddleUserDTO planUser : planUserList) {
                    planUser.setTotalTime(plan.getTotalTime());
                }
            }
            model.addAttribute("planUserList", planUserList);
            model.addAttribute("groupId", groupId);
        } catch (Exception e) {
            logger.error("AdminPlanController.groupPlanInfo", e);
        }
        return toPlanGroupUserProgress;
    }
}
