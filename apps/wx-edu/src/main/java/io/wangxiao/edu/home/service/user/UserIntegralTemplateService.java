package io.wangxiao.edu.home.service.user;

import java.util.List;

import io.wangxiao.edu.home.entity.user.UserIntegralTemplate;

public interface UserIntegralTemplateService {

    /**
     * 添加UserIntegralTemplate
     *
     * @param userIntegralTemplate 要添加的UserIntegralTemplate
     * @return id
     */
    java.lang.Long addUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate);

    /**
     * 根据id删除一个UserIntegralTemplate
     *
     * @param id 要删除的id
     */
    void deleteUserIntegralTemplateById(Long id);

    /**
     * 修改UserIntegralTemplate
     *
     * @param userIntegralTemplate 要修改的UserIntegralTemplate
     */
    void updateUserIntegralTemplate(UserIntegralTemplate userIntegralTemplate);

    /**
     * 根据id获取单个UserIntegralTemplate对象
     *
     * @param id 要查询的id
     * @return UserIntegralTemplate
     */
    UserIntegralTemplate getUserIntegralTemplateById(Long id);

    /**
     * 获取UserIntegralTemplate列表
     *
     * @return
     */
    List<UserIntegralTemplate> getUserIntegralTemplateList();

    /**
     * 更新状态 正常 停止
     *
     * @param userIntegralTemplat
     */
    void updateUserIntegralTemplateStatus(UserIntegralTemplate userIntegralTemplate);

    /**
     * 查询积分模板根据关键字
     *
     * @param keyword
     * @return
     */
    UserIntegralTemplate getUserIntegralTemplateByKeyword(String keyword);
}