package io.wangxiao.edu.app.dao.impl.website;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.app.dao.website.AppCourseDao;
import io.wangxiao.edu.app.entity.website.AppCourse;
import io.wangxiao.edu.app.entity.website.AppCourseDto;
import io.wangxiao.edu.app.entity.website.QueryAppCourseCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("appCourseDao")
public class AppCourseDaoImpl extends GenericDaoImpl implements AppCourseDao {

    @Override
    public List<AppCourseDto> queryAppMainCourse(QueryAppCourseCondition query,
                                                 PageEntity page) {
        return queryForListPage("AppMainCourseMapper.queryAppMainCourse", query, page);
    }

    @Override
    public void delAppCourseById(long id) {
        delete("AppMainCourseMapper.delAppCourseById", id);
    }

    @Override
    public void createAppMainCourse(AppCourse appCourse) {
        insert("AppMainCourseMapper.createAppMainCourse", appCourse);
    }

    @Override
    public int getAppCourseById(Long id) {
        return selectOne("AppMainCourseMapper.getAppCourseById", id);
    }

    /**
     * 批量删除app课程
     *
     * @param ids id字符串集合
     */
    public void delAppCourseBatch(String ids) {
        this.delete("AppMainCourseMapper.delAppCourseBatch", ids);
    }

}
