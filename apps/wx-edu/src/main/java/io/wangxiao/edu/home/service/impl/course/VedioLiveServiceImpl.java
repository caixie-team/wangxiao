package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.course.VedioLiveDao;
import io.wangxiao.edu.home.entity.course.VedioLive;
import io.wangxiao.edu.home.service.course.VedioLiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * VedioLive管理接口
 */
@Service("vedioLiveService")
public class VedioLiveServiceImpl implements VedioLiveService {

    @Autowired
    private VedioLiveDao vedioLiveDao;

    /**
     * 添加VedioLive
     *
     * @param vedioLive 要添加的VedioLive
     * @return id
     */
    public java.lang.Long addVedioLive(VedioLive vedioLive) {
        vedioLive.setAddTime(new Date());
        vedioLive.setUpdateTime(new Date());
        return vedioLiveDao.addVedioLive(vedioLive);
    }

    /**
     * 根据id删除一个VedioLive
     *
     * @param id 要删除的id
     */
    public void deleteVedioLiveById(Long id) {
        vedioLiveDao.deleteVedioLiveById(id);
    }

    /**
     * 修改VedioLive
     *
     * @param vedioLive 要修改的VedioLive
     */
    public void updateVedioLive(VedioLive vedioLive) {
        vedioLive.setUpdateTime(new Date());
        vedioLiveDao.updateVedioLive(vedioLive);
    }

    /**
     * 根据id获取单个VedioLive对象
     *
     * @param id 要查询的id
     * @return VedioLive
     */
    public VedioLive getVedioLiveById(Long id) {
        return vedioLiveDao.getVedioLiveById(id);
    }

    /**
     * 根据条件获取VedioLive列表
     *
     * @param vedioLive 查询条件
     * @return List<VedioLive>
     */
    public List<VedioLive> getVedioLiveList(VedioLive vedioLive) {
        return vedioLiveDao.getVedioLiveList(vedioLive);
    }

    /**
     * 根据条件获取VedioLive列表分页
     *
     * @param vedioLive 查询条件
     */
    public List<VedioLive> queryVedioLiveListPage(VedioLive vedioLive, PageEntity page) {
        return vedioLiveDao.getVedioLiveListPage(vedioLive, page);
    }
}