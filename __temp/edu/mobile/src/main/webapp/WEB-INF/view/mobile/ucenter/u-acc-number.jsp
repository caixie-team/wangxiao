<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>我的账户 </title>
</head>
<body>
	<div class="m-ptb54">
		<header id="header">
			<section class="h-wrap">
				<div class="menu-btn">
					<a href="/mobile/uc/home" title="" class="go-history">&nbsp;</a>
				</div>
				<h2 class="commHeadTitle"><span>我的账户</span></h2>
			</section>
		</header>
		<!-- /header -->
		<div>
			<!-- body main -->
			<section class="comm-main animated fadeIn">
				<!-- 我的账户金额 开始 -->
				<div class="u-a-n-box">
					<svg width="100px" height="100px" viewBox="0 0 1024 1024" enable-background="new 0 0 100 100" xml:space="preserve">
					  <path fill="#ea8010" d="M560.798 629.8l70.283 162.889c0 0-44.359-34.608-70.283-34.608-25.956 0-67.027 33.617-67.027 33.617s-44.323-33.617-70.264-33.617c-25.956 0-68.142 32.554-68.142 32.554l71.36-160.835 134.073 0zM424.567 613.52h138.385v-48.87h-138.385v48.87zM689.748-47.463c-9.543 10.567-17.272 21.469-23.178 32.724-5.912 11.248-8.873 22.553-8.873 33.919v189.539l13.977 2.057 156.888 29.303c-66.015 173.899-265.915 308.29-265.915 308.29l-138.080 0c0 0-301.923-160.97-301.923-410.727 0-249.752 313.463-256.936 313.463-256.936s61.494-4.307 113.852 0c0-1.141 84.472 7.313 163.692 48.827-8.712 7.348-16.685 15.013-23.903 23.004zM598.111 208.231v-49.111h-83.548v-26.708h83.549v-49.113l-83.549 0v-60.665h-62.497v60.665h-82.452l0 49.113h82.452v26.708h-82.452v49.111h58.035l-69.134 140.279h68.448l44.444-94.728c5.646-11.87 9.641-22.267 12.033-31.331 2.804 9.816 6.81 20.244 12.007 31.331l45.311 94.728h68.502l-70.040-140.279h58.89zM974.3 194.542l-143.319 26.272-140.565-26.272-12.528-1.843v-169.896c0-10.189 2.655-20.324 7.953-30.403 5.294-10.091 12.219-19.86 20.776-29.334 8.558-9.47 18.279-18.44 29.186-26.889 10.896-8.459 21.997-15.995 33.311-22.613 11.298-6.626 22.199-12.135 32.692-16.503 10.489-4.381 19.705-7.389 27.653-9.021l3.361-0.606 3.668 0.606c7.739 1.635 16.755 4.641 27.041 9.021 10.29 4.367 20.876 9.877 31.783 16.503 10.898 6.618 21.644 14.154 32.233 22.613 10.594 8.453 20.023 17.42 28.27 26.889 8.252 9.474 14.922 19.243 20.014 29.334 5.093 10.080 7.644 20.214 7.644 30.403l0 169.899-9.17 1.841zM952.67 19.447c0-7.836-1.903-15.572-5.709-23.19-3.807-7.612-8.903-14.952-15.291-22.003-6.385-7.057-13.72-13.672-22.014-19.835-8.29-6.161-16.916-11.764-25.876-16.805-8.959-5.033-18.034-9.411-27.219-13.097-9.19-3.697-17.706-6.45-25.539-8.243l0 152.574h-122.33v107.205l120.646 20.502 1.684-0.336v-127.372h121.649l0-49.4z" transform="translate(0, 812) scale(1, -1)"/>
					</svg>
					<section class="u-a-n-att">
						<h6>我的账户余额</h6>
						<h4>￥${userAccount.balance}</h4>
					</section>
				</div>
				<!-- 我的账户金额 结束 -->
				<div class="u-a-n-list">
					<section>
						<section class="v-card-txt-title">
							<span>我的账户明细</span>
						</section>
						<c:if test="${accList==null||accList.size()==0}">
						<!-- 无数据时提示 开始 -->
						<div class="undataBox">
							<span class="undata-icon">&nbsp;</span>
							<span>你还没有消费记录</span>
						</div>
						</c:if>
						<!-- 无数据时提示 结束 -->
						
						<c:if test="${accList!=null&&accList.size()>0}">
						<article class="v-c-txt">
							<ol class="v-c-kcb" id="accContent">
								<c:forEach var="acc" items="${accList}">
								<li>
									<span class="list-pointer">&nbsp;</span>
									<section class="v-c-kcb-lis">
										<p>
											<c:if test="${acc.actHistoryType=='SALES'}">
												消费
											</c:if> <c:if test="${acc.actHistoryType=='REFUND'}">
												退款
											</c:if> <c:if test="${acc.actHistoryType=='CASHLOAD'}">
												现金充值
											</c:if> <c:if test="${acc.actHistoryType=='VMLOAD'}">
												充值卡充值
											</c:if>
										</p>
									</section>
									<section class="v-c-kcb-time">
										<span><fmt:formatDate value="${acc.createTime}" pattern="yyyy-MM-dd  HH:mm" /></span>
									</section>
									<aside class="u-a-n-num">
										<c:if test="${acc.actHistoryType=='SALES'}">
											<span class="zc-num">-${acc.balance}</span>
										</c:if>
										<c:if test="${acc.actHistoryType!='SALES'}">
											<span class="sr-num">+${acc.balance}</span>
										</c:if>
									</aside>
								</li>
								</c:forEach>
							</ol>
						</article>
						</c:if>
					</section>

					<section>
						<section class="onload-more" style="display: none">
							<a title="加载更多..." href="javascript: void(0)">
								<img src="${ctx }/static/mobile/img/loading.gif">
								<span>正在努力加载中...</span>
							</a>
						</section>
					</section>
					<input type="hidden" value="${page.totalPageSize}" id="totalPageSize"/>
					<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
				</div>
				<!-- / 我的账户明细 -->
			</section>
			<!-- body main -->
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			posFun(); //定位上TopBar与下menuBar的位置
			$("#pageCurrentPage").val(1);
			//加载分页
			$(window).bind('scroll',function(){
				var sTop = document.documentElement.scrollTop + document.body.scrollTop;
				var sHeight = document.documentElement.scrollHeight;
				var windowHeight = $(this).height();
				//当滚动到最底下时加载数据
				if(sHeight==(sTop+windowHeight)){
					var totalPageSize=parseInt($("#totalPageSize").val());//总页数
					var nextPage=parseInt($("#pageCurrentPage").val())+1;//下一页
					if(nextPage<=totalPageSize){
						$("#pageCurrentPage").val(nextPage);
						$(".onload-more").show();
						$.ajax({
							url:'/mobile/uc/ajax/acc',
							type:"post",
							data:{"page.currentPage":nextPage},
							dataType:"text",
							success:function(result){
								$("#accContent").append(result);
							}
						});
						$(".onload-more").hide();
					}
					
				}
			});
		})
	</script>
</body>
</html>