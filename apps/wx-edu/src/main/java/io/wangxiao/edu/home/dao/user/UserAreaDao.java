package io.wangxiao.edu.home.dao.user;
import java.util.List;

import io.wangxiao.edu.home.entity.user.UserArea;

/**
 * UserArea管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface UserAreaDao {

    /**
     * 添加UserArea
     * @param userArea 要添加的UserArea
     * @return id
     */
    public java.lang.Long addUserArea(UserArea userArea);

    /**
     * 根据id删除一个UserArea
     * @param id 要删除的id
     */
    public void deleteUserAreaById(Long id);

    /**
     * 修改UserArea
     * @param userArea 要修改的UserArea
     */
    public void updateUserArea(UserArea userArea);

    /**
     * 根据id获取单个UserArea对象
     * @param id 要查询的id
     * @return UserArea
     */
    public UserArea getUserAreaById(Long id);

    /**
     * 根据条件获取UserArea列表
     * @param userArea 查询条件
     * @return List<UserArea>
     */
    public List<UserArea> getUserAreaList(UserArea userArea);
}