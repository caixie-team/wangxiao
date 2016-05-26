<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>文库列表</title>
<script type="text/javascript">
$(function (){
	//文库搜索内容放入上部搜索框
	$(".tscInp").val('${queryLibrary.name}');
	var order = '${queryLibrary.orderNum}';
	var reconent ='${queryLibrary.name}';
	var subjectId ='${subject.subjectId}';
	var subjectName='${subject.subjectName}';
	subjectId = subjectId.trim();
	
	var queryConditionConent='';
	if(subjectName!=''){
		queryConditionConent+=subjectId+'(专业)';
	}
	if(order==1){
		queryConditionConent+='关注度';
	}else if(order==2){
		queryConditionConent+='最新';
	}
	if(reconent!=null && reconent.trim()!=''){
		if(queryConditionConent!=''){
			queryConditionConent=reconent+'+'+queryConditionConent;
		}else{
			queryConditionConent=reconent;
		}
	}
	$("#retrievalContentId").text(queryConditionConent);
	//设置 显示检索条件。结束------------------
	//if(reconent==null || reconent==''){
	//	$("#sellName").val(derCone);
	//}
	if(order==1||order==2){
		$("#orderAid"+order).parent('li').find('a').removeClass('current');
		$("#orderAid"+order).addClass('current');
	}else{
		if(order=='3'){
			$("#orderAid3").addClass('current');
			$(".u-jg").css('background-position','-149px -270px');
		}else if(order=='4'){
			$("#orderAid3").addClass('current');
			$(".u-jg").css('background-position','-134px -270px');
		}
	};
	$(".s-c-list>li:nth-child(4n)").css({"margin-right" : "0px"});
	
	$(".libraryMenuBox>dl>dd").each(function() {
		$(this).hover(function() {
			$(this).addClass("hover");
		}, function() {
			$(this).removeClass("hover");
		});
	});
});
function selectLibrary(type,id){
	if(type==1){
		$("#hiddenSubjectId").val(id);
	}else if(type==3){
		$("#hiddenSubjectId").val(0);
	}
	$("#searchForm").submit();
}
function orderLibrary(obj,val){
	if($(obj).hasClass("current")){
		return;
	}else{
		$("#orderNum").val(val);
		$("#searchForm").submit();
	}
}
//查找子专业
$(function(){
	var DragElement=null; 
	$(".libraryMenuBox #subjectATag").mouseover(function(){
		var a=$(this).next("section").find(".whiteBgLm").next("a").length;
		if(a>0){//第二次加载  ，不重复追加
			return;
		}
		var subjectId=$(this).prop("name");
		DragElement=this;//这时this指的就是$("#subjectATag")对象   //由于是在mouseover内，这样this对象就不是$("#subjectATag")
		$.ajax({
			  type: 'post',
			  url:"/library/subject/"+subjectId,
			  dataType: 'json',
			  success: function(result){
				  if(result.entity!=null&&result.entity.length>0){
					  var subjectList = result.entity;
					  for(var i=0;i<subjectList.length;i++){ 
					  		$(DragElement).next("section").find(".whiteBgLm").after
					  		("<a href='javascript:selectSubject("+subjectList[i].subjectId+")' >"+subjectList[i].subjectName+"</a>");
					  		 $(DragElement).next("section").find(".LMSwrap").show();
				      }
				  }else{
					  $(DragElement).next("section").find(".LMSwrap").hide();
				  }
			  }
		});
	});
});

//选择专业
function selectSubject(subjectId){
	$("#hiddenSubjectId").val(subjectId);
	$("#searchForm").submit();
}
//使用swf播放时判断是否安装flash
function pdfRead(id){
	if(testingFlash()){
		window.location.href='${ctx}/library/info/'+id;
	}else{
		dialog("提示","您的浏览器还没有安装flash插件，请安装falsh插件后再阅读！",4);
	}
}
/**
 * 检测电脑是否安装了flash
 * @returns {___anonymous44316_44376}
 */
function flashChecker(){
    var hasFlash = 0; //是否安装了flash  
    var flashVersion = 0; //flash版本  
    if(document.all) {  
        var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');  
        if(swf) {  
            hasFlash = 1;  
            VSwf = swf.GetVariable("$version");  
            flashVersion = parseInt(VSwf.split(" ")[1].split(",")[0]);  
        }  
    }else{  
        if(navigator.plugins && navigator.plugins.length > 0) {  
            var swf = navigator.plugins["Shockwave Flash"];  
            if(swf) {  
                hasFlash = 1;  
                var words = swf.description.split(" ");  
                for(var i = 0; i < words.length; ++i) {  
                    if(isNaN(parseInt(words[i]))) continue;  
                    flashVersion = parseInt(words[i]);  
                }  
            }  
        }  
    }  
    return {  
        f: hasFlash,  
        v: flashVersion  
    };  
}

/**
 * 返回true表示已经安装了Flash
 * 返回flase表示没有安装Flash
 * @returns {Boolean}
 */
