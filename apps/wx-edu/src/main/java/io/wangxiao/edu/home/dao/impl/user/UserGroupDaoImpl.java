package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.user.UserGroupDao;
import io.wangxiao.edu.home.entity.user.UserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userGroupDao")
public class UserGroupDaoImpl extends GenericDaoImpl implements UserGroupDao {

    @Override
    public Long addUserGroup(UserGroup userGroup) {
        return this.insert("UserGroupMapper.addUserGroup", userGroup);
    }

    @Override
    public void updateUserGroup(UserGroup userGroup) {
        this.update("UserGroupMapper.updateUserGroup", userGroup);
    }

    @Override
    public UserGroup queryUserGroupById(Long groupId) {
        return this.selectOne("UserGroupMapper.selectUserGroupById", groupId);
    }

    @Override
    public List<UserGroup> queryUserGroupListPage(UserGroup userGroup, PageEntity page) {
        return this.queryForListPage("UserGroupMapper.selectUserGroupList", userGroup, page);
    }

    @Override
    public List<UserGroup> getUserGroupListStatistics(UserGroup userGroup, PageEntity page) {
        return this.queryForListPage("UserGroupMapper.getUserGroupListStatistics", userGroup, page);
    }

    @Override
    public List<UserGroup> getUserGroupList(UserGroup userGroup) {
        return this.selectList("UserGroupMapper.getUserGroupList", userGroup);
    }

    @Override
    public List<UserGroup> getUserGroupListByIds(List ids) {
        return this.selectList("UserGroupMapper.getUserGroupByIds", ids);
    }

    @Override
    public List<UserGroup> getCourseDtoByGroup(Long id) {
        return this.selectList("UserGroupMapper.getCourseDtoListByGroup", id);
    }

    @Override
    public List<UserGroup> getGroupTask(Long userid) {
        return this.selectList("UserGroupMapper.getGroupTaskRecord", userid);
    }

    @Override
    public void delUserGroup(Long id) {
        this.delete("UserGroupMapper.delUserGroup", id);
    }


}
