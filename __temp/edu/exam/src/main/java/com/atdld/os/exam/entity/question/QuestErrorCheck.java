package com.atdld.os.exam.entity.question;

import java.util.Date;

import com.atdld.os.core.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 试题纠错
 *
 * @author :
 * @ClassName com.atdld.os.exam.entity.exampaper.PaperErrorCheck
 * @description
 * @Create Date : 2014年4月23日 下午3:30:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestErrorCheck extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int id;// 主鍵
    private int paperId;// 試卷id
    private int questionId;// 试题id
    private Date addTime;//添加时间
    private String content;// 纠错内容
    private String paperName;//试卷名称
    private String qstName;//试题名称
}
