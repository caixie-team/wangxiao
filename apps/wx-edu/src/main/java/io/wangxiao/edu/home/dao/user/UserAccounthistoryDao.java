package io.wangxiao.edu.home.dao.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.QueryUserAccounthistory;
import io.wangxiao.edu.home.entity.user.UserAccounthistory;

import java.util.List;

/**
 * UserAccounthistory管理接口
 */
public interface UserAccounthistoryDao {

    /**
     * 添加UserAccounthistory
     *
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory);


    /**
     * 根据id获取单个UserAccounthistory对象
     *
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    UserAccounthistory getUserAccounthistoryById(Long id);

    /**
     * 根据条件获取UserAccounthistory列表
     *
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory);

    /**
     * 根据条件获取UserAccounthistory列表
     *
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page);

    /**
     * 支付宝订单号查询账户历史，防止同一订单号多次充值
     *
     * @param out_trade_no
     */
    UserAccounthistory getUserAccounthistoryByOutTtradeNo(String outTradeNo);

    List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page);
}