$(function() {//设置当请求为什么时 放入详情的字
	var pageUrl = window.location;
	pageUrl = pageUrl + '';
	$("#searchForm").attr('action', pageUrl);

	if (pageUrl.indexOf('/friend/frircd') != -1) {//务实推荐列表
		$("#shoudao").after('<div class="ct-tabarrow-bg">&nbsp;</div>');
		$("#shoudao").parent().attr("class", "current");

	}
	if (pageUrl.indexOf('/friend/sdfrircd') != -1) {//意境推荐列表
		$("#faqi").after('<div class="ct-tabarrow-bg">&nbsp;</div>');
		$("#faqi").parent().attr("class", "current");
	}
	var falg = getParameter("falg");
	if(falg=='myfans'){
		myfans();
	}else if(falg=='myblack'){
		myblack();
	}else if(falg=='visitor'){
		visitor();
	}else {
		myattention();
	}
	
});
//我关注的
function myattention(){
	ajaxPage("/fri/ajax/myattention","&falg=myattention",1,callBack);
		clearCurrent();
		$(".myattention").addClass("current");
		$(".myattention").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
}
//我的粉丝
function myfans(){
	ajaxPage("/fri/ajax/myattention","&falg=myfans",1,callBack);
		clearCurrent();
		$(".myfans").addClass("current");
		$(".myfans").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
}
//我的黑名单
function myblack(){
	ajaxPage("/fri/ajax/myattention","&falg=myblack",1,callBack);
		clearCurrent();
		$(".myblack").addClass("current");
		$(".myblack").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
}
//最近访客
function visitor(){
	ajaxPage("/fri/ajax/myattention","&falg=visitor",1,callBack);
		clearCurrent();
		$(".visitor").addClass("current");
		$(".visitor").append('<div class="ct-tabarrow-bg">&nbsp;</div>');
}
//ajax回调
	function callBack(result){
		$(".friHtml").html(result);
		
	}
function clearCurrent(){
	$(".myattention").removeClass("current");
	$(".myfans").removeClass("current");
	$(".myblack").removeClass("current");
	$(".visitor").removeClass("current");
	$(".ct-tabarrow-bg").remove();
}
function initKindEditor(id,width,height){//加载编辑器
	window.EditorObject = KindEditor.create('textarea[id="'+id+'"]', {
		resizeType : 1,
		filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
		pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
		allowPreviewEmoticons : false,
		allowUpload : true,// 允许上传
		syncType : 'auto',
		width : width,
		minWidth : '10px',
		minHeight : '10px',
		height : height,
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		allowFileManager : false,
		/* afterFocus:function(){editor.sync();}, */
		afterBlur : function() {
			EditorObject.sync();
		},
		items : [ 'emoticons' ],
		afterChange : function() {
			$('#num'+id).html(140-this.count('text'));
			var num = 140 - this.count('text');
			if (num >= 0) {
				$('#wenzistr').html("你还可以输入");
			} else {
				$('#wenzistr').html("你已经超过");
				num = -num;
			}
			$('#wenzinum').html(num);
		}
	});
}


var letterCusId ;
function addLetterInput(cusName,cusId,obj){//添加发送站内信的发送框
	dialog_sns("发消息", 4);
	dragFun();// 弹出框
	$(".dContent .dClose,.dCancel").unbind();
	$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove("#letterTextarea");closeFun();});
	$(".cusName").html(cusName);
	letterCusId = cusId;
	initKindEditor('letterTextarea','546px','80px');
	
	/*$(".queding1").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
*/	
	/*$("#del"+cusId).append('<span id="delmessage'+cusId+'"><br/><input type="text" id="message'+cusId+'"/><input type="button" class="reply-sub ml10" value="发送" onclick="sendLetter('+cusId+')"/></span>');
	$(obj).attr("onclick","delLetterInput("+cusId+",this)");*/
}
function delLetterInput(cusId,obj){//移除发送站内信的发送框
	$("#delmessage"+cusId).remove();
	$(obj).attr("onclick","addLetterInput("+cusId+",this)");//设置发消息按钮为移除站内信方法
}

