package io.wangxiao.edu.home.service.plan;


import io.wangxiao.edu.home.entity.plan.PhaseDetailProgress;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;

import java.util.List;

public interface PhaseDetailProgressService {

    /**
     * 添加学习阶段进度
     *
     * @param phaseDetailProgress
     * @return
     */
    Long createPhaseDetailProgress(PhaseDetailProgress phaseDetailProgress);

    /**
     * 批量添加学习进度
     *
     * @param progressList
     * @return
     */
    Long addProgressBatch(List<PhaseDetailProgress> progressList);

    /**
     * 根据id获取学习进度
     *
     * @param id
     * @return
     */
    PhaseDetailProgress getPhaseDetailProgressById(Long id);

    /**
     * 查询小组完成进度
     *
     * @param planMiddleGroup
     * @return
     */
    int getGroupComplete(PlanMiddleGroup planMiddleGroup);

    /**
     * 修改完成时间
     *
     * @param progress
     */
    void updateCompleteTime(PhaseDetailProgress progress);

    /**
     * 获取计划阶段详情信息
     *
     * @param progress
     * @return
     */
    List<PhaseDetailProgress> getPhaseDetailProgressList(PhaseDetailProgress progress);
}