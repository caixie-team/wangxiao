package com.atdld.os.exam.entity.subject;


import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.exam.entity.professional.ExamSubject;
/**
 * 
 * @author huan.liu
 * @ClassName QuerySubject
 * @package com.atdld.os.exam.entity.subject
 * @description 考试科目
 * @Create Date: 2014-12-26 上午11:34:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuerySubject extends ExamSubject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 311482006093158914L;

	private Long professionalId;//专业id
	
	
	
}
