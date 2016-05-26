<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>中国在线教育平台第一品牌</title>
<script type="text/javascript" src="${ctximg }/static/edu/js/ucenter/u_account.js"></script>
<script type="text/javascript">
	function doPayCash(){
		var payCash=$("#payCash").val();
		if(payCash==null||payCash==''){
			dialog('充值提示','请输入充值金额',1);
			return;
		}
		/* var reg = /^[1-9]\d*$/;
		
		if(isNaN(payCash) ||!reg.test(payCash)){
			dialog('充值提示','请输入正确的充值金额',1);
			return;
		}  */
		window.location.href="/cash/order?payCash="+payCash;
	}
</script>
</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<!-- /u-m-c-head -->
			<section class="u-m-c-head">
				<ul class="fl u-m-c-h-txt"> <li class="current"><a class="vam" title="我的账户" href="javascript:void(0)">我的账户</a></li> </ul>
				<div class="clear"></div>
			</section>
			<!-- /u-m-c-head -->
			<section class="line1">
				<div class="pl20 pr15">
					<section class="mt20 line2">
						<h4 class="hLh30">
							<span class="c-333 fsize14">我的账户学币余额：</span><span class="fsize16 f-fM c-red">${userAccount.balance}</span>
							<span class="c-333 fsize14 ml20">冻结金额：</span><span class="fsize16 f-fM c-999">${userAccount.forzenAmount}</span>
						</h4>
					</section>
					<section class="mt20 pb20 line2"> 
						<h4 class="hLh30"> 
							<span class="c-333 fsize14">需要充值学币？</span> 
							<span class="ml20">
								<tt class="vam c-333 fsize14">充值金额：</tt>
								<label class="u-a-txt vam"><input type="text" id="payCash" style="width: 240px;" /></label>
							</span>元
							<a href="javascript:void(0)" title="立即充值" onclick="doPayCash()" class="ml20 jihu-btn">马上充值</a> 
						</h4> 
					</section>
					<section class="mt20 pb20 line2">
						<h4 class="hLh30">
							<span class="c-333 fsize14">有新学币充值卡？</span> 
							<a href="javascript:void(0)" id="activateCardCourse" title="马上充值" class="ml20 jihu-btn">马上充值</a>
						</h4>
					</section>
					<section class="mt30"> <h5 class="c-333 fsize14">1、学币有什么用途？</h5> <p class="c-666 mt5 pl20 ml5">充值账户，方便网站购课所需</p> </section>
					<section class="mt30"> <h5 class="c-333 fsize14">2、充值学币需要注意事项？</h5> <p class="c-666 mt5 pl20 ml5">1元人民币充值1学币,最小支持1元充值,单位元</p> </section>
				
					<section class="mt30">
						<h5 class="c-333 fsize14 line2 pb10">我的账户变动表</h5>
						<c:if test="${empty accList}">
							<section class="comm-tips-1">
								<p>
									<em class="vam c-tips-1">&nbsp;</em> <font
										class="c-999 fsize12 vam">你还没有充值卡记录</font>
								</p>
							</section>
						</c:if>
						<c:if test="${!empty accList}">
							<form action="${ctx}/uc/acc" name="searchForm" id="searchForm" method="post">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}"/>
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="u-c-card tab-integral mt10">
								<thead>
									<tr>
										<th width="">消费金额</th>
										<th width="">操作类型</th>
										<th width="">账户余额</th>
										<th width="">操作时间</th>
									</tr>
								</thead>
								<tbody class="tac">
									<c:forEach var="acc" items="${accList}">
										
										<tr>
											<td>${acc.trxAmount}</td>
											<td>
												<c:if test="${acc.actHistoryType=='SALES'}">
													消费
												</c:if> <c:if test="${acc.actHistoryType=='REFUND'}">
													退款
												</c:if> <c:if test="${acc.actHistoryType=='CASHLOAD'}">
													现金充值
												</c:if> <c:if test="${acc.actHistoryType=='VMLOAD'}">
													充值卡充值
												</c:if>
											</td>
											<td>${acc.balance}</td>
											<td><fmt:formatDate value="${acc.createTime}"
													pattern="yyyy/MM/dd  HH:mm:ss" /></td>
										</tr>
										
									</c:forEach>
								</tbody>
							</table>
							</form>
							<section class="mt5 mb5">
								<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp" />
							</section>
						</c:if>
					</section>
				</div>
			</section>
		</section>
	</article>

</body>
</html>
