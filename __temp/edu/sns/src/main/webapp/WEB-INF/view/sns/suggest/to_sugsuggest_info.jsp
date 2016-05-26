<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>${sugSuggest.title }</title>
<script src="${ctximg}/static/sns/js/suggest/suggest.js?v=<%=version%>" type="text/javascript"></script>
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
	//删除问答
	function delSug(id){
		dialog_sns("确定删除？",2);
		$(".queding2").attr("href","javascript:del("+id+")");
	}
	// 我的问答 删除
	function del(id) {
		$.ajax({
			url:baselocation+"/sug/delSug/"+id,
			type : "post",
			dataType : "json",
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					deldialog_sns();
					dialog_sns("删除成功",1);dragFun();//弹出框
					$(".queding1").attr("href","javascript:window.location.href = '/sug/my'");//确定按钮的方法
					return true;
				} else if (result.message == "isEmpty") {
					return false;
				} else {
					dialog_sns("请刷新重试", 5);
					return false;
				}
			}
		});
	}
</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<section class="W-main-c fl">
					<div class="W-main-cc">
						<section class="W-main-center">
							<div class="pl20">
								<section class="mt10">
									<section class="comm-title-3">
										<span class="c-t-2-l">问题详情</span>
										<div class="c-t-line">&nbsp;</div>
									</section>
								</section>
								<div class="question-txt-wrap mt10">
									<h1>
										<i class="askT-icon icon26">&nbsp;</i> <span
											class="ask-title vam">
											<c:if test="${sugSuggest==null}">
												您要看的数据不存在或已删除，请返回重试！
											</c:if>
											<c:if test="${sugSuggest!=null}">
											${sugSuggest.title }
											</c:if>
											
											</span>
									</h1>
									<section class="of pl10">
										<span class="fr"><tt class="c-bbb f-fM fsize12">
												<fmt:formatDate value="${sugSuggest.addtime}" type="both" ></fmt:formatDate>
											</tt></span> 
											<a href="${ctx}/p/${sugSuggest.cusId}/home" title="${sugSuggest.showname }" class="c-blue">${sugSuggest.showname }</a>
											<span class="c-bbb ml5 mr5">|</span>
											<span class="c-888"> 浏览${sugSuggest.browseNum }次</span>
											<span class="c-bbb ml5 mr5">|</span>
											<span class="c-888"> 回复<span class="replycount">${sugSuggest.replycount }</span>次</span>
											<c:if test="${sugSuggest.cusId==cusId }">
												<span class="c-bbb ml5 mr5">|</span><a class="c-888" href="${ctx}/sug/toEdit/${sugSuggest.id}">编辑</a><span class="c-bbb ml5 mr5">|</span>
												<a class="c-888" href="javascript:void(0)" onclick="delSug(${sugSuggest.id})">删除</a><span class="c-bbb ml5 mr5">|</span>
										 	</c:if>
									</section>
									<section class="mt10 pl10">
										<div class="ask-desc normalLi">${sugSuggest.content }</div>
									</section>
									<c:if test="${sugSuggest.status==0}">
										<section class="mt10 pl10">
											<h6>
												<a href="javascript:void(0)" title="我要回答" class="c-blue"><i
													class="QA-i icon12 mr5">&nbsp;</i>我要回答：</a>
											</h6>
											<div class="mt10">
												<textarea name="" id="sugSuggestReplyContent"
													class="ask-textarea"></textarea>
												<!-- <span id="wenzistr"></span><span id="wenzinumsugSuggestReplyContent"></span>字<br /> -->
											</div>
											<div class="mt10 tar pr10">
												<a href="javascript:void(0)" title="提交回答"
													onclick="addSugSuggestReply('${sugSuggest.id }','${sugSuggest.type}')" class="comm-btn-green"><span
													style="padding: 2px 12px; font-size: 14px;">提交回答</span></a>
											</div>
										</section>
									</c:if>

								</div>
								<c:if test="${sugSuggest.status==1}">
								<div class="QW-line"></div>
								<!-- /最佳答案 开始 -->
									<c:forEach items="${sugSuggestReplyList}" var="ssrl">
										<c:if test="${ssrl.isbest==1 }">
											<div class="best-answer">
												<h2 class="BA-title">
													<i>&nbsp;</i><span class="fsize18 f-fM" style="color: #cc0000;">提问者采纳</span>
												</h2>
												<section class="mt10 pl10 pr10">
													<div class="ask-desc normalLi">${ssrl.content}</div>
													<div class="clearfix mt10">
														<span class="fr"><tt class="c-888 f-fM">
																<fmt:formatDate value="${ssrl.addtime}" type="both" ></fmt:formatDate>
															</tt></span> <span class="fl"><a href="${ctx}/p/${ssrl.cusId}/home" title="${ssrl.showname}"
															class="c-blue">${ssrl.showname}</a></span>
													</div>
												</section>
											</div>
										</c:if>
									</c:forEach>
									<!-- /最佳答案 结束 -->
								<div class="QW-line"></div>
								</c:if>
								<div class="replyList" id="paperList"></div>
								<!-- /其它答案 -->
							</div>
						</section>
						<jsp:include page="/WEB-INF/view/sns/suggest/right.jsp" />
					</div>
				</section>
				<!-- 主体区域 -->
</body>
