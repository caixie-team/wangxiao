package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.exam.entity.question.QuestionOption;


public interface OptionDao {
    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public Long addOneOption(QuestionOption option);

    /**
     * 通过试题Id获得选项
     */
    public List<QuestionOption> queryOptionList(QuestionOption option);

    public void delOptionListBatch(Long qstId);

    /**
     * 批量添加
     */
    public void addOptionBatch(List<QuestionOption> optionList);
}
