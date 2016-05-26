<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /global PC header begin-->
<!-- <script type="text/javascript">
	$(function(){
		 headerFunc();
	})
	function headerFunc(){
		$("#nav-other").hover(function(){
			$("#nav-sub-wrap").show();
		},function(){
			$("#nav-sub-wrap").hide();
		})
	}
</script> -->
<header id="header">
	<section class="container">
		<h1 id="logo">
			<a href="/" title="xxx公司">
				<img src="<%=staticImageServer%>/${logomap.logo.url}" width="100%" alt="xxx公司">
			</a>
		</h1>
		<div class="h-r-nsl">
			<ul class="nav" id="guideInfo">
				<c:set var="contextPath" value="<%=request.getRequestURI()%>"/>
				<c:forEach items="${navigatemap.INDEX}" var="indexNavigate" begin="0" end="5">
					<li <c:if test="${indexNavigate.url==contextPath}">class="current"</c:if> ><a class="nav-fir" href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
				</c:forEach>
				<li class="pr" id="nav-other">
					<a href="javascript:void(0)" title="" class="nav-fir nav-other"><tt class="mr5">其它</tt><b class="caret"></b></a>
					<div class="fn-sub-wrap fn-sub nav-sub-wrap" id="nav-sub-wrap">
						<dl class="clearfix">
							<dd>
								<c:forEach items="${navigatemap.INDEX}" var="indexNavigate" begin="6">
									<a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a>
								</c:forEach>
						   </dd>
						</dl>
					</div>
				</li>
			</ul>
			<!-- / nav -->
			<ul class="h-r-login">
				<c:if test="${ empty loginUser }">
				<li class="">
					<a href="${ctx}/login" title="登录">
						<span class="vam ml5">登录</span>
					</a>
					<a href="${ctx}/register" title="注册">
						<span class="vam ml5">注册</span>
					</a>
				</li>
				</c:if>
				<c:if test="${not empty loginUser }">
				<li class="mr10 pr" id="is-login-one">
					<a class="u-login-box pr" href="/uc/home">
					<c:if test="${empty loginUser.avatar}">
						<img  src="${ctximg}/static/nxb/web/img/avatar-boy.gif" width="30" height="30" >
					</c:if>
					<c:if test="${not empty loginUser.avatar}">
						<c:if test="${fn:contains(loginUser.avatar,'http:')}">
						<img  src="${loginUser.avatar}" width="30" height="30" >
						</c:if>
						<c:if test="${fn:contains(loginUser.avatar,'http:')==false}">
						<img  src="${staticUrl}${loginUser.avatar}" width="30" height="30" >
						</c:if>
					</c:if>
						<q class="red-point msg-red" style="display: none">&nbsp;</q>
					</a>
					<em class="icon12 l-em-arrow ml10"></em>
					<section class="fn-sub-box undis"> 
						<div class="fn-sub-wrap fn-sub"> 
							<dl class="clearfix"> 
								<dd> 
									<a title="个人设置" href="${ctx}/uc/home">个人主页</a> 
									<a title="我的计划" href="${ctx}/uc/planRecordList">学习计划</a>
									<a title="我的课程" href="${ctx}/uc/course">我的课程</a> 
									<a title="我的消息" href="${ctx}/uc/letter" class="pr">我的消息<q class="red-point msg-red">&nbsp;</q></a>
								    <a title="" href="javascript:void(0)" onclick="exit()">退出</a> 
								 </dd> 
							</dl> 
						</div> 
					</section>
				</li>
				</c:if>
			</ul>
			<aside class="h-r-search">
				<form action="${ctx}/front/showCourseList" method="post" id="searchCourse">
					<label class="h-r-s-box"><input type="text" id="searchCourseName" placeholder="输入你想学的课程" name="queryCourse.name" value="">
						<button type="submit" class="s-btn">
							<em class="icon18">&nbsp;</em>
						</button></label>
				</form>
			</aside> 
		</div>
		<aside class="mw-nav-btn">
			<div class="mw-nav-icon"></div>
		</aside>
		<div class="clear"></div>
	</section>
