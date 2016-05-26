<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>新闻资讯</title>
</head>
<body>
	
				<c:forEach items="${articleList}" var="article">
				<li>
					<a href="/mobile/article/${article.id }" >
						<img xsrc="<%=staticImageServer %>${article.picture}" src="${ctximg }/static/mobile/img/sprite.gif" width="100" height="50" alt="">
						<p>${article.title}</p>
					</a>
				</li>
				</c:forEach>
								
</body>
</html>