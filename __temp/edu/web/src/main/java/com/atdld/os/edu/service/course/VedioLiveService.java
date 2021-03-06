package com.atdld.os.edu.service.course;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.course.CourseDto;
import com.atdld.os.edu.entity.course.QueryCourse;
import com.atdld.os.edu.entity.course.VedioLive;

/**
 * VedioLive管理接口
 * User:
 * Date: 2014-05-27
 */
public interface VedioLiveService {

    /**
     * 添加VedioLive
     * @param vedioLive 要添加的VedioLive
     * @return id
     */
    public java.lang.Long addVedioLive(VedioLive vedioLive);

    /**
     * 根据id删除一个VedioLive
     * @param id 要删除的id
     */
    public void deleteVedioLiveById(Long id);

    /**
     * 修改VedioLive
     * @param vedioLive 要修改的VedioLive
     */
    public void updateVedioLive(VedioLive vedioLive);

    /**
     * 根据id获取单个VedioLive对象
     * @param id 要查询的id
     * @return VedioLive
     */
    public VedioLive getVedioLiveById(Long id);

    /**
     * 根据条件获取VedioLive列表
     * @param vedioLive 查询条件
     * @return List<VedioLive>
     */
    public List<VedioLive> getVedioLiveList(VedioLive vedioLive);
    /**
     * 根据条件获取VedioLive列表分页
     * @param vedioLive 查询条件
     */
    public List<VedioLive> queryVedioLiveListPage(VedioLive vedioLive,PageEntity page);
}