package com.atdld.os.exam.service.impl.question;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.dao.question.QuestErrorCheckDao;
import com.atdld.os.exam.entity.question.QuestErrorCheck;
import com.atdld.os.exam.service.question.QuestErrorCheckService;

/**
 * @author :
 * @ClassName com.atdld.os.exam.service.impl.exampaper.PaperErrorCheckServiceImpl
 * @description
 * @Create Date : 2014年4月23日 下午4:05:45
 */
@Service("paperErrorCheckService")
public class QuestErrorCheckServiceImpl implements QuestErrorCheckService {
    @Autowired
    private QuestErrorCheckDao questErrorCheckDao;

    /**
     * 添加纠错内容
     *
     * @param questErrorCheck
     * @return
     */
    public String addQuestErrorCheck(QuestErrorCheck questErrorCheck) throws Exception {
        questErrorCheck.setAddTime(new Date());
        Long num = questErrorCheckDao.addQuestErrorCheck(questErrorCheck);
        if (num == 1) {
            return "success";
        } else {
            return "false";
        }
    }

    /**
     * 查询纠错列表
     *
     * @param questErrorCheck
     * @param page
     * @return
     */
    public List<QuestErrorCheck> queryQuestErrorCheckList(QuestErrorCheck questErrorCheck, PageEntity page) throws Exception {
        return questErrorCheckDao.queryQuestErrorCheckList(questErrorCheck, page);
    }

    /**
     * 删除questErrorCheck
     *
     * @param questErrorCheck
     * @return
     */
    public String delQuestErrorCheckById(QuestErrorCheck questErrorCheck) throws Exception {
        Long num = questErrorCheckDao.delQuestErrorCheckById(questErrorCheck);
        if (num == 1) {
            return "success";
        }
        return "false";
    }
}
