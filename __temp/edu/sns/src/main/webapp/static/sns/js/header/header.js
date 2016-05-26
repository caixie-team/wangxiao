$(function() {
	lMenu();//打开关闭左侧菜单导航
    menuControl();//左侧 跟随效果
	lMenuFun();//左侧目录跟随滚动固定
	
	if(isLogin()){
		queryUnReadNum();
		getlogincus();//显示登录信息
		
	}
	var url=window.document.location.pathname;
	$("a[href$='"+url+"']").parent().addClass("current");
});

function headerEnterSubmit(event) {//头部搜索的回车事件
	var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
	if (keyCode == 13) {
		submitsearchkey('weibo');
	return false;
	}
} 

var title;
var unReadNum;
function queryUnReadNum(){//查询未读消息
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
			//未读系统消息数
			var systemNum = letter.SMNum;
			//未读站内信数
			var letterNum = letter.mNum;
			//未读粉丝数
			var unreadFansNum = letter.unreadFansNum;
			unReadNum = letter.unReadNum;
			if(unReadNum!=0){
				$(".unReadNum").before('<span class="gt-mail-num" title="'+unReadNum+'条未读消息"></span>');
			}
			$(".unReadNum").attr("title",unReadNum+"条未读消息");

			//window.setInterval(titleInterval, 1000);
			title = $('title').text();
				$(".unReadNum").attr('href',baselocation +'/letter');
				
				if(unReadNum!=0){
					$(".letterNum").append("("+unReadNum+")");
				}
				/*if(systemNum!=0){
					$(".systemNum").append("("+systemNum+")");	
					$(".unReadNum").attr('href',baselocation +'/letter/sys');
				}
				if(letterNum!=0){
					$(".letterNum").append("("+unReadNum+")");
					$(".unReadNum").attr('href',baselocation +'/letter/inbox');
				}*/
				if(unreadFansNum!=0){
					$(".unreadFansNum").append("("+unreadFansNum+")");
				}
		}
	});
}
function titleInterval(){
	var titleNow = $('title').text();
	if (titleNow.indexOf('新消息') != -1) {
		$('title').text(title);
	}else{
		$('title').text(unReadNum+"条新消息");
	}
}
function getlogincus(){//获得登陆用户信息
		$.ajax({
			type : "POST",
			dataType : "json",
			url:"/user/loginuser",
			cache : true,
			async : false,
			success : function(result) {
				
				if(result.success ){
					var cus = result.entity;
					 // 微博数
					var weiBoNum = cus.weiBoNum;
					// 粉丝数
					var fansNum = cus.fansNum;
					// 关注数
					var attentionNum = cus.attentionNum;
					$(".weiBoNum").html(weiBoNum);
					$(".fansNum").html(fansNum);
					$(".attentionNum").html(attentionNum);
					var shortShowName = cus.showname.substr(0,12);//截取用户名
					$('.cusShortshowname').attr('title',cus.showname);//修改title
					$('.cusShortshowname').attr('href',''+baselocation +'/p/'+cus.cusId+'/home');//修改链接
                    $('.cusShortshowname').html(cus.showname);//用户名

					$('.cusshowname').attr('title',cus.showname);//修改title
					$('.cusId').val(cus.cusId);//储存用户id
					$('.cusshowname').attr('href',''+baselocation +'/p/'+cus.cusId+'/home');//修改链接
                    $('.cusshowname').html(cus.showname);//用户名
					$('.index').attr('href',''+baselocation +'/u/home');//修改头部链接
					$('.avatarhref').attr('href',''+baselocation +'/p/'+cus.cusId+'/home');//修改链接
					$('.avatarhref').attr('title',cus.showname);//修改title
					
					$("#unameheader").html(shortShowName);
					
					if(isNotEmpty(cus.avatar)){
						$(".avatar").attr("src",staticImageServer+cus.avatar);
					}else{
						$(".avatar").attr("src",imagesPath+"/static/common/images/user_default.jpg");
					}
				}
			}
		});
}

