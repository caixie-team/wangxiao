package io.wangxiao.edu.home.dao.impl.plan;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PlanMiddleUserDao;
import io.wangxiao.edu.home.entity.plan.PlanMiddleUser;
import io.wangxiao.edu.home.entity.plan.PlanMiddleUserDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("planMiddleUserDao")
public class PlanMiddleUserDaoImpl extends GenericDaoImpl implements PlanMiddleUserDao {

    public Long addPlanMiddleUser(PlanMiddleUser planMiddleUser) {
        return this.insert("PlanMiddleUserMapper.createPlanMiddleUser", planMiddleUser);
    }

    /**
     * 批量添加员工
     */
    public void batchAddPlanMiddleUser(List<PlanMiddleUser> planMiddleUsers) {
        this.insert("PlanMiddleUserMapper.batchAddPlanMiddleUser", planMiddleUsers);
    }

    public void deletePlanMiddleUserById(Long id) {
        this.delete("PlanMiddleUserMapper.deletePlanMiddleUserById", id);
    }

    public void updatePlanMiddleUser(PlanMiddleUser planMiddleUser) {
        this.update("PlanMiddleUserMapper.updatePlanMiddleUser", planMiddleUser);
    }

    public PlanMiddleUser getPlanMiddleUserById(Long id) {
        return this.selectOne("PlanMiddleUserMapper.getPlanMiddleUserById", id);
    }

    public List<PlanMiddleUser> getPlanMiddleUserList(PlanMiddleUser planMiddleUser) {
        return this.selectList("PlanMiddleUserMapper.getPlanMiddleUserList", planMiddleUser);
    }

    /**
     * 任务下的员工
     */
    public List<PlanMiddleUser> getPlanUserById(PlanMiddleUser planMiddleUser) {
        return this.selectList("PlanMiddleUserMapper.getPlanUserById", planMiddleUser);
    }

    @Override
    public List<PlanMiddleUserDTO> getPlanUserDTOList(PlanMiddleUser planMiddleUser, PageEntity page) {
        return this.queryForListPage("PlanMiddleUserMapper.getPlanUserDTOList", planMiddleUser, page);
    }

    @Override
    public List<PlanMiddleUserDTO> getPlanUserDTOListByGroupIdAndPlanId(Long groupId, Long planId, PageEntity page) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("planId", planId);
        return this.queryForListPage("PlanMiddleUserMapper.getPlanUserDTOListByGroupIdAndPlanId", map, page);
    }

    @Override
    public List<Long> getPlanUserId(Long id) {
        return this.selectList("PlanMiddleUserMapper.getPlanUserId", id);
    }
}
