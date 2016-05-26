package com.atdld.os.exam.controller.professional;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.exam.controller.common.ExamBaseController;
import com.atdld.os.exam.dao.subject.SubjectDao;
import com.atdld.os.exam.entity.professional.ExamProfessional;
import com.atdld.os.exam.entity.professional.ExamSubject;
import com.atdld.os.exam.service.professional.ProfessionalService;

/**
 * 
 * @author huan.liu
 * @ClassName ProfessionalController
 * @package com.atdld.os.exam.controller.professional
 * @description 考试专业-科目管理
 * @Create Date: 2014-12-26 上午9:45:45
 */
@Controller
@RequestMapping("/admin")
public class AdminProfessionalController extends ExamBaseController {
	private static final Logger logger = Logger.getLogger(AdminProfessionalController.class);
	@Autowired
	private ProfessionalService professionalService;
	@Autowired
	private SubjectDao subjectDao;
	
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("professional")
    public void initBinderProfessional(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("professional.");
    }
	// 绑定变量名字和属性，参数封装进类
	@InitBinder("examSubject")
	public void initBinderExamSubject(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("examSubject.");
	}
	
	
	// 返回路径
    private String toAddProfessionalView = getViewPath("/admin/professional/professional_add");
    private String professionalListView = getViewPath("/admin/professional/professional_list");
    private String toAddExamSubejctView = getViewPath("/admin/professional/exam_subject_add");
    private String toUpdateProfessionalView = getViewPath("/admin/professional/professional_update");
    private String toUpdateSubjectView = getViewPath("/admin/professional/exam_subject_update");
	
