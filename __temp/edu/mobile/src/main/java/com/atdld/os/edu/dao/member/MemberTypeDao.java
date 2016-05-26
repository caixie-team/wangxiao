package com.atdld.os.edu.dao.member;
import java.util.List;

import com.atdld.os.edu.entity.member.MemberType;

/**
 * MemberType管理接口
 * User:
 * Date: 2014-09-26
 */
public interface MemberTypeDao {

	/**
     * 修改MemberType
     * @param MemberType 要修改的MemberType
     */
    public void updateMemberType(MemberType memberType);

    /**
     * 根据id获取单个MemberType对象
     * @param id 要查询的id
     * @return MemberType
     */
    public MemberType getMemberTypeById(Long id);

    /**
     * 添加会员类型
     * @param MemberType
     */
    public void addMemberType(MemberType memberType);
    /**
     * 停用启用会员类型
     * @param id
     */
    public void updateMemberTypeStatus(MemberType memberType);
    /**
     * 会员类型集合
     * @return
     */
    public List<MemberType> getMemberTypes();
    /**
     * Web会员类型集合
     * @return
     */
    public List<MemberType> getWebMemberTypes();
    /**
     * 课程的会员类型集合
     * @return
     */
    public List<MemberType> getMemberTypesBycourse(Long courseId);
}