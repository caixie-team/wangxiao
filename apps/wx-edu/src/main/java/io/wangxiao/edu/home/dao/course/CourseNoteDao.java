package io.wangxiao.edu.home.dao.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.CourseNote;
import io.wangxiao.edu.home.entity.course.QueryCourseNote;

import java.util.List;

public interface CourseNoteDao {

    /**
     * 添加CourseNote
     *
     * @param courseNote 要添加的CourseNote
     * @return id
     */
    java.lang.Long addCourseNote(CourseNote courseNote);

    /**
     * 根据id删除一个CourseNote
     *
     * @param id 要删除的id
     */
    void deleteCourseNoteById(Long id);

    /**
     * 根据课程id删除课程笔记
     *
     * @param courseId 课程id
     */
    void deleteCourseNoteByCourseId(Long courseId);

    /**
     * 修改CourseNote
     *
     * @param courseNote 要修改的CourseNote
     */
    void updateCourseNote(CourseNote courseNote);

    /**
     * 根据id获取单个CourseNote对象
     *
     * @param id 要查询的id
     * @return CourseNote
     */
    CourseNote getCourseNoteById(Long id);

    /**
     * 根据用户id和节点id查询笔记
     *
     * @return CourseNote
     */
    CourseNote getCourseNoteByKpointIdAndUserId(Long kpointId, Long userId);

    /**
     * 根据条件获取CourseNote列表
     *
     * @param courseNote 查询条件
     * @return List<CourseNote>
     */
    List<CourseNote> getCourseNoteList(CourseNote courseNote);

    /**
     * 查询笔记 分页
     *
     * @param queryCourseNote
     * @param page
     * @return
     */
    List<QueryCourseNote> getCourseNoteListPage(QueryCourseNote queryCourseNote, PageEntity page);

    /**
     * 更新显示隐藏
     *
     * @param courseNote
     */
    void updateCourseNoteListStatus(CourseNote courseNote);

    /**
     * 查询 QueryCourseNote
     *
     * @param id
     * @return
     */
    QueryCourseNote getQueryCourseNoteById(Long id);

    /**
     * 查询用户笔记
     *
     * @param userId
     * @param page
     * @return
     */
    List<QueryCourseNote> getUserCourseNoteByUserId(Long userId, PageEntity page);
}