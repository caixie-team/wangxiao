package com.atdld.os.exam.service.subject;

import java.util.List;

import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.subject.QuerySubject;
public interface SubjectService {
	/**
	 * 添加考试科目
	 * @param examSubject
	 */
	public void addExamSubject(ExamSubject examSubject);
	/**
	 * 根据id获取考试科目
	 * @param id
	 * @return
	 */
	public ExamSubject getExamSubjectById(Long id);
	/**
	 * 修改考试科目
	 * @param examSubject
	 */
	public void updateExamSubject(ExamSubject examSubject);
	/**
	 * 修改考试科目状态
	 * @param examSubject
	 */
	public void updateSubjectStatus(ExamSubject examSubject);
	/**
	 * 根据ProfessionalId查询科目
	 * @param querySubject
	 * @return
	 */
	public List<ExamSubject> getSubjectListByProfessionalId(
			QuerySubject querySubject);
	/**
	 * 根据subjectid查询subject
	 * @param subject
	 * @return
	 */
	public ExamSubject getSubjectBySubjectId(ExamSubject subject);
}
