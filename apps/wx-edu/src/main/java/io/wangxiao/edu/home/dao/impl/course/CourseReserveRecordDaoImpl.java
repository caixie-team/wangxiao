package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.CourseReserveRecordDao;
import io.wangxiao.edu.home.entity.course.CourseReserveRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("courseReserveRecordDao")
public class CourseReserveRecordDaoImpl extends GenericDaoImpl implements CourseReserveRecordDao {

    @Override
    public List<CourseReserveRecord> getCourseReserveRecordPageList(CourseReserveRecord courseReserveRecord,
                                                                    PageEntity page) {
        return this.queryForListPage("CourseReserveRecordMapper.getCourseReserveRecordPageList", courseReserveRecord, page);
    }

    @Override
    public Long addCourseReserveRecord(CourseReserveRecord courseReserveRecord) {
        return this.insert("CourseReserveRecordMapper.addCourseReserveRecord", courseReserveRecord);
    }

    @Override
    public CourseReserveRecord getCourseReserveRecord(Long id) {
        return this.selectOne("CourseReserveRecordMapper.getCourseReserveRecord", id);
    }

    @Override
    public CourseReserveRecord queryCourseReserveRecord(CourseReserveRecord courseReserveRecord) {
        return this.selectOne("CourseReserveRecordMapper.queryCourseReserveRecord", courseReserveRecord);
    }

    @Override
    public Integer checkCourseReserved(Map<String, Object> map) {
        return this.selectOne("CourseReserveRecordMapper.checkCourseReserved", map);
    }

    @Override
    public void passCheck(Long recordId) {
        this.update("CourseReserveRecordMapper.passCheck", recordId);
    }

    @Override
    public void delteCourseReservedByCourseId(Long courseId) {
        this.delete("CourseReserveRecordMapper.delteCourseReservedByCourseId", courseId);
    }

    @Override
    public Integer getCourseReserveRecordCount(Long id) {
        return this.selectOne("CourseReserveRecordMapper.getCourseReserveRecordCount", id);
    }
}
