package com.atdld.os.user.dao;

import com.atdld.os.entity.PageEntity;
import com.atdld.os.user.entity.UserIntegralRecord;

import java.util.List;


/**
 * UserIntegralRecord管理接口 User:  Date: 2014-05-27
 */
public interface UserIntegralRecordDao {

    /**
     * 添加UserIntegralRecord
     *
     * @param userIntegralRecord 要添加的UserIntegralRecord
     * @return id
     */
    Long addUserIntegralRecord(UserIntegralRecord userIntegralRecord);

    /**
     * 根据id删除一个UserIntegralRecord
     *
     * @param id 要删除的id
     */
    void deleteUserIntegralRecordById(Long id);

    /**
     * 修改UserIntegralRecord
     *
     * @param userIntegralRecord 要修改的UserIntegralRecord
     */
    void updateUserIntegralRecord(UserIntegralRecord userIntegralRecord);

    /**
     * 根据id获取单个UserIntegralRecord对象
     *
     * @param id 要查询的id
     * @return UserIntegralRecord
     */
    UserIntegralRecord getUserIntegralRecordById(Long id);

    /**
     * 根据条件获取UserIntegralRecord列表
     *
     * @param userIntegralRecord 查询条件
     * @return List<UserIntegralRecord>
     */
    List<UserIntegralRecord> getUserIntegralRecordList(UserIntegralRecord userIntegralRecord);

    /**
     * 查询用户积分记录
     *
     * @param userIntegralRecord
     * @param page
     * @return
     */
    List<UserIntegralRecord> getUserIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page);


    /**
     * 查询用户现在反馈积分
     *
     * @param userIntegralRecord
     * @param page
     * @return
     */
    List<UserIntegralRecord> getUserDownIntegralRecordListPage(UserIntegralRecord userIntegralRecord, PageEntity page);

    /**
     * 积分兑换记录
     *
     * @param userIntegralRecord
     * @param page
     * @return
     */
    List<UserIntegralRecord> getExchangeIntegralRecord(UserIntegralRecord userIntegralRecord, PageEntity page);

    /**
     * 更改兑换记录状态
     *
     * @param id
     */
    void updateIntegralRecordStatus(Long id);

    /**
     * 查询用户今天是否登陆积分纪录
     *
     * @param userIntegralRecord
     * @return
     */
    Long getUserScoreByToday(UserIntegralRecord userIntegralRecord);

    /**
     * 查询其它积分纪录
     *
     * @param userIntegralRecord
     * @return
     */
    Long getUserScoreByOther(UserIntegralRecord userIntegralRecord);
}