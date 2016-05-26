package io.wangxiao.edu.home.service.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.Plan;
import io.wangxiao.edu.home.entity.plan.PlanDTO;
import io.wangxiao.edu.home.entity.plan.QueryPlan;

import java.util.List;

/**
 * plan管理接口
 */
public interface PlanService {

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
     * 复制计划
     *
     * @param plan
     * @return
     */
    void updateCopyPlan(Plan plan);

    /**
     * 更新计划完成人数
     *
     * @param id 计划id
     */
    void updateCompleteNum(Long id);
}