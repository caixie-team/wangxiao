package io.wangxiao.api.rest.library;

import co.bluepx.edu.library.entity.Library;
import co.bluepx.edu.library.entity.QueryLibrary;
import co.bluepx.edu.library.service.LibraryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bison on 1/18/16.
 *
 */
@Api(value = "library-api", description = "文库 API", position = 1)
@RestController
@RequestMapping(value = "/api/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @ApiOperation(value = "根据 ID 查询文库详情", notes = "返回文库详情", response = Library.class, position = 2)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") long id) {

        return new ResponseEntity(libraryService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "根据专业查询文库列表", notes = "返回文库列表", response = Library.class, position = 2)
    @RequestMapping(value = "/subject/{id}", method = RequestMethod.GET)
    public ResponseEntity query(@PathVariable("id") Long id, @RequestParam int pageNum, @RequestParam int pageSize) {
        QueryLibrary query = new QueryLibrary();
        query.setSubjectId(id);

        return new ResponseEntity(libraryService.findLibraryDTOByPage(query, pageNum, pageSize), HttpStatus.OK);

    }
}
