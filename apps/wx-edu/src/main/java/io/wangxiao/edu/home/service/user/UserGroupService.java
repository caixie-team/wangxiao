package io.wangxiao.edu.home.service.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserGroup;

import java.util.List;

public interface UserGroupService {


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
     * 删除学员组
     */
    void delUserGroup(Long id);


    /**
     * 检索学员组
     *
     * @param userGroup
     * @return
     */
    boolean checkUserGroup(UserGroup userGroup);


    /**
     * 查询学员组集合返回对象
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> queryUserGroupListPage(UserGroup UserGroup, PageEntity page);

    /**
     * 查询学员组集合返回对象(统计)
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> getUserGroupListStatistics(UserGroup UserGroup, PageEntity page);


    //public List<JwClassType> getClassTypeFromJsonArray(String classDate);


    /**
     * 查询学员组集合
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> getUserGroupList(UserGroup UserGroup);


    /**
     * 查询学员组集合
     *
     * @param UserGroup
     * @param page
     * @return
     */
    List<UserGroup> getUserGroupListByIds(List<Long> ids);

    /**
     * 获取部门的大体信息
     */
    List<UserGroup> getGroupTask(Long userid);

    /**
     * 获取部门学习数量
     *
     * @param id 用户id
     * @return
     */
    List<UserGroup> getGroupLearningNumByUserId(Long id);
}