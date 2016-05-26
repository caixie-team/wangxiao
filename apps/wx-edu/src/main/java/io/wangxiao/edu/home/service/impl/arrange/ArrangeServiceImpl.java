package io.wangxiao.edu.home.service.impl.arrange;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.HttpUtil;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.home.constants.enums.ArrangeType;
import io.wangxiao.edu.home.dao.arrange.*;
import io.wangxiao.edu.home.entity.arrange.*;
import io.wangxiao.edu.home.entity.user.UserGroupMiddle;
import io.wangxiao.edu.home.service.arrange.ArrangeService;
import io.wangxiao.edu.home.service.user.UserGroupMiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("arrangeService")
public class ArrangeServiceImpl implements ArrangeService {

    @Autowired
    private ArrangeDao arrangeDao;
    @Autowired
    private ArrangeExamDao arrangeExamDao;
    @Autowired
    private ArrangeMiddleUserDao arrangeMiddleUserDao;
    @Autowired
    private ArrangeMiddleGroupDao arrangeMiddleGroupDao;
    @Autowired
    private ArrangeRecordDao arrangeRecordDao;
    @Autowired
    private UserGroupMiddleService userGroupMiddleService;

    /**
     * 添加Arrange
     *
     * @param arrange 要添加的Arrange
     * @return id
     */
    public java.lang.Long addArrange(Arrange arrange) {
        Long num = arrangeDao.addArrange(arrange);
        arrange(arrange);// 参数传递
        return num;
    }

    /**
     * 添加任务/修改任务用到
     */
    public boolean arrange(Arrange arrange) {
        List<ArrangeExam> arrangeExams = new ArrayList<ArrangeExam>();
        // 1.如果考试不是空的执行添加,向考试安排试卷表中添加试卷信息
        if (ObjectUtils.isNotNull(arrange.getExamIds())) {
            // 删除任务下的试卷
            arrangeExamDao.deleteArrangeExamById(arrange.getId());
            //根据试卷ids 获取paperList
            String[] examIds = arrange.getExamIds().split(",");
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", arrange.getExamIds());
            String examJson = HttpUtil.doPost(CommonConstants.examPath + "/webapp/exam/getExamByids", map);
            Gson gson = new Gson();
            Map<String, Object> examResult = gson.fromJson(examJson, new TypeToken<Map<String, Object>>() {
            }.getType());
            List<ExamPaper> paperList = gson.fromJson(gson.toJson(examResult.get("entity")), new TypeToken<List<ExamPaper>>() {
            }.getType());
            Map<Long, String> paperNameMap = new HashMap<Long, String>();
            for (ExamPaper examPaper : paperList) {
                paperNameMap.put(examPaper.getId(), examPaper.getName());
            }
            //向edu_arrang_exam插入数据
            for (String examId : examIds) {// 循环试卷
                if (paperNameMap.containsKey(Long.valueOf(examId))) {
                    String paperName = paperNameMap.get(Long.valueOf(examId));
                    ArrangeExam arrangeExam = new ArrangeExam();
                    arrangeExam.setExampaperId(Long.valueOf(examId));// 设置试卷编号
                    arrangeExam.setArrangeId(arrange.getId());// 设置任务编号
                    arrangeExam.setExampaperName(paperName);
                    arrangeExams.add(arrangeExam);
                }
            }
            // 执行添加任务与试卷中间表(批量添加)
            arrangeExamDao.batchAddArrangeExam(arrangeExams);
        }
        if (arrange.getType() == 0L) {//个人任务
            if (ObjectUtils.isNull(arrange.getGroupIds()) || arrange.getGroupIds().equals("")) {
                // 删除任务下的员工
                arrangeMiddleUserDao.deleteArrangeMiddleUserById(arrange.getId());
                // 如果员工不是空的执行添加
                if (ObjectUtils.isNotNull(arrange.getUserIds())) {
                    List<ArrangeMiddleUser> arrangeMiddleUsers = new ArrayList<ArrangeMiddleUser>();
                    String[] userIds = arrange.getUserIds().split(",");
                    for (String userId : userIds) {// 循环员工
                        ArrangeMiddleUser arrangeMiddleUser = new ArrangeMiddleUser();
                        arrangeMiddleUser.setUserId(Long.valueOf(userId));// 设置员工编号
                        arrangeMiddleUser.setArrangeId(arrange.getId());// 设置任务编号
                    /*List<UserGroupMiddle> userGroupByUserId = userGroupMiddleDao
					    .getUserGroupByUserId(Long.valueOf(userId));
				    for (UserGroupMiddle userGroupMiddle : userGroupByUserId) {
					arrangeMiddleUser.setGroupId(userGroupMiddle.getGroupId());
				    }*/
                        arrangeMiddleUsers.add(arrangeMiddleUser);
                    } // getGroupArrange
                    // 执行添加任务与员工中间表(批量添加)
                    arrangeMiddleUserDao.batchAddArrangeMiddleUser(arrangeMiddleUsers);

                }
            }
        } else if (arrange.getType() == 1L) {//岗位任务
            // 可以添加多个组
            if (StringUtils.isNotEmpty(arrange.getGroupIds())) {
                // 删除任务下的岗位
                arrangeMiddleGroupDao.deleteArrangeMiddleGroupById(arrange.getId());
                List<ArrangeMiddleGroup> arrangeMiddleGroups = new ArrayList<ArrangeMiddleGroup>();
                // 学员信息分组
                String[] groupIdArray = arrange.getGroupIds().split(",");
                for (String groupId : groupIdArray) {
                    ArrangeMiddleGroup arrangeMiddleGroup = new ArrangeMiddleGroup();
                    arrangeMiddleGroup.setUserGroupId(Long.valueOf(groupId));
                    arrangeMiddleGroup.setArrangeId(arrange.getId());
                    arrangeMiddleGroups.add(arrangeMiddleGroup);
                }
                // 执行添加部门
                arrangeMiddleGroupDao.batchAddArrangeMiddleGroup(arrangeMiddleGroups);

            }
        }
        //状态是否创建时发布任务发布任务
        if (arrange.getStatus().equals(ArrangeType.RELEASE.toString())) {
            addStartArrange(arrange.getId());
        } else {
            arrangeRecordDao.deleteArrangeRecoredId(arrange.getId());
        }
        return true;
    }

