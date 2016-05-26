package io.wangxiao.edu.home.dao.member;

import java.util.List;

import io.wangxiao.edu.home.entity.member.MemberType;

/**
 * MemberType管理接口
 */
public interface MemberTypeDao {

    /**
     * 修改MemberType
     *
     * @param MemberType 要修改的MemberType
     */
    void updateMemberType(MemberType memberType);

    /**
     * 根据id获取单个MemberType对象
     *
     * @param id 要查询的id
     * @return MemberType
     */
    MemberType getMemberTypeById(Long id);

    /**
     * 添加会员类型
     *
     * @param MemberType
     */
    void addMemberType(MemberType memberType);

    /**
     * 停用启用会员类型
     *
     * @param id
     */
    void updateMemberTypeStatus(MemberType memberType);

    /**
     * 会员类型集合
     *
     * @return
     */
    List<MemberType> getMemberTypes();

    /**
     * Web会员类型集合
     *
     * @return
     */
    List<MemberType> getWebMemberTypes();

    /**
     * 课程的会员类型集合
     *
     * @return
     */
    List<MemberType> getMemberTypesBycourse(Long courseId);
}