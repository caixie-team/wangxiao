<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<aside class="W-main-l">
	<jsp:include page="/WEB-INF/view/sns/common/avatar.jsp"></jsp:include>
	<div class="l-o-menu">
		<div class="mt20">
			 <section class="l-level">
				<a href="${ctx}/sug" title="同学问答" class="lev-9 sug"> <i
					class="icon18">&nbsp;</i> 同学问答
				</a>
			</section> 
			<%-- <section class="l-level">
				<a href="${ctx}/sug/tj" title="已解决问题" class="lev-9 tj">
					<i class="icon18">&nbsp;</i> 已解决问题
				</a>
			</section> --%>
			<section class="l-level">
				<a href="${ctx}/sug/my" title="我的问题" class="lev-9 my">
					<i class="icon18">&nbsp;</i> 我的问题
				</a>
			</section>
			<%-- <section class="l-level">
				<a href="${ctx}/sug/myhd" title="我回答的问题"
					class="lev-9 myhd"> <i class="icon18">&nbsp;</i> 我回答的问题
				</a>
			</section> --%>
			<div class="mt20 tac">
				<a class="reply-sub-big reply-sub" title="提问问题" href="${ctx}/sug/add"><span>提问问题</span></a>
			</div>
		</div>
	</div>
</aside>
