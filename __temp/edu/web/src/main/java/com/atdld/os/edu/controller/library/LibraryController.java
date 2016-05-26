package com.atdld.os.edu.controller.library;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.library.Library;
import com.atdld.os.edu.entity.library.LibraryDTO;
import com.atdld.os.edu.entity.library.QueryLibrary;
import com.atdld.os.edu.service.library.LibraryService;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.service.SubjectService;

/**
 * 
 * @ClassName com.atdld.edu.library.action.LibraryController
 * @description
 * @author :
 * @Create Date : 2014年8月11日 下午2:44:53
 */
@Controller
public class LibraryController extends EduBaseController {


	private static final Logger logger = Logger.getLogger(LibraryController.class);
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private SubjectService subjectService;
	@InitBinder("library")
	public void initBinderLibrary(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("library.");
	}
	@InitBinder("subject")
	public void initBinderSubject(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("subject.");
	}
	@InitBinder("queryLibrary")
	public void initBinderQueryLibrary(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryLibrary.");
	}

	private static final String getLibraryList = getViewPath("/library/library_list");//文库列表
	private static final String libraryInfo = getViewPath("/library/library_info");//文库详情
	
	
	/**
	 * 查询文库
	 * 
	 * @return
	 */
	@RequestMapping("/library/list")
	public ModelAndView queryLibraryList(HttpServletRequest request,@ModelAttribute("page")PageEntity page,QueryLibrary queryLibrary) {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName(getLibraryList);
		try {
			//查找热门文库
			List<Library> hotLibrary=libraryService.hotLibraries(8);
			modelAndView.addObject("hotLibrary",hotLibrary);
			//查询一级专业
			List<Subject> subjectList = subjectService.getSubjectOneList();
			modelAndView.addObject("subjectList",subjectList);
			//查询条件专业
			Subject subject=new Subject();
			subject.setSubjectId(queryLibrary.getSubjectId());
			subject = subjectService.getSubjectBySubjectId(subject);
			modelAndView.addObject("subject",subject);
			//文库分页
			this.setPage(page);
			this.getPage().setPageSize(10);
			List<LibraryDTO> librarys=libraryService.queryLibrayList(queryLibrary,page);// 查询文库
			modelAndView.addObject("librarys",librarys);
			modelAndView.addObject("page",this.getPage());
		} catch (Exception e) {
			logger.error("LibraryController.queryLibraryList", e);
			return new ModelAndView(this.setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 查询单个文库
	 * 
	 * @return
	 */
	@RequestMapping("/library/info/{id}")
	public String queryLibraryById(HttpServletRequest request,@PathVariable("id") Long id,RedirectAttributes redirectAttributes,Model model) {
		try {
				Library library = new Library();
				library.setId(id);
				library=libraryService.queryLibraryById(library);
				if(ObjectUtils.isNull(library)){
					redirectAttributes.addAttribute("msg","对不起该文库已经下架或者删除");
			        return "redirect:/front/success";
				}
				libraryService.updateLibraryBrowseNumById(id);
				model.addAttribute("library",library);
//				if(library.getType()==2){//如果是图片集
//					List<LibraryImages> libraryImagesList=libraryService.queryLibraryImagesList(library.getId());
//					model.addAttribute("libraryImagesList",libraryImagesList);
//				}
			
		} catch (Exception e) {
			logger.error("LibraryController.queryLibraryById", e);
			return this.setExceptionRequest(request, e);
		}
		return libraryInfo;
	}
	
	/**
	 * 查找子专业方法
	 */
	@RequestMapping("/library/subject/{id}")
	@ResponseBody
	public Map<String,Object> getTwoSubjects(HttpServletRequest request,@PathVariable Long id){
		try {
			List<Subject> subjects = subjectService.getSubjectListByOne(id);
			this.setJson(true, "true", subjects);
		} catch (Exception e) {
			logger.error("LibraryController.getTwoSubjects", e);
			this.setJson(false, "error", null);
		}
		return json;
	}
}
