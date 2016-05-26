package com.atdld.os.exam.service.question;

import java.util.List;

import com.atdld.os.exam.entity.question.QuestionOption;


public interface OptionService {
    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public Long addOneOption(QuestionOption option);

    /**
     * 通过试题Id获得选项
     */
    public List<QuestionOption> queryOptionListByQstId(Long qstId);
}
