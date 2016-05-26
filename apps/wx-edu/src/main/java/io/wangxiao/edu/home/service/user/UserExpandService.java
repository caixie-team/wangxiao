package io.wangxiao.edu.home.service.user;


import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.user.UserExpandDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserExpandService {

    /**
     * 添加UserExpand
     *
     * @param userExpand 要添加的UserExpand
     * @return id
     */
    Long addUserExpand(UserExpand userExpand);

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
     * 通过showname 查询customer
     *
     * @param showName
     */
    List<UserExpandDto> queryCustomerByShowName(String showName, int size);


    /**
     * 通过集合cusId 查询customer 返回map
     *
     * @param cusIds
     * @return
     * @throws Exception
     */
    Map<String, UserExpandDto> queryCustomerInCusIds(List<Long> cusIds) throws Exception;


    /**
     * 根据用户uid获取用户信息
     *
     * @param uids
     * @return
     */
    Map<String, UserExpandDto> getUserExpandByUids(String uids) throws Exception;

    /**
     * 根据用户uid获取用户信息
     *
     * @param uids
     * @return
     */
    UserExpandDto getUserExpandByUids(Long uids);

    /**
     * 根据用户uid获取用户信息 （非缓存）
     *
     * @param uids
     * @return
     */
    UserExpandDto getUserExpandByUid(Long uid);

    /**
     * 补齐用户的信息list对象必须包含字段id
     *
     * @return
     */
    List<UserExpandDto> setUserExpandDtoInfo(List<UserExpandDto> list) throws Exception;

    /**
     * 通过showname 查询customer(精确搜索)
     */
    List<UserExpandDto> queryCustomerByShowNameEquals(String showName);

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
     * 验证用户是否操作频繁
     *
     * @param type   验证的类型
     * @param cus_Id
     * @return
     */
    boolean checkLimitOpt(String type, Long cus_Id);

    /**
     * 增加用户操作次数
     *
     * @param type   验证的类型
     * @param cus_Id
     * @return
     */
    void customerOptLimitCountAdd(String type, Long cus_Id);

    /**
     * 分页查询用户
     *
     * @param userExpandDto 用户实体
     * @param page          分页参数
     * @return List<QueryCustomer>
     */
    List<UserExpandDto> queryUserExpandDtoPage(UserExpandDto userExpandDto, PageEntity page);
}