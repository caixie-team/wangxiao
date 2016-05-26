package com.atdld.os.edu.controller.book;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atdld.os.common.util.DateEditor;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.common.EduBaseController;
import com.atdld.os.edu.entity.Book.Book;
import com.atdld.os.edu.entity.Book.QueryBookCondition;
import com.atdld.os.edu.service.book.BookService;
import com.atdld.os.sysuser.entity.QuerySubject;
import com.atdld.os.sysuser.entity.Subject;
import com.atdld.os.sysuser.service.SubjectService;

/**
 * 
 * @ClassName com.atdld.os.edu.controller.book.AdminBookController
 * @description
 * @author :
 * @Create Date : 2014年10月27日 下午2:03:38
 */
@Controller
@RequestMapping("/admin")
public class AdminBookController extends EduBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminBookController.class);

	private static final String toAddBook = getViewPath("/admin/book/add_book");// 图书添加页面
	private static final String getBookList = getViewPath("/admin/book/book_list");// 图书添加页面
	private static final String toBookInfo = getViewPath("/admin/book/book_info");// 图书详情页面
	private static final String toUpdate = getViewPath("/admin/book/book_update");// 图书更新页面

	@Autowired
	private SubjectService subjectService;
	@Autowired
	private BookService bookService;

	// 绑定变量名参数
	@InitBinder("book")
	public void initBinderBook(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("book.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	// 绑定变量名参数
	@InitBinder("queryBookCondition")
	public void initBinderQueryBookCondition(HttpServletRequest request, ServletRequestDataBinder binder) {
		binder.setFieldDefaultPrefix("queryBookCondition.");
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	/**
	 * 跳转图书添加页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/book/toadd")
	public String toAddBook(HttpServletRequest request) {
		try {
			// 查询专业
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			request.setAttribute("subjectList", gson.toJson(subjectList));
		} catch (Exception e) {
			logger.error("toAddBook", e);
			return setExceptionRequest(request, e);
		}
		return toAddBook;
	}

	/**
	 * 添加图书
	 * 
	 * @param request
	 * @param book
	 * @return
	 */
	@RequestMapping("/book/add")
	public String addBook(HttpServletRequest request, @ModelAttribute Book book) {
		try {
			String[] disProperty = request.getParameterValues("disProperty");
			String disPropertys = "";
			if (ObjectUtils.isNotNull(disProperty) && disProperty.length > 0) {
				for (String disPro : disProperty) {
					disPropertys += disPro + ",";
				}
				disPropertys = disPropertys.substring(0, disPropertys.length() - 1);
				book.setDisProperty(disPropertys);
			}
			book.setAddTime(new Date());
			book.setUpdateTime(new Date());
			book.setStatus(1);// 正常
			if (ObjectUtils.isNotNull(book.getShopState())) {
				if (book.getShopState() == 2) {// 下架
					book.setDropTime(new Date());
				}
				if (book.getShopState() == 1) {// 上架
					book.setUpTime(new Date());
				}
			}
			if (ObjectUtils.isNotNull(book.getWeight())) {
				book.setWeight(book.getWeight() + request.getParameter("gram"));
			}
			bookService.addBook(book);
		} catch (Exception e) {
			logger.error("addBook", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/book/list";
	}

	/**
	 * 查询图书列表
	 * 
	 * @param request
	 * @param model
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	@RequestMapping("/book/list")
	public String getBookList(HttpServletRequest request, Model model, @ModelAttribute QueryBookCondition queryBookCondition, @ModelAttribute("page") PageEntity page) {
		try {
			// 查询专业
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			// 设置分页
			this.setPage(page);
			// 查询图书列表
			List<Book> bookList = bookService.queryBookList(queryBookCondition, this.getPage());
			model.addAttribute("bookList", bookList);
			model.addAttribute("page", this.getPage());
			model.addAttribute("subjectList", gson.toJson(subjectList));
		} catch (Exception e) {
			logger.error("getBookList", e);
			return setExceptionRequest(request, e);
		}
		return getBookList;
	}

	/**
	 * 查询图书详情
	 * 
	 * @param request
	 * @param model
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/book/info/{bookId}")
	public String getBookInfo(HttpServletRequest request, Model model, @PathVariable("bookId") Integer bookId) {
		try {
			// 查询专业
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			QueryBookCondition queryBookCondition = new QueryBookCondition();
			queryBookCondition.setBookId(bookId);
			// 查询图书详情
			Book book = bookService.queryBookById(queryBookCondition);
			model.addAttribute("subjectList", gson.toJson(subjectList));
			model.addAttribute("book", book);
		} catch (Exception e) {
			logger.error("getBookInfo", e);
			return setExceptionRequest(request, e);
		}
		return toBookInfo;
	}
	/**
	 * 跳转跟新页面
	 * 
	 * @param request
	 * @param model
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/book/toupdate/{bookId}")
	public String getBookInfoForUpdate(HttpServletRequest request, Model model, @PathVariable("bookId") Integer bookId) {
		try {
			// 查询专业
			QuerySubject querySubject = new QuerySubject();
			List<Subject> subjectList = subjectService.getSubjectList(querySubject);
			QueryBookCondition queryBookCondition = new QueryBookCondition();
			queryBookCondition.setBookId(bookId);
			// 查询图书详情
			Book book = bookService.queryBookById(queryBookCondition);
			model.addAttribute("subjectList", gson.toJson(subjectList));
			model.addAttribute("book", book);
		} catch (Exception e) {
			logger.error("getBookInfo", e);
			return setExceptionRequest(request, e);
		}
		return toUpdate;
	}
	/**
	 * 更新操作
	 * 
	 * @return
	 */
	@RequestMapping("/book/update")
	public String updateBook(HttpServletRequest request,@ModelAttribute Book book) {
		try {
			if (ObjectUtils.isNotNull(book)) {
				String[] disProperty = request.getParameterValues("disProperty");
				String disPropertys = "";
				if (ObjectUtils.isNotNull(disProperty) && disProperty.length > 0) {
					for (String disPro : disProperty) {
						disPropertys += disPro + ",";
					}
					disPropertys = disPropertys.substring(0, disPropertys.length() - 1);
					book.setDisProperty(disPropertys);
				}
				book.setUpdateTime(new Date());
				if (ObjectUtils.isNotNull(book.getShopState())) {
					if (book.getShopState() == 2) {// 下架
						book.setDropTime(new Date());
					}
					if (book.getShopState() == 1) {// 上架
						book.setUpTime(new Date());
					}
				}
				if (ObjectUtils.isNotNull(book.getWeight())) {
					book.setWeight(book.getWeight().replaceAll("千克", "").replaceAll("克", "") + request.getParameter("gram"));
				}
				bookService.updateBook(book);
			}
		} catch (Exception e) {
			logger.error("BookAction.updateBook", e);
		}
		return "redirect:/admin/book/list";
	}
	/**
	 * 刪除图书(冻结解冻同一方法)
	 * @return
	 */
	@RequestMapping("/book/delbook")
	@ResponseBody
	public Map<String,Object> updateBookStatus(HttpServletRequest request,@ModelAttribute Book book) {
		try {
			if(ObjectUtils.isNotNull(book)&&book.getStatus()!=0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bookId", book.getBookId());
				map.put("status", book.getStatus());
				bookService.updateBookStatus(map);
				this.setJson(true, "删除成功", null);
				return json;
			}
			this.setJson(false, "删除失败", null);
		} catch (Exception e) {
			logger.error("BookAction.updateBookStatus",e);
			this.setJson(false, "system is error", null);
		}
		return json;// 调取查询页面
	}
}
