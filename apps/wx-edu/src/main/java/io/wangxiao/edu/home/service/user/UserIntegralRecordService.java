package io.wangxiao.edu.home.service.user;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.user.UserIntegral;
import io.wangxiao.edu.home.entity.user.UserIntegralRecord;

import java.util.List;

public interface UserIntegralRecordService {

    /**
     * 添加UserIntegralRecord
     *
     * @param userIntegralRecord 要添加的UserIntegralRecord
     * @return id
     */
    java.lang.Long addUserIntegralRecord(UserIntegralRecord userIntegralRecord);

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
     * 查询用户下线反馈积分
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
     * 进行兑换操作
     *
     * @param userIntegralRecord 积分记录
     * @param courseId           课程id
     * @param userIntegral       用户积分对象
     */
    boolean addExchangeOperate(UserIntegralRecord userIntegralRecord, Long courseId, UserIntegral userIntegral, String ipAdrr) throws Exception;

    /**
     * 查询用户今天是否登陆积分纪录
     *
     * @param userIntegralRecord
     * @return
     */
    boolean getUserScoreByToday(UserIntegralRecord userIntegralRecord);

    /**
     * 查询其它积分纪录
     *
     * @param userIntegralRecord
     * @return
     */
    boolean getUserScoreByOther(UserIntegralRecord userIntegralRecord);
}