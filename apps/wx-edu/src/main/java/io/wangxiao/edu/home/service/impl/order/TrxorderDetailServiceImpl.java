package io.wangxiao.edu.home.service.impl.order;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.CourseTeacherDao;
import io.wangxiao.edu.home.dao.order.TrxorderDetailDao;
import io.wangxiao.edu.home.entity.course.Teacher;
import io.wangxiao.edu.home.entity.order.QueryTrxorderDetail;
import io.wangxiao.edu.home.entity.order.TrxorderDetail;
import io.wangxiao.edu.home.service.order.TrxorderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TrxorderDetail管理接口
 */
@Service("trxorderDetailService")
public class TrxorderDetailServiceImpl implements TrxorderDetailService {

    @Autowired
    private TrxorderDetailDao trxorderDetailDao;
    @Autowired
    private CourseTeacherDao courseTeacherDao;

    /**
     * 添加TrxorderDetail
     *
     * @param trxorderDetail 要添加的TrxorderDetail
     * @return id
     */
    public java.lang.Long addTrxorderDetail(TrxorderDetail trxorderDetail) {
        return trxorderDetailDao.addTrxorderDetail(trxorderDetail);
    }

    /**
     * 批量添加TrxorderDetail
     *
     * @param List<trxorderDetail> 要添加的TrxorderDetail
     * @return id
     */
    public void addBatchTrxorderDetail(List<TrxorderDetail> trxorderDetail) {
        trxorderDetailDao.addBatchTrxorderDetail(trxorderDetail);
    }

    /**
     * 根据id删除一个TrxorderDetail
     *
     * @param id 要删除的id
     */
    public void deleteTrxorderDetailById(Long id) {
        trxorderDetailDao.deleteTrxorderDetailById(id);
    }

    /**
     * 修改TrxorderDetail
     *
     * @param trxorderDetail 要修改的TrxorderDetail
     */
    public void updateTrxorderDetail(TrxorderDetail trxorderDetail) {
        trxorderDetailDao.updateTrxorderDetail(trxorderDetail);
    }

    /**
     * 根据id获取单个TrxorderDetail对象
     *
     * @param id 要查询的id
     * @return TrxorderDetail
     */
    public TrxorderDetail getTrxorderDetailById(Long id) {
        return trxorderDetailDao.getTrxorderDetailById(id);
    }

    /**
     * 根据条件获取TrxorderDetail列表
     *
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailList(TrxorderDetail trxorderDetail) {
        List<TrxorderDetail> orderList = trxorderDetailDao.getTrxorderDetailList(trxorderDetail);
        List<Long> listParam = new ArrayList<Long>();//课程id集合
        for (TrxorderDetail orderDetail : orderList) {
            listParam.add(orderDetail.getCourseId());
        }
        // 获取讲师的list
        Map<Long, List<Teacher>> map = courseTeacherDao.getCourseTeacherListByCourse(listParam);
        // 将讲师的list放到旧的list中
        for (TrxorderDetail orderDetail : orderList) {
            orderDetail.setTeacherList(map.get(orderDetail.getCourseId()));
        }
        return orderList;
    }

    /**
     * 查询该用户购买过的课程
     *
     * @param trxorderDetail 查询条件
     * @return List<TrxorderDetail>
     */
    public List<TrxorderDetail> getTrxorderDetailListBuy(Long userId) {
        return trxorderDetailDao.getTrxorderDetailListBuy(userId);
    }

    /**
     * 后台根据 条件查询 分页
     *
     * @param QueryTrxorderDetail
     * @return List<QueryTrxorderDetail>
     */
    public List<QueryTrxorderDetail> queryTrxorderDetailByOrder(QueryTrxorderDetail trxorderDetail, PageEntity page) {


        return trxorderDetailDao.queryTrxorderDetailByOrder(trxorderDetail, page);
    }


    /**
     * 根据流水id关联用户表查询流水详情
     *
     * @param id return QueryTrxorderDetail
     * @Date 2014-09-28
     */
    public QueryTrxorderDetail queryQueryTrxorderDetailById(Long id) {
        return trxorderDetailDao.queryQueryTrxorderDetailById(id);
    }

    /**
     * 判断已购买直播
     *
     * @param userId
     * @param courseId
     * @return
     */
    public int queryOrderByLive(Long userId, Long courseId) {
        return trxorderDetailDao.queryOrderByLive(userId, courseId);
    }

}