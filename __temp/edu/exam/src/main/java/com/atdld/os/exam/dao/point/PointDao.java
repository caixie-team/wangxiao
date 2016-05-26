package com.atdld.os.exam.dao.point;

import java.util.List;
import java.util.Map;

import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.point.PointExtend;
import com.atdld.os.exam.entity.point.QueryPoint;

/**
 * @author
 * @ClassName SubjectDao
 * @package com.atdld.os.exam.dao.subject
 * @description
 * @Create Date: 2013-9-9 下午3:19:23
 */
public interface PointDao {
    /**
     * 根据subjectId返回PointList
     */
    public List<ExamPoint> getPointList(ExamPoint point);

    /**
     * 保存point
     */
    public Long savePont(ExamPoint point);

    /**
     * 通过PointId获得point
     */
    public ExamPoint getPointByPointId(ExamPoint point);

    /**
     * 通过PointId修改point
     */
    public void updatePointByPointId(ExamPoint point);

    /**
     * 通过subjectId修改subjectId
     */
    public void updatePointBysubjectId(Map<String, Long> map);

    /**
     * 通过PointId删除point
     */
    public void delPointByPoint(String[] pointIds);

    /**
     * 通过in获得pointList
     */
    public List<ExamPoint> getPointListByInId(String[] pointIds);

    /*
     * 查询所有考点下的试题数量
	 */
    public List<PointExtend> queryAllPointQstCount(ExamPoint point);

    /**
     * 批量添加point
     */
    public void addPointBatch(List<ExamPoint> pointList);

    /**
     * 获取最大Id
     */
    public int queryPointMaxId();

    /**
     * 获取每个考点下的的试题数量和用户答对过的试题数量
     */
    public List<PointExtend> queryPointAndQuestionRecordListByCusId(QueryPoint queryPoint);

}
