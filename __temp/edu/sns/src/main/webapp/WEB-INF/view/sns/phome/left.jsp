<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<script type="text/javascript" src="${ctximg}/static/sns/js/phome/left.js?v=<%=version%>"></script>
<script type="text/javascript">
	var userid="${userid}";
	$(function() {
		leftshow();
	});
</script>
<aside class="W-main-l">
					<div class="l-person-info">
						<section class="l-p-i-tx">
							<span>	<a href="${ctx}/p/${userid}/home" title="${showname}"><c:if test="${userExpandDto.avatar==null||userExpandDto.avatar=='' }">
							<img src="${ctximg}/static/sns/pics/user.jpg" height="150" width="150" alt="" class="" >
							</c:if>
							<c:if test="${userExpandDto.avatar!=null&&userExpandDto.avatar!='' }">
							<img src="<%=staticImageServer%>${userExpandDto.avatar}" height="150" width="150" alt="" class="" >
							</c:if></a></span>
						</section>
						<section class="l-p-i-name">
							<a href="javascript:void(0)" class="csName" title="${userExpandDto.showname}">${userExpandDto.showname}</a>
							<%-- <div >关注：<span class="">${customer.attentionNum }</span></div>
							<div >粉丝：<span class="">${customer.fansNum }</span></div>
							<div >观点：<span class="">${customer.weiBoNum }</span></div> --%>
						</section>
					</div>
					<div class="l-o-menu">
						<div class="mt20">
							<section class="l-level">
								<a href="${ctx}/p/${userid}/home" title="主页" class="lev-13 phome">
									<i class="icon18">&nbsp;</i>
									<c:if test="${userid==loginId }">
										主页
									</c:if>
									<c:if test="${userid!=loginId }">
										<c:if test="${userExpandDto.genderName=='男' }">
										他的主页
										</c:if>
										<c:if test="${userExpandDto.genderName=='女' }">
										她的主页
										</c:if>
									</c:if>
								</a>
							</section>
							<section class="l-level">
								<a href="${ctx}/p/${userid }/fri" title="好友" class="lev-14 pfri">
									<i class="icon18">&nbsp;</i>
									好友
								</a>
							</section>
							<section class="l-level">
								<a href="${ctx}/p/${userid}/dis" title="小组" class="lev-15 pdis">
									<i class="icon18">&nbsp;</i>
									小组
								</a>
							</section>
							<section class="l-level">
								<a href="${ctx}/p/${userid}/blog" title="博客" class="lev-7 pblog">
									<i class="icon18">&nbsp;</i>
									博客
								</a>
							</section>
							<section class="l-level">
								<a href="${ctx}/p/${userid}/weibo" title="观点" class="lev-6 pweibo">
									<i class="icon18">&nbsp;</i>
									观点
								</a>
							</section>
							<section class="l-level">
								<a href="${ctx}/p/${userid}/sug" title="同学问答" class="lev-9 sug">
									<i class="icon18">&nbsp;</i>
									同学问答
								</a>
							</section>
							<c:if test="${userid==loginId }">
							<section class="l-level">
								<a href="javascript:void(0)" title="消息" class="lev-8 unReadNum" id="msg">
								</a>
							</section>
							</c:if>
						</div>
					</div>
				</aside>
