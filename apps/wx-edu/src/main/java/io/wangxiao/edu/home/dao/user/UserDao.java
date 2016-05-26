package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.User;
import io.wangxiao.edu.home.entity.user.UserFromStatistics;
import io.wangxiao.edu.home.entity.user.UserJw;
import io.wangxiao.edu.home.entity.user.UserOptRecord;

import java.util.List;

public interface UserDao {

    /**
     * 添加User
     *
     * @param user 要添加的User
     * @return id
     */
    Long addUser(User user);

    /**
     * 根据id删除一个User
     *
     * @param id 要删除的id
     */
    void deleteUserById(Long id);

    /**
     * 修改User
     *
     * @param user 要修改的User
     */
    void updateUser(User user);

    /**
     * 通过用户id更新用户Isavalible （冻结，解冻操作）
     *
     * @param user 要修改的User
     */
    void updateUserForIsavalibleById(User user);

    /**
     * 根据id获取单个User对象
     *
     * @param id 要查询的id
     * @return User
     */
    User getUserById(Long id);

    /**
     * 通过用户id 更新密码
     *
     * @return User
     */
    void updatePwdById(User user);

    /**
     * 根据条件获取User列表
     *
     * @param user 查询条件
     * @return List<User>
     */
    List<User> getUserList(User user);

    /**
     * 根据条件获取User列表(用户登陆添加了冻结状态)
     *
     * @param user 查询条件
     * @return List<User>
     */
    List<User> getUserListForLogin(User user);

    /**
     * 根据手机号获取User列表(用户登陆添加了冻结状态)
     *
     * @param user 查询条件
     * @return List<User>
     */
    List<User> getUserListForTelLogin(User user);

    /**
     * 根据条件获取User列表(学习统计)
     *
     * @param user 用户
     * @param page 分页参数
     * @return
     */
    List<User> getUserListByCondition(User user, PageEntity page);

    /**
     * 根据条件获取User列表（用户列表）
     *
     * @param user 用户
     * @param page 分页参数
     * @return
     */
    List<User> getUserListByGroup(User user, PageEntity page);

    /**
     * 根据条件获取User列表  带课程名称
     *
     * @param user 用户
     * @param page 分页参数
     * @return
     */
    List<User> getUserListAndCourse(User user, PageEntity page);

    /**
     * 查询学员邮箱是否存在
     *
     * @param emails
     * @return Long
     */
    List<User> getUserIsExsitByEmail(List<String> emails);

    User getUserIsExsitByEmail(String email);

    /**
     * 批量添加用户
     *
     * @param user
     */
    void addUsers(List<User> users);

    /**
     * 验证手机唯一
     *
     * @param user
     * @return
     */
    Integer getUserByMobile(User user);

    /**
     * 获取网站注册人数
     *
     * @return
     */
    Integer getWebsiteRegNumber();

    /**
     * 添加用户总操作记录
     *
     * @param userOptRecord
     */
    void addUserOptRecord(UserOptRecord userOptRecord);

    /**
     * 获取后台赠送操作列表
     *
     * @param userOptRecord
     * @param page
     * @return
     */
    List<UserOptRecord> getUserOptRecordListByCondition(UserOptRecord userOptRecord, PageEntity page);


    /**
     * 获取组的学员列表
     *
     * @param user
     * @param page
     * @return
     */
    List<User> getUserListByGroupId(User user, PageEntity page);

    void batchUpdateUserList(List<UserJw> userJwList);

    /**
     * 查询部门下的员工
     *
     * @param user
     * @param page
     * @return
     */
    List<User> queryGroupByid(User user, PageEntity page);

    /**
     * 根据Id获取员工
     *
     * @param ids
     * @return
     */
    List<User> queryGroupUserIds(String ids);


    /**
     * 通过用户昵称查询用户
     *
     * @param nickname
     * @return
     */
    User getUserByNickName(String nickname);

    /**
     * 批量删除
     */
    void delUserBatch(String userIds);

    /**
     * 根据登录名查询
     *
     * @param name
     * @return User
     */
    User getUserByName(String name);

    /**
     * 根据Email名查询
     *
     * @param name
     * @return User
     */
    User getUserByLoginEmail(String email);

    /**
     * 根据手机号查询
     *
     * @param name
     * @return User
     */
    User getUserByLoginMobile(String mobile);

    /**
     * 获取用户来源统计数据
     *
     * @return
     */
    UserFromStatistics getUserFromStatistics();

    /**
     * 获取用户名集合(计划进度)
     *
     * @param ids
     * @return
     */
    List<User> queryUserListByIds(String ids);


    /**
     * 按年查询注册来源
     *
     * @param year
     * @return
     */
    List<UserFromStatistics> getRegisterFromByYear(String year);

    /**
     * 按月查询注册来源
     *
     * @param month
     * @param year
     * @return
     */
    List<UserFromStatistics> getRegisterFromByMonth(String month, String year);

    /**
     * 个人中心修改用户信息
     *
     * @param user
     */
    void updateWebUser(User user);
}