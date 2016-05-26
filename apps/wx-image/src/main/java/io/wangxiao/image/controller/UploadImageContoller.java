package io.wangxiao.image.controller;

import com.google.gson.*;
import io.wangxiao.commons.util.PropertyUtil;
import io.wangxiao.image.ueditor.ActionEnter;
import io.wangxiao.image.util.DocConverter;
import io.wangxiao.image.util.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上传图片用
 */
@Controller
public class UploadImageContoller {

    public static int CUS_PHOTO_WIDTH = 150;
    public static int CUS_PHOTO_HEIGHT = 150;

    // 读取配置文件类
    public static PropertyUtil propertyUtil = PropertyUtil.getInstance("project");

    //尾不带/
    public static String import_root = FileUtil.formatUrlEnd(propertyUtil.getProperty("import.root"));

    private static Logger logger = Logger.getLogger(UploadImageContoller.class);

    public static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    public static JsonParser jsonParser = new JsonParser();

    /**
     * kindeditor4.x编辑器中上传图片 返回ke中需要的url和error值 注意：同域中本方法直接返回json格式字符即可
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/imgk4", method = RequestMethod.POST)
    public String img(HttpServletRequest request, HttpServletResponse response) {
        try {
            String referer = request.getHeader("referer");
            Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
            Matcher mathcer = p.matcher(referer);
            logger.info("imgk4 referer:" + referer);
            if (mathcer.find()) {
                String callBackPath = mathcer.group();// 请求来源
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile imgFile = multipartRequest.getFile("imgFile");
                String[] paths = FileUtil.getSavePathByRequest(request);

                JsonObject json = FileUtil.saveImage(imgFile, paths);


                // 编辑器中需要返回完整的路径
                json.addProperty("url", import_root + json.get("url").getAsString());
                // 同域时直接返回json即可无需redirect
                String url = "redirect:" + callBackPath + "/kindeditor/plugins/image/redirect.html?s=" + json.toString() + "#" + json.toString();
                logger.info("imgk4 ok");
                return url;
            } else {
                logger.info("imgk4 referer not find");
            }
        } catch (Exception e) {
            logger.error("imgk4 error", e);
        }
        return null;
    }

    /**
     * kindeditor4 单个按钮上传图片
     * kindeditor3.5中使用call_back.html 单个图片按钮时 上传方法集合，根据参数匹配属性文件 模块提供,返回的是图片的全路径
     * base:项目 param：模块 cusid.用户id
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/gok4", method = RequestMethod.POST)
    public String go(HttpServletRequest request, HttpServletResponse response) {
        try {
            String referer = request.getHeader("referer");
            Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
            Matcher mathcer = p.matcher(referer);
            if (mathcer.find()) {
                String callBackPath = mathcer.group();// 请求来源
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile imgFile = multipartRequest.getFile("fileupload");
                String[] paths = FileUtil.getSavePathByRequest(request);
                JsonObject json = FileUtil.saveImage(imgFile, paths);
                // 同域时直接返回json即可无需redirect
                String url = "redirect:" + callBackPath + "/kindeditor/plugins/image/redirect.html?s=" + json.toString() + "#" + json.toString();
                logger.info("++++gok4 return:" + url);
                return url;
            }
        } catch (Exception e) {
            logger.error("gok4 error", e);
        }
        return null;
    }

    /**
     * kindeditor3.5.x编辑器中上传图片,返回ke中需要的url和error值 注意：同域中本方法直接返回json格式字符即可
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/imgk3")
    @ResponseBody
    public String imgk3(HttpServletRequest request, HttpServletResponse response) {
        try {

            String referer = request.getHeader("referer");
            Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
            Matcher mathcer = p.matcher(referer);
            if (mathcer.find()) {
                StringBuffer buffer = new StringBuffer();
                String callBackPath = mathcer.group();// 请求来源
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile imgFile = multipartRequest.getFile("imgFile");
                String[] paths = FileUtil.getSavePathByRequest(request);
                JsonObject json = FileUtil.saveImage(imgFile, paths);
                // 编辑器中需要返回完整的路径
                json.addProperty("url", import_root + json.get("url").getAsString());
                if (!("0").equals(json.get("error").toString())) {
                    buffer.append("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=utf-8'/></head><body>");
                    buffer.append("<script type='text/javascript'>");
                    buffer.append("alert('").append(json.get("message")).append("!')");
                    buffer.append("</script>");
                    buffer.append("</body></html>");
                } else {
                    // 同域时直接返回json即可无需call_back
                    buffer.append("<html><head>\n" + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" + "<title>PIC UPLOAD</title>\n"
                            + "<script type=\"text/javascript\">\n" + "    var upload_callback = function(){\n" + "        var iframe_proxy = document.createElement('iframe');\n"
                            + "        iframe_proxy.style.display = 'none';\n" + "        iframe_proxy.src = '" + callBackPath + "/kindeditor/plugins/image/call_back.html#'+encodeURIComponent('"
                            + json + "');\n" + "        document.body.appendChild(iframe_proxy);\n" + "    };\n" + "</script>\n" + "</head>\n" + "<body onload=\"upload_callback();\">\n" + "\n"
                            + "</body></html>");
                }
                return buffer.toString();
            }
            logger.info("imgk3 ok");
        } catch (Exception e) {
            logger.error("imgk3 error", e);
        }
        return null;
    }

    /**
     * 点击头像上传face()。上传到临时目录，可以定期清除此目录
     * <p>
     * 点击保存时saveface()，读取临时目录下的文件，按照页面传来的坐标截取图片
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    @ResponseBody
    public String face(HttpServletRequest request, HttpServletResponse response) {
        try {
            String referer = request.getHeader("referer");
            referer = request.getHeader("Referer");
            Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
            Matcher mathcer = p.matcher(referer);

            if (mathcer.find()) {
                String callBackPath = mathcer.group();// 请求来源
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                MultipartFile imgFile = multipartRequest.getFile("fileupload");
                String[] paths = FileUtil.getTempSavePathByRequest(request);
                JsonObject json = FileUtil.saveImage(imgFile, paths);
                // 同域时直接返回json即可无需redirect
                String url = "redirect:" + callBackPath + "/kindeditor/plugins/image/redirect.html?s=" + json.get("url").getAsString() + "#" + json.get("url").getAsString();
                logger.info("++++upload img return:" + url);
                return json.get("url").getAsString();
            }
        } catch (Exception e) {
            logger.error("face error", e);
        }
        return null;
    }

    /**
     * 保存头像，读取临时目录下的文件，按照页面传来的坐标截取图片
     *
     * @param request
     * @param response
     */

