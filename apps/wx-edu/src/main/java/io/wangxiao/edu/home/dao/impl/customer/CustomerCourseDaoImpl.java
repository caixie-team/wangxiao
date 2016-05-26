package io.wangxiao.edu.home.dao.impl.customer;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.customer.CustomerCourseDao;
import io.wangxiao.edu.home.entity.customer.CustomerCourse;
import io.wangxiao.edu.home.entity.customer.CustomerCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCusCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCustomerCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("customerCourseDao")
public class CustomerCourseDaoImpl extends GenericDaoImpl implements CustomerCourseDao {

    public java.lang.Long addCustomerCourse(CustomerCourse customerCourse) {
        return this.insert("CustomerCourseMapper.createCustomerCourse", customerCourse);
    }

    public void deleteCustomerCourseById(Long id) {
        this.delete("CustomerCourseMapper.deleteCustomerCourseById", id);
    }

    public void updateCustomerCourse(CustomerCourse customerCourse) {
        this.update("CustomerCourseMapper.updateCustomerCourse", customerCourse);
    }

    public CustomerCourse getCustomerCourseById(Long id) {
        return this.selectOne("CustomerCourseMapper.getCustomerCourseById", id);
    }

    public List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse, PageEntity page) {
        return this.queryForListPage("CustomerCourseMapper.getCustomerCourseList", queryCustomerCourse, page);
    }

    public List<CustomerCourse> getCustomCourseRankings(int number) {

        return this.selectList("CustomerCourseMapper.getCustomCourseRankings", number);
    }

    public List<QueryCustomerCourse> getPageQueryCusCourseList(int number) {
        return this.selectList("CustomerCourseMapper.getWebCustomerCourseList", number);
    }

    public int queryCusCourseJoinNum() {

        return this.selectOne("CustomerCourseMapper.selectJoinCustomer", null);
    }

    public List<CustomerCourseRecord> queryCustomerCourseRecordList(QueryCusCourseRecord queryCusCourseRecord) {

        return this.selectList("CustomerCourseMapper.queryCusCourseRecordForList", queryCusCourseRecord);
    }

    public void addnewCusCourseRecord(CustomerCourseRecord customerCourseRecord) {

        this.insert("CustomerCourseMapper.createcusCourseRecord", customerCourseRecord);
    }
}
