<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的收藏</title>
</head>
<body>
<article class="uc-m-content mb50">
	<header class="uc-com-title">
		<span>我收藏的课程</span>
	</header>
	<div class="i-box">
		<div>
			<div class="mb15">
				<section>
					<span class="vam">
						<label class="vam am-checkbox">
							<input type="checkbox" value="" name="" id="allC" onclick="allCheck()">
						</label>
					</span>
<%--
					<a class="c-666 ml10 vam" href="javascript:void(0)" onclick="allCheck()" id="allCTitle">全选</a>
--%>
					<a class="c-666 ml10 vam u-mf-btn" onclick="allDelete()" href="javascript:;">取消收藏</a>
				</section>
			</div>
			<form action="${ctx }/uc/fav" id="searchForm" method="post">
				<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage" />
			</form>
			<form id="deleteAllFavorite" action="${ctx}/uc/del" method="post">
				<ul class="u-f-c-list">
					<c:if test="${empty courseList }">
						<li>
							<section class="mt30 mb30 tac">
								<em class="no-data-ico cTipsIco">&nbsp;</em>
								<span class="c-666 fsize14 ml10 vam">对不起，你还没收藏课程!~</span>
							</section>
						</li>
					</c:if>
					<c:if test="${not empty courseList }">
						<c:forEach var="course" items="${courseList}">
							<li>
								<aside class="u-f-wrap">
									<div class="u-f-box">
										<label class="vam">
											<input type="checkbox" value="${course.favouriteId}" name="sellIdArr">
										</label>
										<a href="${ctx}/front/couinfo/${course.courseId}" class="u-f-pic">
											<c:choose>
												<c:when test="${not empty course.logo}">
													<img alt="" class="img-responsive" src="<%=staticImageServer%>/${course.logo}" xsrc="">
												</c:when>
												<c:otherwise>
													<img alt="" class="img-responsive" src="<%=staticImageServer%>/${courseimagemap.courseimage.url}" xsrc="">
												</c:otherwise>
											</c:choose>
										</a>
									</div>
								</aside>
								<h3 class="hLh30 txtOf">
									<a class="i-oc-title" title="" href="${ctx}/front/couinfo/${course.courseId}">${course.name}</a>
								</h3>
								<section class="i-q-txt mt5">
									<p class="txtOf"> <span class="c-666 fsize14">${course.title}</span> </p>
								</section>
								<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo">
									<span class="fsize12 c-999">过期时间：<fmt:formatDate value="${course.loseAbsTime}" pattern="yyyy-MM-dd HH:mm" /></span>
								</section>
								<aside class="u-f-c-more">
									<a class="c-blue fsize12" href="${ctx}/front/couinfo/${course.courseId}">查看详情</a>
									<a class="ml15 c-green fsize12" href="${ctx}/uc/del?sellIdArr=${course.favouriteId}">取消收藏</a>
								</aside>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</form>
			<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
		</div>
	</div>
</article>
<script type="text/javascript">
	function allCheck(){
		var flag = false;
		if($("#allC").prop("checked")){
			flag = true;
		}
		$("input[name='sellIdArr']").prop("checked",flag);
	}
	function allDelete() {
		var idarr = $("input[name='sellIdArr']");
		var id = 0;
		for (var i = 0; i < idarr.length; i++) {
			if (idarr[i].checked) {
				id++;
			}
		}
		if (id == 0) {
			dialogFun('提示', '请选择要删除的课程', 0);
			return false;
		}
		dialogFun('提示','是否确认要取消收藏？',2,'javascript:_allDelete()');
	}
	function _allDelete(){
		$("#deleteAllFavorite").submit()
	}
</script>
</body>
</html>
