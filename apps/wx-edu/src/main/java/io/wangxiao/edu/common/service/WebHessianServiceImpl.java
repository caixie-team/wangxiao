package io.wangxiao.edu.common.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.entity.arrange.ArrangeRecord;
import io.wangxiao.edu.home.entity.course.CourseDto;
import io.wangxiao.edu.home.entity.user.UserExpand;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.arrange.ArrangeRecordService;
import io.wangxiao.edu.home.service.course.CourseProfileService;
import io.wangxiao.edu.home.service.course.CourseService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import io.wangxiao.edu.home.service.user.UserIntegralService;
import io.wangxiao.edu.sysuser.service.KeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

@Service("webHessianService")
public class WebHessianServiceImpl implements WebHessianService {
    private static final Logger logger = LoggerFactory.getLogger(WebHessianServiceImpl.class);
    @Autowired
    private UserIntegralService userIntegralService;
    @Autowired
    private UserExpandService userExpandService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private KeywordService keywordService;
    @Autowired
    private CourseProfileService courseProfileService;
    @Autowired
    private ArrangeRecordService arrangeRecordService;//考试任务记录服务

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 修改用户最后考试的专业
     */
    public void updateUserExpandForSubject(Map<String, String> map) {
        Long userId = Long.parseLong(map.get("userId"));// 用户id
        Long subjectId = Long.parseLong(map.get("subjectId"));//辅助id
        UserExpand userExpand = new UserExpand();
        userExpand.setCusId(userId);
        userExpand.setStudysubject(subjectId);
        userExpandService.updateUserExpandForSubject(userExpand);
    }


