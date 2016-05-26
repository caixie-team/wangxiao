package io.wangxiao.edu.home.dao.impl.task;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.task.TaskExamDao;
import io.wangxiao.edu.home.entity.task.TaskExam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskExamDao")
public class TaskExamDaoImpl extends GenericDaoImpl implements TaskExamDao {

    public Long addTaskExam(TaskExam taskExam) {
        return this.insert("TaskExamMapper.createTaskExam", taskExam);
    }

    /**
     * 批量添加TaskExams
     */
    public void batchAddTaskExam(List<TaskExam> taskExams) {
        this.insert("TaskExamMapper.batchAddTaskExam", taskExams);
    }

    public void deleteTaskExamById(Long id) {
        this.delete("TaskExamMapper.deleteTaskExamById", id);
    }

    public void updateTaskExam(TaskExam taskExam) {
        this.update("TaskExamMapper.updateTaskExam", taskExam);
    }

    public TaskExam getTaskExamById(Long id) {
        return this.selectOne("TaskExamMapper.getTaskExamById", id);
    }

    public List<TaskExam> getTaskExamList(TaskExam taskExam) {
        return this.selectList("TaskExamMapper.getTaskExamList", taskExam);
    }

    /**
     * 任务下的试卷
     */
    public List<TaskExam> getTaskExamByIds(TaskExam taskExam) {
        return this.selectList("TaskExamMapper.getTaskExamByIds", taskExam);
    }
}
