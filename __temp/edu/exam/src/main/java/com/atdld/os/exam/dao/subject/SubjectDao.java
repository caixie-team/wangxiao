package com.atdld.os.exam.dao.subject;

import java.util.List;

import javax.security.auth.Subject;

import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.subject.QuerySubject;


public interface SubjectDao {
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
	 * 根据profissionalId查询科目
	 * @param querySubject
	 * @return
	 */
	public List<ExamSubject> getSubjectListByProfessionalId(QuerySubject querySubject);
	/**
	 * 获取所有科目
	 * @param querySubject
	 * @return
	 */
	public List<ExamSubject> getSubjectList(QuerySubject querySubject);
	/**
	 * 根据subjectid查询subject
	 * @param subject
	 * @return
	 */
	public ExamSubject getSubjectBySubjectId(ExamSubject subject);
	
}
