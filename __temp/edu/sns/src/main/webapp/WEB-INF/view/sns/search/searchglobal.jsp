<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>搜索</title>
	<script src="${ctximg}/static/sns/js/search/search.js?v=${v}" type="text/javascript"></script>
	 <link rel="stylesheet" href="${ctximg}/static/common/jquery-ui-1.10.4/css/ui-lightness/jquery-ui-1.10.4.custom.css?v=${v}">
	 <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery-ui-1.10.4.custom.js?v=${v}"></script>
	 <script src="${ctximg}/static/common/jquery-ui-1.10.4/js/jquery.ui.datepicker-zh-CN.js?v=${v}"></script>
	 <script src="${ctximg}/static/sns/js/weibo/weibo.js?v=${v}"></script>
	 <script src="${ctximg}/static/sns/js/friend/friend.js?v=${v}"></script>
	 <script src="${ctximg}/kindeditor/kindeditor-all.js?v=${v}"></script>
	 
	 <script type="text/javascript">
	
	 $(function(){
		
		 if('${error}'!=""){
			 dialog_sns('${error}',0);dragFun();
			 $(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
		 }
		 if('${search.falgshow}'=='show'){
			 $("#asBtn").click();
		 }
		 $("#disgroupFlag").val('${search.disgroupFlag}');
	 });
	function enterSubmit(event) {
		var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
		if (keyCode == 13) {
			search();
		return false;
		}
	} 
	 </script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center" style="min-height: 600px;width: 100%;">
							<div class="mt10">
								<!-- 公共选项卡 -->
								<section class="comm-TabChange-title clearfix" >
									<ul class="clearfix fl" id="tabs">
										<li tab="blog"  <c:if test="${search.tab=='blog'}">class="current"</c:if>><a href="javascript:"  title="博客">博客</a></li>
										<li tab="weibo"  <c:if test="${search.tab=='weibo'}">class="current"</c:if> ><a href="javascript:" title="观点" >观点</a></li>
										<li tab="sug" <c:if test="${search.tab=='sug'}">class="current"</c:if>><a href="javascript:" title="同学问答" >同学问答</a></li>
										<li tab="dis" <c:if test="${search.tab=='dis'}">class="current"</c:if>><a href="javascript:" title="小组" >小组</a></li>
									</ul>
								</section>
								<!-- 公共选项卡 -->
								<section class="mt20">
									<div class="SG-inp-btn">
										<form action="${ctx}/search" method="post" id="searchForm" name="searchForm">
										<input type="hidden" name="search.falgshow" value="${search.falgshow}" id="falgshow">
											<input type="hidden" name="search.tab" value="${search.tab}" id="tab">
											<input type="hidden" id="pageCurrentPage"  name="page.currentPage" value="${page.currentPage}"/>
											<input type="text" class="search-global-inp fl" name="search.keyword" value="${search.keyword}" id="keyword" onkeyup="enterSubmit(event)">
											<a href="javascript:search()" title="搜索" class="search-global-btn comm-btn-orange fl"><span>搜索</span></a>
											<a href="javascript: void(0)" title="高级搜索" id="asBtn" style="float: right;margin-right: 50px;" class="c-blue fl ml5 mt20">高级搜索</a>
											<div class="clear"></div>
											<div class="advancedSearch" id="advancedSearch">
											<span class="AS-title"><b class="fsize14 c-555">高级搜索</b></span>
											<ol>
												<li class="clearfix" id="search_blog" style="display: none">
													<div class="fl">
														<span class="c-555 fsize14">用户：</span>
														<input type="text" name="search.blogshowname" value="${search.blogshowname}" id="blogshowname" class="AS-inp">
													</div>
													<div class="fl ml20">
														<span class="c-555 fsize14">标题：</span>
														<input type="text"  name="search.blogtitle" id="blogtitle" value="${search.blogtitle}" class="AS-inp">
													</div>
												</li>
												<li id="search_blog2" style="display: none" >
													<span class="pr20 mr10" >
														<span class="c-555 fsize14">时间：</span>
														<input type="text"  name="search.blogdate" value="${search.blogdate}" id="blogdate" readonly="readonly" class="AS-inp">
													</span>
												</li>
												
												<li class="clearfix" id="search_weibo" style="display: none">
													<div class="fl">
														<span class="c-555 fsize14">用户：</span>
														<input type="text" name="weiboshowname" value="${search.weiboshowname}" id="weiboshowname" class="AS-inp">
													</div>
													<div class="fl ml20">
														<span class="c-555 fsize14">时间：</span>
														<input type="text"  name="search.weibodate" value="${search.weibodate}" id="weibodate" readonly="readonly" class="AS-inp">
													</div>
												</li>
												
												<li class="clearfix" id="search_dis" style="display: none">
													<div class="fl">
														<span class="c-555 fsize14">组长：</span>
														<input type="text" name="search.disshowname" value="${search.disshowname}" id="disshowname" class="AS-inp">
													</div>
													<span class="pr20 mr10">
														<tt class="c-555 fsize14">小组分类：</tt>
														<select name="search.disclasstiy" id="disclasstiy" >
															<option value="0">--小组分类--</option>
															<c:forEach items="${disGroupClassifyList}" var="disGroupClassify">
																<c:if test="${disGroupClassify.id==search.disclasstiy}">
																	<option value="${disGroupClassify.id}" selected="selected" >${disGroupClassify.name}</option>
																</c:if>
																<c:if test="${disGroupClassify.id!=search.disclasstiy}">
																	<option value="${disGroupClassify.id}"  >${disGroupClassify.name}</option>
																</c:if>
															</c:forEach>
														</select>
													</span>
													<span class="pr20 mr10">
														<tt class="c-555 fsize14">小组排序：</tt>
														<select name="search.disgroupFlag" id="disgroupFlag" >
														<option value="0">--排序--</option>
														<option value="1" >按热度排序</option>
														<option value="2" >按文章数排序</option>
														<option value="3" >按小组成员数排序</option>
														</select>
													</span>
												</li>
												
												<li class="clearfix" id="search_sug1" style="display: none">
													<div class="fl">
														<span class="c-555 fsize14">用户：</span>
														<input type="text" name="search.sugshowname" value="${search.sugshowname}" id="sugshowname" class="AS-inp">
													</div>
													<div class="fl ml20">
														<span class="c-555 fsize14">标题：</span>
														<input type="text"  name="search.sugtitle" id="sugtitle" value="${search.sugtitle}" class="AS-inp">
													</div>
												</li>
												
												<li class="clearfix"  id="search_sug2" style="display: none" >
													<div class="fl">
														<span class="c-555 fsize14">时间：</span>
														<input type="text"  name="search.sugbodate" value="${search.sugbodate}" id="sugbodate" readonly="readonly" class="AS-inp">
													</div>
													<%--<span class="fl ml20" >
															<tt class="c-555 fsize14">类别：</tt>
															<select name="search.sugType" id="sugType">
																<option value="0">--类别--</option>
																<option value="1"   <c:if test="${search.sugType==1}" >selected="selected"</c:if> >课程问题</option>
																<option value="2" <c:if test="${search.sugType==2}" >selected="selected"</c:if> >考试问题</option>
															</select>
													</span>--%>
												</li>
												<li class="clearfix"  >
														<div style="text-align: center">
															<a href="javascript:search()" class=" comm-btn-orange" title=""><span>搜索</span></a>
															<a href="javascript:cleansearch()" class="comm-btn-gray" title=""><span>重置</span></a>
														</div>
												</li>
													
											</ol>
											<a class="cClose" title="关闭" href="javascript:void(0)">&nbsp;</a>
										</div>
									</form>
									</div>
								</section>
								<!-- 搜索结果区域 -->
								<div class="mt20 pl20 pr20">
									<article class="mt20">
										<div>
											<c:choose>
												<c:when test="${(empty datalist) && !init}">
													<div class="comm-tips-2" >
														<span>
															<i class="icon26 tipsIcon-2">&nbsp;</i>
															<tt class="c-888 fsize16 ml10 vam">抱歉，未找到<c:if test="${!empty search.keyword }">“${search.keyword}”</c:if>相关结果。 </tt>
														</span>
													</div>
												</c:when>
												<c:otherwise>
													<!-- 博文 结果 -->
													<c:if test="${search.tab=='blog'}">
														<div class="pl20 pr20" style=" ">
															<section class="comm-title-3">
																<span class="c-t-2-l">博客搜索结果：</span>
																<div class="c-t-line">&nbsp;</div>
															</section>
														<div class="Q-article-list-2">
															<ul>
																<c:forEach items="${datalist }" var="blog">
																	<li>
																		<h5 class="clearfix">
																			<div class="fr c-bbb fsize12">
																				<span class="ml10"><tt class="c-888">最后发表：</tt>
																					<c:if test="${blog.replyName!=null }">
																						<tt class="c-888">
																							<span
																								class="c-green">${blog.replyName }</span>
																							<fmt:formatDate type="date"
																								value="${blog.updateTime}"></fmt:formatDate>
																						</tt>
																					</c:if> <c:if test="${blog.replyName==null }">
																						<tt class="c-888">
																							<span
																								class="c-green">${blog.showName }</span>
																							<fmt:formatDate type="date"
																								value="${blog.addTime}"></fmt:formatDate>
																						</tt>
																					</c:if> </span>
																			</div>
																			<div class="fl">
																				<c:if test="${blog.selType==1 }">
																					<tt class="icon22 yuan">&nbsp;</tt>
																				</c:if>
																				<c:if test="${blog.selType==2 }">
																					<tt class="icon22 zhuan">&nbsp;</tt>
																				</c:if>
																				<a href="${ctx}/blog/info/${blog.blogId}"
																					class="c-blue article-q-l-link-txt"
																					title="${blog.title }">${blog.title }</a>
																			</div>
																		</h5>
																		<div class="QA-desc-2">${blog.shortContent}</div>
																		<div class="mt5 clearfix">
																			<span class="fr"><tt class="c-888">评论/查看：</tt><a
																				href="javascript:void(0)" title="" class="c-555">${blog.replyCount}/${blog.viewCount}</a></span>
																			<span class="fl ml10"><tt class="c-888">作者：</tt><a
																				href="${ctx}/p/${blog.cusId}/home"
																				title="" class="c-blue">${blog.showName}</a></span>
																		</div>
																	</li>
																</c:forEach>
															</ul>
														</div>
													</div>
													</c:if>
													<!-- 观点 结果 -->
													<c:if test="${search.tab=='weibo'}">
														<div class="DT-wrap pl20 pr20">
															<section class="comm-title-3">
																<span class="c-t-2-l">观点搜索结果：</span>
																<div class="c-t-line">&nbsp;</div>
															</section>
															<c:forEach items="${datalist}" var="weibo">
																<article class="DT-detail-wrap">
																	<aside class="DT-face">
																		<a title="${weibo.showname}" href="${ctx}/p/${weibo.cusId}/home">
																		<c:if test="${weibo.avatar!=null&&weibo.avatar!='' }">
																			<img width="50" height="50" alt="" src="<%=staticImageServer%>${weibo.avatar }">
																		</c:if>
																		<c:if test="${weibo.avatar==null||weibo.avatar=='' }">
																			<img width="50" height="50" alt="" src="${ctximg}/static/sns/pics/user.jpg">
																		</c:if>
																		</a>
																	</aside>
																	<div class="DT-detail">
																		<section class="DT-name">
																			<a class="c-blue dt-xm" title="${weibo.showname}" href="${ctx}/p/${weibo.cusId}/home">${weibo.showname}</a>
																			<span class="c-888"></span>
																			<span class="c-555">发表了观点：</span>
																		</section>
																		<section class="DT-text">
																			${weibo.content}
																		</section>
																		<section class="mt10">
																			<div class="clearfix">
																				<section class="fr">
																					 <c:if test="${empty weibo.cusAttentionId && weibo.cusId!=cusId}">
																					<a class="c-blue"href="javascript:void(0)" onclick="attention(${weibo.cusId},this)">关注</a>&nbsp;
																					</c:if> 
																					<a class="DT-comment-btn c-blue" href="javascript: void(0)">评论(<span id="commentNum${weibo.id }">${weibo.commentNum}</span>)</a>
																				</section>
																				<section class="c-b-green"><fmt:formatDate value="${weibo.addTime}" type="both" ></fmt:formatDate></section>
																			</div>
																		</section>
																		<div class="DT-comment-wrap pingl undis" weiboId="${weibo.id}" ></div>
																	</div>
																</article>
															</c:forEach>
														</div>
													</c:if>
													<!-- 问题结果 -->
													<c:if test="${search.tab=='sug'}">
														<section class="comm-title-3">
															<span class="c-t-2-l">问题搜索结果：</span>
															<div class="c-t-line">&nbsp;</div>
														</section>
														<c:forEach items="${datalist}" var="sslst">
															<div class="Q-article-list-2">
																<ul>
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
																</ul>
															</div>
														</c:forEach>
													</c:if>
													
													<!-- 小组 结果 -->
													<c:if test="${search.tab=='dis'}">
														<section class="comm-title-3">
															<span class="c-t-2-l">小组搜索结果：</span>
															<div class="c-t-line">&nbsp;</div>
														</section>
														<ul class="qun-list pl20 mt10">
															<c:forEach items="${datalist }" var="dis">
																<li>
																	<section class="clearfix">
																		<aside class="fl ">
																			<span class="qun-face"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></span>
																		</aside>
																		<div class="fl qun-info-wrap" style="width: 570px;">
																			<section class="qun-ann-btn">
																				<p><a class="browes-qun c-blue" title="" href="${ctx}/dis/info/${dis.id}"><i class="icon16 mr5">&nbsp;</i>浏览该小组</a></p>
																			</section>
																			<dl class="qun-info-txt">
																				<dt><span class="c-555 fsize14">名称：</span></dt>
																				<dd><a href="${ctx}/dis/info/${dis.id}"><span class="c-blue fsize14">${dis.name}</span></a></dd>
																			</dl>
																			<dl class="qun-info-txt">
																				<dt><span class="c-555 fsize14">成员：</span></dt>
																				<dd><span class="c-555 fsize14">${dis.memberNum}人</span></dd>
																			</dl>
																			<dl class="qun-info-txt">
																				<dt><span class="c-555 fsize14">创建：</span></dt>
																				<dd><span class="c-555 fsize14"><fmt:formatDate value="${dis.createTime}" type="both" ></fmt:formatDate></span></dd>
																			</dl>
																			<dl class="qun-info-txt">
																				<dt><span class="c-555 fsize14">组长：</span></dt>
																				<dd><a href="${ctx}/p/${dis.cusId}/home"  target="_blank"><span class="c-blue fsize14">${dis.showName}</span></a></dd>
																			</dl>
																			<dl class="qun-info-txt">
																				<dt><span class="c-555 fsize14">小组文章数：</span></dt>
																				<dd><span class="c-blue fsize14">${dis.articleCounts}</span></dd>
																			</dl>
																		</div>
																	</section>
																</li>
																</c:forEach>
															</ul>
													</c:if>
												</c:otherwise>
											</c:choose>
										</div>
									</article>
								</div>
								<!-- 搜索结果区域 -->
							</div>

							<!-- 公共分页 开始 -->
							<jsp:include page="/WEB-INF/view/sns/common/page.jsp" />
							<!-- 公共分页 结束 -->

						</section>
					</div>
				</section>
				<!-- 主体区域 -->
</body>
</html>