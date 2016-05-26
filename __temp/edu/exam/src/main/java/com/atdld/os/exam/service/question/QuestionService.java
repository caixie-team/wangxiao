package com.atdld.os.exam.service.question;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.exam.entity.exampaper.ExamRecord;
import com.atdld.os.exam.entity.favorite.Favorite;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;
import com.atdld.os.exam.entity.point.ExamPoint;
import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.entity.question.QueryErrorQuestion;
import com.atdld.os.exam.entity.question.QueryQuestion;
import com.atdld.os.exam.entity.question.Question;
import com.atdld.os.exam.entity.question.QuestionOption;

/**
 * @author
 * @ClassName QuestionService
 * @package com.atdld.os.exam.service.question
 * @description
 * @Create Date: 2013-9-9 下午3:23:34
 */
public interface QuestionService {
    /**
     * ①单条增加 返回主键(可以不返回)
     */
    public void addOneQuestion(Question question, List<String> asr);

    /**
     * 试题列表
     */
    public List<QueryQuestion> getQuestionAllList(QueryQuestion queryQuestion, PageEntity pageEntity);

    /**
     * 查询用户做过的主观题
     */
    public List<QueryQuestion> getQuestionSubjectiveList(QueryQuestion queryQuestion, PageEntity pageEntity);

    /**
     * 批量删除试题
     */
    public void delQuestionListBatch(String questionIds);

    /**
     * 获得试题
     */
    public Question getOneQuestion(QueryQuestion queryQuestion);

    /**
     * 更新试题
     */
    public void updateQuestion(Question question, List<String> asr);

    /**
     * 当删除节点时该考点试题放到父考点中
     */
    public void updateQuestionByDelPoint(List<ExamPoint> pointList);

    /**
     * 删除试题于试卷中间表信息通过id
     */
    public void delQstMiddleById(Long id);

    public void delQstMiddleById(QstMiddle qstMiddle);

    /**
     * 试题上移交换两个试题的sort
     */
    public void updateMoveUp(int oneSort, Long oneQstId, int twotSort, Long twoQstId, QstMiddle qstMiddle);

    /**
     * 通过考点Id随机获得该考点试题
     */
    public List<QueryQuestion> getRandomQuestionByPointIds(String pointIds, int qstType, int num);

    /**
     * 随机获得试题
     */
    public String getRandomQuestionByErrorQst(int num, Long subjectId, Long cusId);

    /**
     * 顺序获得错误试题
     */
    public String getQuestionByErrorQst(int num, Long subjectId, Long cusId);

    public QueryQuestion getParse(QueryQuestion queryQuestion);
    /**
     * 通过条件查询试题数量
     */
    public int getAllQuestionListCount(QueryQuestion queryQuestion);
    /**
     * 判断是否收藏
     */
    public int isFavorite(QueryQuestion queryQuestion2);

    /**
     * 取消收藏
     */
    public void delFavorite(Favorite favorite);

    /**
     * 移出错题
     */
    public void delQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion);
    /**
     * 清空错题
     */
    public void clearQueryErrorQuestion(QueryErrorQuestion queryErrorQuestion);

    /**
     * 收藏试题
     */
    public void addFavorite(Favorite favorite);

    /**
     * 添加试题
     */
    public void addOneQuestion(Question question);

    /**
     * 插入一个选项
     */
    public void addOneOption(QuestionOption option);

    /**
     * 通过考点Id随机获得该考点试题和选项
     */
    public String getRandomQuestionByPointIds(Long cusId, String pointIds, int num);

    /**
     * 获得错题表列表
     */
    public List<QueryQuestion> getErrorQuestionList(QueryErrorQuestion queryErrorQuestion, PageEntity pageEntity);

    /**
     * 获得写过笔记的题列表
     */
    public List<QueryQuestion> getNoteQuestionList(QueryNoteQuestion queryNoteQuestion, PageEntity pageEntity);

    /**
     * item-list获得写过笔记的题列表
     */
    public List<QueryQuestion> getNoteQuestionitemList(QueryNoteQuestion queryNoteQuestion);

    /**
     * 获得错题表列表
     */
    public List<QueryQuestion> getErrorQuestionitemList(QueryErrorQuestion queryErrorQuestion);

    /**
     * @param val
     */
    public String updateImportExcel(MultipartFile myFile) throws Exception;
    
    
    /**
     * @param val
     */
    public void updateImportWord(MultipartFile myFile) throws Exception;

    /**
     * @param queryQuestion
     * @param page
     * @return查詢收藏的試題
     */
    public List<QueryQuestion> getFavoriteQuestion(QueryQuestion queryQuestion,
                                                   PageEntity page);

    /**
     * 通过试题Id获得question
     */
    public List<QueryQuestion> getQuestionByQuestionIds(Long cusId, String qstIds);

    /**
     * 通过试题Id获得question
     */
    public Map<Long, QueryQuestion> getMapQuestionByQuestionIds(String qstIds);

    /**
     * @param note
     * @return新建筆記
     */
    public void insertNote(QueryNoteQuestion queryNoteQuestion);

    /**
     * @param queryNoteQuestion更新筆記
     */
    public void updatetNote(QueryNoteQuestion queryNoteQuestion);

    /**
     * 通过试题记录表更新每个试题的做过多少次正确次数错误次数
     */
    public void updateQuestionByPaperRecord(ExamRecord examRecord);

    /**
     * 获得试题所有的行数
     *
     * @return
     */
    public Long getQuestionCount();
}
