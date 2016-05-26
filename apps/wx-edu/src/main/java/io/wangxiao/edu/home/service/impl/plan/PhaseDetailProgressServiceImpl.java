package io.wangxiao.edu.home.service.impl.plan;

import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.plan.PhaseDetailProgressDao;
import io.wangxiao.edu.home.dao.plan.PlanDao;
import io.wangxiao.edu.home.dao.plan.PlanRecordDao;
import io.wangxiao.edu.home.entity.plan.Phase;
import io.wangxiao.edu.home.entity.plan.PhaseDetailProgress;
import io.wangxiao.edu.home.entity.plan.PlanMiddleGroup;
import io.wangxiao.edu.home.entity.plan.PlanRecord;
import io.wangxiao.edu.home.service.plan.PhaseDetailProgressService;
import io.wangxiao.edu.home.service.plan.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("phaseDetailProgressService")
public class PhaseDetailProgressServiceImpl implements PhaseDetailProgressService {

    @Autowired
    private PhaseDetailProgressDao phaseDetailProgressDao;
    @Autowired
    private PlanRecordDao planRecordDao;
    @Autowired
    private PhaseService phaseService;
    @Autowired
    private PlanDao planDao;

    @Override
    public Long createPhaseDetailProgress(PhaseDetailProgress phaseDetailProgress) {
        return this.phaseDetailProgressDao.createPhaseDetailProgress(phaseDetailProgress);
    }

    @Override
    public Long addProgressBatch(List<PhaseDetailProgress> PhaseDetailProgressList) {
        return this.phaseDetailProgressDao.addProgressBatch(PhaseDetailProgressList);
    }

    public PhaseDetailProgress getPhaseDetailProgress(PhaseDetailProgress progress) {
        return this.phaseDetailProgressDao.getPhaseDetailProgress(progress);
    }

    @Override
    public PhaseDetailProgress getPhaseDetailProgressById(Long id) {
        return this.phaseDetailProgressDao.getPhaseDetailProgressById(id);
    }

    @Override
    public int getGroupComplete(PlanMiddleGroup planMiddleGroup) {
        return this.phaseDetailProgressDao.getGroupComplete(planMiddleGroup);
    }

    @Override
    public void updateCompleteTime(PhaseDetailProgress progress) {
        // 增量
        int complete = progress.getComplete();
        PhaseDetailProgress _progress = this.getPhaseDetailProgress(progress);
        if (_progress.getComplete() != _progress.getTotal()) {
            Phase phase = phaseService.getPhaseById(_progress.getPhaseId());
            // 进度是否完成（未完成）
            if (_progress.getStatus() == 0) {
                // 课程/直播
                if (_progress.getType() == 1 || _progress.getType() == 2) {
                    // 修改后的完成时间
                    int completeTime = _progress.getComplete() + complete;
                    // 进度完成
                    if (completeTime >= _progress.getTotal()) {
                        progress.setComplete(_progress.getTotal());// 学习时间
                        progress.setStatus(1);// 已完成状态
                        progress.setEndTime(new Date());// 结束时间
                    }
                    // 未完成,修改完成时间
                    else {
                        progress.setComplete(completeTime);
                    }
                    phaseDetailProgressDao.updateCompleteTime(progress);

                    // 修改用户计划记录信息
                    PlanRecord planRecord = new PlanRecord();
                    planRecord.setPlanId(phase.getPlanId());
                    planRecord.setUserId(_progress.getUserId());
                    PlanRecord _planRecord = planRecordDao.getPlanRecord(planRecord);
                    if (ObjectUtils.isNotNull(_planRecord)) {
                        Date date = new Date();
                        // 判断时间是否超出
                        if (_planRecord.getCompleteTime() + complete >= _planRecord.getTotalTime()) {
                            _planRecord.setCompleteTime(_planRecord.getTotalTime());
                            // 修改完成时间
                            _planRecord.setFinishTime(date);
                            // 修改计划完成人数
                            planDao.updateCompleteNum(phase.getPlanId());
                        } else {
                            _planRecord.setCompleteTime(_planRecord.getCompleteTime() + complete);
                        }
                        // 修改学习时间
                        _planRecord.setUpdateTime(date);
                        planRecordDao.updatePlanRecord(_planRecord);
                    }

                }
            }
        }
    }

    @Override
    public List<PhaseDetailProgress> getPhaseDetailProgressList(PhaseDetailProgress progress) {
        return phaseDetailProgressDao.getPhaseDetailProgressList(progress);
    }
}