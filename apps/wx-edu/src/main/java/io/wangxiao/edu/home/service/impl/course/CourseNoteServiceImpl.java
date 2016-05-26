package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.constants.enums.CourseProfileType;
import io.wangxiao.edu.home.constants.web.WebContants;
import io.wangxiao.edu.home.dao.course.CourseNoteDao;
import io.wangxiao.edu.home.entity.course.CourseNote;
import io.wangxiao.edu.home.entity.course.CourseProfile;
import io.wangxiao.edu.home.entity.course.QueryCourseNote;
import io.wangxiao.edu.home.service.course.CourseNoteService;
import io.wangxiao.edu.home.service.course.CourseProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseNoteService")
public class CourseNoteServiceImpl implements CourseNoteService {

    @Autowired
    private CourseNoteDao courseNoteDao;
    @Autowired
    private CourseProfileService courseProfileService;

    /**
     * 添加CourseNote
     *
     * @param courseNote 要添加的CourseNote
     * @return id
     */
    public String addCourseNote(CourseNote courseNote) {
        if (ObjectUtils.isNull(getCourseNoteByKpointIdAndUserId(courseNote.getKpointId(), courseNote.getUserId()))) {
            // 更新课程的笔记数量
            courseProfileService.updateCourseProfile(CourseProfileType.notecount.toString(), courseNote.getCourseId(), 1L, CourseProfile.ADD);
            courseNoteDao.addCourseNote(courseNote);
        } else {
            courseNoteDao.updateCourseNote(courseNote);
        }
        return WebContants.SUCCESS;
    }

    /**
     * 根据id删除一个CourseNote
     *
     * @param id 要删除的id
     */
    public void deleteCourseNoteById(Long id) {
        courseNoteDao.deleteCourseNoteById(id);
    }

    /**
     * 修改CourseNote
     *
     * @param courseNote 要修改的CourseNote
     */
    public void updateCourseNote(CourseNote courseNote) {
        courseNoteDao.updateCourseNote(courseNote);
    }

    /**
     * 根据id获取单个CourseNote对象
     *
     * @param id 要查询的id
     * @return CourseNote
     */
    public CourseNote getCourseNoteById(Long id) {
        return courseNoteDao.getCourseNoteById(id);
    }

    /**
     * 根据用户id和节点id查询笔记
     *
     * @return CourseNote
     */
    public CourseNote getCourseNoteByKpointIdAndUserId(Long kpointId, Long userId) {
        return courseNoteDao.getCourseNoteByKpointIdAndUserId(kpointId, userId);
    }

    /**
     * 根据条件获取CourseNote列表
     *
     * @param courseNote 查询条件
     * @return List<CourseNote>
     */
    public List<CourseNote> getCourseNoteList(CourseNote courseNote) {
        return courseNoteDao.getCourseNoteList(courseNote);
    }

    /**
     * 查询笔记 分页
     *
     * @param queryCourseNote
     * @param page
     * @return
     */
    public List<QueryCourseNote> getCourseNoteListPage(QueryCourseNote queryCourseNote, PageEntity page) {
        return courseNoteDao.getCourseNoteListPage(queryCourseNote, page);
    }

    /**
     * 更新显示隐藏
     *
     * @param courseNote
     */
    public void updateCourseNoteListStatus(CourseNote courseNote) {
        courseNoteDao.updateCourseNoteListStatus(courseNote);
    }

    /**
     * 查询 QueryCourseNote
     *
     * @param id
     * @return
     */
    public QueryCourseNote getQueryCourseNoteById(Long id) {
        return courseNoteDao.getQueryCourseNoteById(id);
    }

    /**
     * 查询用户笔记
     *
     * @param userId
     * @param page
     * @return
     */
    public List<QueryCourseNote> getUserCourseNoteByUserId(Long userId, PageEntity page) {
        return courseNoteDao.getUserCourseNoteByUserId(userId, page);
    }
}