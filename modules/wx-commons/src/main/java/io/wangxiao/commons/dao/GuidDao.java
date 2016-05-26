package io.wangxiao.commons.dao;

import io.wangxiao.commons.entity.GuidItem;

import java.util.List;

/**
 * 
 * @ClassName GuidDao
 * @description
 */
public interface GuidDao {
    /**
     * 根据字段获得唯一的id
     * 
     * @param project
     * @return
     */
    public List<GuidItem> getGuidItemByProject(String project);

    /**
     * 添加guidItem
     * 
     * @param guidItem
     * @return
     */
    public void addGuidItem(GuidItem guidItem);

    /**
     * 更新guidItem
     * 
     * @param guidItem
     * @return
     */
    public void updateGuidItem(GuidItem guidItem);

}
