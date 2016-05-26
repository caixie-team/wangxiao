<%@ page import="io.wangxiao.image.ueditor.ActionEnter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

	request.setCharacterEncoding("utf-8");
	response.setHeader("Content-Type", "text/html");
	response.addHeader("Access-Control-Allow-Origin", "*");
	//response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,X_Requested_With");

	String rootPath = application.getRealPath( "/" );
	String result= new ActionEnter( request, rootPath ).exec();
	boolean isAjaxUpload= request.getHeader( "X_Requested_With" ) != null;
	if(isAjaxUpload){
		out.write(result);
	}else {
		String fromdomain= request.getParameter("fromdomain");
		request.setAttribute("s",result);
		//response.sendRedirect(fromdomain+"/kindeditor/plugins/image/redirect.jsp?s="+result);
		response.sendRedirect(fromdomain+"/static/common/ueditor/redirect.jsp?s="+result);
	}

%>