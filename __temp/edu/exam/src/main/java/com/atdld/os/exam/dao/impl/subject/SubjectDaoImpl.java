package com.atdld.os.exam.dao.impl.subject;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.exam.dao.subject.SubjectDao;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.subject.QuerySubject;

@Repository("subjectDao")
public class SubjectDaoImpl extends GenericDaoImpl implements SubjectDao{
	
	private MemCache memCache = MemCache.getInstance();
	/**
	 * 添加考试科目
	 */
	public void addExamSubject(ExamSubject examSubject) {
		this.insert("ExamSubjectMapper.createExamSubject", examSubject);
	}

	/**
	 * 根据id获取考试科目
	 */
	public ExamSubject getExamSubjectById(Long id) {
		return this.selectOne("ExamSubjectMapper.getExamSubjectById", id);
	}

	/**
	 * 修改考试科目
	 */
	public void updateExamSubject(ExamSubject examSubject) {
		this.update("ExamSubjectMapper.updateExamSubject", examSubject);
	}
	/**
	 * 修改考试状态
	 * @param examSubject
	 */
	public void updateSubjectStatus(ExamSubject examSubject) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		this.update("ExamSubjectMapper.updateSubjectStatus", examSubject);
	}
	/**
	 * 根据profissionalId查询科目
	 * @param querySubject
	 * @return
	 */
	public List<ExamSubject> getSubjectListByProfessionalId(QuerySubject querySubject) {
		return this.selectList("ExamSubjectMapper.getSubjectListByProfessionalId", querySubject);
	}

	/**
	 * 获取所有科目
	 */
	public List<ExamSubject> getSubjectList(QuerySubject querySubject) {
		return this.selectList("ExamSubjectMapper.getSubjectListAll", querySubject);
	}
	/**
	 * 根据subjectid查询subject
	 * @param subject
	 * @return
	 */
	public ExamSubject getSubjectBySubjectId(ExamSubject subject) {
		// TODO Auto-generated method stub
		return this.selectOne("ExamSubjectMapper.getSubjectBySubjectId", subject);
	}


}
