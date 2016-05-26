<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>课程</title>
</head>
<body>
	
					<div class="i-box">
						<section class="c-sort-title">
							<div class="c-sort-t-fixed">
								<aside class="fr c-sort-title-r" style="margin: 0;">
									<span class="cs-txt">
										<em class="cst-close">×</em>
									</span>
								</aside>
								<aside class="fl">
									<span class="cs-txt" style="margin: 2px 0 -3px;">
										<svg width="18px" height="18px" viewBox="0 0 1024 1024" enable-background="new 0 0 18 18" xml:space="preserve">
										  <path fill="#0A59C9" d="M70.485843 621.890174l883.027291 0 0-20.466124-883.027291 0 0 20.466124ZM70.485843 310.233062l571.369156 0 0-20.466124-571.369156 0 0 20.466124ZM70.485843-1.423027l311.656089 0 0-20.466124-311.656089 0 0 20.466124Z" transform="translate(0, 812) scale(1, -1)"/>
										</svg>
									</span>
									<span class="cs-txt">课程分类</span>
								</aside>
								<div class="clear"></div>
							</div>
						</section>
						<section class="tjc-box c-sort-box">
							<div class="c-sort">
								<section class="c-sort-a">
									<dl class="clearfix">
										<dt><span>按专业：</span></dt>
										<dd>
											<a href="javascript:void(0)"  onclick="clickSearch('subject',0)"  <c:if test="${queryCourse.subjectId==null||queryCourse.subjectId==0 }">class="current"</c:if>>全部</a>
											<c:forEach items="${subjectList }" var="subject">
												<a href="javascript:void(0)" onclick="clickSearch('subject',${subject.subjectId })"  <c:if test="${queryCourse.subjectId==subject.subjectId }">class="current"</c:if>>${subject.subjectName }</a>
											</c:forEach>
										</dd>
									</dl>
									<dl class="clearfix">
										<dt><span>按讲师：</span></dt>
										<dd>
											<a href="javascript:void(0)"  onclick="clickSearch('teacher',0)"  <c:if test="${queryCourse.teacherId==null||queryCourse.teacherId==0 }">class="current"</c:if>>全部</a>
											<c:forEach items="${teacherList }" var="teacher">
												<a href="javascript:void(0)" onclick="clickSearch('teacher',${teacher.id })"  <c:if test="${queryCourse.teacherId==teacher.id }">class="current"</c:if>>${teacher.name }</a>
											</c:forEach>
										</dd>
									</dl>
									<dl class="clearfix">
										<dt><span>按价格：</span></dt>
										<dd>
											<a href="javascript:void(0)" onclick="clickSearch('isPay',-1)"  <c:if test="${queryCourse.isPay==-1 }">class="current"</c:if>>全部</a>
											<a href="javascript:void(0)" onclick="clickSearch('isPay',0)"  <c:if test="${queryCourse.isPay==0 }">class="current"</c:if>>免费课程</a>
											<a href="javascript:void(0)" onclick="clickSearch('isPay',1)"  <c:if test="${queryCourse.isPay==1 }">class="current"</c:if>>收费课程</a>
										</dd>
									</dl>
								</section>
							</div>
						</section>
					</div>
				
</body>
</html>