package io.wangxiao.edu.home.service.impl.course;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.commons.util.StringUtils;
import io.wangxiao.commons.util.web.WebUtils;
import io.wangxiao.edu.common.constants.CommonConstants;
import io.wangxiao.edu.common.service.SnsHessianService;
import io.wangxiao.edu.home.constants.enums.CourseProfileType;
import io.wangxiao.edu.home.constants.enums.WebSiteProfileType;
import io.wangxiao.edu.home.dao.course.CourseStudyhistoryDao;
import io.wangxiao.edu.home.entity.course.Course;
import io.wangxiao.edu.home.entity.course.CourseKpoint;
import io.wangxiao.edu.home.entity.course.CourseProfile;
import io.wangxiao.edu.home.entity.course.CourseStudyhistory;
import io.wangxiao.edu.home.entity.plan.PhaseDetailProgress;
import io.wangxiao.edu.home.service.course.CourseKpointService;
import io.wangxiao.edu.home.service.course.CourseProfileService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.course.CourseStudyhistoryService;
import io.wangxiao.edu.home.service.plan.PhaseDetailProgressService;
import io.wangxiao.edu.home.service.statistic.UserCourseKpointStatisticService;
import io.wangxiao.edu.home.service.website.WebsiteProfileService;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("courseStudyhistoryService")
public class CourseStudyhistoryServiceImpl implements CourseStudyhistoryService {

    @Autowired
    private CourseStudyhistoryDao courseStudyhistoryDao;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseKpointService courseKpointService;
    @Autowired
    private CourseProfileService courseProfileService;
    @Autowired
    private SnsHessianService snsHessianService;
    @Autowired
    private WebsiteProfileService websiteProfileService;
    @Autowired
    private UserCourseKpointStatisticService userCourseKpointStatisticService;
    @Autowired
    private PhaseDetailProgressService phaseDetailProgressService;


