<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="o-topBar">
<div class="container-i">
			<div class="otb-li">
				<ol class="fr">
					<li class="isLogin" style="display:none;">
						<a class="cusImg" href="/uc/home">
						<img width="30" height="30" class="vam" id="cusImg" src=""/>
						<tt class="fsize14 cusName vam"></tt>
						</a>
					</li>
					<li class="isLogin" style="display:none;"><a href="/letter">消息</a></li>
					<li class="isLogin" style="display:none;"><a href="javascript:void(0)" onclick="exit()">退出</a></li>
					<li class="notLogin"><a href="/login" target="_blank">登录</a></li>
					<li class="notLogin"><a href="/register" target="_blank">注册</a></li>
					<li class="notLogin"><a href="/front/forget_passwd" target="_blank">忘记密码?</a></li>
					<li class="line-share">
						<a href="" target="_blank" class="t-share"><em class="icon14 mr5">&nbsp;</em>分享关注</a>
						<aside class="t-link-sub tShareBox">
							<div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a><a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a><a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a></div>
							<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
						</aside>
					</li>
				</ol>
				<ul class="fl">
					<li><a href="/" target="_blank">理臣官网</a></li>
					<li><a href="/index" target="_blank">理臣网校</a></li>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</section>
	<!-- header begin -->
	<header class="header">
		<div class="container-i cms-header">
			<div class="h-i-l o-h-i-l"> 
				<h1 class="logo-2014 of fl"> 
					<a target="_blank" title="理臣教育" class="fl" href="/">
						<img width="255" height="74" class="dis" src="${staticServer}/static/edu/images/lc_official_line/logo-blue.png">
					</a> 
					<span class="ml15 fl mt30">
						<img width="142" height="24" class="dis" src="${staticServer}/static/edu/images/lc_official_line/slogan-blue.png">
					</span> 
				</h1>
				<div class="clear"></div>
			</div>
			<div class="h-i-r"> 
				<section class="mt50 of o-h-i-r"> 
					<div class="t-search-i fl"> 
						<label class="clearfix"> 
							<input type="text" id="search" placeholder="search..."> 
							<a class="tscBtn" onclick="searchArticle()" href="javascript:void(0);">
							<em class="icon14">&nbsp;</em>
							</a> 
						</label> 
					</div>
					<div class="fr">
						<section class="cms-tel">
							<em class="icon30">&nbsp;</em>
							<span class="ml5 vam fsize18 f-fM">${websitemap.web.phone}</span>
						</section>
					</div>
				</section> 
			</div>
			<div class="clear"></div>
		</div>
	</header>
	<!-- header end -->
	<!-- nav begin -->
	<div class="container-i">
		<nav class="cms-nav clearfix">
			<c:forEach items="${showTypeList}" var="showType" varStatus="index">
				<c:if test="${index.index<2}">
				<dl class="CN-1">
					<dt><a href="/cms/news/list?queryArticle.typeId=${showType.parent.typeId}">${showType.parent.typeName}：</a></dt>
					<dd>
						<c:forEach items="${showType.childList}" var="child">
						<a href="/cms/news/list?queryArticle.typeId=${child.typeId}">${child.typeName}</a>
						</c:forEach>
					</dd>
				</dl>
				</c:if>
				<c:if test="${index.index>1}">
				<dl class="CN-${index.index}">
					<dt><a href="/cms/news/list?queryArticle.typeId=${showType.parent.typeId}">${showType.parent.typeName}：</a></dt>
					<dd>
						<c:forEach items="${showType.childList}" var="child">
						<a href="/cms/news/list?queryArticle.typeId=${child.typeId}">${child.typeName}</a>
						</c:forEach>
					</dd>
				</dl>
				</c:if>
			</c:forEach>
			<dl class="CN-5">
				<dt><a href="/cms/aboutus">关于我们：</a></dt>
				<dd>
					<c:forEach items="${helpMenus}" var="hel">
					<a href="/cms/aboutus?id=${hel[0].id}">${hel[0].name}</a>
					</c:forEach>
					<a href="/official/join">加盟理臣</a>
				</dd>
			</dl>
		</nav>
	</div>
	<!-- nav end -->