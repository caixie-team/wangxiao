package io.wangxiao.order.service;

import io.wangxiao.core.BeetlSqlService;
import io.wangxiao.course.entity.Course;
import io.wangxiao.order.entity.Shopcart;
import com.google.gson.Gson;
import org.beetl.sql.core.SQLReady;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by bison on 1/22/16.
 * <p>
 * 购物车业务
 */
@Service("shopcartService")
public class ShopcartServiceImpl extends BeetlSqlService<Shopcart> implements ShopcartService {

    /**
     * 添加商品到购物车
     *
     * @param goodsId
     * @param type
     * @param userId
     * @return 商品列表
     */
    @Override
    public List<Shopcart> addShopcart(Long goodsId, Long type, Long userId) {

        //~ 创建购物车数据
        Shopcart newCart = new Shopcart();
        newCart.setGoodsid(Long.valueOf(goodsId));
        newCart.setType(Long.valueOf(type));
        newCart.setUserid(userId);
        newCart.setAddTime(new Date());

        // TODO: for Redis

        //~ 查询原有购物车数据
        List<Shopcart> shopcartList = dao.execute(
                new SQLReady("select * from edu_shopcart where userid = ? and type = ?", userId, type),
                Shopcart.class
        );

        //~ 原购物车没有数据
        if (shopcartList == null) {
            dao.insert(newCart);

            // 根据商品ID 查询课程信息
            newCart.setCourse(dao.unique(Course.class, newCart.getGoodsid()));

            shopcartList = Collections.singletonList(newCart);

        }
        //~ 原购物车有数据
        else {
            boolean isexsits = false;

            for (Shopcart oldCart : shopcartList) {
                if (oldCart.getGoodsid().equals(newCart.getGoodsid())
                        && oldCart.getType().equals(newCart.getType())) {

                    isexsits = true;
                    break;
                }
            }
            if (!isexsits) {

                dao.insert(newCart);

                // 查询课程信息
                newCart.setCourse(dao.unique(Course.class, newCart.getGoodsid()));

                shopcartList.add(newCart);
            }
        }

        return shopcartList;
    }

    /**
     * 按商品ID删除购物车中的商品
     *
     * @param id     商品 ID
     * @param userId
     */
    @Override
    public void deleteShopcartById(Long id, Long userId) {
        dao.deleteById(Shopcart.class, id);
    }

    @Override
    public List<Shopcart> getShopcartList(Long userId, Long type) {
//        List<Shopcart> shopcartList = dao.execute(
//                new SQLReady("select * from edu_shopcart where userid = ? and type = ?", userId, type), Shopcart.class
//        );
        Gson gson = new Gson();

        Map map = new HashMap<>();
        map.put("userId", userId);
        map.put("type", type);

        List<Shopcart> shopcarts = dao.select("shopcart.dtos", Shopcart.class, map);
        List<Shopcart> formatCarts = new ArrayList<>();

        // 设置购物车数据
        for (Shopcart car : shopcarts) {
            car.setCourse(gson.fromJson(gson.toJson(car.getTails()), Course.class));
            formatCarts.add(car);
        }


//        from edu_shopcart
//        left join edu_course
//        on edu_shopcart.goodsid = edu_course.id
//        where userid=#{userId} and type =#{type}
//        if (shopcartList != null) {
//
//        }
//        List<Shopcart> list = (List<Shopcart>) memCache.get(MemConstans.SHOPCART + userId);
//        if (ObjectUtils.isNull(list)) {
//            list = shopcartDao.getShopcartListByUserId(userId,type);
//            if (ObjectUtils.isNotNull(list)) {
//                List<Long> listParam = new ArrayList<Long>();
//                for (Shopcart shopcart  : list) {
//                    listParam.add(shopcart.getCourse().getId());
//                }
//                 获取讲师的list
//                Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
//                 将讲师的list放到旧的list中
//                for (Shopcart shopcart  : list) {
//                    shopcart.getCourse().setTeacherList(map.get(shopcart.getCourse().getId()));
//                }
//            }
//
//            if (ObjectUtils.isNotNull(list)) {
//                memCache.set(MemConstans.SHOPCART + userId, list);
//            }
//        }
//        return list;
        return null;
    }

    @Override
    public List<Course> getShopcartCourseList(Long userId) {
        return null;
    }

    @Override
    public String addTempShopcart(Long goodsId, Long type, String json) {
        return null;
    }

    @Override
    public String deleteTempShopcart(Long goodsId, Long type, String json) {
        return null;
    }

    @Override
    public List<Shopcart> getTempShopcartList(String json) {
        return null;
    }

    @Override
    public void addTempShopCart(HttpServletRequest request, HttpServletResponse response, Long userId) {

    }

    @Override
    public void deleteShopcartByType(Long type, Long userId) {

    }
}
