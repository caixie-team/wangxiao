<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<meta charset="UTF-8">
	<title>个人空间</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/phome/phome.js?v=<%=version%>" type="text/javascript"></script>
	<script src="${ctximg}/static/sns/js/weibo/weibo.js?v=<%=version%>" type="text/javascript"></script>
	<script type="text/javascript">
	var showname = '${userExpandDto.showname}';
	var avatar = '${userExpandDto.avatar}';
	var cusId = '${userExpandDto.id}';
	var uBanner = '<div class="PCD-head"><div class="coverBanner_wrap"></div><div class="shadow S_shadow">&nbsp;</div></div>';
	$(function(){
		$(".M-body").prepend(uBanner);
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
		$(".W-main-l-fixed").css({"position" : "absolute" , "top" : "-108px"});
		$(".PCD-head .coverBanner_wrap").addClass("banner_transition").css({"background-image" : "url(/static/sns/images/T-banner-bg.jpg)"});
		fixedMenu();
	});
	function fixedMenu() {
		 if (window.XMLHttpRequst) {
			 return false;
		 } else {
			 var oMenu = $(".W-main-l-fixed");
			 function lmFun() {
				var sTop = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop;
				if (sTop > 215) {
					oMenu.css({"position" : "fixed" , "top" : "70px"});
				} else {
					oMenu.css({"position" : "absolute" , "top" : "-108px"});
				}
			 };
			 $(window).bind("scroll", lmFun);
			 lmFun();
		 };
	};
	</script>
	<style type="text/css">
		.W-main-bg.W-noBanner {margin-top: 0;}
	</style>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20">
								<section class="mt10">
									<section class="comm-title-3">
										<span class="c-t-2-l">个人资料</span>
										<div class="c-t-line">&nbsp;</div>
									</section>
									<div class="mt10 clearfix">
										<article class="fl person-data">
											<ul>
												<li class="c-bbb">
													<span class="c-blue">名称：</span>
													<span class="c-555 mr10">${userExpandDto.showname }</span>|
													<c:if test="${userExpandDto.gender==0 }">
													<span class="c-555 ml10 mr10 boy" title="男生"><i class="icon12">&nbsp;</i></span>
													</c:if>
													<c:if test="${userExpandDto.gender==1 }">
													<span class="c-555 ml10 mr10 girl" title="女生"><i class="icon12">&nbsp;</i></span>
													</c:if>
												</li>
												 <li class="mt10"><span class="c-blue">性别：</span>
                                                     <c:if test="${userExpandDto.gender==0 }">
                                                         <span class="c-888">男</span>
                                                     </c:if>
                                                     <c:if test="${userExpandDto.gender==1 }">
                                                         <span class="c-888">女</span>
                                                     </c:if>
                                                 </li>
												<li class="mt5">
                                                    <span class="c-blue">简介：</span>
                                                    <span class="c-888">${userExpandDto.userInfo }</span>
												</li>

											</ul>
										</article>
										<aside class="fl control-btn" style="width: 148px">
										<c:if test="${loginId!=0 }">
											<c:if test="${userid!=loginId}">
											<ol>
												<li class="mt5"><a href="javascript:void(0)" title="" class="comm-btn-green" onclick="addLetterInput(${userid},this,'${userExpandDto.showname }')"><span>发消息</span></a></li>
												<c:if test="${cusAttentionNum==0 }"><li class="mt5"><a href="javascript:void(0)" onclick="attention(${userid},this)" title="" class="comm-btn-green"><span>+加关注</span></a></li></c:if>
												<c:if test="${blacknum==0 }">
												<li class="mt5"><a href="javascript:void(0)" onclick="addblack(${userid},0)" title="+加黑名单" class="comm-btn-green"><span>+加黑名单</span></a></li>
												</c:if>
												
											</ol>
											</c:if>
											</c:if>
										</aside>
									</div>
								</section>
								<input type="hidden" class="pHomeCusId" value="${userid}">

								<!-- /挑战信息 -->
								<section class="mt10">
									<section class="comm-title-3">
										<span class="c-t-2-r"><a href="${ctx}/p/${userid}/dis" title="" class="c-blue">全部小组</a></span>
										<span class="c-t-2-l">加入的小组</span>
										<div class="c-t-line">&nbsp;</div>
									</section>
									<c:if test="${fn:length(disGroupList)==0 }">
                                        <div class="Prompt">
                                            <img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
                                            <c:choose>
                                                <c:when test="${userid==loginId }">
                                                    <p class="vam c-555 fsize14 disIb">您还没有创建小组呢</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="vam c-555 fsize14 disIb">他还没有创建小组呢</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
								    </c:if>
								<div class="mt10">
										<div class="QM-sort-qun">
										<section id="QM-list" class="QM-list groupph">
											<ol class="clearfix">
												<c:forEach items="${disGroupList }" var="dis" varStatus="index">
												<li class="" style="margin: 5px 9px 10px;width: 80px;">
													<a class="QM-face" title="${dis.name }" href="${ctx}/dis/info/${dis.id}">
													<img height="80" alt="" widht="80" style="width: 80px;height: 80px;" src="<%=staticImageServer%>${dis.imageUrl}">
													<p class="mt5">${dis.name }</p>
													</a>
												</li>	
												</c:forEach>										
											</ol>
										</section>
									</div>
								</div>
								</section>
								<!-- 排行榜 -->
								<section class="mt10 DT-wrap">
								<section class="comm-title-3">
										<span class="c-t-2-r"><a href="${ctx}/p/${userid}/weibo" title="全部观点" class="c-blue">全部观点</a></span>
										<c:choose>
                                            <c:when test="${userid==loginId }">
                                            <span class="c-t-2-l">我的观点</span>
                                            </c:when>
                                            <c:otherwise>
                                            <span class="c-t-2-l">发布的观点</span>
                                            </c:otherwise>
										</c:choose>
										<div class="c-t-line">&nbsp;</div>
									</section>
								<c:if test="${empty weiBoList}">
										<div class="Prompt">
											<img class="vam disIb" src="${ctximg}/static/sns/images/tishi.png">
											<c:if test="${userid==loginId}">
											<p class="vam c-555 fsize14 disIb">您还没有发布观点呢</p>
											</c:if>
											<c:if test="${userid!=loginId}">
											<p class="vam c-555 fsize14 disIb">他还没有发布观点呢</p>
											</c:if>
										</div>
									</c:if>
									<c:forEach items="${weiBoList}" var="qwbl">
										<article class="DT-detail-wrap" id="del${qwbl.id}">
											<aside class="DT-face">
												<a title="" href="${ctx}/p/${qwbl.cusId}/home">
												<c:if test="${userExpandDto.avatar==null||userExpandDto.avatar==''}">
												<img width="50" height="50" alt=""
													src="${ctximg}/static/sns/pics/user.jpg">
													</c:if>
													<c:if test="${userExpandDto.avatar!=null&&userExpandDto.avatar!=''}">
												<img width="50" height="50" alt=""
													src="<%=staticImageServer%>/${userExpandDto.avatar}">
													</c:if>
													</a>
											</aside>
											<div class="DT-detail">
												<section class="DT-name">
													<a class="c-333 dt-xm" title="" href="${ctx}/p/${qwbl.cusId}/home">${userExpandDto.showname}</a> <span
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
								<!-- 排行榜 -->
								<section class="mt20">
									<section class="comm-title-3">
										<span class="c-t-2-r"><a href="${ctx}/p/${userid}/blog" title="" class="c-blue">全部文章</a></span>
										<c:choose>
										<c:when test="${userid==loginId }">
										<span class="c-t-2-l">我的博文</span>
										</c:when>
										<c:otherwise>
										<span class="c-t-2-l">发布的博文</span>
										</c:otherwise>
										</c:choose>
										<div class="c-t-line">&nbsp;</div>
									</section>
									<div class="sub-title-wrap clearfix">
										<section class="fr sub-t-r">
											<span class="c-555">日期</span>
											<span class="c-555">回复</span>
											<span class="c-green" style="width:138px">最后回复</span>
										</section>
										 <h6 class="fl c-555 fsize14" style="width: 254px">标题</h6>
									</div>
									<c:if test="${fn:length(blogBlogList)==0 }">
									<div class="Prompt">
										<img src="${ctximg}/static/sns/images/tishi.png"
											class="vam disIb">
											<c:if test="${userid==loginId}">
										<p class="vam c-555 fsize14 disIb">您还没有发布博文呢</p>
										</c:if>
										<c:if test="${userid!=loginId}">
										<p class="vam c-555 fsize14 disIb">他还没有发布博文呢</p>
										</c:if>
									</div>
								</c:if>
									<div class="question-list">
										<ul>
										<c:forEach items="${blogBlogList }" var="blog" varStatus="index">
											<li>
												<section class="clearfix">
													<div class="fr">
														<span class="replay-num c-555"><fmt:formatDate type="date" value="${blog.addTime}" ></fmt:formatDate></span>
														<span class="replay-num c-orange">${blog.replyCount}/${blog.viewCount
														}</span>
			 											<span class="Q-name-data" style="width:138px">
			 											<%-- <c:choose>
			 											<c:when test="${blog.replyName!=null||blog.replyName!=''}">
			 											<a href="#" class="c-green">${blog.replyName }</a>
															<tt class="ml10 c-555">
																<fmt:formatDate pattern="yyyy-MM-dd" value="${blog.updateTime}" ></fmt:formatDate>
															</tt>
			 											</c:when>
			 											<c:otherwise>
			 											<a href="#" class="c-green">${blog.showName }</a>
															<tt class="ml10 c-555">
																<fmt:formatDate pattern="yyyy-MM-dd" value="${blog.addTime }"></fmt:formatDate>
															</tt>
			 											</c:otherwise>
			 											</c:choose> --%>
			 											 <c:if
															test="${blog.replyName!=null }">
															<a href="#" class="c-green" title="${blog.replyName}">${blog.shortReplyName }</a>
															<tt class="ml10 c-555">
																<fmt:formatDate pattern="yyyy-MM-dd" value="${blog.updateTime}" ></fmt:formatDate>
															</tt>
														</c:if> <c:if test="${ blog.replyName==null}">
															<a href="#" class="c-green" title="${blog.showName }">${blog.shortShowName }</a>
															<tt class="ml10 c-555">
																<fmt:formatDate pattern="yyyy-MM-dd" value="${blog.addTime }"></fmt:formatDate>
															</tt>
														</c:if></span>
													</div>
													<div class="fl">
														<c:if test="${blog.selType==1 }">
															<tt class="icon22 yuan">&nbsp;</tt>
														</c:if>
														<c:if test="${blog.selType==2 }">
															<tt class="icon22 zhuan">&nbsp;</tt>
														</c:if>
														<a class="article-q-l-link-txt c-blue" href="${ctx}/blog/info/${blog.id}" title="${blog.title }" style="width: 240px">
													${fn:substring(blog.title,0,25) }</a></div>
												</section>
											</li>
											</c:forEach>
										</ul>
									</div>
								</section>
								<!-- 动态 -->
								<section class="mt20 DT-wrap">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="current">
											<c:choose>
										<c:when test="${userid==loginId }">
										<a href="javascript:void(0)" title="我的动态">我的动态</a>
										</c:when>
										<c:otherwise>
										<a href="javascript:void(0)" title="动态">动态</a>
										</c:otherwise>
										</c:choose>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
									<div id="dongtai" >
									</div>
									<ul id="loading" page="1" style="text-align: center;"> <img src="${ctximg}/static/sns/images/loading.gif" class="vam" width="15px" height="15px"/><tt class="ml5 c-555 vam">正在加载，请稍后...</tt></ul>
								</section>
								<!-- 动态 -->
								<input type="hidden" value="${friend.id}" id="friendid"/>
								<input type="hidden" value="${userid}" id="userid"/>
								<input type="hidden" value="${loginId}" id="loginId"/>
								<!-- 公共分页 开始 -->
								<div class="pagination pagination-large">
									
										<ul id="pageFlag"></ul>
									
								</div>
								<!-- 公共分页 结束 -->
							</div>
						</section>
						<section class="W-main-right">
							<div class="pl20">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">最近访客(<c:if test="${userid==loginId }"><a href="${ctx}/friend?falg=visitor"><tt class="fsize12 f-fM c-blue">${num }</tt></a></c:if>
										<c:if test="${userid!=loginId }"><a href="${ctx}/friend/${userid}/visitor"><tt class="fsize12 f-fM c-blue">${num }</tt></a></c:if>)</span>
									</section>
									<section class="visitor-list mt20">
										<ul class="clearfix">
											<c:forEach items="${visitorList }" var="vlt">
											<li><a href="${ctx}/p/${vlt.visitorCusId }/home" target="_blank" title="${vlt.showname }">
											<c:if test="${vlt.avatar!=null&&vlt.avatar!='' }">
												<img src="<%=staticImageServer%>${vlt.avatar }" height="40" width="40" alt="">
												<p class="mb10"><span class="c-blue fsize14">
												<c:if test="${vlt.showname!=''}">
													${fn:substring(vlt.showname,0,6)}
												</c:if>
												
												<c:if test="${vlt.showname==''}">
													&nbsp;
												</c:if>
												
												</span></p>
												<span class="c-888"><fmt:formatDate pattern="MM月dd日"  value="${vlt.addTime }"></fmt:formatDate></span>
											</c:if>
											<c:if test="${vlt.avatar==null||vlt.avatar=='' }">
												<img src="${ctximg}/static/sns/pics/user.jpg" height="40" width="40" alt="">
												<p class="mb10"><span class="c-blue fsize14">
													<c:if test="${vlt.showname!=''}">
														${fn:substring(vlt.showname,0,6)}
													</c:if>
													
													<c:if test="${vlt.showname==''}">
														&nbsp;
													</c:if>
												</span></p>
												<span class="c-888"><fmt:formatDate pattern="MM月dd日"  value="${vlt.addTime }"></fmt:formatDate></span>
											</c:if>
											</a></li>
											</c:forEach>
										</ul>
									</section>
								</div>
								<div id="weiboList">
								</div>
								<div id="courseList">
								</div>
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
	<form action="${ctx}/friend/sdfrircd" name="searchForm"
		id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage"
			value="${page.currentPage}" />
	</form>
</body>
</html>