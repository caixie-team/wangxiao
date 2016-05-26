package com.atdld.os.api.help.service;

import com.atdld.os.api.help.entity.HelpMenu;

import java.util.List;


public interface HelpMenuService {
    /**
     * 查询所有菜单
     *
     * @return HelpMenu
     */
    List<List<HelpMenu>> getHelpMenuAll();

    /**
     * 查询所有一级菜单
     *
     * @return HelpMenu
     */
    List<HelpMenu> getHelpMenuOne();

    /**
     * 根据一级菜单ID查询二级菜单
     *
     * @return HelpMenu
     */
    List<HelpMenu> getHelpMenuTwoByOne(Long id);

    /**
     * 删除菜单
     *
     * @param id
     */
    void delHelpMenuById(Long id);

    /**
     * 更新菜单
     *
     * @param HelpMenu
     */
    void updateHelpMenuById(HelpMenu HelpMenu);

    /**
     * 添加菜单
     *
     * @param HelpMenu
     * @return id
     */
    Long createHelpMenu(HelpMenu HelpMenu);

    /**
     * 根据ID查找菜单
     *
     * @param id
     * @return
     */
    HelpMenu getHelpMenuById(Long id);
}

