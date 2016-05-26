<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>他的关注</title>
<script src="${ctximg}/static/sns/js/friend/friend.js?v=<%=version%>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript"
	src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
</head>
<body class="W-body">
	<!-- 主体 开始-->
			<section class="W-main-c fl">
				<div class="W-main-cc">
					<section class="W-main-center W-main-c-r">
						<div class="pl20">
							<section class="mt10 pr20">
								<header class="comm-title-1">
									<ul class="clearfix">
										<!-- <li class="myattention"><a title="我的关注"
											href="javascript:myattention()">我的关注</a>
											</li>
											<li class="myfans"><a title="我的学友"
											href="javascript:myfans()">我的学友</a>
											</li>
											<li class="myblack"><a title="我的黑名单"
											href="javascript:myblack()">我的黑名单</a>
											</li> -->
											<li class="visitor"><a title="最近访客"
											href="javascript:void(0)">最近访客</a>
											</li>
									</ul>
								</header>
							</section>
							<section class="myF-list-box clearfix">
							<c:if test="${ empty visitorList}">
	<div class="Prompt">
		<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
		<p class="vam c-555 fsize14 disIb">他还没有访客呢</p>
	</div>
</c:if>
<c:forEach items="${visitorList}" var="cusList">
	<article class="myF-l-box" id="del${cusList.cusId }">
		<div class="F-face">
			<a href="${ctx}/p/${cusList.cusId}/home"> <c:if
					test="${cusList.userExpandDto.avatar!=null&&cusList.userExpandDto.avatar!='' }">
					<img src="<%=staticImageServer%>${cusList.userExpandDto.avatar }"
						width="50" height="50" alt="">
				</c:if> <c:if
					test="${cusList.userExpandDto.avatar==null||cusList.userExpandDto.avatar=='' }">
					<img src="${ctximg}/static/sns/pics/user.jpg" width="50"
						height="50" alt="">
				</c:if>
			</a>
		</div>
		<ul class="F-info">
			<li><a href="${ctx}/p/${cusList.cusId}/home"
				class="c-blue customerName${cusList.cusId}">
					${cusList.userExpandDto.showname} </a></li>
			<li class="of"><span class="c-888"> </span></li>
			<li><span class="c-888"></span><span class="c-888 ml10"></span></li>
		</ul>
		<div class="F-intor" title="<fmt:formatDate type="both" value="${cusList.addTime}" />">
			访问时间：
			${cusList.modelStr}
		</div>
		<div class="clearfix mt5">
			<span class="fr"> <a href="javascript:void(0)" title="发消息"
				onclick="addLetterInput('${cusList.userExpandDto.showname }',${cusList.cusId },this)"
				class="F-fxx sendmessage${cusList.cusId }"><i class="icon12 mr5">&nbsp;</i>发消息</a>
				<c:if test="${empty cusList.friendId||cusList.friendId==0 }">
					<a href="javascript:void(0)" title="加关注"
						onclick="addFriend(${cusList.visitorCusId })" class="F-add"><i
						class="icon12 mr5">&nbsp;</i>加关注</a>
				</c:if></span>
		</div>
	</article>
</c:forEach>
							</section>
							<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
						</div>
					</section>
				</div>
			</section>
</body>
