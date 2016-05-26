package com.atdld.os.exam.entity.professional;



import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.atdld.os.core.entity.BaseEntity;

/**
 * @author huan.liu
 * @ClassName ExamProfessional
 * @package com.atdld.os.exam.entity.professional
 * @description 考试专业
 * @Create Date: 2014-12-25 下午6:09:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamProfessionalDTO extends  ExamProfessional{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6874641922131793856L;
	private String subjectName;
  
}
