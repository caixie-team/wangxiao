<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<ul class="moka-list paperList">
	<!-- <div class="mt20 mb20 tac">
		<img src="/static/exam/images/loading.gif" width="32" height="32">
	</div> -->
	<c:if test="${paperList!=null&&paperList.size()>0}">
		<c:forEach items="${paperList}" var="item">
			<li>
				<div class="c-666 fsize16">${item.name}</div>
				<p class="c-999 mt5">
					<span class="mr10">试卷难度： <c:if test="${item.level==1}">
							<font class="fsize14 c-orange">简单</font>
						</c:if> <c:if test="${item.level==2}">
							<font class="fsize14 c-orange">中等</font>
						</c:if> <c:if test="${item.level==3}">
							<font class="fsize14 c-orange">困难</font>
						</c:if>
					</span><span class="mr10">考试时间：<font class="fsize14 c-orange">${item.replyTime}</font>分钟
					</span><span></span>
				</p> <span class="moka-btn"><a
					href="/paper/toExamPaper/${item.id}" title="">进入模考</a></span>
			</li>
		</c:forEach>
	</c:if>
	<c:if test="${paperList==null||paperList.size()==0 }">
		<li><div class="c-666 fsize16">暂无数据</div></li>
	</c:if>
</ul>
<!-- 公共分页 开始 -->
<jsp:include page="/WEB-INF/view/exam/common/ajaxpage.jsp"></jsp:include>