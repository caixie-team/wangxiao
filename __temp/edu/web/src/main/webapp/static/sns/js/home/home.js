var scrollload=true;
var scrolllpage=true;
var weibopath = "weibo";
var type = 1;
var scroll_currentPage=0;
var scroll_totalPageSize=1;
var smpage=3;//页面滚动三次时作为一次分页
$(function() {
	loadWbRight();
	loadCRight();
	goTop();
	//initEditor('weiBoContent', '608px', '85px');//加载编辑器
	allDynamic();
	//lmenuFun();
});
window.onload=function(){//编辑器初始化
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
				$(".error").html("还可以输入<tt>"+num+"</tt>字").removeClass("cOrange");
			} else {
				$(".error").html("输入的文字超过<tt>140</tt>字").addClass("cOrange");
				$('#wenzistrweiBoContent').html("你已经超过");
				num = -num;
			}
			$('#numweiBoContent').html(num);
		}
	});
};
function addWeiBo() {
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
				dialog_sns("发布成功", 6);
				KindEditor.html('#weiBoContent', '');
				window.location.href=baselocation+"/weibo?type=1";
			}
			if (result.message == "addMost") {
				dialog_sns("请不要频繁操作，请稍后再试。", 6);
			};
		}
	});
}


function addPage(page) {// 添加分页的html
	//重新赋值分页的数据
	page.currentPage= Math.floor((page.currentPage-1)/smpage)+1;
	page.pageSize=page.pageSize*smpage;
	if (page.totalResultSize == null || page.totalResultSize == 0) {
		page.totalPageSize=0;
	} else {
		page.totalPageSize = Math.floor((page.totalResultSize - 1)
				/ page.pageSize) + 1;
	}
	//重新赋值分页的数据
	
	str = ""; 
	if(page.first){
		str = str +'<li class="disabled"><a href="#">首页</a></li>';
	}else{
		str = str +'<li><a href="javascript:allDynamic(1);">首页</a></li>';
	}
	if (page.currentPage<=1) {
		str = str + '<li class="disabled"><a href="javascript:void(0)">← 上一页</a></li>';
	} else {
		var num = page.currentPage - 1;
		str = str + '<li><a href="javascript:allDynamic(' + num
				+ ')">← 上一页</a></li>';
	}
	var currentPage = page.currentPage - 1 < 1 ? 1 : page.currentPage;
	var totalPage = page.totalPageSize;
	var maxNum_new = currentPage > 4 ? 6 : 8 - currentPage;// 最大显示页码数
	var discnt = 1;
	for ( var i = 3; i > 0; i--) {
		if (currentPage > i) {
			str = str + "<li><a href='javascript:allDynamic("
					+ (currentPage - i) + ")'>" + (currentPage - i)
					+ "</a></li>";
			discnt++;
		}
		;
	}
	str = str + '<li class="active"><a href="javascript:void(0)">'
			+ currentPage + '</a></li>';
	for ( var i = 1; i < maxNum_new; i++) {
		if (currentPage + i <= totalPage && discnt < 7) {
			str = str + "<li><a href='javascript:allDynamic("
					+ (currentPage + i) + ")'>" + (currentPage + i)
					+ "</a></li>";
			discnt++;
		} else {
			break;
		}
		;
	}
	if (page.currentPage>=page.totalPageSize) {
		str = str + '<li class="disabled"><a href="javascript:void(0)">下一页 →</a></li>';
	} else {
		str = str + '<li><a href="javascript:allDynamic('
				+ (page.currentPage + 1) + ')">下一页 →</a></li>';
	}
	if(page.last){
		str = str +'<li class="disabled"><a href="javascript:void(0)">尾页 </a></li>';
	}else{
		str = str +'<li ><a href="javascript:allDynamic('+page.totalPageSize+')">尾页 </a></li>';
	}
	$("#pageFlag").html(str);
}

function allDynamic(zpage) {// 回复分页
	$("#pageFlag").hide();
	var currentPage = $("#loading").attr("page");//当前真实页数
	if(zpage!=null){
		scrolllpage=true;
		$("#dongtai").html("");
		currentPage=(zpage-1)*smpage+1;
	}
	if(scrolllpage==false){
		$("#loading").hide();
		$("#pageFlag").show();
		return;
	}
	if(currentPage>scroll_totalPageSize) {
		$("#loading").hide();
		if(scroll_totalPageSize!=0){
			$("#pageFlag").show();
		}
		return;
	}
	
	
	scrollload=false;
	scroll_currentPage=parseInt(currentPage)+1;
	$.ajax({
				type : "POST",
				dataType : "json",
				url:baselocation+"/dw/queryDynamicWebList",
				data : {
					"page.currentPage" : currentPage,
					"type" : type
				},
				async : false,
				beforeSend: function(XMLHttpRequest){  
			          $("#loading").show();  
			    }, 
				success : function(result) {
					var page = result.page;
					if(page!=null ){
						scroll_totalPageSize =result.page.totalPageSize;
					}else{
						return;
					}
					var html = result.html;
					if (html != null && html!="") {
						if(zpage!=null){
							$("#dongtai").html(html);
						}else{
							$("#dongtai").append(html);
						}
						var num = parseInt(currentPage)+1;
						$("#loading").attr("page",num);//设置页数
						addPage(page);
						$("#pageFlag").show();
					} else {
						if(currentPage==1){
							var str = '<div class="Prompt">';
							str += '<img class="vam disIb" src="'+imagesPath+'/static/sns/images/tishi.png">';
							str += '<p class="vam c-555 fsize14 disIb">您还没有发布动态呢</p>';
							str += '</div>';
							$("#dongtai").html(str);
						}else{
						}
					}
					//$("#dongtai").html(result.html);
					initpinglun();
					$("#loading").hide();
					
				}
			});
	
	scrollload=true;
}

function Dynamic(obj, t) {
	if($(obj).parent().hasClass("current")){
		return;
	}
	scroll_currentPage=0;
	scroll_totalPageSize=1;
	$("#dongtai").html("");
	$("#loading").show();
	type = t;
	$("#loading").attr("page",1);//当前页数
	allDynamic(1);
	$(obj).parent().addClass("current");
	$(obj).parent().siblings().removeClass("current");
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
    		  if((scroll_currentPage-1)%smpage==0){
    			  scrolllpage=false;
    		  }
    		  if(scrolllpage ){
    			  allDynamic();
    		  }
    	  };
      };
  });
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
function lmenuFun() {
	var lM = $(".W-main-l-fixed");
	var lmFun = function() {
		var sTop = document.body.scrollTop || document.documentElement.scrollTop;
		if(sTop > 120) {
			lM.css({"position" : "fixed"});
			lM.animate({"top" : "50px"}, 500);
		} else {
			lM.css({"position" : "absolute" , "top" : "0"});
		}
	};
	$(window).bind("scroll" , lmFun);
	lmFun();
}
function goTop() {
	var goTopEle = $('<a href="javascript:void(0)" id="global-goTop" class="G-goTop"><span><i class="icon12">&nbsp;</i><em class="G-txt">返回顶部</em></span></a>').appendTo($("body"));
	$("#global-goTop").click(function() {
		$("html,body").animate({"scrollTop" : 0}, 300);
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

