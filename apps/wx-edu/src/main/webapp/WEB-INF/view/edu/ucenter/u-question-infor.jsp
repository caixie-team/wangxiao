<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>问答详情</title>
<script type="text/javascript" src="${ctximg}/static/edu/js/ucenter/u_suggest.js?v=<%=version%>" ></script>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript">
$(function() {
	queryPaperListByType('${sugSuggest.id}', 1);//回复的ajax分页
});
window.onload = function() {
	initKindEditor('sugSuggestReplyContent', '560px', '190px');//初始化编辑器
};
var sugSugestCusId = '${sugSuggest.cusId}';//问题id
var nowCusId = "${cusId}";
var status = '${sugSuggest.status}';
var sugSugestId = '${sugSuggest.id}';
var type = '${sugSuggest.type}';
</script>
</head>
<body>
			<article class="u-m-c-w837 u-m-center">
				<section class="u-m-c-wrap">
					<!-- /u-m-c-head -->
					<section class="u-m-c-head">
						<ul class="fl u-m-c-h-txt">
							<li class="current"><a href="" title="">问题详情</a></li>
						</ul>
						<div class="clear"></div>
					</section>
					<!-- /u-m-c-head -->
					<section class="line1">
						<div class="pl15 pr15">
							<section class="mt30 ml20 mr20">
								<div class="clearfix pb20 line3">
									<div>
										<em class="wt-infor-icon icon-2-24 mr5">&nbsp;</em>
										<tt class="vam fsize18 c-333 f-fM">
										<c:if test="${empty sugSuggest}">
												您要看的数据不存在或已删除，请返回重试！
											</c:if>
											<c:if test="${not empty sugSuggest}">
										${sugSuggest.title}
										</c:if>
										</tt>
									</div>
									<div class="mt10 clearfix">
										<aside class="fr">
											<font class="c-999">
											<fmt:formatDate value="${sugSuggest.addtime}" type="both"/>
											</font>
										</aside>
										<aside class="fl c-ccc pl20">
											<span class="c-blue mr10 ml10">
											${sugSuggest.showname }</span> |
											<span class="c-999 mr10 ml10">
											浏览${sugSuggest.browseNum }次</span> |
											<span class="c-999 mr10 ml10">回答次数：<span class="replycount">${sugSuggest.replycount }</span>次</span>
										</aside>
									</div>
									<div class="mt10 pl20"><span class="c-blue">问题详情：</span><span class="c-666">${sugSuggest.content }</span></div>
								</div>
								<div class="best-answer">
							<c:if test="${sugSuggest.status==1}">
								<c:forEach items="${sugSuggestReplyList}" var="ssrl">
										<c:if test="${ssrl.isbest==1 }">
									<section class="pr of">
										<aside class="fr mt30 pt10">
											<font class="c-999"><fmt:formatDate value="${ssrl.addtime}" type="both" ></fmt:formatDate></font>
										</aside>
										<span class="answer-best">&nbsp;</span>
										<span class="vam disIb mt20 ml10"><b class="f-fM fsize16 c-4e">提问者采纳</b></span>
									</section>
									<section class="pt10 pb10">
										<article class="answer-txt-Q">
											${ssrl.content}
										</article>
									</section>
									<section class="clearfix">
										<div class="fl answered-img mr10">
										<c:if test="${not empty ssrl.userExpandDto.avatar }">
										<img src="<%=staticImageServer %>${ssrl.userExpandDto.avatar}" width="60" height="60">
										</c:if>
										<c:if test="${empty ssrl.userExpandDto.avatar }">
										<img src="${ctx}/images/uc-imgage-default.png" width="60" height="60">
										</c:if>
										
										</div>
										<div>
											<p>${ssrl.showname}</p>
											<p class="fsize12 c-999 mt5">最佳答案提供者</p>
										</div>
									</section>
									</c:if>
									</c:forEach>
									</c:if>
									<c:if test="${sugSuggest.status==0}">
									<p class="fsize12 c-999 mt5">暂时没有采纳任何答案</p>
									</c:if>
								</div>
								<c:if test="${sugSuggest.status==0}">
								<div id="divAnswerid" class="answer-ta-box">
								<%-- <form action="${ctx}/cus/pblimit!problemContent.action" method="post" name="addProblem" id="addProblem" ></form> --%>
									<section class="mt10">
										<h4 class="hLh30 of"><b class="fsize16 f-fM c-4e">继续回答</b></h4>
										<div class="edui-answered edui-default">
										<textarea name="" id="sugSuggestReplyContent"
													class="ask-textarea"></textarea>
											<%-- <textarea  name="reProblem.reInfo" class="" id="content" placeholder="在这里输入你的答案" onblur="taBlur(this)" onfocus="taFocus(this)" onkeyup="pkey()" ></textarea>
												<input type="hidden" name="problem.pblId" id="problem.pblId"
											value="<s:property value="problem.pblId"/>" /> 
										<input type="hidden" name="course.courseId" id="course.courseId"
											value="15" /> --%>
										</div>

									  	<div class="tar mt10">
												<span class="vam c-red mr10" id="spanf" ></span> <a
													class="q-submit-btn" href="javascript:void(0)" onclick="addSugSuggestReply('${sugSuggest.id }','${sugSuggest.type}')" title="提交问题" style="background-color: #46B300">提交回答</a>
											</div>

										</section>
								</div>
								</c:if>
								 <div class="other-answer">
									<section class="mt10">
										<h4 class="hLh30 of"><b class="fsize16 f-fM c-4e">其它回答</b></h4>
										<div id="pbdivId"></div>
									</section>	
									<!-- 课程评价分页 -->
									<%--<section id="newStaticShowpage2">
										<div class="pagination pagination-large tac">
							             <jsp:include page="/static/ucenter268/common/uc-staticShowpage2.jsp" />
										</div>
									</section>
								<!-- 课程评价分页 -->	--%>							
								</div> 
							</section>
						</div>
					</section>
				</section>
			</article>
	<!-- /u-main end -->
</body>
</html>
