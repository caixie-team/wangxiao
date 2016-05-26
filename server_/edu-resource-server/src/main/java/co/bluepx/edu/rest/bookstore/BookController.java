package io.wangxiao.api.rest.bookstore;

import co.bluepx.edu.bookstore.entity.Book;
import co.bluepx.edu.bookstore.entity.QueryBookCondition;
import co.bluepx.edu.bookstore.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bison on 1/18/16.
 * Book Api
 */
@Api(value = "bookstore-api", description = "图书商店 API", position = 1)
@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @ApiOperation(value = "根据 ID 查询图书详情", notes = "返回图书详情", response = Book.class, position = 2)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") Object id) {

        return new ResponseEntity(bookService.findById(id), HttpStatus.OK);

    }

    @ApiOperation(value = "根据专业查询图书列表", notes = "返回图书列表", response = Book.class, position = 2)
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public ResponseEntity query(@PathVariable("id") Integer id, @RequestParam int pageNum, @RequestParam int pageSize) {
        QueryBookCondition query = new QueryBookCondition();
        query.setBookSubjectid(id);

        return new ResponseEntity(bookService.findBookDTOByPage(query, pageNum, pageSize), HttpStatus.OK);

    }

//    @ApiOperation(value = "根据查询图书列表", notes = "返回带有分页信息的图书列表", response = Book.class, position = 2)
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity query(@RequestParam(value = "query") QueryBookCondition query, @RequestParam int pageNum, @RequestParam int pageSize) {
//
//        return new ResponseEntity(bookService.findBookDTOByPage(query, pageNum, pageSize), HttpStatus.OK);
//
//    }
}
