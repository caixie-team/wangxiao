package io.wangxiao.edu.home.entity.arrange;


import io.wangxiao.commons.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ExamPaper extends BaseEntity {
	
	private Long id;//试卷id
    private String name;//试卷名称
}
