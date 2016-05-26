package io.wangxiao.edu.home.dao.impl.order;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.order.ShopcartDao;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.order.Shopcart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("shopcartDao")
public class ShopcartDaoImpl extends GenericDaoImpl implements ShopcartDao {

    public java.lang.Long addShopcart(Shopcart shopcart) {
        return this.insert("ShopcartMapper.createShopcart", shopcart);
    }

    public void deleteShopcartById(Long id) {
        this.delete("ShopcartMapper.deleteShopcartById", id);
    }

    public List<Shopcart> getShopcartListByUserId(Long userId, Long type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("type", type);
        return this.selectList("ShopcartMapper.getShopcartListByUserId", map);
    }

    public List<Course> getShopcartCourseList(Long userId) {
        return this.selectList("ShopcartMapper.getShopcartCourseList", userId);
    }

    public List<Shopcart> getShopcartList(Shopcart shopcart) {
        return this.selectList("ShopcartMapper.getShopcartList", shopcart);
    }


    /**
     * 清空数据库的购物车
     *
     * @param type 要删除的类型
     */
    public void deleteShopcartByType(Long type, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", type);
        map.put("userId", userId);
        this.delete("ShopcartMapper.deleteShopcartByType", map);
    }


}
