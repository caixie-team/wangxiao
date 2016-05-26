<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="comm-title-2">
	<span class="c-t-2-r"></span> <span class="c-t-2-l">热门话题</span>
</section>
<section class="cj-comment-list">

	<c:forEach items="${disArticleList }" var="faclt" varStatus="index">
		<dl class="clearfix ${index.count==1?'cj-c-d-show':'' }">
			<c:choose>
				<c:when test="${index.count==1}">
					<dt>
						<span class="cjc-1"></span>
					</dt>
				</c:when>
				<c:when test="${index.count==2}">
					<dt>
						<span class="cjc-2"></span>
					</dt>

				</c:when>
				<c:when test="${index.count==3}">
					<dt>
						<span class="cjc-3"></span>
					</dt>
				</c:when>
				<c:otherwise>
					<dt>
						<span>${index.count }</span>
					</dt>
				</c:otherwise>
			</c:choose>
			<dd>
				<div class="cj-c-txt">
					<a href="${ctx}/dis/artinfor/${faclt.id }/${faclt.groupId}" target="_blank"
						title="${faclt.title }" class="c-blue">${faclt.title }</a>
				</div>
				<div class="cj-c-desc-wrap">
					<div class="DT-arrow"><em>◆</em><span>◆</span></div>
					<div class="cj-c-desc">${fn:substring(faclt.summary,0,30)}</div>
				</div>
				<div class="clearfix mt5">
					<span class="fr c-888"><fmt:formatDate
							value="${faclt.createTime}" /></span> <span class="fl c-888"><i
						class="icon12 oComment-icon mr5">&nbsp;</i>${faclt.showName }</span>
				</div>
			</dd>
		</dl>
	</c:forEach>
</section>