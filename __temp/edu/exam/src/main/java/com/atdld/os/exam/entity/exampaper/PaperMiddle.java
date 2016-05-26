package com.atdld.os.exam.entity.exampaper;

import java.util.ArrayList;
import java.util.List;

import com.atdld.os.core.entity.BaseEntity;
import com.atdld.os.exam.entity.question.QstComplex;
import com.atdld.os.exam.entity.question.QueryQstMiddle;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author
 * @ClassName Paper
 * @package com.atdld.os.exam.entity.exampaper
 * @description
 * @Create Date: 2013-9-27 下午7:17:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaperMiddle extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2863916048289247448L;
    /**
     * Id
     */
    private Long id;
    /**
     * 试卷ID
     */
    private Long paperId;
    /**
     * 试题类型
     */
    private int type;
    /**
     * 名称
     */
    private String name;
    /**
     * 数量
     */
    private int num;
    /**
     * 每题的分数
     */
    private int score;
    /**
     * 描述
     */
    private String title;
    /**
     * 排序字段
     */
    private int sort;
    /**
     * 试题中间表数据
     */
    private List<QueryQstMiddle> qstMiddleList = new ArrayList<QueryQstMiddle>();
    /**
     * 材料题
     */
    private List<QstComplex> complexList = new ArrayList<QstComplex>();
}
