package com.atdld.os.dao.common;


import com.atdld.os.entity.GuidItem;

import java.util.List;

/**
 * @author :
 * @ClassName com.supergenius.sns.dao.common.GuidDao
 * @description
 * @Create Date : 2013-12-18 下午7:37:29
 */
public interface GuidDao {
    /**
     * 根据字段获得唯一的id
     *
     * @param project
     * @return
     */
    List<GuidItem> getGuidItemByProject(String project);

    /**
     * 添加guidItem
     *
     * @param guidItem
     * @return
     */
    void addGuidItem(GuidItem guidItem);

    /**
     * 更新guidItem
     *
     * @param guidItem
     * @return
     */
    void updateGuidItem(GuidItem guidItem);

}
