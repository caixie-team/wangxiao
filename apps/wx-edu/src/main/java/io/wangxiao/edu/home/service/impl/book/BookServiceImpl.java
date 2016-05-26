package io.wangxiao.edu.home.service.impl.book;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.dao.book.BookDao;
import io.wangxiao.edu.home.entity.Book.Book;
import io.wangxiao.edu.home.entity.Book.BookDTO;
import io.wangxiao.edu.home.entity.Book.QueryBookCondition;
import io.wangxiao.edu.home.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
