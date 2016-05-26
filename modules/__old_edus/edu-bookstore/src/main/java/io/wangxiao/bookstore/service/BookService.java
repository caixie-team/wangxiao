package io.wangxiao.bookstore.service;

import io.wangxiao.bookstore.entity.Book;
import io.wangxiao.bookstore.entity.BookDTO;
import io.wangxiao.bookstore.entity.QueryBookCondition;
import io.wangxiao.core.PageInfo;

public interface BookService {

    Book findById(Object id);
    PageInfo<BookDTO> findBookDTOByPage(QueryBookCondition queryBookCondition, int pageNum, int pageSize);


        /**
         * 根据ID查询图书详情
         *
         * @param id
         * @return
         */
//    public Book findBookByI(Long id) {
//
//        return baseDao.find(
//                Arrays.asList(
//                        Condition.parseCondition("book_id.int.eq").setValue(id)
//                )
//        ).get(0);
//    }

    /**
     * 根据条件, 查询图书列表
     *
     * @param queryBookCondition
     * @return
     */
//    public PageInfo<BookDTO> findBookDTOByPage(QueryBookCondition queryBookCondition, int pageNum, int pageSize){
//
//        return PageHelper
//                .startPage(pageNum, pageSize)
//                .doSelectPageInfo(
//                        () -> baseDao.findBookDTOList(queryBookCondition)
//                );
//    }
//
//
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
