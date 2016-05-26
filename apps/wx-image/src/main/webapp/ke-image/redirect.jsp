<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%
    request.setCharacterEncoding("utf-8");
    response.setHeader("Content-Type", "text/html");
    response.addHeader("Access-Control-Allow-Origin", "*");
    //response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");
    String s = request.getParameter("s");
    s = java.net.URLDecoder.decode(s, "UTF-8");
    out.write(s);
%>