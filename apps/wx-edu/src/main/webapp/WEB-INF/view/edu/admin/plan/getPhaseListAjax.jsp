<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${phaseList.size() > 0}">
	<c:forEach var="phase" items="${phaseList}" varStatus="status">
		<div class="" onclick="SelectStudyPhase(${phase.id},${status.count})" style="white-space:nowrap;">
			<input type="hidden" id="phaseId${status.count}" value="${phase.id}">
			<span>阶段</span>
			<span class="">${status.count}</span>
			<span class="" title="${phase.phaseName}">${phase.phaseName}</span>
		</div>
	</c:forEach>
</c:if>
<script type="text/javascript">
	var phaseListSize = ${phaseListSize};
	$("#phaseListSize").val(phaseListSize)
</script>