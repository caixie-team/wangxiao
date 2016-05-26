package io.wangxiao.edu.home.service.impl.member;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.wangxiao.edu.home.dao.member.MemberTypeDao;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.service.member.MemberTypeService;

/**
 * MemberType管理接口
 */
@Service("memberTypeService")
public class MemberTypeServiceImpl implements MemberTypeService {

    @Autowired
    private MemberTypeDao memberTypeDao;

    /**
     * 修改MemberType
     *
     * @param MemberType 要修改的MemberType
     */
    public void updateMemberType(MemberType memberType) {
        memberTypeDao.updateMemberType(memberType);
    }

    /**
     * 根据id获取单个MemberType对象
     *
     * @param id 要查询的id
     * @return MemberType
     */
    public MemberType getMemberTypeById(Long id) {
        return memberTypeDao.getMemberTypeById(id);
    }

    /**
     * 会员类型集合
     *
     * @return
     */
    public List<MemberType> getMemberTypes() {
        return memberTypeDao.getMemberTypes();
    }

    /**
     * Web会员类型集合
     *
     * @return
     */
    public List<MemberType> getWebMemberTypes() {
        return memberTypeDao.getWebMemberTypes();
    }

    /**
     * 添加会员类型
     *
     * @param MemberType
     */
    public void addMemberType(MemberType memberType) {
        memberTypeDao.addMemberType(memberType);
    }

    /**
     * 停用启用会员类型
     *
     * @param id
     */
    public void updateMemberTypeStatus(MemberType memberType) {
        memberTypeDao.updateMemberTypeStatus(memberType);
    }

    /**
     * 课程的会员类型集合
     *
     * @return
     */
    public List<MemberType> getMemberTypesBycourse(Long courseId) {
        return memberTypeDao.getMemberTypesBycourse(courseId);
    }
}