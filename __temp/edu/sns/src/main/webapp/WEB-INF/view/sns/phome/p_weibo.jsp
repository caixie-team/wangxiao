<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>观点</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/weibo/weibo.js?v=<%=version%>" type="text/javascript"></script>
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
										<ul class="clearfix">
											<li class="current"><a href="${ctx}/weibo" title="观点">观点</a>
												<div class="ct-tabarrow-bg">&nbsp;</div></li>
										</ul>
									</header>
									<%-- <div class="tab-nosep mt10">
										<ol class="clearfix">
											<li><a title="全部" href="${ctx}/weibo">全部</a></li>
											<li><a title="热门观点"
												href="${ctx}/weibo/hot">热门观点</a></li>
											<li class="current"><a title="我的观点"
												href="javascript:void(0)">我的观点</a></li>
											<li><a title="好友观点"
												href="${ctx}/weibo/attention">好友观点</a></li>
											<li><a title="评论最多"
												href="${ctx}/weibo/most">评论最多</a></li>
										</ol>
									</div>--%>
								</section> 
								<section class="mt10 DT-wrap">
								<c:if test="${empty queryWeiBoList}">
										<div class="Prompt">
											<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
											<c:if test="${loginId==userid }">
											<p class="vam c-555 fsize14 disIb">您还没有发布观点呢。</p>
											</c:if>
											<c:if test="${loginId!=userid }">
											<p class="vam c-555 fsize14 disIb">他还没有发布观点呢。</p>
											</c:if>
										</div>
									</c:if>
									<c:forEach items="${queryWeiBoList}" var="qwbl">
										<article class="DT-detail-wrap" id="del${qwbl.id}">
											<aside class="DT-face">
												<a title="" href="${ctx}/p/${qwbl.cusId}/home">
												<c:if test="${qwbl.avatar==null||qwbl.avatar==''}">
												<img width="50" height="50" alt=""
													src="${ctximg}/static/sns/pics/user.jpg">
													</c:if>
													<c:if test="${qwbl.avatar!=null&&qwbl.avatar!=''}">
												<img width="50" height="50" alt=""
													src="<%=staticImageServer%>/${qwbl.avatar}">
													</c:if>
													</a>
											</aside>
											<div class="DT-detail">
												<section class="DT-name">
													<a class="c-blue dt-xm" title="" href="${ctx}/p/${qwbl.cusId}/home">${qwbl.showname}</a> <span
														class="c-888"></span> <span class="c-555">发表了观点：</span>
												</section>
												<!-- <section class="mt10">
												<a class="c-blue fsize14" title="" href="">如何成为精英前程的领导人</a>
											</section> -->
												<section class="DT-text">${qwbl.content}</section>
												<section class="mt10">
													<div class="clearfix">
														<section class="fr">
															<%-- <a class="c-blue"
																href="javascript:void(0)"
																onclick="delWeiBoConfirm(${qwbl.id})">删除
															</a> --%>
															<a class="DT-comment-btn c-blue mr10"
																href="javascript: void(0)">评论(<span
																id="commentNum${qwbl.id }">${qwbl.commentNum}</span>)
															</a>
														</section>
														<section class="c-b-green">
															<fmt:formatDate value="${qwbl.addTime}" type="both" ></fmt:formatDate>
														</section>
													</div>
												</section>
												<div class="DT-comment-wrap pingl undis" weiboId="${qwbl.id}"></div>
											</div>
										</article>
									</c:forEach>
								</section>
							</div>
							<!-- 公共分页 开始 -->
							<c:if test="${not empty friend||userid==loginId }">
							<jsp:include page="/WEB-INF/view/sns/common/page.jsp" />
							</c:if>
							<!-- 公共分页 结束 -->

						</section>
						<section class="W-main-right">
							<div class="pl20 pr20">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">一周发表观点/Top10</span>
									</section>
									<section class="WB-ranking-list">
										<ul class="ml10">
											<c:forEach items="${queryWeiBoListForWeiBoNumByWeek }"
												varStatus="index" var="qwlw">
												<li class="${index.count==1?'WB-show-p':''}">
													<div class="WB-r-l-txt">
														<c:if test="${qwlw.avatar==null||qwlw.avatar=='' }">
														<a href="${ctx}/p/${qwlw.cusId}/home" title="${qwlw.showname }" class="WB-r-l-pic"><img
															src="${ctximg}/static/sns/pics/user.jpg" width="67"
															height="67" alt=""></a>
															</c:if>
															<c:if test="${qwlw.avatar!=null&&qwlw.avatar!='' }">
														<a href="${ctx}/p/${qwlw.cusId}/home" title="${qwlw.showname }" class="WB-r-l-pic"><img
															src="<%=staticImageServer%>${qwlw.avatar}" width="67"
															height="67" alt=""></a>
															</c:if>
														<h5>
															<a href="${ctx}/p/${qwlw.cusId}/home" title="${qwlw.showname }" class="c-555">${qwlw.showname }</a>
														</h5>
														<p class="mt5">
															<span class="c-888">总量:${qwlw.weiBoNum }</span>
														</p>
													</div>
													<tt class="order-num">${index.count}</tt>
													</li>
											</c:forEach>
										</ul>
									</section>
								</div>
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
	 
	<form action="${ctx}/p/${userid}/weibo" name="searchForm" id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
	</form>
</body>
