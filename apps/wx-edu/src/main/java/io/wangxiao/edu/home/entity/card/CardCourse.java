package io.wangxiao.edu.home.entity.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 课程卡和课程中间表
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CardCourse implements Serializable {
    private Long id;
    private Long cardId;
    private Long courseId;
}
