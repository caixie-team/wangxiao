<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>同学问答</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/suggest/suggest.js?v=<%=version%>"></script>
	<script type="text/javascript">
	var showname = '${userExpandDto.showname}';
	var avatar = '${userExpandDto.avatar}';
	var cusId = '${userExpandDto.id}';
	$(function(){
		if(avatar!=null&&avatar!=""){
			$(".avatar").attr("src",staticImageServer+""+avatar+"");
		}else{
			$(".avatar").attr("src",imagesPath+"/static/sns/pics/user.jpg");
		}
		$('.avatarhref').attr('href',''+baselocation +'/p/'+cusId+'/home');//修改链接
		$('.avatarhref').attr('title',showname);//修改title
		$('.cusshowname').html(showname);//用户名
		$('.cusshowname').attr('title',showname);//修改title
		$('.cusshowname').attr('href',''+baselocation +'/p/'+cusId+'/home');//修改链接
	});
	</script>
</head>
<body class="W-body">
	<!-- 主体 开始-->
			<section class="W-main-c fl">
				<div class="W-main-cc">
					<section class="W-main-center">
						<div class="pl20">
							<section class="mt10">
								<header class="comm-title-1">
									<a href="${ctx}/sug/add" class="fr c-888 mt10 mr10">提问题&gt;&gt;</a>
									<ul class="clearfix">
										<li><a href="${ctx}/p/${userid}/sug" title="提出的问题">提出的问题</a>
										</li>
										<li class="current"><a href="javascript:void(0)"
											title="回答的问题">回答的问题</a>
											<div class="ct-tabarrow-bg">&nbsp;</div></li>
									</ul>
								</header>
							</section>
							<!-- /挑战信息 -->
										<c:if test="${empty sugSuggestList}">
											<div class="Prompt">
												<img class="vam disIb"
													src="${ctximg}/static/sns/images/tishi.png">
													<c:if test="${loginId==userid }">
												<p class="vam c-555 fsize14 disIb">您还没有回答问题呢。</p>
												</c:if>
												<c:if test="${loginId!=userid }">
												<p class="vam c-555 fsize14 disIb">他还没有回答问题呢。</p>
												</c:if>
											</div>
										</c:if>
										
										<div class="Q-article-list-2">
											<ul>
											<c:forEach items="${sugSuggestList}" var="sslst">
												<li>
													<h5 class="clearfix">
														<div class="fl">
															<a href="javascript:window.location.href='${ctx}/sug/info/${sslst.id }'" class="c-blue article-q-l-link-txt">${sslst.title}</a>
														</div>
													</h5>
													<div class="QA-desc-2">
														${sslst.shortContent }
													</div>
													<div class="mt5 clearfix">
														<span class="fr"><tt class="c-888">评论/查看：</tt><a href="javascript:window.location.href='${ctx}/sug/info/${sslst.id }'" title="评论/查看：${sslst.replycount}&nbsp;/&nbsp;${sslst.browseNum }" class="c-555">${sslst.replycount}&nbsp;/&nbsp;${sslst.browseNum }</a></span>
														<span class="fl ml10"><tt class="c-888">作者：</tt><a href="${ctx}/p/${sslst.cusId }/home" title="${sslst.showname}" class="c-blue">${sslst.showname}</a></span> 
													</div>
												</li>
												</c:forEach>
											</ul>
										</div>
						</div>
						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/sns/common/page.jsp" />
						<!-- 公共分页 结束 -->
					</section>
					<section class="W-main-right">
						<div class="pl20 pr20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-r"><a
										href="${ctx}/sug/zh/1" title="更多" class="c-555">更多</a></span>
									<span class="c-t-2-l">智慧榜</span>
								</section>
								<section class="cj-comment-list wisdom-list">
									<c:forEach items="${sugSuggestWisdomList}" var="sswlst"
										varStatus="index">
										<dl class="clearfix">
											<c:choose>
												 <c:when test="${index.count==1}">
												 	<dt><span class="cjc-1"></span></dt>
												 </c:when>
												 <c:when test="${index.count==2}">
												 	<dt><span class="cjc-2"></span></dt>
												 </c:when>
												 <c:when test="${index.count==3}">
												 	<dt><span class="cjc-3"></span></dt>
												 </c:when>
												 <c:otherwise>
												 	<dt><span>${index.count }</span></dt>
												 </c:otherwise>
											</c:choose>
											<dd>
												<div class="cj-c-txt">
													<a class="c-555"
														href="javascript:window.location.href='${ctx}/sug/info/${sswlst.id }'" title="${sswlst.title}">${sswlst.title}</a>
												</div>
											</dd>
										</dl>
									</c:forEach>
								</section>
							</div>
						</div>
						<div class="pl20 pr20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-r"><a
										href="${ctx}/sug/rx/1" title="更多" class="c-555">更多</a></span>
									<span class="c-t-2-l">热心榜</span>
								</section>
								<section class="cj-comment-list wisdom-list">
									<c:forEach items="${sugSuggestHotList}" var="sshlst"
										varStatus="index">
										<dl class="clearfix">
											<c:choose>
												 <c:when test="${index.count==1}">
												 	<dt><span class="cjc-1"></span></dt>
												 </c:when>
												 <c:when test="${index.count==2}">
												 	<dt><span class="cjc-2"></span></dt>
												 </c:when>
												 <c:when test="${index.count==3}">
												 	<dt><span class="cjc-3"></span></dt>
												 </c:when>
												 <c:otherwise>
												 	<dt><span>${index.count }</span></dt>
												 </c:otherwise>
											</c:choose>
											<dd>
												<div class="cj-c-txt">
													<a class="c-555"
														href="javascript:window.location.href='${ctx}/sug/info/${sshlst.id }'" title="${sshlst.title}">${sshlst.title}</a>
												</div>
											</dd>
										</dl>
									</c:forEach>
								</section>
							</div>
						</div>
					</section>
				</div>
			</section>
			<!-- 主体区域 -->
<form action="${ctx}/p/${userid}/answersug" name="searchForm"
	id="searchForm" method="post">
	<input id="pageCurrentPage" type="hidden" name="page.currentPage"
		value="${page.currentPage}" />
</form>
</body>
