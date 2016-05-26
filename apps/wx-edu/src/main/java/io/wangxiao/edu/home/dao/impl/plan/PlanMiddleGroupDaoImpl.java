package io.wangxiao.edu.home.dao.impl.plan;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PlanMiddleGroupDao;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroupDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("planMiddleGroupDao")
public class PlanMiddleGroupDaoImpl extends GenericDaoImpl implements PlanMiddleGroupDao {

    public Long addPlanMiddleGroup(PlanMiddleGroup planMiddleGroup) {
        return this.insert("PlanMiddleGroupMapper.createPlanMiddleGroup", planMiddleGroup);
    }

    /**
     * 批量添加部门
     */
    public void batchAddPlanMiddleGroup(List<PlanMiddleGroup> planMiddleGroup) {
        this.insert("PlanMiddleGroupMapper.batchAddPlanMiddleGroup", planMiddleGroup);
    }

    public void deletePlanMiddleGroupById(Long id) {
        this.delete("PlanMiddleGroupMapper.deletePlanMiddleGroupById", id);
    }

    public void updatePlanMiddleGroup(PlanMiddleGroup planMiddleGroup) {
        this.update("PlanMiddleGroupMapper.updatePlanMiddleGroup", planMiddleGroup);
    }

    public PlanMiddleGroup getPlanMiddleGroupById(Long id) {
        return this.selectOne("PlanMiddleGroupMapper.getPlanMiddleGroupById", id);
    }

    public List<PlanMiddleGroup> getPlanMiddleGroupList(PlanMiddleGroup planMiddleGroup) {
        return this.selectList("PlanMiddleGroupMapper.getPlanMiddleGroupList", planMiddleGroup);
    }

    /**
     * 任务下的部门
     */
    public List<PlanMiddleGroup> getPlanGroupList(PlanMiddleGroup planMiddleGroup) {
        return this.selectList("PlanMiddleGroupMapper.getPlanGroupList", planMiddleGroup);
    }

    @Override
    public List<PlanMiddleGroupDTO> getPlanGroupDTOList(PlanMiddleGroup planMiddleGroup, PageEntity page) {
        return this.queryForListPage("PlanMiddleGroupMapper.getPlanGroupDTOList", planMiddleGroup, page);
    }

    @Override
    public List<Long> getPlanGroupUserId(Long planId) {
        return this.selectList("PlanMiddleGroupMapper.getPlanGroupUserId", planId);
    }
}
