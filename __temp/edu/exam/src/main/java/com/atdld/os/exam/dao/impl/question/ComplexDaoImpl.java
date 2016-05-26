package com.atdld.os.exam.dao.impl.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.question.ComplexDao;
import com.atdld.os.exam.entity.question.QstComplex;

@Repository("complexDao")
public class ComplexDaoImpl extends GenericDaoImpl implements ComplexDao {

    public void addComplex(QstComplex complex) {
        this.insert("ComplexMapper.createComple", complex);
    }

    public void updateComplex(QstComplex complex) {
        this.update("ComplexMapper.updateComplex", complex);
    }

    public void delComplexByComplex(QstComplex complex) {
        this.delete("ComplexMapper.delComplexByComplex", complex);
    }

    public void delComplexById(Long complex) {
        this.delete("ComplexMapper.delComplexById", complex);
    }

    /**
     * @param paperId
     * @param paperMiddleId
     * @return
     */
    public List<QstComplex> getQstComplexList(Long paperId, Long paperMiddleId) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("paperId", paperId);
        params.put("paperMiddleId", paperMiddleId);
        return this.selectList("ComplexMapper.getQstComplexList", params);
    }
}
