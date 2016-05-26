<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${not empty studylist }">
<dl >
	<dt><span>最近</span></dt>
	<c:forEach items="${studylist }" var="studylist" varStatus="index">
		<c:if test="${index.index<3}">
			<dd>
				<a href="${ctx }/front/playkpoint/${studylist.courseId}/?kpointId=${studylist.kpointId}" title="${studylist.courseId}" class="m-c-go fr">继续学习</a>
				<a href="${ctx }/front/playkpoint/${studylist.courseId}/?kpointId=${studylist.kpointId}" title="${studylist.kpointName}" class="m-c-link">${studylist.kpointName}</a>
			</dd>
		</c:if>
	</c:forEach>
	<c:if test="${studylist.size()>3 }">
		<dt><span>更早</span></dt>
		<c:forEach items="${studylist }" var="studylist" varStatus="index">
			<c:if test="${index.index>=3 && index.index<6}">
				<dd>
					<a href="${ctx }/front/playkpoint/${studylist.courseId}/?kpointId=${studylist.kpointId}" title="${studylist.courseId}" class="m-c-go fr">继续学习</a>
					<a href="${ctx }/front/playkpoint/${studylist.courseId}/?kpointId=${studylist.kpointId}" title="${studylist.kpointName}" class="m-c-link">${studylist.kpointName}</a>
				</dd>
			</c:if>
		</c:forEach>
	</c:if>
</dl>
</c:if>
