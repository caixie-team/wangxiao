<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>新建文本素材</title>
<script type="text/javascript" src="<%=imagesPath %>/kindeditor/kindeditor-all.js?v=${v}"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css?v=${v}" />
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">微信管理</strong> / <small>新建文本素材</small></div>
</div>
<hr/>
	<!-- /tab4 begin -->
	<div class="mt20">
		<div class="am-tabs">
			<ul class="am-tabs-nav am-nav am-nav-tabs">
				<li class="am-active"><a href="javascript:void(0)">基本信息</a></li>
			</ul>
			<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/weixin/add" class="am-form" method="post" id="addForm">
					<input type="hidden" name="weixinReply.msgType" value="1"/>
						
						<div class="am-g am-margin-top am-form-group am-form-error">
							<div for="keyword" class="am-u-sm-4 am-u-md-2 am-text-right" > 关键词 </div>
						<div class="am-u-sm-4 am-u-md-2">
							<input required data-foolish-msg="关键词不能为空" type="text" name="weixinReply.keyword" id="keyword"/>
						</div>
						<div class="am-hide-sm-only am-u-md-6">
						<font color="red">*可设置多个关键字用单个空格隔开，如网校 教育 在线 </font>
						</div>
						</div>
						
						<div class="am-g am-margin-top am-form-group am-form-error">
							<div class="am-u-sm-4 am-u-md-2 am-text-right" for="mobile"> 内容 </div>
						<div class="am-u-sm-4 am-u-md-4">
							<textarea required data-foolish-msg="内容不能为空" rows="4" maxlength="600" cols="40" name="weixinReply.content" id="content"></textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-6">
							<font color="red">*请不要多于600字</font>
						</div>
						</div>
					<div class="am-u-sm-12 am-u-end am-text-center">
						<button class="am-btn am-btn-danger" title="提 交" type="submit">提 交</button>
						<a class="am-btn am-btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a>
					</div>
				</form>
				</div>
				</div>
			</div>
	</div>
	<!-- /tab4 end -->

<script type="text/javascript">
	/* function addSubmit(){
		if($("#keyword").val()==null||$("#keyword").val()==""){
			dialogFun("新建文本素材","关键词不能为空",0);
			return;
		}
		if($("#content").val()==null||$("#content").val()==""){
			dialogFun("新建文本素材","内容不能为空",0);
			return;
		}
		
		$("#addForm").submit();
	} */
	$(function(){
		validateForm("addForm");
	})
</script>
</body>
</html>