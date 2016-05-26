package io.wangxiao.edu.sysuser.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.wangxiao.edu.sysuser.dao.GroupDao;
import io.wangxiao.edu.sysuser.entity.SysGroup;
import io.wangxiao.edu.sysuser.service.GroupService;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupDao groupDao;

    /**
     * 根据id查询Group
     *
     * @param Long id
     * @return Group
     */
    public SysGroup getGroupById(Long id) {
        return groupDao.getGroupById(id);

    }

    /**
     * 根据parentId查询Group
     *
     * @param parentId
     * @return List<Group>
     */
    public List<SysGroup> getChildGroupById(Long parentId) {
        return groupDao.getChildGroupById(parentId);
    }

    /**
     * 根据所有的Group
     *
     * @return List<Group>
     */
    public List<SysGroup> getGroupList() {
        return groupDao.getGroupList();
    }

    /**
     * 根据id删除group
     *
     * @param id
     */
    public void deleteGroupById(Long id) {
        groupDao.deleteGroupById(id);
    }

    /**
     * 删除多个groupid,以逗号间隔参数
     *
     * @param groupIds
     */
    public void deleteGroups(String groupIds) {
        if (groupIds == null || "".equals(groupIds.trim())) {
            return;
        }
        groupIds = groupIds.replaceAll(" ", "");
        String[] groupIdsArray = groupIds.split(",");
        for (String aGroupIdsArray : groupIdsArray) {
            SysGroup group = groupDao.getGroupById(Long.valueOf(aGroupIdsArray));
            group.setStatus(SysGroup.GROUP_DELETE_STATUS);
            // 更新状态为删除
            groupDao.updateGroup(group);
        }

    }

    /**
     * 添加组
     *
     * @param group
     */
    public void addGroup(SysGroup group) {
        group.setCreateTime(new Date());
        group.setUpdateTime(new Date());
        groupDao.addGroup(group);
    }

    /**
     * 修改组
     *
     * @param group
     */
    public void updateGroup(SysGroup group) {
        groupDao.updateGroup(group);
    }

}
