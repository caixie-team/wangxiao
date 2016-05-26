<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>新建讲师</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
	<script type="text/javascript">
		var teacherCareer;
		$(document).ready(function() {
			//上传图片
			initUploadify("fileupload","subjcetpic","imagesUrl","","teacher");
			// 讲师介绍
			teacherCareer = UE.getEditor('teacherCareer', {
				maximumWords:50000,
				initialFrameWidth:500,
				initialFrameHeight:200,
				serverUrl:"${imageUrl}/ueditor/controller.jsp?fromdomain=${ctx}",
				toolbars: [ueditor_simpletoolbar]
			});
		});
		function addTeacherFormSubmit(){
			if(isEmpty($("#teacherName").val())){
				dialogFun("新建讲师","讲师名称不能为空",0);
			}else if(isEmpty($("#imagesUrl").val())){
				dialogFun("新建讲师","请上传讲师头像",0);
			}else if(isEmpty($("#teacherEducation").val())){
				dialogFun("新建讲师","讲师资历不能为空",0);
			}else if(isEmpty(teacherCareer.getContent())){
				dialogFun("新建讲师","讲师简介不能为空",0);
			}else {
				$("#teacherCareer").val(teacherCareer.getContent());
				$("#addTeacherForm").submit();
			}
		}
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">讲师管理</strong> / <small>讲师添加</small></div>
</div>
<hr>
<div class="mt20">
	<div  class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本属性（<font color="red">*</font>&nbsp;为必填项）</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/teacher/add" method="post" id="addTeacherForm" class="am-form">
					<input type="hidden" name="teacher.picPath" id="imagesUrl"  />
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 名称
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="teacherName" class="am-input-sm" required placeholder="请填写讲师名称" name="teacher.name">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 头像
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<img id="subjcetpic" src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="200px" height="150px" class="am-img-thumbnail am-radius">
							<input id="fileupload" type="file">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 资历
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea name="teacher.education" required id="teacherEducation"></textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div for="isStar" class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 等级
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<select name="teacher.isStar" id="isStar" required data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
								<option value="0">高级讲师</option>
								<option value="1">首席讲师</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div for="teacherCareer" class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 简介
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea name="teacher.career" required id="teacherCareer"></textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" type="button" title="提 交" onclick="addTeacherFormSubmit()">提 交</button>
							<button class="am-btn am-btn-danger" type="button" title="返 回" onclick="history.go(-1);">返 回</button>
						</div>
						<div class="am-u-sm-12 am-u-md-4"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctximg}/static/common/ueditor/ueditor.mine.js"></script>
</body>
</html>
