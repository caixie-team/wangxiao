<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/base.jsp"%>

<section class="comm-title-2" style="margin-bottom: 20px;">
	<span class="c-t-2-l" style="font-weight: normal;"><tt
			class="c-333 fsize16 f-fM">评论列表</tt></span>
</section>
<c:if test="${fn:length(blogReplyList)==0 }">
	<div class="Prompt" id="shafa">
		<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
		<p class="vam c-555 fsize14 disIb">还没有评论，快去抢沙发~</p>
	</div>
</c:if>
<section class="blog-comment-list" id="relpylist">
	<c:forEach items="${blogReplyList }" var="br">
		<dl class="blog-comment-cont rem${br.id }">
			<dd class="b-c-nr clearfix">
				<div class="fl BC-head">
				<c:if test="${empty br.userExpandDto||empty br.userExpandDto.avatar}">
				<a href="${ctx}/p/${br.cusId}/home"><img
						src="${ctximg}/static/sns/pics/user.jpg" height="50"
						width="50" /></a>
				</c:if>
				<c:if test="${not empty br.userExpandDto && not empty br.userExpandDto.avatar}">
					<a href="${ctx}/p/${br.cusId}/home"><img
						src="<%=staticImageServer%>${br.userExpandDto.avatar}" height="50"
						width="50" /></a>
						</c:if>
					
				</div>
				<div class="fl BC-TxtAtt">
					<h4 class="of">
						<span class="fr"><a href="javascript:void(0)" title="回复"
							class="b-c-reply-btn fsize12"
							onclick="otherreply(${br.blogId},'${br.showName}',${br.id })"><i
								class="icon12">&nbsp;</i> 回复</a> <c:if test="${br.cusId==loginId }">
								<a class="b-c-delete-btn c-888 ml10 fsize12" title="删除"
									href="javascript:void(0)"
									onclick="delreply(${br.blogId},${br.id})"> <i
									class="icon16 fsize12"> </i> 删除
								</a>
							</c:if> </span> 
							<span class="c-888 fsize12"><tt class="c-blue">
								<a href="${ctx}/p/${br.cusId}/home" class="c-blue">${br.showName
									}</a>
							</tt>评论于：<tt class="fsize12">
								${br.modelStr }
							</tt></span>
					</h4>
					<div class="b-c-cont-txt mt5 huifucontent${br.id }">
						<p>${br.content}</p>
					</div>
				</div>
			</dd>
			<dd class="lineBetween" style="left: -4px; width: 605px">&nbsp;</dd>
		</dl>
	</c:forEach>
</section>
<!-- 公共分页 开始 -->
<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"></jsp:include>
