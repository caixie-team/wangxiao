package io.wangxiao.edu.home.service.impl.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.plan.PhaseDetailProgressDao;
import io.wangxiao.edu.home.dao.plan.PlanMiddleGroupDao;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroupDTO;
import io.wangxiao.edu.home.service.plan.PlanMiddleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PlanMiddleGroup管理接口
 */
@Service("planMiddleGroupService")
public class PlanMiddleGroupServiceImpl implements PlanMiddleGroupService {

    @Autowired
    private PlanMiddleGroupDao planMiddleGroupDao;
    @Autowired
    private PhaseDetailProgressDao phaseDetailProgressDao;

    /**
     * 添加PlanMiddleGroup
     *
     * @param planMiddleGroup 要添加的PlanMiddleGroup
     * @return id
     */
    public Long addPlanMiddleGroup(PlanMiddleGroup planMiddleGroup) {
        return planMiddleGroupDao.addPlanMiddleGroup(planMiddleGroup);
    }

    /**
     * 批量添加部门
     */
    public void batchAddPlanMiddleGroup(List<PlanMiddleGroup> planMiddleGroups) {
        planMiddleGroupDao.batchAddPlanMiddleGroup(planMiddleGroups);
    }

    /**
     * 根据id删除一个PlanMiddleGroup
     *
     * @param id 要删除的id
     */
    public void deletePlanMiddleGroupById(Long id) {
        planMiddleGroupDao.deletePlanMiddleGroupById(id);
    }

    /**
     * 修改PlanMiddleGroup
     *
     * @param planMiddleGroup 要修改的PlanMiddleGroup
     */
    public void updatePlanMiddleGroup(PlanMiddleGroup planMiddleGroup) {
        planMiddleGroupDao.updatePlanMiddleGroup(planMiddleGroup);
    }

    /**
     * 根据id获取单个PlanMiddleGroup对象
     *
     * @param id 要查询的id
     * @return PlanMiddleGroup
     */
    public PlanMiddleGroup getPlanMiddleGroupById(Long id) {
        return planMiddleGroupDao.getPlanMiddleGroupById(id);
    }

    /**
     * 根据条件获取PlanMiddleGroup列表
     *
     * @param planMiddleGroup 查询条件
     * @return List<PlanMiddleGroup>
     */
    public List<PlanMiddleGroup> getPlanMiddleGroupList(PlanMiddleGroup planMiddleGroup) {
        return planMiddleGroupDao.getPlanMiddleGroupList(planMiddleGroup);
    }

    /**
     * 任务下的部门
     */
    public List<PlanMiddleGroup> getPlanGroupList(PlanMiddleGroup planMiddleGroup) {
        return this.planMiddleGroupDao.getPlanGroupList(planMiddleGroup);
    }

    @Override
    public List<PlanMiddleGroupDTO> getPlanGroupDTOList(PlanMiddleGroup planMiddleGroup, PageEntity page) {
        List<PlanMiddleGroupDTO> planGroupDTOList = this.planMiddleGroupDao.getPlanGroupDTOList(planMiddleGroup, page);
        if (ObjectUtils.isNotNull(planGroupDTOList)) {
            for (PlanMiddleGroupDTO planGroup : planGroupDTOList) {
                int groupComplete = phaseDetailProgressDao.getGroupComplete(planGroup);
                planGroup.setComplete(groupComplete);
            }
        }
        return planGroupDTOList;
    }

    @Override
    public List<Long> getPlanGroupUserId(Long planId) {
        return this.planMiddleGroupDao.getPlanGroupUserId(planId);
    }
}