function testingFlash(){
	var fls = flashChecker();  
    if(fls.f) {
    	return true;
    }else{
    	return false;
    } 
}
</script>
</head>
<body>
	<form id="searchForm" action="${ctx}/library/list" method="post">
		<input type="hidden" id="pageCurrentPage" name="page.currentPage" value="${page.currentPage}" />
		<input type="hidden" id="hiddenSubjectId" name="queryLibrary.subjectId" value="${queryLibrary.subjectId }"/>
		<input type="hidden" id="orderNum" name="queryLibrary.orderNum" value="${queryLibrary.orderNum}"/>
		<%-- <input type="hidden" name="queryLibrary.name" value="${queryLibrary.name}">--%>
	</form>
	<!-- /ads-1000x120 结束 -->
	<!-- /课程列表 -->
	<div class="">
		<div class="library-banner">
			<img src="${ctximg}/static/edu/images/library/wkBanner.jpg" width="1000" height="300" class="disIb" />
		</div>
		<section class="container">
			<section class="path-wrap txtOf hLh30">
				<a class="c-999 fsize14" title="" href="/">首页</a> 
				 \ <span class="c-333 fsize14">文库列表</span>
			</section>
			<div class="libraryMenu mt20">
				<aside class="libraryMenuBox">
					<dl>
						<dt>
							<a href="${ctx}/library/list" class="parentA">全部文库</a>
						</dt>
						<c:forEach items="${subjectList}" var="subject">
							<dd>
								<a href="javascript:selectSubject(${subject.subjectId})" name="${subject.subjectId}" class="parentA" id="subjectATag">${subject.subjectName}</a>
								<section class="LMsubBox">
									<section class="LMSwrap">
										<span class="whiteBgLm">&nbsp;</span>
									</section>
								</section>
							</dd>
                        </c:forEach>
					</dl>
				</aside>
				<section class="mt20">
					<h3 class="of a-title unFw">
						<font class="c-red f-fM fsize16">热门文库推荐</font>
					</h3>
					<c:forEach items="${hotLibrary}" var="hLibrary" >
						<section class="hc-list-1" style="padding: 10px 0 0;">
							<ol>
								<li><a title="${hLibrary.name}" href="${ctx}/library/info/${hLibrary.id}">${hLibrary.name}</a></li>
							</ol>
						</section>
					</c:forEach>
				</section>
			</div>
			<div class="libraryCont">
				<!--手机端导航开始  -->
			<section class="c-sort-box library-phone-select">
				<section class="c-s-dl">
					<c:if test="${queryLibrary.subjectId!=null and queryLibrary.subjectId!=0}">
						<dl class="c-s-fir">
							<dt><span class="c-666 fsize14">已选：</span></dt>
							<dd class="c-s-dl-li">
								<c:if test="${queryLibrary.subjectId!=null and queryLibrary.subjectId!=0 }">
									<ul class="f-list">
										<li><a title="${subject.subjectName}" href="javascript:void(0)"><span class="a-text1">按班型：</span><span class="a-text2">${subject.subjectName}</span><span class="a-img" onclick="selectSubject(0)"></span></a></li>
									</ul>
								</c:if>
								<aside class="c-s-del dContent">
									<a class="dClose" title="全部撤销" href="javascript:selectSubject(0)"> </a>
								</aside>
								<div class="clear"></div>
							</dd>
						</dl>
					</c:if>
					<dl>
						<div class="fl-wrap">
							<dt><span class="c-666 fsize14">按班型：</span></dt>
							<dd class="c-s-dl-li">
								<ul class="clearfix">
									<li <c:if test="${empty queryLibrary.subjectId||queryLibrary.subjectId==0}">class="current"</c:if>><a href="javascript:void(0)" onclick="selectSubject(0)" title="">全部</a></li>
									<c:forEach items="${subjectList }"  var ="subject">
										<li <c:if test="${queryLibrary.subjectId==subject.subjectId||parentId==subject.subjectId}">class="current"</c:if>><a onclick="selectSubject(${subject.subjectId})" id="${subject.subjectId}" title="${subject.subjectName }" href="javascript:void(0);">${subject.subjectName }</a></li>
									</c:forEach>
								</ul>
								<aside class="c-s-more">
									<a href="javascript: void(0)" title="" class="fsize14 c-master">[展开]</a>
								</aside>
								<c:if test="${sonSubjectList!=null&&sonSubjectList.size()>0 }">
									<div class="c-second-li">
										<ul class="clearfix">
											<c:forEach items="${sonSubjectList }"  var ="sonSubject">
												<li <c:if test="${queryLibrary.subjectId==sonSubject.subjectId}">class="current"</c:if>><a onclick="selectSubject(${sonSubject.subjectId},true)" title="${sonSubject.subjectName }" href="javascript:void(0);">${sonSubject.subjectName }</a></li>
											</c:forEach>
										</ul>
									</div>
								</c:if>
							</dd>
						</div>
					</dl>
					<div class="clear"></div>
				</section>
				<div class="js-wrap">
					<section class="fr">
						<span class="c-ccc">
							<tt class="c-master f-fM">${page.currentPage}</tt>/<tt class="c-666 f-fM">${page.totalPageSize}</tt>
						</span>
					</section>
					<section class="fl">
						<ol class="js-tap clearfix">
							<li><span class="fsize14 c-666">排序：</span></li>
							<li <c:if test="${queryLibrary.orderNum==2 or queryLibrary.orderNum==0}">class="current"</c:if>><a href="javascript:void(0)" onclick="orderLibrary(this,2)" title="最新">最新</a></li>
							<li <c:if test="${queryLibrary.orderNum==1}">class="current"</c:if>><a href="javascript:void(0)" onclick="orderLibrary(this,1)" title="关注度">关注度</a></li>
						</ol>
					</section>
				</div>
			</section>
			<!--手机端导航结束 -->
					<section class="comm-title-2 mt20 unBg ml20 library-select" style="padding: 0;">
						<div class="clearfix pl20 pr20">
							<section class="fr c-999">
								<span class="vam disIb mr20">
									<font class="c-666 fsize12">检索： 共搜索到<tt class="c-red ml5 mr5">${page.totalResultSize}</tt>条结果</font>
								</span>
								|
								<span class="vam disIb ml20">
									<tt class="vam c-666 fsize14"><font class="c-red">${page.currentPage}</font>/${page.totalPageSize}</tt>
									<tt class="vam u-d-page ml5">
										<a href="" title="上一页" class="shang">上一页</a>
										<a class="xia" href="" title="下一页">下一页</a>
									</tt>
								</span>
								
							</section>
							<script type="text/javascript">
									var str = "";
									if('${page.currentPage}'>=2){
									str +='goPage(${page.currentPage-1})';
									}else{
										str +='void(0)';
									}
									$(".shang").attr("href","javascript:"+str);
									str = "";
									if('${page.currentPage+1}'<='${page.totalPageSize}'){
										str+='goPage(${page.currentPage+1})';
									}else{
										str +='void(0)';
									}
									$(".xia").attr("href","javascript:"+str);
							</script>
							<section class="fl">
								<ul class="of sub-sort">
								 	<li><span class="c-999">排序：</span></li>
								 	<li class="sub-s-wrap">
										<a id="orderAid2" href="javascript:void(0)" onclick="orderLibrary(this,2)" title="最新">最新</a>
										<a id="orderAid1" href="javascript:void(0)" onclick="orderLibrary(this,1)" title="关注度">关注度</a>
								 	</li>
								 </ul>
							</section>
						</div>
					</section>
					<c:if test="${page.totalResultSize==0}">
						<div class="mt40">
							<!-- /无数据提示 开始-->
							<section class="no-data-wrap">
								<em class="no-data-ico">&nbsp;</em>
								<span class="c-666 fsize14 ml10 vam">对不起，此条件下还没有相关文库！</span>
							</section>
						</div>
					</c:if>
					<c:forEach items="${librarys}" var="library" varStatus="index">
						<div class="lib-list-box">
							 <div class="library-list">
								 <div class="lib-pic-box">
								 	<section class="library-pics">
								 		<a href="${ctx}/library/info/${library.id}"><img src="<%=staticImageServer%>${library.imgUrl}" width="220" /></a>
								 	</section>
							 	</div>
							 	<div class="clearfix library-desc">
							 		<section class="fl" >
							 			<h4 class="mt5 hLh30 txtOf">
							 				<img 
							 					<c:if test="${library.type==1}">src="${ctximg}/static/edu/images/library/pdf.png"</c:if>
							 					class="vam" width="16" height="18"  />
									 		<a href="${ctx}/library/info/${library.id}" class="c-master fsize16 vam">${library.name}</a>
									 	</h4>
							 			<p class="c-666 hLh30 txtOf">${library.intro}</p>
							 			<p class="c-999 mt10"><tt class="c-999">${library.subjectName}</tt><tt class="c-999 ml20">浏览数：${library.browseNum}次</tt></p>
							 		</section>
							 		<section class="fr">
								 		<div class="mt5 pr hLh20">
										 	<c:if test="${library.type==1}"><section class="lb-sort b-master"><a href="javascript:void(0)" onclick="pdfRead(${library.id})" class="c-master fsize16 vam"><span class="fsize14 f-fM c-fff">PDF阅读</span></a></section></c:if>
									 		<c:if test="${library.type==2}"><section class="lb-sort b-green"><span class="fsize14 f-fM c-fff">PIC阅读</span></section></c:if>
									 	</div>
							 		</section>
							 	</div>
							 	<div class="clear"></div>
							 </div>
						</div>
						 </c:forEach>
						 </div>
						 <div class="clear"></div>
						 <section class="mt50">
							<div class="pagination pagination-large tac">
		                  		<jsp:include page="/WEB-INF/view/common/web_page.jsp" /> 
							</div>
						</section>
				</section>
			</div>
	<!-- /课程列表 结束 -->
		<div class="clear"></div>
	<br>
	<br>
	<br>
<script type="text/javascript">
	$(function(){
		cSortFun();//课程列表的展开收合
	})
</script>
</body>
</html>
