<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<aside class="W-main-l">
	<jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	<div class="l-o-menu">
		<div class="mt20">
			<section class="l-level">
				<a href="${ctx}/letter" title="站内信"
					class="lev-8 letterNum"> <i class="icon18">&nbsp;</i> 站内信
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/letter/sys" title="系统通知"
					class="lev-8 systemNum"> <i class="icon18">&nbsp;</i> 系统通知
				</a>
			</section>
		</div>
	</div>
</aside>
