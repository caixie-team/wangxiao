package io.wangxiao.edu.home.dao.customer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.customer.CustomerCourse;
import io.wangxiao.edu.home.entity.customer.CustomerCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCusCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCustomerCourse;

import java.util.List;

/**
 * CustomerCourse管理接口
 */
public interface CustomerCourseDao {

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
     * 根据条件获取CustomerCourse列表
     *
     * @param QueryCustomerCourse 查询条件
     * @return List<QueryCustomerCourse>
     */
    List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse, PageEntity page);

    /**
     * 根据查询记录 获取前台  自定义课程排行榜
     *
     * @param number
     * @return List<CustomerCourse>
     */
    List<CustomerCourse> getCustomCourseRankings(int number);

    /**
     * 前台查询自定义课程根据人数最多
     *
     * @param number 记录数
     * @return List<QueryCustomerCourse>
     */
    List<QueryCustomerCourse> getPageQueryCusCourseList(int number);

    /**
     * 查询参与自定义课程的人数
     *
     * @return number
     */

    int queryCusCourseJoinNum();

    /**
     * 查询投票记录  根据条件
     *
     * @param queryCusCourseRecord
     * @return list
     */

    List<CustomerCourseRecord> queryCustomerCourseRecordList(QueryCusCourseRecord queryCusCourseRecord);

    /**
     * 添加自定义投票记录
     *
     * @param customerCourseRecord
     */
    void addnewCusCourseRecord(CustomerCourseRecord customerCourseRecord);

}