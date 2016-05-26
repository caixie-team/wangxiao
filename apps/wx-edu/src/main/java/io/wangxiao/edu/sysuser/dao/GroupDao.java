package io.wangxiao.edu.sysuser.dao;

import io.wangxiao.edu.sysuser.entity.SysGroup;

import java.util.List;

/**
 * @description 用户组管理
 */

public interface GroupDao {
    /**
     * 根据id查询Group
     *
     * @param id
     * @return Group
     */
    SysGroup getGroupById(Long id);

    /**
     * 查询所有的Group
     *
     * @return List<Group>
     */
    List<SysGroup> getGroupList();

    /**
     * 根据parentId查询Group
     *
     * @param parentId
     * @return List<Group>
     */
    List<SysGroup> getChildGroupById(Long parentId);

    /**
     * 根据id删除group
     *
     * @param id
     */
    void deleteGroupById(Long id);

    /**
     * 根据id更新group
     *
     * @param group
     */
    void updateGroup(SysGroup group);

    /**
     * 添加组
     *
     * @param group
     */
    void addGroup(SysGroup group);
}
