<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的积分</title>
</head>
<body>
	<div>
		<article>
			<header class="uc-com-title">
				<span>我的积分</span>
			</header>
			<div class="i-box">
				<div class="p-20-15">
					<section class="mt20 pb20 line2">
						<h4 class="hLh30">
							<span class="c-333 fsize14 f-bold">我的积分：</span> <strong
								class="c-red f-fG fsize24">${userIntegral.currentScore==null?0:userIntegral.currentScore}</strong> <a
								class="ml50 c-red fsize12 unFw" title="我的礼品" href="${ctx}/uc/mygift"> <u>我的礼品&gt;&gt;</u>
							</a>
						</h4>
					</section>
					<section class="inteRecord mt30">
					
					</section>
					<section class="mt20">
						<h5 class="c-333 fsize14 f-bold">我的积分有什么用途？</h5>
						<p class="c-666 mt5 f-int-desc">
							您的积分可以通过 <a class="c-red" title="" href="${ctx}/uc/integift"> <u>兑换礼品</u></a>来换取相应奖品，
							现在就通过在快乐的学习过程中去获取积分吧！
						</p>
					</section>
					<section class="mt20">
						<h5 class="c-333 fsize14 f-bold">我都可以通过哪些操作来获得积分？</h5>
						<c:if test="${empty userIntegralTemplateList }">
							<div class="mt40">
								<!-- /无数据提示 开始-->
								<section class="no-data-wrap">
									<em class="no-data-ico">&nbsp;</em> <span
										class="c-666 fsize14 ml10 vam">您还没有积分</span>
								</section>
								<!-- /无数据提示 结束-->
							</div>
						</c:if>
						
						<c:if test="${not empty userIntegralTemplateList }">
						<table class="tab-integral mt10" width="100%" cellspacing="0"
							cellpadding="0" border="0">
							<thead>
								<tr>
									<th width="40%">以下操作来获得积分</th>
									<th width="30%">获得积分</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach items="${userIntegralTemplateList}" var="integralTemplate">
								<!-- 只显示状态为正常的模板 -->
								<c:if test="${integralTemplate.status==0}">
								<tr>
									<td>${integralTemplate.name}</td>
									<td>${integralTemplate.showScore}</td>
								</tr>
								</c:if>
							</c:forEach>
							</tbody>
						</table>
						</c:if>
					</section>
				</div>
			</div>
		</article>
	</div>
	<script>
		$(function() {
			ajaxPage("/uc/ajax/intrecord", "", 1, integralRecord);
		});
		function integralRecord(result) {
			$(".inteRecord").html(result);
		}
	</script>
</body>
</html>