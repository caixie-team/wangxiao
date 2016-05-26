<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我兑换的礼品</title>
</head>
<body>
	<article class="u-m-center">
				<!-- /u-m-c-head -->
		<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<header class="uc-com-title">
						<span>我兑换的礼品</span>
					</header>
					<form action="${ctx }/uc/mygift" method="post" id="searchForm">
						<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
					</form>
					<!-- /u-m-c-head -->
					<section class="i-box">
						<div>
							<section id="" class="">
							 <c:if test="${empty userIntegralGiftList }">
								  <section class="comm-tips-1">
										<p>
											<em class="vam c-tips-1">&nbsp;</em>
											<font class="c-999 fsize12 vam">你还没有兑换过礼品，去<a class="c-orange" title="" href="${ctx}/uc/integift">礼品列表</a>看看</font>
										</p>
									</section>
							</c:if>
								<ul class="u-collect-list">
								<c:forEach items="${userIntegralGiftList }" var="gift">
									<li>
									<c:if test="${gift.courseId!=0 }">
										<section class="fl u-c-img">
											<img width="154" height="116" src="<%=staticImageServer %>${gift.courseLogo}" alt="">
										</section>
										<h4 class="hLh20 of unFw"><tt class="c-666 fsize16 f-fM txtOf">${gift.courseName}</tt></h4>
										<div class="u-c-list-desc mt10">
											<p class="c-999">${gift.courseTitle }</p>
										</div>
										</c:if>
										<c:if test="${gift.courseId==0 }">
										<section class="fl u-c-img">
											<img src="<%=staticImageServer %>${gift.logo}" alt="">
										</section>
										<h4 class="hLh20 of unFw"><tt class="c-666 fsize16 f-fM txtOf">${gift.name}</tt></h4>
										<div class="u-c-list-desc mt10">
											<p class="c-999">${gift.content }</p>
										</div>
										</c:if>
										<div class="hLh20 of mt30 inte-gift">
											<span class="vam c-666 fsize12 f-fM">
												<tt class="gift-cre-time"><fmt:formatDate value="${gift.createTime }" type="both"/></tt>  <tt class="ml50 inte-amout">兑换所需积分：${gift.score}</tt>
											</span>
										</div>
									</li>
									</c:forEach>
								</ul>
							</section><!-- / -->
						</div>
					</section>
					<c:if test="${not empty userIntegralGiftList }">
									<section class="mt5 mb5 tac">
							<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"/>
							</section>
					</c:if>
				</section>
			</article>
		</article>
	<!-- /u-main end -->
</body>
</html>
