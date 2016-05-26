package io.wangxiao.edu.home.service.customer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.customer.CustomerCourse;
import io.wangxiao.edu.home.entity.customer.CustomerCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCusCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCustomerCourse;

import java.util.List;

/**
 * CustomerCourse管理接口
 */
public interface CustomerCourseService {

    /**
     * 添加CustomerCourse
     *
     * @param customerCourse 要添加的CustomerCourse
     * @return id
     */
    java.lang.Long addCustomerCourse(CustomerCourse customerCourse);

    /**
     * 根据id删除一个CustomerCourse
     *
     * @param id 要删除的id
     */
    void deleteCustomerCourseById(Long id);

    /**
     * 修改CustomerCourse
     *
     * @param customerCourse 要修改的CustomerCourse
     */
    void updateCustomerCourse(CustomerCourse customerCourse);

    /**
     * 根据id获取单个CustomerCourse对象
     *
     * @param id 要查询的id
     * @return CustomerCourse
     */
    CustomerCourse getCustomerCourseById(Long id);

    /**
     * 分页查询
     * 根据条件获取CustomerCourse列表
     *
     * @param queryCustomerCourse 查询条件
     * @return List<QueryCustomerCourse>
     */
    List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse, PageEntity page) throws Exception;

    /**
     * 根据查询记录 获取前台  自定义课程排行榜
     *
     * @param number
     * @return List<CustomerCourse>
     * @throws Exception
     * @author guozhenhzou
     */
    List<CustomerCourse> getCustomCourseRankings(int number) throws Exception;

    /**
     * 前台查询自定义课程根据人数最多
     *
     * @param number 记录数
     * @return List<QueryCustomerCourse>
     * @author guozhenzhou
     */
    List<QueryCustomerCourse> getPageQueryCusCourseList(int number) throws Exception;


    /**
     * 查询参与自定义课程人数
     */
    int queryJoinNum();


    /**
     * 学员是否已经投票过自定义课程
     *
     * @return boolean
     * @author guozhenzhou
     */
    boolean queryuserIsjoinCourse(QueryCusCourseRecord queryCusCourseRecord);

    /**
     * 添加自定义课程投票记录
     *
     * @param customerCourseRecord
     * @version 2014-09-26
     * @author guozhenzhou
     */
    void createCustomerCourseRecord(CustomerCourseRecord customerCourseRecord);
}