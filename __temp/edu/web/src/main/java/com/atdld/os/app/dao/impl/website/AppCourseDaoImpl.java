package com.atdld.os.app.dao.impl.website;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.app.dao.website.AppCourseDao;
import com.atdld.os.app.entity.website.AppCourse;
import com.atdld.os.app.entity.website.AppCourseDto;
import com.atdld.os.app.entity.website.QueryAppCourseCondition;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

@Repository("appCourseDao")
public class AppCourseDaoImpl extends GenericDaoImpl implements AppCourseDao{

	@Override
	public List<AppCourseDto> queryAppMainCourse(QueryAppCourseCondition query,
			PageEntity page) {
		// TODO Auto-generated method stub
		return queryForListPage("AppMainCourseMapper.queryAppMainCourse", query, page);
	}

	@Override
	public void delAppCourseById(long id) {
		// TODO Auto-generated method stub
		delete("AppMainCourseMapper.delAppCourseById", id);
	}

	@Override
	public void createAppMainCourse(AppCourse appCourse) {
		// TODO Auto-generated method stub
		insert("AppMainCourseMapper.createAppMainCourse", appCourse);
	}

	@Override
	public int getAppCourseById(Long id) {
		// TODO Auto-generated method stub
		return selectOne("AppMainCourseMapper.getAppCourseById", id);
	}
	
	/**
	 * 批量删除app课程
	 * @param ids id字符串集合
	 */
	public void delAppCourseBatch(String ids){
		this.delete("AppMainCourseMapper.delAppCourseBatch", ids);
	}

}
