	$().ready(function() {
		if (updateError!=null&&updateError!=''){
			showErrorMsg(updateError);
		}
		if (updateMessage!=null&&updateMessage!=''){
			showSuccessMsg(updateMessage);
		}
	});


	function subit(){
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var newPwdConfirm = $("#newPwdConfirm").val();
		if(newPwd.length > 15 || newPwd.length < 6 ){
			dialogFun('提示','密码必须是6-15位',0);
			return false;
		}
		if(newPwd == newPwdConfirm){
			var pattern = /(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
			if(pattern.test(newPwdConfirm)==false){//格式不正确
	 			$("#newpwd_1").html('请不要输入非法关键字');
	 			return false;
	 		}else{
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
							 dialogFun('提示','密码修改成功',1);
						 }else{
							 dialogFun('提示','当前密码输入不正确',0);
							 
						 }
					}
				 });
	 		}
		}else{
			dialogFun('提示','两次输入的密码不一致',0);
		}
	}