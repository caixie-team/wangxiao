var isimagechange=false;//修改时标识是否上传过新图片
var isimagerelease=true;//有图片时判断图片是否被选中
 //发表小组文章
 var EditorObject;
 var disArticlePath="disArticle";//图片路径
 function initKindEditor_adddisArticle(id,width,height){
		EditorObject = KindEditor.create('textarea[id='+id+']', {
				resizeType  : 1,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       syncType : 'auto',
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       width : width,
			   height : height,
		       minWidth:'30px',
				minHeight : '30px',
		       uploadJson : keImageUploadUrl+'&param'+disArticlePath,//图片上传路径
		       afterBlur:function(){EditorObject.sync();}, 
		       allowFileManager : false,
		       items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline','formatblock','lineheight',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link','plainpaste'],
				afterChange:function(){
				var num=$("#blogFont").html(this.count('text'));//字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字
				if(num>=5){
					return true;
				}else{
					return false;
				}
				}
		});
 }
 
 /**
  * Jcrop.min 坐标记录
  * @param c
  */
 function changeCoords(c) {
	 isimagerelease=true;
	$("#txtx").val(c.x);
	$("#txty").val(c.y);
	$("#txtx2").val(c.x2);
	$("#txty2").val(c.y2);
	$('#txt_DropWidth').val(c.w);
    $('#txt_DropHeight').val(c.h);
	
}
 /**
  * Jcrop.min clearCoords
  */
