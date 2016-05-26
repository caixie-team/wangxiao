package com.atdld.os.exam.entity.favorite;

import java.util.Date;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author
 * @ClassName favorite
 * @package com.atdld.os.exam.entity.favorite
 * @description
 * @Create Date: 2013-10-14 下午1:38:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Favorite extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = -364541552770797695L;
    /**
     * Id
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long cusId;
    /**
     * 试题Id
     */
    private Long qstId;
    /**
     * 添加时间
     */
    private Date addDate;
    private int flag;
    /**
     * 专业Id
     */
    private Long subjectId;
}
