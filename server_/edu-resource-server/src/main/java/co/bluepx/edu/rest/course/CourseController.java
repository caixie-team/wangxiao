package io.wangxiao.api.rest.course;

import co.bluepx.edu.course.entity.*;
import co.bluepx.edu.course.service.CourseAssessService;
import co.bluepx.edu.course.service.CourseKpointService;
import co.bluepx.edu.course.service.CourseService;
import co.bluepx.edu.course.service.TrxorderDetailService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by bison on 12/30/15.
 * <p>
 * 课程 api
 */
@Api(value = "course-api", description = "课程 API", position = 1)
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    // 课程服务
    @Autowired
    CourseService courseService;

    // 课时服务(知识点、目录)
    @Autowired
    CourseKpointService courseKpointService;

    // 课程评论服务
    @Autowired
    CourseAssessService courseAssessService;

    //订单流水
    @Autowired
    TrxorderDetailService trxorderDetailService;

    @ApiOperation(value = "根据 ID 查询课程详情", notes = "返回课程详情", response = Course.class, position = 2)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") long id) {

        return new ResponseEntity(courseService.findCourseDtoById(id), HttpStatus.OK);

    }

    /**
     * 获取课时列表
     *
     * @return
     */
    @ApiOperation(value = "根据 ID 查询课时", notes = "返回课程课时列表", response = CourseKpoint.class, position = 2)
    @RequestMapping(value = "/{id}/lessons", method = RequestMethod.GET)
    public ResponseEntity getCourseLessons(@PathVariable Long id) {

        List<CourseKpoint> courseKpointList = courseKpointService.findCourseKpointsByCouseId(id);
        List<CourseKpoint> nodeList = new ArrayList<>();

        // 遍历章节课时
        for (CourseKpoint k1 : courseKpointList) {
            boolean mark = false;

            for (CourseKpoint k2 : courseKpointList) {

                if (k1.getParentId() != null && k1.getParentId().equals(k2.getId())) {
                    mark = true;

                    if (k2.getChildKpoints() == null)
                        k2.setChildKpoints(new ArrayList<>());

                    k2.getChildKpoints().add(k1);

                    break;
                }
            }
            if (!mark) {
                nodeList.add(k1);
            }
        }
        return new ResponseEntity(nodeList, HttpStatus.OK);

    }

    /**
     * 根据ID查询网站配置信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据 ID 查询课程推荐类别", notes = "返回课程 DTO", response = CourseDto.class, position = 3)
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public ResponseEntity getRecommendCourses(@PathVariable("id") Long id) {

        return new ResponseEntity(courseService.findRecommendCourses(id), HttpStatus.OK);
    }

    /**
     * 分页查询课程信息
     *
     * @param queryCourse
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "分页查询课程信息", notes = "返回分页查询后的课程列表", response = PageInfo.class)
    @RequestMapping(value = "/explore", method = RequestMethod.GET)
    public ResponseEntity getCoursesByPage(@ModelAttribute QueryCourse queryCourse, @RequestParam int pageNum, @RequestParam int pageSize) {

        PageInfo<CourseDto> pageCourse = courseService.findCourseListByPage(queryCourse, pageNum, pageSize);

        return new ResponseEntity(pageCourse, HttpStatus.OK);
    }


    /**
     * 根据老师id查询相关课程列表
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据老师id查询相关课程列表", notes = "返回课程列表", response = Course.class)
    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    public ResponseEntity findCourseByTeacherId(@PathVariable("id") Long id) {
        return new ResponseEntity(courseService.findCourseByTeacherId(id), HttpStatus.OK);
    }


    /**
     * 课程详情咨询列表
     *
     * @return
     */
    @ApiOperation(value = "根据课程ID查询课程评论列表", notes = "返回课程评论列表", response = QueryCourseAssess.class)
    @RequestMapping(value = "/{id}/assess", method = RequestMethod.GET)
    public ResponseEntity getAssessList(@PathVariable("id") Long id, @RequestParam int pageNum, @RequestParam int pageSize) {

        PageInfo<QueryCourseAssess> pageCourseAssess;
        CourseDto course = courseService.findCourseDtoById(id);

        if (course.getSellType().equalsIgnoreCase("PACKAGE")) { // 为套餐课程
            String courseIds = "";
            List<Long> ids = new ArrayList<>();
            ids.add(id);

            List<CourseDto> courseList = courseService.findCourseListPackage(ids);

//            遍历课程集合获得字符串
            if (courseList != null && courseList.size() > 0) {
                for (CourseDto dto : courseList) {
                    courseIds += dto.getId() + ",";
                }
            }
            courseIds = courseIds.substring(0, courseIds.length() - 1);
            pageCourseAssess = courseAssessService.findPackageCourseAssessByPage(courseIds, pageNum, pageSize);

        } else {
            pageCourseAssess = courseAssessService.findCourseAssessByPage(id, pageNum, pageSize);
        }
        return new ResponseEntity(pageCourseAssess, HttpStatus.OK);

    }


    /**
     * 获取用户购买课程的课程
     *
     * @return
     */
    @Deprecated
    @ApiOperation(value = "获取用户购买课程的课程", notes = "过期API,现可采用 /user/courses ", response = Course.class)
    @RequestMapping(value = "/{userId}/buy", method = RequestMethod.GET)
    public ResponseEntity getCoursesByUserId(@PathVariable("userId") Long userId) {
        List<TrxorderDetail> lists = trxorderDetailService.getTrxorderDetailListBuy(userId);
        return new ResponseEntity(courseService.findCourseListByCourseIds(lists), HttpStatus.OK);
    }

}
