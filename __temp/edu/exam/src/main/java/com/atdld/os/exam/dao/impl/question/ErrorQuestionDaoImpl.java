package com.atdld.os.exam.dao.impl.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.question.ErrorQuestionDao;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;

/**
 * @author
 * @ClassName QuestionDaoImpl
 * @package com.atdld.os.exam.dao.impl.question
 * @description
 * @Create Date: 2013-9-9 下午3:21:29
 */
@Repository("errorQuestionDao")
public class ErrorQuestionDaoImpl extends GenericDaoImpl implements ErrorQuestionDao {

    public void insertQueryErrorQuestion(List<QueryErrorQuestion> queryErrorQuestionList) {
        Map<String, List<QueryErrorQuestion>> map = new HashMap<String, List<QueryErrorQuestion>>();
        map.put("queryErrorQuestionList", queryErrorQuestionList);
        this.insert("ErrorQuestionMapper.insertQueryErrorQuestion", map);
    }

    public void delQueryErrorQuestionByPaperRecordId(QueryErrorQuestion queryErrorQuestion) {
        this.delete("ErrorQuestionMapper.delQueryErrorQuestionByPaperRecordId", queryErrorQuestion);
    }

    public void delQueryErrorQuestionBatchByQueryErrorQuestionList(List<QueryErrorQuestion> queryErrorQuestionList, Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("queryErrorQuestionList", queryErrorQuestionList);
        map.put("cusId", cusId);
        this.delete("ErrorQuestionMapper.delQueryErrorQuestionBatchByQueryErrorQuestionList", map);
    }

    public List<QueryErrorQuestion> queryErrorQuestionByQstId(List<QueryErrorQuestion> queryErrorQuestionList, Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("queryErrorQuestionList", queryErrorQuestionList);
        map.put("cusId", cusId);
        return this.selectList("ErrorQuestionMapper.queryErrorQuestionByQstId", map);
    }


}
