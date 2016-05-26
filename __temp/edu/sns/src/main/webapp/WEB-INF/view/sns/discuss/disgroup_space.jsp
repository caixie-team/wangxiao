<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>小组-${disGroup.name}</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
	<style type="text/css">
	 .likes {
	float: left;
	margin-right: 19px;
	padding: 8px 0;
	width: 48px;
	line-height: 1.3;
	text-align: center;
	font-size: 13px;
	color: #ca6445;
	background: #fae9da;	
	</style>
	<script type="text/javascript">
	$(function(){
		ajaxPage("/dis/ajax/article","&groupId="+'${disGroup.id}',1,callback);
		});
	//群首页话题分页
	function callback(result){
		$(".disArticleList").html(result);
	}
	</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<!-- 左侧目录区域 -->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20">
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="current">
												<a href="javascript:void(0)" title="${disGroup.name}">${disGroup.name}</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
									<div class="mt20">
										<section class="clearfix">
											<aside class="fl ">
												<span class="qun-face"><img src="<%=staticImageServer%>${disGroup.imageUrl}" width="105" height="105" alt=""></span>
											</aside>
											<div class="fl qun-info-wrap">
												<section class="qun-ann-btn">
													<ol>
														<c:if test="${isJoin==0 }">
														<li><a href="javascript:addGroup(${disGroup.id})" title="加入该小组" class="comm-btn-orange"><span>加入该小组</span></a></li>
														</c:if>
														<c:if test="${isJoin!=0 }">
														<!-- <li><a href="javascript:void(0)" title="已加入该小组" class="comm-btn-green btnDisable"><span>已加入该小组</span></a></li> -->
														<c:if test="${isTransfer==1 }">
														<li><a href="javascript:void(0)" title="" class="comm-btn-orange" onclick="disexit(${disGroup.id},${disGroup.cusId})"><span>退出该小组</span></a></li> 
														</c:if>
														</c:if>
													</ol>
												</section>
												<dl class="qun-info-txt">
													<dt><span class="c-red fsize14">组长：</span></dt>
													<dd><span class="c-555 fsize14">${disGroup.showName}</span></dd>
												</dl>
												<dl class="qun-info-txt">
													<dt><span class="c-red fsize14">成员：</span></dt>
													<dd><span class="c-555 fsize14">${disGroup.memberNum}人</span></dd>
												</dl>
												<dl class="qun-info-txt">
													<dt><span class="c-red fsize14">创建：</span></dt>
													<dd><span class="c-555 fsize14"><fmt:formatDate value="${disGroup.createTime}" type="both" ></fmt:formatDate></span></dd>
												</dl>
												<dl class="qun-info-txt">
													<dt><span class="c-red fsize14">简介：</span></dt>
													<dd class="qun-info-into" style="width:432px "><span class="c-555 fsize12">${disGroup.introduction}</span></dd>
												</dl>
											</div>
										</section>
									</div>
								</section>
								<section class="mt10">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="current">
												<a href="javascript:void(0)" title="小组话题">小组话题</a>
												<div class="ct-tabarrow-bg">&nbsp;</div>
											</li>
										</ul>
									</header>
							<div class="Q-article-list-2 disArticleList">
						
							</div>	
					</section>
							</div>
							<%-- <!-- 公共分页 开始 -->
							<c:if test="${isJoin!=0 }">
							<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
							</c:if>
							<!-- 公共分页 结束 -->
							<form action="${ctx}/dis/info/${disGroup.id}" id="searchForm" method="post">
										<input id="pageCurrentPage" type="hidden" name="page.currentPage" value="${page.currentPage}" />
										<input type="hidden" name="groupId" value="${disGroup.id }">
										</form> --%>

						</section>
						<section class="W-main-right">
							<div class="pl20 pr10">
								<div>
									<section class="comm-title-2">
										<span class="c-t-2-l">小组最新成员</span>
									</section>
									<section class="mt20">
										<div class="visitor-list mt20">
											<ul class="clearfix" style="width: 195px;">
											<c:forEach items="${disMemberList }" var="dml">
											<%-- <c:if test="${dml.transferId==1}"></c:if> --%>
												<li>
													<a href="${ctx}/p/${dml.cusId}/home" title="" class="c-blue">
														<c:if test="${dml.userExpandDto.avatar==null||dml.userExpandDto.avatar=='' }">
														<img src="${ctximg}/static/sns/pics/user.jpg" height="40" width="40" alt="">
														</c:if>
														<c:if test="${dml.userExpandDto.avatar!=null&&dml.userExpandDto.avatar!='' }">
														<img src="<%=staticImageServer%>${dml.userExpandDto.avatar}" height="40" width="40" alt="">
														</c:if>
														<c:if test="${dml.showName==null||dml.showName==''}">
														<p class="mt5">&nbsp;</p>
														</c:if>
														<c:if test="${dml.showName!=null||dml.showName!=''}">
														<p class="mt5">${dml.showName}</p>
														</c:if>
													</a>
													<div class="mt5">
														<span class="c-bbb"><fmt:formatDate value="${dml.addTime}" pattern="MM月dd日" ></fmt:formatDate></span>
													</div>
												</li>
												
												</c:forEach>
											</ul>
										</div>
									</section>
									<div class="mt20 tac">
									<c:if test="${loginId!=0 }">
										<a href="${ctx}/dis/memb/${disGroup.id}" title="" class="c-green"><u>成员管理&gt;&gt;</u></a><br/>
										<c:if test="${isTransfer==0&&isJoin!=0}">
										<a href="${ctx}/dis/disedit/${disGroup.id}" title="" class="c-green"><u>小组管理&gt;&gt;</u></a><br/>
										</c:if>
										<a class="c-green" href="javascript:void(0)" onclick="isJoinPublish(${isJoin},${groupId},0)"><u>发表话题&gt;&gt;</u></a><br>
										<a href="javascript:void(0)" class="c-green" onclick="isJoinPublish(${isJoin},${groupId},1)"><u>发布线下活动&gt;&gt;</u></a>
										</c:if>
									</div>
								</div>
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
</body>
</html>