package io.wangxiao.commons.dao.impl;

import io.wangxiao.commons.dao.GuidDao;
import io.wangxiao.commons.entity.GuidItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName GuidDaoImpl
 * @description
 */
@Repository("guidDao")
public class GuidDaoImpl extends GenericDaoImpl implements GuidDao {

    /**
     * 获得GuidItem列表
     */
    @Override
    public List<GuidItem> getGuidItemByProject(String project) {
        return this.selectList("publicMapper.getsequence", project);
    }

    /**
     * 增加GuidItem
     */
    public void addGuidItem(GuidItem guidItem) {
        this.insert("publicMapper.addsequence", guidItem);
    }

    /**
     * 更新GuidItem
     */
    public void updateGuidItem(GuidItem guidItem) {
        this.update("publicMapper.updatesequence", guidItem);
    }

}
