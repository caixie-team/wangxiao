package io.wangxiao.edu.home.service.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {

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
     * 删除学员信息
     *
     * @param user
     */
    void updatePwdById(User user);

    /**
     * 修改QueryUser
     */
    String updateQueryUser(UserExpandDto queryUser) throws Exception;

    /**
     * 修改QueryUser
     */
    String updateAppQueryUser(UserExpandDto queryUser) throws Exception;

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
     */
    void updatePwdById(User user, UserCode userCode);

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
     * 设置用户为登录状态
     *
     * @param user
     * @param response
     * @return
     */
    Map<String, Object> setLoginStatus(User user, String autoThirty, HttpServletRequest request, HttpServletResponse response);

    /**
     * 查询学员邮箱是否存在
     *
     * @param emails
     * @return Integer
     */
    List<User> getUserIsExsitByEmail(List<String> emails);

    /**
     * 批量添加用户
     *
     * @param users
     */
    void addUsers(List<User> users);

    /**
     * 验证手机唯一
     *
     * @param user
     * @return
     * @throws Exception
     */
    Integer getUserByMobile(User user) throws Exception;

    /**
     * excel批量开通用户 shanXinYing
     *
     * @param myFile
     * @return
     * @throws Exception
     */
    Map<String, Object> updateImportExcel(MultipartFile myFile, String groupIds, int level, int sysGroupId) throws Exception;


    /**
     * 定时器，用户ip地址修改
     */
    void updateUserAddress();

    /**
     * 获得网站注册人数
     *
     * @return
     */
    Integer getWebsiteRegNumber();

    /**
     * 添加用户操作记录
     *
     * @param userId      用户的id
     * @param type        业务类型
     * @param optuser     操作者id
     * @param optusername 操作者名字
     * @param bizId       业务id
     * @param desc        描述
     */
    void addUserOptRecord(Long userId, String type, Long optuser, String optusername, Long bizId, String desc);

    /**
     * 根据用户ID，查询用户扩展数据
     *
     * @param userId
     * @return
     */
    UserExpandDto queryUserExpand(long userId);

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

    List<UserJw> getUserJwFromJsonArray(String userDate);

    void batchUpdateUser(List<UserJw> userJwList);

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
     * 通过用户昵称查找用户
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
     * 注册来源统计 （按年、月）
     *
     * @return
     * @throws Exception
     */
    Map<String, Object> getRegisterFromMsg(String month, String year);

    /**
     * 个人中心修改用户信息
     *
     * @param user
     */
    void updateWebUser(User user);
}