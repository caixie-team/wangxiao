package com.atdld.os.exam.dao.impl.professional;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.exam.dao.professional.ProfessionalDao;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamProfessionalDTO;

@Repository("professionalDao")
public class ProfessionalDaoImpl extends GenericDaoImpl implements ProfessionalDao{

	/**
	 * 添加考试专业
	 * @param professional
	 */
	public void addProfessional(ExamProfessional professional) {
		this.insert("ProfessionalMapper.createProfessional", professional);
	}

	/**
	 * 查询所有专业
	 * @return
	 */
	public List<ExamProfessional> queryExamProfessionalListForAdmin() {
		
		return this.selectList("ProfessionalMapper.queryExamProfessionalListForAdmin", null);
	}
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<ExamProfessional> queryExamProfessionalList() {
		
		return this.selectList("ProfessionalMapper.queryExamProfessionalList", null);
	}

	/**
	 * 冻结恢复操作
	 * @param professional
	 */
	public void updateStatus(ExamProfessional professional) {
		this.update("ProfessionalMapper.updateStatus", professional);
		
	}

	/**
	 * 根据id获取考试专业
	 */
	public ExamProfessional getProfessionalById(Long id) {
		return this.selectOne("ProfessionalMapper.getProfessionalById", id);
	}

	/**
	 * 修改考试专业
	 */
	public void updateProfessional(ExamProfessional examProfessional) {
		this.update("ProfessionalMapper.updateProfessional", examProfessional);
	}

	/**
	 * 查询所有正常使用（非冻结）专业
	 * @return
	 */
	public List<ExamProfessional> queryProfessionalList() {
		return this.selectList("ProfessionalMapper.queryProfessionalList", null);
	}
	/**
	 * 根据subjectId查询Professional
	 * @param id
	 * @return
	 */
	public ExamProfessionalDTO getProfessionalBySubjectId(Long id) {
		return this.selectOne("ProfessionalMapper.getProfessionalBySubjectId", id);
	}


}
