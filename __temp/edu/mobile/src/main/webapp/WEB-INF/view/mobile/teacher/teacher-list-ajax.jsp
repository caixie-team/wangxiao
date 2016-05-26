<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>名家讲师</title>
	
</head>
<body>
	
											<c:forEach items="${teacherList }" var="teacher">
											<li>
												<div class="oIconDesc" onclick="window.location.href='/mobile/teacher/${teacher.id}'">
													<aside class="oIcon">
														<span class="sm-u-head"><img xsrc="<%=staticImageServer %>${teacher.picPath }" src="/static/mobile/img/sprite.gif" width="55" height="41"></span>
													</aside>
													<h2 class="oIconName">${teacher.name }</h2>
													<section class="oIconTxt">${teacher.education } </section>
												</div>
											</li>
											</c:forEach>
										
</body>
</html>