</header>
<!-- /global PC header end-->
<!-- /global MOBILE header begin-->
<div class="h-mobile-mask"></div>
<div class="head-mobile">
	<div class="head-mobile-box">
		<c:if test="${not empty loginUser }">
		<section class="clearfix">
			<div class="u-face-pic">
			<c:if test="${not empty loginUser.avatar}">
				<c:if test="${fn:contains(loginUser.avatar,'http:')}">
				<img src="${loginUser.avatar}" alt="" class="userImgPhoto">
				</c:if>
				<c:if test="${fn:contains(loginUser.avatar,'http:')==false}">
				<img src="${staticUrl}${loginUser.avatar}" alt="" class="userImgPhoto">
				</c:if>
			</c:if>
			<c:if test="${ empty loginUser.avatar}">
				<img src="${ctximg}/static/nxb/web/img/avatar-boy.gif" alt="" class="userImgPhoto">
				</c:if>
				<a href="${ctx}/uc/avatar" title="" class="c-fff">修改头象</a>
			</div>
			<h4 class="hLh30 txtOf">
				<span class="fsize16 c-999 userNameClass ">
					<span onclick="window.location.href='${ctx}/uc/home'">${loginUser.showname}</span>
				</span>
			</h4>
			<div class="hLh30">
				<%-- <a href="${ctx}/shopcart?command=queryShopCart" title="购物车" class="c-999 shopcar-btn">购物车</a> --%>
				<a onclick="window.location.href='${ctx}/uc/home'" title="个人中心" class="c-999  m-u-center">个人中心</a>
			</div>
		</section>
		</c:if>
		<c:if test="${empty loginUser }">
		<section class="clearfix">
			<h4 class="hLh30 txtOf">
				<span class="fsize16 c-999 userNameClass ">
					<span class="" onclick="location.href='${ctx}/login'">登录</span>
					<span class="" onclick="location.href='${ctx}/register'">注册</span>
				</span>
			</h4>
		</section>
		</c:if>
		<nav class="mw-nav">
			<ul class="clearfix">
				<c:set var="contextPath" value="<%=request.getRequestURI()%>"/>
				<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
      					<li <c:if test="${indexNavigate.url==contextPath}">class="current"</c:if>><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
				</c:forEach>
			</ul>
		</nav> 
		<c:if test="${not empty loginUser }">
		<section class="u-m-dd">
			<ul>
				<li>
					<span>我的课程</span>
					<ol>
						<li class="current"><a href="${ctx}/uc/course">我的课程</a></li>
						<li><a href="${ctx}/uc/study">学习历史</a></li>
						<li><a href="${ctx}/uc/fav">我的收藏</a></li>
						<li><a href="${ctx}/uc/note">我的笔记</a></li>
						<li><a href="${ctx}/uc/mylive">我的直播</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>培训学习</span>
					<ol>
						<li><a href="${ctx}/uc/myArrangeExam">我的测评</a></li>
						<li><a href="${ctx}/uc/planRecordList">我的计划</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>互动问答</span>
					<ol>
						<li><a href="${ctx}/uc/myCouAnswer">课程答疑</a></li>
						<li><a href="${ctx}/uc/ques/my">我的问答</a></li>
						<li><a target="_blank" href="${ctx}/front/articlelist/notice">网站公告</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>积分与账单</span>
					<ol>
						<li><a href="${ctx}/uc/order">课程订单</a></li>
						<li><a href="${ctx}/uc/cash/order">充值订单</a></li>
						<li><a href="${ctx}/uc/address">收货地址</a></li>
						<li><a href="${ctx}/uc/myinte">我的积分</a></li>
						<li><a href="${ctx}/uc/integift">积分兑换</a></li>
					</ol>
				</li>
			</ul>
			<ul>
				<li>
					<span>账号管理</span>
					<ol>
						<li><a href="${ctx}/uc/acc" title="我的账户">我的账户</a></li>
						<li><a href="${ctx}/uc/member" title="我的会员">我的会员</a></li>
						<li><a href="${ctx}/uc/card" title="课程卡">课程卡</a></li>
						<li><a href="${ctx}/uc/uinfo" title="个人设置">个人设置</a></li>
						<li><a href="${ctx}/uc/myProfile" title="第三方账号">第三方账号</a></li>
						<li><a href="${ctx}/uc/letter" title="系统消息">系统消息</a></li>
					</ol>
				</li>
			</ul>
			<div id="mobileExitDiv" class="mt15 undis tac" style="display: block;"> 
				<a href="javascript:void(0)" title="退出" onclick="exit();" class="u-loginout">退出登录</a> 
			</div>
		</section>
		</c:if>
	</div>
</div>
<!-- /global MOBILE header end-->
