<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>课程卡激活</title>
</head>
<body>
<article class="uc-m-content mb50">
	<header class="uc-com-title">
		<span>我的课程卡</span>
	</header>
	<div class="i-box">
		<div class=" p-cou-card-pad">
			<section class="pb20 line2">
				<h4 class="hLh30">
					<span class="c-333 fsize14 fbold">有新课程卡？</span>
					<a class="ml20 jihu-btn" title="马上激活" id="activateCardCourse" href="javascript:void(0)">马上激活</a>
				</h4>
			</section>
			<section class="mt15">
				<h5 class="c-333 fsize14 fbold">课程卡有什么用途？</h5>
				<p class="c-666 mt5">使用课程卡激活后可以去学习与课程卡金额相应的课程。</p>
			</section>
			<section class="mt30 mb30">
				<h5 class="c-333 fsize14 line2 pb10 fbold">我的课程卡列表</h5>
				<c:if test="${empty userCardCodeList}">
					<section class="mt30 mb30 tac">
						<em class="no-data-ico cTipsIco">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">你还没有课程卡记录~</span>
					</section>
				</c:if>
				<c:if test="${not empty userCardCodeList}">
				<!-- pc端开始 -->
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="u-c-card tab-integral mt10 p-card-table">
						<thead>
						<tr>
							<th>编号</th>
							<th>卡号</th>
							<th>卡密码</th>
							<th width="28%">关联课程</th>
							<th>使用时间</th>
							<th>卡状态</th>
						</tr>
						</thead>
						<tbody class="tac">
						<c:forEach var="userCard" items="${userCardCodeList}">
							<tr>
								<td>${userCard.id}</td>
								<td>${userCard.cardCode}</td>
								<td>${userCard.cardCodePassword}</td>
								<td>${userCard.courseName}</td>
								<td><fmt:formatDate value="${userCard.useTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
								<td>已使用</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<!-- pc端结束 -->
				<!-- 移动端开始 -->
				<c:if test="${not empty userCardCodeList}">
					<table width="100%" cellspacing="0" cellpadding="0" border="0" class="u-c-card tab-integral mt10 m-card-table">
						<thead>
						<tr>
							<th>编号</th>
							<th style="width: 60%;">卡号</th>
							<th>卡状态</th>
						</tr>
						</thead>
						<tbody class="tac c-caed-body">
						<c:forEach var="userCard" items="${userCardCodeList}">
							<tr>
								<td class="pr"><span class="vam c-card-code">${userCard.id}</span><em class="cou-arrow-down c-arrow"></em>
									<div class="c-csrd-m-wrap undis">
										<div class="c-card-more">
											<ul>
												<li class="clearfix"><div class="c-c-more-cleft">卡编号：</div><div class="c-c-more-cright">${userCard.cardCode}</div></li>
												<li class="clearfix"><div class="c-c-more-cleft">卡密码：</div><div class="c-c-more-cright">${userCard.cardCodePassword}</div></li>
												<li class="clearfix"><div class="c-c-more-cleft">关联课程：</div><div class="c-c-more-cright">${userCard.courseName}</div></li>
												<li class="clearfix"><div class="c-c-more-cleft">使用时间：</div><div class="c-c-more-cright"><fmt:formatDate value="${userCard.useTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></div></li>
											</ul>
											<div class="yk-DT-arrow"><em>◆</em><span>◆</span></div>
										</div>
									</div>
								</td>
								<td style="width: 60%;">${userCard.cardCode}（课程卡）</td>
								<td>已使用</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<!--移动端结束  -->
				<form action="${ctx}/uc/card" method="post" id="searchForm" name="searchForm">
					<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
				</form>
				<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"/>
			</section>
		</div>
	</div>
</article>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_card.js"></script>
<script>
	$(function() {
		cCardFun();
	})
	function cCardFun(){
		$(".c-caed-body>tr>td>em").each(function() {
			var _this = $(this),
					_cont = _this.siblings(".c-csrd-m-wrap");
			_this.click(function() {
				if(_cont.is(":hidden")){
					_cont.show();
					_this.addClass("cou-arrow-up");
					_this.parent().parent().siblings().find(".c-csrd-m-wrap").hide();
				}else{
					_cont.hide();
					_this.removeClass("cou-arrow-up");
				}
			});
		})
	}
</script>
</body>
</html>
