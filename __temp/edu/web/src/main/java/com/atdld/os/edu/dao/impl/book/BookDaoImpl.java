package com.atdld.os.edu.dao.impl.book;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atdld.os.core.dao.impl.common.GenericDaoImpl;
import com.atdld.os.core.entity.PageEntity;
import com.atdld.os.core.util.ObjectUtils;
import com.atdld.os.edu.dao.book.BookDao;
import com.atdld.os.edu.entity.Book.Book;
import com.atdld.os.edu.entity.Book.BookDTO;
import com.atdld.os.edu.entity.Book.QueryBookCondition;

/**
 * 
 * @ClassName  com.atdld.os.edu.dao.impl.book.BookDaoImpl
 * @description
 * @author :
 * @Create Date : 2014年10月27日 上午11:49:43
 */
@Repository("bookDao")
public class BookDaoImpl extends GenericDaoImpl implements BookDao{

	/**
	 * 添加 book
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book) {
		this.insert("BookMapper.addBook", book);
	}

	/**
	 * 查询图书列表
	 * 
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	public List<Book> queryBookList(QueryBookCondition queryBookCondition,PageEntity page) {
		return this.queryForListPage("BookMapper.queryBookList", queryBookCondition, page);
	}

	/**
	 * 查询图书详情
	 * 
	 * @param queryBookCondition
	 * @return
	 * @throws Exception
	 */
	public Book queryBookById(QueryBookCondition queryBookCondition) {
		List<Book> booklist=this.selectList("BookMapper.queryBookById", queryBookCondition);
		if(ObjectUtils.isNotNull(booklist)&&booklist.size()>0){
			return booklist.get(0);
		}
		return null;
	}

	/**
	 * 更新图书
	 * 
	 * @param book
	 * @throws Exception
	 */
	public void updateBook(Book book) {
		this.update("BookMapper.updateBook", book);
	}

	/**
	 * 删除图书
	 * 
	 * @param map
	 * @throws Exception
	 */
	public void updateBookStatus(Map<String, Object> map) {
		this.update("BookMapper.updateBookStatus", map);
	}
	/**
	 * 查询图书列表（前台）
	 * 
	 * @param queryBookCondition
	 * @param page
	 * @return
	 */
	public List<BookDTO> getbookDTOList(QueryBookCondition queryBookCondition, PageEntity page) {
		return this.queryForListPage("BookMapper.getbookDTOList", queryBookCondition, page);
	}
}