    /**
     * 根据id删除一个Arrange
     *
     * @param id 要删除的id
     */
    public void deleteArrangeById(Long id) {
        arrangeDao.deleteArrangeById(id);
    }

    /**
     * 修改Arrange
     *
     * @param arrange 要修改的Arrange
     */
    public java.lang.Long updateArrange(Arrange arrange) {
        Long num = arrangeDao.updateArrange(arrange);
        arrange(arrange);// 参数传递
        return num;
    }

    /**
     * 根据id获取单个Arrange对象
     *
     * @param id 要查询的id
     * @return Arrange
     */
    public Arrange getArrangeById(Long id) {
        return arrangeDao.getArrangeById(id);
    }

    /**
     * 根据条件获取Arrange列表
     *
     * @param arrange 查询条件
     * @return List<Arrange>
     */
    public List<Arrange> getArrangeList(Arrange arrange, PageEntity page) {
        return arrangeDao.getArrangeList(arrange, page);
    }

    /**
     * 我的部门任务
     */
    public List<Arrange> getGroupArrangeList(Arrange arrange, PageEntity page) {
        return this.arrangeDao.getGroupArrangeList(arrange, page);
    }

    /**
     * 任务列表详情
     */
    public List<Arrange> arrangeDetailsById(Arrange arrange, PageEntity page) {
        return this.arrangeDao.arrangeDetailsById(arrange, page);
    }

    /**
     * 更改任务状态
     */
    public void updateArrangeStatus(Arrange arrange) {
        this.arrangeDao.updateArrangeStatus(arrange);
    }

    /**
     * 前台我的任务
     */
    public List<Arrange> myArrangeForWeb(Arrange arrange, PageEntity page) {
        return this.arrangeDao.myArrangeForWeb(arrange, page);
    }

    /**
     * 前台我的部门任务
     */
    public List<Arrange> myGroupArrangeFroweb(Arrange arrange, PageEntity page) {
        return this.arrangeDao.myGroupArrangeFroweb(arrange, page);
    }

