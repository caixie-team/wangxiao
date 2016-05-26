package io.wangxiao.edu.home.service.impl.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.plan.PhaseDao;
import io.wangxiao.edu.home.dao.plan.PhaseDetailDao;
import io.wangxiao.edu.home.dao.plan.PhaseDetailProgressDao;
import io.wangxiao.edu.home.dao.plan.PlanDao;
import io.wangxiao.edu.home.entity.plan.*;
import io.wangxiao.edu.home.service.plan.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("phaseService")
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseDao phaseDao;
    @Autowired
    private PlanDao planDao;
    @Autowired
    private PhaseDetailDao phaseDetailDao;
    @Autowired
    private PhaseDetailProgressDao phaseDetailProgressDao;

    @Override
    public Long createPhase(Phase phase) {
        return phaseDao.createPhase(phase);
    }

    @Override
    public void deletePhaseById(Long id) {
        phaseDao.deletePhaseById(id);
    }

    @Override
    public Long updatePhase(Phase phase) {
        return phaseDao.updatePhase(phase);
    }

    @Override
    public Phase getPhaseById(Long id) {
        return phaseDao.getPhaseById(id);
    }

    @Override
    public Phase getPhase(Phase phase) {
        return phaseDao.getPhase(phase);
    }

    @Override
    public List<Phase> getPhaseList(Phase phase) {
        return phaseDao.getPhaseList(phase);
    }

    @Override
    public List<PhaseDTO> getPhaseDTOList(Phase phase, PageEntity page) {

        List<PhaseDTO> phaseDTOList = phaseDao.getPhaseDTOList(phase, page);

        // 获取计划信息
        Plan plan = planDao.getPlanById(phase.getPlanId());

        if(ObjectUtils.isNotNull(phaseDTOList)){
            for(PhaseDTO phaseDTO:phaseDTOList){
                PhaseDetailProgress progress = new PhaseDetailProgress();
                progress.setPhaseId(phaseDTO.getId());
                // 阶段总完成时间
                int phaseCompleteTime = phaseDetailProgressDao.getPhaseCompleteTime(phaseDTO.getId());
                phaseDTO.setComplete(phaseCompleteTime);
                // 总人数
                phaseDTO.setPeopleNum(plan.getPeopleNum());
            }
        }
        return phaseDTOList;
    }

    @Override
    public List<PhaseDTO> getPhaseListByUserId(Long planId,Long userId){
        Phase phase = new Phase();
        phase.setPlanId(planId);
        PageEntity page = new PageEntity();
        page.setPageSize(9999);
        List<PhaseDTO> phaseList = this.phaseDao.getPhaseDTOList(phase,page);
        if(ObjectUtils.isNotNull(phaseList)){
            for(PhaseDTO _phase:phaseList){
                Long studyTimeNo = 0l;
                int complete = 0;
                int peopleNum = 1;
                List<PhaseDetailDTO> phaseDetailList = phaseDetailDao.getPhaseDetailDTOByPhaseIdAndUserId(_phase.getId(), userId);
                _phase.setPhaseDetailList(phaseDetailList);
                if(ObjectUtils.isNotNull(phaseDetailList)){
                    for (PhaseDetailDTO phaseDetail:phaseDetailList) {
                        studyTimeNo += phaseDetail.getHours();
                        complete += phaseDetail.getComplete();
                    }
                }
                _phase.setStudyTimeNo(studyTimeNo);
                _phase.setComplete(complete);
                _phase.setPeopleNum(peopleNum);
            }
        }
        return phaseList;
    }
}