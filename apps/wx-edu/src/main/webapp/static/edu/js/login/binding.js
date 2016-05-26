var regType = $("#regType").val();
var isEmailVali=false;//定义全局唯一验证通过
var isMobileVali=false;//定义全局唯一验证通过
var isPasswordVali=false;
var isConfirmPasswordVali=false;
var isCheckCodeVali=false;
var isEmailCheckCodeVali=false;

/**
 * 绑定
 */
function binding(){
	$.ajax({
		url: baselocation +"/binding",
		type:"post",
		data:$("#openLogin").serialize(),
		dataType: "json",
		success:function(result){
			if(result.success){
				window.location.href=baselocation+"/uc/home";
			}else{
				dialogFun('提示',result.entity,0);
			};
		}
	});
}


/**
 * 切换注册方式
 * @param obj
 */
function changeRegisterType(obj){
	var _this = $(obj);
	if(_this.index()==0){
		$(".type_mobile").show();
		$(".type_email").hide();
		regType = 'mobile';

	}else if(_this.index()==1){
		$(".type_email").show();
		$(".type_mobile").hide();
		regType = 'email';
	}
	$("#regType").val(regType)
}
/**
 * 注册
 */
function bindingRegister(){
	if(regType=='mobile'){
		if(!isMobileVali){
			$("#mobile_error").show();
			return;
		}
		if(!isCheckCodeVali){
			$("#randomCode_error").show();
			return;
		}
	}else if(regType=='email'){
		if(!isEmailVali){
			$("#email_error").show();
			return;
		}
	/*	if(!isEmailCheckCodeVali){
			$("#randomCodeEmail_error").show();
			return;
		}*/
	}
	if(!isPasswordVali){
		$("#passwordError").show();
		return;
	}
	if(!isConfirmPasswordVali){
		$("#confirmPasswordError").show();
		return;
	}
	var nxbAgreement = $("input[name='nxbAgreement']").prop('checked');
	if(!nxbAgreement){
		dialogFun('注册提示','请阅读并同意注册协议！',0);
		return;
	}
	var randomCode="";
	if(regType=='mobile'){
		randomCode = $("#randomCode").val();
	}else if(regType=='email'){
		randomCode = $("#randomCodeEmail").val();
	}
	$.ajax({
		url : baselocation + "/registerBinding",
		/*data : {"userForm.email":$("#regEmail").val(),"userForm.password":$("#password").val(),
			"userForm.confirmPassword":$("#cusPwdConfirm").val(),"randomCode":randomCode,
			"userForm.mobile":$("#regMobile").val(),"regType":regType},*/
		data:$("#openRegister").serialize(),
		type : "post",
		dataType : "json",
		cache : false,
		async : false,
		success : function(result) {
			if(result.success) {
				var forwordURL=getCookie("forward");
				if (typeof(forwordURL) != "undefined" && forwordURL) {
					DeleteCookie("forward");
					window.location.href = forwordURL.replaceAll('"','');
					return;
				}
				window.location.href = baselocation + "/uc/home";
			}else if(result.message == 'formDataIsNot'){
				dialogFun('注册提示','表单数据不为能为空',0);
			}else if(result.message == 'emailIsNot'){
				$("#email_error").html('请输入邮箱');
				$("#email_error").show();
			}else if(result.message == 'emailFormatError'){
				$("#email_error").html('请输入正确的邮箱');
				$("#email_error").show();
			}else if(result.message == "regEmailExist") {
				$("#email_error").html('您的邮箱已经注册');
				$("#email_error").show();
			}else if(result.message == 'pwdIsNull'){
				$("#passwordError").html('请输入密码');
				$("#passwordError").show();
			}else if(result.message == 'pwdNotEqual'){
				$("#confirmPasswordError").html('两次密码输入不一致');
				$("#confirmPasswordError").show();
			}else if(result.message == "regMobileFormError") {
				$("#mobile_error").html('手机号码格式不正确');
				$("#mobile_error").show();
			}else if(result.message == "regMobileExist") {
				$("#mobile_error").html('手机号码已经注册');
				$("#mobile_error").show();
			}else if(result.message == "regDangerWord") {
				dialogFun('注册提示','请不要输入非法关键字',0);
			}else if(result.message == "验证码错误") {
				$("#randomCode_error").html('验证码错误');
				$("#randomCode_error").show();
			}else {
				dialogFun('注册提示',result.message,0);
			}
		},
		error : function(error) {
			dialogFun('注册提示','系统繁忙，请稍后再操作',0);
		}
	});
}

var timer;
var time=60;
/**
 * 获取手机验证码
 * @param obj
 */
function getPhoneCheckCode(obj){
	// 手机验证通过
	if(isMobile($("#mobile").val())){
		$.ajax({
			url: baselocation +"/getCheckCode",
			type:"post",
			data:{"mobile":$("#mobile").val()},
			dataType: "json",
			success:function(result){
				if(result.success){
					timer = setInterval(function(){
						if(time<0){
							clearInterval(timer);
							$(obj).html("获取验证码");
							$(obj).css("cursor","pointer");
							$(obj).attr("onclick","getPhoneCheckCode(this)");
							return;
						}
						$(obj).css("cursor","default");
						$(obj).attr("onclick","");
						$(obj).html(time+"秒");
						time--;
					},1000);
					console.log(result.entity);
					$("#randomCode_error").hide();
				}else{
					$("#randomCode_error").html(result.message);
					$("#randomCode_error").show();
				}
			}
		});
	}
}
/**
 * 获取邮件验证码
 * @param obj
 */
