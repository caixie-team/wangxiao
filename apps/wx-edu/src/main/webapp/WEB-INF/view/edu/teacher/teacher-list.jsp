<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>教师</title>
</head>
<body>
<div class="bg-fa of" id="aCoursesList">
	<!-- /课程列表 开始 -->
	<section class="container">
		<header class="comm-title">
			<h2 class="fl tac"><span class="c-333">全部讲师</span></h2>
		</header>
		<section class="c-sort-box">
			<form id="searchForm" name="searchForm" action="${ctx}/front/teacherlist" method="post">
				<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="1" />
			</form>
			<section class="mb20">
				<ul class="teacher-list-wrap clearfix">
					<c:if test="${not empty teacherList }">
					<c:forEach items="${teacherList}" var="teacher">
					<li>
						<article class="fl w50pre1">
							<section class="pr10"> 
								<a title="${teacher.name}" href="${ctx}/front/teacher/${teacher.id}"> 
									<c:if test="${teacher.picPath!=''}">
										<img alt="" class="teacher-img" src="${ctximg}/static/nxb/web/img/default/head-default.gif" xsrc="<%=staticImageServer%>${teacher.picPath}">
									</c:if> 
									<c:if test="${teacher.picPath==''}">
										<img alt="" class="teacher-img" src="${ctximg}/static/nxb/web/img/default/head-default.gif" xsrc="<%=imagesPath%>/static/common/images/user_default.png">
									</c:if>
								</a> 
								<div class="t-name">
									<span class="vam"> 
										<font class="fsize18 c-333 f-fM"><a href="${ctx}/front/teacher/${teacher.id}" class="c-master">${teacher.name}</a></font>
									</span> 
								</div> 
								<div class="mt40 hLh20 of">
									<span class="vam txtOf tea-desc">
										<font class="fsize14 c-666">${teacher.education}</font>
									</span> 
								</div> 
								<%--<div class="mt10 teacher-desc-txt of"> --%>
									<%--<p class="c-999">${teacher.career}</p> --%>
								<%--</div> --%>
							</section>
						</article>
						<article class="fl w50pre2 mt15"> 
							<div class="pl10 teacher-courses-tj clearfix"> 
								<c:if test="${teacher.courseList[0].id!=0}">
								<c:forEach var="course" items="${teacher.courseList}" end="1"> 
								<section class="t-c-tj"> 
									<div class="ml15 pr t-c-box">
										<a href="${ctx}/front/couinfo/${course.id}" title="${course.name }"> 
											<c:if test="${course.logo!=''}">
												<img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${course.logo}" alt="">
											</c:if>
											<c:if test="${course.logo==''}">
												<img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${courseimagemap.courseimage.url}" alt="">
											</c:if>
										</a> 
										<section class="tea-cou-name "> 
											<div class="pl10 pr10 txtOf">
												<a class="fsize14 c-fff" title="" target="_blank" href="${ctx}/front/couinfo/${course.id}">${course.name }</a> 
											</div>
										</section> 
									</div>
								</section>
								</c:forEach>
								</c:if>
								<c:if test="${teacher.courseList[0]==null}"> 
								<section class="mt30 mb30 tac">
									<em class="no-data-ico cTipsIco">&nbsp;</em>
									<span class="c-666 fsize14 ml10 vam">该教师暂无授课~</span>
								</section><!-- /无数据提示 -->
								</c:if>
							</div>  
						</article>
						<div class="clear"></div>
					</li>
					</c:forEach>
					</c:if>
				</ul>
				<!-- 公共分页 开始 -->
				<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
				<!-- 公共分页 结束 -->
			</section>
		</section>
	</section>
</div>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course.js"></script>
<script type="text/javascript">
	var teacherName = '${queryTeacherCondition.teacherName}';
	var subjectId = '${queryTeacherCondition.subjectId}';
	$(function() {
		scrollLoad();
		effect();//讲师列表的课程名显示
	});
</script>
</body>
</html>
