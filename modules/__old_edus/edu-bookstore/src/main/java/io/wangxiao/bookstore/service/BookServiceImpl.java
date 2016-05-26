package io.wangxiao.bookstore.service;

import io.wangxiao.bookstore.entity.Book;
import io.wangxiao.bookstore.entity.BookDTO;
import io.wangxiao.bookstore.entity.QueryBookCondition;
import io.wangxiao.core.BeetlSqlService;
import io.wangxiao.core.PageInfo;
import org.beetl.sql.core.SQLReady;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl extends BeetlSqlService<Book> implements BookService {

    private static String book_dtos = "book.dtos";

    /**
     * 根据ID查询图书详情
     *
     * @param id
     * @return
     */
    @Override
    public Book findById(Object id) {
        List<Book> books = dao.execute(new SQLReady("select * from edu_book where book_id = ?", id), Book.class);

        if (books != null && books.size() > 0) return books.get(0);

        return null;
    }

    public PageInfo findBookDTOByPage(QueryBookCondition queryBookCondition, int pageNum, int pageSize){

        return super.findDtosAllByPage(book_dtos, BookDTO.class, queryBookCondition, pageNum, pageSize);
    }

    /**
     * 根据条件, 查询图书列表
     *
     * @param queryBookCondition
     * @return
     */
//    public PageInfo<BookDTO> findBookDTOByPage(QueryBookCondition queryBookCondition, int pageNum, int pageSize) {

//        return PageHelper
//                .startPage(pageNum, pageSize)
//                .doSelectPageInfo(
//                        () -> baseDao.findBookDTOList(queryBookCondition)
//                );
//    }


    /**
     * 添加 book
     *
     * @param book
     * @throws Exception
     */
//	public void addBook(Book book);

    /**
     * 查询图书列表
     *
     * @param queryBookCondition
     * @param page
     * @return
     */
//	public List<Book> queryBookList(QueryBookCondition queryBookCondition, PageEntity page);

    /**
     * 查询图书详情
     *
     * @param queryBookCondition
     * @return
     * @throws Exception
     */
//	public Book queryBookById(QueryBookCondition queryBookCondition);

    /**
     * 更新图书
     *
     * @param book
     * @throws Exception
     */
//	public void updateBook(Book book);

    /**
     * 删除图书
     *
     * @param map
     * @throws Exception
     */
//	public void updateBookStatus(Map<String, Object> map);
    /**
     * 查询图书列表（前台）
     *
     * @param queryBookCondition
     * @param page
     * @return
     */
//	public List<BookDTO> getbookDTOList(QueryBookCondition queryBookCondition, PageEntity page);
}
