package io.wangxiao.edu.home.service.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroupDTO;

import java.util.List;

/**
 * PlanMiddleGroup管理接口
 */
public interface PlanMiddleGroupService {

    /**
     * 添加PlanMiddleGroup
     *
     * @param planMiddleGroup 要添加的PlanMiddleGroup
     * @return id
     */
    Long addPlanMiddleGroup(PlanMiddleGroup planMiddleGroup);

    /**
     * 批量添加部门
     *
     * @param planMiddleGroups
     * @return
     */
    void batchAddPlanMiddleGroup(List<PlanMiddleGroup> planMiddleGroups);

    /**
     * 根据id删除一个PlanMiddleGroup
     *
     * @param id 要删除的id
     */
    void deletePlanMiddleGroupById(Long id);

    /**
     * 修改PlanMiddleGroup
     *
     * @param planMiddleGroup 要修改的PlanMiddleGroup
     */
    void updatePlanMiddleGroup(PlanMiddleGroup planMiddleGroup);

    /**
     * 根据id获取单个PlanMiddleGroup对象
     *
     * @param id 要查询的id
     * @return PlanMiddleGroup
     */
    PlanMiddleGroup getPlanMiddleGroupById(Long id);

    /**
     * 根据条件获取PlanMiddleGroup列表
     *
     * @param planMiddleGroup 查询条件
     * @return List<PlanMiddleGroup>
     */
    List<PlanMiddleGroup> getPlanMiddleGroupList(PlanMiddleGroup planMiddleGroup);

    /**
     * 任务下的部门
     *
     * @param planMiddleGroup
     * @return
     */
    List<PlanMiddleGroup> getPlanGroupList(PlanMiddleGroup planMiddleGroup);

    /**
     * 任务下的部门
     *
     * @param planMiddleGroup
     * @param page
     * @return
     */
    List<PlanMiddleGroupDTO> getPlanGroupDTOList(PlanMiddleGroup planMiddleGroup, PageEntity page);

    /**
     * 根据计划id获取所有小组人员id
     *
     * @param planId
     * @return
     */
    List<Long> getPlanGroupUserId(Long planId);
}