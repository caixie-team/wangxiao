package com.atdld.os.exam.service.impl.exampaper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.common.service.SnsHessianService;
import com.atdld.os.common.service.WebHessianService;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.dao.customer.CusDateRecordDao;
import com.atdld.os.exam.dao.exampaper.ExamPaperDao;
import com.atdld.os.exam.dao.exampaper.ExamPaperRecordDao;
import com.atdld.os.exam.dao.question.ErrorQuestionDao;
import com.atdld.os.exam.dao.question.QuestionRecordDao;
import com.atdld.os.exam.entity.customer.CusDateRecord;
import com.atdld.os.exam.entity.customer.ExamUserExpandDto;
import com.atdld.os.exam.entity.exampaper.ExamPaper;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.exampaper.QueryPaperMiddle;
import com.atdld.os.exam.entity.exampaper.QueryPaperRecord;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;
import com.atdld.os.exam.entity.question.QuestionRecord;
import com.atdld.os.exam.entity.question.QuestionRecordBean;
import com.atdld.os.exam.service.exampaper.ExamPaperRecordService;
import com.atdld.os.exam.service.user.ExamUserService;


/**
 * @author
 * @ClassName ExamPaperServiceImpl
 * @package com.atdld.os.exam.service.impl.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:22:52
 */
@Service("examPaperRecordService")
public class ExamPaperRecordServiceImpl implements ExamPaperRecordService {
    @Autowired
    private ExamPaperRecordDao examPaperRecordDao;
    @Autowired
    private QuestionRecordDao questionRecordDao;
    @Autowired
    private ExamPaperDao examPaperDao;
    @Autowired
    private SnsHessianService snsHessianService;
    @Autowired
    private CusDateRecordDao cusDateRecordDao;
    @Autowired
    private ExamUserService examUserService;
    @Autowired
    private WebHessianService webHessianService;
    private MemCache memCache = MemCache.getInstance();

    /**
     * 获得该用户试卷记录列表
     */
    public List<QueryPaperRecord> queryExamPaperRecordList(PaperRecord paperRecord,
                                                           PageEntity pageEntity) {
    	List<QueryPaperRecord> examPaperRecordList = examPaperRecordDao.queryExamPaperRecordList(paperRecord, pageEntity);
        return examPaperRecordList;
    }

    /**
     * 通过条件获得试卷记录列表
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
	public List<QueryPaperRecord> queryExamPaperRecordAllList(QueryPaperRecord queryPaperRecord, PageEntity pageEntity) {
    	
        List<QueryPaperRecord> examPaperRecordList = examPaperRecordDao.queryExamPaperRecordAllList(queryPaperRecord, pageEntity);
        if(examPaperRecordList!=null&&examPaperRecordList.size()>0){
        	String cusIds="";
    		for(QueryPaperRecord examPaperRecord:examPaperRecordList){
    			cusIds+=examPaperRecord.getCusId()+",";
    		}
    		Map<String, ExamUserExpandDto> map = examUserService.getUserExpandsByCusId(cusIds);
    		List<QueryPaperRecord> tempList = new ArrayList<>();
            for (QueryPaperRecord qpr : examPaperRecordList) {
            	ExamUserExpandDto examUserExpandDto = map.get(qpr.getCusId()+""); 
                if(examUserExpandDto!=null){
                	String email = examUserExpandDto.getEmail();
                	if(email!=null){
                		qpr.setEmail(email);
                	}
                }
	            if(queryPaperRecord.getEmail()!=null&&!queryPaperRecord.getEmail().equals("")){
	            	if(qpr.getEmail()!=null&&qpr.getEmail().indexOf(queryPaperRecord.getEmail())==-1){
	            		tempList.add(qpr);
	            	}else if(qpr.getEmail()==null){
	            		tempList.add(qpr);
	            	}
	            }
			}
	        if(tempList!=null&&tempList.size()>0){
	        	examPaperRecordList.removeAll(tempList);
	        }
        }
        return examPaperRecordList;
    }

    /**
     * 通过条件获得包含主观题的试卷记录列表
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public List<QueryPaperRecord> queryExamPaperRecordAllListBySubjective(QueryPaperRecord queryPaperRecord,
                                                                          PageEntity pageEntity) {
    	List<QueryPaperRecord> examPaperRecordList = examPaperRecordDao.queryExamPaperRecordAllListBySubjective(queryPaperRecord, pageEntity);
    	if(examPaperRecordList!=null&&examPaperRecordList.size()>0){
    		String cusIds="";
    		for(QueryPaperRecord examPaperRecord:examPaperRecordList){
    			cusIds+=examPaperRecord.getCusId()+",";
    		}
    		Map<String, ExamUserExpandDto> map = examUserService.getUserExpandsByCusId(cusIds);
    		List<QueryPaperRecord> tempList = new ArrayList<>();
            for (QueryPaperRecord qpr : examPaperRecordList) {
            	ExamUserExpandDto examUserExpandDto = map.get(qpr.getCusId()+""); 
                if(examUserExpandDto!=null){
                	String email = examUserExpandDto.getEmail();
                	if(email!=null){
                		qpr.setEmail(email);
                	}
                }
                if(queryPaperRecord.getEmail()!=null&&!queryPaperRecord.getEmail().equals("")){
                	if(qpr.getEmail()!=null&&qpr.getEmail().indexOf(queryPaperRecord.getEmail())==-1){
                		tempList.add(qpr);
                	}else if(qpr.getEmail()==null){
                		tempList.add(qpr);
                	}
                }
    		}
            if(tempList!=null&&tempList.size()>0){
            	examPaperRecordList.removeAll(tempList);
            }
    	} 
    	
        return examPaperRecordList;
    }


    /**
     * 通过试卷id查询每个试题多少分
     *
     * @param queryPaperRecord
     * @param pageEntity
     * @return
     */
    public Map<Long, QueryPaperMiddle> queryPaperMiddleMap(Long paperId) {
        Map<Long, QueryPaperMiddle> map = new HashMap<Long, QueryPaperMiddle>();
        //查询出该试卷下每个每个试题多少分
        List<QueryPaperMiddle> queryPaperMiddleList = examPaperRecordDao.queryPaperMiddleMap(paperId);
        //循环放入map中
        if (ObjectUtils.isNotNull(queryPaperMiddleList)) {
            for (QueryPaperMiddle qpm : queryPaperMiddleList) {
                map.put(qpm.getQstId(), qpm);
            }
        }
        return map;
    }

