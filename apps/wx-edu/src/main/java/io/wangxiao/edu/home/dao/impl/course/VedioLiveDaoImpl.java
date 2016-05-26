package io.wangxiao.edu.home.dao.impl.course;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.VedioLiveDao;
import io.wangxiao.edu.home.entity.course.VedioLive;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * VedioLive
 * User:
 * Date: 2014-05-27
 */
@Repository("vedioLiveDao")
public class VedioLiveDaoImpl extends GenericDaoImpl implements VedioLiveDao {

    public java.lang.Long addVedioLive(VedioLive vedioLive) {
        return this.insert("VedioLiveMapper.createVedioLive", vedioLive);
    }

    public void deleteVedioLiveById(Long id) {
        this.delete("VedioLiveMapper.deleteVedioLiveById", id);
    }

    public void updateVedioLive(VedioLive vedioLive) {
        this.update("VedioLiveMapper.updateVedioLive", vedioLive);
    }

    public VedioLive getVedioLiveById(Long id) {
        return this.selectOne("VedioLiveMapper.getVedioLiveById", id);
    }

    public List<VedioLive> getVedioLiveList(VedioLive vedioLive) {
        return this.selectList("VedioLiveMapper.getVedioLiveList", vedioLive);
    }

    /**
     * 根据条件获取VedioLive列表分页
     *
     * @param vedioLive 查询条件
     */
    public List<VedioLive> getVedioLiveListPage(VedioLive vedioLive, PageEntity page) {
        return queryForListPage("VedioLiveMapper.getVedioLiveListPage", vedioLive, page);
    }
}
