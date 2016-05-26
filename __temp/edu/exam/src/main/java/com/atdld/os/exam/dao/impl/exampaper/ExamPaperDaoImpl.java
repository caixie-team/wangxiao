package com.atdld.os.exam.dao.impl.exampaper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.dao.exampaper.ExamPaperDao;
import com.atdld.os.exam.entity.exampaper.ExamPaper;
import com.atdld.os.exam.entity.exampaper.QueryPaper;

/**
 * @author
 * @ClassName ExamPaperDaoImpl
 * @package com.atdld.os.exam.dao.impl.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:20:23
 */
@Repository("examPaperDao")
public class ExamPaperDaoImpl extends GenericDaoImpl implements ExamPaperDao {

    public List<QueryPaper> getPaperAllList(QueryPaper queryPaper,
                                            PageEntity pageEntity) {
        return this.queryForListPage("PaperMapper.getAllPaperList", queryPaper, pageEntity);
    }

    public List<QueryPaper> getPaperAllListForFront(QueryPaper queryPaper, PageEntity pageEntity) {
        return this.queryForListPage("PaperMapper.getPaperAllListForFront", queryPaper, pageEntity);
    }

    public void addExamPaper(ExamPaper paper) {
        this.insert("PaperMapper.createPaper", paper);
    }

    //批量添加试卷
    public void addExamPaperBatch(List<ExamPaper> paperList) {
        Map<String, List<ExamPaper>> map = new HashMap<String, List<ExamPaper>>();
        map.put("paperList", paperList);
        this.insert("PaperMapper.createPaperBatch", map);
    }


    public List<ExamPaper> queryExamPaperById(ExamPaper paper) {
        return this.selectList("PaperMapper.getPaperList", paper);
    }

    public List<QueryPaper> queryExamPaperListByType(ExamPaper paper) {
        return this.selectList("PaperMapper.queryExamPaperByType", paper);
    }

    public void updateExamPaperById(ExamPaper paper) {
        this.update("PaperMapper.updateExamPaperById", paper);
    }

    public void delPaperListBatch(String[] paperIds) {
        this.delete("PaperMapper.deletePaperByidBatch", paperIds);
    }

    public void updateExamPaperByJoinNumAndAvgscore(QueryPaper queryPaper) {
        this.update("PaperMapper.updateExamPaperByJoinNumAndAvgscore", queryPaper);
    }
}
