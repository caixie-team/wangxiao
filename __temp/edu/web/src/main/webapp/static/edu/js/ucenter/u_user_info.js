//修改信息
function formsubmit(){
	if(!$("#updateUserForm").valid()){
		return;
	}
	var date = $("#updateUserForm").serialize();
	$.ajax({
		url:baselocation+'/uc/user/update',
		type:'post',
		dataType:'json',
		data:date,
		success:function (result){
			if(result.success){
				if(result.entity=='success'){
					dialog('提示','修改成功',15);
					$(".u-h-name").html(result.jumpUrl);
				}
				if(result.entity=='mobileHave'){
					dialog('提示',"手机号已存在,请重新填写",1);
				}
				if(result.entity=='emailHave'){
					dialog('提示',"邮箱号已存在,请重新填写",1);
				}
				if(result.entity=='emailFormatError'){
					dialog('提示',"邮箱格式不正确，请填写正确的邮箱",1);
				}
			}else{
				dialog('失败请刷新重试',"失败请刷新重试",1);
			}
		}
	});
}
