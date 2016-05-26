package com.atdld.os.exam.service.impl.point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.dao.point.PointDao;
import com.atdld.os.exam.dao.question.QuestionDao;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.point.PointExtend;
import com.atdld.os.exam.entity.point.QueryPoint;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.service.point.PointService;

/**
 * @author
 * @ClassName SubjectServiceImpl
 * @package com.atdld.os.exam.service.impl.subject
 * @description
 * @Create Date: 2013-9-9 下午3:24:19
 */
@Service("pointService")
public class PointServiceImpl implements PointService {
    @Autowired
    private PointDao pointDao;
    @Autowired
    private QuestionDao questionDao;

    private MemCache memCache = MemCache.getInstance();

    public List<ExamPoint> getPointListBySubject(Long subjectId) {
        ExamPoint point = new ExamPoint();
        point.setSubjectId(subjectId);
        point.setParentId(-1L);
        return pointDao.getPointList(point);
    }

    public void savePoint(ExamPoint point) {
        pointDao.savePont(point);
        questionDao.updateQuestionWhenAddPointId(point);
    }

    public ExamPoint getPointByPointId(ExamPoint point) {
        String key = MemConstans.MEM_POINTID_POINTNAME + point.getId();
        ExamPoint result = (ExamPoint) memCache.get(key);
        if (ObjectUtils.isNull(result)) {
            result = pointDao.getPointByPointId(point);
            memCache.set(key, result, MemConstans.MEM_COMMON_TIME);
        }
        return result;
    }

    public void updatePointByPointId(ExamPoint point) {
        pointDao.updatePointByPointId(point);
    }

    public void updatePointBysubjectId(ExamSubject subject) {
        Map<String, Long> map = new HashMap<String, Long>();
        //map.put("subjectOld", subject.getParentId());
        map.put("subjectNew", subject.getSubjectId());
        pointDao.updatePointBysubjectId(map);
    }

    public void delPointByPointId(String[] pointIds) {
        pointDao.delPointByPoint(pointIds);
    }

    public List<ExamPoint> getPointList(ExamPoint point) {
        return pointDao.getPointList(point);
    }

    /**
     * 查询所有考点下的试题数量
     *
     * @param int subjectId,按项目查询
     */
    @Override
    public List<PointExtend> queryAllPointQstCount(Long subjectId) throws Exception {
        ExamPoint point = new ExamPoint();
        point.setSubjectId(subjectId);
        // 查询本项目下的考点以及数量
        List<PointExtend> list = pointDao.queryAllPointQstCount(point);
        // 凑数据：将父类节点的数量设置好

        return addparentCount(list);
    }

    /**
     * 增加父类的数量
     *
     * @param list        原始考点以及数量
     * @param sourcePoint 此次添加的考点
     */
    public List<PointExtend> addparentCount(List<PointExtend> list) throws Exception {
        Map<String, PointExtend> pmap = new HashMap<String, PointExtend>();
        // 将考点放到map中
        for (PointExtend pointExtend : list) {
            pmap.put(pointExtend.getId() + "", pointExtend);
        }
        // 循环累加操作
        for (PointExtend sourcePoint : list) {
            // 查找他的父类，给父类增加数量
            if (sourcePoint.getParentId() != 0) {
                // 获取父类
                // System.out.println("+++sourcePoint id:"+sourcePoint.getId()+",pid:"+sourcePoint.getPId());
                PointExtend parentPoint = pmap.get(sourcePoint.getParentId() + "");
                if (parentPoint == null) {
                    throw new Exception();
                }
                // 父类数量增加
                parentPoint.setQstCount(parentPoint.getQstCount()
                        + sourcePoint.getQstCount());
                parentPoint.setCusRightQstNum(parentPoint.getCusRightQstNum()
                        + sourcePoint.getCusRightQstNum());
                // 覆盖掉旧值
                pmap.put(parentPoint.getId() + "", parentPoint);
                // 如果父类的上级还有。继续累加
                if (parentPoint.getParentId() != 0) {
                    PointExtend parentPoint2 = pmap.get(parentPoint.getParentId() + "");
                    if (parentPoint2 == null) {
                        throw new Exception();
                    }
                    parentPoint2.setQstCount(parentPoint2.getQstCount()
                            + sourcePoint.getQstCount());// 注意加的数量应该为sourcePoint
                    parentPoint2.setCusRightQstNum(parentPoint2.getCusRightQstNum()
                            + sourcePoint.getCusRightQstNum());
                    pmap.put(parentPoint2.getId() + "", parentPoint2);
                }
            }
        }
        List<PointExtend> result = new ArrayList<PointExtend>();
        for (Map.Entry<String, PointExtend> entry : pmap.entrySet()) {
            result.add(entry.getValue());
        }
        Collections.sort(result, new ComparePoint());
        return result;

    }

    public List<PointExtend> queryPointAndQuestionRecordListByCusId(Long subjectId,
                                                                    Long cusId, Long paperRecordId) throws Exception {
        QueryPoint queryPoint = new QueryPoint();
        queryPoint.setSubjectId(subjectId);
        queryPoint.setCusId(cusId);
        queryPoint.setPaperRecordId(paperRecordId);
        // 查询本项目下的考点以及数量
        List<PointExtend> list = pointDao
                .queryPointAndQuestionRecordListByCusId(queryPoint);
        // 凑数据：将父类节点的数量设置好
        return addparentCount(list);
    }
}
