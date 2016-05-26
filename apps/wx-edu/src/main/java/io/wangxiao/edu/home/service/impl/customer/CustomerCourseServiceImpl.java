package io.wangxiao.edu.home.service.impl.customer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.service.cache.CacheKit;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.home.dao.customer.CustomerCourseDao;
import io.wangxiao.edu.home.entity.customer.CustomerCourse;
import io.wangxiao.edu.home.entity.customer.CustomerCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCusCourseRecord;
import io.wangxiao.edu.home.entity.customer.QueryCustomerCourse;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.customer.CustomerCourseService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("customerCourseService")
public class CustomerCourseServiceImpl implements CustomerCourseService {

	@Autowired
	private CustomerCourseDao customerCourseDao;
	@Autowired
	private UserExpandService userExpandService;
	private CacheKit cacheKit = CacheKit.getInstance();

	/**
	 * 添加CustomerCourse
	 * 
	 * @param customerCourse
	 *            要添加的CustomerCourse
	 * @return id
	 */
	public java.lang.Long addCustomerCourse(CustomerCourse customerCourse) {
		cacheKit.remove(MemConstans.INDEX_CUSTOMER_COURSE);
		return customerCourseDao.addCustomerCourse(customerCourse);
		
	}

	/**
	 * 根据id删除一个CustomerCourse
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCustomerCourseById(Long id) {
		customerCourseDao.deleteCustomerCourseById(id);
		cacheKit.remove(MemConstans.INDEX_CUSTOMER_COURSE);
	}

	/**
	 * 修改CustomerCourse
	 * 
	 * @param customerCourse
	 *            要修改的CustomerCourse
	 */
	public void updateCustomerCourse(CustomerCourse customerCourse) {
		customerCourseDao.updateCustomerCourse(customerCourse);
		cacheKit.remove(MemConstans.INDEX_CUSTOMER_COURSE);
	}

	/**
	 * 根据id获取单个CustomerCourse对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CustomerCourse
	 */
	public CustomerCourse getCustomerCourseById(Long id) {
		return customerCourseDao.getCustomerCourseById(id);
	}

	/**
	 * 分页查询后台 根据条件获取CustomerCourse列表
	 * 
	 * @param queryCustomerCourse
	 *            查询条件
	 * @return List<CustomerCourse>
	 */
	public List<QueryCustomerCourse> getCustomerCourseList(QueryCustomerCourse queryCustomerCourse, PageEntity page) throws Exception {
		List<QueryCustomerCourse> list = customerCourseDao.getCustomerCourseList(queryCustomerCourse, page);
		// 设置用户信息
		if (ObjectUtils.isNotNull(list)) {
			List<Long> uids = new ArrayList<Long>();//
			for (QueryCustomerCourse customerCourse : list) {
				uids.add(customerCourse.getCreateuserId());
			}
			Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(uids);
			if (ObjectUtils.isNotNull(map)) {
				for (QueryCustomerCourse customerCourse : list) {
                    UserExpandDto userExpandDto =map.get(customerCourse.getCreateuserId().toString());
                    if(ObjectUtils.isNotNull(userExpandDto)){
                        customerCourse.setUserExpandDto(userExpandDto);
                    }
				}
			}
		}
		return list;
	}

	/**
	 * 前台查询自定义课程排行榜 缓存 List<CustomerCourse>
	 * 
	 * @param number
	 *            查询记录数
	 * @return List<CustomerCourse>
	 * @throws Exception
	 */
	public List<CustomerCourse> getCustomCourseRankings(int number) throws Exception {
		@SuppressWarnings("unchecked")
		List<CustomerCourse> list = (List<CustomerCourse>) cacheKit.get(MemConstans.INDEX_CUSTOMER_COURSE + 1);
		if (ObjectUtils.isNull(list)) {
			list = customerCourseDao.getCustomCourseRankings(number); // 从db
			if (ObjectUtils.isNotNull(list)) {
				cacheKit.set(MemConstans.INDEX_CUSTOMER_COURSE + 1, list, MemConstans.INDEX_CUSTOMER_COURSE_TIME);
			}
		}
		return list;
	}

	/**
	 * 前台查询自定义课程根据人数最多
	 * 
	 * @param number
	 *            记录数
	 * @return List<QueryCustomerCourse>
	 */
	public List<QueryCustomerCourse> getPageQueryCusCourseList(int number) throws Exception {
		List<QueryCustomerCourse> list = customerCourseDao.getPageQueryCusCourseList(number);
		// 设置用户信息
		if (ObjectUtils.isNotNull(list)) {
			List<Long> uids = new ArrayList<Long>();
			for (QueryCustomerCourse customerCourse : list) {
				uids.add(customerCourse.getCreateuserId());
			}
			Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(uids);
			if (ObjectUtils.isNotNull(map)) {
				for (QueryCustomerCourse customerCourse : list) {
                    UserExpandDto userExpandDto1=map.get(customerCourse.getCreateuserId().toString());
                    if(ObjectUtils.isNotNull(userExpandDto1)){
                        customerCourse.setUserExpandDto(userExpandDto1);
                    }
				}
			}
		}
		return customerCourseDao.getPageQueryCusCourseList(number);
	}

	/**
	 * 查询加入自定义课程的数目，记录不能重复
	 * 
	 * @return number(int)
	 */
	public int queryJoinNum() {

		return customerCourseDao.queryCusCourseJoinNum();
	}

	/**
	 * 查询学员是否已经投票过自定义课程
	 * 
	 * @param queryCusCourseRecord
	 * @return boolean true表示已经投票过
	 */
	public boolean queryuserIsjoinCourse(QueryCusCourseRecord queryCusCourseRecord) {
		boolean joinState = true;
		List<CustomerCourseRecord> list = new ArrayList<CustomerCourseRecord>();
		list = customerCourseDao.queryCustomerCourseRecordList(queryCusCourseRecord);
		if (list != null && list.size() > 0) {
			joinState = true;
		} else {
			joinState = false;
		}
		return joinState;
	}

	/**
	 * 添加自定义课程投票记录
	 * 
	 * @param customerCourseRecord
	 * @version 2014-09-26
	 */
	public void createCustomerCourseRecord(CustomerCourseRecord customerCourseRecord) {

		customerCourseDao.addnewCusCourseRecord(customerCourseRecord);
	}

}