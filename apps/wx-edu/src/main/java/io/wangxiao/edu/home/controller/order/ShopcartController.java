package io.wangxiao.edu.home.controller.order;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.contoller.OptLogController;
import io.wangxiao.edu.common.util.EhcacheKit;
import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.constants.web.OrderConstans;
import io.wangxiao.edu.home.entity.order.Shopcart;
import io.wangxiao.edu.home.service.order.ShopcartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShopcartController extends EduBaseController {

    private static Logger logger = LoggerFactory.getLogger(ShopcartController.class);

    // 购物车
    private static final String shopCart = getViewPath("/shopcart/shopCart");
    // 购物车
    private static final String showshopCartinfo = getViewPath("/shopcart/showCourseListinfo");
    // 头部购物车
    private static final String queryShopCartinfoHeader = getViewPath("/shopcart/queryShopCartinfoHeader");

    @Autowired
    private ShopcartService shopcartService;

    private EhcacheKit ehcache = EhcacheKit.getInstance();

    /**
     * 添加商品并跳转到购物车
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shopcart", params = "command=addShopitem")
    public String addShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {

            String goodsId = request.getParameter("goodsid");// 商品id
            String type = request.getParameter("type");// 类型1课程2套餐
            logger.info("++++++++++goodsId=" + goodsId + "++++++++++type=" + type);
            if (StringUtils.isNotEmpty(goodsId) && StringUtils.isNotEmpty(type)) {
                if (isLogin(request)) {
                    // 登录用户添加到数据库
                    shopcartService.addShopcart(Long.valueOf(goodsId), Long.valueOf(type), getLoginUserId(request));
                } else {
                    // 从Cookie获取购物车信息
                    String shopJson = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
                    shopJson = shopcartService.addTempShopcart(Long.valueOf(goodsId), Long.valueOf(type), shopJson);
                    WebUtils.setCookie(response, OrderConstans.SHOP_CART, shopJson, 1);
                }
                // 日志打印
                OptLogController.dolog(request, "addShopcart", "goodsId:" + goodsId + ",type:" + type);
            }
        } catch (Exception e) {
            logger.error("addShopcart error", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/shopcart?command=queryShopCart";

    }

    /**
     * 查询购物车（临时购物车,用户购物车）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/shopcart", params = "command=queryShopCart")
    public String queryShopCart(HttpServletRequest request, HttpServletResponse response) {
        return shopCart;
    }

    /**
     * 查询购物车（临时购物车,用户购物车）
     *
     * @param request
     * @param response
     * @param type     添加到购物车中的类型 1课程 2（备用)
     * @return
     */
    @RequestMapping(value = "/shopcart/ajax/queryShopCartinfo")
    public String queryShopCartinfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long type) {
        try {
            List<Shopcart> shopCartList = null;
            if (isLogin(request)) {
                shopCartList = shopcartService.getShopcartList(getLoginUserId(request), type);
            } else {
                String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
                shopCartList = shopcartService.getTempShopcartList(json);
            }
            request.setAttribute("shopcartList", shopCartList);
        } catch (Exception e) {
            logger.error("queryShopCart error", e);
        }
        return showshopCartinfo;
    }

    /**
     * 头部查询购物车（临时购物车,用户购物车）
     */
    @RequestMapping(value = "/shopcart/ajax/headerShopCartinfo")
    public String queryShopCartinfoHeader(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long type) {
        try {
            List<Shopcart> shopCartList = null;
            if (isLogin(request)) {
                shopCartList = shopcartService.getShopcartList(getLoginUserId(request), type);
            } else {
                String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
                shopCartList = shopcartService.getTempShopcartList(json);
            }
            request.setAttribute("shopcartList", shopCartList);
        } catch (Exception e) {
            logger.error("queryShopCart error", e);
        }
        return queryShopCartinfoHeader;
    }

    /**
     * 删除购物车的一个Item ,Ajax 完成实时的 操作
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shopcart/ajax/deleteShopitem")
    @ResponseBody
    public Map<String, Object> deleteShopItem(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        String goodsId = request.getParameter("goodsid");// 商品id
        String type = request.getParameter("type");// 类型1课程2套餐

        try {
            if (isLogin(request)) {
                shopcartService.deleteShopcartById(id, getLoginUserId(request));
            } else {
                String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
                json = shopcartService.deleteTempShopcart(Long.valueOf(goodsId), Long.valueOf(type), json);
                if (json == null) {
                    json = "";
                }
                WebUtils.setCookie(response, OrderConstans.SHOP_CART, json, 1);
            }
            map.put("message", "success");
        } catch (Exception e) {
            logger.error("deleteShopItem error", e);
            map.put("message", "false");
            return map;
        }
        return map;
    }

    /**
     * 根据type清空购物车
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/shopcart/clearShopitem/{type}")
    @ResponseBody
    public Map<String, Object> clearShopitem(@PathVariable Long type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> json = null;
        try {
            Long userId = getLoginUserId(request);
            if (ObjectUtils.isNotNull(userId)) {
                shopcartService.deleteShopcartByType(type, userId);
                json = this.getJsonMap(true, "", null);
            }
        } catch (Exception e) {
            logger.error("deleteShopItem error", e);
            json = this.getJsonMap(false, "", null);
        }
        return json;
    }

    /**
     * 查询购物车（临时购物车,用户购物车）
     *
     * @param request
     * @param response
     * @param type     添加到购物车中的类型 1课程 2（备用)
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/shopcart/ajax/shopcartnums")
    @ResponseBody
    public Object queryShopCartnums(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long type) {
        Map<String, Object> json = null;
        try {

            List<Shopcart> shopCartList = null;
            if (isLogin(request)) {
                //ehcache获取信息
                shopCartList = (List<Shopcart>) ehcache.get("shopcartnums");
                if (ObjectUtils.isNull(shopCartList)) {
                    shopCartList = shopcartService.getShopcartList(getLoginUserId(request), type);
                    ehcache.put("shopcartnums", shopCartList);
                }
            } else {
                String _json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
                shopCartList = shopcartService.getTempShopcartList(_json);
            }
            json = this.getJsonMap(true, null, shopCartList);
        } catch (Exception e) {
            json = this.getJsonMap(true, null, null);
        }
        return json;
    }

}
