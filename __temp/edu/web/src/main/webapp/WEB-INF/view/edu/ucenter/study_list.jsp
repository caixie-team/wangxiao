<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学习记录</title>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt">
					<li class="current"><a href="javascript:void(0)" title="">我的学习记录</a></li>
				</ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="pl15 pr15">
					<c:if test="${!empty  studylist}">
						<article class="s-record-wrap">
							<fmt:formatDate value="${today}" pattern="yyyy/MM/dd  HH:mm:ss" />
							<section>
								<div class="pt30" id="allRes">
									<c:forEach items="${studylist}" var="study" varStatus="index">
										<script type="text/javascript">
											var lastdate=' <fmt:formatDate value="${studylist[index.index-1].updateTime}" pattern="yyyy" />'; 
											var nowdate=' <fmt:formatDate value="${study.updateTime}" pattern="yyyy" />';
											if(lastdate!=nowdate){
												document.write('<aside class="pr hLh20"><span class="s-r-year c-fff"><big>'+nowdate+'</big><small>年</small></span></aside>');
											}
										</script>
										<article class="mt20 s-record">
											<span class="s-r-sj">&nbsp;</span>
											<aside class="s-r-c-time">
												<span class="c-999 mr10"> <fmt:formatDate value="${study.updateTime}" pattern="MM-dd hh:mm" />
												</span> <em class="icon-2-16 s-r-c-t">&nbsp;</em>
											</aside>
											<div>
												<h4 class="s-r-c-title unFw">
													<a href="${ctx}/front/playkpoint/${study.courseId}/?kpointId=${study.kpointId}" title="${study.kpointName }"  class="fsize14 c-blue">学习了：${study.courseName}--${study.kpointName }</a>
												</h4>
												<div class="clearfix zoom pt15">
													<section class="s-r-c-img pr fl">
														<span class="play-1 pa">&nbsp;</span>
														<c:if test="${study.logo!=null&&study.logo!=''}">
															<img src="<%=staticImageServer %>/${study.logo }" height="116" width="154" alt="">
														</c:if>
														<c:if test="${study.logo==''||study.logo==null}">
															<img src="${ctximg }/static/edu/images/default/default_point.jpg" height="116" width="154" alt="">
														</c:if>
													</section>
													<p class="hLh20 of">
														<tt class="c-666">
															主讲：
															<c:choose>
																<c:when test="${study.teacherName!=null &&study.teacherName!=''}">
																${study.teacherName}
													</c:when>
																<c:otherwise>
																未知
													</c:otherwise>
															</c:choose>
														</tt>
													</p>
													<section class="mt10 s-r-c-desc">
														<p class="c-999">${kpoint.content}</p>
													</section>
													<p class="tac mt5">
														<span class="mr5 c-orange"> </span><a href="${ctx}/front/playkpoint/${study.courseId}/?kpointId=${study.kpointId}"  class="c-blue">继续学习&gt;&gt;</a>
													</p>
												</div>
											</div>
										</article>
									</c:forEach>
								</div>
							</section>
						</article>
						<section class="mt5 mb5">
							<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
						</section>
						
						<section class="fr s-inp-wrap">
							<form id="searchForm" action="${ctx}/uc/study" method="post">
								<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
							 </form>
						</section>
					</c:if>
					<c:if test="${empty studylist}">
						<section class="comm-tips-1">
							<p>
								<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">对不起，你还没学习记录！建议你<a href="${ctx}/front/showcoulist" title="" class="c-orange">去选课</a></font>
							</p>
						</section>
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
														<img src="${ctximg }/static/edu/images/default/default_goods.jpg" width="150" height="113" class="dis" />
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
					</c:if>
				</div>
			</section>
		</section>
	</article>
</body>
</html>