function clearCoords(){
	isimagerelease=false;
}

 //上传小组头像
 var jcrop_api;
 function initFileUpload(btnid,urlid,txtname){
	 	KindEditor.create('');
		var uploadbutton = KindEditor.uploadbutton({
			button : KindEditor('#'+btnid+'')[0],
			fieldName : txtname,
			url : uploadSimpleUrl+"&param=temp",
			afterUpload : function(data) {
				if (data.error == 0) {
					isimagechange=true;
					$('#imageUrl').val(data.url);
					$(".jcrop-holder").remove();
					$('#'+urlid+'').attr("style","");
					$('#'+urlid+'').show();
					$('#'+urlid+'').attr("src",staticImageServer+""+data.url);
					if(jcrop_api!=null){try {jcrop_api.destroy();}catch(e){}}
					$('#'+urlid+'').Jcrop({setSelect:[0,0,150,150], minSize:[150, 150], onChange:changeCoords, onSelect:changeCoords,onRelease:clearCoords, aspectRatio:1},function(){jcrop_api = this;});
				} else {
				}
			},
			afterError : function(str) {
				alert('自定义错误信息: ' + str);
			}
		});
		uploadbutton.fileBox.change(function(e) {
			uploadbutton.submit();
		});
}
 	//创建小组
	//进行信息验证
	function disNameHint(){
		if($('#disName').val()==null||$('#disName').val().trim()==''){
			$("#disHint").show();
		}else{
			$("#disHint").hide();
		}
	}
	
	function savedispic(callback){
		if(!isimagerelease){
			alert("请选中图片");
			return;
		}
		//var params = $("#imgcropFrom input").serialize();
		var path=$('#imageUrl').val();
		params="photoPath="+path+"&txt_width="+$("#txtx2").val()+
		"&txt_height="+$("#txty2").val()+"&txt_top="+$("#txty").val()+
	     "&txt_left="+$("#txtx").val()+"&txt_DropWidth="+$("#txt_DropWidth").val()+
	     "&txt_DropHeight="+$("#txt_DropHeight").val();
		var url =uploadServerUrl+"/saveface?"+params+"&callback=?";
		$.getJSON(url,function(json){
			if(callback=="addcallback"){
				addcallback(json);
			}else{
				updatecallback(json);
			}
		});
	/*	$.ajax({ 
		        url:uploadServerUrl+"/saveface?base=sns&"+params+"=dis&callback="+callback,  
		        dataType:'jsonp',  
		        data:params,
		        async:false,
		        jsonp: "callbackparam",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(默认为:callback)
		        jsonpCallback:"success_jsonpCallback",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
		        success:function(json) {
		        	alert("111");
		        	alert(json);
		        }
		 });*/
	}
	
	function addcallback(data){
		if(data!=null){
			if(data.error!=0){
				alert("图片上传错误");
				return;
			}
			$('#imageUrl').val(data.src);
		}
		//获得填写信息
		var disName=$('#disName').val();
		var disIntroduction=$("#disIntroduction").val();
		var disClassify=$('.commSelect').val();
		$.ajax({
			url:baselocation+"/dis/add",
			data:{"disGroup.name":disName,
				"disGroup.introduction":disIntroduction,
				"disGroup.disclassifyId":disClassify,
				"disGroup.imageUrl":$('#imageUrl').val()
				},
			dataType:"json",
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					dialog_sns("创建成功，返回我创建小组",1);
					$(".queding1").attr("href","/dis/my");
					
				}else{
					dialog_sns("创建失败，稍后重试",5);
				}
			}
		});
	}
	function createDisGroup(){
		if(checkLogin()=="unLogin"){
			return;
		};
		//进行信息验证
		var countTxt=$('#disIntroduction').val().length;
		var disname=$('#disName').val().length;
		if(disname>24){
			dialog_sns("您输入的名称过长",5);
			return;
		}
		if(countTxt>200){
			dialog_sns("您输入的内容过长",5);
			return false;
		}
		if($('#disName').val()==null||$('#disName').val().trim()==''||$("#disIntroduction").val()==null
				||$("#disIntroduction").val().trim()==''||$('.commSelect').val()==0||$("#imageUrl").val()==null||
				$("#imageUrl").val()==''){
			dialog_sns("您输入的信息不完整",5);
			return false;
		}else{
			savedispic("addcallback");
			//先保存头像文件
			/*var params = $("#imgcropFrom input").serialize();
			$.getJSON(uploadServerUrl+"/savedis?base=sns&param=dis&callback=?&"+params, function(data){
				alert(2222);
			 });*/
			
		}
	}
 function initKindEditor_editDisArticle(id,width,height){
		EditorObject = KindEditor.create('textarea[id='+id+']', {
			   resizeType  : 1,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       syncType : 'auto',
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       minWidth:'30px',
		       width:width,
		       height:height,
		       uploadJson : keImageUploadUrl+'&param'+disArticlePath,//图片上传路径
		       afterBlur:function(){EditorObject.sync();}, 
		       allowFileManager : false,
		       items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline','formatblock','lineheight',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
				afterChange:function(){
				var num=$("#blogFont").html(this.count('text'));//字数统计包含纯文本、IMG、EMBED，不包含换行符，IMG和EMBED算一个文字
				if(num>=5){
					return true;
				}else{
					return false;
				}
				}		
		});
}
	function limit(){
		var count=$("#text").val().length;//获得标题长度
		if(count>50){
			dialog_sns("对不起您已输入超过50个字",5);
			return false;
		}else{
			return true;
		}
		
	}
	//发布小组文章
	function tijiao(groupId,classify){
		var selType=$("#selType").val();
		var count=EditorObject.count('text');//判断内容字数
		if(count<5){
			dialog_sns("您输入的内容不能少于5个字",5);
			return false;
		}
		if(count>max_text_length){
			dialog_sns("您输入内容超过"+max_text_length+"字,请删减！",5);
			return false;
		}
		if($("#text").val()==null||$("#text").val().trim()==''){//判断标题是否为空
			dialog_sns("标题不能为空!",6);
			return false;
		}
		if($("#text").val().length>50){//判断标题字数
			dialog_sns("对不起,标题不能超过50个字!",5);
			return false;
		}
		else{
			var title=$("#text").val();//获得标题值
			var Content=$("#disArticleContent").val();//获得内容值
			//var artClassifyId=$("#artClassifyId").val();//获得分类id
			$.ajax({
				url:baselocation+"/dis/addart",
				data : {
					"disArticle.title" : title,
					"disArticle.content" : Content,
					"disArticle.artClassifyId" : classify,
					"disArticle.groupId":groupId,
					"disArticle.selType":selType
				},
				type : "post",
				cache : true,
				async : false,
				success : function(result) {
					if (result.message == "success") {
						dialog_sns("发表成功",1);
						$(".queding1").attr("href", "/dis/myart");//发表成功关闭弹出框
							} else if (result.message == "limitOpt") {
								dialog_sns("操作频繁,请您稍后再发",0);
								$(".queding0").attr("href", "javascript:deldialog_sns()");//发表错误弹出框
							} else {
								dialog_sns("系统繁忙",0);
								$(".queding0").attr("href", "javascript:deldialog_sns()");//发表错误弹出框
							}
						}
					});
		}
	}
	//修改小组文章
	function baocun(articleId,groupId){
		var selType=$("#selType").val();
		var count=$("#disArticleContent").val().length;//判断内容字数
		if(count<5){
			dialog_sns("您输入的内容不能少于5个字",5);
			return false;
		}
		if($("#text").val()==null||$("#text").val().trim()==''){//判断标题是否为空
			dialog_sns("标题不能为空！",5);
			return false;
		}
		else{
			var title=$("#text").val();//获得标题内容
			var Content=$("#disArticleContent").val();//文本内容
			$.ajax({
				url:baselocation+"/dis/updateMyArticle",
				data : {
					"disArticle.title" : title,
					"disArticle.content" : Content,
					"disArticle.groupId":groupId,//小组id
					"disArticle.id":articleId,//文章id
					"disArticle.selType":selType
				},
				type : "post",
				cache : true,
				async : false,
				success : function(result) {
					if (result.message == "success") {
						dialog_sns("修改成功",1);
						$(".queding1").attr("href", "/dis/myart");//修改成功
							}else {
								dialog_sns("系统繁忙",0);
						$(".queding0").attr("href", "javascript:deldialog_sns()");//修改失败
							}
						}
					});
		}
	}
	//小组搜索 公用
	function dissearch(){
		var disname=$('#searchdis').val();//获得输入框值
		if(disname!=null||disname!=''){
			$("#dissearchform").submit();
		}else{
			dialog_sns("请输入要搜索的小组名称",5);
		}
		
	}
	
	//我的小组文章
	function delarticle(artId,groupId){
		dialog_sns("确认删除？",2);
		$(".queding2").attr("href","javascript:deldisarticle("+artId+","+groupId+")");//删除弹出框
		
	}
	function deldisarticle(id,groupId){//删除小组文章
		$.ajax({
			url:baselocation+"/dis/delart",
			type:"post",
			dataType:"json",
			data:{"disArticle.id":id,"disArticle.groupId":groupId},//根据小组文章id 文章id删除该文章
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					deldialog_sns();
					dialog_sns("删除成功",6);
					$("#rem"+id).remove();//删除成功，移除删除的数据
				}else{
					dialog_sns("请刷新重试",5);//删除失败稍后重试
					return false;
				}
			}
		});
	}
	
	/**
	 * 修改小组回调
	 * @param data
	 */
	function updatecallback(data){
		if(data!=null){
			if(data.error!=0){
				alert("图片上传错误");
				return;
			}
			$('#imageUrl').val(data.src);
		}
		//获得填写信息
		var disName=$('#disName').val();
		var disIntroduction=$("#disIntroduction").val();
		var disClassify=$('.commSelect').val();
		$.ajax({
			url:baselocation+"/dis/update",
			data:{"disGroup.name":disName,
				"disGroup.introduction":disIntroduction,
				"disGroup.disclassifyId":disClassify,
				"disGroup.imageUrl":$('#imageUrl').val(),
				"disGroup.id":$('#disGroupId').val()
				},
			dataType:"json",
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				var message=result.message;
				if(message=="success"){
					dialog_sns("修改成功",1);
					$(".queding1").attr("href","/dis/my");
					return;
				}
				else{
					dialog_sns("修改失败，稍后重试",0);
					$(".queding0").attr("href","javascript:deldialog_sns()");
					return;
				}
			}
		});
	}
	//修改小组信息
	//创建小组
	function saveDisGroup(){
		var disname=$('#disName').val().length;
		if(disname>24){
			dialog_sns("您输入的名称过长",5);
			return;
		}
		//进行信息验证
		if($('#disName').val()==null||$('#disName').val().trim()==''||$("#disIntroduction").val()==null
				||$("#disIntroduction").val().trim()==''||$('.commSelect').val()==0||$("#imageUrl").val()==null||
				$("#imageUrl").val()==''){
			dialog_sns("您输入的信息不完整",5);
			return false;
		}else{
			if(isimagechange){
				savedispic("updatecallback");
			}else{
				updatecallback();
			}
		}
	}
	//线下活动
	function belowTL(groupId,classify){
		var selType=$("#selType").val();
		if(selType==0){
			dialog_sns("请选择线下活动",5);
			return;
		}else{
			tijiao(groupId,classify);
		}
		
	}
	//格式化时间方法 公用
	Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
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
	//评论文章
	function articleComment(artId,groupId){
		var limitnum=EditorObject.count('text');//获得回复的字数
		var replycontent=$("#replyContent").val();
		var conten=replycontent.replaceAll("&nbsp;","").replaceAll("<br />","");
		if(conten.trim()==""||conten==null){
			$('#wenzi').show();//如果没有输入则提示
			return;
		}
		if(limitnum>5000){
			dialog_sns("您输入的内容过长，也许可以精简一点！",5);//限制回复在五千字内
			return;
		}else{
		$.ajax({
			url:baselocation+"/dis/addrep",//添加回复
			type:"post",
			dataType:"json",
			data:{"disArticleReply.replyContent":replycontent,"disArticleReply.articleId":artId,"disArticleReply.groupId":groupId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					KindEditor.remove('#replyContent');
					$(".dContent .dClose,.dCancel").click();
					dialog_sns("评论成功",6);
					goPageAjax(1);
							} else if (result.message == "limitOpt") {
								deldialog_sns();
								dialog_sns("请不要频繁操作，请稍后再试。",5);//频繁操作提示
								return;
							}else if(result.message =="prohibit"){
								deldialog_sns();
								dialog_sns("该话题已禁止回应",5);
								return;
							}else {
								deldialog_sns();
								dialog_sns("失败请刷新重试",5);//失败重试
								return;
							}

						}
					});
		}
		}
	//回复初始化编辑器
	function initKindEditor_comment(id){
		EditorObject = KindEditor.create('textarea[id='+id+']', {
				resizeType  : 1,
		       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
		       allowPreviewEmoticons : false,
		       allowUpload : true,//允许上传
		       syncType : 'auto',
		       urlType : 'domain',//absolute
		       newlineTag :'br',//回车换行br|p
		       minWidth:'30px',
		       readonlyMode : true,
		       afterBlur:function(){
		    	   EditorObject.sync();
		    	   EditorObject.readonly();},
		       afterFocus: function() {
		    	   EditorObject.readonly(false);
				}, 
		       allowFileManager : false,
		       items : ['emoticons']
		});
		EditorObject.focus();
		EditorObject.appendHtml('');//追加
}
	//评论小组文章
	function dispinglun(artId,groupId){
		if(checkLogin()=="unLogin"){
			return;
		}
		dialog_sns('评论',3);
		$(".dContent .dClose,.dCancel").unbind();
		$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove('#replyContent');closeFun1();closeFun();});
		initKindEditor_comment('replyContent');
		$("#reply").attr("href","javascript:articleComment("+artId+","+groupId+")");
		$("#yingyong").html($(".discontent").html().replace(/<[^>].*?>/g,"").replaceAll("&nbsp;","").substr(0,50)+".........");
		dragFun();
	}
	//回复他人的回复
	function otherreply(artId,groupId,showName,replyId){
		if(checkLogin()=="unLogin"){
			return;
		}
		if(is!=0){
		dialog_sns('评论',3);
		$(".dContent .dClose,.dCancel").unbind();
		$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove('#replyContent');closeFun1();closeFun();});
		$("#replyContent").val("回复@"+showName+":");
		initKindEditor_comment('replyContent');
		$("#reply").attr("href","javascript:articleComment("+artId+","+groupId+")");
		$("#yingyong").html($(".huifucontent"+replyId).html().replace(/<[^>].*?>/g,"").replaceAll("&nbsp;","").substr(0,50)+".........");
		dragFun();
		}else{
			dialog_sns("请加入该小组",5);
			return false;
		}
	}
	//正则replaceAll方法
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
	    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
	        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
	    } else {  
	        return this.replace(reallyDo, replaceWith);  
	    }  
	}; 
	function isJoin(artId,groupId,id,status){//判断是否加入小组 来进行一下操作
		if(checkLogin()=="unLogin"){
			return;
		}
		if(id!=0){
			if(status==1){
				dialog_sns("该话题已禁止回应",5);
				return;
			}else{
				dispinglun(artId,groupId);
			}
		}else{
			dialog_sns("请加入该小组",5);
			return false;
		}
	}
	//删除自己的回复
	function deldisreply(artId,replyId){
		dialog_sns("确定删除？",2);
		$(".queding2").attr("href","javascript:deldissure("+artId+","+replyId+")");
	}
	function deldissure(artId,replyId){//删除回复
		$.ajax({
			url:baselocation+"/dis/delartrep",
			data:{"disArticleReply.articleId":artId,"disArticleReply.id":replyId},
			dataType:"json",
			type:"post",
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					deldialog_sns();
					dialog_sns("删除成功",6);
					$(".rem"+replyId).remove();
					var num=$(".replyCount").html().replace(/[(|)]/g,"");
					var sum=0;
					if(parseInt(num)>0){
						sum= parseInt(num)-1;
					}
					$(".replyCount").html("("+sum+")");
				}else if(result.message=="isEmpty"){
					return false;
				}else{
					deldialog_sns();
					dialog_sns("失败",5);
				}
			}
		});
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
	
	function attention(id, obj) {//加关注
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
	//判断是否加入小组
	function isJoinPublish(id,groupId,type){
		if(id!=0){
			window.location.href="/dis/item/"+groupId+"?type="+type;
			return true;
		}else{
			dialog_sns("请加入该小组",5);
			return false;
		}
	}
	//置顶博客
	function distop(id,obj){
			$.ajax({
				url:baselocation+"/dis/top",
				data:{"articleId":id},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){
						dialog_sns("置顶成功",6);
						$(obj).html("取消置顶");
						$(obj).attr("onclick","disCancel("+id+",this)");
					}
					if(result.message=="false"){
						dialog_sns("置顶失败，刷新重试",5);
					}
				}
				
			});
	}
	function disCancel(id,obj){
			$.ajax({
				url:baselocation+"/dis/cancel",
				data:{"articleId":id},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){
						dialog_sns("取消成功",6);
						$(obj).html("置顶");
						$(obj).attr("onclick","distop("+id+",this)");
					}
					if(result.message=="false"){
						alert("置顶失败，刷新重试");
					}
				}
			});
		
	}
	function delarticle(artId,groupId){
		dialog_sns("确定删除？",2);
		$(".queding2").attr("href","javascript:deldisarticle("+artId+","+groupId+")");
		
	}
	function deldisarticle(id,groupId){
		$.ajax({
			url:baselocation+"/dis/delart",
			type:"post",
			dataType:"json",
			data:{"disArticle.id":id,"disArticle.groupId":groupId},
			cache:true,
			async:false,
			success:function(result){
				if(result.message=="success"){
					deldialog_sns();
					dialog_sns("删除成功",6);
					$("#rem"+id).remove();
				}else{
					dialog_sns("请刷新重试",5);
					return false;
				}
			}
		});
	}
	//退出小组
	function disexit(disId){
		dialog_sns("确定退出小组吗？",2);
		$(".queding2").attr("href", "javascript:exitDis("+disId+")");
	}
	function exitDis(disId){
			$.ajax({
				url:baselocation+"/dis/exit/"+disId,
				type:"post",
				dataType:"json",
				data:{"groupId":disId},
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){
						deldialog_sns();
						dialog_sns("退出成功",6);
						$("#shanchu"+disId).remove();
					}else{
						dialog_sns("系统繁忙稍后重试",5);
						return false;
					}
					
				}
				
			});
	}
	//删除小组成员
	function delmember(groupId,cusId){
		dialog_sns("确定踢出该成员吗？",2);
		$(".queding2").attr("href", "javascript:deletemember("+groupId+","+cusId+")");
	}
	function deletemember(groupId,cusId){
			$.ajax({
				url:baselocation+"/dis/delmember",
				type:"post",
				dataType:"json",
				data:{"disMember.groupId":groupId,"disMember.cusId":cusId},
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){
						deldialog_sns();
						dialog_sns("踢出成功",6);
						$("#delmeb"+cusId).remove();
					}else{
						dialog_sns("系统繁忙稍后重试",5);
						return false;
					}
					
				}
				
			});
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
		function close() {$(".transfer-bg").remove();}
		$(function(){
			$(".qun-list>li").each(function() {
			var _this = $(this);
			_this.find(".transferD .transferBtn").click(function() {
			_this.find(".transferD").css("z-index" , "1");
			var groupId = _this.find(".transferD .transferBtn").attr("groupId");
			var twEle = '';
			twEle += '<section class="transfer-bg">';
			twEle += '<div class="transfer-wrap">';
			twEle += '<div class="DT-arrow TW-arrow"><em>◆</em><span>◆</span></div>';
			twEle += '<section class="TW-search pl10">';
			twEle += '<h2 class="clearfix pr"><a class="cClose" href="javascript:close()" title="关闭">&nbsp;</a><span class="fsize12 c-555">小组管理员列表</span></h2>';
			//twEle += '<input type="tel" name="" value="" class="TW-sInp mt5" id="person">';
			twEle += '</section>';
			twEle += '<section class="TW-p-list" id="disMemberinfo'+groupId+'">';
			//twEle += '<h6><a class="c-555" title="" href="">北京游学教育”教育机构</a></h6>';
			//twEle += '<p><span class="c-888" id="customer">教学总监</span></p>';
			twEle += '</section>';
			twEle += '</div>';
			twEle += '</section>';
			_this.find(".transferD .transferBtn").parent(".transferD").append(twEle);
			_this.find(".transferD .transferBtn").parent(".transferD").find(".transfer-bg").show();
			_this.siblings().find(".transferD .transferBtn").siblings().remove();
			_this.siblings().find(".transferD").attr("style" , "");
			$.ajax({
				url:baselocation+"/dis/member",
				data:{"disMember.groupId":groupId},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					var disMember=result.disMemberList;
					if(disMember.length>0){
					
					for(var i=0;i<disMember.length;i++){
						var zrText='';
						var img='';
						var sex='';
						if(disMember[i].geniusUser==null||disMember[i].geniusUser.avatar==null||disMember[i].geniusUser.avatar==''){
							img+='<img height="40" width="40" alt="" src="'+imagesPath+'/static/common/images/user_default.jpg">';
						}
						if(disMember[i].geniusUser!=null&&disMember[i].geniusUser.avatar!=null&&disMember[i].geniusUser.avatar!=''){
							img+='<img height="40" width="40" alt="" src="'+staticImageServer+''+disMember[i].geniusUser.avatar+'">';
						}
						zrText += '<div class="clearfix TW-p">';
						zrText += '<a class="TW-face"  href="javascript:void(0)" onclick="zhuanrang('+groupId+','+disMember[i].cusId+')">'+img+'</a>';
						zrText += '<h6><a class="c-555" title="" href="javascript:void(0)" onclick="zhuanrang('+groupId+','+disMember[i].cusId+')">'+disMember[i].showName+'</a></h6>';
						if(disMember[i].geniusUser!=null&&disMember[i].geniusUser.genderName=='男'){
							sex+='<span class="boy"><i class="icon16">&nbsp;</i></span>';
						}
						if(disMember[i].geniusUser!=null&&disMember[i].geniusUser.genderName=='女'){
							sex+='<span class="girl"><i class="icon16">&nbsp;</i></span>';
						}
						zrText += '<p>'+sex+'</p>';
						zrText += '</div>';
						$("#disMemberinfo"+groupId).append(zrText);
					}
					}else{
						$("#disMemberinfo"+groupId).html("对不起，没有合适的小组成员");
					}
				}
			});
			});
			});
		});
		function zhuanrang(groupId,cusId){
			$.ajax({
				url:baselocation+"/dis/assign",
				data:{"disMember.groupId":groupId,"disMember.cusId":cusId},
				dataType:"json",
				type:"post",
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){
						dialog_sns("转让成功，您不再是管理员",6);
						$(".transfer-bg").remove();
						$("#shanchu"+groupId).remove();
					}else if(result.message==1){
						dialog_sns("对不起，您不再是管理员",5);
						$(".transfer-bg").remove();
					}else if (result.message=="yourSelf"){
						dialog_sns("对不起，组长职位只能转让给管理员",5);
						$(".transfer-bg").remove();
					}
					else{
						dialog_sns("转让失败稍后重试",5);
						$(".transfer-bg").remove();
					}
				}
			});
		}
		function addGroup(groupId){//接受添加小组
			var flag=checkLogin();
			if(flag=="unLogin"){
				return;
			}
			$.ajax({
				type:"POST",
				dataType:"json",
				url:baselocation+"/dis/apply",
				data:{"groupId":groupId},
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="notExsit"){
						dialog_sns("该小组已不存在", 5);
					}
					if(result.message=="success"){ 
						dialog_sns("欢迎加入小组", 6);
						window.location.reload();
					}
					if(result.message=="exist"){ 
						dialog_sns("已加入该小组", 5);
						dragFun();// 弹出框
					}
					
				}
			});
		}
		function prohibitChat(groupId,artId,obj){//禁止回应
			$.ajax({
				url:baselocation+"/dis/updatestatus",
				type:"post",
				dataType:"json",
				data:{"disArticle.groupId":groupId,"disArticle.id":artId,"type":1},
				async:false,
				success:function(result){
					if(result.message=="success"){
						$(obj).html("允许回应");
						$(obj).attr("onclick","permitChat("+groupId+","+artId+",this)");
						dialog_sns("已禁止小组成员回应该话题",5);
						}else{
							dialog_sns("系统繁忙，请稍后重试",5);
							return;
						}
				}
				
			});
			
		}
		function permitChat(groupId,artId,obj){//允许回应
			$.ajax({
				url:baselocation+"/dis/updatestatus",
				type:"post",
				dataType:"json",
				data:{"disArticle.groupId":groupId,"disArticle.id":artId,"type":0},
				async:false,
				success:function(result){
					if(result.message=="success"){
						$(obj).html("禁止回应");
						$(obj).attr("onclick","prohibitChat("+groupId+","+artId+",this)");
						dialog_sns("已允许小组成员回应该话题",6);
						}else{
							dialog_sns("系统繁忙，请稍后重试",5);
							return;
						}
				}
				
			});
			
		}
		//提拔小组成员
		function promote(groupId,cusId){
			dialog_sns("确定将该成员提拔成管理员吗？",2);
			$(".queding2").attr("href", "javascript:promoteUser("+groupId+","+cusId+")");
		}
		function promoteUser(groupId,cusId){
			$.ajax({
				url:baselocation+"/dis/promote",
				type:"post",
				data:{"disMember.groupId":groupId,"disMember.cusId":cusId,"type":0},
				dateType:"json",
				async:false,
				success:function(result){
					if(result.message=="success"){
						dialog_sns("提拔成功",6);
						window.location.reload();
					}else{
						dialog_sns("系统繁忙，请稍后重试",5);
						return;
					}
					
				}
			});
		}
		//解除小组成员
		function removal(groupId,cusId){
			dialog_sns("确定免去该成员职务吗？",2);
			$(".queding2").attr("href", "javascript:removalUser("+groupId+","+cusId+")");
		}
		function removalUser(groupId,cusId){
			$.ajax({
				url:baselocation+"/dis/promote",
				dateType:"json",
				data:{"disMember.groupId":groupId,"disMember.cusId":cusId,"type":1},
				type:"post",
				async:false,
				success:function(result){
					if(result.message=="success"){
						dialog_sns("免除成功",6);
						window.location.reload();
					}else{
						dialog_sns("系统繁忙，请稍后重试",5);
						return;
					}
					
				}
			});
		}
		//添加删除喜欢
		function addLike(articleId,obj){
			if(checkLogin()=="unLogin"){
				return;
			}
			$.ajax({
				url:baselocation+"/dis/like",
				type:"post",
				data:{"articleId":articleId},
				dataType:"json",
				cache:true,
				async:false,
				success:function(result){
					if(result.message=="success"){
						var num=$(".like").html().replace(/[(|)]/g, "");
						var sum=0;
						if(result.status==0){
							sum=parseInt(num) + 1;
							if($(".Liketitle").html()=='参加'){
								$(".Liketitle").attr("title","取消参加?");
							}else{
								$(".Liketitle").attr("title","取消喜欢?");
							}
						}else{
							sum=parseInt(num) - 1;
							if($(".Liketitle").html()=='参加'){
								$(".Liketitle").attr("title","标为参加?");
							}else{
								$(".Liketitle").attr("title","标为喜欢?");
							}
						}
						$(".like").html("(" + sum + ")");
						//dialog_sns("喜欢成功",6);
						return;
					}
					if(result.message=="limitOpt"){
						dialog_sns("请不要频繁操作",5);
						return;
					}else{
						dialog_sns("系统繁忙，请稍后重试",5);
					}
				}
			});
		}
		//推荐
		function recommend(articleId){
			if(checkLogin()=="unLogin"){
				return;
			}
			dialog_sns('推荐到268网校',3);
			$("#reply").attr("href","javascript:articleRecommend("+articleId+")");
			$("#yingyong").html($(".discontent").html().replace(/<[^>].*?>/g,"").replaceAll("&nbsp;","").substr(0,50)+".........");
			dragFun();
		}
		function articleRecommend(articleId){
			var replycontent=$("#replyContent").val();
			$.ajax({
				url:baselocation+"/dis/recommend",
				data:{"disArticleFavor.content":replycontent,"disArticleFavor.articleId":articleId},
				dataType:"json",
				type:"post",
				async:false,
				success:function(result){
					if(result.message=="success"){
						var num=$(".recommended").html().replace(/[(|)]/g, "");
						var sum=0;
						sum=parseInt(num) + 1;
						$(".recommended").html("(" + sum + ")");
						dialog_sns("分享成功",6);
					}else if(result.message=="exist"){
						dialog_sns("已分享过该话题",5);
					}else{
						dialog_sns("系统繁忙，请稍后重试",5);
					}
				}
			});
			
		}
	/*	//回复分页
		var groupid;
		var articleid;
		var dstatus;
		function queryDisArticleReplyList(currentPage){
			groupid=$("#disgroupid").val();
			articleid=$("#disArticleid").val();
			dstatus=$("#disStatus").val();
			$.ajax({
				type:"POST",
				dataType:"text",
				url:baselocation+"/dis/ajax/reply",
				data:{"articleId":articleid,"groupId":groupid,"page.currentPage":currentPage,"status":dstatus},
				async:false,
				success:function(result){
					$(".disArticleReplyList").html(result);
				}
			});
		}
		function goPageAjax(pageNum){
			queryDisArticleReplyList(pageNum);
		}*/
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
		