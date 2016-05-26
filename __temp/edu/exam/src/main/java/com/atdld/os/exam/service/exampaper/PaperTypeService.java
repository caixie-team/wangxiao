package com.atdld.os.exam.service.exampaper;

import java.util.List;

import com.atdld.os.exam.entity.exampaper.PaperType;


public interface PaperTypeService {

    /**
     * 查询试卷分类
     */
    public List<PaperType> queryPaperTypeList();

    /**
     * 通过state查询试卷分类
     */
    public List<PaperType> queryPaperTypeListByState(int state);

    /**
     * 通过id查询试卷分类
     */
    public PaperType queryPaperTypeById(int id);

    /**
     * 通过id更新试卷分类
     */
    public void updatePaperTypeById(PaperType paperType);


}
