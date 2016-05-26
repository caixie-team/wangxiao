<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改积分礼品</title>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>	
<script type="text/javascript" src="${ctximg}/static/edu/js/common/uploadify.js" ></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
$(document).ready(function() {
	//上传图片
	initUploadify("fileupload","subjcetpic","imagesUrl","fileQueue","gift");
	//加载编辑器
	initKindEditor_addblog('ArticleContent','576px','151px');
});
function updateGiftFormSubmit(){
	if($("#giftName").val()==''){
		dialogFun("礼品修改","请填写礼品名称",0);
		return ;
	 }
	if($("#score").val()==''){
		dialogFun("礼品修改","请填写使用积分",0);
		return ;
	 }
	if($("#imagesUrl").val()==''){
		dialogFun("礼品修改","请上传礼品图片",0);
		return ;
	 }
	if($("#ArticleContent").val()==''){
		dialogFun("礼品修改","请填写内容",0);
		return ;
	}
	
	submitForm();
}
function submitForm(){
	$("#addArticleForm").submit();
}
</script>
</head>
<body >
	<div class="am-cf">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">礼品管理</strong> / <small>礼品修改</small>
		</div>
	</div>
	<hr/>
	<div class="am-tab-panel am-fade am-active am-in">
<form class="am-form" action="${ctx}/admin/user/updategift" method="post" id="addArticleForm">
	<input type="hidden" name="userIntegralGift.id" id="id"  value="${userIntegralGift.id}"/>
	<input type="hidden" name="userIntegralGift.logo" id="imagesUrl"  value="${userIntegralGift.logo}"/>
	<input type="hidden" name="userIntegralGift.courseId" id="courseId"  value="0"/>
	 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;积分礼品名称 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" name="userIntegralGift.name" class="am-input-sm"  id="giftName" value="${userIntegralGift.name}" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
	</div>
	<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;使用分数 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="text" name="userIntegralGift.score" class="am-input-sm"  id="score" value="${userIntegralGift.score}" />
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
	</div>
	<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;礼品图片 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<img src="<%=staticImageServer %>${userIntegralGift.logo}" alt="" id="subjcetpic" width="178px" height="133px"/>
					<input type="file" id="fileupload" />
					<div id="fileQueue" style="margin-top: 30px; border: 0px;"></div>
					<font color="red">*图片链接，支持JPG、PNG格式，尺寸（宽178像素，高133像素）小于256kb</font>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
	</div>
	<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;详细内容 </div>
			<div class="am-u-sm-8 am-u-md-4">
			<textarea rows="" cols="" name="userIntegralGift.content" id="ArticleContent" >${userIntegralGift.content}</textarea>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
	</div>
	<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp; </div>
			<div class="am-u-sm-8 am-u-md-4">
			<a class="am-btn am-btn-danger" title="提 交" href="javascript:updateGiftFormSubmit()">提 交</a>
			<a class="am-btn am-btn-success" title="返 回" href="javascript:history.go(-1);">返 回</a>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
	</div>
	</form>
</div>
</body>
</html>
