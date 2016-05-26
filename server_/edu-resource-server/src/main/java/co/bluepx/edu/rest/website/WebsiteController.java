package io.wangxiao.api.rest.website;

import co.bluepx.edu.course.service.CourseService;
import co.bluepx.edu.website.entity.WebsiteNavigate;
import co.bluepx.edu.website.entity.WebsiteProfile;
import co.bluepx.edu.website.service.WebsiteNavigateService;
import co.bluepx.edu.website.service.WebsiteProfileService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bison on 12/29/15.
 * <p>
 * 网站配置 api
 */
@Api(value = "website-api", description = "网站信息API", position = 1)
@RestController
@RequestMapping(value = "/api/website")
public class WebsiteController {

    @Autowired
    WebsiteProfileService websiteProfileService;

    @Autowired
    WebsiteNavigateService websiteNavigateService;

    // 课程服务
    @Autowired
    CourseService courseService;

    /**
     * 根据ID查询网站配置信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据 ID 查询网站信息", notes = "返回网站实体对象", response = WebsiteProfile.class, position = 2)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") Long id) {

        return new ResponseEntity(websiteProfileService.findById(id), HttpStatus.OK);
    }

    /**
     * 网站导航信息
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/navs/{type}", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "查询成功", response = WebsiteNavigate.class),
            @ApiResponse(code = 404, message = "找不到页面"),
            @ApiResponse(code = 500, message = "内部报错")}
    )
    public ResponseEntity getNav(
            @ApiParam(name = "type", value = "网站导航类型 {INDEX,TABS,USER,SNS,FRIENDLINK} ", required = true)
            @PathVariable("type") String type) {

        if (type.equals("all"))

            return new ResponseEntity(websiteNavigateService.findAll(), HttpStatus.OK);

        return new ResponseEntity(websiteNavigateService.findByType(type), HttpStatus.OK);
    }


    /**
     * 网站配置信息
     * {logo,web}
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/profile/{type}", method = RequestMethod.GET)
    public ResponseEntity getProfile(
            @ApiParam(name = "type", value = "网站配置信息," +
                    "{web,alipay,email,keyword," +
                    "logo,qq,sina,cc,p56,weixin," +
                    "censusCode,online,sale,letv,yee} ",
                    required = true)
            @PathVariable("type") String type) {


        return new ResponseEntity(websiteProfileService.findByType(type), HttpStatus.OK);
    }


    /**
     * 网站 banner
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/banner/{type}", method = RequestMethod.GET)
    public ResponseEntity getBanner(
            @ApiParam(name = "type", value = "网站 Banner," +
                    "{indexCenterBanner : 首页 Banner} ",
                    required = true)
            @PathVariable("type") String type) {


        return new ResponseEntity(websiteProfileService.findByType(type), HttpStatus.OK);
    }

}
