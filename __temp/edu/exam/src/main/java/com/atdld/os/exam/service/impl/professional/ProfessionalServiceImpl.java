package com.atdld.os.exam.service.impl.professional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.common.constants.MemConstans;
import com.atdld.os.core.service.cache.MemCache;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.exam.dao.professional.ProfessionalDao;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamProfessionalDTO;
import com.atdld.os.exam.service.professional.ProfessionalService;

@Service("professionalService")
public class ProfessionalServiceImpl implements ProfessionalService {
	@Autowired
	private ProfessionalDao professionalDao;
	
	private MemCache memCache = MemCache.getInstance();
	/**
	 * 添加考试专业
	 * @param professional
	 */
	public void addProfessional(ExamProfessional professional) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		professionalDao.addProfessional(professional);
	}
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<ExamProfessional> queryExamProfessionalListForAdmin() {
		return professionalDao.queryExamProfessionalListForAdmin();
	}
	/**
	 * 查询所有专业
	 * @return
	 */
	public List<ExamProfessional> queryExamProfessionalList() {
		return professionalDao.queryExamProfessionalList();
	}
	/**
	 * 冻结恢复操作
	 * @param professional
	 */
	public void updateStatus(ExamProfessional professional) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		professionalDao.updateStatus(professional);
	}
	/**
	 * 根据id获取考试专业
	 */
	public ExamProfessional getProfessionalById(Long id) {
		return professionalDao.getProfessionalById(id);
	}
	/**
	 * 修改考试专业
	 * @param examProfessional
	 */
	public void updateProfessional(ExamProfessional examProfessional) {
		memCache.remove(MemConstans.MEM_ALL_PROFESSIONAL);
		professionalDao.updateProfessional(examProfessional);
	}
	/**
	 * 查询所有正常使用（非冻结）专业
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ExamProfessional> queryProfessionalList() {
		List<ExamProfessional> professionalList = (List<ExamProfessional>) memCache.get(MemConstans.MEM_ALL_PROFESSIONAL);
	        if (ObjectUtils.isNull(professionalList)) {
	        	professionalList = professionalDao.queryProfessionalList(); 
	                if (!ObjectUtils.isNull(professionalList)) {
	                    memCache.set(MemConstans.MEM_ALL_PROFESSIONAL, professionalList,MemConstans.MEM_ALL_PROFESSIONAL_TIME);
	                }
	        }
	        return professionalList;
	}
	/**
	 * 根据subjectId查询Professional
	 * @param id
	 * @return
	 */
	public ExamProfessionalDTO getProfessionalBySubjectId(Long id) {
		return professionalDao.getProfessionalBySubjectId(id);
	}
   
}

