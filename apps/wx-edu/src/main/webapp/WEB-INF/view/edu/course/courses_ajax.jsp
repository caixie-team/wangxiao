<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${ empty courseList}">
	<section class="no-data-wrap"> <em class="no-data-ico">&nbsp;</em> <span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span> </section>
</c:if>
<c:forEach items="${courseList}" var="subjectCourse">
	<div class="col-lg-3 col-md-6">
		<div class="widget widget-shadow">
			<div class="widget-header cover overlay" style="height: calc(100% - 100px);">
				<a class="course-img" href="${ctx}/front/couinfo/${subjectCourse.id }">
					<c:choose>
						<c:when test="${ not empty subjectCourse.logo}">
							<%--<img src="<%=staticImageServer%>/${subjectCourse.logo}"--%>
							<%--xSrc="" class="img-responsive">--%>
							<img class="cover-image"
								 src="${ctx}/static/global/photos/placeholder.png" alt="..."
								 style="height: 100%;">
						</c:when>
						<c:otherwise>
							<%--<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}"--%>
							<%--xSrc="" class="img-responsive">--%>
							<img class="cover-image"
								 src="${ctx}/static/global/photos/placeholder.png" alt="..."
								 style="height: 100%;">
						</c:otherwise>
					</c:choose>
				</a>
					<%--<img class="cover-image" src="${ctx}/static/global/photos/placeholder.png" alt="..."--%>
					<%--style="height: 100%;">--%>
				<div class="overlay-panel overlay-background overlay-top">
					<div class="row">
						<div class="col-xs-6">

							<div class="font-size-14 white">
								<a title="${subjectCourse.name}"
								   href="${ctx}/front/couinfo/${subjectCourse.id }">${subjectCourse.name}</a>
							</div>
							<div class="font-size-14 grey-400">
								课时:${subjectCourse.lessionnum}</div>
						</div>
						<div class="col-xs-6 text-right">
							<div class="font-size-14 white">
								<c:forEach items="${subjectCourse.teacherList}"
										   var="teacher" varStatus="teaindex">
									<c:if test="${teaindex.index==0 }">
										<a class="c-666 mr10"
										   href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
									</c:if>
									<c:if test="${teaindex.index>0 }">
										<a class="c-666"
										   href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="widget-footer text-center bg-white padding-horizontal-30 padding-vertical-20 height-100">
				<div class="row no-space">
					<div class="col-xs-4">
						<div class="counter">
							<span class="counter-number">${subjectCourse.playcount}</span>
							<div class="counter-label">播放量</div>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="counter">
							<span class="counter-number">${subjectCourse.commentcount}</span>
							<div class="counter-label">评论数</div>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="counter">
							<span class="counter-number">${subjectCourse.currentprice}</span>
							<div class="counter-label">课单价</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%--<li>--%>
		<%--<div class="cc-l-wrap">--%>
			<%--<a class="course-img" href="${ctx}/front/couinfo/${subjectCourse.id }">--%>
				<%--<c:choose>--%>
					<%--<c:when test="${ not empty subjectCourse.logo}">--%>
						<%--<img src="<%=staticImageServer%>/${subjectCourse.logo}" xSrc="" class="img-responsive">--%>
					<%--</c:when>--%>
					<%--<c:otherwise>--%>
						<%--<img src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xSrc=""  class="img-responsive">--%>
					<%--</c:otherwise>--%>
				<%--</c:choose>--%>
			<%--</a>--%>
			<%--<div class="j-c-desc-wrap">--%>
				<%--<h3 class="hLh30 txtOf pt10">--%>
					<%--<a class="j-course-title" title="${subjectCourse.name}" href="${ctx}/front/couinfo/${subjectCourse.id }">${subjectCourse.name}</a>--%>
				<%--</h3>--%>
				<%--<div class="clearfix of mt15 cj-cou-ds">--%>
					<%--<span class="fl fsize18 c-999 f-fM txtOf" title="<c:forEach items="${subjectCourse.teacherList}" var="teacher">${teacher.name}&nbsp;&nbsp;</c:forEach>">讲师：<span class="c-666">--%>
						<%--<c:forEach items="${subjectCourse.teacherList}" var="teacher">--%>
							<%--<span>${teacher.name}&nbsp;&nbsp;</span>--%>
						<%--</c:forEach>--%>
					<%--</span></span>--%>
					<%--<span class="fr fsize18 c-999 f-fM ">课时：<span class="c-666">${subjectCourse.lessionnum}</span></span>--%>
				<%--</div>--%>
				<%--<dl class="cj-cou-desc of">--%>
					<%--<dd>--%>
						<%--<div class="c-c-sbox txtOf" title="播放量：${subjectCourse.playcount}">--%>
							<%--<em class="icon12 c-play-num"></em>--%>
							<%--<tt class="fsize14">${subjectCourse.playcount}</tt>--%>
						<%--</div>--%>
					<%--</dd>--%>
					<%--<dd>--%>
						<%--<div class="c-c-sbox txtOf" title="评论数：${subjectCourse.commentcount}">--%>
							<%--<em class="icon12 c-review-num"></em>--%>
							<%--<tt class="fsize14">${subjectCourse.commentcount}</tt>--%>
						<%--</div>--%>
					<%--</dd>--%>
					<%--<dd>--%>
						<%--<div style="border-right:none;" class="c-c-sbox txtOf" title="价格：${subjectCourse.currentprice}元">--%>
							<%--<em class="icon12 c-couse-v"></em>--%>
							<%--<tt class="fsize14  vam">${subjectCourse.currentprice}</tt>--%>
						<%--</div>--%>
					<%--</dd>--%>
				<%--</dl>--%>
			<%--</div>--%>
		<%--</div>--%>
	<%--</li>--%>
</c:forEach>
