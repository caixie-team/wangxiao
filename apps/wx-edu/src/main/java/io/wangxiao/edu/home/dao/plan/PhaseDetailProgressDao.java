package io.wangxiao.edu.home.dao.plan;


import io.wangxiao.edu.home.entity.plan.PhaseDetailProgress;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;

import java.util.List;

public interface PhaseDetailProgressDao {

    /**
     * 添加学习阶段进度
     *
     * @param phaseDetailProgress
     * @return
     */
    Long createPhaseDetailProgress(PhaseDetailProgress phaseDetailProgress);

    /**
     * 批量添加学习阶段
     *
     * @param progressList
     * @return
     */
    Long addProgressBatch(List<PhaseDetailProgress> progressList);

    /**
     * 获取学习进度
     *
     * @param progress
     * @return
     */
    PhaseDetailProgress getPhaseDetailProgress(PhaseDetailProgress progress);

    /**
     * 根据id获取学习进度
     *
     * @param id
     * @return
     */
    PhaseDetailProgress getPhaseDetailProgressById(Long id);

    /**
     * 更新完成时长
     *
     * @param progress
     */
    void updateCompleteTime(PhaseDetailProgress progress);

    /**
     * 查询小组完成进度
     *
     * @param planMiddleGroup
     * @return
     */
    int getGroupComplete(PlanMiddleGroup planMiddleGroup);

    /**
     * 获取学习阶段完成时间
     *
     * @param id
     * @return
     */
    int getPhaseCompleteTime(Long id);

    /**
     * 获取课程进度集合
     *
     * @return
     */
    int getPhaseDetailCompleteTime(Long id);


    /**
     * 获取阶段详细信息
     *
     * @param phaseId 阶段id
     * @param userId  用户id
     * @return
     */
    List<PhaseDetailProgress> getPhaseDetailProgressByPhaseIdAndUserId(Long phaseId, Long userId);


    /**
     * 获取计划阶段详情信息
     *
     * @param progress
     * @return
     */
    List<PhaseDetailProgress> getPhaseDetailProgressList(PhaseDetailProgress progress);
}