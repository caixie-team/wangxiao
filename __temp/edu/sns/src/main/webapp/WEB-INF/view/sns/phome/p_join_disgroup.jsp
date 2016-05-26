<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<html>
<head>
	<title>加入的小组</title>
	<script type="text/javascript" src="${ctximg}/static/sns/js/discuss/group.js?v=<%=version%>"></script>
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
									<a href="${ctx}/dis/classify" class="fr c-888 mt10 mr10">创建小组&gt;&gt;</a>
									<ul class="clearfix">
										<li class="current"><a href="javascript:void(0)" title="加入的小组">加入的小组</a> <span
											class="c-green disIb mt10">共有${page.totalResultSize
												}个小组</span>
											<div class="ct-tabarrow-bg">&nbsp;</div></li>
									</ul>
								</header>
							</section>
							<!-- /挑战信息 -->
							<%-- <c:if test="${disMyJoinGroupList==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png"
										class="vam disIb">
									<p class="vam c-555 fsize14 disIb">他还没有加入小组...</p>
								</div>
							</c:if> --%>
							<c:if test="${fn:length(disMyJoinGroupList)==0 }">
								<div class="Prompt">
									<img src="${ctximg}/static/sns/images/tishi.png"
										class="vam disIb">
									<p class="vam c-555 fsize14 disIb">还没有加入小组，快去加入小组吧...</p>
								</div>
							</c:if>
							<section class="mt20">
								<ul class="qun-list">
									<c:forEach items="${disMyJoinGroupList }" var="dis">
										<li id="exitDisGroup${dis.id }">
											<section class="clearfix">
												<aside class="fl ">
													<c:if test="${dis.status==2 }">
													<span class="qun-face"><a href="javascript:void(0)"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
													</c:if>
													<c:if test="${dis.status==1 }">
													<span class="qun-face"><a href="${ctx}/dis/info/${dis.id}"><img height="105" width="105" alt="" src="<%=staticImageServer%>${dis.imageUrl}"></a></span>
													</c:if>
												</aside>
												<div class="fl qun-info-wrap">
													<section class="qun-ann-btn">
													<c:if test="${dis.status==1}">
														<p>
															<a class="browes-qun c-blue" title=""
																href="${ctx}/dis/info/${dis.id}"><i
																class="icon16 mr5">&nbsp;</i>浏览该小组</a>
														</p>
														</c:if>
																<c:if test="${dis.status==2 }">
													<p><a class="add-qun c-red" title="" href="javascript:void(0)"><i class="icon16 mr5">&nbsp;</i>小组关闭</a></p>
													</c:if>
													</section>
													<dl class="qun-info-txt">
													<c:if test="${dis.status==1}">
														<dt><span class="c-555 fsize14">名称：</span></dt>
														<dd><a href="${ctx}/dis/info/${dis.id}"><span class="c-blue fsize14">${dis.name}</span></a></dd>
														</c:if>
														<c:if test="${dis.status==3||dis.status==2||dis.status==0 }">
														<dt><span class="c-555 fsize14">名称：</span></dt>
														<dd><a href="javascript:void(0)"><span class="c-blue fsize14">${dis.name}</span></a></dd>
														</c:if>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">成员：</span>
														</dt>
														<dd>
															<span class="c-555 fsize14">${dis.memberNum}人</span>
														</dd>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">创建：</span>
														</dt>
														<dd>
															<span class="c-555 fsize14"><fmt:formatDate
																	value="${dis.createTime}" type="both" ></fmt:formatDate></span>
														</dd>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">组长：</span>
														</dt>
														<dd>
														<a href="${ctx}/p/${dis.cusId}/home" target="_blank"><span class="c-blue fsize14">${dis.showName}</span></a>
														</dd>
													</dl>
													<dl class="qun-info-txt">
														<dt>
															<span class="c-555 fsize14">小组文章数：</span>
														</dt>
														<c:if test="${dis.status==1}">
														<dd>
														<a href="${ctx}/dis/art/${dis.id}" target="_blank"><span class="c-blue fsize14">${dis.articleCounts}</span></a>
														</dd>
														</c:if>
														<c:if test="${dis.status==3||dis.status==2||dis.status==0 }">
														<span class="c-blue fsize14">${dis.articleCounts}</span>
														</c:if>
														
													</dl>
												</div>
											</section>
										</li>
									</c:forEach>
								</ul>
							</section>
						</div>


						<!-- 公共分页 开始 -->
						<jsp:include page="/WEB-INF/view/sns/common/page.jsp"></jsp:include>
						<!-- 公共分页 结束 -->
						<form action="${ctx}/p/${userid}/dis" name="searchForm"
							id="searchForm" method="post">
							<input id="pageCurrentPage" type="hidden" name="page.currentPage"
								value="${page.currentPage}" />
						</form>
					</section>
					<section class="W-main-right">
						<div class="pl20">
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l">小组搜索&nbsp;<i class="gt-btn"></i></span>
								</section>
								<section class="mt20 pr10">
										<form action="${ctx}/search" method="post" id="dissearchform"> 
											<input type="hidden" name="search.tab"   value="dis">
											<input type="text" name="search.keyword"  id="searchdis" class="boke-search-inp">
											<input type="hidden"   name="page.currentPage" value="1"/>
											<a href="javascript:dissearch()" class="comm-btn-orange"><span>搜索</span></a>
										</form>
								</section>
							</div>
							<div>
								<section class="comm-title-2">
									<span class="c-t-2-l">小组分类</span>
								</section>
								<section class="mt20 pr10">
									<ul class="boke-sort-list">
										<c:forEach items="${disGroupList }" var="disclassify">
											<li><a href="${ctx}/dis/classifygroup/${disclassify.id}" title="" class="fsize14 c-orange">${disclassify.name
													}</a><span class="c-green">（${disclassify.groupNum }）</span></li>
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