package com.atdld.os.exam.dao.impl.exampaper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.exampaper.ExamPaperMiddleDao;
import com.atdld.os.exam.entity.exampaper.PaperMiddle;

@Repository("examPaperMiddleDao")
public class ExamPaperMiddleDaoImpl extends GenericDaoImpl implements ExamPaperMiddleDao {
    public void addExamPaperMiddleBatch(List<PaperMiddle> paperMiddleList) {
        this.insert("PaperMiddleMapper.createPaperMiddleBatch", paperMiddleList);
    }

    public void addExamPaperMiddle(PaperMiddle paperMiddle) {
        this.insert("PaperMiddleMapper.createPaperMiddle", paperMiddle);
    }

    public List<PaperMiddle> queryPaperMiddleList(PaperMiddle paperMiddle) {
        return this.selectList("PaperMiddleMapper.getPaperMiddleList", paperMiddle);
    }

    public void delPaperMiddleByPaperId(PaperMiddle paperMiddle) {
        this.delete("PaperMiddleMapper.deletePaperMiddleByPaperId", paperMiddle);
    }

    public void updatePaperMiddle(PaperMiddle paperMiddle) {
        this.update("PaperMiddleMapper.updatePaperMiddle", paperMiddle);
    }

    public Integer queryPaperMiddleListMaxSort(PaperMiddle paperMiddle) {
        return this.selectOne("PaperMiddleMapper.getPaperMiddleListMaxSort", paperMiddle);
    }

    public void updatePaperMiddleMoveUp(PaperMiddle paperMiddle) {
        this.update("PaperMiddleMapper.updatePaperMiddleMoveUp", paperMiddle);
    }
    /**
     * 查询paperMiddle
     */
    public PaperMiddle getPaperMiddleById(Long paperMiddleId){
    	return this.selectOne("PaperMiddleMapper.getPaperMiddleById", paperMiddleId);
    }
}
