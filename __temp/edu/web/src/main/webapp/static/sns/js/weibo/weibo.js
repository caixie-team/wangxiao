
	//ajax回调方法
	function callBack(result){
		$(".ajaxHtml").html(result);
		$("html,body").animate({"scrollTop" : 0}, 100);
		//评论js
		initpinglun();
		initforward();
		//编辑器方法
		initKindEditorWeiBoContent();
		clearCurrent();
		var type = $(".ajaxHtml").attr("type");
		//全部微博
		if(type==1){
			$(".all").addClass("current");
		}
		//热门微博
		if(type==2){
			$(".hot").addClass("current");
		}
		//我的微博
		if(type==3){
			$(".my").addClass("current");
		}
		//好友微博
		if(type==4){
			$(".attention").addClass("current");
		}
		//评论最多
		if(type==5){
			$(".most").addClass("current");
		}
		
	}
	//全部微博
	function all(){
		ajaxPage("/weibo/ajax/queryWeiBoPageajax","&type=1",1,callBack);
		$(".ajaxHtml").attr("type","1");
		clearCurrent();
		$(".all").addClass("current");
	}
	//热门微博
	function hot(){
		ajaxPage("/weibo/ajax/queryWeiBoPageajax","&type=2",1,callBack);
		$(".ajaxHtml").attr("type","2");
		clearCurrent();
		$(".hot").addClass("current");
	}
	//我的微博
	function my(){
		//未登录不可操作
		if(checkLogin()=='unLogin'){
			return ;
		}
		ajaxPage("/weibo/ajax/queryWeiBoPageajax","&type=3",1,callBack);
		$(".ajaxHtml").attr("type","3");
		clearCurrent();
		$(".my").addClass("current");
	}
	//好友微博
	function attentionajax(){
		//未登录不可操作
		if(checkLogin()=='unLogin'){
			return ;
		}
		ajaxPage("/weibo/ajax/queryWeiBoPageajax","&type=4",1,callBack);
		$(".ajaxHtml").attr("type","4");
		clearCurrent();
		$(".attention").addClass("current");
	}
	//评论最多
	function most(){
		ajaxPage("/weibo/ajax/queryWeiBoPageajax","&type=5",1,callBack);
		$(".ajaxHtml").attr("type","5");
		clearCurrent();
		$(".most").addClass("current");
	}
	//清除选中样式
	function clearCurrent(){
		$(".hot,.my,.attention,.most,.all").removeClass("current");
	}
var weibopath = "weibo";

function initKindEditorWeiBoContent(){//编辑器初始化
	KindEditor.create('textarea[id="weiBoContent"]', {
		resizeType : 1,
		themeType : 'weibo',
		filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
		pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
		allowPreviewEmoticons : false,
		syncType : 'auto',
		width : '607px',
		minWidth : '10px',
		minHeight : '10px',
		height : '85px',
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		readonlyMode : true,
		allowFileManager : false,
		afterBlur : function() {
			this.sync();
			this.readonly();
		},
	    afterFocus: function() {
    	   this.readonly(false);
		}, 
		items : [ 'emoticons' ],
		afterChange : function() {
			$('#numweiBoContent').html(140 - this.count('text'));
			var num = 140 - this.count('text');
			
			if (num >= 0) {
				$(".error").html("您还可以输入"+num+"字");
			} else {
				$(".error").html("您输入的文字超过140字！");
				$('#wenzistrweiBoContent').html("你已经超过");
				num = -num;
			}
			$('#numweiBoContent').html(num);
		}
	});
};

