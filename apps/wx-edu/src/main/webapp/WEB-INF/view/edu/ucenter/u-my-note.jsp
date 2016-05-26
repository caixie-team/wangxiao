<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的笔记</title>
</head>
<body>
	<div class="">
		<!-- 公共左侧目录区 结束 -->
		<article class="uc-m-content mb50">
			<header class="uc-com-title">
				<span>我的笔记</span>
			</header>
			 <form id="searchForm" action="${ctx}/uc/note" method="post">
				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
			 </form>
			<div class="i-box">
				<div>
					<c:if test="${empty  courseNoteList}">
						<div class="mt40">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="no-data-ico">&nbsp;</em> <span
										class="c-666 fsize14 ml10 vam">您还没有添加笔记</span>
								</section>
								<!-- /无数据提示 结束-->
						</div>
					</c:if>
					<div class="pl15">
							<c:if test="${not empty  courseNoteList}">
						<article class="s-record-wrap">
							<section>
								<div class="pt30">
								<c:forEach items="${courseNoteList}" var="study" varStatus="index">
									<script type="text/javascript">
											var lastdate=' <fmt:formatDate value="${courseNoteList[index.index-1].updateTime}" pattern="yyyy" />'; 
											var nowdate=' <fmt:formatDate value="${study.updateTime}" pattern="yyyy" />';
											if(lastdate!=nowdate){
												document.write('<aside class="pr hLh20"><span class="s-r-year c-fff"><big>'+nowdate+'</big><small>年</small></span></aside>');
											}
									</script>
									<article class="mt20 s-record">
										<span class="s-r-sj"> </span>
										<aside class="s-r-c-time">
											<span class="c-999 mr10"> <fmt:formatDate value="${study.updateTime}" pattern="MM-dd hh:mm" /></span> <em
												class="icon-2-16 s-r-c-t"> </em>
										</aside>
										<div>
											<h4 class="s-r-c-title unFw">
												<a class="fsize14 c-blue vam" title="${study.pointName }" href="${ctx}/front/playkpoint/${study.courseId}?kpointId=${study.kpointId}">学习了：${study.courseName}--${study.pointName }</a>
												<span class="c-666 fsize14 vam">并记录学习笔记：</span>
											</h4>
											<div class="clearfix pt15 s-r-c-box">
												<section class="mt10 s-r-c-desc">
													<p class="c-999">${study.shortContent}</p>
												</section>
											</div>
										</div>
									</article>
									</c:forEach>
								</div>
							</section>
						</article>
									</c:if>
					</div>
					
					<section class="paginationWrap">
						<jsp:include page="/WEB-INF/view/common/u_page.jsp"></jsp:include>
					</section>
				</div>
			</div>
		</article>
	</div>
	<script>
		function cCardFun() {
			$(".c-caed-body>tr>td>em").each(
					function() {
						var _this = $(this), _cont = _this
								.siblings(".c-csrd-m-wrap");
						_this.click(function() {
							if (_cont.is(":hidden")) {
								_cont.show();
								_this.addClass("cou-arrow-up");
								_this.parent().parent().siblings().find(
										".c-csrd-m-wrap").hide();
							} else {
								_cont.hide();
								_this.removeClass("cou-arrow-up");
							}
						});
					})
		}
	</script>
</body>
</html>