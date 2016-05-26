package com.atdld.os.sns.dao.impl.discuss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.sns.dao.discuss.DisMemberDao;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.DisMember;

/**
 * @author
 * @ClassName DisMemberDaoImpl
 * @package com.atdld.os.sns.dao.impl.discuss
 * @description 小组成员DaoImpl
 * @Create Date: 2013-12-5 下午3:19:15
 */
@Repository("disMemberDao")
public class DisMemberDaoImpl extends GenericDaoImpl implements DisMemberDao {
    /**
     * 添加小组成员
     *
     * @param disMember 用户的Id,小组的Id
     */
    public Long addDisMember(DisMember disMember) {
        return this.insert("DisMemberMapper.addDisMember", disMember);
    }

    /**
     * 人员每增加一次，dis_group成员则加1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateDisGroupMemberCount(Long groupId, int count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupId", groupId);
        map.put("count", count);
        this.update("DisMemberMapper.updateDisGroupMemberCount", map);
    }

    /**
     * 查看所有成员列表
     *
     * @param groupId    小组组Id
     * @param pageEntity 分页参数
     * @return List<DisMember> 小组成员list
     */
    public List<DisMember> queryDisMemberByGroupId(Long groupId, PageEntity pageEntity) {
        return this.queryForListPage("DisMemberMapper.queryDisMemberByGroupId", groupId, pageEntity);
    }

    /**
     * 按照小组成员加入时间排序,在某个小组
     *
     * @param groupId 小组组Id
     * @return List<DisMember> 小组成员list
     */
    public List<DisMember> queryDisMemberByaddTime(Long groupId) {
        return this.selectList("DisMemberMapper.queryDisMemberByaddTime", groupId);
    }

    /**
     * 退出小组
     *
     * @param groupId
     */
    public void updateDisGroupExit(Long groupId) {
        this.update("DisMemberMapper.updateDisGroupExit", groupId);
    }

    /**
     * 退出小组删除该用户信息
     *
     * @param id
     */
    public Long deleteDisGroupExit(DisMember disMember) {
        return this.delete("DisMemberMapper.deleteDisGroupExit", disMember);
    }

    /**
     * 通过小组id查出该小组的管理员
     *
     * @param groupId 小组id
     * @return List<DisMember>
     * @throws Exception
     */
    public List<DisMember> queryDisMemberAdministratorByGroup(Long groupId) {
        return this.selectList("DisMemberMapper.queryDisMemberAdministratorByGroup", groupId);// 通过小组id查出该小组的管理员
    }

    /**
     * 通过group和cusId查询是否存在
     *
     * @param disMember
     * @return int 返回存在的条数
     * @throws Exception
     */
    public Integer queryDisMemberByGroupIdAndCusId(DisMember disMember) {
        return this.selectOne("DisMemberMapper.queryDisMemberByGroupIdAndCusId", disMember);
    }

    /**
     * 通过小组id和用户id查询用户是否有权限操作转让小组
     *
     * @param disMember
     * @return
     */
    public Integer queryMemberTransferId(DisMember disMember) {
        List<Integer> integerList = this.selectList("DisMemberMapper.queryMemberTransferId", disMember);
        if (integerList != null && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 查询成员
     *
     * @param disMember
     * @return List<DisMember>
     */
    public List<DisMember> querydisMemberByzr(DisMember disMember) {
        return this.selectList("DisMemberMapper.querydisMemberByzr", disMember);
    }

    /**
     * @param groupId 小组id
     * @return
     */
    public Long updateMemberTransferId(DisMember disMember, Long cusId) {
        return this.update("DisMemberMapper.updateMemberTransferId", disMember);
    }

    /**
     * 更新用户的类型
     *
     * @param disMember
     */
    public void updateCustomerType(DisMember disMember) {
        this.update("DisMemberMapper.updateCustomerType", disMember);

    }

    /**
     * 查询用户所属小组id
     *
     * @param cusId
     * @return
     */
    public List<DisMember> queryGroupIdByCusId(Long cusId) {
        return this.selectList("DisMemberMapper.queryGroupIdByCusId", cusId);
    }

    /**
     * 查询用户信息
     *
     * @param disMember
     * @return
     */
    public DisMember queryDisMemebrDetail(DisMember disMember) {
        List<DisMember> disMemberList = this.selectList("DisMemberMapper.queryDisMemebrDetail", disMember);
        if (disMemberList != null && disMemberList.size() > 0) {
            return disMemberList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 删除小组下的所有成员
     *
     * @param groupId
     */
    public void deleteAllMemberByGroupId(Long groupId) {
        this.delete("DisMemberMapper.deleteAllMemberByGroupId", groupId);
    }

    /**
     * 提拔管理员解除管理员
     *
     * @param disMember
     * @return
     */
    public Long updatePromoteUserTransfer(DisMember disMember) {
        return this.update("DisMemberMapper.updatePromoteUserTransfer", disMember);
    }
}
