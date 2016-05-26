package io.wangxiao.edu.home.controller.user;

import io.wangxiao.edu.home.common.EduBaseController;
import io.wangxiao.edu.home.entity.user.UserArea;
import io.wangxiao.edu.home.service.user.UserAreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserAreaController extends EduBaseController {
    private Logger logger = LoggerFactory.getLogger(UserAreaController.class);
    @Autowired
    private UserAreaService userAreaService;

    /**
     * 快速登录，为ajax提供
     *
     * @return
     */
    @RequestMapping("/area/ajax/parentid")
    @ResponseBody
    public Object areaByParentId(HttpServletRequest request, @RequestParam("parentId") Long parentId) {
        Map<String, Object> json = null;
        try {
            // 通过父id查询子类
            UserArea userArea = new UserArea();
            userArea.setParentId(parentId);
            List<UserArea> userAreaList = userAreaService.getUserAreaList(userArea);
            json = this.getJsonMap(true, "success", userAreaList);
        } catch (Exception e) {
            logger.error("Usercontroller.dologin", e);
            json = this.getJsonMap(false, "error", "系统异常");
            return json;
        }
        return json;
    }

}