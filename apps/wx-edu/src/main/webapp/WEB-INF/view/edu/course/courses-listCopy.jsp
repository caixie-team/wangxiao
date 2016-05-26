<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程中心</title>
</head>
<body>
<form id="searchForm" action="${ctx}/front/showCourseList" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
	<input type="hidden" id="hiddenMemberId" name="queryCourse.membertype" value="${queryCourse.membertype}"/>
	<input type="hidden" id="hiddenSubjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
	<input type="hidden" id="hiddenTeacherIds" name="queryCourse.teacherId" value="${queryCourse.teacherId}"/>
	<input type="hidden" id="hiddenOrder" name="queryCourse.order" value="${queryCourse.order}"/>
	<c:set value="${subject.parentId}" var="parentId" />
</form>
<div class="bg-fa of" id="aCoursesList">
	<!-- /课程列表 开始 -->
	<section class="container">
		<header class="comm-title course-com-title">
			<h2 class="fl tac"><span class="c-333">全部课程</span></h2>
			<aside class="h-r-search fr course-search">
				<form action="${ctx}/front/showCourseList" method="post" id="searchCourse">
					<label class="h-r-s-box"><input type="text" id="searchCourseName" placeholder="输入你想学的课程" name="queryCourse.name" value="">
						<button type="submit" class="s-btn">
							<em class="icon18">&nbsp;</em>
						</button></label>
				</form>
			</aside> 
		</header>
		<section class="c-sort-box">
			<section class="c-s-dl">
				<c:if test="${(queryCourse.subjectId!=null and queryCourse.subjectId!=0) || (queryCourse.teacherId!=null and queryCourse.teacherId!=0) || (queryCourse.membertype!=null and queryCourse.membertype!=0 and saleMap.sale.verifyMember=='ON') }">
					<dl class="c-s-fir">
						<dt><span class="c-666 fsize14">已选：</span></dt>
						<dd class="c-s-dl-li">
							<c:if test="${queryCourse.subjectId!=null and queryCourse.subjectId!=0 }">
								<ul class="f-list">
									<li><a title="${subject.subjectName}" href="javascript:void(0)"><span class="a-text1">按班型：</span><span class="a-text2">${subject.subjectName}</span><span class="a-img" onclick="clickSearch('subject',0)"></span></a></li>
								</ul>
							</c:if>
							<c:if test="${queryCourse.teacherId!=null and queryCourse.teacherId!=0 }">
								<ul class="f-list">
									<li><a title="${teacher.name}" href="javascript:void(0)"><span class="a-text1">按讲师：</span><span class="a-text2">${teacher.name}</span><span class="a-img" onclick="clickSearch('teacher',0)"></span></a></li>
								</ul>
							</c:if>
							<c:if test="${queryCourse.membertype!=null and queryCourse.membertype!=0 and saleMap.sale.verifyMember=='ON'}">
								<ul class="f-list">
									<li><a title="${memberType.title}" href="javascript:void(0)"><span class="a-text1">按会员：</span><span class="a-text2">${memberType.title}</span><span class="a-img" onclick="clickSearch('memberType',0)"></span></a></li>
								</ul>
							</c:if>
							<aside class="c-s-del dContent">
								<a class="dClose" title="全部撤销" href="javascript:clickSearch('clear',0)"> </a>
							</aside>
							<div class="clear"></div>
						</dd>
					</dl>
				</c:if>
				<dl>
					<div class="fl-wrap">
						<dt><span class="c-666 fsize14">按班型：</span></dt>
						<dd class="c-s-dl-li">
							<ul class="clearfix">
								<li <c:if test="${empty queryCourse.subjectId||queryCourse.subjectId==0}">class="current"</c:if>><a href="javascript:void(0)" onclick="clickSearch('subject',0)" title="">全部</a></li>
								<c:forEach items="${subjectList }"  var ="subject">
									<li <c:if test="${queryCourse.subjectId==subject.subjectId||parentId==subject.subjectId}">class="current"</c:if>><a onclick="clickSearch('subject',${subject.subjectId})" id="${subject.subjectId}" title="${subject.subjectName }" href="javascript:void(0);">${subject.subjectName }</a></li>
								</c:forEach>
							</ul>
							<aside class="c-s-more">
								<a href="javascript: void(0)" title="" class="fsize14 c-master">[展开]</a>
							</aside>
							<c:if test="${sonSubjectList!=null&&sonSubjectList.size()>0 }">
								<div class="c-second-li">
									<ul class="clearfix">
										<c:forEach items="${sonSubjectList }"  var ="sonSubject">
											<li <c:if test="${queryCourse.subjectId==sonSubject.subjectId}">class="current"</c:if>><a onclick="clickSearch('subject',${sonSubject.subjectId},true)" title="${sonSubject.subjectName }" href="javascript:void(0);">${sonSubject.subjectName }</a></li>
										</c:forEach>
									</ul>
								</div>
							</c:if>
						</dd>
					</div>
				</dl>
				<dl>
					<div class="fl-wrap">
						<dt><span class="c-666 fsize14">按讲师：</span></dt>
						<dd class="c-s-dl-li">
							<ul class="clearfix">
								<li <c:if test="${empty queryCourse.teacherId||queryCourse.teacherId==0}">class="current"</c:if>><a href="javascript:void(0)" onclick="clickSearch('teacher',0)" title="">全部</a></li>
								<c:forEach items="${teacherList }" var="teacher">
									<li <c:if test="${queryCourse.teacherId==teacher.id}">class="current"</c:if>><a onclick="clickSearch('teacher',${teacher.id})" title="${teacher.name }" href="javascript:void(0)"> ${ teacher.name} </a></li>
								</c:forEach>
							</ul>
							<aside class="c-s-more">
								<a href="javascript: void(0)" title="" class="fsize14 c-master">[展开]</a>
							</aside>
						</dd>
					</div>
				</dl>
				<c:if test="${saleMap.sale.verifyMember=='ON' }">
					<dl>
						<div class="fl-wrap">
							<dt><span class="c-666 fsize14">按会员：</span></dt>
							<dd class="c-s-dl-li">
								<ul class="clearfix">
									<li <c:if test="${empty queryCourse.membertype}">class="current"</c:if>><a href="" title="">全部</a></li>
									<c:forEach items="${memberTypes }" var="memberType">
										<li <c:if test="${queryCourse.membertype==memberType.id}">class="current"</c:if>><a onclick="clickSearch('memberType',${memberType.id})" title="${memberType.title }" href="javascript:void(0);"> ${ memberType.title} </a></li>
									</c:forEach>
								</ul>
								<aside class="c-s-more">
									<a href="javascript: void(0)" title="" class="fsize14 c-master">[展开]</a>
								</aside>
							</dd>
						</div>
					</dl>
				</c:if>
				<div class="clear"></div>
			</section>
			<div class="js-wrap">
				<section class="fr">
						<span class="c-ccc">
							<tt class="c-master f-fM">${page.currentPage}</tt>/<tt class="c-666 f-fM">${page.totalPageSize}</tt>
						</span>
				</section>
				<section class="fl">
					<ol class="js-tap clearfix">
						<li><span class="fsize14 c-666">排序：</span></li>
						<li <c:if test="${queryCourse.order==1 or queryCourse.order==0}">class="current"</c:if>><a href="javascript:void(0)" onclick="orderByQuery(this,1)" title="关注度">关注度</a></li>
						<li <c:if test="${queryCourse.order==2}">class="current"</c:if>><a href="javascript:void(0)" onclick="orderByQuery(this,2)" title="最新">最新</a></li>
						<li <c:if test="${queryCourse.order==3}">class="current"</c:if>><a href="javascript:void(0)" onclick="orderByQuery(this,3)" title="价格">价格</a></li>
					</ol>
				</section>
			</div>
			<%-- 课程列表 开始 --%>
			<c:if test="${empty courseList}">
				<div class="mt40">
					<!-- /无数据提示 开始-->
					<section class="no-data-wrap">
						<em class="no-data-ico">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">没有相关数据，小编正在努力整理中...</span>
					</section>
				</div>
			</c:if>
			<c:if test="${not empty courseList}">
				<div class="net-cou-box pt30">
					<ul class="of job-cou-list">
						<c:forEach items="${courseList }" var="course">
							<li>
								<div class="cc-l-wrap">
									<a class="course-img" title="${course.name}" href="${ctx}/front/couinfo/${course.id}" target="_blank">
										<c:choose>
											<c:when test="${not empty course.logo}">
												<img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${course.logo}" alt="" class="img-responsive">
											</c:when>
											<c:otherwise>
												<img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${courseimagemap.courseimage.url}" alt="" class="img-responsive">
											</c:otherwise>
										</c:choose>
									</a>
									<div class="j-c-desc-wrap">
										<h3 class="hLh30 txtOf pt10">
											<a class="j-course-title" title="${course.name}" href="${ctx}/front/couinfo/${course.id}" target="_blank">${course.name}</a>
										</h3>
										<div class="clearfix of mt15 cj-cou-ds">
											<span class="fl c-999 f-fM txtOf" title="<c:forEach items="${course.teacherList}" var="teacher">${teacher.name}&nbsp;&nbsp;</c:forEach>">讲师：
												<c:forEach items="${course.teacherList}" var="teacher">
													<a class="c-666 mr10" href="${ctx}/front/teacher/${teacher.id}">${teacher.name}</a>
												</c:forEach>
											</span>
											<span class="fr c-999 f-fM ">课时：<span class="c-666">${course.totalLessionnum}</span></span>
										</div>
										<dl class="cj-cou-desc of">
											<dd>
												<div class="c-c-sbox txtOf" title="播放量：${course.playcount}">
													<em class="icon12 c-play-num"></em>
													<tt class="fsize14">${course.playcount}</tt>
												</div>
											</dd>
											<dd>
												<div class="c-c-sbox txtOf" title="评论数：${course.commentcount}">
													<em class="icon12 c-review-num"></em>
													<tt class="fsize14">${course.commentcount}</tt>
												</div>
											</dd>
											<dd>
												<div style="border-right:none;" class="c-c-sbox txtOf" title="价格：${course.currentprice}元">
													<em class="icon12 c-couse-v"></em>
													<tt class="fsize14  vam">${course.currentprice}</tt>
												</div>
											</dd>
										</dl>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="page-mar-top">
					<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
				</div>
			</c:if>
			<%-- 课程列表 结束 --%>
		</section>
	</section>
</div>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course_list.js"></script>
</body>
</html>