    /**
     * 跳转到添加考试专业页面
     * @param request
     * @return
     */
    @RequestMapping("/professional/toAddProfessional")
    public String toAddProfessional(HttpServletRequest request) {
        try {
        	
        } catch (Exception e) {
            logger.error("ProfessionalController.toAddProfessional", e);
            return setExceptionRequest(request, e);
        }
        return toAddProfessionalView;
    }
    /**
     * 添加考试专业
     * @param request
     * @param professional
     * @return
     */
    @RequestMapping("/professional/addProfessional")
    public String addProfessional(HttpServletRequest request,@ModelAttribute("professional") ExamProfessional professional) {
    	try {
    		professional.setCreateTime(new Date());
    		professionalService.addProfessional(professional);
    	} catch (Exception e) {
    		logger.error("ProfessionalController.addProfessional", e);
    		return setExceptionRequest(request, e);
    	}
    	return "redirect:/admin/professional/professionalList";
    }
    /**
     * 跳转到修改考试专业页面
     * @param request
     * @return
     */
    @RequestMapping("/professional/toUpdateProfessional/{id}")
    public String toUpdateProfessional(@PathVariable Long id,HttpServletRequest request) {
        try {
        	ExamProfessional examProfessional = professionalService.getProfessionalById(id);
        	request.setAttribute("examProfessional", examProfessional);
        } catch (Exception e) {
            logger.error("ProfessionalController.toAddProfessional", e);
            return setExceptionRequest(request, e);
        }
        return toUpdateProfessionalView;
    }
    /**
     * 修改考试专业
     * @param request
     * @param professional
     * @return
     */
    @RequestMapping("/professional/updateProfessional")
    public String updateProfessional(HttpServletRequest request,@ModelAttribute("professional") ExamProfessional examProfessional) {
    	try {
    		if(examProfessional!=null){
    			examProfessional.setUpdateTime(new Date());
    			professionalService.updateProfessional(examProfessional);
    		}
    	} catch (Exception e) {
    		logger.error("ProfessionalController.updateProfessional", e);
    		return setExceptionRequest(request, e);
    	}
    	return "redirect:/admin/professional/professionalList";
    }
    /**
     * 跳转到添加考试页面
     * @param request
     * @return
     */
    @RequestMapping("/professional/toAddExamSubejct/{id}")
    public String toAddExamSubejct(@PathVariable Long id,HttpServletRequest request) {
        try {
        	request.setAttribute("professionalId", id);
        } catch (Exception e) {
            logger.error("ProfessionalController.toAddExamSubejct", e);
            return setExceptionRequest(request, e);
        }
        return toAddExamSubejctView;
    }
    /**
     * 添加考试科目
     * @param request
     * @param examSubject
     * @return
     */
    @RequestMapping("/professional/addExamSubejct")
    public String addExamSubejct(HttpServletRequest request,@ModelAttribute("examSubject") ExamSubject examSubject) {
    	try {
    		if(examSubject!=null){
    			examSubject.setCreateTime(new Date());
    			subjectDao.addExamSubject(examSubject);
    		}
    		
    	} catch (Exception e) {
    		logger.error("ProfessionalController.addProfessional", e);
    		return setExceptionRequest(request, e);
    	}
    	return "redirect:/admin/professional/professionalList";
    }
    /**
     * 跳转到修改考试科目页面
     * @param request
     * @return
     */
    @RequestMapping("/professional/toUpdateSubject/{id}")
    public String toUpdateSubject(@PathVariable Long id,HttpServletRequest request) {
        try {
        	ExamSubject examSubject = subjectDao.getExamSubjectById(id);
        	request.setAttribute("examSubject", examSubject);
        } catch (Exception e) {
            logger.error("ProfessionalController.toUpdateSubject", e);
            return setExceptionRequest(request, e);
        }
        return toUpdateSubjectView;
    }
    /**
     * 修改考试科目
     * @param request
     * @param examSubject
     * @return
     */
    @RequestMapping("/professional/updateExamSubejct")
    public String updateExamSubejct(HttpServletRequest request,@ModelAttribute("examSubject") ExamSubject examSubject) {
    	try {
    		if(examSubject!=null){
    			examSubject.setUpdateTime(new Date());
    			subjectDao.updateExamSubject(examSubject);
    		}
    	} catch (Exception e) {
    		logger.error("ProfessionalController.updateExamSubject", e);
    		return setExceptionRequest(request, e);
    	}
    	return "redirect:/admin/professional/professionalList";
    }
    /**
     * 专业考试列表
     * @param request
     * @return
     */
    @RequestMapping("/professional/professionalList")
    public String professionalShow(HttpServletRequest request) {
    	try {
    		//查询专业
    		List<ExamProfessional> professionalList = professionalService.queryExamProfessionalListForAdmin();
    		request.setAttribute("professionalList", professionalList);
    	} catch (Exception e) {
    		logger.error("ProfessionalController.professionalShow", e);
    		return setExceptionRequest(request, e);
    	}
    	return professionalListView;
    }
    /**
     * 冻结与恢复操作
     * @param request
     * @param professional
     * @return
     */
    @RequestMapping("/professional/updateStatus")
    @ResponseBody
    public Map<String, Object> updateStatus(HttpServletRequest request,@ModelAttribute("professional") ExamProfessional professional) {
    	try {
    		//修改状态
    		professionalService.updateStatus(professional);
    		this.setJson(true, "", null);
    	} catch (Exception e) {
    		logger.error("ProfessionalController.updateStatus", e);
    		this.setJson(false, "", null);
    	}
    	return json;
    }
    /**
     * 冻结与恢复考试科目操作
     * @param request
     * @param professional
     * @return
     */
    @RequestMapping("/professional/updateSubjectStatus")
    @ResponseBody
    public Map<String, Object> updateSubjectStatus(HttpServletRequest request,@ModelAttribute("examSubject") ExamSubject examSubject) {
    	try {
    		//修改考试科目状态
    		examSubject.setUpdateTime(new Date());
    		subjectDao.updateSubjectStatus(examSubject);
    		this.setJson(true, "", null);
    	} catch (Exception e) {
    		logger.error("ProfessionalController.updateSubjectStatus", e);
    		this.setJson(false, "", null);
    	}
    	return json;
    }
    
    
}
