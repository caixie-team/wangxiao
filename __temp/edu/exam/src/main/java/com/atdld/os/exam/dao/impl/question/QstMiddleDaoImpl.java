package com.atdld.os.exam.dao.impl.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.question.QstMiddleDao;
import com.atdld.os.exam.entity.question.QuestionOption;
import com.atdld.os.exam.entity.question.QstMiddle;
import com.atdld.os.exam.entity.question.QueryQstMiddle;

@Repository("qstMiddleDao")
public class QstMiddleDaoImpl extends GenericDaoImpl implements QstMiddleDao {

    public void addExamQstMiddleBatch(List<QstMiddle> qstMiddleList) {
        for (QstMiddle QstMiddle : qstMiddleList) {
            this.insert("QstMiddleMapper.createQstMiddle", QstMiddle);
        }

    }

    // 批量添加qstMiddle
    public void addExamQstMiddleBatchByMap(List<QstMiddle> qstMiddleList) {
        Map<String, List<QstMiddle>> map = new HashMap<String, List<QstMiddle>>();
        map.put("qstMiddleList", qstMiddleList);
        this.insert("QstMiddleMapper.createQstMiddleBatch", map);
    }

    /**
     * 查询出一张试卷所有的试题
     */
    public List<QueryQstMiddle> queryQstMiddleList(QstMiddle qstMiddle) {
        // 未整理的QueryQstMiddleList
        List<QueryQstMiddle> queryQstMiddleList = this.selectList(
                "QstMiddleMapper.getQstMiddleList", qstMiddle);
        queryQstMiddleList = initQueryQstMiddle(queryQstMiddleList);
        return queryQstMiddleList;
    }

    public List<QueryQstMiddle> queryQstMiddleListBypaperRecord(QstMiddle qstMiddle) {
        // 未整理的QueryQstMiddleList
        List<QueryQstMiddle> queryQstMiddleList = this.selectList(
                "QstMiddleMapper.queryQstMiddleListBypaperRecord", qstMiddle);
        queryQstMiddleList = initQueryQstMiddle(queryQstMiddleList);
        return queryQstMiddleList;
    }

    // 整理数据把选项放入试题的list中
    public List<QueryQstMiddle> initQueryQstMiddle(List<QueryQstMiddle> queryQstMiddleList) {
        if (queryQstMiddleList.size() > 0) {
            Long qstId = queryQstMiddleList.get(0).getQstId();
            Long complexId = queryQstMiddleList.get(0).getComplexId();
            QueryQstMiddle queryQstMiddle2 = queryQstMiddleList.get(0);
            // 整理之后的QueryQstMiddleList
            List<QueryQstMiddle> queryQstMiddleList2 = new ArrayList<QueryQstMiddle>();
            List<QuestionOption> optionList = new ArrayList<QuestionOption>();

            for (QueryQstMiddle queryQstMiddle : queryQstMiddleList) {
                if (queryQstMiddle.getQstId().longValue() == qstId.longValue() && queryQstMiddle.getComplexId().longValue() == complexId.longValue()) {// 如果相等说明为一个试题把选项信息放入list中
                    QuestionOption option = new QuestionOption();
                    option.setOptContent(queryQstMiddle.getOptContent());
                    option.setOptOrder(queryQstMiddle.getOptOrder());
                    option.setOptAnswer(queryQstMiddle.getOptAnswer());
                    option.setQstType(queryQstMiddle.getQuestionType());
                    optionList.add(option);
                } else {// 当qstId不一样说明试题不一样把选项list放入queryQstMiddle2中
                    queryQstMiddle2.setOptionList(optionList);
                    queryQstMiddleList2.add(queryQstMiddle2);
                    qstId = queryQstMiddle.getQstId();
                    complexId = queryQstMiddle.getComplexId();
                    queryQstMiddle2 = queryQstMiddle;
                    optionList = new ArrayList<QuestionOption>();
                    QuestionOption option = new QuestionOption();
                    option.setOptContent(queryQstMiddle.getOptContent());
                    option.setOptOrder(queryQstMiddle.getOptOrder());
                    option.setOptAnswer(queryQstMiddle.getOptAnswer());
                    option.setQstType(queryQstMiddle.getQuestionType());
                    optionList.add(option);
                }
            }
            queryQstMiddle2.setOptionList(optionList);// 把循环的最后一个选项list放入
            queryQstMiddleList2.add(queryQstMiddle2);
            return queryQstMiddleList2;
        } else {
            return queryQstMiddleList;
        }
    }

    public Integer queryQstMiddleListMaxSort(QstMiddle qstMiddle) {
        return this.selectOne("QstMiddleMapper.getQstMiddleListMaxSort", qstMiddle);
    }

    public void delQstMiddleById(QstMiddle qstMiddle) {
        this.delete("QstMiddleMapper.delQstMiddleById", qstMiddle);
    }

    public void addExamQstMiddle(QstMiddle qstMiddle) {
        this.insert("QstMiddleMapper.createQstMiddle", qstMiddle);
    }


    public void updateQstMiddleBySort(QstMiddle qstMiddle) {
        this.update("QstMiddleMapper.updateQstMiddleBySort", qstMiddle);
    }

    /**
     * 获得一张试卷所有试题的list
     */
    public List<Integer> queryQstMiddleListQstIds(QstMiddle qstMiddle) {
        return this.selectList("QstMiddleMapper.queryQstMiddleListQstIds", qstMiddle);
    }
}
