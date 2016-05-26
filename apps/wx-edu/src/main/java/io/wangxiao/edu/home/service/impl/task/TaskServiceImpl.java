package io.wangxiao.edu.home.service.impl.task;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.edu.home.dao.task.*;
import io.wangxiao.edu.home.dao.user.UserGroupMiddleDao;
import io.wangxiao.edu.home.entity.task.*;
import io.wangxiao.edu.home.entity.user.UserGroupMiddle;
import io.wangxiao.edu.home.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TaskExamDao taskExamDao;
    @Autowired
    private TaskMiddleUserDao taskMiddleUserDao;
    @Autowired
    private TaskMiddleGroupDao taskMiddleGroupDao;
    @Autowired
    private TaskRecordDao taskRecordDao;
    @Autowired
    private UserGroupMiddleDao userGroupMiddleDao;

    /**
     * 添加Task
     *
     * @param task 要添加的Task
     * @return id
     */
    public java.lang.Long addTask(Task task) {
        Long num = taskDao.addTask(task);
        boolean task2 = task(task);// 参数传递
        return num;
    }

    /**
     * 添加任务/修改任务用到
     */
    public boolean task(Task task) {
        List<TaskExam> taskExams = new ArrayList<TaskExam>();
        // 如果考试不是空的执行添加
        if (ObjectUtils.isNotNull(task.getExamIds())) {
            // 删除任务下的试卷
            taskExamDao.deleteTaskExamById(task.getId());
            String[] examIds = task.getExamIds().split(",");
            String[] examNames = task.getExamNames().split(",");
            for (String examId : examIds) {// 循环试卷
                for (String examName : examNames) {
                    TaskExam taskExam = new TaskExam();
                    taskExam.setExampaperId(Long.valueOf(examId));// 设置试卷编号
                    taskExam.setTaskId(task.getId());// 设置任务编号
                    taskExam.setExampaperName(examName);
                    taskExams.add(taskExam);
                }
            }
            // 执行添加任务与试卷中间表(批量添加)
            taskExamDao.batchAddTaskExam(taskExams);
        }

        if (ObjectUtils.isNull(task.getGroupIds()) || task.getGroupIds().equals("")) {
            // 删除任务下的员工
            taskMiddleUserDao.deleteTaskMiddleUserById(task.getId());
            // 如果员工不是空的执行添加
            if (ObjectUtils.isNotNull(task.getUserIds())) {
                List<TaskMiddleUser> taskMiddleUsers = new ArrayList<TaskMiddleUser>();
                String[] userIds = task.getUserIds().split(",");
                for (String userId : userIds) {// 循环员工
                    TaskMiddleUser taskMiddleUser = new TaskMiddleUser();
                    taskMiddleUser.setUserId(Long.valueOf(userId));// 设置员工编号
                    taskMiddleUser.setTaskId(task.getId());// 设置任务编号
                    List<UserGroupMiddle> userGroupByUserId = userGroupMiddleDao.getUserGroupByUserId(Long.valueOf(userId));
                    for (UserGroupMiddle userGroupMiddle : userGroupByUserId) {
                        taskMiddleUser.setGroupId(userGroupMiddle.getGroupId());
                        taskMiddleUsers.add(taskMiddleUser);
                    }
                } // getGroupTask
                // 执行添加任务与员工中间表(批量添加)
                taskMiddleUserDao.batchAddTaskMiddleUser(taskMiddleUsers);

                List<TaskRecord> taskRecords = new ArrayList<TaskRecord>();
                // 执行删除任务下的记录
                taskRecordDao.deleteTaskRecoredId(task.getId());
                // 创建个人任务记录
                for (TaskExam taskExam : taskExams) {
                    for (TaskMiddleUser taskMiddleUser : taskMiddleUsers) {
                        TaskRecord taskRecord = new TaskRecord();
                        taskRecord.setUserId(taskMiddleUser.getUserId());
                        taskRecord.setTaskId(taskMiddleUser.getTaskId());
                        taskRecord.setUserGroupId(0L);
                        taskRecord.setExampaperId(taskExam.getExampaperId());
                        taskRecord.setExampaperName(taskExam.getExampaperName());
                        taskRecord.setScore(new BigDecimal(0));
                        taskRecord.setIsComplete(0L);
                        taskRecord.setSubmitTime(null);
                        taskRecords.add(taskRecord);
                        break;
                    }
                }
                // 执行添加个人任务记录
                taskRecordDao.batchAddTaskTecord(taskRecords);
            }
        } else {
            // 可以添加多个组
            if (StringUtils.isNotEmpty(task.getGroupIds())) {
                // 删除任务下的部门
                taskMiddleGroupDao.deleteTaskMiddleGroupById(task.getId());
                List<TaskMiddleGroup> taskMiddleGroups = new ArrayList<TaskMiddleGroup>();
                // 学员信息分组
                String[] groupIdArray = task.getGroupIds().split(",");
                for (String groupId : groupIdArray) {
                    TaskMiddleGroup taskMiddleGroup = new TaskMiddleGroup();
                    taskMiddleGroup.setUserGroupId(Long.valueOf(groupId));
                    taskMiddleGroup.setTaskId(task.getId());
                    taskMiddleGroups.add(taskMiddleGroup);
                }
                // 执行添加部门
                taskMiddleGroupDao.batchAddTaskMiddleGroup(taskMiddleGroups);
            }
        }
        return true;
    }

    /**
     * 根据id删除一个Task
     *
     * @param id 要删除的id
     */
    public void deleteTaskById(Long id) {
        taskDao.deleteTaskById(id);
    }

    /**
     * 修改Task
     *
     * @param task 要修改的Task
     */
    public java.lang.Long updateTask(Task task) {
        Long num = taskDao.updateTask(task);
        boolean task2 = task(task);// 参数传递
        return num;
    }

    /**
     * 根据id获取单个Task对象
     *
     * @param id 要查询的id
     * @return Task
     */
    public Task getTaskById(Long id) {
        return taskDao.getTaskById(id);
    }

    public Task getTask(Task task) {
        return taskDao.getTask(task);
    }

    /**
     * 根据条件获取Task列表
     *
     * @param task 查询条件
     * @return List<Task>
     */
    public List<Task> getTaskList(Task task, PageEntity page) {
        return taskDao.getTaskList(task, page);
    }

    /**
     * 我的部门任务
     */
    public List<Task> getGroupTaskList(Task task, PageEntity page) {
        return this.taskDao.getGroupTaskList(task, page);
    }

    /**
     * 任务列表详情
     */
    public List<Task> taskDetailsById(Task task, PageEntity page) {
        return this.taskDao.taskDetailsById(task, page);
    }

    /**
     * 更改任务状态
     */
    public void updateTaskStatus(Task task) {
        this.taskDao.updateTaskStatus(task);
    }

    /**
     * 前台我的任务
     */
    public List<Task> myTaskForWeb(Task task, PageEntity page) {
        return this.taskDao.myTaskForWeb(task, page);
    }

    /**
     * 前台我的部门任务
     */
    public List<Task> myGroupTaskFroweb(Task task, PageEntity page) {
        return this.taskDao.myGroupTaskFroweb(task, page);
    }

}