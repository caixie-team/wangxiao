package com.atdld.os.exam.dao.impl.exampaper;


import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.exampaper.ExamRecordDao;
import com.atdld.os.exam.entity.exampaper.ExamRecord;

/**
 * @author
 * @ClassName ExamPaperDaoImpl
 * @package com.atdld.os.exam.dao.impl.exampaper
 * @description
 * @Create Date: 2013-9-9 下午3:20:23
 */
@Repository("examRecordDao")
public class ExamRecordDaoImpl extends GenericDaoImpl implements ExamRecordDao {

    public ExamRecord queryExamRecordByType(ExamRecord examRecord) {
        return this.selectOne("ExamRecordMapper.getExamRecordByType", examRecord);
    }

    public void updateExamRecordById(ExamRecord examRecord) {
        this.update("ExamRecordMapper.updateExamRecordById", examRecord);
    }

}
