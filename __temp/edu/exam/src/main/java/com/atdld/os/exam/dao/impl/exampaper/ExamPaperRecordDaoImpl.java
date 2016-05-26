package com.atdld.os.exam.dao.impl.exampaper;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.dao.exampaper.ExamPaperRecordDao;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaperMiddle;
import com.atdld.os.exam.entity.exampaper.QueryPaperRecord;

/**
 * @author
 * @ClassName ExamPaperDaoImpl
 * @package com.atdld.os.exam.dao.impl.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:20:23
 */
@Repository("examPaperRecordDao")
public class ExamPaperRecordDaoImpl extends GenericDaoImpl implements ExamPaperRecordDao {

    public void addExamPaperRecord(PaperRecord paperRecord) {
        this.insert("PaperRecordMapper.createPaperRecord", paperRecord);
    }

    /**
     * 批量添加试卷记录
     */
    public void addExamPaperRecordBatch(List<PaperRecord> paperRecordList) {
        Map<String, List<PaperRecord>> map = new HashMap<String, List<PaperRecord>>();
        map.put("paperRecordList", paperRecordList);
        this.insert("PaperRecordMapper.addExamPaperRecordBatch", map);
    }

    public void updateExamPaperRecordById(PaperRecord paperRecord) {
        this.update("PaperRecordMapper.updateExamPaperRecordById", paperRecord);
    }

    public PaperRecord queryPaperRecordById(PaperRecord paperRecord) {
        List<PaperRecord> paperRecordList = this.selectList("PaperRecordMapper.queryPaperRecordById", paperRecord);
        if (paperRecordList != null && paperRecordList.size() > 0) {
            return paperRecordList.get(0);
        }
        return null;
    }

    /**
     * 通过paperRecordId和cusId获得用户试卷记录
     */
    public PaperRecord queryPaperRecordByIdAndCusId(PaperRecord paperRecord) {
        List<PaperRecord> paperRecordList = this.selectList("PaperRecordMapper.queryPaperRecordByIdAndCusId", paperRecord);
        if (paperRecordList != null && paperRecordList.size() > 0) {
            return paperRecordList.get(0);
        }
        return null;
    }

    /**
     * 获得试卷记录列表
     */
    public List<QueryPaperRecord> queryExamPaperRecordList(PaperRecord paperRecord, PageEntity pageEntity) {
        return this.queryForListPage("PaperRecordMapper.queryExamPaperRecordList", paperRecord, pageEntity);
    }

    /**
     * 通过条件获得试卷记录列表
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public List<QueryPaperRecord> queryExamPaperRecordAllList(QueryPaperRecord queryPaperRecord,
                                                              PageEntity pageEntity) {
        return this.queryForListPage("PaperRecordMapper.queryExamPaperRecordAllList", queryPaperRecord, pageEntity);
    }

    /**
     * 通过条件获得包含主观题的试卷记录列表
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public List<QueryPaperRecord> queryExamPaperRecordAllListBySubjective(QueryPaperRecord queryPaperRecord,
                                                                          PageEntity pageEntity) {
        return this.queryForListPage("PaperRecordMapper.queryExamPaperRecordAllListBySubjective", queryPaperRecord, pageEntity);
    }


    /**
     * 通过试卷id查询每个试题多少分
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public List<QueryPaperMiddle> queryPaperMiddleMap(Long paperId) {
        return this.selectList("PaperRecordMapper.queryPaperMiddleMap", paperId);
    }

    /*获取单张考试试卷的结果*/
    public PaperRecord checkPaperResult(PaperRecord paperRecord) {
        return this.selectOne("PaperRecordMapper.checkPaperResult", paperRecord);
    }

    /*获取单张考试试卷的结果*/
    public int queryPaperRecordAverageScore(PaperRecord paperRecord) {
        return this.selectOne("PaperRecordMapper.queryPaperRecordAverageScore", paperRecord);
    }

    /**
     * 查询为考完的paperRecord的最新的一个
     */
    public QueryPaperRecord queryPaperRecordByCusIdNewest(PaperRecord paperRecord) {
        return this.selectOne("PaperRecordMapper.queryPaperRecordByCusIdNewest", paperRecord);
    }

    /**
     * 查看试卷报告Report
     */
    @Override
    public List<QueryPaperRecord> getExamPaperReport(PaperRecord paperRecord) {
        return this.selectList("PaperRecordMapper.getExamPaperReport", paperRecord);
    }

    /**
     * 值传入cusId获得试卷考试记录
     */
    public List<QueryPaperRecord> queryExamPaperRecordListByCusId(PaperRecord paperRecord) {
        return this.selectList("PaperRecordMapper.queryExamPaperRecordListByCusId", paperRecord);
    }

    public int queryExamPaperRecordScoreRanking(PaperRecord paperRecord) {
        return this.selectOne("PaperRecordMapper.queryExamPaperRecordScoreRanking", paperRecord);
    }

    public int queryExamPaperRecordCorrectNumRanking(PaperRecord paperRecord) {
        return this.selectOne("PaperRecordMapper.queryExamPaperRecordCorrectNumRanking", paperRecord);
    }

    public List<QueryPaperRecord> queryExamPaperRecordGroupByEPId(QueryPaperRecord queryPaperRecord) {
        return this.selectList("PaperRecordMapper.queryExamPaperRecordGroupByEPId", queryPaperRecord);
    }

    public Long queryExamPaperRecordMaxId() {
        return this.selectOne("PaperRecordMapper.queryExamPaperRecordMaxId", null);
    }

    public Date queryExamPaperRecordMaxUpdateTime() {
        return this.selectOne("PaperRecordMapper.queryExamPaperRecordMaxUpdate", null);
    }

    /**
     * 更新paperRecord的分数
     */
    public void updateExamPaperRecordForScore(Long id, String score) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("score", score);
        this.update("PaperRecordMapper.updateExamPaperRecordForScore", map);
    }
}

