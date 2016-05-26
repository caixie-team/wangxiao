<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的收藏</title>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_home.js"></script>
<script type="text/javascript">
	$(function() {
		//stepFun();//新手引导四步骤方法
	});
	selectRondomTeacher();
</script>
</head>
<body>
	<article class="u-m-center">
		<!-- /u-m-c-head -->
		<section class="u-m-c-wrap">
			<section class="u-m-c-head">
				<span class="fr"><em class="right-go icon-2-16">&nbsp;</em><a href="${ctx}/front/showcoulist" title="" class="vam c-666 ml5">去选课</a></span>
				<ul class="fl u-m-c-h-txt">
					<li class="current"><a href="" title="" class="vam">我收藏的课程</a> <tt class="fsize12 vam" id="collectcount"></tt></li>
				</ul>
				<div class="clear"></div>
			</section>
			<section class="line1">
				<c:if test="${courseList==null}">
					<section class="comm-tips-1">
						<p>
							<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">对不起，你还没收藏课程！建议你<a href="${ctx}/front/showcoulist" title="" class="c-orange">去选课</a></font>
						</p>
					</section>
				</c:if>
				<c:if test="${courseList.size()>0}">
				<div class="u-m-sub-head" id="um">
					<input type="checkbox" onclick="allselect(this)" /> <a href="javascript:void(0)" onclick="alldelete();" title="取消收藏" class="u-common-btn ml5">取消收藏</a>
				</div>
				<div class="pl10 pr10">
				<form action="${ctx }/uc/fav" id="searchForm" method="post">
					<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage" />
				</form>
					<form id="deleteAllFavorite" action="${ctx}/uc/del" method="post">
						<ul class="u-collect-list" id="licollect">
							<c:forEach var="list" items="${courseList}">
								<li>
									<section class="fl u-c-img pr">
										<input type="checkbox" name="sellIdArr" value="${list.favouriteId}"> <a href="${ctx}/front/couinfo/${list.courseId}" title="${list.name}"> <c:if test="${list.logo!=''}">
												<img src="<%=staticImageServer %>/${list.logo}" width="220" alt="" class="dis" />
											</c:if> <c:if test="${list.logo==''}">
												<img alt="我的课程" class="dis" src="${ctximg}/static/edu/images/default/default_goods.jpg" />
											</c:if>
										</a>
									</section>
									<h4 class="hLh20 of unFw">
										<a class="c-666 fsize16 f-fM" href="${ctx}/front/couinfo/${list.courseId}" title="${list.name}">${list.name}</a>
									</h4>
									<div class="u-c-list-desc mt10">
										<p class="c-999">${list.title }</p>
									</div>
									<div class="hLh20 of">
										<p class="c-999">
											课程有效期：
											<fmt:formatDate value="${list.loseAbsTime}" pattern="yyyy-MM-dd" />
										</p>
									</div>
									<div class="hLh20 of mt10">
										<span class="fr"><a class="c-999" title="取消收藏" href="${ctx}/uc/del?sellIdArr=${list.favouriteId}">取消收藏</a></span> <span class="vam"><a href="${ctx}/front/couinfo/${list.courseId}" title="" class="c-orange ml10">查看详情</a></span>
									</div>
								</li>
							</c:forEach>
						</ul>
					</form>
				</div>
				</c:if>
				<section class="mt5 mb5 tac">
					<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
				</section>
			</section>
		</section>
		<!-- /u-m-c-head -->

		<!-- 推荐开始 -->
		<section class="u-m-c-head">
			<ul class="fl u-m-c-h-txt">
				<li class="current"><a href="javascript:void(0)" title="" class="vam">为您推荐</a></li>
			</ul>
			<div class="clear"></div>
		</section>
		<section class="u-tj-cour">
			<ol class="clearfix">
				<c:forEach items="${mapCourseList.index_course_6}" var="tjCourse">
					<li>
						<div class="u-tj-cour-img">
							<a href="${ctx}/front/couinfo/${tjCourse.id}" title="${tjCourse.name }"> <c:choose>
									<c:when test="${tjCourse.logo!='not'}">
										<img src="<%=staticImageServer %>${tjCourse.logo}" width="150" height="113" class="dis" />
									</c:when>
									<c:otherwise>
										<img src="${ctximg}/static/edu/images/default/default_goods.jpg" width="150" height="113" class="dis" />
									</c:otherwise>
								</c:choose>
							</a>
							<div class="pa u-tjc-name">
								<a class="fsize14 c-fff" title="${tjCourse.name }" href="${ctx}/front/couinfo/${tjCourse.id}">${tjCourse.name }</a>
							</div>
						</div>
						<div class="hLh20 of mt5">
							<span class="c-999 fr"><tt class="vam">浏览量：</tt><b title="浏览量:${tjCourse.viewcount}" class="vam">${tjCourse.viewcount}</b></span> <span class="fl"><a class="c-brow" title="" href="${ctx}/front/couinfo/${tjCourse.id}">查看课程</a></span>
						</div>
					</li>
				</c:forEach>
			</ol>
		</section>
		<!-- 推荐结束 -->
	</article>
	<aside class="u-m-right">
		<div class="mt10">
			<section class="pl15 pr15">
				<h5 class="hLh30 unFw of line2">
					<span class="c-666 fsize16 f-fM u-c-title">收藏最多的</span>
				</h5>
				<div class="line1">
					<ul class="u-c-list-1 mt20">
						<c:forEach var="course" items="${courseMoreList}">
							<li><a href="${ctx}/front/couinfo/${course.courseId}" target="_blank" class="dis"> <c:if test="${course.logo!=''}">
										<img src="<%=staticImageServer %>/${course.logo}" width="220" alt="" class="dis" />
									</c:if> <c:if test="${course.logo==''}">
										<img src="${ctximg}/static/edu/images/default/default_goods.jpg" width="220" alt="" class="dis" />
									</c:if>
							</a>
								<h6 class="unFw hLh20 of">
									<a href="${ctx}/front/couinfo/${course.courseId}" target="_blank" title="${course.name}" class="c-4e">${course.name}</a>
								</h6>
								<div class="mt5 u-c-desc-1">
									<p class="c-999">${course.title}</p>
								</div></li>
						</c:forEach>
					</ul>
				</div>
			</section>
		</div>
		<div class="mt30 mb30">
			<section class="pl15 pr15">
				<h5 class="hLh30 unFw of line2">
					<a href="javascript:void(0)" onclick="selectRondomTeacher('randomteacher');" class="fr fresh icon-2-18 mt5" title="换一换"></a> <span class="c-666 fsize16 f-fM u-c-title">名师推荐</span>
				</h5>
				<div class="line1 pt15">
					<ol id="randomteacher" class="clearfix u-teacher-list">
					</ol>
				</div>
			</section>
		</div>
	</aside>
</body>
</html>
