<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<head>
<title>提问问题</title>
<script type="text/javascript" src="${ctximg}/kindeditor/kindeditor-all.js?v=<%=version%>"></script>
<script type="text/javascript" src="${ctximg}/static/sns/js/suggest/suggest.js?v=<%=version%>"></script>
<script type="text/javascript">
window.onload=function(){//编辑器初始化
	initKindEditor('suggestContent','576px','380px');//加载编辑器
	checkLogin();
}
</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<form action="${ctx}/sug/addSugSuggest" id="addSugSuggest"
					method="post">
					<input type="hidden" id="type" name="sugSuggest.type">
					<section class="W-main-c fl">
						<div class="W-main-cc">
							<section class="W-main-center">
								<div class="pl20">
									<section class="mt10">
										<section class="comm-title-3">
											<span class="c-t-2-r"></span> <span
												class="c-t-2-l"><tt class="c-888">提问问题</tt></span>
											<div class="c-t-line">&nbsp;</div>
										</section>
									</section>
									<div class="mt20">
										<section>
											<dl class="boke-release-wrap">
												<dt>
													<span class="c-555 fsize14">标题</span>
												</dt>
												<dd>
													<input type="text" class="commInput-1"
														name="sugSuggest.title" id="title"> <span
														class="ml10 c-bbb">标题长度不得多于24个字符</span>
												</dd>
											</dl>
											<dl class="boke-release-wrap">
												<dd>
													<textarea class="commTextarea" id="suggestContent"
														name="sugSuggest.content" style="height: 380px;"></textarea>
													已输入<span id="wenzinumsuggestContent"></span>字<br />
												</dd>
											</dl>
										</section>
										<section class="mt20 tac pt20">
											<a class="comm-btn-orange" title="提交"
												href="javascript:void(0)" onclick="submitwushi();"><span
												style="padding: 5px 30px; font-size: 18px;">提交</span></a> 
										</section>
									</div>
								</div>

							</section>
							<jsp:include page="/WEB-INF/view/sns/suggest/right.jsp" />
						</div>
					</section>
				</form>
				<!-- 主体区域 -->
</body>
</html>