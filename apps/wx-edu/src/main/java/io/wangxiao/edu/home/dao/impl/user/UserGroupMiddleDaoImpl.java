package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.user.UserGroupMiddleDao;
import io.wangxiao.edu.home.entity.user.UserGroupMiddle;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userGroupMiddleDao")
public class UserGroupMiddleDaoImpl extends GenericDaoImpl implements UserGroupMiddleDao {


    @Override
    public void batchGroupMiddleCourse(Long courseId, String groupIds) {
        Map<String, Object> map = new HashMap<>();
        map.put("courseId", courseId);
        map.put("list", groupIds.replace(" ", "").split(","));
        this.insert("UserGroupMiddleMapper.batchGroupMiddleCourse", map);
    }

    @Override
    public void addUserGroupMiddle(List<UserGroupMiddle> userGroupMiddle) {
        this.insert("UserGroupMiddleMapper.addUserGroupMiddle", userGroupMiddle);
    }

    @Override
    public void deleteUserGroupMiddle(UserGroupMiddle userGroupMiddle) {
        this.delete("UserGroupMiddleMapper.deleteUserGroupMiddle", userGroupMiddle);

    }

    @Override
    public void delteUserGroupMiddleByUserId(UserGroupMiddle userGroupMiddle) {
        this.delete("UserGroupMiddleMapper.delteUserGroupMiddleByUserId", userGroupMiddle);
    }

    @Override
    public List<UserGroupMiddle> getUserGroupByUserId(Long userId) {
        return this.selectList("UserGroupMiddleMapper.getUserGroupByUserId", userId);
    }

    @Override
    public UserGroupMiddle getUserGroupUserId(Long userId) {
        return this.selectOne("UserGroupMiddleMapper.getUserGroupByUserId", userId);
    }

    @Override
    public Long getUserGroupCount(Long groupid) {
        return this.selectOne("UserGroupMiddleMapper.getUserGroupCount", groupid);
    }

    /**
     * 根据组id获取学员组信息
     *
     * @param groupId
     * @return
     */
    public List<UserGroupMiddle> getUserGroupByGourpId(Long groupId) {
        return this.selectList("UserGroupMiddleMapper.getUserGroupByGourpId", groupId);
    }

    @Override
    public void deleteCourseGroupMiddle(Long courseId) {
        this.delete("UserGroupMiddleMapper.deleteCourseGroupMiddle", courseId);
    }

    @Override
    public List<Long> getCourseIdList(Long userId) {
        return this.selectList("UserGroupMiddleMapper.getCourseIdList", userId);
    }

    @Override
    public void deleteGroupCourseByCourseId(Map<String, Object> map) {
        this.delete("UserGroupMiddleMapper.deleteGroupCourseByCourseId", map);
    }
}
