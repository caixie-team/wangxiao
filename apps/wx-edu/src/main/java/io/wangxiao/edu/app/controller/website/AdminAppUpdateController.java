package io.wangxiao.edu.app.controller.website;

import io.wangxiao.edu.app.common.AppBaseController;
import io.wangxiao.edu.app.entity.app.AppUpdate;
import io.wangxiao.edu.app.service.edu.AppUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminAppUpdateController extends AppBaseController {
    private static Logger logger = LoggerFactory.getLogger(AdminAppUpdateController.class);

    private static String listPage = getViewPath("/admin/updateApp/update-list");

    private static String updatePage = getViewPath("/admin/updateApp/update-infro");

    @InitBinder({"appUpdate"})
    public void initAppUpdate(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("appUpdate.");
    }

    @Autowired
    private AppUpdateService appUpdateService;

    @RequestMapping("/app/updateList")
    public String showUpdateList(HttpServletRequest request) {
        try {
            List<AppUpdate> list = appUpdateService.queryAllList();
            request.setAttribute("list", list);
        } catch (Exception e) {
            logger.error("showUpdateList()---error", e);
            return this.setExceptionRequest(request, e);
        }
        return listPage;
    }

    @RequestMapping("/app/initupdate/{updateId}")
    public String initUpdate(HttpServletRequest request, @PathVariable("updateId") long updateId) {
        try {
            AppUpdate appUpdate = appUpdateService.queryAppUpdateById(updateId);
            request.setAttribute("appUpdate", appUpdate);
        } catch (Exception e) {
            logger.error("initUpdate()--error", e);
            return this.setExceptionRequest(request, e);
        }
        return updatePage;
    }

    @RequestMapping("/app/updateApp")
    public String updateApp(HttpServletRequest request, @ModelAttribute("appUpdate") AppUpdate appUpdate) {
        try {
            appUpdateService.updateApp(appUpdate);
        } catch (Exception e) {
            logger.error("updateApp()--error", e);
            return this.setExceptionRequest(request, e);
        }
        return "redirect:/admin/app/updateList";
    }

}
