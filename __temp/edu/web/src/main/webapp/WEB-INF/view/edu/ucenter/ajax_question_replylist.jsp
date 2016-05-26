<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>

<c:if test="${fn:length(sugSuggestReplyList)==0 }">
<section class="comm-tips-1">
		<p>
			<%-- <img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb"> --%>
			<em class="vam c-tips-1">&nbsp;</em> <font class="c-999 fsize12 vam">还没有评论，快去抢沙发~</font>
		</p>
	</section>
	<%-- <div class="Prompt" id="shafa">
		<img src="${ctximg}/static/sns/images/tishi.png" class="vam disIb">
		<p class="vam c-555 fsize14 disIb">还没有评论，快去抢沙发~</p>
	</div> --%>
</c:if>
<c:forEach items="${sugSuggestReplyList}" var="br">
	<div class="o-answer-list">
		<div class="clearfix">
			<span class="fr"><font class="c-999">${br.modelStr }</font></span> <span
				class="fl"><tt class="c-blue">
					<span>${br.showname}</span>
				</tt></span>
		</div>
		<article class="answer-txt-Q mt10 unBr">${br.content}</article>
	</div>
	<c:if
		test="${sugSuggest.cusId==cusId&&sugSuggest.status==0&&cusId!=br.cusId }">
		<div class="QA_myAnswer_bt" style="text-align: right">
			<a class="q-submit-btn" title="采纳为答案" href="javascript:void(0)"
				onclick="recommend('${br.id }')" style="background-color: #46B300">
				采纳为答案 </a>
		</div>
	</c:if>
</c:forEach>

<!-- 公共分页 开始 -->
<jsp:include page="/WEB-INF/view/common/u_ajaxpage.jsp"></jsp:include>
