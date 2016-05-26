package io.wangxiao.edu.home.dao.impl.task;

import io.wangxiao.commons.dao.impl.GenericDaoImpl;
import io.wangxiao.edu.home.dao.task.TaskMiddleGroupDao;
import io.wangxiao.edu.home.entity.task.TaskMiddleGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taskMiddleGroupDao")
public class TaskMiddleGroupDaoImpl extends GenericDaoImpl implements TaskMiddleGroupDao {

    public Long addTaskMiddleGroup(TaskMiddleGroup taskMiddleGroup) {
        return this.insert("TaskMiddleGroupMapper.createTaskMiddleGroup", taskMiddleGroup);
    }

    /**
     * 批量添加部门
     */
    public void batchAddTaskMiddleGroup(List<TaskMiddleGroup> taskMiddleGroups) {
        this.insert("TaskMiddleGroupMapper.batchAddTaskMiddleGroup", taskMiddleGroups);
    }

    public void deleteTaskMiddleGroupById(Long id) {
        this.delete("TaskMiddleGroupMapper.deleteTaskMiddleGroupById", id);
    }

    public void updateTaskMiddleGroup(TaskMiddleGroup taskMiddleGroup) {
        this.update("TaskMiddleGroupMapper.updateTaskMiddleGroup", taskMiddleGroup);
    }

    public TaskMiddleGroup getTaskMiddleGroupById(Long id) {
        return this.selectOne("TaskMiddleGroupMapper.getTaskMiddleGroupById", id);
    }

    public List<TaskMiddleGroup> getTaskMiddleGroupList(TaskMiddleGroup taskMiddleGroup) {
        return this.selectList("TaskMiddleGroupMapper.getTaskMiddleGroupList", taskMiddleGroup);
    }

    /**
     * 任务下的部门
     */
    public List<TaskMiddleGroup> getTaskGroupList(TaskMiddleGroup taskMiddleGroup) {
        return this.selectList("TaskMiddleGroupMapper.getTaskGroupList", taskMiddleGroup);
    }
}
