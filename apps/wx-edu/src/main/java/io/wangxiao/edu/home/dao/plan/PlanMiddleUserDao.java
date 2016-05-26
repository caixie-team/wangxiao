package io.wangxiao.edu.home.dao.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.PlanMiddleUser;
import io.wangxiao.edu.home.entity.plan.PlanMiddleUserDTO;

import java.util.List;

/**
 * PlanMiddleUser管理接口
 */
public interface PlanMiddleUserDao {

    /**
     * 添加PlanMiddleUser
     *
     * @param planMiddleUser 要添加的PlanMiddleUser
     * @return id
     */
    Long addPlanMiddleUser(PlanMiddleUser planMiddleUser);

    /**
     * 根据id删除一个PlanMiddleUser
     *
     * @param id 要删除的id
     */
    void deletePlanMiddleUserById(Long id);

    /**
     * 修改PlanMiddleUser
     *
     * @param planMiddleUser 要修改的PlanMiddleUser
     */
    void updatePlanMiddleUser(PlanMiddleUser planMiddleUser);

    /**
     * 根据id获取单个PlanMiddleUser对象
     *
     * @param id 要查询的id
     * @return PlanMiddleUser
     */
    PlanMiddleUser getPlanMiddleUserById(Long id);

    /**
     * 根据条件获取PlanMiddleUser列表
     *
     * @param planMiddleUser 查询条件
     * @return List<PlanMiddleUser>
     */
    List<PlanMiddleUser> getPlanMiddleUserList(PlanMiddleUser planMiddleUser);

    /**
     * 批量添加员工
     */
    void batchAddPlanMiddleUser(List<PlanMiddleUser> planMiddleUsers);

    /**
     * 任务下的员工
     *
     * @param planMiddleUser
     * @return
     */
    List<PlanMiddleUser> getPlanUserById(PlanMiddleUser planMiddleUser);

    /**
     * 任务下的员工
     *
     * @param planMiddleUser
     * @return
     */
    List<PlanMiddleUserDTO> getPlanUserDTOList(PlanMiddleUser planMiddleUser, PageEntity page);

    /**
     * 根据小组id查询任务下的员工
     *
     * @param groupId
     * @param page
     * @return
     */
    List<PlanMiddleUserDTO> getPlanUserDTOListByGroupIdAndPlanId(Long groupId, Long planId, PageEntity page);

    /**
     * 根据计划id获取计划指派人员id
     *
     * @param id
     * @return
     */
    List<Long> getPlanUserId(Long id);
}