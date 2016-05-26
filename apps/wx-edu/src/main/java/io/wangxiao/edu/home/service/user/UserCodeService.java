package io.wangxiao.edu.home.service.user;

import io.wangxiao.edu.home.entity.user.UserCode;

/**
 * UserCode管理接口
 */
public interface UserCodeService {

    /**
     * 添加UserCode
     *
     * @param userCode 要添加的UserCode
     * @return id
     */
    UserCode addUserCode(UserCode userCode);

    /**
     * 根据id删除一个UserCode
     *
     * @param id 要删除的id
     */
    void deleteUserCodeById(Long id);

    /**
     * 修改UserCode
     *
     * @param userCode 要修改的UserCode
     */
    void updateUserCode(UserCode userCode);

    /**
     * 根据id获取单个UserCode对象
     *
     * @param id 要查询的id
     * @return UserCode
     */
    UserCode getUserCodeById(Long id);

    /**
     * 检测Usercode是否合法
     *
     * @param code 加密后的key
     * @return UserCode
     */
    UserCode checkUserCode(String code) throws Exception;

}