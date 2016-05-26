package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.question.QuestErrorCheck;

/**
 * @author :
 * @ClassName com.atdld.os.exam.dao.exampaper.QuestErrorCheck
 * @description
 * @Create Date : 2014年4月23日 下午3:42:10
 */
public interface QuestErrorCheckDao {
    /**
     * 添加纠错内容
     *
     * @param questErrorCheck
     * @return
     */
    public Long addQuestErrorCheck(QuestErrorCheck questErrorCheck);

    /**
     * 查询纠错列表
     *
     * @param questErrorCheck
     * @param page
     * @return
     */
    public List<QuestErrorCheck> queryQuestErrorCheckList(QuestErrorCheck questErrorCheck, PageEntity page);

    /**
     * 删除questErrorCheck
     *
     * @param questErrorCheck
     * @return
     */
    public Long delQuestErrorCheckById(QuestErrorCheck questErrorCheck);
}
