package io.wangxiao.edu.home.controller.card;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.DateUtils;
import io.wangxiao.edu.common.contoller.SingletonLoginUtils;
import io.wangxiao.edu.common.util.FileExportImportUtil;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.enums.CardStatus;
import io.wangxiao.edu.home.controller.course.AdminCourseAssessController;
import io.wangxiao.edu.home.entity.card.*;
import io.wangxiao.edu.home.service.card.CardCodeService;
import io.wangxiao.edu.home.service.card.CardService;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminCourseCardController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCourseAssessController.class);

    private String toCourseCardList = getViewPath("/admin/card/admin_card_list");// 课程卡列表
    private String toCreateCard = getViewPath("/admin/card/card_create");// 创建课程卡页面
    private String toCourseMainList = getViewPath("/admin/card/main_card_list");

    private String toCreateUserCard = getViewPath("/admin/card/card_user_create");// 创建学员卡

    @Autowired
    private CardService cardService;
    @Autowired
    private CardCodeService cardCodeService;
    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("card")
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("card.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCardCode")
    public void initBinderCardCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCardCode.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryMainCard")
    public void initBinderMainCard(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryMainCard.");
    }

    /**
     * 页面跳转
     *
     * @return
     */
    @RequestMapping("/card/tocreatecard")
    public ModelAndView toCreateCard(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(toCreateCard);
        System.out.println(request.getParameter("addtype"));
        modelAndView.addObject("addType", request.getParameter("addtype"));
        return modelAndView;
    }

    /**
     * 创建课程卡
     *
     * @return
     */
    @RequestMapping("/card/createcard")
    public String createCourseCard(HttpServletRequest request, @ModelAttribute("card") Card card) {
        try {
            String courseIds = request.getParameter("courseIds");
            String sysLoginLoginName = SingletonLoginUtils.getLoginUserName(request);
            card.setCreateTime(new Date());
            card.setCreateUser(sysLoginLoginName);
            // 创建课程卡操作
            cardService.saveCourseCardInfo(card, courseIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createCourseCard" + e);
        }
        return "redirect:/admin/card/cardlist"; // 重定向
    }

    /**
     * 课程卡列表
     *
     * @return
     */
    @RequestMapping("/card/cardlist")
    public ModelAndView courseCardList(HttpServletRequest reqeust, @ModelAttribute("page") PageEntity page, @ModelAttribute QueryCardCode queryCardCode) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(toCourseCardList);
        try {
            // 设置分页 ，默认每页10
            List<CourseCardDTO> cardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
            modelAndView.addObject("cardCodeList", cardCodeList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CardMainAction.saveCourseCardInfo", e);

        }
        return modelAndView;
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping("/card/closemaincard/{id}")
    public Map<String, Object> closeMainCard(HttpServletRequest request, @PathVariable("id") Long id) {
        Map<String, Object> json = null;
        try {
            CardCode beforeRecord = cardCodeService.getCardCodeById(id);
            cardCodeService.closeMainCard(id);
            CardCode afterRecord = cardCodeService.getCardCodeById(id);
            String typeStr = "";
            if (beforeRecord.getType() == 1) {
                typeStr = "课程卡";
            } else if (beforeRecord.getType() == 2) {
                typeStr = "充值卡";
            } else if (beforeRecord.getType() == 3) {
                typeStr = "学员卡";
            }
            SysUserOptRecord record = new SysUserOptRecord(request, typeStr + "编码作废", "课程卡编码表-" + id, beforeRecord, afterRecord);
            if (record != null) sysUserOptRecordService.addRecord(record);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 课程卡主卡信息列表
     *
     * @param request
     * @param page
     * @param queryMainCard
     * @return
     */
    @RequestMapping("/card/mainlist")
    public ModelAndView courseCardMainList(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute QueryMainCard queryMainCard) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(toCourseMainList);
        try {
            List<MainCardDTO> mainCardList = cardService.getMianListByCondition(queryMainCard, page);
            modelAndView.addObject("mainCardList", mainCardList);
            modelAndView.addObject("pagew", page);
        } catch (Exception e) {
            logger.error("courseCardMainList", e);
        }
        return modelAndView;
    }

    /**
     * 课程卡状态修改
     *
     * @param request
     * @return
     */
    @RequestMapping("/card/closecard")
    @ResponseBody
    public Map<String, Object> updateCardCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Long cardCodeId = new Long(request.getParameter("id"));
            CardCode cardCode = cardCodeService.getCardCodeById(cardCodeId);
            cardCode.setStatus(CardStatus.CLOSE.toString());
            CardCode beforeRecord = cardCodeService.getCardCodeById(cardCodeId);
            cardCodeService.updateCardCode(cardCode);
            CardCode afterRecord = cardCodeService.getCardCodeById(cardCodeId);
            String typeStr = "";
            if (cardCode.getType() == 1) {
                typeStr = "课程卡";
            } else if (cardCode.getType() == 2) {
                typeStr = "充值卡";
            } else if (cardCode.getType() == 3) {
                typeStr = "学员卡";
            }
            SysUserOptRecord record = new SysUserOptRecord(request, typeStr + "作废", "课程卡记录表-" + cardCodeId, beforeRecord, afterRecord);
            if (record != null) sysUserOptRecordService.addRecord(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 课程卡信息导出
     */
    @RequestMapping("/card/exportCard")
    public void exportCard(HttpServletRequest request, HttpServletResponse response, @ModelAttribute QueryCardCode queryCardCode) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/excelfile/order");
            // 文件名
            String expName = "线下卡_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "名称", "类型", "金额", "有效开始时间", "有效结束时间", "卡号", "密码", "状态", "订单号", "用户邮箱", "创建时间", "创建人"};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            cardService.getCardListByCondtion(queryCardCode, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<CourseCardDTO> cardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
                List<List<String>> list = cardJoint(cardCodeList);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 卡编码excel格式拼接
     *
     * @param cardCodeList
     * @return
     */
    public List<List<String>> cardJoint(List<CourseCardDTO> cardCodeList) {
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < cardCodeList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(cardCodeList.get(i).getId() + "");
            small.add(cardCodeList.get(i).getName());
            if (cardCodeList.get(i).getType() == 1) {
                small.add("课程卡");
            } else if (cardCodeList.get(i).getType() == 2) {
                small.add("充值卡");
            } else if (cardCodeList.get(i).getType() == 3) {
                small.add("学员卡");
            }

            small.add(cardCodeList.get(i).getMoney() + "");
            if (cardCodeList.get(i).getType() == 1 || cardCodeList.get(i).getType() == 2) {
                small.add(format.format(cardCodeList.get(i).getBeginTime()));
                small.add(format.format(cardCodeList.get(i).getEndTime()));
            } else {
                small.add("~");
                small.add("~");
            }
            small.add(cardCodeList.get(i).getCardCode());
            small.add(cardCodeList.get(i).getCardCodePassword());
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.INIT.toString())) {
                small.add("未使用");
            }
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.USED.toString())) {
                small.add("已使用");
            }
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.OVERDUE.toString())) {
                small.add("已过期");
            }
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.CLOSE.toString())) {
                small.add("已关闭");
            }
            small.add(cardCodeList.get(i).getRequestId());
            small.add(cardCodeList.get(i).getEmail());
            small.add(format.format(cardCodeList.get(i).getCreateTime()));
            small.add(cardCodeList.get(i).getCreateUser());

            list.add(small);
        }
        return list;
    }

    /**
     * 页面学员卡添加
     */
    @RequestMapping("/card/tocreatUserecard")
    public String toCreateUserCard(HttpServletRequest request) {
        return toCreateUserCard;
    }

    /**
     * 创建学员卡
     *
     * @return
     */
    @RequestMapping("/card/createUsercard")
    @ResponseBody
    public Map<String, Object> createCourseUserCard(HttpServletRequest request, @ModelAttribute("card") Card card) {
        Map<String, Object> json = null;
        try {
            String courseIds = request.getParameter("courseIds");
            String sysLoginLoginName = SingletonLoginUtils.getLoginUserName(request);
            card.setCreateTime(new Date());
            card.setCreateUser(sysLoginLoginName);
            // 创建学员卡操作
            cardService.addCourseUserCard(card, courseIds);
            json = this.getJsonMap(true, "", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createCourseUserCard" + e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }
}
