package io.wangxiao.edu.home.service.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseReserveRecord;

import java.util.List;

public interface CourseReserveRecordService {
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
     * 检查用户是否预约了该课程,返回true就预约了
     *
     * @param userId
     * @param courseId
     * @return
     */
    boolean checkCourseReserved(Long userId, Long courseId);

    /**
     * 审核通过预约
     *
     * @param recordId
     */
    void passCheck(Long recordId);


    /**
     * 预约直播人数
     *
     * @param id 课程id
     * @return
     */
    Integer getCourseReserveRecordCount(Long id);
}
