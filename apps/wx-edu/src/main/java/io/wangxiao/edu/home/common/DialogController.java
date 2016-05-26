package io.wangxiao.edu.home.common;

import io.wangxiao.edu.home.entity.common.Dialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DialogController extends EduBaseController {

    private static final Logger logger = LoggerFactory.getLogger(DialogController.class);

    private static final String getDialogHtml = "/common/dialog";// 弹出窗页面
    private static final String getSNSDialogHtml = "/common/sns_dialog";// 弹出窗页面

    // 绑定变量参数
    @InitBinder("dialog")
    public void initBinderDialog(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dialog.");
    }

    /**
     * 获取弹出窗页面
     *
     * @param request
     * @param model
     * @param dialog
     * @return
     */
    @RequestMapping("/common/dialog")
    public String getDialog(HttpServletRequest request, Model model, @ModelAttribute Dialog dialog) {
        try {
            model.addAttribute("dialog", dialog);
        } catch (Exception e) {
            logger.error("getDialog", e);
            return setExceptionRequest(request, e);
        }
        return getDialogHtml;
    }

    /**
     * 获取弹出窗页面
     *
     * @param request
     * @param model
     * @param dialog
     * @return
     */
    @RequestMapping("/common/snsdialog")
    public String getSNSDialog(HttpServletRequest request, Model model, @ModelAttribute Dialog dialog) {
        try {
            model.addAttribute("dialog", dialog);
        } catch (Exception e) {
            logger.error("getSNSDialog", e);
            return setExceptionRequest(request, e);
        }
        return getSNSDialogHtml;
    }
}
