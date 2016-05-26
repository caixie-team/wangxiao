package io.wangxiao.api.rest.article;

import co.bluepx.edu.article.entity.Article;
import co.bluepx.edu.article.service.ArticleService;
import co.bluepx.edu.bookstore.entity.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bison on 1/19/16.
 */
@Api(value = "article-api", description = "咨讯 API", position = 1)
@RestController
@RequestMapping(value = "/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @ApiOperation(value = "根据 ID 查询资讯详情", notes = "返回资讯详情", response = Article.class, position = 2)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") long id) {

//        return new ResponseEntity(articleService.findById(id), HttpStatus.OK);
        return new ResponseEntity(articleService.findById(id), HttpStatus.OK);

    }

    @ApiOperation(value = "根据类型查询资讯列表", notes = "type 0:全部 1:资讯 2:公告", response = Book.class, position = 2)
    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET)
    public ResponseEntity query(@PathVariable("id") Long id, @RequestParam int pageNum, @RequestParam int pageSize) {

        return new ResponseEntity(articleService.findArticlesByPage(id, pageNum, pageSize), HttpStatus.OK);
    }
}
