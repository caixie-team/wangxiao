<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>问题</title>
</head>
<body>
<div class="">
	<div class="h-mobile-mask"></div>
	<div class="bg-fa of">
		<!-- /课程列表 开始 -->
		<section class="container">
			<section class="path-wrap txtOf hLh30"> 
				<a class="c-999 fsize14" title="" href="${ctx}/">首页</a> \
				<a class="c-999 fsize14" title="" href="${ctx}/front/question">全部问答</a> \
				<span class="c-333 fsize14">${sugSuggest.title}</span> 
			</section>
			<div class="clearfix">
				<div class="fl col-75">
					<div class="mr20 mb20">
						<div class="i-box">
							<!-- /问题详情 开始 -->
						<div>
							<section class="q-infor-box">
								<div class="pr">
									<aside class="q-head-pic">
										<c:choose>
											<c:when test="${not empty sugSuggest.avatar}">
												<c:if test="${fn:contains(sugSuggest.avatar,'http' )}">
													<img src="${sugSuggest.avatar}" alt="">
												</c:if>
												<c:if test="${!fn:contains(sugSuggest.avatar,'http' )}">
													<img src="<%=staticImageServer%>/${sugSuggest.avatar}" alt="">
												</c:if>
											</c:when>
											<c:otherwise>
												<img src="${ctximg}/static/common/images/user_default.png" alt="">
											</c:otherwise>
										</c:choose>
										<p class="hLh30 txtOf"></p>
									</aside>
									<section class="q-txt-box">
										<h3 class="hLh30 txtOf">
											<span class="c-blue1 fsize14">${sugSuggest.showname }</span>
											<span class="c-999 fsize14">提问</span>
										</h3>
										<aside class="q-share">
											<div class="c-share pr of" style="width: 256px;">
												<div class="disIb">
													<em class="icon14 vam"></em>
													<tt class="ml5 fsize14 c-666 vam f-fM">分享</tt>
												</div>
												<div class="bdsharebuttonbox bdshare-button-style0-16" id="bdshare" data-bd-bind="1456112747602" style="right: -160px;"><a data-cmd="qzone" class="bds_qzone" href="#" title="分享到QQ空间"></a><a data-cmd="tsina" class="bds_tsina" href="#" title="分享到新浪微博"></a><a data-cmd="tqq" class="bds_tqq" href="#" title="分享到腾讯微博"></a><a data-cmd="renren" class="bds_renren" href="#" title="分享到人人网"></a><a data-cmd="weixin" class="bds_weixin" href="#" title="分享到微信"></a></div> 
												<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
												</script>
											</div>
										</aside>
									</section>
									<section class="ml50 ques-pl40">
										<div class="mt20">
											<h3 class="hLh30 txtOf">
												<c:if test="${empty sugSuggest}">
													您要看的数据不存在或已删除，请返回重试！
												</c:if>
												<c:if test="${not empty sugSuggest}">
												<span class="fsize18 c-333 vam">${sugSuggest.title}</span>
												
										</c:if>
											</h3>
										</div>
										<div class=" mt15">
											<p>
												<span class="c-999 f-fA">${sugSuggest.content }</span>
											</p>
										</div>
										<div class="mt20 pr10">	
											<section class="fr">
												<span>
													<c:if test="${isLike>0}">
														<a href="javascript:void(0)" onclick="notLikeEvent(${sugSuggest.id})" title="取消点赞" class="noter-dy vam c-999 zan-already"><em class="icon18 mr5">&nbsp;</em>(${likeCount })</a>
													</c:if>
													<c:if test="${isLike<=0}">
														<a href="javascript:void(0)" onclick="likeEvent(${sugSuggest.id})" title="赞一下" class="noter-dy vam c-999"><em class="icon18 mr5">&nbsp;</em>(${likeCount })</a>
													</c:if>
													<tt id="replycount" class="vam ml10 f-fM c-999 " title="评论"><em class="icon18 c-reply">&nbsp;</em>(${replyList.size() })</tt>
												</span>
											</section>
											<span class="c-ccc fl vam">提问时间：<fmt:formatDate value="${sugSuggest.addtime}" type="both"/></span>
											<div class="clear"></div>
										</div>
									</section>
								</div>
								<!-- /回答列表 开始 -->
								<%-- 可以回复 --%>
								<c:if test="${sugSuggest.status==0}">
									<section class="lh-bj-list pr mt20 replyhtml">
										<ul>
											<li class="unBr">
												<aside class="noter-pic">
													<c:choose>
														<c:when test="${not empty user.avatar}">
															<c:if test="${fn:contains(user.avatar,'http' )}">
																<img src="${user.avatar}" alt="" width="50" height="50" class="picImg">
															</c:if>
															<c:if test="${!fn:contains(user.avatar,'http' )}">
																<img src="<%=staticImageServer%>/${user.avatar}" alt="" width="50" height="50" class="picImg">
															</c:if>
														</c:when>
														<c:otherwise>
															<img src="${ctximg}/static/common/images/user_default.png" alt="" width="50" height="50" class="picImg">
														</c:otherwise>
													</c:choose>
												</aside>
												<div class="of" id="divform">
													<section class="n-reply-wrap">
														<fieldset>
															<textarea onkeyup="checkLen(this)" id="sugSuggestReplyContent" placeholder="输入您要评论的文字" name=""></textarea>
														</fieldset>
														<p class="of mt5 tar pl10 pr10">
														<!-- 	<span class="fl">
																<em class="icon12 msg-e-icon"></em>
																<tt class="c-red com-err-info vam ml5">111</tt>
															</span> -->
															<span class="c-999 fsize14" >还可以输入<tt class="c-red" id="character">500</tt>字</span>
															<a class="lh-reply-btn ml15" onclick="addSugSuggestReply('${sugSuggest.id }','${sugSuggest.type}')" title="回复" href="javascript:void(0)">发表</a>
														</p>
													</section>
												</div>
											</li>
										</ul>
									</section>
								</c:if>
								<c:if test="${sugSuggest.status==1 and not empty bestReply}">
									<div class="good-anwer-box">
										<h4 class="g-a-title"><span class="bg-green vam"><em class="icon24 mr5 good-answ-icon">&nbsp;</em>最佳答案</span></h4>
										<section class="good-answer mt10">
											<section class="question-list lh-bj-list pr">
												<ul class="pr10">
													<li id="199" class="pr firstReplay">
														<div>
															<aside class="noter-pic">
																<c:if test="${not empty bestReply.userExpandDto.avatar}">
																	<c:if test="${fn:contains(bestReply.userExpandDto.avatar,'http' )}">
																		<img width="50" height="50" src="${bestReply.userExpandDto.avatar}" class="picImg">
																	</c:if>
																	<c:if test="${!fn:contains(bestReply.userExpandDto.avatar,'http' )}">
																		<img width="50" height="50" src="<%=staticImageServer%>${bestReply.userExpandDto.avatar}" class="picImg">
																	</c:if>
																</c:if>
																<c:if test="${empty bestReply.userExpandDto.avatar}">
																	<img width="50" height="50" src="${ctximg}/static/common/images/user_default.png" class="picImg">
																</c:if>
															</aside>
															<div class="of">
																<span class="fl">
																	<font class="fsize14 c-blue1">${bestReply.userExpandDto.showname}</font>
																</span>
																<span class="fr c-666 fsize12">
																	<font><fmt:formatDate value="${bestReply.addtime}" type="both" pattern="yyyy-MM-dd HH:mm"/></font>
																</span>
															</div>
															<div class="noter-txt mt5">
																<p>${bestReply.content}</p>
															</div>
															<div class="tar">
																<a title="回复" onclick="showSecondReply(${bestReply.id})" class="c-666 fsize14" href="javascript:void(0)"> <em class="icon18 c-reply"></em> </a>
															</div>
														</div>
														<div class="n-reply mt5 exec-reply" id="secondReplyDiv${bestReply.id}" style="display: none;">
															<div id="secondReplyContent${bestReply.id}"></div>
														</div>
													</li>
												</ul>
											</section>
										</section>
									</div>
								</c:if>
								<div class="mt40"><span class="fsize18 c-333">回复列表</span></div>
								<section class="review-box mt30">
									<c:if test="${empty replyList }">
									<section class="mt30 mb30 tac">
										<em class="no-data-ico cTipsIco">&nbsp;</em>
										<span class="c-666 fsize14 ml10 vam">还没有评论，快去抢沙发~</span>
									</section>
									</c:if>
									<c:if test="${not empty replyList }">
										<form action="${ctx}/front/question/info/${sugSuggest.id}" method="post" id="searchForm">
											<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
										</form>
										<ul>
											<c:forEach items="${replyList}" var="reply" varStatus="index">
												<li class="pr firstReplay" id="${reply.id}">
													<aside class="noter-pic">
														<c:if test="${not empty reply.userExpandDto.avatar}">
															<c:if test="${fn:contains(reply.userExpandDto.avatar,'http' )}">
																<img width="50" height="50" src="${reply.userExpandDto.avatar}" class="picImg">
															</c:if>
															<c:if test="${!fn:contains(reply.userExpandDto.avatar,'http' )}">
																<img width="50" height="50" src="<%=staticImageServer%>${reply.userExpandDto.avatar}" class="picImg">
															</c:if>
														</c:if>
														<c:if test="${empty reply.userExpandDto.avatar}">
															<img width="50" height="50" src="${ctximg}/static/common/images/user_default.png" class="picImg">
														</c:if>
													</aside>
													<div class="of">
														<span class="fl"><font class="fsize14 c-blue1">${reply.showname}</font></span>
														<span class="fr c-666 fsize12"><font>${reply.modelStr} </font></span>
													</div>
													<div class="noter-txt mt5">
														<p>${reply.content}</p>
													</div>
													<div class="tar">
														<c:if test="${adopt and sugSuggest.status==0 and cusId!=reply.cusId}">
															<a href="javascript:void(0)" class="fsize14 c-master" onclick="adoptReply(${reply.suggestId},${reply.id})" title="采纳">
																采纳
															</a>
														</c:if>
														<a href="javascript:void(0)" class="c-666 fsize14" onclick="showSecondReply(${reply.id})" title="回复">
															<em class="icon18 c-reply"></em>
														</a>
													</div>
													<div class="n-reply mt5" id="secondReplyDiv${reply.id}" style="display: none;">
														<section class="n-reply-wrap">
															<fieldset>
																<textarea onkeyup="checklengh(this,${reply.id})" id="replyContent${reply.id}" name=""></textarea>
															</fieldset>
															<p class="of mt5 tar pl10 pr10">
																<span class="c-999 fsize14">还可以输入<tt class="c-red" id="chara${reply.id}">500</tt>字</span>
																<a class="lh-reply-btn ml15" onclick="sendReply(${reply.id})" title="回复" href="javascript:void(0)">回复</a>
															</p>
														</section>
														<div id="secondReplyContent${reply.id}"></div>
													</div>
												</li>
											</c:forEach>
										</ul>
										<jsp:include page="/WEB-INF/view/common/web_page.jsp" />
									</c:if>
								</section>
							</section>
						</div>
						<!-- /问题列表 结束 -->
						</div>
					</div>
				</div>
				<div class="fr col-25">
					<div class="i-box">
						<div class="of">
							<a href="${ctx}/front/questionAdd" class="bm-lr-btn q-ask-btn">我要提问</a>
						</div>
					</div>
					<div class="i-box mt20">
						<div class="clearfix q-a-box">
							<div class="q-a-pic">
							<c:choose>
								<c:when test="${not empty user.avatar }">
									<c:if test="${fn:contains(user.avatar,'http' )}">
										<img src="${user.avatar}" width="60" height="60">

									</c:if>
									<c:if test="${!fn:contains(user.avatar,'http' )}">
										<img src="<%=staticImageServer %>${user.avatar}" width="60" height="60">
									</c:if>
								</c:when>
								<c:otherwise>
									<img src="${ctximg}/static/common/images/user_default.png" width="60" height="60">
								</c:otherwise>
							</c:choose>
							</div>
							<p class="hLh30 c-666">提问：<span class="c-blue">${questionNum }</span></p>
							<p class="hLh30 c-666">回答：<span class="c-blue">${answerNum }</span></p>
							<div class="q-a-btn">
								<a href="${ctx}/uc/ques/my">我的问答</a>
							</div>
						</div>
					</div>
					<div class="i-box mt20 mb20">
						<div class="of">
							<div class="mb10"><span class="fsize16 c-333">热门问答</span></div>
						</div>
						<ul class="q-hot-list comm-new-list">
							<c:forEach items="${hotCommentList }" var="hotComment">
								<li>
									<p><a href="${ctx}/front/question/info/${hotComment.id}" class="txtOf">${hotComment.title }</a></p>
									<span title="" class="q-view-box c-999 vam disIb">
										<em class="icon14 q-view mr5 vam"></em><tt class="vam f-fM">${hotComment.browseNum }</tt>
									</span>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
	<script src="${ctximg}/static/edu/js/front/question/suggest.js"type="text/javascript"></script>
	<script>
		$(function() {
			effect();//讲师列表的课程名显示
		})
		var staticImageServer = '<%=staticImageServer%>';
		var sugSugestCusId = '${sugSuggest.cusId}';//问题id
		var nowCusId = "${cusId}";
		var status = '${sugSuggest.status}';
		var sugSugestId = '${sugSuggest.id}';
		var type = '${sugSuggest.type}';
	</script>
</body>
</html>