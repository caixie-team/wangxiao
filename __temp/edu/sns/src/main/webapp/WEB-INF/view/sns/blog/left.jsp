<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<aside class="W-main-l">
	
	<jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	
	<div class="l-o-menu">
		<div class="mt20">
			<section class="l-level" >
				<a href="javascript:all();" title="" class="lev-7 all"> <i
					class="icon18">&nbsp;</i> 全部博文
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:hot();" title="" class="lev-7 hot">
					<i class="icon18">&nbsp;</i> 热门博文
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:my();" title="" class="lev-7 my"> <i
					class="icon18">&nbsp;</i> 我的博文
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:attentionajax();" title="" class="lev-7 attention">
					<i class="icon18">&nbsp;</i> 我关注的
				</a>
			</section>
			<section class="l-level">
				<a href="javascript:most();" title="" class="lev-7 most">
					<i class="icon18">&nbsp;</i> 评论最多
				</a>
			</section>
			<div class="tac mt20">
				<a class="comm-btn-orange" href="${ctx}/blog/rele"><span
					style="padding: 3px 25px; font-size: 14px;">发表博文</span></a>
			</div>
		</div>
	</div>
</aside>
