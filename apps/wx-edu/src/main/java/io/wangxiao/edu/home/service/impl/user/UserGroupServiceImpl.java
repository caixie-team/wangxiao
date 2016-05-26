package io.wangxiao.edu.home.service.impl.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.user.UserGroupDao;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.user.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupDao userGroupDao;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;

    @Override
    public Long addUserGroup(UserGroup userGroup) {
        // TODO Auto-generated method stub
        return userGroupDao.addUserGroup(userGroup);
    }

    @Override
    public void updateUserGroup(UserGroup userGroup) {
        // TODO Auto-generated method stub
        userGroupDao.updateUserGroup(userGroup);
    }

    @Override
    public UserGroup queryUserGroupById(Long groupId) {
        // TODO Auto-generated method stub
        return userGroupDao.queryUserGroupById(groupId);
    }

    @Override
    public List<UserGroup> queryUserGroupListPage(UserGroup UserGroup, PageEntity page) {
        // TODO Auto-generated method stub
        return userGroupDao.queryUserGroupListPage(UserGroup, page);
    }

    @Override
    public List<UserGroup> getUserGroupListStatistics(UserGroup UserGroup, PageEntity page) {
        return userGroupDao.getUserGroupListStatistics(UserGroup, page);
    }

    @Override
    public List<UserGroup> getUserGroupList(UserGroup UserGroup) {
        // TODO Auto-generated method stub
        return userGroupDao.getUserGroupList(UserGroup);
    }

    @Override
    public List<UserGroup> getUserGroupListByIds(List ids) {
        // TODO Auto-generated method stub
        return userGroupDao.getUserGroupListByIds(ids);
    }

    /**
     * i
     **/
    /*@Override
	public List<JwClassType> getClassTypeFromJsonArray(String classDate) {
		// TODO Auto-generated method stub
		List<JwClassType> jwClassTypeList = new ArrayList<JwClassType>();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(classDate).getAsJsonObject();
        String data = jsonObject.get("data").toString();
        JsonArray jsonArray = jsonParser.parse(data).getAsJsonArray();
        Iterator it = jsonArray.iterator();
        it = jsonArray.iterator();
        //数组转换
        while (it.hasNext()) {
        	JwClassType jwClassType= new JwClassType();
            JsonElement e = (JsonElement) it.next();
            String id =e.getAsJsonObject().get("ID").getAsString();
            String name = e.getAsJsonObject().get("NAME").getAsString();
            jwClassType.setId(Integer.parseInt(id));
            jwClassType.setName(name);
            jwClassTypeList.add(jwClassType);
        }
        return jwClassTypeList;
	}*/
    @Override
    public boolean checkUserGroup(UserGroup userGroup) {
        List<UserGroup> list = userGroupDao.getUserGroupList(userGroup);
        if (list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<UserGroup> getGroupTask(Long userid) {
        // TODO Auto-generated method stub
        return userGroupDao.getGroupTask(userid);
    }

    @Override
    public void delUserGroup(Long id) {
        // TODO Auto-generated method stub
        userGroupDao.delUserGroup(id);
    }

    @Override
    public List<UserGroup> getGroupLearningNumByUserId(Long id) {
        UserGroup userGroup = new UserGroup();
        userGroup.setUserId(id);
        List<UserGroup> groupList = this.getUserGroupList(userGroup);
        if (ObjectUtils.isNotNull(groupList)) {
            for (UserGroup _userGroup : groupList) {
                int learnCourseNum = courseStudyhistoryService.getLearnCourseNum(id, _userGroup.getId());
                _userGroup.setCourseNum(learnCourseNum);
            }
        }
        return groupList;
    }
}
