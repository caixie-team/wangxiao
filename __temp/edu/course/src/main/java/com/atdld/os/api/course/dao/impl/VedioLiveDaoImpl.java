package com.atdld.os.api.course.dao.impl;

import java.util.List;

import com.atdld.os.edu.entity.course.VedioLive;
import com.atdld.os.edu.dao.course.VedioLiveDao;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;

/**
 *
 * VedioLive
 * User:
 * Date: 2014-05-27
 */
 @Repository("vedioLiveDao")
public class VedioLiveDaoImpl extends GenericDaoImpl implements VedioLiveDao{

    public Long addVedioLive(VedioLive vedioLive) {
        return this.insert("VedioLiveMapper.createVedioLive",vedioLive);
    }

    public void deleteVedioLiveById(Long id){
        this.delete("VedioLiveMapper.deleteVedioLiveById",id);
    }

    public void updateVedioLive(VedioLive vedioLive) {
        this.update("VedioLiveMapper.updateVedioLive",vedioLive);
    }

    public VedioLive getVedioLiveById(Long id) {
        return this.selectOne("VedioLiveMapper.getVedioLiveById",id);
    }

    public List<VedioLive> getVedioLiveList(VedioLive vedioLive) {
        return this.selectList("VedioLiveMapper.getVedioLiveList",vedioLive);
    }
    /**
     * 根据条件获取VedioLive列表分页
     * @param vedioLive 查询条件
     */
    public List<VedioLive> getVedioLiveListPage(VedioLive vedioLive,PageEntity page){
    	return queryForListPage("VedioLiveMapper.getVedioLiveListPage", vedioLive, page);
    }
}
