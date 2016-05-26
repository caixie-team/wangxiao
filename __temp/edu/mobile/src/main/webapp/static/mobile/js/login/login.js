	function clearError(object){
    	$(object).parent().parent().removeClass("error");
    }
    /**
     * 公共ajax登录方法
     * @param type 登录类型 1重新加载本页面,2跳转到首页，3跳转到传来的URL
     * @param url 要转向的路径
     */
    function pageLogin(type,url){
        /*$("#requestErrorID").parent().parent().hide();*/
        var userName=$("#userEmail").val();
        var pwd = $("#password").val();
        if(userName==null||userName==""){
            $("#email").attr("class","error");
            return;
        }
        if(pwd==null||pwd==""){
        	$("#pwd").attr("class","error");
        	return;
        }
        $.ajax({
            url : baselocation + "/dologin",
            data : {'userForm.email':userName,'userForm.password':pwd},
            type : "post",
            dataType : "json",
            cache : false,
            async : false,
            success : function(result) {
                if(result.success){
                    //如果登录成功，则刷新页面
                    var forwordURL=getCookieFromServer("redirect");
                    if (typeof(forwordURL) != "undefined" && forwordURL) {
                        DeleteCookie("forward");
                        window.location.href = forwordURL.replaceAll('"','');
                        return;
                    }
                    if(type==1){
                        window.location.reload();
                    }else if(type==2){
                        window.location.href = '/mobile/uc/home';
                    }else if(type==3){
                        window.location=url;
                    }
                }else{
                    if(result.message=='formDataNot'){
                    	dialog('登陆提示','用户不存在','',0);
                    }else if(result.message=='inputIllegal'){
                    	dialog('登陆提示','请不要输入非法数据','',0);
                    }else if(result.message=='freezed'){
                    	dialog('登陆提示','您的帐号已被冻结，请联系客服','',0);
                    }else if(result.message=='false'){
                    	dialog('登陆提示','用户名或者密码不正确','',0);
                    }
                }
            }
        });
    }
  	//联合登录,重新打开窗口
    function oauthLogin(appType){
        window.location.href=baselocation+"/app/authlogin?appType="+appType+"&from=mobile";
    }