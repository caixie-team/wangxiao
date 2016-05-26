package com.atdld.os.exam.service.impl.exampaper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.exam.dao.exampaper.PaperTypeDao;
import com.atdld.os.exam.entity.exampaper.PaperType;
import com.atdld.os.exam.service.exampaper.PaperTypeService;

@Service("paperTypeService")
public class PaperTypeServiceImpl implements PaperTypeService {
    @Autowired
    private PaperTypeDao paperTypeDao;

    /**
     * 查询试卷分类
     */
    public List<PaperType> queryPaperTypeList() {
        return paperTypeDao.queryPaperTypeList();
    }

    /**
     * 通过state查询试卷分类
     */
    public List<PaperType> queryPaperTypeListByState(int state) {
        return paperTypeDao.queryPaperTypeListByState(state);
    }

    /**
     * 通过id查询试卷分类
     */
    public PaperType queryPaperTypeById(int id) {
        return paperTypeDao.queryPaperTypeById(id);
    }

    /**
     * 通过id更新试卷分类
     */
    public void updatePaperTypeById(PaperType paperType) {
        paperTypeDao.updatePaperTypeById(paperType);
    }


}
