package com.atdld.os.exam.service.impl.exampaper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.dao.exampaper.ExamPaperDao;
import com.atdld.os.exam.dao.exampaper.ExamPaperRecordDao;
import com.atdld.os.exam.dao.question.ComplexDao;
import com.atdld.os.exam.dao.question.QstMiddleDao;
import com.atdld.os.exam.entity.exampaper.ExamPaper;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaper;
import com.atdld.os.exam.entity.exampaper.QueryPaperRecord;
import com.atdld.os.exam.entity.question.QstComplex;
import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.service.exampaper.ExamPaperService;

/**
 * @author
 * @ClassName ExamPaperServiceImpl
 * @package com.atdld.os.exam.service.impl.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:22:52
 */
@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {
    @Autowired
    private ExamPaperDao examPaperDao;
    @Autowired
    private QstMiddleDao qstMiddleDao;

    public List<QueryPaper> getPaperAllList(QueryPaper paperQuestion,
                                            PageEntity pageEntity) {
        return examPaperDao.getPaperAllList(paperQuestion, pageEntity);
    }

    public List<QueryPaper> getPaperAllListForFront(QueryPaper paperQuestion,
                                                    PageEntity pageEntity) {
        return examPaperDao.getPaperAllListForFront(paperQuestion, pageEntity);
    }

    public void addExamPaper(ExamPaper paper) {
        paper.setAddTime(new Date());
        paper.setUpdateTime(new Date());
        examPaperDao.addExamPaper(paper);
    }

    public ExamPaper queryExamPaperById(ExamPaper paper) {
        List<ExamPaper> examPaperList = examPaperDao.queryExamPaperById(paper);
        if (examPaperList != null && examPaperList.size() > 0) {
            return examPaperList.get(0);
        }
        return null;
    }

    public List<QueryPaper> queryExamPaperListByType(ExamPaper paper) {
        return examPaperDao.queryExamPaperListByType(paper);
    }

    public void updateExamPaperById(ExamPaper paper) {
        examPaperDao.updateExamPaperById(paper);
    }

    public void delPaperListBatch(String paperIds) {
        examPaperDao.delPaperListBatch(paperIds.replaceAll(" ", "").split(","));
    }

    @Getter
    @Setter
    private List<QstMiddle> qstMiddleList = new ArrayList<QstMiddle>();

    public void initQstIds(String qstIds, Long paperId, int type, Long complexId,
                           Long paperMiddleId) {
        qstMiddleList = new ArrayList<QstMiddle>();
        String[] Array = qstIds.split(",");
        QstMiddle qstMiddle = new QstMiddle();

        // 通过试卷paperId和类型就能查出sort
        qstMiddle.setPaperId(paperId);
        List<Integer> qstIdsList = qstMiddleDao.queryQstMiddleListQstIds(qstMiddle);
        qstMiddle.setQstType(type);
        //qstMiddle.setComplexId(complexId);

        sort = qstMiddleDao.queryQstMiddleListMaxSort(qstMiddle);
        sort = sort == null ? 0 : sort;

        for (int i = 0; i < Array.length; i++) {
            boolean flag = true;
            if (qstIdsList != null && qstIdsList.size() > 0) {
                for (int j = 0; j < qstIdsList.size(); j++) {
                    if (Array[i].trim().contains(qstIdsList.get(j).toString())) {
                        flag = false;
                        break;
                    }
                }
            }
            if (qstIdsList.size() == 0 || flag) {
                qstMiddle = new QstMiddle();
                qstMiddle.setAddTime(new Date());
                qstMiddle.setQstType(type);
                qstMiddle.setPaperId(paperId);
                qstMiddle.setQstId(Long.valueOf(Array[i].trim()));
                qstMiddle.setSort(++sort);
                qstMiddle.setPaperMiddleId(paperMiddleId);
                qstMiddle.setComplexId(complexId);
                qstMiddleList.add(qstMiddle);
            }


        }
    }

    private Integer sort;

    public List<QstMiddle> addExamQstMiddleBatch(String qstIds, QstMiddle qstMiddle) {
        if (qstIds != null && qstIds != "") {
            initQstIds(qstIds, qstMiddle.getPaperId(), qstMiddle.getQstType(), qstMiddle.getComplexId(),
                    qstMiddle.getPaperMiddleId());
        }
        qstMiddleDao.addExamQstMiddleBatch(qstMiddleList);
        return qstMiddleList;
    }

    public QstMiddle addExamQstMiddleCaiLiao(Long qstId, Long paperId, int type,
                                             Long complexId) {
        QstMiddle qstMiddle = new QstMiddle();
        qstMiddle.setQstType(type);
        qstMiddle.setPaperId(paperId);
        qstMiddle.setComplexId(complexId);
        qstMiddle.setAddTime(new Date());
        qstMiddle.setQstId(qstId);
        qstMiddleDao.addExamQstMiddle(qstMiddle);
        return qstMiddle;
    }

    @Autowired
    private ComplexDao complexDao;

    public QstComplex addCaiLiaoContent(QstComplex complex) {
        complex.setAddTime(new Date());
        complexDao.addComplex(complex);
        return complex;
    }

    public void updateCaiLiaoContent(QstComplex complex) {
        complexDao.updateComplex(complex);
    }

    /**
     * 获得该用户试卷记录列表
     */
    public List<PaperRecord> queryExamPaperRecord(PaperRecord paperRecord) {
        return null;
    }

    public List<QueryPaper> getFavoritePaper(QueryPaper queryPaper, PageEntity page) {
        return null;
    }

    @Autowired
    private ExamPaperRecordDao examPaperRecordDao;

    public void updateAllExamPaperByPaperRecord(ExamRecord examRecord) {

        QueryPaperRecord queryPaperRecord = new QueryPaperRecord();
        queryPaperRecord.setLastUpdateRecord(examRecord.getLastUpdateRecord());
        // 查询大于上次的paperRecordId的每个试卷的参加人数和该试卷的分数和
        List<QueryPaperRecord> queryPaperRecordList = examPaperRecordDao
                .queryExamPaperRecordGroupByEPId(queryPaperRecord);
        if (queryPaperRecordList != null && queryPaperRecordList.size() > 0) {
            for (QueryPaperRecord queryPaperRecord1 : queryPaperRecordList) {
                ExamPaper paper = new ExamPaper();
                paper.setId(queryPaperRecord1.getEpId());
                // 获得改试卷的参加人数和平均分
                List<ExamPaper> paperList = examPaperDao.queryExamPaperById(paper);
                if (paperList != null) {
                    paper = paperList.get(0);
                }
                // 参加人数等于上次的加上新求的
                int joinNum = paper.getJoinNum() + queryPaperRecord1.getNum();
                // 平均分是上次的参加人数乘以上次的平均分加上现在的分数和除以参加人数
                int avgScore = (paper.getAvgScore() * paper.getJoinNum() + queryPaperRecord1
                        .getSumUserScore()) / joinNum;
                QueryPaper queryPaper = new QueryPaper();
                queryPaper.setJoinNum(joinNum);
                queryPaper.setAvgScore(avgScore);
                queryPaper.setId(paper.getId());
                // 更新每个试卷的参加人数和平均分
                examPaperDao.updateExamPaperByJoinNumAndAvgscore(queryPaper);
            }

        }

    }

}
