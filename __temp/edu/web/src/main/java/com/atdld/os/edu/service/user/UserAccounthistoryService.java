package com.atdld.os.edu.service.user;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.user.QueryUserAccounthistory;
import com.atdld.os.edu.entity.user.UserAccounthistory;

/**
 * UserAccounthistory管理接口
 * User:
 * Date: 2014-05-27
 */
public interface UserAccounthistoryService {

    /**
     * 添加UserAccounthistory
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    public java.lang.Long addUserAccounthistory(UserAccounthistory userAccounthistory);

    /**
     * 根据id获取单个UserAccounthistory对象
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    public UserAccounthistory getUserAccounthistoryById(Long id);

    /**
     * 根据条件获取UserAccounthistory列表
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory);
    
    
    /**
     * 账户历史 不隐藏后台操作 后台查看
     * @param queryUserAccounthistory
     * @param page
     * @return
     */
    public List<UserAccounthistory> getUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory,PageEntity page);
    
    /**
     * 账户历史 隐藏后台操作 前台个人中心查看
     * @param queryUserAccounthistory
     * @param page
     * @return
     */
    public List<UserAccounthistory> getWebUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory,PageEntity page);
    
}