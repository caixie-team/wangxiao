<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>学习记录</title>
</head>
<body>
<article class="uc-m-content mb50">
	<header class="uc-com-title">
		<span>我的学习记录</span>
	</header>
	<div class="i-box">
		<div>
			<div class="pl15">
				<c:if test="${empty studylist}">
					<section class="mt30 mb30 tac">
						<em class="no-data-ico cTipsIco">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">还没有学习记录~</span>
					</section>
				</c:if>
				<c:if test="${not empty studylist}">
					<form id="searchForm" action="${ctx}/uc/study" method="post">
						<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
					</form>
					<article class="s-record-wrap">
						<section>
							<fmt:formatDate value="${today}" pattern="yyyy/MM/dd  HH:mm:ss" />
							<c:forEach items="${studylist}" var="study" varStatus="index">
								<script type="text/javascript">
									var lastdate=' <fmt:formatDate value="${studylist[index.index-1].updateTime}" pattern="yyyy" />';
									var nowdate=' <fmt:formatDate value="${study.updateTime}" pattern="yyyy" />';
									if(lastdate!=nowdate){
										document.write('<aside class="pr hLh20"><span class="s-r-year c-fff"><big>'+nowdate+'</big><small>年</small></span></aside>');
									}
								</script>
								<div class="pt30">
									<article class="mt20 s-record">
										<span class="s-r-sj"> </span>
										<aside class="s-r-c-time">
											<span class="c-999 mr10"> <fmt:formatDate value="${study.updateTime}" pattern="MM-dd hh:mm" /></span>
											<em class="icon-2-16 s-r-c-t"> </em>
										</aside>
										<div>
											<h4 class="s-r-c-title unFw">
												<a class="fsize14 c-blue" title="${study.kpointName }" href="${ctx}/front/playkpoint/${study.courseId}/?kpointId=${study.kpointId}">学习了：${study.kpointName }</a>
											</h4>
											<div class="clearfix pt15 s-r-c-box">
												<section class="s-r-c-img fl">
													<c:if test="${not empty study.logo }">
														<img class="img-responsive" alt="" src="<%=staticImageServer%>/${study.logo }">
													</c:if>
													<c:if test="${empty study.logo }">
														<img class="img-responsive" alt="" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" />
													</c:if>
												</section>
												<p class="c-666 fsize12">主讲：
													<c:choose>
														<c:when test="${not empty study.teacherName }">
															${study.teacherName}
														</c:when>
														<c:otherwise>
															未知
														</c:otherwise>
													</c:choose>
												</p>
												<section  class="mt5 s-r-c-desc i-q-txt3 of">
													<p class="c-999">${kpoint.content}</p>
												</section>
												<p class="tac mt5">
													<a class="c-master" href="${ctx}/front/playkpoint/${study.courseId}/?kpointId=${study.kpointId}">继续学习&gt;&gt;</a>
												</p>
											</div>
										</div>
									</article>
								</div>
							</c:forEach>
						</section>
					</article>
				</c:if>
			</div>
			<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
		</div>
	</div>
</article>
</body>
</html>
