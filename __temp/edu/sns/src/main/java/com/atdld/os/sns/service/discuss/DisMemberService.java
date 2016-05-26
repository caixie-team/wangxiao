package com.atdld.os.sns.service.discuss;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.sns.entity.discuss.DisMember;

/**
 * @author :
 * @ClassName com.atdld.os.sns.service.discuss.DisMemberService
 * @description 小组成员Service
 * @Create Date : 2013-12-11 下午1:45:24
 */

public interface DisMemberService {
    /**
     * 添加小组成员
     *
     * @param disMember 用户的Id,小组的Id
     * @throws Exception
     */
    public String addDisMember(DisMember disMember) throws Exception;

    /**
     * 查看所有成员列表
     *
     * @param groupId    小组Id
     * @param pageEntity 分页参数
     * @return List<DisMember> 小组成员list
     * @throws Exception
     */
    public List<DisMember> queryDisMemberByGroupId(Long groupId, PageEntity pageEntity) throws Exception;

    /**
     * 按照小组成员加入时间排序
     *
     * @param groupId 小组Id
     * @return List<DisMember> 小组成员
     * @throws Exception
     */
    public List<DisMember> queryDisMemberByaddTime(Long groupId) throws Exception;

    /**
     * 退出小组
     *
     * @param groupId 小组Id
     * @throws Exception
     */
    public void updateDisGroupExit(Long groupId) throws Exception;

    /**
     * 退出小组删除该用户信息
     *
     * @param id
     */
    public String deleteDisGroupExit(DisMember disMember) throws Exception;

    /**
     * 通过小组id查出该小组的管理员
     *
     * @param groupId 小组id
     * @return List<DisMember>
     * @throws Exception
     */
    public List<DisMember> queryDisMemberAdministratorByGroup(Long groupId) throws Exception;

    /**
     * 通过group和cusId查询是否存在
     *
     * @param disMember
     * @return int 返回存在的条数
     * @throws Exception
     */
    public Integer queryDisMemberByGroupIdAndCusId(DisMember disMember) throws Exception;

    /**
     * 通过小组id和用户id查询用户是否有权限操作转让小组
     *
     * @param disMember
     * @return
     */
    public Integer queryMemberTransferId(DisMember disMember) throws Exception;

    /**
     * 查询成员
     *
     * @param disMember
     * @return List<DisMember>
     */
    public List<DisMember> querydisMemberByzr(DisMember disMember) throws Exception;

    /**
     * 转让小组
     *
     * @param groupId 小组id
     * @return
     */
    public String updateMemberTransferId(DisMember disMember, Long cusId) throws Exception;

    /**
     * 更新用户的类型
     *
     * @param disMember
     */
    public void updateCustomerType(DisMember disMember) throws Exception;

    /**
     * 查询用户所属小组id
     *
     * @param cusId
     * @return
     */
    public List<DisMember> queryGroupIdByCusId(Long cusId) throws Exception;

    /**
     * 查询用户信息
     *
     * @param disMember
     * @return
     */
    public DisMember queryDisMemebrDetail(DisMember disMember) throws Exception;

    /**
     * 人员每增加一次，dis_group成员则加1
     *
     * @param groupId 小组id
     * @param count   +1
     */
    public void updateDisGroupMemberCount(Long groupId, int count) throws Exception;

    /**
     * 删除小组下的所有成员
     *
     * @param groupId
     * @throws Exception
     */
    public void deleteAllMemberByGroupId(Long groupId) throws Exception;

    /**
     * 提拔管理员解除管理员
     *
     * @param disMember
     * @return
     * @throws Exception
     */
    public String updatePromoteUserTransfer(DisMember disMember) throws Exception;
}
