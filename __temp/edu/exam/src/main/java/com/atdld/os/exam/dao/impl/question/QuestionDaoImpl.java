package com.atdld.os.exam.dao.impl.question;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.dao.question.QuestionDao;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.favorite.Favorite;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.Question;
import com.atdld.os.exam.entity.question.QuestionOption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @ClassName QuestionDaoImpl
 * @package com.atdld.os.exam.dao.impl.question
 * @description
 * @Create Date: 2013-9-9 下午3:21:29
 */
@Repository("questionDao")
public class QuestionDaoImpl extends GenericDaoImpl implements QuestionDao {

    public Long addOneQuestion(Question question) {
        return new Long(this.insert("QuestionMapper.createQuestion", question));
    }

    public void addBatchQuestion(List<Question> questionList) {
        Map<String, List<Question>> map = new HashMap<String, List<Question>>();
        map.put("questionList", questionList);
        this.insert("QuestionMapper.addBatchQuestion", map);
    }

    public List<QueryQuestion> getQuestionAllList(QueryQuestion queryQuestion, PageEntity pageEntity) {
        return this.queryForListPage("QuestionMapper.getAllQuestionList", queryQuestion, pageEntity);
    }

    /**
     * 查询用户做过的主观题
     */
    public List<QueryQuestion> getQuestionSubjectiveList(QueryQuestion queryQuestion, PageEntity pageEntity) {
        return this.queryForListPage("QuestionMapper.getQuestionSubjectiveList", queryQuestion, pageEntity);
    }

    public void delQuestionListBatch(String[] questionIds) {
        this.delete("QuestionMapper.deleteQuestionByidBatch", questionIds);
    }

    public void delQuestionListBypointId(ExamPoint point) {
        this.delete("QuestionMapper.delQuestionListBypointId", point);
    }

    public List<Question> getQuestionAllList(QueryQuestion queryQuestion) {
        return this.selectList("QuestionMapper.getQuestionList", queryQuestion);
    }

    public void updateQuestionById(Question question) {
        this.update("QuestionMapper.updateQuestionById", question);
    }

    public void updateQuestionWhenAddPointId(ExamPoint point) {
        this.update("QuestionMapper.updateQuestionWhenAddPointId", point);
    }

    public void updateQuestionWhenDelPointId(ExamPoint point) {
        this.update("QuestionMapper.updateQuestionWhenDelPointId", point);
    }

