package com.atdld.os.exam.dao.impl.question;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.dao.question.QuestErrorCheckDao;
import com.atdld.os.exam.entity.question.QuestErrorCheck;

/**
 * @author :
 * @ClassName com.atdld.os.exam.dao.impl.exampaper.QuestErrorCheckDaoImpl
 * @description
 * @Create Date : 2014年4月23日 下午3:47:17
 */
@Repository("questErrorCheckDao")
public class QuestErrorCheckImpl extends GenericDaoImpl implements QuestErrorCheckDao {
    /**
     * 添加纠错内容
     *
     * @param questErrorCheck
     * @return
     */
    public Long addQuestErrorCheck(QuestErrorCheck questErrorCheck) {
        return this.insert("QuestErrorCheckMapper.addQuestErrorCheck", questErrorCheck);
    }

    /**
     * 查询纠错列表
     *
     * @param questErrorCheck
     * @param page
     * @return
     */
    public List<QuestErrorCheck> queryQuestErrorCheckList(QuestErrorCheck questErrorCheck, PageEntity page) {
        return this.queryForListPage("QuestErrorCheckMapper.queryQuestErrorCheckList", questErrorCheck, page);
    }

    /**
     * 删除questErrorCheck
     *
     * @param questErrorCheck
     * @return
     */
    public Long delQuestErrorCheckById(QuestErrorCheck questErrorCheck) {
        return this.delete("QuestErrorCheckMapper.delQuestErrorCheckById", questErrorCheck);
    }

}
