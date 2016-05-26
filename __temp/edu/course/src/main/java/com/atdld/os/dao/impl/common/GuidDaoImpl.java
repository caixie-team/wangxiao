package com.atdld.os.dao.impl.common;

import com.atdld.os.dao.common.GuidDao;
import com.atdld.os.entity.GuidItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @ClassName com.supergenius.sns.dao.impl.common.GuidDaoImpl
 * @description
 * @author :
 * @Create Date : 2013-12-18 下午7:37:25
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
