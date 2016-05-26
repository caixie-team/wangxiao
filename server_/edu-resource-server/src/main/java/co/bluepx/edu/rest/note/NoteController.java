package io.wangxiao.api.rest.note;

import co.bluepx.edu.course.entity.CourseNote;
import co.bluepx.edu.course.service.CourseNoteService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 笔记
 * Created by longjianlin on 16/1/14.
 */
@Api(value = "course-api", description = "笔记 API", position = 1)
@RestController
@RequestMapping(value = "/api/note")
public class NoteController {

    @Autowired
    public CourseNoteService courseNoteService;

    @ApiOperation(value = "根据条件查询笔记列表", notes = "返回笔记列表", response = CourseNote.class, position = 1)
    @RequestMapping(value = "/explore", method = RequestMethod.GET)
    public ResponseEntity get(@ModelAttribute CourseNote courseNote, @RequestParam int pageNum, @RequestParam int pageSize) {
        PageInfo<CourseNote> page = courseNoteService.findNotesByPage(courseNote, pageNum, pageSize);
        return new ResponseEntity(page, HttpStatus.OK);
    }


}
