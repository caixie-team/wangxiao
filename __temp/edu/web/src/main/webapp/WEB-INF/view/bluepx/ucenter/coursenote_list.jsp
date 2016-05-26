<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt"> <li class="current"><a class="vam" title="我的笔记" href="javascript:void(0)">我的笔记</a></li> </ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="pl15 pr15">
					<c:if test="${!empty  courseNoteList}">
						<article class="s-record-wrap">
							<%-- <fmt:formatDate value="${today}" pattern="yyyy/MM/dd  HH:mm:ss" /> --%>
							<section>
								<div class="pt30" id="allRes">
									<c:forEach items="${courseNoteList}" var="study" varStatus="index">
										<script type="text/javascript">
											var lastdate=' <fmt:formatDate value="${courseNoteList[index.index-1].updateTime}" pattern="yyyy" />'; 
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
													<a href="${ctx}/front/playkpoint/${study.courseId}?kpointId=${study.kpointId}" title="${study.pointName }"  class="fsize14 c-blue">学习了：${study.courseName}--${study.pointName }</a>
													<span class="c-666 fsize14">并记录学习笔记：</span>
												</h4>
												 <div class="clearfix zoom pt15">
													<section class="mt10 s-r-c-desc" style="height: auto;">
														<p class="c-999">${study.shortContent}</p>
													</section>
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
							<form id="searchForm" action="${ctx}/uc/note" method="post">
								<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
							 </form>
						</section>
					</c:if>
				</div>
			</section>
		</section>
	</article>

</body>
</html>
