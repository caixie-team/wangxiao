package io.wangxiao.edu.home.controller.course;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.CourserKpointType;
import io.wangxiao.edu.home.constants.enums.IntegralKeyword;
import io.wangxiao.edu.home.constants.enums.SellType;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseKpoint;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.service.course.CourseKpointService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.member.MemberRecordService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseKpointController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseKpointController.class);

    // 课程详情
    private static final String getKopintHtml = getViewPath("/course/videocode");
    private static final String publickpointList = getViewPath("/course/public_kpoint_list");// 课程列表

    // 课程播放
    private static final String playkpoint = getViewPath("/course/videoplayer");
    // 课程试听
    private static final String auditionPlayer = getViewPath("/course/auditionplayer");

    // 直播课程播放
    private static final String livePlaykpoint = getViewPath("/course/live_play_kpoint");

    private static final String callBack56Uploading = getViewPath("/course/callBack56_uploading");// 56视频上传回调

    @Autowired
    private TrxorderService trxorderService;

    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private MemberRecordService memberRecordService;
    /*@Autowired
    private UserService userService;
    private CacheKit cacheKit = CacheKit.getInstance();*/

    /**
     * 获得直播会放的视频url
     *
     * @return
     */
    @RequestMapping("/front/getLiveKopintUrl")
    @ResponseBody
    public Map<String, Object> getLiveKopintUrl(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Long kpointId = Long.valueOf(request.getParameter("kpointId"));
            // 查询节点
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);

            // 视频url
            String videourl = courseKpoint.getVideourl();
            json = this.getJsonMap(true, videourl, null);
        } catch (Exception e) {
            logger.error("CourseKpointController.getLiveKopintUrl", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

    /**
     * 获得视频，图文，PDF,MP3播放的html
     *
     * @return
     */
    @SuppressWarnings("serial")
    @RequestMapping("/front/ajax/getKopintHtml")
    public String getKopintHtml(Model model, HttpServletRequest request) {
        try {
            Long kpointId = Long.valueOf(request.getParameter("kpointId"));
            // 查询节点
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(kpointId);
            // 当传入数据不正确时直接返回
            if (ObjectUtils.isNull(courseKpoint)) {
                return getKopintHtml;
            }
            model.addAttribute("courseKpoint", courseKpoint);
            boolean isok1 = false;
            boolean isok2 = false;
            if (courseKpoint.getIsfree() == 2) {// 判断节点不可以试听
                isok1 = trxorderService.checkCourseLook(courseKpoint.getCourseId(), getLoginUserId(request));
                if (isok1 == false) {// 判断该课程不可以观看
                    if (getLoginUserId(request) != null && getLoginUserId(request) > 0) {// 登陆才判断会员
                        isok2 = memberRecordService.checkUserMember(getLoginUserId(request), courseKpoint.getCourseId());
                        if (isok2 == false) {// 判断会员不可以观看
                            logger.info("++isok:" + false);
                            return getKopintHtml;
                        }
                    }
                }
            }
            // 播放类型
            String courseType = courseKpoint.getCourseType();
            if (CourserKpointType.GRAPHIC.toString().equals(courseType)) {//图文
                // 图文内容
                String courseText = courseKpoint.getCourseText();
                model.addAttribute("courseText", courseText);
            } else if (CourserKpointType.PDF.toString().equals(courseType)) {//PDF
                // PDFurl
                String pdfUrl = courseKpoint.getVideourl();
                model.addAttribute("pdfUrl", pdfUrl);
            } else if (CourserKpointType.MP3.toString().equals(courseType)) {//MP3
                // MP3url
                String mp3Url = courseKpoint.getVideourl();
                model.addAttribute("mp3Url", mp3Url);
            } else if (CourserKpointType.VIDEO.toString().equals(courseType)) {//视频
                // 视频url
                String videoUrl = courseKpoint.getVideourl();
                // 查询cc的appId key
                Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.cc.toString());
                if (courseKpoint.getVideotype().equalsIgnoreCase(WebSiteProfileType.letv.toString())) {// 如果乐视
                    Map<String, Object> lmap = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.letv.toString());
                    model.addAttribute("websiteLetvmap", lmap);
                }
                // demo视频
                if (courseKpoint.getVideotype().equalsIgnoreCase(WebSiteProfileType.mp4flv.toString())) {
                    // 根据id 查询视频返回的m3u8地址和MP4地址
                    Map<String, String> videomap = new HashMap<>();
                    String msg = HttpUtil.doGet(CommonConstants.videoPath + "/api/video/getVideoUrl/" + videoUrl, "");
                    if (StringUtils.isNotEmpty(msg) && !"[]".equals(msg)) {
                        JsonObject jsonObject = jsonParser.parse(msg).getAsJsonObject();
                        JsonElement jsonElement = jsonObject.get("entity");
                        if (ObjectUtils.isNotNull(jsonElement)) {
                            videomap = gson.fromJson(jsonElement,
                                    new com.google.common.reflect.TypeToken<Map<String, String>>() {
                                    }.getType());
                        }
                    }
                    String m3u8_720 = videomap.get("m3u8_720").toString();
                    String m3u8_360 = videomap.get("m3u8_360").toString();
                    String m3u8_270 = videomap.get("m3u8_270").toString();
                    String mp4_360 = videomap.get("mp4_360").toString();
                    model.addAttribute("m3u8_720", m3u8_720);
                    model.addAttribute("m3u8_360", m3u8_360);
                    model.addAttribute("m3u8_270", m3u8_270);
                    model.addAttribute("mp4_360", mp4_360);
                    // 视频平台路径
                    model.addAttribute("videoPath", CommonConstants.videoPath);
                }
                model.addAttribute("ccwebsitemap", map);
            }


            // 判断是否为手机浏览器
            boolean isMoblie = JudgeIsMoblie(request);
            model.addAttribute("isMoblie", isMoblie);
            // 放入数据
            model.addAttribute("courseKpoint", courseKpoint);
            return getKopintHtml;
        } catch (Exception e) {
            logger.error("CourseKpointController.getKopintHtml", e);
            return setExceptionRequest(request, e);
        }
    }

    // 判断是否为手机浏览器
    public boolean JudgeIsMoblie(HttpServletRequest request) {
        boolean isMoblie = false;
        String[] mobileAgents = {"iphone", "android", "ipad", "phone", "mobile", "wap", "netfront", "java",
                "opera mobi", "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry",
                "dopod", "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola",
                "foma", "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad",
                "webos", "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips",
                "sagem", "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                "Googlebot-Mobile"};
        if (request.getHeader("User-Agent") != null) {
            System.out.println("User-Agent:" + request.getHeader("User-Agent"));
            for (String mobileAgent : mobileAgents) {
                if (request.getHeader("User-Agent").toLowerCase().indexOf(mobileAgent) >= 0) {
                    isMoblie = true;
                    break;
                }
            }
        }
        return isMoblie;
    }

    /**
     * 播放视频页面
     * <p>
     * 不管是课程套餐还是课程目录时都放到list(coursePackageList)中
     *
     * @param model
     * @param request  课程id
     * @param kpointId 节点id
     * @return
     */
    @RequestMapping("/front/playkpoint/{id}")
    public String playkpoint(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id,
                             @RequestParam(required = false) Long kpointId, RedirectAttributes redirectAttributes) {
        try {
            // 获取登录用户ID
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNull(userId)) {
                if (ObjectUtils.isNotNull(kpointId)) {
                    return "redirect:/front/auditionKpoint/" + id + "?kpointId?=" + kpointId;
                } else {
                    return "redirect:/front/auditionKpoint/" + id;
                }

            }
            // 查询课程详情
            CourseDto course = courseService.getCourseInfoById(id);
            // 判断课程是否不存在
            if (ObjectUtils.isNull(course)) {
                redirectAttributes.addAttribute("msg", "对不起该课程已经下架或者删除");
                return "redirect:/front/success";
            }
            // 查询课程是否可以观看
            boolean isPlay = courseService.isPlay(userId, course);
            if (!isPlay) {
                WebUtils.setCookie(response, "msg", "false", 1);
                return "redirect:/front/couinfo/" + id;
            }
            model.addAttribute("isok", isPlay);
            model.addAttribute("course", course);
            // 显示节点目录
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(id);
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
            model.addAttribute("kpointList", courseKpointList);
            if (ObjectUtils.isNull(kpointId)) {
                if (ObjectUtils.isNotNull(courseKpointList)) {
                    for (CourseKpoint kpoint : courseKpointList) {
                        if (kpoint.getType() == 0) {
                            model.addAttribute("kpoint", kpoint);
                            break;
                        }
                    }
                }
            } else {
                CourseKpoint kpoint = courseKpointService.getCourseKpointById(kpointId);
                model.addAttribute("kpoint", kpoint);
            }

            // 通过标识判断跳转是直播页面还是 播放页面
            if ("live".equals(request.getParameter("falg"))) {
                // 直播
                return livePlaykpoint;
            } else {
                return playkpoint;
            }
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
    }

    /**
     * 课程试听
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/front/auditionKpoint/{id}")
    public String auditionKpoint(HttpServletRequest request, Model model, @PathVariable("id") Long id, @RequestParam(required = false) Long kpointId
    ) {
        try {
            // 课程信息
            CourseDto course = courseService.getCourseInfoByCourseId(id);
            model.addAttribute("course", course);

            // 课节信息
            model.addAttribute("currentCourseId", course.getId());
            CourseKpoint courseKpoint = new CourseKpoint();
            courseKpoint.setCourseId(id);
            List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
            model.addAttribute("kpointList", courseKpointList);
            CourseKpoint kpoint = null;
            if (ObjectUtils.isNull(kpointId)) {
                // 获取第一节试听课时
                if (ObjectUtils.isNotNull(courseKpointList)) {
                    for (CourseKpoint _kpoint : courseKpointList) {
                        if (_kpoint.getType() == 0 && _kpoint.getIsfree() == 1) {
                            kpoint = _kpoint;
                            break;
                        }
                    }
                }
            } else {
                kpoint = courseKpointService.getCourseKpointById(kpointId);
            }
            model.addAttribute("kpoint", kpoint);

        } catch (Exception e) {
            logger.error("CourseKpointController.auditionKpoint", e);
        }
        return auditionPlayer;
    }


    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;

    /**
     * 添加播放次数
     *
     * @param request
     * @param courseId
     * @param kpointId
     * @return
     */
    @RequestMapping("/course/playertimes")
    @ResponseBody
    public Object addCoursePlayerTime(HttpServletRequest request, @ModelAttribute("courseId") Long courseId,
                                      @ModelAttribute("kpointId") Long kpointId) {
        // 要更新3个表 edu_course_profile,edu_course_studyhistory,edu_course_kpoint
        Map<String, Object> json = null;
        try {
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setCourseId(courseId);
            courseStudyhistory.setKpointId(kpointId);
            courseStudyhistory.setUserId(getLoginUserId(request));
            courseStudyhistoryService.playertimes(courseStudyhistory);
            // 添加播放次数同时添加积分
            userIntegralService.addUserIntegral(IntegralKeyword.watch_video.toString(), getLoginUserId(request),
                    kpointId, 0L, "");
            json = this.getJsonMap(true, "", null);
        } catch (Exception e) {
            return setExceptionRequest(request, e);
        }
        return json;
    }

    /**
     * 获得56上传成功之后的回调
     */
    @RequestMapping("/api/56/callBack")
    public String callBack56Uploading() {
        return callBack56Uploading;
    }

    /**
     * 获取课程目录
     *
     * @param model
     * @param courseId 课程ID
     * @return
     */
    @RequestMapping("/ajax/course/kpointList/{courseId}")
    public String kpointList(Model model, @PathVariable("courseId") Long courseId) {
        try {
            CourseDto course = courseService.getCourseInfoById(courseId);
            model.addAttribute("courseId", courseId);
            model.addAttribute("course", course);
            model.addAttribute("type", course.getSellType());
            if (SellType.COURSE.toString().equals(course.getSellType())) {
                CourseKpoint courseKpoint = new CourseKpoint();
                courseKpoint.setCourseId(courseId);
                List<CourseKpoint> courseKpointList = courseKpointService.getCourseKpointList(courseKpoint);
                if (ObjectUtils.isNotNull(courseKpointList)) {
                    model.addAttribute("kpointList", courseKpointList);
                    model.addAttribute("kpointId", courseKpointList.get(0).getId());
                }
            } else if (SellType.PACKAGE.toString().equals(course.getSellType())) {
                List<Long> courseIdList = new ArrayList<Long>();
                courseIdList.add(courseId);
                List<CourseDto> courseList = courseService.getCourseListPackage(courseIdList);
                if (ObjectUtils.isNotNull(courseList)) {
                    model.addAttribute("courseList", courseList);
                    model.addAttribute("kpointId", courseList.get(0).getId());
                }
            }
        } catch (Exception e) {
            logger.error("CourseKpointController.kpointList", e);
        }
        return publickpointList;
    }
}