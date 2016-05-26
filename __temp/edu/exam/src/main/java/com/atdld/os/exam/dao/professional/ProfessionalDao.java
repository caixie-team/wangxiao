package com.atdld.os.exam.dao.professional;

import java.util.List;

import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamProfessionalDTO;


public interface ProfessionalDao {
	/**
	 * 添加考试专业
	 * @param professional
	 */
	public void addProfessional(ExamProfessional professional);
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<ExamProfessional> queryExamProfessionalListForAdmin();
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<ExamProfessional> queryExamProfessionalList();
	/**
	 * 冻结恢复操作
	 * @param professional
	 */
	public void updateStatus(ExamProfessional professional);
	/**
	 * 根据id获取考试专业
	 * @param id
	 * @return
	 */
	public ExamProfessional getProfessionalById(Long id);
	/**
	 * 修改考试专业
	 * @param examProfessional
	 */
	public void updateProfessional(ExamProfessional examProfessional);
	/**
	 * 查询所有正常使用（非冻结）专业
	 * @return
	 */
	public List<ExamProfessional> queryProfessionalList();
	/**
	 * 根据subjectId查询Professional
	 * @param id
	 * @return
	 */
	public ExamProfessionalDTO getProfessionalBySubjectId(Long id);

	
}
