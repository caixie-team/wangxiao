package com.atdld.os.exam.dao.impl.note;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atdld.os.core.util.ObjectUtils;
import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.question.NoteDao;
import com.atdld.os.exam.entity.note.QueryNoteQuestion;

/**
 * @author
 * @ClassName noteDaoImpl
 * @package com.atdld.os.exam.dao.impl.note
 * @description
 * @Create Date: 2013-10-26 下午3:23:09
 */
@Repository("NoteDao")
public class NoteDaoImpl extends GenericDaoImpl implements NoteDao {
    //新建筆記
    public void insertNote(QueryNoteQuestion queryNoteQuestion) {
        this.insert("NoteMapper.insertNote", queryNoteQuestion);
    }
    //查询筆記
    public QueryNoteQuestion queryNote(QueryNoteQuestion queryNoteQuestion){
        List<QueryNoteQuestion> queryNoteQuestionList = this.selectList("NoteMapper.queryNote",queryNoteQuestion);
        if(ObjectUtils.isNotNull(queryNoteQuestionList)){
            return  queryNoteQuestionList.get(0);
        }else{
            return null;
        }

    }
    //更新筆記
    public void updatetNote(QueryNoteQuestion queryNoteQuestion) {
        this.update("NoteMapper.updatetNote", queryNoteQuestion);
    }

    //批量添加筆記
    public void insertQueryNoteQuestionBatch(List<QueryNoteQuestion> queryNoteQuestionList) {
        Map<String, List<QueryNoteQuestion>> map = new HashMap<String, List<QueryNoteQuestion>>();
        map.put("queryNoteQuestionList", queryNoteQuestionList);
        this.insert("NoteMapper.insertQueryNoteQuestionBatch", map);
    }
}
