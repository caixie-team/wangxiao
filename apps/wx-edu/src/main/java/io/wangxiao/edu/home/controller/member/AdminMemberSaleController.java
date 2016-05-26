package io.wangxiao.edu.home.controller.member;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.member.MemberSale;
import io.wangxiao.edu.home.entity.member.MemberSaleDTO;
import io.wangxiao.edu.home.entity.member.MemberType;
import io.wangxiao.edu.home.entity.member.QueryMemberSale;
import io.wangxiao.edu.home.service.member.MemberSaleService;
import io.wangxiao.edu.home.service.member.MemberTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminMemberSaleController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminMemberSaleController.class);
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private MemberSaleService memberSaleService;

    private static final String getMemberSales = getViewPath("/admin/member/member_sale_list");
    private static final String addMemberSale = getViewPath("/admin/member/member_sale_add");
    private static final String updateMemberSale = getViewPath("/admin/member/member_sale_update");

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("memberSale")
    public void initBindermemberSale(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberSale.");
    }

    @InitBinder("memberSaleDTO")
    public void initBinderMemberSaleDTO(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberSaleDTO.");
    }

    @InitBinder("queryMemberSale")
    public void initBinderQueryMemberSale(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryMemberSale.");
    }

    /**
     * 会员商品列表
     *
     * @return
     */
    @RequestMapping("/membersale/list")
    public ModelAndView getMemberSales(HttpServletRequest request, QueryMemberSale queryMemberSale, @ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getMemberSales);
        try {

            page.setPageSize(10);
            List<MemberSaleDTO> memberSaleList = memberSaleService.getMemberSalePage(queryMemberSale, page);
            modelAndView.addObject("memberSaleList", memberSaleList);
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes", memberTypes);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("AdminMemberController.getMemberSales--会员商品列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 跳转更新会员商品
     *
     * @return
     */
    @RequestMapping("/membersale/doupdate/{id}")
    public ModelAndView doUpdateMemberSale(HttpServletRequest request, @PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(updateMemberSale);
        try {
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            MemberSale memberSale = memberSaleService.getMemberSaleById(id);
            modelAndView.addObject("memberSale", memberSale);
            modelAndView.addObject("memberTypes", memberTypes);
        } catch (Exception e) {
            logger.error("AdminMemberController.doUpdateMemberSale--跳转更新会员商品出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 更新会员商品
     *
     * @return
     */
    @RequestMapping("/membersale/update")
    public String updateMemberSale(HttpServletRequest request, MemberSale memberSale) {
        try {
            memberSaleService.updateMemberSale(memberSale);
        } catch (Exception e) {
            logger.error("AdminMemberController.updateMemberSale--更新会员商品出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/membersale/list";
    }

    /**
     * 跳转添加会员商品
     *
     * @return
     */
    @RequestMapping("/membersale/doadd")
    public ModelAndView doAddMemberSale(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(addMemberSale);
        try {
            List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
            modelAndView.addObject("memberTypes", memberTypes);
        } catch (Exception e) {
            logger.error("AdminMemberController.doAddMemberSale--跳转添加会员商品出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 添加会员商品
     *
     * @return
     */
    @RequestMapping("/membersale/add")
    public String addMemberSale(HttpServletRequest request, MemberSale memberSale) {
        try {
            memberSaleService.addMemberSale(memberSale);
        } catch (Exception e) {
            logger.error("AdminMemberController.addMemberSale--添加会员商品出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/membersale/list";
    }

    /**
     * 删除会员商品
     *
     * @return
     */
    @RequestMapping("/membersale/del/{id}")
    @ResponseBody
    public Map<String, Object> delMemberSale(HttpServletRequest request, @PathVariable Long id) {
        Map<String, Object> json = null;
        try {
            memberSaleService.delMemberSale(id);
            json = this.getJsonMap(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminMemberController.addMemberSale--删除会员商品出错", e);
            json = this.getJsonMap(false, "error", null);
        }
        return json;
    }

}