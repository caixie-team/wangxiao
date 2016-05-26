package io.wangxiao.edu.home.dao.user;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.user.UserExpandDto;

import java.util.Date;
import java.util.List;

public interface UserExpandDao {

    /**
     * 添加UserExpand
     *
     * @param userExpand 要添加的UserExpand
     * @return id
     */
    Long addUserExpand(UserExpand userExpand);

    /**
     * 根据id删除一个UserExpand
     *
     * @param id 要删除的id
     */
    void deleteUserExpandById(Long id);

    /**
     * 修改UserExpand
     *
     * @param userExpand 要修改的UserExpand
     */
    void updateUserExpand(UserExpand userExpand);

    /**
     * 修改员工时 修改userExpand的realname
     *
     * @param userExpand
     */
    void updateUserExpandFromUser(UserExpand userExpand);

    /**
     * 修改UserExpand的上传头像
     */
    void updateAvatarById(UserExpand userExpand);

    /**
     * 通过userId修改UserExpand的subjectId
     */
    void updateUserExpandForSubject(UserExpand userExpand);

    /**
     * 根据id获取单个UserExpand对象
     *
     * @param id 要查询的id
     * @return UserExpand
     */
    UserExpand getUserExpandByUserId(Long userId);

    /**
     * 根据条件获取UserExpand列表
     *
     * @param userExpand 查询条件
     * @return List<UserExpand>
     */
    List<UserExpand> getUserExpandList(UserExpand userExpand);

    /**
     * ①单条增加 返回主键(可以不返回)
     *
     * @param customer 用户实体
     * @return
     */
    Long addOneUser(UserExpand customer);

    /**
     * 查询用户信息
     *
     * @param customer 用户实体 传入cusId
     * @return Customer 查出的用户实体
     */
    UserExpandDto queryUserByid(Long cusId);


    /**
     * 通过showname 查询customer
     *
     * @param showName
     */
    List<UserExpandDto> queryCustomerByShowName(String showName, int size);

    /**
     * 通过showname 查询customer(精确搜索)
     */
    List<UserExpandDto> queryCustomerByShowNameEquals(String showName);

    /**
     * 通过集合cusId 查询customer 返回map
     *
     * @param cusIds
     * @return
     * @throws Exception
     */
    List<UserExpandDto> queryUsersByIds(List<Long> cusIds) throws Exception;


    /**
     * 更新个人中心用户封面
     *
     * @param userId
     */
    void updateUserExpandBannerUrl(Long userId, String bannerUrl);


    /**
     * 查询全部好友
     *
     * @param customer 好友实体
     * @param page     分页参数
     * @return List<QueryCustomer>
     * @throws Exception
     */
    List<UserExpandDto> queryAllCustomer(UserExpandDto customer, PageEntity page);

    /**
     * 通过标识更新未读数加一
     */
    void updateUnReadMsgNumAddOne(String falg, Long cusId);

    /**
     * 通过标识更新未读数清零
     */
    void updateUnReadMsgNumReset(String falg, Long cusId);

    /**
     * 更新用户的上传统计系统消息时间
     */
    void updateCusForLST(Long cusId, Date date);

    /**
     * 要更新的数量类型
     * 修改UserExpand浏览次数
     *
     * @param SnsUserExpand
     */
    void updateUserExpandCount(String type, Long userId, Long count, String operation);

    /**
     * 分页查询用户
     *
     * @param userExpandDto 用户实体
     * @param page          分页参数
     * @return List<QueryCustomer>
     */
    List<UserExpandDto> queryUserExpandDtoPage(UserExpandDto userExpandDto, PageEntity page);
}