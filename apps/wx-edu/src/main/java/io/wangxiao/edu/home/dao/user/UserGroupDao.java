package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserGroup;

import java.util.List;

public interface UserGroupDao {


    /**
     * 添加学员组信息
     *
     * @param userGroup
     * @return
     */
    Long addUserGroup(UserGroup userGroup);

    /**
     * 修改学员组信息
     *
     * @param userGroup
     */
    void updateUserGroup(UserGroup userGroup);

    /**
     * 通过id查询学员组信息
     *
     * @param groupId
     * @return
     */
    UserGroup queryUserGroupById(Long groupId);

    /**
     * 查询学员组集合返回对象
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> queryUserGroupListPage(UserGroup UserGroup, PageEntity page);

    /**
     * 删除学员组
     */
    void delUserGroup(Long id);


    /**
     * 查询学员组集合返回对象(统计)
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> getUserGroupListStatistics(UserGroup UserGroup, PageEntity page);

    /**
     * 查询学员组集合
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> getUserGroupList(UserGroup UserGroup);


    List<UserGroup> getUserGroupListByIds(List<Long> ids);

    /**
     * 获取内部课程
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> getCourseDtoByGroup(Long id);

    /**
     * 获取部门的大体信息
     */
    List<UserGroup> getGroupTask(Long userid);

}