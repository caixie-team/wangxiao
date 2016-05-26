package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.course.CourseNoteDao;
import io.wangxiao.edu.home.entity.course.CourseNote;
import io.wangxiao.edu.home.entity.course.QueryCourseNote;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("courseNoteDao")
public class CourseNoteDaoImpl extends GenericDaoImpl implements CourseNoteDao {
    /**
     * 添加CourseNote
     *
     * @param courseNote 要添加的CourseNote
     * @return id
     */
    public java.lang.Long addCourseNote(CourseNote courseNote) {
        return this.insert("CourseNoteMapper.createCourseNote", courseNote);
    }

    /**
     * 根据id删除一个CourseNote
     *
     * @param id 要删除的id
     */
    public void deleteCourseNoteById(Long id) {
        this.delete("CourseNoteMapper.deleteCourseNoteById", id);
    }

    @Override
    public void deleteCourseNoteByCourseId(Long courseId) {
        this.delete("CourseNoteMapper.deleteCourseNoteByCourseId", courseId);
    }

    /**
     * 修改CourseNote
     *
     * @param courseNote 要修改的CourseNote
     */
    public void updateCourseNote(CourseNote courseNote) {
        this.update("CourseNoteMapper.updateCourseNote", courseNote);
    }

    /**
     * 根据id获取单个CourseNote对象
     *
     * @param id 要查询的id
     * @return CourseNote
     */
    public CourseNote getCourseNoteById(Long id) {
        return this.selectOne("CourseNoteMapper.getCourseNoteById", id);
    }

    /**
     * 根据用户id和节点id查询笔记
     *
     * @return CourseNote
     */
    public CourseNote getCourseNoteByKpointIdAndUserId(Long kpointId, Long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("kpointId", kpointId);
        map.put("userId", userId);
        List<CourseNote> courseNoteList = this.selectList("CourseNoteMapper.getCourseNoteByKpointIdAndUserId", map);
        // 如果返回为多个只取第一个
        if (ObjectUtils.isNotNull(courseNoteList) && courseNoteList.size() > 0) {
            return courseNoteList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据条件获取CourseNote列表
     *
     * @param courseNote 查询条件
     * @return List<CourseNote>
     */
    public List<CourseNote> getCourseNoteList(CourseNote courseNote) {
        return this.selectList("CourseNoteMapper.getCourseNoteList", courseNote);
    }

    /**
     * 查询笔记 分页
     *
     * @param queryCourseNote
     * @param page
     * @return
     */
    public List<QueryCourseNote> getCourseNoteListPage(QueryCourseNote queryCourseNote, PageEntity page) {
        return this.queryForListPage("CourseNoteMapper.getCourseNoteListPage", queryCourseNote, page);
    }

    /**
     * 更新显示隐藏
     *
     * @param courseNote
     */
    public void updateCourseNoteListStatus(CourseNote courseNote) {
        this.update("CourseNoteMapper.updateCourseNoteListStatus", courseNote);
    }

    /**
     * 查询 QueryCourseNote
     *
     * @param id
     * @return
     */
    public QueryCourseNote getQueryCourseNoteById(Long id) {
        return this.selectOne("CourseNoteMapper.getQueryCourseNoteById", id);
    }

    /**
     * 查询用户笔记
     *
     * @param userId
     * @param page
     * @return
     */
    public List<QueryCourseNote> getUserCourseNoteByUserId(Long userId, PageEntity page) {
        return this.queryForListPage("CourseNoteMapper.getUserCourseNoteByUserId", userId, page);
    }
}
