package io.wangxiao.edu.home.dao.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.PhaseDetail;
import io.wangxiao.edu.home.entity.plan.PhaseDetailDTO;

import java.util.List;

public interface PhaseDetailDao {

    /**
     * 添加学习阶段
     *
     * @param phaseDetailList
     * @return
     */
    void addPhaseDetailList(List<PhaseDetail> phaseDetailList);

    /**
     * 删除
     *
     * @param id
     */
    void deletePhaseDetailById(Long id);

    /**
     * 删除
     *
     * @param id
     */
    void deletePhaseDetailByPhaseId(Long id);

    /**
     * 修改
     *
     * @param phaseDetail
     * @return
     */
    Long updatePhaseDetail(PhaseDetail phaseDetail);

    /**
     * @param id
     * @return
     */
    PhaseDetail getPhaseDetailById(Long id);

    /**
     * @param phaseDetail
     * @return
     */
    PhaseDetail getPhaseDetail(PhaseDetail phaseDetail);

    /**
     * 获得
     *
     * @param phaseDetail
     * @return
     */
    List<PhaseDetail> getPhaseDetailList(PhaseDetail phaseDetail);

    /**
     * 获取详细进度
     *
     * @param phaseId 阶段id
     * @param userId  用户id
     * @return
     */
    List<PhaseDetailDTO> getPhaseDetailDTOByPhaseIdAndUserId(Long phaseId, Long userId);

    /**
     * 获得
     *
     * @param phaseDetail
     * @return
     */
    List<PhaseDetailDTO> getPhaseDetailDTOList(PhaseDetail phaseDetail, PageEntity page);

    /**
     * 获取阶段总时长
     *
     * @param phaseDetail
     * @return
     */
    Long getPhaseTotalTime(PhaseDetail phaseDetail);
}