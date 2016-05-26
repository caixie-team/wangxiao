package io.wangxiao.edu.home.service.impl.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PlanMiddleUserDao;
import io.wangxiao.edu.home.entity.plan.PlanMiddleUser;
import io.wangxiao.edu.home.entity.plan.PlanMiddleUserDTO;
import io.wangxiao.edu.home.service.plan.PlanMiddleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("planMiddleUserService")
public class PlanMiddleUserServiceImpl implements PlanMiddleUserService {

    @Autowired
    private PlanMiddleUserDao planMiddleUserDao;

    /**
     * 添加PlanMiddleUser
     *
     * @param planMiddleUser 要添加的PlanMiddleUser
     * @return id
     */
    public Long addPlanMiddleUser(PlanMiddleUser planMiddleUser) {
        return planMiddleUserDao.addPlanMiddleUser(planMiddleUser);
    }

    /**
     * 批量添加员工
     */
    public void batchAddPlanMiddleUser(List<PlanMiddleUser> planMiddleUsers) {
        this.planMiddleUserDao.batchAddPlanMiddleUser(planMiddleUsers);
    }

    /**
     * 根据id删除一个PlanMiddleUser
     *
     * @param id 要删除的id
     */
    public void deletePlanMiddleUserById(Long id) {
        planMiddleUserDao.deletePlanMiddleUserById(id);
    }

    /**
     * 修改PlanMiddleUser
     *
     * @param planMiddleUser 要修改的PlanMiddleUser
     */
    public void updatePlanMiddleUser(PlanMiddleUser planMiddleUser) {
        planMiddleUserDao.updatePlanMiddleUser(planMiddleUser);
    }

    /**
     * 根据id获取单个PlanMiddleUser对象
     *
     * @param id 要查询的id
     * @return PlanMiddleUser
     */
    public PlanMiddleUser getPlanMiddleUserById(Long id) {
        return planMiddleUserDao.getPlanMiddleUserById(id);
    }

    /**
     * 根据条件获取PlanMiddleUser列表
     *
     * @param planMiddleUser 查询条件
     * @return List<PlanMiddleUser>
     */
    public List<PlanMiddleUser> getPlanMiddleUserList(PlanMiddleUser planMiddleUser) {
        return planMiddleUserDao.getPlanMiddleUserList(planMiddleUser);
    }

    /**
     * 任务下的员工
     */
    public List<PlanMiddleUser> getPlanUserById(PlanMiddleUser planMiddleUser) {
        return this.planMiddleUserDao.getPlanUserById(planMiddleUser);
    }

    @Override
    public List<PlanMiddleUserDTO> getPlanUserDTOList(PlanMiddleUser planMiddleUser, PageEntity page) {
        return this.planMiddleUserDao.getPlanUserDTOList(planMiddleUser, page);
    }

    @Override
    public List<PlanMiddleUserDTO> getPlanUserDTOListByGroupIdAndPlanId(Long groupId, Long planId, PageEntity page) {
        return this.planMiddleUserDao.getPlanUserDTOListByGroupIdAndPlanId(groupId, planId, page);
    }

    @Override
    public List<Long> getPlanUserId(Long id) {
        return this.planMiddleUserDao.getPlanUserId(id);
    }
}