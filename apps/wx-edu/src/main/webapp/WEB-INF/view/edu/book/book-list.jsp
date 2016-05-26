<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>图书中心</title>
<script type="text/javascript">
$(function (){
	var order = '${queryBookCondition.orderNum}';
	var reconent ='${queryBookCondition.bookName}';
	var subjectId ='${queryBookCondition.bookSubjectid}';
	var subjectName='${queryBookCondition.subjectName}';
	//图书搜索内容放入上部搜索框
	$(".tscInp").val('${queryBookCondition.bookName}');
	subjectId = subjectId.trim();
	//设置 显示检索条件。开始------------------
	var queryConditionConent='';
	if(subjectName!=''){
		queryConditionConent+=subjectName+'(专业)';
	}
	if(order==1){
		queryConditionConent+='最新';
	}else if(order==2){
		queryConditionConent+='价格';
	}else if(order==3){
		queryConditionConent+='价格(升序)';
	}else if(order==4){
		queryConditionConent+='价格(降序)';
	}
	if(reconent!=null && reconent.trim()!=''){
		if(queryConditionConent!=''){
			queryConditionConent=reconent+'+'+queryConditionConent;
		}else{
			queryConditionConent=reconent;
		}
	}
	$("#retrievalContentId").text(queryConditionConent);
	$("#retrievalContentId").attr("title",queryConditionConent);
	//设置 显示检索条件。结束------------------
	if(order=='1'||order=='2'){
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
	
	$(".suMore").click(function() {
		var _this = $(this),
			_uList = _this.parent().siblings(".select-box2-mid");
		if (_uList.height() <= "40") {
			_uList.css("height" , "auto");
			_this.text("收起↑");
		} else {
			_uList.css("height" , "40");
			_this.text("更多↓");
		}
		return false;
	})
	
	var ik = 0;
	setInterval(function() {
		$(".bookBanner").css("background-position" , --ik + "px");
	}, 10);
	
	$(".libraryMenuBox>dl>dd").each(function() {
		$(this).hover(function() {
			$(this).addClass("hover");
		}, function() {
			$(this).removeClass("hover");
		})
	})
	
});

function orderBook(obj,val){
	if(val!=3){
		if($(obj).hasClass("current")){
			return;
		}
		$("#orderNum").val(val);
	}else{
		var val = $("#orderNum").val();
		if(val==3){
			$("#orderNum").val('4');
		}else{
			$("#orderNum").val('3');
		}
	}
	$("#searchForm").submit();
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
			  url:"${ctx}/book/subject/"+subjectId,
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
</script>
</head>
<body>
	<!-- /ads-1000x120 结束 -->
	<!-- /课程列表 -->
	<div>
		<div class="bookBannerWrap">
			<div class="bookBanner">
				<section class="w1000">
					<section class="bbTxt">&nbsp;</section>
				</section>
			</div>
		</div>
		<div class="b-fff">
		<section class="w1000">
			<div class="mt20 pr">
				<div class="pathwray">
					<ol class="clearfix c-brow f-fM fsize14">
						<li><a href="/" title="首页" class="c-brow">首页</a> &gt;</li>
						<li><span>图书列表</span></li>
					</ol>
				</div>
				<section class="c-search pa">
					<div class="search clearfix" style=" display:none;">
						<section class="fr s-inp-wrap">
							<form id="searchForm" action="${ctx}/book/list" method="post">
								<input type="hidden" name="page.currentPage" value="1" id="pageCurrentPage"/>
								<input type="hidden" id="hiddenSubjectId" name="queryBookCondition.bookSubjectid" value="${queryBookCondition.bookSubjectid}"/>
								<input type="hidden" id="hiddenDisProperty" name="queryBookCondition.disProperty" value="${queryBookCondition.disProperty}"/>
								<!-- 排序条件隐藏域 -->
								<input type="hidden" id="orderNum" name="queryBookCondition.orderNum" value="${queryBookCondition.orderNum}"/> 
								<input type="hidden" name="queryBookCondition.bookName" value="${queryBookCondition.bookName}">
							</form>
						</section>
					</div>
				</section>
			</div>
			<div class="mt30">
				<!-- 图书分类目录 开始 -->
				<div class="booksortMenu">
					<aside class="libraryMenuBox">
						<dl>
							<dt><a href="${ctx}/book/list" class="parentA">全部图书分类</a></dt>
							<%-- <c:forEach items="${subjectList}" var="sub">
								<dd>
									<a href="javascript:selectSubject(${sub.subjectId })" 
										class="parentA subjectATag" name="${sub.subjectId }" id="subjectAId${sub.subjectId }">${sub.subjectName}
									</a>
									<section class="LMsubBox">
										<section class="LMSwrap">
											<span class="whiteBgLm">&nbsp;</span>
											
										</section>
									</section> 
								</dd>
	                        </c:forEach> --%>
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
				</div>
				<!-- 图书分类目录 结束 -->
				<div class="bookCont">
					<div class="pl20">
						<section class="comm-title-2" style="background-color: #FFFBF5;filter: none;">
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
									 		<a id="orderAid1" href="javascript:void(0)" onclick="orderBook(this,1)" title="最新">最新</a>
									 		<!-- <a id="orderAid2" href="javascript:void(0)" onclick="orderBook(this,2)" title="价格">价格</a> -->
									 		<a id="orderAid3" href="javascript:void(0)" onclick="orderBook(this,3)" title="价格">价格<em class="icon14 u-jg">&nbsp;</em></a>
									 	</li>
									 </ul>
								</section>
							</div>
						</section>
						<!-- 图书列表 开始 -->
						<div class="Blist">
							<c:if test="${empty bookList}">
							<section class="comm-tips-1">
								<p>
									<em class="vam c-tips-1">&nbsp;</em>
									<font class="c-999 fsize12 vam">对不起，此条件下还没有相关图书！</font>
								</p>
							</section>
							</c:if>
							<c:forEach items="${bookList}" var="book" varStatus="index">					
								<article class="BLbox">
									<dl>
						                <dt class="bb-name">
						                	<a title="${book.bookName }" href="${ctx}/book/info/${book.bookId }" target="_blank">${book.bookName }</a>
						                </dt>
						                <dd class="bb-price">
						                    <div class="dt fl">售 &#12288;价：</div>
						                    <div class="dd fl"><strong>￥${book.nowPrice }</strong><span class="discount">（${book.rebatePrice }折）</span></div>
						                </dd>
						                <dd class="bb-market">
						                    <div class="dt fl">定&#12288;价：</div>
						                    <div class="dd fl"><del>￥${book.price }</del></div>
						                </dd>
						                <div class="clear"></div>
						            </dl>
						            <ul class="summary">
						                <li>
						                    <div class="dt">作&#12288;&#12288;者：</div>
						                    <div class="dd"> ${book.author }</div>
						                </li>
						
						                <li>
						                        <div class="dt">出  版  社：</div>
						                        <div class="dd">
						                            	${book.press }
						                        </div>
						                </li>
						                <li>
						                    <div class="dt">出版时间：</div>
						                    <div class="dd"><fmt:formatDate value="${book.publishTime }" type="both" pattern="YYYY-MM-dd"/></div>
						                </li>
						                <li>
						                    <div class="dt">分&#12288;&#12288;类：</div>
						                    <div class="dd">${book.subjectName }</div>
						                </li>
						            </ul>
						            <div class="bb-img">
						                <a title="${book.bookName }" href="${ctx}/book/info/${book.bookId }" target="_blank">
						                    <img width="160" height="160" alt="${book.bookName }" src="<%=staticImageServer%>${book.bookImg }"/>
						                </a>
		                            </div>
		                            <div class="bb-btns">
		                            	<a href="${ctx}/book/info/${book.bookId }" target="" title="查看详情" class="brow-btn mt10">查看详情</a>
		                            	<a href="${book.payUrl}" target="_blank" title="链接购买" class="brow-btn mt10">链接购买</a>
		                            </div>
								</article>
							</c:forEach>	
						</div>						
						<!-- 图书列表 结束 -->
						<section>
							<div class="pagination pagination-large tac">
		                  		<jsp:include page="/WEB-INF/view/common/page.jsp" /> 
							</div>
						</section>
					</div>
				</div>
				</div>
				<div class="clear"></div>
		</section>
		</div>
	</div>
	<!-- /课程列表 结束 -->
	<br>
	<br>
	<br>
</body>
</html>