    @RequestMapping(value = "/saveface")
    @ResponseBody
    public String saveface(HttpServletRequest request, HttpServletResponse response) {
        try {

            String referer = request.getHeader("referer");
            System.out.println("referer:" + referer);
            Pattern p = Pattern.compile("([a-z]*:(//[^/?#]+)?)?", Pattern.CASE_INSENSITIVE);
            Matcher mathcer = p.matcher(referer);

            if (mathcer.find()) {
                String photoPath = request.getParameter("photoPath");
                int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
                int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
                int cutTop = Integer.parseInt(request.getParameter("txt_top"));
                int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
                // 保存图片的大小
                int dropWidth = 0;
                int dropHeight = 0;
                if (StringUtils.isNotEmpty(request.getParameter("txt_DropWidth")) && StringUtils.isNotEmpty(request.getParameter("txt_DropHeight"))) {

                    dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
                    dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
                } else {
                    // 未传参数时默认150
                    dropWidth = CUS_PHOTO_WIDTH;
                    dropHeight = CUS_PHOTO_HEIGHT;
                }
                JsonObject json = FileUtil.saveCutImage(photoPath, imageWidth, imageHeight, cutLeft, cutTop, dropWidth, dropHeight);

                json.addProperty("src", json.get("url").getAsString());
                json.addProperty("status", "1");
                String callname = request.getParameter("callback");
                logger.info(callname + "(" + json.toString() + ")");
                return callname + "(" + json.toString() + ")";

            }
        } catch (Exception e) {
            logger.error("saveface error", e);
        }
        return null;

    }

