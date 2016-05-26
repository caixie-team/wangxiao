package co.bluepx.edu.course.dao;

import co.bluepx.edu.core.BaseDao;
import co.bluepx.edu.core.entity.PageEntity;
import co.bluepx.edu.course.entity.VedioLive;

import java.util.List;

/**
 * VedioLive管理接口
 */
public interface VedioLiveDao extends BaseDao<VedioLive> {

    /**
     * 添加VedioLive
     *
     * @param vedioLive 要添加的VedioLive
     * @return id
     */
    Long addVedioLive(VedioLive vedioLive);

    /**
     * 根据id删除一个VedioLive
     *
     * @param id 要删除的id
     */
    void deleteVedioLiveById(Long id);

    /**
     * 修改VedioLive
     *
     * @param vedioLive 要修改的VedioLive
     */
    void updateVedioLive(VedioLive vedioLive);

    /**
     * 根据id获取单个VedioLive对象
     *
     * @param id 要查询的id
     * @return VedioLive
     */
    VedioLive getVedioLiveById(Long id);

    /**
     * 根据条件获取VedioLive列表
     *
     * @param vedioLive 查询条件
     * @return List<VedioLive>
     */
    List<VedioLive> getVedioLiveList(VedioLive vedioLive);

    /**
     * 根据条件获取VedioLive列表分页
     *
     * @param vedioLive 查询条件
     */
    List<VedioLive> getVedioLiveListPage(VedioLive vedioLive, PageEntity page);
}