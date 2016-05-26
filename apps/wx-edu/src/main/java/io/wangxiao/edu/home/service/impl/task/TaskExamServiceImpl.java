package io.wangxiao.edu.home.service.impl.task;

import io.wangxiao.edu.home.dao.task.TaskExamDao;
import io.wangxiao.edu.home.entity.task.TaskExam;
import io.wangxiao.edu.home.service.task.TaskExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("taskExamService")
public class TaskExamServiceImpl implements TaskExamService {

    @Autowired
    private TaskExamDao taskExamDao;

    /**
     * 添加TaskExam
     *
     * @param taskExam 要添加的TaskExam
     * @return id
     */
    public java.lang.Long addTaskExam(TaskExam taskExam) {
        return taskExamDao.addTaskExam(taskExam);
    }

    /**
     * 批量添加TaskExams
     */
    public void batchAddTaskExam(List<TaskExam> taskExams) {
        this.taskExamDao.batchAddTaskExam(taskExams);
    }

    /**
     * 根据id删除一个TaskExam
     *
     * @param id 要删除的id
     */
    public void deleteTaskExamById(Long id) {
        taskExamDao.deleteTaskExamById(id);
    }

    /**
     * 修改TaskExam
     *
     * @param taskExam 要修改的TaskExam
     */
    public void updateTaskExam(TaskExam taskExam) {
        taskExamDao.updateTaskExam(taskExam);
    }

    /**
     * 根据id获取单个TaskExam对象
     *
     * @param id 要查询的id
     * @return TaskExam
     */
    public TaskExam getTaskExamById(Long id) {
        return taskExamDao.getTaskExamById(id);
    }

    /**
     * 根据条件获取TaskExam列表
     *
     * @param taskExam 查询条件
     * @return List<TaskExam>
     */
    public List<TaskExam> getTaskExamList(TaskExam taskExam) {
        return taskExamDao.getTaskExamList(taskExam);
    }

    /**
     * 任务下的试卷
     */
    public List<TaskExam> getTaskExamByIds(TaskExam taskExam) {
        return this.taskExamDao.getTaskExamByIds(taskExam);
    }

}