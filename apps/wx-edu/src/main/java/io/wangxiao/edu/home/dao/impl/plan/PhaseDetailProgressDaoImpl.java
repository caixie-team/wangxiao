package io.wangxiao.edu.home.dao.impl.plan;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.plan.PhaseDetailProgressDao;
import io.wangxiao.edu.home.entity.plan.PhaseDetailProgress;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("PhaseDetailProgressDao")
public class PhaseDetailProgressDaoImpl extends GenericDaoImpl implements PhaseDetailProgressDao {


    @Override
    public Long createPhaseDetailProgress(PhaseDetailProgress phaseDetailProgress) {
        return this.insert("PhaseDetailProgressMapper.createPhaseDetailProgress", phaseDetailProgress);
    }

    @Override
    public Long addProgressBatch(List<PhaseDetailProgress> progressList) {
        return this.insert("PhaseDetailProgressMapper.addProgressBatch", progressList);
    }

    @Override
    public PhaseDetailProgress getPhaseDetailProgress(PhaseDetailProgress progress) {
        return this.selectOne("PhaseDetailProgressMapper.getPhaseDetailProgress", progress);
    }

    @Override
    public PhaseDetailProgress getPhaseDetailProgressById(Long id) {
        return this.selectOne("PhaseDetailProgressMapper.getPhaseDetailProgressById", id);
    }

    @Override
    public void updateCompleteTime(PhaseDetailProgress progress) {
        this.update("PhaseDetailProgressMapper.updateCompleteTime", progress);
    }

    @Override
    public int getGroupComplete(PlanMiddleGroup planMiddleGroup) {
        return this.selectOne("PhaseDetailProgressMapper.getGroupComplete", planMiddleGroup);
    }

    @Override
    public int getPhaseCompleteTime(Long id) {
        return this.selectOne("PhaseDetailProgressMapper.getPhaseCompleteTime", id);
    }

    @Override
    public int getPhaseDetailCompleteTime(Long id) {
        return this.selectOne("PhaseDetailProgressMapper.getPhaseDetailCompleteTime", id);
    }

    @Override
    public List<PhaseDetailProgress> getPhaseDetailProgressByPhaseIdAndUserId(Long phaseId, Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("phaseId", phaseId);
        map.put("userId", userId);
        return this.selectList("PhaseDetailProgressMapper.getPhaseDetailProgressByPhaseIdAndUserId", map);
    }

    @Override
    public List<PhaseDetailProgress> getPhaseDetailProgressList(PhaseDetailProgress progress) {
        return this.selectList("PhaseDetailProgressMapper.getPhaseDetailProgressList", progress);
    }
}