    public List<QueryQuestion> getRandomQuestionByPointIds(String pointIds, int qstType, int num) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] parr = pointIds.split(",");
        Long[] ae = new Long[parr.length];
        for (int i = 0; i < parr.length; i++) {
            ae[i] = Long.valueOf(parr[i]);
        }
        map.put("pointIds", ae);
        map.put("num", num);
        map.put("qstType", qstType);
        return this.selectList("QuestionMapper.getRandomQuestionByPointIds", map);
    }

    /**
     * 通过条件查询试题数量
     */
    public int getAllQuestionListCount(QueryQuestion queryQuestion) {
        return this.selectOne("QuestionMapper.getQuestionListCount", queryQuestion);
    }

    public List<QueryErrorQuestion> getRandomQuestionByErrorQst(int num, Long subjectId, Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("num", num);
        map.put("subjectId", subjectId);
        map.put("cusId", cusId);
        return this.selectList("QuestionMapper.getRandomQuestionByErrorQst", map);
    }

    /**
     * 顺序获得错误试题
     */
    public List<QueryErrorQuestion> getQuestionByErrorQst(int num, Long subjectId, Long cusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("num", num);
        map.put("subjectId", subjectId);
        map.put("cusId", cusId);
        return this.selectList("QuestionMapper.getQuestionByErrorQst", map);
    }

    public List<QueryQuestion> getParse(QueryQuestion queryQuestion) {
        return this.selectList("QuestionMapper.getParse", queryQuestion);
    }

    public List<QueryQuestion> getQuestionByQuestionRecord(PaperRecord paperRecord) {
        List<QueryQuestion> queryQuestionList = this.selectList("QuestionMapper.getQuestionByQuestionRecord", paperRecord);
        return initQueryQuestion(queryQuestionList);
    }

    public List<QueryQuestion> getQuestionByIds(Long cusId, String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cusId", cusId);
        map.put("ids", ids.trim().split(","));
        List<QueryQuestion> queryQuestionList = this.selectList("QuestionMapper.getQuestionByIds", map);

        return initQueryQuestion(queryQuestionList);
    }

    public List<QueryQuestion> initQueryQuestion(List<QueryQuestion> queryQuestionList) {//整理选项
        if (ObjectUtils.isNotNull(queryQuestionList)) {
            Long qstId = queryQuestionList.get(0).getId();
            QueryQuestion QueryQuestion2 = queryQuestionList.get(0);
            List<QuestionOption> optionList = new ArrayList<QuestionOption>();
            List<QueryQuestion> queryQuestionList2 = new ArrayList<QueryQuestion>();
            for (QueryQuestion queryQuestion : queryQuestionList) {
                if (queryQuestion.getId().longValue() == qstId.longValue()) {
                    QuestionOption option = new QuestionOption();
                    option.setOptContent(queryQuestion.getOptContent());
                    option.setOptOrder(queryQuestion.getOptOrder());
                    option.setOptAnswer(queryQuestion.getOptAnswer());
                    option.setQstType(queryQuestion.getQstType());
                    optionList.add(option);
                } else {
                    QueryQuestion2.setOptions(optionList);
                    queryQuestionList2.add(QueryQuestion2);
                    qstId = queryQuestion.getId();
                    QueryQuestion2 = queryQuestion;
                    optionList = new ArrayList<QuestionOption>();
                    QuestionOption option = new QuestionOption();
                    option.setOptContent(queryQuestion.getOptContent());
                    option.setOptOrder(queryQuestion.getOptOrder());
                    option.setOptAnswer(queryQuestion.getOptAnswer());
                    option.setQstType(queryQuestion.getQstType());
                    optionList.add(option);
                }
            }
            QueryQuestion2.setOptions(optionList);//把最后一个选项放入
            queryQuestionList2.add(QueryQuestion2);
            return queryQuestionList2;
        } else {
            return null;
        }
    }

    public int isFavorite(QueryQuestion queryQuestion) {
        return this.selectOne("QuestionMapper.isFavorite", queryQuestion);
    }

    public Long queryQuestiponMaxId() {
        return this.selectOne("QuestionMapper.queryQuestiponMaxId", null);
    }

    public void delFavorite(Favorite favorite) {
        this.delete("QuestionMapper.delFavorite", favorite);
    }

    public void delQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion) {
        this.delete("QuestionMapper.delQueryErrorQuestion", queryErrorQuestion);
    }
    /**
     * 清空错题
     */
    public void clearQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion){
        this.delete("QuestionMapper.clearQueryErrorQuestion", queryErrorQuestion);
    }
    public void addFavorite(Favorite favorite) {
        this.insert("QuestionMapper.addFavorite", favorite);
    }

    //批量添加收藏
    public void insertFavoriteBatch(List<Favorite> favoriteList) {
        Map<String, List<Favorite>> map = new HashMap<String, List<Favorite>>();
        map.put("favoriteList", favoriteList);
        this.insert("QuestionMapper.insertFavoriteBatch", map);
    }

    public List<QueryQuestion> getErrorQuestionList(QueryErrorQuestion queryErrorQuestion, PageEntity pageEntity) {
        return this.queryForListPage("QuestionMapper.getErrorQuestionList", queryErrorQuestion, pageEntity);
    }

    public List<QueryQuestion> getNoteQuestionList(QueryNoteQuestion queryNoteQuestion, PageEntity pageEntity) {
        return this.queryForListPage("QuestionMapper.getNoteQuestionList", queryNoteQuestion, pageEntity);
    }

    public List<QueryQuestion> getFavoriteQuestion(QueryQuestion queryQuestion,
                                                   PageEntity pageEntity) {
        return this.queryForListPage("QuestionMapper.getFavoriteQuestion", queryQuestion, pageEntity);
    }


    @Override
    public List<QueryQuestion> getErrorQuestionitemList(
            QueryErrorQuestion queryErrorQuestion) {
        return this.selectList("QuestionMapper.getErrorQuestionitemList", queryErrorQuestion);
    }

    @Override
    public List<QueryQuestion> getNoteQuestionitemList(
            QueryNoteQuestion queryNoteQuestion) {
        return this.selectList("QuestionMapper.getNoteQuestionitemList", queryNoteQuestion);
    }

    public QueryQuestion queryQuestionById(QueryQuestion queryQuestion) {
        return this.selectOne("QuestionMapper.queryQuestionById", queryQuestion);
    }

    public void updateQuestionForTimeAndRightTimeById(QueryQuestion queryQuestion) {
        this.update("QuestionMapper.updateQuestionForTimeAndRightTimeById", queryQuestion);
    }

    /* 
     * 根据多个id获得试题内容
     * @see com.atdld.os.exam.dao.question.QuestionDao#getLuceneQuestionByIds(java.lang.String)
     */
    @Override
    public List<QueryQuestion> getLuceneQuestionByIds(List<Long> ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ids", ids);
        List<QueryQuestion> queryQuestionList = this.selectList("QuestionMapper.getLuceneQuestionByIds", map);
        return initQueryQuestion(queryQuestionList);
    }

    /*
     *获得所有试题的行数
     */
    @Override
    public Long getQuestionCount() {
        Long r = this.selectOne("QuestionMapper.getLuceneAllQuestonCount", null);
        if (r == null) r = 0L;
        return r;
    }

    /*
     * 获得分页的试题数据
     * @see com.atdld.os.exam.dao.question.QuestionDao#getQuestionByPageQuery(java.lang.Long, java.lang.Long)
     */
    @Override
    public List<Question> getQuestionByPageQuery(Long offset, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", offset.longValue());
        map.put("size", size.longValue());
        List<Question> result = this.selectList("QuestionMapper.getQuestionByPageQuery", map);
        return result;
    }
}
