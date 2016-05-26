package io.wangxiao.api.rest.component;

import co.bluepx.edu.course.entity.Course;
import co.bluepx.edu.course.entity.Subject;
import co.bluepx.edu.course.service.SubjectService;
import co.bluepx.edu.member.entity.MemberType;
import co.bluepx.edu.member.service.MemberTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bison on 1/6/16.
 *
 * UI 组件数据 api,如果搜索栏组件
 */
@Api(value = "component-api", description = "组件数据 API", position = 1)
@RestController
@RequestMapping(value = "/api/component")
public class ComponentController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    MemberTypeService memberTypeService;

    @ApiOperation(value = "根据 ID 查询课程详情", notes = "返回课程详情", response = Course.class, position = 2)
    @RequestMapping(value = "/filter-list", method = RequestMethod.GET)
    public ResponseEntity filterList() {

        // 会员类型
        List<MemberType>memberTypes = memberTypeService.findAll();

        // 第一层级专业
        // TODO: 待替换为 Tree @Bison
        List<Subject> subjects = subjectService.findOneLevelSubject();

        Map<String,Object> filterMap = new HashMap<>();
        filterMap.put("member", memberTypes);
        filterMap.put("subject", subjects);

        return new ResponseEntity(filterMap, HttpStatus.OK);
    }
}
