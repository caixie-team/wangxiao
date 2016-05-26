package com.atdld.os.exam.service.question;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.question.QuestErrorCheck;

/**
 * @author :
 * @ClassName com.atdld.os.exam.service.exampaper.PaperErrorCheckService
 * @description
 * @Create Date : 2014年4月23日 下午4:04:15
 */
public interface QuestErrorCheckService {
    /**
     * 添加纠错内容
     *
     * @param questErrorCheck
     * @return
     */
    public String addQuestErrorCheck(QuestErrorCheck questErrorCheck) throws Exception;

    /**
     * 查询纠错列表
     *
     * @param questErrorCheck
     * @param page
     * @return
     */
    public List<QuestErrorCheck> queryQuestErrorCheckList(QuestErrorCheck questErrorCheck, PageEntity page) throws Exception;

    /**
     * 删除questErrorCheck
     *
     * @param questErrorCheck
     * @return
     */
    public String delQuestErrorCheckById(QuestErrorCheck questErrorCheck) throws Exception;
}
