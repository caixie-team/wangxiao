var letterCusId;
var weibopath = "weibo";
var editor;
var scrollload=true;
var scroll_currentPage=0;
var scroll_totalPageSize=1;
$(function(){
	loadWbRight();
	loadCRight();
	allDynamic();
	goTop();
});
	//微博回复
	//加载编辑器
	function initEditor(id,width,height){//编辑器初始化方法
			 editor = KindEditor.create('textarea[id="'+id+'"]', {
				resizeType:1,
				themeType : 'weibo',
			       filterMode : false,//true时过滤HTML代码，false时允许输入任何代码。
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
			       afterBlur:function(){
			    	   editor.sync();
			    	   }, 
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
		$(window).scroll(function() {  
	        
	        // 并不是所有页面都要执行加载操作。  
	        // 你也可以选择别的识别条件。  
	            
	          //获取网页的完整高度(fix)  
	      var hght= document.body.scrollHeight;  
	  
	      //获取浏览器高度(fix)  
	      var clientHeight =document.documentElement.clientHeight;  
	  
	          //获取网页滚过的高度(dynamic)  
	      var top= window.pageYOffset ||   
	                        (document.compatMode == 'CSS1Compat' ?    
	                        document.documentElement.scrollTop :   
	                        document.body.scrollTop);  
	        
	  
	          //当 top+clientHeight = scrollHeight的时候就说明到底儿了  
	      if(top>=(parseInt(hght)-clientHeight-150)){  
	    	  if(scrollload){
	    		  var friend=$("#friendid").val();
	    		  var userid=$("#userid").val();
	    		  var loginId=$("#loginId").val();
	    		  if(friend!=''||userid==loginId){
	    		  allDynamic(); 
	    		  }else{
	    			  return;
	    		  }
	    	  };
	      }  
	  }); 
			
	
	//展示动态
	function allDynamic(){//回复分页
		var currentPage = $("#loading").attr("page");
		var userid=$(".pHomeCusId").val();
		if(scroll_currentPage>scroll_totalPageSize) return;
		scrollload=false;
		scroll_currentPage=parseInt(currentPage)+1;
		$.ajax({
			type:"POST",
			dataType:"json",
			url:baselocation+"/dw/queryPersonDynamicWebList",
			data:{"page.currentPage":currentPage,"userid":userid},
			async:false,
			beforeSend: function(XMLHttpRequest){  
		          $("#loading").show();  
		        }, 
			success:function(result){
					var page = result.page;
					scroll_totalPageSize=result.page.totalPageSize;
					var html = result.html;
					if(html!=null&&html!=""){
						$("#dongtai").append(html);
						var num = parseInt(currentPage)+1;
						$("#loading").attr("page",num);//设置页数
					}else{
						if(currentPage==1){
							var str = '<div class="Prompt">';
							str += '<img class="vam disIb" src="'+imagesPath+'/static/sns/images/tishi.png">';
							var loginId=$("#loginId").val();
							if(loginId==userid){
								str += '<p class="vam c-555 fsize14 disIb">您还没有发布动态呢</p>';
							}else{
								str += '<p class="vam c-555 fsize14 disIb">他还没有发布动态呢</p>';
							}
							str += '</div>';
							$("#dongtai").html(str);
						}
						$("#pageFlag").html("");
					}
					initpinglun();
					$("#loading").hide();
			}
	});
		scrollload=true;
}
		var EditorObject;
		function initKindEditor_msg(id,width,height){
				EditorObject = KindEditor.create('textarea[id='+id+']', {
						resizeType  : 1,
				       allowPreviewEmoticons : false,
				       allowUpload : true,//允许上传
				       syncType : 'auto',
				       urlType : 'domain',//absolute
				       newlineTag :'br',//回车换行br|p
				       filterMode : true,// true时过滤HTML代码，false时允许输入任何代码。
						pasteType:1,//设置粘贴类型，0:禁止粘贴, 1:纯文本粘贴, 2:HTML粘贴
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
		
		//正则replaceAll方法
		String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {  
		    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {  
		        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);  
		    } else {  
		        return this.replace(reallyDo, replaceWith);  
		    }  
		}; 
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
		 // 添加好友
		function addFriend(cusId) {
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
				   if (result.message == "false") {
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
		function addLetterInput(cusId,obj,showname){//添加发送站内信的发送框
			dialog_sns("发消息", 4);
			dragFun();// 弹出框
			$(".dContent .dClose,.dCancel").unbind();
			$(".dContent .dClose,.dCancel").click(function() {KindEditor.remove("#letterTextarea");closeFun();});
			$(".cusName").html(showname);
			letterCusId=cusId;
			initKindEditor_msg('letterTextarea','546px','80px');
				} 
		
		function loadCRight(){
			$.ajax({
				url:baselocation+"/u/ajax/cright",
				data:{},
				type:"post",
				dataType:"text",
				async:false,
				success:function(result){
					$("#courseList").html(result);
				}
			});
		}
		function loadWbRight(){
			$.ajax({
				url:baselocation+"/u/ajax/wbright",
				data:{},
				type:"post",
				dataType:"text",
				async:false,
				success:function(result){
					$("#weiboList").html(result);
					$(".cj-comment-list>dl").each(function() {
				 		$(this).mouseover(function() {
				 			$(this).addClass("cj-c-d-show").siblings().removeClass("cj-c-d-show");
				 		});
				 	});
				}
			});
		}
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
