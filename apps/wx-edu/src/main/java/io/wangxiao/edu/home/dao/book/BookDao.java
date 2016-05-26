package io.wangxiao.edu.home.dao.book;

import io.wangxiao.commons.entity.PageEntity;
import io.wangxiao.edu.home.entity.Book.Book;
import io.wangxiao.edu.home.entity.Book.BookDTO;
import io.wangxiao.edu.home.entity.Book.QueryBookCondition;

import java.util.List;
import java.util.Map;

/**
 * @description 图书Dao
 */
public interface BookDao {
    /**
     * 添加 book
     *
     * @param book
     * @throws Exception
     */
    void addBook(Book book);

    /**
     * 查询图书列表
     *
     * @param queryBookCondition
     * @param page
     * @return
     */
    List<Book> queryBookList(QueryBookCondition queryBookCondition, PageEntity page);

    /**
     * 查询图书详情
     *
     * @param queryBookCondition
     * @return
     * @throws Exception
     */
    Book queryBookById(QueryBookCondition queryBookCondition);

    /**
     * 更新图书
     *
     * @param book
     * @throws Exception
     */
    void updateBook(Book book);

    /**
     * 删除图书
     *
     * @param map
     * @throws Exception
     */
    void updateBookStatus(Map<String, Object> map);

    /**
     * 查询图书列表（前台）
     *
     * @param queryBookCondition
     * @param page
     * @return
     */
    List<BookDTO> getbookDTOList(QueryBookCondition queryBookCondition, PageEntity page);
}
