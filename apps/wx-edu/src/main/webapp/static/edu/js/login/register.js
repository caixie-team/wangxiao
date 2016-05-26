var regType = 'mobile';
var isEmailVali=false;//定义全局唯一验证通过
var isMobileVali=false;//定义全局唯一验证通过
var isPasswordVali=false;
var isConfirmPasswordVali=false;
var isCheckCodeVali=false;
var isEmailCheckCodeVali=false;

$(function(){
	$("#lr-reg-title a").click(function(){
		var _this = $(this);
		if(_this.index()==0){
			$(".type_mobile").show();
			$(".type_email").hide();
			regType = 'mobile';

		}else if(_this.index()==1){
			$(".type_email").show();
			$(".type_mobile").hide();
			regType = 'email';
		}
	});
});

function emailCheck(){//单独验证email
	isEmailVali=false;
	var emailVal=$("#regEmail").val();
	if(isNotEmpty(emailVal)==false){
		$("#register_email_error").html('请输入邮箱');
		$("#register_email_div").show();
		return;
	}
	if(!isEmail(emailVal)){//格式不正确
		$("#register_email_error").html('请输入正确的邮箱');
		$("#register_email_div").show();
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
				$("#register_email_error").html('该邮箱已经注册过');
				$("#register_email_div").show();
			}else{
				$("#register_email_div").hide();
				isEmailVali=true;
			};
		}
	});
	return isEmailVali;
}

function emailCheckCode(){
	isEmailCheckCodeVali=false;
	var checkCodeEmail = $("#checkCodeEmail").val();
	if(isEmpty(checkCodeEmail)){
		$("#email_check_code_div").show();
		return;
	}
	$("#email_check_code_div").hide();
	isEmailCheckCodeVali=true;
}

//单独验证密码
function passCheck(){
	isPasswordVali=false;
	var passVal=$("#regPwd").val();
	var pattern =/^(?!_)(?!_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	if(isNotEmpty(passVal)==false){
		$("#regPwdError").html('请输入密码');
		$("#pwd_div").show();
		return;
	}else if(pattern.test(passVal)==false){
		$("#regPwdError").html('请不要输入非法关键字');
		$("#pwd_div").show();
		return;
	}else if(passVal.indexOf(" ")!=-1){
		$("#regPwdError").html('密码不能包含空格');
		$("#pwd_div").show();
		return;
	}else if(isNotEmpty(passVal)==false){//验证邮箱是否为空
		$("#regPwdError").html('请输入密码');
		$("#pwd_div").show();
		return;
	}else if(passVal.length<6){
		$("#regPwdError").html('密码不能少于六位');
		$("#pwd_div").show();
		return;
	}
	isPasswordVali=true;
	$("#pwd_div").hide();
}
//单独验证重复密码
function passConfirmCheck(){
	isConfirmPasswordVali = false;
	var passConfirmVal=$("#cusPwdConfirm").val();
	var passVal=$("#regPwd").val();
	if(isNotEmpty(passConfirmVal)==false){
		$("#cusPwdConfirmError").html('请输入重复密码');
		$("#confirm_div").show();
		return;
	}else if(passConfirmVal!=passVal) {
		$("#cusPwdConfirmError").html('两次密码输入不一致');
		$("#confirm_div").show();
		return;
	}
	isConfirmPasswordVali = true;
	$("#confirm_div").hide();
}

function mobileCheck(){//单独验证mobile
	isMobileVali=false;
	var mobileVal=$("#regMobile").val();
	if(isNotEmpty(mobileVal)==false){//验证手机是否为空
		$("#register_phone_div").show();
		$("#register_phone_error").html('请输入手机号!');
		return;
	}
	if(!isMobile(mobileVal)){//格式不正确
		$("#register_phone_div").show();
		$("#register_phone_error").html('请输入正确的手机号!');
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
				$("#register_phone_div").show();
				$("#register_phone_error").html('该手机已经注册过!');
			}else{
				$("#register_phone_div").hide();
				isMobileVali=true;
			};
		}
	});
	return isMobileVali;
 }

