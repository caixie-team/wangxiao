<%@ page import="io.wangxiao.image.ueditor.ActionEnter" %>
<%@ page import="io.wangxiao.commons.util.PropertyUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html");
    response.addHeader("Access-Control-Allow-Origin", "*");
    //response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");

    PropertyUtil propertyUtil = PropertyUtil.getInstance("project");
    String rootPath = propertyUtil.getProperty("file.root");

    //System.out.println("++++++rootPath:"+rootPath);
    String diskPath = application.getRealPath("/");
    //System.out.println("++++++diskPath:"+diskPath);
    String result = new ActionEnter(request, rootPath, diskPath).exec();
    boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
    if (isAjaxUpload) {
        out.write(result);
    } else {
        String fromdomain = request.getParameter("fromdomain");
        request.setAttribute("s", result);
        //response.sendRedirect(fromdomain+"/static/common/ueditor/redirect.jsp?s="+result);
        response.sendRedirect(fromdomain + "/redirect.jsp?s=" + result);
    }

%>
