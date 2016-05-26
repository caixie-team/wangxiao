package com.atdld.os.exam.service.impl.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.exam.dao.question.OptionDao;
import com.atdld.os.exam.entity.question.QuestionOption;
import com.atdld.os.exam.service.question.OptionService;

@Service("optionService")
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    @Override
    public Long addOneOption(QuestionOption option) {
        return optionDao.addOneOption(option);
    }

    @Override
    public List<QuestionOption> queryOptionListByQstId(Long qstId) {
        QuestionOption option = new QuestionOption();
        option.setQstId(qstId);
        return optionDao.queryOptionList(option);
    }

}