    /**
     * swf使用上传文件
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/goswf")
    @ResponseBody
    public String goswf(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("++++goswf");
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile imgFile = multipartRequest.getFile("fileupload");
            String[] paths = FileUtil.getSavePathByRequest(request);
            JsonObject json = FileUtil.saveImage(imgFile, paths);
            logger.info("++++upload img return:" + json.get("url").getAsString());
            return json.get("url").getAsString();
        } catch (Exception e) {
            logger.error("goswf error", e);
        }
        return "error";
    }

    /**
     * 删除文件
     *
     * @param files 删除的文件数组["/a/aa.jpg","a/aa01.jpg","/a/aa01.jpg"]
     * @return
     */
    @RequestMapping("/del")
    public Map<String, Object> del(HttpServletResponse response, @RequestParam(value = "files") String files) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            JsonArray jsonObject = jsonParser.parse(files).getAsJsonArray();
            logger.info("del file:" + jsonObject.toString());
            for (int i = 0; i < jsonObject.size(); i++) {
                map.put(i + "", FileUtil.deleteImageFile(jsonObject.get(i).toString()));
            }
            try {
                response.getWriter().write(map.toString());
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return map;
        } catch (Exception e) {
            logger.error("del error", e);
        }
        return null;
    }

    /**
     * 存储网络图片到服务器
     *
     * @param url
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/netfile")
    @ResponseBody
    public String savenetfile(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] paths = FileUtil.getSavePathByRequest(request);
            JsonObject json = FileUtil.saveFile(url, paths[0]);
            logger.info("++++netfile img return:" + json.get("url").getAsString());
            return json.get("url").getAsString();
        } catch (Exception e) {
            logger.error("savenetfile error", e);
        }
        return null;
    }

    /**
     * swf使用上传文件
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/file")
    @ResponseBody
    public String file(HttpServletRequest request, HttpServletResponse response) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile imgFile = multipartRequest.getFile("fileupload");
            String[] paths = FileUtil.getSavePathByRequest(request);
            JsonObject json = FileUtil.saveCommonFile(imgFile, paths);
            logger.info("++++upload img return:" + json.get("url").getAsString());
            return json.get("url").getAsString();
        } catch (Exception e) {
            logger.error("goswf error", e);
        }
        return null;
    }


    /**
     * pdf并转换为swf
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/pdf2swf")
    @ResponseBody
    public String pdf2swf(HttpServletRequest request, HttpServletResponse response) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile imgFile = multipartRequest.getFile("fileupload");
            String[] paths = FileUtil.getSavePathByRequest(request);
            JsonObject json = FileUtil.saveCommonFile(imgFile, paths);
            String pdffilename = FileUtil.rootpath + json.get("url").getAsString();
            pdffilename = pdffilename.replaceAll("//", "/");
            logger.info("pdffilename:" + pdffilename);
            String swfFileName = pdffilename.substring(0, pdffilename.lastIndexOf(".")) + ".swf";
            String res = DocConverter.pdf2swf(pdffilename, swfFileName);
            if (!"success".equalsIgnoreCase(res)) {
                logger.info("res:" + res);
                return res;
            }
            return swfFileName.replace(FileUtil.rootpath, "/");
        } catch (Exception e) {
            logger.error("pdf2swf error", e);
        }
        return null;
    }

    /**
     * ue
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/ue")
    public Object ueditor(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            String rootPath = request.getSession().getServletContext().getRealPath("/");
            String res = new ActionEnter(request, rootPath).exec();
            Map map = new HashMap();
            map.put("s", res);
            logger.info("+++res:" + res);
            String callBackPath = request.getParameter("fromdomain");// 请求来源
            return new ModelAndView(new RedirectView(callBackPath + "/kindeditor/plugins/image/redirect.jsp"), map);

        } catch (Exception e) {
            logger.error("ue error", e);
        }

        return null;
    }

}