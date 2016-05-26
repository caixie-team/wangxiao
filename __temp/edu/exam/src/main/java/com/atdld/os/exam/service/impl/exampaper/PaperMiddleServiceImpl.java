package com.atdld.os.exam.service.impl.exampaper;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.dao.exampaper.ExamPaperMiddleDao;
import com.atdld.os.exam.dao.exampaper.ExamPaperRecordDao;
import com.atdld.os.exam.dao.question.ComplexDao;
import com.atdld.os.exam.dao.question.QstMiddleDao;
import com.atdld.os.exam.dao.question.QuestionDao;
import com.atdld.os.exam.entity.exampaper.PaperMiddle;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.question.QstComplex;
import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.entity.question.QueryQstMiddle;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.service.exampaper.PaperMiddleService;

@Service("paperMiddleService")
public class PaperMiddleServiceImpl implements PaperMiddleService {
    @Autowired
    private ExamPaperMiddleDao examPaperMiddleDao;
    @Autowired
    private QstMiddleDao qstMiddleDao;
    @Autowired
    private ComplexDao complexDao;
    @Autowired
    private QuestionDao questionDao;

    /**
     * 通过paperId获得试卷列表，第一次考试用
     */
    public List<PaperMiddle> getPaperMiddleListByPaperId(Long paperId, Long cusId) {

        // 查询试卷中间表（有几道大题）
        PaperMiddle queryPaperMiddle = new PaperMiddle();
        queryPaperMiddle.setPaperId(paperId);
        List<PaperMiddle> paperMiddleList = examPaperMiddleDao
                .queryPaperMiddleList(queryPaperMiddle);

        // 查询出一张试卷所有的试题
        QstMiddle queryQstMiddle = new QstMiddle();
        queryQstMiddle.setPaperId(paperId);
        queryQstMiddle.setCusId(cusId);
        List<QueryQstMiddle> qstMiddleList = qstMiddleDao
                .queryQstMiddleList(queryQstMiddle);

        // 按大题循环
        for (PaperMiddle paperMiddle : paperMiddleList) {
            // 如果有材料题先查询材料题的内容
            if (paperMiddle.getType() == 4) {
                List<QstComplex> complexList = complexDao.getQstComplexList(
                        paperMiddle.getPaperId(), paperMiddle.getId());
                paperMiddle.setComplexList(complexList);// 获得材料题的该paperMiddle的complex
            }
            for (QueryQstMiddle qstMiddle : qstMiddleList) {
                // 如果paperMiddleId = qstMiddle的Id说明试题在该大题下，把题放入属于他的list中
                if (paperMiddle.getId().longValue() == qstMiddle.getPaperMiddleId().longValue()) {
                    if (paperMiddle.getType() == 4) {
                        if (!ObjectUtils.isNull(paperMiddle.getComplexList())) {
                            // 材料题要判断材料题的id
                            for (QstComplex qstComplex : paperMiddle.getComplexList()) {
                                if (qstComplex.getId().longValue() == qstMiddle.getComplexId().longValue()) {
                                    qstComplex.getQueryQstMiddleList().add(qstMiddle);
                                }
                            }
                        }
                    } else {// 非材料题。试卷中间表id相等
                        paperMiddle.getQstMiddleList().add(qstMiddle);
                    }
                }
            }
            // 通过sort进行排序
            ComparatorQstMiddle comparator = new ComparatorQstMiddle();
            List<QueryQstMiddle> qstMiddleListSort = paperMiddle.getQstMiddleList();
            Collections.sort(qstMiddleListSort, comparator);
        }
        // 通过sort进行排序
        ComparatorPaperMiddle comparatorPaperMiddle = new ComparatorPaperMiddle();
        Collections.sort(paperMiddleList, comparatorPaperMiddle);
        return paperMiddleList;
    }

    public void updatePaperMiddle(PaperMiddle paperMiddle) {
        examPaperMiddleDao.updatePaperMiddle(paperMiddle);
    }

    /**
     * 删除材料题
     */
    @Override
    public void delQstMiddleBycomplexId(Long paperId, Long complexId) {
        QstComplex complex = new QstComplex();
        complex.setId(complexId);
        complexDao.delComplexById(complexId);
        QstMiddle qstMiddle = new QstMiddle();
        qstMiddle.setPaperId(paperId);
        qstMiddle.setComplexId(complexId);
        qstMiddleDao.delQstMiddleById(qstMiddle);
    }

    public void delQstMiddleById(PaperMiddle paperMiddle) {
        List<PaperMiddle> list = examPaperMiddleDao.queryPaperMiddleList(paperMiddle);

        if (!ObjectUtils.isNull(list)) {
            paperMiddle = list.get(0);
            if (paperMiddle.getType() == 4) {// 材料题时删除材料题表
                QstComplex complex = new QstComplex();
                complex.setPaperId(paperMiddle.getPaperId());
                complex.setPaperMiddleId(paperMiddle.getId());
                complexDao.delComplexByComplex(complex);
            }
            examPaperMiddleDao.delPaperMiddleByPaperId(paperMiddle);
            QstMiddle qstMiddle = new QstMiddle();
            qstMiddle.setPaperId(paperMiddle.getPaperId());
            qstMiddle.setPaperMiddleId(paperMiddle.getId());
            qstMiddleDao.delQstMiddleById(qstMiddle);
        }

    }

