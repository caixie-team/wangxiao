package io.wangxiao.edu.home.controller.course;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.common.util.UploadLetvCloud;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseKpoint;
import io.wangxiao.edu.home.service.course.CourseKpointService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminKpointController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminKpointController.class);
    // 课程列表
    private static final String kpointList = getViewPath("/admin/kpoint/kpoint_list");// 课程列表
    private static final String toUploadLetv = getViewPath("/admin/course/upload_letv");
    private static final String newKpointList = getViewPath("/admin/kpoint/kpoint_lists");// 视频节点列表

    // 章节添加跳转
    private static final String addChapter = getViewPath("/admin/kpoint/add_course_chapter");
    // 课时添加跳转
    private static final String addkpoint = getViewPath("/admin/kpoint/add_course_kpoint");
    // 编辑修改章节name跳转
    private static final String updateChapter = getViewPath("/admin/kpoint/kpoint_update_name");
    @Autowired
    private CourseService courseService;
    @Autowired
    private WebsiteProfileService websiteProfileService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("courseKpoint")
    public void initBinderCourseKpoint(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("courseKpoint.");
    }

    @Autowired
    private CourseKpointService courseKpointService;

    /**
     * 课程的视频列表
     */
    @RequestMapping("/kpoint/list/{id}")
    public String showKpointList(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(id);
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
            request.setAttribute("courseId", id);
            request.setAttribute("kpointList", courseKpointList);
        } catch (Exception e) {
            logger.error("AdminKpointController.showKpointList", e);
            return setExceptionRequest(request, e);
        }
        return kpointList;
    }

    /**
     * 修改拖拽后的序列
     *
     * @param request
     * @param id       数据主键
     * @param sort     数据序列
     * @param courseid 数据课id
     */
    @RequestMapping("/kpoint/updatesortable/{courseid}")
    @ResponseBody
    public Map<String, Object> updatesortable(HttpServletRequest request, @RequestParam("id") String id, @PathVariable("courseid") Long courseid) {
        Map<String, Object> json = null;
        try {
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(courseid);
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);//查出排序前的数据
            if (ObjectUtils.isNotNull(courseKpointList)) {
                Map<String, Object> map = new HashMap<>();
                for (CourseKpoint kpoint : courseKpointList) {
                    map.put(kpoint.getId() + "", kpoint);
                }
                String[] ids = id.split(",");
                for (int i = 0; i < ids.length; i++) {
                    Object o = map.get(ids[i]);
                    if (o != null) {
                        courseKpoint = (CourseKpoint) o;
                        courseKpoint.setSort(new Long(i));
                        courseKpointService.updateSortable(courseKpoint);
                    }
                }
                json = this.getJsonMap(true, "true", null);
            } else {
                json = this.getJsonMap(false, "fail", null);
            }
        } catch (Exception e) {
            logger.error("AdminKpointController.updatesortable", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 查询课程下的视频节点
     *
     * @return
     */
    @RequestMapping("/kpoint/query/{id}")
    @ResponseBody
    public Map<String, Object> queryKpoint(@PathVariable("id") Long id, HttpServletRequest request,
                                           HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(id);
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);

            if (ObjectUtils.isNotNull(courseKpointList)) {
                json = this.getJsonMap(true, "", courseKpointList);
            } else {
                json = this.getJsonMap(false, "", null);
            }
        } catch (Exception e) {
            logger.error("AdminPointAction.queryKpoint", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 查询课程下的视频节点
     *
     * @return
     */
    @RequestMapping("/kpoint/querytype/{id}")
    @ResponseBody
    public Map<String, Object> querytype(@PathVariable("id") Long id, HttpServletRequest request,
                                         HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(id);
            // 文件夹节点
            courseKpoint.setType(1L);
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);

            if (ObjectUtils.isNotNull(courseKpointList)) {
                json = this.getJsonMap(true, "", courseKpointList);
            } else {
                json = this.getJsonMap(false, "", null);
            }
        } catch (Exception e) {
            logger.error("AdminPointAction.queryKpoint", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 查询课程下的视频节点
     *
     * @return
     */
    @RequestMapping("/kpoint/add")
    @ResponseBody
    public Map<String, Object> add(@ModelAttribute("courseKpoint") CourseKpoint courseKpoint,
                                   HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNull(courseKpoint.getId())) {
                courseKpoint.setStatus(0L);
                courseKpointService.addCourseKpoint(courseKpoint);
            } else {
                courseKpointService.updateCourseKpoint(courseKpoint);
            }
            json = this.getJsonMap(true, "", null);
        } catch (Exception e) {
            logger.error("AdminPointAction.queryKpoint", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    // 批量删除
    @RequestMapping("/kpoint/delBatch")
    @ResponseBody
    public Map<String, Object> delPointByPointId(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = null;
        String pointIds = request.getParameter("pointIds");
        try {
            String[] parray = pointIds.replaceAll(" ", "").split(",");
            if (!ObjectUtils.isNull(parray)) {
                courseKpointService.deleteCourseKpointByIdBatch(parray);
            }
            json = this.getJsonMap(true, "", null);
            return json;
        } catch (Exception e) {
            logger.error("AdminPointAction.delPointByPointId", e);
            json = this.getJsonMap(false, "error", null);
            return json;
        }
    }

    // 单个删除
    @RequestMapping("/kpoint/delCourseKpoint/{id}/{courseid}")
    @ResponseBody
    public Map<String, Object> delPointById(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id, @PathVariable("courseid") Long courseid) {
        Map<String, Object> json = null;
        try {
            if (courseid != -1) {
                Long lessionnum = courseService.getCourselessionnum(courseid);
                Course course = new Course();
                course.setLessionnum(lessionnum - 1);
                course.setId(courseid);
                courseService.updateCourselessionnum(course);
            }
            courseKpointService.deleteCourseKpointById(id);
            json = this.getJsonMap(true, "", null);
            return json;
        } catch (Exception e) {
            logger.error("AdminPointAction.delPointByPointId", e);
            json = this.getJsonMap(false, "error", null);
            return json;
        }
    }


    /**
     * 获取56配置
     *
     * @return
     */
    public Map<String, String> getVideoTypeInfo(String profileType) {
        // 获得56配置
        Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(profileType);
        JsonParser jsonParser = new JsonParser();
        // 获得详细info
        JsonObject jsonObject = jsonParser.parse(gson.toJson(map.get(profileType))).getAsJsonObject();
        if (!jsonObject.isJsonNull()) {
            Map<String, String> websitemap = new HashMap<String, String>();
            if (profileType.equals(WebSiteProfileType.p56.toString())) {
                websitemap.put("p56appID", jsonObject.get("p56appID").getAsString());// 56key
                websitemap.put("p56appKEY", jsonObject.get("p56appKEY").getAsString());//
            } else if (profileType.equals(WebSiteProfileType.letv.toString())) {
                websitemap.put("user_unique", jsonObject.get("user_unique").getAsString());// 56key
                websitemap.put("secret_key", jsonObject.get("secret_key").getAsString());//
            }
            return websitemap;
        }
        return null;
    }

    /**
     * 跳转乐视上传
     *
     * @return
     */
    @RequestMapping("/letv/toletv")
    public String toUploadLetv() {
        return toUploadLetv;
    }

    /**
     * 获取letv上传url
     *
     * @param request
     * @return
     */
    @RequestMapping("/letv/uploadurl")
    @ResponseBody
    public Map<String, Object> initLetvUrl(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String videoName = request.getParameter("videoName");
            String courseId = request.getParameter("courseId");
            Course course = courseService.getCourseById(Long.parseLong(courseId));
            Map<String, String> map = getVideoTypeInfo(WebSiteProfileType.letv.toString());
            // 初始化乐视云
            UploadLetvCloud uploadLetvCloud = new UploadLetvCloud(map.get("user_unique"), map.get("secret_key"));
            // 定义输出格式 (json|xml)
            uploadLetvCloud.format = "json";
            // 视频上传初始化（Web方式）
            String response = uploadLetvCloud.videoUploadFlash(
                    videoName != "" ? course.getName() + "-" + videoName : "乐视云视频", "letv_callback", 450, 260);
            if (StringUtils.isNotEmpty(response)) {
                Map<String, Object> letvmap = new HashMap<String, Object>();
                JsonParser jsonParser = new JsonParser();
                // 获得json对象
                JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
                Type typeToken = new TypeToken<Map<String, Object>>() {
                }.getType();
                letvmap = gson.fromJson(jsonObject.get("data"), typeToken);
                Map<String, Object> lmap = new HashMap<String, Object>();
                lmap.put("letvmap", letvmap);
                lmap.put("letvjson", gson.toJson(jsonObject.get("data")));
                json = this.getJsonMap(true, "init is success", lmap);
                return json;
            }
            json = this.getJsonMap(false, "init upload error", null);
        } catch (Exception e) {
            logger.error("initLetvUrl", e);
            json = this.getJsonMap(false, "init upload error", null);
        }
        return json;
    }

    /**
     * 课程的视频列表
     */
    @RequestMapping("/newkpoint/list")
    public String showNewKpointList(HttpServletRequest request,
                                    @ModelAttribute("courseKpoint") CourseKpoint courseKpoint, @ModelAttribute("page") PageEntity page) {
        try {
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointNewList(courseKpoint, page);
            request.setAttribute("list", courseKpointList);
        } catch (Exception e) {
            logger.error("AdminKpointController.showKpointList", e);
            return setExceptionRequest(request, e);
        }
        return newKpointList;
    }

    /**
     * 复制节点
     *
     * @param request
     * @return
     */
    @RequestMapping("/copy/kpoints")
    @ResponseBody
    public Map<String, Object> copyKpointList(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {

            String kpoints = request.getParameter("copyKpoints");// 复制的节点ids
            Long copyCourseId = Long.parseLong(request.getParameter("copyCourseId"));
            Course course = courseService.getCourseById(copyCourseId);
            if (course == null) {
                json = this.getJsonMap(false, "课程不存在", null);
                return json;
            }
            // ids查询节点集合
            List<CourseKpoint> courseKpoints = courseKpointService.getCourseKpointListByIds(kpoints);
            List<CourseKpoint> childKpointList = new ArrayList<CourseKpoint>();// 所有子节点集合
            Map<String, CourseKpoint> map = new HashMap<String, CourseKpoint>();// id键map集合
            for (CourseKpoint courseKpoint : courseKpoints) {
                map.put(courseKpoint.getId() + "", courseKpoint);
            }
            // 子节点添加到父节点中
            for (Map.Entry<String, CourseKpoint> entry : map.entrySet()) {
                CourseKpoint courseKpoint = entry.getValue();
                if (courseKpoint.getParentId().longValue() > 0) {// 子节点
                    CourseKpoint parentKpoint = map.get(courseKpoint.getParentId() + "");// 父节点
                    parentKpoint.getChildKpoints().add(courseKpoint);// 添加子节点到父节点中
                }
            }
            // 保存父节点
            for (Map.Entry<String, CourseKpoint> entry : map.entrySet()) {
                CourseKpoint courseKpoint = entry.getValue();
                if (courseKpoint.getParentId().longValue() == 0) {// 父节点
                    List<CourseKpoint> childKpoints = courseKpoint.getChildKpoints();// 子节点集合
                    courseKpoint.setId(null);// id
                    courseKpoint.setCourseId(copyCourseId);// 课程id
                    courseKpointService.addCourseKpoint(courseKpoint);// 添加父节点
                    if (childKpoints.size() > 0) {
                        for (CourseKpoint childKpoint : childKpoints) {// 子节点设置父id课程id并添加到大集合中
                            childKpoint.setId(null);// id
                            childKpoint.setCourseId(copyCourseId);// 课程id
                            childKpoint.setParentId(courseKpoint.getId());// 父Id
                            childKpointList.add(childKpoint);
                        }
                    }
                }
            }
            // 批量保存子节点
            if (childKpointList.size() > 0) {
                courseKpointService.createChildCourseKpointList(childKpointList);
            }
            json = this.getJsonMap(true, "success", null);
        } catch (Exception e) {
            logger.error("AdminKpointController.copyKpointList", e);
            json = this.getJsonMap(false, "系统错误", null);
        }
        return json;
    }

    /**
     * 章节修改跳转
     *
     * @param model
     * @param courseId
     * @return
     */
    @RequestMapping("/course/updateChapter")
    public String addChapter(Model model, @RequestParam("submitType") String submitType, @RequestParam("courseId") Long courseId, @RequestParam(value = "id", required = false) Long id) {
        model.addAttribute("submitType", submitType);
        model.addAttribute("courseId", courseId);
        if (ObjectUtils.isNotNull(id)) {
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(id);
            model.addAttribute("courseKpoint", courseKpoint);
            return updateChapter;
        }
        return addChapter;
    }

    /**
     * 课程修改跳转
     *
     * @param model
     * @param courseId
     * @return
     */
    @RequestMapping("/course/updateKpoint")
    public String updateKpoint(Model model,
                               @RequestParam(value = "kpointId", required = false) Long kpointId,
                               @RequestParam(value = "parentId", required = false) Long parentId,
                               @RequestParam(value = "sort", required = false) Long sort,
                               @RequestParam("submitType") String submitType,
                               @RequestParam(value = "ids", required = false) String ids,
                               @RequestParam("courseId") Long courseId) {
        model.addAttribute("submitType", submitType);
        model.addAttribute("parentId", parentId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("sort", sort);
        model.addAttribute("ids", ids);
        if (ObjectUtils.isNotNull(kpointId)) {
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);
            model.addAttribute("courseKpoint", courseKpoint);
        }

        return addkpoint;
    }

    /**
     * 课时添加功能实现
     */
    @RequestMapping("/course/addcourse")
    @ResponseBody
    public Map<String, Object> userList(HttpServletRequest request, @ModelAttribute("courseKpoint") CourseKpoint courseKpoint) {
        Map<String, Object> json = null;
        try {
            courseKpoint.setParentId(0L);
            Long sort = courseKpointService.getCourseKpointSort(courseKpoint.getCourseId());
            if (sort == null) {
                sort = 1L;
                courseKpoint.setSort(sort);
            } else {
                courseKpoint.setSort(sort + 1);
            }
            // 添加课时
            courseKpointService.addKpoint(courseKpoint);
            if (courseKpoint.getType() == 0) {//添加课时
                // 课时数
                Long lessionnum = courseService.getCourselessionnum(courseKpoint.getCourseId());
                lessionnum = ObjectUtils.isNotNull(lessionnum) ? lessionnum : 0L;
                Course course = new Course();
                course.setLessionnum(lessionnum + 1);
                course.setId(courseKpoint.getCourseId());
                // 更新课时数
                courseService.updateCourselessionnum(course);
            }
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "error", null);
            e.printStackTrace();
            logger.error("CourseKpointController.addCourseKpointWeb", e);
        }
        return json;
    }


    /**
     * 课时修改功能实现
     */
    @RequestMapping("/course/updateuserList")
    @ResponseBody
    public Map<String, Object> updateuserList(HttpServletRequest request, @ModelAttribute("courseKpoint") CourseKpoint courseKpoint) {
        Map<String, Object> json = null;
        try {
            courseKpoint.setCourseId(courseKpoint.getCourseId());
            courseKpoint.setParentId(0L);
            courseKpointService.updateCourseKpoint(courseKpoint);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "error", null);
            e.printStackTrace();
            logger.error("CourseKpointController.updateuserList", e);
        }
        return json;
    }

    /**
     * 编辑修改章节名称
     */
    @RequestMapping("/kpoint/updatekopintname/{id}")
    @ResponseBody
    public Map<String, Object> updatekopintname(HttpServletRequest request, @PathVariable("id") Long id, @RequestParam("name") String name) {
        Map<String, Object> json = null;
        try {
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setId(id);
            courseKpoint.setName(name);
            courseKpointService.updateKpointName(courseKpoint);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            json = this.getJsonMap(false, "error", null);
            e.printStackTrace();
            logger.error("CourseKpointController.updatekopintname", e);
        }
        return json;
    }

    /**
     * 章节父节点变更
     *
     * @param request
     * @param courseKpoint
     * @return
     */
    @RequestMapping("/course/updateParentId")
    @ResponseBody
    public Map<String, Object> updateCourseKpointParentId(HttpServletRequest request, @ModelAttribute("courseKpoint") CourseKpoint courseKpoint) {
        Map<String, Object> json = null;
        try {
            //执行变更操作
            courseKpointService.updateCourseKpointParentId(courseKpoint);
        } catch (Exception e) {
            logger.error("CourseKpointController.updateCourseKpointParentId", e);
        }
        return json;
    }

}