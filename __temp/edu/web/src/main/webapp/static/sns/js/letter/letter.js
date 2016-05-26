function deldialog_sns(){//弹出框关闭方法
	$(".dContent .dClose,.dCancel").click();
}

function addLetterInput(cusId,obj){//添加发送站内信的发送框
	$("#del"+cusId).append('<span id="delmessage'+cusId+'"><br/><input type="text" id="message'+cusId+'"/><input type="button" class="reply-sub ml10" value="发送" onclick="sendLetter('+cusId+')"/></span>');
	$(obj).attr("onclick","delLetterInput("+cusId+",this)");
}
function delLetterInput(cusId,obj){//移除发送站内信的发送框
	$("#delmessage"+cusId).remove();
	$(obj).attr("onclick","addLetterInput("+cusId+",this)");//设置发消息按钮为移除站内信方法
}
function sendLetter(cusId){//发送站内信
	var content = $("#message"+cusId).val();
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/addLetter",
		data:{"msgReceive.receivingCusId":cusId,
			"msgReceive.content":content},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$(".sendmessage"+cusId).click();//点击发消息按钮隐藏发送框
				dialog_sns("发送成功", 6);
			}
			if(result.message=="false"){ 
				dialog_sns("发送失败请重试", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
			if(result.message=="blackList"){ //操作失败，对方把你加入黑名单中不能发送消息
				$(".sendmessage"+cusId).click();//点击发消息按钮隐藏发送框
				dialog_sns("对方已把你加入黑名单", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
			if(result.message=="oneSelfLetter"){ //自己不能给自己发消息
				$(".sendmessage"+cusId).click();//点击发消息按钮隐藏发送框
				dialog_sns("不能给自己发消息", 6);
			}
			
		}
	});
}
function toaddremarks(cusId,obj){//修改备注
	$("#remarksContent"+cusId).val("");//清空修改备注的框
	$("#remarks"+cusId).show();//展示修改备注的框
	$(obj).attr("onclick","hideaddremarks("+cusId+",this)");//修改备注方法为隐藏添加框的方法
}
function hideaddremarks(cusId,obj){//隐藏备注添加框
	$("#remarks"+cusId).hide();//隐藏修改备注的框
	$(obj).attr("onclick","toaddremarks("+cusId+",this)");
}

