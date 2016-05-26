package io.wangxiao.edu.sysuser.controller;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.sysuser.entity.SysUserOptRecord;
import io.wangxiao.edu.sysuser.service.SysUserOptRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SysUserOptRecordController extends SysBaseController {

    private static final Logger logger = LoggerFactory.getLogger(SysUserOptRecordController.class);

    @Autowired
    private SysUserOptRecordService sysUserOptRecordService;

    // 路径
    private String toOptRecordList = getViewPath("/sysuser/sysUserOptRecord_list");
    private String toOptRecordInfo = getViewPath("/sysuser/sysUserOptRecordInfo");

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("optRecord")
    public void initBinderSysUserOptRecord(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("optRecord.");
    }


    @RequestMapping("/optRecord/getOptRecordList")
    public String getOptRecordList(Model model, @ModelAttribute("optRecord") SysUserOptRecord optRecord, @ModelAttribute("page") PageEntity page) {
        try {
            List<SysUserOptRecord> recordList = sysUserOptRecordService.getRecordList(optRecord, page);
            model.addAttribute("recordList", recordList);
            model.addAttribute("page", page);
        } catch (Exception e) {
            logger.error("SysUserOptRecordController.getOptRecordList", e);
        }
        return toOptRecordList;
    }

    @RequestMapping("/optRecord/getOptRecord/{id}")
    public String getOptRecord(Model model, @PathVariable("id") Long id) {
        try {
            if (ObjectUtils.isNotNull(id)) {
                SysUserOptRecord optRecord = sysUserOptRecordService.getRecordById(id);
                model.addAttribute("optRecord", optRecord);
            }
        } catch (Exception e) {
            logger.error("SysUserOptRecordController.getOptRecord", e);
        }
        return toOptRecordInfo;
    }

}
