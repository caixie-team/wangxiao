<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
</head>
<body>
<article>
	<header class="uc-com-title">
		<span>我的账户</span>
	</header>
	<div class="i-box">
		<section>
			<section class="line2 pb15">
				<h4 class="f-bold">
					<span class="c-333 fsize14">我的账户学币余额：</span>
					<span class="fsize16 f-fM c-red">${userAccount.balance}</span>
					<span class="u-acc-box">
						<span class="c-333 fsize14">冻结金额：</span>
						<span class="fsize16 f-fM c-999">${userAccount.forzenAmount}</span>
					</span>
				</h4>
			</section>
			<section class="mt20 pb20 line2">
				<h4 class="f-bold">
					<span class="c-333 fsize14">需要充值学币？</span>
					<span class="c-recharge">
						<tt class="vam c-333 fsize14">充值金额：</tt>
						<label class="u-a-txt vam">
							<input type="text" id="payCash" style="width: 240px;" />
						</label> 元
					</span>
					<a class="p-ml20 jihu-btn"  title="立即充值" onclick="doPayCash()" href="javascript:void(0)">马上充值</a>
				</h4>
			</section>
			<section class="mt20 pb20 line2">
				<h4 class="f-bold">
					<span class="c-333 fsize14">有新学币充值卡？</span>
					<a class="ml20 jihu-btn" title="马上充值" id="activateCardCourse" href="javascript:void(0)">马上充值</a>
				</h4>
			</section>
			<section class="mt30">
				<h5 class="c-333 fsize14 f-bold">1、学币有什么用途？</h5>
				<p class="c-666 mt5 pl20 ml5">充值账户，方便网站购课所需</p>
			</section>
			<section class="mt30">
				<h5 class="c-333 fsize14 f-bold">2、充值学币需要注意事项？</h5>
				<p class="c-666 mt5 pl20 ml5">1元人民币充值1学币,最小支持1元充值,单位元</p>
			</section>
			<section class="mt30">
				<h5 class="c-333 fsize14 line2 pb10 f-bold">我的账户变动表</h5>

					<c:if test="${empty accList}">
						<section class="mt30 mb30 tac">
							<em class="no-data-ico cTipsIco">&nbsp;</em>
							<span class="c-666 fsize14 ml10 vam">还没有账户变动记录~</span>
						</section>
					</c:if>
					<c:if test="${not empty accList}">
						<form action="${ctx}/uc/acc" name="searchForm" id="searchForm" method="post">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
							<table class="u-c-card tab-integral mt10 accout-table" width="100%" cellspacing="0" cellpadding="0" border="0">
								<thead>
								<tr>
									<th width="22%">消费金额</th>
									<th width="22%">操作类型</th>
									<th width="22%">账户余额</th>
									<th width="34%">操作时间</th>
								</tr>
								</thead>
								<tbody class="tac">
									<c:forEach var="acc" items="${accList}">
										<tr>
											<td>${acc.trxAmount}</td>
											<td>
												<c:if test="${acc.actHistoryType=='SALES'}"> 消费 </c:if>
												<c:if test="${acc.actHistoryType=='REFUND'}"> 退款 </c:if>
												<c:if test="${acc.actHistoryType=='CASHLOAD'}"> 现金充值 </c:if>
												<c:if test="${acc.actHistoryType=='VMLOAD'}"> 充值卡充值 </c:if>
											</td>
											<td>${acc.balance}</td>
											<td><fmt:formatDate value="${acc.createTime}" pattern="yyyy/MM/dd  HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
						<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
					</c:if>
			</section>
		</section>
	</div>
</article>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_account.js"></script>
<script type="text/javascript">
	function doPayCash(){
		var payCash=$("#payCash").val();
		if(payCash==null||payCash==''){
			dialogFun('充值提示','请输入充值金额',0);
			return;
		}
		var reg = /^[0-9]+(.[0-9]{1,2})?$/;

		if(isNaN(payCash) ||!reg.test(payCash)){
			dialogFun('充值提示','请输入正确的充值金额',0);
			return;
		}
		window.location.href="/cash/order?payCash="+payCash;
	}
</script>
</body>
</html>
