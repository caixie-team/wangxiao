<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>我的积分</title>
</head>
<body>
	<!-- 公共左侧目录区 结束 -->
	<article>
		<header class="uc-com-title">
			<span>我的积分</span>
		</header>
		<div class="i-box">
			<div>
				<section class="pb10 line2">
					<span> <em class="icon14 integral-icon vam">&nbsp;</em> <strong
						class="vam c-333 fsize14">礼品商店</strong>
					</span>
				</section>
				<section class="integral-list pb50 line2 of">
					<form id="searchForm" method="post" action="">
					<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
						<ul class="clearfix">
						<c:forEach items="${userIntegralGiftList }" var="gift">
							<li>
								<div class="u-r-wrap">
								<c:if test="${ not empty gift.courseId }">
									<section class="inte-l-img">
										<a class="giftWrap" title="${gift.logo}"
											href="javascript:void(0)"> 
											<img width="178" height="133"
											src="<%=staticImageServer %>${gift.logo}">
										</a>
									</section>
								</c:if>
								<c:if test="${empty gift.courseId }">
									<section class="inte-l-img">
										<a class="giftWrap" title="${gift.logo}"
											href="javascript:void(0)"> 
											<img width="178" height="133"
											src="#">
										</a>
									</section>
								</c:if>
									<h6 class="hLh30 of unFw line2">
										<tt class="c-blue">${gift.name}</tt>
									</h6>
									<div class="of">
										<span class="fl c-red mt5">所需积分：${gift.score}</span> <span class="fr">
											<a class="brow-btn" title="" onclick="initExchangeIntegral(${gift.id})"
											href="javascript:void(0)">兑换</a>
										</span>
									</div>
								</div>
							</li>
							</c:forEach>
						</ul>
					</form>
				</section>
				<!-- 公共分页 开始 -->
				<c:if test="${userIntegralGiftList.size()>0 }">
				<section class="paginationWrap">
				<jsp:include page="/WEB-INF/view/common/u_page.jsp"/>
				</section>
				</c:if>
				<!-- 公共分页 结束 -->
			</div>
		</div>
	</article>
	<script>
		function initExchangeIntegral(giftId) {
			$.ajax({
				url : baselocation + "/uc/exchange/check/" + giftId,
				dataType : "json",
				type : "post",
				async : false,
				success : function(result) {
					if (result.success) {
						window.location.href = "/uc/gift/doexchange/" + giftId;
					} else {
						dialogFun("提示信息", result.message, 2,"");
						return;
					}
				}
			});
		}
	</script>
</body>
</html>