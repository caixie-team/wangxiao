package io.wangxiao.edu.home.controller.user;


import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.controller.course.CourseController;
import io.wangxiao.edu.home.entity.user.UserAddress;
import io.wangxiao.edu.home.service.user.UserAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserAddressController extends EduBaseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    // 地址管理
    private String address = getViewPath("/ucenter/u-address-list");// 收货地址
    private String addAddress = getViewPath("/ucenter/u_address");//去添加收货地址页面

    @Autowired
    private UserAddressService userAddressService;

    @InitBinder("userAddress")
    public void initBinderuserAddress(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("userAddress.");
    }

    /**
     * 修改UserAddress
     *
     * @param userAddress 要修改的UserAddress
     */
    public void updateUserAddress(UserAddress userAddress) {
        userAddressService.updateUserAddress(userAddress);
    }

    /**
     * 查询收获地址
     */
    @RequestMapping("/uc/address")
    public String userAddress(HttpServletRequest request) {
        try {
            //查询我全部的收货地址
            UserAddress userAddress = new UserAddress();
            userAddress.setUserId(getLoginUserId(request));
            List<UserAddress> userAddressList = userAddressService.getUserAddressList(userAddress);
            request.setAttribute("userAddressList", userAddressList);
        } catch (Exception e) {
            logger.error("UserController.userAddress", e);
            return setExceptionRequest(request, e);
        }
        return address;
    }

    /**
     * 去添加收获地址页面
     */
    @RequestMapping("/uc/goUserAddress")
    public String goUserAddress(HttpServletRequest request) {
        try {
        } catch (Exception e) {
            logger.error("UserAddressController.");
        }
        return addAddress;
    }

    /**
     * 添加送货地址
     */
    @RequestMapping("/uc/address/add")
    public String addUserAddress(HttpServletRequest request, @ModelAttribute UserAddress userAddress) {
        try {
            if (ObjectUtils.isNull(userAddress.getId())) {
                //添加送货地址
                userAddress.setUserId(getLoginUserId(request));
                userAddress.setSendTime(1);
                userAddressService.addUserAddress(userAddress);
            } else {
                //修改送货地址
                userAddress.setUserId(getLoginUserId(request));
                userAddressService.updateUserAddress(userAddress);
            }

        } catch (Exception e) {
            logger.error("UserController.addUserAddress", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/uc/address";
    }

    /**
     * 删除送货地址
     */
    @RequestMapping("/uc/address/del/{id}")
    public String delUserAddress(HttpServletRequest request, @PathVariable Long id) {
        try {
            //删除送货地址
            userAddressService.deleteUserAddressById(id);
        } catch (Exception e) {
            logger.error("UserController.delUserAddress", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/uc/address";
    }

    /**
     * 设为常用地址
     */
    @RequestMapping("/uc/address/common/{id}")
    public String commonUserAddress(HttpServletRequest request, @PathVariable Long id) {
        try {
            //设为常用地址
            userAddressService.updateUserAddressById(id, getLoginUserId(request));
        } catch (Exception e) {
            logger.error("UserController.commonUserAddress", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/uc/address";
    }
}