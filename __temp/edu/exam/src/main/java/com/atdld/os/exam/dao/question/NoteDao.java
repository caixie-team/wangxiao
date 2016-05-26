package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.exam.entity.note.QueryNoteQuestion;

/**
 * @author
 * @ClassName NoteDao
 * @package com.atdld.os.exam.dao.question
 * @description
 * @Create Date: 2013-10-26 下午2:50:10
 */
public interface NoteDao {
    //新建筆記
    public void insertNote(QueryNoteQuestion queryNoteQuestion);
    //查询筆記
    public QueryNoteQuestion queryNote(QueryNoteQuestion queryNoteQuestion);

    /**
     * @param queryNoteQuestion更新筆記
     */
    public void updatetNote(QueryNoteQuestion queryNoteQuestion);

    //批量添加筆記
    public void insertQueryNoteQuestionBatch(List<QueryNoteQuestion> queryNoteQuestionList);
}
