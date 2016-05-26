<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>第三方账户绑定</title>

</head>
<body>
	<article class="u-m-c-w837 u-m-center">
		<section class="u-m-c-wrap">
			<header class="uc-com-title"> <span>第三方账户绑定</span> </header>
			<div class="i-box">
				<div class="u-m-c-wrap">
					<div class="Binding">
						<p class="fsize14 f-fM c-orange hLh30 tac">绑定第三方账号，可以直接登录，还可以将内容同步到以下平台，与更多好友分享。</p>
						<ul class="Binding-list clearfix">
							<li class="Binding-qq">
								<c:choose>
									<c:when test="${not empty userProfileQQ.id}">
										<div class="current"> <!-- 已绑定的话就是这个位置加 class="current" -->
											<span class="img">&nbsp;</span>
											<p class="fsize14 f-fM c-333 tac mt20"> 已绑定QQ</p>
											<a class="ljbd-btn mt20" href="javascript:void(0)" onclick="exclideBungingConfirm('QQ',${userProfileQQ.id})">解绑</a>
										</div>
									</c:when>
									<c:otherwise>
										<div>
											<span class="img">&nbsp;</span>
											<p class="fsize14 f-fM c-333 tac mt20"> 未绑定QQ</p>
											<a class="ljbd-btn mt20" href="javascript:oauthLogin('QQ')">立即绑定</a>
										</div>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="Binding-xl">
								<c:choose>
									<c:when test="${not empty userProfileSina.id}">
										<div class="current"> <!-- 已绑定的话就是这个位置加 class="current" -->
											<span class="img">&nbsp;</span>
											<p class="fsize14 f-fM c-333 tac mt20"> 已绑定新浪</p>
											<a class="ljbd-btn mt20" href="javascript:void(0)" onclick="exclideBungingConfirm('SINA',${userProfileSina.id})">解绑</a>
										</div>
									</c:when>
									<c:otherwise>
										<div>
											<span class="img">&nbsp;</span>
											<p class="fsize14 f-fM c-333 tac mt20"> 未绑定新浪</p>
											<a class="ljbd-btn mt20" href="javascript:oauthLogin('SINA')">立即绑定</a>
										</div>
									</c:otherwise>
								</c:choose>
							</li>
							<li class="Binding-wx">
								<c:choose>
									<c:when test="${not empty userProfileWeiXin.id}">
										<div class="current"> <!-- 已绑定的话就是这个位置加 class="current" -->
											<span class="img">&nbsp;</span>
											<p class="fsize14 f-fM c-333 tac mt20"> 已绑定微信</p>
											<a class="ljbd-btn mt20" href="javascript:void(0)" onclick="exclideBungingConfirm('WEIXIN',${userProfileWeiXin.id})">解绑</a>
										</div>
									</c:when>
									<c:otherwise>
										<div>
											<span class="img">&nbsp;</span>
											<p class="fsize14 f-fM c-333 tac mt20"> 未绑定微信</p>
											<a class="ljbd-btn mt20" href="javascript:oauthLogin('WEIXIN')">立即绑定</a>
										</div>
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</article>
	<script src="${ctx}/static/edu/js/ucenter/u_profile.js"></script>
</body>
</html>
