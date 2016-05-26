	$().ready(function() {
		if (updateError!=null&&updateError!=''){
			showErrorMsg(updateError);
		}
		if (updateMessage!=null&&updateMessage!=''){
			showSuccessMsg(updateMessage);
		}
	});


	function subit(){
		$("#oldPwd_1").html("");
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var newPwdConfirm = $("#newPwdConfirm").val();
		if(newPwd.length > 15 || newPwd.length < 6 ){
			dialog('提示','密码必须是6-15位',9);
			return false;
		}
		if(newPwd == newPwdConfirm){
			var pattern = /(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
			if(pattern.test(newPwdConfirm)==false){//格式不正确
	 			$("#newpwd_1").html('请不要输入非法关键字');
	 			return false;
	 		}else{
	 			$("#newpwd_1").html("");
				$("#pwdForm").submit();
				$.ajax({
					url :  baselocation+"/uc/user/updatepwd",
					type : "post",
					dataType : "json",
					data:{
						"oldpwd" : oldPwd,
						"newpwd" : newPwd
					},
					success : function(result) {
						 $("#oldPwd").val("");
						 $("#newPwd").val("");
						 $("#newPwdConfirm").val("");
						 if(result.success){
							 dialog('提示','密码修改成功',15);
						 }else{
							 $("#oldPwd_1").html("当前密码不正确");
						 }
					}
				 });
	 		}
		}else{
			dialog('提示','两次输入的密码不一致',9);
		}
	}