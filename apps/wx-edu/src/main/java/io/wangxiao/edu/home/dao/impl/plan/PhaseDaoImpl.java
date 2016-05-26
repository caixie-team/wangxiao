package io.wangxiao.edu.home.dao.impl.plan;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PhaseDao;
import io.wangxiao.edu.home.entity.plan.Phase;
import io.wangxiao.edu.home.entity.plan.PhaseDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("phaseDao")
public class PhaseDaoImpl extends GenericDaoImpl implements PhaseDao {
    public Long createPhase(Phase phase) {
        return this.insert("PhaseMapper.createPhase", phase);
    }

    public void deletePhaseById(Long id) {
        this.delete("PhaseMapper.deletePhaseById", id);
    }

    public Long updatePhase(Phase phase) {
        return this.update("PhaseMapper.updatePhase", phase);
    }

    public Phase getPhaseById(Long id) {
        return this.selectOne("PhaseMapper.getPhaseById", id);
    }

    public Phase getPhase(Phase phase) {
        return this.selectOne("PhaseMapper.getPhase", phase);
    }

    public List<Phase> getPhaseList(Phase phase) {
        return this.selectList("PhaseMapper.getPhaseList", phase);
    }

    public List<PhaseDTO> getPhaseDTOList(Phase phase, PageEntity page) {
        return this.queryForListPage("PhaseMapper.getPhaseDTOList", phase, page);
    }
}
