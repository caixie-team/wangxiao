package io.wangxiao.edu.home.service.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.course.VedioLive;

import java.util.List;

/**
 * VedioLive管理接口
 */
public interface VedioLiveService {

    /**
     * 添加VedioLive
     *
     * @param vedioLive 要添加的VedioLive
     * @return id
     */
    java.lang.Long addVedioLive(VedioLive vedioLive);

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
    List<VedioLive> queryVedioLiveListPage(VedioLive vedioLive, PageEntity page);
}