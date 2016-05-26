<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	
								<c:forEach items="${studylist}" var="study" varStatus="index">
								<li value="${study.id}">
									<a href="/mobile/course/info/${study.courseId}" title="">
										<section class="c-l-pic">
											<em>&nbsp;</em>
											<img xsrc="<%=staticImageServer %>/${study.logo }" src="${ctx}/static/mobile/img/sprite.gif" alt="">
										</section>
										<h2 class="courese-l-title">
											${study.kpointName }
										</h2>
										<section class="c-l-attr">
											<span>
												<tt>${study.courseName}</tt>
											</span>
										</section>
										<section class="c-l-attr">
											<span>
												<tt><fmt:formatDate value="${study.updateTime}" pattern="yyyy-MM-dd HH:mm" /></tt>
											</span>
										</section>
									</a>
								</li>
								</c:forEach>
							
</body>
</html>