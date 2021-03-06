package co.bluepx.edu.course.service;

import co.bluepx.edu.core.BaseService;
import co.bluepx.edu.core.Condition;
import co.bluepx.edu.course.dao.CourseKpointDao;
import co.bluepx.edu.course.entity.CourseKpoint;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * CourseKpoint管理接口
 */
@Service
public class CourseKpointService extends BaseService<CourseKpoint, CourseKpointDao> {

    public List<CourseKpoint> findCourseKpointsByCouseId(Long couseId) {

        return baseDao.find(null,
                Arrays.asList(
                        Condition.parseCondition("status.int.eq").setValue(0),
                        Condition.parseCondition("course_id.Long.eq").setValue(couseId)
                ));
//        from edu_course_kpoint
//        <where>
//                edu_course_kpoint.status = 0
//                <if test="courseId!=null">
//                and edu_course_kpoint.course_id=#{courseId}
//        </if>
    }

    /**
     * 添加CourseKpoint
     *
     * @param courseKpoint 要添加的CourseKpoint
     * @return id
     */
    public Long addCourseKpoint(CourseKpoint courseKpoint) {
//        courseKpoint.setAddTime(new Date());
        //如果是56视频类型则把数据转成json存入字段
//        if (VideoType.FIVESIX.toString().equals(courseKpoint.getVideotype())) {
//            courseKpoint.setVideojson(initVideojson(courseKpoint.getVideojson()));
//        }
//        return courseKpointDao.addCourseKpoint(courseKpoint);
        return 1L;
    }

    public String initVideojson(String videojson) {
        // 56视频的回调参数（只有56 会有）
//        if (StringUtils.isNotEmpty(videojson)) {
//            String[] arr = videojson.split(",");
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("vid", arr[0]);
//            map.put("subject", arr[1]);
//            map.put("url", arr[2]);
//            map.put("result", arr[3]);
//            map.put("player", arr[4]);
//            map.put("chk", arr[5]);
//            map.put("cover", arr[6]);
//            map.put("coop_public", arr[7]);
//            map.put("forbid", arr[8]);
//            map.put("coopid", arr[9]);
//            map.put("sid", arr[10]);
//            map.put("category", arr[11]);
//            map.put("attach", arr[12]);
//            map.put("tags", arr[13]);
//            map.put("content", arr[14]);
//            map.put("item_1", arr[15]);
//            Gson gson = new Gson();
//            return gson.toJson(map);
//        }
        return "";
    }

    /**
     * 根据id删除一个CourseKpoint
     *
     * @param id 要删除的id
     */
//    public void deleteCourseKpointById(Long id) {
//        courseKpointDao.deleteCourseKpointById(id);
//    }

    /**
     * 根据id集合删除CourseKpoint
     */
//    public void deleteCourseKpointByIdBatch(String[] courseKpointIds) {
//        courseKpointDao.deleteCourseKpointByIdBatch(courseKpointIds);
//    }

    /**
     * 修改CourseKpoint
     *
     * @param courseKpoint 要修改的CourseKpoint
     */
//    public void updateCourseKpoint(CourseKpoint courseKpoint) {
    //如果是56视频类型则把数据转成json存入字段
//        if (StringUtils.isNotEmpty(courseKpoint.getVideojson()) && VideoType.FIVESIX.toString().equals(courseKpoint.getVideotype())) {
//            courseKpoint.setVideojson(initVideojson(courseKpoint.getVideojson()));
//        }
//        courseKpointDao.updateCourseKpoint(courseKpoint);
//    }

    /**
     * 修改CourseKpoint 播放数加一
     */
//    public void updateCourseKpointPlaycountAdd(Long kpointId) {
//        courseKpointDao.updateCourseKpointPlaycountAdd(kpointId);
//    }

    /**
     * 根据id获取单个CourseKpoint对象
     *
     * @param id 要查询的id
     * @return CourseKpoint
     */
//    public CourseKpoint getCourseKpointById(Long id) {
//        return courseKpointDao.getCourseKpointById(id);
//    }

    /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
//    public List<CourseKpoint> getCourseKpointList(CourseKpoint courseKpoint) {
//        return courseKpointDao.getCourseKpointList(courseKpoint);
//    }

    /**
     * 根据条件获取CourseKpoint列表
     *
     * @param courseKpoint 查询条件
     * @return List<CourseKpoint>
     */
//    public List<CourseKpoint> getCourseKpointNewList(CourseKpoint courseKpoint, PageEntity page) {
//        return courseKpointDao.getCourseKpointNewList(courseKpoint, page);
//    }

    /**
     * 根据几点ID集合字符串查询节点集合
     *
     * @param ids
     * @return
     */
//    public List<CourseKpoint> getCourseKpointListByIds(String ids) {
//        return courseKpointDao.getCourseKpointListByIds(ids);
//    }

    /**
     * 批量添加子节点
     *
     * @param childCourseKpointList
     */
//    public void createChildCourseKpointList(List<CourseKpoint> childCourseKpointList) {
//        courseKpointDao.createChildCourseKpointList(childCourseKpointList);
//    }
}