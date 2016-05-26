package io.wangxiao.edu.common.service;

import java.util.Map;


public interface WebHessianService {

    /**
     * 修改用户最后考试的专业
     */
    void updateUserExpandForSubject(Map<String, String> map);

    //调用项目专业接口-web

    /**
     * 查询用户信息
     *
     * @param cusId
     * @return
     */
    Map<String, String> getUserInfo(Long cusId);

    /**
     * 批量查询用户信息
     *
     * @param ids
     * @return
     */
    Map<String, String> getUserInfoByUids(String ids);

    /**
     * 添加积分
     *
     * @param map
     */
    void addUserIntegral(Map<String, String> map);

    /**
     * 修改CourseProfile浏览次数
     *
     * @param map
     */
    void updateCourseProfile(Map<String, String> map);

    /**
     * 通过标识更新未读数加一或清零
     */
    void readMsgNumAddOrReset(String falg, Long cusId, String iType);

    /**
     * 更新用户的上传统计系统消息时间
     */
    void updateCusForLST(Long cusId, Long date);

    /**
     * 修改UserExpand浏览次数
     *
     * @param map
     */
    void updateUserExpandCount(Map<String, String> map);


    /**
     * 查询全部好友
     *
     * @param map
     * @return
     */
    Map<String, String> queryAllCustomer(Map<String, String> map);


    /**
     * 通过showname 查询customer(精确搜索)
     */
    Map<String, String> queryCustomerByShowNameEquals(String showName);


    /**
     * 通过showname 查询customer
     *
     * @param showName
     */
    Map<String, String> queryCustomerByShowName(String showName, int size);

    /**
     * 首页获取推荐的课程 封装为map
     *
     * @param recommendId
     * @return
     * @throws Exception
     */
    Map<String, String> getCourseListByHomePage(Long recommendId);

    /**
     * 关键字过滤
     *
     * @param content
     * @return
     */
    String doFilter(String content);

    /**
     * 添加任务操作记录表
     *
     * @param map
     */
    void updateArrangeRecord(Map<String, Object> map);

    /**
     * 小组  分页查询用户
     *
     * @param map 查询条件 和 分页参数
     * @return
     */
    Map<String, String> queryUserExpandDtoPage(Map<String, String> map);

    /**
     * 判断安排的考试是否完成（不可重复直接判断，可重复，直接为未完成，进行考试，若考试得分更高，修改最终得分）
     *
     * @param arrangeRecordId
     * @return
     */
    Map<String, String> ifCompletedExam(Long arrangeRecordId);
}
