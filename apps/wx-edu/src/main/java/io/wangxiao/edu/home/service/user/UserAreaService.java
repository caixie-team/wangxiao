package io.wangxiao.edu.home.service.user;

import io.wangxiao.edu.home.entity.user.UserArea;

import java.util.List;
import java.util.Map;

/**
 * UserArea管理接口
 */
public interface UserAreaService {

    /**
     * 添加UserArea
     *
     * @param userArea 要添加的UserArea
     * @return id
     */
    java.lang.Long addUserArea(UserArea userArea);

    /**
     * 根据id删除一个UserArea
     *
     * @param id 要删除的id
     */
    void deleteUserAreaById(Long id);

    /**
     * 修改UserArea
     *
     * @param userArea 要修改的UserArea
     */
    void updateUserArea(UserArea userArea);

    /**
     * 根据id获取单个UserArea对象
     *
     * @param id 要查询的id
     * @return UserArea
     */
    UserArea getUserAreaById(Long id);

    /**
     * 根据条件获取UserArea列表
     *
     * @param userArea 查询条件
     * @return List<UserArea>
     */
    List<UserArea> getUserAreaList(UserArea userArea);

    /**
     * 根据条件获取UserArea的map集合
     */
    Map<Long, UserArea> getMapUserAreaList(UserArea userArea);
}