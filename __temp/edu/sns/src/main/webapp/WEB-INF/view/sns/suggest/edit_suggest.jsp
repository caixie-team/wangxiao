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

function baocun(id) {
	if(!isLogin()){
		 dialog('登录','',3,'','1');
		 return;
	}
	var title = $("#title").val();
	var suggestContent = KindEditor.get("#suggestContent").html();
	if (title.trim() == "") {
		dialog_sns("标题不能为空",0);dragFun();//弹出框
		$(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
		return;
	}
	if (title.trim().length > 24) {
		dialog_sns("标题不能多于24个字符",0);dragFun();
		$(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
		return;
	}
	if (suggestContent.trim() == "") {
		dialog_sns("内容不能为空",0);dragFun();
		$(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
		return;
	}
	if (KindEditor.get("#suggestContent").count('text') > max_text_length) {//内容不能超过4000字
		dialog_sns("请输入内容不要超过"+max_text_length+"字,请删减！",0);dragFun();
		$(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
		return;
	}
	if (KindEditor.get("#suggestContent").count('text') < 5) {//内容小于5个字
		dialog_sns("输入内容不能小于5个字,请添加！",0);dragFun();
		$(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
		return;
	}
	$("#type").val(2);
	$.ajax({
		url:baselocation+"/sug/update",
		type : "post",
		dataType : "json",
		data : {
			"sugSuggest.id":id,
			"sugSuggest.type":$("#type").val(),
			"sugSuggest.title":title,
			"sugSuggest.content":suggestContent,
		},
		success : function(result) {
			if (result.message == "修改成功") {
				dialog_sns("修改成功",1);dragFun();//弹出框
				$(".queding1").attr("href","javascript:window.location.href = '/sug/info/"+id+"'");//确定按钮的方法
			}
			if (result.message == "修改失败") {
				dialog_sns("请刷新重试",0);dragFun();//弹出框
				$(".queding0").attr("href","javascript:deldialog_sns()");//确定按钮的方法
			}
		}
	});
}
</script>
</head>
<body class="W-body">
		<!-- 主体 开始-->
				<form action="${ctx}/sug/update" id="addSugSuggest" method="post">
					<input type="hidden" id="type" name="sugSuggest.type" value="${sugSuggest.type }">
					<section class="W-main-c fl">
						<div class="W-main-cc">
							<section class="W-main-center">
								<div class="pl20">
									<section class="mt10">
										<section class="comm-title-3">
											<span class="c-t-2-r"></span> <span
												class="c-t-2-l"><tt class="c-888">修改提问</tt></span>
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
													<input type="text" class="commInput-1" name="sugSuggest.title" id="title" value="${sugSuggest.title }"> <span class="ml10 c-bbb">标题长度不得多于30个字符</span>
												</dd>
											</dl>
											<dl class="boke-release-wrap">
												<dd>
													<textarea class="commTextarea" id="suggestContent"
														name="sugSuggest.content" style="height: 380px;">${sugSuggest.content }</textarea>
													已输入<span id="wenzinumsuggestContent"></span>字<br />
												</dd>
											</dl>
										</section>
										<section class="mt20 tac pt20">
											<a class="comm-btn-orange" title="提交"
												href="javascript:void(0)" onclick="baocun(${sugSuggest.id });"><span
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