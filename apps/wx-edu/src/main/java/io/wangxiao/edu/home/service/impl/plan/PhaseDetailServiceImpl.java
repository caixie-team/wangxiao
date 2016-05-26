package io.wangxiao.edu.home.service.impl.plan;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.plan.PhaseDetailDao;
import io.wangxiao.edu.home.dao.plan.PhaseDetailProgressDao;
import io.wangxiao.edu.home.entity.plan.Phase;
import io.wangxiao.edu.home.entity.plan.PhaseDetail;
import io.wangxiao.edu.home.entity.plan.PhaseDetailDTO;
import io.wangxiao.edu.home.entity.plan.Plan;
import io.wangxiao.edu.home.service.plan.PhaseDetailService;
import io.wangxiao.edu.home.service.plan.PhaseService;
import io.wangxiao.edu.home.service.plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("phaseDetailService")
public class PhaseDetailServiceImpl implements PhaseDetailService {

    @Autowired
    private PhaseDetailDao phaseDetailDao;
    @Autowired
    private PhaseDetailProgressDao phaseDetailProgressDao;
    @Autowired
    private PlanService planService;
    @Autowired
    private PhaseService phaseService;

    @Override
    public void addPhaseDetailList(List<PhaseDetail> phaseDetailList) {
        this.phaseDetailDao.addPhaseDetailList(phaseDetailList);
    }

    @Override
    public void deletePhaseDetailById(Long id) {
        phaseDetailDao.deletePhaseDetailById(id);
    }

    @Override
    public void deletePhaseDetailByPhaseId(Long id) {
        phaseDetailDao.deletePhaseDetailByPhaseId(id);
    }

    @Override
    public Long updatePhaseDetail(PhaseDetail phaseDetail) {
        return phaseDetailDao.updatePhaseDetail(phaseDetail);
    }

    @Override
    public PhaseDetail getPhaseDetailById(Long id) {
        return phaseDetailDao.getPhaseDetailById(id);
    }

    @Override
    public PhaseDetail getPhaseDetail(PhaseDetail phaseDetail) {
        return phaseDetailDao.getPhaseDetail(phaseDetail);
    }

    @Override
    public List<PhaseDetail> getPhaseDetailList(PhaseDetail phaseDetail) {
        return phaseDetailDao.getPhaseDetailList(phaseDetail);
    }

    @Override
    public List<PhaseDetailDTO> getPhaseDetailDTOList(PhaseDetail phaseDetail, PageEntity page) {
        Phase phase = phaseService.getPhaseById(phaseDetail.getPhaseId());
        Plan plan = planService.getPlanById(phase.getPlanId());
        List<PhaseDetailDTO> phaseDetailDTOList = phaseDetailDao.getPhaseDetailDTOList(phaseDetail, page);
        if (ObjectUtils.isNotNull(phaseDetailDTOList)) {
            for (PhaseDetailDTO _phaseDetail : phaseDetailDTOList) {
                // 计划总人数
                _phaseDetail.setPeopleNum(plan.getPeopleNum());
                // 获取完成学时
                int complete = phaseDetailProgressDao.getPhaseDetailCompleteTime(_phaseDetail.getId());
                _phaseDetail.setComplete(complete);
            }
        }
        return phaseDetailDTOList;
    }
}