package io.wangxiao.edu.home.controller.member;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminMemberTypeController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminMemberTypeController.class);
    @Autowired
    private MemberTypeService memberTypeService;
    private static final String getMemberTypes = getViewPath("/admin/member/member_type_list");
    private static final String updateMemberType = getViewPath("/admin/member/member_type_update");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("memberType")
    public void initBinderMemberType(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberType.");
    }

    /**
     * 会员类型列表
     *
     * @return
     */
    @RequestMapping("/membertype/list")
    public ModelAndView getMemberTypes(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getMemberTypes);
        try {
            List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
            modelAndView.addObject("memberTypeList", memberTypeList);
        } catch (Exception e) {
            logger.error("AdminMemberController.getMemberTypes--会员类型列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 跳转更新会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/doupdate/{id}")
    public ModelAndView doUpdateMemberType(HttpServletRequest request, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateMemberType);
        try {
            MemberType memberType = memberTypeService.getMemberTypeById(id);
            modelAndView.addObject("memberType", memberType);
        } catch (Exception e) {
            logger.error("AdminMemberController.doUpdateMemberType--跳转更新会员类型出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/update")
    public String updateMemberType(HttpServletRequest request, MemberType memberType) {
        try {
            memberTypeService.updateMemberType(memberType);
        } catch (Exception e) {
            logger.error("AdminMemberController.updateMemberType--更新会员类型出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/membertype/list";
    }

    /**
     * 停用启用会员类型
     *
     * @return
     */
    @RequestMapping("/membertype/status")
    @ResponseBody
    public Map<String, Object> updateMemberTypeStatus(HttpServletRequest request, MemberType memberType) {
        Map<String, Object> json = null;
        try {
            memberTypeService.updateMemberTypeStatus(memberType);
            json = this.getJsonMap(true, "true", null);
            ;
        } catch (Exception e) {
            logger.error("AdminMemberController.addMemberType--更改会员类型状态出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

}