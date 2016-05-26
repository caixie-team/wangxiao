package com.atdld.os.exam.dao.impl.point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.point.PointDao;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.point.PointExtend;
import com.atdld.os.exam.entity.point.QueryPoint;

/**
 * @author
 * @ClassName SubjectDaoImpl
 * @package com.atdld.os.exam.dao.impl.subject
 * @description
 * @Create Date: 2013-9-9 下午3:21:49
 */
@Repository("pointDao")
public class PointDaoImpl extends GenericDaoImpl implements PointDao {

    @Override
    public List<ExamPoint> getPointList(ExamPoint point) {
        return this.selectList("PointMapper.getPointList", point);
    }

    @Override
    public Long savePont(ExamPoint point) {
        return new Long(this.insert("PointMapper.createPoint", point));
    }

    public void addPointBatch(List<ExamPoint> pointList) {
        Map<String, List<ExamPoint>> map = new HashMap<String, List<ExamPoint>>();
        map.put("pointList", pointList);
        this.insert("PointMapper.addPointBatch", map);
    }

    public int queryPointMaxId() {
        return this.selectOne("PointMapper.queryPointMaxId", null);
    }

    @Override
    public ExamPoint getPointByPointId(ExamPoint point) {
        List<ExamPoint> list = this.selectList("PointMapper.getPointListByPointId", point);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void updatePointByPointId(ExamPoint point) {
        this.update("PointMapper.updatePointByid", point);
    }

    @Override
    public void updatePointBysubjectId(Map<String, Long> map) {
        this.update("PointMapper.updatePointBysubjectId", map);
    }

    @Override
    public void delPointByPoint(String[] pointIds) {
        this.delete("PointMapper.deletePointByidBatch", pointIds);
    }

    @Override
    public List<ExamPoint> getPointListByInId(String[] pointIds) {
        return this.selectList("PointMapper.getPointListByInId", pointIds);
    }

    /*
     * 查询所有考点下的试题数量
     */
    @Override
    public List<PointExtend> queryAllPointQstCount(ExamPoint point) {
        return this.selectList("PointMapper.queryAllPointQstCount", point);
    }

    public List<PointExtend> queryPointAndQuestionRecordListByCusId(QueryPoint queryPoint) {
        return this.selectList("PointMapper.queryPointAndQuestionRecordListByCusId", queryPoint);
    }


}
