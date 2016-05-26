package io.wangxiao.edu.home.dao.impl.plan;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.plan.PhaseDetailDao;
import io.wangxiao.edu.home.entity.plan.PhaseDetail;
import io.wangxiao.edu.home.entity.plan.PhaseDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("PhaseDetailDao")
public class PhaseDetailDaoImpl extends GenericDaoImpl implements PhaseDetailDao {

    @Override
    public void addPhaseDetailList(List<PhaseDetail> phaseDetailList) {
        this.insert("PhaseDetailMapper.addPhaseDetailList", phaseDetailList);
    }

    @Override
    public void deletePhaseDetailById(Long id) {
        this.delete("PhaseDetailMapper.deletePhaseDetailById", id);
    }

    @Override
    public void deletePhaseDetailByPhaseId(Long id) {
        this.delete("PhaseDetailMapper.deletePhaseDetailByPhaseId", id);
    }

    @Override
    public Long updatePhaseDetail(PhaseDetail phaseDetail) {
        return this.update("PhaseDetailMapper.updatePhaseDetail", phaseDetail);
    }

    @Override
    public PhaseDetail getPhaseDetailById(Long id) {
        return this.selectOne("PhaseDetailMapper.getPhaseDetailById", id);
    }

    @Override
    public PhaseDetail getPhaseDetail(PhaseDetail phaseDetail) {
        return this.selectOne("PhaseDetailMapper.getPhaseDetail", phaseDetail);
    }

    @Override
    public List<PhaseDetail> getPhaseDetailList(PhaseDetail phaseDetail) {
        return this.selectList("PhaseDetailMapper.getPhaseDetailList", phaseDetail);
    }

    @Override
    public List<PhaseDetailDTO> getPhaseDetailDTOByPhaseIdAndUserId(Long phaseId, Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("phaseId", phaseId);
        map.put("userId", userId);
        return this.selectList("PhaseDetailMapper.getPhaseDetailDTOByPhaseIdAndUserId", map);
    }

    @Override
    public List<PhaseDetailDTO> getPhaseDetailDTOList(PhaseDetail phaseDetail, PageEntity page) {
        return this.queryForListPage("PhaseDetailMapper.getPhaseDetailDTOList", phaseDetail, page);
    }

    @Override
    public Long getPhaseTotalTime(PhaseDetail phaseDetail) {
        return this.selectOne("PhaseDetailMapper.getPhaseTotalTime", phaseDetail);
    }
}
