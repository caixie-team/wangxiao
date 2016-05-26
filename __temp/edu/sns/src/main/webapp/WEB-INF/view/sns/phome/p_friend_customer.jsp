<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
	<title>关注</title>
	<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
	<script src="${ctximg}/static/sns/js/friend/friend.js?v=<%=version%>" type="text/javascript"></script>
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
						<section class="W-main-center W-main-c-r">
							<div class="pl20">
								<section class="mt10 pr20">
									<header class="comm-title-1">
										<ul class="clearfix">
											<li class="current"><a title="关注"
												href="javascript:void(0)">关注</a>
												<div class="ct-tabarrow-bg">&nbsp;</div></li>
										</ul>
									</header>
								</section>
								<section class="myF-list-box clearfix">
									<c:if test="${empty queryCustomerList}">
										<div class="Prompt">
											<img class="vam disIb"
												src="${ctximg}/static/sns/images/tishi.png">
											<c:if test="${loginId==userid }">
											<p class="vam c-555 fsize14 disIb">您还没有关注呢。</p>
											</c:if>
											<c:if test="${loginId!=userid }">
											<p class="vam c-555 fsize14 disIb">他还没有关注呢。</p>
											</c:if>
										</div>
									</c:if>
									<c:forEach items="${queryCustomerList}" var="cusList">
										<article class="myF-l-box" id="del${cusList.cusId }">
											<div class="F-face">
												<a href="${ctx}/p/${cusList.cusId}/home" title="">
												<c:if test="${cusList.userExpandDto.avatar==null||cusList.userExpandDto.avatar=='' }">
												<img
													src="${ctximg}/static/sns/pics/user.jpg" height="50"
													width="50" alt="">
													</c:if>
													<c:if test="${cusList.userExpandDto.avatar!=null&&cusList.userExpandDto.avatar!='' }">
												<img
													src="<%=staticImageServer%>${cusList.userExpandDto.avatar}" height="50"
													width="50" alt="">
													</c:if>
													</a>
											</div>
											<ul class="F-info">
												<li><a href="${ctx}/p/${cusList.cusId}/home" title=""
													class="c-blue customerName${cusList.cusId}"> <c:choose>
															<c:when test="${cusList.remarks!=''&&userid==loginId}">
														${cusList.remarks}
														</c:when>
															<c:otherwise>
														${cusList.userExpandDto.showname}
														</c:otherwise>
														</c:choose></a></li>
												<li><span class="c-888">
												<c:if test="${cusList.userExpandDto.genderName=='女'}">
												<tt class="girl vam">
															<i class="icon12">&nbsp;</i>
														</tt> </c:if> 
														<c:if test="${cusList.userExpandDto.genderName=='男'}">
												<tt class="boy vam">
															<i class="icon12">&nbsp;</i>
														</tt> </c:if>
														<!-- <tt class="ml10 fsize12 vam">35岁</tt> --></span></li>
												<li><span class="c-888">${cusList.userExpandDto.job }</span><span
													class="c-888 ml10">${cusList.userExpandDto.typeName }</span></li>
											</ul>
											<div class="F-intor">简介：${cusList.userExpandDto.shortSummary}</div>
											<div class="clearfix mt5">
												<span class="fr"> <%-- <a href="javascript:void(0)"
													title="删除好友" onclick="delFriend(${cusList.cusId })"
													class="F-remove"><i class="icon12 mr5">&nbsp;</i>删除好友</a> --%> 
													<c:if test="${cusList.cusId!=loginId}">
														<a
													href="javascript:void(0)" title="发消息"
													onclick="addLetterInput('${cusList.userExpandDto.showname}',${cusList.cusId },this)"
													class="F-remove sendmessage${cusList.cusId }"><i
														class="icon12 mr5">&nbsp;</i>发消息</a> 
													</c:if>
													
														<%-- <a
													href="javascript:void(0)" title="备注"
													id="buttonremarks${cusList.cusId }"
													onclick="toaddremarks(${cusList.cusId },this)"
													class="F-remove"><i class="icon12 mr5">&nbsp;</i>备注</a>  --%><c:if
														test="${empty cusList.cusAttentionId}">
														<a href="javascript:void(0)" title="关注"
															onclick="attention(${cusList.cusId },this)" class="F-gz"><i
															class="icon12 mr5">&nbsp;</i>关注</a>
													</c:if></span>
											</div>
											<br /> <span id="remarks${cusList.cusId }"
												style="display: none;"><input type="text"
												id="remarksContent${cusList.cusId }" /> <input
												class="reply-sub ml10" value="保存" type="button"
												onclick="addremarks(${cusList.cusId })" /></span>
										</article>
									</c:forEach>
								</section>

								<!-- 公共分页 开始 -->
								<jsp:include page="/WEB-INF/view/sns/common/page.jsp" />
								<!-- 公共分页 结束 -->
							</div>
						</section>
					</div>
				</section>
				<!-- 主体区域 -->
		<!-- footer -->
	<form action="${ctx}/p/${userid }/fri" name="searchForm"
		id="searchForm" method="post">
		<input id="pageCurrentPage" type="hidden" name="page.currentPage"
			value="${page.currentPage}" />
	</form>
</body>
