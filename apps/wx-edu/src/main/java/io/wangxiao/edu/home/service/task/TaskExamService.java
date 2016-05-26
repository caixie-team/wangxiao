package io.wangxiao.edu.home.service.task;

import io.wangxiao.edu.home.entity.task.TaskExam;

import java.util.List;

public interface TaskExamService {

    /**
     * 添加TaskExam
     *
     * @param taskExam 要添加的TaskExam
     * @return id
     */
    java.lang.Long addTaskExam(TaskExam taskExam);

    /**
     * 批量添加TaskExams
     *
     * @param taskExams
     */
    void batchAddTaskExam(List<TaskExam> taskExams);

    /**
     * 根据id删除一个TaskExam
     *
     * @param id 要删除的id
     */
    void deleteTaskExamById(Long id);

    /**
     * 修改TaskExam
     *
     * @param taskExam 要修改的TaskExam
     */
    void updateTaskExam(TaskExam taskExam);

    /**
     * 根据id获取单个TaskExam对象
     *
     * @param id 要查询的id
     * @return TaskExam
     */
    TaskExam getTaskExamById(Long id);

    /**
     * 根据条件获取TaskExam列表
     *
     * @param taskExam 查询条件
     * @return List<TaskExam>
     */
    List<TaskExam> getTaskExamList(TaskExam taskExam);

    /**
     * 任务下的试卷
     *
     * @param taskExam
     * @return
     */
    List<TaskExam> getTaskExamByIds(TaskExam taskExam);
}