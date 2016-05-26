<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程</title>
</head>
<body>
	
					<c:forEach items="${courseList }" var="course">
					<li>
						<a href="/mobile/course/info/${course.id}" title="">
							<section class="c-l-pic">
								<img xSrc="<%=staticImageServer%>${course.mobileLogo}" src="/static/mobile/img/sprite.gif" alt="">
							</section>
							<h2 class="courese-l-title">
								${course.name }
							</h2>
							<section class="c-l-attr">
								<span>
									<em class="p-num-ico">&nbsp;</em>
									<tt>${course.viewcount}</tt>
								</span>
								<span>
									<em class="clock-num-ico">&nbsp;</em>
									<tt>${course.lessionnum }时</tt>
								</span>
							</section>
							<section class="c-price">
								<span class="cs-txt">价格：</span>
								<span>￥${course.currentprice}</span>
							</section>
						</a>
					</li>
					</c:forEach>
									
								
</body>
</html>