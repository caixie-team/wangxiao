package com.atdld.os.exam.entity.professional;



import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author huan.liu
 * @ClassName ExamProfessional
 * @package com.atdld.os.exam.entity.professional
 * @description 考试专业
 * @Create Date: 2014-12-25 下午6:09:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamProfessional extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3526295968840140730L;

	private Long professionalId;//id
	private String professionalName;//专业名称
	private int status;//状态 0:默认 1:冻结 2:删除
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private int sort;//排序 倒叙
	private List<ExamSubject> subjectList;//考试科目
    private String colour;//颜色
}
