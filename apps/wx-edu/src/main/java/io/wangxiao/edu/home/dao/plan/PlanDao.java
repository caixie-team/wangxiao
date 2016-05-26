package io.wangxiao.edu.home.dao.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.Plan;
import io.wangxiao.edu.home.entity.plan.PlanDTO;
import io.wangxiao.edu.home.entity.plan.QueryPlan;

import java.util.List;

/**
 * Plan管理接口
 */
public interface PlanDao {

    /**
     * 添加Plan
     *
     * @param plan
     * @return
     */
    Long addPlan(Plan plan);

    /**
     * 根据id删除一个Plan
     *
     * @param id
     */
    void deletePlanById(Long id);

    /**
     * 修改Plan
     *
     * @param plan
     * @return
     */
    Long updatePlan(Plan plan);

    /**
     * 根据id获取单个Plan对象
     *
     * @param id
     * @return
     */
    Plan getPlanById(Long id);

    /**
     * 根据id获取单个Plan对象
     *
     * @param id
     * @return
     */
    PlanDTO queryPlanById(Long id);

    /**
     * 获取计划列表
     *
     * @param queryPlan
     * @return
     */
    List<PlanDTO> queryPlanList(QueryPlan queryPlan, PageEntity page);

    /**
     * 获取单个Plan对象
     *
     * @param plan
     * @return
     */
    Plan getPlan(Plan plan);

    /**
     * 根据条件获取Plan列表
     *
     * @param queryPlan
     * @param page
     * @return
     */
    List<QueryPlan> getPlanList(QueryPlan queryPlan, PageEntity page);

    /**
     * 更新计划完成人数
     *
     * @param id
     */
    void updateCompleteNum(Long id);
}