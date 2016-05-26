package io.wangxiao.bookstore.dao;

import io.wangxiao.bookstore.entity.Book;
import io.wangxiao.bookstore.entity.BookDTO;
import io.wangxiao.bookstore.entity.QueryBookCondition;
import io.wangxiao.core.BaseDao;
import io.wangxiao.core.entity.PageEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookDao extends BaseDao<Book> {

    /**
     * 根据条件查询图书列表 dtos
     *
     * @param queryBookCondition
     * @return
     */
    List<BookDTO> findBookDTOList(@Param(value = "e") QueryBookCondition queryBookCondition);

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