function addremarks(cusId){
	var remarksContent = $("#remarksContent"+cusId).val();//获得修改备注的内容
	if(remarksContent.length>15){//验证备注内容不能大于10
		dialog_sns("请输入15个字以内", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return;
	}
	$.ajax({//修改备注
		type:"POST",
		dataType:"json",
		url:baselocation+"/friend/addRemarks",
		data:{"friend.cusFriendId":cusId,
			"friend.remarks":remarksContent},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ //修改成功
				$(".customerName"+cusId).html(remarksContent);
				$("#buttonremarks"+cusId).click();//模拟点击备注按钮隐藏修改备注的框
				dialog_sns("备注成功", 6);
			}
		}
	});
}
function delFriend(cusId){//删除好友
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/friend/delFriend",
		data:{"friend.cusFriendId":cusId},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+cusId).remove();
				dialog_sns("删除成功", 6);
			}
			if(result.message=="false"){ 
				$("#del"+cusId).remove();
				dialog_sns("删除失败请重试", 6);
			}
		}
	});
}
function quxiaoAttentionCustomer(cusId){//取消关注
	dialog_sns("确定取消关注？",2);
	$(".queding2").attr("href","javascript:quxiaoAttentionCustomerconfirm("+cusId+")");//删除回复的方法
}
function quxiaoAttentionCustomerconfirm(cusId){//取消关注
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/weibo/quxiaoAttentionCustomer",
		data:{"friId":cusId},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+cusId).remove();
				dialog_sns("取消成功", 6);
			}
		}
	});
}
function sendJoinFriend(cusId){//添加好友
	 $.ajax({
		  type : "POST",
		  dataType : "json",
		  url:baselocation+"/friend/addFriend",
		  data : {
		   "friend.cusFriendId" : cusId
		  },
		  cache : true,
		  async : false,
		  success : function(result) {
		   if (result.message == "success") {
		    dialog_sns("关注成功", 6);
		   }
		   if (result.message == "friend") {
		    dialog_sns("您已关注过该用户", 5);
		   }
		   if(result.message=="blackList"){ 
				dialog_sns("操作失败，对方把你加入黑名单中", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
		   if (result.message == "attentiononeSelf") {
		    dialog_sns("请勿关注自己", 5);
		   }
		  }
		 });
}
function addFriend(cusFriendId,id,obj){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/friend/addFriend",
		data:{"friend.cusFriendId":cusFriendId,
			"msgReceive.id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$(obj).parent().html("<span class='fsize14'>接受加为好友</span>");
				dialog_sns("接受", 6);
			}
			if(result.message=="blackList"){
				$(obj).parent().html("<u class='fsize14'>加入黑名单</u>");
				dialog_sns("操作失败，对方把你加入黑名单中", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
			if(result.message=="friend"){
				$(obj).parent().html("<u class='fsize14'>接受加为好友</u>");
				dialog_sns("你们已经是好友了", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
		}
	});
}
function delLettersys(id){
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:delLettersysConfirm("+id+")");//删除回复的方法
}
function delLettersysConfirm(id){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delLetter",
		data:{"id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+id).remove();//
				dialog_sns("删除成功", 6);
				var pageCurrentPage=$("#pageCurrentPage").val();
				if(pageCurrentPage!=null){
					goPage(pageCurrentPage);
				}
			}
		}
	});
}
function delLetterOutbox(id){
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:delLetterOutboxconfirm("+id+")");//删除回复的方法
}
function delLetterOutboxconfirm(id){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delLetterOutbox",
		data:{"id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+id).remove();//
				dialog_sns("删除成功", 6);
				var pageCurrentPage=$("#pageCurrentPage").val();
				if(pageCurrentPage!=null){
					goPage(pageCurrentPage);
				}
			}
		}
	});
}
function delLetter(id){//删除站内信
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:delLetterconfirm("+id+")");//删除回复的方法
}
function delLetterconfirm(id){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delLetterInbox",
		data:{"msgReceive.id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+id).remove();//
				dialog_sns("删除成功", 6);
				var pageCurrentPage=$("#pageCurrentPage").val();
				if(pageCurrentPage!=null){
					goPage(pageCurrentPage);
				}
			}
		}
	});
}
var confirmobj;
function addblack(cusId,id,obj){//删除站内信
	dialog_sns("确定加入黑名单？",2);
	confirmobj = obj;
	$(".queding2").attr("href","javascript:addblackconfirm("+cusId+","+id+")");//删除回复的方法
}
function addblackconfirm(cusId,id){//加入黑名单
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/black/addBlack",
		data:{"blackList.cusBlackListId":cusId,
			"msgReceive.id":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$(confirmobj).parent().html("<u class='fsize14'>加入黑名单</u>");
				dialog_sns("加入黑名单成功", 6);
			}
			if(result.message=="blackList"){ 
				$(confirmobj).parent().html("<u class='fsize14'>加入黑名单</u>");
				dialog_sns("已存在黑名单中", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
		}
	});
}
//清空收件箱
function delAllOutbox(){
$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delAllOutbox",
		data:{},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				dialog_sns("成功", 6);
			}
			if(result.message=="empty"){ 
				dialog_sns("收件箱已清空", 6);
			}
			$(".empty").html("");
		}
	});
}
//清空收件箱
function delAllOutboxconfir(){
	dialog_sns("确定清空收件箱？",2);
	$(".queding2").attr("href","javascript:delAllOutbox()");
}
//清空收件箱
function delAllInbox(){
$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delAllInbox",
		data:{},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				dialog_sns("成功", 6);
			}
			if(result.message=="empty"){ 
				dialog_sns("收件箱已清空", 6);
			}
			$(".empty").html("");
		}
	});
}
//清空收件箱
function delAllInboxconfir(){
	dialog_sns("确定清空收件箱？",2);
	$(".queding2").attr("href","javascript:delAllInbox()");
}
//清空系统消息
function delAllMsgSys(){
$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delallmsgsys",
		data:{},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				dialog_sns("成功", 6);
			}
			if(result.message=="empty"){ 
				dialog_sns("收件箱已清空", 6);
			}
			$(".empty").html("");
		}
	});
}
//清空系统消息
function delAllMsgSysconfir(){
	dialog_sns("确定清空系统消息？",2);
	$(".queding2").attr("href","javascript:delAllMsgSys()");
}
//反选
function noselectAll(obj){
	$("input[name='ids']").removeAttr("checked");
	$(obj).html("<span>全选</span>");
	$(obj).attr("onclick","selectAll(this)");
}
//全选
function selectAll(obj){
	$("input[name='ids']").attr("checked",'true');
	$(obj).html("<span>取消</span>");
	$(obj).attr("onclick","noselectAll(this)");
}
//删除选中信息
function delselectByid(type){
	 var str="";
    $("input[name='ids']").each(function(){
    	if($(this).prop("checked")){
    		str+=this.value+",";
    	}
    });
    if(str==""){
    	dialog_sns("请选择之后再删除", 6);
    	return;
    }
    str = str.substr(0,str.length-1);
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/delselectbyids",
		data:{"ids":str,
				"type":type},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				dialog_sns("成功", 6);
				 $("input[name='ids']").each(function(){
			    	if($(this).prop("checked")){
			    		var id = this.value;
			    		$("#del"+id).remove();
			    	}
			    });
			}
			if(result.message=="empty"){ 
				dialog_sns("已删除", 6);
			}
			if(result.message=="strempty"){ 
				dialog_sns("请选择之后再删除", 6);
			}
			
		}
	});
}
//删除选中信息
function delselectByidconfir(type){
	dialog_sns("确定要删除选中的吗？",2);
	$(".queding2").attr("href","javascript:delselectByid('"+type+"')");
}
//搜索用户站内信
function letterSearch(){
	var username = $("#letterusername").val();
	if(username.trim()==""){
		dialog_sns("请输入用户名", 6);
		return;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/queryUserByName",
		data:{"username":username},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				var cusId = result.entity;
				window.location.href =baselocation+"/letter/history/"+cusId;
			}
			if(result.message=="empty"){ 
				dialog_sns("请输入用户名", 6);
			}
			if(result.message=="null"){ 
				dialog_sns("没有该用户", 6);
			}
			
		}
	});
}