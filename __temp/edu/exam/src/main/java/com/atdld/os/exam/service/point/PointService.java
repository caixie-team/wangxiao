package com.atdld.os.exam.service.point;

import java.util.List;

import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.point.PointExtend;
import com.atdld.os.exam.entity.professional.ExamSubject;

/**
 * @author
 * @ClassName SubjectService
 * @package com.atdld.os.exam.service.subject
 * @description 考点（知识点）
 * @Create Date: 2013-9-9 下午3:24:08
 */
public interface PointService {
    /**
     * 根据subjectId返回PointList
     */
    public List<ExamPoint> getPointListBySubject(Long subject);

    /**
     * 保存point
     */
    public void savePoint(ExamPoint point);

    /**
     * 通过pointId获得Point
     */
    public ExamPoint getPointByPointId(ExamPoint point);

    /**
     * 通过pointId修改Point
     */
    public void updatePointByPointId(ExamPoint point);

    /**
     * 通过subjectId修改subjectId
     */
    public void updatePointBysubjectId(ExamSubject examSubject);

    /**
     * 通过pointId删除Point
     */
    public void delPointByPointId(String[] pointIds);

    /**
     * 根据传入条件返回PointList
     */
    public List<ExamPoint> getPointList(ExamPoint point);

    /**
     * 查询所有考点下的试题数量
     */
    public List<PointExtend> queryAllPointQstCount(Long subjectid) throws Exception;

    /**
     * 查询所有考点下的试题数量该用户所做过的试题数量
     *
     * @param subjectId
     * @param cusId
     * @param paperRecordId 不传时为所有试卷的
     * @return
     * @throws Exception
     */
    public List<PointExtend> queryPointAndQuestionRecordListByCusId(Long subjectId,
                                                                    Long cusId, Long paperRecordId) throws Exception;
}
