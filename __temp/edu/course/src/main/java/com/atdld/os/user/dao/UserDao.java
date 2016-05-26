package com.atdld.os.user.dao;

import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.User;
import com.atdld.os.user.entity.UserOptRecord;

import java.util.List;


/**
 * User管理接口 User:  Date: 2014-01-10
 */
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
     * 根据条件获取User列表
     *
     * @param user 用户
     * @param page 分页参数
     * @return
     */
    List<User> getUserListByCondition(User user, PageEntity page);

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

}