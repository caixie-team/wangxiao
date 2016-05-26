<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>${disArticle.title}</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script type="text/javascript">
	var is="${isJoin}";
	$(function(){
		goTop();
		ajaxPage("/dis/ajax/reply","&groupId="+'${disArticle.groupId}'+"&articleId="+'${disArticle.id}'+"&status="+'${disArticle.status }',1,callback);
	});
	function callback(result){
		$(".disArticleReplyList").html(result);
	}
	</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
			<section class="W-main-c fl">
				<div class="W-main-cc" style="overflow: visible;">
					<section class="W-main-center" style="overflow: visible;">
						<div class="pl20 pr10">
							<!-- <section class="mt10">
								<section class="comm-title-3">
									<span class="c-t-2-l"><tt class="c-red">正文</tt></span>
									<div class="c-t-line">&nbsp;</div>
								</section>
							</section> -->
							<section class="mt10 pl10">
								<h3 class="boke-title" style="border-bottom: 1px solid #5FBAB5;padding-bottom: 5px;">
								<span class="fr"><a href="${ctx}/dis/art/${disArticle.groupId}" class="c-888 fsize12 unFb">返回&gt;&gt;</a></span>
										<c:if test="${disArticle.selType==0 }">
									<!-- <tt class="icon22 yuan">&nbsp;</tt> -->
									</c:if>
									<c:if test="${disArticle.selType==1 }">
									<!-- <tt class="icon22 zhuan">&nbsp;</tt> -->
									</c:if><span class="fsize18 c-green ml5">
									<c:if test="${disArticle.selType==1 }">
									(线下活动)
									</c:if>
									${disArticle.title}
									</span>
								</h3>
								<div class="mt10 of">
									<span class="fr"><tt class="c-bbb">
											<fmt:formatDate type="both" value="${disArticle.createTime }" ></fmt:formatDate>
										</tt></span> <span class="c-bbb">所属分类：</span><span><a href="${ctx}/dis/info/${disArticle.groupId}"
										title="${disArticle.disname}" class="c-orange">${disArticle.disname}</a></span>&nbsp;&nbsp;
										</span><%-- <span><a href="${ctx}/dis/classifyart/${disArticle.artClassifyId}"
										title="${disArticle.classifyName}" class="c-orange">${disArticle.classifyName}</a></span> --%>
								</div>
								<div class="boke-text normalLi mt20 discontent">
									${disArticle.content}</div>
								<div class="mt20 pt20 clearfix grayLine1">
									<div class="fr" style="height: 22px;">
										<!-- <div class="bdsharebuttonbox">
											<a href="#" class="bds_more" data-cmd="more"></a><a href="#"
												class="bds_qzone" data-cmd="qzone"></a><a href="#"
												class="bds_tsina" data-cmd="tsina"></a><a href="#"
												class="bds_tqq" data-cmd="tqq"></a><a href="#"
												class="bds_renren" data-cmd="renren"></a><a href="#"
												class="bds_t163" data-cmd="t163"></a>
										</div> -->
									</div>
									<div class="fl">
										<span class="c-555">阅读</span> <span class="c-888">(${disArticle.countView})</span>
										<em class="SG_txtb tal">┊</em> <span
											class="c-555" >评论</span> <span class="c-888 replyCount">(${disArticle.reNum})</span>
											<em class="SG_txtb tal">┊</em><span
											class="c-555" ><a class="c-blue" title="推荐该话题" href="javascript:recommend(${disArticle.id })">推荐</a></span> <span class="c-888 recommended">(${disArticle.recomCount})</span>
											<em class="SG_txtb tal">┊</em>
										<span class="c-555" >
										<c:if test="${flag==0 }">
										<c:choose>
										<c:when test="${disArticle.selType==0}">
										<a class="c-blue Liketitle" title="标为喜欢?" href="javascript:addLike(${disArticle.id },this)">喜欢</a>
										</c:when>
										<c:otherwise>
										<a class="c-blue Liketitle" title="标为参加?" href="javascript:addLike(${disArticle.id },this)">参加</a>
										</c:otherwise>
										</c:choose>
										
										</c:if>
										<c:if test="${flag!=0 }">
										<c:choose>
										<c:when test="${disArticle.selType==0}">
										<a class="c-blue Liketitle" title="取消喜欢?" href="javascript:addLike(${disArticle.id },this)">喜欢</a>
										</c:when>
										<c:otherwise>
										<a class="c-blue Liketitle" title="取消参加?" href="javascript:addLike(${disArticle.id },this)">参加</a>
										</c:otherwise>
										</c:choose>
										
										</c:if>
										</span><span class="c-888 like">(${disArticle.favorCount})</span>
									</div>
								</div>
								<section class="mt20 clearfix">
								<!--  <span class="c-bbb">(此话题只代表作者个人观点,不代表网站的观点)</span> -->
									<span class="fr"><a href="javascript:void(0)" title=""
										class="comm-btn-orange"
										onclick="isJoin(${disArticle.id},${disArticle.groupId },${isJoin},${disArticle.status})"><span
											style="padding: 3px 30px; font-size: 16px;">我要评论</span></a></span>
											<div style="height: 22px;" class="fl">
										<div class="bdsharebuttonbox bdshare-button-style0-16" data-bd-bind="1400133119445"><a data-cmd="more" class="bds_more" href="#"></a><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="t163" class="bds_t163" href="#" title="分享到网易微博"></a></div>
										<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","t163"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","t163"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=86835285.js?cdnversion='+~(-new Date()/36e5)];</script>
									</div>
								</section>
								<input type="hidden" id="disgroupid" value="${disArticle.groupId}">
								<input type="hidden" id="disArticleid" value="${disArticle.id}">
								<input type="hidden" id="disStatus" value="${disArticle.status }">
								<div class="disArticleReplyList">
									 <br><br>
								</div>
								<!-- /评论列表 结束 -->
							</section>
						</div>

					</section>
					<section class="W-main-right">
						<div class="pl20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l"><tt class="c-green">最近浏览者</tt> <tt
											class="c-bbb">(${visitNum })</tt></span>
								</section>
								<section class="mt20 pr10">
									<div class="visitor-list mt20">
										<ul class="clearfix">
											<c:forEach items="${disLookArticleList }" var="dl">
												<li><a href="${ctx}/p/${dl.cusId}/home" title="" class="c-blue">
												
												 <c:if test="${dl.avatar==null||dl.avatar==''}"> <img
														src="${ctximg}/static/sns/pics/user.jpg" height="40"
														width="40" alt=""></c:if>
												 <c:if test="${dl.avatar!=null&&dl.avatar!=''}"> <img
														src="<%=staticImageServer%>${dl.avatar}" height="40"
														width="40" alt=""></c:if>
														<c:if test="${dl.showName==null||dl.showName=='' }">
														<p class="mt5">&nbsp;</p>
														</c:if>
														<c:if test="${dl.showName!=null||dl.showName!='' }">
														<p class="mt5">${dl.showName }</p>
														</c:if>
												</a>
													<div class="mt5">
														<span class="c-bbb"><fmt:formatDate pattern="MM-dd HH:mm"
																value="${dl.addTime }" ></fmt:formatDate></span>
													</div></li>
											</c:forEach>
										</ul>
									</div>
								</section>
							</div>
						</div>
					</section>
				</div>
			</section>
			<!-- 主体区域 -->
</body>
</html>