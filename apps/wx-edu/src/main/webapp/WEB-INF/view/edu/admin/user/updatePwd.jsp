<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>修改密码</title>

<script type="text/javascript">
function submit(){
	var password = $("#password").val();
	if(password.trim()==""){
		alert("新密码不能为空");
		return;
	}
	if(password.length<6||password.length>16){
		alert("新密码长度应为6到16个字符");
		return;
	}
	var id = $("#id").val();
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${ctx}/admin/user/updatepwd",
		data:{"user.id":id,"user.password":password},
		async:false,
		success:function(result){
			if(result.success){
				alert("修改成功");
				window.location.href="${ctx}/admin/user/list";			
			}
		}
	});
	
}
</script>
</head>
<body >
<div class="am-cf">
<div class="am-fl am-cf">
<strong class="am-text-primary am-text-lg">学员管理</strong>
/
<small>修改密码</small>
</div>
</div>
<hr/>
<div class="am-tab-panel am-fade am-active am-in">
<form action="${ctx}/admin/user/updatePwd" method="post" id="addPaperForm" class="am-form">
<input name="user.id" id="id" type="hidden" value="${user.id}"/>
   <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;学员id</div>
			<div class="am-u-sm-8 am-u-md-4">
			${user.id }
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		<div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;学员邮箱</div>
			<div class="am-u-sm-8 am-u-md-4">
			${user.email }
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			<div class="am-u-sm-4 am-u-md-2 am-text-right"><font color="red">*</font>&nbsp;新密码</div>
			<div class="am-u-sm-8 am-u-md-4">
			<input type="password" name="user.password" id="password" class="am-input-sm" required placeholder="请输入新密码"/>
			</div>
			<div class="am-hide-sm-only am-u-md-6">&nbsp;</div>
		</div>
		 <div class="am-g am-margin-top">
			 <div class="am-u-sm-8 am-u-md-4" style="text-align:center">
			 <button class="am-btn am-btn-danger" type="submit">提交</button>
			 <a class="am-btn am-btn-success" title="返 回" href="${ctx}/admin/user/list">返 回</a>
			</div>
		</div>
</form>
</div>
</body>
</html>
