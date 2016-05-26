package io.wangxiao.edu.home.service.impl.course;

import com.google.gson.Gson;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.constants.enums.VideoType;
import io.wangxiao.edu.home.dao.course.CourseKpointDao;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseKpoint;
import io.wangxiao.edu.home.service.course.CourseKpointService;
import io.wangxiao.edu.home.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseKpointService")
public class CourseKpointServiceImpl implements CourseKpointService {

    @Autowired
    private CourseKpointDao courseKpointDao;
    @Autowired
    private CourseService courseService;

    /**
     * 添加CourseKpoint
     *
     * @param courseKpoint 要添加的CourseKpoint
     * @return id
     */
    public java.lang.Long addCourseKpoint(CourseKpoint courseKpoint) {
        courseKpoint.setAddTime(new Date());
        // 如果是56视频类型则把数据转成json存入字段
        if (VideoType.FIVESIX.toString().equals(courseKpoint.getVideotype())) {
            courseKpoint.setVideojson(initVideojson(courseKpoint.getVideojson()));
        }
        Long id = courseKpointDao.addCourseKpoint(courseKpoint);
        // 添加总课时
        Course course = courseService.getCourseById(courseKpoint.getCourseId());
        // 课程课节
        if (ObjectUtils.isNotNull(course) && courseKpoint.getType().intValue() == 0) {
            course.setLessionnum(course.getLessionnum() + 1);
            courseService.updateCourse(course);
        }
        return id;
    }

    public String initVideojson(String videojson) {
        // 56视频的回调参数（只有56 会有）
        if (StringUtils.isNotEmpty(videojson)) {
            String[] arr = videojson.split(",");
            Map<String, String> map = new HashMap<String, String>();
            map.put("vid", arr[0]);
            map.put("subject", arr[1]);
            map.put("url", arr[2]);
            map.put("result", arr[3]);
            map.put("player", arr[4]);
            map.put("chk", arr[5]);
            map.put("cover", arr[6]);
            map.put("coop_public", arr[7]);
            map.put("forbid", arr[8]);
            map.put("coopid", arr[9]);
            map.put("sid", arr[10]);
            map.put("category", arr[11]);
            map.put("attach", arr[12]);
            map.put("tags", arr[13]);
            map.put("content", arr[14]);
            map.put("item_1", arr[15]);
            Gson gson = new Gson();
            return gson.toJson(map);
        }
        return "";
    }

    /**
     * 根据id删除一个CourseKpoint
     *
     * @param id 要删除的id
     */
    public void deleteCourseKpointById(Long id) {
        courseKpointDao.deleteCourseKpointById(id);
    }

    /**
     * 根据id集合删除CourseKpoint
     */
    public void deleteCourseKpointByIdBatch(String[] courseKpointIds) {
        courseKpointDao.deleteCourseKpointByIdBatch(courseKpointIds);
        CourseKpoint courseKpoint = courseKpointDao.getCourseKpointById(new Long(courseKpointIds[0]));
        Course course = courseService.getCourseById(courseKpoint.getCourseId());
        if (ObjectUtils.isNotNull(course)) {
            Long courseKpointCount = courseKpointDao.getCourseKpointCount(courseKpoint.getCourseId());
            course.setLessionnum(courseKpointCount);
            courseService.updateCourse(course);
        }
    }

    /**
     * 修改CourseKpoint
     *
     * @param courseKpoint 要修改的CourseKpoint
     */
    public void updateCourseKpoint(CourseKpoint courseKpoint) {
        // 如果是56视频类型则把数据转成json存入字段
        if (StringUtils.isNotEmpty(courseKpoint.getVideojson())
                && VideoType.FIVESIX.toString().equals(courseKpoint.getVideotype())) {
            courseKpoint.setVideojson(initVideojson(courseKpoint.getVideojson()));
        }
        courseKpointDao.updateCourseKpoint(courseKpoint);
    }

    /**
     * 修改CourseKpoint 播放数加一
     */
    public void updateCourseKpointPlaycountAdd(Long kpointId) {
        courseKpointDao.updateCourseKpointPlaycountAdd(kpointId);
    }

    /**
     * 根据id获取单个CourseKpoint对象
     *
     * @param id 要查询的id
     * @return CourseKpoint
     */
    public CourseKpoint getCourseKpointById(Long id) {
        return courseKpointDao.getCourseKpointById(id);
    }

    /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint) {
        return courseKpointDao.getCourseKpointList(courseKpoint);
    }

    @Override
    public List<CourseKpoint> getCourseKpointListPage(CourseKpoint courseKpoint, PageEntity page) {
        return courseKpointDao.getCourseKpointListPage(courseKpoint, page);
    }

    /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
    public List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint, PageEntity page) {
        return courseKpointDao.getCourseKpointNewList(courseKpoint, page);
    }

    /**
     * 根据几点ID集合字符串查询节点集合
     *
     * @param ids
     * @return
     */
    public List<CourseKpoint> getCourseKpointListByIds(String ids) {
        return courseKpointDao.getCourseKpointListByIds(ids);
    }

    /**
     * 批量添加子节点
     *
     * @param childCourseKpointList
     */
    public void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList) {
        courseKpointDao.createChildCourseKpointList(childCourseKpointList);
    }

    @Override
    public Long addKpoint(CourseKpoint courseKpoint) {
        // 如果没给时间则给0
        if (courseKpoint.getCourseMinutes() == null) {
            courseKpoint.setCourseMinutes(0L);
        }
        if (courseKpoint.getCourseSeconds() == null) {
            courseKpoint.setCourseSeconds(0L);
        }

        courseKpoint.setAddTime(new Date());
        //默认状态0为正常
        courseKpoint.setStatus(0L);
        // 塞入url(默认) mp3放入视频的地址中
/*	if (courseKpoint.getCourseType().equals(CourserKpointType.MP3.toString())) {
        courseKpoint.setVideourl(courseKpoint.getCourseText());
	    courseKpoint.setCourseText("");
	}*/
        return courseKpointDao.addKpoint(courseKpoint);
    }

    /**
     * 章节父节点变更
     */
    public void updateCourseKpointParentId(CourseKpoint courseKpoint) {
        this.courseKpointDao.updateCourseKpointParentId(courseKpoint);
    }

    @Override
    public void updateSortable(CourseKpoint courseKpointlist) {
        courseKpointDao.updateSortable(courseKpointlist);
    }

    @Override
    public void updateKpointName(CourseKpoint courseKpointlist) {
        courseKpointDao.updateKpointName(courseKpointlist);
    }

    @Override
    public Long getCourseKpointSort(Long courseid) {
        return courseKpointDao.getCourseKpointSort(courseid);
    }

    @Override
    public Long getCourseKpointListPageCount(CourseKpoint CourseKpoint) {
        return courseKpointDao.getCourseKpointListPageCount(CourseKpoint);
    }

    @Override
    public Long getCourseKpointId(CourseKpoint CourseKpoint) {
        return courseKpointDao.getCourseKpointId(CourseKpoint);
    }

    public void addCourseKpointBatch(List<CourseKpoint> courseKpointList) {
        courseKpointDao.addCourseKpointBatch(courseKpointList);
    }
}