<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<aside class="W-main-l">
	<jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	<div class="l-o-menu">
		<div class="mt20">
			<section class="l-level">
				<a href="${ctx}/dis/hot" title="热门小组" class="lev-15 hot">
					<i class="icon18">&nbsp;</i> 热门小组
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/dis" title="所有小组" class="lev-15 disall">
					<i class="icon18">&nbsp;</i> 所有小组
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/dis/my" title="我管理的小组" class="lev-15 my">
					<i class="icon18">&nbsp;</i> 我管理的小组
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/dis/join" title="我加入的小组"
					class="lev-15 myjoin"> <i class="icon18">&nbsp;</i> 我加入的小组
				</a>
			</section>
			<section class="l-level">
				<a href="${ctx}/dis/myart" title="我的小组话题"
					class="lev-15 myart"> <i class="icon18">&nbsp;</i> 我的小组话题
				</a>
			</section>
			<div class="mt20 tac">
				<a class="comm-btn-green" href="${ctx}/dis/classify"><span
					style="padding: 2px 32px; font-size: 16px">创建小组</span></a>
			</div>
		</div>
	</div>
</aside>
