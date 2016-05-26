package io.wangxiao.edu.sysuser.dao.impl;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.sysuser.dao.GroupDao;
import io.wangxiao.edu.sysuser.entity.SysGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("groupDao")
public class GroupDaoImpl extends GenericDaoImpl implements GroupDao {
    /**
     * 根据id查询Group
     *
     * @param id
     * @return Group
     */
    public SysGroup getGroupById(Long id) {
        return this.selectOne("GroupMapper.getGroupById", id);

    }

    /**
     * 查询所有的Group
     *
     * @return List<Group>
     */
    public List<SysGroup> getGroupList() {
        return this.selectList("GroupMapper.getGroupList", null);
    }

    /**
     * 根据parentId查询Group
     *
     * @param parentId
     * @return List<Group>
     */
    public List<SysGroup> getChildGroupById(Long parentId) {
        return this.selectList("GroupMapper.getChildGroupById", parentId);
    }

    /**
     * 根据id删除group
     *
     * @param id
     */
    public void deleteGroupById(Long id) {
        this.delete("GroupMapper.deleteGroupById", id);
    }

    /**
     * 根据id更新group
     *
     * @param group
     */
    public void updateGroup(SysGroup group) {
        this.update("GroupMapper.updateGroup", group);
    }

    /**
     * 添加组
     *
     * @param group
     */
    public void addGroup(SysGroup group) {
        this.insert("GroupMapper.addGroup", group);
    }
}
