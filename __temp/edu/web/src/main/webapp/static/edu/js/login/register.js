$().ready(function() {
		//initValidate();
	}); 
var isEmailVali=false;//定义全局唯一验证通过
var isMobileVali=false;//定义全局唯一验证通过

	function emailCheck(){//单独验证email
	 		var emailVal=$("#regEmail").val();
			if(isNotEmpty(emailVal)==false){//验证邮箱是否为空
				$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入邮箱');
				return;
			}
	 		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(\.[a-zA-Z0-9_])+/; //验证邮箱正则
	 		if(reg.test(emailVal)==false){//格式不正确
	 			$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入正确的邮箱');
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
	 					$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>该邮箱已经注册过');
	 				
	 				}else{
	 					$("#emailError").html('<tt class="o-pass"><em class="icon18 vam disIb"></em></tt>');
	 					isEmailVali=true;
	 				};
	 			}
	 		});
		 }
	

	//单独验证密码
	function passCheck(){
		var passVal=$("#regPwd").val();
		
		var pattern =/^(?!_)(?!_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
		if(pattern.test(passVal)==false){
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>请不要输入非法关键字');
			return false;
		}
		if(passVal.indexOf(" ")!=-1){
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>密码不能包含空格');
			return false;
		}
		if(isNotEmpty(passVal)==false){//验证邮箱是否为空
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入密码');
			return false;
		}else if(passVal.length<6){
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>密码不能少于六位');
			return false;
		}

 		$("#regPwdError").html('<tt class="o-pass"><em class="icon18 vam disIb"></em></tt>');
 		return true;
	}
	//单独验证重复密码
	function passConfirmCheck(){
		var passConfirmVal=$("#cusPwdConfirm").val();
		var passVal=$("#regPwd").val();
		if(isNotEmpty(passConfirmVal)==false){//验证邮箱是否为空
			$("#cusPwdConfirmError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入重复密码');
			return false;
		}
		if(passConfirmVal!=passVal)
		{
			$("#cusPwdConfirmError").html('<em class="icon18 vam disIb">&nbsp;</em>两次密码输入不一致');
			return false;
		}
		$("#cusPwdConfirmError").html('<tt class="o-pass"><em class="icon18 vam disIb"></em></tt>');
		return true;
	}
	function mobileCheck(){//单独验证mobile
 		var mobileVal=$("#regMobile").val();
		if(isNotEmpty(mobileVal)==false){//验证手机是否为空
			$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入手机号');
			return;
		}
 		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
 		if(reg.test(mobileVal)==false){//格式不正确
 			$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入正确的手机号');
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
 					$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>该手机已经注册过');
 				
 				}else{
 					$("#regMobileError").html('<tt class="o-pass"><em class="icon18 vam disIb"></em></tt>');
 					isMobileVali=true;
 				};
 			}
 		});
	 }
	
	//注册新用户 
 	function register() {
 		var emailVal=$("#regEmail").val();
		if(isNotEmpty(emailVal)==false){//验证邮箱是否为空
			$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入邮箱');
			return;
		}
 		var reg=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_])+(.[a-zA-Z0-9_])+/; //验证邮箱正则
 		if(reg.test(emailVal)==false){//格式不正确
 			$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入正确的邮箱');
 			return;
 		};
 		
 		
 		if(isNotEmpty($("#regPwd").val())==false){//验证密码是否为空
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入密码');
			return;
		}
 		if($("#regPwd").val().length<6){//验证密码长度
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>密码长度不能小于六位');
			return;
		}
 		if(($("#regPwd").val()).indexOf(" ")!=-1){
			$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>密码不能包含空格');
			return false;
		}
		if(isNotEmpty($("#cusPwdConfirm").val())==false){//验证确认密码是否为空
			$("#cusPwdConfirmError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入确认密码');
			return;
		}
 		
 		var mobileVal=$("#regMobile").val();
		if(isNotEmpty(mobileVal)==false){//验证手机是否为空
			$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入手机号');
			return;
		}
 		var reg=/^(13[0-9]|15[012356789]|18[012356789]|14[57]|17[012356789])[0-9]{8}$/; //验证手机正则
 		if(reg.test(mobileVal)==false){//格式不正确
 			$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入正确的手机号');
 			return;
 		};
 		if(isNotEmpty($("#randomcode").val())==false){//验证 验证码是否为空
			$("#randomcodeError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入验证码');
			return;
		}

 		/*if($("#regForm input").valid()) {//验证
*/ 			var t268xueAgreement = $("input[name='t268xueAgreement']").prop('checked');
 	 		if(!t268xueAgreement){
 	 			dialog('注册提示','请阅读并同意注册协议！',1);
 	 			return;
 	 		}
 	 		/*//序列化
 	 		var params="";
        //ie7 表单兼容
        $("#regForm input").each(function(){
            if($(this).val()!='请输入密码'&&$(this).val()!='请再输入一次'){
                params+=$(this).serialize()+"&";
            }
        });*/

			$.ajax({
				url : baselocation + "/doregister",
				data : {"userForm.email":$("#regEmail").val(),"userForm.password":$("#regPwd").val(),
					"userForm.confirmPassword":$("#cusPwdConfirm").val(),"randomCode":$("#randomcode").val(),
					"userForm.mobile":$("#regMobile").val()},
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
						window.location.href = baselocation + "/uc/home";
					}else if(result.message == 'formDataIsNot'){
						dialog('注册提示','表单数据不为能为空',1);
					}else if(result.message == 'emailIsNot'){
						$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入邮箱');
					}else if(result.message == 'emailFormatError'){
						$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入正确的邮箱');
					}else if(result.message == 'pwdIsNull'){
						$("#regPwdError").html('<em class="icon18 vam disIb">&nbsp;</em>请输入密码');
					}else if(result.message == 'pwdNotEqual'){
						$("#cusPwdConfirmError").html('<em class="icon18 vam disIb">&nbsp;</em>两次密码输入不一致');
					}else if(result.message == "regMobileFormError") {
						$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>手机号码格式不正确');
					}else if(result.message == "regMobileExist") {
						$("#regMobileError").html('<em class="icon18 vam disIb">&nbsp;</em>手机号码已经注册');
					}else if(result.message == "regEmailExist") {
						$("#emailError").html('<em class="icon18 vam disIb">&nbsp;</em>您的邮箱已经注册');
					}else if(result.message == "regDangerWord") {
						dialog('注册提示','请不要输入非法关键字',1);
					}else if(result.message == "验证码错误") {
						$("#randomcodeError").html('<em class="icon18 vam disIb">&nbsp;</em>验证码错误');
					}else {
						dialog('注册提示',result.message,1);
					}
				},
				error : function(error) {
					dialog('注册提示','系统繁忙，请稍后再操作',1);
				}
			});
 		/*}*/
 	}
 	function gohsData(id){
		$("#"+id).html('');
	}
	
	