    // 获取做够试卷的结果 
    public PaperRecord queryCheckPaperResult(PaperRecord paperRecord) {
        return examPaperRecordDao.checkPaperResult(paperRecord);
    }

    /**
     * 随机考试：我要交卷或者第一次点下次再做
     */
    public void addRandomPaperRecord(String paperTitle,
                                     List<QuestionRecordBean> recordBeans, PaperRecord paperRecord) {
        ExamPaper paper = new ExamPaper();
        paper.setAddTime(new Date());
        paper.setAuthor(paperTitle);
        paper.setAvgScore(0);
        paper.setInfo(paperTitle);
        paper.setJoinNum(0);
        paper.setLevel(1);
        paper.setName(paperTitle);
        paper.setQstCount(5);
        paper.setReplyTime(paperRecord.getReplyTime());
        paper.setScore(100);
        paper.setStatus(0);
        paper.setSubjectId(paperRecord.getSubjectId());
        paper.setType(0);
        paper.setUpdateTime(new Date());
        // 随机试题添加试卷
        examPaperDao.addExamPaper(paper);
        paperRecord.setEpId(paper.getId());
        paperRecord.setPaperName(paper.getName());
        // 添加paperRecord 和questionRecord的记录
        addPaperRecord(recordBeans, paperRecord);
    }

    /**
     * 随机考试：我要交卷或者第一次点下次再做
     */
    public void updateRandomPaperRecord(List<QuestionRecordBean> recordBeans,
                                        PaperRecord paperRecord) {
        // 更新paperRecord 更新试题组卷和更新专题智能练习都是对paperRecord和questionRecord进行操作 且操作一样
        updatePaperRecord(recordBeans, paperRecord);
    }


