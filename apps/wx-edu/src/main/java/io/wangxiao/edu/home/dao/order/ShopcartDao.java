package io.wangxiao.edu.home.dao.order;

import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.order.Shopcart;

import java.util.List;

public interface ShopcartDao {

    /**
     * 添加Shopcart
     *
     * @param shopcart 要添加的Shopcart
     * @return id
     */
    java.lang.Long addShopcart(Shopcart shopcart);

    /**
     * 根据id删除一个Shopcart
     *
     * @param id 要删除的id
     */
    void deleteShopcartById(Long id);

    /**
     * 根据用户id
     *
     * @param Long userId 用户ID
     * @param Long type 购物车类型
     * @return List<Shopcart>
     */
    List<Shopcart> getShopcartListByUserId(Long userId, Long type);

    /**
     * 根据条件获取Shopcart列表
     *
     * @param shopcart 查询条件
     * @return List<Shopcart>
     */
    List<Shopcart> getShopcartList(Shopcart shopcart);

    /**
     * 清空数据库的购物车
     *
     * @param type 要删除的类型
     */
    void deleteShopcartByType(Long type, Long userId);

    /**
     * 根据条件获取购物车课程集合(优惠券专用)
     *
     * @param shopcart 查询条件
     * @return List<Shopcart>
     */
    List<Course> getShopcartCourseList(Long userId);


}