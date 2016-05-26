package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.common.constants.MemConstans;
import io.wangxiao.edu.common.util.StatisticsUtil;
import io.wangxiao.edu.home.constants.enums.StatisticsQueryType;
import io.wangxiao.edu.home.dao.course.CourseKpointDao;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseKpoint;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository("courseKpointDao")
public class CourseKpointDaoImpl extends GenericDaoImpl implements CourseKpointDao {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public java.lang.Long addCourseKpoint(CourseKpoint courseKpoint) {
        //存入redis  今日新增课时
        StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.MEMFIX + StatisticsQueryType.KPOINT_NUM.toString(), sdf.format(new Date()));
        return this.insert("CourseKpointMapper.createCourseKpoint", courseKpoint);
    }


    public void deleteCourseKpointById(Long id) {
        this.delete("CourseKpointMapper.deleteCourseKpointById", id);
    }

    @Override
    public void deleteCourseKpointByCourseId(Long courseId) {
        this.delete("CourseKpointMapper.deleteCourseKpointByCourseId", courseId);
    }

    /**
     * 根据id集合删除CourseKpoint
     */
    public void deleteCourseKpointByIdBatch(String[] courseKpointIds) {
        this.update("CourseKpointMapper.deleteCourseKpointByIdBatch", courseKpointIds);
    }

    public void updateCourseKpoint(CourseKpoint courseKpoint) {
        this.update("CourseKpointMapper.updateCourseKpoint", courseKpoint);
    }

    /**
     * 修改CourseKpoint 播放数加一
     */
    public void updateCourseKpointPlaycountAdd(Long kpointId) {
        this.update("CourseKpointMapper.updateCourseKpointPlaycountAdd", kpointId);
    }

    public CourseKpoint getCourseKpointById(Long id) {
        return this.selectOne("CourseKpointMapper.getCourseKpointById", id);
    }

    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint) {
        return this.selectList("CourseKpointMapper.getCourseKpointList", courseKpoint);
    }

    @Override
    public List<CourseKpoint> getCourseKpointListPage(CourseKpoint courseKpoint, PageEntity page) {
        return this.queryForListPage("CourseKpointMapper.getCourseKpointListPage", courseKpoint, page);
    }

    public List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint, PageEntity page) {
        return this.queryForListPage("CourseKpointMapper.getCourseKpointNewList", courseKpoint, page);
    }

    /**
     * 根据课程集合获取节点集合
     *
     * @param list
     * @return
     */
    public Long getCourseKpointNumByCourseList(List<CourseDto> list) {
        return this.selectOne("CourseKpointMapper.getCourseKpointNumByCourseList", list);
    }

    /**
     * 根据几点ID集合字符串查询节点集合
     *
     * @param ids
     * @return
     */
    public List<CourseKpoint> getCourseKpointListByIds(String ids) {
        return this.selectList("CourseKpointMapper.getCourseKpointByIds", ids);
    }

    /**
     * 批量添加子节点
     *
     * @param childCourseKpointList
     */
    public void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList) {
        for (int i = 0; i < childCourseKpointList.size(); i++) {
            //存入redis  今日新增课时
            StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.MEMFIX + StatisticsQueryType.KPOINT_NUM.toString(), sdf.format(new Date()));
        }
        this.insert("CourseKpointMapper.createChildCourseKpointList", childCourseKpointList);
    }


    /**
     * 章节父节点变更
     */
    public void updateCourseKpointParentId(CourseKpoint courseKpoint) {
        this.update("CourseKpointMapper.updateCourseKpointParentId", courseKpoint);
    }


    @Override
    public Long addKpoint(CourseKpoint courseKpoint) {
        //存入redis  今日新增课时
        StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.MEMFIX + StatisticsQueryType.KPOINT_NUM.toString(), sdf.format(new Date()));
        // TODO Auto-generated method stub
        return this.insert("CourseKpointMapper.addKpoint", courseKpoint);
    }

    @Override
    public Long getCourseKpointCount(Long courseId) {
        return this.selectOne("CourseKpointMapper.getCourseKpointCount", courseId);
    }


    @Override
    public void updateSortable(CourseKpoint courseKpointlist) {
        // TODO Auto-generated method stub
        this.update("CourseKpointMapper.updateSortable", courseKpointlist);
    }


    @Override
    public void updateKpointName(CourseKpoint courseKpointlist) {
        this.update("CourseKpointMapper.updateKpointName", courseKpointlist);

    }


    @Override
    public Long getCourseKpointSort(Long courseid) {
        // TODO Auto-generated method stub
        return this.selectOne("CourseKpointMapper.getCourseKpointSort", courseid);
    }


    @Override
    public Long getCourseKpointListPageCount(CourseKpoint CourseKpoint) {
        // TODO Auto-generated method stub
        return this.selectOne("CourseKpointMapper.getCourseKpointListCount", CourseKpoint);
    }


    @Override
    public Long getCourseKpointId(CourseKpoint CourseKpoint) {
        // TODO Auto-generated method stub
        return this.selectOne("CourseKpointMapper.getCourseKpointId", CourseKpoint);
    }


    public void addCourseKpointBatch(List<CourseKpoint> courseKpointList) {
        for (int i = 0; i < courseKpointList.size(); i++) {
            //存入redis  今日新增课时
            StatisticsUtil.incrActionOneDayOrActionCount(MemConstans.MEMFIX + StatisticsQueryType.KPOINT_NUM.toString(), sdf.format(new Date()));
        }
        this.insert("CourseKpointMapper.addCourseKpointBatch", courseKpointList);
    }
}
