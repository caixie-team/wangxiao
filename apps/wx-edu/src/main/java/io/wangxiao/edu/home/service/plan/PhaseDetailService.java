package io.wangxiao.edu.home.service.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.PhaseDetail;
import io.wangxiao.edu.home.entity.plan.PhaseDetailDTO;

import java.util.List;

public interface PhaseDetailService {

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
     * 获得
     *
     * @param phaseDetail
     * @return
     */
    List<PhaseDetailDTO> getPhaseDetailDTOList(PhaseDetail phaseDetail, PageEntity page);
}