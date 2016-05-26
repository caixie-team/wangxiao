<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>修改讲师</title>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/swfobject.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4_headimg.js?v=1410957986989"></script>
	<script type="text/javascript" src="${ctximg}/static/common/uploadify/jquery.uploadify.v2.1.4.min.js?v=1410957986989"></script>
	<script type="text/javascript">
		var context;
		$(document).ready(function() {
			// 上传头像
			initUploadify("fileupload","subjcetpic","imagesUrl","","teacher");
			// 教师介绍
			context = UE.getEditor('teacherCareer', {
				maximumWords:50000,
				initialFrameWidth:500,
				initialFrameHeight:200,
				serverUrl:"${imageUrl}/ueditor/controller.jsp?fromdomain=${ctx}",
				toolbars: [ueditor_simpletoolbar]
			});
		});

		
		//表单验证
		function submitForm(){
			var img = document.getElementById("imagesUrl").src;
			if($("#teacherName").val()==null || $("#teacherName").val()==''){
				dialogFun("修改讲师","讲师名称不能为空",0);
				return false;
			}
			if(img=="<%=imagesPath %>/static/edu/images/NotAvailable.png"){
				dialogFun("修改讲师","请上传讲师头像",0);
				return false;
			}
			if($("#teacherEducation").val()==null || $("#teacherEducation").val()==''){
				dialogFun("修改讲师","讲师资历不能为空",0);
				return false;
			}
			if(!context.hasContents(['span'])){
				dialogFun("修改讲师","讲师介绍不能为空",0);
				   return false;
			}
			return true;
		}
		//提交表单
		function updateTeacherFormSubmit(){
			if(submitForm()){
				$("#updateTeacherForm").submit();
			}
		}
		
	</script>
</head>
<body>
<div class="am-cf">
	<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">讲师管理</strong> / <small>修改讲师</small></div>
</div>
<hr>
<div class="mt20">
	<div class="am-tabs">
		<ul class="am-tabs-nav am-nav am-nav-tabs">
			<li class="am-active"><a href="javascript:void(0)">基本属性（<font color="red">*</font>&nbsp;为必填项）</a></li>
		</ul>
		<div class="am-tabs-bd">
			<div id="tab1" class="am-tab-panel am-fade am-active am-in">
				<form action="${ctx}/admin/teacher/update" method="post" id="updateTeacherForm" class="am-form" data-am-validator>
					<input type="hidden" name="teacher.id" value="${teacher.id}" />
					<input type="hidden" name="teacher.picPath" id="imagesUrl" value="${teacher.picPath}" />
					<input type="hidden" name="teacher.status" value="${teacher.status}" />
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 名称
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<input type="text" id="teacherName" class="am-input-sm" required placeholder="请填写讲师名称" name="teacher.name" value="${teacher.name}">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 头像
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<c:if test="${not empty teacher.picPath}">
								<img id="subjcetpic" src="<%=staticImageServer %>${teacher.picPath}" alt="" width="200px" height="150px" class="am-img-thumbnail am-radius">
							</c:if>
							<c:if test="${empty teacher.picPath}">
								<img id="subjcetpic" src="${cxt}/static/edu/images/NotAvailable.png" alt="" width="200px" height="150px" class="am-img-thumbnail am-radius">
							</c:if>
							<input id="fileupload" type="file">
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 资历
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea name="teacher.education" required id="teacherEducation">${teacher.education}</textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 等级
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<select name="teacher.isStar" id="isStar" required data-am-selected="{btnWidth: '40%', btnSize: 'sm', btnStyle: 'secondary'}">
								<option value="0" <c:if test="${teacher.isStar==0}">selected='selected'</c:if>>高级讲师</option>
								<option value="1" <c:if test="${teacher.isStar==1}">selected='selected'</c:if>>首席讲师</option>
							</select>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top am-form-group">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">
							<font class="am-text-danger">*</font> 介绍
						</div>
						<div class="am-u-sm-8 am-u-md-6">
							<textarea name="teacher.career" required id="teacherCareer">${teacher.career}</textarea>
						</div>
						<div class="am-hide-sm-only am-u-md-4"></div>
					</div>
					<div class="am-g am-margin-top">
						<div class="am-u-sm-4 am-u-md-2 am-text-right">&nbsp;</div>
						<div class="am-u-sm-8 am-u-md-6">
							<button class="am-btn am-btn-danger" title="提 交" type="button" onclick="updateTeacherFormSubmit()">提 交</button>
							<button class="am-btn am-btn-danger" title="返 回" type="button" onclick="history.go(-1);">返 回</button>
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
