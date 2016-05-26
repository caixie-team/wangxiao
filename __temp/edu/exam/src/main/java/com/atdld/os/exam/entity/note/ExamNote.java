package com.atdld.os.exam.entity.note;

import java.util.Date;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author
 * @ClassName Note
 * @package com.atdld.os.exam.entity.Note
 * @description
 * @Create Date: 2013-10-26 下午2:53:11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamNote extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -5830664127463639361L;
    private Long id;
    private String noteContent;
    private Long qstId;
    private Long cusId;
    private Long subjectId;
    private Date addTime;
}
