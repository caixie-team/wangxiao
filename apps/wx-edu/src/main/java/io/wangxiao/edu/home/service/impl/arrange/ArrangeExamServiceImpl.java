package io.wangxiao.edu.home.service.impl.arrange;

import io.wangxiao.edu.home.dao.arrange.ArrangeExamDao;
import io.wangxiao.edu.home.entity.arrange.ArrangeExam;
import io.wangxiao.edu.home.service.arrange.ArrangeExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("arrangeExamService")
public class ArrangeExamServiceImpl implements ArrangeExamService {

    @Autowired
    private ArrangeExamDao arrangeExamDao;

    /**
     * 添加ArrangeExam
     *
     * @param arrangeExam 要添加的ArrangeExam
     * @return id
     */
    public java.lang.Long addArrangeExam(ArrangeExam arrangeExam) {
        return arrangeExamDao.addArrangeExam(arrangeExam);
    }

    /**
     * 批量添加ArrangeExams
     */
    public void batchAddArrangeExam(List<ArrangeExam> arrangeExams) {
        this.arrangeExamDao.batchAddArrangeExam(arrangeExams);
    }

    /**
     * 根据id删除一个ArrangeExam
     *
     * @param id 要删除的id
     */
    public void deleteArrangeExamById(Long id) {
        arrangeExamDao.deleteArrangeExamById(id);
    }

    /**
     * 修改ArrangeExam
     *
     * @param arrangeExam 要修改的ArrangeExam
     */
    public void updateArrangeExam(ArrangeExam arrangeExam) {
        arrangeExamDao.updateArrangeExam(arrangeExam);
    }

    /**
     * 根据id获取单个ArrangeExam对象
     *
     * @param id 要查询的id
     * @return ArrangeExam
     */
    public ArrangeExam getArrangeExamById(Long id) {
        return arrangeExamDao.getArrangeExamById(id);
    }

    /**
     * 根据条件获取ArrangeExam列表
     *
     * @param arrangeExam 查询条件
     * @return List<ArrangeExam>
     */
    public List<ArrangeExam> getArrangeExamList(ArrangeExam arrangeExam) {
        return arrangeExamDao.getArrangeExamList(arrangeExam);
    }

    /**
     * 任务下的试卷
     */
    public List<ArrangeExam> getArrangeExamByIds(ArrangeExam arrangeExam) {
        return this.arrangeExamDao.getArrangeExamByIds(arrangeExam);
    }


}