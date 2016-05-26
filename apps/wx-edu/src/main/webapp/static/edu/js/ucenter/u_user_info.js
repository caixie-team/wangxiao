//修改信息
function formsubmit(){
	var date = $("#updateUserForm").serialize();
	$.ajax({
		url:baselocation+'/uc/user/update',
		type:'post',
		dataType:'json',
		data:date,
		success:function (result){
			if(result.success){
				if(result.entity=='success'){
					dialogFun('提示','修改成功',5,"");
				}
				if(result.entity=='mobileHave'){
					dialogFun('提示',"手机号已存在,请重新填写",0);
				}
				if(result.entity=='emailHave'){
					dialog('提示',"邮箱号已存在,请重新填写",0);
				}
				if(result.entity=='emailFormatError'){
					dialog('提示',"邮箱格式不正确，请填写正确的邮箱",0);
				}
			}else{
				dialog('失败请刷新重试',"失败请刷新重试",0);
			}
		}
	});
}