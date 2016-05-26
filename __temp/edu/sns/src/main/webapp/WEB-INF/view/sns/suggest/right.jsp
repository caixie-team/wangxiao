<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<section class="W-main-right">
	<div class="pl20 pr20">
		<div>
			<section class="comm-title-2">
				<span class="c-t-2-r"><a href="${ctx}/sug/zh/1"
					title="更多" class="c-555">更多</a></span> <span class="c-t-2-l">最新问题</span>
			</section>
			<section class="cj-comment-list wisdom-list">
				<c:forEach items="${sugSuggestWisdomList}" var="sswlst"
					varStatus="index">
					<dl class="clearfix">
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
								<a class="c-555"
									href="javascript:window.location.href='${ctx}/sug/info/${sswlst.id }'">${sswlst.title}</a>
							</div>
						</dd>
					</dl>
				</c:forEach>
			</section>
		</div>
	</div>
	<div class="mt20 pl20">
		<a class="comm-btn-green" title="同学问答" href="${ctx}/sug"><span style="width: 80px;">同学问答&gt;&gt;</span></a><br><br>
		<a class="comm-btn-green" title="我的问题" href="${ctx}/sug/my"><span style="width: 80px;">我的问题&gt;&gt;</span></a><br><br>
		<a class="comm-btn-green" title="我的问题" href="${ctx}/sug/add"><span style="width: 80px;">提问题&gt;&gt;</span></a><br><br>
	</div>
</section>