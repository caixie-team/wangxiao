
function initblog(){
	//通过传来的类型来判断显示哪个类型
	var type = getParameter("type");
	if(type=="1"){
		all();
	}else if(type=="2"){
		hot();
	}else if(type=="3"){
		my();
	}else if(type=="4"){
		attentionajax();
	}else if(type=="5"){
		most();
	}else{
		all();
	}
}
//删除博文
function delblog(blogId){
	dialog_sns("确定删除？",2);
	$(".queding2").attr("href","javascript:del("+blogId+")");
}

//ajax回调方法
function callBack(result){
	$(".ajaxHtml").html(result);
	$("html,body").animate({"scrollTop" : 0}, 100);
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
//全部博文
function all(){
	var pageUrl = window.location;//获得当前的url
	pageUrl = pageUrl + '';
	if (pageUrl.indexOf("/blog/edit/") != -1) {
		window.location.href="/blog?type=1";
		return;
	}
	if (pageUrl.indexOf("/blog/rele") != -1||pageUrl.indexOf("/blog/art/")!=-1) {
		window.location.href="/blog?type=1";
		return;
	}
	ajaxPage("/blog/ajax/queryBlogBlogAjax","&type=1",1,callBack);
	$(".ajaxHtml").attr("type","1");
	clearCurrent();
	$(".all").addClass("current");
}
//热门博文
function hot(){
	var pageUrl = window.location;//获得当前的url
	pageUrl = pageUrl + '';
	if (pageUrl.indexOf("/blog/edit/") != -1) {
		window.location.href="/blog?type=2";
		return;
	}
	if (pageUrl.indexOf("/blog/rele") != -1||pageUrl.indexOf("/blog/art/")!=-1) {
		window.location.href="/blog?type=2";
		return;
	}
	ajaxPage("/blog/ajax/queryBlogBlogAjax","&type=2",1,callBack);
	$(".ajaxHtml").attr("type","2");
	clearCurrent();
	$(".hot").addClass("current");
}
//我的博文
function my(){
	//未登录不可操作
  if(checkLogin()=='unLogin'){
   return ;
  }
	var pageUrl = window.location;//获得当前的url
	pageUrl = pageUrl + '';
	if (pageUrl.indexOf("/blog/edit/") != -1) {
		window.location.href="/blog?type=3";
		return;
	}
	if (pageUrl.indexOf("/blog/rele") != -1||pageUrl.indexOf("/blog/art/")!=-1) {
		window.location.href="/blog?type=3";
		return;
	}
	ajaxPage("/blog/ajax/queryBlogBlogAjax","&type=3",1,callBack);
	$(".ajaxHtml").attr("type","3");
	clearCurrent();
	$(".my").addClass("current");
}
//好友博文
function attentionajax(){
	//未登录不可操作
  if(checkLogin()=='unLogin'){
   return ;
  }
	var pageUrl = window.location;//获得当前的url
	pageUrl = pageUrl + '';
	if (pageUrl.indexOf("/blog/edit/") != -1) {
		window.location.href="/blog?type=4";
		return;
	}
	if (pageUrl.indexOf("/blog/rele") != -1||pageUrl.indexOf("/blog/art/")!=-1) {
		window.location.href="/blog?type=4";
		return;
	}
	ajaxPage("/blog/ajax/queryBlogBlogAjax","&type=4",1,callBack);
	$(".ajaxHtml").attr("type","4");
	clearCurrent();
	$(".attention").addClass("current");
}
//评论博文
function most(){
	var pageUrl = window.location;//获得当前的url
	pageUrl = pageUrl + '';
	if (pageUrl.indexOf("/blog/edit/") != -1) {
		window.location.href="/blog?type=5";
		return;
	}
	if (pageUrl.indexOf("/blog/rele") != -1||pageUrl.indexOf("/blog/art/")!=-1) {
		window.location.href="/blog?type=5";
		return;
	}
	ajaxPage("/blog/ajax/queryBlogBlogAjax","&type=5",1,callBack);
	$(".ajaxHtml").attr("type","5");
	clearCurrent();
	$(".most").addClass("current");
}
//清除选中样式
function clearCurrent(){
	$(".hot,.my,.attention,.most,.all").removeClass("current");
}
var EditorObject;
var blogpath = "blog";

// 添加博文页面编辑器
function initKindEditor_addblog(id, width, height) {
	EditorObject = KindEditor.create('textarea[id=' + id + ']', {
		resizeType : 1,
		filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
		allowPreviewEmoticons : false,
		allowUpload : true,// 允许上传
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		width : width,
		height : height,
		minWidth : '10px',
		minHeight : '10px',
		uploadJson : keImageUploadUrl + '&param=' + blogpath,// 图片上传路径
		afterBlur : function() {
			this.sync();
		},
		allowFileManager : false,
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline','formatblock','lineheight', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
				'image', 'link','plainpaste' ],
		afterChange : function() {
			var num = $("#blogFont").html(this.count('text'));// 字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字
			if (num >= 100) {
				return true;
			} else {
				return false;
			}
		}
	});
}
// 编辑博文编辑器
function initKindEditor_editblog(id, width, height) {
	EditorObject = KindEditor.create('textarea[id=' + id + ']', {
		resizeType : 1,
		filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
		allowPreviewEmoticons : false,
		allowUpload : true,// 允许上传
		syncType : 'auto',
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		width : width,
		height : height,
		minWidth : '10px',
		minHeight : '10px',
		uploadJson : keImageUploadUrl + '&param=' + blogpath,// 图片上传路径
		afterBlur : function() {
			EditorObject.sync();
		},
		allowFileManager : false,
		items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline','formatblock','lineheight','removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons',
				'image', 'link' ],
		afterChange : function() {
			var num = $("#blogFont").html(this.count('text'));// 字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字
			if (num >= 100) {
				return true;
			} else {
				return false;
			}
		}
	});
}

