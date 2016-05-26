package io.wangxiao.edu.home.controller.arrange;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.DateEditor;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.ArrangeType;
import io.wangxiao.edu.home.entity.arrange.*;
import io.wangxiao.edu.home.entity.user.UserGroup;
import io.wangxiao.edu.home.service.arrange.*;
import io.wangxiao.edu.home.service.user.UserGroupMiddleService;
import io.wangxiao.edu.home.service.user.UserGroupService;
import io.wangxiao.edu.sysuser.entity.SysUserGroup;
import io.wangxiao.edu.sysuser.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminArrangeController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminArrangeController.class);
    private static final String arrangeAdd = getViewPath("/admin/arrange/arrange_add");// 去创建安排考试页面

    private static final String getArrangeExam = getViewPath("/admin/arrange/exam_list");// 试卷列表页面
    private static final String arrangeList = getViewPath("/admin/arrange/arrange_list");// 安排考试列表页面
    private static final String grouparrangeAdd = getViewPath("/admin/arrange/group_arrange_add");// 去创建安排考试页面
    private static final String myArrangeList = getViewPath("/admin/arrange/my_arrange_list");// 我的安排考试
    private static final String myGooupArrangeList = getViewPath("/admin/arrange/my_group_arrange");// 我的部门安排考试
    private static final String arrangeDetails = getViewPath("/admin/arrange/arrange_details");//安排考试详情页面
    private static final String arrangeUpdate = getViewPath("/admin/arrange/arrange_update");//去修改安排考试页面
    private static final String exampaper = getViewPath("/admin/arrange/arrange_exam_paper");// 考试概况曲线图
    private static final String grouparrange = getViewPath("/admin/arrange/arrange_exam_groud");// 考试概况曲线图
    @Autowired
    private ArrangeService arrangeService;// 安排考试服务
    @Autowired
    private UserGroupService userGroupService;//个人部门服务
    @Autowired
    private ArrangeRecordService arrangeRecordService;// 安排考试记录服务
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArrangeMiddleUserService arrangeMiddleUserService;//安排考试员工服务
    @Autowired
    private ArrangeExamService arrangeExamService;//安排考试试卷服务
    @Autowired
    private ArrangeMiddleGroupService arrangeMiddleGroupService;//安排考试部门服务
    @Autowired
    private UserGroupMiddleService UserGroupMiddleService;//查询部门人数

    @InitBinder("arrange")
    public void initBinderArrange(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("arrange.");
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

    @InitBinder("arrangerecord")
    public void arrangerecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("arrangerecord.");
    }

    @InitBinder("arrangeRecord")
    public void initBinderArrangeRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("arrangeRecord.");
    }

    /**
     * 去创建个人安排考试页面
     *
     * @return
     */
    @RequestMapping("/arrange/goArrangeAdd")
    public String arrangeAdd(HttpServletRequest request) {
        return arrangeAdd;
    }

    /**
     * 去创建部门人安排考试页面
     *
     * @return
     */
    @RequestMapping("/arrange/groupArrangeAdd")
    public String groupArrangeAdd(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        UserGroup userGroup = new UserGroup();
        page.setPageSize(9999);
        List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
        request.setAttribute("userGroupList", userGroupList);
        return grouparrangeAdd;
    }

    /**
     * 执行创建安排考试
     */
    @RequestMapping("/arrange/addArrange")
    public String addArrange(HttpServletRequest request, @ModelAttribute("arrange") Arrange arrange) {
        try {
            String sysLoginName = SingletonLoginUtils.getLoginUserName(request);
            // 获取当前登录人
            arrange.setReleasePeople(sysLoginName);
            // 创建时间
            arrange.setReleaseTime(new Date());
            arrange.setCreaterId(getLoginUserId(request));
            // 执行创建安排考试
            arrangeService.addArrange(arrange);

        } catch (Exception e) {
            logger.error("AdminArrangeController.addArrange", e);
        }
        return "redirect:/admin/arrange/arrangeList";
    }

    /**
     * 我安排的考试列表
     */
    @RequestMapping("/arrange/arrangeList")
    public String arrangeList(HttpServletRequest request, @ModelAttribute("arrange") Arrange arrange,
                              @ModelAttribute("page") PageEntity page) {
        try {
            page.setPageSize(10);
            arrange.setCreaterId(getLoginUserId(request));
            List<Arrange> arrangeList = arrangeService.getArrangeList(arrange, page);
            request.setAttribute("arrangeList", arrangeList);
            request.setAttribute("page", page);
            request.setAttribute("arrange", arrange);
        } catch (Exception e) {
            logger.error("AdminArrangeController.arrangeList", e);
        }
        return arrangeList;
    }

    /**
     * 查询所有试卷
     *
     * @param request
     * @return
     */
    @RequestMapping("/arrange/getArrangeExamList")
    public String getArrangeExamList(HttpServletRequest request, @ModelAttribute("page") PageEntity page) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            Gson gson = new Gson();
            String id = request.getParameter("examId");
            String examName = request.getParameter("examName");
            map.put("id", id);
            map.put("examName", examName);
            map.put("page.currentPage", page.getCurrentPage() + "");
            String examJson = HttpUtil.doPost(CommonConstants.examPath + "/webapp/exam/papers", map);
            Map<String, Object> result = gson.fromJson(examJson, new TypeToken<Map<String, Object>>() {
            }.getType());
            if (result != null) {
                Map<String, Object> entity = gson.fromJson(gson.toJson(result.get("entity")), new TypeToken<Map<String, Object>>() {
                }.getType());
                PageEntity newPage = gson.fromJson(gson.toJson(entity.get("page")), new TypeToken<PageEntity>() {
                }.getType());
                page.setTotalResultSize(newPage.getTotalResultSize());
                page.setCurrentPage(newPage.getCurrentPage());
                page.setTotalPageSize(newPage.getTotalPageSize());
            }
            request.setAttribute("result", result);
            request.setAttribute("map", map);
            if (result != null) {
                if (!(boolean) result.get("success")) {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            logger.error("AdminArrangeController.getArrangeExamList", e);
        }
        return getArrangeExam;
    }

    /**
     * 根据id获取试卷
     *
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping("/arrange/queryByExamIds")
    @ResponseBody
    public Map<String, Object> queryGroupUserIds(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        String id = request.getParameter("id");
        map.put("id", id);
        Map<String, Object> json = null;
        try {
            String examJson = HttpUtil.doPost(CommonConstants.examPath + "/webapp/exam/getExamByids", map);
            Map<String, Object> examResult = new Gson().fromJson(examJson, new TypeToken<Map<String, Object>>() {
            }.getType());
            if (ObjectUtils.isNotNull(examResult)) {
                json = this.getJsonMap(true, "success", examResult);
            }
        } catch (Exception e) {
            logger.error("AdminArrangeController.queryGroupUserIds");
            json = this.getJsonMap(false, "not found", null);
        }
        return json;
    }

    /**
     * 我的考试
     *
     * @param request
     * @param arrangeRecord
     * @param page
     * @return
     */
    @RequestMapping("/arrange/getArrangeRecordList")
    public String getArrangeRecordList(HttpServletRequest request, @ModelAttribute("arrangeRecord") ArrangeRecord arrangeRecord,
                                       @ModelAttribute("page") PageEntity page) {
        try {
            //获取用户登录Id
            Long sysUserId = SingletonLoginUtils.getLoginUserId(request);
            arrangeRecord.setUserId(sysUserId);
            page.setPageSize(10);
            List<ArrangeRecord> arrangeRecordList = arrangeRecordService.getArrangeRecordList(arrangeRecord, page);
            request.setAttribute("arrangeRecordList", arrangeRecordList);
            request.setAttribute("arrangeRecord", arrangeRecord);
            request.setAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("AdminArrangeController.getArrangeRecordList", e);
        }
        return myArrangeList;
    }

    /**
     * 我的部门安排考试
     */
    @RequestMapping("/arrange/getGorupArrangeList")
    public String getGorupArrangeList(HttpServletRequest request, @ModelAttribute("arrange") Arrange arrange,
                                      @ModelAttribute("page") PageEntity page) {
        try {
            //获取用户登录Id
            Long sysUserId = SingletonLoginUtils.getLoginUserId(request);
            SysUserGroup sysUserGroup = new SysUserGroup();
            sysUserGroup.setSysUserId(sysUserId);
            page.setPageSize(10);
            // 根据员工编号查询出所属部门
            List<SysUserGroup> sysUserGroupList = sysUserService.getSysUserGroupBySysUserId(sysUserGroup);
            if (sysUserGroupList != null && sysUserGroupList.size() > 0) {
                for (SysUserGroup _sysUserGroup : sysUserGroupList) {// 循环部门获取该用户下的所有部门
                    arrange.setUserGroupId(_sysUserGroup.getGroupId());//获取用户所在部门编号
                }
                //执行查询部门安排考试
                List<Arrange> groupArrangeList = arrangeService.getGroupArrangeList(arrange, page);
                request.setAttribute("groupArrangeList", groupArrangeList);
                request.setAttribute("page", page);
                request.setAttribute("arrange", arrange);
            }
        } catch (Exception e) {
            logger.error("AdminArrangeController.getGorupArrangeList", e);
        }
        return myGooupArrangeList;
    }

    /**
     * 安排考试详情列表展
     *
     * @param request
     * @param arrange
     * @param page
     * @return
     */
    @RequestMapping("/arrange/arrangeDetails")
    public String arrangeDetails(HttpServletRequest request, @ModelAttribute("arrangeRecord") ArrangeRecord arrangeRecord, @ModelAttribute("page") PageEntity page) {
        try {
            //考试详情列表
            List<ArrangeRecord> arrangeRecordList = arrangeRecordService.getArrangeRecordList(arrangeRecord, page);
            //考试任务
            Arrange arrange = arrangeService.getArrangeById(arrangeRecord.getArrangeId());
            request.setAttribute("arrangeRecordList", arrangeRecordList);
            request.setAttribute("arrange", arrange);
            request.setAttribute("page", page);

        } catch (Exception e) {
            logger.error("AdminArrangeController.arrangeDetails", e);
        }
        return arrangeDetails;
    }

    //去安排考试修改页面
    @RequestMapping("/arrange/toUpdateArrange/{id}")
    public String toUpdateArrange(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            //查询安排考试详情
            Arrange arrange = arrangeService.getArrangeById(id);
            //初始安排考试员工实体
            ArrangeMiddleUser arrangeMiddleUser = new ArrangeMiddleUser();
            arrangeMiddleUser.setArrangeId(id);//设置安排考试编号
            //查询员工
            List<ArrangeMiddleUser> arrangeMiddleUsers = arrangeMiddleUserService.getTaakUserById(arrangeMiddleUser);
            if (ObjectUtils.isNotNull(arrangeMiddleUsers)) {
                String userIds = "";
                for (ArrangeMiddleUser user : arrangeMiddleUsers) {
                    userIds += user.getUserId() + ",";
                }
                userIds = userIds.substring(0, userIds.length() - 1);
                //把关联的员工放进安排考试中
                arrange.setUserIds(userIds);
            }
            //初始化安排考试试卷
            ArrangeExam arrangeExam = new ArrangeExam();
            arrangeExam.setArrangeId(id);//设置安排考试编号
            //查询试卷
            List<ArrangeExam> arrangeExams = arrangeExamService.getArrangeExamByIds(arrangeExam);
            if (ObjectUtils.isNotNull(arrangeExams)) {
                String examIds = "";
                for (ArrangeExam exam : arrangeExams) {
                    examIds += exam.getExampaperId() + ",";
                }
                examIds = examIds.substring(0, examIds.length() - 1);
                //把关联的试卷放进安排考试中
                arrange.setExamIds(examIds);
            }
            request.setAttribute("arrange", arrange);
            //安排考试下的部门
            ArrangeMiddleGroup arrangeMiddleGroup = new ArrangeMiddleGroup();
            arrangeMiddleGroup.setArrangeId(arrange.getId());
            List<ArrangeMiddleGroup> arrangeMiddleGroups = arrangeMiddleGroupService.getArrangeGroupList(arrangeMiddleGroup);
            PageEntity page = new PageEntity();
            UserGroup userGroup = new UserGroup();
            page.setPageSize(9999);
            List<UserGroup> userGroupList = userGroupService.queryUserGroupListPage(userGroup, page);
            request.setAttribute("userGroupList", userGroupList);
            for (UserGroup _userGroup : userGroupList) {
                for (ArrangeMiddleGroup _arrangeMiddleGroup : arrangeMiddleGroups) {
                    if (_userGroup.getId() == _arrangeMiddleGroup.getUserGroupId()) {
                        _userGroup.setCheck(1);
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("AdminArrangeController.updateArrange", e);
        }
        return arrangeUpdate;
    }

    /**
     * 执行修改安排考试
     *
     * @param request
     * @param arrange
     * @return
     */
    @RequestMapping("/arrange/updateArrange")
    public String updateArrange(HttpServletRequest request, @ModelAttribute("arrange") Arrange arrange) {
        try {
            // 获取当前登录人
            String sysLoginName = SingletonLoginUtils.getLoginUserName(request);
            arrange.setReleasePeople(sysLoginName);
            arrange.setReleaseTime(new Date());
            arrangeService.updateArrange(arrange);
        } catch (Exception e) {
            logger.error("AdminArrangeController.updateArrange", e);
        }
        return "redirect:/admin/arrange/arrangeList";
    }

    /**
     * 单独更新安排考试状态  （发布）未被使用
     */
    @RequestMapping("/arrange/updateArrangeStatus/{id}")
    public String updateArrangeStatus(HttpServletRequest request, @ModelAttribute("arrange") Arrange arrange, @PathVariable("id") Long id) {
        try {
            arrange.setId(id);
            arrange.setStatus(ArrangeType.RELEASE.toString());
            arrangeService.updateArrangeStatus(arrange);
        } catch (Exception e) {
            logger.error("AdminArrangeController.updateArrangeStatus", e);
        }
        return "redirect:/admin/arrange/arrangeList";
    }

    /**
     * 作废安排考试
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/arrange/invalidArrange/{id}")
    public String invalidArrange(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            Arrange arrange = new Arrange();
            arrange.setId(id);
            arrange.setStatus(ArrangeType.DISUSE.toString());
            arrangeService.updateArrangeStatus(arrange);
        } catch (Exception e) {
            logger.error("AdminArrangeController.invalidArrange", e);
        }
        return "redirect:/admin/arrange/arrangeList";
    }

    /**
     * 考试成绩页面跳转
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/arrange/exam/papers/{id}")
    public ModelAndView getexampaper(HttpServletRequest request, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView(exampaper);
        try {
            modelAndView.addObject("id", id);
        } catch (Exception e) {
            logger.error("AdminUserController.getexampaper" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));

        }
        return modelAndView;
    }

    /**
     * 考试Ajax传值
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/arrange/web/papersajax")
    @ResponseBody
    public Map<String, Object> getWebpapersAjax(HttpServletRequest request, @RequestParam Long id) {
        Map<String, Object> json = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<ArrangeRecord> arrangerecord = arrangeRecordService.getArrangeRecorduserId(id);
            String date = "";
            for (int i = 0; i < arrangerecord.size(); i++) {
                date += sdf.format(arrangerecord.get(i).getSubmitTime()) + ",";
            }
            date = date.substring(0, date.length() - 1);
            json = this.getJsonMap(true, date, arrangerecord);
        } catch (Exception e) {
            logger.error("AdminUserController.getexampaper" + e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }


    /**
     * 部门成绩分数页面跳转
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/arrange/web/groudarrange/{groudid}/{arrangeid}")
    public ModelAndView getGroudarrange(HttpServletRequest request, @PathVariable Long groudid, @PathVariable Long arrangeid) {
        ModelAndView modelAndView = new ModelAndView(grouparrange);
        try {
            Object[][] arry = new Object[4][2];
            DecimalFormat df = new java.text.DecimalFormat("#.0");// 保留百分比一位小数
            ArrangeRecord arrangeRecord = new ArrangeRecord();
            //arrangeRecord.setUserGroupId(groudid);
            arrangeRecord.setArrangeId(arrangeid);
            List<ArrangeRecord> arrangeRecordlist = arrangeRecordService.getGroudArrange(arrangeRecord);
            List<ArrangeRecord> arrangeRecordGroudUser = arrangeRecordService.getArrangeRecordGroudUsers(arrangeRecord);
            String ArrangeTecord = "";
            Double arrangeRecordNum = (double) arrangeRecordlist.size();//考试人总数
            BigDecimal FialB = new BigDecimal(60);//<60分
            BigDecimal PassB = new BigDecimal(80);//60~79分
            BigDecimal GoodB = new BigDecimal(90);//80~89分
            BigDecimal NiceB = new BigDecimal(89);//>=90分
            Long fial = 0L;
            Long Pass = 0L;
            Long Good = 0L;
            Long Nice = 0L;
            for (int i = 0; i < arrangeRecordlist.size(); i++) {
                if (arrangeRecordlist.get(i).getScore().compareTo(FialB) == -1) {//当成绩<60时
                    fial++;
                }
                if (arrangeRecordlist.get(i).getScore().compareTo(FialB) == 1 &&
                        arrangeRecordlist.get(i).getScore().compareTo(PassB) == -1 ||
                        arrangeRecordlist.get(i).getScore().compareTo(FialB) == 0
                        ) {//当成绩>=60并且<80时
                    Pass++;
                }
                if (
                        arrangeRecordlist.get(i).getScore().compareTo(PassB) == 1 &&
                                arrangeRecordlist.get(i).getScore().compareTo(GoodB) == -1 ||
                                arrangeRecordlist.get(i).getScore().compareTo(PassB) == 0) {//当成绩>=80并且<90时
                    Good++;
                }
                if (arrangeRecordlist.get(i).getScore().compareTo(NiceB) == 1) {//当成绩>=90时
                    Nice++;
                }
            }
            Double[] Min = new Double[arrangeRecordlist.size()];//最低分
            for (int i = 0; i < arrangeRecordlist.size(); i++) {
                Min[i] = arrangeRecordlist.get(i).getScore().doubleValue();
            }
            for (int i = 0; i < arrangeRecordlist.size(); i++) {
                for (int j = 0; j < arrangeRecordlist.size(); j++) {
                    Double temp = 0.0;
                    if (Min[i] < Min[j]) {
                        temp = Min[j];
                        Min[j] = Min[i];
                        Min[i] = temp;
                    }
                }
            }

            Double[] Max = new Double[arrangeRecordlist.size()];//最高分
            for (int i = 0; i < arrangeRecordlist.size(); i++) {
                Max[i] = arrangeRecordlist.get(i).getScore().doubleValue();
            }
            for (int i = 0; i < arrangeRecordlist.size(); i++) {
                for (int j = 0; j < arrangeRecordlist.size(); j++) {
                    Double temp = 0.0;
                    if (Max[i] > Max[j]) {
                        temp = Max[j];
                        Max[j] = Max[i];
                        Max[i] = temp;
                    }
                }
            }

            Long number = UserGroupMiddleService.getUserGroupCount(groudid);//总人数
            Long attend = (long) arrangeRecordGroudUser.size();//实际参加人数
            Long notattend = number - attend;
            if (arrangeRecordNum > 0) {
                float fialPersent = Float.parseFloat(df.format((fial / arrangeRecordNum) * 100));
                float PassPersent = Float.parseFloat(df.format((Pass / arrangeRecordNum) * 100));
                float GoodPersent = Float.parseFloat(df.format((Good / arrangeRecordNum) * 100));
                float NicePersent = Float.parseFloat(df.format((Nice / arrangeRecordNum) * 100));

                float fialPayPersent = 100 - PassPersent - GoodPersent - NicePersent;
                float PassPayPersent = 100 - fialPersent - GoodPersent - NicePersent;
                float GoodPayPersent = 100 - fialPersent - PassPersent - NicePersent;
                float NicePayPersent = 100 - fialPersent - PassPersent - GoodPersent;
                arry[0][0] = "低于60分：" + fial + "人";
                arry[0][1] = Float.parseFloat(df.format(fialPayPersent));
                ;

                arry[1][0] = "高于60并小于80分：" + Pass + "人";
                arry[1][1] = Float.parseFloat(df.format(PassPayPersent));

                arry[2][0] = "高于80并小于90分：" + Good + "人";
                arry[2][1] = Float.parseFloat(df.format(GoodPayPersent));

                arry[3][0] = "高于90分：" + Nice + "人";
                arry[3][1] = Float.parseFloat(df.format(NicePayPersent));

                ArrangeTecord = gson.toJson(arry).toString();
            }
            modelAndView.addObject("ArrangeTecord", ArrangeTecord);// 绘图数据
            modelAndView.addObject("arrangeRecordGroudUser", arrangeRecordGroudUser);
            modelAndView.addObject("Max", Max != null && Max.length > 0 ? Max[0] : null);
            modelAndView.addObject("Min", Min != null && Min.length > 0 ? Min[0] : null);
            modelAndView.addObject("number", number);
            modelAndView.addObject("attend", attend);
            modelAndView.addObject("notattend", notattend);
        } catch (Exception e) {
            logger.error("AdminUserController.getGroudarrange" + e);
            return new ModelAndView(this.setExceptionRequest(request, e));

        }
        return modelAndView;
    }

    /**
     * 开启安排考试 发布
     *
     * @param id
     * @return
     */
    @RequestMapping("/arrange/startArrange/{id}")
    public ModelAndView startArrange(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("redirect:/admin/arrange/arrangeList");
        try {
            arrangeService.addStartArrange(id);
        } catch (Exception e) {
            logger.error("AdminUserController.startArrange" + e);
        }
        return mav;
    }

}
