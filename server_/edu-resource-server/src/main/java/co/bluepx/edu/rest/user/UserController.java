package io.wangxiao.api.rest.user;

import co.bluepx.edu.core.util.CollectionUtil;
import co.bluepx.edu.course.entity.Course;
import co.bluepx.edu.course.entity.CourseFavorites;
import co.bluepx.edu.course.entity.FavouriteCourseDTO;
import co.bluepx.edu.course.entity.TrxorderDetail;
import co.bluepx.edu.course.service.CourseFavoritesService;
import co.bluepx.edu.course.service.CourseService;
import co.bluepx.edu.course.service.TrxorderDetailService;
import co.bluepx.edu.user.entity.User;
import co.bluepx.edu.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by bison on 1/8/16.
 *
 * 用户API
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    TrxorderDetailService trxorderDetailService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseFavoritesService courseFavoritesService;


    @ApiOperation(value = "根据权限信息返回当前用户信息")
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    public User me(Authentication authentication) {

        return userService.findById(authentication.getName());
    }


    @ApiOperation(value = "获取用户购买的课程列表", notes = "返回购买课程列表", response = Course.class)
    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ResponseEntity courses(Authentication authentication) {

        List<TrxorderDetail> lists = trxorderDetailService.getTrxorderDetailListBuy(Long.valueOf(authentication.getName()));
        return new ResponseEntity(courseService.findCourseListByCourseIds(lists), HttpStatus.OK);
    }

    /// START 课程收藏 API
    ///

    @ApiOperation(value = "获取当前用户收藏的课程,用户由 header token 中取得", notes = "返回收藏的课程列表", response = FavouriteCourseDTO.class)
    @RequestMapping(value = "/course/fav", method = RequestMethod.GET)
    public ResponseEntity favGet(Authentication authentication, @RequestParam int pageNum, @RequestParam int pageSize) {

        return new ResponseEntity(courseFavoritesService.findCourseFavoritesByUserId(
                Long.valueOf(authentication.getName()),
                pageNum,
                pageSize
        ), HttpStatus.OK);
    }

    //TODO:(1) 这里将会有权限问题,如果tocken 被拦截就有全部删除的权限,需要约束为当前用户 @Bison
    @ApiOperation(value = "根据课程id 或ids, 删除当前用户收藏的课程", notes = "请求参数格式为 /course/fav?ids=1,2,3")
    @RequestMapping(value = "/course/fav", method = RequestMethod.DELETE)
    public ResponseEntity favDel(@RequestParam String ids) {

        //TODO:(2) try/catch
        courseFavoritesService.deleteByIds(CollectionUtil.toLongCollection(ids, ","));

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "添加收藏课程", notes = "post 提交课程id ", httpMethod = "HttpStatus.302, 数据已存在")
    @RequestMapping(value = "/course/fav", method = RequestMethod.POST)
    public ResponseEntity favAdd(Authentication authentication, Long id) {

        //TODO:(3) try/catch
        if (
            // Save entity
                courseFavoritesService.addCourseFavorites(

                        new CourseFavorites(
                                // CourseId
                                id,
                                // UserId
                                Long.valueOf(authentication.getName()),
                                // Date
                                new Date()))
                )
            return new ResponseEntity(HttpStatus.OK);

        // 课程ID存在
        return new ResponseEntity(HttpStatus.FOUND);

    }
    ///END 课程收藏 API
    ///

}
