<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="comm-title-2" style="margin-bottom: 20px;">
	<span class="c-t-2-l" style="font-weight: normal;">
	<tt class="c-333 fsize16 f-fM">问题回复</tt></span>
</section>
<c:if test="${fn:length(sugSuggestReplyList)==0 }">
	<div class="Prompt" id="shafa">
		<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
		<p class="vam c-555 fsize14 disIb">还没有评论，快去抢沙发~</p>
	</div>
</c:if>
<section class="blog-comment-list" id="relpylist">
	<c:forEach items="${sugSuggestReplyList }" var="br">
		<dl class="blog-comment-cont rem${br.id }">
			<dd class="b-c-nr clearfix">
				<div class="fl BC-head">
					<a title="${br.showname}" target="_blank" href="/p/${br.cusId }/home"><c:if test="${br.userExpandDto.avatar!=null&&br.userExpandDto.avatar!='' }">
						<img width="50" height="50" alt="" src="<%=staticImageServer%>${br.userExpandDto.avatar }">
					</c:if>
					<c:if test="${br.userExpandDto.avatar==null||br.userExpandDto.avatar=='' }">
						<img width="50" height="50" alt="" src="${ctximg}/static/sns/pics/user.jpg">
					</c:if></a>
				</div>
				<div class="fl BC-TxtAtt">
					<h4 class="of">
						<span class="c-888 fsize12"><tt class="b-c-reply-btn"><a href="${ctx}/p/${br.cusId }/home">${br.showname}</a></tt>
						评论于：<tt class="fsize12" title="<fmt:formatDate type="both" value="${br.addtime }"/>">${br.modelStr }</tt></span>
							<span class="fr"> <c:if
								test="${sugSuggest.cusId==cusId&&sugSuggest.status==0&&cusId!=br.cusId }">
								<a href="javascript:void(0)" title="采纳为最佳答案"
									onclick="recommend('${br.id }')" class="comm-btn-gray mr10"><span>采纳为最佳答案</span></a>
								<a href="javascript:void(0)" title="回复" class="b-c-reply-btn"
									onclick="suggestHuifu(${br.id },'${br.userExpandDto.showname}')">
									<i class="icon12">&nbsp;</i> 回复</a>
							</c:if> <c:if test="${cusId==br.cusId&&sugSuggest.status==0 }">
								<a class="b-c-delete-btn c-888 ml10 fsize12" title="删除"
									href="javascript:void(0)" onclick="delconfirm(${br.id})"> <i
									class="icon16 "> </i> 删除
								</a>
							</c:if>
						</span>
					</h4>
					<div class="b-c-cont-txt mt5 huifucontent${br.id }">
						<p>${br.content}</p>
					</div>
					<div id="replyAnswer${br.id}"></div>
				</div>
			</dd>
			<dd class="lineBetween" style="left: -4px; width: 605px">&nbsp;</dd>
		</dl>
	</c:forEach>
</section>
<!-- 公共分页 开始 -->
<jsp:include page="/WEB-INF/view/sns/common/ajaxpage.jsp"></jsp:include>