    /**
     * 系统试卷：我要交卷或者第一次点下次再做
     */
    public void addPaperRecord(List<QuestionRecordBean> recordBeans,
                               PaperRecord paperRecord) {
        int userScore = 0;
        float accuracy = 0;
        int testTime = paperRecord.getTestTime();
        int qstCount = recordBeans.size();// qstIdsLite
        int correctNum = 0;
        List<QuestionRecord> questionRecordList = new ArrayList<QuestionRecord>();
        // 回答错误的id放入这个list中
        List<QueryErrorQuestion> queryErrorQuestionList = new ArrayList<QueryErrorQuestion>();
        // 做正确试题的id放如这里
        List<QueryErrorQuestion> queryRightQuestionList = new ArrayList<QueryErrorQuestion>();
        for (int i = 0; i < recordBeans.size(); i++) {
            QuestionRecordBean recordBean = recordBeans.get(i);
            QuestionRecord questionRecord = new QuestionRecord();
            String userAnswer = recordBean.getUserAnswer().trim().replace(",", "");
            //去掉用户答案的逗号
            questionRecord.setUserAnswer(userAnswer);
            questionRecord.setAddTime(new Date());
            questionRecord.setCusId(paperRecord.getCusId());
            questionRecord.setPaperId(paperRecord.getEpId());
            String qstId = recordBean.getQstIdsLite().trim();
            questionRecord.setQstId(Long.valueOf(qstId));
            questionRecord.setQstType(recordBean.getQstType());//冗余字段
            questionRecord.setPointId(recordBean.getPointId());//冗余字段
            if (!ObjectUtils.isNull(recordBean.getPaperMiddle())) {
                questionRecord.setPaperMiddleId(recordBean.getPaperMiddle());
            }
            int score = recordBean.getScore();
            // 当用户答案为null 或者用户答案不等于答案 或为主观题  主观题后台判卷
            if (recordBean.getUserAnswer().trim().equals("")
                    || recordBean.isRight() || recordBean.getQstType() == 6) {
                questionRecord.setStatus(1);
                questionRecord
                        .setUserAnswer(recordBean.getUserAnswer().trim().equals("") ? ""
                                : userAnswer);
                QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
                queryErrorQuestion.setAddTime(new Date());
                queryErrorQuestion.setQstId(questionRecord.getQstId());
                queryErrorQuestionList.add(queryErrorQuestion);

            } else {// 当用户回答正确
                questionRecord.setUserAnswer(userAnswer);
                questionRecord.setStatus(0);
                userScore += score;
                //添加试题得分
                questionRecord.setScore(score);
                correctNum++;
                QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
                queryErrorQuestion.setQstId(questionRecord.getQstId());
                queryRightQuestionList.add(queryErrorQuestion);
            }
            questionRecordList.add(questionRecord);
        }
        accuracy = (float) correctNum / (float) qstCount;
        paperRecord.setAccuracy(accuracy);
        paperRecord.setAddTime(new Date());
        paperRecord.setCorrectNum(correctNum);
        paperRecord.setQstCount(qstCount);
        paperRecord.setTestTime(testTime);
        paperRecord.setUserScore(userScore);
        paperRecord.setObjectiveScore(new BigDecimal(userScore));
        //提交试卷时主观题为0分
        paperRecord.setSubjectiveScore(new BigDecimal(0));
        // 添加paperRercord
        examPaperRecordDao.addExamPaperRecord(paperRecord);
        //考完时间才添加动态
        if (paperRecord.getStatus() == 0) {
        	try{
        		addSnsDynamic(paperRecord);//添加sns考试动态
        		//添加积分
        		//addWebIntegral(IntegralKeyword.exam.toString(), paperRecord.getCusId(), paperRecord.getEpId(), 0L, "");
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }

        // 删除用户在错题表中再次回答正确的试题
        delQueryErrorQuestionBatch(queryRightQuestionList, paperRecord);
        // 筛选出错题表里没有的数据然后添加
        addQueryErrorQuestionBatch(queryErrorQuestionList, paperRecord);

        // 循环吧新添加的paperRecord的Id放入questionRecordList中
        for (QuestionRecord questionRecord : questionRecordList) {
            questionRecord.setPaperRecordId(paperRecord.getId());
        }

        // 批量添加questionRecord
        questionRecordDao.addQuestionRecordListBatch(questionRecordList);
        // 当用户提交试卷时往用户信息表里添加或更新
        addOrUpdateCusDateRecord(paperRecord);
    }

    //添加sns考试动态
    public void addSnsDynamic(PaperRecord paperRecord) throws Exception {//sns demo一起 重新修改添加动态
        if (ObjectUtils.isNull(paperRecord)) {
            return;
        }
        Map<String, String> map = new HashMap<String, String>();
        //用户id
        map.put("userId", paperRecord.getCusId() + "");
        // 学员活动（事件）id 商品id 试卷id
        map.put("bizId", paperRecord.getEpId() + "");
        //11观看课程 12购买了商品 13 考试
        map.put("type", 13 + "");
        // 动态描述
        map.put("desc", "参加了考试");
        // 辅助title
        map.put("title", paperRecord.getPaperName());
        //活动内容
    /*    Subject subject = new Subject();
        subject.setSubjectId(paperRecord.getSubjectId());
        subject = subjectService.getSubjectBySubjectId(subject);
        map.put("content", "专业：" + subject.getSubjectName());*/
        if (2 == paperRecord.getType()) {
            //操作url
            map.put("url", "/paper/toExamPaper/" + paperRecord.getEpId());
        } else {
            //操作url
            map.put("url", "/quest/toQuestionitemList");
        }
        map.put("assistId",0L+"");// 辅助id
        snsHessianService.addDynamic(map);
    }

    //添加网校考试积分
    public void addWebIntegral(String keyWord, Long userId, Long other, Long fromUserId, String otherScorel) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        //关键字
        map.put("keyWord",keyWord);
        // 用户id
        map.put("userId", userId + "");
        //辅助id
        map.put("other", other + "");
        // 来源id
        map.put("fromUserId", fromUserId + "");
        // otherScore
        map.put("otherScorel", otherScorel);
        webHessianService.addUserIntegral(map);
    }

