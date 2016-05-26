<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<c:if test="${not empty phaseList}">
	<c:forEach items="${phaseList}" var="phase" varStatus="index">
		<tr>
			<td>
				${phase.id}
			</td>
			<td>
				${phase.phaseName}
			</td>
			<td>
				${phase.phaseDescribe}
			</td>
			<td>
				${phase.studyTimeNo}
			</td>
			<td>
				${phase.averageComplete}
			</td>
			<td>
				<fmt:formatNumber value="${phase.progressPercentage}" type="percent" />
			</td>
			<td>
				<a href="javascript:void(0);" onclick="phaseDetailProgress(${phase.id})" title="详情" class="am-btn am-btn-link">详情</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="7">
			<jsp:include page="/WEB-INF/view/common/admin_page.jsp" />
		</td>
	</tr>
</c:if>
</form>
<c:if test="${empty phaseList}">
	<tr>
		<td colspan="7">
			<div data-am-alert=""
				 class="am-alert am-alert-secondary mt20 mb50">
				<center>
					<big> <i class="am-icon-frown-o vam"
							 style="font-size: 34px;"></i> <span class="vam ml10">没有学习内容！</span></big>
				</center>
			</div>
		</td>
	</tr>
</c:if>
