package co.bluepx.edu.api.course;

import co.bluepx.edu.course.entity.*;
import co.bluepx.edu.course.service.CourseAssessService;
import co.bluepx.edu.course.service.CourseKpointService;
import co.bluepx.edu.course.service.CourseService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation(value = "根据 ID 查询课程详情", notes = "返回课程详情", response = Course.class, position = 2)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") long id) {

        //显示目录树list,单个课程时 list.size = 1
//        List<CourseDto> courseDirs = new ArrayList<>();

        //获取套餐的课程列表
        // TODO: 待修改套餐课程查询 @Bison
//        if (course.getSellType().equalsIgnoreCase(SellType.PACKAGE.toString())) {
//            courseDirs = courseService.findCourseListPackage(
//                    Arrays.asList(
//                            course.getId()
//                    ));
//        } else {
//            courseDirs.add(course);
//        }

//        Map<String, Object> courseMap = new HashMap<>();
//        courseMap.put("course", course);
//        courseMap.put("courseDir", courseDirs);

        return new ResponseEntity(courseService.findCourseDtoById(id), HttpStatus.OK);
//        return new ResponseEntity(courseMap, HttpStatus.OK);

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
//    public String assess(Model model,HttpServletRequest request, @RequestParam("courseId") Long courseId,@ModelAttribute("page") PageEntity page) {
//        try {
//            分页页数
//            page.setPageSize(6);
//            查询该课程下的评论
//            CourseAssess courseAssess = new CourseAssess();
//            List<QueryCourseAssess> courseAssessList = null;
//            CourseDto course = courseService.getCourseInfoById(courseId);
//            if (course.getSellType().equalsIgnoreCase("PACKAGE")) { // 为套餐课程
//                String courseIds = "";
//                List<Long> ids = new ArrayList<Long>();
//                ids.add(courseId);
//                List<CourseDto> courseList = courseService.getCourseListPackage(ids);
//                 遍历课程集合获得字符串
//                if (ObjectUtils.isNotNull(courseList) && courseList.size() > 0) {
//                    for (CourseDto courseDto : courseList) {
//                        courseIds = courseIds + courseDto.getId() + ",";
//                    }
//                }
//                courseAssess.setCourseIds(courseIds.substring(0, courseIds.length() - 1));
//                courseAssessList = courseAssessService.getAllCourseAssessListPage(courseAssess, page);
//            } else {
//                courseAssess.setCourseId(courseId);
//                courseAssessList = courseAssessService.getCourseAssessListPage(courseAssess, page);
//            }
//            model.addAttribute("courseAssessList", courseAssessList);
//            model.addAttribute("page", page);
//            return assess;
//        } catch (Exception e) {
//            logger.error("CourseAssessController.assess", e);
//            return setExceptionRequest(request, e);
//        }
//    }


//    public String getKopintHtml(Model model, HttpServletRequest request) {
//        try {
//            Long kpointId = Long.valueOf(request.getParameter("kpointId"));
//            // 查询节点
//            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);
//            boolean isok1 = false;
//            boolean isok2 = false;
//            if(courseKpoint.getIsfree()==2){//判断节点不可以试听
//                isok1 = trxorderService.checkCourseLook(courseKpoint.getCourseId(), getLoginUserId(request));
//                if(isok1==false){// 判断该课程不可以观看
//                    if(getLoginUserId(request)!=null&&getLoginUserId(request)>0){//登陆才判断会员
//                        isok2 = memberRecordService.checkUserMember(getLoginUserId(request),courseKpoint.getCourseId());
//                        if(isok2==false){//判断会员不可以观看
//                            logger.info("++isok:" + false);
//                            return getKopintHtml;
//                        }
//                    }
//                }
//            }
//
//            // 当传入数据不正确时直接返回
//            if (ObjectUtils.isNull(courseKpoint)) {
//                return getKopintHtml;
//            }
//            // 视频url
//            String videourl = courseKpoint.getVideourl();
//            // 播放类型
//            String videotype = courseKpoint.getVideotype();
//            //查询cc的appId key
//            Map<String,Object> map=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.cc.toString());
//            if(courseKpoint.getVideotype().equalsIgnoreCase(WebSiteProfileType.letv.toString())){//如果乐视
//                Map<String,Object>	lmap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.letv.toString());
//                model.addAttribute("websiteLetvmap", lmap);
//            }
//            //demo视频
//            if(courseKpoint.getVideotype().equalsIgnoreCase(WebSiteProfileType.mp4flv.toString())){
//                //根据id 查询视频返回的m3u8地址和MP4地址
//                Map<String,String> videomap = new HashMap<>();
//                String msg= HttpUtil.doGet(CommonConstants.videoPath + "/api/video/getVideoUrl/" + videourl, "");
//                if(StringUtils.isNotEmpty(msg)&&!"[]".equals(msg)){
//                    JsonObject jsonObject=jsonParser.parse(msg).getAsJsonObject();
//                    JsonElement jsonElement = jsonObject.get("entity");
//                    if(ObjectUtils.isNotNull(jsonElement)){
//                        videomap=gson.fromJson(jsonElement, new com.google.common.reflect.TypeToken<Map<String,String>>() {}.getType());
//                    }
//                }
//
//                String m3u8_720 = videomap.get("m3u8_720").toString();
//                String m3u8_360 = videomap.get("m3u8_360").toString();
//                String m3u8_270 = videomap.get("m3u8_270").toString();
//                String mp4_360 = videomap.get("mp4_360").toString();
//                model.addAttribute("m3u8_720", m3u8_720);
//                model.addAttribute("m3u8_360", m3u8_360);
//                model.addAttribute("m3u8_270", m3u8_270);
//                model.addAttribute("mp4_360", mp4_360);
//                //视频平台路径
//                model.addAttribute("videoPath", CommonConstants.videoPath);
//
//
//            }
//            //判断是否为手机浏览器
//            boolean isMoblie = JudgeIsMoblie(request);
//            model.addAttribute("isMoblie", isMoblie);
//            // 放入数据
//            model.addAttribute("videourl", videourl);
//            model.addAttribute("videotype", videotype);
//            model.addAttribute("ccwebsitemap", map);
//            return getKopintHtml;
//        } catch (Exception e) {
//            logger.error("CourseKpointController.getKopintHtml", e);
//            return setExceptionRequest(request, e);
//        }
//    }

}
