package com.atdld.os.exam.service.impl.subject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.exam.dao.subject.SubjectDao;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.entity.subject.QuerySubject;
import com.atdld.os.exam.service.subject.SubjectService;

@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectDao subjectDao;
	
	private MemCache memCache = MemCache.getInstance();
	/**
	 * 添加考试科目
	 */
	public void addExamSubject(ExamSubject examSubject) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		subjectDao.addExamSubject(examSubject);
	}
	/**
	 * 根据id获取考试科目
	 */
	public ExamSubject getExamSubjectById(Long id) {
		return subjectDao.getExamSubjectById(id);
	}
	/**
	 * 修改考试科目
	 */
	public void updateExamSubject(ExamSubject examSubject) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		subjectDao.updateExamSubject(examSubject);
	}
	/**
	 * 修改考试科目状态
	 */
	public void updateSubjectStatus(ExamSubject examSubject) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		subjectDao.updateSubjectStatus(examSubject);
	}
	/**
	 * 根据ProfessionalId查询科目
	 * @param querySubject
	 * @return
	 */
	public List<ExamSubject> getSubjectListByProfessionalId(QuerySubject querySubject) {
		return subjectDao.getSubjectListByProfessionalId(querySubject);
	}
	/**
	 * 根据subjectid查询subject
	 * @param subject
	 * @return
	 */
	public ExamSubject getSubjectBySubjectId(ExamSubject subject) {
		return subjectDao.getSubjectBySubjectId(subject);
	}
   
}

