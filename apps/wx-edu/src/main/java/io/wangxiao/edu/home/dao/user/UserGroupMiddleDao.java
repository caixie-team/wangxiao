package io.wangxiao.edu.home.dao.user;

import java.util.List;
import java.util.Map;

import io.wangxiao.edu.home.entity.user.UserGroupMiddle;

public interface UserGroupMiddleDao {

    /**
     * 批量添加课程岗位
     *
     * @param courseId 课程id
     * @param groupIds 岗位id
     */
    void batchGroupMiddleCourse(Long courseId, String groupIds);

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
     * @param userId
     * @return
     */
    List<UserGroupMiddle> getUserGroupByUserId(Long userId);

    /**
     * @param userId
     * @return
     */
    UserGroupMiddle getUserGroupUserId(Long userId);

    /**
     * 查询组中有多少个学员
     *
     * @param userId
     * @return
     */
    Long getUserGroupCount(Long groupid);

    /**
     * 根据组id获取学员组信息
     *
     * @param groupId
     * @return
     */
    List<UserGroupMiddle> getUserGroupByGourpId(Long groupId);

    /**
     * 根据课程id删除岗位课程
     *
     * @param courseId
     */
    void deleteCourseGroupMiddle(Long courseId);

    /**
     * 获取所有小组课程id
     *
     * @param userId 用户id
     * @return
     */
    List<Long> getCourseIdList(Long userId);

    /**
     * 批量删除小组课程
     *
     * @param map
     */
    void deleteGroupCourseByCourseId(Map<String, Object> map);
}