    // 删除错题表中再次回答正确的题
    public void delQueryErrorQuestionBatch(
            List<QueryErrorQuestion> queryErrorQuestionList, PaperRecord paperRecord) {
        // 应该移除的queryError的questionList
        if (queryErrorQuestionList.size() > 0) {
            errorQuestionDao.delQueryErrorQuestionBatchByQueryErrorQuestionList(
                    queryErrorQuestionList, paperRecord.getCusId());
        }
    }

    public void addOrUpdateCusDateRecord(PaperRecord paperRecord) {
        // 当他的状态为提交时非下次继续练习时才统计
        if (paperRecord.getStatus() == 0) {
            CusDateRecord cusDateRecord = new CusDateRecord();
            cusDateRecord.setCusId(paperRecord.getCusId());
            cusDateRecord.setSubjectId(paperRecord.getSubjectId());
            // cusDateRecord =
            // cusDateRecordDao.queryCusDateRecordByCusIdAndSubjectId(cusDateRecord);
            // 通过examPaperRecord 表中的数据算出该用户在该专业下平均分数
            cusDateRecord.setAverageScore(examPaperRecordDao
                    .queryPaperRecordAverageScore(paperRecord));
            cusDateRecord.setErrorQstNum(paperRecord.getQstCount()
                    - paperRecord.getCorrectNum());
            cusDateRecord.setQstNum(paperRecord.getQstCount());
            cusDateRecord.setRightQstNum(paperRecord.getCorrectNum());
            // 看该用户是否有cusDateRecord如果没有则添加有则更新
            CusDateRecord cusDateRecord1 = cusDateRecordDao
                    .queryCusDateRecordByCusIdAndSubjectId(cusDateRecord);
            if (cusDateRecord1 == null) {
                cusDateRecordDao.addCusDateRecord(cusDateRecord);
            } else {
                cusDateRecord.setId(cusDateRecord1.getId());
                // 更新为累加更新原来的数据添加新的数据
                cusDateRecordDao.updateCusDateRecord(cusDateRecord);
            }
        }
    }

    @Autowired
    private ErrorQuestionDao errorQuestionDao;

    public void addQueryErrorQuestionBatch(
            List<QueryErrorQuestion> queryErrorQuestionList, PaperRecord paperRecord) {
        // 应该移除的queryError的questionList
        if (queryErrorQuestionList.size() > 0) {
            List<QueryErrorQuestion> queryErrorQuestionList2 = errorQuestionDao
                    .queryErrorQuestionByQstId(queryErrorQuestionList,
                            paperRecord.getCusId());
            // 把queryErrorQuestionList 中的qstid筛选
            List<QueryErrorQuestion> queryErrorQuestionList3 = new ArrayList<QueryErrorQuestion>();
            for (QueryErrorQuestion queryErrorQuestion : queryErrorQuestionList) {
                for (QueryErrorQuestion queryErrorQuestion2 : queryErrorQuestionList2) {
                    if (queryErrorQuestion2.getQstId().longValue() == queryErrorQuestion.getQstId().longValue()) {
                        queryErrorQuestionList3.add(queryErrorQuestion);
                    }
                }
                queryErrorQuestion.setCusId(paperRecord.getCusId());
                queryErrorQuestion.setPaperId(paperRecord.getEpId());
                queryErrorQuestion.setPaperRecordId(paperRecord.getId());
                queryErrorQuestion.setSubjectId(paperRecord.getSubjectId());
            }
            for (QueryErrorQuestion queryErrorQuestion : queryErrorQuestionList3) {
                queryErrorQuestionList.remove(queryErrorQuestion);
            }
            if (queryErrorQuestionList.size() > 0) {
                // 如果试卷中有重复试题利用map去重
                queryErrorQuestionList = removeDuplicateWithOrder(queryErrorQuestionList);
                errorQuestionDao.insertQueryErrorQuestion(queryErrorQuestionList);
            }
        }
    }