    public void addPaperMiddle(PaperMiddle paperMiddle) {
        Integer sort = examPaperMiddleDao.queryPaperMiddleListMaxSort(paperMiddle);
        if (sort == null) {
            sort = new Integer(0);
        }
        paperMiddle.setSort(++sort);
        // paperMiddle.setTitle(getDefaultExampTitle(paperMiddle.getType()));//
        // 默认的标题
        examPaperMiddleDao.addExamPaperMiddle(paperMiddle);
    }

    public void updatePaperMiddleMoveUp(int oneSort, Long onePMId, int twotSort,
                                        Long twoPMId) {
        PaperMiddle paperMiddle = new PaperMiddle();
        paperMiddle.setId(onePMId);
        paperMiddle.setSort(twotSort);
        examPaperMiddleDao.updatePaperMiddleMoveUp(paperMiddle);
        paperMiddle = new PaperMiddle();
        paperMiddle.setId(twoPMId);
        paperMiddle.setSort(oneSort);
        examPaperMiddleDao.updatePaperMiddleMoveUp(paperMiddle);
    }

    @Autowired
    private ExamPaperRecordDao examPaperRecordDao;

    /**
     * 通过paperId获得用户试卷记录，已经测过的第二次考试或者查看解析时用
     */
    public List<PaperMiddle> getPaperMiddleListByExamPaperRecord(PaperRecord paperRecord) {
        PaperMiddle queryQaperMiddle = new PaperMiddle();
        queryQaperMiddle.setPaperId(paperRecord.getEpId());//试卷id
        // 获得改试卷的paperMiddleList
        List<PaperMiddle> paperMiddleList = examPaperMiddleDao
                .queryPaperMiddleList(queryQaperMiddle);

        QstMiddle queryQstMiddle = new QstMiddle();
        queryQstMiddle.setPaperId(paperRecord.getEpId());
        queryQstMiddle.setPaperRecordId(paperRecord.getId());
        queryQstMiddle.setCusId(paperRecord.getCusId());

        // 获得所有试题的记录
        List<QueryQstMiddle> qstMiddleList = qstMiddleDao
                .queryQstMiddleListBypaperRecord(queryQstMiddle);
        // 按大题循环
        for (PaperMiddle paperMiddle : paperMiddleList) {
            // 如果有材料题先查询材料题的内容
            if (paperMiddle.getType() == 4) {
                List<QstComplex> complexList = complexDao.getQstComplexList(
                        paperMiddle.getPaperId(), paperMiddle.getId());
                paperMiddle.setComplexList(complexList);// 获得材料题的该paperMiddle的complex
            }
            //循环所有的试题列表，放到大题的list中
            for (QueryQstMiddle qstMiddle : qstMiddleList) {
                // 如果paperMiddleId = qstMiddle的Id说明试题在该大题下，把题放入属于他的list中
                //logger.info("+++:" + qstMiddle.getId() + ",getPaperMiddleId:" + qstMiddle.getPaperMiddleId() + ",type:" + paperMiddle.getType());
                if (paperMiddle.getId().longValue() == qstMiddle.getPaperMiddleId().longValue()) {
                    if (paperMiddle.getType() == 4) {
                        if (!ObjectUtils.isNull(paperMiddle.getComplexList())) {
                            // 材料题要判断材料题的id
                            for (QstComplex qstComplex : paperMiddle.getComplexList()) {
                                if (qstComplex.getId().longValue() == qstMiddle.getComplexId().longValue()) {
                                    qstComplex.getQueryQstMiddleList().add(qstMiddle);
                                }
                            }
                        }
                    } else {// 非材料题。试卷中间表id相等
                        paperMiddle.getQstMiddleList().add(qstMiddle);
                    }
                }
            }

            // 通过sort进行排序
            ComparatorQstMiddle comparator = new ComparatorQstMiddle();
            List<QueryQstMiddle> qstMiddleListSort = paperMiddle.getQstMiddleList();
            Collections.sort(qstMiddleListSort, comparator);
        }
        return paperMiddleList;
    }

    public PaperRecord queryPaperRecordById(PaperRecord paperRecord) {
        return examPaperRecordDao.queryPaperRecordById(paperRecord);
    }
    
    /**
     * 通过paperRecordId和cusId获得用户试卷记录
     */
    public PaperRecord queryPaperRecordByIdAndCusId(PaperRecord paperRecord) {
        return examPaperRecordDao.queryPaperRecordByIdAndCusId(paperRecord);
    }

    public List<QueryQuestion> getRandomQuestionByExamPaperRecord(PaperRecord paperRecord) {
        List<QueryQuestion> queryQuestionList = questionDao
                .getQuestionByQuestionRecord(paperRecord);
        return queryQuestionList;
    }

    public String getDefaultExampTitle(int type) {
        switch (type) {
            case 1:
                return "单项选择题";
            case 2:
                return "多项选择题";
            case 3:
                return "判断题";
            case 4:
                return "材料分析题";
            case 5:
                return "不定项选择题";
            default:
                return "";
        }
    }


	public PaperMiddle getPaperMiddleById(Long paperMiddleId) {
		
		return examPaperMiddleDao.getPaperMiddleById(paperMiddleId);
	}
}
