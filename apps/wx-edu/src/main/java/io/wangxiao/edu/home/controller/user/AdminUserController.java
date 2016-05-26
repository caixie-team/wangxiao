package io.wangxiao.edu.home.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.FileExportImportUtil;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.*;
import io.wangxiao.edu.home.constants.web.LetterConstans;
import io.wangxiao.edu.home.entity.coupon.CouponCodeDTO;
import io.wangxiao.edu.home.entity.coupon.QueryCouponCode;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.entity.course.QueryCourse;
import io.wangxiao.edu.home.entity.letter.MsgReceive;
import io.wangxiao.edu.home.entity.letter.MsgSystem;
import io.wangxiao.edu.home.entity.user.*;
import io.wangxiao.edu.home.service.coupon.CouponCodeService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.letter.MsgReceiveService;
import io.wangxiao.edu.home.service.letter.MsgSystemService;
import io.wangxiao.edu.home.service.order.TrxorderService;
import io.wangxiao.edu.home.service.user.*;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import io.wangxiao.edu.sysuser.entity.*;
import io.wangxiao.edu.sysuser.service.GroupService;
import io.wangxiao.edu.sysuser.service.SubjectService;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import io.wangxiao.edu.sysuser.service.SysUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description 用户管理
 */
@Controller
@RequestMapping("/admin")
public class AdminUserController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    private static final String toUserList = getViewPath("/admin/user/user_list");// 返回学员列表
    private static final String userMsg = getViewPath("/admin/user/userMsg");// 返回学员列表
    private static final String toSelectUserList = getViewPath("/admin/user/select_user_list");// 短信发送
    // 查询学员列表
    private static final String toUpdatePwd = getViewPath("/admin/user/updatePwd");// 修改密码页面
    private static final String toSendMobileMsg = getViewPath("/admin/user/to_sendMobileMsg");// 发送短信页面
    private static final String toSendEmailMsg = getViewPath("/admin/user/to_sendEmailMsg");// 发送邮件页面
    private static final String sendMobileMsgList = getViewPath("/admin/user/sendMobile_list");// 短信管理页面
    private static final String sendEmailMsgList = getViewPath("/admin/user/sendEmail_list");// 短信管理页面
    private static final String sendMobileMsgInfo = getViewPath("/admin/user/sendMobile_info");// 短信管理页面
    private static final String sendEmailMsgInfo = getViewPath("/admin/user/sendEmail_info");// 邮件管理页面
    private static final String batchOpen = getViewPath("/admin/user/batchOpen");// 批量开通界面
    private static final String studyHistory = getViewPath("/admin/user/study_history");// 学习记录
    private static final String courseList = getViewPath("/admin/user/course_list");//
    private static final String toUserLoginLog = getViewPath("/admin/user/user_login_log");// 学员登陆log
    private static final String toUserLoginLogs = getViewPath("/admin/user/user_login_logs");// 学员登陆log
    private static final String senSystemMessages = getViewPath("/admin/user/to_send_systemMessage");// 发送系统消息页面
    private static final String progressbar = getViewPath("/admin/user/progressbar");// 进度跳
    private static final String optRecordList = getViewPath("/admin/user/optrecord_list");// 进度跳
    private static final String groupOneOpen = getViewPath("/admin/user/groupOneOpen");// 单个开通界面
    private static final String groupBatchOpen = getViewPath("/admin/user/groupBatchOpen");// 批量开通界面
    private static final String oneOpen = getViewPath("/admin/user/oneOpen");// 单个开通界面
    private static final String userMovieList = getViewPath("/admin/user/user_movie_list");// 去员工观看权限页面
    private static final String userGroup = getViewPath("/admin/user/userGroup");// 去员工组修改页面
    private static final String toCouponCodes = getViewPath("/admin/user/CouponCode_list");// 优惠券列表
    private static final String goGroupList = getViewPath("/admin/user/groupList");// 去部门下的员工页面
    @Autowired
    private UserService userService;
    @Autowired
    private UserMobileMsgService userMobileMsgService;
    @Autowired
    private UserEmailMsgService userEmailMsgService;
    @Autowired
    private CourseStudyhistoryService courseStudyhistoryService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private MsgSystemService msgSystemService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TrxorderService trxorderService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private MsgReceiveService msgReceiveService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private UserGroupMiddleService userGroupMiddleService;
    @Autowired
    private CouponCodeService couponCodeService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;
    private static final String userOptRecordList = getViewPath("/admin/user/user_opt_record_list");// 学员操作记录

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("user")
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("userMobileMsg")
    public void initBinderMsg(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userMobileMsg.");
    }

    @InitBinder("queryUserCondition")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryUserCondition.");
    }

    @InitBinder("userEmailMsg")
    public void initBinderEmail(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userEmailMsg.");
    }

    // 课程查询检索
    @InitBinder("queryCourse")
    public void initBinderqueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }

    // 课程查询检索
    @InitBinder("userLoginLog")
    public void initBinderUserLoginLog(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userLoginLog.");
    }

    // 课程查询检索
    @InitBinder("userOptRecord")
    public void initBinderUserOptRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userOptRecord.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("userForm")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userForm.");
    }

    @InitBinder("queryCouponCode")
    public void initBinderQueryCouponCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCouponCode.");
    }

    /**
     * 用户列表集合
     *
     * @param user
     * @param page
     * @return
     */
    @RequestMapping("/user/list")
    public ModelAndView userList(@ModelAttribute User user, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelandView = new ModelAndView();
        // 设置返回页面
        modelandView.setViewName(toUserList);
        try {
            // 设置分页 ，默认每页10
            page.setPageSize(10);
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            // 查询学员列表
            List<User> list = userService.getUserListByCondition(user, page);
            // 把参数放到modelAndView中
            modelandView.addObject("list", list);
            modelandView.addObject("page", page);
            modelandView.addObject("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("AdminUserController.userList", e);
        }
        return modelandView;
    }

    /**
     * 去员工组修改页面
     *
     * @param id
     * @param model
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/user/getuserGroupId/{id}")
    public String getUserGroupById(@PathVariable("id") Long id, Model model, HttpServletRequest request,
                                   @ModelAttribute("page") PageEntity page) {
        try {

            User user = userService.getUserById(id);

            List<UserGroupMiddle> UserGroupMiddle = userGroupMiddleService.getUserGroupByUserId(id);
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            for (UserGroup _userGroup : userGroupList) {
                for (UserGroupMiddle _userGroupMiddle : UserGroupMiddle) {
                    if (_userGroup.getId() == _userGroupMiddle.getGroupId()) {
                        _userGroup.setCheck(1);
                        continue;
                    }
                }
            }
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            model.addAttribute("user", user);
            model.addAttribute("userGroupList", userGroupList);
            model.addAttribute("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("AdminUserController.getUserGroupById", e);
        }
        return userGroup;
    }

    /**
     * 员工组修改
     *
     * @param user
     * @param request
     * @return
     */
    @RequestMapping("/user/updateUserGroup")
    public String updateUserGroup(@ModelAttribute("user") User user, HttpServletRequest request) {
        try {
            UserGroupMiddle userGroupMiddle = new UserGroupMiddle();
            userGroupMiddle.setUserId(user.getId());
            userGroupMiddleService.delteUserGroupMiddleByUserId(userGroupMiddle);
            // 可以添加多个组
            if (StringUtils.isNotEmpty(user.getGroupIds())) {
                List<UserGroupMiddle> userGroupMiddleList = new ArrayList<UserGroupMiddle>();
                // 学员信息分组
                String[] groupIdArray = user.getGroupIds().split(",");
                for (int i = 0; i < groupIdArray.length; i++) {
                    UserGroupMiddle _userGroupMiddle = new UserGroupMiddle();
                    _userGroupMiddle.setUserId(user.getId());
                    _userGroupMiddle.setGroupId(Long.parseLong(groupIdArray[i]));
                    userGroupMiddleList.add(_userGroupMiddle);
                }
                userGroupMiddleService.addUserGroupMiddle(userGroupMiddleList);
            }
            User _user = userService.getUserById(user.getId());
            _user.setLevel(user.getLevel());
            _user.setSysGroupId(user.getSysGroupId());
            userService.updateUser(_user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/user/list";
    }

    /**
     * 短信邮件发送 查询用户列表集合
     *
     * @param user
     * @param page
     * @return
     */
    @RequestMapping("/user/select_userlist/{type}")
    public ModelAndView selectUserList(@ModelAttribute User user, @ModelAttribute("page") PageEntity page,
                                       @PathVariable("type") int type) {
        ModelAndView modelandView = new ModelAndView();
        // 设置返回页面
        modelandView.setViewName(toSelectUserList);
        try {
            if (type == 3) {
                page.setPageSize(5);
            }
            // 设置分页 ，默认每页10

            // 查询学员列表
            List<User> list = userService.getUserListAndCourse(user, page);
            // 把参数放到modelAndView中
            modelandView.addObject("list", list);
            modelandView.addObject("page", page);
            modelandView.addObject("type", type);// 1 短信 2邮箱 3系统消息
        } catch (Exception e) {
            logger.error("AdminUserController.userList", e);
        }
        return modelandView;
    }

    /**
     * 导入Excel 解析
     */
    @RequestMapping("/user/importMsgExcel/{type}")
    @ResponseBody
    public Map<String, Object> importExcel(HttpServletRequest request, @RequestParam("myFile") MultipartFile myFile,
                                           @PathVariable("type") int type) {
        Map<String, Object> json = null;
        try {
            logger.info("myFile:" + myFile.getName());
            HSSFWorkbook wookbook = new HSSFWorkbook(myFile.getInputStream());
            // 只读取sheet1
            HSSFSheet sheet = wookbook.getSheet("Sheet1");
            int rows = sheet.getLastRowNum();// Excel行数
            String mobileStr = "";
            for (int i = 1; i <= rows; i++) {
                // 读取左上端单元格
                HSSFRow row = sheet.getRow(i);
                // 行不为空
                if (row != null) {
                    // **读取cell**
                    HSSFCell cell = row.getCell((short) 0);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case HSSFCell.CELL_TYPE_FORMULA:
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                DecimalFormat df = new DecimalFormat("#");
                                mobileStr += df.format(cell.getNumericCellValue()) + ",";
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                mobileStr += cell.getStringCellValue().trim() + ",";
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
            // 获得页面的
            if (request.getParameter("numerStr") != null && request.getParameter("numerStr") != "") {
                mobileStr += request.getParameter("numerStr");
            }
            Map<String, Object> returnMap = null;
            if (type == 1) {// 短信页面
                returnMap = checkMobile(mobileStr);
                if (Boolean.parseBoolean(returnMap.get("flag").toString()) == false) {
                    json = this.getJsonMap(false, returnMap.get("errorMobile").toString(), "");
                    return json;
                } else {
                    json = this.getJsonMap(true, "", returnMap.get("mobileList"));
                }
            } else {// 邮箱页面
                returnMap = checkEmail(mobileStr);
                if (Boolean.parseBoolean(returnMap.get("flag").toString()) == false) {
                    json = this.getJsonMap(false, returnMap.get("errorMessage").toString(), "");
                    return json;
                } else {
                    json = this.getJsonMap(true, "", returnMap.get("returnList"));
                }
            }
        } catch (Exception e) {
            logger.error("importExcel", e);
            json = this.getJsonMap(false, "Excel导入错误", null);
            return json;
        }
        return json;
    }

    /**
     * 用户导出
     */
    @RequestMapping("/user/export")
    public void userListExport(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User user) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/user");
            // 文件名
            String expName = "学员信息_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "昵称", "学员邮箱", "学员手机", "注册时间", "状态"};

            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            userService.getUserListByCondition(user, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<User> userList = userService.getUserListByCondition(user, page);
                List<List<String>> list = userJoint(userList);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 学员信息excel格式拼接
     *
     * @return
     */
    public List<List<String>> userJoint(List<User> userList) {
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (User anUserList : userList) {
            List<String> small = new ArrayList<String>();
            small.add(anUserList.getId() + "");
            small.add(anUserList.getNickname());
            small.add(anUserList.getEmail());
            small.add(anUserList.getMobile());
            small.add(format.format(anUserList.getCreatedate()));
            if (anUserList.getIsavalible() == 0) {
                small.add("正常");
            } else if (anUserList.getIsavalible() == 1) {
                small.add("冻结");
            }
            list.add(small);
        }
        return list;
    }

    /**
     * 禁用学员账号
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/updateIsavalible")
    @ResponseBody
    public Map<String, Object> updateIsavalible(HttpServletRequest request, @ModelAttribute User user) {
        Map<String, Object> json = null;
        try {
            User beforeRecord = userService.getUserById(user.getId());
            userService.updateUserForIsavalibleById(user);
            User afterRecord = userService.getUserById(user.getId());
            String status = "";
            if (user.getIsavalible() == 1) {
                status = "禁用";
            } else {
                status = "恢复";
            }

            SysUserOptRecord record = new SysUserOptRecord(request, status + "用户", "用户表-" + user.getId(), beforeRecord,
                    afterRecord);
            if (record != null) {
                sysUserOptRecordService.addRecord(record);
            }
            json = this.getJsonMap(true, "success", null);

            // 记录系统用户操作
            Map<String, Object> descMap = new HashMap<String, Object>();
            descMap.put("optuser", "操作id_" + SingletonLoginUtils.getLoginUserId(request));
            descMap.put("optType", user.getIsavalible() == 1 ? "禁用" : "正常");
            descMap.put("userId", "用户id_" + user.getId());
            userService.addUserOptRecord(user.getId(),
                    user.getIsavalible() == 1 ? UserOptType.DISABLE.toString() : UserOptType.ACTIVE.toString(),
                    SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request), user.getId(),
                    gson.toJson(descMap));

        } catch (Exception e) {
            logger.error("AdminUserController.updateIsavalible", e);
        }
        return json;
    }

    /**
     * 跳转到开通页面
     *
     * @return
     */
    @RequestMapping("/user/forward")
    public ModelAndView forwardImpot() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/user/user_importProcess");
        return modelAndView;
    }

    /**
     * 跳转到修改密码
     *
     * @return
     */
    @RequestMapping("/user/toupdatepwd/{id}")
    public ModelAndView toUpdatePwd(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(toUpdatePwd);
        try {
            User user = userService.getUserById(id);
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            logger.error("AdminUserController.toUpdatePwd", e);
        }
        return modelAndView;
    }

    /**
     * 获取学生学习情况
     *
     * @param id
     * @return
     */
    @RequestMapping("/user/studyhistory/{id}")
    public ModelAndView toUserStudyHistory(@PathVariable("id") Long id, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(studyHistory);
        try {

            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(id);
            List<CourseStudyhistory> studyHistoryList = courseStudyhistoryService
                    .getCourseStudyhistoryListGroupByCourseId(courseStudyhistory, page);
            modelAndView.addObject("page", page);
            modelAndView.addObject("list", studyHistoryList);
            modelAndView.addObject("userId", id);
        } catch (Exception e) {

            logger.error("AdminUserController.updatePwd", e);
        }
        return modelAndView;
    }

    /**
     * 赠送课程
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/user/freeCourse")
    @ResponseBody
    public Map<String, Object> toFreeCourse(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = null;
        try {
            Long userId = new Long(request.getParameter("userId"));
            String courseId = request.getParameter("courseId");
            // 判断课程是否已过期
            boolean overdue = false;
            Course course = courseService.getCourseById(Long.parseLong(courseId));
            if (ObjectUtils.isNotNull(course)) {
                if (course.getLosetype() == 0) {
                    Date date = new Date();// 今天
                    String nowStr = DateUtils.formatDate(date, "yyyy-MM-dd");
                    String authStr = DateUtils.formatDate(course.getLoseAbsTime(), "yyyy-MM-dd");
                    if (Integer.valueOf(DateUtils.getTwoDay(authStr, nowStr)) + 1 > 0) {
                        overdue = true;
                    }
                } else {
                    overdue = true;
                }
            }
            if (overdue) {
                addOrderMsg(userId, courseId);
                json = this.getJsonMap(true, "赠送成功", null);

                // 记录系统用户操作
                Map<String, Object> descMap = new HashMap<String, Object>();
                descMap.put("optuser", "操作id_" + SingletonLoginUtils.getLoginUserId(request));
                descMap.put("optType", "操作_赠送课程");
                descMap.put("courseId", "课程id_" + courseId);
                descMap.put("userId", "用户id_" + userId);
                userService.addUserOptRecord(userId, UserOptType.GIVECOURSE.toString(),
                        SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request),
                        new Long(courseId), gson.toJson(descMap));
            } else {
                json = this.getJsonMap(false, "overdue", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    // 璁㈠崟娴佹按淇℃伅
    public void addOrderMsg(Long userId, String courseId) throws Exception {
        // 鎷艰鍙傛暟
        Map<String, String> sourceMap = new HashMap<String, String>();
        sourceMap.put("type", "1");// 涓嬪崟绫诲瀷
        sourceMap.put("couponcode", "");// 浼樻儬鍗风紪鐮�
        sourceMap.put("userid", userId + "");
        sourceMap.put("reqchanle", ReqChanle.WEB.toString());
        sourceMap.put("reqIp", "");
        sourceMap.put("courseId", courseId);
        sourceMap.put("payType", "FREE");
        trxorderService.addFreeTrxorder(sourceMap);
    }

    /**
     * 获取课程列表
     **/
    @RequestMapping("/user/courseList")
    public ModelAndView toCourseForClass(@ModelAttribute("queryCourse") QueryCourse queryCourse,
                                         @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(courseList);
        QuerySubject querySubject = new QuerySubject();
        try {

            // 搜索课程列表
            List<CourseDto> courseList = courseService.getCourseListPage(queryCourse, page);
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            modelAndView.addObject("courseList", courseList);
            modelAndView.addObject("subjectList", gson.toJson(subjectList));
            modelAndView.addObject("page", page);
            modelAndView.addObject("userId", queryCourse.getUserId());
        } catch (Exception e) {
        }
        return modelAndView;
    }

    /**
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/user/toUserMovie/{id}")
    public String toUserMovie(HttpServletRequest request, @PathVariable("id") Long id, Model model) {
        try {
            // 查询购买过的课程
            List<CourseDto> courseDtos = courseService.getUserBuyCourseList(id, SellType.ALL.toString());
            // 内部课程
            List<UserGroup> innerCourseDtos = courseService.getUserInnerCourseListByGroup(id);
            // 过滤直播课程
            courseDtos = courseService.filtrationLive(courseDtos);
            model.addAttribute("buycourses", courseDtos);// 购买的课程
            model.addAttribute("innerCourseDtos", innerCourseDtos);// 获取内部课程
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMovieList;
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("/user/updatepwd")
    @ResponseBody
    public Map<String, Object> updatePwd(HttpServletRequest request, @ModelAttribute User user) {
        Map<String, Object> json = null;
        try {
            User before = userService.getUserById(user.getId());
            userService.updatePwdById(user, null);
            User after = userService.getUserById(user.getId());
            SysUserOptRecord record = new SysUserOptRecord(request, "修改密码", "用户表-" + user.getId(), before, after);
            if (record != null) {
                sysUserOptRecordService.addRecord(record);
            }
            json = this.getJsonMap(true, "success", null);
            // 记录系统用户操作
            Map<String, Object> descMap = new HashMap<String, Object>();
            descMap.put("optuser", "操作id_" + SingletonLoginUtils.getLoginUserId(request));
            descMap.put("optType", "操作_修改用户密码");
            descMap.put("userId", "用户id_" + user.getId());
            userService.addUserOptRecord(user.getId(), UserOptType.CHANGEPWD.toString(),
                    SingletonLoginUtils.getLoginUserId(request), this.getSysLoginLoginName(request), user.getId(),
                    gson.toJson(descMap));
        } catch (Exception e) {
            logger.error("AdminUserController.updatePwd", e);
        }
        return json;
    }

    // 批量开通
    @RequestMapping("/user/toBatchOpen")
    public String toBatchOpen(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            request.setAttribute("groupList", gson.toJson(groupList));
            // 获取所有岗位
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
        } catch (Exception e) {
            logger.error("AdminUserController.toBatchOpen", e);
            return setExceptionRequest(request, e);
        }
        return batchOpen;
    }

    /**
     * 批量开通学员账号
     *
     * @param request
     * @param myfile
     * @return
     */
    @RequestMapping("/user/importExcel")
    public ModelAndView importProcess(HttpServletRequest request, @RequestParam("myFile") MultipartFile myfile,
                                      @RequestParam("groupIds") String groupIds, @RequestParam("level") int level,
                                      @RequestParam("sysGroupId") int sysGroupId) {
        ModelAndView modelandView = new ModelAndView();
        modelandView.setViewName("/common/emp_success");
        try {
            logger.info("myFile:" + myfile.getName());
            Map<String, Object> map = userService.updateImportExcel(myfile, groupIds, level, sysGroupId);
            request.setAttribute("msg", map.get("retrunmsg"));
            request.setAttribute("information", map.get("information"));
            request.setAttribute("record", map.get("record"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelandView;
    }

    @RequestMapping("/user/impl")
    public ModelAndView inms() {
        ModelAndView modelandView = new ModelAndView();
        modelandView.setViewName("/common/success");
        return modelandView;
    }

    /**
     * 把邮箱密码放到list
     *
     * @param content
     * @return list
     */
    private List<Map<String, String>> content2ListMap(String content) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String[] info = content.split("\n");
        for (String string : info) {
            string = string.trim().replaceAll("  ", " ").replaceAll("  ", " ");
            String[] record = string.split(" ");
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", record[0]);
            map.put("password", record[1]);
            list.add(map);
        }
        return list;
    }

    /**
     * 检测邮箱是否重复，邮箱格式是否正确
     *
     * @param list
     * @return Boolean
     */
    private Boolean checkEmails(List<Map<String, String>> list) {
        try {
            // 判断Email是否重复
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> emailMap = list.get(i);
                String email = (String) emailMap.get("email");
                if (!email.matches("^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$")) {
                    return false;
                }
                for (int j = i + 1; j < list.size(); j++) {
                    Map<String, String> tmpMap = list.get(j);
                    String tmpEmail = (String) tmpMap.get("email");
                    if (email.equals(tmpEmail)) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 跳转发送短信页面
     *
     * @return
     */
    @RequestMapping("/user/toMsg")
    public String toSendMoblieMsg() {
        return toSendMobileMsg;
    }

    /**
     * 跳转发送邮件页面
     *
     * @return
     */
    @RequestMapping("/user/toEmailMsg")
    public String toSendEmailMsg() {
        return toSendEmailMsg;
    }

    /**
     * 发送短信
     *
     * @param request
     * @return
     */
    @RequestMapping("/user/sendMsg")
    @ResponseBody
    public Map<String, Object> sendMobileMsg(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {

            List<UserMobileMsg> msgList = new ArrayList<UserMobileMsg>();

            String linksman = request.getParameter("linksman");// 获取联系人
            String content = request.getParameter("content");// 获取内容
            Integer type = Integer.parseInt(request.getParameter("sendType"));// 发送方式

            Date now = new Date();
            Date sendTime = now;
            if (type == 2) {// 定时发送

                if (StringUtils.isEmpty(request.getParameter("sendTime"))) {
                    json = this.getJsonMap(false, "定时发送时间不能为空", "");
                    return json;
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                sendTime = df.parse(request.getParameter("sendTime"));// 定时发送时间
            }
            if (StringUtils.isNotEmpty(linksman) && StringUtils.isNotEmpty(content)) {
                // 验证手机
                Map<String, Object> returnMap = checkMobile(linksman);
                // 错误信息
                String errorMobile = returnMap.get("errorMobile").toString();
                if (Boolean.parseBoolean(returnMap.get("flag").toString()) == false) {
                    json = this.getJsonMap(false, errorMobile, "");
                    return json;
                } else {
                    // 添加发送记录
                    JsonObject user = SingletonLoginUtils.getLoginUser(request);
                    UserMobileMsg userMobileMsg = new UserMobileMsg();
                    userMobileMsg.setSendTime(sendTime);
                    if (type == 1) {// 正常发送
                        userMobileMsg.setStatus(1);
                    } else {// 定时发送
                        userMobileMsg.setStatus(2);
                    }
                    userMobileMsg.setId(0);
                    userMobileMsg.setType(type);

                    userMobileMsg.setContent(content);
                    userMobileMsg.setMobile(returnMap.get("mobileList").toString());
                    if (ObjectUtils.isNotNull(user)) {
                        userMobileMsg.setUserId(user.get("id").getAsLong());
                    } else {
                        userMobileMsg.setUserId(0L);
                    }
                    userMobileMsg.setCreateTime(now);
                    msgList.add(userMobileMsg);
                    // 添加记录 暂不发送
                    userMobileMsgService.addUserMobileMsg(msgList);
                    if (type == 1) {// 正常发送
                        userMobileMsgService.batchSendMobileMsg(content,
                                returnMap.get("mobileList").toString().split(","), 3);
                    }
                    errorMobile = "发送成功";
                }
                json = this.getJsonMap(Boolean.parseBoolean(returnMap.get("flag").toString()), errorMobile, "");
            } else {
                json = this.getJsonMap(false, "联系人或内容不能为空", null);
            }
        } catch (Exception e) {
            logger.error("sendMobileMsg", e);
        }
        return json;
    }

    /**
     * 验证手机格式 去掉重复的方法
     *
     * @param mobileArr 字符串
     */
    public Map<String, Object> checkMobile(String mobileArr) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        mobileArr = mobileArr.replaceAll("\r\n", "");// 去除空格回车
        mobileArr = mobileArr.replaceAll(" ", "");// 去除空格回车
        String[] lm = mobileArr.split(",");// 定义数组
        List list = new ArrayList();// new一个arralist
        Set set = new HashSet();// new 一个hashset
        set.addAll(Arrays.asList(lm));// 将数组转为list并存入set中，就可以去掉重复项了
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            list.add(it.next());// 遍历set 将所有元素键入list中
        }
        String noRepeatList = list.toString();
        noRepeatList = noRepeatList.replace("[", "");
        noRepeatList = noRepeatList.replace("]", "");
        noRepeatList = noRepeatList.replace(" ", "");
        noRepeatList = noRepeatList.trim();

        String flag = "true";
        String errorMobile = "";
        String[] lms = noRepeatList.split(",");
        if (lms.length > 0) {
            for (int i = 0; i < lms.length; i++) {
                if (!lms[i].trim().matches("^1[0-9]{10}$")) {
                    flag = "false";
                    errorMobile = lms[i] + "格式有误";
                    break;
                }
            }
        }
        returnMap.put("flag", flag);
        returnMap.put("mobileList", noRepeatList);
        returnMap.put("errorMobile", errorMobile);
        return returnMap;
    }

    /**
     * 发送邮件
     *
     * @param request
     * @return
     */
    @RequestMapping("/user/sendEmailMsg")
    @ResponseBody
    public Map<String, Object> sendEmailMsg(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String linksman = request.getParameter("linksman");// 获取联系人
            String title = request.getParameter("title");// 获取标题
            String content = request.getParameter("content");// 获取内容

            int type = Integer.valueOf(request.getParameter("type"));// 邮件类型
            String startTime = request.getParameter("startTime");// 发送时间
            Date starttime = new Date();
            // 如果是定时短信发送时间要大于当前时间
            if (type == 2) {
                if ("".equals(startTime)) {
                    json = this.getJsonMap(false, "请输入发送时间", "");
                    return json;
                }
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                starttime = formatDate.parse(startTime);
                if (!starttime.after(new Date())) {
                    json = this.getJsonMap(false, "定时发送的时间要大于当前日期", "");
                    return json;
                }
            }

            if (StringUtils.isNotEmpty(linksman) && StringUtils.isNotEmpty(content) && StringUtils.isNotEmpty(title)) {
                // 验证邮箱
                Map<String, Object> returnMap = checkEmail(linksman);
                // 错误信息
                String errorEmail = returnMap.get("errorMessage").toString();
                if (Boolean.parseBoolean(returnMap.get("flag").toString()) == false) {
                    json = this.getJsonMap(false, errorEmail, "");
                    return json;
                } else {
                    JsonObject user = SingletonLoginUtils.getLoginUser(request);
                    List<UserEmailMsg> emailMsgList = new ArrayList<UserEmailMsg>();
                    UserEmailMsg userEmailMsg = new UserEmailMsg();
                    userEmailMsg.setId(0);
                    userEmailMsg.setTitle(title);
                    userEmailMsg.setContent(content);
                    userEmailMsg.setEmail(returnMap.get("returnList").toString());
                    userEmailMsg.setType(type);
                    userEmailMsg.setSendTime(starttime);
                    if (type == 1) {
                        // 发送邮件
                        userEmailMsgService.batchSendEmail(returnMap.get("returnList").toString().split(","), content,
                                title, 3);
                        // emailService.sendBatchMail(returnMap.get("returnList").toString().split(","),
                        // content, title);
                        userEmailMsg.setStatus(1);
                    } else {
                        userEmailMsg.setStatus(2);

                    }
                    if (ObjectUtils.isNotNull(user)) {
                        userEmailMsg.setUserId(user.get("id").getAsLong());
                    } else {
                        userEmailMsg.setUserId(0L);
                    }
                    userEmailMsg.setCreateTime(new Date());
                    emailMsgList.add(userEmailMsg);
                    userEmailMsgService.addUserEmailMsg(emailMsgList);
                    errorEmail = "发送成功";
                }
                json = this.getJsonMap(Boolean.parseBoolean(returnMap.get("flag").toString()), errorEmail, "");
            } else {
                json = this.getJsonMap(false, "联系人、标题或内容不能为空", null);
            }
        } catch (Exception e) {
            logger.error("sendEmailMsg", e);
        }
        return json;
    }

    /**
     * 进度条
     */
    @RequestMapping("/user/progressbar")
    public String progressbar(HttpServletRequest request, @RequestParam("type") int type) {
        try {
            request.setAttribute("type", type);
        } catch (Exception e) {
            logger.error("progressbar", e);
        }
        return progressbar;
    }

    /**
     * 查询进度
     */
    @RequestMapping("/query/progressbar")
    @ResponseBody
    public Object queryprogressbar(HttpServletRequest request, @RequestParam("type") int type) {
        Map<String, Object> json = null;
        try {
            if (type == 1) {
                EmailThread emailThread = new EmailThread();
                double sumNum = Double.valueOf(emailThread.getSumNum());
                double listNum = Double.valueOf(emailThread.getList().size());
                Map map = new HashMap();
                map.put("sumNum", sumNum);
                map.put("listNum", listNum);
                json = this.getJsonMap(true, "", map);
            } else {
                SmsBatchThread smsBatchThread = new SmsBatchThread();
                double sumNum = Double.valueOf(smsBatchThread.getSumNum());
                double listNum = Double.valueOf(smsBatchThread.getList().size());
                Map map = new HashMap();
                map.put("sumNum", sumNum);
                map.put("listNum", listNum);
                json = this.getJsonMap(true, "", map);
            }
        } catch (Exception e) {
            logger.error("queryprogressbar", e);
        }
        return json;
    }

    /**
     * 验证邮箱格式 去重
     *
     * @param emailStr
     */
    public Map<String, Object> checkEmail(String emailStr) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        emailStr = emailStr.replaceAll("\r\n", "");// 去除空格回车
        emailStr = emailStr.replaceAll(" ", "");// 去除空格回车
        String[] lm = emailStr.split(",");// 定义数组

        List list = new ArrayList();// new一个arralist
        Set set = new HashSet();// new 一个hashset
        set.addAll(Arrays.asList(lm));// 将数组转为list并存入set中，就可以去掉重复项了
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            list.add(it.next());// 遍历set 将所有元素键入list中
        }
        String noRepeatList = list.toString();
        noRepeatList = noRepeatList.replace("[", "");
        noRepeatList = noRepeatList.replace("]", "");
        noRepeatList = noRepeatList.replace(" ", "");
        noRepeatList = noRepeatList.trim();

        boolean flag = true;
        String errorMessage = "";
        String[] lms = noRepeatList.split(",");
        if (lms.length > 0) {
            for (int i = 0; i < lms.length; i++) {
                if (!lms[i].trim()
                        .matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
                    flag = false;
                    errorMessage = lms[i] + "格式有误";
                    break;
                }
            }
        }

        returnMap.put("flag", flag);
        returnMap.put("returnList", noRepeatList);
        returnMap.put("errorMessage", errorMessage);
        return returnMap;
    }

    /**
     * 短信管理
     *
     * @param model
     * @param request
     * @param userMobileMsg
     * @param page
     * @return
     */
    @RequestMapping("/user/sendMsglist")
    public String querySendMobileMsgList(Model model, HttpServletRequest request,
                                         @ModelAttribute("userMobileMsg") UserMobileMsg userMobileMsg, @ModelAttribute("page") PageEntity page) {
        try {

            page.setPageSize(10);
            List<UserMobileMsg> list = userMobileMsgService.queryUserMobileMsgList(userMobileMsg, page);
            model.addAttribute("list", list);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("querySendMobileMsgList", e);
        }
        return sendMobileMsgList;
    }

    /**
     * 邮箱管理
     */
    @RequestMapping("/user/sendEmaillist")
    public String querySendEmailMsgList(Model model, HttpServletRequest request,
                                        @ModelAttribute("userEmailMsg") UserEmailMsg userEmailMsg, @ModelAttribute("page") PageEntity page) {
        try {

            page.setPageSize(10);
            List<UserEmailMsg> list = userEmailMsgService.queryUserEmailMsgList(userEmailMsg, page);
            model.addAttribute("list", list);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("querySendEmailMsgList", e);
        }
        return sendEmailMsgList;
    }

    /**
     * 邮箱管理
     */
    @RequestMapping("/sendEmail/del")
    public String delSendEmailMsg(Model model, HttpServletRequest request, @RequestParam("id") Long id) {
        try {
            userEmailMsgService.delUserEmailMsgById(id);
        } catch (Exception e) {
            logger.error("delSendEmailMsg", e);
        }
        return "redirect:/admin/user/sendEmaillist";
    }

    /**
     * 删除短信
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/user/delMsgById/{id}")
    @ResponseBody
    public Map<String, Object> delUserMobileMsgById(Model model, @PathVariable("id") Long id) {
        Map<String, Object> json = null;
        try {
            userMobileMsgService.delUserMobileMsg(id);
            json = this.getJsonMap(true, "删除成功", null);
        } catch (Exception e) {
            logger.error("delUserMobileMsgById", e);
            json = this.getJsonMap(false, "删除失败！", null);
        }
        return json;
    }

    /**
     * 查询短信详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/user/sendMsgInfo/{id}")
    public String querySendMobleMsgInfo(Model model, @PathVariable("id") Long id) {
        try {
            UserMobileMsg userMobileMsg = userMobileMsgService.queryUserMobileMsgById(id);
            model.addAttribute("userMobileMsg", userMobileMsg);
        } catch (Exception e) {
            logger.error("querySendMobleMsgInfo", e);
        }
        return sendMobileMsgInfo;
    }

    /**
     * 修改短信
     *
     * @return
     */
    @RequestMapping("/user/updateUserMsg")
    @ResponseBody
    public Map<String, Object> updateUserMsg(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            UserMobileMsg userMobileMsg = new UserMobileMsg();
            userMobileMsg.setId(Integer.parseInt(request.getParameter("msgId")));
            userMobileMsg.setContent(request.getParameter("content"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            userMobileMsg.setSendTime(df.parse(request.getParameter("sendTime")));
            userMobileMsgService.updateUserMobileMsg(userMobileMsg);
            json = this.getJsonMap(true, "修改成功", null);
        } catch (Exception e) {
            logger.error("updateUserMsg", e);
            json = this.getJsonMap(false, "修改失败", null);
        }
        return json;
    }

    /**
     * 查询邮件详情
     */
    @RequestMapping("/user/sendEmailMsgInfo/{id}")
    public String querySendEmailMsgInfo(Model model, @PathVariable("id") Long id) {
        try {
            UserEmailMsg userEmailMsg = userEmailMsgService.queryUserEmailMsgById(id);
            model.addAttribute("userEmailMsg", userEmailMsg);
        } catch (Exception e) {
            logger.error("querySendMobleMsgInfo", e);
        }
        return sendEmailMsgInfo;
    }

    /**
     * 修改定时未发送的邮件
     */
    @RequestMapping("/sendEmailMsg/update")
    @ResponseBody
    public Object updateSendEmailMsgInfo(HttpServletRequest request, UserEmailMsg userEmailMsg) {
        Map<String, Object> json = null;
        try {
            String sendTime = request.getParameter("sendTime");
            if ("".equals(sendTime)) {
                json = this.getJsonMap(false, "请输入发送时间", "");
                return json;
            }
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sendtime = formatDate.parse(sendTime);
            if (!sendtime.after(new Date())) {
                json = this.getJsonMap(false, "定时发送的时间要大于当前日期", "");
                return json;
            }

            userEmailMsgService.updateUserEmailMsgById(userEmailMsg);
            json = this.getJsonMap(true, "成功", "");
        } catch (Exception e) {
            logger.error("querySendMobleMsgInfo", e);
        }
        return json;
    }

    /**
     * 查询登陆log日志
     *
     * @param request
     * @param model
     * @param userLoginLog
     * @param page
     * @return
     */
    @RequestMapping("/user/loginlog")
    public String queryUserLoginLogList(HttpServletRequest request, Model model,
                                        @ModelAttribute UserLoginLog userLoginLog, @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页

            page.setPageSize(10);
            // 查询登陆log日志
            List<UserLoginLog> userLoginLogList = userLoginLogService.getUserLoginLogListPage(userLoginLog, page);
            model.addAttribute("userLoginLogList", userLoginLogList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("queryUserLoginLogList", e);
            return setExceptionRequest(request, e);
        }
        return toUserLoginLog;
    }

    @RequestMapping("/sys/loginlog/{userid}")
    public String queryUserLoginLogLists(HttpServletRequest request, Model model,
                                         @ModelAttribute("userLoginLog") UserLoginLog userLoginLog, @ModelAttribute("page") PageEntity page,
                                         @ModelAttribute("userid") Long userId) {

        try {
            // 设置分页
            UserLoginLog u = new UserLoginLog();
            u.setUserId(userId);
            u.setEndDate(userLoginLog.getEndDate());
            u.setStartDate(userLoginLog.getStartDate());
            page.setPageSize(10);
            // 查询登陆log日志
            List<UserLoginLog> userLoginLogList = userLoginLogService.getUserLoginLogListPage(u, page);
            model.addAttribute("loginLogList", userLoginLogList);
            model.addAttribute("userId", u.getUserId());
            model.addAttribute("page", page);

        } catch (Exception e) {
            logger.error("queryUserLoginLogList", e);
            return setExceptionRequest(request, e);
        }
        return toUserLoginLogs;
    }

    /**
     * 跳转到小组发消息页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/toSendSystemMessages")
    public ModelAndView senSystemMessages(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(senSystemMessages);
        return modelAndView;
    }

    /**
     * 发送系统消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/sendJoinGroup")
    @ResponseBody
    public Map<String, Object> sendSystemInform(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String content = request.getParameter("content");// 发送系统消息的内容
            MsgSystem msgReceive = new MsgSystem();
            msgReceive.setContent(content);// 添加站内信的内容
            msgReceive.setUpdateTime(new Date());// 更新时间s
            msgReceive.setAddTime(new Date());// 添加时间
            msgSystemService.addMsgSystem(msgReceive);
            // msgReceiveService.addSystemMessage(content);
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemInform", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 批量发送 系统消息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/letter/sendSystemInfoByCusIds")
    @ResponseBody
    public Map<String, Object> sendSystemInfoByCusIds(@ModelAttribute User user, HttpServletRequest request,
                                                      @ModelAttribute("page") PageEntity page) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            page.setPageSize(10000);
            // 查询学员列表
            List<User> list = userService.getUserListAndCourse(user, page);

            String content = request.getParameter("messsageContent");// 发送系统消息的内容
            if (list == null || list.size() == 0) {
                map.put("message", "没有符合条件的会员");
            } else {
                List<MsgReceive> msgrcList = new ArrayList<MsgReceive>();
                for (int i = 0; i < list.size(); i++) {
                    MsgReceive msgReceive1 = new MsgReceive();
                    msgReceive1.setContent(content);
                    msgReceive1.setAddTime(new Date());
                    msgReceive1.setReceivingCusId(list.get(i).getId());
                    msgReceive1.setStatus(LetterConstans.LETTER_STATUS_READ);
                    msgReceive1.setType(LetterConstans.LETTER_TYPE_SYSTEMINFORM);
                    msgReceive1.setUpdateTime(new Date());
                    msgReceive1.setShowname("");
                    msgReceive1.setCusId(0L);
                    msgrcList.add(msgReceive1);
                }
                // 发送
                msgReceiveService.addMsgReceiveBatch(msgrcList);
                map.put("message", "success");
            }

        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemInfoByCusIds", e);
            setExceptionRequest(request, e);
        }
        return map;
    }

    /**
     * 获取课程列表
     **/
    @RequestMapping("/user/optRecord")
    public ModelAndView getOptRecord(@ModelAttribute("userOptRecord") UserOptRecord userOptRecord,
                                     @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(optRecordList);
        try {

            List<UserOptRecord> list = userService.getUserOptRecordListByCondition(userOptRecord, page);
            modelAndView.addObject("list", list);
        } catch (Exception e) {
            logger.error("getOptRecord:" + e);
        }
        return modelAndView;
    }

    // 单个开通
    @RequestMapping("/user/toOneOpen")
    public String toOneOpen(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            request.setAttribute("groupList", gson.toJson(groupList));
            // 获取所有岗位
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
        } catch (Exception e) {
            logger.error("AdminUserController.toBatchOpen", e);
            return setExceptionRequest(request, e);
        }
        return oneOpen;
    }

    /**
     * 单独开通学员信息
     *
     * @param request
     * @param myfile
     * @return
     */
    @RequestMapping("/user/open/oneuser")
    @ResponseBody
    public Object importProcess(@ModelAttribute("userForm") UserForm userForm, HttpServletRequest request) {
        ModelAndView modelandView = new ModelAndView();
        modelandView.setViewName("/common/success");
        Map<String, Object> json;
        try {
            // 在邮箱不为空的情况下，验证邮箱格式是否正确
        /*
         * if (ObjectUtils.isNotNull(userForm.getEmail()) &&
	     * StringUtils.isNotEmpty(userForm.getEmail())) { Pattern emailRes =
	     * Pattern.compile(
	     * "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
	     * ); Matcher mat = emailRes.matcher(userForm.getEmail()); boolean
	     * emailF = mat.matches(); if (!emailF) { return
	     * json=this.getJsonMap(false, "邮箱格式错误", null); } }else{ return
	     * json=this.getJsonMap(false, "邮箱为空", null); } // 验证手机是否为空 if
	     * (ObjectUtils.isNull(userForm.getMobile()) ||
	     * StringUtils.isEmpty(userForm.getMobile())) { return
	     * json=this.getJsonMap(false, "手机号为空", null); } //
	     * 在手机不为空的情况下，验证手机格式是否正确 if
	     * (ObjectUtils.isNotNull(userForm.getMobile()) &&
	     * StringUtils.isNotEmpty(userForm.getMobile()) &&
	     * !userForm.getMobile().matches(
	     * "^(13[0-9]|15[012356789]|18[0-9]|14[57]|17[012356789])[0-9]{8}$")
	     * ) { return json=this.getJsonMap(false, "手机号格式错误", null); } if
	     * (ObjectUtils.isNull(userForm.getPassword()) ||
	     * StringUtils.isEmpty(userForm.getPassword())) { return
	     * json=this.getJsonMap(false, "密码为空", null); }
	     * if(ObjectUtils.isNull(userForm.getConfirmPassword()) ||
	     * StringUtils.isEmpty(userForm.getConfirmPassword())){ return
	     * json=this.getJsonMap(false, "重复密码为空", null); }
	     * if(!userForm.getPassword().equals(userForm.getConfirmPassword()))
	     * { return json=this.getJsonMap(false, "密码不一致", null); }
	     */
            User user = new User();
            user.setNickname(userForm.getNickName());// 验证用户名称
            user.setMobile(userForm.getMobile());
            user.setEmail(userForm.getEmail());
            if (userForm.getLevel() != 0) {
                user.setLevel(userForm.getLevel());
            } else {
                user.setLevel(1);// 获取级别(级别默认1员工)
            }

            user.setRealname(userForm.getRealname());
            user.setSysGroupId(userForm.getSysGroupId());// 部门编号
            // List<User> list = userService.getUserList(user);//email查询
            // 验证邮箱唯一,邮箱是否已经注册
	    /*
	     * if (ObjectUtils.isNotNull(list)) { return this.getJsonMap(false,
	     * "邮箱已存在", null); }
	     * 
	     * int ismobile = userService.getUserByMobile(user);//手机查询 // 验证手机唯一
	     * if (ismobile != 0) { return this.getJsonMap(false, "手机号已存在",
	     * null); }
	     * 
	     * user = userService.getUserByNickName(user.getNickname());//用户名查询
	     */
            // 邮箱注册
            String userIp = WebUtils.getIpAddr(request);
            user.setMobile(userForm.getMobile());
            user.setPassword(userForm.getPassword());
            user.setUserip(userIp);
            user.setRegisterFrom(UserExpandFrom.adminFrom.toString());// 后台开通账号
            userService.addUser(user);
            Long upUserId = this.getUpLoginId(request);
            // 注册时发送系统消息
            Map<String, Object> websitemap = websiteProfileService
                    .getWebsiteProfileByType(WebSiteProfileType.web.toString());
            Map<String, Object> web = (Map<String, Object>) websitemap.get("web");
            String company = web.get("company").toString();
            String conent = "欢迎来到" + company + ",希望您能够快乐的学习";
            msgReceiveService.addSystemMessageByCusId(conent, user.getId());
            // 开通学员组信息
            List<UserGroupMiddle> userGroupMiddleList = new ArrayList<UserGroupMiddle>();

            // 可以添加多个组
            if (StringUtils.isNotEmpty(userForm.getGroupIds())) {
                // 学员信息分组
                String[] groupIdArray = userForm.getGroupIds().split(",");
                for (int i = 0; i < groupIdArray.length; i++) {
                    UserGroupMiddle _userGroupMiddle = new UserGroupMiddle();
                    _userGroupMiddle.setUserId(user.getId());
                    _userGroupMiddle.setGroupId(Long.parseLong(groupIdArray[i]));
                    userGroupMiddleList.add(_userGroupMiddle);
                }
                userGroupMiddleService.addUserGroupMiddle(userGroupMiddleList);
            }
            json = this.getJsonMap(true, "success", null);
            return json;
        } catch (Exception e) {
            logger.error("AdminUserController.importProcess", e);
            json = this.getJsonMap(false, "系统出错", null);
        }
        return json;
    }

    /**
     * 验证手机号是否存在
     *
     * @param request
     * @param response
     * @param queryUserCondition
     * @return
     */
    @RequestMapping("/user/checkMobile")
    public String checkMobile(HttpServletRequest request, HttpServletResponse response,
                              @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
        try {
            String searchKey = queryUserCondition.getSearchKey();
            if (StringUtils.isNotEmpty(searchKey)) {
                User user = new User();
                user.setMobile(searchKey);
                int ismobile = userService.getUserByMobile(user);
                if (ismobile == 0) {
                    this.sendMessage(request, response, "true");
                    return null;
                }
            }
            this.sendMessage(request, response, "false");
        } catch (Exception e) {
            logger.error("AdminUserController.checkMobile", e);
            return "";
        }
        return null;
    }

    /**
     * 检查邮箱是否存在
     *
     * @param request
     * @param response
     * @param queryUserCondition
     * @return
     */
    @RequestMapping("/user/checkEmail")
    public String checkEmail(HttpServletRequest request, HttpServletResponse response,
                             @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
        try {
            String searchKey = queryUserCondition.getSearchKey();
            if (StringUtils.isNotEmpty(searchKey)) {
                User user = new User();
                user.setEmail(searchKey);
                List<User> list = userService.getUserList(user);
                if (ObjectUtils.isNull(list)) {
                    this.sendMessage(request, response, "true");
                    return null;
                }
            }
            this.sendMessage(request, response, "false");
        } catch (IOException e) {
            logger.error("AdminUserController.checkEmail", e);
            return "";
        }
        return null;
    }

    // 单个开通
    @RequestMapping("/user/groupToOneOpen")
    public String grouptoOneOpen(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {
            User sysLoginedUser = getSysLoginedUser(request);
            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(sysLoginedUser.getId());
            List<UserGroupMiddle> userGroupMiddleList = userGroupMiddleService.getUserGroupByUserId(sysLoginedUser.getId());
            if (ObjectUtils.isNotNull(userGroupMiddleList)) {
                List<Long> ids = new ArrayList<>();
                for (UserGroupMiddle userGroup : userGroupMiddleList) {
                    ids.add(userGroup.getGroupId());
                }
                List<UserGroup> userGroupList = userGroupService.getUserGroupListByIds(ids);
                request.setAttribute("userGroupList", userGroupList);
                request.setAttribute("sysUserId", sysLoginedUser.getId());
            }
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            request.setAttribute("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("AdminUserController.toBatchOpen", e);
            return setExceptionRequest(request, e);
        }
        return groupOneOpen;
    }

    /**
     * 学员组批量开通
     *
     * @param request
     * @param page
     * @return
     */
    @RequestMapping("/user/groupToBatchOpen")
    public String grouptoBtachOpen(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        try {

            User sysLoginedUser = getSysLoginedUser(request);
            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(sysLoginedUser.getId());
            List<UserGroupMiddle> userGroupMiddleList = userGroupMiddleService.getUserGroupByUserId(sysLoginedUser.getId());
            if (ObjectUtils.isNotNull(userGroupMiddleList)) {
                List<Long> ids = new ArrayList<>();
                for (UserGroupMiddle userGroup : userGroupMiddleList) {
                    ids.add(userGroup.getGroupId());
                }
                List<UserGroup> userGroupList = userGroupService.getUserGroupListByIds(ids);
                request.setAttribute("userGroupList", userGroupList);
                request.setAttribute("sysUserId", sysLoginedUser.getId());
            }
            // 获取所有部门
            List<SysGroup> groupList = this.groupService.getGroupList();
            request.setAttribute("groupList", gson.toJson(groupList));
        } catch (Exception e) {
            logger.error("AdminUserController.toBatchOpen", e);
            return setExceptionRequest(request, e);
        }
        return groupBatchOpen;
    }

    @RequestMapping("/user/toCouponCodes/{toUserId}")
    public ModelAndView toCouponCodes(@PathVariable("toUserId") Long id, HttpServletRequest request,
                                      @ModelAttribute("queryCouponCode") QueryCouponCode queryCouponCode,
                                      @ModelAttribute("page") PageEntity page) {
        ModelAndView mav = new ModelAndView(toCouponCodes);
        try {
            page.setPageSize(10);
            queryCouponCode.setIsGive("give");
            List<CouponCodeDTO> couponCodeDTOList = couponCodeService.getCouponCodePage(queryCouponCode, page);
            mav.addObject("couponCodeDTOList", couponCodeDTOList);
            mav.addObject("page", page);
            mav.addObject("userId", id);
        } catch (Exception e) {
            logger.error("AdminUserController.toCouponCodes", e);
        }
        return mav;
    }

    @RequestMapping("/user/giveCoupons")
    @ResponseBody
    public Map<String, Object> giveCoupons(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            String userId = request.getParameter("userId");
            couponCodeService.giveCouponBatch(ids, Long.valueOf(userId));
            json = getJsonMap(true, "success", null);
        } catch (NumberFormatException e) {
            logger.error("AdminUserController.giveCoupons", e);
            json = getJsonMap(false, "false", null);
        }
        return json;
    }

    /**
     * 获得系统登录用户
     *
     * @param request
     * @return
     */
    public User getSysLoginedUser(HttpServletRequest request) {
        JsonObject jsonObject = SingletonLoginUtils.getLoginUser(request);
        Gson gson = new Gson();
        User user = gson.fromJson(gson.toJson(jsonObject), User.class);

        return user;
    }

    /**
     * 添加全部员工
     *
     * @return
     */
    @RequestMapping("/user/groupList")
    public String queryGroupList(HttpServletRequest request, @ModelAttribute("user") User user,
                                 @ModelAttribute("page") PageEntity page) {
        try {
            // 设置分页 ，默认每页10
            page.setPageSize(10);
            List<User> groupUsers = userService.queryGroupByid(user, page);
            request.setAttribute("groupUsers", groupUsers);
            request.setAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminUserController.queryGroupList", e);
        }

        return goGroupList;
    }

    /**
     * 根据id获取员工
     *
     * @param ids
     * @return
     */
    @RequestMapping("/user/querybyIds")
    @ResponseBody
    public Map<String, Object> queryGroupUserIds(@ModelAttribute("ids") String ids) {
        Map<String, Object> json = null;
        try {
            List<User> userList = userService.queryGroupUserIds(ids);
            if (ObjectUtils.isNotNull(userList)) {
                json = this.getJsonMap(true, "success", userList);
            }
        } catch (Exception e) {
            logger.error("AdminUserController.queryGroupUserIds");
            json = this.getJsonMap(false, "not found", null);
        }
        return json;
    }

    /**
     * 获取用户操作记录
     *
     * @param userOptRecord
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/user/getUserOptRecordList")
    public String getUserOptRecordList(@ModelAttribute("userOptRecord") UserOptRecord userOptRecord,
                                       @ModelAttribute("page") PageEntity page, Model model) {
        try {
            page.setPageSize(12);
            List<UserOptRecord> userOptRecordListByCondition = userService
                    .getUserOptRecordListByCondition(userOptRecord, page);
            model.addAttribute("userOptRecordList", userOptRecordListByCondition);
            model.addAttribute("page", page);
            model.addAttribute("userOptRecord", userOptRecord);
        } catch (Exception e) {
            logger.error("AdminUserController.getUserOptRecordList", e);
        }
        return userOptRecordList;
    }

    /**
     * 获取用户操作记录
     *
     * @param userOptRecord
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/user/UserMsg/{id}")
    public String userMsg(@PathVariable("id") Long id, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        try {

            User user = userService.getUserById(id);
            CourseStudyhistory courseStudyhistory = new CourseStudyhistory();
            courseStudyhistory.setUserId(id);
            PageEntity page = new PageEntity();
            page.setPageSize(10);
            List<CourseStudyhistory> studyHistoryList = courseStudyhistoryService
                    .getCourseStudyhistoryListGroupByCourseId(courseStudyhistory, page);
            map.put("cusId", String.valueOf(id));
            map.put("page.currentPage", "1");
            String resultJson = HttpUtil.doPost(CommonConstants.examPath + "/api/yzl/paperRecord/list", map);
            Map<String, Object> result = gson.fromJson(resultJson, new TypeToken<Map<String, Object>>() {
            }.getType());
            request.setAttribute("studyHistoryList", studyHistoryList);
            request.setAttribute("result", result);
            request.setAttribute("user", user);
        } catch (Exception e) {
            logger.error("AdminUserController.getUserOptRecordList", e);
        }
        return userMsg;
    }

    /**
     * 检查用户名是否可用（nickname）
     *
     * @return
     */
    @RequestMapping("/user/LoginName")
    public String checkLoginName(HttpServletRequest request, HttpServletResponse response,
                                 @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
        try {
            String nickname = queryUserCondition.getSearchKey();
            if (StringUtils.isNotEmpty(nickname)) {
                User user = userService.getUserByName(nickname.trim());
                if (ObjectUtils.isNull(user)) {
                    this.sendMessage(request, response, "true");
                    return null;
                }
            }
            this.sendMessage(request, response, "false");
        } catch (Exception e) {
            logger.error("AdminUserController.LoginName", e);
            return "";
        }
        return null;
    }

    /**
     * 检查邮箱是否可用
     *
     * @return
     */
    @RequestMapping("/user/LoginEmail")
    public String checkLoginEmail(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
        try {
            String Email = queryUserCondition.getSearchKey();
            User user = userService.getUserByLoginEmail(Email);
            if (ObjectUtils.isNotNull(user)) {
                this.sendMessage(request, response, "false");
            } else {
                this.sendMessage(request, response, "true");
            }
        } catch (Exception e) {
            logger.error("AdminUserController.LoginName", e);
            return "";
        }
        return null;
    }

    /**
     * 检查手机号是否可用
     *
     * @return
     */
    @RequestMapping("/user/LoginMobile")
    public Map<String, Object> checkLoginMobile(HttpServletRequest request, HttpServletResponse response,
                                                @ModelAttribute("queryUserCondition") QueryUserCondition queryUserCondition) {
        try {
            String mobile = queryUserCondition.getSearchKey();
            User user = userService.getUserByLoginMobile(mobile);
            if (ObjectUtils.isNotNull(user)) {
                this.sendMessage(request, response, "false");
            } else {
                this.sendMessage(request, response, "true");
            }
        } catch (Exception e) {
            logger.error("AdminUserController.LoginMobile" + e);
        }
        return null;
    }

    /**
     * 单独给用户发送消息 by huan.liu
     *
     * @param request
     * @param msgContent
     * @param userId
     * @return
     */
    @RequestMapping(value = "/letter/sendSystemMsgToUser")
    @ResponseBody
    public Map<String, Object> sendSystemMsgToUser(@RequestParam("userId") Long userId,
                                                   @RequestParam("msgContent") String msgContent, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            msgReceiveService.addSystemMessageByCusId(msgContent, userId);
            map.put("success", true);
        } catch (Exception e) {
            logger.error("AdminLetterAction.sendSystemMsgToUser", e);
            map.put("success", false);
        }
        return map;
    }
}
