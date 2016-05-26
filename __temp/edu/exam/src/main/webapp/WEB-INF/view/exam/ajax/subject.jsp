<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<div class="d-tips-9">
	<section>
	<h4 class="d-s-head pr"><a class="dtClose icon14 pa" title="关闭" href="javascript:void(0)">&nbsp;</a>
		<span class="d-s-head-txt"><em class="allKmIco">&nbsp;</em>请选择您考试的项目</span></h4>
	<section class="allItemBox mt10">
	<div class="clearfix allItemWrap">
		<c:forEach items="${professionalList}" var="professional" varStatus="status">
		<dl>
			<dt>
				<span>
				<c:choose>
					<c:when test="${empty professional.subjectList}">
					<a title="${professional.professionalName}" href="${ctx}/subj/addSubjectCookies?subject.subjectId=${subjectList.subjectId}">${subjectList.subjectName}</a>
					</c:when>
					<c:otherwise>${professional.professionalName}</c:otherwise>
				</c:choose>
				</span>
			</dt>
			<dd>
				<c:forEach items="${professional.subjectList}" var="subject" varStatus="childstatus">
					<a title="${subject.subjectName}" href="${ctx}/subj/addSubjectCookies?subject.subjectId=${subject.subjectId}">${subject.subjectName}</a>
					<c:if test="${childstatus.index<professional.subjectList.size()-1}">|</c:if>
				</c:forEach>
			</dd>
		</dl>
		</c:forEach>
	</div>
	</section></section>
</div>