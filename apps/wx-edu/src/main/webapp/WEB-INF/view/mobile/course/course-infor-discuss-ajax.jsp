<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程详情</title>
	
</head>
<body>
	

					
									<c:forEach items="${courseAssessList }" var="assess">
									<c:if test="${assess.userId!=userId }">
									<!-- /左侧对话气泡开始 -->
									<article class="news-art news-art-l">
										<aside class="oIcon-b">
											<span class="sm-u-head">
												<c:if test="${assess.avatar!=null&& assess.avatar!=''}">
													<img width="50" height="50" src="<%=staticImageServer%>${assess.avatar }">
												</c:if>
												<c:if test="${assess.avatar==null|| assess.avatar==''}">
													<img width="50" height="50" src="/static/mobile/img/avatar-boy.gif">
												</c:if>
											</span>
											<p class="sm-u-name">
												<c:if test="${assess.nickname==null||assess.nickname==''}">
													${assess.email }
												</c:if>
												<c:if test="${assess.nickname!=null&&assess.nickname!=''}">
													${assess.nickname }
												</c:if>
											</p>
										</aside>
										<section class="news-art-txt-wrap">
											<div class="n-l-triangle-css3 n-l-transform"></div>
											<section class="n-a-t-w-txt">
												${assess.content }
											</section>
										</section>
									</article>
									<!-- /左侧对话气泡结束 -->
									</c:if>
									<c:if test="${assess.userId==userId }">
									<!-- /右侧对话气泡开始 -->
									<article class="news-art news-art-r">
										<aside class="oIcon-b">
											<span class="sm-u-head">
												<c:if test="${assess.avatar!=null&& assess.avatar!=''}">
													<img width="50" height="50" src="<%=staticImageServer%>${assess.avatar }">
												</c:if>
												<c:if test="${assess.avatar==null||assess.avatar==''}">
													<img width="50" height="50" src="/static/mobile/img/avatar-boy.gif">
												</c:if>
											</span>
											<p class="sm-u-name">
												<c:if test="${assess.nickname==null||assess.nickname==''}">
													${assess.email }
												</c:if>
												<c:if test="${assess.nickname!=null&&assess.nickname!=''}">
													${assess.nickname }
												</c:if>
											</p>
										</aside>
										<section class="news-art-txt-wrap">
											<div class="n-r-triangle-css3 n-r-transform"></div>
											<section class="n-a-t-w-txt">
												${assess.content }
											</section>
										</section>
									</article>
									<!-- /右侧对话气泡结束 -->
									</c:if>
									</c:forEach>
								
</body>
</html>