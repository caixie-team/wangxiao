package io.wangxiao.edu.home.service.impl.answer;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.commons.util.ObjectUtils;
import io.wangxiao.edu.home.dao.answer.AnswerQuestionDao;
import io.wangxiao.edu.home.entity.answer.AnswerQuestion;
import io.wangxiao.edu.home.entity.user.UserExpandDto;
import io.wangxiao.edu.home.service.answer.AnswerQuestionService;
import io.wangxiao.edu.home.service.user.UserExpandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 学生答疑提问表
 */
@Service("answerQuestionService")
public class AnswerQuestionServiceImpl implements AnswerQuestionService {
    @Autowired
    private AnswerQuestionDao answerQuestionDao;
    @Autowired
    private UserExpandService userExpandService;

    /**
     * 添加答疑
     */
    public void addAnswerQuestion(AnswerQuestion answer) {
        answerQuestionDao.addAnswerQuestion(answer);
    }

    /**
     * 更新答疑状态
     */
    public void updateAnswerQuestionStatus(AnswerQuestion answer) {
        answerQuestionDao.updateAnswerQuestionStatus(answer);
    }

    /**
     * 更新回复次数
     */
    public void updateReplyCount(AnswerQuestion answer) {
        answerQuestionDao.updateReplyCount(answer);
    }

    /**
     * 删除答疑
     */
    public void delAnswerQuestion(Long id) {
        answerQuestionDao.delAnswerQuestion(id);
    }

    /**
     * 前台查询问答集合
     *
     * @throws Exception
     */
    public List<AnswerQuestion> getAnswerQuestionList(AnswerQuestion answer, PageEntity page) throws Exception {
        List<AnswerQuestion> answerList = answerQuestionDao.getAnswerQuestion(answer, page);
        if (ObjectUtils.isNotNull(answerList)) {
            List<Long> cusIdlist = new ArrayList<Long>();
            for (AnswerQuestion a : answerList) {
                cusIdlist.add(a.getUserId());
            }
            //通过用户id查询出customer的map
            Map<String, UserExpandDto> map = userExpandService.queryCustomerInCusIds(cusIdlist);
            if (ObjectUtils.isNotNull(map)) {
                for (AnswerQuestion a : answerList) {
                    UserExpandDto customer = map.get(a.getUserId().toString());
                    if (ObjectUtils.isNotNull(customer)) {
                        a.setShowName(customer.getShowname());
                        a.setQueryCustomer(customer);
                    }
                }
            }
        }
        return answerList;
    }

    /**
     * 后台查询答疑
     */
    public List<AnswerQuestion> queryCourseAnswerQuestionList(AnswerQuestion answer, PageEntity page) {
        return answerQuestionDao.queryCourseAnswerQuestionList(answer, page);
    }

    /**
     * 查询答疑详情
     */
    public AnswerQuestion queryAnswerQuestionInfo(AnswerQuestion answer) {
        return answerQuestionDao.queryAnswerQuestionInfo(answer);
    }

    /**
     * 更新状态为已回复
     */
    public void updateIsReply(Long answerId) {
        answerQuestionDao.updateIsReply(answerId);
    }

    /**
     * 查询我的考试答疑
     */
    public List<AnswerQuestion> queryMyExamAnswerQuestionList(Long userId, PageEntity page) {
        return answerQuestionDao.queryMyExamAnswerQuestionList(userId, page);
    }

    /**
     * 查询我的课程答疑
     */
    public List<AnswerQuestion> queryMyCouAnswerQuestionList(Long userId, PageEntity page) {
        return answerQuestionDao.queryMyCouAnswerQuestionList(userId, page);
    }

    /**
     * 更新答疑状态
     */
    public void updateAnswerQuestion(AnswerQuestion answer) {
        answerQuestionDao.updateAnswerQuestion(answer);
    }
}
