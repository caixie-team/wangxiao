package io.wangxiao.edu.home.dao.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseReserveRecord;

import java.util.List;
import java.util.Map;

public interface CourseReserveRecordDao {

    /**
     * 课程预定记录list
     *
     * @param courseReserveRecord
     * @param page
     * @return
     */
    List<CourseReserveRecord> getCourseReserveRecordPageList(CourseReserveRecord courseReserveRecord, PageEntity page);

    /**
     * 插入课程预定记录
     *
     * @param courseReserveRecord
     * @return
     */
    Long addCourseReserveRecord(CourseReserveRecord courseReserveRecord);

    /**
     * 根据id查询课程预定记录
     *
     * @param id
     * @return
     */
    CourseReserveRecord getCourseReserveRecord(Long id);

    /**
     * 根据条件查询课程预定记录
     *
     * @param courseReserveRecord
     * @return
     */
    CourseReserveRecord queryCourseReserveRecord(CourseReserveRecord courseReserveRecord);

    /**
     * 检查用户是否预约了该课程
     *
     * @param map
     * @return
     */
    Integer checkCourseReserved(Map<String, Object> map);

    /**
     * 审核通过预约
     *
     * @param recordId
     */
    void passCheck(Long recordId);

    /**
     * 删除课程预定记录
     *
     * @param courseId 课程id
     */
    void delteCourseReservedByCourseId(Long courseId);

    /**
     * 预约直播人数
     *
     * @param id 课程id
     * @return
     */
    Integer getCourseReserveRecordCount(Long id);
}
