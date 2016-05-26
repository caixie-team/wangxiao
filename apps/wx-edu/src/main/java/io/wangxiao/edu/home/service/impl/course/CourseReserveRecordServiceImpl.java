package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.course.CourseReserveRecordDao;
import io.wangxiao.edu.home.entity.course.CourseReserveRecord;
import io.wangxiao.edu.home.service.course.CourseReserveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseReserveRecordService")
public class CourseReserveRecordServiceImpl implements CourseReserveRecordService {

    @Autowired
    private CourseReserveRecordDao courseReserveRecordDao;

    @Override
    public List<CourseReserveRecord> getCourseReserveRecordPageList(CourseReserveRecord courseReserveRecord,
                                                                    PageEntity page) {
        return courseReserveRecordDao.getCourseReserveRecordPageList(courseReserveRecord, page);
    }

    @Override
    public Long addCourseReserveRecord(CourseReserveRecord courseReserveRecord) {
        return courseReserveRecordDao.addCourseReserveRecord(courseReserveRecord);
    }

    @Override
    public CourseReserveRecord getCourseReserveRecord(Long id) {
        return courseReserveRecordDao.getCourseReserveRecord(id);
    }

    @Override
    public CourseReserveRecord queryCourseReserveRecord(CourseReserveRecord courseReserveRecord) {
        return courseReserveRecordDao.queryCourseReserveRecord(courseReserveRecord);
    }

    @Override
    public boolean checkCourseReserved(Long userId, Long courseId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("courseId", courseId);
        Integer result = courseReserveRecordDao.checkCourseReserved(map);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void passCheck(Long recordId) {
        courseReserveRecordDao.passCheck(recordId);
    }


    @Override
    public Integer getCourseReserveRecordCount(Long id) {
        Integer integer = courseReserveRecordDao.getCourseReserveRecordCount(id);
        if (ObjectUtils.isNotNull(integer)) {
            return integer;
        } else {
            return 0;
        }
    }
}
