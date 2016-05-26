<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>直播中心</title>
</head>
<body>
<!-- /直播列表 开始-->
<form id="searchForm" action="${ctx}/front/showLiveList" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
	<input type="hidden" id="hiddenMemberId" name="queryCourse.membertype" value="${queryCourse.membertype}"/>
	<input type="hidden" id="hiddenSubjectId" name="queryCourse.subjectId" value="${queryCourse.subjectId}"/>
	<input type="hidden" id="hiddenTeacherIds" name="queryCourse.teacherId" value="${queryCourse.teacherId}"/>
	<input type="hidden" id="hiddenOrder" name="queryCourse.order" value="${queryCourse.order}"/>
	<c:set value="${subject.parentId}" var="pareOntId" />
</form>
<div class="bg-fa of" id="aCoursesList">
	<div class="live-banner">
		<div class="container">
			<img src="${ctx}/static/nxb/web/img/pic/live-banner.png" class="img-responsive">
		</div>
	</div>
	<section class="container mt30">
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
			<%-- 直播列表 开始 --%>
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
				<div class="live-cou-wrap mt20">
					<ul>
						<c:forEach items="${courseList}" var="live">
							<li>
								<div class="l-c-wrap">
									<div class="l-c-time">
										<div class="l-c-time-icon">
											<span></span>
										</div>
										<div class="tac l-c-time-num">
											<h3 class="c-333 hLh30"><fmt:formatDate value="${live.liveBeginTime}" type="both" pattern="MM-dd"/></h3>
											<p class="c-666 fsize16 hLh30">
											<fmt:formatDate value="${live.liveBeginTime}" type="both" pattern="HH:mm"/>
											~
											<fmt:formatDate value="${live.liveEndTime}" type="both" pattern="HH:mm"/>
											</p>
										</div>
									</div>
									<div>
										<aside class="l-c-pic hand" onclick="window.location.href='${ctx}/front/couinfo/${live.id}'">
											<c:choose>
												<c:when test="${not empty live.logo}">
													<img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${live.logo}" alt="" class="img-responsive">
												</c:when>
												<c:otherwise>
													<img src="<%=staticImageServer%>/${loadimagemap.loadimage.url}" xsrc="<%=staticImageServer%>/${courseimagemap.courseimage.url}" alt="" class="img-responsive">
												</c:otherwise>
											</c:choose>
										</aside>
										<div class="l-c-desc">
											<h3 class="hLh30 txtOf">
												<a class="i-art-title" title="${live.name}" href="${ctx}/front/couinfo/${live.id}">${live.name}</a>
											</h3>
											<p class="hLh30 c-666 fsize14">讲师：
												 <c:forEach items="${live.teacherList }" var="teacher">
													${ teacher.name}
												</c:forEach>
											</p>
											<p class="hLh30 c-master l-c-money">￥${live.currentprice}</p>
											<section class="i-q-txt mt5 i-q-txt2">
												<p>
													<span class="c-999 f-fA">${live.title}</span>
												</p>
											</section>
										</div>
									</div>
									<c:if test="${live.beginTimeNum!=0}">
									<div class="l-c-state l-play-soon">
										<div class="l-c-sta-top">
											<span class="vam format-date" data-diff="${live.beginTimeNum }">
											</span>
										</div>
										<div class="l-c-sta-bot tac mt20"><a href="${ctx}/front/couinfo/${live.id}">进入直播</a></div>
									</div>
									</c:if>
									<c:if test="${live.beginTimeNum==0&&live.endTimeNum!=0}">
										<div class="l-c-state">
											<div class="l-c-sta-top">
												<em class="icon24 vam"> </em>
												<span class="vam">直播中</span>
											</div>
											<div class="l-c-sta-bot tac mt20"><a href="${ctx}/front/couinfo/${live.id}">进入直播</a></div>
										</div>
									</c:if>
									<c:if test="${live.beginTimeNum==0&&live.endTimeNum==0}">
									<div class="l-c-state l-play-over">
										<div class="l-c-sta-top">
											<span class="vam">直播结束！</span>
										</div>
										<div class="l-c-sta-bot tac mt20"><a href="${ctx}/front/couinfo/${live.id}">查看回放</a></div>
									</div>
									</c:if>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="page-mar-top1">
					<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
				</div>
			</c:if>
			<%-- 直播列表 结束 --%>
		</section>
	</section>
</div>
<script type="text/javascript" src="<%=imagesPath%>/static/edu/js/front/course/course_list.js"></script>
<script type="text/javascript" src="${ctximg }/static/common/jquery.countdown.js"></script>
<script type="text/javascript">
	$(function(){
		//倒计时
		$('.format-date').countdown({
			tmpl : '<span>%{d}</span>天<span>%{h}</span>小时<span>%{m}</span>分<span>%{s}</span>秒',
			end : function(){
				$(this).html("直播开始");
			}
		});
	});
</script>
</body>
</html>