// 博文详情
Date.prototype.format = function(format)// 格式化时间方法
{
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

// 评论博文
function blogComment(blogId, obj) {
	var limitnum = EditorObject.count('text');
	var replycontent = $("#replyContent").val();// 获得回复内容
	var conten=replycontent.replaceAll("&nbsp;","").replaceAll("<br />","");
	if (conten.trim() == "" || replycontent == null) {// 判断是否为空
		$('#wenzi').show();// show提示
		return;
	}
	if (limitnum > 5000) {
		dialog_sns("您输入的内容过长，也许可以精简一点！", 5);
		return;
	} else {
		$
				.ajax({
					url :baselocation+"/blog/addrep",// 添加回复
					type : "post",
					dataType : "json",
					data : {
						"blogReply.content" : replycontent,
						"blogReply.blogId" : blogId
					},// 传博文id、回复的内容
					cache : true,
					async : false,
					success : function(result) {
						if (result.message == "success") {
							KindEditor.remove('#replyContent');
							$(".dContent .dClose,.dCancel").click();// 关闭dialog_sns
							dialog_sns("评论成功", 6);
							queryBlogReplyList(blogId,1);
						} else if (result.message == "limitOpt") {
							KindEditor.remove('#replyContent');
							deldialog_sns();
							dialog_sns("请不要频繁操作，请稍后再试。", 5);
							return false;
						} else {
							KindEditor.remove('#replyContent');
							deldialog_sns();
							dialog_sns("失败请刷新重试", 5);
							return false;
						}

					}
				});
	}
}

function initKindEditor_comment(id) {
	EditorObject = KindEditor.create('textarea[id="' + id + '"]', {
		resizeType : 1,
		filterMode : false,// true时过滤HTML代码，false时允许输入任何代码。
		allowPreviewEmoticons : false,
		allowUpload : true,// 允许上传
		syncType : 'auto',
		urlType : 'domain',// absolute
		newlineTag : 'br',// 回车换行br|p
		minWidth : '30px',
		readonlyMode : true,
		afterBlur : function() {
			EditorObject.sync();
			EditorObject.readonly();
		},
		afterFocus: function() {
			EditorObject.readonly(false);
		},
		allowFileManager : false,
		items : [ 'emoticons' ]
	});
	EditorObject.focus();
	EditorObject.appendHtml('');//追加
}

// 评论博文
function pinglun(blogId) {
	//未登录不可操作
  if(checkLogin()=='unLogin'){
   return ;
  }
	dialog_sns('评论', 3);// 调dialog_sns
	dragFun();
	$(".dContent .dClose,.dCancel").unbind();
	$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove('#replyContent');closeFun1();closeFun();});
	$("#reply").attr("href", "javascript:blogComment(" + blogId + ",this)");
	initKindEditor_comment('replyContent');
	$("#yingyong").html(
			$(".yinyongcontent").html().replace(/<[^>].*?>/g, "").replaceAll(
					"&nbsp;", "").substr(0, 50)
					+ ".........");
}
// 回复他人的回复
function otherreply(blogId, showName, replyId) {
	//未登录不可操作
  if(checkLogin()=='unLogin'){
   return ;
  }
	dialog_sns('评论', 3);
	dragFun();
	$(".dContent .dClose,.dCancel").unbind();
	$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove('#replyContent');closeFun1();closeFun();});
	$("#reply").attr("href", "javascript:blogComment(" + blogId + ",this)");
	$("#replyContent").val("回复@" + showName + ":");
	initKindEditor_comment('replyContent');
	$("#yingyong").html(
			$(".huifucontent" + replyId).html().replace(/<[^>].*?>/g, "")
					.replaceAll("&nbsp;", "").substr(0, 50)
					+ ".....");
}
// 删除自己的回复
function rem(replyId) {
	var judge = confirm("确定删除吗？");
	if (judge == true) {
		$.ajax({
			url:baselocation+"/blog/drep",
			type : "post",
			dataType : "json",
			data : {
				"id" : replyId
			},
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					dialog_sns("删除成功", 1);
					$("#sp" + replyId).remove();
					return true;
				} else {
					dialog_sns("请刷新重试", 0);
					return false;
				}
			}
		});
	} else {
		return false;
	}
}
// 我的博文 删除
function del(id) {
	$.ajax({
		url:baselocation+"/blog/dbl",
		type : "post",
		dataType : "json",
		data : {
			"id" : id
		},
		cache : true,
		async : false,
		success : function(result) {
			if (result.message == "success") {
				deldialog_sns();
				dialog_sns("删除成功", 6);
				$("#rem" + id).remove();
				return true;
			} else if (result.message == "isEmpty") {
				return false;
			} else {
				dialog_sns("请刷新重试", 5);
				return false;
			}
		}
	});
}
// 发表博文
function limit() {
	var count = $("#text").val().length;// 限制标题字数
	if (count > 50) {
		deldialog_sns();
		dialog_sns("对不起您已输入超过50个字", 5);
		return false;
	} else {
		return true;
	}
}
// 发表博文
function tijiao() {
	var selType=$("#selType").val();
	if(selType==0){
		dialog_sns("请选择文章类型", 5);
		return false;
	}
	var count = EditorObject.count('text');// 判断内容字数
	if (count < 5) {
		dialog_sns("您输入的内容过短，最少5个字", 5);
		return false;
	}
	if (count > max_text_length) {
		dialog_sns("您输入内容超过"+max_text_length+"字,请删减！",5);
		return false;
	}if($("#blogContent").val()==null||$("#blogContent").val().trim()==''){
		dialog_sns("内容不能为空", 5);
	}
	if ($("#text").val() == null || $("#text").val().trim() == '') {
		dialog_sns("标题不能为空！", 5);
		return false;
	}
	if ($("#text").val().length > 50) {
		dialog_sns("标题不能大于50个字！", 5);
		return false;
	}
	if ($("#blogType").val() == 0) {
		dialog_sns("请选择分类！", 5);
		return false;
	} else {
		var title = $("#text").val();// 获得标题
		var Content = $("#blogContent").val();// 获得内容
		var blogType = $("#blogType").val();// 获得分类
		$.ajax({
			url:baselocation+"/blog/crt",
			data : {
				"blogBlog.title" : title,
				"blogBlog.content" : Content,
				"blogBlog.type" : blogType,
				"blogBlog.selType":selType
			},
			type : "post",
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					dialog_sns("发表成功", 1);
					$(".queding1").attr("href", "javascript:my();");
				} else if (result.message == "limitOpt") {
					dialog_sns("请不要频繁操作，请稍后再试。", 0);
					$(".queding0").attr("href", "javascript:deldialog_sns()");
				} else if (result.message == "limitfont") {
					dialog_sns("您输入得内容过长", 0);
					$(".queding0").attr("href", "javascript:deldialog_sns()");
				} else {
					dialog_sns("系统繁忙", 0);
					$(".queding0").attr("href", "javascript:deldialog_sns()");
				}
			}

		});
	}
}

