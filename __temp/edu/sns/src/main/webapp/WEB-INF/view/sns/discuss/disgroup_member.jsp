	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>小组成员</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc" style="overflow: visible">
						<section class="W-main-center W-main-c-r" style="overflow: visible;">
							<div class="pl20 pr20">
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="one current">
												<a title="小组长" href="javascript:void(0)">小组长</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<div>
									<section id="QM-list" class="QM-list">
										<ol class="clearfix">
											<li>
												<a href="" title="" class="QM-face">
													<c:if test="${disMember.userExpandDto.avatar==null||disMember.userExpandDto.avatar=='' }">
														<img src="${ctximg}/static/sns/pics/user.jpg" height="80" width="80" alt="">
														</c:if>
														<c:if test="${disMember.userExpandDto.avatar!=null&&disMember.userExpandDto.avatar!='' }">
														<img src="<%=staticImageServer%>${disMember.userExpandDto.avatar}" height="80" width="80" alt="">
														</c:if>
													<p class="mt5" >${disMember.showName }</p>
												</a>
												<div class="QM-into-wrap">
													<section class="QM-into">
														<div class="F-face">
															<a title="" href="${ctx}/p/${dmal.cusId}/home">
															<c:if test="${disMember.userExpandDto.avatar==null||dmal.disMember.avatar=='' }">
															<img height="50" width="50" alt="" src="${ctximg}/static/sns/pics/user.jpg">
															</c:if>
															<c:if test="${disMember.userExpandDto.avatar!=null&&disMember.userExpandDto.avatar!='' }">
															<img height="50" width="50" alt="" src="<%=staticImageServer%>${disMember.userExpandDto.avatar}">
															</c:if>
															</a>
														</div>
														<ul class="F-info">
															<li><a class="c-blue" title="" href="${ctx}/p/${disMember.cusId}/home">${disMember.showName }</a></li>
															<li><span class="c-888">
															<!-- <tt class="boy vam"><i class="icon12">&nbsp;</i></tt> -->
															 <c:if test="${disMember.userExpandDto.gender=='0' }">
															<tt class="boy vam"><i class="icon12">&nbsp;</i></tt>
															</c:if>
															<c:if test="${disMember.userExpandDto.gender=='1' }">
															<tt class="girl vam"><i class="icon12">&nbsp;</i></tt>
															</c:if>
															<!-- <tt class="ml10 fsize12 vam">35岁</tt> --></span></li>
															<%-- <li><span class="c-888">${dmal.userExpandDto.job }</span><span class="c-888 ml10">${dmal.userExpandDto.typeName }</span></li> --%>
														</ul>
														<div class="F-intor">
															简介：....
														</div>
														<%-- <div class="clearfix mt5">
															<span class="fr c-bbb">
																<c:if test="${disMember.cusId!=loginId }">
																<a class="F-gz mr10" title="关注" href="javascript:void(0)" onclick="attention(${dmal.cusId },this)"><i class="icon12">&nbsp;</i>+关注</a> 
																 <a class="F-zzh ml10" title="关注" href="javascript:void(0)" onclick="addFriend(${disMember.cusId })"><i class="icon12">&nbsp;</i>+关注</a>
																 </c:if> 
															</span>
														</div> --%>
													</section>
												</div>
											</li>
										</ol>
									</section>
								</div>
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="one current">
												<a title="小组管理员" href="javascript:void(0)">管理员</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<div>
									<section id="QM-list" class="QM-list">
										<ol class="clearfix">
										<c:forEach items="${adminList}" var="dmal">
											<li style="margin: 30px 29px 0;" id="delmeb${dmal.cusId}">
												<a href="" title="" class="QM-face">
														<c:if test="${dmal.userExpandDto.avatar==null||dmal.userExpandDto.avatar=='' }">
														<img src="${ctximg}/static/sns/pics/user.jpg" height="80" width="80" alt="">
														</c:if>
														<c:if test="${dmal.userExpandDto.avatar!=null&&dmal.userExpandDto.avatar!='' }">
														<img src="<%=staticImageServer%>${dmal.userExpandDto.avatar}" height="80" width="80" alt="">
														</c:if>
													<p  class="mt5">${dmal.showName }</p>
												</a>
												<div class="QM-into-wrap">
													<section class="QM-into">
														<div class="F-face">
															<a title="" href="${ctx}/p/${dmal.cusId}/home">
															<c:if test="${dmal.userExpandDto.avatar==null||dmal.userExpandDto.avatar=='' }">
															<img height="50" width="50" alt="" src="${ctximg}/static/sns/pics/user.jpg">
															</c:if>
															<c:if test="${dmal.userExpandDto.avatar!=null&&dmal.userExpandDto.avatar!=''  }">
														<img src="<%=staticImageServer%>${dmal.userExpandDto.avatar}" height="50" width="50" alt="">
														</c:if>
															</a>
														</div>
														<ul class="F-info">
															<li><a class="c-blue" title="" href="${ctx}/p/${dmal.cusId}/home">${dmal.showName }</a></li>
															<li><span class="c-888">
															<c:if test="${dmal.userExpandDto.gender==0 }">
															<tt class="boy vam"><i class="icon12">&nbsp;</i></tt>
															</c:if>
															<c:if test="${dmal.userExpandDto.gender==1 }">
															<tt class="girl vam"><i class="icon12">&nbsp;</i></tt>
															</c:if>
															<!-- <tt class="ml10 fsize12 vam">35岁</tt> --></span></li>
															<%-- <li><span class="c-888">${dmal.userExpandDto.job}</span><span class="c-888 ml10">${dmal.userExpandDto.typeName}</span></li> --%>
														</ul>
														<div class="F-intor">
															简介：......<%-- ${dmal.userExpandDto.shortSummary} --%>
														</div>
														<div class="clearfix mt5">
															<span class="fr c-bbb">
															<c:if test="${disMember.cusId==loginId&&dmal.cusId!=loginId}">
																<a class="F-remove mr10" title="免除管理员" href="javascript:void(0)" onclick="removal(${dmal.groupId },${dmal.cusId })"><i class="icon12 mr5">&nbsp;</i>免职</a>
																</c:if>
																<c:if test="${dmal.cusId==loginId&&dmal.transferId==0&&disMember.cusId!=loginId}">
																<a class="F-remove mr10" title="不在担任该小组的管理员" href="javascript:void(0)" onclick="removal(${dmal.groupId },${dmal.cusId })"><i class="icon12 mr5">&nbsp;</i>解除</a>
																</c:if>
																<c:if test="${dmal.cusAttentionId==null&&dmal.cusId!=loginId }">
																<%-- <a class="F-gz mr10" title="关注" href="javascript:void(0)" onclick="attention(${dmal.cusId },this)"><i class="icon12">&nbsp;</i>+关注</a> | --%>
																|<a class="F-zzh ml10" title="关注" href="javascript:void(0)" onclick="addFriend(${dmal.cusId })"><i class="icon12">&nbsp;</i>+关注</a>
																</c:if>
															</span>
														</div>
													</section>
												</div>
												
											</li>
											
											</c:forEach>
										</ol>
									</section>
								</div>
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="one current">
												<a title="小组成员" href="javascript:void(0)">小组成员</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
								</section>
								<!-- /挑战信息 -->
								<c:if test="${fn:length(disMemberALLList)==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有小组成员，快去让大家知道你的小组吧...</p>
								</div>
								</c:if>
								<div>
									<section id="QM-list" class="QM-list">
										<ol class="clearfix">
										<c:forEach items="${disMemberALLList}" var="dmal">
											<li style="margin: 30px 29px 0;" id="delmeb${dmal.cusId}">
												<a href="" title="" class="QM-face">
														<c:if test="${dmal.userExpandDto.avatar==null||dmal.userExpandDto.avatar=='' }">
														<img src="${ctximg}/static/sns/pics/user.jpg" height="80" width="80" alt="">
														</c:if>
														<c:if test="${dmal.userExpandDto.avatar!=null&&dmal.userExpandDto.avatar!='' }">
														<img src="<%=staticImageServer%>${dmal.userExpandDto.avatar}" height="80" width="80" alt="">
														</c:if>
													<p  class="mt5">${dmal.showName }</p>
												</a>
												<div class="QM-into-wrap">
													<section class="QM-into">
														<div class="F-face">
															<a title="" href="${ctx}/p/${dmal.cusId}/home">
															<c:if test="${dmal.userExpandDto.avatar==null||dmal.userExpandDto.avatar=='' }">
															<img height="50" width="50" alt="" src="${ctximg}/static/sns/pics/user.jpg">
															</c:if>
															<c:if test="${dmal.userExpandDto.avatar!=null&&dmal.userExpandDto.avatar!=''  }">
														<img src="<%=staticImageServer%>${dmal.userExpandDto.avatar}" height="50" width="50" alt="">
														</c:if>
															</a>
														</div>
														<ul class="F-info">
															<li><a class="c-blue" title="" href="${ctx}/p/${dmal.cusId}/home">${dmal.showName }</a></li>
															<li><span class="c-888">
															<c:if test="${dmal.userExpandDto.gender==0 }">
															<tt class="boy vam"><i class="icon12">&nbsp;</i></tt>
															</c:if>
															<c:if test="${dmal.userExpandDto.gender==1 }">
															<tt class="girl vam"><i class="icon12">&nbsp;</i></tt>
															</c:if>
															<!-- <tt class="ml10 fsize12 vam">35岁</tt> --></span></li>
															<%-- <li><span class="c-888">${dmal.userExpandDto.job}</span><span class="c-888 ml10">${dmal.userExpandDto.typeName}</span></li> --%>
														</ul>
														<div class="F-intor">
															简介：......<%-- ${dmal.userExpandDto.shortSummary} --%>
														</div>
														<div class="clearfix mt5">
															<span class="fr c-bbb">
															<c:if test="${isJoin!=0&&transfer==0&&dmal.cusId!=loginId&&dmal.transferId==1&&dmal.cusId!=disMember.cusId}">
																<a class="F-remove mr10" title="踢出小组" href="javascript:void(0)" onclick="delmember(${dmal.groupId },${dmal.cusId })"><i class="icon12 mr5">&nbsp;</i>踢出小组</a>
																</c:if>
																<c:if test="${dmal.cusAttentionId==null&&dmal.cusId!=loginId }">|
																<%-- <a class="F-gz mr10" title="关注" href="javascript:void(0)" onclick="attention(${dmal.cusId },this)"><i class="icon12">&nbsp;</i>+关注</a> | --%>
																<a class="F-zzh ml10" title="关注" href="javascript:void(0)" onclick="addFriend(${dmal.cusId })"><i class="icon12">&nbsp;</i>+关注</a>
																</c:if>
																<c:if test="${disMember.cusId==loginId&&dmal.cusId!=disMember.cusId&&dmal.transferId==1}">
																|<a class="F-gz mr10" title="提拔管理员" href="javascript:void(0)" onclick="promote(${dmal.groupId },${dmal.cusId })"><i class="icon12">&nbsp;</i>+提拔</a>
																</c:if> 
															</span>
														</div>
													</section>
												</div>
												
											</li>
											</c:forEach>
										</ol>
									</section>
								</div>
							</div>

							<!-- 公共分页 开始 -->
							<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
							<!-- 公共分页 结束 -->
							<form action="${ctx}/dis/memb/${groupId }" id="searchForm" method="post" name="searchForm">
								<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
							</form>
							</section>
							</div>
						</section>
</body>
</html>