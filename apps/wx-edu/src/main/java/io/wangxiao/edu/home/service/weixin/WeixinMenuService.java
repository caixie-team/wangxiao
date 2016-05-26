package io.wangxiao.edu.home.service.weixin;


import io.wangxiao.edu.home.entity.weixin.WeixinMenu;

import java.util.List;


public interface WeixinMenuService {

    /**
     * 获取自定义菜单一级菜单集合
     *
     * @return 一级菜单集合
     */
    List<WeixinMenu> getWeixinFirstMenu();

    /**
     * 根据一级菜单ID获取自定义菜单二级菜单集合
     *
     * @return 二级菜单集合
     */
    List<WeixinMenu> getWeixinSecondMenuByParentId(Long id);

    /**
     * 获取自定义菜单启用一级菜单集合
     *
     * @return 一级菜单集合
     */
    List<WeixinMenu> getWeixinFirstEnableMenu();

    /**
     * 根据一级菜单ID获取自定义菜单启用二级菜单集合
     *
     * @return 二级菜单集合
     */
    List<WeixinMenu> getWeixinSecondEnableMenuByParentId(Long id);

    /**
     * 根据ID删除菜单及子菜单
     */
    void delWeixinMenuById(Long id);

    /**
     * 添加菜单
     */
    Long addWeixinMenu(WeixinMenu weixinMenu);

    /**
     * 更新菜单
     */
    void updateWeixinMenu(WeixinMenu weixinMenu);
}


