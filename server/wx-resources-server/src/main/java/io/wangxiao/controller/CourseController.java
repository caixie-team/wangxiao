package io.wangxiao.controller;

import io.swagger.annotations.ApiOperation;
import io.wangxiao.course.service.CourseService;
import io.wangxiao.course.vo.CourseVo;
import org.beetl.json.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Created by bison on 5/28/16.
 */
@Controller
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping("/index")
    public String welcome(Map<String, Object> model) {

        JsonTool json = new JsonTool();
        model.put("time", new Date());
        model.put("message", "Hello Spring Boot Beetl!");
//        model.put("courses", courseService.findRecommendCourses(2L));
//        model.put("navs", json.serialize(websiteNavigateService.findByType("INDEX")));

        return "index";
    }

    /**
     * 根据ID查询网站配置信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据 ID 查询课程推荐类别", notes = "返回课程 VO", response = CourseVo.class, position = 3)
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public ResponseEntity getRecommendCourses(@PathVariable("id") Long id) {

        // 首页显示专业
        // 推荐专业
//        List<Subject> subjectShowIndex = subjectService.getSubjectByShowIndex();
//        model.addAttribute("subjectShowIndex", subjectShowIndex);
        return new ResponseEntity(courseService.findRecommendCourses(id), HttpStatus.OK);
    }

}