function getEmailCheckCode(obj){
	// 邮箱验证通过
	if(isEmail($("#email").val())){
		$.ajax({
			url: baselocation +"/sendEmailCode",
			type:"post",
			data:{"email":$("#email").val()},
			dataType: "json",
			success:function(result){
				if(result.success){
					timer = setInterval(function(){
						if(time<0){
							clearInterval(timer);
							$(obj).html("获取验证码");
							$(obj).css("cursor","pointer");
							$(obj).attr("onclick","getEmailCheckCode(this)");
							return;
						}
						$(obj).css("cursor","default");
						$(obj).attr("onclick","");
						$(obj).html(time+"秒");
						time--;
					},1000);
					console.log(result.entity);
					$("#randomCodeEmail_error").hide();
				}else{
					$("#randomCodeEmail_error").html(result.message);
					$("#randomCodeEmail_error").show();
				}
			}
		});
	}
}


/**
 * 邮件验证
 * @returns {Boolean}
 */
function emailCheck(){//单独验证email
	isEmailVali=false;
	var emailVal=$("#email").val();
	if(isNotEmpty(emailVal)==false){
		$("#email_error").html('请输入邮箱');
		$("#email_error").show();
		return;
	}
	if(!isEmail(emailVal)){//格式不正确
		$("#email_error").html('请输入正确的邮箱');
		$("#email_error").show();
		return;
	};
	//验证邮箱是否存在
	$.ajax({
		url: baselocation +"/existsEmail",
		type:"post",
		data:{"email":emailVal},
		dataType: "json",
		success:function(result){
			if(result){
				$("#email_error").html('该邮箱已经注册过');
				$("#email_error").show();
			}else{
				$("#email_error").hide();
				isEmailVali=true;
			};
		}
	});
	return isEmailVali;
}
/**
 * 邮件验证码检验
 */
function emailCheckCode(){
	isEmailCheckCodeVali=false;
	var checkCodeEmail = $("#randomCodeEmail").val();
	if(isEmpty(checkCodeEmail)){
		$("#randomCodeEmail_error").show();
		return;
	}
	$("#randomCodeEmail_error").hide();
	isEmailCheckCodeVali=true;
}

//单独验证密码
function passCheck(){
	isPasswordVali=false;
	var passVal=$("#password").val();
	var pattern =/^(?!_)(?!_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	if(isNotEmpty(passVal)==false){
		$("#passwordError").html('请输入密码');
		$("#passwordError").show();
		return;
	}else if(pattern.test(passVal)==false){
		$("#passwordError").html('请不要输入非法关键字');
		$("#passwordError").show();
		return;
	}else if(passVal.indexOf(" ")!=-1){
		$("#passwordError").html('密码不能包含空格');
		$("#passwordError").show();
		return;
	}else if(isNotEmpty(passVal)==false){//验证邮箱是否为空
		$("#passwordError").html('请输入密码');
		$("#passwordError").show();
		return;
	}else if(passVal.length<6){
		$("#passwordError").html('密码不能少于六位');
		$("#passwordError").show();
		return;
	}
	isPasswordVali=true;
	$("#passwordError").hide();
}
//单独验证重复密码
function passConfirmCheck(){
	isConfirmPasswordVali = false;
	var passConfirmVal=$("#confirmPassword").val();
	var passVal=$("#password").val();
	if(isNotEmpty(passConfirmVal)==false){
		$("#confirmPasswordError").html('请输入重复密码');
		$("#confirmPasswordError").show();
		return;
	}else if(passConfirmVal!=passVal) {
		$("#confirmPasswordError").html('两次密码输入不一致');
		$("#confirmPasswordError").show();
		return;
	}
	isConfirmPasswordVali = true;
	$("#confirmPasswordError").hide();
}
/**
 * 手机验证
 * @returns {Boolean}
 */
function mobileCheck(){//单独验证mobile
	isMobileVali=false;
	var mobileVal=$("#mobile").val();
	if(isNotEmpty(mobileVal)==false){//验证手机是否为空
		$("#mobile_error").show();
		$("#mobile_error").html('请输入手机号!');
		return;
	}
	if(!isMobile(mobileVal)){//格式不正确
		$("#mobile_error").show();
		$("#mobile_error").html('请输入正确的手机号!');
		return;
	};
	//验证手机是否存在
	$.ajax({
		url: baselocation +"/existsMobile",
		type:"post",
		data:{"mobile":mobileVal},
		dataType: "json",
		success:function(result){
			if(result){
				$("#mobile_error").show();
				$("#mobile_error").html('该手机已经注册过!');
			}else{
				$("#mobile_error").hide();
				isMobileVali=true;
			};
		}
	});
	return isMobileVali;
 }
/**
 * 手机验证码验证
 */
function phoneCheckCode(){
	isCheckCodeVali=false;
	var checkCode = $("#randomCode").val();
	if(checkCode==null||checkCode==''||checkCode.length<4){
		$("#randomCode_error").show();
		$("#randomCode_error").html("请输入正确的验证码");
		return;
	}else{
		isCheckCodeVali=true;
		$("#randomCode_error").hide();
	}
}