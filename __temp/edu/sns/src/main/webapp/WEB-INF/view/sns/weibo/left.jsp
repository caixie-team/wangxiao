<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<aside class="W-main-l">
	
	<jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	
	<div class="l-o-menu">
		<div class="mt20">
			<section class="l-level">
				<a href="javascript:all()" title="全站观点" class="lev-6 all">
					<i class="icon18">&nbsp;</i> 全站观点
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:hot()" title="热门观点" class="lev-6 hot">
					<i class="icon18">&nbsp;</i> 热门观点
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:my()" title="我的观点" class="lev-6 my">
					<i class="icon18">&nbsp;</i> 我的观点
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:attentionajax()" title="好友观点"
					class="lev-6 attention"> <i class="icon18">&nbsp;</i> 好友观点
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:most()" title="评论最多"
					class="lev-6 most"> <i class="icon18">&nbsp;</i> 评论最多
				</a>
			</section>
			<!-- <div class="mt20 tac">
				<a class="comm-btn-orange" href="javascript:showaddWeibo()"><span
					style="padding: 1px 25px;">发观点</span></a>
			</div> -->
		</div>
	</div>
</aside>
