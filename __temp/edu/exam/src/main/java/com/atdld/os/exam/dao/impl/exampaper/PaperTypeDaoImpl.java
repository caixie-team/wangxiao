package com.atdld.os.exam.dao.impl.exampaper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.exampaper.PaperTypeDao;
import com.atdld.os.exam.entity.exampaper.PaperType;

@Service("paperTypeDao")
public class PaperTypeDaoImpl extends GenericDaoImpl implements PaperTypeDao {

    /**
     * 查询试卷分类
     */
    public List<PaperType> queryPaperTypeList() {
        return this.selectList("PaperTypeMapper.queryPaperTypeList", null);
    }

    /**
     * 通过state查询试卷分类
     */
    public List<PaperType> queryPaperTypeListByState(int state) {
        return this.selectList("PaperTypeMapper.queryPaperTypeListByState", state);
    }

    /**
     * 通过id查询试卷分类
     */
    public PaperType queryPaperTypeById(int id) {
        return this.selectOne("PaperTypeMapper.queryPaperTypeById", id);
    }

    /**
     * 通过id更新试卷分类
     */
    public void updatePaperTypeById(PaperType paperType) {
        this.update("PaperTypeMapper.updatePaperTypeById", paperType);
    }

}