//加载编辑器
	function initEditor(id,width,height){//编辑器初始化方法
			var editor = KindEditor.create('textarea[id="'+id+'"]', {
				resizeType:1,
				themeType : 'weibo',
			       filterMode : true,//true时过滤HTML代码，false时允许输入任何代码。
			       pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
			       allowPreviewEmoticons : false,
			       allowUpload : true,//允许上传
			       syncType : 'auto',
			       width:width,
			       minWidth:'10px',
			      minHeight:'10px',
				   height:height,
			       urlType : 'domain',//absolute
			       newlineTag :'br',//回车换行br|p
			       uploadJson : keImageUploadUrl+'&param='+weibopath,//图片上传路径
			       allowFileManager : false,
			       /* afterFocus:function(){editor.sync();}, */
			       afterBlur:function(){this.sync();}, 
			       items : ['emoticons'],
			       afterChange : function() {
			    	   $('#num'+id).html(140-this.count('text'));
						var num = 140 - this.count('text');
						if (num >= 0) {
							$('#wenzistr'+id).html("你还可以输入");
						} else {
							$('#wenzistr'+id).html("你已经超过");
							num = -num;
						}
						$('#num'+id).html(num);
					}
			});
	}
	//微博页面左侧下面发表微博显示微博发表框
	function showaddWeibo(){
		$(".addweibo").slideDown(400);
	}
	function addWeiBo() {
		//未登录不可操作
		if(checkLogin()=='unLogin'){
			return ;
		}
		var weiBoContent = $("#weiBoContent").val();
		var replaceAllweiBoContent = weiBoContent.replace(/\ +/g,"").replace(/[ ]/g,"").replaceAll("<br/>","").replaceAll("&nbsp;","");
		
		if (replaceAllweiBoContent.trim() == "") {
			$(".error").html("您还没输入文字！");
			return false;
		}
		
		if (KindEditor.count("#weiBoContent", "text") > 140) {
			$(".error").html("您输入的文字超过140字！");
			return;
		}
		
		var gongkai = 0;
		if(!$("#public").attr("checked")){
			gongkai=1;
		}
		
		$.ajax({
			type : "POST",
			dataType : "json",
			url:baselocation+"/weibo/addWeiBo",
			data : {
				"weiBo.content" : weiBoContent,
				"weiBo.status": gongkai
			},
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					KindEditor.html('#weiBoContent', '');
					//到我的微博页面
					dialog_sns("发布成功", 6);
					my();
					//$(".addweibo").slideUp(400);
					
				}
				if (result.message == "addMost") {
					dialog_sns("请不要频繁操作，请稍后再试。", 6);
				};
			}
		});
	}


