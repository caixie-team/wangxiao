<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!--  /*
 	公共弹出提示框&会话框方法
	dTitle : 标题；
	num    : 类型；
	num = 0 : 错误信息提示框;
	num = 1 : 正确信息提示框;
	num = 2 : 确认信息提示框; 
	num = 3 : 博客评论弹出框; 
	num = 4 : 发私信弹出框;
	num = 5 : 提示操作失败，渐出提示效果;
	num = 6 : 提示操作成，渐出提示效果;
 */-->
<c:choose>
    <c:when test="${dialog.index==0}">
		<section class="dca dca0">
			<i class="icon26 dError">&nbsp;</i><span class="fsize14 c-555 ml5">${dialog.title}</span>
			<div class="mt20 mb10 tac">
				<a href="" title="" class="comm-btn-orange queding0"><span>确定</span></a>
			</div>
		</section>
	</c:when>
    <c:when test="${dialog.index==1}">
		<section class="dca dca1">
			<i class="icon26 dRight">&nbsp;</i><span class="fsize14 c-555 ml5">${dialog.title}</span>
			<div class="mt20 mb10 tac">
				<a href="" title="" class="comm-btn-green queding1"><span>确定</span></a>
			</div>
		</section>
	</c:when>
    <c:when test="${dialog.index==2}">
		<section class="dca dca2">
			<i class="icon26 dAsk">&nbsp;</i><span class="fsize14 c-555 ml5">${dialog.title}</span>
			<div class="mt20 mb10 tac">
				<a href="" title="" class="comm-btn-orange queding2"><span>确定</span></a><a
					href="javascript:void(0)" class="dCancel comm-btn-gray ml10"><span>取消</span></a>
			</div>
		</section>
	</c:when>
	 <c:when test="${dialog.index==3}">
	 <section class="dca dca3">
 			<section><h6><span class="fsize14 c-555">引用：</span></h6></section>
 			<section class="yinY-txt"><p id="yingyong"></p></section>
 			<section class="mt10"><h6><span class="fsize14 c-555">评论：</span></h6></section>
 			<section class="mt10"><textarea name="" class="dTextarea" id="replyContent"></textarea></section>
 			<section class="mt10 tar mb20"><span class="mr10 vam"><tt class="c-red fsize12" id="wenzi" style="display: none;">您还没输入文字！</tt></span><a class="send-btn-wrap" title="" href="javascript:void(0)" id="reply"><span>评论</span></a></section>
	</section>
	</c:when>
	 <c:when test="${dialog.index==4}">
	 <section class="dca dca4"> 
 			<section><h6><span class="fsize14 c-555">收信人：</span><span class="c-555 cusName"></span></h6></section>
 			<section class="mt10"><h6><span class="fsize14 c-555">信件内容：</span></h6></section>
 			<section class="mt10"><textarea name="" class="dTextarea" id="letterTextarea"></textarea></section>
 			<section class="mt10 tar mb20"><span class="mr10 vam"><tt class="c-red fsize12"><span id="wenzistr"></span><span id="wenzinum"></span>字</tt></span><a class="send-btn-wrap" title="发信" href="javascript:void(0)" onclick="sendLetter()"><span>发信</span></a></section>
	</section>
	</c:when>
	 <c:when test="${dialog.index==5}">
	 <section class="dca dca5"><i class="icon26 dFade">&nbsp;</i><span class="fsize14 c-555 ml5">${dialog.title}</span>
	 </section>
	</c:when>
	 <c:when test="${dialog.index==6}">
	 <section class="dca dca5"><i class="icon26 dRight"></i><span class="fsize14 c-555 ml5">${dialog.title}</span>
	 </section>
	</c:when>
	 <c:when test="${dialog.index==7}">
	 <section class="dca dca7"><span class="fsize14 c-555 ml5">备注：<input type="text" value="" id="queding7"/></span>
	 <div class="mt20 mb10 tac"><a href="" title="" class="comm-btn-green queding7"><span>确定</span></a></div></section>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
