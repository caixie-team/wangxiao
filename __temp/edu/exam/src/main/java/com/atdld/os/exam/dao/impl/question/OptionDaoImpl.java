package com.atdld.os.exam.dao.impl.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.question.OptionDao;
import com.atdld.os.exam.entity.question.QuestionOption;

@Repository("optionDao")
public class OptionDaoImpl extends GenericDaoImpl implements OptionDao {

    @Override
    public Long addOneOption(QuestionOption option) {
        return new Long(this.insert("OptionMapper.createOption", option));
    }

    public void addOptionBatch(List<QuestionOption> optionList) {
        Map<String, List<QuestionOption>> map = new HashMap<String, List<QuestionOption>>();
        map.put("optionList", optionList);
        this.insert("OptionMapper.addOptionBatch", map);
    }

    @Override
    public List<QuestionOption> queryOptionList(QuestionOption option) {
        return this.selectList("OptionMapper.getOptionList", option);
    }

    @Override
    public void delOptionListBatch(Long qstId) {
        this.delete("OptionMapper.delOptionListBatch", qstId);
    }


}
