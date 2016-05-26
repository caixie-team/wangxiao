package com.atdld.os.exam.dao.question;

import java.util.List;

import com.atdld.os.exam.entity.question.QstComplex;

public interface ComplexDao {
    /**
     * 添加材料题的材料
     *
     * @param complex
     */
    public void addComplex(QstComplex complex);

    /**
     * 更新材料题的材料
     *
     * @param complex
     */
    public void updateComplex(QstComplex complex);

    /**
     * 删除材料
     *
     * @param complex
     */
    public void delComplexById(Long complex);

    /**
     * 删除材料
     *
     * @param complex
     */
    public void delComplexByComplex(QstComplex complex);

    /**
     * @param paperId
     * @param paperMiddleId
     * @return
     */
    public List<QstComplex> getQstComplexList(Long paperId, Long paperMiddleId);
}
