package com.atdld.os.edu.service.impl.book;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.dao.book.BookDao;
import com.atdld.os.edu.entity.Book.Book;
import com.atdld.os.edu.entity.Book.BookDTO;
import com.atdld.os.edu.entity.Book.QueryBookCondition;
import com.atdld.os.edu.service.book.BookService;

/**
 * 
 * @ClassName com.atdld.os.edu.service.impl.book.BookServiceImpl
 * @description
 * @author :
 * @Create Date : 2014年10月27日 下午1:57:18
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	/**
	 * 添加 book
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	/**
	 * 查询图书列表
	 * 
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	public List<Book> queryBookList(QueryBookCondition queryBookCondition, PageEntity page) {
		return bookDao.queryBookList(queryBookCondition, page);
	}

	/**
	 * 查询图书详情
	 * 
	 * @param queryBookCondition
	 * @return
	 * @throws Exception
	 */
	public Book queryBookById(QueryBookCondition queryBookCondition) {
		return bookDao.queryBookById(queryBookCondition);
	}

	/**
	 * 更新图书
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void updateBook(Book book) {
		bookDao.updateBook(book);
	}

	/**
	 * 删除图书
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateBookStatus(Map<String, Object> map) {
		bookDao.updateBookStatus(map);
	}
	/**
	 * 查询图书列表（前台）
	 * 
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	public List<BookDTO> getbookDTOList(QueryBookCondition queryBookCondition, PageEntity page) {
		return bookDao.getbookDTOList(queryBookCondition, page);
	}
}
