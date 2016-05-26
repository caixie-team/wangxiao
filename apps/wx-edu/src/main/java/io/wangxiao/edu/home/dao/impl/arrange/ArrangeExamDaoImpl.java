package io.wangxiao.edu.home.dao.impl.arrange;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.arrange.ArrangeExamDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeExam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("arrangeExamDao")
public class ArrangeExamDaoImpl extends GenericDaoImpl implements ArrangeExamDao {

    public Long addArrangeExam(ArrangeExam arrangeExam) {
        return this.insert("ArrangeExamMapper.createArrangeExam", arrangeExam);
    }

    /**
     * 批量添加ArrangeExams
     */
    public void batchAddArrangeExam(List<ArrangeExam> arrangeExams) {
        this.insert("ArrangeExamMapper.batchAddArrangeExam", arrangeExams);
    }

    public void deleteArrangeExamById(Long id) {
        this.delete("ArrangeExamMapper.deleteArrangeExamById", id);
    }

    public void updateArrangeExam(ArrangeExam arrangeExam) {
        this.update("ArrangeExamMapper.updateArrangeExam", arrangeExam);
    }

    public ArrangeExam getArrangeExamById(Long id) {
        return this.selectOne("ArrangeExamMapper.getArrangeExamById", id);
    }

    public List<ArrangeExam> getArrangeExamList(ArrangeExam arrangeExam) {
        return this.selectList("ArrangeExamMapper.getArrangeExamList", arrangeExam);
    }

    /**
     * 任务下的试卷
     */
    public List<ArrangeExam> getArrangeExamByIds(ArrangeExam arrangeExam) {
        return this.selectList("ArrangeExamMapper.getArrangeExamByIds", arrangeExam);
    }
}