    /**
     * 查询用户信息
     *
     * @param cusId
     * @return
     */
    public Map<String, String> getUserInfo(Long cusId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //用户信息
            UserExpandDto userExpandDto = userExpandService.getUserExpandByUids(cusId);
            //转Json
            String userJson = gson.toJson(userExpandDto).toString();
            map.put("userJson", userJson);
        } catch (Exception e) {
            logger.error("WebHessianServiceImpl.getUserInfo-----error", e);
        }
        return map;
    }

    /**
     * 批量查询用户信息
     *
     * @param cusIds
     * @return
     */
    public Map<String, String> getUserInfoByUids(String cusIds) {
        Map<String, String> userMap = new HashMap<String, String>();
        try {
            //批量用户信息
            Map<String, UserExpandDto> map = userExpandService.getUserExpandByUids(cusIds);
            //转为String用户集合
            List<String> userExpandDtos = new ArrayList<String>();
            for (Entry<String, UserExpandDto> entry : map.entrySet()) {
                userExpandDtos.add(gson.toJson(entry.getValue()));
            }
            //转Json
            String usersJson = gson.toJson(userExpandDtos).toString();
            userMap.put("usersJson", usersJson);
        } catch (Exception e) {
            logger.error("WebHessianServiceImpl.getUserInfoByUids-----error", e);
        }
        return userMap;
    }


    /**
     * 添加积分
     *
     * @param map
     */
    public void addUserIntegral(Map<String, String> map) {
        try {
            String keyWord = map.get("keyWord");//关键字
            Long userId = Long.parseLong(map.get("userId"));// 用户id
            Long other = Long.parseLong(map.get("other")); //辅助id
            Long fromUserId = Long.parseLong(map.get("fromUserId"));// 来源id
            String otherScore = map.get("otherScorel"); // otherScore
            userIntegralService.addUserIntegral(keyWord, userId, other, fromUserId, otherScore);
        } catch (Exception e) {
            logger.error("WebHessianServiceImpl.addUserIntegral-----error", e);
        }
    }

    /**
     * 修改CourseProfile浏览次数
     *
     * @param map
     */
    public void updateCourseProfile(Map<String, String> map) {
        String type = map.get("type");
        Long courseId = Long.parseLong(map.get("courseId"));
        Long count = Long.parseLong(map.get("count"));
        String operation = map.get("operation");
        courseProfileService.updateCourseProfile(type, courseId, count, operation);
    }


    /**
     * 通过标识更新未读数加一或清零
     */
    public void readMsgNumAddOrReset(String falg, Long cusId, String iType) {
        if (iType.equals("add")) {//添加
            userExpandService.updateUnReadMsgNumAddOne(falg, cusId);
        } else if (iType.equals("reset")) {//清零
            userExpandService.updateUnReadMsgNumReset(falg, cusId);
        }
    }

    /**
     * 更新用户的上传统计系统消息时间
     */
    public void updateCusForLST(Long cusId, Long date) {
        userExpandService.updateCusForLST(cusId, new Date(date));
    }

    /**
     * 修改UserExpand浏览次数
     *
     * @param map
     */
    public void updateUserExpandCount(Map<String, String> map) {
        String type = map.get("type");
        Long userId = Long.parseLong(map.get("cusId"));
        Long count = Long.parseLong(map.get("count"));
        String operation = map.get("operation");
        UserExpand userExpand = userExpandService.getUserExpandByUserId(userId);
        if (operation.equals("-")) {
            if (type.equals("weiboNum") && userExpand.getWeiBoNum() > 0) {
                userExpandService.updateUserExpandCount(type, userId, count, operation);
            }
            if (type.equals("fansNum") && userExpand.getFansNum() > 0) {
                userExpandService.updateUserExpandCount(type, userId, count, operation);
            }
            if (type.equals("msgNum") && userExpand.getMsgNum() > 0) {
                userExpandService.updateUserExpandCount(type, userId, count, operation);
            }
            if (type.equals("attentionNum") && userExpand.getAttentionNum() > 0) {
                userExpandService.updateUserExpandCount(type, userId, count, operation);
            }
            if (type.equals("sysMsgNum") && userExpand.getSysMsgNum() > 0) {
                userExpandService.updateUserExpandCount(type, userId, count, operation);
            }
            if (type.equals("unreadFansNum") && userExpand.getUnreadFansNum() > 0) {
                userExpandService.updateUserExpandCount(type, userId, count, operation);
            }
        } else {
            userExpandService.updateUserExpandCount(type, userId, count, operation);
        }
    }


    /**
     * 查询全部好友
     *
     * @param map
     * @return
     */
    public Map<String, String> queryAllCustomer(Map<String, String> map) {
        String email = map.get("email");//邮箱
        Long cusId = Long.parseLong(map.get("cusId"));//用户id
        int pageSize = Integer.parseInt(map.get("pageSize"));//分页size
        int currentPage = Integer.parseInt(map.get("currentPage"));//当前页数
        UserExpandDto customer = new UserExpandDto();
        customer.setCusId(cusId);
        customer.setEmail(email);
        PageEntity page = new PageEntity();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        //用户集合
        List<UserExpandDto> users = userExpandService.queryAllCustomer(customer, page);
        List<String> usersString = new ArrayList<String>();
        for (UserExpandDto u : users) {
            usersString.add(gson.toJson(u));
        }
        //转成json
        String usersJson = gson.toJson(usersString);
        String pageJson = gson.toJson(page);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("usersJson", usersJson);
        resultMap.put("pageJson", pageJson);
        return resultMap;
    }


    /**
     * 通过showname 查询customer(精确搜索)
     */
    public Map<String, String> queryCustomerByShowNameEquals(String showName) {
        //用户集合
        List<UserExpandDto> users = userExpandService.queryCustomerByShowNameEquals(showName);
        List<String> usersString = new ArrayList<String>();
        for (UserExpandDto u : users) {
            usersString.add(gson.toJson(u));
        }
        //转成json
        String usersJson = gson.toJson(usersString).toString();
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("usersJson", usersJson);
        return resultMap;
    }


    /**
     * 通过showname 查询customer
     *
     * @param showName
     */
    public Map<String, String> queryCustomerByShowName(String showName, int size) {
        //用户集合
        List<UserExpandDto> users = userExpandService.queryCustomerByShowName(showName, size);
        //转成json
        String usersJson = gson.toJson(users).toString();
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("usersJson", usersJson);
        return resultMap;
    }

    /**
     * 首页获取推荐的课程 封装为map
     *
     * @param recommendId
     * @return
     * @throws Exception
     */
    public Map<String, String> getCourseListByHomePage(Long recommendId) {
        Map<String, String> resultMap = new HashMap<String, String>();
        try {
            //批量课程信息
            Map<String, List<CourseDto>> map = courseService.getCourseListByHomePage(recommendId);
            Map<String, String> courseMap = new HashMap<String, String>();
            for (Entry<String, List<CourseDto>> entry : map.entrySet()) {
                //course对象转为json再存入集合
                List<String> coursesString = new ArrayList<String>();
                for (CourseDto course : entry.getValue()) {
                    Map<String, String> mapTemp = new HashMap<String, String>();
                    mapTemp.put("id", course.getId() + "");//课程id
                    mapTemp.put("logo", course.getLogo());//课程封面
                    mapTemp.put("name", course.getName());//课程名称
                    mapTemp.put("lessionnum", course.getLessionnum() + "");//课时
                    coursesString.add(gson.toJson(mapTemp));
                }
                courseMap.put(entry.getKey(), gson.toJson(coursesString));
            }
            //转Json
            String coursesMapJson = gson.toJson(courseMap).toString();
            resultMap.put("coursesMapJson", coursesMapJson);
        } catch (Exception e) {
            logger.error("WebHessianServiceImpl.getCourseListByHomePage-----error", e);
        }
        return resultMap;
    }

    /**
     * 关键字过滤
     *
     * @param content
     * @return
     */
    public String doFilter(String content) {
        return keywordService.doFilter(content);
    }

    /**
     * 修改考试安排任务记录信息
     *
     * @param map
     * @return
     */
    @Override
    public void updateArrangeRecord(Map<String, Object> map) {
        Long score = (long) map.get("score");
        String arrangeRecordId = (String) map.get("arrangeRecordId");
        Long examRecordId = (Long) map.get("examRecordId");
        BigDecimal bdScore = new BigDecimal(score);
        //考试记录
        ArrangeRecord arrangeRecord = arrangeRecordService.getArrangeRecordAndArrangeById(Long.parseLong(arrangeRecordId));
        arrangeRecord.setScore(bdScore);//分数
        arrangeRecord.setExamRecordId(examRecordId);//考试记录id
        if (arrangeRecord.getIsComplete() == 0l) {//未完成
            arrangeRecord.setIsComplete(1L);
            arrangeRecord.setSubmitTime(new Date());
            arrangeRecordService.updateArrangeRecordBySubmit(arrangeRecord);
        } else {
            if (arrangeRecord.getIsComplete() == 1l) {//1可以重复
                //重复考试大于之前考试分数的取最大的
                if (bdScore.compareTo(arrangeRecord.getScore()) > 0) {//-1表示小于，0是等于，1是大于。

                    arrangeRecordService.updateArrangeRecordBySubmit(arrangeRecord);
                }
            }
        }
    }

    @Override
    public Map<String, String> queryUserExpandDtoPage(Map<String, String> map) {
        String queryKeyWord = map.get("queryKeyWord");//
        int pageSize = Integer.parseInt(map.get("pageSize"));//分页size
        int currentPage = Integer.parseInt(map.get("currentPage"));//当前页数
        UserExpandDto customer = new UserExpandDto();
        customer.setQueryKeyWord(queryKeyWord);
        PageEntity page = new PageEntity();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);

        //用户集合
        List<UserExpandDto> users = userExpandService.queryUserExpandDtoPage(customer, page);
        List<String> usersString = new ArrayList<String>();
        if (ObjectUtils.isNotNull(users)) {
            for (UserExpandDto u : users) {
                usersString.add(gson.toJson(u));
            }
        }
        //转成json
        String usersJson = gson.toJson(usersString);
        String pageJson = gson.toJson(page);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("usersJson", usersJson);
        resultMap.put("pageJson", pageJson);
        return resultMap;
    }

    /**
     * 判断安排的考试是否完成（不可重复直接判断，可重复，直接为未完成，进行考试，若考试得分更高，修改最终得分）
     *
     * @param arrangeRecordId
     * @return
     */
    public Map<String, String> ifCompletedExam(Long arrangeRecordId) {
        Map<String, String> map = new HashMap<>();
        ArrangeRecord arrangeRecord = arrangeRecordService.getArrangeRecordAndArrangeById(arrangeRecordId);
        if (arrangeRecord.getIsComplete() == 0l) {//未完成
            map.put("notComplete", "yes");//
        } else {
            if (arrangeRecord.getIsComplete() == 1l) {//1可以重复
                map.put("allowRepeat", "yes");//
            }
        }
        return map;
    }
}