    /**
     * 发布任务
     */
    public void addStartArrange(Long id) {
        Arrange arrange = getArrangeById(id);
        arrange.setStatus(ArrangeType.RELEASE.toString());
        //发布状态修改
        updateArrangeStatus(arrange);
        //获取arrangeExamList
        ArrangeExam queryArrangeExam = new ArrangeExam();
        queryArrangeExam.setArrangeId(id);
        List<ArrangeExam> arrangeExams = arrangeExamDao.getArrangeExamList(queryArrangeExam);
        //判断是员工 还是部门任务类型
        if (arrange.getType() == 0) {
            List<ArrangeRecord> arrangeRecords = new ArrayList<ArrangeRecord>();
            // 执行删除任务下的记录
            //arrangeRecordDao.deleteArrangeRecoredId(arrange.getId());
            ArrangeMiddleUser queryArrangeMiddleUser = new ArrangeMiddleUser();
            queryArrangeMiddleUser.setArrangeId(id);
            List<ArrangeMiddleUser> arrangeMiddleUserList = arrangeMiddleUserDao.getArrangeMiddleUserList(queryArrangeMiddleUser);
            // 创建个人任务记录
            for (ArrangeExam arrangeExam : arrangeExams) {
                for (ArrangeMiddleUser arrangeMiddleUser : arrangeMiddleUserList) {
                    ArrangeRecord arrangeRecord = new ArrangeRecord();
                    arrangeRecord.setUserId(arrangeMiddleUser.getUserId());
                    arrangeRecord.setArrangeId(arrangeMiddleUser.getArrangeId());
                    arrangeRecord.setExampaperId(arrangeExam.getExampaperId());
                    arrangeRecord.setExampaperName(arrangeExam.getExampaperName());
                    arrangeRecord.setScore(new BigDecimal(0));
                    arrangeRecord.setIsComplete(0L);
                    arrangeRecord.setSubmitTime(null);
                    arrangeRecords.add(arrangeRecord);
                }
            }
            // 执行添加个人任务记录
            arrangeRecordDao.batchAddArrangeTecord(arrangeRecords);
        } else if (arrange.getType() == 1) {
            List<ArrangeRecord> arrangeRecords = new ArrayList<ArrangeRecord>();
            // 执行删除任务下的记录
            //arrangeRecordDao.deleteArrangeRecoredId(arrange.getId());
            ArrangeMiddleGroup queryArrangeMiddleGroup = new ArrangeMiddleGroup();
            queryArrangeMiddleGroup.setArrangeId(id);
            List<ArrangeMiddleGroup> arrangeMiddleGroupList = arrangeMiddleGroupDao.getArrangeMiddleGroupList(queryArrangeMiddleGroup);
            // 创建个人任务记录
            for (ArrangeExam arrangeExam : arrangeExams) {
                List<Long> userIds = new ArrayList<Long>();
                for (ArrangeMiddleGroup arrangeMiddleGroup : arrangeMiddleGroupList) {
                    //根据groupId获取userGroupList（包含userId）
                    List<UserGroupMiddle> userGroupMiddleList = userGroupMiddleService.getUserGroupByGourpId(arrangeMiddleGroup.getUserGroupId());

                    for (UserGroupMiddle userGroupMiddle : userGroupMiddleList) {
                        userIds.add(userGroupMiddle.getUserId());
                    }
                }
                HashSet<Long> hs = new HashSet<Long>(userIds);
                userIds = new ArrayList<Long>(hs);
                for (Long userId : userIds) {
                    ArrangeRecord arrangeRecord = new ArrangeRecord();
                    arrangeRecord.setArrangeId(id);
                    arrangeRecord.setUserId(userId);
                    arrangeRecord.setExampaperId(arrangeExam.getExampaperId());
                    arrangeRecord.setExampaperName(arrangeExam.getExampaperName());
                    arrangeRecord.setScore(new BigDecimal(0));
                    arrangeRecord.setIsComplete(0L);
                    arrangeRecord.setSubmitTime(null);
                    arrangeRecords.add(arrangeRecord);
                }
            }
            // 执行添加个人任务记录
            arrangeRecordDao.batchAddArrangeTecord(arrangeRecords);
        }
    }

    /**
     * 定时结束考试
     */
    public void timingEndExam() {
        //获取结束时间小于当前时间的考试
        Date date = new Date();
        List<Arrange> arrangeList = arrangeDao.getArrangeListByTime(date);
        //批量修改用户状态为已完成
        //arrangeRecordDao.updateArrangeRecordStatusBatch(arrangeList);
        //根据结束日期修改考试任务状态为结束
        arrangeDao.updateArrangeListByTime(date);
    }


}