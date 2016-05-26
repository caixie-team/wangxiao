<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<aside class="W-main-l">
	<jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	<div class="l-o-menu">
		<div class="mt20">
			<%-- <section class="l-level">
				<a href="${ctx}/friend" title="我的好友" class=" friend lev-14">
					<i class="icon18">&nbsp;</i> 我的好友
				</a>
			</section> --%>
			<section class="l-level">
				<a href="${ctx}/friend" title="我的关注"
					class=" attent lev-14 lev-14"> <i class="icon18">&nbsp;</i> 我的关注
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/friend/fans" title="我的学友"
					class=" fans lev-14"> <i class="icon18">&nbsp;</i> 我的学友
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/friend/black" title="我的黑名单"
					class=" black lev-14"> <i class="icon18">&nbsp;</i> 我的黑名单
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/friend/visitor" title="最近访客"
					class=" visitor lev-14"> <i class="icon18">&nbsp;</i> 最近访客
				</a>
			</section> 
		</div>
	</div>
</aside>
