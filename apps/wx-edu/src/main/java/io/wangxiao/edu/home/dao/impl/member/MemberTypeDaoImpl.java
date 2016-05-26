package io.wangxiao.edu.home.dao.impl.member;


import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.member.MemberTypeDao;
import io.wangxiao.edu.home.entity.member.MemberType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("memberTypeDao")
public class MemberTypeDaoImpl extends GenericDaoImpl implements MemberTypeDao {


    public void updateMemberType(MemberType memberType) {
        this.update("MemberTypeMapper.updateMemberType", memberType);
    }

    public MemberType getMemberTypeById(Long id) {
        return this.selectOne("MemberTypeMapper.getMemberTypeById", id);
    }

    /**
     * 会员类型集合
     *
     * @return
     */
    public List<MemberType> getMemberTypes() {
        return this.selectList("MemberTypeMapper.getMemberTypes", 0);
    }

    /**
     * Web会员类型集合
     *
     * @return
     */
    public List<MemberType> getWebMemberTypes() {
        return this.selectList("MemberTypeMapper.getWebMemberTypes", 0);
    }

    /**
     * 添加会员类型
     *
     * @param MemberType
     */
    public void addMemberType(MemberType memberType) {
        this.insert("MemberTypeMapper.addMemberType", memberType);
    }

    /**
     * 停用启用会员类型
     *
     * @param id
     */
    public void updateMemberTypeStatus(MemberType memberType) {
        this.update("MemberTypeMapper.updateMemberTypeStatus", memberType);
    }

    /**
     * 课程的会员类型集合
     *
     * @return
     */
    public List<MemberType> getMemberTypesBycourse(Long courseId) {
        return this.selectList("MemberTypeMapper.getMemberTypesBycourse", courseId);
    }
}
