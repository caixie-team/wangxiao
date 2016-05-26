package io.wangxiao.edu.home.dao.arrange;

import io.wangxiao.edu.home.entity.arrange.ArrangeExam;

import java.util.List;

/**
 * ArrangeExam管理接口
 */
public interface ArrangeExamDao {

    /**
     * 添加ArrangeExam
     *
     * @param arrangeExam 要添加的ArrangeExam
     * @return id
     */
    java.lang.Long addArrangeExam(ArrangeExam arrangeExam);

    /**
     * 批量添加ArrangeExams
     *
     * @param arrangeExams
     */
    void batchAddArrangeExam(List<ArrangeExam> arrangeExams);

    /**
     * 根据id删除一个ArrangeExam
     *
     * @param id 要删除的id
     */
    void deleteArrangeExamById(Long id);

    /**
     * 修改ArrangeExam
     *
     * @param arrangeExam 要修改的ArrangeExam
     */
    void updateArrangeExam(ArrangeExam arrangeExam);

    /**
     * 根据id获取单个ArrangeExam对象
     *
     * @param id 要查询的id
     * @return ArrangeExam
     */
    ArrangeExam getArrangeExamById(Long id);

    /**
     * 根据条件获取ArrangeExam列表
     *
     * @param arrangeExam 查询条件
     * @return List<ArrangeExam>
     */
    List<ArrangeExam> getArrangeExamList(ArrangeExam arrangeExam);

    /**
     * 任务下的试卷
     *
     * @param arrangeExam
     * @return
     */
    List<ArrangeExam> getArrangeExamByIds(ArrangeExam arrangeExam);
}