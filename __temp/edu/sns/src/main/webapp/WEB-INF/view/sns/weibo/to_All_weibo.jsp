<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<head>
	<title>全站观点</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/weibo/weibo.js?v=<%=version%>" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	$(function(){
		if(getParameter("type")==1){
			my();
		}else{
			//默认调用全部观点
			all();
		}
	});
	</script>
</head>     
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20">
								<div class="addweibo dis">
								<section class="clearfix">
									<div class="title-txt">与大家分享你的趣事儿！</div>
									<div class="fr">&nbsp;</div>
								</section>
								<section class="mt10">
									<div class="send-txt-input weiBoContent">
										<textarea name="" id="weiBoContent" class="s-t-detail"></textarea>
									</div>
								</section>
								<section class="mt5">
									<div class="clearfix">
										<div class="fr tar">
											<span class="mr10 vam disIb"><tt class="c-red fsize12 error"></tt></span>
											<a href="javascript:void(0)" onclick="addWeiBo();" title="发布" class="send-btn-wrap"><span>发布</span></a>
										</div>
										<div class="fl ml5"><label class="ml20 vam disIb c-888 fsize12" style="margin-top: -4px;"><input type="checkbox" name="name" checked="checked" id="public"/>&nbsp;公开</label></div>
									</div>
								</section>
								</div>
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="current">
												<a href="${ctx}/weibo" title="观点">观点</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
									<div class="tab-nosep mt10">
										<ol class="clearfix">
											<li class="all"><a title="全部" href="javascript:all()">全部</a></li>
											<li class="hot"><a title="热门观点" href="javascript:hot()">热门观点</a></li>
											<li class="my"><a title="我的观点" href="javascript:my()">我的观点</a></li>
											<li class="attention "><a title="好友观点" href="javascript:attentionajax()">好友观点</a></li>
											<li class="most"><a title="评论最多" href="javascript:most()">评论最多</a></li>
										</ol>
									</div>
								</section>
								<section class="mt10 DT-wrap ajaxHtml">
								</section>
							</div>

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
														<a href="${ctx}/p/${qwlw.cusId}/home" title="${qwlw.showname }" class="WB-r-l-pic">
														<c:if test="${qwlw.avatar!=null&&qwlw.avatar!='' }">
															<img width="67" height="67" alt="" src="<%=staticImageServer%>${qwlw.avatar }">
														</c:if>
														<c:if test="${qwlw.avatar==null||qwlw.avatar=='' }">
															<img width="67" height="67" alt="" src="${ctximg}/static/sns/pics/user.jpg">
														</c:if>
														</a>
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
						<section class="W-main-right">
							<div class="pl20 pr20">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-r"><a href="${ctx}/sug/zh/1"
											title="更多" class="c-555"></a></span> <span class="c-t-2-l">评论最多</span>
									</section>
									<section class="cj-comment-list wisdom-list">
										<c:forEach items="${queryWeiBoList}" var="sswlst"
											varStatus="index">
											<dl class="clearfix">
												<c:choose>
													<c:when test="${index.count==1}">
														<dt>
															<span class="cjc-1"></span>
														</dt>
													</c:when>
													<c:when test="${index.count==2}">
														<dt>
															<span class="cjc-2"></span>
														</dt>
													</c:when>
													<c:when test="${index.count==3}">
														<dt>
															<span class="cjc-3"></span>
														</dt>
													</c:when>
													<c:otherwise>
														<dt>
															<span>${index.count }</span>
														</dt>
													</c:otherwise>
												</c:choose>
												<dd>
													<div class="cj-c-txt">
														${sswlst.content}
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
</body>
