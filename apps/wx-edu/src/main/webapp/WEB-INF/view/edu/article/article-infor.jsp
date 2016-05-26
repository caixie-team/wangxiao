<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${article.title}</title>
</head>
<body>
<div class="bg-fa of">
<!-- /课程列表 开始 -->
<section class="container">
	<section class="path-wrap txtOf hLh30"> 
		<a class="c-999 fsize14" title="首页" href="${ctx}">首页</a> \ 
		<a class="c-999 fsize14" title="资讯列表" href="${ctx}/front/articlelist/${article.type}">资讯列表</a> \
		<span class="c-333 fsize14">${article.title}</span> 
	</section>
	<div class="clearfix">
		<div class="fl col-75">
			<div class="mr20 mb20">
				<div class="i-box">
					<article>
						<div class="article_title">
							<h1 class="txtOf">${article.title}</h1>
							<section class="hLh30 txtOf mt10 mb10 a-list-extrainfo"> 
								<div class="tac">
									<span title="浏览数" class="c-999 vam disIb">
										<em class="icon14 q-view mr5 vam"></em><tt class="vam f-fM">${article.clickTimes}</tt>
									</span> 
									<%-- <span title="浏览数" class="disIb c-999 ml15">
										<em class="icon14 q-review mr5"></em> <tt class="vam f-fM">${article.title}</tt>
									</span> --%>
									<tt class="c-999 f-fM ml15 vam"><fmt:formatDate value="${article.createTime}" type="both"/></tt>
								</div> 
							</section> 
						</div>
						<div id="art-infor-body">
							${article.content}
						</div>
					</article>
				    <section id="sharebutton" class="mt20 tac"> 
					    <div class="bdsharebuttonbox clearfix disIb">
					    	<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a><a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a><a title="分享到QQ空间" href="#" class="bds_qzone zan-icon" data-cmd="qzone"></a>
					    </div>
						<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"share":{},"image":{"viewList":["weixin","tsina","qzone"],"viewText":"分享到：","viewSize":"32"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["weixin","tsina","qzone"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
						</section>
					</div>
					<c:if test="${upArticle!=null or downArticle!=null }">
					<div class="mt20">
						<div class="i-box1 mb20">
							<header class="com-title-box">
								<span class="fsize18 c-333">文章推荐</span>
							</header>
							<article class="i-article-list mt30 pl20 pr20">
							<ul>
								<c:if test="${not empty upArticle }">
								<li> 
									<aside class="i-article-pic"> 
										<c:if test="${upArticle.picture!=null && upArticle.picture!=''}">
											<img alt="${upArticle.title}" src="<%=staticImageServer%>${upArticle.picture}"/>
										</c:if>
									</aside> 
									<h3 class="hLh30 txtOf"> 
										<a class="i-art-title" title="" href="${ctx}/front/toArticle/${upArticle.id}">${upArticle.title}</a> 
									</h3> 
									<section class="i-q-txt mt5 i-q-txt2"> 
										<p> 
											<span class="c-999 f-fA">${upArticle.description}</span> 
										</p> 
									</section> 
									<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo"> 
										<div class="fr">
											<%-- <span title="浏览数" class="c-999 vam disIb">
												<em class="icon14 q-view mr5 vam"></em><tt class="vam f-fM">${upArticle.clickTimes}</tt>
											</span>  --%>
											<span title="浏览数" class="disIb c-999 ml15 vam">
												<em class="icon14 q-view mr5"></em> <tt class="vam f-fM">${upArticle.clickTimes}</tt>
											</span>
											<tt class="c-999 f-fM ml15 vam"><fmt:formatDate value="${upArticle.updateTime }"  pattern="yyyy-MM-dd HH:mm"/></tt>
										</div> 
									</section> 
								</li>
								</c:if>
								<c:if test="${not empty downArticle }">
								<li> 
									<aside class="i-article-pic"> 
										<c:if test="${downArticle.picture!=null && downArticle.picture!=''}">
											<img alt="${downArticle.title}" src="<%=staticImageServer%>${downArticle.picture}"/>
										</c:if>
									</aside> 
									<h3 class="hLh30 txtOf"> 
										<a class="i-art-title" title="" href="${ctx}/front/toArticle/${downArticle.id}">${downArticle.title}</a> 
									</h3> 
									<section class="i-q-txt mt5 i-q-txt2"> 
										<p> 
											<span class="c-999 f-fA">${downArticle.description}</span> 
										</p> 
									</section> 
									<section class="hLh30 txtOf mt5 pr10 a-list-extrainfo"> 
										<div class="fr">
											<%-- <span title="浏览数" class="c-999 vam disIb">
												<em class="icon14 q-view mr5 vam"></em><tt class="vam f-fM">${downArticle.clickTimes}</tt>
											</span>  --%>
											<span title="浏览数" class="disIb c-999 ml15 vam">
												<em class="icon14 q-view mr5"></em> <tt class="vam f-fM">${downArticle.clickTimes}</tt>
											</span>
											<tt class="c-999 f-fM ml15 vam"><fmt:formatDate value="${downArticle.updateTime }"  pattern="yyyy-MM-dd HH:mm"/></tt>
										</div> 
									</section> 
								</li>
								</c:if>
								
							</ul>
						</article>
					    </div>
					</div>
					</c:if>
				</div>
			</div>
			<div class="fr col-25">
				<div class="i-box1 mb20">
					<header class="com-title-box">
						<span class="fsize18 c-333">点击排行</span>
					</header>
					<ul class="ar-rank-list">
						<c:forEach items="${articleListOrderclickTimes}" var="circuitRanking" varStatus="index">
							<li>
								<tt class="ord"><font class="f-fM fsize12">${index.index+1}</font></tt>
								<p class="txtOf"><a href="${ctx}/front/toArticle/${circuitRanking.id}" title="${circuitRanking.title}">${circuitRanking.title}</a></p>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</section>
</div>
<script type="text/javascript">
$(function (){
	aRankFun();//点击排名的前三个样式
});
function aRankFun(){
	$(".ar-rank-list>li .ord:eq(0)").addClass("ord-fir");
	$(".ar-rank-list>li .ord:eq(1)").addClass("ord-sec");
	$(".ar-rank-list>li .ord:eq(2)").addClass("ord-thi");
}
</script>
</body>
</html>
