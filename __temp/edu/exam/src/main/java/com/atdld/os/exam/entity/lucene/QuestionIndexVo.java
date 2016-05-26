package com.atdld.os.exam.entity.lucene;

import com.atdld.os.core.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author
 * @ClassName QuestionIndexVo
 * @package com.atdld.os.exam.entity.lucene
 * @description
 * @Create Date: 2013-11-30 下午11:27:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionIndexVo extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 2540430542195218625L;
    /**
     * 试题Id
     */
    private Long id;
    /**
     * 试题内容
     */
    private String qstContent;

}
