package com.atdld.os.exam.dao.question;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.exampaper.PaperRecord;
import com.atdld.os.exam.entity.favorite.Favorite;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.Question;

import java.util.List;

/**
 * @author
 * @ClassName QuestionDao
 * @package com.atdld.os.exam.dao.question
 * @description
 * @Create Date: 2013-9-9 下午3:19:10
 */
public interface QuestionDao {
    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public Long addOneQuestion(Question question);

    /**
     * 试题列表
     */
    public List<QueryQuestion> getQuestionAllList(QueryQuestion queryQuestion,
                                                  PageEntity pageEntity);

    /**
     * 查询用户做过的主观题
     */
    public List<QueryQuestion> getQuestionSubjectiveList(QueryQuestion queryQuestion, PageEntity pageEntity);

    /**
     * 批量删除试题
     */
    public void delQuestionListBatch(String[] questionIds);

    /**
     * 获得试题
     */
    public List<Question> getQuestionAllList(QueryQuestion queryQuestion);

    /**
     * 更新试题
     */
    public void updateQuestionById(Question question);

    /**
     * 添加考点时把父考点下所有试题放到新考点下
     */
    public void updateQuestionWhenAddPointId(ExamPoint point);

    /**
     * 删除考点时把考点id下所有试题放到父考点下
     */
    public void updateQuestionWhenDelPointId(ExamPoint point);

    /**
     * 删除考点时当考点父考点为0删除试题
     */
    public void delQuestionListBypointId(ExamPoint point);

    /**
     * 通过随机获得试题 pointIds考点Id num要获得的数量
     */
    public List<QueryQuestion> getRandomQuestionByPointIds(String pointIds, int qstType,
                                                           int num);
    /**
     * 通过条件查询试题数量
     */
    public int getAllQuestionListCount(QueryQuestion queryQuestion);
    /**
     * 随机获得试题
     */
    public List<QueryErrorQuestion> getRandomQuestionByErrorQst(int num, Long subjectId,
                                                                Long cusId);

    /**
     * 顺序获得错误试题
     */
    public List<QueryErrorQuestion> getQuestionByErrorQst(int num, Long subjectId, Long cusId);

    /**
     * 通过paperRecord获得该用户随机试卷的卷子
     */
    public List<QueryQuestion> getQuestionByQuestionRecord(PaperRecord paperRecord);

    /**
     * 传入试题Ids获得试题 ids为试题Id
     */
    public List<QueryQuestion> getQuestionByIds(Long cusId, String ids);

    /**
     * 获得错题表列表
     */
    public List<QueryQuestion> getErrorQuestionList(
            QueryErrorQuestion queryErrorQuestion, PageEntity pageEntity);

    /**
     * 获得该用户写过笔记的试题列表
     */
    public List<QueryQuestion> getNoteQuestionList(QueryNoteQuestion queryNoteQuestion,
                                                   PageEntity pageEntity);

    /**
     * item-list获得写过笔记的题列表
     */
    public List<QueryQuestion> getNoteQuestionitemList(QueryNoteQuestion queryNoteQuestion);

    /**
     * item 获得错题表列表
     */
    public List<QueryQuestion> getErrorQuestionitemList(
            QueryErrorQuestion queryErrorQuestion);

    // 查询一道题的具体内容
    public List<QueryQuestion> getParse(QueryQuestion queryQuestion);

    // 验证题是否被收藏
    public int isFavorite(QueryQuestion queryQuestion);

    // 取消收藏
    public void delFavorite(Favorite favorite);

    /**
     * 移出错题
     */
    public void delQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion);
    /**
     * 清空错题
     */
    public void clearQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion);
    // 收藏题
    public void addFavorite(Favorite favorite);

    /**
     * 查询收藏的试题
     *
     * @param queryQuestion
     * @param page
     * @return
     */
    public List<QueryQuestion> getFavoriteQuestion(QueryQuestion queryQuestion,
                                                   PageEntity page);

    /**
     * 批量添加试题
     */
    public void addBatchQuestion(List<Question> questionList);

    /**
     * 试题表中最大id
     */
    public Long queryQuestiponMaxId();

    /**
     * 批量添加收藏
     */
    public void insertFavoriteBatch(List<Favorite> favoriteList);

    /**
     * 根据id查询试题
     */
    public QueryQuestion queryQuestionById(QueryQuestion queryQuestion);

    /**
     * 根据试题id更新试题的答题次数正确 次数错误次数 正确率
     */
    public void updateQuestionForTimeAndRightTimeById(QueryQuestion queryQuestion);

    /**
     * lucene用 传入试题Ids获得试题 ids为试题Id
     */
    public List<QueryQuestion> getLuceneQuestionByIds(List<Long> ids);

    // 获得总行数
    public Long getQuestionCount();

    // 分页获得数据
    public List<Question> getQuestionByPageQuery(Long offset, Long size);

}