function sendLetter(){//发送站内信
	var content = $("#letterTextarea").val();
	var conten=content.replaceAll("&nbsp;","").replaceAll("<br />","");
	if(conten.trim()==""){
		deldialog_sns();
		dialog_sns("发送内容不能为空", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return ;
	}
	var contentnum = KindEditor.count("#letterTextarea","text");// 要回复的内容
	if(contentnum>140){
		deldialog_sns();
		dialog_sns("你已经超过140个字", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return ;
	}
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/letter/addLetter",
		data:{"msgReceive.receivingCusId":letterCusId,
			"msgReceive.content":content},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				KindEditor.remove("#letterTextarea");
				deldialog_sns();
				dialog_sns("发送成功", 6);
			}
			if(result.message=="false"){ 
				KindEditor.remove("#letterTextarea");
				deldialog_sns();
				dialog_sns("发送失败请重试", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
			if(result.message=="blackList"){ //对方把你加入黑名单不能发送消息
				KindEditor.remove("#letterTextarea");
				deldialog_sns();
				dialog_sns("操作失败，对方把你加入黑名单中", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
			if(result.message=="oneSelfLetter"){ //自己不能给自己发消息
				KindEditor.remove("#letterTextarea");
				deldialog_sns();
				dialog_sns("不能给自己发消息", 6);
			}
		}
	});
}

function hideaddremarks(cusId,obj){//隐藏备注添加框
	$("#remarks"+cusId).hide();//隐藏修改备注的框
	$(obj).attr("onclick","toaddremarks("+cusId+",this)");
}

function toaddremarks(cusId,obj){//修改备注
	dialog_sns("请输入15个字以内",7);
	dragFun();// 弹出框
	$("#queding7").val("");//清空修改备注的框
	$(".queding7").attr("href","javascript:addremarks("+cusId+")");
}
function addremarks(cusId){//修改备注
	var remarksContent = $("#queding7").val();//获得修改备注的内容
	if(remarksContent.length>10){//验证备注内容不能大于10
		
		dialog_sns("请输入10个字以内", 0);
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
				$(".dContent .dClose,.dCancel").click(function() {closeFun();});
				dialog_sns("备注成功", 6);
			}
		}
	});
}

function delFriend(cusId){//删除好友
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:delFriendconfirm("+cusId+")");//删除回复的方法
}
function delFriendconfirm(cusId){
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
function addFriend(cusId){//添加好友
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
		   if (result.message == "blackList") {
		    dialog_sns("对方把你加入黑名单中", 5);
		   }
		   if (result.message == "attentiononeSelf") {
		    dialog_sns("请勿关注自己", 5);
		   }
		  }
		 });
}
function delblackList(id){//移出黑名单	
	dialog_sns("是否移出黑名单？",2);
	$(".queding2").attr("href","javascript:delblackListconfirm("+id+")");//删除回复的方法
}
function delblackListconfirm(id){//移出黑名单	
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/black/delBlackList",
		data:{"blackList.cusBlackListId":id},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				$("#del"+id).remove();//移出黑名单					
				dialog_sns("移出成功", 6);
			}
		}
	});
}
function addGroup(groupId,letterId,cusId){
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/dis/addMember.do",
		data:{"groupId":groupId,"letterId":letterId,"cusId":cusId},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				dialog_sns("申请加小组成功", 6);
			}
			if(result.message=="exist"){ 
				dialog_sns("已加入该小组", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
		}
	});
}
function refuse(id){//拒绝
	dialog_sns("是否拒绝？",2);
	$(".queding2").attr("href","javascript:refuseconfirm("+id+")");//删除回复的方法
}
function refuseconfirm(id){//拒绝
	$.ajax({
		type:"POST",
		dataType:"json",
		url:baselocation+"/friend/refuseLetter",
		data:{"msgReceive.id":id
			},
		cache:true,
		async:false,
		success:function(result){
			if(result.message=="success"){ 
				dialog_sns("拒绝成功", 1);
				dragFun();// 弹出框
				$(".queding1").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
		}
	});
}
function attention(id, obj) {
	$.ajax({
		type : "POST",
		dataType : "json",
		url:baselocation+"/friend/addFriend",
		data : {
			 "friend.cusFriendId" : id
		},
		cache : true,
		async : false,
		success : function(result) {
			if (result.message == "success") {
				dialog_sns("关注成功", 6);
				$(obj).remove();
				location.reload();
			}
			if (result.message == "friend") {
				dialog_sns("您已关注过该用户", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
				$(obj).remove();
			}
			if (result.message == "blackList") {
				dialog_sns("对方把你加入黑名单中", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
			if (result.message == "attentiononeSelf") {
				dialog_sns("请勿关注自己", 0);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
			}
		}
	});
}
function deldialog_sns(){//弹出框关闭方法
	$(".dContent .dClose,.dCancel").click();
}