    // 如果试卷中有重复试题利用map去重
    public List<QueryErrorQuestion> removeDuplicateWithOrder(List<QueryErrorQuestion> list) {
        Map<Long, QueryErrorQuestion> map = new HashMap<Long, QueryErrorQuestion>();
        List<QueryErrorQuestion> newQueryErrorQuestionList = new ArrayList<QueryErrorQuestion>();
        for (QueryErrorQuestion queryErrorQuestion : list) {
            // 试题id为key去重
            map.put(queryErrorQuestion.getQstId(), queryErrorQuestion);
        }
        Iterator<Entry<Long, QueryErrorQuestion>> iter = map.entrySet().iterator(); // 获得map的Iterator
        // 遍历map
        while (iter.hasNext()) {
            Entry<Long, QueryErrorQuestion> entry = (Entry<Long, QueryErrorQuestion>) iter
                    .next();
            newQueryErrorQuestionList.add((QueryErrorQuestion) entry.getValue());
        }
        return newQueryErrorQuestionList;
    }

    /**
     * 系统试卷： 我要交卷或者第一次点下次再做
     */
    public void updatePaperRecord(List<QuestionRecordBean> recordBeans,
                                  PaperRecord paperRecord) {
        int userScore = 0;
        float accuracy = 0;
        int testTime = paperRecord.getTestTime();
        int qstCount = recordBeans.size();
        int correctNum = 0;
        List<QuestionRecord> questionRecordList = new ArrayList<QuestionRecord>();
        // 做正确试题的id放如这里
        List<QueryErrorQuestion> queryRightQuestionList = new ArrayList<QueryErrorQuestion>();
        // 错题列表
        List<QueryErrorQuestion> queryErrorQuestionList = new ArrayList<QueryErrorQuestion>();
        for (int i = 0; i < recordBeans.size(); i++) {
            QuestionRecordBean recordBean = recordBeans.get(i);
            QuestionRecord questionRecord = new QuestionRecord();
            questionRecord.setAddTime(new Date());
            questionRecord.setCusId(paperRecord.getCusId());
            questionRecord.setPaperId(paperRecord.getEpId());
            String answer = recordBean.getAnswerLite().trim();
            String qstId = recordBean.getQstIdsLite().trim();
            questionRecord.setQstId(Long.valueOf(qstId));
            questionRecord.setQstType(recordBean.getQstType());//冗余字段
            questionRecord.setPointId(recordBean.getPointId());//冗余字段
            if (!ObjectUtils.isNull(recordBean.getPaperMiddle())) {
                questionRecord.setPaperMiddleId(recordBean.getPaperMiddle());
            }
            int score = recordBean.getScore();
            String userAnswer = "";
            userAnswer = recordBean.getUserAnswer().trim();
            if (userAnswer.length() > 0) {
                userAnswer = userAnswer.substring(0, userAnswer.length() - 1);
            }
            // 当用户答案为null 或者用户答案不等于答案
            if (ObjectUtils.isNull(recordBean.getUserAnswer())
                    || !userAnswer.equals(answer)) {
                questionRecord.setStatus(1);
                questionRecord
                        .setUserAnswer(recordBean.getUserAnswer().trim().equals("") ? ""
                                : userAnswer);
                QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
                queryErrorQuestion.setAddTime(new Date());
                queryErrorQuestion.setQstId(questionRecord.getQstId());
                queryErrorQuestionList.add(queryErrorQuestion);
            } else {// 当用户回答正确
                questionRecord
                        .setUserAnswer(recordBean.getUserAnswer().trim().equals("") ? ""
                                : userAnswer);
                questionRecord.setStatus(0);
                userScore += score;
                correctNum++;
                QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
                queryErrorQuestion.setQstId(questionRecord.getQstId());
                queryRightQuestionList.add(queryErrorQuestion);
            }
            questionRecordList.add(questionRecord);
        }
        accuracy = (float) correctNum / qstCount;
        paperRecord.setAccuracy(accuracy);
        paperRecord.setAddTime(new Date());
        paperRecord.setCorrectNum(correctNum);
        paperRecord.setQstCount(qstCount);
        paperRecord.setTestTime(testTime);
        paperRecord.setUserScore(userScore);
        // 添加paperRercord
        examPaperRecordDao.updateExamPaperRecordById(paperRecord);
        //考完时间才添加动态
        if (paperRecord.getStatus() == 0) {
        	try{
        		//添加sns考试动态
        		addSnsDynamic(paperRecord);
        		//添加积分
        		//addWebIntegral(IntegralKeyword.exam.toString(), paperRecord.getCusId(), paperRecord.getEpId(), 0L, "");
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        QueryErrorQuestion queryErrorQuestion = new QueryErrorQuestion();
        queryErrorQuestion.setPaperRecordId(paperRecord.getId());
        queryErrorQuestion.setCusId(paperRecord.getCusId());
        queryErrorQuestion.setPaperId(paperRecord.getEpId());
        paperRecord.setSubjectId(paperRecord.getSubjectId());
        errorQuestionDao.delQueryErrorQuestionByPaperRecordId(queryErrorQuestion);
        // 批量删除用户在错题表中再次回答正确的试题
        delQueryErrorQuestionBatch(queryRightQuestionList, paperRecord);
        // 批量添加错题
        addQueryErrorQuestionBatch(queryErrorQuestionList, paperRecord);
        // 循环吧新添加的paperRecord的Id放入questionRecordList中
        for (QuestionRecord questionRecord : questionRecordList) {
            questionRecord.setPaperRecordId(paperRecord.getId());
        }
        // 通过paperRecordId删除
        questionRecordDao.delQuestionRecordByPaperRecordId(questionRecordList.get(0));
        // 批量添加questionRecord
        questionRecordDao.addQuestionRecordListBatch(questionRecordList);
        memCache.remove(MemConstans.MEM_CUS_ERRORQUESTION + paperRecord.getCusId());
        addOrUpdateCusDateRecord(paperRecord);
    }

    public QueryPaperRecord continuePractise(PaperRecord paperRecord) {
        return examPaperRecordDao.queryPaperRecordByCusIdNewest(paperRecord);
    }

    @Override
    public List<QueryPaperRecord> getExamPaperReport(PaperRecord paperRecord) {
        return examPaperRecordDao.getExamPaperReport(paperRecord);
    }

    public PaperRecord queryPaperRecordById(PaperRecord paperRecord) {
        return examPaperRecordDao.queryPaperRecordById(paperRecord);
    }

    /**
     * 值传入cusId获得试卷考试记录 ,需传入专业
     */
    public List<QueryPaperRecord> queryExamPaperRecordListByCusId(PaperRecord paperRecord) {
        return examPaperRecordDao.queryExamPaperRecordListByCusId(paperRecord);
    }

    public int queryExamPaperRecordScoreRanking(PaperRecord paperRecord) {
        return examPaperRecordDao.queryExamPaperRecordScoreRanking(paperRecord);
    }

    public int queryExamPaperRecordCorrectNumRanking(PaperRecord paperRecord) {
        return examPaperRecordDao.queryExamPaperRecordCorrectNumRanking(paperRecord);
    }

    public Date queryExamPaperRecordMaxUpdateTime() {
        return examPaperRecordDao.queryExamPaperRecordMaxUpdateTime();
    }

    /**
     * 给学生阅卷加分
     */
    public void updateExampaperRecordAddScore(Long qstrcdId, Integer score) {
        QuestionRecord questionRecord = questionRecordDao.queryQuestionRecordById(qstrcdId);
        PaperRecord paperRecord = new PaperRecord();
        paperRecord.setId(questionRecord.getPaperRecordId());
        paperRecord = examPaperRecordDao.queryPaperRecordById(paperRecord);
        int newscore = score - questionRecord.getScore();
        String addScore = "";
        if (newscore == 0) {
            return;
        }
        if (newscore > 0) {
            addScore = "+" + newscore;
        }
        if (newscore < 0) {
            addScore = "" + newscore;
        }

        questionRecordDao.updateQuestionRecordForScore(qstrcdId, addScore);
        examPaperRecordDao.updateExamPaperRecordForScore(questionRecord.getPaperRecordId(), addScore);
    }

}
