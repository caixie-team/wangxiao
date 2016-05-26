package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.user.UserGroupMiddleDao;
import io.wangxiao.edu.home.entity.user.UserGroupMiddle;
import io.wangxiao.edu.home.service.user.UserGroupMiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userGroupMiddleService")
public class UserGroupMiddleServiceImpl implements UserGroupMiddleService {

    private CacheKit cacheKit = CacheKit.getInstance();

    @Autowired
    private UserGroupMiddleDao userGroupMiddleDao;

    @Override
    public void addUserGroupMiddle(List<UserGroupMiddle> userGroupMiddle) {
        userGroupMiddleDao.addUserGroupMiddle(userGroupMiddle);
    }

    @Override
    public void deleteUserGroupMiddle(UserGroupMiddle userGroupMiddle) {
        userGroupMiddleDao.deleteUserGroupMiddle(userGroupMiddle);

    }


    public void delteUserGroupMiddleByUserId(UserGroupMiddle userGroupMiddle) {
        if (ObjectUtils.isNotNull(userGroupMiddle)) {
            Long userId = userGroupMiddle.getUserId();
            List<Long> courseIdList = userGroupMiddleDao.getCourseIdList(userId);
            if (ObjectUtils.isNotNull(courseIdList)) {
                for (Long courseId : courseIdList) {
                    cacheKit.remove(MemConstans.USER_CANLOOK + "_" + userId + "_" + courseId);
                }
            }
        }
        userGroupMiddleDao.delteUserGroupMiddleByUserId(userGroupMiddle);
    }

    ;


    @Override
    public List<UserGroupMiddle> getUserGroupByUserId(Long UserId) {
        // TODO Auto-generated method stub
        return userGroupMiddleDao.getUserGroupByUserId(UserId);
    }

    @Override
    public Long getUserGroupCount(Long groupid) {
        // TODO Auto-generated method stub
        return userGroupMiddleDao.getUserGroupCount(groupid);
    }

    @Override
    public UserGroupMiddle getUserGroupUserId(Long userId) {
        // TODO Auto-generated method stub
        return userGroupMiddleDao.getUserGroupUserId(userId);
    }

    /**
     * 根据组id获取学员组信息
     *
     * @param groupId
     * @return
     */
    public List<UserGroupMiddle> getUserGroupByGourpId(Long groupId) {
        return userGroupMiddleDao.getUserGroupByGourpId(groupId);
    }

    @Override
    public void deleteGroupCourseByCourseId(Long gourpId, String ids) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", gourpId);
        map.put("ids", ids.replace(" ", "").split(","));
        this.userGroupMiddleDao.deleteGroupCourseByCourseId(map);
    }

}
