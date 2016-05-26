//解绑提示
function exclideBungingConfirm(profileType,id){
	var typeName = '';
	if(profileType=='QQ'){
		typeName='QQ';
	}else if(profileType=='SINA'){
		typeName='新浪微博'
	}else if(profileType=='WEIXIN'){
		typeName='微信';
	}
	dialogFun('解除绑定','',7,'javascript:exclideBunging('+id+')');
	$("#typeName").html(typeName);
}
//解绑
function exclideBunging(id){
	var pwd=$("#excludeBundingPwd").val();
	if(isNotEmpty(pwd)){
		//验证密码是否正确  正确将进行解绑操作
		$.ajax({
			url:baselocation+"/uc/ajax/excludeBunging",
			data:{"id":id,"pwd":pwd},
			type:"post",
			dataType:"json",
			cache : false,
			async:false,
			success:function(result){
				if(result.success){
					setTimeout(function(){
						dialogFun('解绑提示',result.message,5,window.location.href);
					},300);
				}else{
					setTimeout(function(){
						dialogFun('解绑提示',result.message,0);
					},300);
				}
			}
		});
	}else{
		dialogFun('解绑提示','请输入密码',0);
	}
}
//联合登录,重新打开窗口
function oauthLogin(appType){
	window.location.href=baselocation+"/app/authlogin?appType="+appType;
}