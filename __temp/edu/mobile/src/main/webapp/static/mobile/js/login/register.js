	function clearError(name){
    	$("#"+name).removeClass("error");
    	$("#"+name+"Error").html("");
    }
	var isEmailVali=false;//定义全局唯一验证通过
	var isMobileVali=false;//定义全局唯一验证通过

	function emailCheck(){//单独验证email
		var emailVal=$("#regEmail").val();
		if(isNotEmpty(emailVal)==false){//验证邮箱是否为空
			$("#emailError").html("x<q>请输入邮箱</q>");
			$("#email").attr("class","error");
			return;
		}
		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
		if(reg.test(emailVal)==false){//格式不正确
			$("#emailError").html("x<q>请输入正确的邮箱</q>");
			$("#email").attr("class","error");
			return;
		};
		//验证邮箱是否存在
		$.ajax({
			url: baselocation +"/checkEmail",
			type:"post",
			data:{"userForm.email":emailVal},
			dataType: "json",
			success:function(result){
				if(result==false){
					$("#emailError").html("x<q>该邮箱已经注册过</q>");
					$("#email").attr("class","error");
				}else{
					isEmailVali=true;
				};
			}
		});
   }
	

	//单独验证密码
	function passCheck(){
		var passVal=$("#regPwd").val();
		if(isNotEmpty(passVal)==false){//验证密码是否为空
			$("#pwdError").html("x<q>请输入密码</q>");
			$("#pwd").attr("class","error");
			return;
		}else if(passVal.length<6){
			$("#pwdError").html("x<q>密码不能少于六位</q>");
			$("#pwd").attr("class","error");
			return;
		}
		var pattern = /^(?!_)(?!_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
		if(pattern.test(passVal)==false){
			$("#pwdError").html("x<q>请不要输入非法关键字</q>");
			$("#pwd").attr("class","error");
			return;
		}
		if(passVal.indexOf(" ")!=-1){
			$("#pwdError").html("x<q>密码不能包含空格</q>");
			$("#pwd").attr("class","error");
			return;
		}
		
 		return true;
	}
	//单独验证重复密码
	function passConfirmCheck(){
		var passConfirmVal=$("#cusPwdConfirm").val();
		var passVal=$("#regPwd").val();
		if(isNotEmpty(passConfirmVal)==false){//验证确认密码是否为空
			$("#confirmPwdError").html("x<q>请输入确认密码</q>");
			$("#confirmPwd").attr("class","error");
			return;
		}
		if(passConfirmVal!=passVal)
		{
			$("#confirmPwdError").html("x<q>两次密码输入不一致</q>");
			$("#confirmPwd").attr("class","error");
			return;
		}
		return true;
	}
	function mobileCheck(){//单独验证mobile
 		var mobileVal=$("#regMobile").val();
		if(isNotEmpty(mobileVal)==false){//验证手机是否为空
			$("#mobileError").html("x<q>请输入手机号</q>");
			$("#mobile").attr("class","error");
			return;
		}
 		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
 		if(reg.test(mobileVal)==false){//格式不正确
 			$("#mobileError").html("x<q>请输入正确的手机号</q>");
			$("#mobile").attr("class","error");
 			return;
 		};
 		//验证手机是否存在
 		$.ajax({
 			url: baselocation +"/checkRegMoblie",
 			type:"post",
 			data:{"mobile":mobileVal},
 			dataType: "json",
 			success:function(result){
 				if(result==false){
 					$("#mobileError").html("x<q>该手机已经注册过</q>");
 					$("#mobile").attr("class","error");
 				}else{
 					isMobileVali=true;
 				};
 			}
 		});
	 }
	
	//注册新用户 
 	function register() {
 		var emailVal=$("#regEmail").val();
		if(isNotEmpty(emailVal)==false){//验证邮箱是否为空
			$("#email").attr("class","error");
			return;
		}
 		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
 		if(reg.test(emailVal)==false){//格式不正确
 			$("#emailError").html("x<q>请输入正确的邮箱</q>");
 			$("#email").attr("class","error");
 			return;
 		};
 		
 		
 		if(isNotEmpty($("#regPwd").val())==false){//验证密码是否为空
 			$("#pwd").attr("class","error");
			return;
		}
 		if($("#regPwd").val().length<6){//验证密码长度
 			$("#pwdError").html("x<q>密码长度不能小于六位</q>");
 			$("#pwd").attr("class","error");
			return;
		}
 		if(($("#regPwd").val()).indexOf(" ")!=-1){
 			$("#pwdError").html("x<q>密码不能包含空格</q>");
 			$("#pwd").attr("class","error");
			return false;
		}
		if(isNotEmpty($("#cusPwdConfirm").val())==false){//验证确认密码是否为空
			$("#confirmPwd").attr("class","error");
			return;
		}
		if($("#cusPwdConfirm").val()!=$("#regPwd").val())
		{
			$("#confirmPwdError").html("x<q>两次密码输入不一致</q>");
			$("#confirmPwd").attr("class","error");
			return;
		}
 		var mobileVal=$("#regMobile").val();
		if(isNotEmpty(mobileVal)==false){//验证手机是否为空
			$("#mobile").attr("class","error");
			return;
		}
 		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
 		if(reg.test(mobileVal)==false){//格式不正确
 			$("#mobileError").attr("x<q>请输入正确的手机号</q>");
			$("#mobile").attr("class","error");
 			return;
 		};
 		if(isNotEmpty($("#randomcode").val())==false){//验证 验证码是否为空
			$("#code").addClass("error");
			return;
		}

 		
 			var t268xueAgreement = $("input[name='t268xueAgreement']").prop('checked');
 	 		if(!t268xueAgreement){
 	 			dialog('注册提示','请同意用户协议！','',0);
 	 			return;
 	 		}
 	 		//序列化
 	 		var params="";
	        //ie7 表单兼容
 	 		params=$("#regForm").serialize();

			$.ajax({
				url : baselocation + "/doregister",
				data : params,
				type : "post",
				dataType : "json",
				cache : false,
				async : false,
				success : function(result) {
					if(result.success==true) {
						var forwordURL=getCookie("forward");
						if (typeof(forwordURL) != "undefined" && forwordURL) {
							DeleteCookie("forward");
							window.location.href = forwordURL.replaceAll('"','');
							return;
						}
						window.location.href = baselocation + "/mobile/uc/home";
					}else if(result.message == 'formDataIsNot'){
						dialog('注册提示','表单数据不为能为空','',0);
					}else if(result.message == 'emailIsNot'){
						$("#emailError").html("x<q>请输入正确的邮箱</q>");
			 			$("#email").attr("class","error");
					}else if(result.message == 'emailFormatError'){
						$("#emailError").html("x<q>请输入正确的邮箱</q>");
			 			$("#email").attr("class","error");
					}else if(result.message == 'pwdIsNull'){
			 			$("#pwd").attr("class","error");
					}else if(result.message == 'pwdNotEqual'){
						$("#confirmPwdError").html("x<q>两次密码输入不一致</q>");
						$("#confirmPwd").attr("class","error");
					}else if(result.message == "regMobileFormError") {
						$("#mobileError").html("x<q>请输入正确的手机号</q>");
						$("#mobile").attr("class","error");
					}else if(result.message == "regMobileExist") {
						$("#mobileError").html("x<q>该手机号码已经注册</q>");
						$("#mobile").attr("class","error");
					}else if(result.message == "regEmailExist") {
						$("#emailError").html("x<q>该邮箱已注册</q>");
			 			$("#email").attr("class","error");
					}else if(result.message == "regDangerWord") {
						dialog('注册提示','请不要输入非法关键字','',0);
					}else if(result.message == "验证码错误") {
						$("#codeError").html("x");
						$("#code").addClass("error");
					}else {
						dialog('注册提示',result.message,'',0);
					}
				},
				error : function(error) {
					dialog('注册提示','系统繁忙，请稍后再操作','',0);
				}
			});
 		
 	}
 	function gohsData(id){
		$("#"+id).html('');
	}
	
	