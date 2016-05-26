<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!-- /公共头 -->
<script type="text/javascript">
//却换搜索类别
function serrchByType(obj,val){
	if(val==1){
		if($(obj).parent().hasClass("current")){
			return;
		}else{
			$(obj).parent().parent().find("li").removeClass("current");
			$(obj).parent().addClass("current"); 
			$(".tscInp").attr("placeholder","输入你想学的课程");
			$(".tscInp").attr("id","course");
		}
	}else if(val==2){
		if($(obj).parent().hasClass("current")){
			return;
		}else{
			$(obj).parent().parent().find("li").removeClass("current");
			$(obj).parent().addClass("current"); 
			$(".tscInp").attr("placeholder","输入你想学的图书");
			$(".tscInp").attr("id","book");
		}
	}else{
		if($(obj).parent().hasClass("current")){
			return;
		}else{
			$(obj).parent().parent().children("li").removeClass("current");
			$(obj).parent().addClass("current"); 
			$(".tscInp").attr("placeholder","输入你想学的文库");
			$(".tscInp").attr("id","library");
		}
	}
}
//搜索
function getSearch(){
	var searchStr=$("#course").val();
	if(searchStr!="输入你想学的课程"){
		$("#courseName").val(searchStr);
	}
	$("#formSearch").submit();
}

//获取热点课程
function getCourseByBrow(){
	var msg = '';
	$.ajax({
		url:baselocation+"/front/getHotCourse",
		type:"post",
		dataType:"json",
		success:function(result){
			var course = result.entity;
			for(i=0;i<course.length;i++){
				msg +='<a href="javascript:searchCourse(\''+course[i].name+'\')">'+course[i].name+'</a>'; 	
			}
			$("#msg").html(msg);
		}
	});
}

function searchCourse(searchStr){
	$("#courseName").val(searchStr);
	$("#formSearch").submit();
}

$(function(){
	getCourseByBrow();
	var url=window.document.location.pathname;
	$("a[href$='"+url+"']").parent().addClass("current");
});
</script>

	<div class="topBarWrap">
		<div class="w1000">
			<section class="cleafix">
				<ul class="t-link c-ccc fr">
					<!-- <li><wb:login-button type="6,2" onlogin="login" onlogout="logout">微博登录</wb:login-button>|</li> -->
					<li class="undis userNameLi"><tt class="c-666 vam">欢迎你</tt></li>
					<li class="undis userNameLi"><a href="${ctx}/uc/home"><img src="" id="cusImg" width="24" height="24" class="vam" /><tt class="c-666 ml5 cusName"></tt></a>|</li>
					<li class="pr undis newsLi"><tt class="tip-news pa undis" id="msgCountId" title="">0</tt><a href="${ctx}/uc/letter" title="">消息</a>|</li>
					<li class="undis outLi"><a href="javascript:void(0)" onclick="javascript:exit()" title="退出">退出</a>|</li>
					<li class="undis loginLi"><a href="/login" title="登录" >登录</a>|</li>
					<li class="undis registerLi"><a href="/register" title="注册" >注册</a>|</li>
					<li class="undis forgetPasswordLi"><a href="${ctx}/front/forget_passwd" title="忘记密码">忘记密码</a>|</li>
					<li><a href="javascript:addFavorite();" title="收藏本站" rel="sidebar" class="collect">收藏本站</a></li>
				</ul><!-- /t-link -->
			</section>
		</div>
	</div>
	<header id="header" class="png">
		<section class="head-wrap">
			<section class="w1000">
				<h1><a href="${ctx }" title="${websitemap.web.company}" class="png"><img src="<%=staticImageServer %>${logomap.logo.url}" class="logo-2013"/></a></h1>
				<div>
					
					<section class="topSearchWrap">
						<div class="tsTabCont">
							<section class="tsTabContInp">
								<input type="text"  onkeyup="enterSubmit(event,'getSearch()')" placeholder="输入你想学的课程" x-webkit-speech="" class="tscInp" id="course"/>
								<a href="javascript:void(0);" onclick="getSearch()"  class="tscBtn">搜 索</a>
							</section>
						<!-- 		<div id="msg"></div> -->
							<form id="formSearch" method="post" action="${ctx}/front/showcoulist">
								<input id="courseName"  type="hidden" name="queryCourse.name" value="${queryCourse.name}"/>
							</form>
						</div>
					</section>
					<section class="top-link pr">
						 <!-- <wb:follow-button uid="3750696877" type="red_2" width="" height="24" class="pa" style="left: -230px;top:-26px;" ></wb:follow-button> --> 
						
						<div class="mt30">
							<section class="my-course-wrap">
								<aside class="aMyCourBox fl pr aMyCourBox1" name="aMyCourBox1">
									<a href="javascript: void(0)" class="aMyCour m-c-btn hand"><tt class="vam f-fM">学习记录</tt><em class="icon14 ml5">&nbsp;</em></a>
									<div class="m-c-box undis">
										<section class="pr m-c">
											<span class="pa white-bg">&nbsp;</span>
											
											<div id="notLogin" class="tac undis">
												<em class="c-tips-2 icon24">&nbsp;</em>
												<font class="c-999 fsize12 vam">对不起，你还没有<a href="${ctx}/login"  title="登录" class="c-master ml5">登录</a></font>
											</div>
											<div id="nocoursebug" class="tac undis">
												<em class="c-tips-2 icon24">&nbsp;</em>
												<font class="c-999 fsize12 vam">购物车暂无课程，建议你去<a href="${ctx}/front/showcoulist" title="选课" class="c-master ml5">选课</a></font>
											</div>
											<div class="my-c-list undis" id="studulist">
                                                <div class="tac hLh30"><img src="/static/edu/images/page/loading.gif" /></div>
											</div>
										</section>
									</div>
								</aside>
								<aside class="aMyCourBox fl pr aMyCourBox2" name="aMyCourBox2">
									<a href="${ctx}/shopcart?command=queryShopCart" class="shopCar hand pr" style="overflow: inherit;" title="购物车">
										<tt class="tip-news pa undis" id="myCartNum">0</tt>
										<em class="icon24">&nbsp;</em><tt class="vam f-fM">查看购物车</tt>
									</a>
									<div class="m-c-box shopCar-box undis">
										<section class="pr m-c carthtml">
                                            <div class="tac hLh30"><img src="/static/edu/images/page/loading.gif" /></div>
										</section>
									</div>
								</aside>
								<div class="clear"></div>
							</section>
						</div>
					</section>

					<div class="clear"></div>
				</div>
				<div class="clear"></div>
			</section>
		</section>
		<div class="hNavWrap">
			<section class="w1000 clearfix">
				<aside class="fr">
					<span class="tel unBg" style="padding: 12px 0 0 0;"><em class="icon18">&nbsp;</em><tt class="c-fff f-fM vam ml5">${websitemap.web.phone}</tt></span>
				</aside>
				<ul class="hNav fl" id="guideInfo"><!-- current -->
					<c:forEach items="${navigatemap.INDEX}" var="indexNavigate">
       					<li><a href="${indexNavigate.url}" title="${indexNavigate.name}" <c:if test="${indexNavigate.newPage==0}">target="_blank"</c:if>>${indexNavigate.name}</a></li>
					</c:forEach>
				</ul>
			</section>
		</div>
	</header>