package com.atdld.os.edu.service.book;

import java.util.List;
import java.util.Map;

import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.edu.entity.Book.Book;
import com.atdld.os.edu.entity.Book.BookDTO;
import com.atdld.os.edu.entity.Book.QueryBookCondition;

/**
 * 
 * @ClassName  com.atdld.os.edu.service.book.BookService
 * @description
 * @author :
 * @Create Date : 2014年10月27日 下午1:55:51
 */
public interface BookService {
	/**
	 * 添加 book
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book);

	/**
	 * 查询图书列表
	 * 
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	public List<Book> queryBookList(QueryBookCondition queryBookCondition, PageEntity page);

	/**
	 * 查询图书详情
	 * 
	 * @param queryBookCondition
	 * @return
	 * @throws Exception
	 */
	public Book queryBookById(QueryBookCondition queryBookCondition);

	/**
	 * 更新图书
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void updateBook(Book book);

	/**
	 * 删除图书
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateBookStatus(Map<String, Object> map);
	/**
	 * 查询图书列表（前台）
	 * 
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	public List<BookDTO> getbookDTOList(QueryBookCondition queryBookCondition, PageEntity page);
}
