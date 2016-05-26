package io.wangxiao.edu.home.dao.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.plan.Phase;
import io.wangxiao.edu.home.entity.plan.PhaseDTO;

import java.util.List;

public interface PhaseDao {

    /**
     * 添加学习阶段
     *
     * @param phase
     * @return
     */
    Long createPhase(Phase phase);

    /**
     * 删除
     *
     * @param id
     */
    void deletePhaseById(Long id);

    /**
     * 修改
     *
     * @param phase
     * @return
     */
    Long updatePhase(Phase phase);

    /**
     * 获得
     *
     * @param id
     * @return
     */
    Phase getPhaseById(Long id);

    /**
     * 获得
     *
     * @param phase
     * @return
     */
    Phase getPhase(Phase phase);

    /**
     * 获得
     *
     * @param phase
     * @return
     */
    List<Phase> getPhaseList(Phase phase);

    /**
     * 获得
     *
     * @param phase
     * @return
     */
    List<PhaseDTO> getPhaseDTOList(Phase phase, PageEntity page);
}