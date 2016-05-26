<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
<title>${blogBlog.title}</title>
<script type="text/javascript"
	src="${ctximg}/static/sns/js/blog/blog.js?v=<%=version%>"></script>
<script type="text/javascript"
	src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script type="text/javascript">
	$(function(){
		goTop();
		var blogId ='${blogBlog.id}';
		queryBlogReplyList(blogId,1);
	});
	</script>
</head>
<body class="W-body">
	<!-- 主体 开始-->
			<section class="W-main-c fl">
				<div class="W-main-cc" style="overflow: visible;">
					<section class="W-main-center" style="overflow: visible;">
						<div class="pl20 pr10">
							
							<section class="mt20 pl10">
								<h3 class="boke-title of" style="border-bottom: 1px solid #ccc;padding-bottom: 5px;">
									<span class="fr"><a href="javascript:history.go(-1)" class="c-888 fsize12 unFb">返回&gt;&gt;</a></span>
									<c:if test="${blogBlog.selType==1}">
									<tt class="icon22 yuan">&nbsp;</tt>
									</c:if>
									<c:if test="${blogBlog.selType==2 }">
									<tt class="icon22 zhuan">&nbsp;</tt>
									</c:if><span class="fsize18 c-blue ml5">${blogBlog.title}</span>
									
								</h3>
								 <!-- <section class="mt10">
									<section class="comm-title-3">
										<span class="c-t-2-l"><tt class="c-red">正文</tt></span>
										<div class="c-t-line">&nbsp;</div>
									</section>
								</section>  -->
								<div class="mt10 of">
									<span class="fr"><tt class="c-bbb">
											<fmt:formatDate type="both" value="${blogBlog.addTime }" ></fmt:formatDate>
										</tt></span> <span class="c-bbb">所属分类：</span><span><a
										href="${ctx}/blog/art/${blogBlog.type}" title=""
										class="c-orange">${blogBlog.articleName}</a></span>
								</div>
								<div class="boke-text normalLi mt20 yinyongcontent">
									${blogBlog.content}</div>
								<div class="mt10 pt20 clearfix grayLine1">
									<%-- <div class="fr">
										<span><a href="javascript:void(0)" title=""
										class="comm-btn-orange" onclick="pinglun(${blogBlog.id})"><span
											style="padding: 3px 30px; font-size: 16px;">我要评论</span></a></span>
									</div> --%>
									<div class="fl">
										<span class="c-555">阅读</span> <span class="c-888">(${blogBlog.viewCount
											})</span> <em class="SG_txtb tal">┊</em> <span class="c-555">评论</span>
										<span class="c-888 replyCount">(${blogBlog.replyCount
											})</span>
									</div>
								</div>
								<section class="mt10 clearfix">
									<div class="fr">
										<span><a href="javascript:void(0)" title=""
										class="comm-btn-orange" onclick="pinglun(${blogBlog.id})"><span
											style="padding: 3px 30px; font-size: 16px;">我要评论</span></a></span>
									</div>
									<div style="height: 22px;" class="fl">
										<div class="bdsharebuttonbox bdshare-button-style0-16" data-bd-bind="1400133119445"><a data-cmd="more" class="bds_more" href="#"></a><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="t163" class="bds_t163" href="#" title="分享到网易微博"></a></div>
										<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","t163"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","t163"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=86835285.js?cdnversion='+~(-new Date()/36e5)];</script>
									</div>
									<!-- <span class="c-bbb">(此文章只代表作者个人观点,不代表网站的观点)</span> -->
									
								</section>
								<!-- 评论列表 开始 -->
								<div class="replyList">
									<br> <br>
									<!-- 公共分页 开始 -->
									<!-- 公共分页 结束 -->
								</div>
								<!-- /评论列表 结束 -->
							</section>
						</div>

					</section>
					<section class="W-main-right">
						<div class="pl20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l"><tt class="c-blue">最近浏览者</tt>
										<tt class="c-bbb">(${visitNum})</tt></span>
								</section>
								<section class="mt20 pr10">
									<div class="visitor-list mt20">
										<ul class="clearfix">
											<c:forEach items="${blogLookArticleList }" var="blogLook">
												<li>
												<c:if test="${blogLook.avatar==null||blogLook.avatar==''}">
												<a href="${ctx}/p/${blogLook.cusId}/home" title="" class="c-blue"> <img
														src="${ctximg}/static/sns/pics/user.jpg" height="50"
														width="50" alt="">
														<c:if test="${blogLook.showName==null||blogLook.showName=='' }">
														<p class="mt5">&nbsp;</p>
														</c:if>
														<c:if test="${blogLook.showName!=null||blogLook.showName!='' }">
														<p class="mb10 fsize14">${blogLook.showName }</p>
														<span class="c-bbb"><fmt:formatDate pattern="MM-dd HH:mm"
																value="${blogLook.addTime}" ></fmt:formatDate></span>
														</c:if>
												</a>
												</c:if>
												<c:if test="${blogLook.avatar!=null &&blogLook.avatar!=''}">
												<a href="${ctx}/p/${blogLook.cusId}/home" title="" class="c-blue"> <img
														src="<%=staticImageServer%>${blogLook.avatar}" height="50"
														width="50" alt="">
														<p class="mb10 fsize14">${blogLook.showName }</p>
														<span class="c-bbb"><fmt:formatDate pattern="MM-dd HH:mm"
																value="${blogLook.addTime}" ></fmt:formatDate></span>
												</a>
												</c:if></li>
											</c:forEach>
										</ul>
									</div>
								</section>
							</div>
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l">博文分类</span>
								</section>
								<section class="mt20 pr10">
									<ul class="boke-sort-list">
										<c:forEach items="${artClassifyList }" var="art">
											<li><a href="${ctx}/blog/art/${art.artId}"
												title="" class="fsize14 c-orange">${art.name }</a><span
												class="c-green">（${art.blogNum }）</span></li>
										</c:forEach>
									</ul>
								</section>
							</div>
						</div>
					</section>
				</div>
			</section>
			<!-- 主体区域 -->
</body>
</html>