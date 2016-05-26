package com.atdld.os.exam.entity.professional;

import com.atdld.os.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
/**
 * 
 * @author huan.liu
 * @ClassName ExamSubject
 * @package com.atdld.os.exam.entity.professional
 * @description 考试科目
 * @Create Date: 2014-12-26 上午11:34:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExamSubject extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1439772663914637856L;
	
	private Long subjectId;//主键
	private String subjectName;//考试科目名称
	private int status;//状态 0:默认 1:冻结 2:删除
	private Date createTime;//创建时间
	private Date updateTime;//修改时间
	private int sort;//排序
	private Long professionalId;//考试专业id
	private int qstNum;//试题数
}
