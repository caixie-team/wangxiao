<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改讲师</title>

<script type="text/javascript" src="<%=imagesPath%>/kindeditor/kindeditor-all.js"></script>
<link rel="stylesheet" type="text/css" href="${ctximg}/kindeditor/themes/default/default.css" />
<script type="text/javascript">
$(document).ready(function() {
initSimpleImageUpload("fileuploadButton","subjcetpic","imagesUrl");
});
function updateTeacherFormSubmit(){
	if($("#teacherName").val()==''||$("#teacherName").val()==null){
		alert("请填写讲师名称");
		return ;
	}
	if($("#teacherEducation").val()==''||$("#teacherEducation").val()==null){
		alert("请填写讲师资历");
		return ;
	}
	if($("#teacherCareer").val()==''||$("#teacherCareer").val()==null){
		alert("请填写讲师简介");
		return ;
	}
	if($("#imagesUrl").val()==''){
		alert("请上传头像");
		return ;
	}
	$("#updateTeacherForm").submit();
}
function initSimpleImageUpload(btnId,urlId,valSet){
	KindEditor.ready(function(K) {
		var uploadbutton = K.uploadbutton({
			button : K('#'+btnId+'')[0],
			fieldName : "fileupload",
			url : '<%=uploadSimpleUrl%>&param=teacher',
			afterUpload : function(data) {
				if (data.error === 0) {
					var url = K.formatUrl(data.url, 'absolute');//absolute,domain
					K('#'+urlId+'').attr("src",'<%=staticImageServer%>' + data.url);
						$("#" + urlId).show();
						$('#' + valSet + '').val(url);
					} else {
						alert(data.message);
					}
				},
				afterError : function(str) {
					alert('自定义错误信息: ' + str);
				}
			});
			uploadbutton.fileBox.change(function(e) {
				uploadbutton.submit();
			});
		});
	}
</script>
</head>
<body>
		<form action="${ctx}/admin/teacher/update" method="post" id="updateTeacherForm">
			<input type="hidden" name="teacher.id" value="${teacher.id}" /> <input type="hidden" name="teacher.picPath" id="imagesUrl" value="${teacher.picPath}" /> <input type="hidden" name="teacher.status" value="${teacher.status}" />
			<div class="page_head">
				<h4>
					<em class="icon14 i_01"></em>&nbsp;<span>讲师管理</span> &gt; <span>讲师添加</span>
				</h4>
			</div>
			<!-- /tab4 begin -->
			<div class="mt20">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" class="commonTab01">
					<thead>
						<tr>
							<th align="left" colspan="2"><span>讲师基本属性<tt class="c_666 ml20 fsize12">
										（<font color="red">*</font>&nbsp;为必填项）
									</tt></span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;讲师名称</td>
							<td><input type="text" name="teacher.name" class="{required:true}" id="teacherName" value="${teacher.name}" /></td>
						</tr>
						<tr>
							<td width="20%" align="center"><font color="red">*</font>讲师资历</td>
							<td width="80%"><input type="text" name="teacher.education" style="width: 500px;" id="teacherEducation" class="{required:true}" value="${teacher.education}" /></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;讲师等级</td>
							<td><select name="teacher.isStar">
									<option value="0" <c:if test="${teacher.isStar==0}">selected='selected'</c:if>>高级讲师</option>
									<option value="1" <c:if test="${teacher.isStar==1}">selected='selected'</c:if>>首席讲师</option>
							</select></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;讲师简介</td>
							<td><textarea rows="6" cols="80" name="teacher.career" id="teacherCareer" class="{required:true}">${teacher.career}</textarea></td>
						</tr>
						<tr>
							<td align="center"><font color="red">*</font>&nbsp;头像</td>
							<td><img src="<%=staticImageServer %>${teacher.picPath}" alt="" id="subjcetpic" width="400px" height="300px" /> <input type="button" value="上传" id="fileuploadButton" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><a class="btn btn-danger" title="提 交" href="javascript:updateTeacherFormSubmit()">提 交</a> <a class="btn btn-danger" title="返 回" href="javascript:history.go(-1);">返 回</a></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- /tab4 end -->
		</form>
</body>
</html>
