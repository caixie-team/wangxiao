package io.wangxiao.edu.home.service.user;

import java.util.List;

import io.wangxiao.edu.home.entity.user.UserGroupMiddle;


public interface UserGroupMiddleService {

    /**
     * 添加组学员
     *
     * @param userGroupMiddle
     */
    void addUserGroupMiddle(List<UserGroupMiddle> userGroupMiddle);

    /**
     * 删除组中学员
     *
     * @param userGroupMiddle
     */
    void deleteUserGroupMiddle(UserGroupMiddle userGroupMiddle);


    /**
     * 删除组中学员
     *
     * @param userGroupMiddle
     */
    void delteUserGroupMiddleByUserId(UserGroupMiddle userGroupMiddle);

    /**
     * 通过学员id获取学员组信息
     *
     * @param UserId
     * @return
     */
    List<UserGroupMiddle> getUserGroupByUserId(Long UserId);

    /**
     * 查询组中有多少个学员
     *
     * @param userId
     * @return
     */
    Long getUserGroupCount(Long groupid);

    /**
     * @param userId
     * @return
     */
    UserGroupMiddle getUserGroupUserId(Long userId);

    /**
     * 根据组id获取学员组信息
     *
     * @param groupId
     * @return
     */
    List<UserGroupMiddle> getUserGroupByGourpId(Long groupId);

    /**
     * 批量删除小组课程
     *
     * @param groupId
     * @param ids
     */
    void deleteGroupCourseByCourseId(Long groupId, String ids);

}