function pinglun(weiboId, obj, pageNum) {// 通过微博id 往后台发送请求获得回复的list然后拼成回复的html字符串
	
	$
			.ajax({
				type : "POST",
				dataType : "json",
				url:baselocation+"/weibo/queryWeiBoComment",
				data : {
					"comment.weiboId" : weiboId,
					"page.currentPage" : pageNum
				},// 微博id 和分页页数
				cache : true,
				async : false,
				success : function(result) {
					var page = result.page;// 返回回来的分页参数
					var cusId = result.cusId;// 当前用户的id
					var currentPage = page.currentPage;// 当前页数
					var queryCommentList = result.queryCommentList;// 回复数据
					if (queryCommentList != null && queryCommentList.length > 0) { // 当返回的分页数据大于0
																					// 才执行
						if (queryCommentList != null
								&& queryCommentList.length != 0) { // 当返回的分页数据不等于0
																	// 才执行
							ocElem = "";
							for ( var i = 0; i < queryCommentList.length; i++) {// 循环回复的list
								var comment = queryCommentList[i];// 单个回复的实体
								var avatar = comment.avatar;
								
								ocElem += '<li id="delcomment'
										+ comment.id
										+ '"><aside class="reply-face"><a href="/p/'+comment.cusId+'/home">';
										if(avatar!=null&&avatar!=""){
											ocElem += '<img src="'+staticImageServer+''+avatar+'" width="30" height="30" />';
										}else{
											ocElem += '<img src="'+imagesPath+'/static/common/images/user_default.jpg" width="30" height="30" />';
										}
								ocElem +='</a></aside>';
								ocElem += '<div class="reply-text"><a href="/p/'+comment.cusId+'/home" class="c-blue">'
										+ comment.showname
										+ '</a><span class="c-888"></span><span class="c-555">：</span>';
								ocElem += '<span class="c-333">'
										+ comment.content + '</span>';
								ocElem += '<div class="mt10 of"><span class="c-888 fl">'
										+ comment.modelStr
										+ '</span>';
								if (cusId == comment.cusId) {// 如果这条回复的人是当前登陆的人可以删除该回复
									ocElem += '<a href="javascript:void(0)" onclick="delpinglunConfirm('
											+ comment.id
											+ ','
											+ weiboId
											+ ')" title="删除" class="c-blue fr ml10">删除</a>';
								}
								ocElem += '<a href="###" onclick="huifu(\''
										+ comment.showname
										+ '\','
										+ weiboId
										+ ',this,'
										+ comment.id
										+ ','+comment.cusId+')" title="回复" class="c-blue fr">回复</a></div>';
								ocElem += '</div><div class="clear"></div></li>';
							}
							var currentPageNum = parseInt(currentPage);// 转换当前页为int加一页
							if (currentPage == page.totalPageSize) {// 如果当前页和分页的最大页数相等，把点击查看更多评论隐藏
								$("#lookmost" + weiboId).hide();// 隐藏点击查看更多
							}else{
								var page = currentPageNum+1;
								$("#lookmost" + weiboId).attr(
										"href",
										"javascript:pinglun(" + weiboId + ",this,"
												+ page + ")");// 更改点击查看更多的页数
								$("#lookmost" + weiboId).show();// 展示点击查看更多
							}
							if (currentPage == 1) {// 如果等于第一页是 用html放入字符串
								$("#comment" + weiboId).html(ocElem);
							} else {
								$("#comment" + weiboId).append(ocElem);// 如果大于第一页则append回复的数据
							}
							
						}
					} else {
						$("#lookmost" + weiboId).hide();// 如果返回的评论没有数据则隐藏点击查看更多
					}
				}
			});
}
function timeStamp2String(time) {// 时间格式化js方法
	var datetime = new Date();
	datetime.setTime(time);
	var year = datetime.getFullYear();
	var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1)
			: datetime.getMonth() + 1;
	var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime
			.getDate();
	var hour = datetime.getHours() < 10 ? "0" + datetime.getHours() : datetime
			.getHours();
	var minute = datetime.getMinutes() < 10 ? "0" + datetime.getMinutes()
			: datetime.getMinutes();
	var second = datetime.getSeconds() < 10 ? "0" + datetime.getSeconds()
			: datetime.getSeconds();
	return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":"
			+ second;
}
function delpinglunConfirm(commentId, weiboId){
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:delpinglun("+commentId+","+weiboId+")");
}
function delpinglun(commentId, weiboId) {// 删除回复
		$.ajax({
			type : "POST",
			dataType : "json",
			url:baselocation+"/weibo/delCommentById",
			data : {
				"comment.id" : commentId,
				"comment.weiboId" : weiboId
			},// 传入 微博id和回复id
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") { // 成功
					deldialog_sns();//删除回复成功则移除弹出的确定删除的框
					$("#delcomment" + commentId).remove();// 页面移除回复
					var commentNum = $("#commentNum" + weiboId).html();// 获得回复数
					commentNum = parseInt(commentNum)- 1;
					if(commentNum<0){//验证不能减成负数
						commentNum = 0;
					}
					pinglun(weiboId,weiboId,1);// 微博回复加载
					$("#commentNum" + weiboId).html(commentNum);// 回复数减一
					dialog_sns("删除成功", 6);
				} else {
					dialog_sns("删除失败，请重试", 0);
					dragFun();// 弹出框
					$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
				}
			}
		});
}
function deldialog_sns(){//弹出框关闭方法
	$(".dContent .dClose,.dCancel").click();
}
function showPinglun(weiboId, obj) {
	$("#pinglun" + weiboId).hide();
	$(obj).attr("onclick", "pinglun(" + weiboId + ",this,1)");
}
function addComment(weiboId) {// 添加回复
	//未登录不可操作
		if(checkLogin()=='unLogin'){
			return ;
		}
	if (KindEditor.count("#content" + weiboId,"text") >140) {// 验证字数限制
		dialog_sns("请输入140个字以内", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return;
	}
	
	var content = $("#content" + weiboId).val();
	var conten=content.replaceAll("&nbsp;","").replaceAll("<br />","");
	if (conten.trim() == "") {// 保存的内容不能为空
		dialog_sns("输入内容不能为空", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return;
	}
	$.ajax({
				type : "POST",
				dataType : "json",
				url:baselocation+"/weibo/addWeiBoComment",
				data : {
					"comment.weiboId" : weiboId,
					"comment.content" : content
				},// 发送微博id和回复内容
				cache : true,
				async : false,
				success : function(result) {
					if (result.message == "success") { // 返回成功
						dialog_sns("回复成功", 6);
						var str = "";
						var comment = result.comment;// 返回添加的回复实体
						var customer = result.customer;// 返回当前登陆的用户实体
						var geniusUser = result.geniusUser;
						str += '<li id="delcomment'+comment.id+'"><aside class="reply-face"><a href="/p/'+comment.cusId+'/home">';
						if(geniusUser!=null&&geniusUser.avatar!=null){
							str +='<img src="'+staticImageServer+''+geniusUser.avatar+'" width="30" height="30" />';
						}else{
							str +='<img src="'+imagesPath+'/static/common/images/user_default.jpg" width="30" height="30" />';
						}
						str += '</a></aside>';
						str += '<div class="reply-text"><a href="/p/'+comment.cusId+'/home" class="c-blue">'
								+ comment.showname
								+ '</a><span class="c-888"></span><span class="c-555">&nbsp;：</span>';
						str += '<span class="c-333">' + comment.content
								+ '</span>';
						str += '<div class="mt10 of"><span class="c-888 fl">'
								+ timeStamp2String(comment.addTime)
								+ '</span>';
								if (customer.cusId == comment.cusId) {
									str += '<a href="javascript:void(0)" onclick="delpinglunConfirm('
											+ comment.id
											+ ','
											+ weiboId
											+ ')" title="删除" class="c-blue fr ml10">删除</a>';
								}
								
							str +='<a href="javascript:void(0)" onclick="huifu(\''
								+ customer.showname
								+ '\','
								+ weiboId
								+ ',this,'
								+ comment.id
								+ ','+comment.cusId+')" title="回复" class="c-blue fr">回复</a></div>';
						str += '</div><div class="clear"></div></li>';
						pinglun(weiboId,weiboId,1);// 微博回复加载
						KindEditor.html('#content' + weiboId, "");// 编辑器清空
						KindEditor.sync('#content' + weiboId);// 编辑器同步内容
						var commentNum = $("#commentNum" + weiboId).html();// 获得回复数
						$("#commentNum" + weiboId).html(parseInt(commentNum) + 1);// 回复数加一
						
						
					}
				}
			});
}
function attention(id, obj) {//关注用户
	//未登录不可操作
	if(checkLogin()=='unLogin'){
		return ;
	}
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
				//location.reload();
			}
			if (result.message == "friend") {
				dialog_sns("您已关注过该用户", 6);
				dragFun();// 弹出框
				$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
				$(obj).remove();
			}
			if (result.message == "blackList") {
				dialog_sns("对方已把你加入黑名单中", 0);
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
/**
 * 
 * @param str 页面的用户名
 * @param weiboId 回复的微博id
 * @param obj 当前元素
 * @param commentId  当前的回复id,在后面加载编辑器。
 */
function huifu(showname, weiboId, obj, commentId,cusId) {// 回复一个人的回复
	var removeHuifustr = $("#removeHuifu"+commentId).html();
	if(removeHuifustr!=null&&removeHuifustr!=""){
		KindEditor.remove('#contenthuifu' + commentId);
		$("#removeHuifu"+commentId).remove();
	}else{
		var str2 = '<div id="removeHuifu'
			+ commentId
			+ '"></br><textarea  id="contenthuifu'+ commentId
			+ '">回复<a target="_blank" class="c-blue" href="'+baselocation+'/p/'+cusId+'/home/">@'+ showname+'</a>'
			/*+ '">回复@'+ showname*/
			+ '：</textarea> <a class="reply-sub mr10 fr" href="###" onclick="addCommenthuifu('
			+ weiboId + ',' + commentId + ',this,'+cusId+')"><span>回复</span></a><span style="display:none" id="numcontenthuifu'+commentId+'"></span></div>';
	$(obj).after(str2);
	
	initEditor('contenthuifu' + commentId, 435, 60);// 加载编辑器
	}
	
	
}
function hidehuifu(str, weiboId, obj, commentId,cusId) {// 点击回复隐藏
	$("#removeHuifu" + commentId).remove();// 移除回复的编辑器
	$(obj).attr("onclick",
			"huifu('" + str + "'," + weiboId + ",this," + commentId + ","+cusId+")");// 修改回复的方法
}
function addCommenthuifu(weiboId, commentId,obj) {// 添加回复
		//未登录不可操作
	  if(checkLogin()=='unLogin'){
	   return ;
	  }
	var content = $("#contenthuifu" + commentId).val();// 要回复的内容
	var contentnum = KindEditor.count("#contenthuifu" + commentId,"text");// 要回复的内容
	if (contentnum >140) {// 验证字数限制
		dialog_sns("请输入140个字以内", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return;
	}
	var conten=content.replaceAll("&nbsp;","").replaceAll("<br />","");
	if (conten.trim() == "") {// 保存的内容不能为空
		dialog_sns("输入内容不能为空", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return;
	}
	
	$.ajax({// 添加的回复ajax
				type : "POST",
				dataType : "json",
				url:baselocation+"/weibo/addWeiBoComment",
				data : {
					"comment.weiboId" : weiboId,
					"comment.content" : content
				},// 传入微博id和添加的回复内容
				cache : true,
				async : false,
				success : function(result) {
					if (result.message == "success") { // 如果返回成功则执行下面
						dialog_sns("添加成功", 6);
						
						var ocElem = "";// 要拼的字符串
						var comment = result.comment;// 返回的添加的回复id
						var customer = result.customer;// 返回当前登陆的用id
						var geniusUser = result.geniusUser;//用户信息
						ocElem += '<li id="delcomment'+comment.id+'"><aside class="reply-face"><a href="/p/'+comment.cusId+'/home">';
						if(geniusUser!=null&&geniusUser.avatar!=null){
							ocElem +='<img src="'+staticImageServer+''+geniusUser.avatar+'" width="30" height="30" />';
						}else{
							ocElem +='<img src="'+imagesPath+'/static/common/images/user_default.jpg" width="30" height="30" />';
						}
						ocElem +='</a></aside>';
						ocElem += '<div class="reply-text"><a href="/p/'+comment.cusId+'/home" class="c-blue">'
								+ comment.showname
								+ '</a><span class="c-888"></span><span class="c-555">：</span>';
						ocElem += '<span class="c-333">' + comment.content
								+ '</span>';
						ocElem += '<div class="mt10 of"><span class="c-888 fl">'
								+ timeStamp2String(comment.addTime) + '</span>';
						if (customer.cusId == comment.cusId) {
							ocElem += '<a href="javascript:void(0)" onclick="delpinglunConfirm('
									+ comment.id
									+ ','
									+ weiboId
									+ ')" title="删除" class="c-blue fr">删除</a>';
						}
						ocElem += '<a href="###" onclick="huifu(\''
								+ comment.showname
								+ '\','
								+ weiboId
								+ ',this,'
								+ comment.id
								+ ','+comment.cusId+')" title="回复" class="c-blue fr mr10">回复</a></div>';
						ocElem += '</div><div class="clear"></div></li>';
						//$("#comment" + weiboId).prepend(ocElem);// 在回复的最前面添加最新的回复
						pinglun(weiboId,weiboId,1);// 微博回复加载
						$(obj).parent().prev().click();
						//$("#removeHuifu" + commentId).remove();// 移除回复的编辑器
						
						var commentNum = $("#commentNum" + weiboId).html();// 获得当前回复数
						$("#commentNum" + weiboId).html(parseInt(commentNum) + 1);// 回复数加一
					}
				}
			});
}

function delWeiBoConfirm(id){
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:delWeiBo("+id+")");
}
function delWeiBo(id) {// 删除微博
		//未登录不可操作
		  if(checkLogin()=='unLogin'){
		   return ;
		  }
		$.ajax({
			type : "POST",
			dataType : "json",
			url:baselocation+"/weibo/delWeiBo",
			data : {
				"weiBo.id" : id
			},
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					deldialog_sns();
					$("#del" + id).remove();
					dialog_sns("删除成功", 6);
				}
				if (result.message == "falg") {
					deldialog_sns();
					dialog_sns("删除失败请重试", 0);
					dragFun();// 弹出框
					$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
				}
			}
		});
}

var ocElem = '';
function initpinglun(){
	if (navigator.userAgent.indexOf("MSIE 6.0")<0&&!$.support.style ) {
		$(".DT-wrap .DT-detail-wrap").each(function() {
			var cBtn = $(this).find(".DT-comment-btn"),
				cBox = $(this).find(".DT-comment-wrap.pingl");
			cBtn.unbind();
			cBtn.click(function() {
				$(".DT-comment-wrap.DT-comment-forward").empty().hide();
				if (cBox.is(":hidden")) {
					cBox.show(function() {
						var weiboId = cBox.attr("weiboId");//获得微博id
						ocElem = '';
						ocElem += '<div class="DT-arrow"><em>◆</em><span>◆</span></div>';
						ocElem += '<div><div>';
						ocElem += '<section class="send-txt-input reply-input"><textarea class="s-t-detail" name="" id="content'+weiboId+'"></textarea></section>';
						ocElem += '<section class="clearfix mt20"><div class="fr">';
						ocElem += '<span class="c-orange" ><span id="wenzistrcontent'+weiboId+'">已输入</span><span id="numcontent'+weiboId+'">140</span>字</span><a href="javascript:void(0)" onclick="addComment('+weiboId+')" class="reply-sub ml10"><span>回复</span></a>';
						ocElem += '</div>';
						ocElem += '';
						ocElem += '</section></div>';
						ocElem += '<div class="mt10"><ul class="DT-reply-list" id="comment'+weiboId+'">';
						ocElem += '</ul></div></div>';
						ocElem += '<div class="mt20 mb20 tac"><a href="javascript:pinglun('+weiboId+',this,2)" id="lookmost'+weiboId+'"  title="点击查看更多评论" class="c-blue">点击查看更多评论&gt;&gt;</a></div>';
						cBox.html(ocElem).show();// 加载微博回复的html
						initEditor('content'+weiboId,500,60);// 启动编辑器
						pinglun(weiboId,cBox,1);// 获得微博回复的html
					});
				} else {
					cBox.empty().slideUp(50);
				};
			});
		});
		} else{
			$(".DT-wrap .DT-detail-wrap").each(function() {
				var cBtn = $(this).find(".DT-comment-btn"),
					cBox = $(this).find(".DT-comment-wrap.pingl");
				cBtn.unbind();
				cBtn.click(function() {
					$(".DT-comment-wrap.DT-comment-forward").empty().hide();
					if (cBox.is(":hidden")) {
						cBox.slideDown(50).show(function() {
							var weiboId = cBox.attr("weiboId");//获得微博id
							ocElem = '';
							ocElem += '<div class="DT-arrow"><em>◆</em><span>◆</span></div>';
							ocElem += '<div><div>';
							ocElem += '<section class="send-txt-input reply-input"><textarea class="s-t-detail" name="" id="content'+weiboId+'"></textarea></section>';
							ocElem += '<section class="clearfix mt20"><div class="fr">';
							ocElem += '<span class="c-orange" ><span id="wenzistrcontent'+weiboId+'">已输入</span><span id="numcontent'+weiboId+'">140</span>字</span><a href="javascript:void(0)" onclick="addComment('+weiboId+')" class="reply-sub ml10"><span>回复</span></a>';
							ocElem += '</div>';
							ocElem += '';
							ocElem += '</section></div>';
							ocElem += '<div class="mt10"><ul class="DT-reply-list" id="comment'+weiboId+'">';
							ocElem += '</ul></div></div>';
							ocElem += '<div class="mt20 mb20 tac"><a href="javascript:pinglun('+weiboId+',this,2)" id="lookmost'+weiboId+'"  title="点击查看更多评论" class="c-blue">点击查看更多评论&gt;&gt;</a></div>';
							cBox.html(ocElem).show();// 加载微博回复的html
							initEditor('content'+weiboId,500,60);// 启动编辑器
							pinglun(weiboId,cBox,1);// 获得微博回复的html
						});
					} else {
						cBox.empty().slideUp(50);
					};
				});
			});
		}
}
function initforward(){
	if (navigator.userAgent.indexOf("MSIE 6.0")<0&&!$.support.style) {
		$(".DT-wrap .DT-detail-wrap").each(function() {
			var cBtn = $(this).find(".forward-btn"),
				cBox = $(this).find(".DT-comment-wrap.DT-comment-forward");
			cBtn.unbind();
			cBtn.click(function() {
				$(".DT-comment-wrap.pingl").empty().hide();
				if (cBox.is(":hidden")) {
					cBox.show(function() {
						var weiboId = cBox.attr("weiboId");//获得微博id
						ocElem = '';
						ocElem += '<div class="DT-arrow"><em>◆</em><span>◆</span></div>';
						ocElem += '<div><div>';
						ocElem += '<section class="send-txt-input reply-input"><textarea class="s-t-detail" name="" id="content'+weiboId+'"></textarea></section>';
						ocElem += '<section class="clearfix mt20"><div class="fr">';
						ocElem += '<span class="c-orange" ><span id="wenzistrcontent'+weiboId+'">已输入</span><span id="numcontent'+weiboId+'">140</span>字</span><a href="javascript:void(0)" onclick="forward('+weiboId+')" class="reply-sub ml10"><span>转发</span></a>';
						ocElem += '</div>';
						ocElem += '';
						ocElem += '</section></div>';
						ocElem += '<div class="mt10"><ul class="DT-reply-list" id="comment'+weiboId+'">';
						ocElem += '</ul></div></div>';
						cBox.html(ocElem).show();// 加载微博回复的html
						initEditor('content'+weiboId,500,60);// 启动编辑器
					});
				} else {
					cBox.empty().slideUp(50);
				};
			});
		});
		} else{
			$(".DT-wrap .DT-detail-wrap").each(function() {
				var cBtn = $(this).find(".forward-btn"),
					cBox = $(this).find(".DT-comment-wrap.DT-comment-forward");
				cBtn.unbind();
				cBtn.click(function() {
					$(".DT-comment-wrap.pingl").empty().hide();
					if (cBox.is(":hidden")) {
						cBox.slideDown(50).show(function() {
							var weiboId = cBox.attr("weiboId");//获得微博id
							ocElem = '';
							ocElem += '<div class="DT-arrow"><em>◆</em><span>◆</span></div>';
							ocElem += '<div><div>';
							ocElem += '<section class="send-txt-input reply-input"><textarea class="s-t-detail" name="" id="content'+weiboId+'"></textarea></section>';
							ocElem += '<section class="clearfix mt20"><div class="fr">';
							ocElem += '<span class="c-orange" ><span id="wenzistrcontent'+weiboId+'">已输入</span><span id="numcontent'+weiboId+'">140</span>字</span><a href="javascript:void(0)" onclick="forward('+weiboId+')" class="reply-sub ml10"><span>转发</span></a>';
							ocElem += '</div>';
							ocElem += '';
							ocElem += '</section></div>';
							ocElem += '<div class="mt10"><ul class="DT-reply-list" id="comment'+weiboId+'">';
							ocElem += '</ul></div></div>';
							cBox.html(ocElem).show();// 加载微博回复的html
							initEditor('content'+weiboId,500,60);// 启动编辑器
						});
					} else {
						cBox.empty().slideUp(50);
					};
				});
			});
		}
}
//转发
function forward(id){
	 //未登录不可操作
	if(checkLogin()=='unLogin'){
		return ;
	}
	if (KindEditor.count("#content" + id,"text") >140) {// 验证字数限制
		dialog_sns("请输入140个字以内", 0);
		dragFun();// 弹出框
		$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return;
	}
	
	var content = $("#content" + id).val();
	//var conten=content.replaceAll("&nbsp;","").replaceAll("<br />","");
	$.ajax({
				type : "POST",
				dataType : "json",
				url:baselocation+"/weibo/forward",
				data : {
					"id" : id,
					"content" : content
				},// 发送微博id和回复内容
				cache : true,
				async : false,
				success : function(result) {
					if (result.message == "success") {
						deldialog_sns();
						dialog_sns("转发成功", 6);
						var forwardnum = $(".forwardnum" + id).html();// 获得回复数
						forwardnum = parseInt(forwardnum)+1;
						$(".forwardnum" + id).html(forwardnum);// 回复数加一
						my();
					}
					if (result.message == "false") {
						deldialog_sns();
						dialog_sns("要转发的微博不存在，请重试", 6);
					}
					if (result.message == "forward") {
						deldialog_sns();
						dialog_sns("该微博已转发过", 6);
					}
					if (result.message == "delForward") {
						deldialog_sns();
						dialog_sns("转发失败，原始微博已删除", 6);
					}
					if (result.message == "forwardSelf") {
						deldialog_sns();
						dialog_sns("转发失败，转发为自己原创微博", 6);
					}
				}
			});
}

//到某页方法
function goPageAjax(pageNum){
	ajaxPage(ajaxUrl,ajaxparameters,pageNum,callBack);
}