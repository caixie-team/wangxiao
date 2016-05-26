package io.wangxiao.edu.home.dao.impl.plan;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PlanDao;
import io.wangxiao.edu.home.entity.plan.Plan;
import io.wangxiao.edu.home.entity.plan.PlanDTO;
import io.wangxiao.edu.home.entity.plan.QueryPlan;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("planDao")
public class PlanDaoImpl extends GenericDaoImpl implements PlanDao {

    public Long addPlan(Plan plan) {
        return this.insert("PlanMapper.createPlan", plan);
    }

    public void deletePlanById(Long id) {
        this.delete("PlanMapper.deletePlanById", id);
    }

    public Long updatePlan(Plan plan) {
        return this.update("PlanMapper.updatePlan", plan);
    }

    public Plan getPlanById(Long id) {
        return this.selectOne("PlanMapper.getPlanById", id);
    }

    @Override
    public PlanDTO queryPlanById(Long id) {
        return this.selectOne("PlanMapper.queryPlanById", id);
    }

    @Override
    public List<PlanDTO> queryPlanList(QueryPlan queryPlan, PageEntity page) {
        return this.queryForListPage("PlanMapper.queryPlanList", queryPlan, page);
    }

    public Plan getPlan(Plan plan) {
        return this.selectOne("PlanMapper.getPlan", plan);
    }

    public List<QueryPlan> getPlanList(QueryPlan queryPlan, PageEntity page) {
        return this.queryForListPage("PlanMapper.getPlanList", queryPlan, page);
    }

    @Override
    public void updateCompleteNum(Long id) {
        this.update("PlanMapper.updateCompleteNum", id);
    }
}
