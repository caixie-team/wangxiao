<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>帮助中心</title>
</head>

<body>
	<div class="bg-fa of">
		<!-- /课程列表 开始 -->
		<section class="container">
			<section class="path-wrap txtOf hLh30"> 
				<a class="c-999 fsize14" title="" href="${ctx }">首页</a> \ 
				<span class="c-333 fsize14">帮助中心</span> 
			</section>
			<div class="clearfix mb30">
				<div class="fl col-25">
					<div class="i-box1">
						<ul class="help-c-nav pb20">
						<c:forEach items="${helpMenus}" var="helpMenu1" varStatus="index">
						<c:if test="${helpMenu1.size()==1}">
						<li><a href="${ctx}/help?id=${helpMenu1[0].id}" title="${helpMenu1[0].name}" class="h-nav-fir">${helpMenu1[0].name}</a></li>
						</c:if>
						<c:if test="${helpMenu1.size()>1}">
							<li>
								<a href="${ctx}/help?id=${helpMenu1[0].id}" title="${helpMenu1[0].name}" class="h-nav-fir">${helpMenu1[0].name}</a>
								<dl class="h-nav-sec-dl">
								<c:forEach items="${helpMenu1}" var="helpMenu" varStatus="index1">
									<c:if test="${index1.index!=0}">
									<dd><em></em>
										<a title="${helpMenu.name}" href="${ctx}/help?id=${helpMenu.id}">${helpMenu.name}</a>
									</dd>
									</c:if>
								</c:forEach>
								</dl>
							</li>
						</c:if>
						</c:forEach>
						</ul>
					</div>
				</div>
				<div class="fl col-75 h-c-top">
					<div class="ml30">
						<div class="i-box">
							${helpMenuContent.content}
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
<script type="text/javascript">
	function menuControl(){
	  var helpUrl =window.location+'';
	  var statuId='';
	  var aArr = $(".container a");
	  for(var i=0;i<aArr.length;i++){
		  var aHref = $(aArr[i]).attr("href");
		  if(helpUrl==aHref){
			$(aArr[i]).parent().addClass("current");
		  	if($(aArr[i]).parent()[0].localName=="dd"){
		  		$(aArr[i]).parent().parent().parent().addClass("current");
		  	}
		  }
	  }
	};
	$(function(){
		menuControl();
	});
</script>
</body>
</html>
