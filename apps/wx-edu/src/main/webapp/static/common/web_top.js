$(function() {
	// 头部登陆未登录状态是否显示
	queryUnReadNum();
	//headerShow();
});
//联合登录,重新打开窗口
function oauthLogin(appType){
    window.location.href=baselocation+"/app/authlogin?appType="+appType;
}
function queryUnReadNum(){//查询未读消息
	if(userId>0){
		$.ajax({
			type : "POST",
			dataType : "json",
			url:baselocation+"/letter/queryUnReadLetter",
			cache : true,
			async : false,
			success : function(result) {
				var letter = result.entity;
				if(letter==null){
					return;
				}
				//未读消息数
				var unReadNum = letter.unReadNum;
				if(unReadNum>0){
					$(".msg-red").html(unReadNum);
					$(".msg-red").show();
				}else{
					$(".msg-red").hide();
				}
			}
		});
	}
}

// 继续购买
function goCorder() {
	if (isLogin()) {
		window.location.href = baselocation
				+ "/order?queryContractCondition.currentPage=1";
	} else {
		window.location.href = baselocation + '/login';
	}
}
// 提示消息
function showDialog(dTitle, conent) {
	var oBg = $('<div class="bg-shadow"></div>').appendTo($("body")), dialogEle = $('<div class="dialog-shadow"><div class="dialog-ele"><h4 class="d-s-head pr"><a href="javascript:void(0)" title="关闭" class="dClose icon16 pa">&nbsp;</a><span class="d-s-head-txt">'
			+ dTitle
			+ '</span></h4><div id="dcWrap" class="pt20 pb20 pl20 pr20 of" style=""></div></div></div>')
			.appendTo($("body"));

	var dCont = [
			"<div class='d-tips-1'><em class='icon30 pa d-t-icon-1'></em><p class='fsize14 c-666'>"
					+ conent
					+ "</p><div class='tac mt20'><a href='javascript:void(0);' title='' class='blue-btn ml10 dClose'>确定</a></div><p class='tar mt20 c-666'></p></div>",
			"<div></div>", "<div></div>"];
	$("#dcWrap").html(dCont[0]);

	var dTop = (parseInt(document.documentElement.clientHeight, 10) / 2)
			+ (parseInt(document.documentElement.scrollTop
							|| document.body.scrollTop, 10)), dH = dialogEle
			.height(), dW = dialogEle.width();
	dialogEle.css({
				"top" : (dTop - (dH / 2)),
				"margin-left" : -(dW / 2)
			});
	$(".dClose").bind("click", function() {
				dialogEle.remove();
				oBg.remove();
			});
}
// 已完成支付
function goOrder() {
	window.location.href = baselocation + '/order';
}

// 录输入用户名或密码时，会清空错误提示信息
function userOrPwdChange(type, id) {
	$("#requestErrorID").html('');
	if (type == 1) {
		var userName = $("#" + id).val();
		if (userName != null && userName.trim() != '') {
			$("#userNameError").html('');
			return false;
		}
	} else if (type == 2) {
		var pwd = $("#" + id).val();
		if (pwd != null && pwd.trim() != '') {
			$("#passwordError").html('');
			return false;
		}
	}
}

/**
 * 头部点击哪一个，就改其中的样式
 */
function headerShow() {
	var pageUrl = window.location.href;
	$.each($("#guideInfo a"), function(i, n){
		var href = $(this).attr("href");
		if(pageUrl=="/" || pageUrl==baselocation ||pageUrl==(baselocation+"/")){
			$(this).parent().addClass("current");
			return false;
		}
		if(urlindexOf(pageUrl, $(this).attr("href"))){
			if(href!="/"&&href!=baselocation){
				$(this).parent().addClass("current");
				return false;
			}
		}
	});
}
// str1是否包含str2
function urlindexOf(str1, str2) {
	return str1.indexOf(str2) != -1;
}

