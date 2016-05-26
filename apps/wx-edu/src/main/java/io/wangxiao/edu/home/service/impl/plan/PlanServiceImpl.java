package io.wangxiao.edu.home.service.impl.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.plan.*;
import io.wangxiao.edu.home.entity.plan.*;
import io.wangxiao.edu.home.service.plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PlanService")
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanDao planDao;
    @Autowired
    private PlanRecordDao planRecordDao;
    @Autowired
    private PhaseDao phaseDao;
    @Autowired
    private PhaseDetailDao phaseDetailDao;
    @Autowired
    private PlanMiddleUserDao planMiddleUserDao;
    @Autowired
    private PlanMiddleGroupDao planMiddleGroupDao;
    @Autowired
    private PhaseDetailProgressDao phaseDetailProgressDao;

    public Long addPlan(Plan plan) {
        return planDao.addPlan(plan);
    }

    public void deletePlanById(Long id) {
        planDao.deletePlanById(id);
    }

    public Long updatePlan(Plan plan) {
        if (plan.getStatus() != 1) {
            return planDao.updatePlan(plan);
        }
        // 计划启动

        // 存放用户id
        List<Long> userIdList = new ArrayList<>();
        // 个人计划
        if (plan.getType() == 0) {
            // 获取个人计划分派人员
            userIdList = planMiddleUserDao.getPlanUserId(plan.getId());
        }
        // 部门计划
        else if (plan.getType() == 1) {
            // 获取部门计划指派人员
            userIdList = planMiddleGroupDao.getPlanGroupUserId(plan.getId());
        }
        // 添加指派人数
        if (ObjectUtils.isNotNull(userIdList)) {
            plan.setPeopleNum(userIdList.size());
        }
        Long num = planDao.updatePlan(plan);
        PlanDTO _plan = queryPlanById(plan.getId());
        // 计划启动成功
        if (ObjectUtils.isNotNull(num)) {

            boolean flag = true;
            Long courseId = null;

            List<PhaseDetailProgress> progressList = new ArrayList<>();
            Phase phase = new Phase();
            phase.setPlanId(plan.getId());
            // 获取阶段信息
            List<Phase> phaseList = phaseDao.getPhaseList(phase);
            if (ObjectUtils.isNotNull(phaseList)) {
                for (Phase _phase : phaseList) {
                    PhaseDetail phaseDetail = new PhaseDetail();
                    phaseDetail.setPhaseId(_phase.getId());
                    // 获取详情信息
                    List<PhaseDetail> phaseDetailList = phaseDetailDao.getPhaseDetailList(phaseDetail);
                    if (ObjectUtils.isNotNull(phaseDetailList)) {
                        for (PhaseDetail _phaseDetail : phaseDetailList) {
                            if (flag && _phaseDetail.getType() == 2) {
                                courseId = _phaseDetail.getOtherId();
                                flag = false;
                            }
                            for (Long userId : userIdList) {
                                PhaseDetailProgress progress = new PhaseDetailProgress();
                                // 阶段详细id
                                progress.setPhaseDetailId(_phaseDetail.getId());
                                // 阶段id
                                progress.setPhaseId(_phase.getId());
                                // 开始时间
                                progress.setStartTime(plan.getBeginTime());
                                // 结束时间
                                progress.setEndTime(plan.getEndTime());
                                // 类型
                                progress.setType(_phaseDetail.getType().intValue());
                                // 状态
                                progress.setStatus(0);
                                // 名称
                                progress.setName(_phaseDetail.getDetailName());
                                // 总时长
                                progress.setTotal(_phaseDetail.getHours().intValue());
                                // 完成时长
                                progress.setComplete(0);
                                // 用户id
                                progress.setUserId(userId);
                                // 添加到集合中
                                progressList.add(progress);
                            }
                        }
                    }
                }

                // 课程记录是否为空
                if (ObjectUtils.isNotNull(progressList)) {
                    // 批量添加课程记录
                    phaseDetailProgressDao.addProgressBatch(progressList);
                }

                List<PlanRecord> planRecordList = new ArrayList<>();

                // 添加用户计划记录
                for (Long userId : userIdList) {
                    PlanRecord planRecord = new PlanRecord();
                    // 计划id
                    planRecord.setPlanId(plan.getId());
                    // 用户id
                    planRecord.setUserId(userId);
                    // 总学时
                    planRecord.setTotalTime(_plan.getTotalTime());
                    // 完成学时
                    planRecord.setCompleteTime(0);
                    // 课程id
                    planRecord.setCourseId(courseId);
                    // 将记录添加到集合中
                    planRecordList.add(planRecord);
                }
                if (ObjectUtils.isNotNull(planRecordList)) {
                    //批量添加用户计划记录
                    this.planRecordDao.addPlanRecordBatch(planRecordList);
                }

            }

        }
        return num;
    }

    public Plan getPlanById(Long id) {
        return planDao.getPlanById(id);
    }

    @Override
    public PlanDTO queryPlanById(Long id) {
        return this.planDao.queryPlanById(id);
    }

    @Override
    public List<PlanDTO> queryPlanList(QueryPlan queryPlan, PageEntity page) {
        return this.planDao.queryPlanList(queryPlan, page);
    }

    public Plan getPlan(Plan plan) {
        return planDao.getPlan(plan);
    }

    public List<QueryPlan> getPlanList(QueryPlan queryPlan, PageEntity page) {
        return planDao.getPlanList(queryPlan, page);
    }

    @Override
    public void updateCopyPlan(Plan plan) {
        Long oldPlanId = plan.getId();
        // 复制计划
        plan.setId(null);
        this.addPlan(plan);
        Long newPlanId = plan.getId();
        // 复制阶段详情
        Phase phase = new Phase();
        phase.setPlanId(oldPlanId);
        List<Phase> phaseList = phaseDao.getPhaseList(phase);
        if (ObjectUtils.isNotNull(phaseList)) {
            for (Phase _phase : phaseList) {
                long oldPhaseId = _phase.getId();
                _phase.setPlanId(newPlanId);
                _phase.setId(0L);
                phaseDao.createPhase(_phase);
                long newPhaseId = _phase.getId();
                PhaseDetail phaseDetail = new PhaseDetail();
                phaseDetail.setPhaseId(oldPhaseId);
                List<PhaseDetail> phaseDetailList = phaseDetailDao.getPhaseDetailList(phaseDetail);
                if (ObjectUtils.isNotNull(phaseDetailList)) {
                    for (PhaseDetail _phaseDetail : phaseDetailList) {
                        _phaseDetail.setId(0L);
                        _phaseDetail.setPhaseId(newPhaseId);
                    }
                    phaseDetailDao.addPhaseDetailList(phaseDetailList);
                }
            }
        }
        //复制个人计划
        if (plan.getType() == 0) {
            //初始任务员工实体
            PlanMiddleUser planMiddleUser = new PlanMiddleUser();
            planMiddleUser.setPlanId(oldPlanId);//设置任务编号
            List<PlanMiddleUser> planMiddleUsers = planMiddleUserDao.getPlanUserById(planMiddleUser);
            if (ObjectUtils.isNotNull(planMiddleUsers)) {
                for (PlanMiddleUser _planMiddleUser : planMiddleUsers) {
                    _planMiddleUser.setId(null);
                    _planMiddleUser.setPlanId(newPlanId);
                }
                planMiddleUserDao.batchAddPlanMiddleUser(planMiddleUsers);
            }
            //复制部门计划
        } else if (plan.getType() == 1) {
            //初始任务部门实体
            PlanMiddleGroup planMiddleGroup = new PlanMiddleGroup();
            planMiddleGroup.setPlanId(oldPlanId);//设置任务编号
            List<PlanMiddleGroup> planMiddleGroups = planMiddleGroupDao.getPlanGroupList(planMiddleGroup);
            if (ObjectUtils.isNotNull(planMiddleGroups)) {
                for (PlanMiddleGroup _planMiddleGroup : planMiddleGroups) {
                    _planMiddleGroup.setId(null);
                    _planMiddleGroup.setPlanId(newPlanId);
                }
                planMiddleGroupDao.batchAddPlanMiddleGroup(planMiddleGroups);
            }
        }
    }

    @Override
    public void updateCompleteNum(Long id) {
        this.planDao.updateCompleteNum(id);
    }
}