package io.wangxiao.edu.home.dao.impl.user;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.user.UserDao;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserFromStatistics;
import io.wangxiao.edu.home.entity.user.UserJw;
import io.wangxiao.edu.home.entity.user.UserOptRecord;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

    public Long addUser(User user) {
        return this.insert("UserMapper.createUser", user);
    }


    public void deleteUserById(Long id) {
        this.delete("UserMapper.deleteUserById", id);
    }

    public void updateUser(User user) {
        this.update("UserMapper.updateUser", user);
    }

    /**
     * 通过用户id更新用户Isavalible （冻结，解冻操作）
     *
     * @param user 要修改的User
     */
    public void updateUserForIsavalibleById(User user) {
        this.update("UserMapper.updateUserForIsavalibleById", user);
    }

    public User getUserById(Long id) {
        return this.selectOne("UserMapper.getUserById", id);
    }

    /**
     * 通过用户id 更新密码
     *
     * @return User
     */
    public void updatePwdById(User user) {
        this.update("UserMapper.updatePwdById", user);
    }

    /**
     * @param user 查询条件
     * @return
     */
    public List<User> getUserList(User user) {
        return this.selectList("UserMapper.getUserList", user);
    }

    /**
     * 根据邮箱获取User列表(用户登陆添加了冻结状态)
     *
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForLogin(User user) {
        return this.selectList("UserMapper.getUserListForLogin", user);
    }

    /**
     * 根据手机号获取User列表(用户登陆添加了冻结状态)
     *
     * @param user 查询条件
     * @return List<User>
     */
    public List<User> getUserListForTelLogin(User user) {
        return this.selectList("UserMapper.getUserListForTelLogin", user);
    }

    public List<User> getUserListByCondition(User user, PageEntity page) {
        return this.queryForListPage("UserMapper.getUserListByCondition", user, page);
    }

    /**
     * 批量查询学员邮箱是否存在
     *
     * @param emails
     * @return Long
     */
    public List<User> getUserIsExsitByEmail(List<String> emails) {
        List<User> userList = this.selectList("UserMapper.getUserIsExsitByEmail", emails);
        if (ObjectUtils.isNotNull(userList)) {
            return userList;
        }
        return null;
    }

    @Override
    public User getUserIsExsitByEmail(String email) {
        User user = this.selectOne("UserMapper.getUserIsExsitByOneEmail", email);
        if (ObjectUtils.isNotNull(user)) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 批量添加用户
     *
     * @param user
     */
    public void addUsers(List<User> users) {
        this.insert("UserMapper.addUsers", users);
    }

    /**
     *
     */
    public Integer getUserByMobile(User user) {
        List<Integer> integerList = this.selectList("UserMapper.getUserByMobile", user);
        if (ObjectUtils.isNotNull(integerList) && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 获取网站注册人数
     *
     * @return
     */
    public Integer getWebsiteRegNumber() {
        List<Integer> integerList = this.selectList("UserMapper.getWebsiteRegNumber", null);
        if (ObjectUtils.isNotNull(integerList) && integerList.size() > 0) {
            return integerList.get(0);
        }
        return 0;
    }

    /**
     * 添加用户总操作记录
     *
     * @param userOptRecord
     */
    public void addUserOptRecord(UserOptRecord userOptRecord) {
        this.insert("UserMapper.addUserOptRecord", userOptRecord);
    }


    @Override
    public List<User> getUserListAndCourse(User user, PageEntity page) {
        return this.queryForListPage("UserMapper.getUserListAndCourse", user, page);
    }

    /**
     * 获取后台赠送操作列表
     *
     * @param userOptRecord
     * @param page
     * @return
     */
    public List<UserOptRecord> getUserOptRecordListByCondition(UserOptRecord userOptRecord, PageEntity page) {
        return this.queryForListPage("UserMapper.getUserOptRecordListByCondition", userOptRecord, page);
    }


    /**
     * 获取组的学员列表
     */
    public List<User> getUserListByGroupId(User user, PageEntity page) {
        return this.queryForListPage("UserMapper.getUserListByGroupId", user, page);
    }

    @Override
    public void batchUpdateUserList(List<UserJw> userJwList) {
        this.update("UserMapper.batchUpdateUserJw", userJwList);
    }


    /**
     * 查询部门下的员工
     */
    public List<User> queryGroupByid(User user, PageEntity page) {
        return this.queryForListPage("UserMapper.queryGroupByid", user, page);
    }


    /**
     * 根据Id获取员工
     */
    public List<User> queryGroupUserIds(String ids) {
        return this.selectList("UserMapper.queryGroupUserIds", ids);
    }

    @Override
    public User getUserByNickName(String nickname) {
        // TODO Auto-generated method stub
        return this.selectOne("UserMapper.getUserByNickName", nickname);
    }


    @Override
    public void delUserBatch(String userIds) {
        this.delete("UserMapper.delUserBatch", userIds.replaceAll(" ", "").split(","));

    }


    @Override
    public User getUserByName(String name) {
        // TODO Auto-generated method stub
        return this.selectOne("UserMapper.getUserByLoginName", name);
    }


    @Override
    public User getUserByLoginEmail(String email) {
        // TODO Auto-generated method stub
        return this.selectOne("UserMapper.getUserByLoginEmail", email);
    }


    @Override
    public User getUserByLoginMobile(String mobile) {
        // TODO Auto-generated method stub
        return this.selectOne("UserMapper.getUserByLoginMobile", mobile);
    }


    /**
     * 获取用户来源统计数据
     *
     * @return
     */
    public UserFromStatistics getUserFromStatistics() {
        return this.selectOne("UserMapper.getUserFromStatistics", null);
    }

    @Override
    public List<User> queryUserListByIds(String ids) {
        return this.selectList("UserMapper.queryUserListByIds", ids.replace(" ", "").split(","));
    }


    @Override
    public List<User> getUserListByGroup(User user, PageEntity page) {
        // TODO Auto-generated method stub
        return this.queryForListPage("UserMapper.getGroupUserList", user, page);
    }

    @Override
    public List<UserFromStatistics> getRegisterFromByYear(String year) {
        return this.selectList("UserMapper.getRegisterFromByYear", year);
    }

    @Override
    public List<UserFromStatistics> getRegisterFromByMonth(String month, String year) {
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("yearAndMonth", year + "-" + month);//年月
        return this.selectList("UserMapper.getRegisterFromByMonth", map);
    }


    /**
     * 个人中心修改用户信息
     */
    public void updateWebUser(User user) {
        this.update("UserMapper.updateWebUser", user);
    }

}