    /**
     * 添加CourseStudyhistory
     *
     * @param courseStudyhistory 要添加的CourseStudyhistory
     * @return id
     */
    public java.lang.Long addCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return courseStudyhistoryDao.addCourseStudyhistory(courseStudyhistory);
    }

    /**
     * 添加播放记录和播放次数
     */
    public void playertimes(CourseStudyhistory courseStudyhistory) {
        Course course = courseService.getCourseById(courseStudyhistory.getCourseId());
        // 判断课程不为空
        if (ObjectUtils.isNull(course)) {
            return;
        }
        // 判断节点不为空
        CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(courseStudyhistory.getKpointId());
        if (ObjectUtils.isNull(courseKpoint)) {
            return;
        }

        // 更新 用户课程节点统计表
        userCourseKpointStatisticService.updatePlayCount(courseStudyhistory);

        // 根据用户id节点id判读记录是否为空
        CourseStudyhistory studyhistory = this.getCourseStudyhistory(courseStudyhistory);
        if (ObjectUtils.isNull(studyhistory)) {
            courseKpoint.setLookNumber(courseKpoint.getLookNumber() + 1);
            courseKpointService.updateCourseKpoint(courseKpoint);
        }
        // 更新节点播放次数加1
        courseKpointService.updateCourseKpointPlaycountAdd(courseStudyhistory.getKpointId());
        CourseStudyhistory tempHistory = new CourseStudyhistory();
        tempHistory.setUserId(courseStudyhistory.getUserId());
        tempHistory.setCourseId(courseStudyhistory.getCourseId());
        //查询是否有该课程观看记录
        List<CourseStudyhistory> courseStudyList = getCourseStudyhistoryList(tempHistory);
        if (courseStudyList == null || courseStudyList.size() == 0) {
            //更新观看人数加1
            courseProfileService.updateCourseProfile(CourseProfileType.watchpersoncount.toString(), courseStudyhistory.getCourseId(), 1L, CourseProfile.ADD);
        }
        // 更新课程播放次数加1
        courseProfileService.updateCourseProfile(CourseProfileType.playcount.toString(), courseStudyhistory.getCourseId(), 1L, CourseProfile.ADD);
        // 查询是否添加过记录
        List<CourseStudyhistory> courseStudyhistoryList = getCourseStudyhistoryList(courseStudyhistory);
        if (ObjectUtils.isNull(courseStudyhistoryList)) {
            // 如果没有添加过则添加记录
            // 填充数据
            courseStudyhistory.setKpointName(courseKpoint.getName());
            courseStudyhistory.setCourseName(course.getName());
            courseStudyhistory.setUpdateTime(new Date());
            courseStudyhistory.setDatabak(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + ",");
            courseStudyhistory.setPlayercount(1L);
            addCourseStudyhistory(courseStudyhistory);

            //观看动态 已经做判断 动态无需判断
            Map<String, String> map = new HashMap<String, String>();
            map.put("userId", courseStudyhistory.getUserId() + "");//用户id
            map.put("bizId", courseStudyhistory.getCourseId() + "");// 学员活动（事件）id 商品id 试卷id
            map.put("type", 11 + "");//11观看课程 12购买了商品 13 考试
            map.put("desc", "观看了课程");// 动态描述
            map.put("title", course.getName() + "--" + courseKpoint.getName());// 辅助title
            map.put("assistId", courseKpoint.getId() + "");// 辅助id
            String content = WebUtils.replaceTagHTML(course.getTitle());
            if (StringUtils.isNotEmpty(content)) {// 回复的内容
                content = StringUtils.getLength(content, 300);
                map.put("content", content);
            } else {
                map.put("content", content);
            }
            map.put("url", CommonConstants.contextPath + "/front/couinfo/" + courseStudyhistory.getCourseId());//操作url
            // 获得社区的开关是否打开
            Map<String, Object> websiteProfile = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.keyword.toString());
            if (ObjectUtils.isNotNull(websiteProfile)) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map1 = (Map<String, Object>) websiteProfile.get(WebSiteProfileType.keyword.toString());
                if (map1.get("verifySns").toString().equalsIgnoreCase("no")) { // 如果开关打开
                    snsHessianService.addDynamic(map);
                }
            }

        } else {
            // 如果添加过则更新记录
            CourseStudyhistory courseStudy = courseStudyhistoryList.get(0);
            courseStudy.setKpointName(courseKpoint.getName());
            courseStudy.setCourseName(course.getName());
            courseStudy.setUpdateTime(new Date());
            // 更新时间记录存字段
            courseStudy.setDatabak(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "," + courseStudy.getDatabak());
            // 当字符串大于500时截取，留前面最新的
            if (courseStudy.getDatabak().length() > 500) {
                courseStudy.setDatabak(courseStudy.getDatabak().substring(0, 500));
            }
            courseStudy.setPlayercount(courseStudy.getPlayercount() + 1);
            updateCourseStudyhistory(courseStudy);
        }

    }

    /**
     * 根据id删除一个CourseStudyhistory
     *
     * @param id 要删除的id
     */
    public void deleteCourseStudyhistoryById(Long id) {
        courseStudyhistoryDao.deleteCourseStudyhistoryById(id);
    }

    /**
     * 修改CourseStudyhistory
     *
     * @param courseStudyhistory 要修改的CourseStudyhistory
     */
    public void updateCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        courseStudyhistoryDao.updateCourseStudyhistory(courseStudyhistory);
    }

    /**
     * 根据id获取单个CourseStudyhistory对象
     *
     * @param id 要查询的id
     * @return CourseStudyhistory
     */
    public CourseStudyhistory getCourseStudyhistoryById(Long id) {
        return courseStudyhistoryDao.getCourseStudyhistoryById(id);
    }

    /**
     * 获取单个CourseStudyhistory
     *
     * @param courseStudyhistory
     * @return
     */
    public CourseStudyhistory getCourseStudyhistory(CourseStudyhistory courseStudyhistory) {
        return courseStudyhistoryDao.getCourseStudyhistory(courseStudyhistory);
    }

    /**
     * 根据条件获取CourseStudyhistory列表
     *
     * @param courseStudyhistory 查询条件
     * @return List<CourseStudyhistory>
     */
    public List<CourseStudyhistory> getCourseStudyhistoryList(CourseStudyhistory courseStudyhistory) {
        return courseStudyhistoryDao.getCourseStudyhistoryList(courseStudyhistory);
    }

    @Override
    public List<CourseStudyhistory> getCourseStudyhistoryListByCondition(CourseStudyhistory courseStudyhistory, PageEntity page) {
        // TODO Auto-generated method stub
        return courseStudyhistoryDao.getCourseStudyhistoryListByCondition(courseStudyhistory, page);
    }

    @Override
    public List<CourseStudyhistory> getCourseStudyhistoryListGroupByCourseId(CourseStudyhistory courseStudyhistory, PageEntity page) {
        return courseStudyhistoryDao.getCourseStudyhistoryListGroupByCourseId(courseStudyhistory, page);
    }


    /**
     * 根据ids删除CourseStudyhistory
     *
     * @param ids 要删除的id
     */
    public void delCourseStudyhistory(String ids) {
        courseStudyhistoryDao.delCourseStudyhistory(ids);
    }

    /**
     * 清空播放记录
     *
     * @param userId
     */
    public void cleanCourseStudyhistory(Long userId) {
        courseStudyhistoryDao.cleanCourseStudyhistory(userId);
    }

    @Override
    public void updateCourseStudyhistoryPlayTime(CourseStudyhistory courseStudyhistory) {
        List<CourseStudyhistory> courseStudyhistoryList = getCourseStudyhistoryList(courseStudyhistory);
        if (ObjectUtils.isNotNull(courseStudyhistoryList)) {
            // 个人纪录播放时长
            CourseStudyhistory _courseStudyhistory = courseStudyhistoryList.get(0);
            _courseStudyhistory.setPlayTime(_courseStudyhistory.getPlayTime() + courseStudyhistory.getPlayTime());
            courseStudyhistoryDao.updateCourseStudyhistoryPlayTime(_courseStudyhistory);

            // 课节时长
            CourseKpoint courseKpoint = courseKpointService.getCourseKpointById(courseStudyhistory.getKpointId());
            if (ObjectUtils.isNotNull(courseKpoint)) {
                courseKpoint.setPlayTime(courseKpoint.getPlayTime() + courseStudyhistory.getPlayTime());
                courseKpointService.updateCourseKpoint(courseKpoint);

                // 课程时长
                Course course = courseService.getCourseById(courseKpoint.getCourseId());
                if (ObjectUtils.isNotNull(course)) {
                    course.setPlayTime(course.getPlayTime() + courseStudyhistory.getPlayTime());
                    courseService.updateCoursePlayTime(course);

                    // 修改学习计划时长
                    PhaseDetailProgress progress = new PhaseDetailProgress();
                    progress.setCourseId(course.getId());
                    progress.setUserId(courseStudyhistory.getUserId());
                    List<PhaseDetailProgress> progressList = phaseDetailProgressService.getPhaseDetailProgressList(progress);
                    if (ObjectUtils.isNotNull(progressList)) {
                        for (PhaseDetailProgress _progress : progressList) {
                            _progress.setComplete(5);
                            phaseDetailProgressService.updateCompleteTime(_progress);
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getLearnCourseNum(Long userId, Long groupId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("groupId", groupId);
        return courseStudyhistoryDao.getLearnCourseNum(map);
    }
}