// 博文搜索
function searchblog() {
	var keyword = $("#searchblog").val();
	if (keyword == null || keyword == "") {
		dialog_sns("请输入要搜索的关键字", 5);
		return;
	}
	$("#searchblogform").submit();
}
function addFriend(cusId) {// 添加好友
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
// 加关注
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
function delreply(blogId, replyId) {
	dialog_sns("确定删除？", 2);
	$(".queding2").attr("href",
			"javascript:delsure(" + blogId + "," + replyId + ")");
}
function delsure(blogId, replyId) {
	$.ajax({
		url:baselocation+"/blog/drep",
		data : {
			"blogReply.blogId" : blogId,
			"blogReply.id" : replyId
		},
		dataType : "json",
		type : "post",
		cache : true,
		async : false,
		success : function(result) {
			if (result.message == "success") {
				deldialog_sns();
				dialog_sns("删除成功", 6);
				$(".rem" + replyId).remove();
				var num = $(".replyCount").html().replace(/[(|)]/g, "");
				var sum=0;
				if(parseInt(num)==0){
					sum=0;
				}else{
					sum= parseInt(num) - 1;
				}
				$(".replyCount").html("(" + sum + ")");
			} else if (result.message == "isEmpty") {
				return false;
			} else {
				deldialog_sns();
				dialog_sns("删除失败", 5);
			}
		}
	});
}

// 编辑博文
function baocun(blogId) {
	var selType=$("#selType").val();
	var count = EditorObject.count('text');// 判断内容字数
	if (count < 5) {
		dialog_sns("您输入的内容过短，最少5个字", 5);
		return false;
	}
	if($("#blogContent").val()==null||$("#blogContent").val().trim()==''){
		dialog_sns("内容不能为空", 5);
	}
	if (count > max_text_length) {
		dialog_sns("您输入内容超过"+max_text_length+"字,请删减！",5);
		return false;
	}
	if ($("#text").val() == null || $("#text").val().trim() == '') {
		dialog_sns("标题不能为空！", 5);
		return false;
	}
	if ($("#text").val().length > 50) {
		dialog_sns("标题不能大于50个字！", 5);
		return false;
	}
	if ($("#blogType").val() == 0) {
		dialog_sns("请选择分类！", 5);
		return false;
	} else {
		var title = $("#text").val();// 获得标题
		var Content = $("#blogContent").val();// 获得内容
		var blogType = $("#blogType").val();// 获得分类
		$.ajax({
			url:baselocation+"/blog/update",
			data : {
				"blogBlog.title" : title,
				"blogBlog.content" : Content,
				"blogBlog.type" : blogType,
				"blogBlog.id" : blogId,
				"blogBlog.selType":selType
			},
			type : "post",
			cache : true,
			async : false,
			success : function(result) {
				if (result.message == "success") {
					dialog_sns("保存成功", 1);
					$(".queding1").attr("href", "javascript:my()");
				} else if (result.message == "isEmpty") {
					return false;
				} else {
					dialog_sns("系统繁忙", 0);
					$(".queding0").attr("href", "javascript:deldialog_sns()");
				}
			}
		});
	}
}
	var letterCusId;
	function addLetterInput(cusId,obj,showname){//添加发送站内信的发送框
		dialog_sns("发消息", 4);
		dragFun();// 弹出框
		$(".dContent .dClose,.dCancel").unbind();
		$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove("#letterTextarea");closeFun();});
		$(".cusName").html(showname);
		letterCusId=cusId;
		initKindEditor_msg('letterTextarea','546px','80px');
			} 
	
	var EditorObject;
	function initKindEditor_msg(id,width,height){
			EditorObject = KindEditor.create('textarea[id='+id+']', {
					resizeType  : 1,
					filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
					pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
			       allowPreviewEmoticons : false,
			       allowUpload : true,//允许上传
			       syncType : 'auto',
			       urlType : 'domain',//absolute
			       newlineTag :'br',//回车换行br|p
			       minWidth:'30px',
			       width:width,
			       height:height,
			       afterBlur:function(){EditorObject.sync();}, 
			       allowFileManager : false,
			       items : ['emoticons'],
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
	function sendLetter(){//发送站内信
			var content = $("#letterTextarea").val();
			var conten=content.replaceAll("&nbsp;","").replaceAll("<br />","");
			if(conten.trim()==""){
			KindEditor.remove("#letterTextarea");
			deldialog_sns();
			dialog_sns("发送内容不能为空", 0);
			dragFun();// 弹出框
			$(".queding0").attr("href", "javascript:deldialog_sns()");// 确定按钮的方法
		return ;
			}
		var contentnum = EditorObject.count("text");// 要回复的内容
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
	function goTop() {
		var goTopEle = $('<a href="javascript:void(0)" id="global-goTop" class="G-goTop"><span><i class="icon12">&nbsp;</i><em class="G-txt">返回顶部</em></span></a>').appendTo($("body"));
		$("#global-goTop").click(function() {
			$("html,body").animate({"scrollTop" : 0}, 100);
		});
		var goTopF = function() {
			var scrH = $(document).scrollTop(),
				winH = $(window).height();
			(scrH > 0) ? goTopEle.fadeIn(50) : goTopEle.fadeOut(50);
			if (!window.XMLHttpRequest) {
				goTopEle.css(top , scrH + winH);
			};
		};
		$(window).bind("scroll" , goTopF);
	}
	//回复分页
	var blogid;
	function queryBlogReplyList(blogId,currentPage){
		blogid=blogId;
		ajaxPage("/blog/ajax/reply","&blogId="+blogId+"",1,callBackBlogReply);
	}
	function callBackBlogReply(result){
		$(".replyList").html(result);
	}
