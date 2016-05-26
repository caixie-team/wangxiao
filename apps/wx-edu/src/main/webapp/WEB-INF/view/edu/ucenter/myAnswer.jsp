<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>我的问答</title>
</head>
<body>
<form id="searchForm" action="${ctx }/uc/myCouAnswer" method="post">
	<input type="hidden" name="page.currentPage" value="${page.currentPage}" id="pageCurrentPage"/>
</form>
<article class="uc-m-content mb50">
	<header class="uc-com-title">
		<span>课程提问</span>
	</header>
	<div class="i-box">
		<div>
			<div class="pl15">
				<c:if test="${empty answerList }">
					<section class="mt30 mb30 tac">
						<em class="no-data-ico cTipsIco">&nbsp;</em>
						<span class="c-666 fsize14 ml10 vam">还没有课程提问~</span>
					</section>
				</c:if>
				<c:if test="${not empty answerList }">
					<article class="s-record-wrap">
						<section>
							<div class="pt30">
								<%--<aside class="pr hLh20">
									<span class="s-r-year c-fff">
										<big> 2016</big>
										<small>年</small>
									</span>
								</aside>--%>
								<c:forEach items="${answerList}" var="answer" varStatus="index">
									<script type="text/javascript">
										var lastdate=' <fmt:formatDate value="${answerList[index.index-1].addTime}" pattern="yyyy" />';
										var nowdate=' <fmt:formatDate value="${answer.addTime}" pattern="yyyy" />';
										if(lastdate!=nowdate){
											document.write('<aside class="pr hLh20"><span class="s-r-year c-fff"><big>'+nowdate+'</big><small>年</small></span></aside>');
										}
									</script>
									<article class="mt20 s-record">
										<span class="s-r-sj"> </span>
										<aside class="s-r-c-time">
											<span class="c-999 mr10"> <fmt:formatDate value="${answer.addTime}" pattern="MM-dd hh:mm" /></span>
											<em class="icon-2-16 s-r-c-t"> </em>
										</aside>
										<div>
											<h4 class="s-r-c-title unFw">
												<span class="c-master fsize14 vam">提出疑问：有${answer.replyCount}个回答</span>
												<a class="fsize14 c-blue vam ml10" title="查看详情" href="${ctx}/front/playkpoint/${answer.parentId}?kpointId=${answer.sonId}">课程详情</a>
											</h4>
											<div class="clearfix pt5 s-r-c-box">
												<section  class="mt10 s-r-c-desc">
													<p class="c-999">${answer.content}</p>
												</section>
											</div>
											<div class="mt10 c-orange">
												<c:if test="${answer.replyCount>0}">
													<a href="javascript:void(0)"  title="回答" onclick="showReply(${answer.id })" class="c-orange"> 查看回答 </a>
												</c:if>
											</div>
											<section id="reply${answer.id }" class="mt10 line1 u-c-a-wrap" style="display: none;height: auto;">
											</section>
										</div>
									</article>
								</c:forEach>
							</div>
						</section>
					</article>
				</c:if>
			</div>
			<jsp:include page="/WEB-INF/view/edu/common/u_page.jsp"></jsp:include>
		</div>
	</div>
</article>
<script type="text/javascript">
	function showReply(id){
		if($("#reply"+id).css("display")!='none'){
			$("#reply"+id).slideUp(150);
		}else{
			$.ajax({
				url:baselocation+'/front/ajax/answerReplyList',
				type:'post',
				dataType:'json',
				async:false,
				data:{'answerId':id},
				success:function (result){
					if(result.success){
						var obj = result.entity;
						var reProblemContext='';
						for(var i=0;i<obj.length;i++){
							var reProName = obj[i].showName;
							reProblemContext+='<div class="mt10 pl20 pr10"><dl class="u-cou-reply-list">';
							reProblemContext+='<dd><div class="of"><span class="fl"><font class="fsize12 c-blue">'+reProName+'</font><font class="fsize12 c-999 ml5">于'+obj[i].addTime+'</font><font class="fsize12 c-999 ml5">回复：</font></span></div>';
							reProblemContext+='<div class="mt5"><p class="c-999">'+obj[i].content+'</p></div></dd>';
							reProblemContext+='</dl></div>';
						}
						$("#reply"+id).slideDown(150).html(reProblemContext);
					}
				}
			});
		}
	}
</script>
</body>
</html>