function phoneCheckCode(){
	isCheckCodeVali=false;
	var checkCode = $("#checkCode").val();
	if(checkCode==null||checkCode==''||checkCode.length<4){
		$("#mobile_check_code_div").show();
		$("#mobile_check_code_error").html("请输入正确的验证码");
		return;
	}else{
		isCheckCodeVali=true;
		$("#mobile_check_code_div").hide();
	}
}

//注册新用户
function register() {
	if(regType=='mobile'){
		if(!isMobileVali){
			$("#register_phone_div").show();
			return;
		}
		if(!isCheckCodeVali){
			$("#mobile_check_code_div").show();
			return;
		}
	}else if(regType=='email'){
		if(!isEmailVali){
			$("#register_email_div").show();
			return;
		}
		if(!isEmailCheckCodeVali){
			$("#email_check_code_div").show();
			return;
		}
	}
	if(!isPasswordVali){
		$("#pwd_div").show();
		return;
	}
	if(!isConfirmPasswordVali){
		$("#confirm_div").show();
		return;
	}
	var abcAgreement = $("input[name='abcAgreement']").prop('checked');
	if(!abcAgreement){
		dialogFun('注册提示','请阅读并同意注册协议！',0);
		return;
	}
	var randomCode="";
	if(regType=='mobile'){
		randomCode = $("#checkCode").val();
	}else if(regType=='email'){
		randomCode = $("#checkCodeEmail").val();
	}
	$.ajax({
		url : baselocation + "/doregister",
		data : {"userForm.email":$("#regEmail").val(),"userForm.password":$("#regPwd").val(),
			"userForm.confirmPassword":$("#cusPwdConfirm").val(),"randomCode":randomCode,
			"userForm.mobile":$("#regMobile").val(),"regType":regType},
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
				$("#register_email_error").html('请输入邮箱');
				$("#register_email_div").show();
			}else if(result.message == 'emailFormatError'){
				$("#register_email_error").html('请输入正确的邮箱');
				$("#register_email_div").show();
			}else if(result.message == "regEmailExist") {
				$("#register_email_error").html('您的邮箱已经注册');
				$("#register_email_div").show();
			}else if(result.message == 'pwdIsNull'){
				$("#regPwdError").html('请输入密码');
				$("#pwd_div").show();
			}else if(result.message == 'pwdNotEqual'){
				$("#cusPwdConfirmError").html('两次密码输入不一致');
				$("#confirm_div").show();
			}else if(result.message == "regMobileFormError") {
				$("#register_phone_error").html('手机号码格式不正确');
				$("#register_phone_div").show();
			}else if(result.message == "regMobileExist") {
				$("#register_phone_error").html('手机号码已经注册');
				$("#register_phone_div").show();
			}else if(result.message == "regDangerWord") {
				dialogFun('注册提示','请不要输入非法关键字',0);
			}else if(result.message == "验证码错误") {
				$("#mobile_check_code_error").html('验证码错误');
				$("#mobile_check_code_div").show();
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
function getPhoneCheckCode(obj){
	// 手机验证通过
	if(isMobile($("#regMobile").val())){
		$.ajax({
			url: baselocation +"/getCheckCode",
			type:"post",
			data:{"mobile":$("#regMobile").val()},
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
					$("#mobile_check_code_div").hide();
				}else{
					$("#mobile_check_code_error").html(result.message);
					$("#mobile_check_code_div").show();
				}
			}
		});
	}
}
function getEmailCheckCode(obj){
	// 邮箱验证通过
	if(isEmail($("#regEmail").val())){
		$.ajax({
			url: baselocation +"/sendEmailCode",
			type:"post",
			data:{"email":$("#regEmail").val()},
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
					$("#email_check_code_div").hide();
				}else{
					$("#email_check_code_error").html(result.message);
					$("#email_check_code_div").show();
				}
			}
		});
	}
}