// commonjs
// 收藏本站
function addFavorite() {
	var _url = baselocation, _tit = 'xxx公司';
	if (window.sidebar && window.sidebar.addPanel) {// 新版火狐不再支持window.sidebar.addPanel
		try {
			window.sidebar.addPanel(_tit, _url, "");
		} catch (e) {
			showDialog('提示消息',
					'您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	} else if (document.all) {// IE类浏览器
		try {
			var external = window.external;
			external.AddFavorite(_url, _tit);
		} catch (e) {
			showDialog('提示消息',
					'国内开发的360浏览器等不支持主动加入收藏\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	} else {
		showDialog('提示消息', '您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
	}
}
//购买商品
function BuyNow(courseId){
    //添加到购物车
	$.ajax({//验证课程金额
		url:baselocation+"/course/check/"+courseId,
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.message!='true'){
				dialogFun('提示信息',result.message,0);
			}else{
				window.location.href="/shopcart?goodsid="+courseId+"&type=1&command=addShopitem";
			}
		}
	})
	
}


// 加入收藏
function addFavorite(){
	var _url = baselocation,
	_tit = 'xxx公司';
	if(window.sidebar && window.sidebar.addPanel) {//新版火狐不再支持window.sidebar.addPanel
		try{
			window.sidebar.addPanel(_tit, _url, "");
		}catch (e) {
			showDialog('提示消息','您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	}else if(document.all) {//IE类浏览器
		try{
		var external = window.external;
		external.AddFavorite(_url, _tit);
		}catch(e){
			showDialog('提示消息','国内开发的360浏览器等不支持主动加入收藏\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
		}
	}else {
		showDialog('提示消息','您使用的浏览器不支持此操作。\n请使用 Ctrl + D 进行添加，或手动在浏览器里进行设置。');
	}
}

//课程收藏
 function house(couresId){
 		if(isLogin()){
 			$.ajax({
 				url:baselocation+"/front/addfavorites",
 				data:{'courseId':couresId},
 				type : "post",
 				dataType : "json",
 				success: function (result){
 					if(result.message=="success"){
 						$("#sc-a").addClass("sc-already");
 						dialogFun('收藏提示',"收藏成功",5,window.location.href);
 					}
 					if(result.message=="false"){
						dialogFun('收藏提示',"收藏失败",0);
 					}
 					if(result.message=="owned"){
						dialogFun('收藏提示',"您已收藏过，请不要重复收藏",0,"javascript:void(0)");
 					}
 				}
 			});
 		}else{
			lrFun();
 		}
 	}
/**
 * 公共ajax登录方法
 * @param type 登录类型 1重新加载本页面,2跳转到首页，3跳转到传来的URL
 * @param url 要转向的路径
 */
function pageLogin(type,url){
    /*$("#requestErrorID").parent().parent().hide();*/
    var userName=$("#userEmail").val();
    var pwd = $("#userPassword").val();
    var autoThirty=$("#autoThirty").prop("checked")
    $("#login_account_error").html('');
    $("#login_password_error").html('');
    $(".bm-lr-jy-box").hide();
    if(!isNotEmpty(userName)){
        $("#login_account_error").html('请输入邮箱/手机号！');
        $("#login_account_div").show();
        return false;
    }
    if(!isNotEmpty(pwd)){
    	$("#login_password_error").html('请输入密码！');
    	$("#login_password_div").show();
        return false;
    }
    $.ajax({
        url : baselocation + "/dologin",
        data : {'userForm.email':userName,'userForm.password':pwd,"autoThirty":autoThirty},
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
                    window.location.href = baselocation+'/';
                }else if(type==3){
                    window.location=url;
                }
            }else{
                if(result.message=='formDataNot'){
                    $("#login_account_div").show();
                    $("#login_account_error").html("用户名不存在");
                }else if(result.message=='inputIllegal'){
                    $("#login_account_div").show();
                    $("#login_account_error").html("请不要输入非法数据");
                }else if(result.message=='freezed'){
                    $("#login_account_div").show();
                    $("#login_account_error").html("您的帐号已被冻结，请联系客服");
                }else if(result.message=='false'){
                	  $("#login_password_div").show();
                      $("#login_password_error").html("密码不正确");
                }
            }
        }
    });
}
/**
 * 显示购物车数量
 */
function showshopnums(){
    $.ajax({
        url:"/shopcart/ajax/shopcartnums",
        data:{"type":1},
        type : "post",
        dataType : "json",
        success: function (result){
            if(result.entity && isNotNull(result.entity ) && result.entity.length>0){
                $("#myCartNum").html(result.entity.length);
                $("#myCartNum").show();
            }else{
                $("#myCartNum").html(0);
                $("#myCartNum").hide();
            }
        }
    });
}

//删除购物车
function deleteCartId(id,goodsid,type) {
    $.ajax({
        url : baselocation + "/shopcart/ajax/deleteShopitem",
        data : {
            "id":id,
            'goodsid' : goodsid,
            "type":type
        },
        type : "post",
        dataType : "json",
        async : false,
        cache : false,
        success : function(result) {
            headerCartHtml();
        }
    });
}
// 反馈
function feedback(){
	dialogFun("反馈","",4);
}
//提交反馈新行
function sumbitFeed() {
	var content = $("#content").val();
	if (content == "") {
		$("#contentErrorClass").html('请输入您要反馈的信息!');
		return;
	}else{
		$("#contentErrorClass").html('');
	}
	var mobile = $("#mobile").val();
	var qq = $("#qq").val();
	var email = $("#email").val();
	var name = $("#name").val();
	$.ajax({
		url : baselocation+"/front/addfreeback",
		data : {
			"userFeedback.content" : content,
			"userFeedback.mobile" : mobile,
			"userFeedback.qq" : qq,
			"userFeedback.email" : email,
			"userFeedback.name" : name,
		},
		dataType : "json",
		type : "post",
		async : false,
		success : function(result) {
			closeFun()
			if (result.success == true) {
				dialogFun('提示', '反馈信息已经添加成功', 3);
				//$("#mobile,#qq,#email,#name,#content").val('');
			} else {
				dialogFun('提示', '系统繁忙，请稍后操作', 3);
			}
		}
	})
}